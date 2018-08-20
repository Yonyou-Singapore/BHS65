/**
 * 
 */
package nc.bs.so.m30.maintain;

import nc.bs.scmpub.rule.CrossRuleValidateRule;
import nc.bs.so.m30.maintain.rule.insert.RewriteBillInsertRule;
import nc.bs.so.m30.maintain.rule.insert.RewritePromotePriceInsertRule;
import nc.bs.so.m30.plugin.BP30PlugInPoint;
import nc.bs.so.m30.rule.atp.SaleOrderVOATPAfterRule;
import nc.bs.so.m30.rule.atp.SaleOrderVOATPBeforeRule;
import nc.bs.so.m30.rule.billcode.AddNewBillCodeRule;
import nc.bs.so.m30.rule.billcode.CheckUniqueBillCodeRule;
import nc.bs.so.m30.rule.businessinfo.SaveTransferMsgRule;
import nc.bs.so.m30.rule.credit.RenovateARByHidsBeginRule;
import nc.bs.so.m30.rule.credit.RenovateARByHidsEndRule;
import nc.bs.so.m30.rule.feature.FeatureSelectSaveRule;
import nc.bs.so.m30.rule.m35.ArsubOffsetBeforeSaveRule;
import nc.bs.so.m30.rule.m35.LrgCashMarCheckRule;
import nc.bs.so.m30.rule.maintaincheck.CheckDateRule;
import nc.bs.so.m30.rule.maintaincheck.CheckLrgTotalMoney;
import nc.bs.so.m30.rule.maintaincheck.CheckNumPriceMnyRule;
import nc.bs.so.m30.rule.maintaincheck.CheckSaveBillRule;
import nc.bs.so.m30.rule.maintaincheck.CheckSettleOrgRepeat;
import nc.bs.so.m30.rule.maintainprocess.FillupFretexchangeRule;
import nc.bs.so.m30.rule.maintainprocess.FillupRedundanceDataRule;
import nc.bs.so.m30.rule.maintainprocess.InsertSoBalanceWhenAddNewRule;
import nc.bs.so.m30.rule.maintainprocess.ThisGatheringRule;
import nc.bs.so.m30.rule.reserve.AutoReserveRule;
import nc.bs.so.m30.rule.rewrite.price.RewritePriceFormRule;
import nc.bs.so.pub.rule.FillBillTailInfoRuleForIns;
import nc.impl.pubapp.bd.userdef.UserDefSaveRule;
import nc.impl.pubapp.pattern.data.bill.BillInsert;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.vo.credit.engrossmaintain.pub.action.M30EngrossAction;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.pubapp.util.SetAddAuditInfoRule;
import nc.vo.scmpub.rule.SaleOrgEnableCheckRule;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.rule.SaleOrderScaleCheckRule;


/**
 * ��������BP
 * 
 * @author gdsjw
 */
public class InsertSaleOrderBP {

  public SaleOrderVO[] insert(SaleOrderVO[] bills) {
    AroundProcesser<SaleOrderVO> processer =
        new AroundProcesser<SaleOrderVO>(BP30PlugInPoint.InsertBP);

    // ע���
    TimeLog.logStart();
    this.addBeforeRule(processer);
    processer.before(bills);

    TimeLog.info("������������ǰBP�����"); /* -=notranslate=- */

    TimeLog.logStart();
    BillInsert<SaleOrderVO> bo = new BillInsert<SaleOrderVO>();
    SaleOrderVO[] vos = bo.insert(bills);

    TimeLog.info("���浥�ݵ����ݿ�"); /* -=notranslate=- */

    // ע���
    TimeLog.logStart();
    this.addAfterRule(processer);
    processer.after(vos);

    TimeLog.info("�������������BP�����"); /* -=notranslate=- */

    return vos;
  }

