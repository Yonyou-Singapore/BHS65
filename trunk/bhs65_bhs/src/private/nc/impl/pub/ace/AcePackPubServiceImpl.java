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
	// 新增
	public AggSoOrderPackVO[] pubinsertBills(AggSoOrderPackVO[] clientFullVOs,
			AggSoOrderPackVO[] originBills) throws BusinessException {
		try {
			// 数据库中数据和前台传递过来的差异VO合并后的结果
			BillTransferTool<AggSoOrderPackVO> transferTool = new BillTransferTool<AggSoOrderPackVO>(
					clientFullVOs);
			// 调用BP
			AcePackInsertBP action = new AcePackInsertBP();
			AggSoOrderPackVO[] retvos = action.insert(clientFullVOs);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// 删除
	public void pubdeleteBills(AggSoOrderPackVO[] clientFullVOs,
			AggSoOrderPackVO[] originBills) throws BusinessException {
		try {
			// 调用BP
			new AcePackDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// 修改
	public AggSoOrderPackVO[] pubupdateBills(AggSoOrderPackVO[] clientFullVOs,
			AggSoOrderPackVO[] originBills) throws BusinessException {
		try {
			// 加锁 + 检查ts
			BillTransferTool<AggSoOrderPackVO> transferTool = new BillTransferTool<AggSoOrderPackVO>(
					clientFullVOs);
			AcePackUpdateBP bp = new AcePackUpdateBP();
			AggSoOrderPackVO[] retvos = bp.update(clientFullVOs, originBills);
			// 构造返回数据
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
	 * 由子类实现，查询之前对queryScheme进行加工，加入自己的逻辑
	 * 
	 * @param queryScheme
	 */
	protected void preQuery(IQueryScheme queryScheme) {
		// 查询之前对queryScheme进行加工，加入自己的逻辑
	}

	// 提交
	public AggSoOrderPackVO[] pubsendapprovebills(
			AggSoOrderPackVO[] clientFullVOs, AggSoOrderPackVO[] originBills)
			throws BusinessException {
		AcePackSendApproveBP bp = new AcePackSendApproveBP();
		AggSoOrderPackVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// 收回
	public AggSoOrderPackVO[] pubunsendapprovebills(
			AggSoOrderPackVO[] clientFullVOs, AggSoOrderPackVO[] originBills)
			throws BusinessException {
		AcePackUnSendApproveBP bp = new AcePackUnSendApproveBP();
		AggSoOrderPackVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// 审批
	public AggSoOrderPackVO[] pubapprovebills(AggSoOrderPackVO[] clientFullVOs,
			AggSoOrderPackVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AcePackApproveBP bp = new AcePackApproveBP();
		AggSoOrderPackVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// 弃审

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