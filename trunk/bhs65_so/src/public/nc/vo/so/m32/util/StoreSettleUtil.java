package nc.vo.so.m32.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import nc.bs.dao.BaseDAO;
import nc.bs.framework.common.NCLocator;
import nc.impl.pub.util.db.InSqlManager;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ResultSetProcessor;
import nc.vo.bhs.sostore.ItsStoreInOutVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;

public class StoreSettleUtil {

//	public final static String PAYBY_FIXBYSPACE = "1001V810000000069FKZ";
//	public final static String PAYBY_PAYPERUSE = "1001V810000000069FL0";
//	public final static String PAYBY_FIXEDINCREMENTAL = "1001V81000000006BKWP";
//	public final static String SPACEUNIT_M2 = "1001V810000000000LGZ";
//	public final static String SPACEUNIT_M3 = "1001V810000000000LH0";
//	public final static String DURATIONUNIT_PERDAY = "1001V810000000069FMC";
//	public final static String DURATIONUNIT_PERWEEK = "1001V810000000069FMD";
//	public final static String DURATIONUNIT_PERMONTH = "1001V810000000069FME";
	public final static String FLD_RENTFROM = SaleInvoiceHVO.VDEF18;
	public final static String FLD_RENTTO = SaleInvoiceHVO.VDEF19;
	public final static String FLD_ISFINALBILL = SaleInvoiceHVO.VDEF20;
//	public final static String FLD_PAYBY = SaleInvoiceBVO.VBDEF6;
	public final static String FLD_DURATION = SaleInvoiceBVO.VBDEF7;
	public final static String FLD_DURATION_UNIT = SaleInvoiceBVO.VBDEF8;
	public final static String FLD_MINIMUM_SPACE = SaleInvoiceBVO.VBDEF9;
	public final static String FLD_MINIMUM_DURATION = SaleInvoiceBVO.VBDEF10;
	public final static String FLD_FIXED_INCREMENTAL = SaleInvoiceBVO.VBDEF11;
	public final static String FLD_IS_FIXED_INCREASE = SaleInvoiceBVO.VBDEF12;
//	public final static String FLD_SPACEUNIT = SaleInvoiceBVO.VBDEF3;
	public final static String FIXED_AMOUNT = "10";
	public final static String FIXED_SPACE_PERDAY = "21";
	public final static String FIXED_SPACE_PERWEEK = "22";
	public final static String FIXED_SPACE_PERMONTH = "23";
	public final static String PER_SQFT_PERDAY = "31";
	public final static String PER_SQFT_PERWEEK = "32";
	public final static String PER_SQFT_PERMONTH = "33";
	public final static String PER_M3_PERDAY = "41";
	public final static String PER_M3_PERWEEK = "43";
	public final static String PER_M3_PERMONTH = "42";
	public final static String PER_M2_PERDAY = "51";
	public final static String PER_M2_PERWEEK = "52";
	public final static String PER_M2_PERMONTH = "53";
	public final static String PER_SQFT_PERDAY_BYMONTH = "61";
	public final static String PER_SQFT_PERDAY_BYYEAR = "62";


	/**
	 * 判断是否是Storage, 如果是，默认不是最后一期账单
	 * 
	 * @param vos
	 * @throws BusinessException
	 */
	public void processStorage(SaleInvoiceVO[] vos) throws BusinessException {
		for (SaleInvoiceVO vo : vos) {
			SaleInvoiceHVO hvo = vo.getParentVO();
			SaleInvoiceBVO[] bvos = vo.getChildrenVO();
			Set<String> saleorderids = new HashSet<String>();
			for (SaleInvoiceBVO bvo : bvos) {
				saleorderids.add(bvo.getCsrcid());
			}
			if (isStorage(saleorderids)) {
				// 如果是仓库租赁结算的发票，默认不是最后一期账单
				hvo.setAttributeValue(FLD_ISFINALBILL,
						UFBoolean.FALSE.toString());
				UFDate beginDate = null;
				// 上次结算结束日期
				UFDate lastSettleEndDate = getLastSettleEndDate(saleorderids);
				if (lastSettleEndDate != null) {
					beginDate = lastSettleEndDate.getDateAfter(1);
				}else{//如果一次也没有结算，也没有指定开始日期，则取最早进场日期作为结算开始日期
					beginDate = getMinimumInbounddate(saleorderids);
				}
				hvo.setAttributeValue(FLD_RENTFROM, beginDate.toString());
				//结算结束日期默认为当天
				hvo.setAttributeValue(FLD_RENTTO, new UFDate().toString());
			} else {
				// 如果是其他类型的发票，默认为最后一期账单
				hvo.setAttributeValue(FLD_ISFINALBILL,
						UFBoolean.TRUE.toString());
			}
		}

	}

