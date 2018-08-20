package nc.ui.bhs.pack.ace.view;

import nc.ui.pub.beans.table.ColumnGroup;
import nc.ui.pub.beans.table.GroupableTableHeader;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;

/**
 * pack ��Ƭ����
 * @autor:Thinkpad
 * @date :2017-3-13
 */
public class PackBillForm extends ShowUpableBillForm{

	private static final long serialVersionUID = 7472030982351025382L;
	
	@Override
	public void initUI() {
		// TODO �Զ����ɵķ������
		super.initUI();
		initBodyColumnGroups();
	}
	
	@Override
	public boolean isShowTotalLine() {
		// TODO �Զ����ɵķ������
		return true;
	}
	
	/**
	 * ���������
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
