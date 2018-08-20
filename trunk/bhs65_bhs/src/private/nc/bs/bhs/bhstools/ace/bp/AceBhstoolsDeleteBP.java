package nc.bs.bhs.bhstools.ace.bp;

import nc.bs.bhs.bhstools.plugin.bpplugin.BhstoolsPluginPoint;
import nc.vo.bhs.bhstools.AggBhsToolsHeadVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * 标准单据删除BP
 */
public class AceBhstoolsDeleteBP {

	public void delete(AggBhsToolsHeadVO[] bills) {

		DeleteBPTemplate<AggBhsToolsHeadVO> bp = new DeleteBPTemplate<AggBhsToolsHeadVO>(
				BhstoolsPluginPoint.DELETE);
		// 增加执行前规则
		this.addBeforeRule(bp.getAroundProcesser());
		// 增加执行后业务规则
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	private void addBeforeRule(AroundProcesser<AggBhsToolsHeadVO> processer) {
		// TODO 前规则
		IRule<AggBhsToolsHeadVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}

	/**
	 * 删除后业务规则
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<AggBhsToolsHeadVO> processer) {
		// TODO 后规则

	}
}
