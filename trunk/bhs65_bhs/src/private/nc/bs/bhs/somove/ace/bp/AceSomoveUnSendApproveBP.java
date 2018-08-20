package nc.bs.bhs.somove.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.bhs.somove.AggSoMoveHVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * 标准单据收回的BP
 */
public class AceSomoveUnSendApproveBP {

	public AggSoMoveHVO[] unSend(AggSoMoveHVO[] clientBills,
			AggSoMoveHVO[] originBills) {
		// 把VO持久化到数据库中
		this.setHeadVOStatus(clientBills);
		BillUpdate<AggSoMoveHVO> update = new BillUpdate<AggSoMoveHVO>();
		AggSoMoveHVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

	private void setHeadVOStatus(AggSoMoveHVO[] clientBills) {
		for (AggSoMoveHVO clientBill : clientBills) {
			clientBill.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.FREE.value());
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
	}
}
