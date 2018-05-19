package nc.bs.arap.sql;

import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.arap.util.SqlUtils;
import nc.bs.arap.utils.TmpTableCreator;
import nc.itf.arap.fieldmap.IBillFieldGet;
import nc.itf.fipub.report.IPubReportConstants;
import nc.pub.report.utils.ReportSqlUtils;
import nc.utils.arap.ArapReportResource;
import nc.vo.arap.comreport.IArapReportConstants;
import nc.vo.arap.comreport.ReportTableEnum;
import nc.vo.arap.pub.BillEnumCollection;
import nc.vo.arap.tally.BusiTypeEnum;
import nc.vo.arap.tally.TallyVO;
import nc.vo.fipub.report.ReportQueryCondVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;

import org.apache.commons.lang.StringUtils;

/**
 * Ӧ��/Ӧ�����˵���ѯ<br>
 * 
 * @author ������<br>
 * @since V60<br>
 */
public class RecPayStatementSQLCreator extends ArapBaseSqlCreator {

	private static final String BD_TABLE = "bd_table";
	private static final String BD_PK = "bd_pk";
	private static final String BD_CODE = "bd_code";
	private static final String BD_NAME = "bd_name";
	private static final String BUSI_PK = "busi_pk";

	private List<Map<String, String>> bdMaps = new ArrayList<Map<String, String>>();

	private String tmpTblName = null;
	private String[] tmpTblColNames = null;
	private Integer[] tmpTblColTypes = null;

	private static final String anadate = "anadate";
	
	
	private static final String LOCAL_NULL = IPubReportConstants.NULL;

	private static final Integer[] VERIFY_FLAGS = { BusiTypeEnum.INDIFF_VERIFY.getCode(),
			BusiTypeEnum.DIFF_VERIFY.getCode(), BusiTypeEnum.RED_BLUE_VERIFY.getCode() };
	//update for �¼���DBA 20161111
	private static final String fixedDetailField = "pk_group, pk_org, pk_customer, pk_supplier, pk_deptid, pk_psndoc,matcustcode, pk_currtype, brief, pk_billtype, pk_tradetypeid, pk_bill, billno, billno2, busidate,iscounterfee, tallydate, "
			+ anadate
			+ ", invoiceno, dealflag, pk_dealnum, project, debt_qua, debt_ori, debt_loc, gr_debt_loc, gl_debt_loc, credit_qua, credit_ori, credit_loc, gr_credit_loc, gl_credit_loc";

	private String sltAnaDate = null;
	private String endDate = "9999-12-31" + sTime;

	public void setParams(ReportQueryCondVO queryVO) {
		this.queryVO = queryVO;
		sltAnaDate = ReportSqlUtils.getAnaDateField(queryVO.getAnaDate());
		if(queryVO.getEndDate() != null){
			endDate = queryVO.getEndDate().toString();
		}
	}

	public String[] getArrangeSqls() throws SQLException, BusinessException {
		List<String> sqlList = new ArrayList<String>();
		// ��ѯ��ǰ��ѯ�����Ӧ�յ��ݡ��տ�ݡ��ۿۼ�¼�����ʼ�¼�����˷����������ջء��������
		sqlList.add(getDetailSql1());
		//update for �¼���DBA 20161111 chenth
//		if(UFBoolean.FALSE == this.queryVO.getUserObject().get(IArapReportConstants.IS_CANAFTERCODE)){
//		 //��ѯ��ǰ��ѯ����ĵ��ݺ�����¼(��ǵ�ǰ��ѯ����ĵ��ݵĺ�����¼��ͬ���ֺ���������ֺ����������Գ�)
//			sqlList.add(getDetailSql2());
//		}
		return sqlList.toArray(new String[0]);
	}

	public String getResultSql() throws SQLException {
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" select tt.* from ");
		sqlBuffer.append("(");
		//update chenth 20180225  BHS Ӧ�ն��˵�����Ҫ�ڳ�����Ҫ��ʾ����δ�����Ӧ�յ��ͱ��ڵ��տ�
//		sqlBuffer.append("(").append(getPeriodBegin()).append(")"); // �ڳ���markperiod = 1
//		sqlBuffer.append(" union all ");
//		sqlBuffer.append("(").append(getPeriodTally()).append(")"); // ���ڣ�markperiod = 2
		sqlBuffer.append(getPeriodTally()); // ���ڣ�markperiod = 2
		sqlBuffer.append(") tt ");

		sqlBuffer.append(" order by ");
//		sqlBuffer.append("markperiod,pk_org, pk_customer, pk_supplier, pk_deptid, tallydate, pk_billtype, pk_tradetypeid, billno, pk_dealnum, matcustcode");
		sqlBuffer.append("pk_billtype, markperiod,pk_org, pk_customer, pk_supplier, pk_deptid, tallydate,invoiceno, pk_tradetypeid, billno, pk_dealnum, matcustcode");
		//update end 
		return sqlBuffer.toString();
	}
	
	//add for �¼���BDA 20161111
	//swb add
	public String getResultDescSql() throws SQLException {
		StringBuffer whereBuffer = new StringBuffer();
		whereBuffer.append(" where ").append(ReportSqlUtils.getFixedWhere());
		// ������ʼ����
		String beginDate = "0000-00-00 00:00:00";
		if (queryVO.getBeginDate() != null) {
			beginDate = queryVO.getBeginDate().toString();
		}
		whereBuffer.append(" and t2.").append(anadate).append(" >= '").append(beginDate).append("' ");
		// ������������
		String endDate = "9999-12-31" + sTime;
		if (queryVO.getEndDate() != null) {
			endDate = queryVO.getEndDate().toString();
		}
		whereBuffer.append(" and t2.").append(anadate).append(" <= '").append(endDate).append("' ");
		whereBuffer.append(" and a2.dr = 0 and a1.dr = 0 ");
		
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" select a1.billno,a2.billno as billno22,a1.invoiceno from ").append(getTmpTblName()).append(" t2 inner join ar_gatheritem a1 on(t2.billno=a1.billno) ");
		sqlBuffer.append(" inner join ar_recitem a2 on(a1.pk_gatherbill = a2.top_billid) ");
		sqlBuffer.append(whereBuffer.toString());
		sqlBuffer.append(" group by a1.billno,a2.billno,a1.invoiceno ");
		sqlBuffer.append(" union all ");
		sqlBuffer.append("select a1.billno,a2.billno as billno22,a1.invoiceno from ").append(getTmpTblName()).append(" t2 inner join ar_gatheritem a1 on(t2.billno=a1.billno)");
		sqlBuffer.append(" inner join ar_recitem a2 on(a1.top_billid = a2.pk_recbill) ");
		sqlBuffer.append(whereBuffer.toString());
		sqlBuffer.append(" group by a1.billno,a2.billno,a1.invoiceno ");
		//-----Ӧ��
		sqlBuffer.append(" union all ");
		sqlBuffer.append("select a1.billno,a2.billno,a1.invoiceno from ").append(getTmpTblName()).append(" t2 inner join ap_payitem a1 on(t2.billno=a1.billno)");
		sqlBuffer.append("  inner join ap_payableitem a2 on(a1.pk_paybill = a2.top_billid) ");
		sqlBuffer.append(whereBuffer.toString());
		sqlBuffer.append(" group by a1.billno,a2.billno,a1.invoiceno ");
		sqlBuffer.append(" union all ");
		sqlBuffer.append("select a1.billno,a2.billno,a1.invoiceno from ").append(getTmpTblName()).append(" t2 inner join ap_payitem a1 on(t2.billno=a1.billno)");
		sqlBuffer.append("  inner join ap_payableitem a2 on(a1.top_billid = a2.pk_payablebill) ");
		sqlBuffer.append(whereBuffer.toString());
		sqlBuffer.append(" group by a1.billno,a2.billno,a1.invoiceno ");		
		return sqlBuffer.toString();
	}
	//add end

	public String getDistinctCurrency() throws SQLException, BusinessException {
		return null;
	}

	public String[] getDropTableSqls() throws SQLException, BusinessException {
		return new String[0];
	}

	/**
	 * ��ѯ��ǰ��ѯ����ĵ��ݼ�¼(Ӧ�յ��ݡ��տ�ݡ�Ӧ�����ݡ������)���ۿۼ�¼�����ʼ�¼�����˷����������ջ�
	 * 
	 * @return String
	 * @throws SQLException
	 * @throws BusinessException
	 */
	private String getDetailSql1() throws SQLException, BusinessException {
		//add chenth 20170717  �����µ�633����
		Object obj = this.queryVO.getUserObject().get(IArapReportConstants.IS_CANAFTERCODE);
		obj = obj == null ? "N" : obj.toString();
		//add end
		
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" insert into ").append(getTmpTblName());
		
		//add chenth 20170717  �����µ�633����
		//�����ݺ�,��Ʊ��,�������ںϼ�һ��
		sqlBuffer.append(" select ");
		sqlBuffer.append(" pk_group, pk_org, pk_customer, pk_supplier, pk_deptid, pk_psndoc,matcustcode, pk_currtype, ' ' brief, pk_billtype, pk_tradetypeid, pk_bill, billno, billno2, busidate,iscounterfee, tallydate, "
			+ anadate
			+ ", invoiceno, dealflag, pk_dealnum, ' ' project, ");
		sqlBuffer.append(" sum(temptable.debt_qua)   debt_qua, ");
		sqlBuffer.append(" sum(temptable.debt_ori)   debt_ori, ");
		sqlBuffer.append(" sum(temptable.debt_loc)   debt_loc, ");
		sqlBuffer.append(" sum(temptable.gr_debt_loc) gr_debt_loc, ");
		sqlBuffer.append(" sum(temptable.gl_debt_loc) gl_debt_loc, ");
		sqlBuffer.append(" sum(temptable.credit_qua)  credit_qua, ");
		sqlBuffer.append(" sum(temptable.credit_ori)  credit_ori, ");
		sqlBuffer.append(" sum(temptable.credit_loc)  credit_loc, ");
		sqlBuffer.append(" sum(temptable.gr_credit_loc)  gr_credit_loc, ");
		sqlBuffer.append(" sum(temptable.gl_credit_loc) gl_credit_loc ");
		sqlBuffer.append(" from ( ");
		//add end
		
		sqlBuffer.append(" select ");
		sqlBuffer.append("arap_tally.pk_group, arap_tally.pk_org, ");
		if (BillEnumCollection.ObjType.CUSTOMER.VALUE.toString().equals(queryVO.getAssoObj())) {
			sqlBuffer.append("arap_tally.customer pk_customer, null pk_supplier, ");
			sqlBuffer.append(StringUtils.isEmpty(queryVO.getDept()) ? "null" : "arap_tally.pk_deptid").append(" pk_deptid, ");
			sqlBuffer.append(StringUtils.isEmpty(queryVO.getPsndoc()) ? "null" : "arap_tally.pk_psndoc").append(" pk_psndoc, ");
		} else if (BillEnumCollection.ObjType.DEP.VALUE.toString().equals(queryVO.getAssoObj())) {
			sqlBuffer.append(StringUtils.isEmpty(queryVO.getCustomer()) ? "null" : "arap_tally.customer").append(" pk_customer, ");
			sqlBuffer.append(StringUtils.isEmpty(queryVO.getSupplier()) ? "null" : "arap_tally.supplier").append(" pk_supplier, ");
			sqlBuffer.append("arap_tally.pk_deptid pk_deptid, ");
			sqlBuffer.append(StringUtils.isEmpty(queryVO.getPsndoc()) ? "null" : "arap_tally.pk_psndoc").append(" pk_psndoc, ");
		} else if (BillEnumCollection.ObjType.PERSON.VALUE.toString().equals(queryVO.getAssoObj())) {
			sqlBuffer.append(StringUtils.isEmpty(queryVO.getCustomer()) ? "null" : "arap_tally.customer").append(" pk_customer, ");
			sqlBuffer.append(StringUtils.isEmpty(queryVO.getSupplier()) ? "null" : "arap_tally.supplier").append(" pk_supplier, ");
			sqlBuffer.append(StringUtils.isEmpty(queryVO.getDept()) ? "null" : "arap_tally.pk_deptid").append(" pk_deptid, ");
			sqlBuffer.append("arap_tally.pk_psndoc pk_psndoc, ");
		} else if (BillEnumCollection.ObjType.SUPPLIER.VALUE.toString().equals(queryVO.getAssoObj())) {
			sqlBuffer.append("null pk_customer, arap_tally.supplier pk_supplier, ");
			sqlBuffer.append(StringUtils.isEmpty(queryVO.getDept()) ? "null" : "arap_tally.pk_deptid").append(" pk_deptid, ");
			sqlBuffer.append(StringUtils.isEmpty(queryVO.getPsndoc()) ? "null" : "arap_tally.pk_psndoc").append(" pk_psndoc, ");
		} else if (BillEnumCollection.ObjType.CUSSUP.VALUE.toString().equals(queryVO.getAssoObj())){
			sqlBuffer.append("case when arap_tally.customer <> '~' then arap_tally.customer else arap_tally.supplier end as pk_customer, ");
			sqlBuffer.append("case when arap_tally.supplier <> '~' then arap_tally.supplier else arap_tally.customer end as pk_supplier, ");
			sqlBuffer.append(StringUtils.isEmpty(queryVO.getDept()) ? "null" : "arap_tally.pk_deptid").append(" pk_deptid, ");
			sqlBuffer.append(StringUtils.isEmpty(queryVO.getPsndoc()) ? "null" : "arap_tally.pk_psndoc").append(" pk_psndoc, ");
		}
		if (UFBoolean.TRUE.equals(queryVO.getUserObject().get(IArapReportConstants.IS_MATCUSTCODE))) {
			sqlBuffer.append("arap_tally_all.matcustcode, ");
		} else {
			sqlBuffer.append("null matcustcode, ");
		}
		sqlBuffer.append("arap_tally.pk_currtype, arap_tally.brief, ");
		sqlBuffer.append("case when arap_tally.pk_dealnum = '~' then arap_tally.pk_billtype else null end as pk_billtype, ");
		sqlBuffer.append("case when arap_tally.pk_dealnum = '~' then arap_tally.pk_tradetypeid else null end as pk_tradetypeid, ");
		sqlBuffer.append("arap_tally.pk_bill, ");
		sqlBuffer.append("case when arap_tally.pk_dealnum = '~' then arap_tally.billno else null end as billno, ");
		//add for �¼���BDA 20161111
		//swb add
		sqlBuffer.append("case when arap_tally.pk_dealnum = '~' then arap_tally.billno else null end as billno2, ");
		sqlBuffer.append("substring(arap_termitem.expiredate,0,11) || ' 00:00:00' as busidate, ");
		
		sqlBuffer.append("case when fi_recpaytype.issettle = 'Y' then 'N' when fi_recpaytype.issettle = 'N' then 'Y' end as iscounterfee, ");
		//add end
		
		//update chenth 20170717 ����633�µĲ���
		sqlBuffer.append(" concat(substring(arap_tally.tallydate,0,11),' 00:00:00') tallydate, ");
