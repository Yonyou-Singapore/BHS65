package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.bhs.sostore.plugin.bpplugin.SostorePluginPoint;
import nc.vo.bhs.sostore.AggSoStoreHVO;
import nc.itf.bhs.ISostoreMaintain;

public class N_STOR_DELETE extends AbstractPfAction<AggSoStoreHVO> {

	@Override
	protected CompareAroundProcesser<AggSoStoreHVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggSoStoreHVO> processor = new CompareAroundProcesser<AggSoStoreHVO>(
				SostorePluginPoint.SCRIPT_DELETE);
		// TODO 在此处添加前后规则
		return processor;
	}

	@Override
	protected AggSoStoreHVO[] processBP(Object userObj,
			AggSoStoreHVO[] clientFullVOs, AggSoStoreHVO[] originBills) {
		ISostoreMaintain operator = NCLocator.getInstance().lookup(
				ISostoreMaintain.class);
		try {
			operator.delete(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return clientFullVOs;
	}

}
