package nc.uap.lfw.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author yonou.jensen
 * @version ����ʱ�䣺Dec 20, 2016 4:11:28 PM
 * 
 */
public interface ActionContext {

	HttpServletRequest getRequest();
	
	
	HttpServletResponse getResponse();
}
