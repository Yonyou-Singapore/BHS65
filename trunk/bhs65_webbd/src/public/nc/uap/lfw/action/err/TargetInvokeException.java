package nc.uap.lfw.action.err;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

/**
 * @author yonou.jensen
 * @version 创建时间：Dec 20, 2016 10:39:47 AM
 * 
 */
public class TargetInvokeException extends ActionException{
	
	private static final long serialVersionUID = 1L;

	public TargetInvokeException(String message)
	{
		super(message);
	}
	
	public TargetInvokeException(Exception e)
	{
		super(e.getMessage());
	}
	
	
	
}
