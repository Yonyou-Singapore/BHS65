package nc.impl.pub.ace;

import nc.bs.bhs.bhsdo.ace.bp.AceBhsdoInsertBP;
import nc.bs.bhs.bhsdo.ace.bp.AceBhsdoUpdateBP;
import nc.bs.bhs.bhsdo.ace.bp.AceBhsdoDeleteBP;
import nc.bs.bhs.bhsdo.ace.bp.AceBhsdoSendApproveBP;
import nc.bs.bhs.bhsdo.ace.bp.AceBhsdoUnSendApproveBP;
import nc.bs.bhs.bhsdo.ace.bp.AceBhsdoApproveBP;
import nc.bs.bhs.bhsdo.ace.bp.AceBhsdoUnApproveBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.bhs.bhsdo.AggBhsDoVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceBhsdoPubServiceImpl {
	// ����
	public AggBhsDoVO[] pubinsertBills(AggBhsDoVO[] clientFullVOs,
			AggBhsDoVO[] originBills) throws BusinessException {
		try {
			// ���ݿ������ݺ�ǰ̨���ݹ����Ĳ���VO�ϲ���Ľ��
			BillTransferTool<AggBhsDoVO> transferTool = new BillTransferTool<AggBhsDoVO>(
					clientFullVOs);
			// ����BP
			AceBhsdoInsertBP action = new AceBhsdoInsertBP();
			AggBhsDoVO[] retvos = action.insert(clientFullVOs);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// ɾ��
	public void pubdeleteBills(AggBhsDoVO[] clientFullVOs,
			AggBhsDoVO[] originBills) throws BusinessException {
		try {
			// ����BP
			new AceBhsdoDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// �޸�
	public AggBhsDoVO[] pubupdateBills(AggBhsDoVO[] clientFullVOs,
			AggBhsDoVO[] originBills) throws BusinessException {
		try {
			// ���� + ���ts
			BillTransferTool<AggBhsDoVO> transferTool = new BillTransferTool<AggBhsDoVO>(
					clientFullVOs);
			AceBhsdoUpdateBP bp = new AceBhsdoUpdateBP();
			AggBhsDoVO[] retvos = bp.update(clientFullVOs, originBills);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public AggBhsDoVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		AggBhsDoVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<AggBhsDoVO> query = new BillLazyQuery<AggBhsDoVO>(
					AggBhsDoVO.class);
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
	public AggBhsDoVO[] pubsendapprovebills(
			AggBhsDoVO[] clientFullVOs, AggBhsDoVO[] originBills)
			throws BusinessException {
		AceBhsdoSendApproveBP bp = new AceBhsdoSendApproveBP();
		AggBhsDoVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// �ջ�
	public AggBhsDoVO[] pubunsendapprovebills(
			AggBhsDoVO[] clientFullVOs, AggBhsDoVO[] originBills)
			throws BusinessException {
		AceBhsdoUnSendApproveBP bp = new AceBhsdoUnSendApproveBP();
		AggBhsDoVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// ����
	public AggBhsDoVO[] pubapprovebills(AggBhsDoVO[] clientFullVOs,
			AggBhsDoVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceBhsdoApproveBP bp = new AceBhsdoApproveBP();
		AggBhsDoVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// ����

	public AggBhsDoVO[] pubunapprovebills(AggBhsDoVO[] clientFullVOs,
			AggBhsDoVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceBhsdoUnApproveBP bp = new AceBhsdoUnApproveBP();
		AggBhsDoVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}