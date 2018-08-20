package nc.bs.bhs.pack.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.bhs.pack.AggSoOrderPackVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * ��׼�����ջص�BP
 */
public class AcePackUnSendApproveBP {

	public AggSoOrderPackVO[] unSend(AggSoOrderPackVO[] clientBills,
			AggSoOrderPackVO[] originBills) {
		// ��VO�־û������ݿ���
		this.setHeadVOStatus(clientBills);
		BillUpdate<AggSoOrderPackVO> update = new BillUpdate<AggSoOrderPackVO>();
		AggSoOrderPackVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

	private void setHeadVOStatus(AggSoOrderPackVO[] clientBills) {
		for (AggSoOrderPackVO clientBill : clientBills) {
			clientBill.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.FREE.value());
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
	}
}
