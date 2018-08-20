package nc.bs.bhs.sotruck;

import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.vo.bhs.sotruck.SoTruckHVO;
import nc.vo.pub.AggregatedValueObject;

/**
 * truck单据动作按钮前后事件
 * @author Thinkpad
 * 20170514
 */
public class TruckAction {
	
	private static BaseDAO  dao = null;

	public TruckAction() {
		// TODO 自动生成的构造函数存根
	}
	
	private static BaseDAO getdao(){
		if(dao == null){
			dao = new BaseDAO();
		}
		return dao;
	}
	/**
	 * 保存后事件
	 * @param aggvo
	 * @throws DAOException 
	 */
	public void afterSaveAction(AggregatedValueObject aggvo) throws DAOException{
		
		String inouttype = null;
		String vessel = null;
		String voyage = null;
		
		SoTruckHVO headvo = (SoTruckHVO) aggvo.getParentVO();
		//进口
		String tsimportvessel = headvo.getTsimportvessel();
		String tsimportvoyage = headvo.getTsimportvoyage();
		//出口
		String tsexportvessel = headvo.getTsfeexportvessel();
		String tsexportvoyage = headvo.getTsfeexportvoyage();
		
		if(tsimportvessel != null && !tsimportvessel.equals("") &&!tsimportvessel.equals("null") ){
			inouttype = getInoutTypePK("IN");
			vessel = tsimportvessel;
		}
		
		if(tsimportvoyage != null && !tsimportvoyage.equals("") &&!tsimportvoyage.equals("null") ){
			voyage = tsimportvoyage;
		}
		if(tsexportvessel != null && !tsexportvessel.equals("") &&!tsexportvessel.equals("null") ){
			inouttype = getInoutTypePK("OUT");
			vessel = tsexportvessel;
		}
		if(tsexportvoyage != null && !tsexportvoyage.equals("") &&!tsexportvoyage.equals("null") ){
			voyage = tsexportvoyage;
		}
		String csaleorderid = headvo.getCsaleorderid();
		String sql = " update so_saleorder set vdef11 = '"+inouttype+"' ,  vdef12 = '"+voyage+"' , vdef13 = '"+vessel+"'  where csaleorderid = '"+csaleorderid+"'  ";
		getdao().executeUpdate(sql);
	}
	/**
	 * 根据编码获取进出口类型的主键
	 * @param code
	 * @return
	 * @throws DAOException
	 */
	private String getInoutTypePK(String name) throws DAOException{
		String sql = " select pk_defdoc from bd_defdoc where pk_defdoclist in (select pk_defdoclist from bd_defdoclist where code = 'BHS60') and name = '"+name+"'  ";
		Object obj = getdao().executeQuery(sql, new ColumnProcessor());
		return obj == null?"":obj.toString();
	}
	
}
