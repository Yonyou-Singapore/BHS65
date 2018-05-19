package nc.uap.portal.user.impl;

import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SimpleTimeZone;
import java.util.TimeZone;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.framework.common.UserExit;
import nc.uap.cpb.org.exception.CpbBusinessException;
import nc.uap.cpb.org.itf.ICpSysinitQry;
import nc.uap.cpb.org.vos.CpUserVO;
import nc.uap.lfw.core.AppInteractionUtil;
import nc.uap.lfw.core.LfwRuntimeEnvironment;
import nc.uap.lfw.core.WebContext;
import nc.uap.lfw.core.cache.LfwCacheManager;
import nc.uap.lfw.core.common.CookieConstant;
import nc.uap.lfw.core.common.ParamConstant;
import nc.uap.lfw.core.common.SessionConstant;
import nc.uap.lfw.core.comp.text.ComboBoxComp;
import nc.uap.lfw.core.comp.text.TextComp;
import nc.uap.lfw.core.ctx.AppLifeCycleContext;
import nc.uap.lfw.core.ctx.IUIContext;
import nc.uap.lfw.core.exception.LfwInteractionException;
import nc.uap.lfw.core.exception.LfwRuntimeException;
import nc.uap.lfw.core.page.LfwView;
import nc.uap.lfw.core.util.HttpUtil;
import nc.uap.lfw.login.vo.LfwSessionBean;
import nc.uap.lfw.util.LfwUserShareUtil;
import nc.uap.portal.cache.PortalCacheManager;
import nc.uap.portal.deploy.vo.PtSessionBean;
import nc.uap.portal.exception.BreakPortalLoginException;
import nc.uap.portal.exception.PortalServerRuntimeException;
import nc.uap.portal.exception.PortalServiceException;
import nc.uap.portal.log.PortalLogger;
import nc.uap.portal.login.itf.ILoginHandler;
import nc.uap.portal.login.itf.ILoginSsoService;
import nc.uap.portal.login.itf.IMaskerHandler;
import nc.uap.portal.login.itf.LoginInterruptedException;
import nc.uap.portal.login.vo.AuthenticationUserVO;
import nc.uap.portal.om.Page;
import nc.uap.portal.plugins.PluginManager;
import nc.uap.portal.service.PortalServiceUtil;
import nc.uap.portal.service.itf.IPtPageQryService;
import nc.uap.portal.servlet.PortalLoginFilter;
import nc.uap.portal.user.chain.CookieAssistant;
import nc.uap.portal.user.entity.IOrgVO;
import nc.uap.portal.user.entity.IUserVO;
import nc.uap.portal.user.itf.IUserLoginPlugin;
import nc.uap.portal.util.PortalPageDataWrap;
import nc.uap.portal.util.ToolKit;
import nc.uap.portal.vo.PtPageVO;
import nc.vo.bd.format.FormatDocVO;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;

import org.apache.commons.lang.StringUtils;

import sun.misc.BASE64Encoder;
import uap.lfw.core.locator.AdapterServiceLocator;
import uap.lfw.core.ml.LfwResBundle;
import uap.lfw.portal.model.BrotherPair;
import uap.lfw.portal.user.ForceLoginTools;
import uap.lfw.portal.user.itf.IUserBill;

/**
 * Portal登陆默认实现
 * 
 * @author licza
 * 
 */
