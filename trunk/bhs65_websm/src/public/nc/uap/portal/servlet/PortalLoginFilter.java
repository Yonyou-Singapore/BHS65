package nc.uap.portal.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nc.uap.cpb.org.itf.ICpSysinitQry;
import nc.uap.lfw.action.ActionConstants;
import nc.uap.lfw.core.LfwRuntimeEnvironment;
import nc.uap.lfw.login.vo.LfwSessionBean;
import nc.uap.portal.deploy.vo.PtSessionBean;
import nc.uap.portal.log.PortalLogger;
import nc.uap.portal.login.filter.AbstractLfwLoginFilter;
import nc.uap.portal.login.itf.LoginHelper;
import nc.uap.portal.login.util.LfwLoginFetcher;
import nc.uap.portal.service.PortalServiceUtil;
import nc.uap.portal.user.chain.UserVerifyChainCycle;
import nc.uap.portal.util.PortalDsnameFetcher;

import org.apache.commons.lang.StringUtils;

import uap.lfw.portal.user.ForceLoginTools;

/**
 * Portal登陆过滤器
 * 
 * @author licza
 * 
 */
public class PortalLoginFilter extends AbstractLfwLoginFilter {

	
	public static final String PORTAL_SYS_TYPE = "pt"; 

	@Override
	protected String getSysType() {
		return PORTAL_SYS_TYPE;
	}

	/**
	 * 判断用户是否已经登陆 加入匿名用户支持
	 */
	protected boolean isUserLogin(HttpServletRequest request,
			HttpServletResponse response) {
		if (request.getRequestURI().indexOf(ActionConstants.RESPONSE_PATH)>0  && isAjax(request) )
		{
			request.setAttribute(ActionConstants.ATTR_ORIGIN_REQUESTURL, request.getRequestURI());
			return true;
		}
		
		
		boolean isLoginflag = hasLogin();
		
		/**
		 * 已经登陆
		 */
		if (isLoginflag)
			return isLoginflag;
		if(request.getParameter("isAjax") != null)
			return false;
		/**
		 * 设置ds
		 */
		setDataSouceName();
		try {
			UserVerifyChainCycle.verify();
			/**
			 * 是否登陆成功
			 */
			return hasLogin();
		} catch (Exception e) {
			PortalLogger.error(e.getMessage(), e);
		}
		return false;
	}

	/**
	 * 是否登陆
	 * 
	 * @return
	 */
	private boolean hasLogin() {
		LfwSessionBean sb = LfwRuntimeEnvironment.getLfwSessionBean();
		if(sb != null){
			ensureSessionTimeOut(sb.getTimespan());
			ForceLoginTools.validator(LfwRuntimeEnvironment.getWebContext().getRequest(), LfwRuntimeEnvironment.getWebContext().getResponse());
		}
		return LfwRuntimeEnvironment.getLfwSessionBean() != null;
	}
	/**
	 * 会话安全验证
	 * 在登录系统之后，如果会话建立时间超过绝对会话过期时间，强行会话过期
	 * @param ts
	 */
	private void ensureSessionTimeOut(long ts){
		ICpSysinitQry sysInitQry = PortalServiceUtil.getCpSysinitQry();
		//是否显示
		int time = -1;	
		try {
			 String sessionTimeOut = sysInitQry.getSysinitValueByCodeAndPkorg("sessionTimeOut",null);
			 if(sessionTimeOut != null && sessionTimeOut.length() > 0 && StringUtils.isNumeric(sessionTimeOut)){
				 time = Integer.parseInt(sessionTimeOut);
				 if(ts + (time * 1000) <  System.currentTimeMillis()){
					 LfwRuntimeEnvironment.getWebContext().getRequest().getSession().invalidate();
					 LfwRuntimeEnvironment.setLfwSessionBean(null);
				 }
			 }
		} catch (Throwable e) {
			 nc.uap.portal.log.PortalLogger.error(e.getMessage(), e);
		}
	}
	

	/**
	 * 设置数据源名称
	 */
	private void setDataSouceName() {
		String dsName = LfwRuntimeEnvironment.getDatasource();
		if (dsName == null)
			dsName = PortalDsnameFetcher.getPortalDsName();
		LfwRuntimeEnvironment.setDatasource(dsName);
	}

	public LoginHelper<PtSessionBean> getLoginHelper() {
		return (LoginHelper<PtSessionBean>) LfwLoginFetcher.getGeneralInstance().getLoginHelper();
	}
	
	private boolean isAjax(HttpServletRequest request)
	{
		   boolean ajax = "XMLHttpRequest".equals( request.getHeader("X-Requested-With") );
	    String ajaxFlag = null == request.getParameter("ajax") ?  "false": request.getParameter("ajax") ;
	   return ajax || ajaxFlag.equalsIgnoreCase("true");
	}
}
