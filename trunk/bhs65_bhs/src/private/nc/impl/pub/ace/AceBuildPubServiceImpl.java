package nc.impl.pub.ace;

import nc.bs.bhs.build.ace.bp.AceBuildInsertBP;
import nc.bs.bhs.build.ace.bp.AceBuildUpdateBP;
import nc.bs.bhs.build.ace.bp.AceBuildDeleteBP;
import nc.bs.bhs.build.ace.bp.AceBuildSendApproveBP;
import nc.bs.bhs.build.ace.bp.AceBuildUnSendApproveBP;
import nc.bs.bhs.build.ace.bp.AceBuildApproveBP;
import nc.bs.bhs.build.ace.bp.AceBuildUnApproveBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.bhs.build.AggSoOrderBuildHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceBuildPubServiceImpl {
	// 新增
	public AggSoOrderBuildHVO[] pubinsertBills(AggSoOrderBuildHVO[] clientFullVOs,
			AggSoOrderBuildHVO[] originBills) throws BusinessException {
		try {
			// 数据库中数据和前台传递过来的差异VO合并后的结果
			BillTransferTool<AggSoOrderBuildHVO> transferTool = new BillTransferTool<AggSoOrderBuildHVO>(
					clientFullVOs);
			// 调用BP
			AceBuildInsertBP action = new AceBuildInsertBP();
			AggSoOrderBuildHVO[] retvos = action.insert(clientFullVOs);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// 删除
	public void pubdeleteBills(AggSoOrderBuildHVO[] clientFullVOs,
			AggSoOrderBuildHVO[] originBills) throws BusinessException {
		try {
			// 调用BP
			new AceBuildDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// 修改
	public AggSoOrderBuildHVO[] pubupdateBills(AggSoOrderBuildHVO[] clientFullVOs,
			AggSoOrderBuildHVO[] originBills) throws BusinessException {
		try {
			// 加锁 + 检查ts
			BillTransferTool<AggSoOrderBuildHVO> transferTool = new BillTransferTool<AggSoOrderBuildHVO>(
					clientFullVOs);
			AceBuildUpdateBP bp = new AceBuildUpdateBP();
			AggSoOrderBuildHVO[] retvos = bp.update(clientFullVOs, originBills);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public AggSoOrderBuildHVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		AggSoOrderBuildHVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<AggSoOrderBuildHVO> query = new BillLazyQuery<AggSoOrderBuildHVO>(
					AggSoOrderBuildHVO.class);
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
	public AggSoOrderBuildHVO[] pubsendapprovebills(
			AggSoOrderBuildHVO[] clientFullVOs, AggSoOrderBuildHVO[] originBills)
			throws BusinessException {
		AceBuildSendApproveBP bp = new AceBuildSendApproveBP();
		AggSoOrderBuildHVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// 收回
	public AggSoOrderBuildHVO[] pubunsendapprovebills(
			AggSoOrderBuildHVO[] clientFullVOs, AggSoOrderBuildHVO[] originBills)
			throws BusinessException {
		AceBuildUnSendApproveBP bp = new AceBuildUnSendApproveBP();
		AggSoOrderBuildHVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// 审批
	public AggSoOrderBuildHVO[] pubapprovebills(AggSoOrderBuildHVO[] clientFullVOs,
			AggSoOrderBuildHVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceBuildApproveBP bp = new AceBuildApproveBP();
		AggSoOrderBuildHVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// 弃审

	public AggSoOrderBuildHVO[] pubunapprovebills(AggSoOrderBuildHVO[] clientFullVOs,
			AggSoOrderBuildHVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceBuildUnApproveBP bp = new AceBuildUnApproveBP();
		AggSoOrderBuildHVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}