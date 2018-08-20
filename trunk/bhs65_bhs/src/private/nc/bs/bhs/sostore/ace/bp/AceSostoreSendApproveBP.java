package nc.bs.bhs.sostore.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.bhs.sostore.AggSoStoreHVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * ��׼���������BP
 */
public class AceSostoreSendApproveBP {
	/**
	 * ������
	 * 
	 * @param vos
	 *            ����VO����
	 * @param script
	 *            ���ݶ����ű�����
	 * @return �����ĵ���VO����
	 */

	public AggSoStoreHVO[] sendApprove(AggSoStoreHVO[] clientBills,
			AggSoStoreHVO[] originBills) {
		for (AggSoStoreHVO clientFullVO : clientBills) {
			clientFullVO.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.COMMIT.value());
			clientFullVO.getParentVO().setStatus(VOStatus.UPDATED);
		}
		// ���ݳ־û�
		AggSoStoreHVO[] returnVos = new BillUpdate<AggSoStoreHVO>().update(
				clientBills, originBills);
		return returnVos;
	}
}
