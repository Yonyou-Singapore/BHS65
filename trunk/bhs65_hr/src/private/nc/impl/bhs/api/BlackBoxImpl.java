package nc.impl.bhs.api;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.bs.logging.Logger;
import nc.impl.pub.util.db.InSqlManager;
import nc.jdbc.framework.SQLParameter;
import nc.jdbc.framework.processor.ResultSetProcessor;
import nc.pubitf.bhs.api.IBlackBox;
import nc.vo.bhs.blackbox.BDDocVO;
import nc.vo.bhs.blackbox.BlackBoxConstant;
import nc.vo.bhs.blackbox.JobOrder;
import nc.vo.bhs.blackbox.JobOrderSkillUsers;
import nc.vo.bhs.blackbox.JobOrderUsers;
import nc.vo.bhs.blackbox.PlanInfoVO;
import nc.vo.bhs.blackbox.UserInfoVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.ta.leave.LeaveRegVO;

public class BlackBoxImpl implements IBlackBox{

	@Override
	public JobOrder[] getBlackBox(PlanInfoVO planinfo) throws BusinessException {
		String date = planinfo.getPlandate();
		JobOrder newOrder = planinfo.getNewOrder();
		
		//-----һ���Ű�ǰ����׼��------//
		Object[] joborderInfo = getJobOrderInfo(planinfo);
		if(joborderInfo == null){
			if(newOrder != null){
				Object[] returnObj = new Object[2];
				returnObj[0] = new HashMap<String, JobOrder>();
				returnObj[1] = null;
			}else{
				return null;
			}
		}
		//1.�������ڲ�ѯ���۶�����Ϣ
		Map<String,JobOrder> jobOrderMap = (Map<String, JobOrder>) joborderInfo[0];
		
		//У���¼Ӷ����󣬹��������Ƿ�������
		if(newOrder != null){
			jobOrderMap.put(newOrder.getJobId(), newOrder);
		}
		
		//�ȶ����ϲ�
		combineJobOrder(jobOrderMap);
		
		//�ٹ��������以�ཨ����ϵ
		refJobOrder(jobOrderMap);
		
		Collection<JobOrder> orders = jobOrderMap.values();
		//�ѷ�����Ա��Ϣ����ʼ������ʱ�䣩
		Map<String, UserInfoVO> assignedUserInfoMap = (Map<String, UserInfoVO>) joborderInfo[1];
		//��ѯ������ְ��Ա��Ϣkey:pk_psndoc
		Map<String, UserInfoVO> userMap = getUserInfo(date);
		//���ѷ����û��Ĺ���ʱ��ϲ����û���Ϣ��
		combineUserInfo(userMap, assignedUserInfoMap);
		
		//��ѯԱ������Ҫ�Űඩ���Ĺ���ʱ�䷶Χ�ڵ������Ϣkey:pk_psndoc
		Map<String, List<LeaveRegVO>> leaveMap = getLeaveInfo(orders);
		
		//��ѯ��Աskill��Ϣ
		Object[] skillUserMaps = getSkillUserInfo(date);
		if(skillUserMaps == null){
			return null;
		}
		Map<String, Set<String>> userKeySkillMap = (Map<String, Set<String>>) skillUserMaps[0];
		Map<String, Set<String>> skillKeyUserMap = (Map<String, Set<String>>) skillUserMaps[1];
		
		//��ѯ��Աcertificate��Ϣ
		Object[] certificateUserMaps = getCertificateUserInfo(date);
		Map<String, Set<String>> certificateKeyUserMap = null;
		Map<String, Set<String>> userKeyCertificateMap = null;
		if(certificateUserMaps == null){
			certificateKeyUserMap = new HashMap<String, Set<String>>();
			userKeyCertificateMap = new HashMap<String, Set<String>>();
		}else{
			userKeyCertificateMap = (Map<String, Set<String>>) certificateUserMaps[0];
			certificateKeyUserMap = (Map<String, Set<String>>) certificateUserMaps[1];
		}
		
		List<BDDocVO> skillVOs = getBDDocInfo(BlackBoxConstant.DEFDOC_SKILL);
		List<BDDocVO> certifacateVOs = getBDDocInfo(BlackBoxConstant.DEFDOC_CERTIFICATE);
		
		//��ѯ�ͻ�ƫ����Ϣkey:pk_customer:degree:Set<pk_psndoc>
		Map<String, Map<Integer,Set<String>>> customerMap = null;
		//��ѯƫ�ó̶�
		Integer[] degreeArr = null;
		Object[] custmerobj = getCustomerPreferInfo(orders);
		if(custmerobj != null){
			customerMap = (Map<String, Map<Integer, Set<String>>>) custmerobj[0];
			Set<Integer> degrees = (Set<Integer>) custmerobj[1];
			//ƫ�ó̶�����
			degreeArr = degrees.toArray(new Integer[degrees.size()]);
			Arrays.sort(degreeArr);
		}
		
		//-----������ʼ�Ű�------//
		//�������򣬰���ʼʱ����
		JobOrder[] orderArr = orders.toArray(new JobOrder[orders.size()]);
		Arrays.sort(orderArr);
		//��������Ű࣬�����ֱ���˳���ţ������������ж�����TeamLeader, �������ж���Mover
		Collections.sort(skillVOs);
		for(BDDocVO skillVo : skillVOs){
			//1�����Ȱ�����������
			assignByRefOrder(skillVo, orderArr, userKeySkillMap, userKeyCertificateMap, userMap, leaveMap);
			
			//2����ƫ�ó̶�����
			assignByDegree(degreeArr, customerMap, skillVo, orderArr, userKeySkillMap, userKeyCertificateMap, userMap, leaveMap);
			
			//3������ƫ����������
			assignNormal(skillKeyUserMap, skillVo, orderArr, userKeySkillMap, userKeyCertificateMap, userMap, leaveMap);
			
		}
		
		//�Զ��Ű�������㼼�ܡ�֤�顢��Ա���ƣ�
		afterAutoAssign(orderArr, planinfo, skillVOs, certifacateVOs, userMap);
		
		return orderArr;
	}


	private void combineJobOrder(Map<String, JobOrder> jobOrderMap) {
		JobOrder order = null;
		JobOrder combineOrder = null;
		String combineOrderID = null;
		List<String> jobids = new ArrayList<String>();
		
		for(String jobid : jobOrderMap.keySet()){
			order = jobOrderMap.get(jobid);
			
			combineOrderID = order.getCombinejoborderID();
			if(combineOrderID == null)
				continue ;
			
			//��JobOrder
			combineOrder = jobOrderMap.get(combineOrderID);
			if(combineOrder == null){
				return ;
			}
			
			//�ϲ����������Ĺ�������
			combineOrderSkills(order, combineOrder);
			
			//�ϲ�����������requirement��Ϣ����JobOrder
			combineOrder.setRequirement(combineOrder.getRequirement() + "<p>" + order.getRequirement());
			if(false == combineOrder.getSoNo().equals(order.getSoNo())){
				combineOrder.setSoNo(combineOrder.getSoNo() + ", " + order.getSoNo());
			}
			
			jobids.add(jobid);
		}
		
		//�ϲ��Ķ������ٵ����Ű�
		for(String jobid : jobids){
			jobOrderMap.remove(jobid);
		}
		
	}


	/**
	 * ���Ȱ�����������
	 * @param skillVo
	 * @param orderArr
	 * @param userKeySkillMap
	 * @param userKeyCertificateMap
	 * @param userMap
	 * @param leaveMap
	 */
	private void assignByRefOrder(BDDocVO skillVo, JobOrder[] orderArr,
			Map<String, Set<String>> userKeySkillMap,
			Map<String, Set<String>> userKeyCertificateMap,
			Map<String, UserInfoVO> userMap,
			Map<String, List<LeaveRegVO>> leaveMap) {
		Set<String> pk_users = null;
		for (JobOrder order : orderArr) {
			pk_users = getRefOrderSkillUser(order, skillVo);
			assignOrderSkill(order, skillVo, pk_users, userKeySkillMap, userKeyCertificateMap, userMap, leaveMap);
		}

	}
	