public class PortalLoginHandler implements ILoginHandler<PtSessionBean>, IMaskerHandler<PtSessionBean> {
	private static final String INFO = "INFO";
	private static final String ERROR = "ERROR";
	private static final String LEVEL2 = "level";
	private static final String CHALLLID2 = "challlid";
	private static final String DESC = "DESC";
	private static final String CODE = "CODE";
	private static final String AFTER = "after";
	private static final String CA_USER_ID = "p_userId";
	private static final String SIGNDATA = "p_signdata";
	private static final String MAXWIN = "p_maxwin";
	private static final String LANGUAGE = "p_language";
	protected static final String LOGINDATE = "logindate";
	protected static final String FORCE = "force";
	private static final String BEFORE = "before";
	public static final String KEY = "ufida&UAP!102";
	private List<IUserLoginPlugin> plugins = null;
	IUserBill ub = null;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public PtSessionBean doAuthenticate(AuthenticationUserVO userInfo) throws LoginInterruptedException {
		try {
			Map<String, String> extMap = (Map<String, String>) userInfo.getExtInfo();
			/**
			 * 登陆前Plugin操作
			 */
			loginPluginExecutor(userInfo, BEFORE);

			String userid = userInfo.getUserID();
			Map rsl = getUserBill().userVerify(userid, userInfo.getPassword(), extMap);
			//mock 
			
			String rslCode = (String) rsl.get(CODE);
			String rslMsg = (String) rsl.get(DESC);
			String level = (String) rsl.get(LEVEL2);
			/**
			 * 认证通过
			 */
			if ("0".equals(rslCode)) {
				/**
				 * 强制修改密码
				 */
				if(AppLifeCycleContext.current() != null){
					if (rslMsg != null) {
						if(ERROR.equals(level)){
							//强制修改密码时，不用更新验证码
							LfwRuntimeEnvironment.getWebContext().getRequest().setAttribute("isResetUserPwdFlag", "Y");
							ensureChangePasswd(userid,rslMsg);
//							throw new LoginInterruptedException(LfwResBundle.getInstance().getStrByID("ad", "CpUserPasswordServiceImpl-000011")/*不能使用系统重置的密码，请修改!*/);
							throw new LoginInterruptedException(rslMsg);
						}
						else if(INFO.equals(level)){
							AppInteractionUtil.showMessageDialogWithRePost(rslMsg);
						}
					}
				}else{
					if (rslMsg != null) {
						if(ERROR.equals(level)){
							throw new LoginInterruptedException(rslMsg);
						}
					}
				}
				
			}
			
			/**
			 * 致命异常
			 */
			if ("1".equals(rslCode)) {
				getUserBill().doLoginErrorLog(userInfo, rslMsg);
				WebContext webContext = LfwRuntimeEnvironment.getWebContext();
				HttpSession session = null;
				if(webContext != null){
					HttpServletRequest httpServRequest = webContext.getRequest();
					if(httpServRequest != null){
						session = httpServRequest.getSession();
						if(session != null){
							 session.setAttribute("fatalexcp:"+userid, "Y");
						}
					}
				}else{
					String userId = InvocationInfoProxy.getInstance().getUserId();  
					String groupId = InvocationInfoProxy.getInstance().getGroupId();	
				}
				throw new LoginInterruptedException(rslMsg);
			}
			
			/**
			 * 静态密码验证通过，需要CA验证
			 */
			if ("2".equals(rslCode)) {
//				HttpSession session = LfwRuntimeEnvironment.getWebContext().getRequest().getSession();
				WebContext webContext = LfwRuntimeEnvironment.getWebContext();
				HttpSession session = null;
				String challlid = UUID.randomUUID().toString();
				if(webContext != null){
					HttpServletRequest httpServRequest = webContext.getRequest();
					if(httpServRequest != null){
						session = httpServRequest.getSession();
						if(session != null){
							session.setAttribute(CHALLLID2, challlid);
						}
					}
				}
				AppLifeCycleContext.current().getWindowContext()
						.addExecScript("calogin('" + challlid + "','" + userid + "')");
				return null;
			}
			
			String langCode = UserExit.DEFAULT_LANG_CODE_VALUE;
			if (extMap.get(LANGUAGE) != null) {
				langCode = extMap.get(LANGUAGE);
			} else {
				langCode = LfwRuntimeEnvironment.getLangCode();
			}
			IUserVO ptUser = (IUserVO) rsl.get("USER");
			
			if(!ForceLoginTools.canMultiLogin()){
				String datasource = InvocationInfoProxy.getInstance().getUserDataSource();
				BrotherPair<String, String> ret = ForceLoginTools.getOriSessionId(ptUser.getUserid(), datasource);
				if(ret != null){
					if(AppLifeCycleContext.current() != null){
						boolean forceLoginFlag = AppInteractionUtil.showConfirmDialog(NCLangRes4VoTransl.getNCLangRes().getStrByID("pserver", "exception-0001")/*提示*/, NCLangRes4VoTransl.getNCLangRes().getStrByID("pserver", "PortalLoginHandler-0000")/*当前用户已经登陆,是否强制登陆?*/);
						if(forceLoginFlag){
							ForceLoginTools.logout(ret);
						}else{
							return null;
						}
					}else{   
						if("Y".equals(extMap.get("forcelogin"))){
							ForceLoginTools.logout(ret);
						}else{
							throw new SecurityException(NCLangRes4VoTransl.getNCLangRes().getStrByID("pserver", "PortalLoginHandler-0000")/*当前用户已经登陆,是否强制登陆?*/);
						}
					}
				}
			}
			
			ptUser.setLangcode(langCode);
			PtSessionBean sbean = createSessionBean(ptUser);
			String tzOffset = extMap.get("p_tz");
			if(tzOffset != null ){
				int rawOffset = Integer.parseInt(tzOffset) * 60 * -1 * 1000;
				TimeZone tz = new SimpleTimeZone(rawOffset, "GMT " + (rawOffset / 60 / 60 / 1000));
				sbean.setTimeZone(tz);
			}
			
			sbean.setSecurityToken(null);
			sbean.fireLocalEnvironment();
			
			//add chenth 20170511 记住密码
			WebContext webContext = LfwRuntimeEnvironment.getWebContext();
			HttpServletResponse response = webContext.getResponse();
			HttpServletRequest request = webContext.getRequest();
			String remeber=extMap.get("p_keepstate");
			CookieAssistant ca = CookieAssistant.getInstance();
			if (remeber!=null && remeber.trim().equals("1"))
			{
				ca.setCookie(response, "p_userId", userInfo.getUserID(), CookieConstant.MAX_AGE);
				ca.setCookie(response, "p_auth", md5encoder(userInfo.getUserID()), CookieConstant.MAX_AGE);
				ca.setCookie(response, "p_security", userInfo.getPassword(), CookieConstant.MAX_AGE);
			}
			else
			{
				ca.delCookie(response, "p_userId");
				ca.delCookie(response, "p_auth");
				ca.delCookie(response, "p_security");
			}
			//System.out.println("---------Login success");
			//add end chenth
			
			
			return sbean;
		} catch (Exception e) {
			if (e instanceof LfwInteractionException) {
				throw (LfwInteractionException) e;
			}
			if(e instanceof LoginInterruptedException){
				throw (LoginInterruptedException) e;
			}if(e instanceof SecurityException){
				throw (SecurityException)e;
			}
			PortalLogger.error("Login Error:" + e.getMessage(), e);
			throw new LoginInterruptedException(e.getMessage());
		}
	}
	/**
	 * 强制修改密码
	 * @param rslMsg
	 */
	private void ensureChangePasswd(String userid, String rslMsg) {
//		HttpSession session = LfwRuntimeEnvironment.getWebContext().getRequest().getSession();
		WebContext webContext = LfwRuntimeEnvironment.getWebContext();
		HttpSession session = null;
		String challlid = UUID.randomUUID().toString();
		if(webContext != null){
			HttpServletRequest httpServRequest = webContext.getRequest();
			if(httpServRequest != null){
				session = httpServRequest.getSession();
				if(session != null){
					session.setAttribute("USER_SESSION_ID", userid);
				}
			}
		}
		StringBuffer urlBuf = new StringBuffer();
		urlBuf.append("/portal/app/mockapp/passwordmng?model=nc.uap.portal.mng.pwdmng.PasswordManagerModel");
		urlBuf.append("&" + ParamConstant.OTHER_PAGE_UNIQUE_ID + "=" + LfwRuntimeEnvironment.getWebContext().getWebSession().getWebSessionId());
		AppLifeCycleContext.current().getApplicationContext().popOuterWindow(urlBuf.toString(), rslMsg, "480", "280", IUIContext.TYPE_DIALOG);
	}

