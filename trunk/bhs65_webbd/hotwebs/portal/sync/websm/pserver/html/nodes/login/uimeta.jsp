<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="nc.bs.framework.common.NCLocator" %>
<%@ page import="nc.uap.cpb.org.exception.CpbBusinessException" %>
<%@ page import="nc.uap.cpb.org.itf.ICpSysinitQry" %>
<%@ page import="nc.uap.cpb.org.vos.CpSysinitVO" %>
<%@ page import="nc.uap.lfw.util.LanguageUtil" %>

<%@ taglib uri="http://www.ufida.com/lfw" prefix="lfw" %>
<%@ taglib uri="http://www.ufida.com/ra" prefix="ra" %>
<%@ taglib uri="http://www.ufida.com/multilang" prefix="ml" %>

<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<% 
		response.setHeader("cache-control","no-store");
		String portalDataSource = nc.uap.portal.util.PortalDsnameFetcher.getPortalDsName();
		nc.uap.lfw.core.LfwRuntimeEnvironment.setDatasource(portalDataSource);
		nc.uap.cpb.org.itf.ICpSysinitQry sysInitQry = nc.uap.portal.service.PortalServiceUtil.getCpSysinitQry();
		//是否显示验证码
		String showRanImg = "N";	
		try {
			showRanImg = sysInitQry.getSysinitValueByCodeAndPkorg("randomimg",null);	
		} catch (Exception e) {
			 nc.uap.portal.log.PortalLogger.error(e.getMessage(), e);
		}
		//showRanImg = "Y";
		//打开方式
		//String openMode = portalConfig.get("openmaxwin");
		pageContext.setAttribute("showRanImg", nc.vo.pub.lang.UFBoolean.valueOf(showRanImg).booleanValue());
		CpSysinitVO systemInit = null;
		String mail = systemInit == null ? "":systemInit.getDefaultvalue();
		pageContext.setAttribute("showFindPassWord", systemInit != null);
		pageContext.setAttribute("mail", mail);
		boolean isIpadSafari = nc.uap.lfw.core.LfwRuntimeEnvironment.getBrowserInfo().isIpadSafari();
		pageContext.setAttribute("isIpadSafari",isIpadSafari);
		String langcode = nc.uap.lfw.core.LfwRuntimeEnvironment.getLangCode();
		pageContext.setAttribute("langcode", langcode);
		nc.uap.lfw.core.BrowserSniffer browserInfo = nc.uap.lfw.core.LfwRuntimeEnvironment.getBrowserInfo();
		pageContext.setAttribute("IEVersion", browserInfo.getIEVersion() + "");
		pageContext.setAttribute("LoginLogo",nc.uap.portal.comm.icon.IconResources.get("LoginLogo"));
		pageContext.setAttribute("LoginIcon",nc.uap.portal.comm.icon.IconResources.get("LoginIcon"));