//		if (queryVO.getSyscode() == IArapReportConstants.SYS_FLAG_REC) {
//			sqlBuffer.append("  case when ar_recitem.busidate is not null then concat(substring(ar_recitem.busidate,0,11),' 00:00:00') else concat(substring(arap_tally.tallydate,0,11),' 00:00:00') end tallydate, ");
//		} else if (queryVO.getSyscode() == IArapReportConstants.SYS_FLAG_PAY) {
//			sqlBuffer.append("  case when ap_payableitem.busidate is not null then concat(substring(ap_payableitem.busidate,0,11),' 00:00:00') else concat(substring(arap_tally.tallydate,0,11),' 00:00:00') end tallydate, ");
//		}
		//update end
		
		sqlBuffer.append(sltAnaDate).append(" ").append(anadate).append(", "); // ��������
		sqlBuffer.append("case when arap_tally.pk_dealnum = '~' then arap_tally.invoiceno else null end as invoiceno, ");
		sqlBuffer.append("arap_tally.dealflag, arap_tally.pk_dealnum, arap_tally_all.project, ");
		
		//update chenth 20180224  BHS Ӧ�ն��˵�����Ҫ�ڳ�����Ҫ��ʾ����δ�����Ӧ�յ��ͱ��ڵ��տ�
		//update chenth 20170717 ����633�µĲ���
//		if(UFBoolean.TRUE.equals(new UFBoolean(obj.toString()))){//�����˽���ʾδ����,���Ѿ�������ԭʼ���ݹ��˵�
//			if (IArapReportConstants.SYS_FLAG_REC == queryVO.getSyscode()) {
//				sqlBuffer.append("arap_tally.quantity_de-isnull(verifydetail.quantity_de,0) debt_qua, arap_tally.money_de-isnull(verifydetail.money_de,0) debt_ori, arap_tally.local_money_de-isnull(verifydetail.local_money_de,0) debt_loc, arap_tally.grouplocal_money_de-isnull(verifydetail.group_money_de,0) gr_debt_loc, arap_tally.globallocal_money_de-isnull(verifydetail.global_money_de,0) gl_debt_loc, ");
//				sqlBuffer.append("arap_tally.quantity_cr-isnull(verifydetail.quantity_cr,0) credit_qua, arap_tally.money_cr-isnull(verifydetail.money_cr,0) credit_ori, arap_tally.local_money_cr-isnull(verifydetail.local_money_cr,0) credit_loc, arap_tally.grouplocal_money_cr-isnull(verifydetail.group_money_cr,0) gr_credit_loc, arap_tally.globallocal_money_cr-isnull(verifydetail.global_money_cr,0) gl_credit_loc ");
//			} else if (IArapReportConstants.SYS_FLAG_PAY == queryVO.getSyscode()) {
//				sqlBuffer.append("arap_tally.quantity_de-isnull(verifydetail.quantity_cr,0) debt_qua, arap_tally.money_de-isnull(verifydetail.money_cr,0) debt_ori, arap_tally.local_money_de-isnull(verifydetail.local_money_cr,0) debt_loc, arap_tally.grouplocal_money_de-isnull(verifydetail.group_money_cr,0) gr_debt_loc, arap_tally.globallocal_money_de-isnull(verifydetail.global_money_cr,0) gl_debt_loc, ");
//				sqlBuffer.append("arap_tally.quantity_cr-isnull(verifydetail.quantity_de,0) credit_qua, arap_tally.money_cr-isnull(verifydetail.money_de,0) credit_ori, arap_tally.local_money_cr-isnull(verifydetail.local_money_de,0) credit_loc, arap_tally.grouplocal_money_cr-isnull(verifydetail.group_money_de,0) gr_credit_loc, arap_tally.globallocal_money_cr-isnull(verifydetail.global_money_de,0) gl_credit_loc ");
//			}
//		}else{
//			sqlBuffer.append("arap_tally.quantity_de debt_qua, arap_tally.money_de debt_ori, arap_tally.local_money_de debt_loc, arap_tally.grouplocal_money_de gr_debt_loc, arap_tally.globallocal_money_de gl_debt_loc, ");
//			sqlBuffer.append("arap_tally.quantity_cr credit_qua, arap_tally.money_cr credit_ori, arap_tally.local_money_cr credit_loc, arap_tally.grouplocal_money_cr gr_credit_loc, arap_tally.globallocal_money_cr gl_credit_loc ");
//		}
		
		sqlBuffer.append(" arap_tally.quantity_de+isnull(verify.quantity_de,0) debt_qua, ");
		sqlBuffer.append(" arap_tally.money_de+isnull(verify.money_de,0) debt_ori, ");
		sqlBuffer.append(" arap_tally.local_money_de+isnull(verify.local_money_de,0) debt_loc, ");
		sqlBuffer.append(" arap_tally.grouplocal_money_de+isnull(verify.grouplocal_money_de,0) gr_debt_loc, ");
		sqlBuffer.append(" arap_tally.globallocal_money_de+isnull(verify.globallocal_money_de,0) gl_debt_loc, ");
		sqlBuffer.append(" arap_tally.quantity_cr+isnull(verify.quantity_cr,0) credit_qua, ");
		sqlBuffer.append(" arap_tally.money_cr+isnull(verify.money_cr,0) credit_ori, ");
		sqlBuffer.append(" arap_tally.local_money_cr+isnull(verify.local_money_cr,0) credit_loc, ");
		sqlBuffer.append(" arap_tally.grouplocal_money_cr+isnull(verify.grouplocal_money_cr,0) gr_credit_loc, ");
		sqlBuffer.append(" arap_tally.globallocal_money_cr+isnull(verify.globallocal_money_cr,0) gl_credit_loc ");
		//update end
		
		sqlBuffer.append(" from ").append(TallyVO.getDefaultTableName());
		sqlBuffer.append(getJoinTableSql());

		if (!StringUtils.isEmpty(queryVO.getAreapk())) {
			// �����������൵��
			if (queryVO.getSyscode() == IArapReportConstants.SYS_FLAG_REC) {
				sqlBuffer.append(" inner join bd_customer on arap_tally.customer = bd_customer.pk_customer ");
				sqlBuffer.append(" left outer join bd_areacl on bd_customer.pk_areacl = bd_areacl.pk_areacl ");
			} else if (queryVO.getSyscode() == IArapReportConstants.SYS_FLAG_PAY) {
				sqlBuffer.append(" inner join bd_supplier on arap_tally.supplier = bd_supplier.pk_supplier ");
				sqlBuffer.append(" left outer join bd_areacl on bd_supplier.pk_areacl = bd_areacl.pk_areacl ");
			}
		}
		
		//del chenth 20180312 for BHS ��ʾδ������
		//add chenth 20170717  �����µ�633����
		//���Ӻ�����
