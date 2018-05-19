package nc.impl.arap.other;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import nc.bs.arap.accountage.AccountAgeAnalyzerFactory;
import nc.bs.arap.accountage.IAccountAgeAna;
import nc.bs.arap.sql.ArapBaseSqlCreator;
import nc.bs.arap.sql.RecPayStatementSQLCreator;
import nc.bs.framework.common.NCLocator;
import nc.itf.arap.other.IRecPayStatementBO;
import nc.itf.fipub.queryobjreg.IReportQueryObjRegQuery;
import nc.itf.fipub.report.IPubReportConstants;
import nc.md.data.access.NCObject;
import nc.md.persist.framework.MDPersistenceService;
import nc.pub.smart.context.SmartContext;
import nc.pub.smart.exception.SmartException;
import nc.pubitf.org.IDeptPubService;
import nc.pubitf.uapbd.ICustomerPubService;
import nc.pubitf.uapbd.IPsndocPubService;
import nc.pubitf.uapbd.ISupplierPubService;
import nc.utils.arap.ArapReportResource;
import nc.utils.fipub.FipubSqlExecuter;
import nc.vo.arap.annotation.AccountQueryBiz;
import nc.vo.arap.annotation.ArapBusinessDef;
import nc.vo.arap.comreport.ArapStatementCalculator;
import nc.vo.arap.comreport.IArapReportConstants;
import nc.vo.arap.pub.BillEnumCollection;
import nc.vo.arap.tally.AggRecStatementVO;
import nc.vo.arap.tally.RecStatementItemVO;
import nc.vo.arap.tally.RecStatementVO;
import nc.vo.arap.utils.ArapReportQryObjUtil;
import nc.vo.bd.cust.CustSupplierVO;
import nc.vo.bd.cust.CustomerVO;
import nc.vo.bd.psn.PsndocVO;
import nc.vo.bd.supplier.SupplierVO;
import nc.vo.fipub.annotation.Business;
import nc.vo.fipub.annotation.BusinessType;
import nc.vo.fipub.report.PubCommonReportMethod;
import nc.vo.fipub.report.QryObj;
import nc.vo.fipub.report.QueryObjVO;
import nc.vo.fipub.report.ReportInitializeVO;
import nc.vo.fipub.report.ReportQueryCondVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.SuperVO;
import nc.vo.pub.billtype.BilltypeVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pub.rs.MemoryResultSet;
import nc.vo.pub.rs.MemoryResultSetMetaData;
import nc.vo.trade.voutils.SafeCompute;

import org.apache.commons.lang.StringUtils;

