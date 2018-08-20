package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.ApproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.bhs.bhsquality.plugin.bpplugin.BhsqualityPluginPoint;
import nc.vo.bhs.bhsquality.AggBhsPoHeadVO;
import nc.itf.bhs.IBhsqualityMaintain;

public class N_QUAL_APPROVE extends AbstractPfAction<AggBhsPoHeadVO> {

	public N_QUAL_APPROVE() {
		super();
	}

	@Override
	protected CompareAroundProcesser<AggBhsPoHeadVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggBhsPoHeadVO> processor = new CompareAroundProcesser<AggBhsPoHeadVO>(
				BhsqualityPluginPoint.APPROVE);
		processor.addBeforeRule(new ApproveStatusCheckRule());
		return processor;
	}

	@Override
	protected AggBhsPoHeadVO[] processBP(Object userObj,
			AggBhsPoHeadVO[] clientFullVOs, AggBhsPoHeadVO[] originBills) {
		AggBhsPoHeadVO[] bills = null;
		IBhsqualityMaintain operator = NCLocator.getInstance().lookup(
				IBhsqualityMaintain.class);
		try {
			bills = operator.approve(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
