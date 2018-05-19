package nc.bs.bhs.rule;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.bs.logging.Logger;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.jdbc.framework.SQLParameter;
import nc.jdbc.framework.processor.ResultSetProcessor;
import nc.vo.bhs.blackbox.BDDocVO;
import nc.vo.bhs.blackbox.BlackBoxConstant;
import nc.vo.bhs.blackbox.SkillRequirementCntVO;
import nc.vo.bhs.blackbox.SkillRequirementVO;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.saleorder.SoOrderBBVO;

import org.apache.commons.lang.ArrayUtils;

public class BhsCheckReqBeforeRule implements IRule<AbstractBill> {

	@Override
	public void process(AbstractBill[] vos) {
		 if (ArrayUtils.isEmpty(vos)) {
		      return;
		 }
		 Map<String, BDDocVO> skillMap = getBDDocInfo(BlackBoxConstant.DEFDOC_SKILL);
		 Map<String, BDDocVO> certificateMap = getBDDocInfo(BlackBoxConstant.DEFDOC_CERTIFICATE);
		 
		 
		 checkEnoughSkillUser(vos, skillMap, certificateMap);
		 updateHeadSkills(vos, skillMap, certificateMap);
		
	}

	
	private void checkEnoughSkillUser(AbstractBill[] vos, Map<String, BDDocVO> skillMap, Map<String, BDDocVO> certificateMap) {
		StringBuffer errStr = new StringBuffer();
		for (AbstractBill vo : vos) {	
			ISuperVO[] bbvos = vo.getChildren(SoOrderBBVO.class);
			if (bbvos == null || bbvos.length < 1)
				continue;
			
			CircularlyAccessibleValueObject headVo = vo.getParentVO();
			String csaleorderid = (String) headVo.getAttributeValue("csaleorderid");
			UFDateTime dstartdate = (UFDateTime) headVo.getAttributeValue("startdate");
			UFDateTime denddate = (UFDateTime) headVo.getAttributeValue("enddate");
			if(dstartdate == null 
					|| denddate == null)
				continue;
			
			Map<String, Integer> skillUserCntMap = querySkillUser(dstartdate, denddate);
			Map<String, SkillRequirementCntVO> skillRequirementMap = querySkillRequirement(csaleorderid, dstartdate, denddate);
			
			String jobcertificateid = (String) headVo.getAttributeValue("vdef20");
			String isNeedCertificate = (String) headVo.getAttributeValue("vdef19");
			
			String skillid = null;
			String certificateid = null;
			String key = null;
			Integer skillUserCnt = 0;
			Integer skillUserReqCnt = 0;
			String skillname = null;
			SkillRequirementCntVO skillReqCntVO = null;
			for (ISuperVO bbvo : bbvos) {
				SoOrderBBVO obbvo = (SoOrderBBVO) bbvo;
				if (obbvo.getSkill() == null) 
					continue;
				skillid = obbvo.getSkill();
				certificateid = obbvo.getCertificate();
				//表体证书要求为空的话，取表头的
				if(UFBoolean.TRUE.equals(new UFBoolean(isNeedCertificate))
						&& certificateid == null){
					certificateid = jobcertificateid;
				}
				
				key = certificateid == null ? skillid : skillid + certificateid;
				
				skillname = getSkillName(skillid, certificateid, skillMap, certificateMap);
				
				skillUserCnt = skillUserCntMap.get(key);
				if(skillUserCnt == null
						|| skillUserCnt == 0){
					errStr.append(skillname).append(" does not have the right person. \n");
					continue;
				}
				
				skillUserReqCnt = obbvo.getNumberofpeople();
				if(skillUserReqCnt > skillUserCnt){
					errStr.append(skillname).append(" does not have enough people. ")
					.append(" Total:").append(skillUserCnt)
					.append(" Requirement:").append(skillUserReqCnt).append(" \n");
					continue;
				}
				skillReqCntVO = skillRequirementMap.get(key);
				if(skillReqCntVO != null){
					skillUserReqCnt = skillUserReqCnt + skillReqCntVO.getNumberofpeople();
					if(skillUserReqCnt > skillUserCnt){
						errStr.append(skillname).append(" does not have enough people. ")
						.append(" Total:").append(skillUserCnt)
						.append(" Requirement:").append(skillUserReqCnt).append(" Other Job Order Required:\n");
						
						List<SkillRequirementVO> skillReqVOs = skillReqCntVO.getSkillReqList();
						for(SkillRequirementVO skillReqVO : skillReqVOs){
							errStr.append("   Job Order:").append(skillReqVO.getVbillcode()).append(" Requirement:").append(skillReqVO.getNumberofpeople()).append(" \n");
						}
					}
				}
			}
		}
		if(errStr.length() > 0){
			ExceptionUtils.wrappBusinessException(errStr.toString());
		}
	}
	
	
	private String getSkillName(String skillid, String certificateid,
			Map<String, BDDocVO> skillMap, Map<String, BDDocVO> certificateMap) {
		StringBuffer skillname = new StringBuffer();
		BDDocVO skill = skillMap.get(skillid);
		BDDocVO certificate = certificateMap.get(certificateid);
		if(skill != null){
			skillname.append("Skill:[").append(skill.getName()).append("] ");
		}
		if(certificate != null){
			skillname.append(" Certificate:[").append(certificate.getName()).append("] ");
		}
		return skillname.toString();
	}


