package nc.bs.bhs.sotruck.ace.bp;

import nc.bs.bhs.sotruck.plugin.bpplugin.SotruckPluginPoint;
import nc.bs.bhs.sotruck.rule.ReWriteSaleOrderRule;
import nc.impl.pubapp.pattern.data.bill.template.UpdateBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.bhs.sotruck.AggSoTruckHVO;

/**
 * �޸ı����BP
 * 
 */
public class AceSotruckUpdateBP {

	public AggSoTruckHVO[] update(AggSoTruckHVO[] bills,
			AggSoTruckHVO[] originBills) {
		// �����޸�ģ��
		UpdateBPTemplate<AggSoTruckHVO> bp = new UpdateBPTemplate<AggSoTruckHVO>(
				SotruckPluginPoint.UPDATE);
		// ִ��ǰ����
		this.addBeforeRule(bp.getAroundProcesser());
		// ִ�к����
		this.addAfterRule(bp.getAroundProcesser());
		return bp.update(bills, originBills);
	}

	private void addAfterRule(CompareAroundProcesser<AggSoTruckHVO> processer) {
		// TODO �����
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
		// TODO ǰ����
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
