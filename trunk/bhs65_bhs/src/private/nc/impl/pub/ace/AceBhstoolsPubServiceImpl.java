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
	// 新增
	public AggBhsToolsHeadVO[] pubinsertBills(AggBhsToolsHeadVO[] clientFullVOs,
			AggBhsToolsHeadVO[] originBills) throws BusinessException {
		try {
			// 数据库中数据和前台传递过来的差异VO合并后的结果
			BillTransferTool<AggBhsToolsHeadVO> transferTool = new BillTransferTool<AggBhsToolsHeadVO>(
					clientFullVOs);
			// 调用BP
			AceBhstoolsInsertBP action = new AceBhstoolsInsertBP();
			AggBhsToolsHeadVO[] retvos = action.insert(clientFullVOs);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// 删除
	public void pubdeleteBills(AggBhsToolsHeadVO[] clientFullVOs,
			AggBhsToolsHeadVO[] originBills) throws BusinessException {
		try {
			// 调用BP
			new AceBhstoolsDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// 修改
	public AggBhsToolsHeadVO[] pubupdateBills(AggBhsToolsHeadVO[] clientFullVOs,
			AggBhsToolsHeadVO[] originBills) throws BusinessException {
		try {
			// 加锁 + 检查ts
			BillTransferTool<AggBhsToolsHeadVO> transferTool = new BillTransferTool<AggBhsToolsHeadVO>(
					clientFullVOs);
			AceBhstoolsUpdateBP bp = new AceBhstoolsUpdateBP();
			AggBhsToolsHeadVO[] retvos = bp.update(clientFullVOs, originBills);
			// 构造返回数据
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
	 * 由子类实现，查询之前对queryScheme进行加工，加入自己的逻辑
	 * 
	 * @param queryScheme
	 */
	protected void preQuery(IQueryScheme queryScheme) {
		// 查询之前对queryScheme进行加工，加入自己的逻辑
	}

	// 提交
	public AggBhsToolsHeadVO[] pubsendapprovebills(
			AggBhsToolsHeadVO[] clientFullVOs, AggBhsToolsHeadVO[] originBills)
			throws BusinessException {
		AceBhstoolsSendApproveBP bp = new AceBhstoolsSendApproveBP();
		AggBhsToolsHeadVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// 收回
	public AggBhsToolsHeadVO[] pubunsendapprovebills(
			AggBhsToolsHeadVO[] clientFullVOs, AggBhsToolsHeadVO[] originBills)
			throws BusinessException {
		AceBhstoolsUnSendApproveBP bp = new AceBhstoolsUnSendApproveBP();
		AggBhsToolsHeadVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// 审批
	public AggBhsToolsHeadVO[] pubapprovebills(AggBhsToolsHeadVO[] clientFullVOs,
			AggBhsToolsHeadVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceBhstoolsApproveBP bp = new AceBhstoolsApproveBP();
		AggBhsToolsHeadVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// 弃审

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