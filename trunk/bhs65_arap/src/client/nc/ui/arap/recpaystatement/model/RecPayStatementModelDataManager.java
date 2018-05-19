package nc.ui.arap.recpaystatement.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nc.itf.fi.pub.Currency;
import nc.itf.fipub.report.IPubReportConstants;
import nc.itf.org.IOrgConst;
import nc.pubitf.uapbd.CurrencyRateUtilHelper;
import nc.ui.arap.scale.ArapTempletScaleGet;
import nc.ui.format.NCFormater;
import nc.ui.ml.NCLangRes;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.BillListData;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pub.bill.BillModel;
import nc.ui.pub.bill.DefaultCurrTypeDecimalAdapter;
import nc.ui.pubapp.uif2app.view.BillListView;
import nc.ui.uif2.model.IAppModelDataManagerEx;
import nc.utils.arap.ArapReportResource;
import nc.vo.arap.comreport.ArapReportPubUtil;
import nc.vo.arap.comreport.IArapReportConstants;
import nc.vo.arap.pub.BillEnumCollection;
import nc.vo.arap.tally.AggRecStatementVO;
import nc.vo.arap.tally.RecStatementItemVO;
import nc.vo.arap.tally.RecStatementVO;
import nc.vo.fipub.report.ReportQueryCondVO;
import nc.vo.fipub.timecontrol.TimeCtrlDetail;
import nc.vo.fipub.timecontrol.TimeCtrlVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.format.exception.FormatException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.uif2.LoginContext;

import org.apache.commons.lang.StringUtils;

import com.ufida.iufo.pub.tools.AppDebug;

/**
 * Ӧ��/Ӧ�����˵�ģ�͹�����<br>
 * 
 * @author ������<br>
 * @since V60<br>
 */
@SuppressWarnings("restriction")
public class RecPayStatementModelDataManager implements IAppModelDataManagerEx {

	private LoginContext context = null;
	private BillListView listView = null;
	private ReportQueryCondVO queryCondVO = null;

	private List<AggRecStatementVO> resultVOList = new ArrayList<AggRecStatementVO>();

	public void initModel() {
		// ��̬������
		adjustColumns(getQueryCondVO());
		
		// ��ӱ��徫�ȿ�����
		BillModel bodyBillModel = listView.getBillListPanel().getBodyBillModel();
		bodyBillModel.addDecimalListener(new DefaultCurrTypeDecimalAdapter(bodyBillModel, ArapTempletScaleGet.getPk_currency(), getBodyDecimalLocalkeys()));

		// ��ӱ�ͷ���ȿ�����
		BillModel headBillModel = listView.getBillListPanel().getHeadBillModel();
		headBillModel.addDecimalListener(new DefaultCurrTypeDecimalAdapter(headBillModel, ArapTempletScaleGet.getPk_currency(), getHeadDecimalLocalkeys()));

		
		if (getResultVOList() == null || getResultVOList().size() == 0) {
			getListView().getModel().initModel(null);
		} else {
			formatResult(getResultVOList());
			// ��ʾ����
			getListView().getModel().initModel(getResultVOList().toArray());
		}
	}
	/**
	 * ���������ڸ�ʽ����ת��
	 * editor tianjlc 
	 * 2015-08-06
	 */
	private void formatResult(List<AggRecStatementVO> resultVOList2) {
		try {
			for (AggRecStatementVO aggRecStatementVO : resultVOList2) {
				for (CircularlyAccessibleValueObject itemVO : aggRecStatementVO.getChildrenVO()) {
					if(((RecStatementItemVO)itemVO).getTallydate()!=null
							&&!ArapReportResource.getDateSubTotalLbl().equals(((RecStatementItemVO)itemVO).getTallydate())
							//add chenth 20180311 Remark��
							&&! "REMARKS".equals(((RecStatementItemVO)itemVO).getTallydate())
							//add end
							){
						((RecStatementItemVO)itemVO).setTallydate(NCFormater.formatDate(((RecStatementItemVO)itemVO).getTallydate()).getValue().toString());
					}
				}
			}
		} catch (FormatException e) {
			AppDebug.debug(e);
		}
	}

	private String[] getBodyDecimalLocalkeys(){
		String[] keys = {RecStatementItemVO.BAL_ORI,RecStatementItemVO.DEBT_ORI,RecStatementItemVO.CREDIT_ORI};
		return keys;
	}
	
	private String[] getHeadDecimalLocalkeys(){
		String[] keys = {RecStatementVO.BAL_ORI};
		return keys;
	}  
	
	public void setShowSealDataFlag(boolean showSealDataFlag) {

	}

	public void initModelBySqlWhere(String sqlWhere) {
		initModel();
	}

