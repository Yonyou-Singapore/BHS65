package nc.ui.so.salequotation.handler.body;

import nc.vo.jcom.lang.StringUtil;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.pub.keyvalue.IKeyRela;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.SOBuysellTriaRule;
import nc.vo.so.pub.rule.SOCountryInfoRule;
import nc.vo.so.pub.rule.SOCustMaterialInfoRule;
import nc.vo.so.pub.rule.SOTaxInfoRule;
import nc.vo.so.salequotation.entity.SalequotationBVO;
import nc.vo.so.salequotation.entity.SalequotationHVO;
import nc.vo.so.salequotation.keyrela.SalequoKeyRela;

import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.pubapp.uif2app.view.util.RefMoreSelectedUtils;
import nc.ui.scmpub.ref.FilterMaterialRefUtils;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.so.salequotation.rule.BodyDefaultRule;
import nc.ui.so.salequotation.rule.SaleQuotationUnitChangeRateRule;
import nc.ui.so.salequotation.rule.SaleQuotationUnitDefaultRule;

/**
 * 
 * @since 6.1
 * @version 2012-12-20 16:29:20
 * @author liangjm
 */
public class MaterialEditHandler {

  /**
   * ���� �༭��
   * 
   * @param e
   */
  public void afterEdit(CardBodyAfterEditEvent e) {
    // --���ն�ѡ����
    RefMoreSelectedUtils utils = new RefMoreSelectedUtils(e.getBillCardPanel());
    int[] rows = utils.refMoreSelected(e.getRow(), e.getKey(), true);

    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    IKeyRela keyRela = new SalequoKeyRela();

    // --1.ͨ������id�������Ĭ�ϵ�λ,
    SaleQuotationUnitDefaultRule unitdef = new SaleQuotationUnitDefaultRule();
    unitdef.setDefaultSaleUnit(keyValue, rows);

    // 2.�����ջ���������������˰����Ϣ
    SOCountryInfoRule conutry = new SOCountryInfoRule(keyValue);
    conutry.setCountryInfoByPk_Org(rows);
    // 3.���ù������͡�����ó��
    SOBuysellTriaRule buysellTria = new SOBuysellTriaRule(keyValue);
    buysellTria.setBuysellAndTriaFlag(rows);
    // 4.ѯ˰
    SOTaxInfoRule taxInfo = new SOTaxInfoRule(keyValue, keyRela);
    taxInfo.setTaxInfoByBodyPos(rows);

    // 5.���ò����㻻����
    SaleQuotationUnitChangeRateRule unitrate =
        new SaleQuotationUnitChangeRateRule(keyValue);
    for (int row : rows) {
      unitrate.calcAstChangeRate(row);
      unitrate.calcQtChangeRate(row);
      
      e.getBillCardPanel().getBillModel().execFormula(row, new String[]{"vbdef2->getcolvalue( bd_material,def1 ,pk_material ,pk_material );"
    	  ,"cc->getcolvalue( bd_material,def1 ,pk_material ,pk_material );"
      });
    }

    // 6.����Ĭ��ֵ����
    BodyDefaultRule defaultValue = new BodyDefaultRule(keyValue);
    defaultValue.setBodyDefaultRule(rows);

    // 7.�༭���Ϻ����ÿͻ�������(V63�¼�)
    SOCustMaterialInfoRule socustmar = new SOCustMaterialInfoRule(keyValue);
    socustmar.set4310CustMaterial(rows);
  }

  /**
   * ���� �༭ǰ
   * 
   * @param e
   */
  public void beforeEdit(CardBodyBeforeEditEvent e) {
    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    String vtrantype = keyValue.getHeadStringValue(SalequotationHVO.VTRANTYPE);
    String customer = keyValue.getHeadStringValue(SalequotationHVO.PK_CUSTOMER);
    if (StringUtil.isEmptyWithTrim(vtrantype)) {
      e.setReturnValue(Boolean.FALSE);
      // fengjb 2012.03.05 UE��ʾ�淶
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006009_0", "04006009-0012")/*@res "�������뱨�۵����͡�"*/);
    }
    else if (StringUtil.isEmptyWithTrim(customer)) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006009_0", "04006009-0073")/*@res "��������ͻ���"*/);
    }
    else {
      // �������Ͽ��Զ�ѡ
      UIRefPane refPane =
          (UIRefPane) cardPanel.getBodyItem(SalequotationBVO.PK_MATERIAL_V)
              .getComponent();
      refPane.setMultiSelectedEnabled(true);
      // ��������Լ������
      BillItem cmaterialvid =
          cardPanel.getBodyItem(SalequotationBVO.PK_MATERIAL_V);
      FilterMaterialRefUtils utils =
          new FilterMaterialRefUtils((UIRefPane) cmaterialvid.getComponent());
      String pk_org = keyValue.getHeadStringValue(SalequotationHVO.PK_ORG);
      utils.filterItemRefByOrg(pk_org);
      e.setReturnValue(Boolean.TRUE);
    }
  }

}
