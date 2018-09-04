package nc.ui.bhs.ref;

import nc.ui.bd.ref.AbstractRefModel;

public class ITSLocationRefModel extends AbstractRefModel {
	
	public ITSLocationRefModel() {
		super();
		reset();
	}
	
	
	public void reset() {
		setRefNodeName("Store Location(ITS)");
		setRefTitle("Store Location(ITS)");
		setTableName("v_its_Location_Master");
		setFieldCode(new String[] {
				"Location",
				"Status",
				"Total_Available_Space",
				"OccupiedAreaM2"
				, "M2Available"
				, "No_Of_Tools"
				, "No_Of_Items"
				, "Utilized"
		});
		setFieldName(new String[] {
				"Location",
				"Status",
				"Total Available Space",
				"Occupied Area(M2)"
				, "Available(M2)"
				, "No Of Tools"
				, "No of Items"
				, "Utilized"
		});
		setHiddenFieldCode(new String[]{});
		setPkFieldCode("Location");
		setRefCodeField("Location");
		setRefNameField("Location");
		setDefaultFieldCount(getFieldName().length);
		setMutilLangNameRef(false);
	}

}
