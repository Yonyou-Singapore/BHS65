package nc.ui.bhs.pub.action;

import nc.vo.pub.lang.UFDouble;

/**
 * 数据格式化工具类
 * @author Thinkpad
 * 2017-03-13
 */
public class DataUtils {
	
	private static DataUtils instance;
	
	public static DataUtils getInstance(){
		if(instance == null){
			instance = new DataUtils();
		}
		return instance;
	}
	
	public UFDouble getUFDouble(Object obj){
		return obj == null?UFDouble.ZERO_DBL:new UFDouble(obj.toString());
	}

}
