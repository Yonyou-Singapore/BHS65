package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.UnapproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.bhs.build.plugin.bpplugin.BuildPluginPoint;
import nc.vo.bhs.build.AggSoOrderBuildHVO;
import nc.itf.bhs.IBuildMaintain;

public class N_BUIL_UNAPPROVE extends AbstractPfAction<AggSoOrderBuildHVO> {

	@Override
	protected CompareAroundProcesser<AggSoOrderBuildHVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggSoOrderBuildHVO> processor = new CompareAroundProcesser<AggSoOrderBuildHVO>(
				BuildPluginPoint.UNAPPROVE);
		// TODO 在此处添加前后规则
		processor.addBeforeRule(new UnapproveStatusCheckRule());

		return processor;
	}

	@Override
	protected AggSoOrderBuildHVO[] processBP(Object userObj,
			AggSoOrderBuildHVO[] clientFullVOs, AggSoOrderBuildHVO[] originBills) {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AggSoOrderBuildHVO[] bills = null;
		try {
			IBuildMaintain operator = NCLocator.getInstance()
					.lookup(IBuildMaintain.class);
			bills = operator.unapprove(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
