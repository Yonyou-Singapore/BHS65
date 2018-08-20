package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.bhs.pack.plugin.bpplugin.PackPluginPoint;
import nc.vo.bhs.pack.AggSoOrderPackVO;
import nc.itf.bhs.IPackMaintain;

public class N_PACK_SAVEBASE extends AbstractPfAction<AggSoOrderPackVO> {

	@Override
	protected CompareAroundProcesser<AggSoOrderPackVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggSoOrderPackVO> processor = null;
		AggSoOrderPackVO[] clientFullVOs = (AggSoOrderPackVO[]) this.getVos();
		if (!StringUtil.isEmptyWithTrim(clientFullVOs[0].getParentVO()
				.getPrimaryKey())) {
			processor = new CompareAroundProcesser<AggSoOrderPackVO>(
					PackPluginPoint.SCRIPT_UPDATE);
		} else {
			processor = new CompareAroundProcesser<AggSoOrderPackVO>(
					PackPluginPoint.SCRIPT_INSERT);
		}
		// TODO 在此处添加前后规则
		IRule<AggSoOrderPackVO> rule = null;

		return processor;
	}

	@Override
	protected AggSoOrderPackVO[] processBP(Object userObj,
			AggSoOrderPackVO[] clientFullVOs, AggSoOrderPackVO[] originBills) {

		AggSoOrderPackVO[] bills = null;
		try {
			IPackMaintain operator = NCLocator.getInstance()
					.lookup(IPackMaintain.class);
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