	/**
	 * 结算，计算租赁数量和单价
	 * 
	 * @param vo
	 * @throws BusinessException
	 */
	public SettleReturnVO settle(SaleInvoiceVO vo) throws BusinessException {
		SettleReturnVO returnVO = new SettleReturnVO();
		UFDouble settleQty = UFDouble.ZERO_DBL;
		
		SaleInvoiceHVO hvo = vo.getParentVO();
		SaleInvoiceBVO[] bvos = vo.getChildrenVO();
		Set<String> saleorderids = new HashSet<String>();
		SaleInvoiceBVO storageBvo = null;
		for (SaleInvoiceBVO bvo : bvos) {
			saleorderids.add(bvo.getCsrcid());
			if (bvo.getAttributeValue(FLD_DURATION_UNIT) != null) {
				storageBvo = bvo;
			}
		}

		if (storageBvo == null) {
			return returnVO;
		}
		
		// 本次结算开始日期
		UFDate beginDate = null;
		// 本次结算结束日期
		UFDate endDate = null;
		String rentFrom = (String) hvo.getAttributeValue(FLD_RENTFROM);
		if (rentFrom != null) {
			beginDate = new UFDate(rentFrom);
			beginDate = beginDate.asBegin();
		}
		String rentTo = (String) hvo.getAttributeValue(FLD_RENTTO);
		if (rentTo != null) {
			endDate = new UFDate(rentTo);
			endDate = endDate.asEnd();
		}

		if (beginDate == null || endDate == null) {
			ExceptionUtils
					.wrappBusinessException("Please input [Rent From],[Rent To].");
		}

		// 查询ITS进退场信息
		Collection<ItsStoreInOutVO> storeItems = getStoreInOutVO(
				saleorderids, beginDate, endDate);
		if (storeItems == null || storeItems.size() < 1) {
			return returnVO;
		}

		Map<String, String> durationMap = getDurationMap();
		// 计租方式（3大类：Fixed Amount, Fixed Space by duration, Pay by use by duration)
		String durationUnit = (String) storageBvo
				.getAttributeValue(FLD_DURATION_UNIT);
		durationUnit = durationMap.get(durationUnit);
		// 租期计价单位(duration) 默认为 1， 比如每10天多少钱、每2周、每月等
		Integer duration = storageBvo.getAttributeValue(FLD_DURATION) == null ? 1 : new Integer(
				(String) storageBvo.getAttributeValue(FLD_DURATION));
		// 最小租赁空间
		UFDouble minimumSpace = storageBvo.getAttributeValue(FLD_MINIMUM_SPACE) == null ? UFDouble.ZERO_DBL
				: new UFDouble(
						(String) storageBvo
								.getAttributeValue(FLD_MINIMUM_SPACE));
		
		// 最小租赁的时间 即最少要租多少个租期计价单位，比如约定的每10天多少钱，至少租2个duration,那就是20天
		Integer minimumDuration = storageBvo
				.getAttributeValue(FLD_MINIMUM_DURATION) == null ? 0
				: new Integer(
						(String) storageBvo
								.getAttributeValue(FLD_MINIMUM_DURATION));
		// 是否固定增长(场景：按每个Pallet或每个canton来算多少sqft/m2/m3/，仓库是按pallet/canton计数，根据这个数量*增量，得出租赁空间数量)
		UFBoolean isFixedIncrease = storageBvo.getAttributeValue(FLD_IS_FIXED_INCREASE) == null ? UFBoolean.FALSE 
				: new UFBoolean((String)storageBvo.getAttributeValue(FLD_IS_FIXED_INCREASE));
		// 固定增长的增量
		UFDouble fixedIncremental = storageBvo.getAttributeValue(FLD_FIXED_INCREMENTAL) == null ? UFDouble.ONE_DBL
				: new UFDouble(
						(String) storageBvo
								.getAttributeValue(FLD_FIXED_INCREMENTAL));
		
		
		// 计算租赁数量
		// 第一种情况：FIXED_AMOUNT，如果是固定金额，直接返回订单上的数量
		UFDouble qty = storageBvo.getNastnum();
		if(durationUnit == null
				|| FIXED_AMOUNT.equals(durationUnit)){
			settleQty = qty;
			returnVO.setSettleQty(settleQty);
			return returnVO;
		}
		
		// 其他情况：
		Integer durationCnt = 0;
		Integer durationUnitCnt = 0;
		UFDouble space = UFDouble.ZERO_DBL;

		// 获取结算期间内有多少个duration unit
		durationUnitCnt = getDurationUnitCnt(beginDate, endDate,
				durationUnit);
		// 获取有多少个duration, 不足1个duration 按1个duration算
		durationCnt = new UFDouble(durationUnitCnt).div(
				new UFDouble(duration), 0, UFDouble.ROUND_UP).intValue();
		// 判断是否小于Minimum Duration，如果小于，则取Minimum Duration
		if (durationCnt < minimumDuration) {
			durationCnt = minimumDuration;
		}

		// 第二种情况：Fixed space, 则直接用 space * duration
		if (isFixedSpace(durationUnit)) {
			space = qty;
			//如果是固定增量，qty * 增量（场景：租固定的几个Pallet, 每个Pallet算多少m2/m3/sqft)
			if(UFBoolean.TRUE.equals(isFixedIncrease)){
				space = space.multiply(fixedIncremental);
			}
			settleQty = space.multiply(durationCnt);
		}
		// 第三种情况：Pay by use, 则分别计算每个duration里的space，最后再汇总
		else {
			// 每个duration的开始时间
			UFDate durationBeginDate = beginDate;
			// 每个duration的结束时间
			UFDate durationEndDate = null;
			for (int i = 0; i < durationCnt; i++) {
				// 获取duration的结束时间
				durationEndDate = getDateAfterDuration(durationBeginDate,
						duration, durationUnit);
				// 获取每个duration期间内占用的sqft/m2/m3
				space = getSpace(storeItems, durationBeginDate,
						durationEndDate, durationUnit, isFixedIncrease);
				// 判断是否小于Minimum Space，如果小于，则取Minimum Space
				if (space.compareTo(minimumSpace) < 0) {
					space = minimumSpace;
				}
				// 如果是固定增量 使用空间=数量*固定增量
				if(UFBoolean.TRUE.equals(isFixedIncrease)){
					space = space.multiply(fixedIncremental);
				}
				// 每个duration租赁的空间汇总
				settleQty = settleQty.add(space);

				durationBeginDate = durationEndDate.getDateAfter(1);
				durationBeginDate.asBegin();
			}
		}
		
		returnVO.setSettleQty(settleQty);
		
		// 第四情况，租赁空间数量同上，但月单价需要转成日单价
		if(isMonth2Day(durationUnit)){
			UFDouble settlePrice = storageBvo.getNqtorigprice();
			settlePrice = month2DayPrice(settlePrice, durationUnit, endDate);
			returnVO.setSettlePrice(settleQty);
		}

		return returnVO;
	}


