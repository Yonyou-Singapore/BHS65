package nc.impl.wa.paydata;
import java.util.LinkedList;
import java.util.List;

import nc.bs.dao.DAOException;
import nc.bs.framework.common.NCLocator;
import nc.bs.logging.Logger;

import nc.itf.ta.IPsnCalendarQueryService;
import nc.itf.uap.busibean.SysinitAccessor;
import nc.pubitf.uapbd.IWorkCalendarPubService;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pub.lang.UFLiteralDate;
import nc.vo.trn.PsnWorkDayVO;
import nc.vo.wa.paydata.PsncomputeVO;
import nc.vo.wa.pub.ParaConstant;
import nc.vo.wa.pub.WaLoginVO;

/**
 * @author zhangg on 2009-10-14
 */
public class WaWorkDay {
	
	private IPsnCalendarQueryService psnCalendarQueryService;
	private IWorkCalendarPubService workCalendarPubService;
	/**
	 * 获得需要填充的人员
	 * @author zhangg on 2009-10-14
	 * @param psncomputeVO
	 * @param waclassVO
	 * @return
	 */
	private PsnWorkDayVO[] findNeedFillPsn(PsncomputeVO[] psncomputeVOs, WaLoginVO  waclassVO){
		List<PsnWorkDayVO> listPsnWorkDayVOs = new LinkedList<PsnWorkDayVO>();
		for (PsncomputeVO psncomputeVO : psncomputeVOs) {
			// 2015-12-28 NCdp205566203 zhousze 对于已经计算了的人员，不需要重新设置基准天数 begin
			//20151121 shenliangc 时点薪资不论是否计算都重新设置基准天数。
			if (psncomputeVO.getPk_psncompute() == null||psncomputeVO.getIscompute().equals(false)) {
				UFDouble d1 = psncomputeVO.getWanceforedays();
				UFDouble d2 = psncomputeVO.getWanafterdays();

				if (d1 == null || d2 == null) {
					PsnWorkDayVO psnWorkDayVO = new PsnWorkDayVO();
					psnWorkDayVO.setBasedays(psncomputeVO.getBasedays());
					psnWorkDayVO.setPk_psndoc(psncomputeVO.getPk_psndoc());
					psnWorkDayVO.setAccyear(waclassVO.getPeriodVO().getCaccyear());
					psnWorkDayVO.setAccperiod(waclassVO.getPeriodVO().getCaccperiod());
					psnWorkDayVO.setWageBeginDate(psncomputeVO.getBegindate());
					//新加入的，时点薪资新的计算方法
					psnWorkDayVO.setEndDate(psncomputeVO.getEnddate());
					psnWorkDayVO.setOldBeginDate(psncomputeVO.getOldbegindate());
					psnWorkDayVO.setOldEndDate(psncomputeVO.getOldenddate());
					psnWorkDayVO.setPk_wa_item(psncomputeVO.getPk_wa_item());
					listPsnWorkDayVOs.add(psnWorkDayVO);
				}
		}
		}
		// end
		return (listPsnWorkDayVOs.size() == 0)? null: listPsnWorkDayVOs.toArray(new PsnWorkDayVO[listPsnWorkDayVOs.size()]);
	}

	/**
	 * 参数值为薪资期间天数时:
	 * 调薪前天数为当前薪资期间开始日期到调薪日期的天数；
	 * 调薪后天数为调薪开始日期到当前薪资期间的结束日期的天数；
	 * @author zhangg on 2009-10-14
	 * @param WaclassVO
	 * @param psnWorkDayVOs
	 */
	private void filledData0(PsnWorkDayVO[] psnWorkDayVOs, WaLoginVO waclassVO) {
		for (PsnWorkDayVO psnWorkDayVO : psnWorkDayVOs) {

			UFLiteralDate periodstart = waclassVO.getPeriodVO().getCstartdate();
			UFLiteralDate periodend = waclassVO.getPeriodVO().getCenddate();
			
			int sn=0;
			int en=0;
			int basedays =UFLiteralDate.getDaysBetween(periodstart,periodend)+1;

			if(psnWorkDayVO.getWageBeginDate().afterDate(periodstart)){
				if(psnWorkDayVO.getOldBeginDate()!=null&&!psnWorkDayVO.getOldEndDate().beforeDate(periodstart)){
					sn = psnWorkDayVO.getOldBeginDate().afterDate(periodstart)?UFLiteralDate.getDaysBetween(psnWorkDayVO.oldBeginDate, psnWorkDayVO.oldEndDate)+1:UFLiteralDate.getDaysBetween(periodstart, psnWorkDayVO.oldEndDate)+1;
				}
				en = psnWorkDayVO.getEndDate()!=null&&psnWorkDayVO.getEndDate().beforeDate(periodend)?UFLiteralDate.getDaysBetween(psnWorkDayVO.wageBeginDate,psnWorkDayVO.endDate)+1: UFLiteralDate.getDaysBetween(psnWorkDayVO.wageBeginDate, periodend) + 1;
			}else{
				en = UFLiteralDate.getDaysBetween(periodstart,psnWorkDayVO.endDate!=null&&psnWorkDayVO.endDate.before(periodend)?psnWorkDayVO.endDate:periodend) + 1;
			}
		
			psnWorkDayVO.setWanceforedays((new UFDouble(sn)).setScale(0, UFDouble.ROUND_UP));
			psnWorkDayVO.setWanafterdays((new UFDouble(en)).setScale(0, UFDouble.ROUND_UP));
			psnWorkDayVO.setBasedays((new UFDouble(basedays)).setScale(0, UFDouble.ROUND_UP));

		}
	}

