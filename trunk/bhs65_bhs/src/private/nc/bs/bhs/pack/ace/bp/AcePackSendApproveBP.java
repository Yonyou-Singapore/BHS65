package nc.bs.bhs.pack.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.bhs.pack.AggSoOrderPackVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * ��׼���������BP
 */
public class AcePackSendApproveBP {
	/**
	 * ������
	 * 
	 * @param vos
	 *            ����VO����
	 * @param script
	 *            ���ݶ����ű�����
	 * @return �����ĵ���VO����
	 */

	public AggSoOrderPackVO[] sendApprove(AggSoOrderPackVO[] clientBills,
			AggSoOrderPackVO[] originBills) {
		for (AggSoOrderPackVO clientFullVO : clientBills) {
			clientFullVO.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.COMMIT.value());
			clientFullVO.getParentVO().setStatus(VOStatus.UPDATED);
		}
		// ���ݳ־û�
		AggSoOrderPackVO[] returnVos = new BillUpdate<AggSoOrderPackVO>().update(
				clientBills, originBills);
		return returnVos;
	}
}
