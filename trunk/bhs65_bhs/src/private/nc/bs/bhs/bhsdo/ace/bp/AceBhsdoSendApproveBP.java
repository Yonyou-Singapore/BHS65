package nc.bs.bhs.bhsdo.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.bhs.bhsdo.AggBhsDoVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * 标准单据送审的BP
 */
public class AceBhsdoSendApproveBP {
	/**
	 * 送审动作
	 * 
	 * @param vos
	 *            单据VO数组
	 * @param script
	 *            单据动作脚本对象
	 * @return 送审后的单据VO数组
	 */

	public AggBhsDoVO[] sendApprove(AggBhsDoVO[] clientBills,
			AggBhsDoVO[] originBills) {
		for (AggBhsDoVO clientFullVO : clientBills) {
			clientFullVO.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.COMMIT.value());
			clientFullVO.getParentVO().setStatus(VOStatus.UPDATED);
		}
		// 数据持久化
		AggBhsDoVO[] returnVos = new BillUpdate<AggBhsDoVO>().update(
				clientBills, originBills);
		return returnVos;
	}
}
