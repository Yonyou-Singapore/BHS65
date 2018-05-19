package nc.vo.so.m32.util;

import java.util.ArrayList;
import java.util.List;

import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.pub.util.SOCurrencyUtil;
import nc.vo.so.pub.util.SOPubParaUtil;
import nc.vo.trade.checkrule.VOChecker;

/**
 * ���۷�Ʊ���ʼ��㣨����VO��
 * 
 * @since 6.0
 * @version 2011-5-21 ����10:55:42
 * @author ô��
 */
public class SaleInvoiceRateUtil {

  /**
   * �۱����ʸı�ķ�Ʊvo
   */
  private List<SaleInvoiceVO> ratechangevos = new ArrayList<SaleInvoiceVO>();

  /**
   * ȫ�ֱ�λ�һ��ʸı�ķ�Ʊvo
   */
  private List<SaleInvoiceVO> globalexchgratevos =
      new ArrayList<SaleInvoiceVO>();

  /**
   * ���ű�λ�һ��ʸı�ķ�Ʊvo
   */
  private List<SaleInvoiceVO> groupchgratevos = new ArrayList<SaleInvoiceVO>();

  /**
   * 
   * @return ȫ�ֱ�λ�һ��ʸı�ķ�Ʊvo
   */
  public List<SaleInvoiceVO> getGlobalexchgratevos() {
    return this.globalexchgratevos;
  }

  /**
   * ����ȫ�ֱ�λ�һ��ʸı�ķ�Ʊvo
   * 
   * @param globalexchgratevos
   */
  public void setGlobalexchgratevos(List<SaleInvoiceVO> globalexchgratevos) {
    this.globalexchgratevos = globalexchgratevos;
  }

  /**
   * 
   * @return ���ű�λ�һ��ʸı�ķ�Ʊvo
   */
  public List<SaleInvoiceVO> getGroupchgratevos() {
    return this.groupchgratevos;
  }

  /**
   * ���ü��ű�λ�һ��ʸı�ķ�Ʊvo
   * 
   * @param groupchgratevos
   */
  public void setGroupchgratevos(List<SaleInvoiceVO> groupchgratevos) {
    this.groupchgratevos = groupchgratevos;
  }

  /**
   * 
   * @return �۱����ʸı�ķ�Ʊvo
   */
  public List<SaleInvoiceVO> getRatechangevos() {
    return this.ratechangevos;
  }

  /**
   * �����۱����ʸı�ķ�Ʊvo
   * 
   * @param ratechangevos
   */
  public void setRatechangevos(List<SaleInvoiceVO> ratechangevos) {
    this.ratechangevos = ratechangevos;
  }

  public void setBuyRate(SaleInvoiceVO[] vos) {
    this.setGlobalLocalCurrencyBuyRate(vos);
    this.setGroupLocalCurrencyBuyRate(vos);
    this.setCurrencyBuyRate(vos);
  }

  /**
   * ȫ�ֻ��ʻ�����֯��λ�Ҽ���
   * 
   * @return
   */
  private boolean isCurToGlobalMoney() {
    return SOPubParaUtil.getInstance().isGlobalLocalCurrencyEnable()
        && !SOPubParaUtil.getInstance().isOrigCurToGlobalMoney();
  }

  /**
   * ���Ż��ʻ�����֯��λ�Ҽ���
   * 
   * @return
   */
  private boolean isCurToGroupMoney(SaleInvoiceVO[] vos) {
    String pk_group = vos[0].getParentVO().getPk_group();
    return SOPubParaUtil.getInstance().isGroupLocalCurrencyEnable(pk_group)
        && !SOPubParaUtil.getInstance().isOrigCurToGroupMoney(pk_group);
  }

  /**
   * ȫ�ֻ��ʻ�����֯ԭ�Ҽ���
   * 
   * @return
   */
  private boolean isOrigCurToGlobalMoney() {
    return SOPubParaUtil.getInstance().isGlobalLocalCurrencyEnable()
        && SOPubParaUtil.getInstance().isOrigCurToGlobalMoney();
  }

  /**
   * ���Ż��ʻ���ԭ�Ҽ���
   * 
   * @return
   */
  private boolean isOrigCurToGroupMoney(SaleInvoiceVO[] vos) {
    String pk_group = vos[0].getParentVO().getPk_group();
    return SOPubParaUtil.getInstance().isGroupLocalCurrencyEnable(pk_group)
        && SOPubParaUtil.getInstance().isOrigCurToGroupMoney(pk_group);
  }

