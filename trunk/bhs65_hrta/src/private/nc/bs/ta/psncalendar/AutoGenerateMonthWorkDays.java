package nc.bs.ta.psncalendar;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import nc.bs.dao.BaseDAO;
import nc.bs.pub.pa.PreAlertObject;
import nc.bs.pub.pa.PreAlertReturnType;
import nc.bs.pub.taskcenter.BgWorkingContext;
import nc.bs.pub.taskcenter.IBackgroundWorkPlugin;
import nc.jdbc.framework.processor.ResultSetProcessor;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pub.lang.UFLiteralDate;

public class AutoGenerateMonthWorkDays implements IBackgroundWorkPlugin {

	@Override
	public PreAlertObject executeTask(BgWorkingContext bgwc)
			throws BusinessException {
		PreAlertObject retObj = new PreAlertObject();
		retObj.setReturnObj("Successful.");
		retObj.setReturnType(PreAlertReturnType.RETURNMESSAGE);

		HashMap<String, Object> km = bgwc.getKeyMap();
		String pk_psndoc = (String) km.get("pk_psndoc");
		String cyearperiod = (String) km.get("cyearperiod");
		List<WorkDaysVO> psnWorkType = getPsnWorkDayType(pk_psndoc);
		if(psnWorkType == null || psnWorkType.isEmpty()){
			return retObj;
		}
		
		if(cyearperiod == null){
			UFDate current = new UFDate();
			cyearperiod = current.getYear() + "" +current.getStrMonth();
		}
		String cyear = cyearperiod.substring(0, 4);
		String cperiod = cyearperiod.substring(4);
		
		//先删除
		BaseDAO dao = new BaseDAO();
		dao.deleteByClause(WorkDaysVO.class, " cyearperiod = '"+cyearperiod+"' ");
		
		//再生成
		Map<String, UFDouble> workingDays = getWorkingDays(cyear, cperiod);
		for(WorkDaysVO vo : psnWorkType){
			vo.setCyearperiod(cyearperiod);
			vo.setCyear(cyear);
			vo.setCperiod(cperiod);
			vo.setDays(workingDays.get(vo.getPk_workdaytype()));
		}
		
		dao.insertVOList(psnWorkType);
		
		return retObj;
	}

	private Map<String, UFDouble> getWorkingDays(String cyear, String cperiod) throws BusinessException {
		Map<String, UFDouble> workingDays = new HashMap<String, UFDouble>();
		Map<String, String> workDayTypes = getWorkDayTypes();
		Set<Entry<String, String>> entrys = workDayTypes.entrySet();
		UFLiteralDate firstDayofMonth = new UFLiteralDate(cyear + "-" + cperiod + "-01" );
		UFLiteralDate lastDayofMonth = firstDayofMonth.getDateAfter(firstDayofMonth.getDaysMonth()-1);
		for(Entry<String, String>  entry : entrys){
			UFDouble days = getWorkingDays(firstDayofMonth, lastDayofMonth, entry.getValue());
			workingDays.put(entry.getKey(), days);
		}
		return workingDays;
	}

	/**
	 * add chenth 20181108 根据人员信息上定义的一周工作天数，来计算工作时长
	 * @param begindate
	 * @param enddate
	 * @param pk_psndoc
	 * @param pk_org
	 * @return
	 * @throws BusinessException
	 */
	private UFDouble getWorkingDays(UFLiteralDate begindate,
			UFLiteralDate enddate, String workdayType) throws BusinessException {
		UFDouble days = UFDouble.ZERO_DBL;
		UFDouble incremental = UFDouble.ZERO_DBL;
		UFDouble weekendincremental = UFDouble.ZERO_DBL;
		//默认5.5天
		if(workdayType == null){
			workdayType = "5.5";
		}
		switch(workdayType){
			case "5":
			case "5.5":
			case "6":
				incremental = UFDouble.ONE_DBL;
				break;
			case "5 PT":
			case "5.5 PT":
				incremental = new UFDouble(0.5);
				break;
			default:
				incremental = UFDouble.ZERO_DBL;
		}
		switch(workdayType){
			case "6":
				weekendincremental = UFDouble.ONE_DBL;
				break;
			case "5.5":
			case "5.5 PT":
				weekendincremental = new UFDouble(0.5);
				break;
			default:
				weekendincremental = UFDouble.ZERO_DBL;
		}
		while(begindate.compareTo(enddate)<1){
			int week = begindate.getWeek() == 0 ? 7 : begindate
					.getWeek();
			if(week < 6){
				days = days.add(incremental);
			}else if(week == 6){
				days = days.add(weekendincremental);
			}
			begindate = begindate.getDateAfter(1);
		}
		
		return days;
	}

	/**
	 * add chenth 20181108
	 * @param pk_psndoc
	 * @return
	 * @throws BusinessException
	 */
	private List<WorkDaysVO> getPsnWorkDayType(String pk_psndoc) throws BusinessException {
		StringBuffer sql = new StringBuffer(" select pk_psndoc,p.glbdef39 as pk_workdaytype, d.name as workdaytype from bd_psndoc p left join bd_defdoc d on p.glbdef39 = d.pk_defdoc ");
		sql.append(" where isnull(p.glbdef39,'~') != '~' ");
		if(pk_psndoc != null){
			sql.append(" and p.pk_psndoc = '").append(pk_psndoc).append("' ");
		}
		BaseDAO dao = new BaseDAO();
		List<WorkDaysVO> relist =  (List<WorkDaysVO>) dao.executeQuery(sql.toString(), new ResultSetProcessor(){

			@Override
			public Object handleResultSet(ResultSet rs) throws SQLException {
				List<WorkDaysVO> relist = new ArrayList<WorkDaysVO>();
				 while (rs.next()) {
					 WorkDaysVO vo = new WorkDaysVO();
					 vo.setPk_psndoc(rs.getString("pk_psndoc"));
					 vo.setPk_workdaytype(rs.getString("pk_workdaytype"));
					 vo.setWorkdaytype(rs.getString("workdaytype"));
					 relist.add(vo);
			        }
				return relist;
			}
			
		});
		return relist;
	}
	
	/**
	 * add chenth 20181108
	 * @param pk_psndoc
	 * @return
	 * @throws BusinessException
	 */
	private Map<String,String> getWorkDayTypes() throws BusinessException {
		StringBuffer sql = new StringBuffer(" select a.pk_defdoc,a.name from bd_defdoc a inner join bd_defdoclist b on a.pk_defdoclist = b.pk_defdoclist where b.code = 'BHS08' ");
		
		BaseDAO dao = new BaseDAO();
		Map<String,String> remap =  (Map<String,String>) dao.executeQuery(sql.toString(), new ResultSetProcessor(){

			@Override
			public Object handleResultSet(ResultSet rs) throws SQLException {
				Map<String,String> remap = new HashMap<String,String>();
				while (rs.next()) {
					 remap.put(rs.getString("pk_defdoc"), rs.getString("name"));
			    }
				return remap;
			}
			
		});
		return remap;
	}

}
