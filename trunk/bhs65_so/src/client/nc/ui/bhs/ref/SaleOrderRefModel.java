package nc.ui.bhs.ref;

import nc.ui.bd.ref.AbstractRefModel;

public class SaleOrderRefModel extends AbstractRefModel {
	
	public SaleOrderRefModel() {
		super();
		reset();
	}
	
	
	public void reset() {
		setRefNodeName("Sale Order");
		setRefTitle("Sale Order");
		setTableName("so_saleorder " 
				+ " left join bd_customer on so_saleorder.ccustomerid = bd_customer.pk_customer");
		setFieldCode(new String[] {
				"bd_customer.name",
				"so_saleorder.vbillcode",
				"so_saleorder.dbilldate", "so_saleorder.subject"
		});
		setFieldName(new String[] {
				"Customer Name",
				"SO No.", "SO Date", "Subject"
		});
		setHiddenFieldCode(new String[] {
				"so_saleorder.csaleorderid"
		});
		setPkFieldCode("so_saleorder.csaleorderid");
		setRefCodeField("so_saleorder.vbillcode");
		setRefNameField("so_saleorder.subject");
		setWherePart(" so_saleorder.dr=0 ");
		setDefaultFieldCount(getFieldName().length);
		setMutilLangNameRef(false);
	}

}
