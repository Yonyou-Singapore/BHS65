package nc.uap.lfw.action.controller;

import nc.bs.framework.common.NCLocator;
import nc.uap.cpb.org.itf.ICpUserBill;
import nc.uap.cpb.org.vos.CpUserVO;
import nc.uap.lfw.action.ActionContext;
import nc.uap.lfw.action.cfg.RequestRoute;
import nc.uap.lfw.action.err.ActionException;
import nc.uap.lfw.action.view.JsonView;
import nc.uap.lfw.action.view.ModelData;
import nc.uap.lfw.action.view.RenderView;
import nc.uap.portal.user.impl.CpUser;
import uap.lfw.core.locator.AdapterServiceLocator;
import uap.lfw.portal.user.itf.IUserBill;


/**
 * Forgot Pwd Action
 * @author yonyou.jensen
 *
 */
public class RetrievePwdAction implements BaseAction{

	private IUserBill ub;

	@RequestRoute("/retrievePwd")
	public RenderView retrievePwd(ActionContext context, ModelData model)
	{
		JsonView jv = new JsonView();
		String userId = (String)model.getAttribute("userId");
		try
		{
			
			CpUser userVo =  (CpUser)getUserBill().getUser(userId);
			if (userVo==null || userVo.getPk_user()==null || userVo.getPk_user().length()<1)
			{
				jv.getModel().addAttribute("reason", "UserName Error");
				jv.getModel().addAttribute("oper", "failure");
			}
				
			/**
			 * ReestPwd
			 */
			String newPwd = ((ICpUserBill)NCLocator.getInstance().lookup(ICpUserBill.class)).resetCpUserPwd(userVo.getPk_user());
			
			jv.getModel().addAttribute("pwd", newPwd);
			jv.getModel().addAttribute("oper", "success");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			jv.getModel().addAttribute("reason", ActionException.toString(e));
			jv.getModel().addAttribute("oper", "failure");
		}
		return jv;
	}
	
	private IUserBill getUserBill() {
		if (ub == null) {
			ub = AdapterServiceLocator.newIns().get(IUserBill.class);
		}
		return ub;
	}
}
