package nc.bs.bhs.build.ace.bp;

import nc.bs.bhs.build.plugin.bpplugin.BuildPluginPoint;
import nc.bs.bhs.build.rule.ReWriteSaleOrderRule;
import nc.impl.pubapp.pattern.data.bill.template.InsertBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.bhs.build.AggSoOrderBuildHVO;

/**
 * ��׼��������BP
 */
public class AceBuildInsertBP {

	public AggSoOrderBuildHVO[] insert(AggSoOrderBuildHVO[] bills) {

		InsertBPTemplate<AggSoOrderBuildHVO> bp = new InsertBPTemplate<AggSoOrderBuildHVO>(
				BuildPluginPoint.INSERT);
		this.addBeforeRule(bp.getAroundProcesser());
		this.addAfterRule(bp.getAroundProcesser());
		return bp.insert(bills);

	}

	/**
	 * ���������
	 * 
	 * @param processor
	 */
	private void addAfterRule(AroundProcesser<AggSoOrderBuildHVO> processor) {
		// TODO ���������
		IRule<AggSoOrderBuildHVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillCodeCheckRule();
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule).setCbilltype("BUIL");
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule)
				.setCodeItem("vbillno");
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule)
				.setGroupItem("pk_group");
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule).setOrgItem("pk_org");
		processor.addAfterRule(rule);
		
		IRule<AggSoOrderBuildHVO> rule1 = new ReWriteSaleOrderRule();
		processor.addAfterRule(rule1);
	}

	/**
	 * ����ǰ����
	 * 
	 * @param processor
	 */
	private void addBeforeRule(AroundProcesser<AggSoOrderBuildHVO> processer) {
		// TODO ����ǰ����
		IRule<AggSoOrderBuildHVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.FillInsertDataRule();
		processer.addBeforeRule(rule);
		rule = new nc.bs.pubapp.pub.rule.CreateBillCodeRule();
		((nc.bs.pubapp.pub.rule.CreateBillCodeRule) rule).setCbilltype("BUIL");
		((nc.bs.pubapp.pub.rule.CreateBillCodeRule) rule)
				.setCodeItem("vbillno");
		((nc.bs.pubapp.pub.rule.CreateBillCodeRule) rule)
				.setGroupItem("pk_group");
		((nc.bs.pubapp.pub.rule.CreateBillCodeRule) rule).setOrgItem("pk_org");
		processer.addBeforeRule(rule);
	}
}
