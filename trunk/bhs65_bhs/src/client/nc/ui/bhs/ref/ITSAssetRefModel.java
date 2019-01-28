package nc.ui.bhs.ref;

import nc.ui.bd.ref.AbstractRefModel;

public class ITSAssetRefModel extends AbstractRefModel {
	
	public ITSAssetRefModel() {
		super();
		reset();
	}
	
	
	public void reset() {
		setRefNodeName("StorageItem");
		setRefTitle("Storage Item");
		setTableName("v_its_StockAvailable");
		setFieldCode(new String[] {
				"Asset_ID"
				, "EPC_ID"
				, "Asset_No"
				, "Micap_No"
				, "Tool_ID"
				, "Set_No"
				, "Description"
				, "Date_of_Purchase"
				, "Location"
				, "customer"
				, "LastBal"
				, "Doc No."
		});
		setFieldName(new String[] {
				"Asset ID"
				, "EPC ID"
				, "Asset_No"
				, "Micap No."
				, "Tool ID"
				, "Set No."
				, "Description"
				, "Move In Date"
				, "Location"
				, "customer"
				, "LastBal"
				, "Move In Doc No."
		});
//		setHiddenFieldCode(new String[]{"Asset_No"});
		setPkFieldCode("Asset_No");
		setRefCodeField("Asset_ID");
		setRefNameField("Asset_No");
		setDefaultFieldCount(getFieldName().length);
		setMutilLangNameRef(false);
	}

}
