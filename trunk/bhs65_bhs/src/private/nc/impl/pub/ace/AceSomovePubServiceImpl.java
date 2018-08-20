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
	// 新增
	public AggSoMoveHVO[] pubinsertBills(AggSoMoveHVO[] clientFullVOs,
			AggSoMoveHVO[] originBills) throws BusinessException {
		try {
			// 数据库中数据和前台传递过来的差异VO合并后的结果
			BillTransferTool<AggSoMoveHVO> transferTool = new BillTransferTool<AggSoMoveHVO>(
					clientFullVOs);
			// 调用BP
			AceSomoveInsertBP action = new AceSomoveInsertBP();
			AggSoMoveHVO[] retvos = action.insert(clientFullVOs);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// 删除
	public void pubdeleteBills(AggSoMoveHVO[] clientFullVOs,
			AggSoMoveHVO[] originBills) throws BusinessException {
		try {
			// 调用BP
			new AceSomoveDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// 修改
	public AggSoMoveHVO[] pubupdateBills(AggSoMoveHVO[] clientFullVOs,
			AggSoMoveHVO[] originBills) throws BusinessException {
		try {
			// 加锁 + 检查ts
			BillTransferTool<AggSoMoveHVO> transferTool = new BillTransferTool<AggSoMoveHVO>(
					clientFullVOs);
			AceSomoveUpdateBP bp = new AceSomoveUpdateBP();
			AggSoMoveHVO[] retvos = bp.update(clientFullVOs, originBills);
			// 构造返回数据
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
	 * 由子类实现，查询之前对queryScheme进行加工，加入自己的逻辑
	 * 
	 * @param queryScheme
	 */
	protected void preQuery(IQueryScheme queryScheme) {
		// 查询之前对queryScheme进行加工，加入自己的逻辑
	}

	// 提交
	public AggSoMoveHVO[] pubsendapprovebills(
			AggSoMoveHVO[] clientFullVOs, AggSoMoveHVO[] originBills)
			throws BusinessException {
		AceSomoveSendApproveBP bp = new AceSomoveSendApproveBP();
		AggSoMoveHVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// 收回
	public AggSoMoveHVO[] pubunsendapprovebills(
			AggSoMoveHVO[] clientFullVOs, AggSoMoveHVO[] originBills)
			throws BusinessException {
		AceSomoveUnSendApproveBP bp = new AceSomoveUnSendApproveBP();
		AggSoMoveHVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// 审批
	public AggSoMoveHVO[] pubapprovebills(AggSoMoveHVO[] clientFullVOs,
			AggSoMoveHVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceSomoveApproveBP bp = new AceSomoveApproveBP();
		AggSoMoveHVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// 弃审

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