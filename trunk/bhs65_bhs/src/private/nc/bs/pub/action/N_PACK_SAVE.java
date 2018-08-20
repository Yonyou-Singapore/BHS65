package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.CommitStatusCheckRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.bhs.pack.plugin.bpplugin.PackPluginPoint;
import nc.vo.bhs.pack.AggSoOrderPackVO;
import nc.itf.bhs.IPackMaintain;

public class N_PACK_SAVE extends AbstractPfAction<AggSoOrderPackVO> {

	protected CompareAroundProcesser<AggSoOrderPackVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggSoOrderPackVO> processor = new CompareAroundProcesser<AggSoOrderPackVO>(
				PackPluginPoint.SEND_APPROVE);
		// TODO 在此处添加审核前后规则
		IRule<AggSoOrderPackVO> rule = new CommitStatusCheckRule();
		processor.addBeforeRule(rule);
		return processor;
	}

	@Override
	protected AggSoOrderPackVO[] processBP(Object userObj,
			AggSoOrderPackVO[] clientFullVOs, AggSoOrderPackVO[] originBills) {
		IPackMaintain operator = NCLocator.getInstance().lookup(
				IPackMaintain.class);
		AggSoOrderPackVO[] bills = null;
		try {
			bills = operator.save(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