	/**
	 * 参数值为薪资期间计薪日天数:
	 * 调薪前天数为当前薪资期间开始日期到调薪日期薪资期间计薪日天数；
	 * 调薪后天数为调薪日期到当前薪资期间结束日期薪资期间计薪日天数；
	 * @author zhangg on 2009-10-14
	 * @param psnWorkDayVOs
	 * @param waclassVO
	 * @throws BusinessException 
	 */

	private void filledData1(PsnWorkDayVO[] psnWorkDayVOs, WaLoginVO waclassVO) throws BusinessException {
		for (PsnWorkDayVO psnWorkDayVO : psnWorkDayVOs) {

			UFLiteralDate periodstart = waclassVO.getPeriodVO().getCstartdate();
			UFLiteralDate periodend = waclassVO.getPeriodVO().getCenddate();
			
			UFDouble sn = new UFDouble(0);
			UFDouble en = new UFDouble(0);
			UFDouble basedays = getWorkCalndPsnWageDays(waclassVO.getPk_org(),periodstart,periodend);

			if(psnWorkDayVO.getWageBeginDate().afterDate(periodstart)){
				if(psnWorkDayVO.getOldBeginDate()!=null&&!psnWorkDayVO.getOldEndDate().beforeDate(periodstart)){
					//有调薪前记录
					sn = psnWorkDayVO.getOldBeginDate().afterDate(periodstart)?getWorkCalndPsnWageDays(waclassVO.getPk_org(),psnWorkDayVO.oldBeginDate, psnWorkDayVO.oldEndDate):getWorkCalndPsnWageDays(waclassVO.getPk_org(),periodstart, psnWorkDayVO.oldEndDate);
				}
				en = psnWorkDayVO.getEndDate()!=null&&psnWorkDayVO.getEndDate().beforeDate(periodend)?getWorkCalndPsnWageDays(waclassVO.getPk_org(),psnWorkDayVO.wageBeginDate,psnWorkDayVO.endDate): getWorkCalndPsnWageDays(waclassVO.getPk_org(),psnWorkDayVO.wageBeginDate, periodend);
			}else{
				en = getWorkCalndPsnWageDays(waclassVO.getPk_org(),periodstart,psnWorkDayVO.endDate!=null&&psnWorkDayVO.endDate.before(periodend)?psnWorkDayVO.endDate:periodend);
			}

			psnWorkDayVO.setWanceforedays(sn==null?new UFDouble(0).setScale(0, UFDouble.ROUND_UP):sn.setScale(0, UFDouble.ROUND_UP));
			psnWorkDayVO.setWanafterdays(en==null?new UFDouble(0).setScale(0, UFDouble.ROUND_UP):en.setScale(0, UFDouble.ROUND_UP));
			psnWorkDayVO.setBasedays(basedays==null?new UFDouble(0).setScale(0, UFDouble.ROUND_UP):basedays.setScale(0, UFDouble.ROUND_UP));
		}
	}


	
	/**
	 * 得到两个日期间薪资期间计薪日天数
	 * @author suihang on 2012-3-7
	 * @param beginDay
	 * @param endDay
	 * @param pk_psndoc
	 * @return
	 * @throws DAOException 
	 */
	private UFDouble getWorkCalndPsnWageDays(String pk_org,UFLiteralDate beginDay, UFLiteralDate endDay) throws BusinessException {
		return getWorkCalendarPubService().getWorkCalndPsnWageDays( pk_org, beginDay,  endDay);
	}

