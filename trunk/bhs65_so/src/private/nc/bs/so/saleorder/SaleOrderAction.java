package nc.bs.so.saleorder;

import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.so.m30.entity.SaleOrderHVO;

/**
 * ���۶�����������¼�
 * @author Thinkpad
 * 20170506
 */
public class SaleOrderAction {

	public SaleOrderAction() {
		// TODO �Զ����ɵĹ��캯�����
	}
	/**
	 * ��˺��¼�
	 * �������ĵ���ȫ�����
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
		//delete chenth 20181214 store��job ���Զ�����
//		updateStoreStatus(csaleorderid, dapprovedate, cuserid, true);
		updateStruckStatus(csaleorderid, dapprovedate, cuserid, true);
		
	}
	
	/**
	 * ȡ������¼�
	 *�������ĵ���ȫ��ȡ�����
	 * @param aggvo
	 * @throws BusinessException 
	 */
	public void afterUnApproveAction(AggregatedValueObject aggvo) throws BusinessException{
		
		SaleOrderHVO headvo = (SaleOrderHVO) aggvo.getParentVO();
		String csaleorderid = headvo.getCsaleorderid();
		updateBuildStatus(csaleorderid, null, null, false);
		updatePackStatus(csaleorderid, null, null, false);
		updateMoveStatus(csaleorderid, null, null, false);
		//delete chenth 20181214 store��job ���Զ�����
//		updateStoreStatus(csaleorderid, null, null, false);
		updateStruckStatus(csaleorderid, null, null, false);
		
	}
	/**
	 * ����build״̬
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
			// TODO �Զ����ɵ� catch ��
			throw new BusinessException(e.getMessage());
		}
	}
	/**
	 * ����pack״̬
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
			// TODO �Զ����ɵ� catch ��
			throw new BusinessException(e.getMessage());
		}
	}
	
	/**
	 * ����Move״̬
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
			// TODO �Զ����ɵ� catch ��
			throw new BusinessException(e.getMessage());
		}
	}
	
	/**
	 * ����Store״̬
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
			// TODO �Զ����ɵ� catch ��
			throw new BusinessException(e.getMessage());
		}
	}
	
	/**
	 * ����Struck״̬
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
			// TODO �Զ����ɵ� catch ��
			throw new BusinessException(e.getMessage());
		}
	}
	
	
}
