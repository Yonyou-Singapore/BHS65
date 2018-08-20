package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.UncommitStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.bhs.bhstools.plugin.bpplugin.BhstoolsPluginPoint;
import nc.vo.bhs.bhstools.AggBhsToolsHeadVO;
import nc.itf.bhs.IBhstoolsMaintain;

public class N_TOOL_UNSAVEBILL extends AbstractPfAction<AggBhsToolsHeadVO> {

	@Override
	protected CompareAroundProcesser<AggBhsToolsHeadVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggBhsToolsHeadVO> processor = new CompareAroundProcesser<AggBhsToolsHeadVO>(
				BhstoolsPluginPoint.UNSEND_APPROVE);
		// TODO 在此处添加前后规则
		processor.addBeforeRule(new UncommitStatusCheckRule());

		return processor;
	}

	@Override
	protected AggBhsToolsHeadVO[] processBP(Object userObj,
			AggBhsToolsHeadVO[] clientFullVOs, AggBhsToolsHeadVO[] originBills) {
		IBhstoolsMaintain operator = NCLocator.getInstance().lookup(
				IBhstoolsMaintain.class);
		AggBhsToolsHeadVO[] bills = null;
		try {
			bills = operator.unsave(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
