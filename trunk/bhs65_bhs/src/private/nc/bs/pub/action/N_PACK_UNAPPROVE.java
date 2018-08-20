package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.UnapproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.bhs.pack.plugin.bpplugin.PackPluginPoint;
import nc.vo.bhs.pack.AggSoOrderPackVO;
import nc.itf.bhs.IPackMaintain;

public class N_PACK_UNAPPROVE extends AbstractPfAction<AggSoOrderPackVO> {

	@Override
	protected CompareAroundProcesser<AggSoOrderPackVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggSoOrderPackVO> processor = new CompareAroundProcesser<AggSoOrderPackVO>(
				PackPluginPoint.UNAPPROVE);
		// TODO �ڴ˴����ǰ�����
		processor.addBeforeRule(new UnapproveStatusCheckRule());

		return processor;
	}

	@Override
	protected AggSoOrderPackVO[] processBP(Object userObj,
			AggSoOrderPackVO[] clientFullVOs, AggSoOrderPackVO[] originBills) {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AggSoOrderPackVO[] bills = null;
		try {
			IPackMaintain operator = NCLocator.getInstance()
					.lookup(IPackMaintain.class);
			bills = operator.unapprove(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
