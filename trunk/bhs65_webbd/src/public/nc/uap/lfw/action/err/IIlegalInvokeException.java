package nc.uap.lfw.action.err;
/**
 * 非法调用
 * @author yonou.jensen
 * @version 创建时间：Dec 20, 2016 4:48:04 PM
 * 
 */
public class IIlegalInvokeException extends ActionException{
	
	private static final long serialVersionUID = 1L;

	public IIlegalInvokeException(String message)
	{
		super(message);
	}
	
	public IIlegalInvokeException(Exception e)
	{
		super(e.getMessage());
	}
	
	
	
}