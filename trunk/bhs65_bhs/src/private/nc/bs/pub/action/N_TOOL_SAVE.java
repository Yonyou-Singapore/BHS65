package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.CommitStatusCheckRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.bhs.bhstools.plugin.bpplugin.BhstoolsPluginPoint;
import nc.vo.bhs.bhstools.AggBhsToolsHeadVO;
import nc.itf.bhs.IBhstoolsMaintain;

public class N_TOOL_SAVE extends AbstractPfAction<AggBhsToolsHeadVO> {

	protected CompareAroundProcesser<AggBhsToolsHeadVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggBhsToolsHeadVO> processor = new CompareAroundProcesser<AggBhsToolsHeadVO>(
				BhstoolsPluginPoint.SEND_APPROVE);
		// TODO 在此处添加审核前后规则
		IRule<AggBhsToolsHeadVO> rule = new CommitStatusCheckRule();
		processor.addBeforeRule(rule);
		return processor;
	}

	@Override
	protected AggBhsToolsHeadVO[] processBP(Object userObj,
			AggBhsToolsHeadVO[] clientFullVOs, AggBhsToolsHeadVO[] originBills) {
		IBhstoolsMaintain operator = NCLocator.getInstance().lookup(
				IBhstoolsMaintain.class);
		AggBhsToolsHeadVO[] bills = null;
		try {
			bills = operator.save(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
