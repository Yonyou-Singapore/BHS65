package nc.vo.bhs.blackbox;

public class JobOrderUsers implements Comparable<JobOrderUsers> {

	private String id;
    private String jobId;
    private String assignedUserId;
    private String assignedUserName;
    private String skillCategoryId;
    private String skillCategoryName;
    private String jobUserStatusId;
    private String jobUserStatusName;
    private String isLocked;
    private String confirmedLateMins;
    private String notCommingInformedDate;
    private String checkedinTime;
    private String checkedoutTime;
    private String onsiteFormFile;
    private String checkinConfirmed;
    private String auditRate;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getJobId() {
		return jobId;
	}
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
	public String getAssignedUserId() {
		return assignedUserId;
	}
	public void setAssignedUserId(String assignedUserId) {
		this.assignedUserId = assignedUserId;
	}
	public String getAssignedUserName() {
		return assignedUserName;
	}
	public void setAssignedUserName(String assignedUserName) {
		this.assignedUserName = assignedUserName;
	}
	public String getSkillCategoryId() {
		return skillCategoryId;
	}
	public void setSkillCategoryId(String skillCategoryId) {
		this.skillCategoryId = skillCategoryId;
	}
	public String getSkillCategoryName() {
		return skillCategoryName;
	}
	public void setSkillCategoryName(String skillCategoryName) {
		this.skillCategoryName = skillCategoryName;
	}
	public String getJobUserStatusId() {
		return jobUserStatusId;
	}
	public void setJobUserStatusId(String jobUserStatusId) {
		this.jobUserStatusId = jobUserStatusId;
	}
	public String getJobUserStatusName() {
		return jobUserStatusName;
	}
	public void setJobUserStatusName(String jobUserStatusName) {
		this.jobUserStatusName = jobUserStatusName;
	}
	public String getIsLocked() {
		return isLocked;
	}
	public void setIsLocked(String isLocked) {
		this.isLocked = isLocked;
	}
	public String getConfirmedLateMins() {
		return confirmedLateMins;
	}
	public void setConfirmedLateMins(String confirmedLateMins) {
		this.confirmedLateMins = confirmedLateMins;
	}
	public String getNotCommingInformedDate() {
		return notCommingInformedDate;
	}
	public void setNotCommingInformedDate(String notCommingInformedDate) {
		this.notCommingInformedDate = notCommingInformedDate;
	}
	public String getCheckedinTime() {
		return checkedinTime;
	}
	public void setCheckedinTime(String checkedinTime) {
		this.checkedinTime = checkedinTime;
	}
	public String getCheckedoutTime() {
		return checkedoutTime;
	}
	public void setCheckedoutTime(String checkedoutTime) {
		this.checkedoutTime = checkedoutTime;
	}
	public String getOnsiteFormFile() {
		return onsiteFormFile;
	}
	public void setOnsiteFormFile(String onsiteFormFile) {
		this.onsiteFormFile = onsiteFormFile;
	}
	public String getCheckinConfirmed() {
		return checkinConfirmed;
	}
	public void setCheckinConfirmed(String checkinConfirmed) {
		this.checkinConfirmed = checkinConfirmed;
	}
	public String getAuditRate() {
		return auditRate;
	}
	public void setAuditRate(String auditRate) {
		this.auditRate = auditRate;
	}
	
	public JobOrderUsers clone(String newJobId){
		JobOrderUsers clone = new JobOrderUsers();
		clone.setJobId(newJobId);
		clone.setSkillCategoryId(skillCategoryId);
		clone.setSkillCategoryName(skillCategoryName);
		clone.setAssignedUserId(assignedUserId);
		clone.setAssignedUserName(assignedUserName);
		clone.setIsLocked(isLocked);
		clone.setJobUserStatusId(jobUserStatusId);
		clone.setJobUserStatusName(jobUserStatusName);
		clone.setCheckedinTime(checkedinTime);
		clone.setCheckedoutTime(checkedoutTime);
		clone.setCheckinConfirmed(checkinConfirmed);
		clone.setNotCommingInformedDate(notCommingInformedDate);
		clone.setConfirmedLateMins(confirmedLateMins);
		return clone;
		
	}
	@Override
	public int compareTo(JobOrderUsers o) {
		if(this.assignedUserName == null)
			return 1;
		return this.assignedUserName.compareTo(o.getAssignedUserName());
	}
}
