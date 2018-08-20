package nc.bs.bhs.build.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.bhs.build.AggSoOrderBuildHVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * ��׼���������BP
 */
public class AceBuildSendApproveBP {
	/**
	 * ������
	 * 
	 * @param vos
	 *            ����VO����
	 * @param script
	 *            ���ݶ����ű�����
	 * @return �����ĵ���VO����
	 */

	public AggSoOrderBuildHVO[] sendApprove(AggSoOrderBuildHVO[] clientBills,
			AggSoOrderBuildHVO[] originBills) {
		for (AggSoOrderBuildHVO clientFullVO : clientBills) {
			clientFullVO.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.COMMIT.value());
			clientFullVO.getParentVO().setStatus(VOStatus.UPDATED);
		}
		// ���ݳ־û�
		AggSoOrderBuildHVO[] returnVos = new BillUpdate<AggSoOrderBuildHVO>().update(
				clientBills, originBills);
		return returnVos;
	}
}
