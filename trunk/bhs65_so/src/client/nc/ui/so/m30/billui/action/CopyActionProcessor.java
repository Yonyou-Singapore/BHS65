/**
 * 
 */
package nc.ui.so.m30.billui.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.itf.price.priceform.IPriceFormService;
import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.ui.pub.pf.PfUtilClient;
import nc.ui.pubapp.AppUiContext;
import nc.ui.pubapp.uif2app.actions.intf.ICopyActionProcessor;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.scmpub.util.StringUtil;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.pub.SaleOrderVOCalculator;
import nc.vo.so.pub.enumeration.BillStatus;
import nc.vo.uif2.LoginContext;

/**
 * @author gdsjw
 * 
 */
public class CopyActionProcessor implements ICopyActionProcessor<SaleOrderVO> {

  @Override
  public void processVOAfterCopy(SaleOrderVO billVO, LoginContext context) {
    // ������ݻָ�
    this.handleOffectData(billVO);
    // ��ͷ��Ϣ
    this.handleHeadInfo(billVO);

    // ���������ô�������Ʒ�����
    this.deleteBuyLargessItems(billVO);

    // ������Ϣ
    this.handleBodyInfo(billVO);
  }

  /**
   * ���������ô�������Ʒ����� add by jilu for 633
   * 
   * @param billVO
   */
  private void deleteBuyLargessItems(SaleOrderVO billVO) {
    if (billVO.getChildrenVO() == null) {
      return;
    }

    List<SaleOrderBVO> filterBVOs = new ArrayList<SaleOrderBVO>();
    for (SaleOrderBVO bVO : billVO.getChildrenVO()) {
      // �������Ʒ�ж�Ӧ��Դ������ID����ֵ���򽫸���ɾ��
      if (StringUtil.isEmptyTrimSpace(bVO.getClargesssrcid())) {
        filterBVOs.add(bVO);
      }
    }
    // ���û���κ�ɾ���ģ������κβ���
    if (filterBVOs.size() == billVO.getChildrenVO().length) {
      return;
    }
    // �����ɾ��������Ʒ�У�����µ��ӱ�VO������
    billVO.setChildrenVO(filterBVOs.toArray(new SaleOrderBVO[0]));
  }

  private void clearBodyOldValue(SaleOrderBVO item) {

    item.setCsaleorderbid(null);
    item.setCsaleorderid(null);
    item.setTs(null);

    // ��Դ��Դͷ
    item.setVsrctype(null);
    item.setCsrcid(null);
    item.setCsrcbid(null);
    item.setVsrccode(null);
    item.setVsrcrowno(null);
    item.setVsrctrantype(null);
    item.setVfirsttype(null);
    item.setVfirstcode(null);
    item.setCfirstid(null);
    item.setCfirstbid(null);
    item.setVfirstrowno(null);
    item.setVfirsttrantype(null);

    // ��ͬ
    item.setVctcode(null);
    item.setCctmanageid(null);
    item.setCctmanagebid(null);

    UFDate date = AppContext.getInstance().getBusiDate();
    item.setDbilldate(date);
    item.setDsenddate(date.asLocalEnd());
    item.setDreceivedate(date.asLocalEnd());
    item.setFrowstatus(null);
    item.setVbrevisereason(null);

    item.setBboutendflag(UFBoolean.FALSE);
    item.setBbsendendflag(UFBoolean.FALSE);
    item.setBbinvoicendflag(UFBoolean.FALSE);
    item.setBbcostsettleflag(UFBoolean.FALSE);
    item.setBbarsettleflag(UFBoolean.FALSE);
    item.setBarrangedflag(UFBoolean.FALSE);

    item.setCarrangepersonid(null);
    item.setTlastarrangetime(null);
    item.setBjczxsflag(null);
    item.setFretexchange(null);
    item.setCexchangesrcretid(null);

    item.setNtotalsendnum(null);
    item.setNtotalinvoicenum(null);
    item.setNtotaloutnum(null);
    item.setNtotalnotoutnum(null);
    item.setNtotalsignnum(null);
    item.setNtranslossnum(null);
    item.setNtotalrushnum(null);
    item.setNtotalestarnum(null);
    item.setNtotalarnum(null);
    item.setNtotalcostnum(null);
    item.setNtotalestarmny(null);
    item.setNtotalarmny(null);
    item.setNtotalpaymny(null);
    item.setNtotalplonum(null);
    item.setNarrangescornum(null);
    item.setNarrangepoappnum(null);
    item.setNarrangetoornum(null);
    item.setNarrangetoappnum(null);
    item.setNarrangemonum(null);
    item.setNarrangeponum(null);
    item.setNtotalreturnnum(null);
    item.setNtotaltradenum(null);
    item.setNreqrsnum(null);

    // ���ó����Ϣ
    item.setNorigsubmny(null);

    // ������Ϣ
    item.setBbindflag(null);
    item.setCbindsrcid(null);

    // ����
    // modified by jilu 2014-08-18 for 633 �������������Ϣ����copyAction�лὫ���ֶ���ֵ����ɾ��������ƥ��
    // item.setClargesssrcid(null);

    // �򿪹ر�ԭ��
    item.setVclosereason(null);
    
    // jilu for ������635
    item.setNarrangeitcnum(null);
  }

