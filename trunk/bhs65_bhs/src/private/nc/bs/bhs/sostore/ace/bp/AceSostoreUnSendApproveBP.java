package nc.bs.bhs.sostore.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.bhs.sostore.AggSoStoreHVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * 标准单据收回的BP
 */
public class AceSostoreUnSendApproveBP {

	public AggSoStoreHVO[] unSend(AggSoStoreHVO[] clientBills,
			AggSoStoreHVO[] originBills) {
		// 把VO持久化到数据库中
		this.setHeadVOStatus(clientBills);
		BillUpdate<AggSoStoreHVO> update = new BillUpdate<AggSoStoreHVO>();
		AggSoStoreHVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

	private void setHeadVOStatus(AggSoStoreHVO[] clientBills) {
		for (AggSoStoreHVO clientBill : clientBills) {
			clientBill.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.FREE.value());
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
	}
}