	/**
	 * 登陆后回调
	 */
	@Override
	public void afterLogin(LfwSessionBean userVO) {

		HttpServletRequest request = LfwRuntimeEnvironment.getWebContext().getRequest();
		try {
			LfwRuntimeEnvironment.setLfwSessionBean(userVO);
			LfwRuntimeEnvironment.setClientIP(HttpUtil.getIp());
			LfwRuntimeEnvironment.setDatasource(userVO.getDatasource());
			if(!"annoyuser".equals(userVO.getUser_code()))
				changeSessionIdentifier(request);
			request.getSession().setAttribute(SessionConstant.LOGIN_SESSION_BEAN, userVO);
			initUser(userVO);
			regOnlineUser(userVO, request);
			ILoginSsoService<PtSessionBean> ssoService = getLoginSsoService();
			ssoService.addSsoSign((PtSessionBean) userVO, getSysType());
			UFBoolean loginResult = UFBoolean.TRUE;
			/**
			 * 登陆后处理插件
			 */
			loginPluginExecutor(userVO, AFTER);
			getUserBill().doLoginLog(userVO, loginResult, LfwResBundle.getInstance().getStrByID("pserver", "PortalLoginHandler-000023")/*普通登陆*/);
			
		} catch (BusinessException e) {
			PortalLogger.error(e.getMessage(), e);
		}
	}

