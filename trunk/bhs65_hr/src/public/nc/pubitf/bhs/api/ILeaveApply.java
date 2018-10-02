package nc.pubitf.bhs.api;

import nc.vo.bhs.leave.Leave;
import nc.vo.pub.BusinessException;

public interface ILeaveApply {
	boolean leaveApply(Leave leaveVo) throws BusinessException;

	boolean leaveCancel(Leave leaveVo) throws BusinessException;

}
