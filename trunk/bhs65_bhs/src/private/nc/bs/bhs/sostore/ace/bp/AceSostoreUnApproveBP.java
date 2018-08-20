package nc.bs.bhs.sostore.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.bhs.sostore.AggSoStoreHVO;
import nc.vo.pub.VOStatus;

/**
 * ��׼���������BP
 */
public class AceSostoreUnApproveBP {

	public AggSoStoreHVO[] unApprove(AggSoStoreHVO[] clientBills,
			AggSoStoreHVO[] originBills) {
		for (AggSoStoreHVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<AggSoStoreHVO> update = new BillUpdate<AggSoStoreHVO>();
		AggSoStoreHVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}
}
