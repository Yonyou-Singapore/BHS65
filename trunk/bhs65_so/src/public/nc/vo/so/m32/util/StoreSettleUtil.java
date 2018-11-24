package nc.vo.so.m32.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

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
import nc.vo.so.m30.entity.SaleOrderBVO;
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
	public final static String PER_M3_PERWEEK = "42";
	public final static String PER_M3_PERMONTH = "43";
	public final static String PER_M2_PERDAY = "51";
	public final static String PER_M2_PERWEEK = "52";
	public final static String PER_M2_PERMONTH = "53";
	public final static String PER_SQFT_PERDAY_BYMONTH = "61";
	public final static String PER_SQFT_PERDAY_BYYEAR = "62";
	public final static String HANDLING_IN_M3 = "11";
	public final static String HANDLING_OUT_M3 = "12";
	public final static String HANDLING_INOUT_M3 = "13";

	/**
	 * �ж϶������Ƿ�storage,��Ҫ��ν����
	 * @param bvo
	 * @return
	 */
	public static boolean isStorage(SaleOrderBVO bvo){
		if (bvo.getAttributeValue(StoreSettleUtil.FLD_DURATION_UNIT) != null) {
      	   return true;
        }else{
        	return false;
        }
	}
	
	/**
	 * �ж϶������Ƿ�storage,��Ҫ��ν����
	 * @param bvo
	 * @return
	 */
	public static boolean isStorage(SaleInvoiceBVO bvo){
		if (bvo.getAttributeValue(StoreSettleUtil.FLD_DURATION_UNIT) != null) {
      	   return true;
        }else{
        	return false;
        }
	}
	
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
	public List<SaleInvoiceBVO> settle(SaleInvoiceVO vo)
			throws BusinessException {
		UFDouble settleQty = UFDouble.ZERO_DBL;
		UFDouble settleAmt = UFDouble.ZERO_DBL;

		List<SaleInvoiceBVO> returnVos = new ArrayList<SaleInvoiceBVO>();
		SaleInvoiceHVO hvo = vo.getParentVO();
		SaleInvoiceBVO[] bvos = vo.getChildrenVO();
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

		Set<String> saleorderids = new HashSet<String>();
		Set<String> saleorderbids = new HashSet<String>();
		for (SaleInvoiceBVO bvo : bvos) {
			saleorderids.add(bvo.getCsrcid());
			saleorderbids.add(bvo.getCsrcbid());

		}
		// ��ѯITS���˳���Ϣ
		Collection<ItsStoreInOutVO> storeItemCol = getStoreInOutVO(
				saleorderids, beginDate, endDate);
		// �����ȫû�н��˳���Ϣ��������
		if (storeItemCol == null || storeItemCol.size() < 1) {
			return null;
		}
		// ������ʱ������
		List<ItsStoreInOutVO> storeItems = new ArrayList<ItsStoreInOutVO>();
		storeItems.addAll(storeItemCol);
		Collections.sort(storeItems);

		// ��ȡSOԭʼ��Ϣ
		Map<String, SaleOrderBVO> saleOrderBvoMap = getSaleOrderBVOMap(saleorderbids);
		SaleOrderBVO saleOrderBvo = null;
		String oriDescription = null;
		UFDouble oriPrice = null;

		for (SaleInvoiceBVO bvo : bvos) {
			// ���㷽ʽΪ�յ�����
			if (bvo.getAttributeValue(FLD_DURATION_UNIT) == null) {
				continue;
			}

			storageBvo = bvo;

			// ��ȡSOԭʼ��Ϣ(�ظ������ʱ��decription���ظ�,price���ټ��㣬����ÿ�μ������»�ȡ��ԭʼ��description��price)
			saleOrderBvo = saleOrderBvoMap.get(bvo.getCsrcbid());
			oriDescription = saleOrderBvo.getVbdef2();
			oriPrice = saleOrderBvo.getNqtorigprice();
			
			UFDouble settlePrice = oriPrice;

			// ���ⷽʽ��3���ࣺFixed Amount, Fixed Space by duration, Pay by use by duration)
			// update chenth 20181013 �ٶ࿼�� handling in/out�ķ��񣬰�ÿ�ζ���m3����
			Map<String, String> durationMap = getDurationMap();
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
				storageBvo.setVbdef2(oriDescription);
				storageBvo = getHandlings(storageBvo, storeItems, beginDate,
						endDate, minimumSpace, durationUnit);
				returnVos.add(storageBvo);
				continue;
			}

			// �����������duration��
			Integer durationCnt = 0;
			Integer durationUnitCnt = 0;
			UFDouble space = UFDouble.ZERO_DBL;
			UFDouble amount = UFDouble.ZERO_DBL;

			// Desription
			StringBuffer newDescription = new StringBuffer(
					oriDescription == null ? "" : oriDescription);
			appendBeginingDesc(newDescription, beginDate, endDate, storeItems,
					durationUnit, isFixedIncrease, fixedIncremental);

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
				appendDesc4FixedSpace(newDescription, durationUnit, duration,
						minimumDuration, durationCnt, space, isFixedIncrease,
						fixedIncremental);

				// ����ǹ̶�������qty * ��������������̶��ļ���Pallet, ÿ��Pallet�����m2/m3/sqft)
				if (UFBoolean.TRUE.equals(isFixedIncrease)) {
					space = space.multiply(fixedIncremental);
				}
				settleQty = space.multiply(durationCnt);
				
				//������
				settleAmt = settleQty.multiply(settlePrice);
			}
			// ���������������Pay by use, ��ֱ����ÿ��duration���space������ٻ���
			else {
				// ����ǵ�����������޿ռ���������͵��������һ�������µ�����Ҫת���յ���
				if (isMonth2Day(durationUnit)) {
					settlePrice = month2DayPrice(settlePrice, durationUnit,
							endDate);
					storageBvo.setNqtorigprice(settlePrice);
				}
				
				// ����ڳ�Description
				appendOpeningDesc(newDescription, beginDate, endDate,
						storeItems, durationUnit, isFixedIncrease,
						fixedIncremental);

				// ÿ��duration�Ŀ�ʼʱ��
				UFDate durationBeginDate = beginDate;
				// ÿ��duration�Ľ���ʱ��
				UFDate durationEndDate = null;
				UFDouble durationSettleQty = UFDouble.ZERO_DBL;
				// ����Ƽ۵������
				UFDate lastBeginDate = durationBeginDate;
				UFDouble lastSpace = UFDouble.ZERO_DBL;
				boolean existInOrOut = false;
				boolean isFinal = false;
				
				for (int i = 0; i < durationCnt; i++) {
					// ��ȡduration�Ľ���ʱ��
					durationEndDate = getDateAfterDuration(durationBeginDate,
							duration, durationUnit);
					if(durationEndDate.compareTo(endDate)>0){
						durationEndDate = endDate;
					}
					// ��ȡÿ��duration�ڼ���ռ�õ�sqft/m2/m3
					space = getSpace(storeItems, durationBeginDate,
							durationEndDate, durationUnit, isFixedIncrease);
					// �ж��Ƿ�С��Minimum Space�����С�ڣ���ȡMinimum Space
					if (space.compareTo(minimumSpace) < 0) {
						space = minimumSpace;
					}

					if(i==0){
						lastSpace = space;
					}
					//�����Ƿ��н�����ͨ�������ڿռ�Ƚ�
					existInOrOut = lastSpace.compareTo(space) != 0;
					isFinal = i == durationCnt-1;
					// �����ϸDescription
					appendDetailDesc(newDescription, storeItems,
							durationBeginDate, durationEndDate, durationUnit,
							duration, space, isFixedIncrease, fixedIncremental,settlePrice, existInOrOut, lastBeginDate, lastSpace,isFinal);

					// ����ǹ̶����� ʹ�ÿռ�=����*�̶�����
					if (UFBoolean.TRUE.equals(isFixedIncrease)) {
						durationSettleQty = space.multiply(fixedIncremental);
					}else{
						durationSettleQty = space;
					}
					// ÿ��duration���޵Ŀռ����
					settleQty = settleQty.add(durationSettleQty);
					
					//������
					amount = durationSettleQty.multiply(settlePrice);
					settleAmt = settleAmt.add(amount);

					if(existInOrOut){
						lastBeginDate = durationBeginDate;
					}
					lastSpace = space;
					
					durationBeginDate = durationEndDate.getDateAfter(1)
							.asBegin();
					
				}

				// ��Ӻϼ�Description
				appendTotalDesc(newDescription, settleQty, settlePrice, settleAmt);
			}

			storageBvo.setNastnum(settleQty);