	/**
	 * 初始化用户信息
	 * 
	 * @param sbean
	 * @throws PortalServiceException
	 */
	public void initUser(LfwSessionBean sbean) throws PortalServiceException {
		IPtPageQryService qry = PortalServiceUtil.getPageQryService();
		PtSessionBean sb = (PtSessionBean) sbean;
		IUserVO user = sb.getUser();
		
		String origPkorg=user.getPk_org();
		CpUserVO uservo=(CpUserVO)user.getUser();
		/**
		 * 跨集团共享用户开关
		 */
		if(LfwUserShareUtil.isNeedShareUser && StringUtils.isNotEmpty(sbean.getPk_unit()) && StringUtils.isNotEmpty(user.getPk_group()) && !sbean.getPk_unit().equals(user.getPk_group())){
			uservo.setPk_org(sbean.getPk_unit());
		}
		PtPageVO[] pageVOs = qry.getPagesByUser(user);
		uservo.setPk_org(origPkorg);
		
		if (pageVOs == null || pageVOs.length == 0)
			throw new PortalServerRuntimeException(LfwResBundle.getInstance().getStrByID("pserver", "PortalLoginHandler-000024")/*初始化用户信息失败,未找到当前用户可用的布局!*/);
		pageVOs = PortalPageDataWrap.filterPagesByUserType(pageVOs, sb.getUser_type());
		if (pageVOs == null || pageVOs.length == 0)
			throw new PortalServerRuntimeException(LfwResBundle.getInstance().getStrByID("pserver", "PortalLoginHandler-000025")/*初始化用户信息失败,未找到当前类型用户可用的布局!*/);
		List<Page> pageList = PortalPageDataWrap.praseUserPages(pageVOs);
		if (pageList.isEmpty())
			throw new PortalServerRuntimeException(LfwResBundle.getInstance().getStrByID("pserver", "PortalLoginHandler-000026")/*初始化用户信息失败:解析布局出现异常!*/);
		Map<String, Page> pagesCache = PortalPageDataWrap.praseUserPages(pageList.toArray(new Page[] {}));
		PortalCacheManager.getUserPageCache().clear();
		PortalCacheManager.getUserPageCache().putAll(pagesCache);
	}

	/**
	 * 获取登陆插件
	 * 
	 * @return
	 */
	private List<IUserLoginPlugin> getLoginPlugins() {
		if (plugins == null)
			plugins = PluginManager.newIns().getExtInstances(IUserLoginPlugin.ID, IUserLoginPlugin.class);
		return plugins;
	}

	/**
	 * 创建会话Bean
	 * 
	 * @param user
	 * @param ub
	 * @return
	 */
	private PtSessionBean createSessionBean(IUserVO user) {
		String groupNo;
		String groupName;
		IOrgVO org = getUserBill().getOrg(user.getPk_group());
		if (org != null) {
			groupNo = org.getCode();
			groupName = org.getName();
		} else {
			groupNo = "0000";
			groupName = "0000";
		}

		PtSessionBean sbean = new PtSessionBean();

		sbean.setDatasource(LfwRuntimeEnvironment.getDatasource());
		sbean.setUnitNo(groupNo);
		sbean.setUnitName(groupName);
		sbean.setUserType(user.getUsertype());
		sbean.setUser(user);
		sbean.setTimespan(System.currentTimeMillis());
		String themeId = LfwRuntimeEnvironment.getThemeId();
		sbean.setThemeId(themeId);
		return sbean;
	}

