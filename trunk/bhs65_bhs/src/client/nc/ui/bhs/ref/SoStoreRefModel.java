package nc.ui.bhs.ref;

import nc.ui.bd.ref.AbstractRefModel;

public class SoStoreRefModel extends AbstractRefModel {
	public SoStoreRefModel() {
		super();
		reset();
	}
	
	public void reset() {
		setRefNodeName("SO Storage");
		setRefTitle("SO Storage");
		setTableName("v_bhs_storejob");
		setFieldCode(new String[] {
				"custcode",	"custname", "toolidlid", "micapno"
				, "sono","sodate"
				, "pono","quotationno", "machinemodel",	"machinesubmodel"
		});
		setFieldName(new String[] {
				"Customer Code",
				"Customer Name",
				"Tool ID",
				"Micap No.",
				"SO No.", "SO Date"
				, "Po No.", "Quotation No.",
				"Model", "Sub Model",
		});
		setHiddenFieldCode(new String[] {
				"csaleorderid"
		});
		setPkFieldCode("csaleorderid");
		setRefCodeField("custcode");
		setRefNameField("custname");
		setDefaultFieldCount(getFieldName().length);
		setMutilLangNameRef(false);
	}
	
	
}
