package nc.impl.pub.ace;

import nc.bs.bhs.build.ace.bp.AceBuildInsertBP;
import nc.bs.bhs.build.ace.bp.AceBuildUpdateBP;
import nc.bs.bhs.build.ace.bp.AceBuildDeleteBP;
import nc.bs.bhs.build.ace.bp.AceBuildSendApproveBP;
import nc.bs.bhs.build.ace.bp.AceBuildUnSendApproveBP;
import nc.bs.bhs.build.ace.bp.AceBuildApproveBP;
import nc.bs.bhs.build.ace.bp.AceBuildUnApproveBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.bhs.build.AggSoOrderBuildHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceBuildPubServiceImpl {
	// ����
	public AggSoOrderBuildHVO[] pubinsertBills(AggSoOrderBuildHVO[] clientFullVOs,
			AggSoOrderBuildHVO[] originBills) throws BusinessException {
		try {
			// ���ݿ������ݺ�ǰ̨���ݹ����Ĳ���VO�ϲ���Ľ��
			BillTransferTool<AggSoOrderBuildHVO> transferTool = new BillTransferTool<AggSoOrderBuildHVO>(
					clientFullVOs);
			// ����BP
			AceBuildInsertBP action = new AceBuildInsertBP();
			AggSoOrderBuildHVO[] retvos = action.insert(clientFullVOs);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// ɾ��
	public void pubdeleteBills(AggSoOrderBuildHVO[] clientFullVOs,
			AggSoOrderBuildHVO[] originBills) throws BusinessException {
		try {
			// ����BP
			new AceBuildDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// �޸�
	public AggSoOrderBuildHVO[] pubupdateBills(AggSoOrderBuildHVO[] clientFullVOs,
			AggSoOrderBuildHVO[] originBills) throws BusinessException {
		try {
			// ���� + ���ts
			BillTransferTool<AggSoOrderBuildHVO> transferTool = new BillTransferTool<AggSoOrderBuildHVO>(
					clientFullVOs);
			AceBuildUpdateBP bp = new AceBuildUpdateBP();
			AggSoOrderBuildHVO[] retvos = bp.update(clientFullVOs, originBills);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public AggSoOrderBuildHVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		AggSoOrderBuildHVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<AggSoOrderBuildHVO> query = new BillLazyQuery<AggSoOrderBuildHVO>(
					AggSoOrderBuildHVO.class);
			bills = query.query(queryScheme, null);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return bills;
	}

	/**
	 * ������ʵ�֣���ѯ֮ǰ��queryScheme���мӹ��������Լ����߼�
	 * 
	 * @param queryScheme
	 */
	protected void preQuery(IQueryScheme queryScheme) {
		// ��ѯ֮ǰ��queryScheme���мӹ��������Լ����߼�
	}

	// �ύ
	public AggSoOrderBuildHVO[] pubsendapprovebills(
			AggSoOrderBuildHVO[] clientFullVOs, AggSoOrderBuildHVO[] originBills)
			throws BusinessException {
		AceBuildSendApproveBP bp = new AceBuildSendApproveBP();
		AggSoOrderBuildHVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// �ջ�
	public AggSoOrderBuildHVO[] pubunsendapprovebills(
			AggSoOrderBuildHVO[] clientFullVOs, AggSoOrderBuildHVO[] originBills)
			throws BusinessException {
		AceBuildUnSendApproveBP bp = new AceBuildUnSendApproveBP();
		AggSoOrderBuildHVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// ����
	public AggSoOrderBuildHVO[] pubapprovebills(AggSoOrderBuildHVO[] clientFullVOs,
			AggSoOrderBuildHVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceBuildApproveBP bp = new AceBuildApproveBP();
		AggSoOrderBuildHVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// ����

	public AggSoOrderBuildHVO[] pubunapprovebills(AggSoOrderBuildHVO[] clientFullVOs,
			AggSoOrderBuildHVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceBuildUnApproveBP bp = new AceBuildUnApproveBP();
		AggSoOrderBuildHVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}