	public void refresh() {

	}

	private void adjustColumns(ReportQueryCondVO qryCondVO) {
		BillListPanel billListPanel = getListView().getBillListPanel();

		List<String> hiddenColList = new ArrayList<String>();
		hiddenColList.add(RecStatementItemVO.PK_CUSTOMER);
		hiddenColList.add(RecStatementItemVO.PK_DEPTID);
		hiddenColList.add(RecStatementItemVO.PK_PSNDOC);
		hiddenColList.add(RecStatementItemVO.PK_SUPPLIER);
		hiddenColList.add(RecStatementItemVO.MATCUSTCODE);
		//add for �¼���BDA 20161111
		//swb add
		hiddenColList.add(RecStatementItemVO.BILLNO2);
		//add by xudw
		hiddenColList.add(RecStatementItemVO.ISCOUNTERFEE);
		//add end

		// ��ʾָ����
		for (String key : hiddenColList) {
			billListPanel.showBodyTableCol(key);
		}

		if (qryCondVO != null) {
			//add for �¼���BDA 20161111
			//swb add
			UFBoolean fn = (UFBoolean)qryCondVO.getUserObject().get(IArapReportConstants.IS_DJCODE);
			if(fn.booleanValue()){
				hiddenColList.remove(RecStatementItemVO.BILLNO2);
			}
			//add by xudw
			fn = (UFBoolean)qryCondVO.getUserObject().get(IArapReportConstants.IS_COUNTER_FEE);
			if(fn.booleanValue()){
				hiddenColList.remove(RecStatementItemVO.ISCOUNTERFEE);
			}
			//add end
			
			if (BillEnumCollection.ObjType.CUSTOMER.VALUE.toString().equals(qryCondVO.getAssoObj())) {
				hiddenColList.remove(RecStatementItemVO.PK_CUSTOMER);
			} else if (BillEnumCollection.ObjType.DEP.VALUE.toString().equals(qryCondVO.getAssoObj())) {
				hiddenColList.remove(RecStatementItemVO.PK_DEPTID);
			} else if (BillEnumCollection.ObjType.PERSON.VALUE.toString().equals(qryCondVO.getAssoObj())) {
				hiddenColList.remove(RecStatementItemVO.PK_PSNDOC);
			} else if (BillEnumCollection.ObjType.SUPPLIER.VALUE.toString().equals(qryCondVO.getAssoObj())) {
				hiddenColList.remove(RecStatementItemVO.PK_SUPPLIER);
			} else if (BillEnumCollection.ObjType.CUSSUP.VALUE.toString().equals(qryCondVO.getAssoObj())) {
				if (IArapReportConstants.SYS_FLAG_REC == qryCondVO.getSyscode()) {
					hiddenColList.remove(RecStatementItemVO.PK_CUSTOMER);
				} else if (IArapReportConstants.SYS_FLAG_PAY == qryCondVO.getSyscode()) {
					hiddenColList.remove(RecStatementItemVO.PK_SUPPLIER);
				}
			}

			if (!StringUtils.isEmpty(qryCondVO.getCustomer())) {
				hiddenColList.remove(RecStatementItemVO.PK_CUSTOMER);
			}
			if (!StringUtils.isEmpty(qryCondVO.getDept())) {
				hiddenColList.remove(RecStatementItemVO.PK_DEPTID);
			}
			if (!StringUtils.isEmpty(qryCondVO.getPsndoc())) {
				hiddenColList.remove(RecStatementItemVO.PK_PSNDOC);
			}
			if (!StringUtils.isEmpty(qryCondVO.getSupplier())) {
				hiddenColList.remove(RecStatementItemVO.PK_SUPPLIER);
			}
			// ���ݲ�ѯ�����ж��Ƿ���ʾ�ͻ��������ֶ�
			if (UFBoolean.TRUE.equals(qryCondVO.getUserObject().get(IArapReportConstants.IS_MATCUSTCODE))) {
				hiddenColList.remove(RecStatementItemVO.MATCUSTCODE);
			}
			BillListData billListData = billListPanel.getBillListData();
			// ���ý�����ֵ��ʾ����
			int digit = 0;
			billListData.getHeadItem(RecStatementVO.BAL_QUA).setDecimalDigits(digit);
			billListData.getBodyItem(RecStatementItemVO.DEBT_QUA).setDecimalDigits(digit);
			billListData.getBodyItem(RecStatementItemVO.CREDIT_QUA).setDecimalDigits(digit);
			billListData.getBodyItem(RecStatementItemVO.BAL_QUA).setDecimalDigits(digit);

			digit = getCurrDigit(RecStatementVO.BAL_ORI);
			billListData.getHeadItem(RecStatementVO.BAL_ORI).setDecimalDigits(digit);
			billListData.getBodyItem(RecStatementItemVO.DEBT_ORI).setDecimalDigits(digit);
			billListData.getBodyItem(RecStatementItemVO.CREDIT_ORI).setDecimalDigits(digit);
			billListData.getBodyItem(RecStatementItemVO.BAL_ORI).setDecimalDigits(digit);

			digit = getCurrDigit(RecStatementVO.BAL_LOC);
			billListData.getHeadItem(RecStatementVO.BAL_LOC).setDecimalDigits(digit);
			billListData.getBodyItem(RecStatementItemVO.DEBT_LOC).setDecimalDigits(digit);
			billListData.getBodyItem(RecStatementItemVO.CREDIT_LOC).setDecimalDigits(digit);
			billListData.getBodyItem(RecStatementItemVO.BAL_LOC).setDecimalDigits(digit);

			digit = getCurrDigit(RecStatementVO.GR_BAL_LOC);
			billListData.getHeadItem(RecStatementVO.GR_BAL_LOC).setDecimalDigits(digit);
			billListData.getBodyItem(RecStatementItemVO.GR_DEBT_LOC).setDecimalDigits(digit);
			billListData.getBodyItem(RecStatementItemVO.GR_CREDIT_LOC).setDecimalDigits(digit);
			billListData.getBodyItem(RecStatementItemVO.GR_BAL_LOC).setDecimalDigits(digit);

			digit = getCurrDigit(RecStatementVO.GL_BAL_LOC);
			billListData.getHeadItem(RecStatementVO.GL_BAL_LOC).setDecimalDigits(digit);
			billListData.getBodyItem(RecStatementItemVO.GL_DEBT_LOC).setDecimalDigits(digit);
			billListData.getBodyItem(RecStatementItemVO.GL_CREDIT_LOC).setDecimalDigits(digit);
			billListData.getBodyItem(RecStatementItemVO.GL_BAL_LOC).setDecimalDigits(digit);
			
			//add for �¼���BDA 20161111
			// add by xudw �������䷽����̬�޸�����
			billListPanel.hideHeadTableCol(RecStatementVO.PERIOD1);
			billListPanel.hideHeadTableCol(RecStatementVO.PERIOD2);
			billListPanel.hideHeadTableCol(RecStatementVO.PERIOD3);
			billListPanel.hideHeadTableCol(RecStatementVO.PERIOD4);
			billListPanel.hideHeadTableCol(RecStatementVO.PERIOD5);
			billListPanel.hideHeadTableCol(RecStatementVO.PERIOD6);
			billListPanel.hideHeadTableCol(RecStatementVO.PERIOD7);
			billListPanel.hideHeadTableCol(RecStatementVO.PERIOD8);
			
			Object obj = qryCondVO.getUserObject().get(IArapReportConstants.AGING_REF);
			String accAgePlan = obj == null ? null: String.valueOf(obj);
			if(accAgePlan != null && accAgePlan.length() > 0 && !accAgePlan.equals("null")){
				// ��ȡ���䷽��
				try{
					TimeCtrlVO timeCtrlVO = ArapReportPubUtil.getTimeCtrlVO(accAgePlan);
					
					Integer unit = timeCtrlVO.getUnit();
					String unitName = "";
					if(unit == 0){
						unitName = NCLangRes.getInstance().getStrByID("zhtx", "RecPayStatementModelDataManager-0000")/*��*/;
					}else if(unit == 1){
						unitName = NCLangRes.getInstance().getStrByID("zhtx", "RecPayStatementModelDataManager-0001")/*��*/;
					}else if(unit == 2){
						unitName = NCLangRes.getInstance().getStrByID("zhtx", "RecPayStatementModelDataManager-0002")/*��*/;
					}
					
					TimeCtrlDetail[] timeCtrlDetails = timeCtrlVO.getItems();
					BillListData bd = listView.getBillListPanel().getBillListData();
					if(timeCtrlDetails != null && timeCtrlDetails.length >0){
						int length = timeCtrlDetails.length;
						List<String> listDesc = new ArrayList<String>();
						for(int i=0;i<length;i++){
							if(i==0){
								if(timeCtrlDetails[i].getStartunit() != null){
									Integer t = timeCtrlDetails[i].getStartunit();
									if(t == null){
										listDesc.add(NCLangRes.getInstance().getStrByID("zhtx", "RecPayStatementModelDataManager-0003", null, new String[]{"",unitName})/*{0}{1}��ǰ*/);
									}else{
										listDesc.add(NCLangRes.getInstance().getStrByID("zhtx", "RecPayStatementModelDataManager-0003", null, new String[]{t + "",unitName})/*{0}{1}��ǰ*/);
									}
								}
								listDesc.add(timeCtrlDetails[i].getDescr());
							}else if(i==length-1){
								listDesc.add(timeCtrlDetails[i].getDescr());
								if(timeCtrlDetails[i].getEndunit() != null)
									listDesc.add((timeCtrlDetails[i].getEndunit()+1)+unitName+NCLangRes.getInstance().getStrByID("zhtx", "RecPayStatementModelDataManager-0004")/*����*/);
							}else{
								listDesc.add(timeCtrlDetails[i].getDescr());
							}
						}
						length = listDesc.size();
						if(length > 8)
							length = 8;
						for(int i=0;i<length;i++){
							int index = i+1;
							BillItem item = bd.getHeadItem("period"+index);
							item.setName(listDesc.get(i));
							billListPanel.showHeadTableCol("period"+index);
						}
					}
					listView.getBillListPanel().setListData(bd);
					//add chenth 20180327 ���ܶ�ѡ�����Ҳ���������ӡ
					listView.setMultiSelectionEnable(true);
					listView.setMultiSelectionMode(BillListView.MOUSE_CLICK_SELECTION);
					((nc.ui.arap.recpaystatement.view.ListView)listView).setListMultiProp();
				}catch(BusinessException e){
					MessageDialog.showErrorDlg(listView, NCLangRes.getInstance().getStrByID("zhtx", "RecPayStatementModelDataManager-0005")/*��ѯ���䷽������*/, e.getMessage());
				}
			}
		}
		//add end

		// ����ָ����
		for (String key : hiddenColList) {
			billListPanel.hideBodyTableCol(key);
		}
		//del chenth 20180327 ��ͷ֧������
//		billListPanel.getHeadTable().setSortEnabled(false);
		billListPanel.getBodyTable().setSortEnabled(false);
	}

