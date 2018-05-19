package nc.uap.lfw.action;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import nc.uap.lfw.action.view.ModelData;

/**
 * Convert Request Param to ModelData
 * @author yonou.jensen
 * @version 创建时间：Dec 20, 2016 3:55:38 PM
 * 
 */
public class ModelMapper {

	/**
	 * map request parameter
	 * @param request
	 * @return
	 */
	public static ModelData map(HttpServletRequest request)
	{
		return merge(request,null);
	}
	
	/**
	 * merge request parameter
	 * @param request
	 * @param model
	 * @return
	 */
	public static ModelData merge(HttpServletRequest request,ModelData model)
	{
		if (model==null)
		{
			model = new ModelData();
		}
		String parameterName = null;
		Enumeration<String> es = request.getParameterNames();
		while (es.hasMoreElements())
		{
			parameterName  = es.nextElement();
			model.addAttribute(parameterName,request.getParameter(parameterName));
		}
		return model;
	}
	
}
