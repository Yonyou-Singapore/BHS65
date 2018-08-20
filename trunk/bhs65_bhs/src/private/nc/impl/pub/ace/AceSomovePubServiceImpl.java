package nc.impl.pub.ace;

import nc.bs.bhs.somove.ace.bp.AceSomoveInsertBP;
import nc.bs.bhs.somove.ace.bp.AceSomoveUpdateBP;
import nc.bs.bhs.somove.ace.bp.AceSomoveDeleteBP;
import nc.bs.bhs.somove.ace.bp.AceSomoveSendApproveBP;
import nc.bs.bhs.somove.ace.bp.AceSomoveUnSendApproveBP;
import nc.bs.bhs.somove.ace.bp.AceSomoveApproveBP;
import nc.bs.bhs.somove.ace.bp.AceSomoveUnApproveBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.bhs.somove.AggSoMoveHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceSomovePubServiceImpl {
	// ����
	public AggSoMoveHVO[] pubinsertBills(AggSoMoveHVO[] clientFullVOs,
			AggSoMoveHVO[] originBills) throws BusinessException {
		try {
			// ���ݿ������ݺ�ǰ̨���ݹ����Ĳ���VO�ϲ���Ľ��
			BillTransferTool<AggSoMoveHVO> transferTool = new BillTransferTool<AggSoMoveHVO>(
					clientFullVOs);
			// ����BP
			AceSomoveInsertBP action = new AceSomoveInsertBP();
			AggSoMoveHVO[] retvos = action.insert(clientFullVOs);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// ɾ��
	public void pubdeleteBills(AggSoMoveHVO[] clientFullVOs,
			AggSoMoveHVO[] originBills) throws BusinessException {
		try {
			// ����BP
			new AceSomoveDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// �޸�
	public AggSoMoveHVO[] pubupdateBills(AggSoMoveHVO[] clientFullVOs,
			AggSoMoveHVO[] originBills) throws BusinessException {
		try {
			// ���� + ���ts
			BillTransferTool<AggSoMoveHVO> transferTool = new BillTransferTool<AggSoMoveHVO>(
					clientFullVOs);
			AceSomoveUpdateBP bp = new AceSomoveUpdateBP();
			AggSoMoveHVO[] retvos = bp.update(clientFullVOs, originBills);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public AggSoMoveHVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		AggSoMoveHVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<AggSoMoveHVO> query = new BillLazyQuery<AggSoMoveHVO>(
					AggSoMoveHVO.class);
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
	public AggSoMoveHVO[] pubsendapprovebills(
			AggSoMoveHVO[] clientFullVOs, AggSoMoveHVO[] originBills)
			throws BusinessException {
		AceSomoveSendApproveBP bp = new AceSomoveSendApproveBP();
		AggSoMoveHVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// �ջ�
	public AggSoMoveHVO[] pubunsendapprovebills(
			AggSoMoveHVO[] clientFullVOs, AggSoMoveHVO[] originBills)
			throws BusinessException {
		AceSomoveUnSendApproveBP bp = new AceSomoveUnSendApproveBP();
		AggSoMoveHVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// ����
	public AggSoMoveHVO[] pubapprovebills(AggSoMoveHVO[] clientFullVOs,
			AggSoMoveHVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceSomoveApproveBP bp = new AceSomoveApproveBP();
		AggSoMoveHVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// ����

	public AggSoMoveHVO[] pubunapprovebills(AggSoMoveHVO[] clientFullVOs,
			AggSoMoveHVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceSomoveUnApproveBP bp = new AceSomoveUnApproveBP();
		AggSoMoveHVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}