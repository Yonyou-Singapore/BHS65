package nc.uap.lfw.action;

import java.lang.reflect.Method;

import nc.uap.lfw.action.cfg.RequestRoute;
import nc.uap.lfw.action.controller.BaseAction;
import nc.uap.lfw.action.err.TargetInvokeException;
import nc.uap.lfw.action.view.ModelData;
import nc.uap.lfw.action.view.RenderView;

/**
 * @author yonou.jensen
 * @version 创建时间：Dec 20, 2016 10:39:08 AM
 * 
 */
public class SimpleActionInvoker implements ActionInvoker{

	@Override
	public RenderView invoke(ActionTarget target,ActionContext context, ModelData model) {
		try
		{
			Class<?> actionClass = Class.forName(target.getAction());
			Object action = actionClass.newInstance();
			if (!(action instanceof BaseAction))
			{
				throw new TargetInvokeException("Invoke target not exists!");
			}
			Method[] methods = actionClass.getMethods();
			for (Method mt : methods)
			{
				if (mt.getName().equals(target.getMethod()))
				{
					if (mt.getAnnotation(RequestRoute.class)!=null)
					{
						return (RenderView)mt.invoke(action,context, model);
					}
				}
			}
			return null;
		}
		catch (Exception e)
		{
			throw new TargetInvokeException(e);
		}
	}

}
