package nc.pubitf.bhs.api.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.bhs.api.IBlackBox;
import nc.vo.bhs.api.rest.utils.RestUtils;
import nc.vo.bhs.blackbox.JobOrder;
import nc.vo.bhs.blackbox.PlanInfoVO;
import nc.vo.pub.BusinessException;

import org.json.JSONString;

import uap.ws.rest.resource.AbstractUAPRestResource;

/**
 * @description
 *              BlackBox OpenAPI
 *              <li>
 *              http://127.0.0.1:80/uapws/rest/blackbox/getblackbox?
 *              </li>
 * @scene
 *
 * @param
 *
 *
 * @since 6.5
 * @version 2017-04-23 
 * @author chenth
 */
@Path("blackbox")
public class BlackBoxApi extends AbstractUAPRestResource {

	@Override
	public String getModule() {
		return "hr";
	}
	

	  @POST
	  @Path("getblackbox")
	  @Consumes("application/json")
	  @Produces("application/json")
	  public JSONString getBlackBox(PlanInfoVO planinfo) throws BusinessException {
	    if (planinfo == null 
	    		|| planinfo.getPlandate() == null
	    		|| "".equals(planinfo.getPlandate())) {
	      return RestUtils.emptyJSONString;
	    }
	    RestUtils.initInvocationInfo();

	    IBlackBox blackBox =
	            NCLocator.getInstance().lookup(IBlackBox.class);
	    JobOrder[] orders = blackBox.getBlackBox(planinfo);
	    return RestUtils.toJSONString(orders);
	  }
	  
	  @POST
	  @Path("getjoborder")
	  @Consumes("application/json")
	  @Produces("application/json")
	  public JSONString getJobOrder(PlanInfoVO planinfo) throws BusinessException {
//	    if (planinfo == null 
//	    		|| planinfo.getPlandate() == null
//	    		|| "".equals(planinfo.getPlandate())) {
//	      return RestUtils.emptyJSONString;
//	    }
	    RestUtils.initInvocationInfo();

	    IBlackBox blackBox =
	            NCLocator.getInstance().lookup(IBlackBox.class);
	    JobOrder[] orders = blackBox.getJobOrder(planinfo);
	    return RestUtils.toJSONString(orders);
	  }


}
