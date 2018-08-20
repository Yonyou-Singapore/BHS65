package nc.bs.bhs.sostore.ace.bp;

import nc.bs.bhs.sostore.plugin.bpplugin.SostorePluginPoint;
import nc.bs.bhs.sostore.rule.ReWriteSaleOrderRule;
import nc.impl.pubapp.pattern.data.bill.template.UpdateBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.bhs.sostore.AggSoStoreHVO;

/**
 * �޸ı����BP
 * 
 */
public class AceSostoreUpdateBP {

	public AggSoStoreHVO[] update(AggSoStoreHVO[] bills,
			AggSoStoreHVO[] originBills) {
		// �����޸�ģ��
		UpdateBPTemplate<AggSoStoreHVO> bp = new UpdateBPTemplate<AggSoStoreHVO>(
				SostorePluginPoint.UPDATE);
		// ִ��ǰ����
		this.addBeforeRule(bp.getAroundProcesser());
		// ִ�к����
		this.addAfterRule(bp.getAroundProcesser());
		return bp.update(bills, originBills);
	}

	private void addAfterRule(CompareAroundProcesser<AggSoStoreHVO> processer) {
		// TODO �����
		IRule<AggSoStoreHVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillCodeCheckRule();
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule).setCbilltype("STOR");
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule)
				.setCodeItem("vbillno");
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule)
				.setGroupItem("pk_group");
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule).setOrgItem("pk_org");
		processer.addAfterRule(rule);
		IRule<AggSoStoreHVO> rule1 = new ReWriteSaleOrderRule();
		processer.addAfterRule(rule1);
	}

	private void addBeforeRule(CompareAroundProcesser<AggSoStoreHVO> processer) {
		// TODO ǰ����
		IRule<AggSoStoreHVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.FillUpdateDataRule();
		processer.addBeforeRule(rule);
		nc.impl.pubapp.pattern.rule.ICompareRule<AggSoStoreHVO> ruleCom = new nc.bs.pubapp.pub.rule.UpdateBillCodeRule();
		((nc.bs.pubapp.pub.rule.UpdateBillCodeRule) ruleCom)
				.setCbilltype("STOR");
		((nc.bs.pubapp.pub.rule.UpdateBillCodeRule) ruleCom)
				.setCodeItem("vbillno");
		((nc.bs.pubapp.pub.rule.UpdateBillCodeRule) ruleCom)
				.setGroupItem("pk_group");
		((nc.bs.pubapp.pub.rule.UpdateBillCodeRule) ruleCom)
				.setOrgItem("pk_org");
		processer.addBeforeRule(ruleCom);
	}

}
