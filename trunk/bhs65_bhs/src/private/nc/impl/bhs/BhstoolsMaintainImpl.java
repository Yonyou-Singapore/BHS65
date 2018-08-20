package nc.impl.bhs;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pub.ace.AceBhstoolsPubServiceImpl;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.itf.bhs.IBhstoolsMaintain;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.bhs.bhstools.AggBhsToolsHeadVO;
import nc.vo.pu.report.queryinfo.praybill.PrayBillQryInfoPara;
import nc.vo.pub.BusinessException;
import nc.vo.pub.TempTableUtil;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.query.ConditionVO;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

import com.ufida.dataset.IContext;
import com.ufida.report.anareport.FreeReportContextKey;
import com.ufida.report.anareport.base.BaseQueryCondition;

public class BhstoolsMaintainImpl extends AceBhstoolsPubServiceImpl
		implements IBhstoolsMaintain {

	@Override
	public void delete(AggBhsToolsHeadVO[] clientFullVOs,
			AggBhsToolsHeadVO[] originBills) throws BusinessException {
		super.pubdeleteBills(clientFullVOs, originBills);
	}

	@Override
	public AggBhsToolsHeadVO[] insert(AggBhsToolsHeadVO[] clientFullVOs,
			AggBhsToolsHeadVO[] originBills) throws BusinessException {
		return super.pubinsertBills(clientFullVOs, originBills);
	}

	@Override
	public AggBhsToolsHeadVO[] update(AggBhsToolsHeadVO[] clientFullVOs,
			AggBhsToolsHeadVO[] originBills) throws BusinessException {
		return super.pubupdateBills(clientFullVOs, originBills);
	}

	@Override
	public AggBhsToolsHeadVO[] query(IQueryScheme queryScheme)
			throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

	@Override
	public AggBhsToolsHeadVO[] save(AggBhsToolsHeadVO[] clientFullVOs,
			AggBhsToolsHeadVO[] originBills) throws BusinessException {
		return super.pubsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggBhsToolsHeadVO[] unsave(AggBhsToolsHeadVO[] clientFullVOs,
			AggBhsToolsHeadVO[] originBills) throws BusinessException {
		return super.pubunsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggBhsToolsHeadVO[] approve(AggBhsToolsHeadVO[] clientFullVOs,
			AggBhsToolsHeadVO[] originBills) throws BusinessException {
		return super.pubapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggBhsToolsHeadVO[] unapprove(AggBhsToolsHeadVO[] clientFullVOs,
			AggBhsToolsHeadVO[] originBills) throws BusinessException {
		return super.pubunapprovebills(clientFullVOs, originBills);
	}

	/**
	 * 工具报表
	 * @author Thinkpad
	 * @date 2017-7-17
	 */
	@Override
	public String getToolReportSql(IContext context) throws BusinessException {
		// TODO 自动生成的方法存根
		TempTableUtil tmputil = new TempTableUtil();
		//临时表
		String temptable = tmputil.getToolTempTable();

		BaseQueryCondition conditionvo =
		          (BaseQueryCondition) context
		              .getAttribute(FreeReportContextKey.KEY_IQUERYCONDITION);
		 if (conditionvo == null ) {
	    	  return buildFinalSQL(temptable);
	      }    
		ConditionVO[] conditionvos = (ConditionVO[]) context.getAttribute(FreeReportContextKey.KEY_REPORT_QUERYCONDITIONVOS);
		List<ConditionVO> list = new ArrayList<ConditionVO>();
		UFDate ddate = new UFDate();
		for (int i = 0; i < conditionvos.length; i++) {
			if(conditionvos[i].getFieldCode().equals("ddate")){
				ddate = new UFDate(conditionvos[i].getValue().toString());
			}else{
				list.add(conditionvos[i]);
			}
		}
		String whereSql = null;
		if(list != null && list.size() > 0){
			 whereSql = conditionvos[0].getWhereSQL(list.toArray(new ConditionVO[0]));
		}
		
		initTempTableOnHandNumandOneData(temptable, whereSql, ddate.toString());
		for(int i = 2; i <= 14;i++){
			initTempTableTwoData(temptable, whereSql, ddate.getDateAfter(i-1).toString(), i);
		}
		return buildFinalSQL(temptable);
	}
	
	/**
	 * 最终查询SQL
	 * @param table
	 * @return
	 */
	public String buildFinalSQL(String table){
		SqlBuilder sql = new SqlBuilder();
	    sql.append(" select pk_material,sum(nnum1) as nnum1,");
	    sql.append(" sum(nnum1+nnum2) as nnum2,");
	    sql.append(" sum(nnum1+nnum2+nnum3) as nnum3,");
	    sql.append(" sum(nnum1+nnum2+nnum3+nnum4) as nnum4,");
	    sql.append(" sum(nnum1+nnum2+nnum3+nnum4+nnum5) as nnum5,");
	    sql.append(" sum(nnum1+nnum2+nnum3+nnum4+nnum5+nnum6) as nnum6,");
	    sql.append(" sum(nnum1+nnum2+nnum3+nnum4+nnum5+nnum6+nnum7) as nnum7,");
	    sql.append(" sum(nnum1+nnum2+nnum3+nnum4+nnum5+nnum6+nnum7+nnum8) as nnum8,");
	    sql.append(" sum(nnum1+nnum2+nnum3+nnum4+nnum5+nnum6+nnum7+nnum8+nnum9) as nnum9,");
	    sql.append(" sum(nnum1+nnum2+nnum3+nnum4+nnum5+nnum6+nnum7+nnum8+nnum9+nnum10) as nnum10,");
	    sql.append(" sum(nnum1+nnum2+nnum3+nnum4+nnum5+nnum6+nnum7+nnum8+nnum9+nnum10+nnum11) as nnum11,");
	    sql.append(" sum(nnum1+nnum2+nnum3+nnum4+nnum5+nnum6+nnum7+nnum8+nnum9+nnum10+nnum11+nnum12) as nnum12,");
	    sql.append(" sum(nnum1+nnum2+nnum3+nnum4+nnum5+nnum6+nnum7+nnum8+nnum9+nnum10+nnum11+nnum12+nnum13) as nnum13,");
	    sql.append(" sum(nnum1+nnum2+nnum3+nnum4+nnum5+nnum6+nnum7+nnum8+nnum9+nnum10+nnum11+nnum12+nnum13+nnum14) as nnum14 from ");
	    sql.append(table);
	    sql.append(" group by pk_material");
	    return sql.toString();
	}
	/**
	 * 现存量+第一天数据
	 * @param tablename
	 * @param whereSql
	 */
	public void initTempTableOnHandNumandOneData(String tablename,String whereSql,String ddate){
		
		StringBuffer bufferSql = new StringBuffer();
		bufferSql.append(" insert into "+ tablename);
		bufferSql.append(" (pk_material,nnum1,nnum2,nnum3,nnum4,nnum5,nnum6,nnum7,nnum8,nnum9,nnum10,nnum11,nnum12,nnum13,nnum14) ");
		bufferSql.append("  select pk_material, nnum1,0 as nnum2,0 as nnum3,0 as nnum4,0 as nnum5,0 as nnum6,0 as nnum7,0 as nnum8,0 as nnum9,0 as nnum10, ");
		bufferSql.append("  0 as nnum11,0 as nnum12,0 as nnum13,0 asnnum14 from (");
		bufferSql.append("	select data.pk_material,sum(isnull(data.nxclnum,0))-sum(isnull(data.njcnum,0))+ sum(isnull(data.nhhnum,0))  as nnum1 from (	");
		bufferSql.append("		");
		bufferSql.append("	select b.cmaterialoid as pk_material,sum(h.nonhandnum) as nxclnum,0 as njcnum,0 as nhhnum from ic_onhandnum h	");
		bufferSql.append("	inner join  ic_onhanddim  b on h.pk_onhanddim = b.pk_onhanddim 	");
		bufferSql.append("	where isnull(h.dr,0) = 0 and isnull(b.dr,0) = 0	");
		bufferSql.append("	group by b.cmaterialoid	");
		bufferSql.append("		");
		bufferSql.append("	union all	");
		bufferSql.append("	select pk_material,0 as nxclnum,isnull(estissueqty,0) as njcnum,0 as nhhnum from bhs_bhstools_b b where isnull(b.dr,0) = 0 	");
		bufferSql.append("	and  left(estissuedate,10) <= '"+ddate.substring(0,10)+"'  and   left(estissuedate,10) >= '"+new UFDate().toString().substring(0,10)+"' ");
		bufferSql.append("	union all	");
		bufferSql.append("	select pk_material,0 as nxclnum,0 as njcnum,isnull(estreturnqty,0) as nhhnum from bhs_bhstools_b b where isnull(b.dr,0) = 0 	");
		bufferSql.append("  and  left(estreturndate,10) <= '"+ddate.substring(0,10)+"'  and left(estreturndate,10) >= '"+new UFDate().toString().substring(0,10)+"' ");
		bufferSql.append("	) data group by data.pk_material ) alldata where 1=1	");

		if(whereSql != null){
			bufferSql.append(" and "+whereSql);
		}
		DataAccessUtils dbutil = new DataAccessUtils();
		dbutil.update(bufferSql.toString());
	}
	
	/**
	 * 第2天
	 * @param tablename
	 * @param whereSql
	 */
	public void initTempTableTwoData(String tablename,String whereSql,String ddate,int i){
		
		StringBuffer bufferSql = new StringBuffer();
		bufferSql.append(" insert into "+ tablename);
		bufferSql.append(" (pk_material,nnum1,nnum2,nnum3,nnum4,nnum5,nnum6,nnum7,nnum8,nnum9,nnum10,nnum11,nnum12,nnum13,nnum14) ");
		bufferSql.append("  select pk_material,  ");
		for (int j = 1; j <= 14; j++) {
			
			if( j == 14){
				if(j == i){
					bufferSql.append("nnum"+j);
				}else{
					bufferSql.append( " 0 as nnum"+j);
				}
			}else{
				
				if(j == i){
					bufferSql.append("nnum"+j + ",");
				}else{
					bufferSql.append( " 0 as nnum"+j + ",");
				}
			}
		}
		bufferSql.append("	from ( select data.pk_material , sum(isnull(data.nhhnum,0))-sum(isnull(data.njcnum,0))  as nnum"+i+" from (	");
		bufferSql.append("	select pk_material,isnull(estissueqty,0) as njcnum,0 as nhhnum from bhs_bhstools_b b where isnull(b.dr,0) = 0  	");
		bufferSql.append("	and  left(estissuedate,10) = '"+ddate.substring(0,10)+"'");
		bufferSql.append("	union all	");
		bufferSql.append("	select pk_material,0 as njcnum,isnull(estreturnqty,0) as nhhnum from bhs_bhstools_b b where isnull(b.dr,0) = 0 	");
		bufferSql.append("  and  left(estreturndate,10) = '"+ddate.substring(0,10)+"' ");
		bufferSql.append("	) data group by data.pk_material ) alldata where 1=1	");
		if(whereSql != null){
			bufferSql.append(" and "+whereSql);
		}
		DataAccessUtils dbutil = new DataAccessUtils();
		dbutil.update(bufferSql.toString());
	}
	
	
	


}
