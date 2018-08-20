package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.UnapproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.bhs.bhsquality.plugin.bpplugin.BhsqualityPluginPoint;
import nc.vo.bhs.bhsquality.AggBhsPoHeadVO;
import nc.itf.bhs.IBhsqualityMaintain;

public class N_QUAL_UNAPPROVE extends AbstractPfAction<AggBhsPoHeadVO> {

	@Override
	protected CompareAroundProcesser<AggBhsPoHeadVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggBhsPoHeadVO> processor = new CompareAroundProcesser<AggBhsPoHeadVO>(
				BhsqualityPluginPoint.UNAPPROVE);
		// TODO �ڴ˴����ǰ�����
		processor.addBeforeRule(new UnapproveStatusCheckRule());

		return processor;
	}

	@Override
	protected AggBhsPoHeadVO[] processBP(Object userObj,
			AggBhsPoHeadVO[] clientFullVOs, AggBhsPoHeadVO[] originBills) {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AggBhsPoHeadVO[] bills = null;
		try {
			IBhsqualityMaintain operator = NCLocator.getInstance()
					.lookup(IBhsqualityMaintain.class);
			bills = operator.unapprove(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
