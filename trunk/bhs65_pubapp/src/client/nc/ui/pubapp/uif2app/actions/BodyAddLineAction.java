package nc.ui.pubapp.uif2app.actions;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import nc.bs.uif2.IActionCode;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.BillScrollPane;
import nc.ui.pub.bill.action.BillTableLineAction;
import nc.ui.pubapp.uif2app.actions.intf.ICardPanelDefaultActionProcessor;
import nc.ui.pubapp.uif2app.event.card.BodyRowEditType;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterRowEditEvent;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.actions.ActionInitializer;

public class BodyAddLineAction extends AbstractBodyTableExtendAction implements
    ICardPanelDefaultActionProcessor, IBatchBodyLine {
  private static final long serialVersionUID = 1L;

  /** �Զ����б�־. */
  private int autoAddLine;

  public BodyAddLineAction() {
    this.setCode(IActionCode.ADDLINE);

    NCAction action = new NCAction() {
      private static final long serialVersionUID = 1L;

      @Override
      public void doAction(ActionEvent e) throws Exception {
        // do nothing
      }
    };
    ActionInitializer.initializeAction(action, IActionCode.ADDLINE);
    this.putActionValue(action);
  }

  @Override
  public void doAction() {
	this.getCardPanel().stopEditing();
	  
    // e��Ϊ�ձ�ʾ��Ӧ��ͷ�������������������ť����Ҫ���У�
    // ������ǿ�Ƭ�Զ������¼���ƽ̨�����¼�����ƽ̨model���У�ֻ��Ҫ��������Action������Ĭ��ֵ��
    if (this.autoAddLine == BillScrollPane.AUTOADDLINE) {
      super.getCardPanel().getBodyPanel().addLine(false);
    }
    else {
      super.getCardPanel().getBodyPanel().addLine();
    }
    this.getCardPanel().stopEditing();
    
    int index = this.getCardPanel().getBillModel().getRowCount() - 1;
    this.afterLineInsert(index);
    this.execLoadFormula(index);
    
    this.fireEvent();
  }
  
  protected void fireEvent() {
    int[] rows = new int[]{ this.getCardPanel().getRowCount()-1 };
    this.getModel().fireEvent(
        new CardBodyAfterRowEditEvent(this.getCardPanel(),
            BodyRowEditType.ADDLINE, rows));
  }
  
  protected void batchFireEvent( int[] rows) {
	    this.getModel().fireEvent(new CardBodyAfterRowEditEvent(this.getCardPanel(), BodyRowEditType.ADDLINE, rows));
  }

  @Override
  public int getType() {
    return BillTableLineAction.ADDLINE;
  }

  /**
   * ҳ�汻����ĳ��ʱ����ô˷����������н���Ԫ�صĿ��������á�
   * 
   * @param index
   */
  protected void afterLineInsert(int index) {
    // donothing
  }

  @Override
  protected boolean doBeforeAction(ActionEvent e) {
    this.autoAddLine = e.getID();
    return true;
  }

  protected void execLoadFormula(int index) {

    // ���幫ʽ
    BillItem[] billItems = this.getCardPanel().getBodyItems();
    String[] formulas = this.getExecLoadFormula(billItems);
    if (formulas != null) {
      this.getCardPanel().getBillModel().execFormula(index, formulas);
    }

  }
  
  /**
   * ����ִ�й�ʽ
   * @Description
   */
  protected void batchExecLoadFormula(){
	  //update chenth 20171121 ����Ӧ�û�ȡ��ǰҳǩ��items
//	  BillItem[] billItems = this.getCardPanel().getBodyItems();
	  BillItem[] billItems = this.getCardPanel().getBillModel().getBodyItems();
	  //update chenth end
	  
	    String[] formulas = this.getExecLoadFormula(billItems);
	    if (formulas != null) {
	      this.getCardPanel().getBillModel().execFormulas(formulas);
	    }
  }

  /**
   * ���ִ�еļ��ع�ʽ�� �������ڣ�(2004-2-19 8:43:23)
   */
  protected String[] getExecLoadFormula(BillItem[] items) {
    List<String> vColNames = new ArrayList<String>();
    List<String> vFormulas = new ArrayList<String>();
    for (BillItem item : items) {
      String[] formulas = item.getLoadFormula();
      if (formulas != null) {
        for (String formula2 : formulas) {
          vColNames.add(item.getKey());
          String formula = formula2;
          if (formula.indexOf("->") < 0) {
            formula = item.getKey() + "->" + formula;
          }
          vFormulas.add(formula);
        }
      }
    }

    if (vFormulas.size() == 0) {
      return null;
    }

    String[] formulas = new String[vFormulas.size()];
    // ת��Ϊ����
    for (int i = 0; i < vFormulas.size(); i++) {
      formulas[i] = vFormulas.get(i);
    }
    return formulas;
  }

	@Override
	public void batchBodyLineOperate(int rowLen) {
		super.getCardPanel().getBodyPanel().addLine(rowLen);
		this.getCardPanel().stopEditing();

		int row = 0;
		int[] rows = new int[rowLen];
		for (int index =rowLen; index > 0;index--) {
			row = this.getCardPanel().getBillModel().getRowCount() - index;
			rows[rowLen-index] = row;
			this.afterLineInsert(row);
//			this.execLoadFormula(row);
		}
		batchExecLoadFormula();
		this.batchFireEvent(rows);
	}
}
