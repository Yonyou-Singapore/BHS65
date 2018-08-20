package nc.bs.bhs.somove.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.bhs.somove.AggSoMoveHVO;

/**
 * 标准单据审核的BP
 */
public class AceSomoveApproveBP {

	/**
	 * 审核动作
	 * 
	 * @param vos
	 * @param script
	 * @return
	 */
	public AggSoMoveHVO[] approve(AggSoMoveHVO[] clientBills,
			AggSoMoveHVO[] originBills) {
		for (AggSoMoveHVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<AggSoMoveHVO> update = new BillUpdate<AggSoMoveHVO>();
		AggSoMoveHVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

}
