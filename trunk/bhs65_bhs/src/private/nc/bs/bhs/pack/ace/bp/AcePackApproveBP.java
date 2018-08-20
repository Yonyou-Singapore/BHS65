package nc.bs.bhs.pack.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.bhs.pack.AggSoOrderPackVO;

/**
 * 标准单据审核的BP
 */
public class AcePackApproveBP {

	/**
	 * 审核动作
	 * 
	 * @param vos
	 * @param script
	 * @return
	 */
	public AggSoOrderPackVO[] approve(AggSoOrderPackVO[] clientBills,
			AggSoOrderPackVO[] originBills) {
		for (AggSoOrderPackVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<AggSoOrderPackVO> update = new BillUpdate<AggSoOrderPackVO>();
		AggSoOrderPackVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

}
