package nc.vo.so.m32.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

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

	public final static String PAYBY_FIXBYSPACE = "1001V810000000069FKZ";
	public final static String PAYBY_PAYPERUSE = "1001V810000000069FL0";
	public final static String PAYBY_FIXEDINCREMENTAL = "";
	public final static String SPACEUNIT_M2 = "1001V810000000000LGZ";
	public final static String SPACEUNIT_M3 = "1001V810000000000LH0";
	public final static String DURATIONUNIT_PERDAY = "1001V810000000069FMC";
	public final static String DURATIONUNIT_PERWEEK = "1001V810000000069FMD";
	public final static String DURATIONUNIT_PERMONTH = "1001V810000000069FME";
	public final static String FLD_RENTFROM = SaleInvoiceHVO.VDEF18;
	public final static String FLD_RENTTO = SaleInvoiceHVO.VDEF19;
	public final static String FLD_ISFINALBILL = SaleInvoiceHVO.VDEF20;
	public final static String FLD_PAYBY = SaleInvoiceBVO.VBDEF6;
	public final static String FLD_DURATION = SaleInvoiceBVO.VBDEF7;
	public final static String FLD_DURATION_UNIT = SaleInvoiceBVO.VBDEF8;
	public final static String FLD_MINIMUM_SPACE = SaleInvoiceBVO.VBDEF9;
	public final static String FLD_MINIMUM_DURATION = SaleInvoiceBVO.VBDEF10;
	public final static String FLD_SPACEUNIT = SaleInvoiceBVO.VBDEF3;

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
				// Ĭ�ϲ������һ���˵�
				hvo.setAttributeValue(FLD_ISFINALBILL,
						UFBoolean.FALSE.toString());
				// �ϴν����������
				UFDate lastSettleEndDate = getLastSettleEndDate(saleorderids);
				if (lastSettleEndDate != null) {
					UFDate beginDate = lastSettleEndDate.getDateAfter(1);
					hvo.setAttributeValue(FLD_RENTFROM, beginDate.toString());
				}
			} else {
				// Ĭ��Ϊ���һ���˵�
				hvo.setAttributeValue(FLD_ISFINALBILL,
						UFBoolean.TRUE.toString());
			}
		}

	}

	/**
	 * ���㣬������������
	 * 
	 * @param vo
	 * @throws BusinessException
	 */
	public UFDouble settle(SaleInvoiceVO vo) throws BusinessException {
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
			return settleQty;
		}

		String payBy = (String) storageBvo.getAttributeValue(FLD_PAYBY);
		Integer duration = new Integer(
				(String) storageBvo.getAttributeValue(FLD_DURATION));
		String durationUnit = (String) storageBvo
				.getAttributeValue(FLD_DURATION_UNIT);
		UFDouble minimumSpace = storageBvo.getAttributeValue(FLD_MINIMUM_SPACE) == null ? UFDouble.ZERO_DBL
				: new UFDouble(
						(String) storageBvo
								.getAttributeValue(FLD_MINIMUM_SPACE));
		Integer minimumDuration = storageBvo
				.getAttributeValue(FLD_MINIMUM_DURATION) == null ? 0
				: new Integer(
						(String) storageBvo
								.getAttributeValue(FLD_MINIMUM_DURATION));
		String spaceUnit = (String) storageBvo.getAttributeValue(FLD_SPACEUNIT);
		// �������
		if (storageBvo != null && payBy != null && duration != null) {
			UFDate beginDate = null;
			UFDate endDate = null;

			// ���ν��㿪ʼ����
			String rentFrom = (String) hvo.getAttributeValue(FLD_RENTFROM);
			if (rentFrom != null) {
				beginDate = new UFDate(rentFrom);
				beginDate = beginDate.asBegin();
			}

			// ���ν����������
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
				return settleQty;
			}

			Integer durationCnt = 0;
			Integer durationUnitCnt = 0;
			UFDouble space = UFDouble.ZERO_DBL;

			// // ���һ��Ҳû�н��㣬Ҳû��ָ����ʼ���ڣ���ȡ�������������Ϊ���㿪ʼ����
			// if (beginDate == null) {
			// beginDate = getMinimumInbounddate(storeItems);
			// }
			// ���������������ȫ�˳���endDateΪ����˳�����
			endDate = getEndDate(storeItems, endDate);
			endDate = endDate.asEnd();

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

			// �����Fix by space, ��ֱ���� space * duration
			if (StoreSettleUtil.PAYBY_FIXBYSPACE.equals(payBy)) {
				space = minimumSpace;
				settleQty = space.multiply(durationCnt);
			}
			// �����Pay by use, ��ֱ����ÿ��duration���space������ٻ���
			else {
				// ÿ��duration�Ŀ�ʼʱ��
				UFDate durationBeginDate = beginDate;
				// ÿ��duration�Ľ���ʱ��
				UFDate durationEndDate = null;
				for (int i = 0; i < durationCnt; i++) {
					// ��ȡduration�Ľ���ʱ��
					durationEndDate = getDateAfterDuration(durationBeginDate,
							duration, durationUnit);
					space = getSpace(storeItems, durationBeginDate,
							durationEndDate, spaceUnit);
					// �ж��Ƿ�С��Minimum Space�����С�ڣ���ȡMinimum Space
					if (space.compareTo(minimumSpace) < 0) {
						space = minimumSpace;
					}
					// �����Fixed Incremental
					else if (StoreSettleUtil.PAYBY_FIXEDINCREMENTAL
							.equals(payBy)) {
						space = space.div(minimumSpace, 0, UFDouble.ROUND_UP)
								.multiply(minimumSpace);
					}
					settleQty = settleQty.add(space);

					durationBeginDate = durationEndDate.getDateAfter(1);
					durationBeginDate.asBegin();
				}
			}
			storageBvo.setNastnum(settleQty);
		}
		return settleQty;
	}

	/**
	 * ��ȡĳ���졢ĳ���ܡ�ĳ���º��һ�� �ܡ��°���Ȼ�ܺ���Ȼ�£���ȡĳ���ܡ�ĳ���µ����һ��
	 */
	private UFDate getDateAfterDuration(UFDate durationBeginDate,
			Integer duration, String durationUnit) {
		UFDate durationEndDate = null;
		if (StoreSettleUtil.DURATIONUNIT_PERDAY.equals(durationUnit)) {
			durationEndDate = durationBeginDate.getDateAfter(duration - 1);
		} else if (StoreSettleUtil.DURATIONUNIT_PERWEEK.equals(durationUnit)) {
			durationEndDate = getDateAfterWeeks(durationBeginDate, duration);
		} else if (StoreSettleUtil.DURATIONUNIT_PERMONTH.equals(durationUnit)) {
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
		;
		if (week != 1) {
			duration = duration - 1;
			durationBeginDate = durationBeginDate.getDateAfter(7 - week + 1);
		}
		return durationBeginDate.getDateAfter(duration * 7 - 1);
	}

	private Integer getDurationUnitCnt(UFDate beginDate, UFDate endDate,
			String durationUnit) {
		Integer durationUnitCnt = 0;
		if (StoreSettleUtil.DURATIONUNIT_PERDAY.equals(durationUnit)) {
			durationUnitCnt = UFDate.getDaysBetween(beginDate, endDate) + 1;
		} else if (StoreSettleUtil.DURATIONUNIT_PERWEEK.equals(durationUnit)) {
			durationUnitCnt = getWeeksBetween(beginDate, endDate);
		} else if (StoreSettleUtil.DURATIONUNIT_PERMONTH.equals(durationUnit)) {
			durationUnitCnt = getMonthsBetween(beginDate, endDate);
		}
		return durationUnitCnt;
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
			UFDate durationBeginDate, UFDate durationEndDate, String spaceUnit) {
		Iterator<ItsStoreInOutVO> iterator = storeItems.iterator();
		ItsStoreInOutVO vo = null;
		UFDouble space = UFDouble.ZERO_DBL;
		while (iterator.hasNext()) {
			vo = iterator.next();
			if (vo.getDate_In().compareTo(durationEndDate) < 1
					&& (vo.getDate_Out() == null || vo.getDate_Out().compareTo(
							durationBeginDate) > -1)) {
				if (SPACEUNIT_M2.equals(spaceUnit)) {
					space = space.add(vo.getSpace_m2());
				} else {
					space = space.add(vo.getCubic_Meter());
				}
			}
		}
		return space;
	}

	private Collection<ItsStoreInOutVO> getStoreInOutVO(
			Set<String> saleorderids, UFDate beginDate, UFDate endDate)
			throws BusinessException {
		Set<String> jonoSet = getStoreJoNo(saleorderids);
		if (jonoSet == null) {
			return null;
		}
		IUAPQueryBS query = NCLocator.getInstance().lookup(IUAPQueryBS.class);
		// TODO
		StringBuffer condition = new StringBuffer(" 1=1 ");
		// new StringBuffer(" Doc_No in ");
		// condition.append(InSqlManager.getInSQLValue(jonoSet));
		condition.append(" and Date_In <= '");
		condition.append(endDate.toString());
		condition.append("' ");
		if (beginDate != null) {
			condition.append(" and (Date_Out is null or Date_Out >= '");
			condition.append(beginDate.toString());
			condition.append("') ");
		}

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

	private UFDate getMinimumInbounddate(Collection<ItsStoreInOutVO> storeItems)
			throws BusinessException {
		Iterator<ItsStoreInOutVO> iterator = storeItems.iterator();
		ItsStoreInOutVO vo = iterator.next();
		UFDate minimumInbounddate = vo.getDate_In();
		while (iterator.hasNext()) {
			vo = iterator.next();
			if (minimumInbounddate.compareTo(vo.getDate_In()) > 0) {
				minimumInbounddate = vo.getDate_In();
			}
		}

		return minimumInbounddate;
	}

	/**
	 * ���������������ȫ�˳���endDateΪ����˳�����
	 * 
	 * @param storeItems
	 * @param dbilldate
	 * @return
	 * @throws BusinessException
	 */
	private UFDate getEndDate(Collection<ItsStoreInOutVO> storeItems,
			UFDate dbilldate) throws BusinessException {
		Iterator<ItsStoreInOutVO> iterator = storeItems.iterator();
		ItsStoreInOutVO vo = iterator.next();
		UFDate outbounddate = vo.getDate_Out();
		if (outbounddate == null || outbounddate.compareTo(dbilldate) > 0) {
			return dbilldate;
		}
		UFDate outbounddate2 = null;
		while (iterator.hasNext()) {
			vo = iterator.next();
			outbounddate2 = vo.getDate_Out();
			if (outbounddate2 == null || outbounddate2.compareTo(dbilldate) > 0) {
				return dbilldate;
			} else if (outbounddate.compareTo(outbounddate2) < 0) {
				outbounddate = outbounddate2;
			}
		}

		return outbounddate;
	}

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
