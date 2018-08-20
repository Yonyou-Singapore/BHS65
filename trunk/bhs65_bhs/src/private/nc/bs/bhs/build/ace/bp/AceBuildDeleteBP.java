package nc.bs.bhs.build.ace.bp;

import nc.bs.bhs.build.plugin.bpplugin.BuildPluginPoint;
import nc.vo.bhs.build.AggSoOrderBuildHVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * ��׼����ɾ��BP
 */
public class AceBuildDeleteBP {

	public void delete(AggSoOrderBuildHVO[] bills) {

		DeleteBPTemplate<AggSoOrderBuildHVO> bp = new DeleteBPTemplate<AggSoOrderBuildHVO>(
				BuildPluginPoint.DELETE);
		// ����ִ��ǰ����
		this.addBeforeRule(bp.getAroundProcesser());
		// ����ִ�к�ҵ�����
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	private void addBeforeRule(AroundProcesser<AggSoOrderBuildHVO> processer) {
		// TODO ǰ����
		IRule<AggSoOrderBuildHVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}

	/**
	 * ɾ����ҵ�����
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<AggSoOrderBuildHVO> processer) {
		// TODO �����

	}
}
