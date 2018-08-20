package nc.bs.bhs.bhstools.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.bhs.bhstools.AggBhsToolsHeadVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * 标准单据收回的BP
 */
public class AceBhstoolsUnSendApproveBP {

	public AggBhsToolsHeadVO[] unSend(AggBhsToolsHeadVO[] clientBills,
			AggBhsToolsHeadVO[] originBills) {
		// 把VO持久化到数据库中
		this.setHeadVOStatus(clientBills);
		BillUpdate<AggBhsToolsHeadVO> update = new BillUpdate<AggBhsToolsHeadVO>();
		AggBhsToolsHeadVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

	private void setHeadVOStatus(AggBhsToolsHeadVO[] clientBills) {
		for (AggBhsToolsHeadVO clientBill : clientBills) {
			clientBill.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.FREE.value());
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
	}
}
