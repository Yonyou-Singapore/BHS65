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
import nc.vo.bhs.sostore.AggSoStoreHVO;
import nc.vo.bhs.sostore.SoStoreBVO;
import nc.vo.bhs.sostore.SoStoreHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import com.su.sa.job.utils.ExportExcel;

public abstract class AceSostorePubServiceImpl {
	// ����
	public AggSoStoreHVO[] pubinsertBills(AggSoStoreHVO[] clientFullVOs,
			AggSoStoreHVO[] originBills) throws BusinessException {
		try {
			// ���ݿ������ݺ�ǰ̨���ݹ����Ĳ���VO�ϲ���Ľ��
			BillTransferTool<AggSoStoreHVO> transferTool = new BillTransferTool<AggSoStoreHVO>(
					clientFullVOs);
			// ����BP
			AceSostoreInsertBP action = new AceSostoreInsertBP();
			AggSoStoreHVO[] retvos = action.insert(clientFullVOs);
			
			//chenth 20180820 ����excel��ITS
			exportExcel(retvos[0]);
			
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// ɾ��
	public void pubdeleteBills(AggSoStoreHVO[] clientFullVOs,
			AggSoStoreHVO[] originBills) throws BusinessException {
		try {
			// ����BP
			new AceSostoreDeleteBP().delete(clientFullVOs);
			
			deleteCSV(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// �޸�
	public AggSoStoreHVO[] pubupdateBills(AggSoStoreHVO[] clientFullVOs,
			AggSoStoreHVO[] originBills) throws BusinessException {
		try {
			// ���� + ���ts
			BillTransferTool<AggSoStoreHVO> transferTool = new BillTransferTool<AggSoStoreHVO>(
					clientFullVOs);
			AceSostoreUpdateBP bp = new AceSostoreUpdateBP();
			AggSoStoreHVO[] retvos = bp.update(clientFullVOs, originBills);
			
			//chenth 20180820 ����excel��ITS
			exportExcel(retvos[0]);
			
			// ���췵������
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
	 * ������ʵ�֣���ѯ֮ǰ��queryScheme���мӹ��������Լ����߼�
	 * 
	 * @param queryScheme
	 */
	protected void preQuery(IQueryScheme queryScheme) {
		// ��ѯ֮ǰ��queryScheme���мӹ��������Լ����߼�
	}

	// �ύ
	public AggSoStoreHVO[] pubsendapprovebills(
			AggSoStoreHVO[] clientFullVOs, AggSoStoreHVO[] originBills)
			throws BusinessException {
		AceSostoreSendApproveBP bp = new AceSostoreSendApproveBP();
		AggSoStoreHVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// �ջ�
	public AggSoStoreHVO[] pubunsendapprovebills(
			AggSoStoreHVO[] clientFullVOs, AggSoStoreHVO[] originBills)
			throws BusinessException {
		AceSostoreUnSendApproveBP bp = new AceSostoreUnSendApproveBP();
		AggSoStoreHVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// ����
	public AggSoStoreHVO[] pubapprovebills(AggSoStoreHVO[] clientFullVOs,
			AggSoStoreHVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceSostoreApproveBP bp = new AceSostoreApproveBP();
		AggSoStoreHVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// ����

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
		String outpath = itsparaMap.get("PATH_OUTBOUND");
		String inpath = itsparaMap.get("PATH_INBOUND");
		
		//��ɾ����Ӧ�ļ�
		ExportExcel.deleteCSV(jobOrderNo, inpath);
		ExportExcel.deleteCSV(jobOrderNo, outpath);
		
		SoStoreBVO[] bvos = (SoStoreBVO[]) aggVO.getChildren(SoStoreBVO.class);
		if(bvos == null
				|| bvos.length < 1){
			return;
		}
		List<String[]> recordList = new ArrayList<String[]>();
		
		if("INBOUND".equals(type) ){
			String customer = getCustomerName(hvo);
			String toolid = hvo.getDef19() == null ? "" : hvo.getDef19();
			Integer maxRFID = Integer.valueOf(itsparaMap.get("RFID_SEQ_NUMBER"));
			String prex = itsparaMap.get("RFID_PREFIX_PROVIDE") + itsparaMap.get("RFID_PREFIX_BHS");
			Integer lengthofRFID = Integer.valueOf(itsparaMap.get("RFID_LENGTH"));
			String RFIDNumber = null;
			DateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
			UFDate dbilldate = hvo.getDbilldate();
			int year = dbilldate.getYear();
			int yearofRFID = Integer.valueOf(itsparaMap.get("RFID_YEAR"));
			//RFID ��ˮ�����±��
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
					//description = description + "  " + toolid + "  " + snno;
					
					
					String[] record = new String[titleArry.length];
					record[0] = customer;//Customer
					record[1] = hvo.getDef16()==null? "" : hvo.getDef16();//Manufacturer
					record[2] = hvo.getDef17()==null? "" : hvo.getDef17();//Model
					record[3] = hvo.getDef18()==null? "" : hvo.getDef18();//Sub Model
					record[4] = bvo.getDef10();//Tag ID RFID Number
					record[5] = description;//Description
					record[6] = bvo.getDef5()==null? hvo.getDbilldate().toString(TimeZone.getDefault(), df) : UFDate.getDate(bvo.getDef5()).toString(TimeZone.getDefault(), df);//Date of Purchase
					record[7] = bvo.getDef6()==null? "" : bvo.getDef6();//Length
					record[8] = bvo.getDef7()==null? "" : bvo.getDef7();//Width
					record[9] = bvo.getDef8()==null? "" : bvo.getDef8();//Height
					record[10] = bvo.getDef9()==null? "" : bvo.getDef9();//Weight
					record[11] = "Virtual Location";//Location
					record[12] = "";//Person In Charge
					record[13] = bvo.getDef4();//Qty
					record[14] = "";//
					record[15] = "";//
					record[16] = "";//
					record[17] = "";//
					record[18] = "";//
					record[19] = "";//
					record[20] = "";//
					record[21] = "";//
					record[22] = "";//
					record[23] = "";//
					record[24] = hvo.getDef20()==null? "" : hvo.getDef20();//Micap No
					record[25] = toolid;//Tool ID/LID
					record[26] = snno;//Set No
					recordList.add(record);
				}
			}
			if(recordList.size() > 0){
				ExportExcel.createCSV(jobOrderNo , titleArry, recordList,
						inpath);
			}
			//����vo��RFID Number
			new BaseDAO().updateVOArray(bvos, new String[]{"def10"});
			//����RFID����ˮ�Ų���
			new BaseDAO().executeUpdate("update bd_defdoc set name='" + maxRFID + "' where code = 'RFID_SEQ_NUMBER' ");
			if(year!=yearofRFID){
				new BaseDAO().executeUpdate("update bd_defdoc set name='" + year + "' where code = 'RFID_YEAR' ");
			}
			
		}else if("OUTBOUND".equals(type)){
			String[] titleArry = new String[] { "AssetNo", "Qty"};
			for(SoStoreBVO bvo : bvos){
				if(bvo.getDef3() != null){
					String[] record = new String[2];
					record[0] = bvo.getDef3();
					record[1] = bvo.getDef4();
					recordList.add(record);
				}
			}
			if(recordList.size() > 0){
				ExportExcel.createCSV(jobOrderNo, titleArry, recordList,
						outpath);
			}
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
				//��ɾ����Ӧ�ļ�
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
	
	public String frontCompWithZore(int number,int formatLength){
		/*
		 * 0 ָǰ�油����
		 * formatLength �ַ��ܳ���Ϊ formatLength
		 * d ����Ϊ����
		 */
		String newString = String.format("%0"+formatLength+"d", number);
		return newString;
	}


}