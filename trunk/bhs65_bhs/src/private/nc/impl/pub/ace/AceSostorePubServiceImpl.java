package nc.impl.pub.ace;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import nc.bs.bhs.sostore.ace.bp.AceSostoreApproveBP;
import nc.bs.bhs.sostore.ace.bp.AceSostoreDeleteBP;
import nc.bs.bhs.sostore.ace.bp.AceSostoreInsertBP;
import nc.bs.bhs.sostore.ace.bp.AceSostoreSendApproveBP;
import nc.bs.bhs.sostore.ace.bp.AceSostoreUnApproveBP;
import nc.bs.bhs.sostore.ace.bp.AceSostoreUnSendApproveBP;
import nc.bs.bhs.sostore.ace.bp.AceSostoreUpdateBP;
import nc.bs.dao.BaseDAO;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.jdbc.framework.processor.ResultSetProcessor;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.bd.cust.CustomerVO;
import nc.vo.bd.defdoc.DefdocVO;
import nc.vo.bhs.sostore.AggSoStoreHVO;
import nc.vo.bhs.sostore.SoStoreBVO;
import nc.vo.bhs.sostore.SoStoreHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.trade.voutils.SafeCompute;

import com.su.sa.job.utils.ExportExcel;

public abstract class AceSostorePubServiceImpl {
	// 新增
	public AggSoStoreHVO[] pubinsertBills(AggSoStoreHVO[] clientFullVOs,
			AggSoStoreHVO[] originBills) throws BusinessException {
		try {
			// 数据库中数据和前台传递过来的差异VO合并后的结果
			BillTransferTool<AggSoStoreHVO> transferTool = new BillTransferTool<AggSoStoreHVO>(
					clientFullVOs);
			// 调用BP
			AceSostoreInsertBP action = new AceSostoreInsertBP();
			//add chenth 20181002 计算m2 m3 largest
			caculate(clientFullVOs[0]);
			//add end 
			AggSoStoreHVO[] retvos = action.insert(clientFullVOs);
			
			//add chenth 20180820 导出excel给ITS
			exportExcel(retvos[0]);
			//add end
			
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// 删除
	public void pubdeleteBills(AggSoStoreHVO[] clientFullVOs,
			AggSoStoreHVO[] originBills) throws BusinessException {
		try {
			// 调用BP
			new AceSostoreDeleteBP().delete(clientFullVOs);
			
			deleteCSV(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// 修改
	public AggSoStoreHVO[] pubupdateBills(AggSoStoreHVO[] clientFullVOs,
			AggSoStoreHVO[] originBills) throws BusinessException {
		try {
			// 加锁 + 检查ts
			BillTransferTool<AggSoStoreHVO> transferTool = new BillTransferTool<AggSoStoreHVO>(
					clientFullVOs);
			AceSostoreUpdateBP bp = new AceSostoreUpdateBP();
			//add chenth 20181002 计算m2 m3 largest
			caculate(clientFullVOs[0]);
			//add end 
			AggSoStoreHVO[] retvos = bp.update(clientFullVOs, originBills);
			
			//add chenth 20180820 导出excel给ITS
			exportExcel(retvos[0]);
			//add end
			
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public AggSoStoreHVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		AggSoStoreHVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<AggSoStoreHVO> query = new BillLazyQuery<AggSoStoreHVO>(
					AggSoStoreHVO.class);
			bills = query.query(queryScheme, null);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return bills;
	}

	/**
	 * 由子类实现，查询之前对queryScheme进行加工，加入自己的逻辑
	 * 
	 * @param queryScheme
	 */
	protected void preQuery(IQueryScheme queryScheme) {
		// 查询之前对queryScheme进行加工，加入自己的逻辑
	}

	// 提交
	public AggSoStoreHVO[] pubsendapprovebills(
			AggSoStoreHVO[] clientFullVOs, AggSoStoreHVO[] originBills)
			throws BusinessException {
		AceSostoreSendApproveBP bp = new AceSostoreSendApproveBP();
		AggSoStoreHVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// 收回
	public AggSoStoreHVO[] pubunsendapprovebills(
			AggSoStoreHVO[] clientFullVOs, AggSoStoreHVO[] originBills)
			throws BusinessException {
		AceSostoreUnSendApproveBP bp = new AceSostoreUnSendApproveBP();
		AggSoStoreHVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// 审批
	public AggSoStoreHVO[] pubapprovebills(AggSoStoreHVO[] clientFullVOs,
			AggSoStoreHVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceSostoreApproveBP bp = new AceSostoreApproveBP();
		AggSoStoreHVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// 弃审

	public AggSoStoreHVO[] pubunapprovebills(AggSoStoreHVO[] clientFullVOs,
			AggSoStoreHVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceSostoreUnApproveBP bp = new AceSostoreUnApproveBP();
		AggSoStoreHVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}
	
	private void exportExcel(AggSoStoreHVO aggVO) throws BusinessException{
		if(aggVO == null){
			return;
		}
		SoStoreHVO hvo = aggVO.getParentVO();
		String jobOrderNo = hvo.getVbillno();
		String type = hvo.getDef1();
		
		Map<String, String> itsparaMap = getITSPara();
		
		//add chenth 20181126 增加inspection和change ownership的处理
		String customer = getCustomerName(hvo);
		if("INBOUND".equals(type)){
			exportInboundExcel(aggVO, jobOrderNo, customer, itsparaMap);
		}else if("OUTBOUND".equals(type)){
			exportOutboundExcel(aggVO, jobOrderNo, customer, itsparaMap, false);
			
		}else if("Inspection".equals(type)){
			//先自动release
			exportOutboundExcel(aggVO, jobOrderNo, customer, itsparaMap, true);
			//再自动inbound
			exportInboundExcel(aggVO, jobOrderNo, customer, itsparaMap);
		}else if("Change ownership".equals(type)){
			String newJoborderNo = hvo.getDef3();
			String newCustomer = getNewOwnership(hvo.getDef2());
			SoStoreBVO[] bvos = (SoStoreBVO[]) aggVO.getChildren(SoStoreBVO.class);
			for(SoStoreBVO bvo : bvos){
				//RFID置空，重新生成
				bvo.setDef10(null);
			}
			//先自动release
			exportOutboundExcel(aggVO, jobOrderNo, customer, itsparaMap, true);
			//再自动inbound
			exportInboundExcel(aggVO, newJoborderNo, newCustomer, itsparaMap);
		}
		
	}
	

	private void exportOutboundExcel(AggSoStoreHVO aggVO, String jobOrderNo,
			String customer, Map<String, String> itsparaMap, boolean isAutoRelease) throws BusinessException {
		String[] titleArry = new String[] { "AssetNo", "Qty", "Release Date"};
		if(isAutoRelease){
			jobOrderNo = "AR_" + jobOrderNo;
		}
		
		String outpath = itsparaMap.get("PATH_OUTBOUND");
		//先删除对应文件
		ExportExcel.deleteCSV(jobOrderNo, outpath);
		
		SoStoreHVO hvo = aggVO.getParentVO();
		SoStoreBVO[] bvos = (SoStoreBVO[]) aggVO.getChildren(SoStoreBVO.class);
		if(bvos == null
				|| bvos.length < 1){
			return;
		}
		List<String[]> recordList = new ArrayList<String[]>();
		
		UFDate date = hvo.getDbilldate().getDateBefore(1);
		DateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
		String releasedate = date.toString(TimeZone.getDefault(), df);
		
		for(SoStoreBVO bvo : bvos){
			if(bvo.getDef3() != null){
				String[] record = new String[3];
				record[0] = bvo.getDef3();
				record[1] = bvo.getDef4();
				record[2] = releasedate;
				
				recordList.add(record);
			}
		}
		if(recordList.size() > 0){
			ExportExcel.createCSV(jobOrderNo, titleArry, recordList,
					outpath);
		}
	}

	private void exportInboundExcel(AggSoStoreHVO aggVO, String jobOrderNo,
			String customer, Map<String, String> itsparaMap) throws BusinessException {
		String inpath = itsparaMap.get("PATH_INBOUND");
		//先删除对应文件
		ExportExcel.deleteCSV(jobOrderNo, inpath);
		
		SoStoreHVO hvo = aggVO.getParentVO();
		SoStoreBVO[] bvos = (SoStoreBVO[]) aggVO.getChildren(SoStoreBVO.class);
		if(bvos == null
				|| bvos.length < 1){
			return;
		}
		List<String[]> recordList = new ArrayList<String[]>();

		String toolid = hvo.getToolidlid() == null ? "" : hvo.getToolidlid();
		Integer maxRFID = Integer.valueOf(itsparaMap.get("RFID_SEQ_NUMBER"));
		String prex = itsparaMap.get("RFID_PREFIX_PROVIDE") + itsparaMap.get("RFID_PREFIX_BHS");
		Integer lengthofRFID = Integer.valueOf(itsparaMap.get("RFID_LENGTH"));
		String RFIDNumber = null;
		DateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
		UFDate dbilldate = hvo.getDbilldate();
		int year = dbilldate.getYear();
		int yearofRFID = Integer.valueOf(itsparaMap.get("RFID_YEAR"));
		//RFID 流水号重新编号
		if(year!=yearofRFID){
			maxRFID = 0;
		}
		prex = prex + year;

		String[] titleArry = new String[] { "Customer", "Manufacturer", "Model", "Sub Model", "Tag ID", "Description", "Date of Purchase", "Length", "Width", "Height", "Weight", "Location", "Person In Charge", "Qty"
				, "Shock Watch Activated", "Tilt_Watch_Activated", "Remarks", "Physically Damaged", "Progress/Status", "Project", "Inspected", "Crates Status", "Toppel Risk Crate No", "Asset No", "Micap No", "Tool ID/LID", "Set No"};
		for(SoStoreBVO bvo : bvos){
			if(bvo.getDef6() != null){
				if(bvo.getDef10() == null 
						|| "".equals(bvo.getDef10().trim())){
					maxRFID = maxRFID + 1;
					RFIDNumber = prex + frontCompWithZore(maxRFID, lengthofRFID-prex.length());
					bvo.setDef10(RFIDNumber);
				}
				String snno = bvo.getDef11()==null? "" : bvo.getDef11();
				String description = bvo.getDef2()==null? "" : bvo.getDef2();
				//add chenth 20181128 逗号处理
				description = description.replaceAll(",", " ").replaceAll("\n", " ");
				//description = description + "  " + toolid + "  " + snno;
				String[] record = new String[titleArry.length];
				record[0] = customer;//Customer
				record[1] = hvo.getSuppliername()==null? "" : hvo.getSuppliername();//Manufacturer
				record[2] = hvo.getMachinemodel()==null? "" : hvo.getMachinemodel();//Model
				record[3] = hvo.getMachinesubmodel()==null? "" : hvo.getMachinesubmodel();//Sub Model
				record[4] = bvo.getDef10();//Tag ID RFID Number
				record[5] = description;//Description
				record[6] = dbilldate.toString(TimeZone.getDefault(), df);//Date of INBound/OUTBound
				record[7] = bvo.getDef6()==null? "" : bvo.getDef6();//Length
				record[8] = bvo.getDef7()==null? "" : bvo.getDef7();//Width
				record[9] = bvo.getDef8()==null? "" : bvo.getDef8();//Height
				record[10] = bvo.getDef9()==null? "" : bvo.getDef9();//Weight
				record[11] = hvo.getWarehousezone()==null? "Virtual Location" : hvo.getWarehousezone();//Location
				record[12] = "";//Person In Charge
				record[13] = bvo.getDef4()==null? "1" : bvo.getDef4();;//Qty
				record[14] = "";//hvo.getNoofshockwatches()==null? "" : String.valueOf(hvo.getNoofshockwatches());//Shock Watch Activated
				record[15] = "";//hvo.getNooftiltwatches()==null? "" : String.valueOf(hvo.getNooftiltwatches());//Tilt_Watch_Activated
				record[16] = bvo.getDef12()==null? "" : bvo.getDef12();//Remarks
				record[17] = "";//hvo.getDamagedcrateno()==null? "" : hvo.getDamagedcrateno();//Physically Damaged
				record[18] = "";//Progress/Status
				record[19] = "";//Project
				record[20] = "";//Inspected
				record[21] = hvo.getCratestatus()==null? "" : getCrateStatus(hvo.getCratestatus());//Crates Status
				record[22] = hvo.getToppleriskcrateno()==null? "" : hvo.getToppleriskcrateno();//Toppel Risk Crate No
				record[23] = "";//
				record[24] = hvo.getMicapno()==null? "" : hvo.getMicapno();//Micap No
				record[25] = toolid;//Tool ID/LID
				record[26] = snno;//Set No
				recordList.add(record);
			}
		}
		if(recordList.size() > 0){
			ExportExcel.createCSV(jobOrderNo , titleArry, recordList,
					inpath);
		}
		BaseDAO dao = new BaseDAO();
		//更新vo的RFID Number
		dao.updateVOArray(bvos, new String[]{"def10"});
		Collection<SoStoreBVO> newBvos = dao.retrieveByClause(SoStoreBVO.class, " dr=0 and billid = '" + hvo.getBillid() + "' ");
		aggVO.setChildren(SoStoreBVO.class, newBvos.toArray(new SoStoreBVO[newBvos.size()]));
		
		//更新RFID的流水号参数
		dao.executeUpdate("update bd_defdoc set name='" + maxRFID + "' where code = 'RFID_SEQ_NUMBER' ");
		if(year!=yearofRFID){
			dao.executeUpdate("update bd_defdoc set name='" + year + "' where code = 'RFID_YEAR' ");
		}
	}

	private void deleteCSV(AggSoStoreHVO[] aggVOs) throws BusinessException{
			if(aggVOs == null 
					|| aggVOs.length < 1){
				return;
			}
			Map<String, String> itsparaMap = getITSPara();
			
			for(AggSoStoreHVO aggVO : aggVOs){
				SoStoreHVO hvo = aggVO.getParentVO();
				String jobOrderNo = hvo.getVbillno();
				String type = hvo.getDef1();
				String path = null;
				if("INBOUND".equals(type)){
					type = "IN";
					path = itsparaMap.get("PATH_INBOUND");
				}else{
					type = "OUT";
					path = itsparaMap.get("PATH_OUTBOUND");
				}
				//先删除对应文件
				ExportExcel.deleteCSV(jobOrderNo , path);
			}
	}
	

	private Map<String, String> getITSPara() throws BusinessException {
		String sql = "select code, name from bd_defdoc where pk_defdoclist in(select pk_defdoclist from bd_defdoclist where code = 'BHS98')";
		
		Map<String, String> itsparaMap = (Map<String, String>) new BaseDAO().executeQuery(sql, new ResultSetProcessor(){
			@Override
			public Object handleResultSet(ResultSet rs) throws SQLException {
				Map<String, String> itsparaMap = new HashMap<String, String>();
		        while (rs.next()) {
		        	itsparaMap.put(rs.getString(1), rs.getString(2));
		        }
		        return itsparaMap;
			}
			
		});
		
		return itsparaMap;
	}
	


	private String getCustomerName(SoStoreHVO hvo) throws BusinessException {
		String custName = null;
		String csaleorderid = hvo.getCsaleorderid();
		String condition = " pk_customer in ( select ccustomerid from so_saleorder where csaleorderid = '" 
					+ csaleorderid + "') ";
		Collection<CustomerVO> vos = new BaseDAO().retrieveByClause(CustomerVO.class, condition, new String[]{"code", "name"});
		
		if(vos != null ){
			for ( CustomerVO vo : vos){
				custName = vo.getCode();
			}
		}
		return custName;
	}
	

	private String getNewOwnership(String newjobid) throws BusinessException {
		String sql = "select custcode, custname from v_bhs_storejob where jobid = '" + newjobid +"' ";
		
		String custcode = (String) new BaseDAO().executeQuery(sql, new ResultSetProcessor(){
			@Override
			public Object handleResultSet(ResultSet rs) throws SQLException {
				String custcode = null;
		        while (rs.next()) {
		        	custcode = rs.getString("custcode");
		        }
		        return custcode;
			}
			
		});
		if(custcode == null){
			ExceptionUtils.wrappBusinessException("Please input the New Ownership. ");
		}
		return custcode;
	}
	
	private String getCrateStatus(String pk_defdoc) throws BusinessException {
		String cratestatus = "";
		DefdocVO vo = (DefdocVO) new BaseDAO().retrieveByPK(DefdocVO.class, pk_defdoc);
		
		if(vo != null ){
			cratestatus = vo.getName();
		}
		return cratestatus;
	}
	
	public String frontCompWithZore(int number,int formatLength){
		/*
		 * 0 指前面补充零
		 * formatLength 字符总长度为 formatLength
		 * d 代表为正数
		 */
		String newString = String.format("%0"+formatLength+"d", number);
		return newString;
	}
	

	private void caculate(AggSoStoreHVO aggVO) {
		SoStoreHVO hvo = aggVO.getParentVO();
		SoStoreBVO[] bvos = (SoStoreBVO[]) aggVO.getChildren(SoStoreBVO.class);
		if(bvos == null
				|| bvos.length < 1){
			return;
		}
		UFDouble m2 = UFDouble.ZERO_DBL;
		UFDouble m3 = UFDouble.ZERO_DBL;
		UFDouble kg = UFDouble.ZERO_DBL;
		Integer totalPkgs = 0;
		UFDouble largestL = UFDouble.ZERO_DBL;
		UFDouble largestW = UFDouble.ZERO_DBL;
		UFDouble largestH = UFDouble.ZERO_DBL;
		UFDouble largestWeight = UFDouble.ZERO_DBL;
		String lcrate = null;
		String wcrate = null;
		String hcrate = null;
		String kcrate = null;

		UFDouble L = UFDouble.ZERO_DBL;
		UFDouble W = UFDouble.ZERO_DBL;
		UFDouble H = UFDouble.ZERO_DBL;
		UFDouble Weight = UFDouble.ZERO_DBL;
		for(SoStoreBVO bvo : bvos){
			if(bvo.getDef6()!=null){
				totalPkgs ++;
			}
			m2 = SafeCompute.add(m2, new UFDouble(bvo.getDef14()));
			m3 = SafeCompute.add(m3, new UFDouble(bvo.getDef15()));
			kg = SafeCompute.add(kg, new UFDouble(bvo.getDef9()));
			L = bvo.getDef6() == null ? UFDouble.ZERO_DBL : new UFDouble(bvo.getDef6());
			W = bvo.getDef7() == null ? UFDouble.ZERO_DBL : new UFDouble(bvo.getDef7());
			H = bvo.getDef8() == null ? UFDouble.ZERO_DBL : new UFDouble(bvo.getDef8());
			Weight = bvo.getDef9() == null ? UFDouble.ZERO_DBL : new UFDouble(bvo.getDef9());
			if(largestL.compareTo(L) < 0){
				largestL = L;
				lcrate = bvo.getDef11();
			}
			if(largestW.compareTo(W) < 0){
				largestW = W;
				wcrate = bvo.getDef11();
			}
			if(largestH.compareTo(H) < 0){
				largestH = H;
				hcrate = bvo.getDef11();
			}
			if(largestWeight.compareTo(Weight) < 0){
				largestWeight = Weight;
				kcrate = bvo.getDef11();
			}
		}
		
		hvo.setSummarym2(m2);
		hvo.setSummarym3(m3);
		hvo.setSummarykg(kg);
		hvo.setSummarypkgs(new UFDouble(totalPkgs));
		hvo.setLargestlength(largestL);
		hvo.setLargestwidth(largestW);
		hvo.setLargestheight(largestH);
		hvo.setLargestweight(largestWeight);
		hvo.setLcrate(lcrate);
		hvo.setWcrate(wcrate);
		hvo.setHcrate(hcrate);
		hvo.setKcrate(kcrate);
		
		
		
	}
	


}