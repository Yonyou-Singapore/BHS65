package nc.vo.bhs.leave;

public class Leave {
	public String leaveapplyid;
	public String employeecode;
	public String leavetypecode;
	public long begintime;
	public long endtime;
	public String remarks;
	
	public String getEmployeecode() {
		return employeecode;
	}
	public void setEmployeecode(String employeecode) {
		this.employeecode = employeecode;
	}
	public String getLeavetypecode() {
		return leavetypecode;
	}
	public void setLeavetypecode(String leavetypecode) {
		this.leavetypecode = leavetypecode;
	}
	public long getBegintime() {
		return begintime;
	}
	public void setBegintime(long begintime) {
		this.begintime = begintime;
	}
	public long getEndtime() {
		return endtime;
	}
	public void setEndtime(long endtime) {
		this.endtime = endtime;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getLeaveapplyid() {
		return leaveapplyid;
	}
	public void setLeaveapplyid(String leaveapplyid) {
		this.leaveapplyid = leaveapplyid;
	}
}
