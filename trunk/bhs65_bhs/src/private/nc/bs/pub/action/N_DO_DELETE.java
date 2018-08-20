package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.bhs.bhsdo.plugin.bpplugin.BhsdoPluginPoint;
import nc.vo.bhs.bhsdo.AggBhsDoVO;
import nc.itf.bhs.IBhsdoMaintain;

public class N_DO_DELETE extends AbstractPfAction<AggBhsDoVO> {

	@Override
	protected CompareAroundProcesser<AggBhsDoVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggBhsDoVO> processor = new CompareAroundProcesser<AggBhsDoVO>(
				BhsdoPluginPoint.SCRIPT_DELETE);
		// TODO 在此处添加前后规则
		return processor;
	}

	@Override
	protected AggBhsDoVO[] processBP(Object userObj,
			AggBhsDoVO[] clientFullVOs, AggBhsDoVO[] originBills) {
		IBhsdoMaintain operator = NCLocator.getInstance().lookup(
				IBhsdoMaintain.class);
		try {
			operator.delete(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return clientFullVOs;
	}

}
