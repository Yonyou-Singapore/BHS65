package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.UnapproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.bhs.somove.plugin.bpplugin.SomovePluginPoint;
import nc.vo.bhs.somove.AggSoMoveHVO;
import nc.itf.bhs.ISomoveMaintain;

public class N_MOVE_UNAPPROVE extends AbstractPfAction<AggSoMoveHVO> {

	@Override
	protected CompareAroundProcesser<AggSoMoveHVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggSoMoveHVO> processor = new CompareAroundProcesser<AggSoMoveHVO>(
				SomovePluginPoint.UNAPPROVE);
		// TODO 在此处添加前后规则
		processor.addBeforeRule(new UnapproveStatusCheckRule());

		return processor;
	}

	@Override
	protected AggSoMoveHVO[] processBP(Object userObj,
			AggSoMoveHVO[] clientFullVOs, AggSoMoveHVO[] originBills) {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AggSoMoveHVO[] bills = null;
		try {
			ISomoveMaintain operator = NCLocator.getInstance()
					.lookup(ISomoveMaintain.class);
			bills = operator.unapprove(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
