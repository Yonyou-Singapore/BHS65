package nc.ui.arap.comreport;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.DefaultCellEditor;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

import nc.bs.framework.common.NCLocator;
import nc.bs.logging.Logger;
import nc.itf.fipub.queryobjreg.IReportQueryObjRegQuery;
import nc.itf.fipub.report.IPubReportConstants;
import nc.ui.fipub.comp.PubUIComboBox;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UICheckBox;
import nc.ui.pub.beans.UIComboBox;
import nc.ui.pub.beans.UILabel;
import nc.ui.pub.beans.UIPanel;
import nc.ui.pub.beans.UITable;
import nc.ui.pub.beans.constenum.DefaultConstEnum;
import nc.utils.arap.ArapReportResource;
import nc.utils.fipub.FipubReportResource;
import nc.vo.arap.comreport.IArapReportConstants;
import nc.vo.fipub.report.QueryObjVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;

import org.apache.commons.lang.StringUtils;

/**
 * �ո��ʱ�ڵ㷢��-��ѯ����ѡ�����
 *
 * @author ������
 * @since V60
 * Create at 2010-11-09
 *
 */
public class ArapReleaseQryObjSelectComp extends UIPanel {
	private static final long serialVersionUID = 1L;

	private String moduleid = null;

	// ��ѯ����ѡ�����������ʱ֧��5����ѯ����ѡ��
	private final int qryObjTblRowCount = 5;
	// ��ѯ����ѡ�����������ʱ֧��2������
	private final int qryObjTblColCount = 2;
	// ��ѯ����ѡ���
	private UITable qryObjTable = null;
	// ��ѯ����ѡ�������
	private final List<String> colNameList = Arrays.asList(new String[] {ArapReportResource.getQryObjLbl(), ArapReportResource.getQryObjSeqLbl()});

	// ��ѯ�����б�
	private List<QueryObjVO> qryObjVOList = new ArrayList<QueryObjVO>();
	private int qryObjVOSize = 0;
	public int getQryObjVOSize() {
		return qryObjVOSize;
	}

	private List<QueryObjVO> qryObjVOListCache = null;

	private UIComboBox reportFormatCombo = null; // ��ҳ��ʽ��Ͽ�(��ѡ�����ʽ/��ҽ��ʽ)
	private UIComboBox showFormatCombo = null; // ��ʾ��ʽ��Ͽ�(��ѡ������/����/����+����)
	// private UIComboBox directCombo = null; // ����ѡ����Ͽ�(��ѡ����/��)
	// private UILabel directLabel = null;

	private UICheckBox showVoucherChk = null; // �Ƿ���ʾƾ֤��
	private UICheckBox multiUnitSepChk = null; // �Ƿ񰴵�λ��ʾ
	private UICheckBox multiUnitMerChk = null; // �Ƿ�൥λ�ϲ�

	private String reportType = null; // ��������

	// ��ʾ�ո�����ı����б�
	private final List<String> showDirectReportList = new ArrayList<String>();

	// ��ʾƾ֤�ŵı����б�
	private final List<String> showVoucherReportList = new ArrayList<String>();

	// ��ʾ�൥λѡ��ı����б�
	private final List<String> multiUnitReportList = new ArrayList<String>();

	// û�в�ѯ����ı����б�
	private final List<String> noQryObjReportList = new ArrayList<String>();

	private List<Object> selectQryObj = null;

	public ArapReleaseQryObjSelectComp(String moduleid) {
		setName("qryObjSelectPanel");
		setSize(new Dimension(780, 500));
		setPreferredSize(new Dimension(780, 500));
		setLayout(new BorderLayout());
		setVisible(true);
		this.moduleid = moduleid;
		initialize();
		initTable();
		initOther();
	}

	private void initialize() {
		showDirectReportList.add(IPubReportConstants.GENERAL_REPORT);
		showDirectReportList.add(IPubReportConstants.BALANCE_REPORT);
		showDirectReportList.add(IPubReportConstants.DETAIL_REPORT);

		showVoucherReportList.add(IPubReportConstants.DETAIL_REPORT);
		showVoucherReportList.add(IPubReportConstants.RECQRY_REPORT);
		showVoucherReportList.add(IPubReportConstants.PAYQRY_REPORT);

		multiUnitReportList.add(IPubReportConstants.YSZLFX_REPORT);
		multiUnitReportList.add(IPubReportConstants.YFZLFX_REPORT);
		multiUnitReportList.add(IPubReportConstants.SKYC_REPORT);
		multiUnitReportList.add(IPubReportConstants.FKYC_REPORT);
		multiUnitReportList.add(IPubReportConstants.YSQKFX_REPORT);
		multiUnitReportList.add(IPubReportConstants.SKFX_REPORT);

		noQryObjReportList.add(IPubReportConstants.ARALARM_REPORT);
		noQryObjReportList.add(IPubReportConstants.APALARM_REPORT);
		noQryObjReportList.add(IPubReportConstants.RECQRY_REPORT);
		noQryObjReportList.add(IPubReportConstants.PAYQRY_REPORT);
	}

