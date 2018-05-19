package nc.vo.bhs.blackbox;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.vo.ta.leave.LeaveRegVO;

public class BlackBoxData {
	/**
	 * JobOder
	 */
	Collection<JobOrder> orders;
	/**
	 * ��ѡ��Ա��Ϣ
	 */
	Map<String, UserInfoVO> userMap;
	/**
	 * ��Ա�����Ϣ
	 */
	Map<String, List<LeaveRegVO>> leaveMap;
			
	/**
	 * �û�������Ϣ key:pk_user, value: pk_skills		
	 */
	Map<String, Set<String>> userKeySkillMap;
	/**
	 * �����û���Ϣ key:pk_skill, value: pk_users		
	 */
	Map<String, Set<String>> skillKeyUserMap;
		
	/**
	 * �û�֤����Ϣ key:pk_user, value: pk_certificates		
	 */
	Map<String, Set<String>> certificateKeyUserMap;
	/**
	 * ֤���û���Ϣ key:pk_certificate, value: pk_users		
	 */
	Map<String, Set<String>> userKeyCertificateMap;

	/**
	 * ������Ϣ
	 */
	List<BDDocVO> skillVOs;
	/**
	 * ֤����Ϣ
	 */
	List<BDDocVO> certifacateVOs;
			
	/**
	 * �ͻ�ƫ����Ϣkey:pk_customer:degree:Set<pk_psndoc>
	 */
	Map<String, Map<Integer,Set<String>>> customerMap = null;
	/**
	 * ƫ�ó̶�
	 */
	Integer[] degreeArr = null;
	public Collection<JobOrder> getOrders() {
		return orders;
	}
	public void setOrders(Collection<JobOrder> orders) {
		this.orders = orders;
	}
	public Map<String, UserInfoVO> getUserMap() {
		return userMap;
	}
	public void setUserMap(Map<String, UserInfoVO> userMap) {
		this.userMap = userMap;
	}
	public Map<String, List<LeaveRegVO>> getLeaveMap() {
		return leaveMap;
	}
	public void setLeaveMap(Map<String, List<LeaveRegVO>> leaveMap) {
		this.leaveMap = leaveMap;
	}
	public Map<String, Set<String>> getUserKeySkillMap() {
		return userKeySkillMap;
	}
	public void setUserKeySkillMap(Map<String, Set<String>> userKeySkillMap) {
		this.userKeySkillMap = userKeySkillMap;
	}
	public Map<String, Set<String>> getSkillKeyUserMap() {
		return skillKeyUserMap;
	}
	public void setSkillKeyUserMap(Map<String, Set<String>> skillKeyUserMap) {
		this.skillKeyUserMap = skillKeyUserMap;
	}
	public Map<String, Set<String>> getCertificateKeyUserMap() {
		return certificateKeyUserMap;
	}
	public void setCertificateKeyUserMap(
			Map<String, Set<String>> certificateKeyUserMap) {
		this.certificateKeyUserMap = certificateKeyUserMap;
	}
	public Map<String, Set<String>> getUserKeyCertificateMap() {
		return userKeyCertificateMap;
	}
	public void setUserKeyCertificateMap(
			Map<String, Set<String>> userKeyCertificateMap) {
		this.userKeyCertificateMap = userKeyCertificateMap;
	}
	public List<BDDocVO> getSkillVOs() {
		return skillVOs;
	}
	public void setSkillVOs(List<BDDocVO> skillVOs) {
		this.skillVOs = skillVOs;
	}
	public List<BDDocVO> getCertifacateVOs() {
		return certifacateVOs;
	}
	public void setCertifacateVOs(List<BDDocVO> certifacateVOs) {
		this.certifacateVOs = certifacateVOs;
	}
	public Map<String, Map<Integer, Set<String>>> getCustomerMap() {
		return customerMap;
	}
	public void setCustomerMap(Map<String, Map<Integer, Set<String>>> customerMap) {
		this.customerMap = customerMap;
	}
	public Integer[] getDegreeArr() {
		return degreeArr;
	}
	public void setDegreeArr(Integer[] degreeArr) {
		this.degreeArr = degreeArr;
	}
}