%>
<html>
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=8,9" />
		<title>${ml:transp("portal","login-000000")}</title>	
		<link rel="shortcut icon" href="/portal/ufida.ico"/>	
		<lfw:base/>		
		<lfw:head/>		
		<lfw:pageImport/>
		<link href="/portal/frame/themes/${THEME_ID}/login/login.css" rel="stylesheet" type="text/css">
		<script>
		<%
		if(request.getSession().getAttribute("LoginInterruptedException") != null){
			pageContext.setAttribute("LoginInterruptedException", request.getSession().getAttribute("LoginInterruptedException"));
			request.getSession().removeAttribute("LoginInterruptedException");
			%>alert('${LoginInterruptedException}');<%
		}
		%>
		</script>
		<style>
		.inputpanel {
			background: url(/portal/frame/themes/webclassic/login/images/panel2.png) no-repeat;
		}
		.formtable{height:80px;}
		.formtable tr td{}
		.fixdiv{color:#fff;height:20px;line-height:20px;position:absolute;top:60px;left:280px;position:absolute}
		.fixdiv span{height:14px;line-height:14px;display:block}
		a.login_forgotpwd_bt{color:#fff;display:inline-block;height:15px;line-height:15px;font-size:12px;cursor:pointer}
		.login_status_ck{
			background:url(/portal/frame/themes/webclassic/login/images/bg.png) no-repeat;
			width:14px;
			height:14px;
			z-index:999;
		}
		.login_status_ck_checked{
			background:url(/portal/frame/themes/webclassic/login/images/check.png) no-repeat;
			width:14px;
			height:14px;
			z-index:999;
		}
		</style>
			<script type='text/javascript' src='/lfw/frame/device_pc/script/ui/lang/login_<%=langcode%>.js'></script>
	</head>
	<body>	
	<div class="login_bg"> </div>
	<lfw:pageModel>			
		<lfw:widget id="main">
				<div class="ctn">
					<div class="hd">
						<div style="height:40px;">
							<table width="100%" height="40" cellpadding="0" cellspacing="0" border="0" >
								<tr>
									<td align="left" width="50%"><div style="margin-top: 12px; margin-left: 35px;"><img src="${LoginIcon}"></div></td>
									<td width="50%" align="right">
										<div style="width:100%;position:relative;margin-top:25px;color:#ffffff;font-size:12pt;">
											<div class="rightTopDivStyle" style="margin-right:35px;">
												<a onclick="javascript:window.open('/portal/activexlist.jsp','','height=600,width=700');" style="display: inline-block; text-align: left;cursor:pointer;">${ml:transp("portal","login-000003")}</a>
											</div>
											<div class="rightTopDivStyle rightTopDivBg" style=""></div>
											<div class="rightTopDivStyle">
												<c:if test="${isIpadSafari eq 'true'}"><ra:textcomp id="multiLanguageCombo" widgetId='main' width="98"/></c:if>
												<c:if test="${isIpadSafari eq 'false'}"><ra:textcomp id="multiLanguageCombo" widgetId='main' width="80"/></c:if>
											</div>
										</div>
									</td></tr>
							</table>
						</div>
					</div>
					
					<div class="content">
						<table cellpadding="0" cellspacing="0" border="0"  width="100%">
							<tr>
								<td  width="100%" height="500" align="middle" valign="center">
									<table cellpadding="0" cellspacing="0" border="0" height="70">
										<tr>
											<td colspan="3" align="left" valign="top" height="342">
												<div style="position: relative; width: 100%; height: 100%;">
													<img src="${LoginLogo}">
												</div>
											</td>
										</tr>
										<tr>
											<td align="right" colspan="3" <c:if test="${showRanImg eq 'false'}">width="649"</c:if><c:if test="${showRanImg eq 'true'}">width="696"</c:if> >
												<div style="position:relative">
												<table cellpadding="0"  cellspacing="0" border="0" class="formtable inputc inputpanel<c:if test="${showRanImg eq 'true'}">1</c:if>" >
													<tr>
														<td width="<c:if test="${showRanImg eq 'false'}">20px</c:if><c:if test="${showRanImg eq 'true'}">15px</c:if>"></td>
														<td>
															 <table cellpadding="0" cellspacing="0" border="0" class="inputc">
															 	<tr>
																 	<td>
																 		<div class="<c:if test="${showRanImg eq 'false'}">ipt_m1</c:if><c:if test="${showRanImg eq 'true'}">ipt_m1_code</c:if>">
																	 		<div class="<c:if test="${showRanImg eq 'false'}">ipt_m</c:if><c:if test="${showRanImg eq 'true'}">ipt_m_code</c:if>">
																			 	<div style="position:relative;padding-left:17px;padding-top:10px;position:relative;">
																			 		<div id="usernamelab" style="position:absolute;z-index:99999;" class="label" onclick="loginTipWordClick(this);">${ml:transp("portal","login_000001")}</div>
																		 			<c:if test="${showRanImg eq 'false'}"><ra:textcomp id="userid" widgetId='main' width="190"/></c:if><c:if test="${showRanImg eq 'true'}"><ra:textcomp id="userid" widgetId='main' width="160"/></c:if>
																			 	</div>
																		 	</div>
																	 	</div>
																 	</td>
															 	</tr>
															 </table>
														</td>
														<td  >
															 <table cellpadding="0" cellspacing="0" border="0" class="inputc">
															 	<tr>
																 	<td>
																 		<div class="<c:if test="${showRanImg eq 'false'}">ipt_m1</c:if><c:if test="${showRanImg eq 'true'}">ipt_m1_code</c:if>">
																	 		<div class="<c:if test="${showRanImg eq 'false'}">ipt_m</c:if><c:if test="${showRanImg eq 'true'}">ipt_m_code</c:if>">
																			 	<div style="position:relative;padding-left:17px;padding-top:10px;position:relative;">
																			 		<div id="passwordlab" style="position:absolute;z-index:99999;" class="label" onclick="loginTipWordClick(this);">${ml:transp("portal","login_000002")}</div>
																		 			<c:if test="${showRanImg eq 'false'}"><ra:textcomp id="password" widgetId='main' width="190"/></c:if><c:if test="${showRanImg eq 'true'}"><ra:textcomp id="password" widgetId='main' width="160"/></c:if>
																			 	</div>
																		 	</div>
																	 	</div>
																 	</td>
															 	</tr>
															 </table>
														</td>
														<c:if test="${showRanImg eq 'true'}">
														<td >
															 <table cellpadding="0" cellspacing="0" border="0" class="inputc">
															 	<tr>
																 	<td>
																 		<div class="check_code_border">
																 		  <div class="check_code">
															 		     	<c:if test="${isIpadSafari eq 'true'}">																 		
																	 		  	<div style="position:relative;padding-left:15px;padding-top:12px;">
																			 		<div id="randimglab" style="position:absolute;z-index:99999;left:15px;" class="label" onclick="loginTipWordClick(this);">${ml:transp("portal","login_000006")}</div>
																		 			<div style="width:50px;overflow:hidden;">
																			 			<ra:textcomp id="randimg" widgetId='main' width="85"/>
																		 			</div>
																	 		     </div>
																	 		</c:if>
																		 	<c:if test="${isIpadSafari eq 'false'}">
																	 		  	<div style="position:relative;padding-left:15px;padding-top:12px;">
																			 		<div id="randimglab" style="position:absolute;z-index:99999;left:15px;<c:if test="${showRanImg eq 'true'}">left:10px;</c:if><c:if test="${langcode ne 'simpchn' and langcode ne 'tradchn' and showRanImg eq 'true'}">left:6px;font-size:12px;top:6px;text-align:left;</c:if>" class="label" onclick="loginTipWordClick(this);">${ml:transp("portal","login_000006")}</div>
																			 		<ra:textcomp id="randimg" widgetId='main' width="48"/>
																	 		     </div>
																		 	</c:if>
																 		  </div>
																 		</div>
																 	</td>
																 	<td>
																 		<div class="imgcs">
																 			<img id="randimage" style="position:absolute;margin-top:12px;left:8px;" src="./randimg" onclick="this.src='./randimg?d='+ new Date().getTime()" />
																 		</div>
																 	</td>
															 	</tr>
															 </table>
														</td>
														</c:if>
														<td  width="120px">
															<ra:button id="submitBtn" width="111" height="36" widgetId='main' className="login_button_div" ></ra:button>
														</td>
														<td width="<c:if test="${showRanImg eq 'false'}">20px</c:if><c:if test="${showRanImg eq 'true'}">10px</c:if>"></td>
													</tr>
												</table>

													  <div class="fixdiv">
														<div class="login_status_ck" onclick="changeLoginStatusCK()" style="margin-left:60px;position: relative;display:block;float:left">
															<div id="loginStatusCK" class="">
															</div>
														</div><span style="float:left;font-size:12px" class="login_remeberpwd_bt"></span>

														<div style="margin-left:20px;position: relative;display:block;float:left"><a  class="login_forgotpwd_bt"></a></div>
													</div>
												</div>
											</td>
										</tr>
										<tr>
											<td align="right" width="41px">
												
											</td>
											
											<td align="left" colspan="2" >
												<ra:label id="tiplabel" width="500" widgetId='main'></ra:label>
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</div>
					<div class="foot" >
					</div>
				</div>
		</lfw:widget>
	</lfw:pageModel>
	</body>
</html>