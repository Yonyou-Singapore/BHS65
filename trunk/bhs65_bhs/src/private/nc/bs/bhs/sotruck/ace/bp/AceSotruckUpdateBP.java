package nc.bs.bhs.sotruck.ace.bp;

import nc.bs.bhs.sotruck.plugin.bpplugin.SotruckPluginPoint;
import nc.bs.bhs.sotruck.rule.ReWriteSaleOrderRule;
import nc.impl.pubapp.pattern.data.bill.template.UpdateBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.bhs.sotruck.AggSoTruckHVO;

/**
 * 修改保存的BP
 * 
 */
public class AceSotruckUpdateBP {

	public AggSoTruckHVO[] update(AggSoTruckHVO[] bills,
			AggSoTruckHVO[] originBills) {
		// 调用修改模板
		UpdateBPTemplate<AggSoTruckHVO> bp = new UpdateBPTemplate<AggSoTruckHVO>(
				SotruckPluginPoint.UPDATE);
		// 执行前规则
		this.addBeforeRule(bp.getAroundProcesser());
		// 执行后规则
		this.addAfterRule(bp.getAroundProcesser());
		return bp.update(bills, originBills);
	}

	private void addAfterRule(CompareAroundProcesser<AggSoTruckHVO> processer) {
		// TODO 后规则
		IRule<AggSoTruckHVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillCodeCheckRule();
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule).setCbilltype("TRUC");
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule)
				.setCodeItem("vbillno");
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule)
				.setGroupItem("pk_group");
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule).setOrgItem("pk_org");
		processer.addAfterRule(rule);
		
		IRule<AggSoTruckHVO> rule1 = new ReWriteSaleOrderRule();
		processer.addAfterRule(rule1);
	}

	private void addBeforeRule(CompareAroundProcesser<AggSoTruckHVO> processer) {
		// TODO 前规则
		IRule<AggSoTruckHVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.FillUpdateDataRule();
		processer.addBeforeRule(rule);
		nc.impl.pubapp.pattern.rule.ICompareRule<AggSoTruckHVO> ruleCom = new nc.bs.pubapp.pub.rule.UpdateBillCodeRule();
		((nc.bs.pubapp.pub.rule.UpdateBillCodeRule) ruleCom)
				.setCbilltype("TRUC");
		((nc.bs.pubapp.pub.rule.UpdateBillCodeRule) ruleCom)
				.setCodeItem("vbillno");
		((nc.bs.pubapp.pub.rule.UpdateBillCodeRule) ruleCom)
				.setGroupItem("pk_group");
		((nc.bs.pubapp.pub.rule.UpdateBillCodeRule) ruleCom)
				.setOrgItem("pk_org");
		processer.addBeforeRule(ruleCom);
	}

}
