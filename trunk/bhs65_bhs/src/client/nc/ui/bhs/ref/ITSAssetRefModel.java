package nc.ui.bhs.ref;

import nc.ui.bd.ref.AbstractRefModel;

public class ITSAssetRefModel extends AbstractRefModel {
	
	public ITSAssetRefModel() {
		super();
		reset();
	}
	
	
	public void reset() {
		setRefNodeName("Storage Item");
		setRefTitle("Storage Item");
		setTableName("v_its_StockAvailable");
		setFieldCode(new String[] {
				"Location",
				"Asset_ID",
				"Asset_No",
				"Description"
				, "EPC_ID"
				, "Date_of_Purchase"
				, "Date_of_Expire"
				, "customer"
				, "LastBal"
				, "Name"
		});
		setFieldName(new String[] {
				"Location",
				"Tool ID",
				"Asset No",
				"Description"
				, "EPC ID"
				, "Date of Purchase"
				, "Date of Expire"
				, "Customer"
				, "Last Bal"
				, "Name"
		});
		setHiddenFieldCode(new String[]{});
		setPkFieldCode("Asset_No");
		setRefCodeField("Asset_No");
		setRefNameField("Description");
		setDefaultFieldCount(getFieldName().length);
		setMutilLangNameRef(false);
	}

}