	public LoginContext getContext() {
		return context;
	}

	public void setContext(LoginContext context) {
		this.context = context;
	}

	public BillListView getListView() {
		return listView;
	}

	public void setListView(BillListView listView) {
		this.listView = listView;
	}

	public List<AggRecStatementVO> getResultVOList() {
		return resultVOList;
	}

	public void setResultVOList(List<AggRecStatementVO> resultVOList) {
		this.resultVOList = resultVOList;
	}

	public ReportQueryCondVO getQueryCondVO() {
		return queryCondVO;
	}

	public void setQueryCondVO(ReportQueryCondVO queryCondVO) {
		this.queryCondVO = queryCondVO;
	}

	private int getCurrDigit(String fldName) {
		String upperFldName = fldName.toUpperCase();

		if (upperFldName.endsWith("_ORI")) {
			return getMaxDigit(); // ԭ��
		} else if (upperFldName.endsWith("_QUA")) {
			return 2; // ����
		}

		String pk_org = null;
		if (upperFldName.startsWith("GL_") && upperFldName.endsWith("_LOC")) {
			// ȫ�ֱ�λ��
			pk_org = IOrgConst.GLOBEORG;
		} else if (upperFldName.startsWith("GR_") && upperFldName.endsWith("_LOC")) {
			// ���ű�λ��
			pk_org = getQueryCondVO().getPk_group();
		} else if (upperFldName.endsWith("_LOC") && IPubReportConstants.GLOBLE_LOCAL_CURRENCY
				.equals(getQueryCondVO().getLocalCurrencyType())) {
			// ȫ�ֱ�λ��
			pk_org = IOrgConst.GLOBEORG;
		} else if (upperFldName.endsWith("_LOC") && IPubReportConstants.GROUP_LOCAL_CURRENCY
				.equals(getQueryCondVO().getLocalCurrencyType())) {
			// ���ű�λ��
			pk_org = getQueryCondVO().getPk_group();
		} else if (upperFldName.endsWith("_LOC")) {
			// ҵ��Ԫ��λ��
			pk_org = getQueryCondVO().getPk_orgs()[0];
		}

		return Currency.getCurrDigit(CurrencyRateUtilHelper.getInstance().getLocalCurrtypeByOrgID(pk_org));
	}

	private int getMaxDigit() {
		int maxDigit = 0;
		Set<String> currTypeSet = new HashSet<String>();
		if (getResultVOList() != null && getResultVOList().size() > 0) {
			for (AggRecStatementVO vo : getResultVOList()) {
				currTypeSet.add(((RecStatementVO) vo.getParentVO()).getPk_currtype());
			}
		}

		for (String curr : currTypeSet) {
			maxDigit = Math.max(maxDigit, Currency.getCurrDigit(curr));
		}

		return maxDigit;
	}

}

// /:~
