package nc.uap.lfw.action;

/**
 * Action Target
 * @author atian
 *
 */
public class ActionTarget {
	
	public static final String PARAM_ACTION = "action";
	public static final String PARAM_METHOD = "method";

	private String path;
	
	private String action;
	
	private String method;
	
	public ActionTarget(){
		
	}
	
	public ActionTarget(String action,String method)
	{
		this.action = action;
		this.method = method;
	}
	


	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	
	
}
