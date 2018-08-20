package nc.bs.bhs.somove.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.bhs.somove.AggSoMoveHVO;
import nc.vo.pub.VOStatus;

/**
 * 标准单据弃审的BP
 */
public class AceSomoveUnApproveBP {

	public AggSoMoveHVO[] unApprove(AggSoMoveHVO[] clientBills,
			AggSoMoveHVO[] originBills) {
		for (AggSoMoveHVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<AggSoMoveHVO> update = new BillUpdate<AggSoMoveHVO>();
		AggSoMoveHVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}
}
