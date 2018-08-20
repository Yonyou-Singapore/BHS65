package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.bhs.sostore.plugin.bpplugin.SostorePluginPoint;
import nc.vo.bhs.sostore.AggSoStoreHVO;
import nc.itf.bhs.ISostoreMaintain;

public class N_STOR_SAVEBASE extends AbstractPfAction<AggSoStoreHVO> {

	@Override
	protected CompareAroundProcesser<AggSoStoreHVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggSoStoreHVO> processor = null;
		AggSoStoreHVO[] clientFullVOs = (AggSoStoreHVO[]) this.getVos();
		if (!StringUtil.isEmptyWithTrim(clientFullVOs[0].getParentVO()
				.getPrimaryKey())) {
			processor = new CompareAroundProcesser<AggSoStoreHVO>(
					SostorePluginPoint.SCRIPT_UPDATE);
		} else {
			processor = new CompareAroundProcesser<AggSoStoreHVO>(
					SostorePluginPoint.SCRIPT_INSERT);
		}
		// TODO 在此处添加前后规则
		IRule<AggSoStoreHVO> rule = null;

		return processor;
	}

	@Override
	protected AggSoStoreHVO[] processBP(Object userObj,
			AggSoStoreHVO[] clientFullVOs, AggSoStoreHVO[] originBills) {

		AggSoStoreHVO[] bills = null;
		try {
			ISostoreMaintain operator = NCLocator.getInstance()
					.lookup(ISostoreMaintain.class);
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
