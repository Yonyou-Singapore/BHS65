package nc.uap.lfw.action.view;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nc.ms.tb.oba.jackson.map.ObjectMapper;
import nc.uap.lfw.action.err.RenderException;

/**
 * JsonView
 * @author yonou.jensen
 * @version 创建时间：Dec 20, 2016 9:38:32 AM
 * 
 */
public class JsonView extends AbstractView{
	
	public JsonView()
	{
		this.setContentType("application/json; charset=utf-8");
		this.setEncoding("UTF-8");
	}

	@Override
	public void render(HttpServletRequest request, HttpServletResponse response) {
		this.setContentType("text/html; charset="+this.getEncoding().toLowerCase());
		super.setResponseHeader(response);
		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.writeValue(response.getOutputStream(), this.getModel());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RenderException(e);
		} 
		
	}

}
