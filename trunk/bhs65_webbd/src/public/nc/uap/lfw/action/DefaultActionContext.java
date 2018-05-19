package nc.uap.lfw.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author yonou.jensen
 * @version 创建时间：Dec 20, 2016 4:12:28 PM
 * 
 */
public class DefaultActionContext implements ActionContext{
	
	private HttpServletRequest request;
	
	private HttpServletResponse response;


	public DefaultActionContext(HttpServletRequest request,HttpServletResponse response)
	{
		this.request = request;
		this.response = response;
	}
	
	
	public  HttpServletRequest getRequest()
	{
		return request;
	}
	
	
	public HttpServletResponse getResponse()
	{
		return response;
	}
}
