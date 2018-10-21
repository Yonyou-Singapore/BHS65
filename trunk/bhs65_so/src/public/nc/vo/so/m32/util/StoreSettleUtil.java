package nc.vo.so.m32.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

import edu.emory.mathcs.backport.java.util.Arrays;
import edu.emory.mathcs.backport.java.util.Collections;

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

	// public final static String PAYBY_FIXBYSPACE = "1001V810000000069FKZ";
	// public final static String PAYBY_PAYPERUSE = "1001V810000000069FL0";
	// public final static String PAYBY_FIXEDINCREMENTAL =
	// "1001V81000000006BKWP";
	// public final static String SPACEUNIT_M2 = "1001V810000000000LGZ";
	// public final static String SPACEUNIT_M3 = "1001V810000000000LH0";
	// public final static String DURATIONUNIT_PERDAY = "1001V810000000069FMC";
	// public final static String DURATIONUNIT_PERWEEK = "1001V810000000069FMD";
	// public final static String DURATIONUNIT_PERMONTH =
	// "1001V810000000069FME";
	public final static String FLD_RENTFROM = SaleInvoiceHVO.VDEF18;
	public final static String FLD_RENTTO = SaleInvoiceHVO.VDEF19;
	public final static String FLD_ISFINALBILL = SaleInvoiceHVO.VDEF20;
	// public final static String FLD_PAYBY = SaleInvoiceBVO.VBDEF6;
	public final static String FLD_DURATION = SaleInvoiceBVO.VBDEF7;
	public final static String FLD_DURATION_UNIT = SaleInvoiceBVO.VBDEF8;
	public final static String FLD_MINIMUM_SPACE = SaleInvoiceBVO.VBDEF9;
	public final static String FLD_MINIMUM_DURATION = SaleInvoiceBVO.VBDEF10;
	public final static String FLD_FIXED_INCREMENTAL = SaleInvoiceBVO.VBDEF11;
	public final static String FLD_IS_FIXED_INCREASE = SaleInvoiceBVO.VBDEF12;
	// public final static String FLD_SPACEUNIT = SaleInvoiceBVO.VBDEF3;
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
	public final static String HANDLING_IN_M3 = "11";
	public final static String HANDLING_OUT_M3 = "12";

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
				} else {// ���һ��Ҳû�н��㣬Ҳû��ָ����ʼ���ڣ���ȡ�������������Ϊ���㿪ʼ����
					beginDate = getMinimumInbounddate(saleorderids);
				}
				hvo.setAttributeValue(FLD_RENTFROM, beginDate.toString());
				// �����������Ĭ��Ϊ����
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
	public List<SaleInvoiceBVO> settle(SaleInvoiceVO vo) throws BusinessException {
		UFDouble settleQty = UFDouble.ZERO_DBL;

		List<SaleInvoiceBVO> returnVos = new ArrayList<SaleInvoiceBVO>();
		SaleInvoiceHVO hvo = vo.getParentVO();
		SaleInvoiceBVO[] bvos = vo.getChildrenVO();
		Set<String> saleorderids = new HashSet<String>();
		SaleInvoiceBVO storageBvo = null;

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
		
		for (SaleInvoiceBVO bvo : bvos) {
			saleorderids.add(bvo.getCsrcid());
			if (bvo.getAttributeValue(FLD_DURATION_UNIT) == null) {
				continue;
			}

			storageBvo = bvo;
			// ��ѯITS���˳���Ϣ
			Collection<ItsStoreInOutVO> storeItems = getStoreInOutVO(
					saleorderids, beginDate, endDate);
			//�����ȫû�н��˳���Ϣ��������
			if (storeItems == null || storeItems.size() < 1) {
				continue;
			}

			Map<String, String> durationMap = getDurationMap();
			// update chenth 20181013 �ٶ࿼�� handling in/out�ķ��񣬰�û�ζ���m3����
			// ���ⷽʽ��3���ࣺFixed Amount, Fixed Space by duration, Pay by use by duration)
			String durationUnit = (String) storageBvo
					.getAttributeValue(FLD_DURATION_UNIT);
			durationUnit = durationMap.get(durationUnit);
			// ���ڼƼ۵�λ(duration) Ĭ��Ϊ 1�� ����ÿ10�����Ǯ��ÿ2�ܡ�ÿ�µ�
			Integer duration = storageBvo.getAttributeValue(FLD_DURATION) == null ? 1
					: new Integer(
							(String) storageBvo.getAttributeValue(FLD_DURATION));
			// ��С���޿ռ�
			UFDouble minimumSpace = storageBvo
					.getAttributeValue(FLD_MINIMUM_SPACE) == null ? UFDouble.ZERO_DBL
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
			UFBoolean isFixedIncrease = storageBvo
					.getAttributeValue(FLD_IS_FIXED_INCREASE) == null ? UFBoolean.FALSE
					: new UFBoolean(
							(String) storageBvo
									.getAttributeValue(FLD_IS_FIXED_INCREASE));
			// �̶�����������
			UFDouble fixedIncremental = storageBvo
					.getAttributeValue(FLD_FIXED_INCREMENTAL) == null ? UFDouble.ONE_DBL
					: new UFDouble(
							(String) storageBvo
									.getAttributeValue(FLD_FIXED_INCREMENTAL));

			// ������������
			// ��һ�������FIXED_AMOUNT������ǹ̶���ֱ�ӷ���,����Ҫ����
			UFDouble qty = storageBvo.getNastnum();
			if (FIXED_AMOUNT.equals(durationUnit)) {
				continue;
			}
			
			// Handling In/Out�����
			if (HANDLING_IN_M3.equals(durationUnit)
					|| HANDLING_OUT_M3.equals(durationUnit)) {
				storageBvo = getHandlings(storageBvo,
						storeItems, beginDate, endDate, minimumSpace,
						durationUnit);
				returnVos.add(storageBvo);
				continue;
			}

			// �����������duration��
			Integer durationCnt = 0;
			Integer durationUnitCnt = 0;
			UFDouble space = UFDouble.ZERO_DBL;
			
			//Desription
			StringBuffer newDescription = new StringBuffer(storageBvo.getVbdef2());
			appendBeginingDesc(newDescription, beginDate, endDate, storeItems,durationUnit, isFixedIncrease, fixedIncremental);
			
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
				// ���Fixed Space Description
				appendDesc4FixedSpace(newDescription, durationUnit, duration, minimumDuration, durationCnt, space, isFixedIncrease, fixedIncremental);
				
				// ����ǹ̶�������qty * ��������������̶��ļ���Pallet, ÿ��Pallet�����m2/m3/sqft)
				if (UFBoolean.TRUE.equals(isFixedIncrease)) {
					space = space.multiply(fixedIncremental);
				}
				settleQty = space.multiply(durationCnt);
			}
			// �����������Pay by use, ��ֱ����ÿ��duration���space������ٻ���
			else {
				// ����ڳ�Description
				appendOpeningDesc(newDescription, beginDate, endDate, storeItems,durationUnit, isFixedIncrease, fixedIncremental);
				
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
					
					//�����ϸDescription
					appendDetailDesc(newDescription,storeItems, durationBeginDate,
							durationEndDate, durationUnit, duration, space, isFixedIncrease, fixedIncremental);
					
					// ����ǹ̶����� ʹ�ÿռ�=����*�̶�����
					if (UFBoolean.TRUE.equals(isFixedIncrease)) {
						space = space.multiply(fixedIncremental);
					}
					// ÿ��duration���޵Ŀռ����
					settleQty = settleQty.add(space);

					durationBeginDate = durationEndDate.getDateAfter(1);
					durationBeginDate.asBegin();
				}
				
				
				// ������������޿ռ�����ͬ�ϣ����µ�����Ҫת���յ���
				UFDouble settlePrice = storageBvo.getNqtorigprice();
				if (isMonth2Day(durationUnit)) {
					settlePrice = month2DayPrice(settlePrice, durationUnit, endDate);
					storageBvo.setNqtorigprice(settlePrice);
				}
				
				// ��Ӻϼ�Description
				appendTotalDesc(newDescription, settleQty, settlePrice);
			}

			storageBvo.setNastnum(settleQty);
			storageBvo.setVbdef2(newDescription.toString());
			returnVos.add(storageBvo);
		}

		return returnVos;
	}


	private UFDouble month2DayPrice(UFDouble settlePrice, String durationUnit,
			UFDate endDate) {
		if (PER_SQFT_PERDAY_BYMONTH.equals(durationUnit)) {
			// �յ��� = �µ��� / ��������
			int monthDays = UFDate.getDaysMonth(endDate.getYear(),
					endDate.getMonth());
			settlePrice = settlePrice.div(monthDays);
		} else {
			// �յ��� = �µ��� * 12 / 365
			int yearDays = 365;
			settlePrice = settlePrice.multiply(12).div(yearDays);
		}
		return settlePrice;
	}

	private boolean isMonth2Day(String durationUnit) {
		return PER_SQFT_PERDAY_BYMONTH.equals(durationUnit)
				|| PER_SQFT_PERDAY_BYYEAR.equals(durationUnit);
	}

	private boolean isFixedSpace(String durationUnit) {
		return FIXED_SPACE_PERDAY.equals(durationUnit)
				|| FIXED_SPACE_PERWEEK.equals(durationUnit)
				|| FIXED_SPACE_PERMONTH.equals(durationUnit);
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
				|| PER_SQFT_PERMONTH.equals(durationUnit)
				|| PER_M3_PERMONTH.equals(durationUnit)
				|| PER_M2_PERMONTH.equals(durationUnit);
	}

	private boolean isByPerWeek(String durationUnit) {
		return FIXED_SPACE_PERWEEK.equals(durationUnit)
				|| PER_SQFT_PERWEEK.equals(durationUnit)
				|| PER_M3_PERWEEK.equals(durationUnit)
				|| PER_M2_PERWEEK.equals(durationUnit);
	}

	private boolean isByPerDay(String durationUnit) {
		return FIXED_SPACE_PERDAY.equals(durationUnit)
				|| PER_SQFT_PERDAY.equals(durationUnit)
				|| PER_M3_PERDAY.equals(durationUnit)
				|| PER_M2_PERDAY.equals(durationUnit)
				|| PER_SQFT_PERDAY_BYMONTH.equals(durationUnit)
				|| PER_SQFT_PERDAY_BYYEAR.equals(durationUnit);
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
		// ����һ����һ��
				+ new UFDouble(days).div(new UFDouble(7), 0, UFDouble.ROUND_UP)
						.intValue();
		return duration;
	}

	private UFDouble getSpace(Collection<ItsStoreInOutVO> storeItems,
			UFDate durationBeginDate, UFDate durationEndDate,
			String durationUnit, UFBoolean isFixedIncrease) {
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
				qty = qty.add(vo.getQty_In());
			}// �˳�����ȥ��duration��ʼʱ��ǰ���������죩�������˳����ݣ�
			else if (vo.getQty_Out() != null
					&& vo.getDate_InOut().compareTo(durationBeginDate) < 0) {
				m2 = m2.sub(vo.getSpace_m2());
				m3 = m3.sub(vo.getCubic_Meter());
				sqft = sqft.sub(vo.getSpace_ft());
				qty = qty.sub(vo.getQty_Out());
			}
		}
		if (UFBoolean.TRUE.equals(isFixedIncrease)) {
			space = qty;
		} else if (isByPerM2(durationUnit)) {
			space = m2;
		} else if (isByPerM3(durationUnit)) {
			space = m3;
		} else if (isByPerSqft(durationUnit)) {
			space = sqft;
		}
		return space;
	}

	private SaleInvoiceBVO getHandlings(SaleInvoiceBVO storageBvo,
			Collection<ItsStoreInOutVO> storeItems, UFDate beginDate,
			UFDate endDate, UFDouble minimumSpace, String durationUnit) {
		ItsStoreInOutVO vo = null;
		ItsStoreInOutVO sumVo = null;
		UFDouble m2 = UFDouble.ZERO_DBL;
		UFDouble m3 = UFDouble.ZERO_DBL;
		UFDouble sqft = UFDouble.ZERO_DBL;
		UFDouble qty = UFDouble.ZERO_DBL;
		Map<UFDate, ItsStoreInOutVO> handlings = new HashMap<UFDate, ItsStoreInOutVO>();
		UFDate dateInOut = null;
		
		Iterator<ItsStoreInOutVO> iterator = storeItems.iterator();
		while (iterator.hasNext()) {
			vo = iterator.next();
			dateInOut = vo.getDate_InOut();
			// �жϽ��˳�ʱ���Ƿ��ڽ����ڼ���
			if (dateInOut.compareTo(beginDate) > -1
					&& dateInOut.compareTo(endDate) < 1) {
				// �ж��ǽ��������˳�
				if ((HANDLING_IN_M3.equals(durationUnit) && vo.getQty_In() != null)
						|| (HANDLING_OUT_M3.equals(durationUnit) && vo
								.getQty_Out() != null)) {

					if (HANDLING_IN_M3.equals(durationUnit)) {
						qty = vo.getQty_In();
					} else {
						qty = vo.getQty_Out();
					}
					m3 = vo.getCubic_Meter();
					m2 = vo.getSpace_m2();
					sqft = vo.getSpace_ft();

					if (handlings.containsKey(dateInOut)) {
						sumVo = handlings.get(dateInOut);
						m3 = m3.add(sumVo.getCubic_Meter());
						qty = qty.add(sumVo.getQty_In());
						m2 = m2.add(sumVo.getSpace_m2());
						sqft = sqft.add(sumVo.getSpace_ft());
					} else {
						sumVo = new ItsStoreInOutVO();
					}
					sumVo.setDate_InOut(dateInOut);
					sumVo.setQty_In(qty);
					sumVo.setCubic_Meter(m3);
					sumVo.setSpace_ft(sqft);
					sumVo.setSpace_m2(m2);
					sumVo.setTool_ID(vo.getTool_ID());
					sumVo.setMicap_No(vo.getMicap_No());
					handlings.put(dateInOut, sumVo);
				}
			}
		}

		// ��ǰ�����ڼ�û�н��˳�
		if (handlings.isEmpty()) {
			return storageBvo;
		}

		StringBuffer newDescription = new StringBuffer(storageBvo.getVbdef2());
		Iterator<UFDate> iteratorD = handlings.keySet().iterator();
		UFDouble totalM3 = UFDouble.ZERO_DBL;
		while (iteratorD.hasNext()) {
			dateInOut = iteratorD.next();
			sumVo = handlings.get(dateInOut);
			// �ж�ÿ��handling�������Ƿ�С��minimumSpace
			m3 = sumVo.getCubic_Meter();
			if (m3.compareTo(minimumSpace) < 0) {
				m3 = minimumSpace;
			}
			totalM3 = totalM3.add(m3);
			
			DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			// WAREHOUSE HANDLING IN FOR 4 PKGS OF P3004 SHIPMENTS ON 23/9/2017.
			newDescription.append("\n ");
			newDescription.append(sumVo.getQty_In()).append(" PKGS OF " );
			newDescription.append(sumVo.getTool_ID()).append("/").append(sumVo.getMicap_No());
			newDescription.append(" SHIPMENTS ON ").append(dateInOut.toString(TimeZone.getDefault(), format));
		}
		storageBvo.setNastnum(totalM3);
		storageBvo.setVbdef2(newDescription.toString());

		return storageBvo;
	}

	private boolean isByPerSqft(String durationUnit) {
		return PER_SQFT_PERDAY.equals(durationUnit)
				|| PER_SQFT_PERWEEK.equals(durationUnit)
				|| PER_SQFT_PERMONTH.equals(durationUnit)
				|| PER_SQFT_PERDAY_BYMONTH.equals(durationUnit)
				|| PER_SQFT_PERDAY_BYYEAR.equals(durationUnit);
	}

	private boolean isByPerM3(String durationUnit) {
		return PER_M3_PERDAY.equals(durationUnit)
				|| PER_M3_PERWEEK.equals(durationUnit)
				|| PER_M3_PERMONTH.equals(durationUnit);
	}

	private boolean isByPerM2(String durationUnit) {
		return PER_M2_PERDAY.equals(durationUnit)
				|| PER_M2_PERWEEK.equals(durationUnit)
				|| PER_M2_PERMONTH.equals(durationUnit);
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
		Collection<ItsStoreInOutVO> storeItems = getStoreInOutVO(saleorderids,
				null, new UFDate());
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

	// /**
	// * ���������������ȫ�˳���endDateΪ����˳�����
	// *
	// * @param storeItems
	// * @param dbilldate
	// * @return
	// * @throws BusinessException
	// */
	// private UFDate getEndDate(Collection<ItsStoreInOutVO> storeItems,
	// UFDate dbilldate) throws BusinessException {
	// Iterator<ItsStoreInOutVO> iterator = storeItems.iterator();
	// ItsStoreInOutVO vo = iterator.next();
	// UFDate outbounddate = vo.getDate_Out();
	// if (outbounddate == null || outbounddate.compareTo(dbilldate) > 0) {
	// return dbilldate;
	// }
	// UFDate outbounddate2 = null;
	// while (iterator.hasNext()) {
	// vo = iterator.next();
	// outbounddate2 = vo.getDate_Out();
	// if (outbounddate2 == null || outbounddate2.compareTo(dbilldate) > 0) {
	// return dbilldate;
	// } else if (outbounddate.compareTo(outbounddate2) < 0) {
	// outbounddate = outbounddate2;
	// }
	// }
	//
	// return outbounddate;
	// }

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

		Map<String, String> durationMap = (Map<String, String>) new BaseDAO()
				.executeQuery(sql, new ResultSetProcessor() {
					@Override
					public Object handleResultSet(ResultSet rs)
							throws SQLException {
						Map<String, String> itsparaMap = new HashMap<String, String>();
						while (rs.next()) {
							itsparaMap.put(rs.getString(1), rs.getString(2));
						}
						return itsparaMap;
					}

				});

		return durationMap;
	}
	

	private void appendBeginingDesc(StringBuffer newDescription,
			UFDate beginDate, UFDate endDate,
			Collection<ItsStoreInOutVO> storeItems, String durationUnit
			,UFBoolean isFixedIncrease, UFDouble fixedIncremental) {
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		String strBegindate = beginDate.toString(TimeZone.getDefault(), format);
		String strEnddate = endDate.toString(TimeZone.getDefault(), format);
		//��1�У�FROM 1/10/2018 TO 31/10/2018
		newDescription.append("\nFrom ").append(strBegindate).append(" To ").append(strEnddate);
	}
	
	private void appendOpeningDesc(StringBuffer newDescription,
			UFDate beginDate, UFDate endDate,
			Collection<ItsStoreInOutVO> storeItems, String durationUnit
			,UFBoolean isFixedIncrease, UFDouble fixedIncremental) {
		//��2�У�Opening Balance: 100 sqft
		Iterator<ItsStoreInOutVO> iterator = storeItems.iterator();
		ItsStoreInOutVO vo = null;
		UFDouble space = UFDouble.ZERO_DBL;
		UFDouble m2 = UFDouble.ZERO_DBL;
		UFDouble m3 = UFDouble.ZERO_DBL;
		UFDouble sqft = UFDouble.ZERO_DBL;
		UFDouble qty = UFDouble.ZERO_DBL;
		while (iterator.hasNext()) {
			vo = iterator.next();
			// ���㿪ʼ����ǰ����(�ۼ���duration����ʱ��ǰ�������죩�����н������ݣ�
			if (vo.getQty_In() != null
					&& vo.getDate_InOut().compareTo(beginDate) < 0) {
				m2 = m2.add(vo.getSpace_m2());
				m3 = m3.add(vo.getCubic_Meter());
				sqft = sqft.add(vo.getSpace_ft());
				qty = qty.add(vo.getQty_In());
			}// ���㿪ʼ����ǰ�˳�����ȥ��duration��ʼʱ��ǰ���������죩�������˳����ݣ�
			else if (vo.getQty_Out() != null
					&& vo.getDate_InOut().compareTo(beginDate) < 0) {
				m2 = m2.sub(vo.getSpace_m2());
				m3 = m3.sub(vo.getCubic_Meter());
				sqft = sqft.sub(vo.getSpace_ft());
				qty = qty.sub(vo.getQty_Out());
			}
		}
		
		if (UFBoolean.TRUE.equals(isFixedIncrease)) {
			space = qty;
		} else if (isByPerM2(durationUnit)) {
			space = m2;
		} else if (isByPerM3(durationUnit)) {
			space = m3;
		} else if (isByPerSqft(durationUnit)) {
			space = sqft;
		}
		String spaceUnit = getDisplaySpaceUnit(durationUnit);
		newDescription.append("\n Opening Balance: ").append(space.setScale(2, UFDouble.ROUND_HALF_UP));
		if (UFBoolean.TRUE.equals(isFixedIncrease)) {
			newDescription.append(" * ").append(fixedIncremental).append(" = ").append(space.multiply(fixedIncremental).setScale(2, UFDouble.ROUND_HALF_UP));
		}
		newDescription.append(" ").append(spaceUnit);
	}

	private void appendDesc4FixedSpace(StringBuffer newDescription, String durationUnit, Integer duration,
			Integer minimumDuration, Integer durationCnt, UFDouble space, UFBoolean isFixedIncrease, UFDouble fixedIncremental) {
		//Fixed space: 100 sqft * 10 days, Minimal duration: 10 days
		if(isFixedSpace(durationUnit)){
			String timeUnit = getDisplayTimeUnit(durationUnit);
			String spaceUnit = getDisplaySpaceUnit(durationUnit);
			
			newDescription.append("\n Fixed space: ").append(space.setScale(2, UFDouble.ROUND_HALF_UP));
			if (UFBoolean.TRUE.equals(isFixedIncrease)) {
				newDescription.append(" * ").append(fixedIncremental).append(" = ").append(space.multiply(fixedIncremental).setScale(2, UFDouble.ROUND_HALF_UP));
			}
			newDescription.append(" ").append(spaceUnit);
			newDescription.append(" * ").append(durationCnt);
			if (duration > 1) {
				newDescription.append(" ").append(duration);
			}
			newDescription.append(timeUnit);
			if(durationCnt <= minimumDuration){
				newDescription.append(", Minimal duration: ").append(minimumDuration).append(" ").append(timeUnit);
			}
		}
	}
	

	private void appendDetailDesc(StringBuffer newDescription,
			Collection<ItsStoreInOutVO> storeItems, UFDate durationBeginDate,
			UFDate durationEndDate, String durationUnit, Integer duration,
			UFDouble space, UFBoolean isFixedIncrease, UFDouble fixedIncremental) {
		String timeUnit = getDisplayTimeUnit(durationUnit);
		String spaceUnit = getDisplaySpaceUnit(durationUnit);
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		//������Ͱ��µ�������������ڼ䶼��Ҫ��һ��С��
		if(isByPerWeek(durationUnit)
				|| duration > 1){
			String strBegindate = durationBeginDate.toString(TimeZone.getDefault(), format);
			String strEnddate = durationEndDate.toString(TimeZone.getDefault(), format);
			//1/10/2018-31/10/2018
			newDescription.append("\n ").append(strBegindate).append("-").append(strEnddate);
		}
		
		appendInOutDetailDescs(newDescription,storeItems, durationBeginDate,
				durationEndDate, durationUnit, isFixedIncrease, fixedIncremental);
		
		// SubTotal: 400 sqft * 1 weeks  = 400
		if(isByPerWeek(durationUnit)
				|| duration > 1){
			newDescription.append("\n SubTotal: ").append(space.setScale(2, UFDouble.ROUND_HALF_UP));
			if (UFBoolean.TRUE.equals(isFixedIncrease)) {
				newDescription.append(" * ").append(fixedIncremental).append(" = ").append(space.multiply(fixedIncremental).setScale(2, UFDouble.ROUND_HALF_UP));
				space = space.multiply(fixedIncremental);
			}
			newDescription.append(" ").append(spaceUnit);
			newDescription.append(" * ").append(1);
			if (duration > 1) {
				newDescription.append(" ").append(duration);
			}
			newDescription.append(" ").append(timeUnit);
			newDescription.append(" = ").append(space.multiply(1).setScale(2, UFDouble.ROUND_HALF_UP));
		}
	}
	
	

	private void appendInOutDetailDescs(StringBuffer newDescription,
			Collection<ItsStoreInOutVO> storeItems, UFDate durationBeginDate,
			UFDate durationEndDate, String durationUnit, UFBoolean isFixedIncrease, UFDouble fixedIncremental) {
		//����
		List<ItsStoreInOutVO> listSotreItems = new ArrayList<ItsStoreInOutVO>();
		listSotreItems.addAll(storeItems);
		Collections.sort(listSotreItems);
		
		ItsStoreInOutVO vo = null;
		ItsStoreInOutVO sumVo = null;
		UFDouble m2 = UFDouble.ZERO_DBL;
		UFDouble m3 = UFDouble.ZERO_DBL;
		UFDouble sqft = UFDouble.ZERO_DBL;
		UFDouble qty_in = UFDouble.ZERO_DBL;
		UFDouble qty_out = UFDouble.ZERO_DBL;
		UFDouble bal_m2 = UFDouble.ZERO_DBL;
		UFDouble bal_m3 = UFDouble.ZERO_DBL;
		UFDouble bal_sqft = UFDouble.ZERO_DBL;
		UFDouble bal_qty = UFDouble.ZERO_DBL;
		Map<UFDate, ItsStoreInOutVO> inMap = new HashMap<UFDate, ItsStoreInOutVO>();
		Map<UFDate, ItsStoreInOutVO> outMap = new HashMap<UFDate, ItsStoreInOutVO>();
		UFDate dateInOut = null;
		
		Iterator<ItsStoreInOutVO> iterator = storeItems.iterator();
		while (iterator.hasNext()) {
			vo = iterator.next();
			dateInOut = vo.getDate_InOut();
			qty_in = vo.getQty_In();
			qty_out = vo.getQty_Out();
			m3 = vo.getCubic_Meter();
			m2 = vo.getSpace_m2();
			sqft = vo.getSpace_ft();
			
			//��balance
			// ����(�ۼ���duration����ʱ��ǰ�������죩�����н������ݣ�
			if (vo.getQty_In() != null
					&& vo.getDate_InOut().compareTo(durationEndDate) < 1) {
				bal_m2 = bal_m2.add(m2);
				bal_m3 = bal_m3.add(m3);
				bal_sqft = bal_sqft.add(sqft);
				bal_qty = bal_qty.add(qty_in);
			}// �˳�����ȥ��duration��ʼʱ��ǰ���������죩�������˳����ݣ�
			else if (vo.getQty_Out() != null
					&& vo.getDate_InOut().compareTo(durationEndDate) < 0) {
				bal_m2 = bal_m2.sub(m2);
				bal_m3 = bal_m3.sub(m3);
				bal_sqft = bal_sqft.sub(sqft);
				bal_qty = bal_qty.sub(qty_out);
			}
			
			
			// �жϽ��˳�ʱ���Ƿ��ڽ����ڼ���
			if (dateInOut.compareTo(durationBeginDate) > -1
					&& dateInOut.compareTo(durationEndDate) < 1) {
					if(vo.getQty_In() != null){
						if (inMap.containsKey(dateInOut)) {
							sumVo = inMap.get(dateInOut);
							qty_in = qty_in.add(sumVo.getQty_In());
							m3 = m3.add(sumVo.getCubic_Meter());
							m2 = m2.add(sumVo.getSpace_m2());
							sqft = sqft.add(sumVo.getSpace_ft());
						} else {
							sumVo = new ItsStoreInOutVO();
							inMap.put(dateInOut, sumVo);
						}
					}else if(vo.getQty_Out() != null){
						if (outMap.containsKey(dateInOut)) {
							sumVo = outMap.get(dateInOut);
							qty_out = qty_out.add(sumVo.getQty_Out());
							m3 = m3.add(sumVo.getCubic_Meter());
							m2 = m2.add(sumVo.getSpace_m2());
							sqft = sqft.add(sumVo.getSpace_ft());
						} else {
							sumVo = new ItsStoreInOutVO();
							outMap.put(dateInOut, sumVo);
						}
					}
						
					sumVo.setDate_InOut(dateInOut);
					sumVo.setQty_In(qty_in);
					sumVo.setQty_Out(qty_out);
					sumVo.setCubic_Meter(m3);
					sumVo.setSpace_ft(sqft);
					sumVo.setSpace_m2(m2);
					sumVo.setTool_ID(vo.getTool_ID());
					sumVo.setMicap_No(vo.getMicap_No());
					sumVo.setBal_m2(bal_m2);
					sumVo.setBal_m3(bal_m3);
					sumVo.setBal_sqft(bal_sqft);
					sumVo.setBal_qty(bal_qty);
					
				}
			}

		// ��ǰ�����ڼ�û�н��˳�
		if (inMap.isEmpty()
				&& outMap.isEmpty()) {
			return ;
		}
		
		Set<UFDate> dates = new HashSet<UFDate>();
		dates.addAll(inMap.keySet());
		dates.addAll(outMap.keySet());
		//��������
		UFDate[] dateArr = dates.toArray(new UFDate[dates.size()]);
		Arrays.sort(dateArr);
		
		// �����ϸ����
		for(UFDate date : dateArr){
			sumVo = inMap.get(date);
			if(sumVo!=null){
				appendInOutDetailDesc(newDescription, durationUnit, sumVo, "INBOUND", isFixedIncrease, fixedIncremental);
			}
			sumVo = outMap.get(date);
			if(sumVo!=null){
				appendInOutDetailDesc(newDescription, durationUnit, sumVo, "OUTBOUND", isFixedIncrease, fixedIncremental);
			}
		}
	}

	private void appendInOutDetailDesc(StringBuffer newDescription,
			String durationUnit, ItsStoreInOutVO sumVo, String inOutType, UFBoolean isFixedIncrease,
			UFDouble fixedIncremental) {
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		String spaceUnit = getDisplaySpaceUnit(durationUnit);
		UFDouble inOutSpace = UFDouble.ZERO_DBL;
		UFDouble bal_InOutSpace = UFDouble.ZERO_DBL;
		
		if (UFBoolean.TRUE.equals(isFixedIncrease)) {
			inOutSpace = sumVo.getQty_In();
			bal_InOutSpace = sumVo.getBal_qty();
		} else if (isByPerM2(durationUnit)) {
			inOutSpace = sumVo.getSpace_m2();
			bal_InOutSpace = sumVo.getBal_m2();
		} else if (isByPerM3(durationUnit)) {
			inOutSpace = sumVo.getCubic_Meter();
			bal_InOutSpace = sumVo.getBal_m3();
		} else if (isByPerSqft(durationUnit)) {
			inOutSpace = sumVo.getSpace_ft();
			bal_InOutSpace = sumVo.getBal_sqft();
		}
		//4/10/2018 INBOUND 3PKGS, 300 sqft, Bal: 400 sqft.
		newDescription.append("\n  ").append(sumVo.getDate_InOut().toString(TimeZone.getDefault(), format));
		newDescription.append(" ").append(inOutType).append(" ");
		newDescription.append(sumVo.getQty_In()).append(" PKGS OF " );
		newDescription.append(sumVo.getTool_ID()).append("/").append(sumVo.getMicap_No());
		newDescription.append(", ").append(inOutSpace);
		if (UFBoolean.TRUE.equals(isFixedIncrease)) {
			newDescription.append(" * ").append(fixedIncremental).append(" = ").append(inOutSpace.multiply(fixedIncremental));
		}
		newDescription.append(" ").append(spaceUnit);
		newDescription.append(", Bal:").append(bal_InOutSpace).append(" ").append(spaceUnit);
	
	}

	private void appendTotalDesc(StringBuffer newDescription,
			UFDouble settleQty, UFDouble settlePrice) {
		newDescription.append("\n Total: ").append(settleQty.setScale(2, UFDouble.ROUND_HALF_UP)).append(" * $").append(settlePrice);
	}

	private String getDisplaySpaceUnit(String durationUnit) {
		String unit = null;
		if(isByPerSqft(durationUnit)){
			unit = "sqft";
		}else if (isByPerM3(durationUnit)){
			unit = "m3";
		}else{
			unit = "m2";
		}
		return unit;
	}

	private String getDisplayTimeUnit(String durationUnit) {
		String unit = null;
		if(isByPerDay(durationUnit)){
			unit = "days";
		}else if (isByPerWeek(durationUnit)){
			unit = "weeks";
		}else{
			unit = "months";
		}
		return unit;
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
