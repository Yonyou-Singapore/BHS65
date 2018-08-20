package nc.bs.bhs.bhsquality.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.bhs.bhsquality.AggBhsPoHeadVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * 标准单据收回的BP
 */
public class AceBhsqualityUnSendApproveBP {

	public AggBhsPoHeadVO[] unSend(AggBhsPoHeadVO[] clientBills,
			AggBhsPoHeadVO[] originBills) {
		// 把VO持久化到数据库中
		this.setHeadVOStatus(clientBills);
		BillUpdate<AggBhsPoHeadVO> update = new BillUpdate<AggBhsPoHeadVO>();
		AggBhsPoHeadVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

	private void setHeadVOStatus(AggBhsPoHeadVO[] clientBills) {
		for (AggBhsPoHeadVO clientBill : clientBills) {
			clientBill.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.FREE.value());
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
	}
}
