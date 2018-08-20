package nc.bs.bhs.sostore.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.bhs.sostore.AggSoStoreHVO;

/**
 * ��׼������˵�BP
 */
public class AceSostoreApproveBP {

	/**
	 * ��˶���
	 * 
	 * @param vos
	 * @param script
	 * @return
	 */
	public AggSoStoreHVO[] approve(AggSoStoreHVO[] clientBills,
			AggSoStoreHVO[] originBills) {
		for (AggSoStoreHVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<AggSoStoreHVO> update = new BillUpdate<AggSoStoreHVO>();
		AggSoStoreHVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

}
