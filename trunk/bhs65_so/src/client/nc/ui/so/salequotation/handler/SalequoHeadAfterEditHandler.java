package nc.ui.so.salequotation.handler;

import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.itf.scmpub.reference.uap.bd.customer.CustomerPubService;
import nc.itf.scmpub.reference.uap.org.DeptPubService;
import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.itf.so.m4310trantype.IM4310TranTypeService;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.ClientContext;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.so.salequotation.findprice.AbstractPriceFinder;
import nc.ui.so.salequotation.findprice.PriceFinderCreator;
import nc.ui.so.salequotation.model.FindPriceService;
import nc.ui.so.salequotation.model.SalequoModel;
import nc.ui.so.salequotation.pub.SalequoCalculator;
import nc.ui.so.salequotation.rule.BodyDefaultRule;
import nc.vo.bd.cust.saleinfo.CustsaleVO;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m4310trantype.entity.M4310TranTypeVO;
import nc.vo.so.m4310trantype.entity.SalequoDataSource;
import nc.vo.so.pub.SOItemKey;
import nc.vo.so.pub.enumeration.FindPriceTrigRule;
import nc.vo.so.pub.enumeration.PriceDiscountType;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.BodyValueRowRule;
import nc.vo.so.pub.rule.SOCustMaterialInfoRule;
import nc.vo.so.pub.util.SOSysParaInitUtil;
import nc.vo.so.salequotation.entity.SalequotationBVO;
import nc.vo.so.salequotation.entity.SalequotationHVO;

/**
 * 
 * ���۵��༭�¼�
 * 
 * @since 6.1
 * @version 2012-12-20 11:19:20
 * @author liangjm
 */
