package nc.bs.bhs.build.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.bhs.build.AggSoOrderBuildHVO;
import nc.vo.pub.VOStatus;

/**
 * 标准单据弃审的BP
 */
public class AceBuildUnApproveBP {

	public AggSoOrderBuildHVO[] unApprove(AggSoOrderBuildHVO[] clientBills,
			AggSoOrderBuildHVO[] originBills) {
		for (AggSoOrderBuildHVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<AggSoOrderBuildHVO> update = new BillUpdate<AggSoOrderBuildHVO>();
		AggSoOrderBuildHVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}
}
