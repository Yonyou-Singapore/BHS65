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
				, "sono","sodate", "jobno", "jobdate"
				, "pono","quotationno", "machinemodel",	"machinesubmodel"
		});
		setFieldName(new String[] {
				"Customer Code",
				"Customer Name",
				"Tool ID",
				"Micap No.",
				"SO No.", "SO Date", "Job Order No.","Job Date"
				, "Po No.", "Quotation No.",
				"Model", "Sub Model",
		});
		setHiddenFieldCode(new String[] {
				"jobid"
		});
		setPkFieldCode("jobid");
		setRefCodeField("custcode");
		setRefNameField("custname");
		setDefaultFieldCount(getFieldName().length);
		setMutilLangNameRef(false);
	}
	
	
}