	private UFDouble month2DayPrice(UFDouble settlePrice, String durationUnit,
			UFDate endDate) {
		if(PER_SQFT_PERDAY_BYMONTH.equals(durationUnit)){
			//日单价 = 月单价 / 当月天数
			int monthDays = UFDate .getDaysMonth(endDate.getYear(), endDate.getMonth());
			settlePrice = settlePrice.div(monthDays);
		}else{
			//日单价 = 月单价 * 12 / 365
			int yearDays = 365;
			settlePrice = settlePrice.multiply(12).div(yearDays);
		}
		return settlePrice;
	}

	private boolean isMonth2Day(String durationUnit) {
		return PER_SQFT_PERDAY_BYMONTH.equals(durationUnit) 
				|| PER_SQFT_PERDAY_BYYEAR.equals(durationUnit) ;
	}

	private boolean isFixedSpace(String durationUnit) {
		return FIXED_SPACE_PERDAY.equals(durationUnit) 
				|| FIXED_SPACE_PERWEEK.equals(durationUnit)
				|| FIXED_SPACE_PERMONTH.equals(durationUnit) ;
	}

	/**
	 * 获取某几天、某几周、某几月后的一天 周、月按自然周和自然月，即取某几周、某几月的最后一天
	 */
	private UFDate getDateAfterDuration(UFDate durationBeginDate,
			Integer duration, String durationUnit) {
		UFDate durationEndDate = null;
		if (isByPerDay(durationUnit)) {
			durationEndDate = durationBeginDate.getDateAfter(duration - 1);
		} else if (isByPerWeek(durationUnit)) {
			durationEndDate = getDateAfterWeeks(durationBeginDate, duration);
		} else if (isByPerMonth(durationUnit)) {
			durationEndDate = getDateAfterMonths(durationBeginDate, duration);
		}
		return durationEndDate.asEnd();
	}

