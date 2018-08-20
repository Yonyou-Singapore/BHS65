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
	// 新增
	public AggBhsDoVO[] pubinsertBills(AggBhsDoVO[] clientFullVOs,
			AggBhsDoVO[] originBills) throws BusinessException {
		try {
			// 数据库中数据和前台传递过来的差异VO合并后的结果
			BillTransferTool<AggBhsDoVO> transferTool = new BillTransferTool<AggBhsDoVO>(
					clientFullVOs);
			// 调用BP
			AceBhsdoInsertBP action = new AceBhsdoInsertBP();
			AggBhsDoVO[] retvos = action.insert(clientFullVOs);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// 删除
	public void pubdeleteBills(AggBhsDoVO[] clientFullVOs,
			AggBhsDoVO[] originBills) throws BusinessException {
		try {
			// 调用BP
			new AceBhsdoDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// 修改
	public AggBhsDoVO[] pubupdateBills(AggBhsDoVO[] clientFullVOs,
			AggBhsDoVO[] originBills) throws BusinessException {
		try {
			// 加锁 + 检查ts
			BillTransferTool<AggBhsDoVO> transferTool = new BillTransferTool<AggBhsDoVO>(
					clientFullVOs);
			AceBhsdoUpdateBP bp = new AceBhsdoUpdateBP();
			AggBhsDoVO[] retvos = bp.update(clientFullVOs, originBills);
			// 构造返回数据
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
	 * 由子类实现，查询之前对queryScheme进行加工，加入自己的逻辑
	 * 
	 * @param queryScheme
	 */
	protected void preQuery(IQueryScheme queryScheme) {
		// 查询之前对queryScheme进行加工，加入自己的逻辑
	}

	// 提交
	public AggBhsDoVO[] pubsendapprovebills(
			AggBhsDoVO[] clientFullVOs, AggBhsDoVO[] originBills)
			throws BusinessException {
		AceBhsdoSendApproveBP bp = new AceBhsdoSendApproveBP();
		AggBhsDoVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// 收回
	public AggBhsDoVO[] pubunsendapprovebills(
			AggBhsDoVO[] clientFullVOs, AggBhsDoVO[] originBills)
			throws BusinessException {
		AceBhsdoUnSendApproveBP bp = new AceBhsdoUnSendApproveBP();
		AggBhsDoVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// 审批
	public AggBhsDoVO[] pubapprovebills(AggBhsDoVO[] clientFullVOs,
			AggBhsDoVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceBhsdoApproveBP bp = new AceBhsdoApproveBP();
		AggBhsDoVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// 弃审

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