public class RecPayStatementBOImpl extends FipubSqlExecuter implements IRecPayStatementBO {
	@Business(business = ArapBusinessDef.AccountQuery,subBusiness=AccountQueryBiz.StatementReport,description = "���˵���ѯ����/����",type = BusinessType.NORMAL)	/*-=notranslate=-*/
	public List<AggRecStatementVO> query(ReportQueryCondVO qryCondVO, SmartContext context) throws BusinessException {
		try {
			ArapBaseSqlCreator sqlCreator = new RecPayStatementSQLCreator();
			sqlCreator.setParams(qryCondVO);
			reSetDept(qryCondVO);
			String[] arrangeSqls = sqlCreator.getArrangeSqls();
			String resultSql = sqlCreator.getResultSql();
			String[] dropTableSqls = sqlCreator.getDropTableSqls();

			// ������ʱ������
			for (String sql : arrangeSqls) {
				executeUpdate(sql);
			}

			// ����ʱ��ȡ�ý��
			MemoryResultSet resultSet = (MemoryResultSet) executeQuery(resultSql, getResultProcessor());
			
			//add for �¼���BDA 20161111
			//swb add
			String resultDescSql = sqlCreator.getResultDescSql();
			MemoryResultSet resultDescSet = (MemoryResultSet) executeQuery(resultDescSql, getResultProcessor());
			ArrayList<List<Object>> retList = resultDescSet.getResultArrayList();
			Map<String,Map<String,String>> showBillsMap = new HashMap<String,Map<String,String>>();
			for (List<Object> list : retList) {
				String key = String.valueOf(list.get(0));
				if(showBillsMap.get(key) == null) {
					Map<String,String> valMap = new HashMap<String,String>();
					String billno = "";
					String invoiceno = "";
					if(list.get(1)!=null && !StringUtils.isEmpty(String.valueOf(list.get(1)))){
						billno = " "+String.valueOf(list.get(1))+",";
					}
					valMap.put("billno", billno);
					if(list.get(2)!=null && !StringUtils.isEmpty(String.valueOf(list.get(2)))){
						invoiceno = " "+String.valueOf(list.get(2))+",";
					}
					valMap.put("invoiceno", invoiceno);
					showBillsMap.put(key, valMap);
				}else{
					Map<String,String> valMap = showBillsMap.get(key);
					String billno = valMap.get("billno");
					String invoiceno = valMap.get("invoiceno");
					if(list.get(1)!=null && billno.indexOf(" "+String.valueOf(list.get(1))+",") == -1){
						billno +=" "+String.valueOf(list.get(1))+",";
						valMap.put("billno", billno);
					}
					if(list.get(2)!=null && invoiceno.indexOf(" "+String.valueOf(list.get(2))+",") == -1){
						invoiceno +=" "+String.valueOf(list.get(2))+",";
						valMap.put("invoiceno", invoiceno);
					}
					showBillsMap.put(key, valMap);
				}
			}
			//add end
			
			// ɾ����ʱ��
			for (String sql : dropTableSqls) {
				executeUpdate(sql);
			}

			String pk_qryObj = null;
			if (BillEnumCollection.ObjType.CUSTOMER.VALUE.toString().equals(qryCondVO.getAssoObj())) {
				// �ͻ�
				pk_qryObj = RecStatementItemVO.PK_CUSTOMER;
			} else if (BillEnumCollection.ObjType.DEP.VALUE.toString().equals(qryCondVO.getAssoObj())) {
				// ����
				pk_qryObj = RecStatementItemVO.PK_DEPTID;
			} else if (BillEnumCollection.ObjType.PERSON.VALUE.toString().equals(qryCondVO.getAssoObj())) {
				// ҵ��Ա
				pk_qryObj = RecStatementItemVO.PK_PSNDOC;
			} else if (BillEnumCollection.ObjType.SUPPLIER.VALUE.toString().equals(qryCondVO.getAssoObj())) {
				// ��Ӧ��
				pk_qryObj = RecStatementItemVO.PK_SUPPLIER;
			} else if (BillEnumCollection.ObjType.CUSSUP.VALUE.toString().equals(qryCondVO.getAssoObj())) {
				// ����
				if (IArapReportConstants.SYS_FLAG_REC == qryCondVO.getSyscode()) {
					pk_qryObj = RecStatementItemVO.PK_CUSTOMER;
				} else if (IArapReportConstants.SYS_FLAG_PAY == qryCondVO.getSyscode()) {
					pk_qryObj = RecStatementItemVO.PK_SUPPLIER;
				}
			}
			
			//add for �¼���BDA 20161111
			Object ageingSchema = qryCondVO.getUserObject().get(IArapReportConstants.AGING_REF);
			Map<String, List<Object>> resultMap = new HashMap<String, List<Object>>();
			if(ageingSchema != null){
				// ��ȡ���������,����ȡӦ��Ӧ����������Ϣ
				ReportQueryCondVO qryCondVOClone = (ReportQueryCondVO)qryCondVO.clone();
				ReportInitializeVO reportInitializeVO = (ReportInitializeVO)qryCondVOClone.getRepInitContext().getParentVO();
				if(reportInitializeVO == null)
					reportInitializeVO = new ReportInitializeVO();
				reportInitializeVO.setReportformat("foreign");
				IAccountAgeAna analyzer = null;
				if (IArapReportConstants.SYS_FLAG_REC == qryCondVO.getSyscode()) {
					analyzer = AccountAgeAnalyzerFactory.getAccountAgeAnalyzer(IPubReportConstants.YSZLFX_REPORT);
					reportInitializeVO.setRec_pay_direct("rec");//��
				} else if (IArapReportConstants.SYS_FLAG_PAY == qryCondVO.getSyscode()) {
					analyzer = AccountAgeAnalyzerFactory.getAccountAgeAnalyzer(IPubReportConstants.YFZLFX_REPORT);
					reportInitializeVO.setRec_pay_direct("pay");//��
				}
				qryCondVOClone.getRepInitContext().setParentVO(reportInitializeVO);
				
				//��ѯ����ֻ���ǿͻ�
				List<QueryObjVO> queryObjVOs = NCLocator.getInstance().lookup(
						IReportQueryObjRegQuery.class).getRegisteredQueryObj(
						IPubReportConstants.MODULE_ARAP);
				List<QryObj> listQryObj = new ArrayList<QryObj>();
				QryObj qryObj = null;
				for(QueryObjVO queryObjVO : queryObjVOs){
					if (IArapReportConstants.SYS_FLAG_REC == qryCondVO.getSyscode()) {
						if("�ͻ�".equals(queryObjVO.getDsp_objname())){
							qryObj = ArapReportQryObjUtil.convertQueryObjVO2QryObj(queryObjVO);
							listQryObj.add(qryObj);
							break;
						}
					} else if (IArapReportConstants.SYS_FLAG_PAY == qryCondVO.getSyscode()) {
						if("��Ӧ��".equals(queryObjVO.getDsp_objname())){
							qryObj = ArapReportQryObjUtil.convertQueryObjVO2QryObj(queryObjVO);
							listQryObj.add(qryObj);
						}
					}
				}
				
				qryCondVOClone.setQryObjs(listQryObj);
				qryCondVOClone.setAnaMode(IArapReportConstants.ACC_ANA_MODE_AGE);//����ģʽ�������� IArapReportConstants.ACC_ANA_MODE_AGE
				//del chenth 20180307 BHS�������ڲ�Ҫд�� ��֧�ְ���������
//				qryCondVOClone.setAnaDate(IArapReportConstants.ACC_ANA_DATE_EXPIREDATE);//д�����������ڰ���������
				
				//qryCondVOClone.setAnaPattern(IArapReportConstants.ACC_ANA_PATTERN_POINT);//��������ֱ��д��
				qryCondVOClone.setAccAgePlan(qryCondVOClone.getUserObject().get(IArapReportConstants.AGING_REF)+"");//���䷽��
				
				//add chenth 20180503 �������ʱҪ���������������ȥ
				String qryScope = qryCondVOClone.getQryScope();
				if(qryScope.indexOf("rec") > -1){
					qryCondVOClone.setAnaDirect(IPubReportConstants.QUERY_DIRECT_REC);
				}else{
					qryCondVOClone.setAnaDirect(IPubReportConstants.QUERY_DIRECT_PAY);
				}
				//add end 
				
				MemoryResultSet resultSetTemp = analyzer.getAccountAgeAnaResult(qryCondVOClone, context);
				ArrayList<List<Object>> retList1 = resultSetTemp.getResultArrayList();
				String key = null;
				List<?> oneLine = null;
				List<String> keyList = new ArrayList<String>();
				if (retList1 != null && retList1.size() > 0) {
					for (int i = 0; i < retList1.size(); i++) {
						oneLine = (List<?>) retList1.get(i);
						key = oneLine.get(0)+"#"+oneLine.get(1)+"#"+oneLine.get(5)+"#"+oneLine.get(9)+"#"+oneLine.get(16)+"#"+oneLine.get(18);
						if (resultMap.get(key) == null) {
							keyList.add(key);
							resultMap.put(key, new ArrayList<Object>());
						}
						resultMap.get(key).add(oneLine);
					}
				}
			}
			//add end
			
			List<AggRecStatementVO> arrangeResult = arrangeResult(resultSet, qryCondVO, pk_qryObj);
			for(int i = 0 ; i < arrangeResult.size(); i ++){
				AggRecStatementVO  VO = arrangeResult.get(i);
				RecStatementVO headVO = (RecStatementVO) VO.getParentVO();
				headVO.setPk_recstatement("h" + i);
				
				//add for �¼���BDA 20161111
				//��"����ʾδ����"���õ���ͷ������ӡģ��ʹ�� add by xudw  chenth
				Object objTemp = qryCondVO.getUserObject().get(IArapReportConstants.IS_CANAFTERCODE);
				String strTemp = String.valueOf(objTemp);
				UFBoolean iscancelaftercode = UFBoolean.valueOf(strTemp);
				headVO.setIscancelaftercode(iscancelaftercode);
				
				//��"��������"���õ���ͷ������ӡģ��ʹ�� add by xudw
				headVO.setDeadline(qryCondVO.getDateline().toStdString());
				
				//��������Ϣ���õ���ͷ
				if(resultMap != null && !resultMap.isEmpty()){
					String pk_group = headVO.getPk_group();
					String pk_org = headVO.getPk_org();
					String pk_qryobj = headVO.getPk_qryobj();
					String pk_currtype = headVO.getPk_currtype();
					for(int k=0;k<8;k++){
						String keyTemp = pk_group+"#"+pk_org+"#"+pk_qryobj+"#"+pk_currtype+"#"+k+"#0";
						List<Object> oneLineTemp = resultMap.get(keyTemp);
						if(oneLineTemp != null && oneLineTemp.size() > 0){
							List ListObj = (List)oneLineTemp.get(0);
							String str = String.valueOf(ListObj.get(12));
							//update chenth 20180307 BHS �������ݺ͡��ͻ�Ӧ������������Բ��ϣ�����һ���£���k��0��ʼ��Ϊ��1��ʼ��
							if(k==1){
								headVO.setPeriod1(new UFDouble(str,2));
							}else if(k==2){
								headVO.setPeriod2(new UFDouble(str,2));
							}else if(k==3){
								headVO.setPeriod3(new UFDouble(str,2));
							}else if(k==4){
								headVO.setPeriod4(new UFDouble(str,2));
							}else if(k==5){
								headVO.setPeriod5(new UFDouble(str,2));
							}else if(k==6){
								headVO.setPeriod6(new UFDouble(str,2));
							}else if(k==7){
								headVO.setPeriod7(new UFDouble(str,2));
							}else if(k==8){
								headVO.setPeriod8(new UFDouble(str,2));
							}
							//update chenth end
						}
					}
				}
				//add end
				
				boolean isReal = false;
				List<RecStatementItemVO> lstChildren = new ArrayList<RecStatementItemVO>();
				
				RecStatementItemVO[] childrenVO = (RecStatementItemVO[])VO.getChildrenVO();
				for(int j = 0 ; j < childrenVO.length; j++){
					childrenVO[j].setPk_recstatement("h" + i);
					childrenVO[j].setPk_recstatementitem("b" + i + j);
					//add for �¼���BDA 20161111
					//swb add
					if(showBillsMap.get(childrenVO[j].getBillno())!=null
							//add chenth 20180309 �տ����ʾ��Ʊ��
							&& !(childrenVO[j].getPk_billtype().equals("F2")
							||childrenVO[j].getPk_billtype().equals("F3"))
							//add end
					){
						Map<String,String> valMap = showBillsMap.get(childrenVO[j].getBillno());
						childrenVO[j].setInvoiceno(valMap.get("invoiceno").replaceAll(" ",""));
						childrenVO[j].setBillno2(valMap.get("billno").replaceAll(" ",""));
					}else{
						childrenVO[j].setBillno2("");
					}
					//add end
					
					//add chenth 20180311  for BHS �Խ��������
					//1������Remarks�У�2��Credit Note��� ����[Credit] �У�3���տ������[Debit]��
					if(childrenVO[j].getPk_billtype().equals("F2")
							||childrenVO[j].getPk_billtype().equals("F3")){
						if(false == isReal){
							//����һ�п���
							RecStatementItemVO remarkVO = new RecStatementItemVO(); 
							lstChildren.add(remarkVO);
							remarkVO.setPk_recstatement("h" + i);
							remarkVO.setPk_recstatementitem("b" + i + j);
							remarkVO = new RecStatementItemVO(); // Remark��
							remarkVO.setPk_recstatement("h" + i);
							remarkVO.setPk_recstatementitem("b" + i + (j+1));
							remarkVO.setTallydate("REMARKS"); // 
							lstChildren.add(remarkVO);
							isReal = true;
						}
						if(childrenVO[j].getPk_billtype().equals("F2")){
							//�տ������[Debit]��
							childrenVO[j].setPk_recstatementitem("b" + i + (j+2));
							childrenVO[j].setDebt_qua(childrenVO[j].getCredit_qua());
							childrenVO[j].setDebt_ori(childrenVO[j].getCredit_ori());
							childrenVO[j].setDebt_loc(childrenVO[j].getCredit_loc());
							childrenVO[j].setGr_debt_loc(childrenVO[j].getGr_credit_loc());
							childrenVO[j].setGl_debt_loc(childrenVO[j].getGl_credit_loc());
							childrenVO[j].setCredit_qua(null);
							childrenVO[j].setCredit_ori(null);
							childrenVO[j].setCredit_loc(null);
							childrenVO[j].setGr_credit_loc(null);
							childrenVO[j].setGl_credit_loc(null);
						}
					}else{
						//Ӧ�յ���Credit Note��� ����[Credit] ��
						if(childrenVO[j].getPk_billtype().equals("F0")
								&& childrenVO[j].getDebt_ori() != null
								&& childrenVO[j].getDebt_ori().compareTo(UFDouble.ZERO_DBL)<0){
							childrenVO[j].setCredit_qua(childrenVO[j].getDebt_qua());
							childrenVO[j].setCredit_ori(childrenVO[j].getDebt_ori().abs());
							childrenVO[j].setCredit_loc(childrenVO[j].getDebt_loc().abs());
							childrenVO[j].setGr_credit_loc(childrenVO[j].getGr_debt_loc().abs());
							childrenVO[j].setGl_credit_loc(childrenVO[j].getGl_debt_loc().abs());
							
							childrenVO[j].setDebt_qua(null);
							childrenVO[j].setDebt_ori(null);
							childrenVO[j].setDebt_loc(null);
							childrenVO[j].setGr_debt_loc(null);
							childrenVO[j].setGl_debt_loc(null);
						}
						//Ӧ������Credit Note��� ����[Debt] ��
						else if(childrenVO[j].getPk_billtype().equals("F1")
								&& childrenVO[j].getCredit_ori() != null
								&& childrenVO[j].getCredit_ori().compareTo(UFDouble.ZERO_DBL)<0){
							childrenVO[j].setDebt_qua(childrenVO[j].getCredit_qua());
							childrenVO[j].setDebt_ori(childrenVO[j].getCredit_ori().abs());
							childrenVO[j].setDebt_loc(childrenVO[j].getCredit_loc().abs());
							childrenVO[j].setGr_debt_loc(childrenVO[j].getGr_credit_loc().abs());
							childrenVO[j].setGl_debt_loc(childrenVO[j].getGl_credit_loc().abs());
							
							childrenVO[j].setCredit_qua(null);
							childrenVO[j].setCredit_ori(null);
							childrenVO[j].setCredit_loc(null);
							childrenVO[j].setGr_credit_loc(null);
							childrenVO[j].setGl_credit_loc(null);
						}
						
					}
					lstChildren.add(childrenVO[j]);
				}
				
				VO.setChildrenVO(lstChildren.toArray(new CircularlyAccessibleValueObject[0]));
				//add end
			}
			return arrangeResult;
		} catch (SQLException e) {
			SmartException exp = handleExecuteException(e);
			throw new BusinessException(exp.getMessage(), exp);
		}
	}

