package nc.bs.bhs.sotruck.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.bhs.sotruck.AggSoTruckHVO;
import nc.vo.pub.VOStatus;

/**
 * 标准单据弃审的BP
 */
public class AceSotruckUnApproveBP {

	public AggSoTruckHVO[] unApprove(AggSoTruckHVO[] clientBills,
			AggSoTruckHVO[] originBills) {
		for (AggSoTruckHVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<AggSoTruckHVO> update = new BillUpdate<AggSoTruckHVO>();
		AggSoTruckHVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}
}
