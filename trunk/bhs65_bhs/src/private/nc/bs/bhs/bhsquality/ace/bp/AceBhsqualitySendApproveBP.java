package nc.bs.bhs.bhsquality.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.bhs.bhsquality.AggBhsPoHeadVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * ��׼���������BP
 */
public class AceBhsqualitySendApproveBP {
	/**
	 * ������
	 * 
	 * @param vos
	 *            ����VO����
	 * @param script
	 *            ���ݶ����ű�����
	 * @return �����ĵ���VO����
	 */

	public AggBhsPoHeadVO[] sendApprove(AggBhsPoHeadVO[] clientBills,
			AggBhsPoHeadVO[] originBills) {
		for (AggBhsPoHeadVO clientFullVO : clientBills) {
			clientFullVO.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.COMMIT.value());
			clientFullVO.getParentVO().setStatus(VOStatus.UPDATED);
		}
		// ���ݳ־û�
		AggBhsPoHeadVO[] returnVos = new BillUpdate<AggBhsPoHeadVO>().update(
				clientBills, originBills);
		return returnVos;
	}
}