	private static UFDate getDateAfterMonths(UFDate durationBeginDate,
			Integer duration) {
		int year = durationBeginDate.getYear();
		int month = durationBeginDate.getMonth();
		for (int i = 0; i < duration; i++) {
			month = month + i;
			if (month > 12) {
				month = 1;
				year = year + 1;
			}
		}
		int date = UFDate.getDaysMonth(year, month);
		return new UFDate(year + (month < 10 ? "-0" + month : "-" + month)
				+ "-" + date);

	}

	private static UFDate getDateAfterWeeks(UFDate durationBeginDate,
			Integer duration) {
		int week = durationBeginDate.getWeek() == 0 ? 7 : durationBeginDate
				.getWeek();
		if (week != 1) {
			duration = duration - 1;
			durationBeginDate = durationBeginDate.getDateAfter(7 - week + 1);
		}
		return durationBeginDate.getDateAfter(duration * 7 - 1);
	}

	private Integer getDurationUnitCnt(UFDate beginDate, UFDate endDate,
			String durationUnit) {
		Integer durationUnitCnt = 0;
		if (isByPerDay(durationUnit)) {
			durationUnitCnt = UFDate.getDaysBetween(beginDate, endDate) + 1;
		} else if (isByPerWeek(durationUnit)) {
			durationUnitCnt = getWeeksBetween(beginDate, endDate);
		} else if (isByPerMonth(durationUnit)) {
			durationUnitCnt = getMonthsBetween(beginDate, endDate);
		}
		return durationUnitCnt;
	}

	private boolean isByPerMonth(String durationUnit) {
		return FIXED_SPACE_PERMONTH.equals(durationUnit)
				||PER_SQFT_PERMONTH.equals(durationUnit)
				||PER_M3_PERMONTH.equals(durationUnit)
				||PER_M2_PERMONTH.equals(durationUnit);
	}

	private boolean isByPerWeek(String durationUnit) {
		return FIXED_SPACE_PERWEEK.equals(durationUnit)
				||PER_SQFT_PERWEEK.equals(durationUnit)
				||PER_M3_PERWEEK.equals(durationUnit)
				||PER_M2_PERWEEK.equals(durationUnit);
	}

