package nc.bs.bhs.bhsdo.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.bhs.bhsdo.AggBhsDoVO;
import nc.vo.pub.VOStatus;

/**
 * 标准单据弃审的BP
 */
public class AceBhsdoUnApproveBP {

	public AggBhsDoVO[] unApprove(AggBhsDoVO[] clientBills,
			AggBhsDoVO[] originBills) {
		for (AggBhsDoVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<AggBhsDoVO> update = new BillUpdate<AggBhsDoVO>();
		AggBhsDoVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}
}
