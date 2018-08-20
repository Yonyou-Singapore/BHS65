package nc.bs.bhs.bhsdo.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.bhs.bhsdo.AggBhsDoVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * ��׼�����ջص�BP
 */
public class AceBhsdoUnSendApproveBP {

	public AggBhsDoVO[] unSend(AggBhsDoVO[] clientBills,
			AggBhsDoVO[] originBills) {
		// ��VO�־û������ݿ���
		this.setHeadVOStatus(clientBills);
		BillUpdate<AggBhsDoVO> update = new BillUpdate<AggBhsDoVO>();
		AggBhsDoVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

	private void setHeadVOStatus(AggBhsDoVO[] clientBills) {
		for (AggBhsDoVO clientBill : clientBills) {
			clientBill.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.FREE.value());
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
	}
}
