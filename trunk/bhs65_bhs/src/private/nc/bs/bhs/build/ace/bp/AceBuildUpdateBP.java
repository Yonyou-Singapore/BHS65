package nc.bs.bhs.build.ace.bp;

import nc.bs.bhs.build.plugin.bpplugin.BuildPluginPoint;
import nc.bs.bhs.build.rule.ReWriteSaleOrderRule;
import nc.impl.pubapp.pattern.data.bill.template.UpdateBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.bhs.build.AggSoOrderBuildHVO;

/**
 * �޸ı����BP
 * 
 */
public class AceBuildUpdateBP {

	public AggSoOrderBuildHVO[] update(AggSoOrderBuildHVO[] bills,
			AggSoOrderBuildHVO[] originBills) {
		// �����޸�ģ��
		UpdateBPTemplate<AggSoOrderBuildHVO> bp = new UpdateBPTemplate<AggSoOrderBuildHVO>(
				BuildPluginPoint.UPDATE);
		// ִ��ǰ����
		this.addBeforeRule(bp.getAroundProcesser());
		// ִ�к����
		this.addAfterRule(bp.getAroundProcesser());
		return bp.update(bills, originBills);
	}

	private void addAfterRule(CompareAroundProcesser<AggSoOrderBuildHVO> processer) {
		// TODO �����
		IRule<AggSoOrderBuildHVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillCodeCheckRule();
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule).setCbilltype("BUIL");
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule)
				.setCodeItem("vbillno");
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule)
				.setGroupItem("pk_group");
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule).setOrgItem("pk_org");
		processer.addAfterRule(rule);
		
		IRule<AggSoOrderBuildHVO> rule1 = new ReWriteSaleOrderRule();
		processer.addAfterRule(rule1);

	}

	private void addBeforeRule(CompareAroundProcesser<AggSoOrderBuildHVO> processer) {
		// TODO ǰ����
		IRule<AggSoOrderBuildHVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.FillUpdateDataRule();
		processer.addBeforeRule(rule);
		nc.impl.pubapp.pattern.rule.ICompareRule<AggSoOrderBuildHVO> ruleCom = new nc.bs.pubapp.pub.rule.UpdateBillCodeRule();
		((nc.bs.pubapp.pub.rule.UpdateBillCodeRule) ruleCom)
				.setCbilltype("BUIL");
		((nc.bs.pubapp.pub.rule.UpdateBillCodeRule) ruleCom)
				.setCodeItem("vbillno");
		((nc.bs.pubapp.pub.rule.UpdateBillCodeRule) ruleCom)
				.setGroupItem("pk_group");
		((nc.bs.pubapp.pub.rule.UpdateBillCodeRule) ruleCom)
				.setOrgItem("pk_org");
		processer.addBeforeRule(ruleCom);
	}

}
