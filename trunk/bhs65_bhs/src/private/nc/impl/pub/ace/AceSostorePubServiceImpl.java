package nc.impl.pub.ace;

import nc.bs.bhs.sostore.ace.bp.AceSostoreInsertBP;
import nc.bs.bhs.sostore.ace.bp.AceSostoreUpdateBP;
import nc.bs.bhs.sostore.ace.bp.AceSostoreDeleteBP;
import nc.bs.bhs.sostore.ace.bp.AceSostoreSendApproveBP;
import nc.bs.bhs.sostore.ace.bp.AceSostoreUnSendApproveBP;
import nc.bs.bhs.sostore.ace.bp.AceSostoreApproveBP;
import nc.bs.bhs.sostore.ace.bp.AceSostoreUnApproveBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.bhs.sostore.AggSoStoreHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceSostorePubServiceImpl {
	// ����
	public AggSoStoreHVO[] pubinsertBills(AggSoStoreHVO[] clientFullVOs,
			AggSoStoreHVO[] originBills) throws BusinessException {
		try {
			// ���ݿ������ݺ�ǰ̨���ݹ����Ĳ���VO�ϲ���Ľ��
			BillTransferTool<AggSoStoreHVO> transferTool = new BillTransferTool<AggSoStoreHVO>(
					clientFullVOs);
			// ����BP
			AceSostoreInsertBP action = new AceSostoreInsertBP();
			AggSoStoreHVO[] retvos = action.insert(clientFullVOs);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// ɾ��
	public void pubdeleteBills(AggSoStoreHVO[] clientFullVOs,
			AggSoStoreHVO[] originBills) throws BusinessException {
		try {
			// ����BP
			new AceSostoreDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// �޸�
	public AggSoStoreHVO[] pubupdateBills(AggSoStoreHVO[] clientFullVOs,
			AggSoStoreHVO[] originBills) throws BusinessException {
		try {
			// ���� + ���ts
			BillTransferTool<AggSoStoreHVO> transferTool = new BillTransferTool<AggSoStoreHVO>(
					clientFullVOs);
			AceSostoreUpdateBP bp = new AceSostoreUpdateBP();
			AggSoStoreHVO[] retvos = bp.update(clientFullVOs, originBills);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public AggSoStoreHVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		AggSoStoreHVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<AggSoStoreHVO> query = new BillLazyQuery<AggSoStoreHVO>(
					AggSoStoreHVO.class);
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
	public AggSoStoreHVO[] pubsendapprovebills(
			AggSoStoreHVO[] clientFullVOs, AggSoStoreHVO[] originBills)
			throws BusinessException {
		AceSostoreSendApproveBP bp = new AceSostoreSendApproveBP();
		AggSoStoreHVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// �ջ�
	public AggSoStoreHVO[] pubunsendapprovebills(
			AggSoStoreHVO[] clientFullVOs, AggSoStoreHVO[] originBills)
			throws BusinessException {
		AceSostoreUnSendApproveBP bp = new AceSostoreUnSendApproveBP();
		AggSoStoreHVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// ����
	public AggSoStoreHVO[] pubapprovebills(AggSoStoreHVO[] clientFullVOs,
			AggSoStoreHVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceSostoreApproveBP bp = new AceSostoreApproveBP();
		AggSoStoreHVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// ����

	public AggSoStoreHVO[] pubunapprovebills(AggSoStoreHVO[] clientFullVOs,
			AggSoStoreHVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceSostoreUnApproveBP bp = new AceSostoreUnApproveBP();
		AggSoStoreHVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}