package nc.uap.lfw.action.view;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Render View
 * @author yonyou.jensen
 *
 */
public interface RenderView {

	void render(HttpServletRequest request,HttpServletResponse response);
}
