package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.bhs.bhsdo.plugin.bpplugin.BhsdoPluginPoint;
import nc.vo.bhs.bhsdo.AggBhsDoVO;
import nc.itf.bhs.IBhsdoMaintain;

public class N_DO_SAVEBASE extends AbstractPfAction<AggBhsDoVO> {

	@Override
	protected CompareAroundProcesser<AggBhsDoVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggBhsDoVO> processor = null;
		AggBhsDoVO[] clientFullVOs = (AggBhsDoVO[]) this.getVos();
		if (!StringUtil.isEmptyWithTrim(clientFullVOs[0].getParentVO()
				.getPrimaryKey())) {
			processor = new CompareAroundProcesser<AggBhsDoVO>(
					BhsdoPluginPoint.SCRIPT_UPDATE);
		} else {
			processor = new CompareAroundProcesser<AggBhsDoVO>(
					BhsdoPluginPoint.SCRIPT_INSERT);
		}
		// TODO 在此处添加前后规则
		IRule<AggBhsDoVO> rule = null;

		return processor;
	}

	@Override
	protected AggBhsDoVO[] processBP(Object userObj,
			AggBhsDoVO[] clientFullVOs, AggBhsDoVO[] originBills) {

		AggBhsDoVO[] bills = null;
		try {
			IBhsdoMaintain operator = NCLocator.getInstance()
					.lookup(IBhsdoMaintain.class);
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
