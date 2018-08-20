package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.ApproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.bhs.sotruck.plugin.bpplugin.SotruckPluginPoint;
import nc.vo.bhs.sotruck.AggSoTruckHVO;
import nc.itf.bhs.ISotruckMaintain;

public class N_TRUC_APPROVE extends AbstractPfAction<AggSoTruckHVO> {

	public N_TRUC_APPROVE() {
		super();
	}

	@Override
	protected CompareAroundProcesser<AggSoTruckHVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggSoTruckHVO> processor = new CompareAroundProcesser<AggSoTruckHVO>(
				SotruckPluginPoint.APPROVE);
		processor.addBeforeRule(new ApproveStatusCheckRule());
		return processor;
	}

	@Override
	protected AggSoTruckHVO[] processBP(Object userObj,
			AggSoTruckHVO[] clientFullVOs, AggSoTruckHVO[] originBills) {
		AggSoTruckHVO[] bills = null;
		ISotruckMaintain operator = NCLocator.getInstance().lookup(
				ISotruckMaintain.class);
		try {
			bills = operator.approve(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