//		if(UFBoolean.TRUE.equals(new UFBoolean(obj.toString()))){//�����˽���ʾδ����,���Ѿ�������ԭʼ���ݹ��˵�
//			sqlBuffer.append(" left join  ");
//			sqlBuffer.append(" (select pk_item,sum(arap_verifydetail.money_de) money_de,sum(local_money_de) local_money_de,sum(quantity_de) quantity_de,  ");
//			sqlBuffer.append(" sum(money_cr) money_cr,sum(local_money_cr) local_money_cr,sum(quantity_cr) quantity_cr, ");
//			sqlBuffer.append(" sum(group_money_de) group_money_de, sum(global_money_de) global_money_de,sum(group_money_cr) group_money_cr, sum(global_money_cr) global_money_cr  ");
//			sqlBuffer.append(" from arap_verifydetail where isnull(arap_verifydetail.dr,0)=0 group by arap_verifydetail.pk_item  ");
//			sqlBuffer.append(" ) verifydetail on arap_tally.pk_item = verifydetail.pk_item  ");
//		}
		//del end
		
		//add chenth 20180224  BHS Ӧ�ն��˵�����Ҫ�ڳ�����Ҫ��ʾ����δ�����Ӧ�յ��ͱ��ڵ��տ�,�����Ѻ�����Ӧ�յ�
		// ������ʼ����
		String beginDate = "0000-00-00 00:00:00";
		if (queryVO.getBeginDate() != null) {
			beginDate = queryVO.getBeginDate().toString();
		}
		// ������������
		String endDate = "9999-12-31" + sTime;
		if (queryVO.getEndDate() != null) {
			endDate = queryVO.getEndDate().toString();
		}
		sqlBuffer.append(" left join ( ");
		sqlBuffer.append("  select arap_tally.pk_item, sum(arap_tally.quantity_de) as quantity_de, sum(arap_tally.money_de)  as money_de, sum(arap_tally.local_money_de) as local_money_de ");
		sqlBuffer.append("  , sum(arap_tally.grouplocal_money_de) as grouplocal_money_de, sum(arap_tally.globallocal_money_de) as globallocal_money_de ,sum(arap_tally.quantity_cr) as quantity_cr, sum(arap_tally.money_cr) as money_cr ");
		sqlBuffer.append("  , sum(arap_tally.local_money_cr) as local_money_cr, sum(arap_tally.grouplocal_money_cr) as grouplocal_money_cr, sum(arap_tally.globallocal_money_cr) as globallocal_money_cr ");
		sqlBuffer.append("  from arap_tally ");
		//add chenth 20180328 ÿ��ʱ���ȥ��ĳ���µ�SOAӦ�ý��һ��
		sqlBuffer.append("  left join ar_gatherbill on arap_tally.pk_corbill=ar_gatherbill.pk_gatherbill  ");
//		sqlBuffer.append("  left join ap_payablebill on arap_tally.pk_corbill = ap_payablebill.pk_payablebill  ");
		//add chenth 20180405 ֧��Ӧ�����˵�
		sqlBuffer.append("  left join ap_paybill on arap_tally.pk_corbill = ap_paybill.pk_paybill ");
//		sqlBuffer.append("  left join ar_recbill on arap_tally.pk_corbill = ar_recbill.pk_recbill ");
		//add end
		sqlBuffer.append("  where arap_tally.dealflag in (11,12,13) ");
		//update chenth 20180407 Ӧ�պ���Ӧ��������Ӧ������Ӧ�յģ������������ڣ�����������
		sqlBuffer.append("  and( ( arap_tally.billclass in ('ys','zs') and isnull(ar_gatherbill.billdate,arap_tally.tallydate) <= '").append(endDate).append("') ");
		sqlBuffer.append("     or( arap_tally.billclass in ('yf','zf') and isnull(ap_paybill.billdate,arap_tally.tallydate) <= '").append(endDate).append("') ) ");
		sqlBuffer.append(" group by arap_tally.pk_item) verify on arap_tally.pk_item = verify.pk_item ");
		//add end
				
		//add for �¼���BDA 20161111
		//swb add ������
		sqlBuffer.append(" left join arap_termitem on(arap_tally.pk_bill = arap_termitem.pk_bill and arap_tally.pk_item = arap_termitem.pk_item ) ");
		//�Ƿ���ʾ������
		//Object obj = queryVO.getUserObject().get(IArapReportConstants.IS_COUNTER_FEE);
		//String str = String.valueOf(obj);
		//UFBoolean isCounterFee = UFBoolean.valueOf(str);
		//if (isCounterFee.booleanValue()) {
			if (queryVO.getSyscode() == IArapReportConstants.SYS_FLAG_REC) {
				//add chenth 20170717  �����µ�633����
				sqlBuffer.append(" left join  ar_recitem  on arap_tally.pk_item=ar_recitem.pk_recitem and isnull(ar_recitem.dr,0)=0 ");
				//add end
				
				sqlBuffer.append("  left join ar_gatheritem  on arap_tally.billno = ar_gatheritem.billno and arap_tally.pk_item=ar_gatheritem.pk_gatheritem and isnull(ar_gatheritem.dr,0)=0 ");
				sqlBuffer.append("  left join fi_recpaytype on ar_gatheritem.pk_recpaytype = fi_recpaytype.pk_recpaytype  and isnull(fi_recpaytype.dr,0)=0 ");
			} else if (queryVO.getSyscode() == IArapReportConstants.SYS_FLAG_PAY) {
				//add chenth 20170717  �����µ�633����
				sqlBuffer.append(" left join ap_payableitem  on arap_tally.pk_item=ap_payableitem.pk_payableitem and isnull(ap_payableitem.dr,0)=0 ");
				//add end
				
				sqlBuffer.append(" left join ap_payitem  on arap_tally.billno = ap_payitem.billno and arap_tally.pk_item=ap_payitem.pk_payitem and isnull(ap_payitem.dr,0)=0 ");
				sqlBuffer.append(" left join fi_recpaytype on ap_payitem.pk_recpaytype = fi_recpaytype.pk_recpaytype and isnull(fi_recpaytype.dr,0)=0 ");
			}
		//}
		//add end
				
		sqlBuffer.append(" where ").append(ReportSqlUtils.getFixedWhere());
		sqlBuffer.append(getCompositeWhereSql());

		sqlBuffer.append(" and (");
		sqlBuffer.append(" arap_tally.pk_dealnum = '~' "); // ���ݼ�¼
		sqlBuffer.append(" or arap_tally.dealflag = '").append(BusiTypeEnum.DISCOUNT.getCode()).append("' "); // �ۿۼ�¼
		sqlBuffer.append(" or arap_tally.dealflag = '").append(BusiTypeEnum.UNION_DEBT.getCode()).append("' "); // ���ʼ�¼
		sqlBuffer.append(" or arap_tally.dealflag = '").append(BusiTypeEnum.BAD_DEBT_OCCUR.getCode()).append("' "); // ���˷���
		sqlBuffer.append(" or arap_tally.dealflag = '").append(BusiTypeEnum.BAD_DEBT_REVOKE.getCode()).append("' "); // �����ջ�
//		sqlBuffer.append(" or arap_tally.dealflag = '").append(BusiTypeEnum.AGIO.getCode()).append("' "); // �������
		sqlBuffer.append(") ");
		
		//add for �¼���BDA 20161111
		//alvin ���˺������� chenth
//		if(UFBoolean.TRUE == this.queryVO.getUserObject().get(IArapReportConstants.IS_CANAFTERCODE)){
//			sqlBuffer.append(" and arap_tally.billno not in(select ly.billno from arap_tally ly where ly.dealflag='11')"); 
//		}

		//del chenth 20180312 for BHS ��ʾδ������
		//update chenth 20170717 ����633�µĲ���
		//��������߼��������Ѿ������ĵ��ݻ���ʾ��ֻ�Ǻ�����¼��getDetailSql2�й����ˣ�
		//��������߼������ֺ����ĵ���Ҳ����˵�,������������
		//�Ƿ���Գ�����������,��ѯ���ݶ�Ӧ�ĺ�����Ȼ�������������֤
