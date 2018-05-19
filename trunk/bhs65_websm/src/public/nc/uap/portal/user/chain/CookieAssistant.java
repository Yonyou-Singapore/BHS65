package nc.uap.portal.user.chain;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * Cookie ToolBox
 * @author yonyou.jenshen
 *
 */
public class CookieAssistant {

	private static CookieAssistant instance;
	
	public static CookieAssistant getInstance()
	{
		if (instance==null)
		{
			instance = new CookieAssistant();
		}
		return instance;
	}
	
	/**
	 * setCookie ,default path is "/"
	 * @param response
	 * @param key
	 * @param val
	 * @param expiry
	 */
	public void setCookie(HttpServletResponse response,String key,String val,int expiry)
	{
		setCookie(response,key,val,"/",expiry);
	}
	/**
	 * 
	 * @param response
	 * @param key
	 * @param val
	 * @param path 
	 * @param expiry
	 */
	public void setCookie(HttpServletResponse response,String key,String val,String path,int expiry)
	{
		Cookie cookie=new Cookie(key,val);
		cookie.setPath(path);
		cookie.setMaxAge(expiry);
		response.addCookie(cookie);
	}
	
	public void delCookie(HttpServletResponse response,String key)
	{
		delCookie(response,key,"/");
	}
	
	public void delCookie(HttpServletResponse response,String key,String path)
	{
		Cookie cookie=new Cookie(key,null);
		cookie.setPath(path);
		cookie.setMaxAge(0);
		response.addCookie(cookie);
	}
	

}
