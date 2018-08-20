package nc.bs.bhs.bhsquality.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.bhs.bhsquality.AggBhsPoHeadVO;

/**
 * 标准单据审核的BP
 */
public class AceBhsqualityApproveBP {

	/**
	 * 审核动作
	 * 
	 * @param vos
	 * @param script
	 * @return
	 */
	public AggBhsPoHeadVO[] approve(AggBhsPoHeadVO[] clientBills,
			AggBhsPoHeadVO[] originBills) {
		for (AggBhsPoHeadVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<AggBhsPoHeadVO> update = new BillUpdate<AggBhsPoHeadVO>();
		AggBhsPoHeadVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

}