  private void handleBodyInfo(SaleOrderVO billVO) {

    UFDate busidate = AppUiContext.getInstance().getBusiDate();
    UFDate localend = busidate.asLocalEnd();

    SaleOrderBVO[] items = billVO.getChildrenVO();
    List<String> listpriceform = new ArrayList<String>();
    for (SaleOrderBVO item : items) {
      String oldpriceform = item.getCpriceformid();
      if (!PubAppTool.isNull(oldpriceform)) {
        listpriceform.add(oldpriceform);
      }
      // ��ձ���ֵ
      this.clearBodyOldValue(item);
      // ����Ĭ��ֵ
      item.setDbilldate(busidate);
      item.setDsenddate(localend);
      item.setDreceivedate(localend);
    }

    if (listpriceform.size() > 0) {
      String[] oldforms = new String[listpriceform.size()];
      listpriceform.toArray(oldforms);

      IPriceFormService pricesrv =
          NCLocator.getInstance().lookup(IPriceFormService.class);
      Map<String, String> mapform = new HashMap<String, String>();
      try {
        mapform = pricesrv.copyPriceForm(oldforms);
      }
      catch (BusinessException e) {
        ExceptionUtils.wrappException(e);
      }
      for (SaleOrderBVO item : items) {
        String oldpriceform = item.getCpriceformid();
        if (PubAppTool.isNull(oldpriceform)) {
          continue;
        }
        item.setCpriceformid(mapform.get(oldpriceform));
      }

    }
  }
  
  private void handleHeadInfo(SaleOrderVO billVO) {

    SaleOrderHVO header = billVO.getParentVO();

    // �����µ�������֯
    String newOrgID = OrgUnitPubService.getNewVIDByOrgID(header.getPk_org());
    header.setPk_org_v(newOrgID);

    // 1.���ø���Ĭ��ֵ
    this.setCopyHeadDefValue(header);

    // 2.�Զ�ƥ��ҵ������
    String trantypecode = header.getVtrantypecode();
    String csaleorgid = header.getPk_org();
    String userid = AppContext.getInstance().getPkUser();
    String cbiztypeid = null;
    try {
      cbiztypeid =
          PfUtilClient.retBusitypeCanStart(SOBillType.Order.getCode(),
              trantypecode, csaleorgid, userid);
    }
    catch (BusinessException ex) {
      ExceptionUtils.wrappException(ex);
    }
    // ����ҵ������
    header.setCbiztypeid(cbiztypeid);
  }

  private void handleOffectData(SaleOrderVO billVO) {
    UFBoolean offsetflag = billVO.getParentVO().getBoffsetflag();
    // δ��ֹ�
    if (null == offsetflag || !offsetflag.booleanValue()) {
      return;
    }

    SaleOrderBVO[] bodyvos = billVO.getChildrenVO();
    List<Integer> listsubrow = new ArrayList<Integer>();
    for (int i = 0; i < bodyvos.length; i++) {
      SaleOrderBVO bvo = bodyvos[i];
      // �ۼƳ�ֽ��
      UFDouble submnys = bvo.getNorigsubmny();
      if (null == submnys || submnys.compareTo(UFDouble.ZERO_DBL) == 0) {
        continue;
      }
      UFDouble norigtaxmny = bvo.getNorigtaxmny();
      UFDouble bfsubmny = MathTool.add(norigtaxmny, submnys);
      bvo.setNorigtaxmny(bfsubmny);
      listsubrow.add(Integer.valueOf(i));
    }

    if (listsubrow.size() > 0) {
      int[] rows = new int[listsubrow.size()];
      int i = 0;
      for (Integer subrow : listsubrow) {
        rows[i] = subrow.intValue();
        i++;
      }

      SaleOrderVOCalculator calcultor = new SaleOrderVOCalculator(billVO);
      calcultor.calculate(rows, SaleOrderBVO.NORIGTAXMNY);
    }
  }

  private void setCopyHeadDefValue(SaleOrderHVO header) {
    header.setCsaleorderid(null);
    header.setVbillcode(null);
    header.setDbilldate(AppContext.getInstance().getBusiDate());
    header.setFstatusflag((Integer) BillStatus.FREE.value());

    header.setBillmaker(null);
    header.setDmakedate(null);
    header.setCreationtime(null);
    header.setApprover(null);
    header.setTaudittime(null);
    header.setModifiedtime(null);

    header.setBoutendflag(UFBoolean.FALSE);
    header.setBinvoicendflag(UFBoolean.FALSE);
    header.setBcostsettleflag(UFBoolean.FALSE);
    header.setBsendendflag(UFBoolean.FALSE);
    header.setBarsettleflag(UFBoolean.FALSE);

    // ʵ��Ԥ�տ��ʵ���տ�������տ���
    header.setNpreceivemny(null);
    header.setNreceivedmny(null);
    header.setNthisreceivemny(null);

    header.setIprintcount(null);

    header.setBcooptopoflag(UFBoolean.FALSE);
    header.setBpocooptomeflag(UFBoolean.FALSE);
    header.setVcooppohcode(null);
    header.setIversion(Integer.valueOf(0));
    header.setTrevisetime(null);
    header.setCreviserid(null);

    header.setModifier(null);
    header.setCreator(null);

    // ���ó����Ϣ
    header.setNtotalmny(null);
    header.setNtotalorigsubmny(null);
    header.setBoffsetflag(null);

    header.setIprintcount(Integer.valueOf(0));
    header.setTs(null);
    header.setVrevisereason(null);
  }
}