	private boolean isByPerDay(String durationUnit) {
		return FIXED_SPACE_PERDAY.equals(durationUnit)
				||PER_SQFT_PERDAY.equals(durationUnit)
				||PER_M3_PERDAY.equals(durationUnit)
				||PER_M2_PERDAY.equals(durationUnit)
				||PER_SQFT_PERDAY_BYMONTH.equals(durationUnit)
				||PER_SQFT_PERDAY_BYYEAR.equals(durationUnit);
	}

	private Integer getMonthsBetween(UFDate beginDate, UFDate endDate) {
		int beginYear = beginDate.getYear();
		int beginMonth = beginDate.getMonth();
		int endYear = endDate.getYear();
		int endMonth = endDate.getMonth();
		int duration = 0;
		if (beginYear == endYear) {
			duration = endMonth - beginMonth + 1;
		} else {
			duration = 12 - beginMonth + 1;
			beginYear++;
			while (beginYear < endYear) {
				duration = duration + 12;
				beginYear++;
			}
			duration = duration + endMonth;
		}
		return duration;
	}

	private Integer getWeeksBetween(UFDate beginDate, UFDate endDate) {
		int duration = 0;
		int beginWeek = beginDate.getWeek() == 0 ? 7 : beginDate.getWeek();
		// 首尾不满一周，各算一周
		if (beginWeek != 1) {// 获取下个周一
			duration++;
			beginDate = beginDate.getDateAfter(7 - beginWeek + 1);
		}
		int days = UFDate.getDaysBetween(beginDate, endDate) + 1;
		duration = duration
				//不满一周算一周
				+ new UFDouble(days).div(new UFDouble(7), 0, UFDouble.ROUND_UP)
						.intValue();
		return duration;
	}

	private UFDouble getSpace(Collection<ItsStoreInOutVO> storeItems,
			UFDate durationBeginDate, UFDate durationEndDate, String durationUnit, UFBoolean isFixedIncrease) {
		Iterator<ItsStoreInOutVO> iterator = storeItems.iterator();
		ItsStoreInOutVO vo = null;
		UFDouble space = UFDouble.ZERO_DBL;
		UFDouble m2 = UFDouble.ZERO_DBL;
		UFDouble m3 = UFDouble.ZERO_DBL;
		UFDouble sqft = UFDouble.ZERO_DBL;
		UFDouble qty = UFDouble.ZERO_DBL;
		while (iterator.hasNext()) {
			vo = iterator.next();
			// 进场(累加在duration结束时间前（含当天）的所有进场数据）
			if (vo.getQty_In() != null
					&& vo.getDate_InOut().compareTo(durationEndDate) < 1) {
				m2 = m2.add(vo.getSpace_m2());
				m3 = m3.add(vo.getCubic_Meter());
				sqft = sqft.add(vo.getSpace_ft());
				qty = space.add(vo.getQty_In());
			}// 退场（减去在duration开始时间前（不含当天）的所有退场数据）
			else if(vo.getQty_Out() != null 
					&& vo.getDate_InOut().compareTo(durationBeginDate) < 0){
				m2 = m2.sub(vo.getSpace_m2());
				m3 = m3.sub(vo.getCubic_Meter());
				sqft = sqft.sub(vo.getSpace_ft());
				qty = space.sub(vo.getQty_In());
			}
		}
		if(UFBoolean.TRUE.equals(isFixedIncrease)){
			space = qty;
		}else if(isByPerM2(durationUnit)){
			space = m2;
		}else if(isByPerM3(durationUnit)){
			space = m3;
		}else if(isByPerSqft(durationUnit)){
			space = sqft;
		}
		return space;
	}
	
