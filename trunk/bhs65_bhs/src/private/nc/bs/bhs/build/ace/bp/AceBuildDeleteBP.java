package nc.bs.bhs.build.ace.bp;

import nc.bs.bhs.build.plugin.bpplugin.BuildPluginPoint;
import nc.vo.bhs.build.AggSoOrderBuildHVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * 标准单据删除BP
 */
public class AceBuildDeleteBP {

	public void delete(AggSoOrderBuildHVO[] bills) {

		DeleteBPTemplate<AggSoOrderBuildHVO> bp = new DeleteBPTemplate<AggSoOrderBuildHVO>(
				BuildPluginPoint.DELETE);
		// 增加执行前规则
		this.addBeforeRule(bp.getAroundProcesser());
		// 增加执行后业务规则
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	private void addBeforeRule(AroundProcesser<AggSoOrderBuildHVO> processer) {
		// TODO 前规则
		IRule<AggSoOrderBuildHVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}

	/**
	 * 删除后业务规则
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<AggSoOrderBuildHVO> processer) {
		// TODO 后规则

	}
}
