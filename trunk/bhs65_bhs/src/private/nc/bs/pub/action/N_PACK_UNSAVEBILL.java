package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.UncommitStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.bhs.pack.plugin.bpplugin.PackPluginPoint;
import nc.vo.bhs.pack.AggSoOrderPackVO;
import nc.itf.bhs.IPackMaintain;

public class N_PACK_UNSAVEBILL extends AbstractPfAction<AggSoOrderPackVO> {

	@Override
	protected CompareAroundProcesser<AggSoOrderPackVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggSoOrderPackVO> processor = new CompareAroundProcesser<AggSoOrderPackVO>(
				PackPluginPoint.UNSEND_APPROVE);
		// TODO 在此处添加前后规则
		processor.addBeforeRule(new UncommitStatusCheckRule());

		return processor;
	}

	@Override
	protected AggSoOrderPackVO[] processBP(Object userObj,
			AggSoOrderPackVO[] clientFullVOs, AggSoOrderPackVO[] originBills) {
		IPackMaintain operator = NCLocator.getInstance().lookup(
				IPackMaintain.class);
		AggSoOrderPackVO[] bills = null;
		try {
			bills = operator.unsave(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
