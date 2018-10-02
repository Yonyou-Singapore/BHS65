package nc.pubitf.bhs.api.rest;

import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import nc.bs.framework.common.NCLocator;
import nc.bs.logging.Logger;
import nc.itf.ta.algorithm.ITimeScopeWithBillInfo;
import nc.pubitf.bhs.api.ILeaveApply;
import nc.vo.bhs.api.rest.utils.RestUtils;
import nc.vo.bhs.leave.Leave;
import nc.vo.pub.BusinessException;
import nc.vo.rest.api.RestMessageVO;
import nc.vo.ta.bill.BillMutexException;
import nc.vo.ta.leave.LeaveCommonVO;

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
@Path("leave")
public class LeaveApi extends AbstractUAPRestResource {

	@Override
	public String getModule() {
		return "hr";
	}
	

	  @POST
	  @Path("leaveApply")
	  @Consumes("application/json")
	  @Produces("application/json")
	  public JSONString leaveApply(Leave leaveVo) throws BusinessException {
	    if (leaveVo == null 
	    		|| leaveVo.getEmployeecode() == null
	    		|| leaveVo.getLeavetypecode() == null) {
	      return RestUtils.emptyJSONString;
	    }
	    RestUtils.initInvocationInfo();

	    RestMessageVO message = new RestMessageVO();
	    message.setBillno(leaveVo.getLeaveapplyid());
	    try{
		    ILeaveApply leaveApply =
		            NCLocator.getInstance().lookup(ILeaveApply.class);
		    boolean result = leaveApply.leaveApply(leaveVo);
		    message.setReturncode("200");
		    message.setDescription("Leave apply success.");
	    }catch(BillMutexException bme){
	    	StringBuffer errorMessage = new StringBuffer(bme.getMessage());
	    	Map<String, Map<Integer, ITimeScopeWithBillInfo[]>> result = bme.getMutexBillsMap();
	    	if(result!=null){
		    	for(String key : result.keySet()){
		    		Map<Integer, ITimeScopeWithBillInfo[]> map = result.get(key);
		    		for(Integer key1 : map.keySet()){
		    			LeaveCommonVO[] billinfos = (LeaveCommonVO[]) map.get(key1);
		    			int i =1;
		    			for(LeaveCommonVO commonvo : billinfos){
		    				errorMessage.append(String.valueOf(i) + ": ");
		    				errorMessage.append(commonvo.getLeavebegintime());
		    				errorMessage.append("--" );
	    					errorMessage.append(commonvo.getLeaveendtime());
		    				errorMessage.append("\r\n" );
	    					i++;
		    			}
		    			
		    		}
		    	}
	    	}
	    	message.setReturncode("400");
	    	message.setDescription("Faild:" + errorMessage);
	    	Logger.error(errorMessage, bme);
	    }catch(Exception e){
	    	message.setReturncode("400");
			message.setDescription("Faild:" + e.getMessage());
			Logger.error(e.getMessage(), e);
	    }
	    return RestUtils.toJSONString(message);
	  }
	  
	  @POST
	  @Path("leaveCancel")
	  @Consumes("application/json")
	  @Produces("application/json")
	  public JSONString leaveCancel(Leave leaveVo) throws BusinessException {
	    RestUtils.initInvocationInfo();
	    
	    RestMessageVO message = new RestMessageVO();
	    try{
		    ILeaveApply leaveApply =
		            NCLocator.getInstance().lookup(ILeaveApply.class);
	    boolean result = leaveApply.leaveCancel(leaveVo);
	    message.setBillno(leaveVo.getLeaveapplyid());
	    message.setReturncode("200");
	    message.setDescription("Leave cancel success.");
	    }catch(Exception e){
	    	message.setReturncode("400");
			message.setDescription("Faild:" + e.getMessage());
			Logger.error(e.getMessage(), e);
	    }
	    return RestUtils.toJSONString(message);
	  }


}

