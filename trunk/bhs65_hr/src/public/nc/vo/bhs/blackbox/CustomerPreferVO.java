package nc.vo.bhs.blackbox;

import nc.vo.pub.SuperVO;

public class CustomerPreferVO extends SuperVO {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6813983923008326523L;
	
	private String pk_customer;
	private String pk_psndoc;
	private Integer preferdegree;
	public String getPk_customer() {
		return pk_customer;
	}
	public void setPk_customer(String pk_customer) {
		this.pk_customer = pk_customer;
	}
	public String getPk_psndoc() {
		return pk_psndoc;
	}
	public void setPk_psndoc(String pk_psndoc) {
		this.pk_psndoc = pk_psndoc;
	}
	public Integer getPreferdegree() {
		return preferdegree;
	}
	public void setPreferdegree(Integer preferdegree) {
		this.preferdegree = preferdegree;
	}
	
	@Override
	public String getTableName(){
		return "bhs_cust_prefer";
	}
	
	@Override
	public String getPKFieldName() {
		return "pk_customer";
	}
}