	@SuppressWarnings("unchecked")
	private List<AggRecStatementVO> arrangeResult(MemoryResultSet mrs, ReportQueryCondVO qryCondVO,
			String pk_qryObj) throws SQLException, BusinessException {

		ArrayList<List<Object>> retList = mrs.getResultArrayList();

		if (retList == null || retList.size() == 0) {
			return new ArrayList<AggRecStatementVO>();
		}

		if (IPubReportConstants.GROUP_LOCAL_CURRENCY.equals(qryCondVO.getLocalCurrencyType())
				|| IPubReportConstants.GLOBLE_LOCAL_CURRENCY.equals(qryCondVO.getLocalCurrencyType())) {
			String[] targetFields = new String[] { "debt_loc", "credit_loc", "bal_loc" };
			String[] formulas = null;
			if (IPubReportConstants.GROUP_LOCAL_CURRENCY.equals(qryCondVO.getLocalCurrencyType())) {
				// ���ű���
				formulas = new String[] { "debt_loc->gr_debt_loc", "credit_loc->gr_credit_loc", "bal_loc->gr_bal_loc" };
			} else if (IPubReportConstants.GLOBLE_LOCAL_CURRENCY.equals(qryCondVO.getLocalCurrencyType())) {
				// ȫ�ֱ���
				formulas = new String[] { "debt_loc->gl_debt_loc", "credit_loc->gl_credit_loc", "bal_loc->gl_bal_loc" };
			}
			mrs.setColumnByFormulate_type(targetFields, formulas);
		}

		String multiLangIndex = PubCommonReportMethod.getMultiLangIndex();
		String[] fields = new String[] { RecStatementItemVO.BILLTYPE };
		BilltypeVO billtypeVO = new BilltypeVO();
		String[] formulas = new String[] { RecStatementItemVO.BILLTYPE + "->iif(getColValue(" + billtypeVO.getTableName()
				+ ", billtypename" + multiLangIndex + ", pk_billtypecode, " + RecStatementItemVO.PK_BILLTYPE
				+ ")==null, " + "getColValue(" + billtypeVO.getTableName()
				+ ", billtypename, pk_billtypecode, " + RecStatementItemVO.PK_BILLTYPE + "), " + "getColValue("
				+ billtypeVO.getTableName() + ", billtypename" + multiLangIndex + ", pk_billtypecode, "
				+ RecStatementItemVO.PK_BILLTYPE + "))" };
		mrs.setColumnByFormulate_type(fields, formulas);

		MemoryResultSetMetaData metaData = mrs.getMetaData0();

		String[] dimenName = { 
				RecStatementItemVO.PK_GROUP, RecStatementItemVO.PK_ORG,
				RecStatementItemVO.PK_CUSTOMER, RecStatementItemVO.PK_SUPPLIER,
				RecStatementItemVO.PK_DEPTID, RecStatementItemVO.PK_PSNDOC,
				RecStatementItemVO.PK_CURRTYPE}; // ά���ֶ�

		String[] otherName = { 
				RecStatementItemVO.PK_GROUP, RecStatementItemVO.PK_ORG,
				RecStatementItemVO.PK_CUSTOMER, RecStatementItemVO.PK_SUPPLIER,
				RecStatementItemVO.PK_DEPTID,RecStatementItemVO.PK_PSNDOC, 
				RecStatementItemVO.PK_CURRTYPE, RecStatementItemVO.MATCUSTCODE,
				RecStatementItemVO.BRIEF,RecStatementItemVO.BILLTYPE, 
				RecStatementItemVO.PK_TRADETYPEID, RecStatementItemVO.PK_BILL,
				//update for �¼���BDA 20161111
				RecStatementItemVO.BILLNO,RecStatementItemVO.BILLNO2,RecStatementItemVO.BUSIDATE,RecStatementItemVO.ISCOUNTERFEE,RecStatementItemVO.TALLYDATE, 
				RecStatementItemVO.INVOICENO, RecStatementItemVO.DEALFLAG,
				RecStatementItemVO.PK_DEALNUM, 
				RecStatementItemVO.MARKPERIOD, RecStatementItemVO.DEBT_QUA,
				RecStatementItemVO.DEBT_ORI, RecStatementItemVO.DEBT_LOC,
				RecStatementItemVO.GR_DEBT_LOC, RecStatementItemVO.GL_DEBT_LOC,
				RecStatementItemVO.CREDIT_QUA, RecStatementItemVO.CREDIT_ORI,
				RecStatementItemVO.CREDIT_LOC,RecStatementItemVO.GR_CREDIT_LOC, 
				RecStatementItemVO.GL_CREDIT_LOC, RecStatementItemVO.PK_BILLTYPE, 
				RecStatementItemVO.PROJECT}; // ȫ�ֶ�
		int[] dimenIndex = new int[dimenName.length];
		int[] otherIndex = new int[otherName.length];
		for (int i = 0; i < dimenName.length; i++) {
			dimenIndex[i] = metaData.getNameIndex(dimenName[i]);
		}

		for (int i = 0; i < otherName.length; i++) {
			otherIndex[i] = metaData.getNameIndex(otherName[i]);
		}

		String key = null;
		List<?> oneLine = null;
		List<String> keyList = new ArrayList<String>();
		Map<String, List<Object>> resultMap = new HashMap<String, List<Object>>();

		// ����ѯ�������
		for (int i = 0; i < retList.size(); i++) {
			oneLine = (List<?>) retList.get(i);
			key = getKey(oneLine, dimenIndex);
			if (resultMap.get(key) == null) {
				keyList.add(key);
				resultMap.put(key, new ArrayList<Object>());
			}
			resultMap.get(key).add(oneLine);
		}

		List<String> qryObjKeyList = new ArrayList<String>(); // ������ѯ����ѯ�����ֵ��
		Map<String, AggRecStatementVO> resultVOMap = new HashMap<String, AggRecStatementVO>();

		Object value = null;
		List<Object> oneObj = null;
		AggRecStatementVO aggVO = null;
		RecStatementVO headVO = null;
		RecStatementItemVO bodyVO = null;
		List<RecStatementItemVO> bodyVOs = null;
		
		//add for �¼���BDA 20161111
		//��ĩ���Ϊ���ұ����޷����Ƿ���ʾ add by xudw
		Object obj = qryCondVO.getUserObject().get(IArapReportConstants.IS_FINAL_BALANCE);
		String str = String.valueOf(obj);
		UFBoolean isFinalBalance = UFBoolean.valueOf(str);
		
		//�������ո����Ƿ���ʾ add by xudw
		obj = qryCondVO.getUserObject().get(IArapReportConstants.IS_COUNTER_FEE);
		str = String.valueOf(obj);
		UFBoolean isCounterFee = UFBoolean.valueOf(str);
		//add end
		
		// ��ʼ�������ۺ�VO
		int direct = qryCondVO.getSyscode() == IArapReportConstants.SYS_FLAG_REC ? 1 : -1;
		for (int i = 0; i < keyList.size(); i++) {
			oneObj = resultMap.get(keyList.get(i));
			aggVO = new AggRecStatementVO();

			bodyVOs = new ArrayList<RecStatementItemVO>();
			for (Object bodyLine : oneObj) {
				bodyVO = new RecStatementItemVO();
				// ѭ�����ø�������ֵ
				for (int j = 0; j < otherName.length; j++) {
					value = ((List<?>) bodyLine).get(otherIndex[j]);
					if (value == null || (value instanceof String && StringUtils.isEmpty(value.toString()))) {
						bodyVO.setAttributeValue(otherName[j], null);
					} else if (RecStatementItemVO.TALLYDATE.equalsIgnoreCase(otherName[j])) {
						//update for �¼���BDA 20161111
						// �����ʱ������
						String a = new UFDateTime((String) value).getDate().toStdString((TimeZone) qryCondVO.getUserObject().get("statementzone"));
						bodyVO.setAttributeValue(otherName[j], a);
					} else if (RecStatementItemVO.BUSIDATE.equalsIgnoreCase(otherName[j])) {
						// �����ʱ������
						String a = new UFDateTime((String) value).getDate().toStdString((TimeZone) qryCondVO.getUserObject().get("statementzone"));
						bodyVO.setAttributeValue(otherName[j], a);
						//update for �¼���BDA 20161111
					} else {
						bodyVO.setAttributeValue(otherName[j], value);
					}
				}

				if (!(UFDouble.ZERO_DBL.equals(bodyVO.getDebt_qua())
						&& UFDouble.ZERO_DBL.equals(bodyVO.getCredit_qua())
						&& UFDouble.ZERO_DBL.equals(bodyVO.getDebt_ori())
						&& UFDouble.ZERO_DBL.equals(bodyVO.getCredit_ori())
						&& UFDouble.ZERO_DBL.equals(bodyVO.getDebt_loc())
						&& UFDouble.ZERO_DBL.equals(bodyVO.getCredit_loc()))) {
					bodyVOs.add(bodyVO);
				}
			}

			if (bodyVOs.size() == 0) {
				continue;
			}

			RecStatementItemVO totalVO = new RecStatementItemVO(); // �ϼ���
			totalVO.setBrief(ArapReportResource.getAllTotalLbl()); // �ܼ�
			totalVO.setDebt_qua(UFDouble.ZERO_DBL);
			totalVO.setDebt_ori(UFDouble.ZERO_DBL);
			totalVO.setDebt_loc(UFDouble.ZERO_DBL);
			totalVO.setGr_debt_loc(UFDouble.ZERO_DBL);
			totalVO.setGl_debt_loc(UFDouble.ZERO_DBL);
			totalVO.setCredit_qua(UFDouble.ZERO_DBL);
			totalVO.setCredit_ori(UFDouble.ZERO_DBL);
			totalVO.setCredit_loc(UFDouble.ZERO_DBL);
			totalVO.setGr_credit_loc(UFDouble.ZERO_DBL);
			totalVO.setGl_credit_loc(UFDouble.ZERO_DBL);

			int k = 0;
			RecStatementItemVO[] arrBodyVO = bodyVOs.toArray(new RecStatementItemVO[0]);
			bodyVOs = new ArrayList<RecStatementItemVO>();
			// see nc.bs.arap.sql.RecPayStatementSQLCreator
			// �ڳ���markperiod = 1�����ڣ�markperiod = 2
			//del chenth 20180225 BHS Ӧ�ն��˵�����Ҫ�ڳ�����Ҫ��ʾ����δ�����Ӧ�յ��ͱ��ڵ��տ�
//			if (!(ArapReportResource.getPeriodBeginLbl().equals(arrBodyVO[0].getBrief()) || arrBodyVO[0]
//					.getMarkperiod() == 1)) {
//				// �������ڳ�����ʾ����һ���ڳ���¼
//				bodyVO = new RecStatementItemVO();
//				bodyVO.setPk_group(arrBodyVO[0].getPk_group());
//				bodyVO.setPk_org(arrBodyVO[0].getPk_org());
//				bodyVO.setPk_customer(arrBodyVO[0].getPk_customer());
//				bodyVO.setPk_deptid(arrBodyVO[0].getPk_deptid());
//				bodyVO.setPk_psndoc(arrBodyVO[0].getPk_psndoc());
//				bodyVO.setPk_supplier(arrBodyVO[0].getPk_supplier());
//				bodyVO.setPk_currtype(arrBodyVO[0].getPk_currtype());
//				bodyVO.setBrief(ArapReportResource.getPeriodBeginLbl());
//				bodyVO.setBal_qua(UFDouble.ZERO_DBL);
//				bodyVO.setBal_ori(UFDouble.ZERO_DBL);
//				bodyVO.setBal_loc(UFDouble.ZERO_DBL);
//				bodyVO.setGr_bal_loc(UFDouble.ZERO_DBL);
//				bodyVO.setGl_bal_loc(UFDouble.ZERO_DBL);
//
//				bodyVOs.add(bodyVO);
//			} else {
//				// �Ѿ������ڳ���¼
//				k++;
//				bodyVO = arrBodyVO[0];
//
//				// ������ĩ���������������
//				bodyVO.setBal_qua((bodyVO.getDebt_qua().sub(bodyVO.getCredit_qua())).multiply(direct));
//				bodyVO.setDebt_qua(UFDouble.ZERO_DBL);
//				bodyVO.setCredit_qua(UFDouble.ZERO_DBL);
//
//				bodyVO.setBal_ori(bodyVO.getDebt_ori().sub(bodyVO.getCredit_ori()).multiply(direct));
//				bodyVO.setDebt_ori(UFDouble.ZERO_DBL);
//				bodyVO.setCredit_ori(UFDouble.ZERO_DBL);
//
//				bodyVO.setBal_loc(bodyVO.getDebt_loc().sub(bodyVO.getCredit_loc()).multiply(direct));
//				bodyVO.setDebt_loc(UFDouble.ZERO_DBL);
//				bodyVO.setCredit_loc(UFDouble.ZERO_DBL);
//
//				bodyVO.setGr_bal_loc(bodyVO.getGr_debt_loc().sub(bodyVO.getGr_credit_loc()).multiply(direct));
//				bodyVO.setGr_debt_loc(UFDouble.ZERO_DBL);
//				bodyVO.setGr_credit_loc(UFDouble.ZERO_DBL);
//
//				bodyVO.setGl_bal_loc(bodyVO.getGl_debt_loc().sub(bodyVO.getGl_credit_loc()).multiply(direct));
//				bodyVO.setGl_debt_loc(UFDouble.ZERO_DBL);
//				bodyVO.setGl_credit_loc(UFDouble.ZERO_DBL);
//
//				bodyVOs.add(bodyVO);
//			}
			//del end

			for (; k < arrBodyVO.length; k++) {
				//update chenth 20180225 BHS Ӧ�ն��˵�����Ҫ�ڳ�����Ҫ��ʾ����δ�����Ӧ�յ��ͱ��ڵ��տ�,�����ʾΪ��ǰӦ�յ���δ��������
				// ����ÿ����ĩ���
//				arrBodyVO[k].setBal_qua(bodyVO.getBal_qua().add(arrBodyVO[k].getDebt_qua().sub(arrBodyVO[k].getCredit_qua()).multiply(direct)));
//				arrBodyVO[k].setBal_ori(bodyVO.getBal_ori().add(arrBodyVO[k].getDebt_ori().sub(arrBodyVO[k].getCredit_ori()).multiply(direct)));
//				arrBodyVO[k].setBal_loc(bodyVO.getBal_loc().add(arrBodyVO[k].getDebt_loc().sub(arrBodyVO[k].getCredit_loc()).multiply(direct)));
//				arrBodyVO[k].setGr_bal_loc(bodyVO.getGr_bal_loc().add(arrBodyVO[k].getGr_debt_loc().sub(arrBodyVO[k].getGr_credit_loc()).multiply(direct)));
//				arrBodyVO[k].setGl_bal_loc(bodyVO.getGl_bal_loc().add(arrBodyVO[k].getGl_debt_loc().sub(arrBodyVO[k].getGl_credit_loc()).multiply(direct)));
				
				//�տ��������Ӧ�յ������Ϊ��ǰӦ�յ���δ��������
				if(arrBodyVO[k].getPk_billtype().equals("F2")
						||arrBodyVO[k].getPk_billtype().equals("F3")){
					arrBodyVO[k].setBal_qua(null);
					arrBodyVO[k].setBal_ori(null);
					arrBodyVO[k].setBal_loc(null);
					arrBodyVO[k].setGr_bal_loc(null);
					arrBodyVO[k].setGl_bal_loc(null);
				}else{
					arrBodyVO[k].setBal_qua(arrBodyVO[k].getDebt_qua().sub(arrBodyVO[k].getCredit_qua()).multiply(direct));
					arrBodyVO[k].setBal_ori(arrBodyVO[k].getDebt_ori().sub(arrBodyVO[k].getCredit_ori()).multiply(direct));
					arrBodyVO[k].setBal_loc(arrBodyVO[k].getDebt_loc().sub(arrBodyVO[k].getCredit_loc()).multiply(direct));
					arrBodyVO[k].setGr_bal_loc(arrBodyVO[k].getGr_debt_loc().sub(arrBodyVO[k].getGr_credit_loc()).multiply(direct));
					arrBodyVO[k].setGl_bal_loc(arrBodyVO[k].getGl_debt_loc().sub(arrBodyVO[k].getGl_credit_loc()).multiply(direct));
				}
				//update end 
				//add chenth 20180309 Ϊ���������ʾΪ��
				if(arrBodyVO[k].getCredit_qua().equals(UFDouble.ZERO_DBL)){
					arrBodyVO[k].setCredit_qua(null);
				}
				if(arrBodyVO[k].getCredit_ori().equals(UFDouble.ZERO_DBL)){
					arrBodyVO[k].setCredit_ori(null);
				}
				if(arrBodyVO[k].getCredit_loc().equals(UFDouble.ZERO_DBL)){
					arrBodyVO[k].setCredit_loc(null);
				}
				if(arrBodyVO[k].getDebt_qua().equals(UFDouble.ZERO_DBL)){
					arrBodyVO[k].setDebt_qua(null);
				}
				if(arrBodyVO[k].getDebt_ori().equals(UFDouble.ZERO_DBL)){
					arrBodyVO[k].setDebt_ori(null);
				}
				if(arrBodyVO[k].getDebt_loc().equals(UFDouble.ZERO_DBL)){
					arrBodyVO[k].setDebt_loc(null);
				}
				//add end
				
				bodyVO = arrBodyVO[k]; // ��¼��һ�м�¼
				
				//update for �¼���BDA 20161111
				//add by xudw
				if(isCounterFee.equals(UFBoolean.FALSE)){//�������ո����ʾ
					if(bodyVO.getIscounterfee() == null || bodyVO.getIscounterfee().equals(UFBoolean.FALSE)){//�����������ո���
						bodyVOs.add(bodyVO);
					}
				}else{//�������ո�����ʾ
					bodyVOs.add(bodyVO);
				}

				//update for �¼���BDA 20161111
				// ����ϼ� add by xudw
				if(bodyVO.getIscounterfee() == null || bodyVO.getIscounterfee().equals(UFBoolean.FALSE)){//�����������ո���
					totalVO.setDebt_qua(SafeCompute.add(totalVO.getDebt_qua(), bodyVO.getDebt_qua()));
					totalVO.setDebt_ori(SafeCompute.add(totalVO.getDebt_ori(), bodyVO.getDebt_ori()));
					totalVO.setDebt_loc(SafeCompute.add(totalVO.getDebt_loc(), bodyVO.getDebt_loc()));
					totalVO.setGr_debt_loc(SafeCompute.add(totalVO.getGr_debt_loc(), bodyVO.getGr_debt_loc()));
					totalVO.setGl_debt_loc(SafeCompute.add(totalVO.getGl_debt_loc(), bodyVO.getGl_debt_loc()));
					
					//update chenth 20180225 BHS Ӧ�ն��˵�����Ҫ�ڳ�����Ҫ��ʾ����δ�����Ӧ�յ��ͱ��ڵ��տ�,�����ʾΪ��ǰӦ�յ���δ��������
					if(bodyVO.getPk_billtype().equals("F2")
							|| bodyVO.getPk_billtype().equals("F3")){
						totalVO.setCredit_qua(SafeCompute.add(totalVO.getCredit_qua(), bodyVO.getCredit_qua()));
						totalVO.setCredit_ori(SafeCompute.add(totalVO.getCredit_ori(), bodyVO.getCredit_ori()));
						totalVO.setCredit_loc(SafeCompute.add(totalVO.getCredit_loc(), bodyVO.getCredit_loc()));
						totalVO.setGr_credit_loc(SafeCompute.add(totalVO.getGr_credit_loc(), bodyVO.getGr_credit_loc()));
						totalVO.setGl_credit_loc(SafeCompute.add(totalVO.getGl_credit_loc(), bodyVO.getGl_credit_loc()));
					}else{//���ΪӦ�յ�������
						totalVO.setBal_qua(SafeCompute.add(totalVO.getBal_qua(),bodyVO.getBal_qua()));
						totalVO.setBal_ori(SafeCompute.add(totalVO.getBal_ori(),bodyVO.getBal_ori()));
						totalVO.setBal_loc(SafeCompute.add(totalVO.getBal_loc(),bodyVO.getBal_loc()));
						totalVO.setGr_bal_loc(SafeCompute.add(totalVO.getGr_bal_loc(),bodyVO.getGr_bal_loc()));
						totalVO.setGl_bal_loc(SafeCompute.add(totalVO.getGl_bal_loc(),bodyVO.getGl_bal_loc()));
					}
					//update end
				}
			}

			if (bodyVOs.size() == 1) {
				// ֻ��һ���ڳ�(���ݿ��ѯ������)
				RecStatementItemVO itemVO = bodyVOs.get(0);
				if (UFDouble.ZERO_DBL.equals(itemVO.getBal_loc()) && UFDouble.ZERO_DBL.equals(itemVO.getBal_ori())) {
					continue;
				}
			}

			// ���ñ�ͷ��������
			headVO = new RecStatementVO();
			headVO.setPk_group(((List<?>) oneObj.get(0)).get(metaData.getNameIndex(RecStatementItemVO.PK_GROUP)).toString());
			headVO.setPk_org(((List<?>) oneObj.get(0)).get(metaData.getNameIndex(RecStatementItemVO.PK_ORG)).toString());
			headVO.setPk_qryobj(((List<?>) oneObj.get(0)).get(metaData.getNameIndex(pk_qryObj)).toString());
			// headVO.setQryobj("��ѯ����");
			headVO.setPk_currtype(((List<?>) oneObj.get(0)).get(metaData.getNameIndex(RecStatementItemVO.PK_CURRTYPE)).toString());

			//update chenth 20180225 BHS Ӧ�ն��˵�����Ҫ�ڳ��ͺϼ��У���Ҫ��ʾ����δ�����Ӧ�յ��ͱ��ڵ��տ�,�����ʾΪ��ǰӦ�յ���δ��������
			// ���ñ�ͷ���
			headVO.setBal_qua(totalVO.getBal_qua());
			headVO.setBal_ori(totalVO.getBal_ori());
			headVO.setBal_loc(totalVO.getBal_loc());
			headVO.setGr_bal_loc(totalVO.getGr_bal_loc());
			headVO.setGl_bal_loc(totalVO.getGl_bal_loc());
//			totalVO.setBal_qua(SafeCompute.add(totalVO.getBal_qua(),bodyVO.getBal_qua()));
//			totalVO.setBal_ori(SafeCompute.add(totalVO.getBal_ori(),bodyVO.getBal_ori()));
//			totalVO.setBal_loc(SafeCompute.add(totalVO.getBal_loc(),bodyVO.getBal_loc()));
//			totalVO.setGr_bal_loc(SafeCompute.add(totalVO.getGr_bal_loc(),bodyVO.getGr_bal_loc()));
//			totalVO.setGl_bal_loc(SafeCompute.add(totalVO.getGl_bal_loc(),bodyVO.getGl_bal_loc()));
//			bodyVOs.add(totalVO);
			//update end

			aggVO.setParentVO(headVO);
			aggVO.setChildrenVO(bodyVOs.toArray(new CircularlyAccessibleValueObject[0]));
			
			//update for �¼���BDA 20161111
			//edit by xudw
			if(isFinalBalance.equals(UFBoolean.FALSE)){
				if(!UFDouble.ZERO_DBL.equals(bodyVO.getBal_ori())
						|| !UFDouble.ZERO_DBL.equals(bodyVO.getBal_loc())
						|| !UFDouble.ZERO_DBL.equals(bodyVO.getGr_bal_loc())
						|| !UFDouble.ZERO_DBL.equals(bodyVO.getGl_bal_loc())){
					qryObjKeyList.add(headVO.getPk_qryobj());
					resultVOMap.put(keyList.get(i), aggVO);
				}
			}else{
				qryObjKeyList.add(headVO.getPk_qryobj());
				resultVOMap.put(keyList.get(i), aggVO);
			}
			//update end
		}

		List<AggRecStatementVO> resultVOList = new ArrayList<AggRecStatementVO>();
		if (qryObjKeyList.size() > 0) {
			// ���������ѯ��������
			SuperVO[] resultVos = getQryObjName(qryCondVO.getAssoObj(), qryObjKeyList);
			Map<String, String> qryObjNameMap = new HashMap<String, String>();
			String qryObjName = null;
			for (SuperVO vo : resultVos) {
				if (vo == null) {
					continue;
				}
				qryObjName = (String) vo.getAttributeValue("name" + multiLangIndex);
				if (StringUtils.isEmpty(qryObjName)) {
					qryObjName = (String) vo.getAttributeValue("name");
				}
				qryObjNameMap.put(vo.getPrimaryKey(), qryObjName);
			}

			AggRecStatementVO aggRecStatementVO = null;
			for (int i = 0; i < keyList.size(); i++) {
				aggRecStatementVO = resultVOMap.get(keyList.get(i));
				if (aggRecStatementVO == null) {
					continue;
				}
				headVO = (RecStatementVO) aggRecStatementVO.getParentVO();
				headVO.setQryobj(qryObjNameMap.get(headVO.getPk_qryobj()));
				// ��ԭ��ѯ����˳��
				resultVOList.add(aggRecStatementVO);
			}
		}
		
		//update for �¼���BDA 20161111
		//add kuangxw
		ArapStatementCalculator calculator = new ArapStatementCalculator();
		if(UFBoolean.TRUE == qryCondVO.getUserObject().get(IArapReportConstants.IS_TOTALCODE)){
			return calculator.computeDateSubTotal(resultVOList);
		}
		
		return resultVOList;//calculator.computeDateSubTotal(resultVOList);
		//update end
	}

