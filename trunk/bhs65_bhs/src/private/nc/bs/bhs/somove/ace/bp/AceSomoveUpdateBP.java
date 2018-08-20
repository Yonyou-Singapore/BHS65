package nc.bs.bhs.somove.ace.bp;

import nc.bs.bhs.somove.plugin.bpplugin.SomovePluginPoint;
import nc.bs.bhs.somove.rule.ReWriteSaleOrderRule;
import nc.impl.pubapp.pattern.data.bill.template.UpdateBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.bhs.somove.AggSoMoveHVO;

/**
 * �޸ı����BP
 * 
 */
public class AceSomoveUpdateBP {

	public AggSoMoveHVO[] update(AggSoMoveHVO[] bills,
			AggSoMoveHVO[] originBills) {
		// �����޸�ģ��
		UpdateBPTemplate<AggSoMoveHVO> bp = new UpdateBPTemplate<AggSoMoveHVO>(
				SomovePluginPoint.UPDATE);
		// ִ��ǰ����
		this.addBeforeRule(bp.getAroundProcesser());
		// ִ�к����
		this.addAfterRule(bp.getAroundProcesser());
		return bp.update(bills, originBills);
	}

	private void addAfterRule(CompareAroundProcesser<AggSoMoveHVO> processer) {
		// TODO �����
		IRule<AggSoMoveHVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillCodeCheckRule();
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule).setCbilltype("MOVE");
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule)
				.setCodeItem("vbillno");
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule)
				.setGroupItem("pk_group");
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule).setOrgItem("pk_org");
		processer.addAfterRule(rule);
		IRule<AggSoMoveHVO> rule1  = new ReWriteSaleOrderRule();
		processer.addAfterRule(rule1);
	}

	private void addBeforeRule(CompareAroundProcesser<AggSoMoveHVO> processer) {
		// TODO ǰ����
		IRule<AggSoMoveHVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.FillUpdateDataRule();
		processer.addBeforeRule(rule);
		nc.impl.pubapp.pattern.rule.ICompareRule<AggSoMoveHVO> ruleCom = new nc.bs.pubapp.pub.rule.UpdateBillCodeRule();
		((nc.bs.pubapp.pub.rule.UpdateBillCodeRule) ruleCom)
				.setCbilltype("MOVE");
		((nc.bs.pubapp.pub.rule.UpdateBillCodeRule) ruleCom)
				.setCodeItem("vbillno");
		((nc.bs.pubapp.pub.rule.UpdateBillCodeRule) ruleCom)
				.setGroupItem("pk_group");
		((nc.bs.pubapp.pub.rule.UpdateBillCodeRule) ruleCom)
				.setOrgItem("pk_org");
		processer.addBeforeRule(ruleCom);
	}

}
