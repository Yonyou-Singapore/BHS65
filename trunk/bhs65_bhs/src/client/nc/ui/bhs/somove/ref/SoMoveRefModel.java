package nc.ui.bhs.somove.ref;

import nc.ui.bd.ref.AbstractRefModel;

public class SoMoveRefModel extends AbstractRefModel {
	public SoMoveRefModel() {
		super();
		reset();
	}
	
	
	public void reset() {
		setRefNodeName("SO Move");
		setRefTitle("SO Move");
		setTableName("bhs_somove_h left join so_saleorder " 
				+ "on bhs_somove_h.csaleorderid = so_saleorder.csaleorderid"
				+ " left join bd_customer on so_saleorder.ccustomerid = bd_customer.pk_customer");
		setFieldCode(new String[] {
				"bd_customer.name",
				"bhs_somove_h.csaleordercode", "bhs_somove_h.vbillno", "bhs_somove_h.fromaddress",
				"bhs_somove_h.toaddress", "bhs_somove_h.departuretime", "bhs_somove_h.jobstarttime",
				"bhs_somove_h.jobendtime"
		});
		setFieldName(new String[] {
				"Customer Name",
				"SO No.", "Job Order No.", "From Address",
				"To Address", "Departure Time", "Job Start Time",
				"Job End Time"
		});
		setHiddenFieldCode(new String[] {
				"bhs_somove_h.billid"
		});
		setPkFieldCode("bhs_somove_h.billid");
		setRefCodeField("bhs_somove_h.vbillno");
		setRefNameField("bhs_somove_h.vbillno");
		setWherePart(" 1=1 and bhs_somove_h.dr=0 ");
		setDefaultFieldCount(getFieldName().length);
		setMutilLangNameRef(false);
	}
	
	
}
