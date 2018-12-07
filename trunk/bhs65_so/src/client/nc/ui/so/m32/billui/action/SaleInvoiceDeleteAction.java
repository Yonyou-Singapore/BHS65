package nc.ui.so.m32.billui.action;

import java.util.HashSet;
import java.util.Set;

import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.m32.paravo.CombinCacheVO;
import nc.vo.so.pub.enumeration.BillStatus;

import nc.ui.ml.NCLangRes;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pubapp.pub.common.context.PFlowContext;
import nc.ui.pubapp.uif2app.actions.pflow.DeleteScriptAction;
import nc.ui.so.m32.billui.model.SaleInvoiceManageModel;
import nc.ui.so.m32.billui.pub.SaleInvoiceCombin;
import nc.ui.uif2.UIState;

/**
 * ���۷�Ʊɾ����ť
 * 
 * @since 6.3
 * @version 2012-12-21 ����11:18:33
 * @author yaogj
 */
public class SaleInvoiceDeleteAction extends DeleteScriptAction {

  /**
     * 
     */
  private static final long serialVersionUID = -9001790797638052546L;

  @Override
  protected void fillUpContext(PFlowContext context) {
    super.fillUpContext(context);
    
  //add chenth 20181130  ɾ������
    Object[] uiobj = this.model.getSelectedOperaDatas();
    int length = uiobj.length;
    SaleInvoiceVO[] uicomvos = new SaleInvoiceVO[length];
    for (int i = 0; i < length; i++) {
      uicomvos[i] = (SaleInvoiceVO) uiobj[i];
    }
  	String sReason =
  	        (String) MessageDialog
	            .showInputDlg(this.getModel().getContext().getEntranceUI(),
	                "Reason for deletion",
	                "Deleted invoice please print the archive. And please enter the reason for the deletion.",
	                null, 120);
    
    while(sReason != null && "".equals(sReason.trim())){
    	MessageDialog.showErrorDlg(this.getModel().getContext().getEntranceUI(), "Error", "Please enter the reason for the deletion.");
    	sReason =
      	        (String) MessageDialog
      	            .showInputDlg(this.getModel().getContext().getEntranceUI(),
      	                "Reason for deletion",
      	                "Deleted invoice please print the archive. And please enter the reason for the deletion.",
      	                null, 120);
    }
    // ȡ����ر�ԭ��򷵻صĶ�Ϊnull,�����ر�/�򿪴���
    if (sReason == null) {
    	this.getFlowContext().setBillVos(null);
    	return;
    }
    
    for(SaleInvoiceVO vo : uicomvos){
    	vo.getParentVO().setVnote((vo.getParentVO().getVnote() == null ? "" : vo.getParentVO().getVnote()) +sReason);
    }
    this.getFlowContext().setBillVos(uicomvos);
    //add end
    
    // ����Ʊ�ϲ���ʾ
    this.processCombinShow();
  }

  @Override
  protected boolean isActionEnable() {

    boolean isEnable =
        this.getModel().getUiState() == UIState.NOT_EDIT
            && null != this.getModel().getSelectedData();

    if (isEnable) {
      Object[] selectedRows = this.getModel().getSelectedOperaDatas();
      SaleInvoiceVO selectedData =
          (SaleInvoiceVO) this.getModel().getSelectedData();
      Integer billstatus = selectedData.getParentVO().getFstatusflag();

      isEnable =
          null != selectedRows && selectedRows.length > 1
              || BillStatus.FREE.equalsValue(billstatus)
              || BillStatus.NOPASS.equalsValue(billstatus);
    }
    return isEnable;
  }

  @Override
  protected void processReturnObj(Object[] returnObj) throws Exception {
    SaleInvoiceManageModel invoicemodel =
        (SaleInvoiceManageModel) this.getModel();
    CombinCacheVO cachevo = invoicemodel.getCombinCacheVO();
    // �ϲ���ʾ
    if (cachevo.getBcombinflag()) {
      // ʧ�ܵ�vos
      SaleInvoiceVO[] returnvos = (SaleInvoiceVO[]) returnObj;
      // ԭ����ȫvos
      SaleInvoiceVO[] oldcomvos = (SaleInvoiceVO[]) super.getFullOldVOs();
      Set<SaleInvoiceBVO> bvoset = new HashSet<SaleInvoiceBVO>();
      Set<String> returnids = new HashSet<String>();
      if (null != returnvos) {
        for (SaleInvoiceVO vinvoiceo : returnvos) {
          returnids.add(vinvoiceo.getPrimaryKey());
        }
      }

      for (SaleInvoiceVO vinvoiceo : oldcomvos) {
        if (returnids.contains(vinvoiceo.getPrimaryKey())) {
          continue;
        }
        SaleInvoiceBVO[] bvos = vinvoiceo.getChildrenVO();
        for (SaleInvoiceBVO bvo : bvos) {
          bvoset.add(bvo);
        }
      }

      // SaleInvoiceBVO[] oldcombvos = oldcomvos[0].getChildrenVO();
      SaleInvoiceCombin combin = new SaleInvoiceCombin();
      combin.updateCombinRela(
          bvoset.toArray(new SaleInvoiceBVO[bvoset.size()]),
          cachevo.getCombinRela());
    }

    super.processReturnObj(returnObj);

  }

  private void processCombinShow() {

    SaleInvoiceManageModel invoicemodel =
        (SaleInvoiceManageModel) this.getModel();
    CombinCacheVO cachevo = invoicemodel.getCombinCacheVO();
    // �ϲ���ʾ
    if (null != cachevo && cachevo.getBcombinflag()) {
      Object[] uiobj = this.model.getSelectedOperaDatas();
      int length = uiobj.length;
      SaleInvoiceVO[] uicomvos = new SaleInvoiceVO[length];
      for (int i = 0; i < length; i++) {
        uicomvos[i] = (SaleInvoiceVO) uiobj[i];
      }
      
      SaleInvoiceCombin combin = new SaleInvoiceCombin();
      SaleInvoiceVO[] detainvo =
          combin.splitNoEditSaleInvoice(uicomvos, cachevo.getCombinRela());
      
      this.getFlowContext().setBillVos(detainvo);
    }

  }

}
