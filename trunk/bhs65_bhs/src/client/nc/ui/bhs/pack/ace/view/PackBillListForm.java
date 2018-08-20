package nc.ui.bhs.pack.ace.view;

import nc.ui.pub.beans.table.ColumnGroup;
import nc.ui.pub.beans.table.GroupableTableHeader;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;

/**
 * pack 列表界面
 * @autor:Thinkpad
 * @date :2017-3-13
 */
public class PackBillListForm extends ShowUpableBillListView{

	private static final long serialVersionUID = -1826043542274258267L;
	@Override
	public void initUI() {
		// TODO 自动生成的方法存根
		super.initUI();
		initBodyColumnGroups();
	}
	/**
	 * 表体列组合
	 * @autor:Thinkpad
	 * @date :2017-3-13
	 * @return void
	 */
	public void initBodyColumnGroups(){
		getBillListPanel().getBodyScrollPane(
				getBillListPanel().getBodyTabbedPane().getSelectedTableCode())
				.getShowCol("nyfhtdfzhtmny");
		((GroupableTableHeader) getBillListPanel().getBodyTable().getTableHeader()).clearColumnGroups();
		ColumnGroup cargoDimension = new ColumnGroup("Cargo Dimension(CM)");
		cargoDimension.add(getBillListPanel().getBodyScrollPane(getBillListPanel().getBodyTabbedPane().getSelectedTableCode()).getShowCol("cargodimensionl"));
		cargoDimension.add(getBillListPanel().getBodyScrollPane(getBillListPanel().getBodyTabbedPane().getSelectedTableCode()).getShowCol("cargodimensionw"));
		cargoDimension.add(getBillListPanel().getBodyScrollPane(getBillListPanel().getBodyTabbedPane().getSelectedTableCode()).getShowCol("cargodimensionh"));
		((GroupableTableHeader) getBillListPanel().getBodyTable().getTableHeader()).addColumnGroup(cargoDimension);

		ColumnGroup internalDimension = new ColumnGroup("Internal Dimension(CM)");
		internalDimension.add(getBillListPanel().getBodyScrollPane(getBillListPanel().getBodyTabbedPane().getSelectedTableCode()).getShowCol("internaldimensionl"));
		internalDimension.add(getBillListPanel().getBodyScrollPane(getBillListPanel().getBodyTabbedPane().getSelectedTableCode()).getShowCol("internaldimensionw"));
		internalDimension.add(getBillListPanel().getBodyScrollPane(getBillListPanel().getBodyTabbedPane().getSelectedTableCode()).getShowCol("internaldimensionh"));
		((GroupableTableHeader) getBillListPanel().getBodyTable().getTableHeader()).addColumnGroup(internalDimension);
  
		ColumnGroup crateDimension = new ColumnGroup("Crate Dimension(CM)");
		crateDimension.add(getBillListPanel().getBodyScrollPane(getBillListPanel().getBodyTabbedPane().getSelectedTableCode()).getShowCol("cratedimensionl"));
		crateDimension.add(getBillListPanel().getBodyScrollPane(getBillListPanel().getBodyTabbedPane().getSelectedTableCode()).getShowCol("cratedimensionw"));
		crateDimension.add(getBillListPanel().getBodyScrollPane(getBillListPanel().getBodyTabbedPane().getSelectedTableCode()).getShowCol("cratedimensionh"));
		((GroupableTableHeader) getBillListPanel().getBodyTable().getTableHeader()).addColumnGroup(crateDimension);
	}
}
