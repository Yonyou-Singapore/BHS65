package nc.uap.lfw.action.err;
/**
 * Action Execute Failure
 * @author yonou.jensen
 * @version 创建时间：Dec 20, 2016 4:48:48 PM
 * 
 */
public class InvokeException extends ActionException{
	
	private static final long serialVersionUID = 1L;

	public InvokeException(String message)
	{
		super(message);
	}
	
	public InvokeException(Exception e)
	{
		super(e.getMessage());
	}
	
	
	
}