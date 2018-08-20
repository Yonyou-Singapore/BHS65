package nc.bs.bhs.sotruck.ace.bp;

import nc.bs.bhs.sotruck.plugin.bpplugin.SotruckPluginPoint;
import nc.vo.bhs.sotruck.AggSoTruckHVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * 标准单据删除BP
 */
public class AceSotruckDeleteBP {

	public void delete(AggSoTruckHVO[] bills) {

		DeleteBPTemplate<AggSoTruckHVO> bp = new DeleteBPTemplate<AggSoTruckHVO>(
				SotruckPluginPoint.DELETE);
		// 增加执行前规则
		this.addBeforeRule(bp.getAroundProcesser());
		// 增加执行后业务规则
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	private void addBeforeRule(AroundProcesser<AggSoTruckHVO> processer) {
		// TODO 前规则
		IRule<AggSoTruckHVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}

	/**
	 * 删除后业务规则
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<AggSoTruckHVO> processer) {
		// TODO 后规则

	}
}
