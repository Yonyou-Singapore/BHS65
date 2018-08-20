package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.bhs.bhstools.plugin.bpplugin.BhstoolsPluginPoint;
import nc.vo.bhs.bhstools.AggBhsToolsHeadVO;
import nc.itf.bhs.IBhstoolsMaintain;

public class N_TOOL_SAVEBASE extends AbstractPfAction<AggBhsToolsHeadVO> {

	@Override
	protected CompareAroundProcesser<AggBhsToolsHeadVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggBhsToolsHeadVO> processor = null;
		AggBhsToolsHeadVO[] clientFullVOs = (AggBhsToolsHeadVO[]) this.getVos();
		if (!StringUtil.isEmptyWithTrim(clientFullVOs[0].getParentVO()
				.getPrimaryKey())) {
			processor = new CompareAroundProcesser<AggBhsToolsHeadVO>(
					BhstoolsPluginPoint.SCRIPT_UPDATE);
		} else {
			processor = new CompareAroundProcesser<AggBhsToolsHeadVO>(
					BhstoolsPluginPoint.SCRIPT_INSERT);
		}
		// TODO 在此处添加前后规则
		IRule<AggBhsToolsHeadVO> rule = null;

		return processor;
	}

	@Override
	protected AggBhsToolsHeadVO[] processBP(Object userObj,
			AggBhsToolsHeadVO[] clientFullVOs, AggBhsToolsHeadVO[] originBills) {

		AggBhsToolsHeadVO[] bills = null;
		try {
			IBhstoolsMaintain operator = NCLocator.getInstance()
					.lookup(IBhstoolsMaintain.class);
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
