package nc.uap.lfw.action.view;

import javax.servlet.http.HttpServletResponse;

/**
 * @author yonou.jensen
 * @version 创建时间：Dec 20, 2016 9:50:40 AM
 * 
 */
public abstract class AbstractView implements RenderView{
	
	private static final String DEFAULT_CONTENTTYPE = "text/html";
	
	private static final String DEFAULT_ENCODING = "UTF-8";

	private ModelData model;
	
	private String contentType;
	
	private String encoding;
	
	protected AbstractView()
	{
		this.model = new ModelData();
		this.contentType = DEFAULT_CONTENTTYPE;
		this.encoding = DEFAULT_ENCODING;
		
	}
	
	public ModelData getModel()
	{
		return this.model;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}
	
	protected void setResponseHeader(HttpServletResponse response)
	{
		response.setCharacterEncoding(encoding);  
		response.setContentType(contentType); 
	}
}