	/**
	 * 获得登录信息VO
	 */
	@Override
	public AuthenticationUserVO getAuthenticateVO() throws LoginInterruptedException {
		AuthenticationUserVO userVO = new AuthenticationUserVO();
		Map<String, String> extMap = new HashMap<String, String>();
		LfwView widget = getCurrentWidget();
		TextComp userIdComp = (TextComp) widget.getViewComponents().getComponent("userid");
		TextComp randomImageComp = (TextComp) widget.getViewComponents().getComponent("randimg");

		ICpSysinitQry cpSysinitQry = PortalServiceUtil.getCpSysinitQry();
		boolean enabledRandomImage = false;
		try {
			String showRanImg = cpSysinitQry.getSysinitValueByCodeAndPkorg("randomimg", null);
			enabledRandomImage = UFBoolean.valueOf(showRanImg).booleanValue();
		} catch (CpbBusinessException e) {
			PortalLogger.error(e.getMessage(), e);
		}
		String userId = null;

		AppLifeCycleContext pctx = AppLifeCycleContext.current();
		HttpSession session = LfwRuntimeEnvironment.getWebContext().getRequest().getSession();
		String signdata = pctx.getParameter(SIGNDATA);
		String sn = pctx.getParameter("p_sn");
		String tz = pctx.getParameter("p_tz");
		if (userIdComp != null) {
			if (enabledRandomImage) {
				String rand = null;
				if (session != null) {
					rand = (String) session.getAttribute("rand");
				}
				String ricv = randomImageComp.getValue();
				if (!StringUtils.equals(rand, ricv))
					throw new LoginInterruptedException(LfwResBundle.getInstance().getStrByID("pserver",
							"PortalLoginHandler-000006")/* 验证码错误! */);
			}
			userId = userIdComp.getValue();
			if (userId == null || userId.equals("")) {
				throw new LoginInterruptedException(LfwResBundle.getInstance().getStrByID("pserver",
						"PortalLoginHandler-000007")/* 用户名不能为空 */);
			}
		}
		
		TextComp passComp = (TextComp) widget.getViewComponents().getComponent("password");
		String passValue = null;
		if (passComp != null) {
			passValue = passComp.getValue();
			if (passValue == null) {
				passValue = ("");
			}
		}

		ComboBoxComp multiLanguageCombo = (ComboBoxComp) widget.getViewComponents().getComponent("multiLanguageCombo");
		String language = multiLanguageCombo.getValue();

		userVO.setUserID(userId);
		userVO.setPassword(passValue);
		extMap.put(LANGUAGE, language);
		extMap.put(MAXWIN, "N");
		extMap.put(SIGNDATA, signdata);
		extMap.put("p_sn", sn);
		//Timezone
		extMap.put("p_tz", tz);
		String challlid = (String) session.getAttribute(CHALLLID2);
		extMap.put(CHALLLID2, challlid);
		//add chenth 20170511 记住密码
		String keepState= pctx.getParameter("p_keepstate");
		if (keepState!=null)
		{
			extMap.put("p_keepstate", keepState);
		}
		//add end
		userVO.setExtInfo(extMap);
		return userVO;
	}

	@SuppressWarnings({ "unchecked" })
	@Override
	public Cookie[] getCookies(AuthenticationUserVO userVO) {
		List<Cookie> list = new ArrayList<Cookie>();
		String userId = userVO.getUserID();
		Map<String, String> extMap = (Map<String, String>) userVO.getExtInfo();
		String sysId = "" + LfwRuntimeEnvironment.getSysId();
		String themeId = LfwRuntimeEnvironment.getLfwSessionBean().getThemeId();
		String language = extMap.get(LANGUAGE);
		String maxwin = extMap.get(MAXWIN);
		String useridEncode = null;
		String cookiePath = LfwRuntimeEnvironment.getRootPath();
		try {
			useridEncode = URLEncoder.encode(userId, "UTF-8");
		} catch (Exception e) {
			PortalLogger.warn(e.getMessage());
		}

		// Theme
		Cookie tc = new Cookie(CookieConstant.THEME_CODE + sysId, themeId);
		tc.setPath("/");
		tc.setMaxAge(CookieConstant.MAX_AGE);
		list.add(tc);

		// 语种
		Cookie lc = new Cookie(CookieConstant.LANG_CODE + sysId, language);
		lc.setPath("/");
		lc.setMaxAge(CookieConstant.MAX_AGE);
		list.add(lc);
		// 用户账号
		Cookie uc = new Cookie(CA_USER_ID, useridEncode);
		uc.setPath(cookiePath);
		uc.setMaxAge(CookieConstant.MAX_AGE);
		list.add(uc);
		// 最大化打开
		Cookie mc = new Cookie("isMaxWindow", maxwin);
		mc.setPath(cookiePath);
		mc.setMaxAge(CookieConstant.MAX_AGE);
		list.add(mc);

		Cookie p_auth = new Cookie("p_logoutflag", null);
		p_auth.setMaxAge(CookieConstant.MAX_AGE);
		p_auth.setPath(cookiePath);
		list.add(p_auth);
		
		return list.toArray(new Cookie[0]);
	}

