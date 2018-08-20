package nc.impl.pub.ace;

import nc.bs.bhs.bhsquality.ace.bp.AceBhsqualityInsertBP;
import nc.bs.bhs.bhsquality.ace.bp.AceBhsqualityUpdateBP;
import nc.bs.bhs.bhsquality.ace.bp.AceBhsqualityDeleteBP;
import nc.bs.bhs.bhsquality.ace.bp.AceBhsqualitySendApproveBP;
import nc.bs.bhs.bhsquality.ace.bp.AceBhsqualityUnSendApproveBP;
import nc.bs.bhs.bhsquality.ace.bp.AceBhsqualityApproveBP;
import nc.bs.bhs.bhsquality.ace.bp.AceBhsqualityUnApproveBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.bhs.bhsquality.AggBhsPoHeadVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceBhsqualityPubServiceImpl {
	// 新增
	public AggBhsPoHeadVO[] pubinsertBills(AggBhsPoHeadVO[] clientFullVOs,
			AggBhsPoHeadVO[] originBills) throws BusinessException {
		try {
			// 数据库中数据和前台传递过来的差异VO合并后的结果
			BillTransferTool<AggBhsPoHeadVO> transferTool = new BillTransferTool<AggBhsPoHeadVO>(
					clientFullVOs);
			// 调用BP
			AceBhsqualityInsertBP action = new AceBhsqualityInsertBP();
			AggBhsPoHeadVO[] retvos = action.insert(clientFullVOs);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// 删除
	public void pubdeleteBills(AggBhsPoHeadVO[] clientFullVOs,
			AggBhsPoHeadVO[] originBills) throws BusinessException {
		try {
			// 调用BP
			new AceBhsqualityDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// 修改
	public AggBhsPoHeadVO[] pubupdateBills(AggBhsPoHeadVO[] clientFullVOs,
			AggBhsPoHeadVO[] originBills) throws BusinessException {
		try {
			// 加锁 + 检查ts
			BillTransferTool<AggBhsPoHeadVO> transferTool = new BillTransferTool<AggBhsPoHeadVO>(
					clientFullVOs);
			AceBhsqualityUpdateBP bp = new AceBhsqualityUpdateBP();
			AggBhsPoHeadVO[] retvos = bp.update(clientFullVOs, originBills);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public AggBhsPoHeadVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		AggBhsPoHeadVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<AggBhsPoHeadVO> query = new BillLazyQuery<AggBhsPoHeadVO>(
					AggBhsPoHeadVO.class);
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
	public AggBhsPoHeadVO[] pubsendapprovebills(
			AggBhsPoHeadVO[] clientFullVOs, AggBhsPoHeadVO[] originBills)
			throws BusinessException {
		AceBhsqualitySendApproveBP bp = new AceBhsqualitySendApproveBP();
		AggBhsPoHeadVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// 收回
	public AggBhsPoHeadVO[] pubunsendapprovebills(
			AggBhsPoHeadVO[] clientFullVOs, AggBhsPoHeadVO[] originBills)
			throws BusinessException {
		AceBhsqualityUnSendApproveBP bp = new AceBhsqualityUnSendApproveBP();
		AggBhsPoHeadVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// 审批
	public AggBhsPoHeadVO[] pubapprovebills(AggBhsPoHeadVO[] clientFullVOs,
			AggBhsPoHeadVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceBhsqualityApproveBP bp = new AceBhsqualityApproveBP();
		AggBhsPoHeadVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// 弃审

	public AggBhsPoHeadVO[] pubunapprovebills(AggBhsPoHeadVO[] clientFullVOs,
			AggBhsPoHeadVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceBhsqualityUnApproveBP bp = new AceBhsqualityUnApproveBP();
		AggBhsPoHeadVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}