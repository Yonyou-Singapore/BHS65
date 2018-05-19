package nc.ui.so.salequotation.handler;

import nc.ui.bd.ref.model.CustAddressDefaultRefModel;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.ui.scmpub.ref.FilterCustomerRefUtils;
import nc.ui.scmpub.ref.FilterDeptRefUtils;
import nc.ui.scmpub.ref.FilterPsndocRefUtils;
import nc.ui.scmpub.ref.FilterTransTypeRefUtils;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.price.pub.context.PriceContext;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.salequotation.entity.SalequotationHVO;

public class SalequoHeadBeforeEditHandler implements
    IAppEventHandler<CardHeadTailBeforeEditEvent> {

  @Override
  public void handleAppEvent(CardHeadTailBeforeEditEvent e) {
    boolean retFlag = true;
    // ���۵�����
    if (SalequotationHVO.CTRANTYPEID.equals(e.getKey())) {
      this.filterTranType(e);
      retFlag = true;
    }
    if (SalequotationHVO.PK_DEPT_V.equals(e.getKey())) {
      this.filterDept(e);
      retFlag = true;
    }
    if (SalequotationHVO.CEMPLOYEEID.equals(e.getKey())) {
      this.filterEmployee(e);
      retFlag = true;
    }
    if(SalequotationHVO.PK_CUSTOMER.equals(e.getKey())){
      custEdit(e);
    }
    if (SalequotationHVO.VDEF8.equals(e.getKey())) {
    	this.filterAddress(e);
    	retFlag = true;
    }
    e.setReturnValue(Boolean.valueOf(retFlag));
  }
  
  private void custEdit(CardHeadTailBeforeEditEvent e) {
    // �����ͻ�
    BillItem customeritem =
        e.getBillCardPanel().getHeadTailItem(SalequotationHVO.PK_CUSTOMER);
    FilterCustomerRefUtils filterutils =
        new FilterCustomerRefUtils(customeritem);
    filterutils.filterRefByFrozenFlag(UFBoolean.FALSE);

  }

  private void filterDept(CardHeadTailBeforeEditEvent e) {
    String pk_org = e.getContext().getPk_org();
    if (PubAppTool.isNull(pk_org)) {
      e.setReturnValue(Boolean.FALSE);
      String message = nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006009_0","04006009-0016")/*@res "����¼��������֯"*/;
      e.getBillCardPanel().transferFocus();
      ExceptionUtils.wrappBusinessException(message);
    }
    BillItem item =
        e.getBillCardPanel().getHeadItem(SalequotationHVO.PK_DEPT_V);
    FilterDeptRefUtils filter = FilterDeptRefUtils
        .createFilterDeptRefUtilsOfSO((UIRefPane) item.getComponent());
        filter.filterItemRefByOrg(pk_org);
  }

  private void filterEmployee(CardHeadTailBeforeEditEvent e) {
    String pk_org = e.getContext().getPk_org();
    if (PubAppTool.isNull(pk_org)) {
      e.setReturnValue(Boolean.FALSE);
      String message = nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006009_0","04006009-0016")/*@res "����¼��������֯"*/;
      e.getBillCardPanel().transferFocus();
      ExceptionUtils.wrappBusinessException(message);
    }

    BillItem item =
        e.getBillCardPanel().getHeadItem(SalequotationHVO.CEMPLOYEEID);
    FilterPsndocRefUtils filter = FilterPsndocRefUtils
        .createFilterPsndocRefUtilsOfSO((UIRefPane) item.getComponent());
        filter.filterItemRefByOrg(pk_org);
        
    // ����Զ���λ������
    String pk_dept =
        (String) e.getBillCardPanel().getHeadItem(SalequotationHVO.PK_DEPT)
            .getValueObject();
    filter.fixFocusByPK(pk_dept);
  }

  private void filterTranType(CardHeadTailBeforeEditEvent e) {
    BillItem trantypeItem = e.getBillCardPanel().getHeadItem(e.getKey());
    FilterTransTypeRefUtils transTypeFilter =
        new FilterTransTypeRefUtils(trantypeItem, e.getContext().getPk_org());
    transTypeFilter.filterTranType(new String[] {
      PriceContext.SALEQUOBILLTYPE
    });
  }
  
  private void filterAddress(CardHeadTailBeforeEditEvent e) {
	  BillCardPanel panel = e.getBillCardPanel();
	  BillItem item = panel.getHeadItem(e.getKey());
	  UIRefPane refPane = (UIRefPane) item.getComponent();
	  
	  CustAddressDefaultRefModel model = (CustAddressDefaultRefModel) refPane.getRefModel();
	  
	  IKeyValue keyValue = (IKeyValue) new CardKeyValue(panel);
	  String customer = keyValue.getHeadStringValue(SalequotationHVO.PK_CUSTOMER);
	  
	  model.setPk_customer(customer);
	  
  }
}