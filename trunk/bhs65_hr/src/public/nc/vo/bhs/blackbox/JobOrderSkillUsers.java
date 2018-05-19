package nc.vo.bhs.blackbox;

import java.util.ArrayList;
import java.util.List;

public class JobOrderSkillUsers {
	private String jobId;
    private String skillCategoryId;
    private String skillCategoryName;
    private String certificateId;
    private String certificateName;
    private int count;
    private String vehicleno;
    //已排班人数
    private transient int planedcount;
    private List<JobOrderUsers> jobOrderUsers;
    
	public String getJobId() {
		return jobId;
	}
	public void setJobId(String jobId) {
		this.jobId = jobId;
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
	public String getCertificateId() {
		return certificateId;
	}
	public void setCertificateId(String certificateId) {
		this.certificateId = certificateId;
	}
	public String getCertificateName() {
		return certificateName;
	}
	public void setCertificateName(String certificateName) {
		this.certificateName = certificateName;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	/**
	 * 获取工种安排的人员
	 * @return
	 */
	public List<JobOrderUsers> getJobOrderUsers() {
		return jobOrderUsers;
	}
	public void setJobOrderUsers(List<JobOrderUsers> jobOrderUsers) {
		this.jobOrderUsers = jobOrderUsers;
	}
	public int getPlanedcount() {
		return planedcount;
	}
	public void setPlanedcount(int planedcount) {
		this.planedcount = planedcount;
	}
	public String getVehicleno() {
		return vehicleno;
	}
	public void setVehicleno(String vehicleno) {
		this.vehicleno = vehicleno;
	}
	
	public JobOrderSkillUsers clone(String newJobId){
		JobOrderSkillUsers clone = new JobOrderSkillUsers();
		clone.setJobId(newJobId);
		clone.setSkillCategoryId(skillCategoryId);
		clone.setCertificateName(certificateName);
		clone.setCertificateId(certificateId);
		clone.setCertificateName(certificateName);
		clone.setCount(count);
		clone.setVehicleno(vehicleno);
		clone.setJobOrderUsers(jobOrderUsers);
		clone.setPlanedcount(planedcount);
		if(jobOrderUsers != null 
				&& jobOrderUsers.size() > 0){
			JobOrderUsers cloneOrderUser = null;
			List<JobOrderUsers> cloneJobOrderUsers = new ArrayList<JobOrderUsers>();
			for(JobOrderUsers orderUser : jobOrderUsers){
				cloneOrderUser = orderUser.clone(newJobId);
				cloneJobOrderUsers.add(cloneOrderUser);
			}
			clone.setJobOrderUsers(cloneJobOrderUsers);
		}
		return clone;
		
	}
}
