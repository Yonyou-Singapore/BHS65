package nc.bs.bhs.build.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.bhs.build.AggSoOrderBuildHVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * ��׼�����ջص�BP
 */
public class AceBuildUnSendApproveBP {

	public AggSoOrderBuildHVO[] unSend(AggSoOrderBuildHVO[] clientBills,
			AggSoOrderBuildHVO[] originBills) {
		// ��VO�־û������ݿ���
		this.setHeadVOStatus(clientBills);
		BillUpdate<AggSoOrderBuildHVO> update = new BillUpdate<AggSoOrderBuildHVO>();
		AggSoOrderBuildHVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

	private void setHeadVOStatus(AggSoOrderBuildHVO[] clientBills) {
		for (AggSoOrderBuildHVO clientBill : clientBills) {
			clientBill.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.FREE.value());
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
	}
}
