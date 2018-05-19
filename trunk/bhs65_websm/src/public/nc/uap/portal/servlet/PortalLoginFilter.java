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
 * Portal��½������
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
	 * �ж��û��Ƿ��Ѿ���½ ���������û�֧��
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
		 * �Ѿ���½
		 */
		if (isLoginflag)
			return isLoginflag;
		if(request.getParameter("isAjax") != null)
			return false;
		/**
		 * ����ds
		 */
		setDataSouceName();
		try {
			UserVerifyChainCycle.verify();
			/**
			 * �Ƿ��½�ɹ�
			 */
			return hasLogin();
		} catch (Exception e) {
			PortalLogger.error(e.getMessage(), e);
		}
		return false;
	}

	/**
	 * �Ƿ��½
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
	 * �Ự��ȫ��֤
	 * �ڵ�¼ϵͳ֮������Ự����ʱ�䳬�����ԻỰ����ʱ�䣬ǿ�лỰ����
	 * @param ts
	 */
	private void ensureSessionTimeOut(long ts){
		ICpSysinitQry sysInitQry = PortalServiceUtil.getCpSysinitQry();
		//�Ƿ���ʾ
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
	 * ��������Դ����
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
