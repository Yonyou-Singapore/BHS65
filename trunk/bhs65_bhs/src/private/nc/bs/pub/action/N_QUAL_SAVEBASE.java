package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.bhs.bhsquality.plugin.bpplugin.BhsqualityPluginPoint;
import nc.vo.bhs.bhsquality.AggBhsPoHeadVO;
import nc.itf.bhs.IBhsqualityMaintain;

public class N_QUAL_SAVEBASE extends AbstractPfAction<AggBhsPoHeadVO> {

	@Override
	protected CompareAroundProcesser<AggBhsPoHeadVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggBhsPoHeadVO> processor = null;
		AggBhsPoHeadVO[] clientFullVOs = (AggBhsPoHeadVO[]) this.getVos();
		if (!StringUtil.isEmptyWithTrim(clientFullVOs[0].getParentVO()
				.getPrimaryKey())) {
			processor = new CompareAroundProcesser<AggBhsPoHeadVO>(
					BhsqualityPluginPoint.SCRIPT_UPDATE);
		} else {
			processor = new CompareAroundProcesser<AggBhsPoHeadVO>(
					BhsqualityPluginPoint.SCRIPT_INSERT);
		}
		// TODO 在此处添加前后规则
		IRule<AggBhsPoHeadVO> rule = null;

		return processor;
	}

	@Override
	protected AggBhsPoHeadVO[] processBP(Object userObj,
			AggBhsPoHeadVO[] clientFullVOs, AggBhsPoHeadVO[] originBills) {

		AggBhsPoHeadVO[] bills = null;
		try {
			IBhsqualityMaintain operator = NCLocator.getInstance()
					.lookup(IBhsqualityMaintain.class);
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
