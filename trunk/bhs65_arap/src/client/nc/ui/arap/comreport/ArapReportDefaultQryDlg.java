package nc.ui.arap.comreport;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import nc.itf.fi.pub.Currency;
import nc.itf.fipub.report.IPubReportConstants;
import nc.itf.fipub.report.IReportQueryCond;
import nc.itf.org.IOrgConstBasic;
import nc.pubitf.accperiod.AccountCalendar;
import nc.ui.arap.pub.ArapUiUtil;
import nc.ui.bd.ref.model.AccPeriodDefaultRefModel;
import nc.ui.fipub.comp.ReportUiUtil;
import nc.ui.pub.beans.UICheckBox;
import nc.ui.pub.beans.UIComboBox;
import nc.ui.pub.beans.UILabel;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.beans.ValueChangedEvent;
import nc.ui.pub.beans.constenum.DefaultConstEnum;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.utils.fipub.FipubReportResource;
import nc.vo.arap.comreport.IArapReportConstants;
import nc.vo.fipub.exception.ExceptionHandler;
import nc.vo.fipub.report.QryObj;
import nc.vo.fipub.report.ReportInitializeVO;
import nc.vo.fipub.report.ReportQueryCondVO;
import nc.vo.fipub.utils.RefConstant;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.querytemplate.TemplateInfo;
import nc.vo.querytemplate.queryscheme.QuerySchemeObject;
import nc.vo.querytemplate.queryscheme.QuerySchemeVO;
import nc.vo.uif2.LoginContext;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

import com.ufida.dataset.IContext;
import com.ufida.report.anareport.FreeReportFucletContextKey;

/**
 * �ո������ʱ��ѯ�Ի���<br>
 *
 * @author ������<br>
 * @since V60 2010-12-15<br>
 */
public class ArapReportDefaultQryDlg extends ArapBaseReportQryDlg {
	/**
	 * ���л��汾ID<br>
	 */
	private static final long serialVersionUID = 1L;

//	private static final String ASSO_OBJ_COMB = "assoObjComb"; // ��������
//	private static final String QRY_MODE_COMB = "qryModeComb"; // ��ѯ��ʽ
//	private static final String QRY_DIRECT_COMB = "qryDirectComb"; // ��ѯ����
//	private static final String QRY_SCOPE_COMB = "qryScopeComb"; // ��ѯ��Χ
//	private static final String BILL_STATE_COMB = "billStateComb"; // ����״̬
//	private static final String BEGIN_TIME_REF = "beginTimeRef"; // ��ʼʱ��
//	private static final String AEND_TIME_REF = "endTimeRef"; // ����ʱ��
//	private static final String CURRENCY_REF = "currencyRef"; // ����
//	private static final String FINANCIAL_ORG_REF = "financialOrgRef"; // ������֯

	private static final String TIME_TYPE_LABEL = "timeTypeLabel";

	private static final List<String> diffReport = new ArrayList<String>();
	static {
		diffReport.add(IPubReportConstants.GENERAL_REPORT);
	}

	public ArapReportDefaultQryDlg(Container parent, IContext context,
			String strNodeCode, int iSysCode, TemplateInfo ti, String title) {
		super(parent, context, strNodeCode, iSysCode, ti, title);
	}

