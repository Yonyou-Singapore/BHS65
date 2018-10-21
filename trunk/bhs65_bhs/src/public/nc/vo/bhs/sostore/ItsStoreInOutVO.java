package nc.vo.bhs.sostore;

import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;

public class ItsStoreInOutVO extends SuperVO implements Comparable<ItsStoreInOutVO>{
	
	
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
	private UFDate date_InOut; 
	private UFDouble qty_In; 
	private UFDouble qty_Out; 
	private String person_In_Charge; 
	private String remarks; 
	private String doc_No;
	private UFDouble space_ft;

	private UFDouble bal_m2; 
	private UFDouble bal_m3; 
	private UFDouble bal_sqft;
	private UFDouble bal_qty; 
	
	

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
		return space_m2==null?UFDouble.ZERO_DBL:space_m2;
	}
	public void setSpace_m2(UFDouble space_m2) {
		this.space_m2 = space_m2;
	}
	public UFDouble getCubic_Meter() {
		return cubic_Meter==null?UFDouble.ZERO_DBL:cubic_Meter;
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
	public UFDouble getQty_In() {
		return qty_In==null?UFDouble.ZERO_DBL:qty_In;
	}
	public void setQty_In(UFDouble qty_In) {
		this.qty_In = qty_In;
	}
	public UFDouble getQty_Out() {
		return qty_Out==null?UFDouble.ZERO_DBL:qty_Out;
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

	public UFDate getDate_InOut() {
		return date_InOut;
	}

	public void setDate_InOut(UFDate date_InOut) {
		this.date_InOut = date_InOut;
	}

	@Override
	public int compareTo(ItsStoreInOutVO o) {
		if(date_InOut.compareTo(o.getDate_InOut()) == 0){
			return (qty_Out == null ? UFDouble.ZERO_DBL : qty_Out).compareTo(o.getQty_Out()==null?UFDouble.ZERO_DBL:o.getQty_Out());
		}else{
			return this.date_InOut.compareTo(o.getDate_InOut());
		}
	}

	public UFDouble getSpace_ft() {
		return space_ft==null?UFDouble.ZERO_DBL:space_ft;
	}

	public void setSpace_ft(UFDouble space_ft) {
		this.space_ft = space_ft;
	}

	public UFDouble getBal_m2() {
		return bal_m2;
	}

	public void setBal_m2(UFDouble bal_m2) {
		this.bal_m2 = bal_m2;
	}

	public UFDouble getBal_m3() {
		return bal_m3;
	}

	public void setBal_m3(UFDouble bal_m3) {
		this.bal_m3 = bal_m3;
	}

	public UFDouble getBal_sqft() {
		return bal_sqft;
	}

	public void setBal_sqft(UFDouble bal_sqft) {
		this.bal_sqft = bal_sqft;
	}

	public UFDouble getBal_qty() {
		return bal_qty;
	}

	public void setBal_qty(UFDouble bal_qty) {
		this.bal_qty = bal_qty;
	}


}
