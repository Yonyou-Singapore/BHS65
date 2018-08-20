package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.ApproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.bhs.sostore.plugin.bpplugin.SostorePluginPoint;
import nc.vo.bhs.sostore.AggSoStoreHVO;
import nc.itf.bhs.ISostoreMaintain;

public class N_STOR_APPROVE extends AbstractPfAction<AggSoStoreHVO> {

	public N_STOR_APPROVE() {
		super();
	}

	@Override
	protected CompareAroundProcesser<AggSoStoreHVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggSoStoreHVO> processor = new CompareAroundProcesser<AggSoStoreHVO>(
				SostorePluginPoint.APPROVE);
		processor.addBeforeRule(new ApproveStatusCheckRule());
		return processor;
	}

	@Override
	protected AggSoStoreHVO[] processBP(Object userObj,
			AggSoStoreHVO[] clientFullVOs, AggSoStoreHVO[] originBills) {
		AggSoStoreHVO[] bills = null;
		ISostoreMaintain operator = NCLocator.getInstance().lookup(
				ISostoreMaintain.class);
		try {
			bills = operator.approve(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