	/**
	 * ��ȡ����������ĳ�����ֵ��Ű���Ա
	 * @param order
	 * @param skillVo
	 * @return
	 */
	private Set<String> getRefOrderSkillUser(JobOrder order, BDDocVO skillVo) {
		Set<String> pk_users = new HashSet<String>();
		List<JobOrder> refJobOrders = order.getRefjoborder();
		if (refJobOrders == null)
			return pk_users;
		
		String skillKey = null;
		List<JobOrderUsers> refOrderSkillUsers = null;
		
		for(JobOrder refJobOrder : refJobOrders){
			skillKey = refJobOrder.getJobId() + skillVo.getId();
			refOrderSkillUsers = refJobOrder.getJobSkillUsersMap().get(skillKey);
			//�����Ķ���û����������ֵ���
			if(refOrderSkillUsers == null
					|| refOrderSkillUsers.size() < 1)
				continue;
			
			for(JobOrderUsers refOrderSkillUser : refOrderSkillUsers){
				pk_users.add(refOrderSkillUser.getAssignedUserId());
			}
		}
		return pk_users;
	}
	
	/**
	 * ��ĳ��������ĳ�������Űࣨ���Ȱ����������ţ�
	 * @param order
	 * @param skillVo
	 * @param pk_users ��ѡ��
	 * @param userKeySkillMap
	 * @param userKeyCertificateMap
	 * @param userMap
	 * @param leaveMap
	 */
	private void assignOrderSkillByRefOrder(JobOrder order, BDDocVO skillVo, Set<String> pk_users, 
			Map<String, Set<String>> userKeySkillMap,
			Map<String, Set<String>> userKeyCertificateMap,
			Map<String, UserInfoVO> userMap,
			Map<String, List<LeaveRegVO>> leaveMap) {
		Set<String> refOderusers = getRefOrderSkillUser(order, skillVo);
		//���Ȱ�����������
		assignOrderSkill(order, skillVo, refOderusers, userKeySkillMap, userKeyCertificateMap, userMap, leaveMap);
		
		assignOrderSkill(order, skillVo, pk_users, userKeySkillMap, userKeyCertificateMap, userMap, leaveMap);
	}


	/**
	 * ��ͨ�Ű�
	 * @param skillKeyUserMap
	 * @param skillVo
	 * @param orderArr
	 * @param userKeySkillMap
	 * @param userKeyCertificateMap
	 * @param userMap
	 * @param leaveMap
	 */
	private void assignNormal(Map<String, Set<String>> skillKeyUserMap, 
			BDDocVO skillVo, JobOrder[] orderArr,
			Map<String, Set<String>> userKeySkillMap, 
			Map<String, Set<String>> userKeyCertificateMap,
			Map<String, UserInfoVO> userMap,
			Map<String, List<LeaveRegVO>> leaveMap) {
		
		String pk_skill = skillVo.getId();
		Set<String> pk_users = skillKeyUserMap.get(pk_skill);
		//û��������ֵ���
		if(pk_users == null 
				|| pk_users.size() < 1)
			return ;
		
		for(JobOrder order : orderArr){
			assignOrderSkillByRefOrder(order, skillVo, pk_users, userKeySkillMap, userKeyCertificateMap, userMap, leaveMap);
		}
		
	}

	/**
	 * ��ƫ�ó̶��Ű�
	 * @param degreeArr
	 * @param customerMap
	 * @param skillVo
	 * @param orderArr
	 * @param userKeySkillMap
	 * @param userKeyCertificateMap
	 * @param userMap
	 * @param leaveMap
	 */
	private void assignByDegree(Integer[] degreeArr,
			Map<String, Map<Integer, Set<String>>> customerMap,
			BDDocVO skillVo, JobOrder[] orderArr,
			Map<String, Set<String>> userKeySkillMap,
			Map<String, Set<String>> userKeyCertificateMap,
			Map<String, UserInfoVO> userMap,
			Map<String, List<LeaveRegVO>> leaveMap) {
		if (degreeArr == null)
			return;

		String pk_customer = null;
		Map<Integer, Set<String>> customerPreferMap = null;
		Set<String> pk_users = null;

		for (Integer degree : degreeArr) {
			for (JobOrder order : orderArr) {
				//update chenth 20180409 �ĳ�tolocation
//				pk_customer = order.getCustomerAddressID();
				pk_customer = order.getTolocationid();
				customerPreferMap = customerMap.get(pk_customer);
				// ���ƫ�ó̶�û����Ա
				if (customerPreferMap == null
						|| customerPreferMap.get(degree) == null)
					continue;
				
				pk_users = customerPreferMap.get(degree);

				assignOrderSkillByRefOrder(order, skillVo, pk_users, userKeySkillMap,
						userKeyCertificateMap, userMap, leaveMap);
			}
		}

	}

	/**
	 * ��ĳ��������ĳ�������Ű�
	 * @param order
	 * @param skillVo
	 * @param pk_users ��ѡ��
	 * @param userKeySkillMap
	 * @param userKeyCertificateMap
	 * @param userMap
	 * @param leaveMap
	 */
	private void assignOrderSkill(JobOrder order, BDDocVO skillVo, Set<String> pk_users, 
			Map<String, Set<String>> userKeySkillMap,
			Map<String, Set<String>> userKeyCertificateMap,
			Map<String, UserInfoVO> userMap,
			Map<String, List<LeaveRegVO>> leaveMap) {
		String skillKey = null;
		skillKey = order.getJobId() + skillVo.getId();
		List<JobOrderSkillUsers> skills = order.getJobSkillMap().get(skillKey);
		
		//û�иù�������
		if(skills == null
				|| skills.size() < 1)
			return;
		
		for(JobOrderSkillUsers skill : skills){
			for(String pk_user : pk_users){
				assignUser(order, skill, pk_user, userKeySkillMap, userKeyCertificateMap, userMap, leaveMap);
			}
		}
		
	}

	/**
	 * ��ĳ��������ĳ������������ĳ��
	 * @param order
	 * @param skill
	 * @param pk_user
	 * @param userKeySkillMap
	 * @param userKeyCertificateMap
	 * @param userMap
	 * @param leaveMap
	 */
	private void assignUser(JobOrder order,
			JobOrderSkillUsers skill,String pk_user, 
			Map<String, Set<String>> userKeySkillMap,
			Map<String, Set<String>> userKeyCertificateMap, Map<String, UserInfoVO> userMap,
			Map<String, List<LeaveRegVO>> leaveMap) {
		//�ù��ֹ���������
		if(skill.getPlanedcount() >= skill.getCount())
			return;
		
		//�ж���������Ƿ����Ҫ�󣨼��ܡ�֤�顢�Ƿ���١���������Ĺ�����
		if(isTheUser(pk_user, order, skill, userKeySkillMap, userKeyCertificateMap, userMap, leaveMap)){
			UserInfoVO theUser = userMap.get(pk_user);
			JobOrderUsers orderUser = changeUser2OrderUser(theUser, skill);
			
			addUser2Skills(skill, orderUser, false);
			
			String sameSkillKey = order.getJobId() + skill.getSkillCategoryId();
			updateJobSkillUsersMap(order, sameSkillKey, orderUser);
			
			//����Ա��������Ϣ
			updateUserWorkTime(theUser, order);
		}
		
	}

	private void updateUserWorkTime(UserInfoVO theUser, JobOrder order) {
		if(theUser.getDstartdate() == null
				|| order.getTStartDate().before(theUser.getDstartdate())){
			theUser.setDstartdate(order.getTStartDate());
		}

		if(theUser.getDenddate() == null
				|| order.getTEndDate().after(theUser.getDenddate())){
			theUser.setDenddate(order.getTEndDate());
		}
	}


