package nc.vo.pub;

import nc.impl.pubapp.pattern.database.TempTable;

/**
 * 工具报表临时表
 * @author Thinkpad 
 * 2017-07-17
 */
public class TempTableUtil {

	public static final String TEMP_BHS_TOOL = "TEMP_BHS_TOOL";

	public String getToolTempTable() {
		String[] columns = new String[] { "pk_material", "nnum1",
				"nnum2","nnum3","nnum4","nnum5","nnum6", "nnum7","nnum8",
				"nnum9","nnum10","nnum11","nnum12","nnum13", "nnum14"
		};
		String[] columnTypes = new String[] { "varchar(20)","decimal(28,8)", "decimal(28,8)",
				"decimal(28,8)", "decimal(28,8)", "decimal(28,8)",
				"decimal(28,8)", "decimal(28,8)","decimal(28,8)", "decimal(28,8)",
				"decimal(28,8)", "decimal(28,8)", "decimal(28,8)",
				"decimal(28,8)", "decimal(28,8)"};
		TempTable dao = new TempTable();
		String tableName = TempTableUtil.TEMP_BHS_TOOL;
		tableName = dao.getTempTable(tableName, columns, columnTypes);
		return tableName;
	}

}