	/**
	 * ���ܣ���ʼ����ѯ�����б�
	 */
	private void initTable() {
		if (qryObjTable == null) {
			// ���ñ�ģ��
			DefaultTableModel tableModel = new DefaultTableModel();
			tableModel.setColumnIdentifiers(new String[] { colNameList.get(0), colNameList.get(1) });
			tableModel.setRowCount(qryObjTblRowCount);
			tableModel.setColumnCount(qryObjTblColCount);

			qryObjTable = new UITable(qryObjTblRowCount, qryObjTblColCount) {
				private static final long serialVersionUID = 1L;
				// ���浥Ԫ��༭��
				private final TableCellEditor[] cellEditors = new TableCellEditor[qryObjTblRowCount];

				@Override
				public TableCellEditor getCellEditor(int row, int column) {
					if (column == 0 && cellEditors[row] != null) {
						return cellEditors[row];
					}

					switch (column) {
					case 0:
						PubUIComboBox comboBox = new PubUIComboBox();
						comboBox.addItemListener(new ComboBoxItemListener());
						cellEditors[row] = new DefaultCellEditor(comboBox);
						return cellEditors[row];
					default:
						return null;
					}

				}

			};
			qryObjTable.setName("qryObjTable");
			qryObjTable.setModel(tableModel);
			qryObjTable.getTableHeader().setBackground(super.getBackground());
			qryObjTable.setVisible(true);

			JScrollPane scrollPane = new JScrollPane(qryObjTable);
			scrollPane.setPreferredSize(new Dimension(100, 145));
			add(scrollPane, BorderLayout.NORTH);
		}
	}

