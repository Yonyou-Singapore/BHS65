package nc.uap.lfw.action.err;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

/**
 * @author yonou.jensen
 * @version 创建时间：Dec 20, 2016 10:34:47 AM
 * 
 */
public class RenderException extends ActionException{
	
	private static final long serialVersionUID = 1L;

	public RenderException(String message)
	{
		super(message);
	}
	
	public RenderException(Exception e)
	{
		super(e.getMessage());
	}
	
	
	
}