	/**
	 * 参数值为考勤期间计薪日天数时，
	 * 调薪前天数为当前薪资期间对应考勤期间的开始日期到调薪日期人员的考勤期间计薪日天数；
	 * 调薪后天数为调薪开始日期到当前薪资期间对应考勤期间的结束日期人员的考勤期间计薪日天数；
	 * @author suihang on 2009-10-14
	 * @param psnWorkDayVOs
	 * @param waclassVO
	 * @throws BusinessException
	 */
	private void filledData2(PsnWorkDayVO[] psnWorkDayVOs, WaLoginVO waclassVO) throws BusinessException {

		for (PsnWorkDayVO psnWorkDayVO : psnWorkDayVOs) {

			UFLiteralDate periodstart = waclassVO.getPeriodVO().getCstartdate();
			UFLiteralDate periodend = waclassVO.getPeriodVO().getCenddate();
			
			UFDouble sn = new UFDouble(0);
			UFDouble en = new UFDouble(0);
			UFDouble basedays = getTaPeriodPsnWageDays(periodstart,periodend,psnWorkDayVO.getPk_psndoc(),waclassVO.getPk_org());

			if(psnWorkDayVO.getWageBeginDate().afterDate(periodstart)){
				if(psnWorkDayVO.getOldBeginDate()!=null&&!psnWorkDayVO.getOldEndDate().beforeDate(periodstart)){
					//有调薪前记录
					sn = psnWorkDayVO.getOldBeginDate().afterDate(periodstart)?getTaPeriodPsnWageDays(psnWorkDayVO.oldBeginDate, psnWorkDayVO.oldEndDate,psnWorkDayVO.getPk_psndoc(),waclassVO.getPk_org()):getTaPeriodPsnWageDays(periodstart, psnWorkDayVO.oldEndDate,psnWorkDayVO.getPk_psndoc(),waclassVO.getPk_org());
				}
				en = psnWorkDayVO.getEndDate()!=null&&psnWorkDayVO.getEndDate().beforeDate(periodend)?getTaPeriodPsnWageDays(psnWorkDayVO.wageBeginDate,psnWorkDayVO.endDate,psnWorkDayVO.getPk_psndoc(),waclassVO.getPk_org()): getTaPeriodPsnWageDays(psnWorkDayVO.wageBeginDate, periodend,psnWorkDayVO.getPk_psndoc(),waclassVO.getPk_org());
			}else{
				en = getTaPeriodPsnWageDays(periodstart,psnWorkDayVO.endDate!=null&&psnWorkDayVO.endDate.before(periodend)?psnWorkDayVO.endDate:periodend,psnWorkDayVO.getPk_psndoc(),waclassVO.getPk_org());
			}
			
			//update chenth20180329 有半天的情况，精度不能设置为0,
			psnWorkDayVO.setWanceforedays(sn==null?new UFDouble(0).setScale(0, UFDouble.ROUND_UP):sn.setScale(1, UFDouble.ROUND_UP));
			psnWorkDayVO.setWanafterdays(en==null?new UFDouble(0).setScale(0, UFDouble.ROUND_UP):en.setScale(1, UFDouble.ROUND_UP));
			psnWorkDayVO.setBasedays(basedays==null?new UFDouble(0).setScale(0, UFDouble.ROUND_UP):basedays.setScale(1, UFDouble.ROUND_UP));
			//update end
		}

	}
	

	/**
	 * 取规定日期段内考勤期间计薪日天数（基准期间、调薪前天数、调薪后天数）
	 * @author suihang on 2012-3-7
	 * @param beginDay
	 * @param endDay
	 * @param pk_psndoc
	 * @param pk_org
	 * @return
	 * @throws BusinessException 
	 */
	
	private UFDouble getTaPeriodPsnWageDays(UFLiteralDate beginDay, UFLiteralDate endDay,String pk_psndoc,String pk_org) throws BusinessException {
		return  getPsnCalendarQueryService().taPeriodPsnWageDays(beginDay, endDay, pk_psndoc,pk_org);
	}
	