//			storageBvo.setNastnum(UFDouble.ONE_DBL);
			storageBvo.setNorigmny(settleAmt);
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
			if (vo.getIsinbound().equals(UFBoolean.TRUE)
					&& vo.getDate_InOut().compareTo(durationEndDate) < 1) {
				m2 = m2.add(vo.getSpace_m2());
				m3 = m3.add(vo.getCubic_Meter());
				sqft = sqft.add(vo.getSpace_ft());
				qty = qty.add(vo.getQty_In());
			}// �˳�����ȥ��duration��ʼʱ��ǰ���������죩�������˳����ݣ�
			else if (vo.getIsoutbound().equals(UFBoolean.TRUE)
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
		String toolID = null;
		String micapNo = null;

		Iterator<ItsStoreInOutVO> iterator = storeItems.iterator();
		while (iterator.hasNext()) {
			vo = iterator.next();
			dateInOut = vo.getDate_InOut();
			// �жϽ��˳�ʱ���Ƿ��ڽ����ڼ���
			if (dateInOut.compareTo(beginDate) > -1
					&& dateInOut.compareTo(endDate) < 1) {
				// �ж��ǽ��������˳�
				if ((HANDLING_IN_M3.equals(durationUnit) 
						&& vo.getIsinbound().equals(UFBoolean.TRUE))
						|| (HANDLING_OUT_M3.equals(durationUnit) 
								&& vo.getIsoutbound().equals(UFBoolean.TRUE))
						||(HANDLING_INOUT_M3.equals(durationUnit) )) {

					if (HANDLING_IN_M3.equals(durationUnit)
							|| (HANDLING_INOUT_M3.equals(durationUnit) 
							&& vo.getIsinbound().equals(UFBoolean.TRUE))) {
						qty = vo.getQty_In();
					} else {
						qty = vo.getQty_Out();
					}
					
					m3 = vo.getCubic_Meter();
					m2 = vo.getSpace_m2();
					sqft = vo.getSpace_ft();
					toolID = vo.getTool_ID();
					micapNo = vo.getMicap_No();

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
					sumVo.getToolIDS().add(toolID);
					sumVo.getMicapNOS().add(micapNo);
					
					String handlingDesc = " HANDLING IN ";
					if(vo.getIsoutbound().equals(UFBoolean.TRUE)){
						handlingDesc = " HANDLING OUT ";
					}
					sumVo.setDescription(handlingDesc);
					
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
		UFDouble totalAmount = UFDouble.ZERO_DBL;
		UFDouble amount = UFDouble.ZERO_DBL;
		DecimalFormat decimalFormat = new DecimalFormat("###################.###########");
		while (iteratorD.hasNext()) {
			dateInOut = iteratorD.next();
			sumVo = handlings.get(dateInOut);

			//23/9/2017 HANDLING IN FOR 4 PKGS OF P3004, 3 m3, mininum 5 m3 .
			newDescription.append("\n ");
			DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			newDescription.append(dateInOut.toString(TimeZone.getDefault(), format));
			newDescription.append(sumVo.getDescription());
			newDescription.append(sumVo.getQty_In().setScale(0, UFDouble.ROUND_HALF_UP)).append(" PKGS OF ");
			if (sumVo.getToolIDS() != null) {
				newDescription.append(set2String(sumVo.getToolIDS()));
			}
			if (sumVo.getMicap_No() != null) {
				newDescription.append("|").append(set2String(sumVo.getMicapNOS()));
			}
			
			// �ж�ÿ��handling�������Ƿ�С��minimumSpace
			m3 = sumVo.getCubic_Meter();
			newDescription.append("; SubTotal: ").append(m3).append(" m3");
			if (m3.compareTo(minimumSpace) < 0) {
				m3 = minimumSpace;
				newDescription.append(", Minimum: ").append(m3).append(" m3");
			}
			totalM3 = totalM3.add(m3);
			
			amount = m3.multiply(storageBvo.getNqtorigprice());
			totalAmount = totalAmount.add(amount);
			
			newDescription.append(" * $").append(decimalFormat.format(storageBvo.getNqtorigprice())).append(" = ").append(decimalFormat.format(amount));
			
		}
		newDescription.append("\n Total: $").append(totalAmount.setScale(2, UFDouble.ROUND_HALF_UP));
		storageBvo.setNastnum(totalM3.setScale(2, UFDouble.ROUND_HALF_UP));
		storageBvo.setNorigmny(totalAmount);
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
			if (vo.getIsinbound().equals(UFBoolean.TRUE)
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
									lastbilldate = new UFDate(date);
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
			Collection<ItsStoreInOutVO> storeItems, String durationUnit,
			UFBoolean isFixedIncrease, UFDouble fixedIncremental) {
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		String strBegindate = beginDate.toString(TimeZone.getDefault(), format);
		String strEnddate = endDate.toString(TimeZone.getDefault(), format);
		// ��1�У�FROM 1/10/2018 TO 31/10/2018
		newDescription.append("\nFrom ").append(strBegindate).append(" To ")
				.append(strEnddate);
	}

	private void appendOpeningDesc(StringBuffer newDescription,
			UFDate beginDate, UFDate endDate,
			Collection<ItsStoreInOutVO> storeItems, String durationUnit,
			UFBoolean isFixedIncrease, UFDouble fixedIncremental) {
		// ��2�У�Opening Balance: 100 sqft
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
			if (vo.getIsinbound().equals(UFBoolean.TRUE)
					&& vo.getDate_InOut().compareTo(beginDate) < 0) {
				m2 = m2.add(vo.getSpace_m2());
				m3 = m3.add(vo.getCubic_Meter());
				sqft = sqft.add(vo.getSpace_ft());
				qty = qty.add(vo.getQty_In());
			}// ���㿪ʼ����ǰ�˳�����ȥ��duration��ʼʱ��ǰ���������죩�������˳����ݣ�
			else if (vo.getIsoutbound().equals(UFBoolean.TRUE)
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
		DecimalFormat decimalFormat = new DecimalFormat("###################.###########");  
		newDescription.append("\n Opening Balance: ").append(decimalFormat.format(space));
		if (UFBoolean.TRUE.equals(isFixedIncrease)) {
			newDescription
					.append(" * ")
					.append(decimalFormat.format(fixedIncremental))
					.append(" = ")
					.append(decimalFormat.format(space.multiply(fixedIncremental)));
		}
		newDescription.append(spaceUnit);
	}

	private void appendDesc4FixedSpace(StringBuffer newDescription,
			String durationUnit, Integer duration, Integer minimumDuration,
			Integer durationCnt, UFDouble space, UFBoolean isFixedIncrease,
			UFDouble fixedIncremental) {
		// Fixed space: 100 sqft * 10 days, Minimal duration: 10 days
		if (isFixedSpace(durationUnit)) {
			String timeUnit = getDisplayTimeUnit(durationUnit);
			String spaceUnit = getDisplaySpaceUnit(durationUnit);
			DecimalFormat decimalFormat = new DecimalFormat("###################.###########");
			newDescription.append("\n Fixed space: ").append(
					decimalFormat.format(space));
			if (UFBoolean.TRUE.equals(isFixedIncrease)) {
				newDescription
						.append(" * ")
						.append(fixedIncremental)
						.append(" = ")
						.append(decimalFormat.format(space.multiply(fixedIncremental)));
			}
			newDescription.append(spaceUnit);
			newDescription.append(" * ").append(durationCnt);
			if (duration > 1) {
				newDescription.append(" ").append(duration);
			}
			newDescription.append(timeUnit);
			if (durationCnt <= minimumDuration) {
				newDescription.append(", Minimal duration: ")
						.append(minimumDuration).append(" ").append(timeUnit);
			}
		}
	}

	private void appendDetailDesc(StringBuffer newDescription,
			Collection<ItsStoreInOutVO> storeItems, UFDate durationBeginDate,
			UFDate durationEndDate, String durationUnit, Integer duration,
			UFDouble space, UFBoolean isFixedIncrease, UFDouble fixedIncremental, UFDouble settlePrice, boolean existInOrOut, UFDate lastBeginDate, UFDouble lastSpace, boolean isFinal) {
		
		if(isByPerMonth(durationUnit) && duration == 1 ){// 1����ÿ�¼Ƽ۵�, ֻ��Ҫ��ʾ IN/OUT��ϸ
			// IN/OUT��ϸ��26/04/2018 INBOUND 3 PKGS OF OPI902, 68.4230 sqft, Bal:68.42300000 sqft
			appendInOutDetailDescs(newDescription, storeItems, durationBeginDate,
					durationEndDate, durationUnit, isFixedIncrease,
					fixedIncremental);
		}else if(isByPerDay(durationUnit) && duration == 1 ){// 2����ÿ��Ƽ۵ģ� ��IN/OUT������
			if(existInOrOut){
				appendDetailDescSubtotal(newDescription, storeItems,
						lastBeginDate, durationBeginDate.getDateBefore(1).asEnd(), durationUnit,
							duration, lastSpace, isFixedIncrease, fixedIncremental,settlePrice);
			}else if(isFinal){
				appendDetailDescSubtotal(newDescription, storeItems,
						lastBeginDate, durationEndDate, durationUnit,
							duration, space, isFixedIncrease, fixedIncremental,settlePrice);
			}
			
		}else {// 3����ÿ�ܻ�ÿ�����ÿ���ܼƼ۵ģ�������Ͱ��µ����������Ҫ����ʱ�䷶Χ���ڼ�С��        if(isByPerWeek(durationUnit) || duration > 1)
			appendDetailDescSubtotal(newDescription, storeItems,
					durationBeginDate, durationEndDate, durationUnit,
						duration, space, isFixedIncrease, fixedIncremental,settlePrice);
		}
	}
	
	private void appendDetailDescSubtotal(StringBuffer newDescription,
			Collection<ItsStoreInOutVO> storeItems, UFDate durationBeginDate,
			UFDate durationEndDate, String durationUnit, Integer duration,
			UFDouble space, UFBoolean isFixedIncrease, UFDouble fixedIncremental, UFDouble settlePrice) {
		String timeUnit = getDisplayTimeUnit(durationUnit);
		String spaceUnit = getDisplaySpaceUnit(durationUnit);
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		DecimalFormat decimalFormat = new DecimalFormat("###################.###########");
		String strBegindate = durationBeginDate.toString(
				TimeZone.getDefault(), format);
		String strEnddate = durationEndDate.toString(TimeZone.getDefault(),
				format);
		// ʱ�䷶Χ�� 1/10/2018-31/10/2018
		newDescription.append("\n ").append(strBegindate).append("-")
				.append(strEnddate);

		// IN/OUT��ϸ��26/04/2018 INBOUND 3 PKGS OF OPI902, 68.4230 sqft, Bal:68.42300000 sqft
		appendInOutDetailDescs(newDescription, storeItems, durationBeginDate,
				durationEndDate, durationUnit, isFixedIncrease,
				fixedIncremental);

		// �ڼ�С��: SubTotal: 400 sqft * 1 weeks = 400
		newDescription.append("\n SubTotal: ").append(decimalFormat.format(space));
		if (UFBoolean.TRUE.equals(isFixedIncrease)) {
			newDescription
					.append(" * ")
					.append(decimalFormat.format(fixedIncremental))
					.append(" = ")
					.append(decimalFormat.format(space.multiply(fixedIncremental)));
			space = space.multiply(fixedIncremental);
		}
		newDescription.append(spaceUnit);
		
		
		//������
		UFDouble amount = space.multiply(settlePrice);
		newDescription.append(" * $").append(decimalFormat.format(settlePrice)).append( " = $").append(decimalFormat.format(amount));
		
		if(isByPerDay(durationUnit) && duration == 1){
			int days = UFDate.getDaysBetween(durationBeginDate, durationEndDate) + 1;
			newDescription.append(" * ").append(days).append(timeUnit).append( " = $").append(decimalFormat.format(amount.multiply(days)));
		}else{
//			newDescription.append(" ").append(1);
//			if (duration > 1) {
//				newDescription.append(" ").append(duration);
//			}
//			newDescription.append(timeUnit);
		}
	}

	private void appendInOutDetailDescs(StringBuffer newDescription,
			Collection<ItsStoreInOutVO> storeItems, UFDate durationBeginDate,
			UFDate durationEndDate, String durationUnit,
			UFBoolean isFixedIncrease, UFDouble fixedIncremental) {
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
		String toolID = null;
		String micapNo = null;

		Iterator<ItsStoreInOutVO> iterator = storeItems.iterator();
		while (iterator.hasNext()) {
			vo = iterator.next();
			dateInOut = vo.getDate_InOut();
			qty_in = vo.getQty_In();
			qty_out = vo.getQty_Out();
			m3 = vo.getCubic_Meter();
			m2 = vo.getSpace_m2();
			sqft = vo.getSpace_ft();
			toolID = vo.getTool_ID();
			micapNo = vo.getMicap_No();

			// ��balance
			// ����(�ۼ���duration����ʱ��ǰ�������죩�����н������ݣ�
			if (vo.getIsinbound().equals(UFBoolean.TRUE)
					&& vo.getDate_InOut().compareTo(durationEndDate) < 1) {
				bal_m2 = bal_m2.add(m2);
				bal_m3 = bal_m3.add(m3);
				bal_sqft = bal_sqft.add(sqft);
				bal_qty = bal_qty.add(qty_in);
			}// �˳�����ȥ��duration��ʼʱ��ǰ���������죩�������˳����ݣ�
			else if (vo.getIsoutbound().equals(UFBoolean.TRUE)
					&& vo.getDate_InOut().compareTo(durationEndDate) < 0) {
				bal_m2 = bal_m2.sub(m2);
				bal_m3 = bal_m3.sub(m3);
				bal_sqft = bal_sqft.sub(sqft);
				bal_qty = bal_qty.sub(qty_out);
			}

			// �жϽ��˳�ʱ���Ƿ��ڽ����ڼ���
			if (dateInOut.compareTo(durationBeginDate) > -1
					&& dateInOut.compareTo(durationEndDate) < 1) {
				if (vo.getIsinbound().equals(UFBoolean.TRUE)) {
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
					sumVo.setQty_In(qty_in);
				} else if (vo.getIsoutbound().equals(UFBoolean.TRUE)) {
					if (outMap.containsKey(dateInOut)) {
						sumVo = outMap.get(dateInOut);
						qty_out = qty_out.add(sumVo.getQty_In());
						m3 = m3.add(sumVo.getCubic_Meter());
						m2 = m2.add(sumVo.getSpace_m2());
						sqft = sqft.add(sumVo.getSpace_ft());
					} else {
						sumVo = new ItsStoreInOutVO();
						outMap.put(dateInOut, sumVo);
					}
					sumVo.setQty_In(qty_out);
				}

				sumVo.setDate_InOut(dateInOut);
				sumVo.setCubic_Meter(m3);
				sumVo.setSpace_ft(sqft);
				sumVo.setSpace_m2(m2);
				sumVo.setBal_m2(bal_m2);
				sumVo.setBal_m3(bal_m3);
				sumVo.setBal_sqft(bal_sqft);
				sumVo.setBal_qty(bal_qty);
				sumVo.setTool_ID(toolID);
				sumVo.setMicap_No(micapNo);
				sumVo.getToolIDS().add(toolID);
				sumVo.getMicapNOS().add(micapNo);

			}
		}

		// ��ǰ�����ڼ�û�н��˳�
		if (inMap.isEmpty() && outMap.isEmpty()) {
			return;
		}

		Set<UFDate> dates = new HashSet<UFDate>();
		dates.addAll(inMap.keySet());
		dates.addAll(outMap.keySet());
		// ��������
		UFDate[] dateArr = dates.toArray(new UFDate[dates.size()]);
		Arrays.sort(dateArr);

		// �����ϸ����
		for (UFDate date : dateArr) {
			sumVo = inMap.get(date);
			if (sumVo != null) {
				appendInOutDetailDesc(newDescription, durationUnit, sumVo,
						"INBOUND", isFixedIncrease, fixedIncremental);
			}
			sumVo = outMap.get(date);
			if (sumVo != null) {
				appendInOutDetailDesc(newDescription, durationUnit, sumVo,
						"OUTBOUND", isFixedIncrease, fixedIncremental);
			}
		}
	}

	private void appendInOutDetailDesc(StringBuffer newDescription,
			String durationUnit, ItsStoreInOutVO sumVo, String inOutType,
			UFBoolean isFixedIncrease, UFDouble fixedIncremental) {
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
		// 4/10/2018 INBOUND 3PKGS, 300 sqft, Bal: 400 sqft.
		newDescription.append("\n  ").append(
				sumVo.getDate_InOut().toString(TimeZone.getDefault(), format));
		newDescription.append(" ").append(inOutType).append(" ");
		newDescription.append(
				sumVo.getQty_In().setScale(0, UFDouble.ROUND_HALF_UP)).append(
				" PKGS OF ");
		if (sumVo.getToolIDS() != null) {
			newDescription.append(set2String(sumVo.getToolIDS()));
		}
		if (sumVo.getMicap_No() != null) {
			newDescription.append("|").append(set2String(sumVo.getMicapNOS()));
		}
		DecimalFormat decimalFormat = new DecimalFormat("###################.###########");
		newDescription.append(", ").append(decimalFormat.format(inOutSpace));
		if (UFBoolean.TRUE.equals(isFixedIncrease)) {
			newDescription.append(" * ").append(decimalFormat.format(fixedIncremental)).append(" = ")
					.append(decimalFormat.format(inOutSpace.multiply(fixedIncremental)));
			bal_InOutSpace = bal_InOutSpace.multiply(fixedIncremental);
		}
		newDescription.append(spaceUnit);
		newDescription.append(", Bal:")
				.append(decimalFormat.format(bal_InOutSpace))
				.append(spaceUnit);

	}

	private String set2String(Set<String> set) {
		if (set == null || set.size() < 1) {
			return "";
		}
		StringBuffer strSet = new StringBuffer();
		for (String str : set) {
			if (strSet.length() > 0) {
				strSet.append(",");
			}
			strSet.append(str);
		}
		return strSet.toString();
	}

	private void appendTotalDesc(StringBuffer newDescription,
			UFDouble settleQty, UFDouble settlePrice, UFDouble settleAmt) {
//		newDescription.append("\n Total: ")
//				.append(settleQty.setScale(2, UFDouble.ROUND_HALF_UP))
//				.append(" * $").append(settlePrice);
		newDescription.append("\n Total: $").append(settleAmt.setScale(2, UFDouble.ROUND_HALF_UP));
	}

	private String getDisplaySpaceUnit(String durationUnit) {
		String unit = null;
		if (isByPerSqft(durationUnit)) {
			unit = "sqft";
		} else if (isByPerM3(durationUnit)) {
			unit = "m3";
		} else {
			unit = "m2";
		}
		return unit;
	}

	private String getDisplayTimeUnit(String durationUnit) {
		String unit = null;
		if (isByPerDay(durationUnit)) {
			unit = "days";
		} else if (isByPerWeek(durationUnit)) {
			unit = "weeks";
		} else {
			unit = "months";
		}
		return unit;
	}

	private Map<String, SaleOrderBVO> getSaleOrderBVOMap(
			Set<String> saleorderbids) throws BusinessException {
		Map<String, SaleOrderBVO> saleOrderBvoMap = new HashMap<String, SaleOrderBVO>();
		IUAPQueryBS query = NCLocator.getInstance().lookup(IUAPQueryBS.class);
		StringBuffer condition = new StringBuffer(" dr=0 and csaleorderbid in ");
		condition.append(InSqlManager.getInSQLValue(saleorderbids));

		Collection<SaleOrderBVO> vos = query.retrieveByClause(
				SaleOrderBVO.class, condition.toString());
		for (SaleOrderBVO bvo : vos) {
			saleOrderBvoMap.put(bvo.getCsaleorderbid(), bvo);
		}
		return saleOrderBvoMap;
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
