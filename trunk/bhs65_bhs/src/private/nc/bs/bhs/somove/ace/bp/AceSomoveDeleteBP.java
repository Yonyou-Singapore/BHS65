package nc.bs.bhs.somove.ace.bp;

import nc.bs.bhs.somove.plugin.bpplugin.SomovePluginPoint;
import nc.vo.bhs.somove.AggSoMoveHVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * ��׼����ɾ��BP
 */
public class AceSomoveDeleteBP {

	public void delete(AggSoMoveHVO[] bills) {

		DeleteBPTemplate<AggSoMoveHVO> bp = new DeleteBPTemplate<AggSoMoveHVO>(
				SomovePluginPoint.DELETE);
		// ����ִ��ǰ����
		this.addBeforeRule(bp.getAroundProcesser());
		// ����ִ�к�ҵ�����
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	private void addBeforeRule(AroundProcesser<AggSoMoveHVO> processer) {
		// TODO ǰ����
		IRule<AggSoMoveHVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}

	/**
	 * ɾ����ҵ�����
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<AggSoMoveHVO> processer) {
		// TODO �����

	}
}
