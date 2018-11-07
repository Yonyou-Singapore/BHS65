package nc.vo.bhs.blackbox;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.vo.pub.lang.UFDateTime;

public class JobOrder implements Comparable<JobOrder>{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2747522975286285404L;
	private String jobId;
	private String soNo;
	private String subject;
	private String customerId;
	private String customerName;
	private String customerAddress;
	private String contactPerson;
	private String contactPhoneNo;
	private String requirement;
	private transient UFDateTime tstartDate;
	private transient UFDateTime tendDate;

	private long startDate;
	private long endDate;
	private String color;
	
	private String fromLocation;
	private String fromAddress;
	private String fromContact;
	private String fromContactphone;
	private String toLocation;
	private String toAddress;
	private String toContact;
	private String toContactphone;
	
	//add chenth 20180409
	private transient String fromlocationid;
	private transient String tolocationid;
	
	private List<JobOrderSkillUsers> jobOrderSkillUsers;
	
	//add chenth 20180409
	private String oz_job_user_id;
	private String oz_job_user_status_id;
	
	//add chenth 20181107
	private String micap_no;
	
	
	/**
	 * 订单工种map: key: jobid+skillid value: JobOrderSkillUsers
	 */
	private transient Map<String, List<JobOrderSkillUsers>> jobSkillMap;
	
	/**
	 * 订单工种人员map： key: jobid+skillid value: JobOrderUsers
	 */
	private transient Map<String, List<JobOrderUsers>> jobSkillUsersMap;
	
	private transient String operator;
	private transient String refjoborderID;
	private transient List<JobOrder> refjoborder;
	private transient String customerAddressID;
	private transient String customerPostCode;
	private transient String combinejoborderID;
	
	
	public String getJobId() {
		return jobId;
	}
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
	public String getSoNo() {
		return soNo;
	}
	public void setSoNo(String soNo) {
		this.soNo = soNo;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerAddress() {
		return customerAddress;
	}
	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}
	public String getContactPerson() {
		return contactPerson;
	}
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}
	public String getContactPhoneNo() {
		return contactPhoneNo;
	}
	public void setContactPhoneNo(String contactPhoneNo) {
		this.contactPhoneNo = contactPhoneNo;
	}
	public String getRequirement() {
		return requirement;
	}
	public void setRequirement(String requirement) {
		this.requirement = requirement;
	}
	public UFDateTime getTStartDate() {
		return tstartDate;
	}
	public void setTStartDate(UFDateTime startDate) {
		this.tstartDate = startDate;
	}
	public UFDateTime getTEndDate() {
		return tendDate;
	}
	public void setTEndDate(UFDateTime endDate) {
		this.tendDate = endDate;
	}
	
	/**
	 * 获取 job order 的工种需求
	 * @return List<JobOrderSkillUsers>
	 */
	public List<JobOrderSkillUsers> getJobOrderSkillUsers() {
		return jobOrderSkillUsers;
	}
	/**
	 * 设置job order 的工种需求
	 * @param jobOrderSkillUsers
	 */
	public void setJobOrderSkillUsers(List<JobOrderSkillUsers> jobOrderSkillUsers) {
		this.jobOrderSkillUsers = jobOrderSkillUsers;
	}
	
	/**
	 * Skill Map key： joborderid + skillid (存在同一工种不同证书需求）
	 * @return
	 */
	public Map<String, List<JobOrderSkillUsers>> getJobSkillMap() {
		if(jobSkillMap == null){
			jobSkillMap = new HashMap<String, List<JobOrderSkillUsers>>();
		}
		return jobSkillMap;
	}
	
	/**
	 * 工种人员 Map Key:joborderid + skillid 
	 * @return
	 */
	public Map<String, List<JobOrderUsers>> getJobSkillUsersMap() {
		if(jobSkillUsersMap == null){
			jobSkillUsersMap = new HashMap<String, List<JobOrderUsers>>();
		}
		return jobSkillUsersMap;
	}
	
	public long getStartDate() {
		return startDate;
	}
	public void setStartDate(long startDate) {
		this.startDate = startDate;
	}
	public long getEndDate() {
		return endDate;
	}
	public void setEndDate(long endDate) {
		this.endDate = endDate;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	
	public String getRefjoborderID() {
		return refjoborderID;
	}
	public void setRefjoborderID(String refjoborderID) {
		this.refjoborderID = refjoborderID;
	}
	public List<JobOrder> getRefjoborder() {
		return refjoborder;
	}
	public void setRefjoborder(List<JobOrder> refjoborder) {
		this.refjoborder = refjoborder;
	}

	public String getCustomerAddressID() {
		return customerAddressID;
	}
	public void setCustomerAddressID(String customerAddressID) {
		this.customerAddressID = customerAddressID;
	}

	public String getCustomerPostCode() {
		return customerPostCode;
	}
	public void setCustomerPostCode(String customerPostCode) {
		this.customerPostCode = customerPostCode;
	}
	
	@Override
	public int compareTo(JobOrder o) {
		if(this.tstartDate.compareTo(o.getTStartDate()) == 0)
			return this.tendDate.compareTo(o.getTEndDate());
		return this.tstartDate.compareTo(o.getTStartDate());
	}
	
	public String getCombinejoborderID() {
		return combinejoborderID;
	}
	public void setCombinejoborderID(String combinejoborderID) {
		this.combinejoborderID = combinejoborderID;
	}
	public String getFromLocation() {
		return fromLocation;
	}
	public void setFromLocation(String fromLocation) {
		this.fromLocation = fromLocation;
	}
	public String getFromAddress() {
		return fromAddress;
	}
	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}
	public String getFromContact() {
		return fromContact;
	}
	public void setFromContact(String fromContact) {
		this.fromContact = fromContact;
	}
	public String getFromContactphone() {
		return fromContactphone;
	}
	public void setFromContactphone(String fromContactphone) {
		this.fromContactphone = fromContactphone;
	}
	public String getToLocation() {
		return toLocation;
	}
	public void setToLocation(String toLocation) {
		this.toLocation = toLocation;
	}
	public String getToAddress() {
		return toAddress;
	}
	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}
	public String getToContact() {
		return toContact;
	}
	public void setToContact(String toContact) {
		this.toContact = toContact;
	}
	public String getToContactphone() {
		return toContactphone;
	}
	public void setToContactphone(String toContactphone) {
		this.toContactphone = toContactphone;
	}
	public String getFromlocationid() {
		return fromlocationid;
	}
	public void setFromlocationid(String fromlocationid) {
		this.fromlocationid = fromlocationid;
	}
	public String getTolocationid() {
		return tolocationid;
	}
	public void setTolocationid(String tolocationid) {
		this.tolocationid = tolocationid;
	}
	public String getOz_job_user_id() {
		return oz_job_user_id;
	}
	public void setOz_job_user_id(String oz_job_user_id) {
		this.oz_job_user_id = oz_job_user_id;
	}
	public String getOz_job_user_status_id() {
		return oz_job_user_status_id;
	}
	public void setOz_job_user_status_id(String oz_job_user_status_id) {
		this.oz_job_user_status_id = oz_job_user_status_id;
	}
	public String getMicap_no() {
		return micap_no;
	}
	public void setMicap_no(String micap_no) {
		this.micap_no = micap_no;
	}
}
