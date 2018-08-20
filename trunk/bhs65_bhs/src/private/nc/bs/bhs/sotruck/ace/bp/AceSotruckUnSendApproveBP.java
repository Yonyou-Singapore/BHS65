package nc.bs.bhs.sotruck.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.bhs.sotruck.AggSoTruckHVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * 标准单据收回的BP
 */
public class AceSotruckUnSendApproveBP {

	public AggSoTruckHVO[] unSend(AggSoTruckHVO[] clientBills,
			AggSoTruckHVO[] originBills) {
		// 把VO持久化到数据库中
		this.setHeadVOStatus(clientBills);
		BillUpdate<AggSoTruckHVO> update = new BillUpdate<AggSoTruckHVO>();
		AggSoTruckHVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

	private void setHeadVOStatus(AggSoTruckHVO[] clientBills) {
		for (AggSoTruckHVO clientBill : clientBills) {
			clientBill.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.FREE.value());
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
	}
}
