package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.CommitStatusCheckRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.bhs.bhsdo.plugin.bpplugin.BhsdoPluginPoint;
import nc.vo.bhs.bhsdo.AggBhsDoVO;
import nc.itf.bhs.IBhsdoMaintain;

public class N_DO_SAVE extends AbstractPfAction<AggBhsDoVO> {

	protected CompareAroundProcesser<AggBhsDoVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggBhsDoVO> processor = new CompareAroundProcesser<AggBhsDoVO>(
				BhsdoPluginPoint.SEND_APPROVE);
		// TODO 在此处添加审核前后规则
		IRule<AggBhsDoVO> rule = new CommitStatusCheckRule();
		processor.addBeforeRule(rule);
		return processor;
	}

	@Override
	protected AggBhsDoVO[] processBP(Object userObj,
			AggBhsDoVO[] clientFullVOs, AggBhsDoVO[] originBills) {
		IBhsdoMaintain operator = NCLocator.getInstance().lookup(
				IBhsdoMaintain.class);
		AggBhsDoVO[] bills = null;
		try {
			bills = operator.save(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
