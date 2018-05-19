package nc.uap.lfw.action.view;

import java.util.HashMap;

/**
 * Model
 * @author yonou.jensen
 * @version 创建时间：Dec 20, 2016 9:51:12 AM
 * 
 */
public class ModelData  extends HashMap<String, Object>{

	private static final long serialVersionUID = 1L;
	
	public void addAttribute(String key,Object val)
	{
		this.put(key, val);
	}
	
	
	public Object getAttribute(String key)
	{
		return this.get(key);
	}

}
