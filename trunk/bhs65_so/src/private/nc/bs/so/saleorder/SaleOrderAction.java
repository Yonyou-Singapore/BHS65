package nc.bs.so.saleorder;

import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.so.m30.entity.SaleOrderHVO;

/**
 * 销售订单审核弃审事件
 * @author Thinkpad
 * 20170506
 */
public class SaleOrderAction {

	public SaleOrderAction() {
		// TODO 自动生成的构造函数存根
	}
	/**
	 * 审核后事件
	 * 将关联的单据全部审核
	 * @param aggvo
	 * @throws BusinessException 
	 */
	public void afterApproveAction(AggregatedValueObject aggvo) throws BusinessException{
		
		SaleOrderHVO headvo = (SaleOrderHVO) aggvo.getParentVO();
		String csaleorderid = headvo.getCsaleorderid();
		UFDate dapprovedate = headvo.getTaudittime();
		String cuserid = headvo.getApprover();
		updateBuildStatus(csaleorderid, dapprovedate, cuserid, true);
		updatePackStatus(csaleorderid, dapprovedate, cuserid, true);
		updateMoveStatus(csaleorderid, dapprovedate, cuserid, true);
		//delete chenth 20181214 store的job 不自动审批
//		updateStoreStatus(csaleorderid, dapprovedate, cuserid, true);
		updateStruckStatus(csaleorderid, dapprovedate, cuserid, true);
		
	}
	
	/**
	 * 取消审核事件
	 *将关联的单据全部取消审核
	 * @param aggvo
	 * @throws BusinessException 
	 */
	public void afterUnApproveAction(AggregatedValueObject aggvo) throws BusinessException{
		
		SaleOrderHVO headvo = (SaleOrderHVO) aggvo.getParentVO();
		String csaleorderid = headvo.getCsaleorderid();
		updateBuildStatus(csaleorderid, null, null, false);
		updatePackStatus(csaleorderid, null, null, false);
		updateMoveStatus(csaleorderid, null, null, false);
		//delete chenth 20181214 store的job 不自动审批
//		updateStoreStatus(csaleorderid, null, null, false);
		updateStruckStatus(csaleorderid, null, null, false);
		
	}
	/**
	 * 更新build状态
	 * @param csaleorderid
	 * @param dapprovedate
	 * @param cuserid
	 * @throws BusinessException
	 */
	public void updateBuildStatus(String csaleorderid,UFDate dapprovedate,String cuserid,boolean isApprove) throws BusinessException{
		String sql = "";
		if(isApprove){
			sql = " update bhs_build_h set  vbillstatus = 1,approver = '"+cuserid+"',approvedate = '"+dapprovedate.toString()+"' where csaleorderid = '"+csaleorderid+"' and isnull(dr,0) = 0";
		}else{
			sql = " update bhs_build_h set  vbillstatus = -1,approver = '~',approvedate = null where csaleorderid = '"+csaleorderid+"' and isnull(dr,0) = 0";
		}
		try {
			new BaseDAO().executeUpdate(sql);
		} catch (DAOException e) {
			// TODO 自动生成的 catch 块
			throw new BusinessException(e.getMessage());
		}
	}
	/**
	 * 更新pack状态
	 * @param csaleorderid
	 * @param dapprovedate
	 * @param cuserid
	 * @throws BusinessException
	 */
	public void updatePackStatus(String csaleorderid,UFDate dapprovedate,String cuserid,boolean isApprove) throws BusinessException{
		String sql = "";
		if(isApprove){
			sql = " update bhs_pack_h set  vbillstatus = 1,approver = '"+cuserid+"',approvedate = '"+dapprovedate.toString()+"' where csaleorderid = '"+csaleorderid+"' and isnull(dr,0) = 0";
		}else{
			sql = " update bhs_pack_h set  vbillstatus = -1,approver = '~',approvedate = null where csaleorderid = '"+csaleorderid+"' and isnull(dr,0) = 0";
		}
		try {
			new BaseDAO().executeUpdate(sql);
		} catch (DAOException e) {
			// TODO 自动生成的 catch 块
			throw new BusinessException(e.getMessage());
		}
	}
	
	/**
	 * 更新Move状态
	 * @param csaleorderid
	 * @param dapprovedate
	 * @param cuserid
	 * @throws BusinessException
	 */
	public void updateMoveStatus(String csaleorderid,UFDate dapprovedate,String cuserid,boolean isApprove) throws BusinessException{
		String sql = "";
		if(isApprove){
			sql = " update bhs_somove_h set  vbillstatus = 1,approver = '"+cuserid+"',approvedate = '"+dapprovedate.toString()+"' where csaleorderid = '"+csaleorderid+"' and isnull(dr,0) = 0";
		}else{
			sql = " update bhs_somove_h set  vbillstatus = -1,approver = '~',approvedate = null where csaleorderid = '"+csaleorderid+"' and isnull(dr,0) = 0";
		}
		try {
			new BaseDAO().executeUpdate(sql);
		} catch (DAOException e) {
			// TODO 自动生成的 catch 块
			throw new BusinessException(e.getMessage());
		}
	}
	
	/**
	 * 更新Store状态
	 * @param csaleorderid
	 * @param dapprovedate
	 * @param cuserid
	 * @throws BusinessException
	 */
	public void updateStoreStatus(String csaleorderid,UFDate dapprovedate,String cuserid,boolean isApprove) throws BusinessException{
		String sql = "";
		if(isApprove){
			sql = " update bhs_sostore_h set  vbillstatus = 1,approver = '"+cuserid+"',approvedate = '"+dapprovedate.toString()+"' where csaleorderid = '"+csaleorderid+"' and isnull(dr,0) = 0";
		}else{
			sql = " update bhs_sostore_h set  vbillstatus = -1,approver = '~',approvedate = null where csaleorderid = '"+csaleorderid+"' and isnull(dr,0) = 0";
		}
		try {
			new BaseDAO().executeUpdate(sql);
		} catch (DAOException e) {
			// TODO 自动生成的 catch 块
			throw new BusinessException(e.getMessage());
		}
	}
	
	/**
	 * 更新Struck状态
	 * @param csaleorderid
	 * @param dapprovedate
	 * @param cuserid
	 * @throws BusinessException
	 */
	public void updateStruckStatus(String csaleorderid,UFDate dapprovedate,String cuserid,boolean isApprove) throws BusinessException{
		String sql = "";
		if(isApprove){
			sql = " update bhs_sotruck_h set  vbillstatus = 1,approver = '"+cuserid+"',approvedate = '"+dapprovedate.toString()+"' where csaleorderid = '"+csaleorderid+"' and isnull(dr,0) = 0";
		}else{
			sql = " update bhs_sotruck_h set  vbillstatus = -1,approver = '~',approvedate = null where csaleorderid = '"+csaleorderid+"' and isnull(dr,0) = 0";
		}
		try {
			new BaseDAO().executeUpdate(sql);
		} catch (DAOException e) {
			// TODO 自动生成的 catch 块
			throw new BusinessException(e.getMessage());
		}
	}
	
	
}
