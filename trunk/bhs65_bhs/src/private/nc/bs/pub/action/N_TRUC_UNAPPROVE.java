package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.UnapproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.bhs.sotruck.plugin.bpplugin.SotruckPluginPoint;
import nc.vo.bhs.sotruck.AggSoTruckHVO;
import nc.itf.bhs.ISotruckMaintain;

public class N_TRUC_UNAPPROVE extends AbstractPfAction<AggSoTruckHVO> {

	@Override
	protected CompareAroundProcesser<AggSoTruckHVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggSoTruckHVO> processor = new CompareAroundProcesser<AggSoTruckHVO>(
				SotruckPluginPoint.UNAPPROVE);
		// TODO 在此处添加前后规则
		processor.addBeforeRule(new UnapproveStatusCheckRule());

		return processor;
	}

	@Override
	protected AggSoTruckHVO[] processBP(Object userObj,
			AggSoTruckHVO[] clientFullVOs, AggSoTruckHVO[] originBills) {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AggSoTruckHVO[] bills = null;
		try {
			ISotruckMaintain operator = NCLocator.getInstance()
					.lookup(ISotruckMaintain.class);
			bills = operator.unapprove(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
