package nc.bs.bhs.bhsquality.ace.bp;

import nc.bs.bhs.bhsquality.plugin.bpplugin.BhsqualityPluginPoint;
import nc.vo.bhs.bhsquality.AggBhsPoHeadVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * ��׼����ɾ��BP
 */
public class AceBhsqualityDeleteBP {

	public void delete(AggBhsPoHeadVO[] bills) {

		DeleteBPTemplate<AggBhsPoHeadVO> bp = new DeleteBPTemplate<AggBhsPoHeadVO>(
				BhsqualityPluginPoint.DELETE);
		// ����ִ��ǰ����
		this.addBeforeRule(bp.getAroundProcesser());
		// ����ִ�к�ҵ�����
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	private void addBeforeRule(AroundProcesser<AggBhsPoHeadVO> processer) {
		// TODO ǰ����
		IRule<AggBhsPoHeadVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}

	/**
	 * ɾ����ҵ�����
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<AggBhsPoHeadVO> processer) {
		// TODO �����

	}
}
