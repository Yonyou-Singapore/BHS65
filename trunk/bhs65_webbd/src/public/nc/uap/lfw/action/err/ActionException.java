package nc.uap.lfw.action.err;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

/**
 * @author yonou.jensen
 * @version 创建时间：Dec 20, 2016 10:33:44 AM
 * 
 */
public class ActionException  extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ActionException(String message)
	{
		super(message);
	}
	
	public ActionException(Exception e)
	{
		super(e.getMessage());
	}
	
	public static String toString(Exception e)
	{
		Writer w = new StringWriter();
		e.printStackTrace(new PrintWriter(w));
		return w.toString();
	}
}