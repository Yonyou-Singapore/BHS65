package nc.pubitf.bhs.api;

import nc.vo.bhs.blackbox.JobOrder;
import nc.vo.bhs.blackbox.PlanInfoVO;
import nc.vo.pub.BusinessException;

public interface IBlackBox {

	public JobOrder[] getBlackBox(PlanInfoVO planinfo) throws BusinessException;
	
	/**
	 * add chenth 20180412 替换视图v_joborders，改用接口提供joborder
	 * @param planinfo
	 * @return
	 * @throws BusinessException
	 */
	public JobOrder[] getJobOrder(PlanInfoVO planinfo) throws BusinessException;
	
}
