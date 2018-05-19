package nc.ui.arap.comreport;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import javax.swing.JLabel;

import nc.itf.fipub.report.IPubReportConstants;
import nc.itf.fipub.report.IReportQueryCond;
import nc.itf.fipub.reportconstants.IGlAssistantRefConstants;
import nc.pubitf.accperiod.AccountCalendar;
import nc.ui.arap.pub.ArapUiUtil;
import nc.ui.bd.ref.IRefConst;
import nc.ui.fipub.comp.ReportUiUtil;
import nc.ui.fipub.ref.FiRefModelUtil;
import nc.ui.ml.NCLangRes;
import nc.ui.pub.beans.UICheckBox;
import nc.ui.pub.beans.UIComboBox;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.beans.constenum.DefaultConstEnum;
import nc.ui.vorg.ref.DeptVersionDefaultNCRefModel;
import nc.utils.arap.ArapReportResource;
import nc.utils.fipub.FipubReportResource;
import nc.vo.arap.comreport.IArapReportConstants;
import nc.vo.fipub.report.ReportQueryCondVO;
import nc.vo.fipub.utils.RefConstant;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.querytemplate.TemplateInfo;

import org.apache.commons.lang.StringUtils;

import com.ufida.dataset.IContext;

/**
 * Ӧ��/Ӧ�����˵���ѯ�Ի���<br>
 *
 * @author ������<br>
 * @since V60<br>
 */
public class ArapStatementQryDlg extends ArapBaseReportQryDlg {
	private static final long serialVersionUID = 1L;

//	private static final String ASSO_OBJ_COMB = "assoObjComb"; // ��������
//	private static final String ANA_DATE_COMB = "anaDateComb"; // ��������
//	private static final String QRY_SCOPE_COMB = "qryScopeComb"; // ��ѯ��Χ
//	private static final String BILL_STATE_COMB = "billStateComb"; // ����״̬
//	private static final String AREA_REF = "areaRef"; // ��������
//	private static final String CUS_SUP_REF = "cussupRef"; // �ͻ�
//	private static final String DEPT_REF = "deptRef"; // ����
//	private static final String BUSIMAN_REF = "busimanRef"; // ҵ��Ա
//	private static final String CURRENCY_REF = "currencyRef"; // ����
//	private static final String FINANCIAL_ORG_REF  = "financialOrgRef"; // ������֯
//	private static final String BEGIN_TIME_REF = "beginTimeRef"; // ��ʼʱ��
//	private static final String END_TIME_REF = "endTimeRef"; // ����ʱ��
//	private static final String DATELINE_REF = "dateLineRef"; // ��ֹ����
//	private static final String ANA_PATTERN_COMB = "anaPatternComb"; // ���˷�ʽ(������ʽ)

	public ArapStatementQryDlg(Container parent, IContext context, String strNodeCode,
			int iSysCode, TemplateInfo ti, String title) {
		super(parent, context, strNodeCode, iSysCode, ti, title);
	}

