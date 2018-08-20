package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.UnapproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.bhs.sostore.plugin.bpplugin.SostorePluginPoint;
import nc.vo.bhs.sostore.AggSoStoreHVO;
import nc.itf.bhs.ISostoreMaintain;

public class N_STOR_UNAPPROVE extends AbstractPfAction<AggSoStoreHVO> {

	@Override
	protected CompareAroundProcesser<AggSoStoreHVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggSoStoreHVO> processor = new CompareAroundProcesser<AggSoStoreHVO>(
				SostorePluginPoint.UNAPPROVE);
		// TODO 在此处添加前后规则
		processor.addBeforeRule(new UnapproveStatusCheckRule());

		return processor;
	}

	@Override
	protected AggSoStoreHVO[] processBP(Object userObj,
			AggSoStoreHVO[] clientFullVOs, AggSoStoreHVO[] originBills) {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AggSoStoreHVO[] bills = null;
		try {
			ISostoreMaintain operator = NCLocator.getInstance()
					.lookup(ISostoreMaintain.class);
			bills = operator.unapprove(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
