package nc.bs.bhs.bhsdo.ace.bp;

import nc.bs.bhs.bhsdo.plugin.bpplugin.BhsdoPluginPoint;
import nc.bs.bhs.bhsdo.rule.ReWriteSaleOrderRule;
import nc.vo.bhs.bhsdo.AggBhsDoVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * 标准单据删除BP
 */
public class AceBhsdoDeleteBP {

	public void delete(AggBhsDoVO[] bills) {

		DeleteBPTemplate<AggBhsDoVO> bp = new DeleteBPTemplate<AggBhsDoVO>(
				BhsdoPluginPoint.DELETE);
		// 增加执行前规则
		this.addBeforeRule(bp.getAroundProcesser());
		// 增加执行后业务规则
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	private void addBeforeRule(AroundProcesser<AggBhsDoVO> processer) {
		// TODO 前规则
		IRule<AggBhsDoVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}

	/**
	 * 删除后业务规则
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<AggBhsDoVO> processer) {
		// TODO 后规则
		processer.addAfterRule(new ReWriteSaleOrderRule());
	}
}
