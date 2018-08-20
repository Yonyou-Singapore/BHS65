package nc.bs.bhs.sotruck.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.bhs.sotruck.AggSoTruckHVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * ��׼���������BP
 */
public class AceSotruckSendApproveBP {
	/**
	 * ������
	 * 
	 * @param vos
	 *            ����VO����
	 * @param script
	 *            ���ݶ����ű�����
	 * @return �����ĵ���VO����
	 */

	public AggSoTruckHVO[] sendApprove(AggSoTruckHVO[] clientBills,
			AggSoTruckHVO[] originBills) {
		for (AggSoTruckHVO clientFullVO : clientBills) {
			clientFullVO.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.COMMIT.value());
			clientFullVO.getParentVO().setStatus(VOStatus.UPDATED);
		}
		// ���ݳ־û�
		AggSoTruckHVO[] returnVos = new BillUpdate<AggSoTruckHVO>().update(
				clientBills, originBills);
		return returnVos;
	}
}
