package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.bhs.sotruck.TruckAction;
import nc.bs.bhs.sotruck.plugin.bpplugin.SotruckPluginPoint;
import nc.vo.bhs.sotruck.AggSoTruckHVO;
import nc.itf.bhs.ISotruckMaintain;

public class N_TRUC_SAVEBASE extends AbstractPfAction<AggSoTruckHVO> {

	@Override
	protected CompareAroundProcesser<AggSoTruckHVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggSoTruckHVO> processor = null;
		AggSoTruckHVO[] clientFullVOs = (AggSoTruckHVO[]) this.getVos();
		if (!StringUtil.isEmptyWithTrim(clientFullVOs[0].getParentVO()
				.getPrimaryKey())) {
			processor = new CompareAroundProcesser<AggSoTruckHVO>(
					SotruckPluginPoint.SCRIPT_UPDATE);
		} else {
			processor = new CompareAroundProcesser<AggSoTruckHVO>(
					SotruckPluginPoint.SCRIPT_INSERT);
		}
		// TODO 在此处添加前后规则
		IRule<AggSoTruckHVO> rule = null;

		return processor;
	}

	@Override
	protected AggSoTruckHVO[] processBP(Object userObj,
			AggSoTruckHVO[] clientFullVOs, AggSoTruckHVO[] originBills) {

		AggSoTruckHVO[] bills = null;
		try {
			ISotruckMaintain operator = NCLocator.getInstance()
					.lookup(ISotruckMaintain.class);
			if (!StringUtil.isEmptyWithTrim(clientFullVOs[0].getParentVO()
					.getPrimaryKey())) {
				bills = operator.update(clientFullVOs, originBills);
			} else {
				bills = operator.insert(clientFullVOs, originBills);
			}
			//更新销售订单中的进出口类型，船名，船号字段2017-05-14
			new TruckAction().afterSaveAction(bills[0]);
			
			
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}
}
