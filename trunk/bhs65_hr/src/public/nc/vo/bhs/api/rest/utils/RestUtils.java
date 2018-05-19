package nc.vo.bhs.api.rest.utils;

import java.io.UnsupportedEncodingException;

import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.framework.common.NCLocator;
import nc.login.bs.INCUserQueryService;
import nc.pubitf.org.IGroupPubService;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.json.GsonUtils;
import nc.vo.sm.UserVO;

import org.json.JSONString;

import uap.ws.rest.util.UAPRSConstance;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class RestUtils {

  public static final JSONString emptyJSONString = new JSONString() {

    @Override
    public String toJSONString() {
      return "";
    }
  };

  /**
   * 初始化InvocationInfo
   */
  public static void initInvocationInfo() {
    String dataSource = InvocationInfoProxy.getInstance().getUserDataSource();
    String userCode = InvocationInfoProxy.getInstance().getUserCode();
    try {
      UserVO userVO = NCLocator.getInstance().lookup(INCUserQueryService.class)
          .findUserVO(dataSource, userCode);
      if (userVO != null) {
        InvocationInfoProxy.getInstance().setGroupId(userVO.getPk_group());
        String groupNo = NCLocator.getInstance().lookup(IGroupPubService.class)
            .getGroupNoByPK(userVO.getPk_group());
        InvocationInfoProxy.getInstance().setGroupNumber(groupNo);
        InvocationInfoProxy.getInstance().setUserId(userVO.getPrimaryKey());
      }
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
  }


  /**
   * 创建NC系统的GsonBuilder
   * 
   * @return Gson
   */
  public static Gson buildNCGson() {
	  GsonBuilder gsonBuilder = GsonUtils.gsonBuilder4Rest();
	  gsonBuilder.serializeNulls();
	  return gsonBuilder.create();
  }

  /**
   * 使用Gson转换对象
   * 
   * @param object
   * @return
   */
  public static JSONString toJSONString(final Object object) {
    return new JSONString() {

      @Override
      public String toJSONString() {
        String json = buildNCGson().toJson(object);
        try {
          String jsonCode = new String(json.getBytes(UAPRSConstance.CHARSET),
              UAPRSConstance.CHARSET);
          return jsonCode;
        }
        catch (UnsupportedEncodingException e) {
          ExceptionUtils.wrappException(e);
        }
        return null;
      }
    };
  }

  /**
   * 转换为JSONString，处理了编码
   * 
   * @param value
   * @return
   */
  public static JSONString toJSONString2(final Object object) {
    return new JSONString() {

      @Override
      public String toJSONString() {
        String json = buildNCGson().toJson(object);
        try {

          String iso =
              new String(json.getBytes(UAPRSConstance.CHARSET), "ISO-8859-1");
          String jsonCode =
              new String(iso.getBytes("ISO-8859-1"), UAPRSConstance.CHARSET);

          return jsonCode;
        }
        catch (UnsupportedEncodingException e) {
          ExceptionUtils.wrappException(e);
        }
        return null;
      }
    };
  }
}