	protected List<Component> getComponentList() throws BusinessException {
		List<Component> normalCondCompList = super.getNormalCondCompList();
		if (normalCondCompList.size() > 0) {
			return normalCondCompList;
		}

		// ��ȡ�����ʼ�������Ļ���
		ReportInitializeVO reportInitializeVO = (ReportInitializeVO) getReportInitializeVO().getParentVO();
		// ��ȡ��������
		String reportType = reportInitializeVO.getReporttype();
		// ��ȡ�ո�����
		String rec_pay_direct = getRecPayDirect();

		// ��������
		normalCondCompList.add(getShowLabel(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common","UC000-0001935")/*@res "��������"*/));
		UIComboBox assoObjComb = new UIComboBox();
		if (IPubReportConstants.DIRECT_REC.equals(rec_pay_direct)) {
			// ������
			assoObjComb.addItem(new DefaultConstEnum(IPubReportConstants.ASSO_OBJ_CUSTOMER, FipubReportResource.getAssoObjCusLbl()));
		} else {
			// ���򣺸�
			assoObjComb.addItem(new DefaultConstEnum(IPubReportConstants.ASSO_OBJ_SUPPLIER, FipubReportResource.getAssoObjSupLbl()));
		}
		assoObjComb.addItems(new DefaultConstEnum[] {
				new DefaultConstEnum(IPubReportConstants.ASSO_OBJ_DEPT, FipubReportResource.getAssoObjDeptLbl()),
				new DefaultConstEnum(IPubReportConstants.ASSO_OBJ_BUSIMAN, FipubReportResource.getAssoObjPsnLbl()),
				new DefaultConstEnum(IPubReportConstants.ASSO_OBJ_CUS_SUP, FipubReportResource.getAssoObjCusSupLbl()) });
		normalCondCompList.add(assoObjComb);
		addComponent(ArapValidationUtil.ASSO_OBJ_COMB, assoObjComb);

		if (!diffReport.contains(reportType)) {
			// ��ѯ��ʽ
			normalCondCompList.add(getShowLabel(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common","UC000-0002777")/*@res "��ѯ��ʽ"*/));
			UIComboBox qryModeComb = new UIComboBox();
			qryModeComb.addItems(new DefaultConstEnum[] {
					new DefaultConstEnum(IPubReportConstants.QUERY_MODE_MONTH, FipubReportResource.getQueryModeMonthLbl()),
					new DefaultConstEnum(IPubReportConstants.QUERY_MODE_DATE, FipubReportResource.getQueryModeDateLbl()) });
			qryModeComb.addItemListener(new ComboBoxItemListener());
			normalCondCompList.add(qryModeComb);
			addComponent(ArapValidationUtil.QRY_MODE_COMB, qryModeComb);
		}

		// ��ѯ����
		normalCondCompList.add(getShowLabel(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("2006agr","12006agr0004")/*@res "��ѯ����"*/));
		UIComboBox qryDirectComb = new UIComboBox();
		if (IPubReportConstants.DIRECT_REC.equals(rec_pay_direct)) {
			// ������
			qryDirectComb.addItems(new DefaultConstEnum[] {
					new DefaultConstEnum(IPubReportConstants.QUERY_DIRECT_REC_GAT, FipubReportResource.getQueryDirRecivableGatLbl()),
					new DefaultConstEnum(IPubReportConstants.QUERY_DIRECT_REC, FipubReportResource.getQueryDirRecivableLbl()) });
		} else {
			// ���򣺸�
			qryDirectComb.addItems(new DefaultConstEnum[] {
					new DefaultConstEnum(IPubReportConstants.QUERY_DIRECT_PAY_GAT, FipubReportResource.getQueryDirPayablePayLbl()),
					new DefaultConstEnum(IPubReportConstants.QUERY_DIRECT_PAY, FipubReportResource.getQueryDirPayableLbl()) });
		}
		qryDirectComb.addItem(new DefaultConstEnum(IPubReportConstants.QUERY_DIRECT_ALL, FipubReportResource.getQueryDirAllLbl()));
		normalCondCompList.add(qryDirectComb);
		addComponent(ArapValidationUtil.QRY_DIRECT_COMB, qryDirectComb);

		// ��ѯ��Χ
		normalCondCompList.add(getShowLabel(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("2006agr","12006agr0003")/*@res "��ѯ��Χ"*/));
		UIComboBox qryScopeComb = new UIComboBox();
		if (IPubReportConstants.DIRECT_REC.equals(rec_pay_direct)) {
			// ������
			qryScopeComb.addItems(new DefaultConstEnum[] {
					new DefaultConstEnum(IPubReportConstants.QUERY_SCOPE_ALL_REC, FipubReportResource.getQueryScopeAllRecivableLbl()),
					new DefaultConstEnum(IPubReportConstants.QUERY_SCOPE_CONFIRM_REC, FipubReportResource.getQueryScopeConfirmRecivableLbl()),
					new DefaultConstEnum(IPubReportConstants.QUERY_SCOPE_UNCONFIRM_REC, FipubReportResource.getQueryScopeUnconfirmRecivableLbl())});
		} else {
			// ���򣺸�
			qryScopeComb.addItems(new DefaultConstEnum[] {
					new DefaultConstEnum(IPubReportConstants.QUERY_SCOPE_ALL_PAY, FipubReportResource.getQueryScopeAllPayableLbl()),
					new DefaultConstEnum(IPubReportConstants.QUERY_SCOPE_CONFIRM_PAY, FipubReportResource.getQueryScopeConfirmPayableLbl()),
					new DefaultConstEnum(IPubReportConstants.QUERY_SCOPE_UNCONFIRM_PAY, FipubReportResource.getQueryScopeUnconfirmPayableLbl())});
		}
		normalCondCompList.add(qryScopeComb);
		addComponent(ArapValidationUtil.QRY_SCOPE_COMB, qryScopeComb);

		if (diffReport.contains(reportType)) { // ���˱�(Ϊ�˽����Ѻ��ԣ������ַ���ǰ��)
			// ����
			normalCondCompList.add(getShowLabel(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("2006v61008_0","02006v61008-0011")/*@res "�ҡ�����"*/));
			UIRefPane currencyRef = new UIRefPane(RefConstant.REF_NODENAME_CURRENCY); // ���ֵ���
			normalCondCompList.add(currencyRef);
			addComponent(ArapValidationUtil.CURRENCY_REF, currencyRef);
		}

		// ��ʼʱ��
		UILabel timeTypeLabel = getShowLabel(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("2006v61008_0","02006v61008-0043")/*@res "�¡�����"*/);
		normalCondCompList.add(timeTypeLabel);
		addComponent(TIME_TYPE_LABEL, timeTypeLabel);
		UIRefPane beginTimeRef = new UIRefPane(RefConstant.REF_NODENAME_ACCPERIOD); // ����ڼ䵵��
		normalCondCompList.add(beginTimeRef);
		addComponent(ArapValidationUtil.BEGIN_TIME_REF, beginTimeRef);

		// ����ʱ��
		normalCondCompList.add(getShowLabel(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common","UC000-0003365")/*@res "��"*/));
		UIRefPane endTimeRef = new UIRefPane(RefConstant.REF_NODENAME_ACCPERIOD); // ����ڼ䵵��
		normalCondCompList.add(endTimeRef);
		addComponent(ArapValidationUtil.END_TIME_REF, endTimeRef);

		if (!diffReport.contains(reportType)) { // ������ϸ��(Ϊ�˽����Ѻ��ԣ������ַ��ں���)
			// ����
			normalCondCompList.add(getShowLabel(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("2006v61008_0","02006v61008-0011")/*@res "�ҡ�����"*/));
			UIRefPane currencyRef = new UIRefPane(RefConstant.REF_NODENAME_CURRENCY); // ���ֵ���
			normalCondCompList.add(currencyRef);
			addComponent(ArapValidationUtil.CURRENCY_REF, currencyRef);
		}

		// ����״̬
		normalCondCompList.add(getShowLabel(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common","UC000-0000804")/*@res "����״̬"*/));
		UIComboBox billStateComb = new UIComboBox(); 
		billStateComb.addItems(new DefaultConstEnum[] {
				new DefaultConstEnum(IPubReportConstants.BILL_STATUS_ALL, FipubReportResource.getBillStatusAllLbl()),
				new DefaultConstEnum(IPubReportConstants.BILL_STATUS_SAVE, FipubReportResource.getBillStatusSaveLbl()),
				new DefaultConstEnum(IPubReportConstants.BILL_STATUS_CONFIRM, FipubReportResource.getBillStatusAuditLbl()),
				new DefaultConstEnum(IPubReportConstants.BILL_STATUS_EFFECT, FipubReportResource.getBillStatusEffectLbl())});
		normalCondCompList.add(billStateComb);
		addComponent(ArapValidationUtil.BILL_STATE_COMB, billStateComb);

		// ������֯
		normalCondCompList.add(getShowLabel(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("arap","1arap0020")/*@res "������֯"*/));
		UIRefPane financialOrgRef = new UIRefPane(RefConstant.REF_NODENAME_FINANCEORG); // ������֯
		financialOrgRef.getRefUIConfig().setDisabledDataButtonShow(true);
		financialOrgRef.setPK(ReportUiUtil.getDefaultOrgUnit());
		financialOrgRef.setMultiSelectedEnabled(true);
		String[] permissionOrgs = ArapUiUtil.getPermissionOrgs(((LoginContext) getContext().getAttribute(FreeReportFucletContextKey.PRIVATE_CONTEXT)));
		//editor tianjlc �ڵ����֯����Ϊȫ��ʱ������������Ȩ��
		if(!(permissionOrgs.length==1&&permissionOrgs[0].equals(IOrgConstBasic.GLOBEORG))){
			financialOrgRef.getRefModel().setFilterPks(permissionOrgs); // ����Ȩ�޿���
		}
		financialOrgRef.addValueChangedListener(new OrgChangedListener());
		normalCondCompList.add(financialOrgRef);
		addComponent(ArapValidationUtil.FINANCIAL_ORG_REF, financialOrgRef);

		normalCondCompList.add(getShowLabel(FipubReportResource.getLocalCurrencyTpyLbl()));
		UIComboBox locCurrTypeComb = new UIComboBox();
		locCurrTypeComb.addItem(new DefaultConstEnum(IPubReportConstants.ORG_LOCAL_CURRENCY, FipubReportResource.getOrgLocalCurrencyLbl()));
		if (Currency.isStartGroupCurr(ReportUiUtil.getPK_group())) {
			locCurrTypeComb.addItem(new DefaultConstEnum(IPubReportConstants.GROUP_LOCAL_CURRENCY, FipubReportResource.getGroupLocalCurrencyLbl()));
		}
		if (Currency.isStartGlobalCurr(null)) {
			locCurrTypeComb.addItem(new DefaultConstEnum(IPubReportConstants.GLOBLE_LOCAL_CURRENCY, FipubReportResource.getGlobleLocalCurrencyLbl()));
		}
		normalCondCompList.add(locCurrTypeComb);
		addComponent(LOCAL_CURRENCY_TYPE_COMB, locCurrTypeComb);

		setQueryPeriod(IPubReportConstants.QUERY_MODE_MONTH, null);

		return normalCondCompList;
	}

	protected List<Component> getExtendCondCompList() throws BusinessException {
		List<Component> extendCondCompList = super.getExtendCondCompList();
		if (extendCondCompList.size() > 0) {
			return extendCondCompList;
		}

		// ��ȡ�����ʼ�������Ļ���
		ReportInitializeVO reportInitializeVO = (ReportInitializeVO) getReportInitializeVO().getParentVO();
		// ��ȡ��������
		String reportType = reportInitializeVO.getReporttype();

		if (IPubReportConstants.DETAIL_REPORT.equals(reportType)) {
			extendCondCompList.add(new UILabel());
			UICheckBox showDateTotalChk = new UICheckBox(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("2006v61008_0","02006v61008-0044")/*@res "��ʾ��С��"*/);
			showDateTotalChk.setSelected(true);
			extendCondCompList.add(showDateTotalChk);
			addComponent(IArapReportConstants.KEY_SHOW_DATE_TOTAL, showDateTotalChk);
			UICheckBox notshowBackEstiChk = new UICheckBox(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("2006v61008_0","02006v61008-0045")/*@res "�ѻس���ݹ����ݲ���ʾ"*/);
			notshowBackEstiChk.setSelected(false);
			extendCondCompList.add(notshowBackEstiChk);
			addComponent(IArapReportConstants.KEY_NOTSHOW_BACKESTI, notshowBackEstiChk);
		} else if (IPubReportConstants.BALANCE_REPORT.equals(reportType)) {
			UICheckBox noOccChk = new UICheckBox(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("2006v61008_0","02006v61008-0046")/*@res "�޷�������ʾ"*/);
			noOccChk.setSelected(false);
			extendCondCompList.add(new UILabel());
			extendCondCompList.add(noOccChk);
			addComponent(IArapReportConstants.KEY_NOOCC_NOSHOW, noOccChk);
			UICheckBox noBalOccChk = new UICheckBox(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("2006v61008_0","02006v61008-0047")/*@res "������޷�������ʾ"*/);
			noBalOccChk.setSelected(false);
			extendCondCompList.add(noBalOccChk);
			addComponent(IArapReportConstants.KEY_NOBAL_NOOCC_NOSHOW, noBalOccChk);
		}

		return extendCondCompList;
	}

	/**
	 * ��д���෽����ִ�б�Ҫ��ҵ��У��
	 */
	protected String doBusiCheck(){
		// ִ�и���У��
		String errMsg = super.doBusiCheck();
		if (!StringUtils.isEmpty(errMsg)) {
			return errMsg;
		}

		try {

			// ��ȡ�����ʼ�������Ļ���
			ReportInitializeVO reportInitializeVO = (ReportInitializeVO) getReportInitializeVO().getParentVO();
			// ��ȡ��������
			String reportType = reportInitializeVO.getReporttype();
			// ��ȡ�����ѯ����VO
			ReportQueryCondVO queryCondVO = (ReportQueryCondVO) getReportQueryCondVO();

			UFDate beginDate = queryCondVO.getBeginDate();
			UFDate endDate = queryCondVO.getEndDate();

			// ���ѯ���ڲ���Ϊ��
			if (beginDate == null || endDate == null) {
				return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("2006v61008_0","02006v61008-0036")/*@res "��ѯ���ڲ���Ϊ�գ�"*/;
			}

			// ����ϸ�˲�ѯ��Ҫ���ѯ�ڼ䲻����������
			if (IPubReportConstants.DETAIL_REPORT.equals(reportType)) {
				int days = UFDate.getDaysBetween(beginDate, endDate);
				boolean hasConcreteQryObjValue = false;
				for (QryObj qryObj : queryCondVO.getQryObjs()) {
					if (qryObj.getValues().size() > 0) {
						hasConcreteQryObjValue = true;
						break;
					}
				}

				if (hasConcreteQryObjValue == false && days > 183) {
					return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("2006v61008_0","02006v61008-0048")/*@res "�ڼ���������£���ѡ���ѯ�������ֵ��ѯ��"*/;
				}
			}

			// ���ѯ��ʼʱ�䲻������ϵͳ����ʱ��
			return checkSystemStartDate(queryCondVO.getPk_orgs(), queryCondVO.getModuleId(), beginDate);
		} catch (BusinessException e) {
			errMsg = e.getMessage();
		}

		return errMsg;
	}

	/**
	 * ��ѯ��ʽ�仯������<br>
	 *
	 * @author ������<br>
	 * @since V60<br>
	 */
	class ComboBoxItemListener implements ItemListener {
		public void itemStateChanged(ItemEvent e) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				setQueryPeriod((String) ((DefaultConstEnum) e.getItem()).getValue(), null);
			}
		}
	}

	protected void handleOrgChangeEvent(ValueChangedEvent event) throws BusinessException {
		super.handleOrgChangeEvent(event);
		ReportQueryCondVO queryCondVO = (ReportQueryCondVO) getReportQueryCondVO();
		setQueryPeriod(queryCondVO.getQryMode(), queryCondVO);
	}

	/**
	 * ���ò�ѯ�ڼ䷶Χ<br>
	 *
	 * @param qryMode ��ѯ��ʽ<br>
	 * @author ������<br>
	 */
	protected void setQueryPeriod(String qryMode, IReportQueryCond queryCondVO) {
		UFDate currBusiDate = ReportUiUtil.getBusiDate();
		UFDate beginDate = queryCondVO == null || queryCondVO.getBeginDate() == null ? currBusiDate : queryCondVO.getBeginDate();
		UFDate endDate = queryCondVO == null || queryCondVO.getEndDate() == null ? currBusiDate : queryCondVO.getEndDate();
		// ��ȡ����ؼ�
		UILabel tempLabel = (UILabel) getComponent(TIME_TYPE_LABEL);
		UIRefPane tempBeginRefPane = (UIRefPane) getComponent(ArapValidationUtil.BEGIN_TIME_REF);
		UIRefPane tempEndRefPane = (UIRefPane) getComponent(ArapValidationUtil.END_TIME_REF);
		if (IPubReportConstants.QUERY_MODE_MONTH.equals(qryMode)) {
			tempLabel.setText(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("2006v61008_0","02006v61008-0043")/*@res "�¡�����"*/);
			tempBeginRefPane.setRefNodeName(RefConstant.REF_NODENAME_ACCPERIOD); // ����ڼ䵵��
			tempEndRefPane.setRefNodeName(RefConstant.REF_NODENAME_ACCPERIOD); // ����ڼ䵵��

			String[] pk_orgs = getPk_org();
			try {
				if (!ArrayUtils.isEmpty(pk_orgs)) {
					AccountCalendar calendar = AccountCalendar.getInstanceByPk_org(pk_orgs[0]);
					if(calendar.getYearVO() != null){
						String defaultpk_accperiodscheme = calendar.getYearVO().getPk_accperiodscheme();
						((AccPeriodDefaultRefModel) tempBeginRefPane.getRefModel()).setDefaultpk_accperiodscheme(defaultpk_accperiodscheme);
						((AccPeriodDefaultRefModel) tempEndRefPane.getRefModel()).setDefaultpk_accperiodscheme(defaultpk_accperiodscheme);
					}
					calendar.setDate(beginDate);
					String pk_accperiodmonthbegin = calendar.getMonthVO().getPk_accperiodmonth();
					calendar.setDate(endDate);
					String pk_accperiodmonthend = calendar.getMonthVO().getPk_accperiodmonth();

					tempBeginRefPane.setPK(pk_accperiodmonthbegin);
					tempEndRefPane.setPK(pk_accperiodmonthend);
				}
			} catch (BusinessException e) {
				ExceptionHandler.consume(e);
			}
		} else {
			tempLabel.setText(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("2006v61008_0","02006v61008-0049")/*@res "�ա��� ��"*/);

			tempBeginRefPane.setRefNodeName(RefConstant.REF_NODENAME_CALENDAR); // ����
			tempEndRefPane.setRefNodeName(RefConstant.REF_NODENAME_CALENDAR); // ����

			tempBeginRefPane.setValueObj(beginDate); // ��ʼʱ��
			tempEndRefPane.setValueObj(endDate); // ����ʱ��
		}

		tempLabel.repaint();
	}
	
	/**
	 * ���ܣ����ó��ò�ѯ����VO
	 */
	protected void setQueryCond(ReportQueryCondVO qryCondVO) throws BusinessException {
		UIComboBox tempComboBox = null;

		// ��������
		qryCondVO.setAssoObj(((UIComboBox) getComponent(ArapValidationUtil.ASSO_OBJ_COMB)).getSelectdItemValue().toString());

		// ��ѯ��ʽ
		tempComboBox = (UIComboBox) getComponent(ArapValidationUtil.QRY_MODE_COMB);
		if (tempComboBox != null) {
			qryCondVO.setQryMode((String) tempComboBox.getSelectdItemValue());
		} else if (IPubReportConstants.GENERAL_REPORT.equals(((ReportInitializeVO)
				getReportInitializeVO().getParentVO()).getReporttype())) {
			qryCondVO.setQryMode(IPubReportConstants.QUERY_MODE_MONTH);
		}

		// ��ѯ����
		qryCondVO.setQryDirect((String) ((UIComboBox) getComponent(ArapValidationUtil.QRY_DIRECT_COMB)).getSelectdItemValue());

		// ��ѯ��Χ
		qryCondVO.setQryScope((String) ((UIComboBox) getComponent(ArapValidationUtil.QRY_SCOPE_COMB)).getSelectdItemValue());

		// ��ʼ����
		Object beginVal = ((UIRefPane) getComponent(ArapValidationUtil.BEGIN_TIME_REF)).getValueObj();
		UFDate beginDate = null;
		if (beginVal == null || "".equals(beginVal)) {
			beginDate = null;
		} else if (beginVal instanceof UFDate) {
			beginDate = new UFDate(((UFDate) beginVal).toLocalString(), true);
		} else if (beginVal.getClass().isArray()) {
			Object[] arrObj = (Object[]) beginVal;
			beginDate = AccountCalendar.getInstanceByAccperiodMonth(arrObj[0].toString()).getMonthVO().getBegindate();
		} else {
			beginDate = new UFDate(beginVal.toString(), true);
		}
		qryCondVO.setBeginDate(beginDate);

		// ��������
		Object endVal = ((UIRefPane) getComponent(ArapValidationUtil.END_TIME_REF)).getValueObj();
		UFDate endDate = null;
		if (endVal == null || "".equals(endVal)) {
			endDate = null;
		} else if (endVal instanceof UFDate) {
			endDate = new UFDate(((UFDate) endVal).toLocalString(), false);
		} else if (endVal.getClass().isArray()) {
			Object[] arrObj = (Object[]) endVal;
			endDate = AccountCalendar.getInstanceByAccperiodMonth(arrObj[0].toString()).getMonthVO().getEnddate();
		} else {
			endDate = new UFDate(endVal.toString(), false);
		}
		qryCondVO.setEndDate(endDate);

		// ����
		qryCondVO.setPk_currency(((UIRefPane) getComponent(ArapValidationUtil.CURRENCY_REF)).getRefPK());
		// ����״̬
		qryCondVO.setBillState((String) ((UIComboBox) getComponent(ArapValidationUtil.BILL_STATE_COMB)).getSelectdItemValue());

		qryCondVO.setLocalCurrencyType((String) ((UIComboBox) getComponent(LOCAL_CURRENCY_TYPE_COMB)).getSelectdItemValue());

		// ������չ��ѯ����
		setExtendQueryCond(qryCondVO);
	}

	/**
	 * ������չ��ѯ����
	 *
	 * @param qryCondVO
	 */
	protected void setExtendQueryCond(ReportQueryCondVO qryCondVO) {
		String[] extKey = { IArapReportConstants.KEY_SHOW_DATE_TOTAL, // �Ƿ���ʾ��С��
				IArapReportConstants.KEY_NOTSHOW_BACKESTI, // �ѻس���ݹ����ݲ���ʾ
				IArapReportConstants.KEY_NOOCC_NOSHOW,// �޷�������ʾ
				IArapReportConstants.KEY_NOBAL_NOOCC_NOSHOW // �޷���������ʾ
		};

		UICheckBox chkBox = null;
		for (String key : extKey) {
			if (getComponent(key) != null) {
				chkBox = (UICheckBox) getComponent(key);
				qryCondVO.getUserObject().put(key, UFBoolean.valueOf(chkBox.isSelected()));
			}
		}
	}

	protected void resetUserReportQueryCondVO(IReportQueryCond queryCond) {
		ReportQueryCondVO queryCondVO = (ReportQueryCondVO) queryCond;
		((UIComboBox) getComponent(ArapValidationUtil.ASSO_OBJ_COMB)).setSelectedItem(Integer.parseInt(queryCondVO.getAssoObj())); // ��������
		if (getComponent(ArapValidationUtil.QRY_MODE_COMB) != null) {
			((UIComboBox) getComponent(ArapValidationUtil.QRY_MODE_COMB)).setSelectedItem(queryCondVO.getQryMode()); // ��ѯ��ʽ
		}
		((UIComboBox) getComponent(ArapValidationUtil.QRY_DIRECT_COMB)).setSelectedItem(queryCondVO.getQryDirect()); // ��ѯ����
		((UIComboBox) getComponent(ArapValidationUtil.QRY_SCOPE_COMB)).setSelectedItem(queryCondVO.getQryScope()); // ��ѯ��Χ
		((UIComboBox) getComponent(ArapValidationUtil.BILL_STATE_COMB)).setSelectedItem(queryCondVO.getBillState()); // ����״̬
		((UIRefPane) getComponent(ArapValidationUtil.CURRENCY_REF)).setPK(queryCondVO.getPk_currency()); // ����
		((UIRefPane) getComponent(ArapValidationUtil.FINANCIAL_ORG_REF)).setPKs(queryCondVO.getPk_orgs()); // ������֯
		((UIComboBox) getComponent(LOCAL_CURRENCY_TYPE_COMB)).setSelectedItem(queryCondVO.getLocalCurrencyType()); // ���ر���
		//update for �¼���BDA 20161111
		setQueryPeriod(queryCondVO.getQryMode(), queryCondVO);

		String[] extKey = {
				IArapReportConstants.KEY_SHOW_DATE_TOTAL, // �Ƿ���ʾ��С��
				IArapReportConstants.KEY_NOOCC_NOSHOW,// �޷�������ʾ
				IArapReportConstants.KEY_NOBAL_NOOCC_NOSHOW // �޷���������ʾ
		};

		UICheckBox chkBox = null;
		for (String key : extKey) {
			if (getComponent(key) != null) {
				chkBox = (UICheckBox) getComponent(key);
				UFBoolean isselected = (UFBoolean) queryCondVO.getUserObject().get(key);
				if(isselected != null){ 
					chkBox.setSelected(isselected.booleanValue());
				}
			}
		}

		// =================================
		setPk_org(queryCondVO.getPk_orgs());
		// =================================
	}

	protected String[] getPk_org() {
		return ((UIRefPane) getComponent(ArapValidationUtil.FINANCIAL_ORG_REF)).getRefPKs();
	}

	public void setPk_org(String[] pkOrgs) {
		super.setPk_org(pkOrgs);
		String pk_org = null;
		if (pkOrgs != null && pkOrgs.length > 0) {
			pk_org = pkOrgs[0];
		}

		String[] refKey = { ArapValidationUtil.CURRENCY_REF, ArapValidationUtil.BEGIN_TIME_REF, ArapValidationUtil.END_TIME_REF };
		UIRefPane ref = null;
		for (String key : refKey) {
			ref = (UIRefPane) (getComponent(key));
			if (ref.getRefModel() != null) {
				ref.setPk_org(pk_org);
			}
		}
	}
	
	@Override
	public IQueryScheme getQuerySchemeByName(String name) {
		// TODO Auto-generated method stub
		IQueryScheme queryScheme = super.getQuerySchemeByName(name);
		QuerySchemeObject qsObj = findQsVoByName(name).getQSObject4Blob();
		String[] userDefineKeys = qsObj.getUserDefineKeys();
		if(userDefineKeys!=null && userDefineKeys.length>0){
			for(String userKey : userDefineKeys){
				queryScheme.put(userKey, qsObj.get(userKey));
			}
		}
		return queryScheme;
	}
	/**
	 * �����еķ�����˽�з��������̳�
	 * @param name
	 * @return
	 */
	private QuerySchemeVO findQsVoByName(String name) {
		if(StringUtils.isBlank(name)){
			return null;
		}
		QuerySchemeVO[] vos = getQuerySchemeVOs();
		if (vos == null || vos.length == 0) return null;
		for (QuerySchemeVO vo : vos) {
			if (vo.getCurLangNameValue().equals(name)) {
				return vo;
			}
		}
		return null;
	}
	protected boolean isShowLocalCurrencySelect() {
		return false;
	}

}

// /:~