	private void addUser2Skills(JobOrderSkillUsers skill,
			JobOrderUsers orderUser, boolean isVirtual) {
		List<JobOrderUsers> orderUserLst = skill.getJobOrderUsers();
		if(orderUserLst == null){
			orderUserLst = new ArrayList<JobOrderUsers>();
			skill.setJobOrderUsers(orderUserLst);
		}
		orderUserLst.add(orderUser);
		if(false == isVirtual){
			skill.setPlanedcount(skill.getPlanedcount() + 1);
		}
	}

	private JobOrderUsers changeUser2OrderUser(UserInfoVO theUser, JobOrderSkillUsers skill) {
		JobOrderUsers orderUser = new JobOrderUsers();
		orderUser.setAssignedUserId(theUser.getPk_psndoc());
		orderUser.setAssignedUserName(theUser.getName());
		
		orderUser.setJobId(skill.getJobId());
		orderUser.setSkillCategoryId(skill.getSkillCategoryId());
		return orderUser;
	}

	/**
	 * �ж���������Ƿ����Ҫ�󣨼��ܡ�֤�顢�Ƿ���١���������Ĺ�����
	 * @param pk_user
	 * @param order
	 * @param skill
	 * @param userKeySkillMap 
	 * @param userKeyCertificateMap
	 * @param userMap
	 * @param leaveMap
	 * @return
	 */
	private boolean isTheUser(String pk_user, JobOrder order, JobOrderSkillUsers skill, 
			Map<String, Set<String>> userKeySkillMap,
			Map<String, Set<String>> userKeyCertificateMap, Map<String, UserInfoVO> userMap,
			Map<String, List<LeaveRegVO>> leaveMap) {
		String pk_certificate = null;
		UserInfoVO user = null;
		List<LeaveRegVO> leaveLst = null;
		UFDateTime startdate = order.getTStartDate();
		UFDateTime enddate = order.getTEndDate();
		String pk_skill = skill.getSkillCategoryId();
		
		//�жϷ������ϼ���Ҫ��
		if(userKeySkillMap.get(pk_user) == null
				|| false == userKeySkillMap.get(pk_user).contains(pk_skill))
			return false;
		
		
		//�жϷ�������֤��Ҫ��
		pk_certificate = skill.getCertificateId();
		if(pk_certificate != null 
				&& (userKeyCertificateMap.get(pk_user) == null
						|| false == userKeyCertificateMap.get(pk_user).contains(pk_certificate)))
			return false;
		
		//�ж��Ƿ��а�������������
		user = userMap.get(pk_user);
		if(isOverlap(startdate,enddate, user.getDstartdate(),user.getDenddate()))
			return false;
		
		//�ж��Ƿ����ݼ�
		if(leaveMap != null){
			leaveLst = leaveMap.get(pk_user);
			if(leaveLst != null){
				boolean isLeave = false;
				for(LeaveRegVO leavevo : leaveLst){
					if(isOverlap(startdate,enddate, leavevo.getLeavebegintime(),leavevo.getLeaveendtime())){
						isLeave = true;
						break;
					}
				}
				if(isLeave)
					return false;
			}
		}
		
		return true;
	}
	
	/**
	 * �Զ��Ű�������㼼�ܡ�֤�顢��Ա����,������������
	 * 
	 * @param orderArr
	 * @param planinfo
	 * @param skillVOs
	 * @param certifacateVOs
	 * @param userMap
	 */
	private void afterAutoAssign(JobOrder[] orderArr, PlanInfoVO planinfo,
			List<BDDocVO> skillVOs, List<BDDocVO> certifacateVOs,
			Map<String, UserInfoVO> userMap) {
		Map<String, BDDocVO> skillMap = listToMap(skillVOs);
		Map<String, BDDocVO> certifacateMap = listToMap(certifacateVOs);
		
		List<BDDocVO> vehicleVOs = getBDDocInfo(BlackBoxConstant.DEFDOC_VEHICLE);
		Map<String, BDDocVO> vehicleMap = listToMap(vehicleVOs);

		BDDocVO docvo = null;
		UserInfoVO userInfoVo = null;
		List<JobOrderSkillUsers> skills = null;
		List<JobOrderUsers> orderUsers = null;

		for (JobOrder order : orderArr) {
			skills = order.getJobOrderSkillUsers();
			if (skills == null)
				continue;

			// ��ͬҵ��Ա�Ķ�������ɫ����
			String operator = order.getOperator();
			userInfoVo = userMap.get(operator);
			if (userInfoVo != null) {
				order.setColor(userInfoVo.getColor());
			}

			for (JobOrderSkillUsers skill : skills) {
				// ��ȡ����
				if (skill.getVehicleno() != null){
					docvo = vehicleMap.get(skill.getVehicleno());
					if (docvo != null) {
						skill.setVehicleno(docvo.getName());
					}
				}
				// ���㼼������
				if (skill.getSkillCategoryName() == null) {
					docvo = skillMap.get(skill.getSkillCategoryId());
					if (docvo != null) {
						skill.setSkillCategoryName(docvo.getName());
					}
				}

				// ����֤������
				if (skill.getCertificateName() == null) {
					docvo = certifacateMap.get(skill.getCertificateId());
					if (docvo != null) {
						skill.setCertificateName(docvo.getName());
					}
				}

				orderUsers = skill.getJobOrderUsers();
				if (orderUsers != null && orderUsers.size() > 0) {
					for (JobOrderUsers orderUser : orderUsers) {
						// ���㹤������
						if (orderUser.getAssignedUserName() == null) {
							userInfoVo = userMap.get(orderUser
									.getAssignedUserId());
							if (userInfoVo != null) {
								orderUser.setAssignedUserName(userInfoVo
										.getName());
							}
//							//add chenth 20180412  ��Ա����ְ
//							else if(orderUser.getAssignedUserId() != null){
//								orderUser.setAssignedUserName("Resigned");
//							}
						}

						// ����ǰ��Ҫ��������Ա
						if (UFBoolean.TRUE.equals(new UFBoolean(orderUser
								.getIsLocked())))
							continue;
						orderUser.setIsLocked(planinfo.getDefaultIsLocked());
					}
				}
				
				//add chenth 20180412 ��Ա����������
				Collections.sort(orderUsers);

				// ���ĳ������û�к��ʵ��Ž������򴴽�һ���յ�JobOrderUsers��ozform��Ҫ
				if (skill.getPlanedcount() == 0) {
					JobOrderUsers orderUser = new JobOrderUsers();
					orderUser.setJobId(skill.getJobId());
					boolean isVirtual = true;
					addUser2Skills(skill, orderUser, isVirtual);
				}
			}
		}
	}
	
	
	/**
	 * ��������������ϵ
	 * @param jobOrderMap
	 */
	private void refJobOrder(Map<String, JobOrder> jobOrderMap) {
		JobOrder order = null;
		JobOrder refOrder = null;
		Set<String> refJobOrderids = new HashSet<String>();
		Set<String> jobids = new HashSet<String>();
		
		//ͬһ��Ķ����໥����
		for(String jobid : jobOrderMap.keySet()){
			order = jobOrderMap.get(jobid);
			
			String refjoborderid = order.getRefjoborderID();
			if(refjoborderid == null)
				continue ;
			
			//��������Ķ������ڱ����Ű๤���У����ߺ͹��������Ĺ���ʱ�����ص�����ȡ������
			refOrder = jobOrderMap.get(refjoborderid);
			if(refOrder == null){
				jobids.add(jobid);
				refJobOrderids.add(refjoborderid);
			}else{
				//�����������
				refEachOther(order, refOrder);
				//add 20171123 ͬһ���໥�����Ķ����Ĺ�������ϲ�
				combineOrderSkills(order, refOrder);
			}
		}
		
		//����ͬһ��Ķ����໥����
		if(jobids.size() > 0){
			Object[] refjoborderInfo = getRefJobOrder(refJobOrderids);
			Map<String,JobOrder> otherRefjobOrderMap = (Map<String, JobOrder>) refjoborderInfo[0];
			for(String jobid : jobids){
				order = jobOrderMap.get(jobid);
				refOrder = otherRefjobOrderMap.get(order.getRefjoborderID());
				//�����������
				refEachOther(order, refOrder);
			}
		}
	}
	

