package nc.bs.bhs.bhsquality.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.bhs.bhsquality.AggBhsPoHeadVO;
import nc.vo.pub.VOStatus;

/**
 * 标准单据弃审的BP
 */
public class AceBhsqualityUnApproveBP {

	public AggBhsPoHeadVO[] unApprove(AggBhsPoHeadVO[] clientBills,
			AggBhsPoHeadVO[] originBills) {
		for (AggBhsPoHeadVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<AggBhsPoHeadVO> update = new BillUpdate<AggBhsPoHeadVO>();
		AggBhsPoHeadVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}
}
