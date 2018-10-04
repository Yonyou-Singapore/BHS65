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
	 * �ж��Ƿ���Storage, ����ǣ�Ĭ�ϲ������һ���˵�
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
				// ����ǲֿ����޽���ķ�Ʊ��Ĭ�ϲ������һ���˵�
				hvo.setAttributeValue(FLD_ISFINALBILL,
						UFBoolean.FALSE.toString());
				UFDate beginDate = null;
				// �ϴν����������
				UFDate lastSettleEndDate = getLastSettleEndDate(saleorderids);
				if (lastSettleEndDate != null) {
					beginDate = lastSettleEndDate.getDateAfter(1);
				}else{//���һ��Ҳû�н��㣬Ҳû��ָ����ʼ���ڣ���ȡ�������������Ϊ���㿪ʼ����
					beginDate = getMinimumInbounddate(saleorderids);
				}
				hvo.setAttributeValue(FLD_RENTFROM, beginDate.toString());
				//�����������Ĭ��Ϊ����
				hvo.setAttributeValue(FLD_RENTTO, new UFDate().toString());
			} else {
				// ������������͵ķ�Ʊ��Ĭ��Ϊ���һ���˵�
				hvo.setAttributeValue(FLD_ISFINALBILL,
						UFBoolean.TRUE.toString());
			}
		}

	}

	/**
	 * ���㣬�������������͵���
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
		
		// ���ν��㿪ʼ����
		UFDate beginDate = null;
		// ���ν����������
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

		// ��ѯITS���˳���Ϣ
		Collection<ItsStoreInOutVO> storeItems = getStoreInOutVO(
				saleorderids, beginDate, endDate);
		if (storeItems == null || storeItems.size() < 1) {
			return returnVO;
		}

		Map<String, String> durationMap = getDurationMap();
		// ���ⷽʽ��3���ࣺFixed Amount, Fixed Space by duration, Pay by use by duration)
		String durationUnit = (String) storageBvo
				.getAttributeValue(FLD_DURATION_UNIT);
		durationUnit = durationMap.get(durationUnit);
		// ���ڼƼ۵�λ(duration) Ĭ��Ϊ 1�� ����ÿ10�����Ǯ��ÿ2�ܡ�ÿ�µ�
		Integer duration = storageBvo.getAttributeValue(FLD_DURATION) == null ? 1 : new Integer(
				(String) storageBvo.getAttributeValue(FLD_DURATION));
		// ��С���޿ռ�
		UFDouble minimumSpace = storageBvo.getAttributeValue(FLD_MINIMUM_SPACE) == null ? UFDouble.ZERO_DBL
				: new UFDouble(
						(String) storageBvo
								.getAttributeValue(FLD_MINIMUM_SPACE));
		
		// ��С���޵�ʱ�� ������Ҫ����ٸ����ڼƼ۵�λ������Լ����ÿ10�����Ǯ��������2��duration,�Ǿ���20��
		Integer minimumDuration = storageBvo
				.getAttributeValue(FLD_MINIMUM_DURATION) == null ? 0
				: new Integer(
						(String) storageBvo
								.getAttributeValue(FLD_MINIMUM_DURATION));
		// �Ƿ�̶�����(��������ÿ��Pallet��ÿ��canton�������sqft/m2/m3/���ֿ��ǰ�pallet/canton�����������������*�������ó����޿ռ�����)
		UFBoolean isFixedIncrease = storageBvo.getAttributeValue(FLD_IS_FIXED_INCREASE) == null ? UFBoolean.FALSE 
				: new UFBoolean((String)storageBvo.getAttributeValue(FLD_IS_FIXED_INCREASE));
		// �̶�����������
		UFDouble fixedIncremental = storageBvo.getAttributeValue(FLD_FIXED_INCREMENTAL) == null ? UFDouble.ONE_DBL
				: new UFDouble(
						(String) storageBvo
								.getAttributeValue(FLD_FIXED_INCREMENTAL));
		
		
		// ������������
		// ��һ�������FIXED_AMOUNT������ǹ̶���ֱ�ӷ��ض����ϵ�����
		UFDouble qty = storageBvo.getNastnum();
		if(durationUnit == null
				|| FIXED_AMOUNT.equals(durationUnit)){
			settleQty = qty;
			returnVO.setSettleQty(settleQty);
			return returnVO;
		}
		
		// ���������
		Integer durationCnt = 0;
		Integer durationUnitCnt = 0;
		UFDouble space = UFDouble.ZERO_DBL;

		// ��ȡ�����ڼ����ж��ٸ�duration unit
		durationUnitCnt = getDurationUnitCnt(beginDate, endDate,
				durationUnit);
		// ��ȡ�ж��ٸ�duration, ����1��duration ��1��duration��
		durationCnt = new UFDouble(durationUnitCnt).div(
				new UFDouble(duration), 0, UFDouble.ROUND_UP).intValue();
		// �ж��Ƿ�С��Minimum Duration�����С�ڣ���ȡMinimum Duration
		if (durationCnt < minimumDuration) {
			durationCnt = minimumDuration;
		}

		// �ڶ��������Fixed space, ��ֱ���� space * duration
		if (isFixedSpace(durationUnit)) {
			space = qty;
			//����ǹ̶�������qty * ��������������̶��ļ���Pallet, ÿ��Pallet�����m2/m3/sqft)
			if(UFBoolean.TRUE.equals(isFixedIncrease)){
				space = space.multiply(fixedIncremental);
			}
			settleQty = space.multiply(durationCnt);
		}
		// �����������Pay by use, ��ֱ����ÿ��duration���space������ٻ���
		else {
			// ÿ��duration�Ŀ�ʼʱ��
			UFDate durationBeginDate = beginDate;
			// ÿ��duration�Ľ���ʱ��
			UFDate durationEndDate = null;
			for (int i = 0; i < durationCnt; i++) {
				// ��ȡduration�Ľ���ʱ��
				durationEndDate = getDateAfterDuration(durationBeginDate,
						duration, durationUnit);
				// ��ȡÿ��duration�ڼ���ռ�õ�sqft/m2/m3
				space = getSpace(storeItems, durationBeginDate,
						durationEndDate, durationUnit, isFixedIncrease);
				// �ж��Ƿ�С��Minimum Space�����С�ڣ���ȡMinimum Space
				if (space.compareTo(minimumSpace) < 0) {
					space = minimumSpace;
				}
				// ����ǹ̶����� ʹ�ÿռ�=����*�̶�����
				if(UFBoolean.TRUE.equals(isFixedIncrease)){
					space = space.multiply(fixedIncremental);
				}
				// ÿ��duration���޵Ŀռ����
				settleQty = settleQty.add(space);

				durationBeginDate = durationEndDate.getDateAfter(1);
				durationBeginDate.asBegin();
			}
		}
		
		returnVO.setSettleQty(settleQty);
		
		// ������������޿ռ�����ͬ�ϣ����µ�����Ҫת���յ���
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
			//�յ��� = �µ��� / ��������
			int monthDays = UFDate .getDaysMonth(endDate.getYear(), endDate.getMonth());
			settlePrice = settlePrice.div(monthDays);
		}else{
			//�յ��� = �µ��� * 12 / 365
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
	 * ��ȡĳ���졢ĳ���ܡ�ĳ���º��һ�� �ܡ��°���Ȼ�ܺ���Ȼ�£���ȡĳ���ܡ�ĳ���µ����һ��
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
		// ��β����һ�ܣ�����һ��
		if (beginWeek != 1) {// ��ȡ�¸���һ
			duration++;
			beginDate = beginDate.getDateAfter(7 - beginWeek + 1);
		}
		int days = UFDate.getDaysBetween(beginDate, endDate) + 1;
		duration = duration
				//����һ����һ��
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
			// ����(�ۼ���duration����ʱ��ǰ�������죩�����н������ݣ�
			if (vo.getQty_In() != null
					&& vo.getDate_InOut().compareTo(durationEndDate) < 1) {
				m2 = m2.add(vo.getSpace_m2());
				m3 = m3.add(vo.getCubic_Meter());
				sqft = sqft.add(vo.getSpace_ft());
				qty = space.add(vo.getQty_In());
			}// �˳�����ȥ��duration��ʼʱ��ǰ���������죩�������˳����ݣ�
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
		//TODO �����£���Ӧ�ü���������˾��ٳ������ݣ���������
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
		// ��ѯITS���˳���Ϣ
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
//	 * ���������������ȫ�˳���endDateΪ����˳�����
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
