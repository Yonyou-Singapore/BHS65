package nc.vo.bhs.formula.function;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import nc.bs.framework.common.NCLocator;
import nc.bs.logging.Logger;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.SQLParameter;
import nc.jdbc.framework.processor.ColumnListProcessor;
import nc.ui.dbcache.DBCacheFacade;
import nc.ui.ml.NCLangRes;
import nc.vo.dbcache.query.Criteria;
import nc.vo.dbcache.query.MatchCriteria;
import nc.vo.dbcache.query.QueryClause;
import nc.vo.dbcache.query.Table;
import nc.vo.pub.BusinessException;
import nc.vo.pub.formulaset.IFormulaConstant;
import nc.vo.pub.formulaset.core.ParseException;
import nc.vo.pub.formulaset.function.NcInnerFunction;
import nc.vo.pub.formulaset.function.PostfixMathCommand.ArrayWithInfo;

/**
 * 根据订单主键获取订单明细字段values值
 * @author Thinkpad
 * @date 2017-6-24
 */
public class GetSoOrderDetailFieldValues extends NcInnerFunction{

	public GetSoOrderDetailFieldValues() {
		// TODO 自动生成的构造函数存根
	}
	/**
	 * 参数个数
	 * @author Thinkpad
	 * @date 2017-6-24
	 */
	@Override
	public int getNumberOfParameters() {
		// TODO 自动生成的方法存根
		return 2;
	}
	
	List<String> list = new ArrayList<String>();
	
	@Override
	public void run(Stack inStack) throws ParseException {
		// TODO 自动生成的方法存根
		Object csaleorderid = inStack.pop();
		if(csaleorderid == null){
			return;
		}
		Object fieldcode = inStack.pop();
		if(fieldcode == null){
			return;
		}
		List<Object> list;
		try {
			list = queryValues(fieldcode.toString(),csaleorderid.toString());
		} catch (BusinessException e) {
			// TODO 自动生成的 catch 块
			throw new ParseException("执行查询订单明细字段信息公式getSoOrderDetailFieldValues错误"+e.getMessage());
		}
		if(list == null || list.size() == 0){
			return;
		}
		inStack.push(list);
	}
	/**
	 * 执行查询
	 * @author Thinkpad
	 * @date 2017-6-24
	 */
	private List<Object> queryValues(String fieldcode,String csaleorderid) throws BusinessException{
		
		if(list.size() != 0 && csaleorderid != null && csaleorderid.indexOf(",") == -1){
			list.clear();
			list.add(csaleorderid);
		}else if(csaleorderid != null && csaleorderid.indexOf(",") == -1 ){
			list.add(csaleorderid);
		}
		csaleorderid = list.get(0);
		if(csaleorderid != null && csaleorderid.length() >= 21){
			csaleorderid = csaleorderid.substring(1,21);
		}
		
		String sql = " select "+fieldcode+" from so_saleorder_b where csaleorderid = '"+csaleorderid+"' and isnull(dr,0) = 0 ";
		IUAPQueryBS service = NCLocator.getInstance().lookup(IUAPQueryBS.class);
		List<Object> list = (List<Object>) service.executeQuery(sql, new ColumnListProcessor());
		
		return list;
	}
	
	/**
	 * 公式说明
	 * @author Thinkpad
	 * @date 2017-6-24
	 */
	@Override
	public String getFunctionDesc() {
		// TODO 自动生成的方法存根
		return "getSoOrderDetailFieldValues(\"fieldcode\",csaleorderid)  根据订单主键获取销售订单明细字段信息";
	}
	/**
	 * 公式在数据库页签
	 * @author Thinkpad
	 * @date 2017-6-24
	 */
	@Override
	public int getFunctionType() {
		// TODO 自动生成的方法存根
		return IFormulaConstant.FUN_DB;
	}
	
	
	
}
