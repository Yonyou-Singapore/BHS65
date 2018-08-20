package nc.bs.bhs.bhstools.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.bhs.bhstools.AggBhsToolsHeadVO;

/**
 * 标准单据审核的BP
 */
public class AceBhstoolsApproveBP {

	/**
	 * 审核动作
	 * 
	 * @param vos
	 * @param script
	 * @return
	 */
	public AggBhsToolsHeadVO[] approve(AggBhsToolsHeadVO[] clientBills,
			AggBhsToolsHeadVO[] originBills) {
		for (AggBhsToolsHeadVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<AggBhsToolsHeadVO> update = new BillUpdate<AggBhsToolsHeadVO>();
		AggBhsToolsHeadVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

}
