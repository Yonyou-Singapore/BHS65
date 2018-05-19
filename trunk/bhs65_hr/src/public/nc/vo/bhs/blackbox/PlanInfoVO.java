package nc.vo.bhs.blackbox;

public class PlanInfoVO {
	private String plandate;
	private String defaultIsLocked;
	private JobOrder newOrder;
	private String userid;
	private String[] job_user_status_id;
	

	public String getPlandate() {
		return plandate;
	}

	public void setPlandate(String plandate) {
		this.plandate = plandate;
	}

	public String getDefaultIsLocked() {
		return defaultIsLocked;
	}

	public void setDefaultIsLocked(String defaultIsLocked) {
		this.defaultIsLocked = defaultIsLocked;
	}

	public JobOrder getNewOrder() {
		return newOrder;
	}

	public void setNewOrder(JobOrder newOrder) {
		this.newOrder = newOrder;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String[] getJob_user_status_id() {
		return job_user_status_id;
	}

	public void setJob_user_status_id(String[] job_user_status_id) {
		this.job_user_status_id = job_user_status_id;
	}

}