//		if(UFBoolean.TRUE.equals(new UFBoolean(obj.toString()))){//�����˽���ʾδ����,���Ѿ�������ԭʼ���ݹ��˵�
//			sqlBuffer.append(" and "); 
//			sqlBuffer.append(" ( arap_tally.pk_item not in (select ly.pk_item from arap_tally ly where ly.dealflag in ('11','12','13'))");
//			sqlBuffer.append(" or (select (arap_tally.money_de-sum(arap_verifydetail.money_de)) qty from arap_verifydetail where arap_verifydetail.pk_item = arap_tally.pk_item) > 0 ");
//			sqlBuffer.append(" ) ");
//		}
		//update end
		//add end
		//del end
		
		// ������ʼ����
		// if (!StringUtils.isEmpty(queryVO.getBeginDate())) {
		//     String beginDate = new UFDate(queryVO.getBeginDate()).toString();
		//     sqlBuffer.append(" and ").append(sltAnaDate).append(" >= '").append(beginDate).append("' ");
		// }

		// ������������
		// if (!StringUtils.isEmpty(queryVO.getEndDate())) {
		//     String endDate = queryVO.getEndDate() + sTime;
		//     sqlBuffer.append(" and ").append(sltAnaDate).append(" <= '").append(endDate).append("' ");
		// }

		
		if (!BillEnumCollection.ObjType.CUSSUP.VALUE.toString().equals(queryVO.getAssoObj())) {
			String[] billClass = null;
			if (IArapReportConstants.SYS_FLAG_REC == queryVO.getSyscode()) {
				// Ӧ��ϵͳ
				billClass = new String[] { IBillFieldGet.YS, IBillFieldGet.ZS, IBillFieldGet.SK };
			} else if (IArapReportConstants.SYS_FLAG_PAY == queryVO.getSyscode()) {
				// Ӧ��ϵͳ
				billClass = new String[] { IBillFieldGet.YF, IBillFieldGet.ZF, IBillFieldGet.FK };
			}
			sqlBuffer.append(" and ").append(SqlUtils.getInStr("arap_tally.billclass", billClass, false));
		}

		sqlBuffer.append(ReportSqlUtils.getEstimateBillTypeSql(queryVO.getQryScope(), ReportTableEnum.ARAP_TALLY)); // ��ѯ��Χ
		sqlBuffer.append(ReportSqlUtils.getAssoObjSql(queryVO.getAssoObj(), ReportTableEnum.ARAP_TALLY)); // ��������
		sqlBuffer.append(ReportSqlUtils.getBillStatusSql(queryVO.getBillState(), ReportTableEnum.ARAP_TALLY)); // ����״̬
		sqlBuffer.append(ReportSqlUtils.getCurrencySql(queryVO.getPk_currency(), ReportTableEnum.ARAP_TALLY)); // ����
		sqlBuffer.append(ReportSqlUtils.getOrgSql(queryVO.getPk_orgs(), ReportTableEnum.ARAP_TALLY)); // ҵ��Ԫ
		sqlBuffer.append(ReportSqlUtils.getGroupSql(queryVO.getPk_group(), ReportTableEnum.ARAP_TALLY)); // ����
		
		sqlBuffer.append(" and " + sltAnaDate + " <= '" + endDate + "'"); // ������������
		
		//������ʽ��ѯ  ҵ������<��ֹ����
		if(IArapReportConstants.ACC_ANA_PATTERN_POINT.equals(queryVO.getAnaPattern())){
			sqlBuffer.append(" and (").append(TallyVO.getDefaultTableName()).append(".").append(
					TallyVO.BILLDATE).append(" <= '" + queryVO.getDateline() + "') ");
			sqlBuffer.append(" and (").append(TallyVO.getDefaultTableName()).append(".").append(
					TallyVO.TALLYDATE).append(" <= '" + queryVO.getDateline() + "') ");
		} 
		
		if (BillEnumCollection.ObjType.CUSTOMER.VALUE.toString().equals(queryVO.getAssoObj())) {
			sqlBuffer.append(" and arap_tally.customer <> '").append(LOCAL_NULL).append("' ");
		} else if (BillEnumCollection.ObjType.DEP.VALUE.toString().equals(queryVO.getAssoObj())) {
			sqlBuffer.append(" and arap_tally.pk_deptid <> '").append(LOCAL_NULL).append("' ");
		} else if (BillEnumCollection.ObjType.PERSON.VALUE.toString().equals(queryVO.getAssoObj())) {
			sqlBuffer.append(" and arap_tally.pk_psndoc <> '").append(LOCAL_NULL).append("' ");
		} else if (BillEnumCollection.ObjType.SUPPLIER.VALUE.toString().equals(queryVO.getAssoObj())) {
			sqlBuffer.append(" and arap_tally.supplier <> '").append(LOCAL_NULL).append("' ");
		}

		if (BillEnumCollection.ObjType.CUSSUP.VALUE.toString().equals(queryVO.getAssoObj())) {
			if (!StringUtils.isEmpty(queryVO.getCustomer())) {
				// ����
				sqlBuffer.append(SqlUtils.getInStr(" and ( arap_tally.customer ", queryVO.getCustomer().split(","), true));
				sqlBuffer.append(SqlUtils.getInStr(" or arap_tally.supplier ", queryVO.getCustomer().split(","), true));
				sqlBuffer.append(")");
			}
			if (!StringUtils.isEmpty(queryVO.getSupplier())) {
				// ����
				sqlBuffer.append(SqlUtils.getInStr(" and ( arap_tally.customer ", queryVO.getSupplier().split(","), true));
				sqlBuffer.append(SqlUtils.getInStr(" or arap_tally.supplier ", queryVO.getSupplier().split(","), true));
				sqlBuffer.append(")");
			}

		} else {
			if (!StringUtils.isEmpty(queryVO.getCustomer())) {
				// �ͻ�
				sqlBuffer.append(SqlUtils.getInStr(" and arap_tally.customer ", queryVO.getCustomer().split(","), true));
			}
			if (!StringUtils.isEmpty(queryVO.getSupplier())) {
				// ��Ӧ��
				sqlBuffer.append(SqlUtils.getInStr(" and arap_tally.supplier ", queryVO.getSupplier().split(","), true));
			}
		}
		if (!StringUtils.isEmpty(queryVO.getAreapk())) {
			// ��������
			sqlBuffer.append(SqlUtils.getInStr(" and bd_areacl.pk_areacl ", queryVO.getAreapk().split(","), true));
		}
		if (!StringUtils.isEmpty(queryVO.getDept())) {
			// ����
			sqlBuffer.append(SqlUtils.getInStr(" and arap_tally.pk_deptid ", queryVO.getDept().split(","), true));
		}
		if (!StringUtils.isEmpty(queryVO.getPsndoc())) {
			// ҵ��Ա
			sqlBuffer.append(SqlUtils.getInStr(" and arap_tally.pk_psndoc ", queryVO.getPsndoc().split(","), true));
		}
		
		//add chenth 20170717 ����633�µĲ���
		sqlBuffer.append(" )  temptable ");
		sqlBuffer.append(" group by ");
		sqlBuffer.append("pk_group, pk_org, pk_customer, pk_supplier, pk_deptid, pk_psndoc,matcustcode, pk_currtype, pk_billtype, pk_tradetypeid, pk_bill, billno, billno2, busidate,iscounterfee, tallydate, "
				+ anadate
				+ ", invoiceno, dealflag, pk_dealnum");//, project ��Ŀ���ܷ��� add by xudw
		//add end
		
		return sqlBuffer.toString();
	}

	/**
	 * ��ѯ��ǰ��ѯ����ĵ��ݺ�����¼(��ǵ�ǰ��ѯ����ĵ��ݵĺ�����¼��ͬ���ֺ���������ֺ����������Գ�)
	 * 
	 * @return
	 * @throws SQLException
	 * @throws BusinessException 
	 */
	private String getDetailSql2() throws SQLException, BusinessException {
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" insert into ").append(getTmpTblName());

		sqlBuffer.append(" select ");
		sqlBuffer.append("arap_tally.pk_group, arap_tally.pk_org, ");
		if (BillEnumCollection.ObjType.CUSTOMER.VALUE.toString().equals(queryVO.getAssoObj())) {
			sqlBuffer.append("arap_tally.customer pk_customer, null pk_supplier, ");
			sqlBuffer.append(StringUtils.isEmpty(queryVO.getDept()) ? "null" : "arap_tally.pk_deptid").append(" pk_deptid, ");
			sqlBuffer.append(StringUtils.isEmpty(queryVO.getPsndoc()) ? "null" : "arap_tally.pk_psndoc").append(" pk_psndoc, ");
		} else if (BillEnumCollection.ObjType.DEP.VALUE.toString().equals(queryVO.getAssoObj())) {
			sqlBuffer.append(StringUtils.isEmpty(queryVO.getCustomer()) ? "null" : "arap_tally.customer").append(" pk_customer, ");
			sqlBuffer.append(StringUtils.isEmpty(queryVO.getSupplier()) ? "null" : "arap_tally.supplier").append(" pk_supplier, ");
			sqlBuffer.append("arap_tally.pk_deptid pk_deptid, ");
			sqlBuffer.append(StringUtils.isEmpty(queryVO.getPsndoc()) ? "null" : "arap_tally.pk_psndoc").append(" pk_psndoc, ");
		} else if (BillEnumCollection.ObjType.PERSON.VALUE.toString().equals(queryVO.getAssoObj())) {
			sqlBuffer.append(StringUtils.isEmpty(queryVO.getCustomer()) ? "null" : "arap_tally.customer").append(" pk_customer, ");
			sqlBuffer.append(StringUtils.isEmpty(queryVO.getSupplier()) ? "null" : "arap_tally.supplier").append(" pk_supplier, ");
			sqlBuffer.append(StringUtils.isEmpty(queryVO.getDept()) ? "null" : "arap_tally.pk_deptid").append(" pk_deptid, ");
			sqlBuffer.append("arap_tally.pk_psndoc pk_psndoc, ");
		} else if (BillEnumCollection.ObjType.SUPPLIER.VALUE.toString().equals(queryVO.getAssoObj())) {
			sqlBuffer.append("null pk_customer, arap_tally.supplier pk_supplier, ");
			sqlBuffer.append(StringUtils.isEmpty(queryVO.getDept()) ? "null" : "arap_tally.pk_deptid").append(" pk_deptid, ");
			sqlBuffer.append(StringUtils.isEmpty(queryVO.getPsndoc()) ? "null" : "arap_tally.pk_psndoc").append(" pk_psndoc, ");
		} else if (BillEnumCollection.ObjType.CUSSUP.VALUE.toString().equals(queryVO.getAssoObj())){
			sqlBuffer.append("case when arap_tally.customer <> '~' then arap_tally.customer else arap_tally.supplier end as pk_customer, ");
			sqlBuffer.append("case when arap_tally.supplier <> '~' then arap_tally.supplier else arap_tally.customer end as pk_supplier, ");
			sqlBuffer.append(StringUtils.isEmpty(queryVO.getDept()) ? "null" : "arap_tally.pk_deptid").append(" pk_deptid, ");
			sqlBuffer.append(StringUtils.isEmpty(queryVO.getPsndoc()) ? "null" : "arap_tally.pk_psndoc").append(" pk_psndoc, ");
		}
		if(UFBoolean.TRUE.equals(queryVO.getUserObject().get(IArapReportConstants.IS_MATCUSTCODE))){
			sqlBuffer.append("arap_tally_all.matcustcode, ");
		} else {
			sqlBuffer.append("null matcustcode, ");
		}
		sqlBuffer.append("arap_tally.pk_currtype, arap_tally.brief, ");
		sqlBuffer.append("case when arap_tally.pk_dealnum = '~' then arap_tally.pk_billtype else null end as pk_billtype, ");
		sqlBuffer.append("case when arap_tally.pk_dealnum = '~' then arap_tally.pk_tradetypeid else null end as pk_tradetypeid, ");
		sqlBuffer.append("case when arap_tally.pk_dealnum = '~' then arap_tally.pk_bill else null end as pk_bill, ");
		sqlBuffer.append("case when arap_tally.pk_dealnum = '~' then arap_tally.billno else null end as billno, ");
		
		//add for �¼���BDA 20161111
		//swb add
		sqlBuffer.append("case when arap_tally.pk_dealnum = '~' then arap_tally.billno else null end as billno2, ");
		sqlBuffer.append("substring(arap_termitem.expiredate,0,11) || ' 00:00:00' as busidate, ");
		
		sqlBuffer.append("case when fi_recpaytype.issettle = 'Y' then 'N' when fi_recpaytype.issettle= 'N' then 'Y' end as iscounterfee, ");
		//add end
		//add chenth 20170717 ����633�µĲ���
		//edit by xudw ɵ�ƿͻ�
