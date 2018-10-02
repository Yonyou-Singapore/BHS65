package nc.impl.bhs.api;

import java.util.TimeZone;

import nc.bs.dao.BaseDAO;
import nc.bs.framework.common.NCLocator;
import nc.itf.ta.ILeaveRegisterInfoDisplayer;
import nc.itf.ta.ILeaveRegisterManageMaintain;
import nc.itf.ta.ILeaveRegisterQueryMaintain;
import nc.jdbc.framework.SQLParameter;
import nc.jdbc.framework.processor.BeanProcessor;
import nc.pubitf.bhs.api.ILeaveApply;
import nc.vo.bhs.leave.EmployeeVO;
import nc.vo.bhs.leave.Leave;
import nc.vo.bhs.leave.LeaveTypeVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFLiteralDate;
import nc.vo.ta.leave.LeaveCheckResult;
import nc.vo.ta.leave.LeaveRegVO;
import nc.vo.ta.leave.SplitBillResult;

public class LeaveApplyImpl implements ILeaveApply {

	@Override
	public boolean leaveApply(Leave leaveVo) throws BusinessException {
		LeaveRegVO vo = translateToLeaveRegVO(leaveVo);
		ILeaveRegisterQueryMaintain maintain = NCLocator.getInstance()
				.lookup(ILeaveRegisterQueryMaintain.class);
		LeaveCheckResult<LeaveRegVO> checkResult = maintain
				.checkWhenSave(vo);

		SplitBillResult<LeaveRegVO> splitResult = checkResult
				.getSplitResult();
		LeaveRegVO[] addResult = (LeaveRegVO[]) getManageMaintain()
				.insertData(splitResult);

		for (LeaveRegVO addVO : addResult) {
			directlyAdd(addVO);
		}
		return true;
	}

	private void directlyAdd(LeaveRegVO addVO) {
		new nc.vo.bd.meta.BDObjectAdpaterFactory().createBDObject(addVO);
	}

	private LeaveRegVO translateToLeaveRegVO(Leave leaveVo)
			throws BusinessException {
		String employeecode = leaveVo.getEmployeecode();
		EmployeeVO employee = getEmployeeVO(employeecode);
		String leavetypecode = leaveVo.getLeavetypecode();
		LeaveTypeVO leaveTypeVo = getLeaveTypeVO(leavetypecode,
				employee.getPk_org());

		LeaveRegVO vo = new LeaveRegVO();
		vo.setPk_leavereg(leaveVo.getLeaveapplyid());
		vo.setPk_leavetype(leaveTypeVo.getPk_leavetype());
		vo.setPk_leavetypecopy(leaveTypeVo.getPk_leavetypecopy());
		vo.setPk_psndoc(employee.getPk_psndoc());
		vo.setPk_adminorg(employee.getPk_adminorg());
		vo.setPk_group(employee.getPk_group());
		vo.setPk_org(employee.getPk_org());
		vo.setPk_org_v(employee.getPk_org_v());
		vo.setPk_dept_v(employee.getPk_dept_v());
		vo.setPk_psnjob(employee.getPk_psnjob());
		vo.setPk_psnorg(employee.getPk_psnorg());
		vo.setCreator(employee.getCuserid());
		vo.setCreationtime(new UFDateTime());
		vo.setLeaveremark(leaveVo.getRemarks());
		vo.setBillsource(2);

		vo.setLeavebegintime(new UFDateTime(leaveVo.getBegintime()));
		vo.setLeaveendtime(new UFDateTime(leaveVo.getEndtime()));
		vo.setLeavebegindate(new UFLiteralDate(leaveVo.getBegintime()));
		vo.setLeaveenddate(new UFLiteralDate(leaveVo.getEndtime()));

		ILeaveRegisterInfoDisplayer displayer = NCLocator.getInstance().lookup(
				ILeaveRegisterInfoDisplayer.class);
		vo = displayer.calculate(vo, TimeZone.getDefault());

		return vo;
	}

	private LeaveTypeVO getLeaveTypeVO(String leavetypecode, String pk_org)
			throws BusinessException {
		StringBuffer sb = new StringBuffer();
		sb.append(" select t.pk_timeitem as pk_leavetype, c.pk_timeitemcopy as pk_leavetypecopy, t.timeitemcode leavetypecode, t.timeitemname leavetypename, c.pk_org ");
		sb.append(" from tbm_timeitem t  ");
		sb.append(" inner join tbm_timeitemcopy c on t.pk_timeitem = c.pk_timeitem ");
		sb.append(" where c.enablestate in ( 2 , 1 )  and c.itemtype = 0  ");
		sb.append(" and t.timeitemcode = ? ");
		sb.append(" and c.pk_org = ? ");
		BaseDAO dao = new BaseDAO();
		SQLParameter parameter = new SQLParameter();
		parameter.addParam(leavetypecode);
		parameter.addParam(pk_org);
		LeaveTypeVO typeVO = (LeaveTypeVO) dao.executeQuery(sb.toString(),
				parameter, new BeanProcessor(LeaveTypeVO.class));
		return typeVO;
	}

	private EmployeeVO getEmployeeVO(String employeecode)
			throws BusinessException {
		StringBuffer sb = new StringBuffer();
		sb.append(" select u.cuserid, p.pk_hrorg as pk_adminorg, d.pk_vid as pk_dept_v, p.pk_group,p.pk_psndoc, j.pk_psnjob, j.pk_psnorg, j.pk_org, o.pk_vid as pk_org_v,p.code as employeecode, p.name as employeename ");
		sb.append(" from bd_psndoc p  ");
		sb.append(" left join sm_user u on p.pk_psndoc = u.pk_psndoc ");
		sb.append(" inner join hi_psnjob j on (p.pk_psndoc = j.pk_psndoc and lastflag = 'Y' and poststat='Y' and ismainjob='Y') ");
		sb.append(" inner join org_orgs_v o on j.pk_org = o.pk_org ");
		sb.append(" inner join org_dept_v d on j.pk_dept = d.pk_dept ");
		sb.append(" where p.code=? ");
		BaseDAO dao = new BaseDAO();
		SQLParameter parameter = new SQLParameter();
		parameter.addParam(employeecode);
		EmployeeVO typeVO = (EmployeeVO) dao.executeQuery(sb.toString(),
				parameter, new BeanProcessor(EmployeeVO.class));
		return typeVO;
	}

	public ILeaveRegisterManageMaintain getManageMaintain() {
		return NCLocator.getInstance().lookup(
				ILeaveRegisterManageMaintain.class);
	}

	@Override
	public boolean leaveCancel(Leave leaveVo) throws BusinessException {
		LeaveRegVO[] vos = NCLocator.getInstance()
				.lookup(ILeaveRegisterQueryMaintain.class)
				.queryByPks(new String[] { leaveVo.getLeaveapplyid() });
		;
		getManageMaintain().deleteArrayData(vos);
		return true;
	}

}
