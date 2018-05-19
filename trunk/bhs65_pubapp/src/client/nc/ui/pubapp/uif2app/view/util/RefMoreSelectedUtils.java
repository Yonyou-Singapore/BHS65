package nc.ui.pubapp.uif2app.view.util;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Action;

import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillModel;
import nc.ui.pub.bill.BillScrollPane;
import nc.ui.pub.bill.IBillItem;
import nc.ui.pub.bill.action.BillTableLineAction;
import nc.ui.pub.bill.tableaction.AddLineAction;
import nc.ui.pub.bill.tableaction.InsertLineAction;
import nc.ui.pubapp.uif2app.actions.IBatchBodyLine;
import nc.ui.pubapp.uif2app.view.BatchBillTable;
import nc.ui.uif2.model.BatchBillTableModel;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * ���ն�ѡ������
 * 
 * @author yinyxa 2010-4-7
 */
public class RefMoreSelectedUtils {

  private BillCardPanel billCardPanel;

  private BatchBillTable editor;

  public RefMoreSelectedUtils(BatchBillTable editor) {
    this.editor = editor;
    this.billCardPanel = editor.getBillCardPanel();
  }

  public RefMoreSelectedUtils(nc.ui.pub.bill.BillCardPanel billCardPanel) {
    this.billCardPanel = billCardPanel;
  }

  /**
   * ���ն�ѡ���� - ���ӱ�
   * 
   * @param currentRow
   * @param key
   * @param isAddNullLine
   *          Ϊtrue�������У�Ϊfalse������
   * @return void
   */
  public int[] refMoreSelected(int currentRow, String key, boolean isAddLine) {
    UIRefPane ref =
        (UIRefPane) this.billCardPanel.getBodyItem(key).getComponent();
    String[] refPKs = ref.getRefPKs();

    if (refPKs == null || refPKs.length <= 1) {
      return new int[] {
        currentRow
      };
    }

    boolean isInsert = this.insertLine(currentRow, refPKs.length - 1, isAddLine);

    List<Integer> addRowIndexes = isInsert ? this.getInsertRowIndex(currentRow, refPKs.length):
        this.getAddRowIndex(currentRow, refPKs.length);
    if (addRowIndexes == null || addRowIndexes.size() == 0) {
      return null;
    }
    
    for (int i = 0; i < addRowIndexes.size(); i++) {
      this.billCardPanel.setBodyValueAt(refPKs[i], addRowIndexes.get(i).intValue(), key);
    }

    // ����ִ�б༭��ʽ
    int startrow = currentRow;
    int endrow = currentRow + refPKs.length - 1;
    BillModel model = this.billCardPanel.getBillModel();
    // ���ع�����
    model.loadEditRelationItemValue(startrow, endrow, key);
    model.loadLoadRelationItemValue(startrow, endrow);
    
    //update chenth 20171122 �����ѡ��༭��ʽ��Ч�����⣬ԭ��Դ�����߼�ִ���������ֶεı༭��ʽ���߼������⣬Ӧ��ִֻ�е�ǰ�ֶεı༭��ʽ
    // ִ�б༭��ʽ
    for (int i = startrow; i <= endrow; i++) {
    	model.execEditFormulaByKey(i, key);
	}
    
//    // ִ�б༭��ʽ
//    if (addRowIndexes.size() == 1) {
//      for (int i = startrow; i <= endrow; i++) {
//        model.execEditFormulaByKey(i, key);
//      }
//    } else {
//      model.exec.execEditFormulasByRows(startrow, endrow, key);
//    }
    //update chenth end

    
    return this.toArray(addRowIndexes);
  }

