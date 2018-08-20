package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.bhs.somove.plugin.bpplugin.SomovePluginPoint;
import nc.vo.bhs.somove.AggSoMoveHVO;
import nc.itf.bhs.ISomoveMaintain;

public class N_MOVE_SAVEBASE extends AbstractPfAction<AggSoMoveHVO> {

	@Override
	protected CompareAroundProcesser<AggSoMoveHVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggSoMoveHVO> processor = null;
		AggSoMoveHVO[] clientFullVOs = (AggSoMoveHVO[]) this.getVos();
		if (!StringUtil.isEmptyWithTrim(clientFullVOs[0].getParentVO()
				.getPrimaryKey())) {
			processor = new CompareAroundProcesser<AggSoMoveHVO>(
					SomovePluginPoint.SCRIPT_UPDATE);
		} else {
			processor = new CompareAroundProcesser<AggSoMoveHVO>(
					SomovePluginPoint.SCRIPT_INSERT);
		}
		// TODO 在此处添加前后规则
		IRule<AggSoMoveHVO> rule = null;

		return processor;
	}

	@Override
	protected AggSoMoveHVO[] processBP(Object userObj,
			AggSoMoveHVO[] clientFullVOs, AggSoMoveHVO[] originBills) {

		AggSoMoveHVO[] bills = null;
		try {
			ISomoveMaintain operator = NCLocator.getInstance()
					.lookup(ISomoveMaintain.class);
			if (!StringUtil.isEmptyWithTrim(clientFullVOs[0].getParentVO()
					.getPrimaryKey())) {
				bills = operator.update(clientFullVOs, originBills);
			} else {
				bills = operator.insert(clientFullVOs, originBills);
			}
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}
}
