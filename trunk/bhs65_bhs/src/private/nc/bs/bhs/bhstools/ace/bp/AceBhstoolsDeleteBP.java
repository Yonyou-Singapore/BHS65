package nc.bs.bhs.bhstools.ace.bp;

import nc.bs.bhs.bhstools.plugin.bpplugin.BhstoolsPluginPoint;
import nc.vo.bhs.bhstools.AggBhsToolsHeadVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * ��׼����ɾ��BP
 */
public class AceBhstoolsDeleteBP {

	public void delete(AggBhsToolsHeadVO[] bills) {

		DeleteBPTemplate<AggBhsToolsHeadVO> bp = new DeleteBPTemplate<AggBhsToolsHeadVO>(
				BhstoolsPluginPoint.DELETE);
		// ����ִ��ǰ����
		this.addBeforeRule(bp.getAroundProcesser());
		// ����ִ�к�ҵ�����
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	private void addBeforeRule(AroundProcesser<AggBhsToolsHeadVO> processer) {
		// TODO ǰ����
		IRule<AggBhsToolsHeadVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}

	/**
	 * ɾ����ҵ�����
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<AggBhsToolsHeadVO> processer) {
		// TODO �����

	}
}
