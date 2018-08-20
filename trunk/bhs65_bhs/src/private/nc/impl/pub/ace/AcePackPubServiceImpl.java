package nc.impl.pub.ace;

import nc.bs.bhs.pack.ace.bp.AcePackInsertBP;
import nc.bs.bhs.pack.ace.bp.AcePackUpdateBP;
import nc.bs.bhs.pack.ace.bp.AcePackDeleteBP;
import nc.bs.bhs.pack.ace.bp.AcePackSendApproveBP;
import nc.bs.bhs.pack.ace.bp.AcePackUnSendApproveBP;
import nc.bs.bhs.pack.ace.bp.AcePackApproveBP;
import nc.bs.bhs.pack.ace.bp.AcePackUnApproveBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.bhs.pack.AggSoOrderPackVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AcePackPubServiceImpl {
	// ����
	public AggSoOrderPackVO[] pubinsertBills(AggSoOrderPackVO[] clientFullVOs,
			AggSoOrderPackVO[] originBills) throws BusinessException {
		try {
			// ���ݿ������ݺ�ǰ̨���ݹ����Ĳ���VO�ϲ���Ľ��
			BillTransferTool<AggSoOrderPackVO> transferTool = new BillTransferTool<AggSoOrderPackVO>(
					clientFullVOs);
			// ����BP
			AcePackInsertBP action = new AcePackInsertBP();
			AggSoOrderPackVO[] retvos = action.insert(clientFullVOs);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// ɾ��
	public void pubdeleteBills(AggSoOrderPackVO[] clientFullVOs,
			AggSoOrderPackVO[] originBills) throws BusinessException {
		try {
			// ����BP
			new AcePackDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// �޸�
	public AggSoOrderPackVO[] pubupdateBills(AggSoOrderPackVO[] clientFullVOs,
			AggSoOrderPackVO[] originBills) throws BusinessException {
		try {
			// ���� + ���ts
			BillTransferTool<AggSoOrderPackVO> transferTool = new BillTransferTool<AggSoOrderPackVO>(
					clientFullVOs);
			AcePackUpdateBP bp = new AcePackUpdateBP();
			AggSoOrderPackVO[] retvos = bp.update(clientFullVOs, originBills);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public AggSoOrderPackVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		AggSoOrderPackVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<AggSoOrderPackVO> query = new BillLazyQuery<AggSoOrderPackVO>(
					AggSoOrderPackVO.class);
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
	public AggSoOrderPackVO[] pubsendapprovebills(
			AggSoOrderPackVO[] clientFullVOs, AggSoOrderPackVO[] originBills)
			throws BusinessException {
		AcePackSendApproveBP bp = new AcePackSendApproveBP();
		AggSoOrderPackVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// �ջ�
	public AggSoOrderPackVO[] pubunsendapprovebills(
			AggSoOrderPackVO[] clientFullVOs, AggSoOrderPackVO[] originBills)
			throws BusinessException {
		AcePackUnSendApproveBP bp = new AcePackUnSendApproveBP();
		AggSoOrderPackVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// ����
	public AggSoOrderPackVO[] pubapprovebills(AggSoOrderPackVO[] clientFullVOs,
			AggSoOrderPackVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AcePackApproveBP bp = new AcePackApproveBP();
		AggSoOrderPackVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// ����

	public AggSoOrderPackVO[] pubunapprovebills(AggSoOrderPackVO[] clientFullVOs,
			AggSoOrderPackVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AcePackUnApproveBP bp = new AcePackUnApproveBP();
		AggSoOrderPackVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}