//		if (queryVO.getSyscode() == IArapReportConstants.SYS_FLAG_REC) {
//			sqlBuffer.append("  case when ar_recitem.busidate is not null then CONCAT(substring(ar_recitem.busidate,0,11),' 00:00:00') else null end tallydate, ");
//		} else if (queryVO.getSyscode() == IArapReportConstants.SYS_FLAG_PAY) {
//			sqlBuffer.append("  case when ap_payableitem.busidate is not null then CONCAT(substring(ap_payableitem.busidate,0,11),' 00:00:00') else null end tallydate, ");
//		}
		//add end 
		
		sqlBuffer.append("arap_tally.tallydate tallydate, ");
		sqlBuffer.append(ReportSqlUtils.getAnaDateField(queryVO.getAnaDate())).append(" ").append(anadate).append(", "); // ��������
		sqlBuffer.append("case when arap_tally.pk_dealnum = '~' then arap_tally.invoiceno else null end as invoiceno, ");
		sqlBuffer.append("arap_tally.dealflag, arap_tally.pk_dealnum, arap_tally_all.project, ");
		sqlBuffer.append("arap_tally.quantity_de debt_qua, arap_tally.money_de debt_ori, arap_tally.local_money_de debt_loc, arap_tally.grouplocal_money_de gr_debt_loc, arap_tally.globallocal_money_de gl_debt_loc, ");
		sqlBuffer.append("arap_tally.quantity_cr credit_qua, arap_tally.money_cr credit_ori, arap_tally.local_money_cr credit_loc, arap_tally.grouplocal_money_cr gr_credit_loc, arap_tally.globallocal_money_cr gl_credit_loc ");

		sqlBuffer.append(" from ").append(TallyVO.getDefaultTableName());
		sqlBuffer.append(getJoinTableSql());

		if (!StringUtils.isEmpty(queryVO.getAreapk())) {
			// �����������൵��
			if (queryVO.getSyscode() == IArapReportConstants.SYS_FLAG_REC) {
				sqlBuffer.append(" inner join bd_customer on arap_tally.customer = bd_customer.pk_customer ");
				sqlBuffer.append(" left outer join bd_areacl on bd_customer.pk_areacl = bd_areacl.pk_areacl ");
			} else if (queryVO.getSyscode() == IArapReportConstants.SYS_FLAG_PAY) {
				sqlBuffer.append(" inner join bd_supplier on arap_tally.supplier = bd_supplier.pk_supplier ");
				sqlBuffer.append(" left outer join bd_areacl on bd_supplier.pk_areacl = bd_areacl.pk_areacl ");
			}
		}
		
		//add for �¼���BDA 20161111
		//swb add ������
