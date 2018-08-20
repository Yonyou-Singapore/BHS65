package nc.impl.pub.ace;

import nc.bs.bhs.sotruck.ace.bp.AceSotruckInsertBP;
import nc.bs.bhs.sotruck.ace.bp.AceSotruckUpdateBP;
import nc.bs.bhs.sotruck.ace.bp.AceSotruckDeleteBP;
import nc.bs.bhs.sotruck.ace.bp.AceSotruckSendApproveBP;
import nc.bs.bhs.sotruck.ace.bp.AceSotruckUnSendApproveBP;
import nc.bs.bhs.sotruck.ace.bp.AceSotruckApproveBP;
import nc.bs.bhs.sotruck.ace.bp.AceSotruckUnApproveBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.bhs.sotruck.AggSoTruckHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceSotruckPubServiceImpl {
	// ����
	public AggSoTruckHVO[] pubinsertBills(AggSoTruckHVO[] clientFullVOs,
			AggSoTruckHVO[] originBills) throws BusinessException {
		try {
			// ���ݿ������ݺ�ǰ̨���ݹ����Ĳ���VO�ϲ���Ľ��
			BillTransferTool<AggSoTruckHVO> transferTool = new BillTransferTool<AggSoTruckHVO>(
					clientFullVOs);
			// ����BP
			AceSotruckInsertBP action = new AceSotruckInsertBP();
			AggSoTruckHVO[] retvos = action.insert(clientFullVOs);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// ɾ��
	public void pubdeleteBills(AggSoTruckHVO[] clientFullVOs,
			AggSoTruckHVO[] originBills) throws BusinessException {
		try {
			// ����BP
			new AceSotruckDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// �޸�
	public AggSoTruckHVO[] pubupdateBills(AggSoTruckHVO[] clientFullVOs,
			AggSoTruckHVO[] originBills) throws BusinessException {
		try {
			// ���� + ���ts
			BillTransferTool<AggSoTruckHVO> transferTool = new BillTransferTool<AggSoTruckHVO>(
					clientFullVOs);
			AceSotruckUpdateBP bp = new AceSotruckUpdateBP();
			AggSoTruckHVO[] retvos = bp.update(clientFullVOs, originBills);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public AggSoTruckHVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		AggSoTruckHVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<AggSoTruckHVO> query = new BillLazyQuery<AggSoTruckHVO>(
					AggSoTruckHVO.class);
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
	public AggSoTruckHVO[] pubsendapprovebills(
			AggSoTruckHVO[] clientFullVOs, AggSoTruckHVO[] originBills)
			throws BusinessException {
		AceSotruckSendApproveBP bp = new AceSotruckSendApproveBP();
		AggSoTruckHVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// �ջ�
	public AggSoTruckHVO[] pubunsendapprovebills(
			AggSoTruckHVO[] clientFullVOs, AggSoTruckHVO[] originBills)
			throws BusinessException {
		AceSotruckUnSendApproveBP bp = new AceSotruckUnSendApproveBP();
		AggSoTruckHVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// ����
	public AggSoTruckHVO[] pubapprovebills(AggSoTruckHVO[] clientFullVOs,
			AggSoTruckHVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceSotruckApproveBP bp = new AceSotruckApproveBP();
		AggSoTruckHVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// ����

	public AggSoTruckHVO[] pubunapprovebills(AggSoTruckHVO[] clientFullVOs,
			AggSoTruckHVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceSotruckUnApproveBP bp = new AceSotruckUnApproveBP();
		AggSoTruckHVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}