package uap.ws.rest.filter;

import org.restlet.Context;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.data.Form;
import org.restlet.engine.http.header.HeaderConstants;
import org.restlet.routing.Filter;

import uap.ws.rest.service.UAPAccessControlService;

public class UAPAccessControlFilter extends Filter {

	public UAPAccessControlFilter(Context context,
			UAPAccessControlService uapAccessControlService) {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected int beforeHandle(Request request, Response response) {
		Form httpHeader = (Form) request.getAttributes().get(
				"org.restlet.http.headers");
		String host = httpHeader.getFirstValue("host");// IP
		// CallContext callContext = (CallContext) request.getAttributes().get(
		// "org.restlet.ext.jaxrs.CallContext");
		// String url = callContext.toString();// URL
		// ((HttpRequest)request).get

		//add chenth 20170430 解决跨域访问问题
		// Initialize response headers
		Form responseHeaders = (Form) response
                    .getAttributes().get(HeaderConstants.ATTRIBUTE_HEADERS);
		if(responseHeaders == null){
			responseHeaders = new Form();
		}

        String requestOrigin = httpHeader.getFirstValue("Origin",
            false, "*");
        String rh = httpHeader.getFirstValue(
            "Access-Control-Request-Headers", false, "*");

        // Set CORS headers in response
//        responseHeaders.set(
//            "Access-Control-Expose-Headers",
//            "Authorization, Link");
//        responseHeaders.set("Access-Control-Allow-Credentials", "true");
//        responseHeaders.set("Access-Control-Allow-Methods",
//            "GET,POST,PUT,DELETE");
//        responseHeaders.set("Access-Control-Allow-Origin", requestOrigin);
//        responseHeaders.set("Access-Control-Allow-Headers", "Cache-Control, Pragma, Origin, Authorization, Content-Type, X-Requested-With");

        responseHeaders.set("Access-Control-Allow-Origin", "*");
        responseHeaders.set("Access-Control-Allow-Headers", "Cache-Control, Pragma, Origin, Authorization, Content-Type, X-Requested-With");
        responseHeaders.set("Access-Control-Allow-Methods", "GET, PUT, POST");
			
        // Set response headers
        response.getAttributes().put(HeaderConstants.ATTRIBUTE_HEADERS,
            responseHeaders);

        // Handle HTTP methods
        /*if (org.restlet.data.Method.OPTIONS.equals(request.getMethod())) {
            return Filter.STOP;
        }*/
       
		return super.beforeHandle(request, response);
	}

	@Override
	protected void afterHandle(Request request, Response response) {
		// TODO Auto-generated method stub
		super.afterHandle(request, response);
	}
}
