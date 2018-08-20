package nc.bs.bhs.pack.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.bhs.pack.AggSoOrderPackVO;
import nc.vo.pub.VOStatus;

/**
 * 标准单据弃审的BP
 */
public class AcePackUnApproveBP {

	public AggSoOrderPackVO[] unApprove(AggSoOrderPackVO[] clientBills,
			AggSoOrderPackVO[] originBills) {
		for (AggSoOrderPackVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<AggSoOrderPackVO> update = new BillUpdate<AggSoOrderPackVO>();
		AggSoOrderPackVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}
}
