package nc.vo.bhs.sostore;

import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;

public class ItsStoreInOutVO extends SuperVO {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2618289207365234656L;
	
	private Integer asset_ID;
	private String asset_No; 
	private String description; 
	private String tool_ID; 
	private String micap_No; 
	private String set_No; 
	private String epc_ID; 
	private String date_of_Purchase; 
	private String customer; 
	private String location; 
	private UFDouble length; 
	private UFDouble width; 
	private UFDouble height; 
	private UFDouble space_m2; 
	private UFDouble cubic_Meter; 
	private String project; 
	private UFDate date_In; 
	private UFDate date_Out; 
	private UFDouble qty_In; 
	private UFDouble qty_Out; 
	private String person_In_Charge; 
	private String remarks; 
	private String doc_No;
	

	@Override
	public String getTableName(){
		return "v_its_ItemHistory";
	}
	
	@Override
	public String getPKFieldName() {
		return "asset_ID";
	}
	
	public Integer getAsset_ID() {
		return asset_ID;
	}
	public void setAsset_ID(Integer asset_ID) {
		this.asset_ID = asset_ID;
	}
	public String getAsset_No() {
		return asset_No;
	}
	public void setAsset_No(String asset_No) {
		this.asset_No = asset_No;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTool_ID() {
		return tool_ID;
	}
	public void setTool_ID(String tool_ID) {
		this.tool_ID = tool_ID;
	}
	public String getMicap_No() {
		return micap_No;
	}
	public void setMicap_No(String micap_No) {
		this.micap_No = micap_No;
	}
	public String getSet_No() {
		return set_No;
	}
	public void setSet_No(String set_No) {
		this.set_No = set_No;
	}
	public String getEpc_ID() {
		return epc_ID;
	}
	public void setEpc_ID(String epc_ID) {
		this.epc_ID = epc_ID;
	}
	public String getDate_of_Purchase() {
		return date_of_Purchase;
	}
	public void setDate_of_Purchase(String date_of_Purchase) {
		this.date_of_Purchase = date_of_Purchase;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public UFDouble getLength() {
		return length;
	}
	public void setLength(UFDouble length) {
		this.length = length;
	}
	public UFDouble getWidth() {
		return width;
	}
	public void setWidth(UFDouble width) {
		this.width = width;
	}
	public UFDouble getHeight() {
		return height;
	}
	public void setHeight(UFDouble height) {
		this.height = height;
	}
	public UFDouble getSpace_m2() {
		return space_m2;
	}
	public void setSpace_m2(UFDouble space_m2) {
		this.space_m2 = space_m2;
	}
	public UFDouble getCubic_Meter() {
		return cubic_Meter;
	}
	public void setCubic_Meter(UFDouble cubic_Meter) {
		this.cubic_Meter = cubic_Meter;
	}
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	public UFDate getDate_In() {
		return date_In;
	}
	public void setDate_In(UFDate date_In) {
		this.date_In = date_In;
	}
	public UFDate getDate_Out() {
		return date_Out;
	}
	public void setDate_Out(UFDate date_Out) {
		this.date_Out = date_Out;
	}
	public UFDouble getQty_In() {
		return qty_In;
	}
	public void setQty_In(UFDouble qty_In) {
		this.qty_In = qty_In;
	}
	public UFDouble getQty_Out() {
		return qty_Out;
	}
	public void setQty_Out(UFDouble qty_Out) {
		this.qty_Out = qty_Out;
	}
	public String getPerson_In_Charge() {
		return person_In_Charge;
	}
	public void setPerson_In_Charge(String person_In_Charge) {
		this.person_In_Charge = person_In_Charge;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getDoc_No() {
		return doc_No;
	}
	public void setDoc_No(String doc_No) {
		this.doc_No = doc_No;
	}

}
