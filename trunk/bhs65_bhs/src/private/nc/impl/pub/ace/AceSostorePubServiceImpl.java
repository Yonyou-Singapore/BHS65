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
	// 新增
	public AggSoStoreHVO[] pubinsertBills(AggSoStoreHVO[] clientFullVOs,
			AggSoStoreHVO[] originBills) throws BusinessException {
		try {
			// 数据库中数据和前台传递过来的差异VO合并后的结果
			BillTransferTool<AggSoStoreHVO> transferTool = new BillTransferTool<AggSoStoreHVO>(
					clientFullVOs);
			// 调用BP
			AceSostoreInsertBP action = new AceSostoreInsertBP();
			AggSoStoreHVO[] retvos = action.insert(clientFullVOs);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// 删除
	public void pubdeleteBills(AggSoStoreHVO[] clientFullVOs,
			AggSoStoreHVO[] originBills) throws BusinessException {
		try {
			// 调用BP
			new AceSostoreDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// 修改
	public AggSoStoreHVO[] pubupdateBills(AggSoStoreHVO[] clientFullVOs,
			AggSoStoreHVO[] originBills) throws BusinessException {
		try {
			// 加锁 + 检查ts
			BillTransferTool<AggSoStoreHVO> transferTool = new BillTransferTool<AggSoStoreHVO>(
					clientFullVOs);
			AceSostoreUpdateBP bp = new AceSostoreUpdateBP();
			AggSoStoreHVO[] retvos = bp.update(clientFullVOs, originBills);
			// 构造返回数据
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
	 * 由子类实现，查询之前对queryScheme进行加工，加入自己的逻辑
	 * 
	 * @param queryScheme
	 */
	protected void preQuery(IQueryScheme queryScheme) {
		// 查询之前对queryScheme进行加工，加入自己的逻辑
	}

	// 提交
	public AggSoStoreHVO[] pubsendapprovebills(
			AggSoStoreHVO[] clientFullVOs, AggSoStoreHVO[] originBills)
			throws BusinessException {
		AceSostoreSendApproveBP bp = new AceSostoreSendApproveBP();
		AggSoStoreHVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// 收回
	public AggSoStoreHVO[] pubunsendapprovebills(
			AggSoStoreHVO[] clientFullVOs, AggSoStoreHVO[] originBills)
			throws BusinessException {
		AceSostoreUnSendApproveBP bp = new AceSostoreUnSendApproveBP();
		AggSoStoreHVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// 审批
	public AggSoStoreHVO[] pubapprovebills(AggSoStoreHVO[] clientFullVOs,
			AggSoStoreHVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceSostoreApproveBP bp = new AceSostoreApproveBP();
		AggSoStoreHVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// 弃审

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