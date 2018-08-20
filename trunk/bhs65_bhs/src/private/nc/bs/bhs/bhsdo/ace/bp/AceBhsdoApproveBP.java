package nc.bs.bhs.bhsdo.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.bhs.bhsdo.AggBhsDoVO;

/**
 * ��׼������˵�BP
 */
public class AceBhsdoApproveBP {

	/**
	 * ��˶���
	 * 
	 * @param vos
	 * @param script
	 * @return
	 */
	public AggBhsDoVO[] approve(AggBhsDoVO[] clientBills,
			AggBhsDoVO[] originBills) {
		for (AggBhsDoVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<AggBhsDoVO> update = new BillUpdate<AggBhsDoVO>();
		AggBhsDoVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

}