	private Map<String, SkillRequirementCntVO> querySkillRequirement(
			String csaleorderid, UFDateTime dstartdate, UFDateTime denddate) {
		StringBuffer sb = new StringBuffer();

		sb.append(" select h.csaleorderid, h.vbillcode, b.skill, b.certificate, b.numberofpeople ");
		sb.append(" from so_saleorder h inner join so_saleorder_bblack b on h.csaleorderid = b.csaleorderid ");
		sb.append(" where isnull(h.dr,0) = 0 and isnull(b.dr,0) = 0 ");
		sb.append(" and ((h.startdate >= ? and h.startdate < ?) ");
		sb.append(" or (h.enddate > ? and h.enddate <= ?) ) ");
		
		BaseDAO dao = new BaseDAO();
		SQLParameter parameter = new SQLParameter();
		parameter.addParam(dstartdate);
		parameter.addParam(denddate);
		parameter.addParam(dstartdate);
		parameter.addParam(denddate);
		if(csaleorderid != null){
			sb.append(" and h.csaleorderid != ? ");
			parameter.addParam(csaleorderid);
		}
		Map<String, SkillRequirementCntVO>  skillRequirementMap = null;
		try {
			skillRequirementMap = (Map<String, SkillRequirementCntVO>) dao.executeQuery(sb.toString(), parameter, new SkillReqRSProcessor());
		} catch (DAOException e) {
			Logger.error("BhsCheckReqBeforeRule.querySkillRequirement() faild:" + e.getMessage(), e);
			ExceptionUtils.wrappException(e);
		}
		return skillRequirementMap;
	}


	private Map<String, Integer> querySkillUser(UFDateTime dstartdate,
			UFDateTime denddate) {
		StringBuffer sb = new StringBuffer();

		sb.append(" select distinct s.pk_psndoc, s.glbdef1 as skillid, c.glbdef1 as certificateid FROM  hi_psndoc_glbdef3 s ");
		sb.append(" left join hi_psndoc_glbdef4 c on s.pk_psndoc = c.pk_psndoc ");
		sb.append(" where isnull(s.dr,0) = 0 and isnull(c.dr,0) = 0 ");
		sb.append(" and exists( select 1 from hi_psnjob j where s.pk_psndoc = j.pk_psndoc and j.dr=0 and j.poststat = 'Y') ");
		sb.append(" and s.pk_psndoc not in( ");
		sb.append("    select pk_psndoc from tbm_leavereg where ");
		sb.append("      ((leavebegintime >=  ?  and leavebegintime < ?) or (leaveendtime >  ?  and leaveendtime <= ?)) ");
		sb.append(" ) ");
		sb.append(" and s.pk_psndoc not in( ");
		sb.append("    select assigned_user_id from oz_joborder_user ");
		sb.append("    where ((substring(not_comming_informed_date,0,11) + ' 00:00:00' >=  ?  and substring(not_comming_informed_date,0,11) + ' 00:00:00' < ?) ");
		sb.append("       or (substring(not_comming_informed_date,0,11) + ' 23:59:59' >  ?  and substring(not_comming_informed_date,0,11) + ' 23:59:59' <= ?)) ");
		sb.append(" ) ");
		sb.append(" and (c.glbdef3 is null or substring(c.glbdef3,0,11) >= ? ) ");
		
		BaseDAO dao = new BaseDAO();
		SQLParameter parameter = new SQLParameter();
		parameter.addParam(dstartdate);
		parameter.addParam(denddate);
		parameter.addParam(dstartdate);
		parameter.addParam(denddate);
		parameter.addParam(dstartdate);
		parameter.addParam(denddate);
		parameter.addParam(dstartdate);
		parameter.addParam(denddate);
		parameter.addParam(denddate.getDate());
		Map<String, Integer>  skillUserCntMap = null;
		try {
			skillUserCntMap = (Map<String, Integer>) dao.executeQuery(sb.toString(), parameter, new SkillRSProcessor());
		} catch (DAOException e) {
			Logger.error("BhsCheckReqBeforeRule.querySkillUser() faild:" + e.getMessage(), e);
			ExceptionUtils.wrappException(e);
		}
		return skillUserCntMap;
	}


	private void updateHeadSkills(AbstractBill[] vos, Map<String, BDDocVO> skillMap, Map<String, BDDocVO> certificateMap) {
		for (AbstractBill vo : vos) {
			ISuperVO[] bbvos = vo.getChildren(SoOrderBBVO.class);
			if (bbvos == null || bbvos.length < 1)
				continue;
			StringBuffer skills = new StringBuffer();
			for (ISuperVO bbvo : bbvos) {
				SoOrderBBVO obbvo = (SoOrderBBVO) bbvo;
				if (obbvo.getSkill() != null) {
					skills.append(skillMap.get(obbvo.getSkill()).getName())
							.append("(").append(obbvo.getNumberofpeople())
							.append(");");
				}
			}
			if(skills.length() > 0){
				vo.getParentVO().setAttributeValue(SaleOrderHVO.VDEF14, skills.toString());
			}

		}
	}

