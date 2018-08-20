package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.UnapproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.bhs.bhsdo.plugin.bpplugin.BhsdoPluginPoint;
import nc.vo.bhs.bhsdo.AggBhsDoVO;
import nc.itf.bhs.IBhsdoMaintain;

public class N_DO_UNAPPROVE extends AbstractPfAction<AggBhsDoVO> {

	@Override
	protected CompareAroundProcesser<AggBhsDoVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggBhsDoVO> processor = new CompareAroundProcesser<AggBhsDoVO>(
				BhsdoPluginPoint.UNAPPROVE);
		// TODO 在此处添加前后规则
		processor.addBeforeRule(new UnapproveStatusCheckRule());

		return processor;
	}

	@Override
	protected AggBhsDoVO[] processBP(Object userObj,
			AggBhsDoVO[] clientFullVOs, AggBhsDoVO[] originBills) {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AggBhsDoVO[] bills = null;
		try {
			IBhsdoMaintain operator = NCLocator.getInstance()
					.lookup(IBhsdoMaintain.class);
			bills = operator.unapprove(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
