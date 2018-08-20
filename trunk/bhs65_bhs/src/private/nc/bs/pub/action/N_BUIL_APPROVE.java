package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.ApproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.bhs.build.plugin.bpplugin.BuildPluginPoint;
import nc.vo.bhs.build.AggSoOrderBuildHVO;
import nc.itf.bhs.IBuildMaintain;

public class N_BUIL_APPROVE extends AbstractPfAction<AggSoOrderBuildHVO> {

	public N_BUIL_APPROVE() {
		super();
	}

	@Override
	protected CompareAroundProcesser<AggSoOrderBuildHVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggSoOrderBuildHVO> processor = new CompareAroundProcesser<AggSoOrderBuildHVO>(
				BuildPluginPoint.APPROVE);
		processor.addBeforeRule(new ApproveStatusCheckRule());
		return processor;
	}

	@Override
	protected AggSoOrderBuildHVO[] processBP(Object userObj,
			AggSoOrderBuildHVO[] clientFullVOs, AggSoOrderBuildHVO[] originBills) {
		AggSoOrderBuildHVO[] bills = null;
		IBuildMaintain operator = NCLocator.getInstance().lookup(
				IBuildMaintain.class);
		try {
			bills = operator.approve(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