	private Map<String, BDDocVO> getBDDocInfo(String docType) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select a.pk_defdoc as id, a.code as code, a.name as name ");
		sb.append(" from bd_defdoc a inner join  bd_defdoclist b on a.pk_defdoclist = b.pk_defdoclist  ");
		sb.append(" where b.code= ? order by code ");
		
		BaseDAO dao = new BaseDAO();
		SQLParameter parameter = new SQLParameter();
		parameter.addParam(docType);
		Map<String, BDDocVO>  docVOs = null;
		try {
			docVOs = (Map<String, BDDocVO>) dao.executeQuery(sb.toString(), parameter, new BDDocRSProcessor());
		} catch (DAOException e) {
			Logger.error("BhsCheckReqBeforeRule.getBDDocInfo() faild:" + e.getMessage(), e);
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
	
	class SkillRSProcessor implements ResultSetProcessor{
		public SkillRSProcessor(){
		}

		@Override
		public Object handleResultSet(ResultSet rs) throws SQLException {
			if (rs == null)
				return null;
			Map<String, Integer>  skillUserCntMap = new HashMap<String, Integer>();
			Map<String, Set<String>> skillUserMap = new HashMap<String, Set<String>>();
			Set<String> pk_psndocs = null;
			String skillid = null;
			String certificateid = null;
			String pk_psndoc = null;
			String key = null;
			Integer cnt = null;
			while (rs.next()) {
				pk_psndoc = rs.getString("pk_psndoc");
				skillid = rs.getString("skillid");
				certificateid = rs.getString("certificateid");
				if(certificateid != null){
					key = skillid + certificateid;
					cnt = skillUserCntMap.get(key);
					if(cnt == null){
						cnt = 0;
					}
					cnt = cnt + 1;
					skillUserCntMap.put(key, cnt);
				}
				
				pk_psndocs = skillUserMap.get(skillid);
				if(pk_psndocs != null
						&& pk_psndocs.contains(pk_psndoc))
					continue;
				
				cnt = skillUserCntMap.get(skillid);
				if(cnt == null){
					cnt = 0;
				}
				cnt = cnt + 1;
				skillUserCntMap.put(skillid, cnt);
				
				
				if(pk_psndocs == null){
					pk_psndocs = new HashSet<String>();
					skillUserMap.put(skillid, pk_psndocs);
				}
				pk_psndocs.add(pk_psndoc);
				
				
			}
			return skillUserCntMap;
		}
	}
	
	
	class SkillReqRSProcessor implements ResultSetProcessor{
		public SkillReqRSProcessor(){
		}

		@Override
		public Object handleResultSet(ResultSet rs) throws SQLException {
			if (rs == null)
				return null;
			Map<String, SkillRequirementCntVO> skillRequirementMap = new HashMap<String, SkillRequirementCntVO>();
			String skillid = null;
			String certificateid = null;
			String key = null;
			SkillRequirementVO skillReqVO = null;
			SkillRequirementCntVO skillReqCntVO = null;
			Integer numberofpeople = null;
			while (rs.next()) {
				skillid = rs.getString("skill");
				certificateid = rs.getString("certificate");
				numberofpeople = rs.getInt("numberofpeople");
				skillReqVO = new SkillRequirementVO();
				skillReqVO.setCsaleorderid(rs.getString("csaleorderid"));
				skillReqVO.setVbillcode(rs.getString("vbillcode"));
				skillReqVO.setSkillid(skillid);
				skillReqVO.setCertificateid(certificateid);
				skillReqVO.setNumberofpeople(numberofpeople);
				
				skillReqCntVO = skillRequirementMap.get(skillid);
				if(skillReqCntVO == null){
					skillReqCntVO = new SkillRequirementCntVO();
					skillReqCntVO.setSkillid(skillid);
					skillReqCntVO.setNumberofpeople(0);
					skillRequirementMap.put(skillid, skillReqCntVO);
				}
				skillReqCntVO.setNumberofpeople(numberofpeople + skillReqCntVO.getNumberofpeople());
				skillReqCntVO.getSkillReqList().add(skillReqVO);
				
				if(certificateid != null){
					key = skillid + certificateid;
					skillReqCntVO = skillRequirementMap.get(key);
					if(skillReqCntVO == null){
						skillReqCntVO = new SkillRequirementCntVO();
						skillReqCntVO.setSkillid(skillid);
						skillReqCntVO.setCertificateid(certificateid);
						skillReqCntVO.setNumberofpeople(0);
						skillRequirementMap.put(key, skillReqCntVO);
					}
					skillReqCntVO.setNumberofpeople(numberofpeople + skillReqCntVO.getNumberofpeople());
					skillReqCntVO.getSkillReqList().add(skillReqVO);
				}
			}
			return skillRequirementMap;
		}
	}
	
	private String null2String(Object obj){
		if(obj == null
				|| obj.toString().equals("~")){
			return null;
		}
		return obj.toString();
			
	}

}