	private String getKey(List<?> oneLine, int[] indexs) {
		StringBuffer buff = new StringBuffer();
		for (int ind : indexs) {
			buff.append(oneLine.get(ind));
		}
		return buff.toString();
	}

	private SuperVO[] getQryObjName(String assoObj, List<String> keyList) throws BusinessException {
		String[] keyArray = keyList.toArray(new String[0]);

		String multiLangIndex = PubCommonReportMethod.getMultiLangIndex();

		SuperVO[] result = null;
		if (BillEnumCollection.ObjType.CUSTOMER.VALUE.toString().equals(assoObj)) {
			// �ͻ�
			result = NCLocator.getInstance().lookup(ICustomerPubService.class).getCustomerVO(
					keyArray, new String[] { CustomerVO.PK_CUSTOMER, CustomerVO.NAME, CustomerVO.NAME + multiLangIndex });
		} else if (BillEnumCollection.ObjType.DEP.VALUE.toString().equals(assoObj)) {
			// ����
			result = NCLocator.getInstance().lookup(IDeptPubService.class).queryDeptVOsByPKS(keyArray);
		} else if (BillEnumCollection.ObjType.PERSON.VALUE.toString().equals(assoObj)) {
			// ҵ��Ա
			result = NCLocator.getInstance().lookup(IPsndocPubService.class).queryPsndocByPks(
					keyArray, new String[] { PsndocVO.PK_PSNDOC, PsndocVO.NAME, PsndocVO.NAME + multiLangIndex });
		} else if (BillEnumCollection.ObjType.SUPPLIER.VALUE.toString().equals(assoObj)) {
			// ��Ӧ��
			result = NCLocator.getInstance().lookup(ISupplierPubService.class).getSupplierVO(
					keyArray, new String[] { SupplierVO.PK_SUPPLIER, SupplierVO.NAME, SupplierVO.NAME + multiLangIndex });
		} else if (BillEnumCollection.ObjType.CUSSUP.VALUE.toString().equals(assoObj)) {
			// ����
			NCObject[] ncObjects = MDPersistenceService.lookupPersistenceQueryService().queryBillOfNCObjectByPKs(CustSupplierVO.class, keyArray, new String[] { CustSupplierVO.PK_CUST_SUP, CustSupplierVO.NAME, CustSupplierVO.NAME + multiLangIndex }, false);
			if(ncObjects != null && ncObjects.length > 0){
				CustSupplierVO[] custsupVOs = new CustSupplierVO[ncObjects.length];
				for(int i =0 ; i< ncObjects.length; i++){
					CustSupplierVO custsupVO = new CustSupplierVO();
					custsupVO.setPk_cust_sup(((CustSupplierVO)ncObjects[i].getContainmentObject()).getPk_cust_sup());
					custsupVO.setAttributeValue("name" + multiLangIndex, ((CustSupplierVO)ncObjects[i].getContainmentObject()).getAttributeValue("name" + multiLangIndex));
					custsupVOs[i] = custsupVO;
				}
				return custsupVOs;
			}
		}
		
		return result;
	}

	private void reSetDept(ReportQueryCondVO qryConditionVO){ 
		try {
			String pk_dept = "";
			Map<String,String> map = new HashMap<String,String>();
			map = NCLocator.getInstance().lookup(IDeptPubService.class).getOIDSByDeptVIDS(new String[] {qryConditionVO.getDept()});
			pk_dept = map.get(qryConditionVO.getDept());
			qryConditionVO.setDept(pk_dept);	
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}
}

///:~
