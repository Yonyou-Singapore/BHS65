package nc.bs.ta.psncalendar;

import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDouble;

public class WorkDaysVO extends SuperVO {
	private String pk_workdays;
	private String pk_psndoc;
	private String pk_workdaytype;
	private String workdaytype;
	private String cyearperiod;
	private String cperiod;
	private String cyear;
	private UFDouble days;
	
	@Override
	public String getTableName() {
		return "hr_wokedays";
	}
	
	@Override
	public String getPrimaryKey() {
		return pk_workdays;
	}
	
	@Override
	public String getPKFieldName(){
		return "pk_workdays";
	}
	
	public String getPk_workdays() {
		return pk_workdays;
	}
	public void setPk_workdays(String pk_workdays) {
		this.pk_workdays = pk_workdays;
	}
	public String getPk_psndoc() {
		return pk_psndoc;
	}
	public void setPk_psndoc(String pk_psndoc) {
		this.pk_psndoc = pk_psndoc;
	}
	public String getPk_workdaytype() {
		return pk_workdaytype;
	}
	public void setPk_workdaytype(String pk_workdaytype) {
		this.pk_workdaytype = pk_workdaytype;
	}
	public String getCyearperiod() {
		return cyearperiod;
	}
	public void setCyearperiod(String cyearperiod) {
		this.cyearperiod = cyearperiod;
	}
	public String getCperiod() {
		return cperiod;
	}
	public void setCperiod(String cperiod) {
		this.cperiod = cperiod;
	}
	public String getCyear() {
		return cyear;
	}
	public void setCyear(String cyear) {
		this.cyear = cyear;
	}
	public UFDouble getDays() {
		return days;
	}
	public void setDays(UFDouble days) {
		this.days = days;
	}

	public String getWorkdaytype() {
		return workdaytype;
	}

	public void setWorkdaytype(String workdaytype) {
		this.workdaytype = workdaytype;
	}  

}
