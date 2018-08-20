package nc.bs.bhs.build.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.bhs.build.AggSoOrderBuildHVO;

/**
 * ��׼������˵�BP
 */
public class AceBuildApproveBP {

	/**
	 * ��˶���
	 * 
	 * @param vos
	 * @param script
	 * @return
	 */
	public AggSoOrderBuildHVO[] approve(AggSoOrderBuildHVO[] clientBills,
			AggSoOrderBuildHVO[] originBills) {
		for (AggSoOrderBuildHVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<AggSoOrderBuildHVO> update = new BillUpdate<AggSoOrderBuildHVO>();
		AggSoOrderBuildHVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

}
