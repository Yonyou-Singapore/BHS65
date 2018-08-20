package nc.bs.bhs.sostore.ace.bp;

import nc.bs.bhs.sostore.plugin.bpplugin.SostorePluginPoint;
import nc.bs.bhs.sostore.rule.ReWriteSaleOrderRule;
import nc.impl.pubapp.pattern.data.bill.template.InsertBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.bhs.sostore.AggSoStoreHVO;

/**
 * 标准单据新增BP
 */
public class AceSostoreInsertBP {

	public AggSoStoreHVO[] insert(AggSoStoreHVO[] bills) {

		InsertBPTemplate<AggSoStoreHVO> bp = new InsertBPTemplate<AggSoStoreHVO>(
				SostorePluginPoint.INSERT);
		this.addBeforeRule(bp.getAroundProcesser());
		this.addAfterRule(bp.getAroundProcesser());
		return bp.insert(bills);

	}

	/**
	 * 新增后规则
	 * 
	 * @param processor
	 */
	private void addAfterRule(AroundProcesser<AggSoStoreHVO> processor) {
		// TODO 新增后规则
		IRule<AggSoStoreHVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillCodeCheckRule();
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule).setCbilltype("STOR");
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule)
				.setCodeItem("vbillno");
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule)
				.setGroupItem("pk_group");
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule).setOrgItem("pk_org");
		processor.addAfterRule(rule);
		
		IRule<AggSoStoreHVO> rule1 = new ReWriteSaleOrderRule();
		processor.addAfterRule(rule1);
	}

	/**
	 * 新增前规则
	 * 
	 * @param processor
	 */
	private void addBeforeRule(AroundProcesser<AggSoStoreHVO> processer) {
		// TODO 新增前规则
		IRule<AggSoStoreHVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.FillInsertDataRule();
		processer.addBeforeRule(rule);
		rule = new nc.bs.pubapp.pub.rule.CreateBillCodeRule();
		((nc.bs.pubapp.pub.rule.CreateBillCodeRule) rule).setCbilltype("STOR");
		((nc.bs.pubapp.pub.rule.CreateBillCodeRule) rule)
				.setCodeItem("vbillno");
		((nc.bs.pubapp.pub.rule.CreateBillCodeRule) rule)
				.setGroupItem("pk_group");
		((nc.bs.pubapp.pub.rule.CreateBillCodeRule) rule).setOrgItem("pk_org");
		processer.addBeforeRule(rule);
	}
}