	/**
	 * 得到时点薪资的天数和日薪
	 * @author zhangg on 2009-10-14
	 * @param psncomputeVOs
	 * @param waclassVO
	 * @throws BusinessException
	 */
	public void setWorkDayAndWage(PsncomputeVO[] psncomputeVOs, WaLoginVO waclassVO) throws BusinessException {
		if(psncomputeVOs == null) {
			return;
		}
		PsnWorkDayVO[] psnWorkDayVOs = findNeedFillPsn(psncomputeVOs, waclassVO);
		if (psnWorkDayVOs != null) {

			
			Integer workdayType = getWorkdayType(waclassVO.getPk_org());
			//默认为薪资期间天数 
			
			//IHrPara para = NCLocator.getInstance().lookup(IHrPara.class);
			//Integer workdayType = para.getIntValue(waclassVO.getPk_org(), "WA-TRN001", null, null);
			if (workdayType == null ||workdayType == 0) {
				filledData0(psnWorkDayVOs, waclassVO);
			} else if (workdayType == 1) {
				filledData1(psnWorkDayVOs, waclassVO);
//				filledData0(psnWorkDayVOs, waclassVO);
			} else if (workdayType == 2) {
				filledData2(psnWorkDayVOs, waclassVO);
			} else {
				filledData0(psnWorkDayVOs, waclassVO);
			}
		}

		for (PsncomputeVO psncomputeVO : psncomputeVOs) {
			psncomputeVO.setSelect(!psncomputeVO.getIscompute());
			if (psncomputeVO.getOldwadocnmoney() == null) {
				psncomputeVO.setOldwadocnmoney(new UFDouble(0));
			}
			// 调薪前日薪为调薪前金额除以(调薪前天数+调薪后天数)；
			// 调薪后日薪为调薪后金额除以(调薪前天数+调薪后天数)。
			if (psnWorkDayVOs != null) {
				for (PsnWorkDayVO psnWorkDayVO : psnWorkDayVOs) {
					if (psnWorkDayVO.getPk_psndoc().equals(psncomputeVO.getPk_psndoc()) && psnWorkDayVO.getPk_wa_item().equals(psncomputeVO.getPk_wa_item())) {
						//同一个人员可能有多个项目是时点薪资。
						psncomputeVO.setWanceforedays(psnWorkDayVO.getWanceforedays());
						psncomputeVO.setWanafterdays(psnWorkDayVO.getWanafterdays());
						psncomputeVO.setBasedays(psnWorkDayVO.getBasedays());

						UFDouble allDay = psncomputeVO.getWanceforedays().add(psncomputeVO.getWanafterdays());//调薪前天数+调薪后天数
						
						if(psncomputeVO.getBasedays() == null || psncomputeVO.getBasedays().doubleValue() == 0){
							psncomputeVO.setBasedays(allDay);
						}else{
							allDay = psncomputeVO.getBasedays();
						}
						
						UFDouble olddaypay = new UFDouble(0.0);
						UFDouble nowdaypay = new UFDouble(0.0);
						UFDouble oneday = new UFDouble(1.0);
						if(allDay.doubleValue() >= oneday.doubleValue()){//如果合起来还小于1天则日薪都为0
							olddaypay = psncomputeVO.getOldwadocnmoney().div(new UFDouble(allDay),6);
							nowdaypay = psncomputeVO.getWadocnmoney().div(new UFDouble(allDay),6);
						}
						psncomputeVO.setWanbeforemoney(olddaypay);
						psncomputeVO.setWanaftermoney(nowdaypay);
//						psncomputeVO.setBasedays(psnWorkDayVO.getBasedays());
					}
				}
			}
		}
	}
	
	
	private Integer getWorkdayType(String pk_org){
		Integer workdayType2 = 0;
		try {
			workdayType2 = SysinitAccessor.getInstance().getParaInt(pk_org, ParaConstant.ONTIMES_WORKDAYS);
		} catch (Exception e) {
			Logger.error(e.getMessage(), e);
			workdayType2 = 0;
		}
		
		return workdayType2;
		
	}
	
	private IPsnCalendarQueryService getPsnCalendarQueryService(){
		if(psnCalendarQueryService==null){
			psnCalendarQueryService = NCLocator.getInstance().lookup(IPsnCalendarQueryService.class);
		}
		return psnCalendarQueryService;
	}
	private IWorkCalendarPubService getWorkCalendarPubService(){
		if(workCalendarPubService==null){
			workCalendarPubService = NCLocator.getInstance().lookup(IWorkCalendarPubService.class);
		}
		return workCalendarPubService;
	}
}
