package nc.ui.bhs.ref;

import nc.ui.bd.ref.AbstractRefModel;

public class SOPartsRefModel extends AbstractRefModel {
	
	public SOPartsRefModel() {
		super();
		reset();
	}

	public void reset() {
		setRefNodeName("Parts");
		setRefTitle("Parts");
		setTableName(" so_saleorder_b inner join bd_material on cmaterialid = pk_material ");
		setFieldCode(new String[] {
				"bd_material.code",
				"so_saleorder_b.vbdef2",
				"bd_material.name", 
				"so_saleorder_b.nqtorigprice"
				,"so_saleorder_b.csaleorderbid"
		});
		setFieldName(new String[] {
				"Part Code",
				"Description", 
				"Part Name", 
				"Unit Price Excl. Tax"
				,"PK"
		});
		setHiddenFieldCode(new String[] {
				"so_saleorder_b.csaleorderbid"
		});
		setPkFieldCode("so_saleorder_b.csaleorderbid");
		setRefCodeField("bd_material.code");
		setRefNameField("so_saleorder_b.vbdef2");
		setWherePart(" so_saleorder_b.dr=0 ");
		setDefaultFieldCount(getFieldName().length);
		setMutilLangNameRef(false);
	}

}
