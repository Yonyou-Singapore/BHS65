package nc.bs.bhs.somove.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.bhs.somove.AggSoMoveHVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * ��׼���������BP
 */
public class AceSomoveSendApproveBP {
	/**
	 * ������
	 * 
	 * @param vos
	 *            ����VO����
	 * @param script
	 *            ���ݶ����ű�����
	 * @return �����ĵ���VO����
	 */

	public AggSoMoveHVO[] sendApprove(AggSoMoveHVO[] clientBills,
			AggSoMoveHVO[] originBills) {
		for (AggSoMoveHVO clientFullVO : clientBills) {
			clientFullVO.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.COMMIT.value());
			clientFullVO.getParentVO().setStatus(VOStatus.UPDATED);
		}
		// ���ݳ־û�
		AggSoMoveHVO[] returnVos = new BillUpdate<AggSoMoveHVO>().update(
				clientBills, originBills);
		return returnVos;
	}
}
