package nc.uap.lfw.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nc.uap.lfw.action.err.IIlegalInvokeException;
import nc.uap.lfw.action.err.InvokeException;
import nc.uap.lfw.action.view.ModelData;
import nc.uap.lfw.action.view.RenderView;
import nc.uap.lfw.core.ControlPlugin;

import org.apache.commons.lang.StringUtils;

/**
 * ActionControlPlugin For Lfw
 * @author atian
 *
 */
public class ActionControlPlugin   implements ControlPlugin{
	
	private ActionInvoker invoker = new SimpleActionInvoker();

	@Override
	public void handle(HttpServletRequest request,
			HttpServletResponse response, String paramString)
			throws Exception {
		ActionTarget target = this.lookup(request);
		if (target==null)
		{
			throw new IIlegalInvokeException("Request IIlegal");
		}
		try
		{
			ActionContext context = new DefaultActionContext(request,response);
			ModelData model = ModelMapper.map(request);
			RenderView view = invoker.invoke(target, context, model);
			view.render(request, response);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new InvokeException(e);
		}
	}
	
	private ActionTarget lookup(HttpServletRequest request)
	{
		String action = request.getParameter(ActionTarget.PARAM_ACTION);
		String method = request.getParameter(ActionTarget.PARAM_METHOD);
		if (StringUtils.isNotBlank(action) && StringUtils.isNotBlank(method))
		{
			return new ActionTarget(action,method);
		}
		else
			return null;
	}

}
