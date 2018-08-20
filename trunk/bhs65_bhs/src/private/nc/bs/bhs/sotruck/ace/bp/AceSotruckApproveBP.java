package nc.bs.bhs.sotruck.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.bhs.sotruck.AggSoTruckHVO;

/**
 * 标准单据审核的BP
 */
public class AceSotruckApproveBP {

	/**
	 * 审核动作
	 * 
	 * @param vos
	 * @param script
	 * @return
	 */
	public AggSoTruckHVO[] approve(AggSoTruckHVO[] clientBills,
			AggSoTruckHVO[] originBills) {
		for (AggSoTruckHVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<AggSoTruckHVO> update = new BillUpdate<AggSoTruckHVO>();
		AggSoTruckHVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

}