	private boolean isByPerSqft(String durationUnit) {
		return PER_SQFT_PERDAY.equals(durationUnit)
				||PER_SQFT_PERWEEK.equals(durationUnit)
				||PER_SQFT_PERMONTH.equals(durationUnit)
				||PER_SQFT_PERDAY_BYMONTH.equals(durationUnit)
				||PER_SQFT_PERDAY_BYYEAR.equals(durationUnit);
	}

	private boolean isByPerM3(String durationUnit) {
		return PER_M3_PERDAY.equals(durationUnit)
				||PER_M3_PERWEEK.equals(durationUnit)
				||PER_M3_PERMONTH.equals(durationUnit);
	}

	private boolean isByPerM2(String durationUnit) {
		return PER_M2_PERDAY.equals(durationUnit)
				||PER_M2_PERWEEK.equals(durationUnit)
				||PER_M2_PERMONTH.equals(durationUnit);
	}
	

	private Collection<ItsStoreInOutVO> getStoreInOutVO(
			Set<String> saleorderids, UFDate beginDate, UFDate endDate)
			throws BusinessException {
		Set<String> jonoSet = getStoreJoNo(saleorderids);
		if (jonoSet == null) {
			return null;
		}
		IUAPQueryBS query = NCLocator.getInstance().lookup(IUAPQueryBS.class);
		StringBuffer condition = new StringBuffer(" Doc_No in ");
		condition.append(InSqlManager.getInSQLValue(jonoSet));
		condition.append(" and Date_InOut <= '");
		condition.append(endDate.toString());
		condition.append("' ");
		//TODO 考虑下，不应该加这个，加了就少出库数据，会多算租金
//		if (beginDate != null) {
//			condition.append(" and (Qty_In is null and Qty_Out is not null and Date_InOut >= '");
//			condition.append(beginDate.toString());
//			condition.append("') ");
//		}

		Collection<ItsStoreInOutVO> vos = query.retrieveByClause(
				ItsStoreInOutVO.class, condition.toString());
		return vos;
	}

	private Set<String> getStoreJoNo(Set<String> saleorderids)
			throws BusinessException {
		IUAPQueryBS query = NCLocator.getInstance().lookup(IUAPQueryBS.class);
		StringBuffer sql = new StringBuffer(
				" select vbillno from bhs_sostore_h where dr=0 and csaleorderid in ");
		sql.append(InSqlManager.getInSQLValue(saleorderids));
		Set<String> jonoSet = (Set<String>) query.executeQuery(sql.toString(),
				new ResultSetProcessor() {
					/**
					 * 
					 */
					private static final long serialVersionUID = -3813245162676282925L;

					@Override
					public Object handleResultSet(ResultSet rs)
							throws SQLException {
						Set<String> jonoSet = new HashSet<String>();
						if (rs != null) {
							while (rs.next()) {
								jonoSet.add(rs.getString(1));
							}
						}
						return jonoSet.size() > 0 ? jonoSet : null;
					}

				});

		return jonoSet;
	}

	private UFDate getMinimumInbounddate(Set<String> saleorderids)
			throws BusinessException {
		UFDate minimumInbounddate = new UFDate();
		// 查询ITS进退场信息
		Collection<ItsStoreInOutVO> storeItems = getStoreInOutVO(
				saleorderids, null, new UFDate());
		if (storeItems == null || storeItems.size() < 1) {
			return minimumInbounddate;
		}
		
		Iterator<ItsStoreInOutVO> iterator = storeItems.iterator();
		ItsStoreInOutVO vo = null;
		while (iterator.hasNext()) {
			vo = iterator.next();
			if (vo.getQty_In() != null 
					&& minimumInbounddate.compareTo(vo.getDate_InOut()) > 0) {
				minimumInbounddate = vo.getDate_InOut();
			}
		}

		return minimumInbounddate;
	}

//	/**
//	 * 如果结算期内已完全退场，endDate为最后退场日期
//	 * 
//	 * @param storeItems
//	 * @param dbilldate
//	 * @return
//	 * @throws BusinessException
//	 */
//	private UFDate getEndDate(Collection<ItsStoreInOutVO> storeItems,
//			UFDate dbilldate) throws BusinessException {
//		Iterator<ItsStoreInOutVO> iterator = storeItems.iterator();
//		ItsStoreInOutVO vo = iterator.next();
//		UFDate outbounddate = vo.getDate_Out();
//		if (outbounddate == null || outbounddate.compareTo(dbilldate) > 0) {
//			return dbilldate;
//		}
//		UFDate outbounddate2 = null;
//		while (iterator.hasNext()) {
//			vo = iterator.next();
//			outbounddate2 = vo.getDate_Out();
//			if (outbounddate2 == null || outbounddate2.compareTo(dbilldate) > 0) {
//				return dbilldate;
//			} else if (outbounddate.compareTo(outbounddate2) < 0) {
//				outbounddate = outbounddate2;
//			}
//		}
//
//		return outbounddate;
//	}

