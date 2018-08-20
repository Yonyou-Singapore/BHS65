package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.bhs.somove.plugin.bpplugin.SomovePluginPoint;
import nc.vo.bhs.somove.AggSoMoveHVO;
import nc.itf.bhs.ISomoveMaintain;

public class N_MOVE_DELETE extends AbstractPfAction<AggSoMoveHVO> {

	@Override
	protected CompareAroundProcesser<AggSoMoveHVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggSoMoveHVO> processor = new CompareAroundProcesser<AggSoMoveHVO>(
				SomovePluginPoint.SCRIPT_DELETE);
		// TODO 在此处添加前后规则
		return processor;
	}

	@Override
	protected AggSoMoveHVO[] processBP(Object userObj,
			AggSoMoveHVO[] clientFullVOs, AggSoMoveHVO[] originBills) {
		ISomoveMaintain operator = NCLocator.getInstance().lookup(
				ISomoveMaintain.class);
		try {
			operator.delete(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return clientFullVOs;
	}

}
