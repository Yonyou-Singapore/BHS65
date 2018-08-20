package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.bhs.bhsquality.plugin.bpplugin.BhsqualityPluginPoint;
import nc.vo.bhs.bhsquality.AggBhsPoHeadVO;
import nc.itf.bhs.IBhsqualityMaintain;

public class N_QUAL_DELETE extends AbstractPfAction<AggBhsPoHeadVO> {

	@Override
	protected CompareAroundProcesser<AggBhsPoHeadVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggBhsPoHeadVO> processor = new CompareAroundProcesser<AggBhsPoHeadVO>(
				BhsqualityPluginPoint.SCRIPT_DELETE);
		// TODO 在此处添加前后规则
		return processor;
	}

	@Override
	protected AggBhsPoHeadVO[] processBP(Object userObj,
			AggBhsPoHeadVO[] clientFullVOs, AggBhsPoHeadVO[] originBills) {
		IBhsqualityMaintain operator = NCLocator.getInstance().lookup(
				IBhsqualityMaintain.class);
		try {
			operator.delete(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return clientFullVOs;
	}

}
