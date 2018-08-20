package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.UncommitStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.bhs.bhsquality.plugin.bpplugin.BhsqualityPluginPoint;
import nc.vo.bhs.bhsquality.AggBhsPoHeadVO;
import nc.itf.bhs.IBhsqualityMaintain;

public class N_QUAL_UNSAVEBILL extends AbstractPfAction<AggBhsPoHeadVO> {

	@Override
	protected CompareAroundProcesser<AggBhsPoHeadVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggBhsPoHeadVO> processor = new CompareAroundProcesser<AggBhsPoHeadVO>(
				BhsqualityPluginPoint.UNSEND_APPROVE);
		// TODO 在此处添加前后规则
		processor.addBeforeRule(new UncommitStatusCheckRule());

		return processor;
	}

	@Override
	protected AggBhsPoHeadVO[] processBP(Object userObj,
			AggBhsPoHeadVO[] clientFullVOs, AggBhsPoHeadVO[] originBills) {
		IBhsqualityMaintain operator = NCLocator.getInstance().lookup(
				IBhsqualityMaintain.class);
		AggBhsPoHeadVO[] bills = null;
		try {
			bills = operator.unsave(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