	private void refEachOther(JobOrder order, JobOrder refOrder) {
		
		/* del 20171123 ����ͬһ����ȥ�����ļ����ط�������ͬʱȥ��ͬʱ�أ�������ʱ���ص�
		 * //������������Ĺ���ʱ�����ص�����ȡ������
		 * if(isOverlap(order.getTStartDate(), order.getTEndDate(), 
					reforder.getTStartDate(), reforder.getTEndDate())){
			order.setRefjoborderID(null);
			return;
		}*/
		
		List<JobOrder> refjoborders = null;
		refjoborders = order.getRefjoborder();
		if(refjoborders == null){
			refjoborders = new ArrayList<JobOrder>();
			order.setRefjoborder(refjoborders);
		}
		refjoborders.add(refOrder);
		
		refjoborders = refOrder.getRefjoborder();
		if(refjoborders == null){
			refjoborders = new ArrayList<JobOrder>();
			refOrder.setRefjoborder(refjoborders);
		}
		refjoborders.add(order);
	}
	
	

	/**
	 * �ϲ�����joborder�Ĺ�������
	 * 	1���������jobder����ͬһ��skill������������������Ϊ����
	 *  2����������joborder����û�еĹ�������
	 * @param order
	 * @param refOrder
	 */
	private void combineOrderSkills(JobOrder order, JobOrder refOrder) {
		int diffCount = 0;
		boolean isRefOrderHave = false;
		boolean isOrderHave = false;
		JobOrderSkillUsers skill = null;
		List<JobOrderSkillUsers> orderSkills = order.getJobOrderSkillUsers();
		List<JobOrderSkillUsers> refOrderSkills = refOrder.getJobOrderSkillUsers();
		for(JobOrderSkillUsers orderSkill : orderSkills){
			isRefOrderHave = false;
			for(JobOrderSkillUsers refOrderSkill : refOrderSkills){
				if(orderSkill.getSkillCategoryId().equals(refOrderSkill.getSkillCategoryId())){
					if(orderSkill.getCount() > refOrderSkill.getCount()){
						refOrderSkill.setCount(orderSkill.getCount());
						diffCount = orderSkill.getCount() - refOrderSkill.getCount();
						refOrderSkill.setPlanedcount(refOrderSkill.getPlanedcount() + diffCount);
					}else{
						orderSkill.setCount(refOrderSkill.getCount());
						diffCount = refOrderSkill.getCount() - orderSkill.getCount();
						orderSkill.setPlanedcount(orderSkill.getPlanedcount() + diffCount);
						
					}
					isRefOrderHave = true;
				}
			}
			if(false == isRefOrderHave){
				skill = (JobOrderSkillUsers) orderSkill.clone(refOrder.getJobId());
				updateJobSkill(refOrder, skill);
			}
		}
		for(JobOrderSkillUsers refOrderSkill : refOrderSkills){
			isOrderHave = false;
			for(JobOrderSkillUsers orderSkill : orderSkills){
				if(orderSkill.getSkillCategoryId().equals(refOrderSkill.getSkillCategoryId())){
					isOrderHave = true;
				}
			}
			if(false == isOrderHave){
				skill = (JobOrderSkillUsers) refOrderSkill.clone(order.getJobId());
				updateJobSkill(order, skill);
			}
		}
	}


	/**
	 * �ж������ڼ��Ƿ��ص�
	 * @param startdate1
	 * @param enddate1
	 * @param startdate2
	 * @param enddate2
	 * @return
	 */
	private boolean isOverlap(UFDateTime startdate1, UFDateTime enddate1,
			UFDateTime startdate2, UFDateTime enddate2) {
		if(startdate1 == null || startdate2 == null)
			return false;
	/*	return (startdate1.equals(startdate2) && enddate1.equals(enddate2))
				|| (startdate1.equals(startdate2) && enddate1.after(enddate2))
				|| (startdate1.before(startdate2) && enddate1.equals(enddate2))
				|| (startdate1.after(startdate2) && startdate1.before(enddate2))
				  || (enddate1.after(startdate2) && enddate1.before(enddate2));*/
		return ((startdate1.compareTo(startdate2) >= 0 && startdate1.before(enddate2))
				||(enddate1.after(startdate2) && enddate1.compareTo(enddate2) <= 0 ));
	}

	/**
	 * ���ѷ����û��Ĺ���ʱ��ϲ����û���Ϣ��
	 * @param userMap
	 * @param assignedUserInfoMap
	 */
	private void combineUserInfo(Map<String, UserInfoVO> userMap,
			Map<String, UserInfoVO> assignedUserInfoMap) {
		if(assignedUserInfoMap == null)
			return;
		UserInfoVO user = null;
		for(String key : assignedUserInfoMap.keySet()){
			user = userMap.get(key);
			if(user != null){
				user.setDstartdate(assignedUserInfoMap.get(key).getDstartdate());
				user.setDenddate(assignedUserInfoMap.get(key).getDenddate());
			}
		}
		
	}

	private Object[] getCertificateUserInfo(String date) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select pk_psndoc as user_id, ").append(BlackBoxConstant.FLD_PSNDOCSCERT_CERT).append(" as certificate_id from ").append(BlackBoxConstant.TABLE_PSNDOC_CERTIFICATE).append(" s ");
		sb.append(" where isnull(dr,0) = 0 ");
		sb.append(" and exists( select 1 from hi_psnjob j where s.pk_psndoc = j.pk_psndoc and j.dr=0 and j.poststat = 'Y') ");
		//����Ч���ڵ�
		sb.append(" and (").append(BlackBoxConstant.FLD_PSNDOCSCERT_EXPIREDATE).append(" is null or substring(")
		.append(BlackBoxConstant.FLD_PSNDOCSCERT_EXPIREDATE).append(",0,11) >= ? ) ");
		/*
		sb.append(" and s.pk_psndoc not in( select o.assigned_user_id from oz_joborder_user o inner join so_saleorder so on o.job_id = so.csaleorderid ");
		//���챻������Ա�������Űֻࣨ�������Ҫ��Ҫ�����Űࣿ��
		sb.append("  where  (substring(so.startdate,0,11) = ? and o.is_locked = 'Y') ");
		//�治���ں���ĵ������ˣ��Ȳ�����
		///������ǰ�źõ���Ա�������Űࣨ��û�е������������Ҫ���������Ű�ģ���ʱ������
		sb.append(" or (substring(so.startdate,0,11) < ? and substring(so.enddate,0,11) >= ?)) ");
		*/
		
		BaseDAO dao = new BaseDAO();
		SQLParameter parameter = new SQLParameter();
		parameter.addParam(date); 
//		parameter.addParam(date);
//		parameter.addParam(date);
		Object[] skillUserMaps = null;
		try {
			skillUserMaps = (Object[]) dao.executeQuery(sb.toString(), parameter, new UserRSProcessor());
		} catch (DAOException e) {
			Logger.error("BlackBoxImpl.getCertificateUserInfo() faild:" + e.getMessage(), e);
			ExceptionUtils.wrappException(e);
		}
		return skillUserMaps;
	}
	
	//TODO �ĳ��Ȼ�ȡ������Ա��Ϣ���ٸ�����Ա��Ϣ��ȡ��Ӧ��skill��certificate
	private Object[] getSkillUserInfo(String date) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select pk_psndoc as user_id, ")
		.append(BlackBoxConstant.FLD_PSNDOCSKILL_SKILL).append(" as skill_category_id from ")
		.append(BlackBoxConstant.TABLE_PSNDOC_SKILL).append(" s ");
		sb.append(" where isnull(dr,0) = 0 ");
		sb.append(" and exists( select j.pk_psndoc from hi_psnjob j where s.pk_psndoc = j.pk_psndoc and j.dr=0 and j.poststat = 'Y') ");
		/*
		sb.append(" and s.pk_psndoc not in( select o.assigned_user_id from oz_joborder_user o inner join so_saleorder so on o.job_id = so.csaleorderid ");
		//���챻������Ա�������Űֻࣨ�������Ҫ��Ҫ�����Űࣿ��
		sb.append("  where  (substring(so.startdate,0,11) = ? and o.is_locked = 'Y') ");
		//�治���ں���ĵ������ˣ��Ȳ�����
		///������ǰ�źõ���Ա�������Űࣨ��û�е������������Ҫ���������Ű�ģ���ʱ������
		sb.append(" or (substring(so.startdate,0,11) < ? and substring(so.enddate,0,11) >= ?)) ");
		*/
		
		BaseDAO dao = new BaseDAO();
