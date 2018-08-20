package nc.bs.bhs.bhstools.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.bhs.bhstools.AggBhsToolsHeadVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * ��׼���������BP
 */
public class AceBhstoolsSendApproveBP {
	/**
	 * ������
	 * 
	 * @param vos
	 *            ����VO����
	 * @param script
	 *            ���ݶ����ű�����
	 * @return �����ĵ���VO����
	 */

	public AggBhsToolsHeadVO[] sendApprove(AggBhsToolsHeadVO[] clientBills,
			AggBhsToolsHeadVO[] originBills) {
		for (AggBhsToolsHeadVO clientFullVO : clientBills) {
			clientFullVO.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.COMMIT.value());
			clientFullVO.getParentVO().setStatus(VOStatus.UPDATED);
		}
		// ���ݳ־û�
		AggBhsToolsHeadVO[] returnVos = new BillUpdate<AggBhsToolsHeadVO>().update(
				clientBills, originBills);
		return returnVos;
	}
}
