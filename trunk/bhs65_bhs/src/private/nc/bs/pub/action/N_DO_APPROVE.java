package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.ApproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.bhs.bhsdo.plugin.bpplugin.BhsdoPluginPoint;
import nc.vo.bhs.bhsdo.AggBhsDoVO;
import nc.itf.bhs.IBhsdoMaintain;

public class N_DO_APPROVE extends AbstractPfAction<AggBhsDoVO> {

	public N_DO_APPROVE() {
		super();
	}

	@Override
	protected CompareAroundProcesser<AggBhsDoVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggBhsDoVO> processor = new CompareAroundProcesser<AggBhsDoVO>(
				BhsdoPluginPoint.APPROVE);
		processor.addBeforeRule(new ApproveStatusCheckRule());
		return processor;
	}

	@Override
	protected AggBhsDoVO[] processBP(Object userObj,
			AggBhsDoVO[] clientFullVOs, AggBhsDoVO[] originBills) {
		AggBhsDoVO[] bills = null;
		IBhsdoMaintain operator = NCLocator.getInstance().lookup(
				IBhsdoMaintain.class);
		try {
			bills = operator.approve(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
