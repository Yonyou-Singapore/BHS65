package nc.ui.bhs.pack.ace.view;

import nc.ui.pub.beans.table.ColumnGroup;
import nc.ui.pub.beans.table.GroupableTableHeader;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;

/**
 * pack 卡片界面
 * @autor:Thinkpad
 * @date :2017-3-13
 */
public class PackBillForm extends ShowUpableBillForm{

	private static final long serialVersionUID = 7472030982351025382L;
	
	@Override
	public void initUI() {
		// TODO 自动生成的方法存根
		super.initUI();
		initBodyColumnGroups();
	}
	
	@Override
	public boolean isShowTotalLine() {
		// TODO 自动生成的方法存根
		return true;
	}
	
	/**
	 * 表体列组合
	 * @autor:Thinkpad
	 * @date :2017-3-13
	 * @return void
	 */
	public void initBodyColumnGroups(){

		((GroupableTableHeader) getBillCardPanel().getBillTable().getTableHeader()).clearColumnGroups();
		ColumnGroup cargoDimension = new ColumnGroup("Cargo Dimension(CM)");
		cargoDimension.add(getBillCardPanel().getBodyPanel().getShowCol("cargodimensionl"));
		cargoDimension.add(getBillCardPanel().getBodyPanel().getShowCol("cargodimensionw"));
		cargoDimension.add(getBillCardPanel().getBodyPanel().getShowCol("cargodimensionh"));
		((GroupableTableHeader) getBillCardPanel().getBillTable().getTableHeader()).addColumnGroup(cargoDimension);

		ColumnGroup internalDimension = new ColumnGroup("Internal Dimension(CM)");
		internalDimension.add(getBillCardPanel().getBodyPanel().getShowCol("internaldimensionl"));
		internalDimension.add(getBillCardPanel().getBodyPanel().getShowCol("internaldimensionw"));
		internalDimension.add(getBillCardPanel().getBodyPanel().getShowCol("internaldimensionh"));
		((GroupableTableHeader) getBillCardPanel().getBillTable().getTableHeader()).addColumnGroup(internalDimension);
  
		ColumnGroup crateDimension = new ColumnGroup("Crate Dimension(CM)");
		crateDimension.add(getBillCardPanel().getBodyPanel().getShowCol("cratedimensionl"));
		crateDimension.add(getBillCardPanel().getBodyPanel().getShowCol("cratedimensionw"));
		crateDimension.add(getBillCardPanel().getBodyPanel().getShowCol("cratedimensionh"));
		((GroupableTableHeader) getBillCardPanel().getBillTable().getTableHeader()).addColumnGroup(crateDimension);
		
	}
	
	
}