	private UFDate getLastSettleEndDate(Set<String> saleorderids)
			throws BusinessException {
		IUAPQueryBS query = NCLocator.getInstance().lookup(IUAPQueryBS.class);
		StringBuffer sql = new StringBuffer(" select max(h." + FLD_RENTTO
				+ ") from so_saleinvoice h ");
		sql.append(" inner join so_saleinvoice_b b on h.csaleinvoiceid = b.csaleinvoiceid ");
		sql.append(" where h.dr=0 and b.dr=0 and h." + FLD_RENTTO
				+ " != '~' and b.csrcid in  ");
		sql.append(InSqlManager.getInSQLValue(saleorderids));
		UFDate lastbilldate = (UFDate) query.executeQuery(sql.toString(),
				new ResultSetProcessor() {

					/**
					 * 
					 */
					private static final long serialVersionUID = 197625125795172473L;

					@Override
					public Object handleResultSet(ResultSet rs)
							throws SQLException {
						UFDate lastbilldate = null;
						String date = null;
						if (rs != null) {
							while (rs.next()) {
								date = rs.getString(1);
								if (date != null) {
									lastbilldate = new UFDate();
								}
							}
						}
						return lastbilldate;
					}

				});

		return lastbilldate;
	}

	private boolean isStorage(Set<String> saleorderids)
			throws BusinessException {
		IUAPQueryBS query = NCLocator.getInstance().lookup(IUAPQueryBS.class);
		StringBuffer condition = new StringBuffer(" csaleorderid in ");
		condition.append(InSqlManager.getInSQLValue(saleorderids));
		condition
				.append(" and exists(select 1 from so_saleorder_b b inner join bd_material m on b.cmaterialid = m.pk_material where so_saleorder.csaleorderid = b.csaleorderid and m.def20='Y')");
		Collection<SaleOrderHVO> vos = query.retrieveByClause(
				SaleOrderHVO.class, condition.toString(),
				new String[] { "csaleorderid" });
		if (vos != null && vos.size() > 0) {
			return true;
		}
		return false;
	}
	

	private Map<String, String> getDurationMap() throws BusinessException {
		String sql = "select pk_defdoc, code from bd_defdoc where pk_defdoclist in(select pk_defdoclist from bd_defdoclist where code = 'BHS76')";
		
		Map<String, String> durationMap = (Map<String, String>) new BaseDAO().executeQuery(sql, new ResultSetProcessor(){
			@Override
			public Object handleResultSet(ResultSet rs) throws SQLException {
				Map<String, String> itsparaMap = new HashMap<String, String>();
		        while (rs.next()) {
		        	itsparaMap.put(rs.getString(1), rs.getString(2));
		        }
		        return itsparaMap;
			}
			
		});
		
		return durationMap;
	}

	public static void main(String[] args) {
		UFDate date = new UFDate("2018-09-14");
		int duration = 2;
		date = date.getDateAfter(duration);
		System.out.println(date);
		date = getDateAfterWeeks(date, duration);
		System.out.println(date);
		date = getDateAfterMonths(date, duration);
		System.out.println(date);
	}
}
