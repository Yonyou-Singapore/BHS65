package nc.ui.bhs.pub.action;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;


import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.uif2.NCAction;

/**
 * ��յ�����Ϣ����
 * @author Thinkpad
 * 20170311
 */
public class ClearAllAction extends NCAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2335480920998346108L;
	
	//����յı�ͷ�ֶα���
	private String headfieldcodeStr;
	//����յı���ҳǩ����
	private String bodytablecodeStr;
	
	private ShowUpableBillForm editor;
	
	private List<String> notallowClearHeadList;
	private List<String> notallowClearTailList;

	public ClearAllAction() {
		// TODO �Զ����ɵĹ��캯�����
		setCode("clearallAction");
		setBtnName("clear all");
		initNotAllowClearHeadCodeList();
//		initNotAllowClearTailCodeList();
	}

	@Override
	public void doAction(ActionEvent arg0) throws Exception {
		// TODO �Զ����ɵķ������
		
		if(MessageDialog.showYesNoDlg(getEditor(), "��ʾ", "�Ƿ�ȷ����ս�����Ϣ")  != nc.ui.pub.beans.UIDialog.ID_YES){
			return;
		}
		clearHeadItemValue();
		clearBodyItemValue();
		
	}
	/**
	 * ��ձ�ͷ��Ϣ
	 * @autor:Thinkpad
	 * @date :2017-3-11
	 * @return void
	 */
	private void clearHeadItemValue(){
		
		if(headfieldcodeStr != null && !headfieldcodeStr.equals("")){
			String[] headfileds = headfieldcodeStr.split(","); 
			for (int i = 0; i < headfileds.length; i++) {
				if(notallowClearHeadList.contains(headfileds[i])){
					continue;
				}
				getEditor().getBillCardPanel().setHeadItem(headfileds[i], null);
			}
		}else{
			BillItem[] billitems = getEditor().getBillCardPanel().getHeadShowItems();
			for (int i = 0; i < billitems.length; i++) {
				String key = billitems[i].getKey();
				if(notallowClearHeadList.contains(key)){
					continue;
				}
				getEditor().getBillCardPanel().setHeadItem(key, null);
			}
 		}
	}
	/**
	 * ��ձ�����Ϣ
	 * @autor:Thinkpad
	 * @date :2017-3-11
	 * @return void
	 */
	private void clearBodyItemValue(){
		
		if(bodytablecodeStr != null && !bodytablecodeStr.equals("")){
			String[] tablecodes = bodytablecodeStr.split(","); 
			for (int i = 0; i < tablecodes.length; i++) {
				int rowCount = getEditor().getBillCardPanel().getBillModel(tablecodes[i]).getRowCount();
				if(rowCount < 0){
					continue;
				}
				int[] rows = new int[rowCount];
				for (int j = 0; j < rowCount; j++) {
					rows[j] = j;
				}
				getEditor().getBillCardPanel().getBillModel(tablecodes[i]).delLine(rows);
			}
		}
	}
	
	/**
	 * ��ͷ��������յ��ֶ���Ϣ
	 * @autor:Thinkpad
	 * @date :2017-3-11
	 * @return void
	 */
	public void initNotAllowClearHeadCodeList(){
		if(notallowClearHeadList == null){
			notallowClearHeadList = new ArrayList<String>();
		}
		notallowClearHeadList.add("pk_group");
		notallowClearHeadList.add("pk_org");
		notallowClearHeadList.add("csaleorderid");
		notallowClearHeadList.add("csaleordercode");
		notallowClearHeadList.add("billid");
		notallowClearHeadList.add("pk_billtype");
		notallowClearHeadList.add("pk_transtype");
		notallowClearHeadList.add("transtypecode");
		notallowClearHeadList.add("vbillstatus");
		notallowClearHeadList.add("vbillno");
	}
	
	/**
	 * ��β��������յ��ֶ���Ϣ
	 * @autor:Thinkpad
	 * @date :2017-3-11
	 * @return void
	 */
	public void initNotAllowClearTailCodeList(){
		if(notallowClearTailList == null){
			notallowClearTailList = new ArrayList<String>();
		}
		notallowClearTailList.add("creator");
		notallowClearTailList.add("creationtime");
		notallowClearTailList.add("modifier");
		notallowClearTailList.add("modifiedtime");
	}

	public String getHeadfieldcodeStr() {
		return headfieldcodeStr;
	}

	public void setHeadfieldcodeStr(String headfieldcodeStr) {
		this.headfieldcodeStr = headfieldcodeStr;
	}

	public String getBodytablecodeStr() {
		return bodytablecodeStr;
	}

	public void setBodytablecodeStr(String bodytablecodeStr) {
		this.bodytablecodeStr = bodytablecodeStr;
	}

	public ShowUpableBillForm getEditor() {
		return editor;
	}

	public void setEditor(ShowUpableBillForm editor) {
		this.editor = editor;
	}
	
	
	
	
	

}
