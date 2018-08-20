package nc.impl.pub.ace;

import nc.bs.bhs.bhstools.ace.bp.AceBhstoolsInsertBP;
import nc.bs.bhs.bhstools.ace.bp.AceBhstoolsUpdateBP;
import nc.bs.bhs.bhstools.ace.bp.AceBhstoolsDeleteBP;
import nc.bs.bhs.bhstools.ace.bp.AceBhstoolsSendApproveBP;
import nc.bs.bhs.bhstools.ace.bp.AceBhstoolsUnSendApproveBP;
import nc.bs.bhs.bhstools.ace.bp.AceBhstoolsApproveBP;
import nc.bs.bhs.bhstools.ace.bp.AceBhstoolsUnApproveBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.bhs.bhstools.AggBhsToolsHeadVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceBhstoolsPubServiceImpl {
	// ����
	public AggBhsToolsHeadVO[] pubinsertBills(AggBhsToolsHeadVO[] clientFullVOs,
			AggBhsToolsHeadVO[] originBills) throws BusinessException {
		try {
			// ���ݿ������ݺ�ǰ̨���ݹ����Ĳ���VO�ϲ���Ľ��
			BillTransferTool<AggBhsToolsHeadVO> transferTool = new BillTransferTool<AggBhsToolsHeadVO>(
					clientFullVOs);
			// ����BP
			AceBhstoolsInsertBP action = new AceBhstoolsInsertBP();
			AggBhsToolsHeadVO[] retvos = action.insert(clientFullVOs);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// ɾ��
	public void pubdeleteBills(AggBhsToolsHeadVO[] clientFullVOs,
			AggBhsToolsHeadVO[] originBills) throws BusinessException {
		try {
			// ����BP
			new AceBhstoolsDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// �޸�
	public AggBhsToolsHeadVO[] pubupdateBills(AggBhsToolsHeadVO[] clientFullVOs,
			AggBhsToolsHeadVO[] originBills) throws BusinessException {
		try {
			// ���� + ���ts
			BillTransferTool<AggBhsToolsHeadVO> transferTool = new BillTransferTool<AggBhsToolsHeadVO>(
					clientFullVOs);
			AceBhstoolsUpdateBP bp = new AceBhstoolsUpdateBP();
			AggBhsToolsHeadVO[] retvos = bp.update(clientFullVOs, originBills);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public AggBhsToolsHeadVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		AggBhsToolsHeadVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<AggBhsToolsHeadVO> query = new BillLazyQuery<AggBhsToolsHeadVO>(
					AggBhsToolsHeadVO.class);
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
	public AggBhsToolsHeadVO[] pubsendapprovebills(
			AggBhsToolsHeadVO[] clientFullVOs, AggBhsToolsHeadVO[] originBills)
			throws BusinessException {
		AceBhstoolsSendApproveBP bp = new AceBhstoolsSendApproveBP();
		AggBhsToolsHeadVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// �ջ�
	public AggBhsToolsHeadVO[] pubunsendapprovebills(
			AggBhsToolsHeadVO[] clientFullVOs, AggBhsToolsHeadVO[] originBills)
			throws BusinessException {
		AceBhstoolsUnSendApproveBP bp = new AceBhstoolsUnSendApproveBP();
		AggBhsToolsHeadVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// ����
	public AggBhsToolsHeadVO[] pubapprovebills(AggBhsToolsHeadVO[] clientFullVOs,
			AggBhsToolsHeadVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceBhstoolsApproveBP bp = new AceBhstoolsApproveBP();
		AggBhsToolsHeadVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// ����

	public AggBhsToolsHeadVO[] pubunapprovebills(AggBhsToolsHeadVO[] clientFullVOs,
			AggBhsToolsHeadVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceBhstoolsUnApproveBP bp = new AceBhstoolsUnApproveBP();
		AggBhsToolsHeadVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}