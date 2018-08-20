package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.UncommitStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.bhs.sostore.plugin.bpplugin.SostorePluginPoint;
import nc.vo.bhs.sostore.AggSoStoreHVO;
import nc.itf.bhs.ISostoreMaintain;

public class N_STOR_UNSAVEBILL extends AbstractPfAction<AggSoStoreHVO> {

	@Override
	protected CompareAroundProcesser<AggSoStoreHVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggSoStoreHVO> processor = new CompareAroundProcesser<AggSoStoreHVO>(
				SostorePluginPoint.UNSEND_APPROVE);
		// TODO 在此处添加前后规则
		processor.addBeforeRule(new UncommitStatusCheckRule());

		return processor;
	}

	@Override
	protected AggSoStoreHVO[] processBP(Object userObj,
			AggSoStoreHVO[] clientFullVOs, AggSoStoreHVO[] originBills) {
		ISostoreMaintain operator = NCLocator.getInstance().lookup(
				ISostoreMaintain.class);
		AggSoStoreHVO[] bills = null;
		try {
			bills = operator.unsave(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
