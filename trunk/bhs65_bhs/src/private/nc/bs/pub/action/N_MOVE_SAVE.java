package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.CommitStatusCheckRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.bhs.somove.plugin.bpplugin.SomovePluginPoint;
import nc.vo.bhs.somove.AggSoMoveHVO;
import nc.itf.bhs.ISomoveMaintain;

public class N_MOVE_SAVE extends AbstractPfAction<AggSoMoveHVO> {

	protected CompareAroundProcesser<AggSoMoveHVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggSoMoveHVO> processor = new CompareAroundProcesser<AggSoMoveHVO>(
				SomovePluginPoint.SEND_APPROVE);
		// TODO 在此处添加审核前后规则
		IRule<AggSoMoveHVO> rule = new CommitStatusCheckRule();
		processor.addBeforeRule(rule);
		return processor;
	}

	@Override
	protected AggSoMoveHVO[] processBP(Object userObj,
			AggSoMoveHVO[] clientFullVOs, AggSoMoveHVO[] originBills) {
		ISomoveMaintain operator = NCLocator.getInstance().lookup(
				ISomoveMaintain.class);
		AggSoMoveHVO[] bills = null;
		try {
			bills = operator.save(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