  private void addAfterRule(AroundProcesser<SaleOrderVO> processer) {
    IRule<SaleOrderVO> rule = null;

    // �տ������ϵ
    rule = new InsertSoBalanceWhenAddNewRule();
    processer.addAfterRule(rule);

    // ����
    rule = new RenovateARByHidsEndRule(M30EngrossAction.M30Insert);
    processer.addAfterRule(rule);

    // �����տ������
    rule = new ThisGatheringRule();
    processer.addAfterRule(rule);

    // ��鵥�ݺ��Ƿ��ظ�
    rule = new CheckUniqueBillCodeRule();
    processer.addAfterRule(rule);
    // // ��д�ɹ�����---�Ƶ���д��������
    // rule = new Rewrite21InsertRule();
    // processer.addAfterRule(rule);

    // ��д��Դ��Դͷ���ݣ���д���۵�����ͬ��Ԥ�������������ۡ����۶��������ⵥ���������
    rule = new RewriteBillInsertRule();
    processer.addAfterRule(rule);

    // �����ڲ�������Ϣ
    rule = new SaveTransferMsgRule();
    processer.addAfterRule(rule);

    // ����۸����
    rule = new RewritePriceFormRule();
    processer.addAfterRule(rule);

    boolean icEnable = SysInitGroupQuery.isICEnabled();
    if (icEnable) {
      // ������
      rule = new SaleOrderVOATPAfterRule();
      processer.addAfterRule(rule);
      // �����Զ�Ԥ��
      rule = new AutoReserveRule();
      processer.addAfterRule(rule);

    }
    
    // ��д�����۸� jilu for �㰲��������
    if (SysInitGroupQuery.isPRICEEnabled()) {
      rule = new RewritePromotePriceInsertRule();
      processer.addAfterRule(rule);
    }
    // end

  }

  private void addBeforeRule(AroundProcesser<SaleOrderVO> processer) {

    // ������֯ͣ�ü��
    IRule<SaleOrderVO> rule = new SaleOrgEnableCheckRule<SaleOrderVO>();
    processer.addBeforeRule(rule);

    // ��ȫ���ݹ���
    rule = new FillupRedundanceDataRule();
    processer.addBeforeRule(rule);
    
    // ��ȫ�˻������
    rule = new FillupFretexchangeRule();
    processer.addBeforeRule(rule);

    // ��ȡ���ݺŹ���
    rule = new AddNewBillCodeRule();
    processer.addBeforeRule(rule);

    // �������۽�������
    rule = new CheckNumPriceMnyRule();
    processer.addBeforeRule(rule);
    // �����������֯�Ƿ�һ��
    rule = new CheckSettleOrgRepeat();
    processer.addBeforeRule(rule);
    // ���ڼ�����
    rule = new CheckDateRule();
    processer.addBeforeRule(rule);
    // ����������
    rule = new CheckSaveBillRule();
    processer.addBeforeRule(rule);
  
    // ����Ƶ�
    rule = new FillBillTailInfoRuleForIns<SaleOrderVO>();
    processer.addBeforeRule(rule);

    // ��������Ϣ:�����ˡ�����ʱ�䡢����޸��ˡ�����޸�ʱ��
    rule = new SetAddAuditInfoRule<SaleOrderVO>();
    processer.addBeforeRule(rule);

    // ����
    rule = new RenovateARByHidsBeginRule(M30EngrossAction.M30Insert);
    processer.addBeforeRule(rule);

    boolean icEnable = SysInitGroupQuery.isICEnabled();
    if (icEnable) {
      // ������
      rule = new SaleOrderVOATPBeforeRule();
      processer.addBeforeRule(rule);
    }

    // ���۶�������ʱ������Ʒ�Ҹ���Χ���
    rule = new LrgCashMarCheckRule();
    processer.addBeforeRule(rule);

    // ���۶�������ʱ��Ʒ�Ҹ����õ���ֱ���ǰ����
    rule = new ArsubOffsetBeforeSaveRule();
    processer.addBeforeRule(rule);

    // ����ͷ�ͱ�����Ʒ��˰�ϼ��Ƿ�һ��
    rule = new CheckLrgTotalMoney();
    processer.addBeforeRule(rule);

    // ���۶���������ѡ�䱣��
    rule = new FeatureSelectSaveRule();
    processer.addBeforeRule(rule);

    // У���ͷ����¼����Զ������Ƿ�����
    rule = new UserDefSaveRule<SaleOrderVO>(new Class[] {
      SaleOrderHVO.class, SaleOrderBVO.class
    });
    processer.addBeforeRule(rule);
    
    //����У�����
    rule = new CrossRuleValidateRule<SaleOrderVO>();
    processer.addBeforeRule(rule);
  }

}