//		SQLParameter parameter = new SQLParameter();
//		parameter.addParam(date);
//		parameter.addParam(date);
//		parameter.addParam(date);
		Object[] skillUserMaps = null;
		try {
			skillUserMaps = (Object[]) dao.executeQuery(sb.toString(), new UserRSProcessor());
		} catch (DAOException e) {
			Logger.error("BlackBoxImpl.getSkillUserInfo() faild:" + e.getMessage(), e);
			ExceptionUtils.wrappException(e);
		}
		return skillUserMaps;
	}
	
	private List<BDDocVO> getBDDocInfo(String docType) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select a.pk_defdoc as id, a.code as code, a.name as name ");
		sb.append(" from bd_defdoc a inner join  bd_defdoclist b on a.pk_defdoclist = b.pk_defdoclist  ");
		sb.append(" where b.code= ? order by code ");
		
		BaseDAO dao = new BaseDAO();
		SQLParameter parameter = new SQLParameter();
		parameter.addParam(docType);
		List<BDDocVO> docVOs = null;
		try {
			docVOs = (List<BDDocVO>) dao.executeQuery(sb.toString(), parameter, new BDDocRSProcessor());
		} catch (DAOException e) {
			Logger.error("BlackBoxImpl.getBDDocInfo() faild:" + e.getMessage(), e);
			ExceptionUtils.wrappException(e);
		}
		return docVOs;
	}

	private Map<String, UserInfoVO> getUserInfo(
			String date)  {
		StringBuffer sb = new StringBuffer();
		sb.append(" select pk_psndoc, code, name, colorhex as color from bd_psndoc  ");
		sb.append(" left join ( ");
		sb.append(" select a.pk_defdoc as id, a.code as colorhex from bd_defdoc a ");
		sb.append(" inner join  bd_defdoclist b on a.pk_defdoclist = b.pk_defdoclist where b.code= '")
		.append(BlackBoxConstant.DEFDOC_COLOR).append("' ");
		sb.append(" ) defdoc on ").append(BlackBoxConstant.FLD_PSNDOC_COLOR).append(" = defdoc.id ");
		sb.append(" where pk_psndoc in( select pk_psndoc from hi_psnjob j where j.dr=0 and j.poststat = 'Y') ");
		
		BaseDAO dao = new BaseDAO();
		Map<String, UserInfoVO> userMap = null;
		try {
			userMap = (Map<String, UserInfoVO>) dao.executeQuery(sb.toString(), new UserInfoRSProcessor());
		} catch (DAOException e) {
			Logger.error("BlackBoxImpl.getUserInfo() faild:" + e.getMessage(), e);
			ExceptionUtils.wrappException(e);
		}
		
		return userMap;
	}
	
	class UserInfoRSProcessor implements ResultSetProcessor{
		public UserInfoRSProcessor(){
		}

		@Override
		public Object handleResultSet(ResultSet rs) throws SQLException {
			if (rs == null)
				return null;
			Map<String, UserInfoVO> userMap = new HashMap<String, UserInfoVO>();
			while (rs.next()) {
				String pk_psndoc = rs.getString("pk_psndoc");
				UserInfoVO userVO = new UserInfoVO();
				userVO.setPk_psndoc(pk_psndoc);
				userVO.setCode(rs.getString("code"));
				userVO.setName(rs.getString("name"));
				userVO.setColor(rs.getString("color"));
					
				userMap.put(pk_psndoc, userVO);
				
			}
			return userMap;
		}
	}
	
	/**
	 * ��ȡ�ͻ�ƫ����Ϣ�Լ�ƫ�ó̶�
	 * @param orders
	 * @return
	 * @throws BusinessException
	 */
	private Object[] getCustomerPreferInfo(
			Collection<JobOrder> orders) {
		Set<String> customerIds = new HashSet<String>();
		for(JobOrder order : orders){
			//update chenth 20180409 ����tolocation
//			customerIds.add(order.getCustomerAddressID());
			customerIds.add(order.getTolocationid());
		}
		String sqlin = InSqlManager.getInSQLValue(customerIds);
		
		StringBuffer sb = new StringBuffer();
		sb.append(" select a.def1 as pk_customer, a.def2 as pk_psndoc, a.def3 as degree ");
		sb.append(" from bd_defdoc a inner join  bd_defdoclist b on a.pk_defdoclist = b.pk_defdoclist  ");
		sb.append(" where b.code= '").append(BlackBoxConstant.DEFDOC_CUSTOMERPREFER).append("' ");
		sb.append(" and a.def1 in ").append(sqlin);
		
		BaseDAO dao = new BaseDAO();
		Object[] returnObj = null;
		try {
			returnObj = (Object[]) dao.executeQuery(sb.toString(), new CustomerPreferRSProcessor());
		} catch (DAOException e) {
			Logger.error("BlackBoxImpl.getCustomerPreferInfo() faild:" + e.getMessage(), e);
			ExceptionUtils.wrappException(e);
		}
		return returnObj;
	}
	

	private Map<String, List<LeaveRegVO>> getLeaveInfo(
			Collection<JobOrder> orders) throws BusinessException {
		//��ȡ����Ҫ���������ж�������С��ʼʱ���������ʱ��
		UFDateTime minStartdate = null;
		UFDateTime maxEnddate = null;
		for(JobOrder order : orders){
			if(minStartdate == null
					|| minStartdate.after(order.getTStartDate())){
				minStartdate = order.getTStartDate();
			}
			
			if(maxEnddate == null
					|| maxEnddate.before(order.getTEndDate())){
				maxEnddate = order.getTEndDate();
			}
		}
		return getLeaveInfo(minStartdate, maxEnddate);
	}

	private Map<String, List<LeaveRegVO>> getLeaveInfo(UFDateTime minStartdate,
			UFDateTime maxEnddate) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select pk_psndoc, leavebegintime, leaveendtime ");
		sb.append(" from ( ");
		sb.append("   select pk_psndoc, leavebegintime, leaveendtime from tbm_leavereg ");
		sb.append("   union ");
		sb.append("   select assigned_user_id as pk_psndoc, substring(not_comming_informed_date,0,11) + ' 00:00:00' as leavebegintime, substring(not_comming_informed_date,0,11) + ' 23:59:59' as leaveendtime ");
		sb.append("   from oz_joborder_user where job_user_status_id = 'NC' ");
		sb.append("   union ");
		sb.append("   select surveyor_id as user_id, substring(appointment_date,0,11) + ' 00:00:00' as leavebegintime, substring(appointment_date,0,11) + ' 23:59:59' as leaveendtime ");
		sb.append("   from oz_joborder_survey ");
		sb.append(" ) a ");
		sb.append(" where ((leavebegintime >= ? and leavebegintime < ?) or (leaveendtime > ? and leaveendtime <= ?))  ");
		//(leavebegintime > minbegindate and leavebegintime < maxenddate) or (leaveendtime > minbegindate and leaveendtime < maxenddate)
		BaseDAO dao = new BaseDAO();
		SQLParameter parameter = new SQLParameter();
		parameter.addParam(minStartdate);
		parameter.addParam(maxEnddate);
		parameter.addParam(minStartdate);
		parameter.addParam(maxEnddate);
		
		Map<String, List<LeaveRegVO>> leaveMap = null;
		try {
			leaveMap = (Map<String, List<LeaveRegVO>>) dao.executeQuery(sb.toString(), parameter, new LeaveInfoRSProcessor());
		} catch (DAOException e) {
			Logger.error("BlackBoxImpl.getLeaveInfo() faild:" + e.getMessage(), e);
			ExceptionUtils.wrappException(e);
		}
		
		return leaveMap;
	}
	
	class LeaveInfoRSProcessor implements ResultSetProcessor{
		public LeaveInfoRSProcessor(){
		}

		@Override
		public Object handleResultSet(ResultSet rs) throws SQLException {
			if (rs == null)
				return null;
			
			Map<String, List<LeaveRegVO>> leaveMap = new HashMap<String, List<LeaveRegVO>>();
			String pk_psndoc = null;
			List<LeaveRegVO> leaveList = null;
			while (rs.next()) {
				pk_psndoc = rs.getString("pk_psndoc");
				LeaveRegVO leaveInfoVO = new LeaveRegVO();
				leaveInfoVO.setPk_psndoc(pk_psndoc);
				leaveInfoVO.setLeavebegintime(new UFDateTime(rs.getString("leavebegintime")));
				leaveInfoVO.setLeaveendtime(new UFDateTime(rs.getString("leaveendtime")));
					
				leaveList = leaveMap.get(pk_psndoc);
				if(leaveList == null ){
					leaveList = new ArrayList<LeaveRegVO>();
					leaveMap.put(pk_psndoc, leaveList);
				}
				leaveList.add(leaveInfoVO);
			}
			return leaveMap;
		}
	}

	/**
	 * ��ȡjoborder
	 * @param planinfo
	 * @return  joborder �� ������Ա��Ϣ
	 * @throws BusinessException 
	 */
	private Object[] getJobOrderInfo(PlanInfoVO planinfo) {
		String date = planinfo.getPlandate();
		JobOrder newOrder = planinfo.getNewOrder();
		String userID = planinfo.getUserid();
		String[] job_user_status_id = planinfo.getJob_user_status_id();
		
		StringBuffer sb = getJobOrderSql();
		
		BaseDAO dao = new BaseDAO();
		SQLParameter parameter = new SQLParameter();
		//update chenth 20180418 ����������ڣ����ѯ����
		if(date != null){
			sb.append(" and substring(job.departuretime,0,11) <= ? and substring(job.jobendtime,0,11) >= ? ");
			sb.append(" and blackbox.dr=0 ");
			parameter.addParam(date);
			parameter.addParam(date);
		}
		
		if(newOrder != null){
			sb.append(" and job.billid != ? ");
			parameter.addParam(newOrder.getJobId());
		}
		
		//add chenth 20180418 ����Ա����ѯ����
		if(userID != null){
			sb.append(" and sou.job_user_status_id != 'TS' and sou.assigned_user_id = ? ");
			parameter.addParam(userID);
		}
		if(job_user_status_id != null){
			String sqlin = InSqlManager.getInSQLValue(job_user_status_id);
			sb.append(" and sou.job_user_status_id in ").append(sqlin);
		}
		//add end
		
		Object[] orders = null;
		try {
			orders = (Object[]) dao.executeQuery(sb.toString(), parameter, new JobOrderRSProcessor(false, date));
		} catch (DAOException e) {
			Logger.error("BlackBoxImpl.getJobOrder() faild:" + e.getMessage(), e);
			ExceptionUtils.wrappException(e);
		}
		return orders;
	}
	
	private Object[] getRefJobOrder(Set<String> refJobids) {
		String sqlin = InSqlManager.getInSQLValue(refJobids);
		StringBuffer sb = getJobOrderSql();
		sb.append(" and job.billid in ").append(sqlin);
		
		BaseDAO dao = new BaseDAO();
		Object[] orders = null;
		try {
			orders = (Object[]) dao.executeQuery(sb.toString(), null, new JobOrderRSProcessor(true, null));
		} catch (DAOException e) {
			Logger.error("BlackBoxImpl.getJobOrder() faild:" + e.getMessage(), e);
			ExceptionUtils.wrappException(e);
		}
		return orders;
	}
	
	private StringBuffer getJobOrderSql() {
		StringBuffer sb = new StringBuffer();
		sb.append(" select distinct job.billid as jobid, som.vbillcode as sono, job.subject as subject");
		sb.append(" , som.ccustomerid as customerid, job.departuretime as startdate, job.jobendtime as enddate ");
		sb.append(" ,blackbox.billid_b as sobid, blackbox.skill as skillcategoryid, blackbox.certificate as certificateid, blackbox.numberofpeople as count  ");
		sb.append(" ,som.cemployeeid as operator, job.def16 as refjoborderid, job.def15 as combinejoborderid, job.def20 as jobcertificateid ");
		sb.append(" ,sou.assigned_user_id as assigneduserid, sou.is_locked as islocked, sou.job_user_status_id as jobuserstatusid ");
		sb.append(" ,cus.name as customername, job.def3 as contactphoneno, job.contactperson as contactperson  ");
		sb.append(" ,blackbox.def1 as vehicleno, som.chreceiveaddid as customeraddressid, cusaddr.detailinfo as customeraddress, cusaddr.postcode as customerpostcode ");
		sb.append(" ,fc.name as fromlocation, job.fromaddress+ ' ' +job.fromcode as fromaddress, job.fromcorp as fromlocationid");
		sb.append(" ,job.contactperson as fromcontact, job.def3 as fromcontactphone ");
		sb.append(" ,tc.name as tolocation, job.toaddress+ ' ' +job.tocode as toaddress, job.tocorp as tolocationid ");
		sb.append(" ,job.def4 as tocontact, job.def5 as tocontactphone ");
		//update chenth 20180409
		sb.append(" ,concat('S/O NO.: ', som.vbillcode, memo.description)  as requirement ");
//		sb.append(" ,concat('S/O NO.: ',job.vbillno ");
//		sb.append(" , ' <br/>Make:', job.machinemake, ' Model:', job.machinemodel, ' Submodel:', job.machinesubmodel ");
//		sb.append(" , ' <br/>Total # of Pkgs:', cast(job.totalofpkgs as int) ");
//		sb.append(" , ' <br/>Longest (CM):', cast(job.lengthcm as int), ' Crate #:', cast(job.crate1 as int) ");
//		sb.append(" , ' <br/>Widest (CM):', cast(job.widthcm as int), ' Crate #:', cast(job.crate2 as int) ");
//		sb.append(" , ' <br/>Highest (CM):', cast(job.heightcm as int), ' Crate #:', cast(job.crate3 as int) ");
//		sb.append(" , ' <br/>Heaviest (KG):', cast(job.largestweight as int), ' Crate #:', cast(job.kcrate as int) ");
//		sb.append(" ) as requirement ");
		sb.append(" ,sou.id as id ");
		sb.append(" from bhs_somove_h job ");
		sb.append(" inner join so_saleorder som on job.csaleorderid = som.csaleorderid ");
		sb.append(" left join bhs_somove_box blackbox on job.billid = blackbox.billid  ");
		sb.append(" left join ( ");
		sb.append("   select distinct ou.id, ou.job_id, ou.assigned_user_id, ou.skill_category_id,ou.is_locked, ou.job_user_status_id, ce.").append(BlackBoxConstant.FLD_PSNDOCSCERT_CERT)
		.append(" as certificate_id ");
		sb.append("   from oz_joborder_user ou inner join ").append(BlackBoxConstant.TABLE_PSNDOC_CERTIFICATE).append(" ce on ou.assigned_user_id = ce.pk_psndoc ");
		sb.append(" ) sou on (job.billid = sou.job_id and sou.skill_category_id = blackbox.skill and (sou.certificate_id = blackbox.certificate or blackbox.certificate = '~') ) ");
		sb.append(" left join bd_addressdoc fc on job.fromcorp = fc.pk_addressdoc ");
		sb.append(" left join bd_addressdoc tc on job.tocorp = tc.pk_addressdoc ");
		sb.append(" left join bd_customer cus on som.ccustomerid = cus.pk_customer ");
		sb.append(" left join bd_address cusaddr on som.chreceiveaddid = cusaddr.pk_address ");
		sb.append(" left join v_bhs_move_memo memo on job.billid = memo.billid ");
		sb.append(" where job.dr=0 ");
		//delete chenth 20180418 ��ѯ����joborderʱ�����Բ������Ƿ��й������� 
//		sb.append(" and blackbox.dr=0 ");
		//update chenth 20180418 combine��joborder���Բ���ʱ��
		sb.append(" and ((job.def15 is not null and job.def15 != '~' ) or (job.departuretime is not null and job.jobendtime is not null)) ");
		return sb;
	}
	
	/**
	 * ά��skill-userMap
	 * @param order
	 * @param sameSkillKey
	 * @param orderUser
	 */
	private void updateJobSkillUsersMap(JobOrder order,
			String sameSkillKey, JobOrderUsers orderUser) {
		List<JobOrderUsers> jobOrderSkillUsers = order.getJobSkillUsersMap().get(sameSkillKey);
		if(jobOrderSkillUsers == null){
			jobOrderSkillUsers = new ArrayList<JobOrderUsers>();
			order.getJobSkillUsersMap().put(sameSkillKey, jobOrderSkillUsers);
		}
		jobOrderSkillUsers.add(orderUser);
	}

	class JobOrderRSProcessor implements ResultSetProcessor{
		private UFDate plandate;
		private boolean isQueryRef = false;
		public JobOrderRSProcessor(boolean isQueryRef, String date){
			if(date != null){
				this.plandate = new UFDate(date);
			}
			this.isQueryRef = isQueryRef;
		}

		@Override
		public Object handleResultSet(ResultSet rs) throws SQLException {
			if (rs == null)
				return null;
			Map<String,JobOrder> jobOrderMap = new HashMap<String, JobOrder>();
			Map<String,JobOrderSkillUsers> jobOrderSkillMap = new HashMap<String, JobOrderSkillUsers>();
			
			//�ѷ����û���Ϣ����ʼ������ʱ�䣩
			Map<String, UserInfoVO> assignedUserInfoMap = new HashMap<String, UserInfoVO>();
			String jobid = null;
			String skillcategoryid = null;
			String assigneduserid = null;
			String jobskillKey = null;
			String certificateid = null;
			String sameSkillKey = null;
			while (rs.next()) {
				jobid = rs.getString("jobid");
				
				JobOrder order = jobOrderMap.get(jobid);
				if(order == null){
					order = createJobOrderByRS(rs);
					jobOrderMap.put(jobid, order);
				}

				skillcategoryid = rs.getString("skillcategoryid");
				certificateid = getCertificateID(rs);
				if(skillcategoryid != null){
					sameSkillKey = jobid + skillcategoryid;
					
					jobskillKey = jobid + skillcategoryid + certificateid;
					JobOrderSkillUsers orderSkill = jobOrderSkillMap.get(jobskillKey);
					if(orderSkill == null){
						orderSkill = createJobOrderSkillUsersByRS(rs);
						jobOrderSkillMap.put(jobskillKey, orderSkill);
						
						//����order�е�jobSkillMap
						updateJobSkill(order, orderSkill);
					}
					

					assigneduserid = rs.getString("assigneduserid");
					if(assigneduserid != null){
						JobOrderUsers orderUser = createJobOrderUsersByRS(rs);
						//�ж����Ű���Ա�Ƿ������ˣ������������������
						if(isUserLocked(orderUser, order)){ 
							
							addUser2Skills(orderSkill,orderUser, false);
							
							//����order�е�jobSkillUsersMap
							updateJobSkillUsersMap(order, sameSkillKey, orderUser);
							
							//��¼�����ѷ����û�����ʱ����Ϣ����ʼ������ʱ�䣩
							updateAssignedUserInfoMap(order, assigneduserid, assignedUserInfoMap);
							
						}
					}
				}
				
			}
			
			
			Object[] returnObj = new Object[2];
			returnObj[0] = jobOrderMap;
			returnObj[1] = assignedUserInfoMap.size() > 0 ? assignedUserInfoMap : null;
			return returnObj;
		}
		
		/**
		 * ��¼�����ѷ����û��Ĺ���ʱ����Ϣ����ʼ������ʱ�䣩
		 * @param order
		 * @param assigneduserid
		 * @param assignedUserInfoMap
		 */
		private void updateAssignedUserInfoMap(JobOrder order,
				String assigneduserid,
				Map<String, UserInfoVO> assignedUserInfoMap) {
			UserInfoVO user = assignedUserInfoMap.get(assigneduserid);
			if(user == null){
				user = new UserInfoVO();
				user.setPk_psndoc(assigneduserid);
				user.setDstartdate(order.getTStartDate());
				user.setDenddate(order.getTEndDate());
				assignedUserInfoMap.put(assigneduserid, user);
			}else{
				if(order.getTStartDate().before(user.getDstartdate())){
					user.setDstartdate(order.getTStartDate());
				}
				if(order.getTEndDate().after(user.getDenddate())){
					user.setDenddate(order.getTEndDate());
				}
			}
			
		}

		private String getCertificateID(ResultSet rs) throws SQLException {
			String certificateid = null2String(rs.getString("certificateid"));
			String jobcertificateid = null2String(rs.getString("jobcertificateid"));
			if(certificateid == null){
				certificateid = jobcertificateid;
			}
			return certificateid;
		}

		/**
		 * �ж����Ű���Ա�Ƿ������ˣ������������������
		 * @param orderUser
		 * @param order
		 * @return
		 */
		private boolean isUserLocked(JobOrderUsers orderUser, JobOrder order) {
			String userStatus = orderUser.getJobUserStatusId();
			if(isQueryRef){//��ѯ����������������Ա�����أ�����no coming����
				if(userStatus == null 
						|| "NC".equals(userStatus)){
					return false;
				}else{
					return true;
				}
			}else{
				//1���ж���Ա��OZFORM�Ƿ�������
				if(UFBoolean.TRUE.equals(new UFBoolean(orderUser.getIsLocked())))
					return true;
				
				//2���ж�joboder��ʼ�����Ƿ��Ű����ڵ���
				//���joboder��ʼ���ں��Ű����ڲ���ͬһ��Ļ��������Ա��������������
				if(plandate == null 
						|| !plandate.isSameDate(order.getTStartDate().getDate()))
					return true;
				
				//����Ѿ��Ű���û�״̬�����ڡ�AS����Assigned�� ����TS����Temporary Save����"NC" (Not Coming) ������
				//�����Ѿ�����Ϣ���û����Ѿ��������깤�ģ�����������
				if(userStatus == null 
						|| "AS".equals(userStatus) 
						|| "TS".equals(userStatus)
						|| "NC".equals(userStatus)){
					return false;
				}else{
					return true;
				}
			}
			
		}

		private JobOrder createJobOrderByRS(ResultSet rs) throws SQLException{
			JobOrder order = new JobOrder();
			order.setJobId(rs.getString("jobid"));
			order.setSoNo(rs.getString("sono"));
			order.setSubject(null2String(rs.getString("subject")));
			order.setRequirement(null2String(rs.getString("requirement")));
			order.setCustomerId(rs.getString("customerid"));
			order.setCustomerName(rs.getString("customername"));
			order.setContactPerson(null2String(rs.getString("contactperson")));
			order.setContactPhoneNo(null2String(rs.getString("contactphoneno")));
			order.setTStartDate(rs.getString("startdate") == null ? null : new UFDateTime(rs.getString("startdate")));
			order.setTEndDate(rs.getString("enddate") == null ? null : new UFDateTime(rs.getString("enddate")));
			order.setStartDate(rs.getString("startdate") == null ? 0 : order.getTStartDate().getMillis() );
			order.setEndDate(rs.getString("enddate") == null ? 0 : order.getTEndDate().getMillis());
			order.setOperator(null2String(rs.getString("operator")));
			order.setRefjoborderID(null2String(rs.getString("refjoborderid")));
			order.setCombinejoborderID(null2String(rs.getString("combinejoborderid")));
			order.setCustomerAddressID(null2String(rs.getString("customeraddressid")));
			order.setCustomerPostCode(null2String(rs.getString("customerpostcode")));
			order.setCustomerAddress(null2String(rs.getString("customeraddress")) + order.getCustomerPostCode());
			order.setFromLocation(null2String(rs.getString("fromlocation")));
			order.setFromAddress(null2String(rs.getString("fromaddress")));
			order.setFromContact(null2String(rs.getString("fromcontact")));
			order.setFromContactphone(null2String(rs.getString("fromcontactphone")));
			order.setToLocation(null2String(rs.getString("tolocation")));
			order.setToAddress(null2String(rs.getString("toaddress")));
			order.setToContact(null2String(rs.getString("tocontact")));
			order.setToContactphone(null2String(rs.getString("tocontactphone")));
			order.setFromlocationid(null2String(rs.getString("fromlocationid")));
			order.setTolocationid(null2String(rs.getString("tolocationid")));

			//add chenth 20180501
			order.setOz_job_user_status_id(rs.getString("jobuserstatusid"));
			order.setOz_job_user_id(rs.getString("id"));
			//add end
			return order;
		}
		
		private JobOrderSkillUsers createJobOrderSkillUsersByRS(ResultSet rs) throws SQLException{
			JobOrderSkillUsers orderSkillUser = new JobOrderSkillUsers();
			orderSkillUser.setJobId(rs.getString("jobid"));
			orderSkillUser.setSkillCategoryId(rs.getString("skillcategoryid"));
			orderSkillUser.setCertificateId(getCertificateID(rs));
			orderSkillUser.setCount(rs.getInt("count"));
			orderSkillUser.setVehicleno(null2String(rs.getString("vehicleno")));
			orderSkillUser.setPlanedcount(0);
			return orderSkillUser;
		}
		

		private JobOrderUsers createJobOrderUsersByRS(ResultSet rs) throws SQLException{
			JobOrderUsers orderUser = new JobOrderUsers();
			orderUser.setJobId(rs.getString("jobid"));
			orderUser.setSkillCategoryId(rs.getString("skillcategoryid"));
			orderUser.setAssignedUserId(rs.getString("assigneduserid"));
			orderUser.setIsLocked(rs.getString("islocked"));
			orderUser.setJobUserStatusId(rs.getString("jobuserstatusid"));
			//add chenth 20180501
			orderUser.setId(rs.getString("id"));
			//add end
			return orderUser;
		}
		
	}
	
	class UserRSProcessor implements ResultSetProcessor{
		public UserRSProcessor(){
		}

		@Override
		public Object handleResultSet(ResultSet rs) throws SQLException {
			if (rs == null)
				return null;
			Map<String,Set<String>> map1 = new HashMap<String,Set<String>>();
			Map<String,Set<String>> map2 = new HashMap<String,Set<String>>();
			String key1 = null;
			String key2 = null;
			Set<String> valueSet = null;
			while (rs.next()) {
				key1 = rs.getString(1);
				key2 = rs.getString(2);
				valueSet = map1.get(key1);
				if(valueSet == null){
					valueSet = new HashSet<String>();
					map1.put(key1, valueSet);
				}
				valueSet.add(key2);
				
				valueSet = map2.get(key2);
				if(valueSet == null){
					valueSet = new HashSet<String>();
					map2.put(key2, valueSet);
				}
				valueSet.add(key1);
			}
			Object[] maps = new Object[2];
			maps[0] = map1;
			maps[1] = map2;
			return maps;
		}
	}
	
	class CustomerPreferRSProcessor implements ResultSetProcessor{
		public CustomerPreferRSProcessor(){
		}

		@Override
		public Object handleResultSet(ResultSet rs) throws SQLException {
			if (rs == null)
				return null;
			
			Map<String, Map<Integer,Set<String>>> customerMap = new HashMap<String, Map<Integer, Set<String>>>();
			Map<Integer,Set<String>> customerPreferMap = null;
			Set<String> customerPreferSet = null;
			String pk_customer = null;
			String pk_psndoc = null;
			Integer degree = null;
			Set<Integer> degreeSet = new HashSet<Integer>();
			while (rs.next()) {
				pk_customer = rs.getString("pk_customer");
				pk_psndoc = rs.getString("pk_psndoc");
				degree = rs.getInt("degree");
				
				customerPreferMap = customerMap.get(pk_customer);
				if(customerPreferMap == null ){
					customerPreferMap = new HashMap<Integer,Set<String>>();
					customerMap.put(pk_customer, customerPreferMap);
				}
				customerPreferSet = customerPreferMap.get(degree);
				if(customerPreferSet == null){
					customerPreferSet = new HashSet<String>();
					customerPreferMap.put(degree, customerPreferSet);
				}
				customerPreferSet.add(pk_psndoc);
				
				degreeSet.add(degree);
			}

			Object[] returnObj = new Object[2];
			returnObj[0] = customerMap;
			returnObj[1] = degreeSet;
			
			return returnObj;
		}
	}
	
	class BDDocRSProcessor implements ResultSetProcessor{
		public BDDocRSProcessor(){
		}

		@Override
		public Object handleResultSet(ResultSet rs) throws SQLException {
			if (rs == null)
				return null;
			List<BDDocVO> docVos = new ArrayList();
			while (rs.next()) {
				BDDocVO docVO = new BDDocVO();
				docVO.setId(rs.getString("id"));
				docVO.setCode(rs.getString("code"));
				docVO.setName(rs.getString("name"));
				docVos.add(docVO);
			}
			return docVos;
		}
	}
	
	/**
	 * skill-certificateMap  1��skill������Ҫ��ͬ�����֤��
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
	
	private Map<String, BDDocVO> listToMap(List<BDDocVO> voList){
		Map<String, BDDocVO> voMap = new HashMap<String, BDDocVO>();
		if(voList == null)
			return voMap;
		for(BDDocVO vo : voList){
			voMap.put(vo.getId(), vo);
		}
		return voMap;
	}
	
	private String null2String(Object obj){
		if(obj == null
				|| obj.toString().equals("~")){
			return null;
		}
		return obj.toString();
			
	}


	@Override
	public JobOrder[] getJobOrder(PlanInfoVO planinfo) throws BusinessException {
		if(planinfo == null)
			planinfo = new PlanInfoVO();
		
		Object[] joborderInfo = getJobOrderInfo(planinfo);
		if(joborderInfo == null){
			return null;
		}
		//1.�������ڲ�ѯ���۶�����Ϣ
		Map<String,JobOrder> jobOrderMap = (Map<String, JobOrder>) joborderInfo[0];
		//�����ϲ�
		combineJobOrder(jobOrderMap);
		
		Collection<JobOrder> orders = jobOrderMap.values();
		JobOrder[] orderArr = orders.toArray(new JobOrder[orders.size()]);
		
		//��ѯ������ְ��Ա��Ϣkey:pk_psndoc
		Map<String, UserInfoVO> userMap = getUserInfo(null);
		UserInfoVO userInfoVo = null;
		for(JobOrder order : orderArr){
			order.setJobOrderSkillUsers(null);
			// ��ͬҵ��Ա�Ķ�������ɫ����
			String operator = order.getOperator();
			userInfoVo = userMap.get(operator);
			if (userInfoVo != null) {
				order.setColor(userInfoVo.getColor());
			}
		}
		return orderArr;
	}

}
