package nc.uap.portal.ctrl;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import nc.uap.lfw.action.ActionConstants;
import nc.uap.lfw.action.ActionControlPlugin;
import nc.uap.lfw.core.ControlPlugin;
import nc.uap.lfw.core.LfwCoreController;
import nc.uap.lfw.core.LfwRuntimeEnvironment;
import nc.uap.lfw.core.PresentPluginFactory;

/**
 * PortalCoreController
 * @author jensen
 *
 */
public class PortalCoreController  extends LfwCoreController
{
	private static String PATH_ACTION = "/eca.app";

   public PortalCoreController() {}
   
   protected void beforeInitPageModel(HttpServletRequest req, HttpServletResponse res)
   {
     String pageId = LfwRuntimeEnvironment.getWebContext().getPageId();
  }
   


 

   protected ControlPlugin getControlPlugin(HttpServletRequest req, HttpServletResponse res, String path) {
	 
	  if (req.getAttribute(ActionConstants.ATTR_ORIGIN_REQUESTURL)!=null)
	  {
		  String url  = (String)req.getAttribute(ActionConstants.ATTR_ORIGIN_REQUESTURL);
		  if (validReq(url))
		  {
			  return new ActionControlPlugin();
		  }
	  }
     return PresentPluginFactory.getControlPlugin(path);
   }
   
   private boolean validReq(String url)
   {
	   if (StringUtils.isNotBlank(url)  
			   && url.indexOf(ActionConstants.RESPONSE_PATH)==(url.length()-ActionConstants.RESPONSE_PATH.length()))
		   return true;
	   else
		   return false;
   }

 
 
 
  public void handleRequest(HttpServletRequest req, HttpServletResponse res)
     throws Exception
  {
				
    super.handleRequest(req, res);
  }
 }