	@Override
	public ILoginSsoService<PtSessionBean> getLoginSsoService() {
		return new PortalSSOServiceImpl();
	}

	@Override
	public String getSysType() {
		return PortalLoginFilter.PORTAL_SYS_TYPE;
	}

	public LfwView getCurrentWidget() {
		return AppLifeCycleContext.current().getViewContext().getView();
	}

	@Override
	public FormatDocVO getMaskerInfo(PtSessionBean loginBean) {
		return getUserBill().getMaskerInfo(loginBean);
	}

	/**
	 * 注册Portal在线人数
	 * 
	 * @param request
	 * @throws BusinessException
	 */
	private void regOnlineUser(LfwSessionBean sb, HttpServletRequest request) throws BusinessException {
		String clientIP = HttpUtil.getIp();
		String sessionid = request.getSession().getId();
		getUserBill().regOnlineUser(sb, sessionid, clientIP);
	}

	/**
	 * 登陆插件执行器
	 * 
	 * @param userInfo
	 */
	private void loginPluginExecutor(Object userInfo, String cmd) {
		/**
		 * 
		 * 登录前操作
		 */
		if (ToolKit.notNull(getLoginPlugins())) {
			for (IUserLoginPlugin ex : getLoginPlugins()) {
				boolean isBefore = BEFORE.equals(cmd);
				try {
					if (isBefore)
						ex.beforeLogin((AuthenticationUserVO) userInfo);
					else
						ex.afterLogin((PtSessionBean) userInfo);
				} catch (BreakPortalLoginException e) {
					PortalLogger.error(e.getMessage(), e);
					if (isBefore)
						getUserBill().doLoginErrorLog((AuthenticationUserVO) userInfo, e.getHint());
					else
						getUserBill().doLoginLog((LfwSessionBean) userInfo, UFBoolean.FALSE, e.getHint());
					throw new LfwRuntimeException(e.getHint());
				} catch (Throwable a) {
					PortalLogger.error(LfwResBundle.getInstance().getStrByID("pserver", "PortalLoginHandler-000027")/*登录操作异常:*/ + a.getMessage(), a);
				}
			}
		}
	}

	private IUserBill getUserBill() {
		if (ub == null) {
			ub = AdapterServiceLocator.newIns().get(IUserBill.class);
		}
		return ub;
	}
	
	private HttpSession changeSessionIdentifier(HttpServletRequest request)  {
	       // get the current session
	       HttpSession oldSession = request.getSession();

	       // make a copy of the session content
	       Map<String,Object> temp = new ConcurrentHashMap<String,Object>();
	       Map<String, Object> oldSessionCache = new ConcurrentHashMap<String,Object>();
	       oldSessionCache.putAll(LfwCacheManager.getSessionCache());
	       Enumeration<String> e = oldSession.getAttributeNames();
	       while (e != null && e.hasMoreElements()) {
	           String name = (String) e.nextElement();
	           Object value = oldSession.getAttribute(name);
	           temp.put(name, value);
	       }
	       oldSession.setAttribute(SessionConstant.SESSION_SELF_DESTORY , Boolean.TRUE);
	       // kill the old session and create a new one
	       oldSession.invalidate();
	       HttpSession newSession = request.getSession(true);
	       
	       // copy back the session content
	      for (Map.Entry<String, Object> stringObjectEntry : temp.entrySet())
	      {
	         newSession.setAttribute(stringObjectEntry.getKey(), stringObjectEntry.getValue());
	       }
	      Map<String, Object> newSessionCache = LfwCacheManager.getSessionCache();
	      newSessionCache.putAll(oldSessionCache);
	       return newSession;
	    }
	
	public String md5encoder(String str) {
		MessageDigest md5 = null;
		String value = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
			BASE64Encoder base64Encoder = new BASE64Encoder();
			value = base64Encoder.encode(md5.digest(str.getBytes("UTF-8")));
		} catch (Exception e) {
			PortalLogger.error(e.getMessage(), e);
		}
		return value;
	}


}
