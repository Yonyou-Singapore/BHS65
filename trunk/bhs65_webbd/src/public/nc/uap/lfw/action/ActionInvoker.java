package nc.uap.lfw.action;

import nc.uap.lfw.action.view.ModelData;
import nc.uap.lfw.action.view.RenderView;

/**
 * Action Invoker
 * @author yonou.jensen
 * @version 创建时间：Dec 20, 2016 10:37:51 AM
 * 
 */
public interface ActionInvoker {

	/**
	 * 
	 * @param cls Action's Class
	 * @param method Invoke Method
	 * @param path RequestPath
	 * @param model InputData Wrapper
	 * @return
	 */
	RenderView invoke(ActionTarget target,ActionContext context,ModelData model);
}
