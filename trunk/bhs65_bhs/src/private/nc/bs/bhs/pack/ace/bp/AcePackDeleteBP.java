package nc.bs.bhs.pack.ace.bp;

import nc.bs.bhs.pack.plugin.bpplugin.PackPluginPoint;
import nc.vo.bhs.pack.AggSoOrderPackVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * ��׼����ɾ��BP
 */
public class AcePackDeleteBP {

	public void delete(AggSoOrderPackVO[] bills) {

		DeleteBPTemplate<AggSoOrderPackVO> bp = new DeleteBPTemplate<AggSoOrderPackVO>(
				PackPluginPoint.DELETE);
		// ����ִ��ǰ����
		this.addBeforeRule(bp.getAroundProcesser());
		// ����ִ�к�ҵ�����
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	private void addBeforeRule(AroundProcesser<AggSoOrderPackVO> processer) {
		// TODO ǰ����
		IRule<AggSoOrderPackVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}

	/**
	 * ɾ����ҵ�����
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<AggSoOrderPackVO> processer) {
		// TODO �����

	}
}
