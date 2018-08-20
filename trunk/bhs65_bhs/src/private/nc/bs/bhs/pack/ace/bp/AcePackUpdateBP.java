package nc.bs.bhs.pack.ace.bp;

import nc.bs.bhs.pack.plugin.bpplugin.PackPluginPoint;
import nc.bs.bhs.pack.rule.ReWriteSaleOrderRule;
import nc.impl.pubapp.pattern.data.bill.template.UpdateBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.bhs.pack.AggSoOrderPackVO;

/**
 * �޸ı����BP
 * 
 */
public class AcePackUpdateBP {

	public AggSoOrderPackVO[] update(AggSoOrderPackVO[] bills,
			AggSoOrderPackVO[] originBills) {
		// �����޸�ģ��
		UpdateBPTemplate<AggSoOrderPackVO> bp = new UpdateBPTemplate<AggSoOrderPackVO>(
				PackPluginPoint.UPDATE);
		// ִ��ǰ����
		this.addBeforeRule(bp.getAroundProcesser());
		// ִ�к����
		this.addAfterRule(bp.getAroundProcesser());
		return bp.update(bills, originBills);
	}

	private void addAfterRule(CompareAroundProcesser<AggSoOrderPackVO> processer) {
		// TODO �����
		IRule<AggSoOrderPackVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillCodeCheckRule();
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule).setCbilltype("PACK");
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule)
				.setCodeItem("vbillno");
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule)
				.setGroupItem("pk_group");
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule).setOrgItem("pk_org");
		processer.addAfterRule(rule);
		IRule<AggSoOrderPackVO> rule1 = new ReWriteSaleOrderRule();
		processer.addAfterRule(rule1);
	}

	private void addBeforeRule(CompareAroundProcesser<AggSoOrderPackVO> processer) {
		// TODO ǰ����
		IRule<AggSoOrderPackVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.FillUpdateDataRule();
		processer.addBeforeRule(rule);
		nc.impl.pubapp.pattern.rule.ICompareRule<AggSoOrderPackVO> ruleCom = new nc.bs.pubapp.pub.rule.UpdateBillCodeRule();
		((nc.bs.pubapp.pub.rule.UpdateBillCodeRule) ruleCom)
				.setCbilltype("PACK");
		((nc.bs.pubapp.pub.rule.UpdateBillCodeRule) ruleCom)
				.setCodeItem("vbillno");
		((nc.bs.pubapp.pub.rule.UpdateBillCodeRule) ruleCom)
				.setGroupItem("pk_group");
		((nc.bs.pubapp.pub.rule.UpdateBillCodeRule) ruleCom)
				.setOrgItem("pk_org");
		processer.addBeforeRule(ruleCom);
	}

}