//		sqlBuffer.append(" left join arap_termitem on(arap_tally.pk_bill = arap_termitem.pk_bill) ");
		//update chenth 20170717 ����633�µĲ���
		sqlBuffer.append(" left join arap_termitem on(arap_tally.pk_item = arap_termitem.pk_item) ");
		//update end 
		
		//�Ƿ���ʾ������
		//Object obj = queryVO.getUserObject().get(IArapReportConstants.IS_COUNTER_FEE);
		//String str = String.valueOf(obj);
		//UFBoolean isCounterFee = UFBoolean.valueOf(str);
		//if (isCounterFee.booleanValue()) {
			if (queryVO.getSyscode() == IArapReportConstants.SYS_FLAG_REC) {
				//add chenth 20170717  �����µ�633����
				sqlBuffer.append("  left join  ar_recitem  on arap_tally.pk_item=ar_recitem.pk_recitem and isnull(ar_recitem.dr,0)=0 ");
				//add end
				
				sqlBuffer.append("  left join ar_gatheritem  on arap_tally.billno = ar_gatheritem.billno and arap_tally.pk_item=ar_gatheritem.pk_gatheritem and isnull(ar_gatheritem.dr,0)=0 ");
				sqlBuffer.append("  left join fi_recpaytype on ar_gatheritem.pk_recpaytype = fi_recpaytype.pk_recpaytype  and isnull(fi_recpaytype.dr,0)=0 ");
			} else if (queryVO.getSyscode() == IArapReportConstants.SYS_FLAG_PAY) {
				//add chenth 20170717  �����µ�633����
				sqlBuffer.append(" left join ap_payableitem  on arap_tally.pk_item=ap_payableitem.pk_payableitem and isnull(ap_payableitem.dr,0)=0 ");
				//add end
				
				sqlBuffer.append(" left join ap_payitem  on arap_tally.billno = ap_payitem.billno and arap_tally.pk_item=ap_payitem.pk_payitem and isnull(ap_payitem.dr,0)=0 ");
				sqlBuffer.append(" left join fi_recpaytype on ap_payitem.pk_recpaytype = fi_recpaytype.pk_recpaytype and isnull(fi_recpaytype.dr,0)=0 ");
			}
		//}
		//add end
		
		sqlBuffer.append(" where ").append(ReportSqlUtils.getFixedWhere());
		sqlBuffer.append(getCompositeWhereSql());

		sqlBuffer.append(SqlUtils.getInInt(" and arap_tally.dealflag ", VERIFY_FLAGS, false)); // ���˺�����¼

		if (!BillEnumCollection.ObjType.CUSSUP.VALUE.toString().equals(queryVO.getAssoObj())){
			String[] billClass = null;
			if (IArapReportConstants.SYS_FLAG_REC == queryVO.getSyscode()) {
				// Ӧ��ϵͳ
				billClass = new String[] { IBillFieldGet.YS, IBillFieldGet.ZS, IBillFieldGet.SK };
			} else if (IArapReportConstants.SYS_FLAG_PAY == queryVO.getSyscode()) {
				// Ӧ��ϵͳ
				billClass = new String[] { IBillFieldGet.YF, IBillFieldGet.ZF, IBillFieldGet.FK };
			}  
			sqlBuffer.append(" and ").append(SqlUtils.getInStr("arap_tally.billclass", billClass, false));
		}

		sqlBuffer.append(ReportSqlUtils.getCurrencySql(queryVO.getPk_currency(), ReportTableEnum.ARAP_TALLY)); // ����
		sqlBuffer.append(ReportSqlUtils.getEstimateBillTypeSql(queryVO.getQryScope(),
				ReportTableEnum.ARAP_TALLY)); // ��ѯ��Χ
		sqlBuffer.append(ReportSqlUtils.getBillStatusSql(queryVO.getBillState(), ReportTableEnum.ARAP_TALLY)); // ����״̬
		sqlBuffer.append(ReportSqlUtils.getAssoObjSql(queryVO.getAssoObj(), ReportTableEnum.ARAP_TALLY)); // ��������
		sqlBuffer.append(ReportSqlUtils.getOrgSql(queryVO.getPk_orgs(), ReportTableEnum.ARAP_TALLY)); // ҵ��Ԫ
		sqlBuffer.append(ReportSqlUtils.getGroupSql(queryVO.getPk_group(), ReportTableEnum.ARAP_TALLY)); // ҵ��Ԫ
		sqlBuffer.append(" and " + sltAnaDate + " <= '" + endDate + "'"); // ������������
		
		//������ʽ��ѯ  ҵ������<��ֹ����
		if(IArapReportConstants.ACC_ANA_PATTERN_POINT.equals(queryVO.getAnaPattern())){
			sqlBuffer.append(" and (").append(TallyVO.getDefaultTableName()).append(".").append(
					TallyVO.BILLDATE).append(" <= '" + queryVO.getDateline() + "') ");
			sqlBuffer.append(" and (").append(TallyVO.getDefaultTableName()).append(".").append(
					TallyVO.TALLYDATE).append(" <= '" + queryVO.getDateline() + "') ");
		}
		
		if (BillEnumCollection.ObjType.CUSSUP.VALUE.toString().equals(queryVO.getAssoObj())) {
			if (!StringUtils.isEmpty(queryVO.getCustomer())) {
				// ����
				sqlBuffer.append(SqlUtils.getInStr(" and ( arap_tally.customer ", queryVO.getCustomer().split(","), true));
				sqlBuffer.append(SqlUtils.getInStr(" or arap_tally.supplier ", queryVO.getCustomer().split(","), true));
				sqlBuffer.append(")");
			}
			if (!StringUtils.isEmpty(queryVO.getSupplier())) {
				// ����
				sqlBuffer.append(SqlUtils.getInStr(" and ( arap_tally.customer ", queryVO.getSupplier().split(","), true));
				sqlBuffer.append(SqlUtils.getInStr(" or arap_tally.supplier ", queryVO.getSupplier().split(","), true));
				sqlBuffer.append(")");
			}

		} else {
			if (!StringUtils.isEmpty(queryVO.getCustomer())) {
				// �ͻ�
				sqlBuffer.append(SqlUtils.getInStr(" and arap_tally.customer ", queryVO.getCustomer().split(","), true));
			}
			if (!StringUtils.isEmpty(queryVO.getSupplier())) {
				// ��Ӧ��
				sqlBuffer.append(SqlUtils.getInStr(" and arap_tally.supplier ", queryVO.getSupplier().split(","), true));
			}
		}
		
		if (!StringUtils.isEmpty(queryVO.getAreapk())) {
			// ��������
			sqlBuffer.append(SqlUtils.getInStr(" and bd_areacl.pk_areacl ", queryVO.getAreapk().split(","), true));
		}
		if (!StringUtils.isEmpty(queryVO.getDept())) {
			// ����
			sqlBuffer.append(SqlUtils.getInStr(" and arap_tally.pk_deptid ", queryVO.getDept().split(","), true));
		}
		if (!StringUtils.isEmpty(queryVO.getPsndoc())) {
			// ҵ��Ա
			sqlBuffer.append(SqlUtils.getInStr(" and arap_tally.pk_psndoc ", queryVO.getPsndoc().split(","), true));
		}

		return sqlBuffer.toString();
	}

	@SuppressWarnings("unused")
	private String getDetailSql3() throws SQLException, BusinessException {
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" insert into ").append(getTmpTblName());

		sqlBuffer.append(" select ");
		sqlBuffer.append("cur.pk_group, cur.pk_org, ");
		if (BillEnumCollection.ObjType.CUSTOMER.VALUE.toString().equals(queryVO.getAssoObj())) {
			sqlBuffer.append("cur.customer pk_customer, null pk_supplier, ");
			sqlBuffer.append(StringUtils.isEmpty(queryVO.getDept()) ? "null" : "cur.pk_deptid").append(" pk_deptid, ");
			sqlBuffer.append(StringUtils.isEmpty(queryVO.getPsndoc()) ? "null" : "cur.pk_psndoc").append(" pk_psndoc, ");
		} else if (BillEnumCollection.ObjType.DEP.VALUE.toString().equals(queryVO.getAssoObj())) {
			sqlBuffer.append(StringUtils.isEmpty(queryVO.getCustomer()) ? "null" : "cur.customer").append(" pk_customer, ");
			sqlBuffer.append(StringUtils.isEmpty(queryVO.getSupplier()) ? "null" : "cur.supplier").append(" pk_supplier, ");
			sqlBuffer.append("cur.pk_deptid pk_deptid, ");
			sqlBuffer.append(StringUtils.isEmpty(queryVO.getPsndoc()) ? "null" : "cur.pk_psndoc").append(" pk_psndoc, ");
		} else if (BillEnumCollection.ObjType.PERSON.VALUE.toString().equals(queryVO.getAssoObj())) {
			sqlBuffer.append(StringUtils.isEmpty(queryVO.getCustomer()) ? "null" : "cur.customer").append(" pk_customer, ");
			sqlBuffer.append(StringUtils.isEmpty(queryVO.getSupplier()) ? "null" : "cur.supplier").append(" pk_supplier, ");
			sqlBuffer.append(StringUtils.isEmpty(queryVO.getDept()) ? "null" : "cur.pk_deptid").append(" pk_deptid, ");
			sqlBuffer.append("cur.pk_psndoc pk_psndoc, ");
		} else if (BillEnumCollection.ObjType.SUPPLIER.VALUE.toString().equals(queryVO.getAssoObj())) {
			sqlBuffer.append("null pk_customer, cur.supplier pk_supplier, ");
			sqlBuffer.append(StringUtils.isEmpty(queryVO.getDept()) ? "null" : "cur.pk_deptid").append(" pk_deptid, ");
			sqlBuffer.append(StringUtils.isEmpty(queryVO.getPsndoc()) ? "null" : "cur.pk_psndoc").append(" pk_psndoc, ");
		}

		sqlBuffer.append("cur.pk_currtype, cur.brief, ");
		sqlBuffer.append("case when cur.pk_dealnum = '~' then cur.pk_billtype else null end as pk_billtype, ");
		sqlBuffer.append("case when cur.pk_dealnum = '~' then cur.pk_tradetypeid else null end as pk_tradetypeid, ");
		sqlBuffer.append("cur.pk_bill, ");
		sqlBuffer.append("case when cur.pk_dealnum = '~' then cur.billno else null end as billno, ");
		sqlBuffer.append("cur.tallydate tallydate, ");
		sqlBuffer.append(ReportSqlUtils.getAnaDateField(queryVO.getAnaDate()).replace("arap_tally.", "cur.")).append(" ").append(anadate).append(", "); // ��������
		sqlBuffer.append("case when cur.pk_dealnum = '~' then cur.invoiceno else null end as invoiceno, ");
		sqlBuffer.append("cur.dealflag, cur.pk_dealnum, ");
		sqlBuffer.append("cur.quantity_de debt_qua, cur.money_de debt_ori, cur.local_money_de debt_loc, cur.grouplocal_money_de gr_debt_loc, cur.globallocal_money_de gl_debt_loc, ");
		sqlBuffer.append("cur.quantity_cr credit_qua, cur.money_cr credit_ori, cur.local_money_cr credit_loc, cur.grouplocal_money_cr gr_credit_loc, cur.globallocal_money_cr gl_credit_loc ");

		String cur = " cur";
		String cor = " cor";

		sqlBuffer.append(" from ").append(TallyVO.getDefaultTableName()).append(cur);
		sqlBuffer.append(" inner join ").append(" (select * from ").append(TallyVO.getDefaultTableName()).append(
				" where pk_dealnum = '~' and pk_group = '").append(queryVO.getPk_group()).append("' ")
				.append(SqlUtils.getInStr(" and pk_org ", queryVO.getPk_orgs())).append(") ").append(cor);
		sqlBuffer.append(" on (cur.pk_corbill = cor.pk_bill and cur.pk_coritem = cor.pk_item)");
		sqlBuffer.append(getJoinTableSql().replace("arap_tally.", "cur."));

		if (!StringUtils.isEmpty(queryVO.getAreapk())) {
			// �����������൵��
			if (queryVO.getSyscode() == IArapReportConstants.SYS_FLAG_REC) {
				sqlBuffer.append(" inner join bd_customer on cur.customer = bd_customer.pk_customer ");
				sqlBuffer.append(" left outer join bd_areacl on bd_customer.pk_areacl = bd_areacl.pk_areacl ");
			} else if (queryVO.getSyscode() == IArapReportConstants.SYS_FLAG_PAY) {
				sqlBuffer.append(" inner join bd_supplier on cur.supplier = bd_supplier.pk_supplier ");
				sqlBuffer.append(" left outer join bd_areacl on bd_supplier.pk_areacl = bd_areacl.pk_areacl ");
			}
		}

		sqlBuffer.append(" where ").append(ReportSqlUtils.getFixedWhere());
		sqlBuffer.append(getCompositeWhereSql().replace("arap_tally.", "cur."));

		sqlBuffer.append(SqlUtils.getInInt(" and " + cur + ".dealflag ", VERIFY_FLAGS, false)); // ���˺�����¼
		// sqlBuffer.append(SqlUtils.getInInt(" and " + cor + ".dealflag ", VERIFY_FLAGS, false)); // ���˺�����¼

		// ������ʼ����
		// if (!StringUtils.isEmpty(queryVO.getBeginDate())) {
		// 	  String beginDate = new UFDate(queryVO.getBeginDate()).toString();
		//	  sqlBuffer.append(" and ").append(sltAnaDate.replace("arap_tally", cur)).append(" >= '")
		//			.append(beginDate).append("' ");
		// }

		// ������������
		// if (!StringUtils.isEmpty(queryVO.getEndDate())) {
		//     String endDate = queryVO.getEndDate() + sTime;
		//     sqlBuffer.append(" and ").append(sltAnaDate.replace("arap_tally", cur)).append(" <= '")
		// 		    .append(endDate).append("' ");
		// }

		String[] billClass = null;
		if (IArapReportConstants.SYS_FLAG_REC == queryVO.getSyscode()) {
			billClass = new String[] { IBillFieldGet.YS, IBillFieldGet.ZS, IBillFieldGet.SK };
		} else if (IArapReportConstants.SYS_FLAG_PAY == queryVO.getSyscode()) {
			billClass = new String[] { IBillFieldGet.YF, IBillFieldGet.ZF, IBillFieldGet.FK };
		}
		sqlBuffer.append(SqlUtils.getInStr(" and " + cur + ".billclass", billClass, false));
		sqlBuffer.append(ReportSqlUtils.getCurrencySql(queryVO.getPk_currency(), ReportTableEnum.ARAP_TALLY).replace(ReportTableEnum.ARAP_TALLY.getCode() + ".", cur + ".")); // ����
		sqlBuffer.append(ReportSqlUtils.getEstimateBillTypeSql(queryVO.getQryScope(), ReportTableEnum.ARAP_TALLY).replace(ReportTableEnum.ARAP_TALLY.getCode() + ".", cur + ".")); // ��ѯ��Χ
		sqlBuffer.append(ReportSqlUtils.getBillStatusSql(queryVO.getBillState(), ReportTableEnum.ARAP_TALLY).replace(ReportTableEnum.ARAP_TALLY.getCode() + ".", cur + ".")); // ����״̬
		sqlBuffer.append(ReportSqlUtils.getAssoObjSql(queryVO.getAssoObj(), ReportTableEnum.ARAP_TALLY).replace(ReportTableEnum.ARAP_TALLY.getCode() + ".", cur + ".")); // ��������
		sqlBuffer.append(ReportSqlUtils.getOrgSql(queryVO.getPk_orgs(), ReportTableEnum.ARAP_TALLY).replace(ReportTableEnum.ARAP_TALLY.getCode() + ".", cur + ".")); // ҵ��Ԫ
		sqlBuffer.append(ReportSqlUtils.getGroupSql(queryVO.getPk_group(), ReportTableEnum.ARAP_TALLY).replace(ReportTableEnum.ARAP_TALLY.getCode() + ".", cur + ".")); // ҵ��Ԫ

		if (BillEnumCollection.ObjType.CUSTOMER.VALUE.toString().equals(queryVO.getAssoObj())) {
			sqlBuffer.append(" and " + cur + ".customer <> '").append(LOCAL_NULL).append("' ");
			sqlBuffer.append(" and " + cur + ".customer <> ").append(cor).append(".customer "); // �ǵ�ǰ��ѯ�������
		} else if (BillEnumCollection.ObjType.DEP.VALUE.toString().equals(queryVO.getAssoObj())) {
			sqlBuffer.append(" and " + cur + ".pk_deptid <> '").append(LOCAL_NULL).append("' ");
			sqlBuffer.append(" and " + cur + ".pk_deptid <> ").append(cor).append(".pk_deptid "); // �ǵ�ǰ��ѯ�������
		} else if (BillEnumCollection.ObjType.PERSON.VALUE.toString().equals(queryVO.getAssoObj())) {
			sqlBuffer.append(" and " + cur + ".pk_psndoc <> '").append(LOCAL_NULL).append("' ");
			sqlBuffer.append(" and " + cur + ".pk_psndoc <> ").append(cor).append(".pk_psndoc "); // �ǵ�ǰ��ѯ�������
		} else if (BillEnumCollection.ObjType.SUPPLIER.VALUE.toString().equals(queryVO.getAssoObj())) {
			sqlBuffer.append(" and " + cur + ".supplier <> '").append(LOCAL_NULL).append("' ");
			sqlBuffer.append(" and " + cur + ".supplier <> ").append(cor).append(".supplier "); // �ǵ�ǰ��ѯ�������
		}
		
		if (!StringUtils.isEmpty(queryVO.getCustomer())) {
			// �ͻ�
			sqlBuffer.append(SqlUtils.getInStr(" and " + cur + ".customer ", queryVO.getCustomer().split(","), true));
		}
		if (!StringUtils.isEmpty(queryVO.getSupplier())) {
			// ��Ӧ��
			sqlBuffer.append(SqlUtils.getInStr(" and " + cur + ".supplier ", queryVO.getSupplier().split(","), true));
		}
		if (!StringUtils.isEmpty(queryVO.getAreapk())) {
			// ��������
			sqlBuffer.append(SqlUtils.getInStr(" and bd_areacl.pk_areacl ", queryVO.getAreapk().split(","), true));
		}
		if (!StringUtils.isEmpty(queryVO.getDept())) {
			// ����
			sqlBuffer.append(SqlUtils.getInStr(" and " + cur + ".pk_deptid ", queryVO.getDept().split(","), true));
		}
		if (!StringUtils.isEmpty(queryVO.getPsndoc())) {
			// ҵ��Ա
			sqlBuffer.append(SqlUtils.getInStr(" and " + cur + ".pk_psndoc ", queryVO.getPsndoc().split(","), true));
		}

		return sqlBuffer.toString();
	}

	private String getPeriodBegin() throws SQLException {
		String groupByClause = "t1.pk_group, t1.pk_org, t1.pk_customer, t1.pk_supplier, t1.pk_deptid, t1.pk_psndoc, t1.pk_currtype, t1.matcustcode";
		
		StringBuffer sqlBuffer = new StringBuffer();

		sqlBuffer.append(" select ");
		sqlBuffer.append(groupByClause);
		sqlBuffer.append(", '").append(ArapReportResource.getPeriodBeginLbl()).append("' brief");
		
		//update for �¼���BDA 20161111
		sqlBuffer.append(", null pk_billtype, null pk_tradetypeid, null pk_bill, null billno, null billno2,null busidate,null iscounterfee, null tallydate, null invoiceno, null dealflag, null pk_dealnum, null project, ");
		//update end
		
		sqlBuffer.append("sum(t1.debt_qua) debt_qua, sum(t1.debt_ori) debt_ori, sum(t1.debt_loc) debt_loc, sum(t1.gr_debt_loc) gr_debt_loc, sum(t1.gl_debt_loc) gl_debt_loc, ");
		sqlBuffer.append("sum(t1.credit_qua) credit_qua, sum(t1.credit_ori) credit_ori, sum(t1.credit_loc) credit_loc, sum(t1.gr_credit_loc) gr_credit_loc, sum(t1.gl_credit_loc) gl_credit_loc, ");
		sqlBuffer.append("1 markperiod "); // �ڳ���markperiod = 1

		sqlBuffer.append(" from ").append(getTmpTblName()).append(" t1 ");

		sqlBuffer.append(" where ").append(ReportSqlUtils.getFixedWhere());

		// ������ʼ����
		String beginDate = "0000-00-00 00:00:00";
		if (queryVO.getBeginDate() != null) {
			beginDate = queryVO.getBeginDate().toString();
		}
		sqlBuffer.append(" and t1.").append(anadate).append(" < '").append(beginDate).append("' ");

		sqlBuffer.append(" group by ");
		sqlBuffer.append(groupByClause);

		return sqlBuffer.toString();
	}

	private String getPeriodTally() throws SQLException {
		
		//update for �¼���BDA 20161111
		String groupByClause = "t2.pk_group, t2.pk_org, t2.pk_customer, t2.pk_supplier, t2.pk_deptid, t2.pk_psndoc, t2.pk_currtype, t2.matcustcode, t2.brief, t2.pk_billtype, t2.pk_tradetypeid, t2.pk_bill, t2.billno,t2.busidate,t2.iscounterfee, t2.tallydate, t2.invoiceno, t2.dealflag, t2.pk_dealnum, t2.project";
		StringBuffer sqlBuffer = new StringBuffer();

		sqlBuffer.append(" select ");
		//update for �¼���BDA 20161111
		//sqlBuffer.append(groupByClause).append(", ");
		//swb add
		sqlBuffer.append("t2.pk_group, t2.pk_org, t2.pk_customer, t2.pk_supplier, t2.pk_deptid, t2.pk_psndoc, t2.pk_currtype, t2.matcustcode, t2.brief, t2.pk_billtype, t2.pk_tradetypeid, t2.pk_bill, t2.billno,'2' as billno2, t2.busidate,t2.iscounterfee, t2.tallydate, t2.invoiceno, t2.dealflag, t2.pk_dealnum, t2.project").append(", ");
		//update end

		sqlBuffer.append("sum(t2.debt_qua) debt_qua, sum(t2.debt_ori) debt_ori, sum(t2.debt_loc) debt_loc, sum(t2.gr_debt_loc) gr_debt_loc, sum(t2.gl_debt_loc) gl_debt_loc, ");
		sqlBuffer.append("sum(t2.credit_qua) credit_qua, sum(t2.credit_ori) credit_ori, sum(t2.credit_loc) credit_loc, sum(t2.gr_credit_loc) gr_credit_loc, sum(t2.gl_credit_loc) gl_credit_loc, ");
		sqlBuffer.append("2 markperiod "); // ���ڣ�markperiod = 2

		sqlBuffer.append(" from ").append(getTmpTblName()).append(" t2 ");

		sqlBuffer.append(" where ").append(ReportSqlUtils.getFixedWhere());

		// ������ʼ����
		String beginDate = "0000-00-00 00:00:00";
		if (queryVO.getBeginDate() != null) {
			beginDate = queryVO.getBeginDate().toString();
		}
		//update chenth 20180225  BHS Ӧ�ն��˵�����Ҫ�ڳ�����Ҫ��ʾ����δ�����Ӧ�յ��ͱ��ڵ��տ�
//		sqlBuffer.append(" and t2.").append(anadate).append(" >= '").append(beginDate).append("' ");

		// ������������
		String endDate = "9999-12-31" + sTime;
		if (queryVO.getEndDate() != null) {
			endDate = queryVO.getEndDate().toString();
		}
//		sqlBuffer.append(" and t2.").append(anadate).append(" <= '").append(endDate).append("' ");
		sqlBuffer.append(" and t2.").append(anadate).append(" <= '").append(endDate).append("' ");
		sqlBuffer.append(" and ( ");
		sqlBuffer.append("   (t2.pk_billtype = 'F0' and (t2.debt_ori > t2.credit_ori or (t2.debt_ori < 0 and t2.debt_ori < t2.credit_ori)))");
		//add chenth 20180405 ����Ӧ�����˵�
		sqlBuffer.append("    or (t2.pk_billtype = 'F1' and (t2.debt_ori < t2.credit_ori or (t2.credit_ori < 0 and t2.debt_ori > t2.credit_ori))) ");
		sqlBuffer.append(" )");
		//add end 
		//update end
		
		//add for �¼���BDA 20161111
		//swb add
		sqlBuffer.append(" and (t2.pk_billtype not in ('F2','F3') or t2.pk_billtype is null) ");
		//add end
		
		sqlBuffer.append(" group by ");
		sqlBuffer.append(groupByClause);
		
		//add for �¼���BDA 20161111
		//swb add
		sqlBuffer.append(" union all ");
		sqlBuffer.append(" select t2.pk_group, t2.pk_org, t2.pk_customer, t2.pk_supplier, t2.pk_deptid, t2.pk_psndoc, t2.pk_currtype, t2.matcustcode, t2.brief, t2.pk_billtype, t2.pk_tradetypeid, t2.pk_bill, t2.billno,'1' billno2, t2.tallydate as busidate,t2.iscounterfee, t2.tallydate,'' as invoiceno, t2.dealflag, t2.pk_dealnum, t2.project, ");
		sqlBuffer.append("sum(t2.debt_qua) debt_qua, sum(t2.debt_ori) debt_ori, sum(t2.debt_loc) debt_loc, sum(t2.gr_debt_loc) gr_debt_loc, sum(t2.gl_debt_loc) gl_debt_loc, ");
		sqlBuffer.append("sum(t2.credit_qua) credit_qua, sum(t2.credit_ori) credit_ori, sum(t2.credit_loc) credit_loc, sum(t2.gr_credit_loc) gr_credit_loc, sum(t2.gl_credit_loc) gl_credit_loc, ");
		sqlBuffer.append("2 markperiod ");
		sqlBuffer.append(" from ").append(getTmpTblName()).append(" t2 ");
		sqlBuffer.append(" where ").append(ReportSqlUtils.getFixedWhere());
		
		//update chenth 20180225  BHS Ӧ�ն��˵�����Ҫ�ڳ�����Ҫ��ʾ����δ�����Ӧ�յ��ͱ��ڵ��տ�,ֻ��ʾ���ڵ��տ
		sqlBuffer.append(" and t2.").append(anadate).append(" >= '").append(beginDate).append("' ");
		sqlBuffer.append(" and t2.").append(anadate).append(" <= '").append(endDate).append("' ");
		//add end
		
		sqlBuffer.append(" and t2.pk_billtype in ('F2','F3') ");
		sqlBuffer.append(" group by t2.pk_group, t2.pk_org, t2.pk_customer, t2.pk_supplier, t2.pk_deptid, t2.pk_psndoc, t2.pk_currtype, t2.matcustcode, t2.brief, t2.pk_billtype, t2.pk_tradetypeid, t2.pk_bill, t2.billno,t2.iscounterfee, t2.tallydate, t2.dealflag, t2.pk_dealnum, t2.project ");

		return sqlBuffer.toString();
	}

	/**
	 * ��ȡ��ʱ����<br>
	 * 
	 * @return String<br>
	 * @throws SQLException<br>
	 */
	private String getTmpTblName() throws SQLException {
		if (StringUtils.isEmpty(tmpTblName)) {
			tmpTblName = TmpTableCreator.createTmpTable("tmp_arap_statement", getTmpTblColNames(),
					getTmpTblColTypes());
		}

		return tmpTblName;
	}

	/**
	 * ��ȡ��ʱ����<br>
	 * @return String[]<br>
	 */
	private String[] getTmpTblColNames() {
		if (tmpTblColNames == null || tmpTblColNames.length == 0) {
			tmpTblColNames = fixedDetailField.split(",");
		}

		return tmpTblColNames;
	}

	/**
	 * ��ȡ��ʱ��������<br>
	 * @return Integer[]<br>
	 */
	private Integer[] getTmpTblColTypes() {
		if (tmpTblColTypes == null || tmpTblColTypes.length == 0) {
			tmpTblColTypes = new Integer[getTmpTblColNames().length];
			int i = 0;
			for (; i < tmpTblColTypes.length - 10; i++) {
				tmpTblColTypes[i] = Types.VARCHAR;
			}

			for (; i < tmpTblColTypes.length - 5 + 1; i += 5) {
				tmpTblColTypes[i] = Types.INTEGER;
				tmpTblColTypes[i + 1] = Types.DECIMAL;
				tmpTblColTypes[i + 2] = Types.DECIMAL;
				tmpTblColTypes[i + 3] = Types.DECIMAL;
				tmpTblColTypes[i + 4] = Types.DECIMAL;
			}
		}

		return tmpTblColTypes;
	}

	protected List<ComputeTotal> getAllQryObj() {
		return null;
	}

	@SuppressWarnings("unused")
	private List<Map<String, String>> getBDMaps() {
		if (bdMaps.size() > 0) {
			return bdMaps;
		}

		Map<String, String> cusBdMap = new HashMap<String, String>();
		cusBdMap.put(BD_TABLE, "bd_customer");
		cusBdMap.put(BD_PK, "pk_customer");
		cusBdMap.put(BD_CODE, "code");
		cusBdMap.put(BD_NAME, "name");
		cusBdMap.put(BUSI_PK, "pk_customer");
		Map<String, String> supBdMap = new HashMap<String, String>();
		supBdMap.put(BD_TABLE, "bd_supplier");
		supBdMap.put(BD_PK, "pk_supplier");
		supBdMap.put(BD_CODE, "code");
		supBdMap.put(BD_NAME, "name");
		supBdMap.put(BUSI_PK, "pk_supplier");
		Map<String, String> deptBdMap = new HashMap<String, String>();
		deptBdMap.put(BD_TABLE, "org_dept");
		deptBdMap.put(BD_PK, "pk_dept");
		deptBdMap.put(BD_CODE, "code");
		deptBdMap.put(BD_NAME, "name");
		deptBdMap.put(BUSI_PK, "pk_deptid");
		Map<String, String> psnBdMap = new HashMap<String, String>();
		psnBdMap.put(BD_TABLE, "bd_psndoc");
		psnBdMap.put(BD_PK, "pk_psndoc");
		psnBdMap.put(BD_CODE, "code");
		psnBdMap.put(BD_NAME, "name");
		psnBdMap.put(BUSI_PK, "pk_psndoc");
		
		if (BillEnumCollection.ObjType.CUSTOMER.VALUE.toString().equals(queryVO.getAssoObj())) {
			bdMaps.add(cusBdMap);
			if (!StringUtils.isEmpty(queryVO.getSupplier())) {
				bdMaps.add(supBdMap);
			}
			if (!StringUtils.isEmpty(queryVO.getDept())) {
				bdMaps.add(deptBdMap);
			}
			if (!StringUtils.isEmpty(queryVO.getPsndoc())) {
				bdMaps.add(psnBdMap);
			}
		} else if (BillEnumCollection.ObjType.SUPPLIER.VALUE.toString().equals(queryVO.getAssoObj())) {
			if (!StringUtils.isEmpty(queryVO.getCustomer())) {
				bdMaps.add(cusBdMap);
			}
			bdMaps.add(supBdMap);
			if (!StringUtils.isEmpty(queryVO.getDept())) {
				bdMaps.add(deptBdMap);
			}
			if (!StringUtils.isEmpty(queryVO.getPsndoc())) {
				bdMaps.add(psnBdMap);
			}
		} else if (BillEnumCollection.ObjType.DEP.VALUE.toString().equals(queryVO.getAssoObj())) {
			if (!StringUtils.isEmpty(queryVO.getCustomer())) {
				bdMaps.add(cusBdMap);
			}
			if (!StringUtils.isEmpty(queryVO.getSupplier())) {
				bdMaps.add(supBdMap);
			}
			bdMaps.add(deptBdMap);
			if (!StringUtils.isEmpty(queryVO.getPsndoc())) {
				bdMaps.add(psnBdMap);
			}
		} else if (BillEnumCollection.ObjType.PERSON.VALUE.toString().equals(queryVO.getAssoObj())) {
			if (!StringUtils.isEmpty(queryVO.getCustomer())) {
				bdMaps.add(cusBdMap);
			}
			if (!StringUtils.isEmpty(queryVO.getSupplier())) {
				bdMaps.add(supBdMap);
			}
			if (!StringUtils.isEmpty(queryVO.getDept())) {
				bdMaps.add(deptBdMap);
			}
			bdMaps.add(psnBdMap);
		} else if(BillEnumCollection.ObjType.CUSSUP.VALUE.toString().equals(queryVO.getAssoObj())){
			if (!StringUtils.isEmpty(queryVO.getCustomer())) {
				bdMaps.add(cusBdMap);
			}
			if (!StringUtils.isEmpty(queryVO.getSupplier())) {
				bdMaps.add(supBdMap);
			}
			if (!StringUtils.isEmpty(queryVO.getDept())) {
				bdMaps.add(deptBdMap);
			}
			if (!StringUtils.isEmpty(queryVO.getPsndoc())) {
				bdMaps.add(psnBdMap);
			}
		}

		return bdMaps;
	}
	
	protected String getJoinTableSql() throws BusinessException {
		if (beFirstJoin) {
			StringBuffer sqlBuffer = new StringBuffer();

			// ��������
			String joinTable = ReportTableEnum.ARAP_TALLYAGR.getCode();
			if (IArapReportConstants.ACC_ANA_DATE_EXPIREDATE.equals(queryVO.getAnaDate()) // �������ڣ�������
					|| IArapReportConstants.ACC_ANA_DATE_WITHINDATE.equals(queryVO.getAnaDate())) { // �������ڣ��ڿص�������
					// && (IArapReportConstants.ACC_ANA_PATTERN_POINT.equals(queryVO.getAnaPattern()) // ������ʽ�������(������Ӧ�ա�Ӧ����������ĵ�����������Ҫ��Э���ȡ��)
					// || IArapReportConstants.ACC_ANA_TYP_SETTLE.equals(queryVO.getAnaType()) // �������ͣ���������(�������տ��������)
					// || IArapReportConstants.ACC_ANA_TYP_DEADLINE.equals(queryVO.getAnaType())) // �������ͣ���ֹ����(�������տ��������)
				sqlBuffer.append(" inner join ").append(joinTable);
				sqlBuffer.append(" on arap_tally.pk_tally =  ").append(joinTable).append(".pk_tally ");
			}

			joinTable = ReportTableEnum.ARAP_TALLYALL.getCode();
			sqlBuffer.append(" left outer join ").append(joinTable);
			sqlBuffer.append(" on arap_tally.pk_bill = ").append(joinTable).append(".pk_bill ");
			sqlBuffer.append(" and arap_tally.pk_item = ").append(joinTable).append(".pk_item ");
			joinTableSql = sqlBuffer.toString();
			beFirstJoin = false;
		}
		
		return joinTableSql;
	}

}

