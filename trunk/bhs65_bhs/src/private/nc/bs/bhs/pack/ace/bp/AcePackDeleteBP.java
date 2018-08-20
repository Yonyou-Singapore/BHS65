package nc.bs.bhs.pack.ace.bp;

import nc.bs.bhs.pack.plugin.bpplugin.PackPluginPoint;
import nc.vo.bhs.pack.AggSoOrderPackVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * 标准单据删除BP
 */
public class AcePackDeleteBP {

	public void delete(AggSoOrderPackVO[] bills) {

		DeleteBPTemplate<AggSoOrderPackVO> bp = new DeleteBPTemplate<AggSoOrderPackVO>(
				PackPluginPoint.DELETE);
		// 增加执行前规则
		this.addBeforeRule(bp.getAroundProcesser());
		// 增加执行后业务规则
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	private void addBeforeRule(AroundProcesser<AggSoOrderPackVO> processer) {
		// TODO 前规则
		IRule<AggSoOrderPackVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}

	/**
	 * 删除后业务规则
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<AggSoOrderPackVO> processer) {
		// TODO 后规则

	}
}
