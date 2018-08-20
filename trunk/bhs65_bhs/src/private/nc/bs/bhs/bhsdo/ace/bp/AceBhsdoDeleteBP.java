package nc.bs.bhs.bhsdo.ace.bp;

import nc.bs.bhs.bhsdo.plugin.bpplugin.BhsdoPluginPoint;
import nc.bs.bhs.bhsdo.rule.ReWriteSaleOrderRule;
import nc.vo.bhs.bhsdo.AggBhsDoVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * ��׼����ɾ��BP
 */
public class AceBhsdoDeleteBP {

	public void delete(AggBhsDoVO[] bills) {

		DeleteBPTemplate<AggBhsDoVO> bp = new DeleteBPTemplate<AggBhsDoVO>(
				BhsdoPluginPoint.DELETE);
		// ����ִ��ǰ����
		this.addBeforeRule(bp.getAroundProcesser());
		// ����ִ�к�ҵ�����
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	private void addBeforeRule(AroundProcesser<AggBhsDoVO> processer) {
		// TODO ǰ����
		IRule<AggBhsDoVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}

	/**
	 * ɾ����ҵ�����
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<AggBhsDoVO> processer) {
		// TODO �����
		processer.addAfterRule(new ReWriteSaleOrderRule());
	}
}
