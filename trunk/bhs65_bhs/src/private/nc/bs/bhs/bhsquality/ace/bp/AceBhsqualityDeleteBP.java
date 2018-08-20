package nc.bs.bhs.bhsquality.ace.bp;

import nc.bs.bhs.bhsquality.plugin.bpplugin.BhsqualityPluginPoint;
import nc.vo.bhs.bhsquality.AggBhsPoHeadVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * 标准单据删除BP
 */
public class AceBhsqualityDeleteBP {

	public void delete(AggBhsPoHeadVO[] bills) {

		DeleteBPTemplate<AggBhsPoHeadVO> bp = new DeleteBPTemplate<AggBhsPoHeadVO>(
				BhsqualityPluginPoint.DELETE);
		// 增加执行前规则
		this.addBeforeRule(bp.getAroundProcesser());
		// 增加执行后业务规则
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	private void addBeforeRule(AroundProcesser<AggBhsPoHeadVO> processer) {
		// TODO 前规则
		IRule<AggBhsPoHeadVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}

	/**
	 * 删除后业务规则
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<AggBhsPoHeadVO> processer) {
		// TODO 后规则

	}
}
