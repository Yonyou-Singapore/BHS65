package nc.vo.ta.leave.pf.validator;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.bs.logging.Logger;
import nc.bs.uif2.validation.ValidationFailure;
import nc.bs.uif2.validation.Validator;
import nc.hr.utils.ResHelper;
import nc.itf.ta.IPeriodQueryService;
import nc.itf.ta.ITimeRuleQueryService;
import nc.itf.ta.algorithm.ITimeScope;
import nc.itf.ta.algorithm.TimeScopeUtils;
import nc.itf.ta.algorithm.impl.DefaultTimeScope;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.vo.bhs.somove.SoMoveHVO;
import nc.vo.format.Format;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFLiteralDate;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.ta.leave.AggLeaveVO;
import nc.vo.ta.leave.LeavebVO;
import nc.vo.ta.leave.LeavehVO;
import nc.vo.ta.period.PeriodVO;
import nc.vo.ta.timerule.TimeRuleVO;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;


public class PFSaveLeaveValidator implements Validator{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6239164844896346434L;

	public ValidationFailure validate(Object obj) {
		if(obj == null)
			return null;
		AggLeaveVO aggVO = (AggLeaveVO)obj;
		LeavehVO headVO = aggVO.getLeavehVO();
		LeavebVO[] leavebvos = aggVO.getLeavebVOs();
		String blandErr = ResHelper.getString("6017leave","06017leave0239")

/*@res "未填写休假明细信息！"*/;
		if(ArrayUtils.isEmpty(leavebvos))
			return new ValidationFailure(blandErr);
		
		IUAPQueryBS queryBS = (IUAPQueryBS) NCLocator.getInstance().lookup(IUAPQueryBS.class.getName());
		String userPk = headVO.getCreator();
		
		List<SoMoveHVO> moveVOs = null;
		try {
			StringBuilder whereInStr = new StringBuilder();
			whereInStr.append("select * from so_move_h where billid in (");
			whereInStr.append("select job_id from oz_joborder_user where assigned_user_id in(");
			whereInStr.append(userPk + ") and job_user_status_id != 'NC')");
			moveVOs = (List<SoMoveHVO>) queryBS.executeQuery(
					whereInStr.toString(), new BeanListProcessor(
							SoMoveHVO.class));

		} catch (BusinessException e1) {
			ExceptionUtils.wrappException(e1);
		}
		
		TimeRuleVO timeRule = NCLocator.getInstance().lookup(ITimeRuleQueryService.class).queryByOrgWithoutException(headVO.getPk_org());
		UFBoolean canOverYear = timeRule.getIscansaveoveryear();
		List<LeavebVO> subvos = new ArrayList<LeavebVO>();
		for(int i = 0; i < leavebvos.length; i++){
			if(VOStatus.DELETED == leavebvos[i].getStatus())
				continue;
			subvos.add(leavebvos[i]);
		}
		if(CollectionUtils.isEmpty(subvos))
			return new ValidationFailure(blandErr);

		LeavebVO[] leaveVOs = subvos.toArray(new LeavebVO[0]);
		boolean isLactation = headVO.getIslactation()==null?false:headVO.getIslactation().booleanValue();
		for(int i=0; i<leaveVOs.length; i++){
			// 校验工作交接人
			if(headVO.getPk_psnjob()!=null&&leaveVOs[i].getPk_agentpsn()!=null&&leaveVOs[i].getPk_agentpsn().equals(headVO.getPk_psnjob()))
				return new ValidationFailure(ResHelper.getString("6017leave","06017leave0240"
/*@res "{0}行工作交接人不能与休假人相同,请修改!"*/,Format.indexFormat(i+1)));

			// 校验时间
			if(isLactation)
			{
				if(leaveVOs[i].getLeavebegindate()==null||leaveVOs[i].getLeaveenddate()==null
						||leaveVOs[i].getLeavebegindate().after(leaveVOs[i].getLeaveenddate()))
					return new ValidationFailure(MessageFormat.format(ResHelper.getString("6017leave","06017leave0241")
/*@res "{0}行开始日期{1}晚于结束日期{2},请修改!"*/, Format.indexFormat(i+1),leaveVOs[i].getLeavebegindate(),leaveVOs[i].getLeaveenddate()));
			}
			else
			{
				if(leaveVOs[i].getLeavebegintime()==null||leaveVOs[i].getLeaveendtime()==null
						||leaveVOs[i].getLeavebegintime().after(leaveVOs[i].getLeaveendtime()))
					return new ValidationFailure(MessageFormat.format(ResHelper.getString("6017leave","06017leave0242")
/*@res "{0}行开始时间{1}晚于结束时间{2},请修改!"*/, Format.indexFormat(i+1),leaveVOs[i].getLeavebegintime(),leaveVOs[i].getLeaveendtime()));
			}

			//校验时间段冲突
			UFDateTime begin = leaveVOs[i].getLeavebegintime();
			UFDateTime end = leaveVOs[i].getLeaveendtime();
			if(isLactation)
			{
				begin = new UFDateTime(leaveVOs[i].getBegindate().toDate());
				end = new UFDateTime(leaveVOs[i].getEnddate().toDate());
			}
			
			// Check with somove
			for (SoMoveHVO moveHVO : moveVOs) {
				UFDateTime moveDeptTime = moveHVO.getDeparturetime();
				UFDateTime moveEndTime = moveHVO.getEnddate();
				if (moveDeptTime.after(begin) && moveDeptTime.before(end)) {
					return new ValidationFailure("Job order arranged, cannot apply leave.");
				}
				if (moveEndTime.after(begin) && moveEndTime.before(end)) {
					return new ValidationFailure("Job order arranged, cannot apply leave."); 
				}
			}
			
			//跨年度休假是否允许保存
			PeriodVO period1=null;
			PeriodVO period2 = null;
			try {
				period1 =NCLocator.getInstance().lookup(IPeriodQueryService.class).queryByDate(headVO.getPk_org(),new UFLiteralDate(begin.toString()));
				period2 =  NCLocator.getInstance().lookup(IPeriodQueryService.class).queryByDate(headVO.getPk_org(),new UFLiteralDate(end.toString()));
				if(period1 == null||period2 == null){
					return null;
				}
				if(!period1.getYear().equals(period2.getYear())){//判断开始时间和结束时间是否在同一年
					if(!canOverYear.booleanValue()||canOverYear==null){//考勤规则中的休假跨年是否保存
						return new ValidationFailure(ResHelper.getString("6017leave","06017leave0264"
								/*@res "{0}行 休假时间段跨年不允许保存!"*/,Format.indexFormat(i+1)));	
					}
				}
			} catch (BusinessException e) {
				Logger.error(e.getMessage(), e);
			}
			
			ITimeScope voScope = new DefaultTimeScope(begin,end);
			for(int j = 0; j < i; j++){
				UFDateTime thisBegin = leaveVOs[j].getLeavebegintime();
				UFDateTime thisEnd = leaveVOs[j].getLeaveendtime();
				if(isLactation)
				{
					begin = new UFDateTime(leaveVOs[j].getBegindate().toDate());
					end = new UFDateTime(leaveVOs[j].getEnddate().toDate());
				}
				ITimeScope thisScope = new DefaultTimeScope(thisBegin,thisEnd);
				//时间段是否有交集
				if(TimeScopeUtils.isCross(voScope, thisScope)){
					return new ValidationFailure(ResHelper.getString("6017leave","06017leave0243"
/*@res "{0}行 休假时间段与{1}行 休假时间段有交集!"*/,Format.indexFormat(j+1),Format.indexFormat(i+1)));
				}
			}
		}
		return null;
	}

}