  /**
   * ���ն�ѡ���� - ���� ����Ҫע��editor��������ʱeditorҪע��addLineAction/insLineAction��
   * ������ʱeditorҪע��copyLineAction/pasteLineAction��
   * 
   * @param currentRow
   * @param key
   * @param isAddLine
   *          Ϊtrue�������У�Ϊfalse������
   * @return void
   */
  public int[] refMoreSelectedForBillTable(int currentRow, String key,
      boolean isAddLine) {
    UIRefPane ref =
        (UIRefPane) this.billCardPanel.getBodyItem(key).getComponent();
    String[] refPKs = ref.getRefPKs();
    if (refPKs == null || refPKs.length <= 1) {
      return new int[] {
        currentRow
      };
    }
    try {
      int oldSelectedRow =
          this.billCardPanel.getBodyPanel().getTable().getSelectedRow();

      BatchBillTableModel model =
          this.insertLineForBillTable(currentRow, refPKs.length - 1, isAddLine);

      List<Integer> addRowIndexes =
          this.getAddRowIndex(currentRow, refPKs.length);

      /*for (int i = 0; i < addRowIndexes.size(); i++) {
        this.updateRowForBillTable(model, refPKs[i], addRowIndexes.get(i)
            .intValue(), key);
      }*/
      this.updateRowsForBUlltable(model, refPKs, addRowIndexes, key);

      // ����ִ�й�ʽ
      this.billCardPanel.getBillModel().loadLoadRelationItemValue();

      int newSelectedRow =
          oldSelectedRow <= currentRow ? oldSelectedRow : oldSelectedRow
              + refPKs.length;
      model.setSelectedIndex(newSelectedRow);
      return this.toArray(addRowIndexes);
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
    return null;
  }

  private List<Integer> getAddRowIndex(int currentRow, int length) {
    List<Integer> indexes = new ArrayList<Integer>();
    for (int i = 0; i < length; i++) {
      indexes.add(Integer.valueOf(currentRow + i));
    }
    return indexes;
  }
  private List<Integer> getInsertRowIndex(int currentRow, int length) {
	    List<Integer> indexes = new ArrayList<Integer>();
	    for (int i = length; i > 0; i--) {
	      indexes.add(Integer.valueOf(currentRow + i -1));
	    }
	    return indexes;
	  }
  

  private boolean insertLine(int baseRow, int rowNum, boolean isAddLine) {
    BillScrollPane scrollPane = this.billCardPanel.getBodyPanel();
    Action action = null;
    ActionEvent actionEvent = null;
    // �Ƿ�ϼ���
    Boolean isNeedCalculate = null;
    boolean isInsert = false;
    if (isAddLine) {
      if (baseRow == this.billCardPanel.getBillModel().getRowCount() - 1) {
        action = scrollPane.getBillTableAction(BillTableLineAction.ADDLINE);
        actionEvent =
            new ActionEvent(scrollPane.getTable(), BillTableLineAction.ADDLINE,
                "AddLine");
      }
      else {
        action = scrollPane.getBillTableAction(BillTableLineAction.INSERTLINE);
        isInsert = true;
      }
    }
    else {
      scrollPane.getBillTableAction(BillTableLineAction.COPYLINE).actionPerformed(
          actionEvent);
      action = scrollPane.getBillTableAction(BillTableLineAction.PASTELINE);
      isNeedCalculate = this.billCardPanel.getBillModel().isNeedCalculate();
    }
    if(isNeedCalculate != null) {
      this.billCardPanel.getBillModel().setNeedCalculate(false);
    }
    
    // ���Actionʵ���������ӿڣ����������ӿڷ���
    if(action instanceof IBatchBodyLine) {
      ((IBatchBodyLine)action).batchBodyLineOperate(rowNum);
    } else {
      for (int i = 0; action != null && i < rowNum; i++) {
        // ��Ϊճ����ʱ�ᴥ���ϼ��¼������ϼ��зǳ������ܣ�����ֻ������ճ�����һ��ʱ���кϼ�  by yinyxa 2011-9-19
        if(i == (rowNum-1) && isNeedCalculate != null) {
          this.billCardPanel.getBillModel().setNeedCalculate(isNeedCalculate);
        }
        //�����кͲ����У�ѭ�������������������
        if (action instanceof AddLineAction || action instanceof InsertLineAction)
        	continue;
        
        action.actionPerformed(actionEvent);
      }
      if (action instanceof AddLineAction) {
    	  if(actionEvent.getID() == BillScrollPane.AUTOADDLINE) {
    		 scrollPane.addLine(rowNum, false);
    	  } else {
  			scrollPane.addLine(rowNum, true);
    	  }
      } else if (action instanceof InsertLineAction) {
    	  scrollPane.addLine(rowNum, false);
    	  int row = scrollPane.getTable().getSelectedRow();
		  if (row >= 0) {
				scrollPane.insertLine(row, rowNum);
    	  }
      }
      
    }
    return isInsert;
  }

  private BatchBillTableModel insertLineForBillTable(int baseRow, int rowNum,
      boolean isAddLine) {
    BatchBillTableModel model = null;
    Action action = null;
    if (isAddLine) {
      int count =
          this.billCardPanel.getBodyPanel().getTable().getRowCount() - 1;
      if (baseRow == count) {
        action = this.editor.getAddLineAction();
        model = this.editor.getAddLineAction().getModel();
      }
      else {
        action = this.editor.getInsLineAction();
        model = this.editor.getInsLineAction().getModel();
      }
      model.setSelectedIndex(baseRow);
    }
    else {
      model = this.editor.getCopyLineAction().getModel();
      model.setSelectedIndex(baseRow);
      this.editor.getCopyLineAction().actionPerformed(null);
      action = this.editor.getPasteLineAction();
    }
    
    // ���Actionʵ���������ӿڣ����������ӿڷ���
    if(action instanceof IBatchBodyLine) {
      ((IBatchBodyLine)action).batchBodyLineOperate(rowNum);
    } else {
      for (int i = 0; i < rowNum; i++) {
        action.actionPerformed(null);
      }
    }
    return model;
  }

  private int[] toArray(List<Integer> rowList) {
    int[] rows = new int[rowList.size()];
    int length = rows.length;
    for (int i = 0; i < length; i++) {
      rows[i] = rowList.get(i).intValue();
    }
    return rows;
  }

  private void updateRowForBillTable(BatchBillTableModel model,
      String newRefPK, int currentRow, String key) {
    this.billCardPanel.getBodyValueAt(currentRow, key);
    this.billCardPanel.setBodyValueAt(newRefPK, currentRow, key
        + IBillItem.ID_SUFFIX);
    // this.billCardPanel.getBillModel().loadLoadRelationItemValue(currentRow,
    // key + IBillItem.ID_SUFFIX);

    Object obj = this.editor.getEditingLineVO(currentRow);
    model.updateLine(currentRow, obj);
  }
  
  
  private void updateRowsForBUlltable(BatchBillTableModel model,
		  String[] refPKs, List<Integer> addRowIndexes, String key){
	  
	  Object[] objs=new Object[addRowIndexes.size()];
	  int[] rows=new int[addRowIndexes.size()];
	  for (int i = 0; i < addRowIndexes.size(); i++) {
		  
		  int currentRow=addRowIndexes.get(i).intValue();
		  billCardPanel.getBodyValueAt(currentRow, key);
		  billCardPanel.setBodyValueAt(refPKs[i],currentRow,key+IBillItem.ID_SUFFIX);
		  objs[i] = editor.getEditingLineVO(currentRow);
		  rows[i]=currentRow;
	 }
	  model.updateLines(rows, objs);
  }
}
