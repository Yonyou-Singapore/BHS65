package nc.uap.portal.user.chain;

import java.security.MessageDigest;

import javax.servlet.http.HttpServletRequest;

import nc.uap.cpb.org.itf.ICpUserQry;
import nc.uap.cpb.org.util.CpbServiceFacility;
import nc.uap.cpb.org.vos.CpUserVO;
import nc.uap.lfw.util.CookieUtil;
import nc.uap.portal.log.PortalLogger;
import nc.uap.portal.login.itf.LoginInterruptedException;
import nc.uap.portal.login.vo.AuthenticationUserVO;

import org.apache.commons.lang.StringUtils;

import sun.misc.BASE64Encoder;

/**保持登录校验链
 * @author guoshg
 */
public class KeepSessionLoinVerifyChain extends AbstractVerifyChain {
	
	public static final String KEY = "ufida&UAP!102";
	
	@Override
	public void doVerify(HttpServletRequest request, VerifyAtomChain chain) {
		String res = CookieUtil.get(request.getCookies(),"p_auth");
		if(res == null)
			return;
		String userid=CookieUtil.get(request.getCookies(), "p_userId");
		if(StringUtils.isNotBlank(userid)){
			ICpUserQry userQry = CpbServiceFacility.getCpUserQry();
			CpUserVO cpUserVO = null;
			try {
				cpUserVO = userQry.getUserByCodeWithGroupAdmin(userid);
			} catch (Exception e) {
				PortalLogger.error(e.getMessage(), e);
			}
			if (cpUserVO != null) {
				StringBuffer key=new StringBuffer(cpUserVO.getUser_name());
				key.append(cpUserVO.getCreationtime().toString());
				key.append(cpUserVO.getUser_password());
				key.append(KEY);
				
				String md5=md5encoder(key.toString());
				String from=request.getHeader("referer");//http://localhost/portal/
				if(StringUtils.isNotBlank(res) && md5.equals(res) ){
					AuthenticationUserVO userVO = new AuthenticationUserVO();
					userVO.setUserID(userid);
					try {
						doLogin(userVO);
					} catch (LoginInterruptedException e) {
						PortalLogger.error(e.getMessage(), e);
					}
					if (hasLogin())
						chain.doFilter(request);
				}
			}
		}
	}

	@Override
	public int compareTo(IUserVerifyChain o) {
		return 0;
	}
	public String md5encoder(String str) {
		MessageDigest md5 = null;
		String value = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
			BASE64Encoder base64Encoder = new BASE64Encoder();
			value = base64Encoder.encode(md5.digest(str.getBytes("UTF-8")));
		} catch (Exception e) {
			PortalLogger.error(e.getMessage(), e);
		}
		return value;
	}


}
