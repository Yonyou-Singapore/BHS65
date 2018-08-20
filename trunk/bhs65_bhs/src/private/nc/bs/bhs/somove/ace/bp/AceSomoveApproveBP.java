package nc.bs.bhs.somove.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.bhs.somove.AggSoMoveHVO;

/**
 * ��׼������˵�BP
 */
public class AceSomoveApproveBP {

	/**
	 * ��˶���
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