  private void setCurrencyBuyRate(SaleInvoiceVO[] vos) {
    for (SaleInvoiceVO vo : vos) {

      SaleInvoiceHVO hvo = vo.getParentVO();
      String pk_org = hvo.getPk_org();
      // ��������
      UFDate billdate = hvo.getDbilldate();
      // ԭ�ұ���
      String orgcurrency = hvo.getCorigcurrencyid();
      // ��λ��
      String currency = hvo.getCcurrencyid();
      UFDouble oldnexchangerate = null;
      if (!VOChecker.isEmpty(orgcurrency) && !VOChecker.isEmpty(currency)) {
        UFDouble changestrate =
            SOCurrencyUtil.getInCurrencyRateByOrg(pk_org, orgcurrency,
                currency, billdate);
        oldnexchangerate = hvo.getNexchangerate();
//        if (!MathTool.equals(oldnexchangerate, changestrate)) {
          this.ratechangevos.add(vo);
//        }
        hvo.setNexchangerate(changestrate);

      }
    }
  }

  /**
   * ȫ�ֻ��ʻ��ڱ��Ҽ���
   * 
   * @param vos
   */
  private void setGlobalCurRate(SaleInvoiceVO[] vos) {
    for (SaleInvoiceVO vo : vos) {
      // ��������
      UFDate billdate = vo.getParentVO().getDbilldate();
      // ����
      String currency = vo.getParentVO().getCcurrencyid();
      UFDouble newrate =
          SOCurrencyUtil.getGlobalLocalCurrencyBuyRate(currency, billdate);

      UFDouble oldNglobalexchgrate = vo.getParentVO().getNglobalexchgrate();
//      if (!MathTool.equals(oldNglobalexchgrate, newrate)) {
        this.globalexchgratevos.add(vo);
//      }
      vo.getParentVO().setNglobalexchgrate(newrate);
    }
  }

  /**
   * ȫ�ֱ�λ�һ���
   * 
   * @param vos
   * @return
   */
  private void setGlobalLocalCurrencyBuyRate(SaleInvoiceVO[] vos) {
    if (this.isCurToGlobalMoney()) {
      this.setGlobalCurRate(vos);
    }
    if (this.isOrigCurToGlobalMoney()) {
      this.setGlobalOrigCurRate(vos);
    }
  }

  /**
   * ȫ�ֻ��ʻ���ԭ�Ҽ���
   * 
   * @param vos
   */
  private void setGlobalOrigCurRate(SaleInvoiceVO[] vos) {
    for (SaleInvoiceVO vo : vos) {
      // ��������
      UFDate billdate = vo.getParentVO().getDbilldate();
      // ԭ�ұ���
      String orgcurrency = vo.getParentVO().getCorigcurrencyid();

      UFDouble newrate =
          SOCurrencyUtil.getGlobalLocalCurrencyBuyRate(orgcurrency, billdate);

      UFDouble oldNglobalexchgrate = vo.getParentVO().getNglobalexchgrate();
//      if (!MathTool.equals(oldNglobalexchgrate, newrate)) {
        this.globalexchgratevos.add(vo);
//      }
      vo.getParentVO().setNglobalexchgrate(newrate);
    }

  }

  /**
   * ���Ż��ʻ��ڱ��Ҽ���
   * 
   * @param vos
   */
  private void setGroupCurRate(SaleInvoiceVO[] vos) {
    for (SaleInvoiceVO vo : vos) {
      // ��������
      UFDate billdate = vo.getParentVO().getDbilldate();
      // ����
      String currency = vo.getParentVO().getCcurrencyid();

      UFDouble newrate =
          SOCurrencyUtil.getGroupLocalCurrencyBuyRate(currency, billdate);

      UFDouble oldNgroupexchgrate = vo.getParentVO().getNgroupexchgrate();
//      if (!MathTool.equals(oldNgroupexchgrate, newrate)) {
        this.groupchgratevos.add(vo);
//      }
      vo.getParentVO().setNgroupexchgrate(newrate);
    }
  }

  /**
   * ���Ż���
   * 
   * @param vos
   * @return
   */
  private void setGroupLocalCurrencyBuyRate(SaleInvoiceVO[] vos) {

    if (this.isCurToGroupMoney(vos)) {
      this.setGroupCurRate(vos);
    }
    if (this.isOrigCurToGroupMoney(vos)) {
      this.setGroupOrigCurRate(vos);
    }
  }

  /**
   * ���Ż��ʻ���ԭ�Ҽ���
   * 
   * @param vos
   */
  private void setGroupOrigCurRate(SaleInvoiceVO[] vos) {
    for (SaleInvoiceVO vo : vos) {
      // ��������
      UFDate billdate = vo.getParentVO().getDbilldate();
      // ԭ�ұ���
      String orgcurrency = vo.getParentVO().getCorigcurrencyid();

      UFDouble newrate =
          SOCurrencyUtil.getGroupLocalCurrencyBuyRate(orgcurrency, billdate);

      UFDouble oldNgroupexchgrate = vo.getParentVO().getNgroupexchgrate();

//      if (!MathTool.equals(oldNgroupexchgrate, newrate)) {
        this.groupchgratevos.add(vo);
//      }
      vo.getParentVO().setNgroupexchgrate(newrate);
    }

  }
}
