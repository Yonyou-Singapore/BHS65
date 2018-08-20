package nc.bs.bhs.bhstools.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.bhs.bhstools.AggBhsToolsHeadVO;
import nc.vo.pub.VOStatus;

/**
 * 标准单据弃审的BP
 */
public class AceBhstoolsUnApproveBP {

	public AggBhsToolsHeadVO[] unApprove(AggBhsToolsHeadVO[] clientBills,
			AggBhsToolsHeadVO[] originBills) {
		for (AggBhsToolsHeadVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<AggBhsToolsHeadVO> update = new BillUpdate<AggBhsToolsHeadVO>();
		AggBhsToolsHeadVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}
}
