package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.ApproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.bhs.bhstools.plugin.bpplugin.BhstoolsPluginPoint;
import nc.vo.bhs.bhstools.AggBhsToolsHeadVO;
import nc.itf.bhs.IBhstoolsMaintain;

public class N_TOOL_APPROVE extends AbstractPfAction<AggBhsToolsHeadVO> {

	public N_TOOL_APPROVE() {
		super();
	}

	@Override
	protected CompareAroundProcesser<AggBhsToolsHeadVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggBhsToolsHeadVO> processor = new CompareAroundProcesser<AggBhsToolsHeadVO>(
				BhstoolsPluginPoint.APPROVE);
		processor.addBeforeRule(new ApproveStatusCheckRule());
		return processor;
	}

	@Override
	protected AggBhsToolsHeadVO[] processBP(Object userObj,
			AggBhsToolsHeadVO[] clientFullVOs, AggBhsToolsHeadVO[] originBills) {
		AggBhsToolsHeadVO[] bills = null;
		IBhstoolsMaintain operator = NCLocator.getInstance().lookup(
				IBhstoolsMaintain.class);
		try {
			bills = operator.approve(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