@SuppressWarnings("restriction")
public class SalequoHeadAfterEditHandler implements
    IAppEventHandler<CardHeadTailAfterEditEvent> {

  private FindPriceService findPriceService;

  private SalequoModel model;

  /**
   * 
   * 
   * @return hh
   */
  public FindPriceService getFindPriceService() {
    return this.findPriceService;
  }

  /**
   * 
   * 
   * @return hh
   */
  public SalequoModel getModel() {
    return this.model;
  }

  @Override
  public void handleAppEvent(CardHeadTailAfterEditEvent e) {
    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    // Ч���Ż������ٺϼƴ���
    cardPanel.getBillModel().setNeedCalculate(false);

    if (SalequotationHVO.CTRANTYPEID.equals(e.getKey())) {
      // 1.���潻������VO
      String trantypecode =
          keyValue.getHeadStringValue(SalequotationHVO.VTRANTYPE);
      this.initM4310TranTypeBuffer(trantypecode);
    }
    if (SalequotationHVO.DQUOTEDATE.equals(e.getKey())) {
      this.editQuoteDate(e);
    }
    if (SalequotationHVO.DENDDATE.equals(e.getKey())) {
      this.editEndDate(e);
    }
    if (SalequotationHVO.PK_CUSTOMER.equals(e.getKey())) {
      this.editCustomer(e, keyValue);
    }
    if (SalequotationHVO.PK_CURRTYPE.equals(e.getKey())) {
      this.editCurrtype(e, keyValue);
    }
    if (SalequotationHVO.NDISCOUNT.equals(e.getKey())) {
      BillModel bm = e.getBillCardPanel().getBillModel();
      for (int i = 0; i < bm.getRowCount(); i++) {
        bm.setValueAt(e.getValue(), i, SalequotationBVO.NDISCOUNTRATE);
        this.execCalculator(e, SalequotationBVO.NDISCOUNTRATE);
      }
    }
    this.findPrice(e);
    cardPanel.getBillModel().setNeedCalculate(true);
  }

  private void editCurrtype(CardHeadTailAfterEditEvent e, IKeyValue keyValue) {
    SalequoCalculator calculator = new SalequoCalculator(e.getBillCardPanel());
    Map<String, M4310TranTypeVO> m4310TranTypeBuffer =
        this.getModel().getM4310TranTypeBuffer();
    String trantype =
        (String) e.getBillCardPanel().getHeadItem(SalequotationHVO.VTRANTYPE)
            .getValueObject();
    if (StringUtil.isEmptyWithTrim(trantype)) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006009_0", "04006009-0008")/*@res "�������Ͳ���Ϊ��"*/);
    }
    M4310TranTypeVO tranTypeVO = m4310TranTypeBuffer.get(trantype);
    // ���õ����۷�ʽ
    boolean isChgPriceOrDiscount = false;
    String modifymny = tranTypeVO.getFmodifymny();
    if (PriceDiscountType.PRICETYPE.getStringValue().equals(modifymny)) {
      isChgPriceOrDiscount = true;
    }
    calculator.setIsChgPriceOrDiscount(isChgPriceOrDiscount);
    int row = e.getBillCardPanel().getRowCount();
    int[] rows = new int[row];
    for (int i = 0; i < row; i++) {
      rows[i] = i;
    }
    if (!isNeedFindPrice(keyValue, tranTypeVO.getFsourceflag())) {
      BodyDefaultRule clearRule = new BodyDefaultRule(keyValue);
      clearRule.clearBodyPriceItemsValue(rows);
    }
    calculator.calculate(rows, SalequotationHVO.PK_CURRTYPE);
  }

  private boolean isNeedFindPrice(IKeyValue keyValue, Integer fsourceflag) {
    if (SalequoDataSource.NO_PRICE.value().equals(fsourceflag)) {
      return false;
    }
    // ����ѯ�۴����������ж��Ƿ�ѯ��
    String pk_org = keyValue.getHeadStringValue(SOItemKey.PK_ORG);
    String[] so21 = null;

    so21 = SOSysParaInitUtil.getSO21(pk_org);

    // ѯ�۴�������Ϊ��
    if (null == so21 || so21.length == 0) {
      return false;
    }
    for (String condition : so21) {
      if (PubAppTool.isEqual(FindPriceTrigRule.CORIGCURRENCYID.getKey(),
          condition)) {
        return true;
      }
    }
    return false;
  }

  public void setFindPriceService(FindPriceService findPriceService) {
    this.findPriceService = findPriceService;
  }

  public void setModel(SalequoModel model) {
    this.model = model;
  }

  private CustsaleVO getCustSaleVO(String[] fieldNames, IKeyValue keyValue) {
    String pk_org = keyValue.getHeadStringValue(SOItemKey.PK_ORG);
    String customer = keyValue.getHeadStringValue(SOItemKey.CCUSTOMERID);
    if (PubAppTool.isNull(customer)) {
      return new CustsaleVO();
    }
    Map<String, CustsaleVO> mret =
        CustomerPubService.getCustSaleVOByPks(new String[] {
          customer
        }, pk_org, fieldNames);

    if (null == mret || mret.size() == 0) {
      return new CustsaleVO();
    }
    return mret.get(customer);
  }

  private void editCustomer(CardHeadTailAfterEditEvent e, IKeyValue keyValue) {

    // ����ǿ���
    BodyValueRowRule countutil = new BodyValueRowRule(keyValue);
    int[] rows = countutil.get4310MarNotNullRows();
    String pk_customer = (String) e.getValue();
    if (!StringUtil.isEmptyWithTrim(pk_customer)) {
      String[] custColumns =
          new String[] {
            CustsaleVO.CURRENCYDEFAULT, CustsaleVO.PAYTERMDEFAULT,
            CustsaleVO.CHANNEL, CustsaleVO.RESPDEPT, CustsaleVO.RESPPERSON,
          };

      CustsaleVO custSaleVO =
          CustomerPubService.getCustSaleVOByPk(pk_customer, e.getContext()
              .getPk_org(), custColumns);

      if (custSaleVO != null) {
        // ��ҵ��Ա
        String cemployeeid = custSaleVO.getRespperson();
        if (!PubAppTool.isNull(cemployeeid)) {
          keyValue.setHeadValue(SalequotationHVO.CEMPLOYEEID, cemployeeid);
        }
        // 2.�ͻ�û��ר�ܲ���
        String deptid = custSaleVO.getRespdept();
        if (!PubAppTool.isNull(deptid)) {
          keyValue.setHeadValue(SalequotationHVO.PK_DEPT, deptid);
          String[] pk_depts = new String[] {
            deptid
          };
          Map<String, String> mapvids =
              DeptPubService.getLastVIDSByDeptIDS(pk_depts);
          keyValue
              .setHeadValue(SalequotationHVO.PK_DEPT_V, mapvids.get(deptid));
        }
      }

      // �ͻ���Ĭ�Ͻ��ױ���
      String pk_currency = null;
      if (custSaleVO != null) {
        pk_currency = custSaleVO.getCurrencydefault();
      }
      // ���ȡ�����ͻ���Ĭ�Ͻ��ױ��֣�ȡ������֯��Ĭ�Ͻ��������֯�ı�λ��
      if (StringUtil.isEmptyWithTrim(pk_currency)) {
        pk_currency =
            OrgUnitPubService.queryOrgCurrByPk(e.getContext().getPk_org());
      }
      BillItem currtypeItem =
          e.getBillCardPanel().getHeadItem(SalequotationHVO.PK_CURRTYPE);
      if (currtypeItem != null) {
        currtypeItem.setValue(pk_currency);
        this.editCurrtype(e, keyValue);
      }
      // Ĭ���ո���Э��
      String pk_payment = null;
      if (custSaleVO != null) {
        pk_payment = custSaleVO.getPaytermdefault();
      }
      e.getBillCardPanel().getHeadItem(SalequotationHVO.PK_PAYTERM)
          .setValue(pk_payment);
      // Ĭ�ϵ��������� buxh
      String pk_channel = null;
      if (custSaleVO != null) {
        pk_channel = custSaleVO.getChannel();
      }
      e.getBillCardPanel().getHeadItem(SalequotationHVO.PK_CHANNELTYPE)
          .setValue(pk_channel);
      // Ĭ�ϵ��ջ���ַ
      String pk_custAddress = null;
      if (custSaleVO != null) {
    	  String custid = custSaleVO.getPk_customer();
    	  String pk_org = keyValue.getHeadStringValue(SOItemKey.PK_ORG);
		  String[] defadds = CustomerPubService.getDefaultAddresses(
			new String[] { custid }, pk_org);
		  if (null != defadds && defadds.length > 0) {
		      pk_custAddress = defadds[0];
		  }
		  e.getBillCardPanel().getHeadItem(SalequotationHVO.VDEF8).setValue(pk_custAddress);
      }

      // �༭���Ϻ����ÿͻ�������(V63�¼�)
      SOCustMaterialInfoRule socustmar = new SOCustMaterialInfoRule(keyValue);
      socustmar.set4310CustMaterial(rows);

    }
  }

  private void editEndDate(CardHeadTailAfterEditEvent e) {
    // У��ʧЧ���ڴ��ڱ�������
    BillItem dendDateItem =
        e.getBillCardPanel().getHeadItem(SalequotationHVO.DENDDATE);
    if (dendDateItem != null) {
      UFDate dendDate = (UFDate) dendDateItem.getValueObject();
      BillItem dquoteDateItem =
          e.getBillCardPanel().getHeadItem(SalequotationHVO.DQUOTEDATE);
      if (dendDate == null || dquoteDateItem == null) {
        return;
      }
      UFDate dquoteDate =
          (UFDate) e.getBillCardPanel()
              .getHeadItem(SalequotationHVO.DQUOTEDATE).getValueObject();
      if (dquoteDate == null) {
        return;
      }
      if (dendDate.before(dquoteDate) || dendDate.equals(dquoteDate)) {
        dendDateItem.setValue(null);
        // fengjb 2012.03.05 UE��ʾ�淶
        ExceptionUtils
            .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
                .getStrByID("4006009_0", "04006009-0014")/*@res "ʧЧ���ڲ���С�ڱ������ڣ�"*/);

      }
    }
  }

  private void editQuoteDate(CardHeadTailAfterEditEvent e) {
    // У�鱨������С��ʧЧ����
    BillItem dquoteDateItem =
        e.getBillCardPanel().getHeadItem(SalequotationHVO.DQUOTEDATE);
    if (dquoteDateItem != null) {
      UFDate dquoteDate =
          (UFDate) e.getBillCardPanel()
              .getHeadItem(SalequotationHVO.DQUOTEDATE).getValueObject();
      BillItem dendDateItem =
          e.getBillCardPanel().getHeadItem(SalequotationHVO.DENDDATE);
      if (dquoteDate == null || dendDateItem == null) {
        return;
      }
      UFDate dendDate = (UFDate) dendDateItem.getValueObject();
      if (dendDate == null) {
        return;
      }
      if (dquoteDate.after(dendDate) || dquoteDate.equals(dendDate)) {
        dquoteDateItem.setValue(null);
        // fengjb 2012.03.05 UE��ʾ�淶
        ExceptionUtils
            .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
                .getStrByID("4006009_0", "04006009-0015")/*@res "�������ڲ��ܴ���ʧЧ���ڣ�"*/);

      }
    }
  }

  private void execCalculator(CardHeadTailAfterEditEvent e, String itemKey) {
    SalequoCalculator calculator = new SalequoCalculator(e.getBillCardPanel());
    Map<String, M4310TranTypeVO> m4310TranTypeBuffer =
        this.getModel().getM4310TranTypeBuffer();
    String trantype =
        (String) e.getBillCardPanel().getHeadItem(SalequotationHVO.VTRANTYPE)
            .getValueObject();
    if (StringUtil.isEmptyWithTrim(trantype)) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006009_0", "04006009-0008")/*@res "�������Ͳ���Ϊ��"*/);
    }
    M4310TranTypeVO tranTypeVO = m4310TranTypeBuffer.get(trantype);
    // ���õ����۷�ʽ
    boolean isChgPriceOrDiscount = false;
    String modifymny = tranTypeVO.getFmodifymny();
    if (PriceDiscountType.PRICETYPE.getStringValue().equals(modifymny)) {
      isChgPriceOrDiscount = true;
    }
    calculator.setIsChgPriceOrDiscount(isChgPriceOrDiscount);
    int row = e.getBillCardPanel().getRowCount();
    int[] rows = new int[row];
    for (int i = 0; i < row; i++) {
      rows[i] = i;
    }
    calculator.calculate(rows, itemKey);
  }

  private void findPrice(CardHeadTailAfterEditEvent e) {
    if (e.getBillCardPanel().getRowCount() <= 0) {
      return;
    }
    Map<String, M4310TranTypeVO> tranTypeBuffer =
        this.getModel().getM4310TranTypeBuffer();
    String vtrantype =
        (String) e.getBillCardPanel().getHeadItem(SalequotationHVO.VTRANTYPE)
            .getValueObject();
    if (!StringUtil.isEmptyWithTrim(vtrantype)) {
      M4310TranTypeVO tranTypeVO = tranTypeBuffer.get(vtrantype);
      if (tranTypeVO == null) {
        return;
      }
      AbstractPriceFinder priceFinder =
          PriceFinderCreator.getInstance().createPriceFinder(
              this.getFindPriceService(), tranTypeVO);
      priceFinder.findPrice(e);
    }
  }

  private void initM4310TranTypeBuffer(String pk_billtypecode) {
    try {
      if (PubAppTool.isNull(pk_billtypecode)) {
        return;
      }
      IM4310TranTypeService qry =
          NCLocator.getInstance().lookup(IM4310TranTypeService.class);
      M4310TranTypeVO m4310TranTypeVO =
          qry.queryTranType(ClientContext.getInstance().getPk_group(),
              pk_billtypecode);
      this.getModel().getM4310TranTypeBuffer()
          .put(m4310TranTypeVO.getVtrantype(), m4310TranTypeVO);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }
}
