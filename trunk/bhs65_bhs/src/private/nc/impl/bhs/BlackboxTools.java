package nc.impl.bhs;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.bs.framework.common.NCLocator;
import nc.bs.logging.Logger;
import nc.jdbc.framework.SQLParameter;
import nc.jdbc.framework.processor.ResultSetProcessor;
import nc.pubitf.bhs.api.IBlackBox;
import nc.vo.bhs.blackbox.BDDocVO;
import nc.vo.bhs.blackbox.BlackBoxConstant;
import nc.vo.bhs.blackbox.JobOrder;
import nc.vo.bhs.blackbox.JobOrderSkillUsers;
import nc.vo.bhs.blackbox.JobOrderUsers;
import nc.vo.bhs.blackbox.PlanInfoVO;
import nc.vo.bhs.somove.AggSoMoveHVO;
import nc.vo.bhs.somove.SoMoveBlackBoxVO;
import nc.vo.bhs.somove.SoMoveHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class BlackboxTools {

	public void checkAndUpdateSkills(AggSoMoveHVO[] clientFullVOs) throws BusinessException {
		Map<String, BDDocVO> skillMap = getBDDocInfo(BlackBoxConstant.DEFDOC_SKILL_PK);
		Map<String, BDDocVO> certificateMap = getBDDocInfo(BlackBoxConstant.DEFDOC_CERTIFICATE_PK);
		checkEnoughSkillUser(clientFullVOs, skillMap, certificateMap);
		skillsToHeader(clientFullVOs, skillMap, certificateMap);
	}
	public void skillsToHeader(AggSoMoveHVO[] clientFullVOs, Map<String, BDDocVO> skillMap, Map<String, BDDocVO> certificateMap) throws BusinessException {
		Integer totalPeoples = 0;
		int num = 0;
		for (AggSoMoveHVO vo : clientFullVOs) {
			List<SoMoveBlackBoxVO> bbvos = getBlackBoxVOs(vo);
			if (bbvos == null || bbvos.size() < 1)
				continue;
			
			totalPeoples = 0;
			StringBuilder sb = new StringBuilder();
			for (CircularlyAccessibleValueObject bbvo : bbvos) {
				SoMoveBlackBoxVO obbvo = (SoMoveBlackBoxVO) bbvo;
				if (obbvo.getSkill() != null) {
					sb.append(skillMap.get(obbvo.getSkill()).getName());
					num = obbvo.getNumberofpeople() == null ? 0 : obbvo.getNumberofpeople();
					sb.append("(").append(num).append("); ");
					totalPeoples = totalPeoples + num;
				}
			}
			if(sb.length() > 0){
				vo.getParentVO().setAttributeValue("def14", "Total: (" + totalPeoples + "); " + sb.toString());
			}
		}
	}
	
	
	private void checkEnoughSkillUser(AggSoMoveHVO[] vos, Map<String, BDDocVO> skillMap, Map<String, BDDocVO> certificateMap) throws BusinessException {
		boolean isShowErr = false;
		StringBuffer errStr = new StringBuffer();
		errStr.append("There are not enough worker to arrange. The prescheduling is as follows: ");
		
		for (AggSoMoveHVO vo : vos) {	
			List<SoMoveBlackBoxVO> bbvos = getBlackBoxVOs(vo);
			if (bbvos == null || bbvos.size() < 1)
				continue;
			
			CircularlyAccessibleValueObject headVo = vo.getParentVO();
			UFDateTime dstarttime = (UFDateTime) headVo.getAttributeValue("departuretime");
			UFDateTime dendtime = (UFDateTime) headVo.getAttributeValue("jobendtime");
			if(dstarttime == null 
					|| dendtime == null)
				continue;
			
			if(dstarttime.after(dendtime)){
				ExceptionUtils.wrappBusinessException("Departure Time can not later than Job End time.");
			}
			
			JobOrder newOrder = createJobOrder(vo, skillMap, certificateMap);
			
			PlanInfoVO planinfo = new PlanInfoVO();
			planinfo.setPlandate(dstarttime.getDate().toStdString());
			planinfo.setNewOrder(newOrder);
			
			if(dstarttime.getDate().isSameDate(dendtime.getDate())){
			    isShowErr = preSchedule(planinfo, errStr);
			}else{
				boolean result = false;
				UFDate plandate = dstarttime.getDate();
				while(plandate.compareTo(dendtime.getDate()) < 1){
					planinfo.setPlandate(plandate.toStdString());
					result = preSchedule(planinfo, errStr);
					if(result == true){
						isShowErr = result;
					}
					plandate = plandate.getDateAfter(1);
				}
			}
		}
				
		if(isShowErr){
			ExceptionUtils.wrappBusinessException(errStr.toString());
		}
	}
	
	/**
	 * //预排班
	 *	    //Job No: JOB001
	 *	    //	Skill: xxx(5); Arranged worker：AAA, BBB, CCC; Gap：2 "
	 * @param planinfo
	 * @param errStr
	 * @return
	 * @throws BusinessException
	 */
	private boolean preSchedule(PlanInfoVO planinfo, StringBuffer errStr) throws BusinessException {
		boolean isShowErr = false;
		
		//预排班
		IBlackBox blackBox =
	            NCLocator.getInstance().lookup(IBlackBox.class);
	    JobOrder[] orders = blackBox.getBlackBox(planinfo);
	    
	    errStr.append("\nJob Date: ").append(planinfo.getPlandate());
	    StringBuffer tmpStr = null;
	    String userName = null;
	    for(JobOrder order : orders){
	    	errStr.append("\n        Job No:").append(order.getSoNo());
	    	List<JobOrderSkillUsers> jobOrderSkills = order.getJobOrderSkillUsers();
	    	if(jobOrderSkills != null && jobOrderSkills.size() > 0){
				for(JobOrderSkillUsers skill : jobOrderSkills){
					int gap = skill.getCount() - skill.getPlanedcount();
					if(gap > 0){
						isShowErr = true;
					}
					errStr.append("\n                [").append(skill.getSkillCategoryName()).append("] Gap: (").append(gap);
					errStr.append(") ; Arranged: (").append(skill.getPlanedcount());
					errStr.append(") ; Total: (").append(skill.getCount());
					errStr.append(") ; Arranged worker: ");
					List<JobOrderUsers> orderUsers = skill.getJobOrderUsers();
					if(orderUsers != null && orderUsers.size() > 0){
						tmpStr = new StringBuffer();
	    				for(JobOrderUsers orderUser : orderUsers){
	    					userName = orderUser.getAssignedUserName() == null ? "" : orderUser.getAssignedUserName();
	    					tmpStr.append(userName).append(", ");
	    				}
	    				errStr.append(tmpStr.substring(0, tmpStr.length() - 2));
	    			}
				}
			}
	    }
		return isShowErr;
	}



	private JobOrder createJobOrder(AggSoMoveHVO vo, Map<String, BDDocVO> skillMap, Map<String, BDDocVO> certificateMap) {
		SoMoveHVO jobvo = vo.getParentVO();
		String jobid = jobvo.getBillid();
		if(jobid == null){
			jobid = "NEWJOBID";
		}
		JobOrder order = new JobOrder();
		order.setJobId(jobid);
		order.setSoNo(jobvo.getVbillno());
		order.setSubject(jobvo.getSubject());
		order.setCustomerId(jobvo.getFromcorp());
		order.setTStartDate(jobvo.getDeparturetime());
		order.setTEndDate(jobvo.getJobendtime());
		order.setStartDate(order.getTStartDate().getMillis() );
		order.setEndDate(order.getTEndDate().getMillis());
		order.setRefjoborderID(jobvo.getDef16());
		order.setCombinejoborderID(jobvo.getDef15());
		
		List<SoMoveBlackBoxVO> bbvos = getBlackBoxVOs(vo);
		String certificateid = null;
		String jobcertificateid = jobvo.getDef20(); 
		
		BDDocVO docVo = null;
		for(SoMoveBlackBoxVO bbvo : bbvos){
			JobOrderSkillUsers orderSkill = new JobOrderSkillUsers();
			orderSkill.setJobId(jobid);
			orderSkill.setSkillCategoryId(bbvo.getSkill());
			docVo = skillMap.get(bbvo.getSkill());
			if(docVo != null){
				orderSkill.setSkillCategoryName(docVo.getName());
			}
			certificateid = bbvo.getCertificate();
			if(certificateid == null){
				certificateid = jobcertificateid;
			}
			orderSkill.setCertificateId(certificateid);
			docVo = certificateMap.get(certificateid);
			if(docVo != null){
				orderSkill.setCertificateName(docVo.getName());
			}
			orderSkill.setCount(bbvo.getNumberofpeople()== null ? 0 : bbvo.getNumberofpeople());
			orderSkill.setVehicleno(bbvo.getDef1());
			orderSkill.setPlanedcount(0);
			
			updateJobSkill(order, orderSkill);
		}
		
		return order;
	}

	private List<SoMoveBlackBoxVO> getBlackBoxVOs(AggSoMoveHVO vo) {
		List<SoMoveBlackBoxVO> bbvoLst = null;
		SoMoveBlackBoxVO[] bbvos = (SoMoveBlackBoxVO[]) vo.getChildren(SoMoveBlackBoxVO.class);
		if(bbvos == null 
				|| bbvos.length < 1){
			return bbvoLst;
		}
		bbvoLst = new ArrayList<SoMoveBlackBoxVO>();
		for(SoMoveBlackBoxVO bbvo : bbvos){
			if(VOStatus.DELETED == bbvo.getStatus()){
				continue;
			}
			bbvoLst.add(bbvo);
		}
		return bbvoLst;
	}
	/**
	 * skill-certificateMap  1个skill可能需要不同种类的证书
	 * @param order
	 * @param sameSkillKey
	 * @param orderSkill
	 */
	private void updateJobSkill(JobOrder order,
			JobOrderSkillUsers orderSkill) {
		List<JobOrderSkillUsers> orderSkillUsers = order.getJobOrderSkillUsers();
		if(orderSkillUsers == null){
			orderSkillUsers = new ArrayList<JobOrderSkillUsers>();
			order.setJobOrderSkillUsers(orderSkillUsers);
		}
		orderSkillUsers.add(orderSkill);
		
		String sameSkillKey = order.getJobId() + orderSkill.getSkillCategoryId();
		List<JobOrderSkillUsers> sameSkill = order.getJobSkillMap().get(sameSkillKey);
		if(sameSkill == null){
			sameSkill = new ArrayList<JobOrderSkillUsers>();
			order.getJobSkillMap().put(sameSkillKey, sameSkill);
		}
		sameSkill.add(orderSkill);
	}

	private Map<String, BDDocVO> getBDDocInfo(String docType) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select a.pk_defdoc as id, a.code as code, a.name as name ");
		sb.append(" from bd_defdoc a where a.pk_defdoclist= ? order by code ");
		
		BaseDAO dao = new BaseDAO();
		SQLParameter parameter = new SQLParameter();
		parameter.addParam(docType);
		Map<String, BDDocVO>  docVOs = null;
		try {
			docVOs = (Map<String, BDDocVO>) dao.executeQuery(sb.toString(), parameter, new BDDocRSProcessor());
		} catch (DAOException e) {
			Logger.error("BlackboxTools.getBDDocInfo() faild:" + e.getMessage(), e);
			ExceptionUtils.wrappException(e);
		}
		return docVOs;
	}
	
	class BDDocRSProcessor implements ResultSetProcessor{
		public BDDocRSProcessor(){
		}

		@Override
		public Object handleResultSet(ResultSet rs) throws SQLException {
			if (rs == null)
				return null;
			Map<String, BDDocVO> docMap = new HashMap<String, BDDocVO>();
			while (rs.next()) {
				BDDocVO docVO = new BDDocVO();
				docVO.setId(rs.getString("id"));
				docVO.setCode(rs.getString("code"));
				docVO.setName(rs.getString("name"));
				docMap.put(docVO.getId(), docVO);
			}
			return docMap;
		}
	}
}
