package nc.vo.bhs.blackbox;

import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDateTime;

public class UserInfoVO extends SuperVO{

	/**
	 * 
	 */
	private static final long serialVersionUID = -663227985399212454L;
	
	private String pk_psndoc;
	private String name;
	private String code;
	private boolean isAssgin;
	private UFDateTime dstartdate;
	private UFDateTime denddate;
	
	private String color;
	
	@Override
	public String getTableName(){
		return "bd_psndoc";
	}
	
	@Override
	public String getPKFieldName() {
		return "pk_psndoc";
	}

	public String getPk_psndoc() {
		return pk_psndoc;
	}

	public void setPk_psndoc(String pk_psndoc) {
		this.pk_psndoc = pk_psndoc;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public boolean isAssgin() {
		return isAssgin;
	}

	public void setAssgin(boolean isAssgin) {
		this.isAssgin = isAssgin;
	}

	public UFDateTime getDstartdate() {
		return dstartdate;
	}

	public void setDstartdate(UFDateTime dstartdate) {
		this.dstartdate = dstartdate;
	}

	public UFDateTime getDenddate() {
		return denddate;
	}

	public void setDenddate(UFDateTime denddate) {
		this.denddate = denddate;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
}