	private void initOther() {
		UIPanel otherPanel = new UIPanel();
		otherPanel.setName("otherPanel");
		FlowLayout otherPanelLayout = new FlowLayout();
		otherPanelLayout.setAlignment(FlowLayout.LEFT);
		otherPanelLayout.setHgap(25);
		otherPanelLayout.setVgap(15);
		otherPanel.setLayout(otherPanelLayout);

		UIPanel generalPanel = new UIPanel();
		generalPanel.setName("generalPanel");
		FlowLayout generalPanelLayout = new FlowLayout();
		generalPanelLayout.setAlignment(FlowLayout.LEFT);
		generalPanelLayout.setHgap(20);
		generalPanelLayout.setVgap(15);
		generalPanel.setLayout(generalPanelLayout);
		// generalPanel.setBorder(BorderFactory.createEtchedBorder());

		Dimension labelSize = new Dimension(105, 30);
		UILabel accountFormatLabel = new UILabel(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common","UC000-0003829")/*@res "��ҳ��ʽ"*/);
		accountFormatLabel.setHorizontalAlignment(SwingConstants.LEFT);
		accountFormatLabel.setSize(labelSize);
		accountFormatLabel.setPreferredSize(labelSize);

		UILabel showFormatLabel = new UILabel(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common","UC000-0002449")/*@res "��ʾ��ʽ"*/);
		showFormatLabel.setHorizontalAlignment(SwingConstants.LEFT);
		showFormatLabel.setSize(labelSize);
		showFormatLabel.setPreferredSize(labelSize);

		// directLabel = new UILabel("�ո�����");
		// directLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		// directLabel.setPreferredSize(new Dimension(labelSize));

		Dimension compSize = new Dimension(105, 30);
		reportFormatCombo = new UIComboBox();
		reportFormatCombo.addItems(new DefaultConstEnum[] {
				//update for �¼���BDA 20161111
				new DefaultConstEnum(IPubReportConstants.ACCOUNT_FORMAT_FOREIGN, FipubReportResource.getAccountFormatLocalLbl()),
				new DefaultConstEnum(IPubReportConstants.ACCOUNT_FORMAT_LOCAL, FipubReportResource.getAccountFormatForeignLbl()) });
		reportFormatCombo.setSize(compSize);
		reportFormatCombo.setPreferredSize(compSize);

		showFormatCombo = new UIComboBox();
		showFormatCombo.addItems(new DefaultConstEnum[] {
				new DefaultConstEnum(IPubReportConstants.SHOW_FORMAT_NAME, FipubReportResource.getShowFormatNameLbl()),
				new DefaultConstEnum(IPubReportConstants.SHOW_FORMAT_CODE, FipubReportResource.getShowFormatCodeLbl()),
				new DefaultConstEnum(IPubReportConstants.SHOW_FORMAT_NAME_CODE, FipubReportResource.getShowFormatNameCodeLbl()) });
		showFormatCombo.setSize(compSize);
		showFormatCombo.setPreferredSize(compSize);

		// directCombo = new UIComboBox();
		// directCombo.addItems(new DefaultConstEnum[] {
		//		new DefaultConstEnum(IPubReportConstants.DIRECT_REC, FipubReportResource.getDirectRecLbl()),
		//		new DefaultConstEnum(IPubReportConstants.DIRECT_PAY, FipubReportResource.getDirectPayLbl()) });
		// directCombo.setPreferredSize(compSize);

		generalPanel.add(accountFormatLabel);
		generalPanel.add(reportFormatCombo);
		generalPanel.add(new UILabel(" "));
		generalPanel.add(showFormatLabel);
		generalPanel.add(showFormatCombo);

		// generalPanel.add(directLabel);
		// generalPanel.add(directCombo);

		otherPanel.add(generalPanel);

		// ==============================================================

		UIPanel specialPanel = new UIPanel();
		specialPanel.setName("specialPanel");
		FlowLayout specialPanelLayout = new FlowLayout();
		specialPanelLayout.setAlignment(FlowLayout.LEFT);
		specialPanelLayout.setHgap(20);
		specialPanelLayout.setVgap(15);
		specialPanel.setLayout(specialPanelLayout);
		// specialPanel.setBorder(BorderFactory.createEtchedBorder());

		compSize = new Dimension(150, 30);
		showVoucherChk = new UICheckBox(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("2006v61008_0","02006v61008-0037")/*@res "��ʾƾ֤��"*/);
		multiUnitSepChk = new UICheckBox(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("2006v61008_0","02006v61008-0038")/*@res "����λ��ʾ"*/);
		multiUnitMerChk = new UICheckBox(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("2006v61008_0","02006v61008-0039")/*@res "�൥λ�ϲ�"*/);
		showVoucherChk.setPreferredSize(compSize);
		multiUnitSepChk.setPreferredSize(compSize);
		multiUnitMerChk.setPreferredSize(compSize);

		specialPanel.add(showVoucherChk);
		specialPanel.add(multiUnitSepChk);
		specialPanel.add(multiUnitMerChk);

		ButtonGroup multiGroup = new ButtonGroup();
		multiGroup.add(multiUnitSepChk);
		multiGroup.add(multiUnitMerChk);
		multiUnitSepChk.doClick();

		otherPanel.add(specialPanel);

		add(otherPanel, BorderLayout.CENTER);
	}

	/**
	 * ���ܣ����ز�ѯ������б�<br>
	 * ˵��������λ��Ϊ��ѯ����ż��λ��Ϊ��ѯ����Ĵ���<br>
	 *
	 * @return ��ѯ������б�<br>
	 */
	public List<Object> getCheckSelectQryObj() {
		List<Object> qryObjList = new ArrayList<Object>();
		for (int i = 0; i < qryObjTblRowCount; i++) {
			Object qryObj = ((PubUIComboBox) ((DefaultCellEditor) qryObjTable.getCellEditor(i, 0))
					.getComponent()).getSelectdItem();
			if (qryObj == null) {
				continue;
			}

			qryObjList.add(qryObj);
			qryObjList.add(qryObjTable.getValueAt(i, 1));
		}

		return qryObjList;
	}

	public List<Object> getSelectQryObj() {
		return selectQryObj;
	}

	public void setSelectQryObj(List<Object> selectQryObj) {
		this.selectQryObj = selectQryObj;
	}

	/**
	 * ���ܣ��������ѡ������<br>
	 *
	 * @return Map<String, String><br>
	 */
	public Map<String, String> getOtherConds() {
		Map<String, String> otherConds = new HashMap<String, String>();
		otherConds.put("accountFormat", reportFormatCombo.getSelectdItemValue().toString());
		otherConds.put("showFormat", showFormatCombo.getSelectdItemValue().toString());
		return otherConds;
	}

	/**
	 * �õ��ò�ѯ�ڵ㶨��Ĳ�ѯ����<br>
	 */
	public List<QueryObjVO> getUseableQryObj() {
		if (noQryObjReportList.contains(reportType)) {
			qryObjVOListCache = qryObjVOList;
			qryObjVOList = new ArrayList<QueryObjVO>();
		} else if (qryObjVOListCache != null && qryObjVOListCache.size() > 0) {
			qryObjVOList = qryObjVOListCache;
		} else {
			try {
				// ��ѯѡ���ʱ�����ע��Ĳ�ѯ����
				qryObjVOList = NCLocator.getInstance().lookup(IReportQueryObjRegQuery.class)
						.getRegisteredQueryObjWithTrue(IPubReportConstants.MODULE_ARAP);
			} catch (BusinessException e) {
				Logger.error(e.getMessage(), e);
				MessageDialog.showErrorDlg(this, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("2006v61008_0","02006v61008-0040")/*@res "����"*/, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("2006v61008_0","02006v61008-0041")/*@res "��ȡ��ѯ����ʱ��������"*/);
			}
		}

		// ���ÿ�ѡ��ѯ�����ֵ
		setQryObjTableData();
		qryObjVOSize = qryObjVOList.size();
		return qryObjVOList;
	}

	public void updateUIOptions() {
		// directLabel.setVisible(false);
		// directCombo.setVisible(false);
		showVoucherChk.setVisible(false);
		multiUnitSepChk.setVisible(false);
		multiUnitMerChk.setVisible(false);

		// if (showDirectReportList.contains(reportType)) {
		//     directLabel.setVisible(true);
		//     directCombo.setVisible(true);
		// }

		if (showVoucherReportList.contains(reportType)) {
			showVoucherChk.setVisible(true);
			showVoucherChk.setSelected(false);
		}

		if (multiUnitReportList.contains(reportType)) {
			multiUnitSepChk.setVisible(true);
			multiUnitMerChk.setVisible(true);
			multiUnitSepChk.doClick();
		}

		this.repaint();
	}

	/**
	 * ���ÿ�ѡ��ѯ�����ֵ<br>
	 */
	private void setQryObjTableData() {
		String prop = QueryObjVO.DSP_OBJNAME;
		for (int i = 0; i < qryObjTblRowCount; i++) {
			((PubUIComboBox) ((DefaultCellEditor) qryObjTable.getCellEditor(i, 0)).getComponent())
					.addItems(qryObjVOList, prop, true);
		}
	}

	/**
	 * ���ܣ����ò�ѯ����˳��<br>
	 */
	private void setQryObjOrder() {
		int order = 1;
		for (int i = 0; i < qryObjTblRowCount; i++) {
			if (StringUtils.isEmpty((String) qryObjTable.getCellEditor(i, 0).getCellEditorValue())) {
				qryObjTable.setValueAt("", i, 1);
				continue;
			}

			qryObjTable.setValueAt(order, i, 1);
			order++;
		}
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	/**
	 * ��ȡ��ҳ��ʽ
	 *
	 * @return String
	 */
	public String getReportFormat() {
		return reportFormatCombo.getSelectdItemValue().toString();
	}

	/**
	 * ��ȡ��ʾ��ʽ
	 *
	 * @return String
	 */
	public String getShowFormat() {
		return showFormatCombo.getSelectdItemValue().toString();
	}

	/**
	 * ��ȡ�ո�����
	 *
	 * @return String
	 */
	public String getRecPayDirect() {
		String direct = null;
		if (IArapReportConstants.MODULEID_AR.equals(moduleid)) {
			direct = IPubReportConstants.DIRECT_REC;
		} else if (IArapReportConstants.MODULEID_AP.equals(moduleid)) {
			direct = IPubReportConstants.DIRECT_PAY;
		}

		return direct;
	}

	/**
	 * �Ƿ���ʾƾ֤��
	 *
	 * @return String
	 */
	public UFBoolean getShowVoucher() {
		if (showVoucherReportList.contains(reportType)) {
			return UFBoolean.valueOf(showVoucherChk.isSelected());
		}
		return null;
	}

	/**
	 * ��ȡ����֯��ʾ��ʽ
	 *
	 * @return String 0����/1������λ��ʾ/2������֯�ϲ�
	 */
	public int getMultiUnitShowMode() {
		if (multiUnitReportList.contains(reportType)) {
			if (multiUnitSepChk.isSelected()) {
				return 1;
			} else if (multiUnitMerChk.isSelected()) {
				return 2;
			}
		}
		return 0;
	}

	class ComboBoxItemListener implements ItemListener {
		public void itemStateChanged(ItemEvent e) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				setQryObjOrder();
			}
		}
	}

}

// /:~