	protected List<Component> getComponentList() throws BusinessException {
		List<Component> normalCondCompList = super.getNormalCondCompList();
		if (normalCondCompList.size() > 0) {
			return normalCondCompList;
		}

		UFDate busiDate = ArapUiUtil.getBusisDate();
		// ��ѯ���󣺿ͻ�����Ӧ�̡����š�ҵ��Ա
		normalCondCompList.add(getShowLabel(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common","UC000-0002770")/*@res "��ѯ����"*/));
		UIComboBox assoObjComb = new UIComboBox();
		if (IArapReportConstants.SYS_FLAG_REC == getSysCode()) {
			// ������
			assoObjComb.addItem(new DefaultConstEnum(IPubReportConstants.ASSO_OBJ_CUSTOMER, FipubReportResource.getAssoObjCusLbl()));
		} else if (IArapReportConstants.SYS_FLAG_PAY == getSysCode()) {
			// ���򣺸�
			assoObjComb.addItem(new DefaultConstEnum(IPubReportConstants.ASSO_OBJ_SUPPLIER, FipubReportResource.getAssoObjSupLbl()));
		}
		assoObjComb.addItems(new DefaultConstEnum[] {
				new DefaultConstEnum(IPubReportConstants.ASSO_OBJ_DEPT, FipubReportResource.getAssoObjDeptLbl()),
				new DefaultConstEnum(IPubReportConstants.ASSO_OBJ_BUSIMAN, FipubReportResource.getAssoObjPsnLbl()),
				new DefaultConstEnum(IPubReportConstants.ASSO_OBJ_CUS_SUP, FipubReportResource.getAssoObjCusSupLbl()) });
		
		normalCondCompList.add(assoObjComb);
		addComponent(ArapValidationUtil.ASSO_OBJ_COMB, assoObjComb);

		// �������ڣ��������ڡ�������ڡ���������
		normalCondCompList.add(getShowLabel(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("20060CUFB","120060CUFB0004")/*@res "��������"*/));
		UIComboBox anaDateComb = new UIComboBox();
		anaDateComb.addItems(new DefaultConstEnum[] {
				new DefaultConstEnum(IArapReportConstants.ACC_ANA_DATE_BILLDATE, ArapReportResource.getAccAnaDateBilldateLbl()),
				new DefaultConstEnum(IArapReportConstants.ACC_ANA_DATE_AUDITDATE, ArapReportResource.getAccAnaDateAuditdateLbl()),
				new DefaultConstEnum(IArapReportConstants.ACC_ANA_DATE_RAISEDATE, ArapReportResource.getAccAnaDateRaisedateLbl())
		});
		normalCondCompList.add(anaDateComb);
		addComponent(ArapValidationUtil.ANA_DATE_COMB, anaDateComb);

		// ������ʽ������������� 
		normalCondCompList.add(getShowLabel(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("2006v61008_0","02006v61008-0015")/*@res "������ʽ"*/));
		UIComboBox anaPatternComb = new UIComboBox();
		anaPatternComb.addItems(new DefaultConstEnum[] {
				new DefaultConstEnum(IArapReportConstants.ACC_ANA_PATTERN_FINAL, ArapReportResource.getAccAnaPatternFinalLbl()),
				new DefaultConstEnum(IArapReportConstants.ACC_ANA_PATTERN_POINT, ArapReportResource.getAccAnaPatternPointLbl()) });
		
		//add chenth 20170717  �����µ�633����
		anaPatternComb.setSelectedItem(new DefaultConstEnum(IArapReportConstants.ACC_ANA_PATTERN_FINAL, ArapReportResource.getAccAnaPatternPointLbl()));//����Ĭ��ֵΪ�����
		//add end
		
		anaPatternComb.addItemListener(new ForDatelineListener());
		normalCondCompList.add(anaPatternComb);
		addComponent(ArapValidationUtil.ANA_PATTERN_COMB, anaPatternComb);
		
		normalCondCompList.add(getShowLabel(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common","UC000-0001971")/*@res "��ֹ����"*/));
		UIRefPane dateLineRef = new UIRefPane(RefConstant.REF_NODENAME_CALENDAR); // ����
		dateLineRef.setValueObj(busiDate);
		dateLineRef.setEnabled(false);
		normalCondCompList.add(dateLineRef);
		addComponent(ArapValidationUtil.DATELINE_REF, dateLineRef);
		
		// Ӧ�շ�Χ��ȫ��Ӧ�ա���ȷ��Ӧ�ա�δȷ��Ӧ�� 
		JLabel scopeLabel = null;
		if (IArapReportConstants.SYS_FLAG_REC == getSysCode()) {
			scopeLabel = getShowLabel(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("20060CUFB","120060CUFB0009")/*@res "Ӧ�շ�Χ"*/);
		} else if (IArapReportConstants.SYS_FLAG_PAY == getSysCode()) {
			scopeLabel = getShowLabel(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("2006v61008_0","02006v61008-0058")/*@res "Ӧ����Χ"*/);
		}
		normalCondCompList.add(scopeLabel);
		UIComboBox qryScopeComb = new UIComboBox();
		if (IArapReportConstants.SYS_FLAG_REC == getSysCode()) {
			qryScopeComb.addItems(new DefaultConstEnum[] {
					new DefaultConstEnum(IPubReportConstants.QUERY_SCOPE_ALL_REC, FipubReportResource.getQueryScopeAllRecivableLbl()),
					new DefaultConstEnum(IPubReportConstants.QUERY_SCOPE_CONFIRM_REC, FipubReportResource.getQueryScopeConfirmRecivableLbl()),
					new DefaultConstEnum(IPubReportConstants.QUERY_SCOPE_UNCONFIRM_REC, FipubReportResource.getQueryScopeUnconfirmRecivableLbl())});
		} else if (IArapReportConstants.SYS_FLAG_PAY == getSysCode()) {
			qryScopeComb.addItems(new DefaultConstEnum[] {
					new DefaultConstEnum(IPubReportConstants.QUERY_SCOPE_ALL_PAY, FipubReportResource.getQueryScopeAllPayableLbl()),
					new DefaultConstEnum(IPubReportConstants.QUERY_SCOPE_CONFIRM_PAY, FipubReportResource.getQueryScopeConfirmPayableLbl()),
					new DefaultConstEnum(IPubReportConstants.QUERY_SCOPE_UNCONFIRM_PAY, FipubReportResource.getQueryScopeUnconfirmPayableLbl())});
		}
		normalCondCompList.add(qryScopeComb);
		addComponent(ArapValidationUtil.QRY_SCOPE_COMB, qryScopeComb);
		
		// ����״̬��ȫ�����ѱ��桢����ˡ�����Ч
		normalCondCompList.add(getShowLabel(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common","UC000-0000804")/*@res "����״̬"*/));
		UIComboBox billStateComb = new UIComboBox();
		billStateComb.addItems(new DefaultConstEnum[] {
				new DefaultConstEnum(IPubReportConstants.BILL_STATUS_ALL, FipubReportResource.getBillStatusAllLbl()),
				new DefaultConstEnum(IPubReportConstants.BILL_STATUS_SAVE, FipubReportResource.getBillStatusSaveLbl()),
				new DefaultConstEnum(IPubReportConstants.BILL_STATUS_CONFIRM, FipubReportResource.getBillStatusAuditLbl()),
				new DefaultConstEnum(IPubReportConstants.BILL_STATUS_EFFECT, FipubReportResource.getBillStatusEffectLbl())});
		normalCondCompList.add(billStateComb);
		addComponent(ArapValidationUtil.BILL_STATE_COMB, billStateComb);

		normalCondCompList.add(getShowLabel(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common","UC000-0001235")/*@res "��������"*/));
		UIRefPane areaRef = new UIRefPane(RefConstant.REF_NODENAME_AREACLASS); // ��������
		normalCondCompList.add(areaRef);
		addComponent(ArapValidationUtil.AREA_REF, areaRef);

		JLabel tempLabel = null;
		UIRefPane tempRef = null;
		if (IArapReportConstants.SYS_FLAG_REC == getSysCode()) {
			tempLabel = getShowLabel(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("2006v61008_0","02006v61008-0059")/*@res "�͡�����"*/);
			tempRef = new UIRefPane(RefConstant.REF_NODENAME_CUSTOMER); // �ͻ�����
		} else if (IArapReportConstants.SYS_FLAG_PAY == getSysCode()) {
			tempLabel = getShowLabel(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("2006v61008_0","02006v61008-0060")/*@res "�� Ӧ ��"*/);
			tempRef = new UIRefPane(RefConstant.REF_NODENAME_SUPPLIER); // ��Ӧ�̵���
		}
		normalCondCompList.add(tempLabel);
		//modifier tianjlc ��Ӧ�̺Ϳͻ������������ұ�ṹ
		tempRef.setRefType(IRefConst.GRIDTREE);
		tempRef.setPk_org(ReportUiUtil.getDefaultOrgUnit());
		tempRef.setMultiSelectedEnabled(false);
		tempRef.setDataPowerOperation_code(IPubReportConstants.FI_REPORT_REF_POWER); // ����Ȩ�޿���
		normalCondCompList.add(tempRef);
		addComponent(ArapValidationUtil.CUS_SUP_REF, tempRef);

		normalCondCompList.add(getShowLabel(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("2006v61008_0","02006v61008-0061")/*@res "��������"*/));
		UIRefPane deptRef = new UIRefPane(RefConstant.REF_NODENAME_DEPT); // ����
		deptRef.setRefType(UIRefPane.REFINPUTTYPE_CODE);
		deptRef.setRefModel(new DeptVersionDefaultNCRefModel());
		deptRef.setPk_org(ReportUiUtil.getDefaultOrgUnit());
		deptRef.setMultiSelectedEnabled(false);
		deptRef.setDataPowerOperation_code(IPubReportConstants.FI_REPORT_REF_POWER); // ����Ȩ�޿���
		FiRefModelUtil.setFiRelation(deptRef);
		FiRefModelUtil.setFilter(deptRef);
		normalCondCompList.add(deptRef);
		addComponent(ArapValidationUtil.DEPT_REF, deptRef);

		normalCondCompList.add(getShowLabel(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common","UC000-0000050")/*@res "ҵ �� Ա"*/));
		UIRefPane busimanRef = new UIRefPane(RefConstant.REF_NODENAME_PSN); // ��Ա
		busimanRef.setRefType(UIRefPane.REFINPUTTYPE_CODE);
		busimanRef.setPk_org(ReportUiUtil.getDefaultOrgUnit());
		busimanRef.setMultiSelectedEnabled(false);
		busimanRef.setDataPowerOperation_code(IPubReportConstants.FI_REPORT_REF_POWER); // ����Ȩ�޿���
		FiRefModelUtil.setFiRelation(busimanRef);
		FiRefModelUtil.setFilter(busimanRef);
		normalCondCompList.add(busimanRef);
		addComponent(ArapValidationUtil.BUSIMAN_REF, busimanRef);

		// ����
		normalCondCompList.add(getShowLabel(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("2006v61008_0","02006v61008-0011")/*@res "�ҡ�����"*/));
		UIRefPane currencyRef = new UIRefPane(RefConstant.REF_NODENAME_CURRENCY); // ���ֵ���
		normalCondCompList.add(currencyRef);
		addComponent(ArapValidationUtil.CURRENCY_REF, currencyRef);

		// ������֯
		normalCondCompList.add(getShowLabel(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("arap","1arap0020")/*@res "������֯"*/));
		UIRefPane financialOrgRef = new UIRefPane(RefConstant.REF_NODENAME_FINANCEORG); // ������֯
		String defaultOrgUnit = ReportUiUtil.getDefaultOrgUnit();
		financialOrgRef.setPK(defaultOrgUnit);
		financialOrgRef.setMultiSelectedEnabled(true);
		financialOrgRef.getRefUIConfig().setDisabledDataButtonShow(true);
		financialOrgRef.getRefModel().setFilterPks(getAllPermissionOrgs()); // ����Ȩ�޿���
		financialOrgRef.addValueChangedListener(new OrgChangedListener());
		normalCondCompList.add(financialOrgRef);
		addComponent(ArapValidationUtil.FINANCIAL_ORG_REF , financialOrgRef);


		normalCondCompList.add(getShowLabel(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("2006v61008_0","02006v61008-0062")/*@res "�ա�����"*/));
		UIRefPane beginTimeRef = new UIRefPane(RefConstant.REF_NODENAME_CALENDAR); // ����
		try {
			AccountCalendar calendar = StringUtils.isEmpty(defaultOrgUnit) ? AccountCalendar
					.getInstance() : AccountCalendar.getInstanceByPk_org(defaultOrgUnit);
			calendar.setDate(busiDate);
			String beginTime = calendar.getMonthVO().getBegindate().toLocalString();
			beginTimeRef.setValue(beginTime);
		} catch (Exception e) {
		}
		normalCondCompList.add(beginTimeRef);
		addComponent(ArapValidationUtil.BEGIN_TIME_REF, beginTimeRef);

		normalCondCompList.add(getShowLabel(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common","UC000-0003365")/*@res "��"*/));
		UIRefPane endTimeRef = new UIRefPane(RefConstant.REF_NODENAME_CALENDAR); // ����
		//update for �¼���BDA 20161111
		//swb add ���һ��
		Calendar cal = Calendar.getInstance();
		cal.setTime(busiDate.toDate());
		cal.set(Calendar.DAY_OF_MONTH,1);//����Ϊ1��,��ǰ���ڼ�Ϊ���µ�һ�� 
		cal.add(Calendar.MONTH,1);//������1�� 
		cal.add(Calendar.DAY_OF_MONTH,-1);//���ڵ���һ��,�ȵõ��������һ�� 
		UFDate lastDate = new UFDate(cal.getTime());
		
		endTimeRef.setValueObj(lastDate);
		//update end
		normalCondCompList.add(endTimeRef);
		addComponent(ArapValidationUtil.END_TIME_REF, endTimeRef);
		assoObjComb.addActionListener(new CusSupSelectedListener(assoObjComb, tempRef));
		
		//add for �¼���BDA 20161111
		normalCondCompList.add(getShowLabel(NCLangRes.getInstance().getStrByID("zhtx", "ArapStatementQryDlg-0000")/*���䷽��*/));
		UIRefPane agingRef = new UIRefPane(RefConstant.REF_NODENAME_ACCAGEPLAN);//����
		normalCondCompList.add(agingRef);
		addComponent(IArapReportConstants.AGING_REF, agingRef);
		
		//kuangxw add
		normalCondCompList.add(getShowLabel("        "));
		UICheckBox isShowTotalCode = new UICheckBox(NCLangRes.getInstance().getStrByID("zhtx", "ArapStatementQryDlg-0002")/*�Ƿ���ʾС�� */);
		isShowTotalCode.setSelected(false);
		normalCondCompList.add(isShowTotalCode);
		addComponent(IArapReportConstants.IS_TOTALCODE, isShowTotalCode);
				
		//swb add
		normalCondCompList.add(getShowLabel("        "));
		UICheckBox isShowDJCode = new UICheckBox(NCLangRes.getInstance().getStrByID("zhtx", "ArapStatementQryDlg-0004")/*�Ƿ���ʾ���ݺ� */);
		isShowDJCode.setSelected(true);
		normalCondCompList.add(isShowDJCode);
		addComponent(IArapReportConstants.IS_DJCODE, isShowDJCode);
		
		//add by xudw
		normalCondCompList.add(getShowLabel("        "));
		UICheckBox isFinalBalance = new UICheckBox(NCLangRes.getInstance().getStrByID("zhtx", "ArapStatementQryDlg-0005")/*��ĩ���Ϊ���ұ����޷����Ƿ��ӡ */);
		isFinalBalance.setSelected(false);
		normalCondCompList.add(isFinalBalance);
		addComponent(IArapReportConstants.IS_FINAL_BALANCE, isFinalBalance);
		
		
		normalCondCompList.add(getShowLabel("        "));
		UICheckBox isCounterFee = new UICheckBox(NCLangRes.getInstance().getStrByID("zhtx", "ArapStatementQryDlg-0006")/*�Ƿ���ʾ������ */);
		isCounterFee.setSelected(true);
		normalCondCompList.add(isCounterFee);
		addComponent(IArapReportConstants.IS_COUNTER_FEE, isCounterFee);
		
		//kuangxw add chenth
		normalCondCompList.add(getShowLabel("        "));
		UICheckBox isCanafter = new UICheckBox(NCLangRes.getInstance().getStrByID("zhtx", "ArapStatementQryDlg-0007")/*�Ƿ����ʾδ��������*/);
		isCanafter.setSelected(true);
		normalCondCompList.add(isCanafter);
		addComponent(IArapReportConstants.IS_CANAFTERCODE, isCanafter);
		//add end
		
		return normalCondCompList;
	}

	@Override
	protected List<Component> getExtendCondCompList() throws BusinessException {
		List<Component> extendCondCompList = super.getExtendCondCompList();
		if (extendCondCompList.size() > 0) {
			return extendCondCompList;
		}
		if(IArapReportConstants.SYS_FLAG_REC == getSysCode()){
			UICheckBox isShowMatCustCode = new UICheckBox(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("2006v61008_0","02006v61008-0366")/*@res "�Ƿ���ʾ���Ͽͻ���"*/);
			isShowMatCustCode.setSelected(false);
			extendCondCompList.add(isShowMatCustCode);
			addComponent(IArapReportConstants.IS_MATCUSTCODE, isShowMatCustCode);
		}
		
		//add for �¼���BDA 20161111
//		
//		UICheckBox isShowTotalCode = new UICheckBox(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("2006v61008_0","02006v61008-0366")/*@res "�Ƿ���ʾС��"*/);
//		isShowTotalCode.setSelected(false);
//		extendCondCompList.add(isShowTotalCode);
//		addComponent(IArapReportConstants.IS_CANAFTERCODE, isShowTotalCode);
		//add end
		
		return extendCondCompList;
	}

	/**
	 * ���ܣ����ó��ò�ѯ����VO
	 */
	protected void setQueryCond(ReportQueryCondVO qryCondVO) throws BusinessException {
		UIComboBox tempComboBox = null;
		UIRefPane tempRefPane = null;
		UICheckBox tempCheckBox = null;

		// ��ѯ����
		tempComboBox = (UIComboBox) getComponent(ArapValidationUtil.ASSO_OBJ_COMB);
		qryCondVO.setAssoObj(tempComboBox.getSelectdItemValue().toString());

		// ��������
		tempComboBox = (UIComboBox) getComponent(ArapValidationUtil.ANA_DATE_COMB);
		qryCondVO.setAnaDate((String) tempComboBox.getSelectdItemValue());

		// Ӧ�շ�Χ
		tempComboBox = (UIComboBox) getComponent(ArapValidationUtil.QRY_SCOPE_COMB);
		qryCondVO.setQryScope((String) tempComboBox.getSelectdItemValue());

		// ����״̬
		tempComboBox = (UIComboBox) getComponent(ArapValidationUtil.BILL_STATE_COMB);
		qryCondVO.setBillState((String) tempComboBox.getSelectdItemValue());

		// ��������
		qryCondVO.setAreapk(((UIRefPane) getComponent(ArapValidationUtil.AREA_REF)).getRefPK());

		// �ͻ�&��Ӧ��
		if (IArapReportConstants.SYS_FLAG_REC == getSysCode()) {
			qryCondVO.setCustomer(((UIRefPane) getComponent(ArapValidationUtil.CUS_SUP_REF)).getRefPK());
		} else if (IArapReportConstants.SYS_FLAG_PAY == getSysCode()) {
			qryCondVO.setSupplier(((UIRefPane) getComponent(ArapValidationUtil.CUS_SUP_REF)).getRefPK());
		}

		// ����
		qryCondVO.setDept(((UIRefPane) getComponent(ArapValidationUtil.DEPT_REF)).getRefPK());

		// ҵ��Ա
		qryCondVO.setPsndoc(((UIRefPane) getComponent(ArapValidationUtil.BUSIMAN_REF)).getRefPK());

		// ����
		qryCondVO.setPk_currency(((UIRefPane) getComponent(ArapValidationUtil.CURRENCY_REF)).getRefPK());

		// ��ʼ����
		UFDate busiDate = ArapUiUtil.getBusisDate();
		tempRefPane = (UIRefPane) getComponent(ArapValidationUtil.BEGIN_TIME_REF);
		Object beginDate = tempRefPane.getValueObj();
		if (beginDate == null) {
			beginDate = busiDate.toLocalString();
		} else if (beginDate instanceof UFDate) {
			beginDate = ((UFDate) beginDate).toLocalString();
		} else {
			beginDate = beginDate.toString();
		}
		qryCondVO.setBeginDate(new UFDate(beginDate.toString(), true));

		// ��������
		tempRefPane = (UIRefPane) getComponent(ArapValidationUtil.END_TIME_REF);
		Object endDate = tempRefPane.getValueObj();
		if (endDate == null) {
			endDate = busiDate.toLocalString();
		} else if (endDate instanceof UFDate) {
			endDate = ((UFDate) endDate).toLocalString();
		} else {
			endDate = endDate.toString();
		}
		qryCondVO.setEndDate(new UFDate(endDate.toString(), false));

		// ��ֹ����
		tempRefPane = (UIRefPane) getComponent(ArapValidationUtil.DATELINE_REF);
		Object dateLine = tempRefPane.getValueObj();
		if (dateLine == null) {
			dateLine = busiDate.toLocalString();
		} else if (endDate instanceof UFDate) {
			dateLine = ((UFDate) dateLine).toLocalString();
		} else {
			dateLine = dateLine.toString();
		}
		qryCondVO.setDateline(new UFDate(dateLine.toString(), false));
		
		// ������ʽ
		tempComboBox = (UIComboBox) getComponent(ArapValidationUtil.ANA_PATTERN_COMB);
		qryCondVO.setAnaPattern((String) tempComboBox.getSelectdItemValue());
		
		qryCondVO.setSyscode(getSysCode()); // ϵͳ��ʶ
		qryCondVO.setOwnModule(IPubReportConstants.MODULE_ARAP);
		qryCondVO.setModuleId(getSysCode() == IArapReportConstants.SYS_FLAG_REC ? IArapReportConstants.MODULEID_AR
				: (getSysCode() == IArapReportConstants.SYS_FLAG_PAY ? IArapReportConstants.MODULEID_AP: null));
		qryCondVO.getUserObject().put("statementzone", TimeZone.getDefault());
		
		// �Ƿ���ʾ���Ͽͻ���
		if(IArapReportConstants.SYS_FLAG_REC == getSysCode()){
			tempCheckBox = (UICheckBox)getComponent(IArapReportConstants.IS_MATCUSTCODE);
			qryCondVO.getUserObject().put(IArapReportConstants.IS_MATCUSTCODE, UFBoolean.valueOf(tempCheckBox.isSelected()));
		}
		
		//add for �¼���BDA 20161111
		//����
		qryCondVO.getUserObject().put(IArapReportConstants.AGING_REF,((UIRefPane) getComponent(IArapReportConstants.AGING_REF)).getRefPK());
		
		// �Ƿ���ʾС��
		//if(IArapReportConstants.SYS_FLAG_REC == getSysCode()){
//			tempCheckBox = (UICheckBox)getComponent(IArapReportConstants.IS_TOTALCODE);
//			qryCondVO.getUserObject().put(IArapReportConstants.IS_TOTALCODE, new UFBoolean(tempCheckBox.isSelected()));
	
		//}
			
		
		//alvin add
		tempCheckBox = (UICheckBox)getComponent(IArapReportConstants.IS_TOTALCODE);
		qryCondVO.getUserObject().put(IArapReportConstants.IS_TOTALCODE, new UFBoolean(tempCheckBox.isSelected()));
		
		//alvin add �Ƿ���ʾ���� chenth
		tempCheckBox = (UICheckBox)getComponent(IArapReportConstants.IS_CANAFTERCODE);
		qryCondVO.getUserObject().put(IArapReportConstants.IS_CANAFTERCODE, new UFBoolean(tempCheckBox.isSelected()));
		
		//swb add
		tempCheckBox = (UICheckBox)getComponent(IArapReportConstants.IS_DJCODE);
		qryCondVO.getUserObject().put(IArapReportConstants.IS_DJCODE, new UFBoolean(tempCheckBox.isSelected()));
		
		//add by xudw
		tempCheckBox = (UICheckBox)getComponent(IArapReportConstants.IS_FINAL_BALANCE);
		qryCondVO.getUserObject().put(IArapReportConstants.IS_FINAL_BALANCE, new UFBoolean(tempCheckBox.isSelected()));
		
		//add by xudw
		tempCheckBox = (UICheckBox)getComponent(IArapReportConstants.IS_COUNTER_FEE);
		qryCondVO.getUserObject().put(IArapReportConstants.IS_COUNTER_FEE, new UFBoolean(tempCheckBox.isSelected()));
		//add end
		
	}

	

	protected void resetUserReportQueryCondVO(IReportQueryCond queryCond) {
		ReportQueryCondVO queryCondVO = (ReportQueryCondVO) queryCond;

		((UIRefPane) getComponent(ArapValidationUtil.BEGIN_TIME_REF)).setValue(queryCondVO.getBeginDate().toLocalString()); // ��ʼʱ��
		((UIRefPane) getComponent(ArapValidationUtil.END_TIME_REF)).setValue(queryCondVO.getEndDate().toLocalString()); // ����ʱ��
		((UIComboBox) getComponent(ArapValidationUtil.ASSO_OBJ_COMB)).setSelectedItem(Integer.valueOf(queryCondVO.getAssoObj())); // ��������
		((UIComboBox) getComponent(ArapValidationUtil.QRY_SCOPE_COMB)).setSelectedItem(queryCondVO.getQryScope()); // ��ѯ��Χ
		((UIComboBox) getComponent(ArapValidationUtil.BILL_STATE_COMB)).setSelectedItem(queryCondVO.getBillState()); // ����״̬
		((UIComboBox) getComponent(ArapValidationUtil.ANA_DATE_COMB)).setSelectedItem(queryCondVO.getAnaDate()); // ��������
		((UIRefPane) getComponent(ArapValidationUtil.CURRENCY_REF)).setPK(queryCondVO.getPk_currency()); // ����
		((UIRefPane) getComponent(ArapValidationUtil.FINANCIAL_ORG_REF )).setPKs(queryCondVO.getPk_orgs()); // ������֯
		((UIRefPane) getComponent(ArapValidationUtil.AREA_REF)).setPK(queryCondVO.getAreapk()); // ��������
		if (IArapReportConstants.SYS_FLAG_REC == getSysCode()) {
			((UIRefPane) getComponent(ArapValidationUtil.CUS_SUP_REF)).setPK(queryCondVO.getCustomer()); // �ͻ�
		} else if (IArapReportConstants.SYS_FLAG_PAY == getSysCode()) {
			((UIRefPane) getComponent(ArapValidationUtil.CUS_SUP_REF)).setPK(queryCondVO.getSupplier()); // ��Ӧ��
		}
		((UIRefPane) getComponent(ArapValidationUtil.DEPT_REF)).setPK(queryCondVO.getDept()); // ����
		((UIRefPane) getComponent(ArapValidationUtil.BUSIMAN_REF)).setPK(queryCondVO.getPsndoc()); // ҵ��Ա

		// =================================
		setPk_org(queryCondVO.getPk_orgs());
		// =================================
	}

	/**
	 * ��д���෽����ִ�б�Ҫ��ҵ��У��
	 */
	protected String doBusiCheck() {
		// ��ִ�и���У��
		String errMsg = super.doBusiCheck();
		if (!StringUtils.isEmpty(errMsg)) {
			return errMsg;
		}

		try {
			ReportQueryCondVO queryCondVO = (ReportQueryCondVO) getReportQueryCondVO();
			if (IArapReportConstants.ACC_ANA_DATE_AUDITDATE.equals(queryCondVO.getAnaDate())
					&& (IPubReportConstants.BILL_STATUS_ALL.equals(queryCondVO.getBillState()) || IPubReportConstants.BILL_STATUS_SAVE
							.equals(queryCondVO.getBillState()))) {
				return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("2006v61008_0","02006v61008-0063")/*@res "������״̬Ϊ��ȫ�������ѱ��桱ʱ���������ڲ�����ѡ���������ڡ���"*/;
			}
		} catch (BusinessException e) {
			errMsg = e.getMessage();
		}

		return StringUtils.isEmpty(errMsg)?null:errMsg;
	}

	public void setPk_org(String[] pkOrgs) {
		super.setPk_org(pkOrgs);

		String pk_org = null;
		if (pkOrgs != null && pkOrgs.length > 0) {
			pk_org = pkOrgs[0];
		}
		((UIRefPane) (getComponent(ArapValidationUtil.AREA_REF))).setPk_org(pk_org);
		((UIRefPane) (getComponent(ArapValidationUtil.CUS_SUP_REF))).setPk_org(pk_org);
		((UIRefPane) (getComponent(ArapValidationUtil.DEPT_REF))).setPk_org(pk_org);
		((UIRefPane) (getComponent(ArapValidationUtil.BUSIMAN_REF))).setPk_org(pk_org);
		((UIRefPane) (getComponent(ArapValidationUtil.CURRENCY_REF))).setPk_org(pk_org);
		FiRefModelUtil.setFiRelation(((UIRefPane) (getComponent(ArapValidationUtil.DEPT_REF))));
		FiRefModelUtil.setFilter(((UIRefPane) (getComponent(ArapValidationUtil.DEPT_REF))));
		FiRefModelUtil.setFiRelation(((UIRefPane) (getComponent(ArapValidationUtil.BUSIMAN_REF))));
		FiRefModelUtil.setFilter(((UIRefPane) (getComponent(ArapValidationUtil.BUSIMAN_REF))));
	}

	protected List<Integer> getPanelHeightList() {
		List<Integer> heightList = new ArrayList<Integer>();
		//update for �¼���BDA 20161111
		heightList.add(360);
		heightList.add(0);
		heightList.add(320);
		return heightList;
	}

	protected boolean isShowLocalCurrencySelect() {
		return false;
	}

	protected String[] getPk_org() {
		return ((UIRefPane) getComponent(ArapValidationUtil.FINANCIAL_ORG_REF )).getRefPKs();
	}

	public class CusSupSelectedListener implements ActionListener {

		UIComboBox assoObjComb;
		UIRefPane tempRef;

		public CusSupSelectedListener(UIComboBox assoObjComb, UIRefPane tempRef) {
			this.assoObjComb = assoObjComb;
			this.tempRef = tempRef;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if(this.assoObjComb.getSelectdItemValue()!=null){
				if (this.assoObjComb.getSelectdItemValue().equals(IPubReportConstants.ASSO_OBJ_CUS_SUP)) {
					tempRef.setRefNodeName(IGlAssistantRefConstants.CUST_SUP_REF);
				} else if (this.assoObjComb.getSelectdItemValue().equals(IPubReportConstants.ASSO_OBJ_CUSTOMER)
						|| this.assoObjComb.getSelectdItemValue().equals(IPubReportConstants.ASSO_OBJ_SUPPLIER)) {
					tempRef.setRefNodeName(RefConstant.REF_NODENAME_CUSTOMER);
				}
			}
		}

	}

	class ForDatelineListener implements ItemListener {
		public void itemStateChanged(ItemEvent e) {
			Component comp = getComponent(ArapValidationUtil.DATELINE_REF); // ��ֹ����
			String pattern = (String) ((DefaultConstEnum) e.getItem()).getValue();
			if (e.getStateChange() == ItemEvent.SELECTED &&
					(IArapReportConstants.ACC_ANA_PATTERN_POINT.equals(pattern) || IArapReportConstants.ACC_ANA_TYP_DEADLINE.equals(pattern))) {
				comp.setEnabled(true);
			} else if (e.getStateChange() == ItemEvent.SELECTED
					&& (IArapReportConstants.ACC_ANA_PATTERN_FINAL.equals(pattern) || IArapReportConstants.ACC_ANA_TYP_SETTLE
							.equals(pattern))) {
				comp.setEnabled(false);
			}
		}
	}
	
}

// /:~