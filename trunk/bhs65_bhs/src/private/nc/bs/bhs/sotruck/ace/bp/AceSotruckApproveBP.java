package nc.bs.bhs.sotruck.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.bhs.sotruck.AggSoTruckHVO;

/**
 * ��׼������˵�BP
 */
public class AceSotruckApproveBP {

	/**
	 * ��˶���
	 * 
	 * @param vos
	 * @param script
	 * @return
	 */
	public AggSoTruckHVO[] approve(AggSoTruckHVO[] clientBills,
			AggSoTruckHVO[] originBills) {
		for (AggSoTruckHVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<AggSoTruckHVO> update = new BillUpdate<AggSoTruckHVO>();
		AggSoTruckHVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

}
