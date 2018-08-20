package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.bhs.sotruck.plugin.bpplugin.SotruckPluginPoint;
import nc.vo.bhs.sotruck.AggSoTruckHVO;
import nc.itf.bhs.ISotruckMaintain;

public class N_TRUC_DELETE extends AbstractPfAction<AggSoTruckHVO> {

	@Override
	protected CompareAroundProcesser<AggSoTruckHVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggSoTruckHVO> processor = new CompareAroundProcesser<AggSoTruckHVO>(
				SotruckPluginPoint.SCRIPT_DELETE);
		// TODO 在此处添加前后规则
		return processor;
	}

	@Override
	protected AggSoTruckHVO[] processBP(Object userObj,
			AggSoTruckHVO[] clientFullVOs, AggSoTruckHVO[] originBills) {
		ISotruckMaintain operator = NCLocator.getInstance().lookup(
				ISotruckMaintain.class);
		try {
			operator.delete(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return clientFullVOs;
	}

}
