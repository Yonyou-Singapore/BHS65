package nc.bs.bhs.bhsdo.ace.bp;

import nc.bs.bhs.bhsdo.plugin.bpplugin.BhsdoPluginPoint;
import nc.bs.bhs.bhsdo.rule.ReWriteSaleOrderRule;
import nc.impl.pubapp.pattern.data.bill.template.UpdateBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.bhs.bhsdo.AggBhsDoVO;

/**
 * 修改保存的BP
 * 
 */
public class AceBhsdoUpdateBP {

	public AggBhsDoVO[] update(AggBhsDoVO[] bills,
			AggBhsDoVO[] originBills) {
		// 调用修改模板
		UpdateBPTemplate<AggBhsDoVO> bp = new UpdateBPTemplate<AggBhsDoVO>(
				BhsdoPluginPoint.UPDATE);
		// 执行前规则
		this.addBeforeRule(bp.getAroundProcesser());
		// 执行后规则
		this.addAfterRule(bp.getAroundProcesser());
		return bp.update(bills, originBills);
	}

	private void addAfterRule(CompareAroundProcesser<AggBhsDoVO> processer) {
		// TODO 后规则
		IRule<AggBhsDoVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillCodeCheckRule();
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule).setCbilltype("DO");
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule)
				.setCodeItem("vbillno");
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule)
				.setGroupItem("pk_group");
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule).setOrgItem("pk_org");
		processer.addAfterRule(rule);
		
		IRule<AggBhsDoVO> rule1 = new ReWriteSaleOrderRule();
		processer.addAfterRule(rule1);

	}

	private void addBeforeRule(CompareAroundProcesser<AggBhsDoVO> processer) {
		// TODO 前规则
		IRule<AggBhsDoVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.FillUpdateDataRule();
		processer.addBeforeRule(rule);
		nc.impl.pubapp.pattern.rule.ICompareRule<AggBhsDoVO> ruleCom = new nc.bs.pubapp.pub.rule.UpdateBillCodeRule();
		((nc.bs.pubapp.pub.rule.UpdateBillCodeRule) ruleCom)
				.setCbilltype("DO");
		((nc.bs.pubapp.pub.rule.UpdateBillCodeRule) ruleCom)
				.setCodeItem("vbillno");
		((nc.bs.pubapp.pub.rule.UpdateBillCodeRule) ruleCom)
				.setGroupItem("pk_group");
		((nc.bs.pubapp.pub.rule.UpdateBillCodeRule) ruleCom)
				.setOrgItem("pk_org");
		processer.addBeforeRule(ruleCom);
	}

}
