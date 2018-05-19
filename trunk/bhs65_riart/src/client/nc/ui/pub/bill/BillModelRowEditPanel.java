package nc.ui.pub.bill;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.Action;
import javax.swing.InputMap;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPasswordField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import nc.bs.logging.Logger;
import nc.ui.bill.depend.IPageNavigationListener3;
import nc.ui.bill.depend.PageInfo;
import nc.ui.bill.depend.PageNavigationToolBar;
import nc.ui.bill.tools.BillCardPanelFlowLayout;
import nc.ui.pub.beans.UIFractionTextField;
import nc.ui.pub.beans.UIMultiLangCombox;
import nc.ui.pub.beans.UIPanel;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.beans.UIRefPaneTextField;
import nc.ui.pub.beans.UIScrollPane;
import nc.ui.pub.beans.UITextAreaScrollPane;
import nc.ui.pub.beans.constenum.DefaultConstEnum;
import nc.ui.pub.beans.constenum.IConstEnum;
import nc.ui.pub.bill.itemeditors.BillItemEditerUtil;
import nc.ui.pub.bill.panel.BillAreaPanel;
import nc.vo.pub.bill.BillTabVO;

@SuppressWarnings("serial")
public class BillModelRowEditPanel extends UIPanel implements
		TableModelListener, IPageNavigationListener3 {
	private BillModel billModel = null;
	private int editingRow = -1;
	private BillTabVO baseTab = null;
	private BillTabVO[] shareTabs = null;
	private boolean nochange = false;

	private BillEditListener el = null; // 表格编辑监听
	private BillEditListener2 el2 = null; // 表格编辑监听扩展

	private HashSet<String> hasItemIsShow = new HashSet<String>();

	private UIScrollPane editpanel = null;
	private PageNavigationToolBar pageNavigationBar = null;

	private PageInfo pageinfo = new PageInfo();

	private BillScrollPane billSclPane = null;
	
	private BillModelRowEditDialog dialog = null;

	// // 编辑监听
	// class HeadValueChangeListener implements ValueChangedListener {
	// // uirefpane + billitem
	// Hashtable<Component, BillItem> hashItem = new Hashtable<Component,
	// BillItem>();
	//
	// public void valueChanged(ValueChangedEvent e) {
	// JComponent editorComp = (JComponent) e.getSource();
	// if (editorComp instanceof UIRefPane) {
	// BillItem item = hashItem.get(editorComp);
	// Object value = ((UIRefPane) editorComp).getText();
	// editingStopped(item, value);
	// }
	// }
	// }
	//
	// class ComboBoxItemListener implements ItemListener {
	// // combobox + item
	// Hashtable<Component, BillItem> hashItem = new Hashtable<Component,
	// BillItem>();
	//
	// public void itemStateChanged(ItemEvent e) {
	// if (e.getStateChange() == ItemEvent.SELECTED) {
	// JComponent editorComp = (JComponent) e.getSource();
	// if (editorComp instanceof JComboBox && editorComp.hasFocus()) {
	// JComboBox box = (JComboBox) editorComp;
	// BillItem item = hashItem.get(box);
	// String text = getJComboBoxText(box);
	// editingStopped(item, text);
	// }
	// }
	// }
	//
	// /**
	// * 获取下拉框的显示字符串. 主要用于可编辑下拉框. 创建日期:(2003-6-23 9:24:52)
	// *
	// * @param box
	// * the combobox
	// *
	// * @return the display string of the combobox
	// */
	// String getJComboBoxText(final JComboBox box) {
	// if (box == null)
	// return null;
	// String text = null;
	// Object o;
	// java.awt.Component editor;
	// if ((o = box.getSelectedItem()) != null) {
	// text = o.toString();
	// } else if ((editor = box.getEditor().getEditorComponent()) instanceof
	// javax.swing.text.JTextComponent) {
	// text = ((javax.swing.text.JTextComponent) editor).getText();
	// }
	// return text;
	// }
	// }
	//
	// class CheckBoxActionListener implements ActionListener {
	// // checkbox + item
	// Hashtable<Component, BillItem> hashItem = new Hashtable<Component,
	// BillItem>();
	//
	// public void actionPerformed(ActionEvent e) {
	// JComponent editorComp = (JComponent) e.getSource();
	// if (editorComp instanceof JCheckBox) {
	// JCheckBox box = (JCheckBox) editorComp;
	// BillItem item = hashItem.get(box);
	// Object o = item.getValueObject();
	// editingStopped(item, o);
	// }
	// }
	// }
	//
	// class BillItemtFocusListener implements FocusListener {
	// // component + billitem
	// Hashtable<Component, BillItem> hashFocus = new Hashtable<Component,
	// BillItem>();
	//
	// // current Billitem
	// BillItem focusedItem = null;
	//
	// public void focusLost(java.awt.event.FocusEvent e) {
	// if (!e.isTemporary())
	// focusedItem = null;
	// }
	//
	// public void focusGained(java.awt.event.FocusEvent e) {
	// if (!e.isTemporary()) {
	// BillItem oldItem = focusedItem;
	// focusedItem = (BillItem) hashFocus.get(e.getSource());
	// if (focusedItem != null && focusedItem != oldItem) {
	// if (el2 != null) {
	// String key = focusedItem.getKey();// getBodyKeyByCol(column);
	// BillEditEvent ev = new BillEditEvent(focusedItem, null,
	// key, editingRow, BillItem.BODY);
	// ev.setTableCode(getBaseTab().getTabcode());
	// if (!el2.beforeEdit(ev))
	// setCompEditabled(focusedItem, false);
	// }
	// }
	// }
	// }
	// }
	//
	// class BillItemtRefEditListener implements RefEditListener {
	// // component + billitem
	// Hashtable<Component, BillItem> hashFocus = new Hashtable<Component,
	// BillItem>();
	//
	// public boolean beforeEdit(RefEditEvent e) {
	// // BillItem oldItem = focusedItem;
	// BillItem focusedItem = hashFocus.get(e.getSource());
	//
	// int col = getBillModel().getBodyColByKey(focusedItem.getKey());
	//
	// boolean edit = getBillModel().isCellEditable(editingRow, col);
	//
	// if (focusedItem != null) {
	// if (el2 != null) {
	// String key = focusedItem.getKey();// getBodyKeyByCol(column);
	// BillEditEvent ev = new BillEditEvent(focusedItem, null,
	// key, editingRow, BillItem.BODY);
	// ev.setTableCode(getBaseTab().getTabcode());
	// return edit && el2.beforeEdit(ev);
	// }
	// }
	//
	// return edit;
	// }
	//
	// }
	//
	// class BillItemPropertyChangeAdpeter implements
	// BillItemPropertyChangeListener {
	//
	// public void propertyChange(BillItemPropertyChangeEvent ent) {
	// if (ent.getItemProperty() == BillItemPropertyChangeEvent.COMPONENT) {
	// initConnection((BillItem) ent.getSource());
	// }
	// }
	//
	// }
	//
	// // HeadValueChangeListener
	// HeadValueChangeListener headValueChangeListener = new
	// HeadValueChangeListener();
	//
	// // BillItemtFocusListener
	// BillItemtFocusListener headTailFocusListener = new
	// BillItemtFocusListener();
	//
	// BillItemtRefEditListener headTailRefEditListener = new
	// BillItemtRefEditListener();
	//
	// // ComboBoxItemListener
	// ComboBoxItemListener comboBoxItemListener = new ComboBoxItemListener();
	//
	// CheckBoxActionListener checkBoxItemListener = new
	// CheckBoxActionListener();

	public BillModelRowEditDialog getDialog() {
		return dialog;
	}

	public void setDialog(BillModelRowEditDialog dialog) {
		this.dialog = dialog;
	}

	public BillModelRowEditPanel(BillScrollPane billSclPane) {
		this.billSclPane = billSclPane;
	}

	private class BillItemEditListener implements BillEditListener {

		@Override
		public void afterEdit(BillEditEvent e) {
			editingStopped(getBillModel().getItemByKey(e.getKey()), e
					.getValue());
		}

		@Override
		public void bodyRowChange(BillEditEvent e) {
		}

	}

	private class BillItemBeforeEditListener implements
			BillCardBeforeEditListener {

		@Override
		public boolean beforeEdit(BillItemEvent e) {
			if (el2 != null) {
				BillEditEvent ev = new BillEditEvent(e.getItem(), null, e
						.getItem().getKey(), editingRow, BillItem.BODY);
				ev.setTableCode(getBaseTab().getTabcode());
				return el2.beforeEdit(ev);
			}

			return true;
		}

	}

	private BillEditListener billEditListener = new BillItemEditListener();

	private BillCardBeforeEditListener billCardBeforeEditListener = new BillItemBeforeEditListener();

	private BillItem[] getBillItems() {
		if (billModel != null)
			return billModel.getBodyItems();
		else
			return null;
	}

	private void setItemEnabled(boolean newEnabled) {
		for (int i = 0; i < getBillItems().length; i++) {
			BillItem item = getBillItems()[i];
			if (newEnabled) {
				setCompEditabled(item, item.isEdit());
			} else {
				setCompEditabled(item, newEnabled);
			}
		}
	}

	private void setCompEditabled(BillItem item, boolean newEditabled) {
		JComponent comp = item.getComponent();
		if (comp != null) {

			if (comp instanceof UIRefPane) {
				// ((UIRefPane)comp).setEditable(newEditabled);
				((UIRefPane) comp).setEnabled(newEditabled);
				// ((UIRefPane)comp).getUITextField().setEditable(newEditabled);
				// ((UIRefPane)comp).getUIButton().setEnabled(newEditabled);
			} else
				comp.setEnabled(newEditabled);
		}
	}

	/**
	 * 得到可显示元素数组.
	 */
	private BillItem[] getShowItems(String tablecode) {
		BillItem[] biItems = getBillItems();

		if (biItems == null)
			return null;

		final ArrayList<BillItem> list = new ArrayList<BillItem>();
		for (int i = 0; i < biItems.length; i++) {
			if (!isPanelShowItem(biItems[i])) {
				biItems[i].setShareTableCode(tablecode);
				if (biItems[i].isShow()) {
					list.add(biItems[i]);
					addShowItem(biItems[i]);
				}
			}
		}
		if (list.size() > 0)
			return list.toArray(new BillItem[list.size()]);
		return null;
	}

	public BillModel getBillModel() {
		return billModel;
	}

	public void setBillModel(BillModel billModel) {
		this.billModel = billModel;
		initialize();
	}

	private void initialize() {
		try {
			setLayout(new BorderLayout());
			add(getPageNavigationBar(), BorderLayout.NORTH);
			if (getBillModel() != null) {
				if (getBillModel().getRowEditState()) {
					getPageNavigationBar().setShowFlagAdd(false);
					getPageNavigationBar().setShowFlagDelete(false);
				}
				getBillModel().addTableModelListener(this);
			}
			add(getEditPanel(), BorderLayout.CENTER);
			getBillSclPane().getTable().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
				
				@Override
				public void valueChanged(ListSelectionEvent e) {
					// TODO Auto-generated method stub
					if(getDialog()!=null && getDialog().isVisible())
					if (getEditingRow()!=getBillSclPane().getTable().getSelectedRow())
					setEditingRow(getBillSclPane().getTable().getSelectedRow(),true,true);
					
				}
			});
			
		} catch (java.lang.Throwable e) {
			Logger.error(e);
		}

	}

	/**
	 * 创建日期:(2003-9-2 15:09:40)
	 * 
	 * @return nc.ui.test.PageNavigationBar
	 */
	private PageNavigationToolBar getPageNavigationBar() {
		if (pageNavigationBar == null) {
			pageNavigationBar = new PageNavigationToolBar();
			pageNavigationBar.setPreferredSize(new Dimension(20, 22));
			pageNavigationBar.setShowFlagConfig(false);
			pageNavigationBar.setShowFlagRefresh(false);
			pageNavigationBar
					.setShowFlagAdd(isEditButtonVisible(BillScrollPane.ADDLINE));
			pageNavigationBar
					.setShowFlagDelete(isEditButtonVisible(BillScrollPane.DELLINE));
			pageNavigationBar.setTotalPageTextVisible(false);
			pageNavigationBar.addPageNavigationListener(this);

		}
		return pageNavigationBar;
	}

	// /**
	// *
	// ************************************************************************
	// * 创建默认组件标题
	// *
	// ************************************************************************
	// */
	// private void createDefaultLabel(BillItem item) {
	// if (item.getDataType() == BillItem.BLANK)
	// item.setCaptionLabel(new UILabel(" "));
	// else
	// item.setCaptionLabel(new UILabel(getName()));
	// item.getCaptionLabel().setName(BillItem.LABLE_PRESTR + item.getKey());
	// }
	//
	// private UIPanel creatEditPanel(String tabcode) {
	// if (getBillModel() == null)
	// return null;
	//
	// BillItem[] showItems;
	// UIPanel pnl = new UIPanel();
	//
	// if (tabcode.equals(getBaseTab().getTabcode()))
	// getBillModel().addTableModelListener(this);
	//
	// showItems = getShowItems(tabcode);
	//
	// if (showItems == null || showItems.length == 0)
	// return null;
	//
	// pnl.removeAll();
	// int maxWidth = 0;
	// for (int i = 0; i < showItems.length; i++) {
	// if (showItems[i].getCaptionLabel() == null)
	// createDefaultLabel(showItems[i]);
	//
	// int l = showItems[i].getCaptionLabel().getSize().width;
	// if (l > maxWidth)
	// maxWidth = l;
	// }
	// for (int i = 0; i < showItems.length; i++) {
	//
	// // set label foreground
	// if (showItems[i].isNull())
	// setLabelForground(showItems[i], UILabel.notNullColor);
	// else
	// setLabelForground(showItems[i], ColorConstants
	// .getColor(showItems[i].getForeground()));
	//
	// switch (showItems[i].getDataType()) {
	// case BillItem.IMAGE: {
	// break;
	// }
	// case BillItem.BLANK: {
	// break;
	// }
	// case BillItem.TEXTAREA: {
	// }
	// default: {
	// showItems[i].setCaptionWidth(maxWidth);
	// showItems[i].getCaptionLabel().setText(showItems[i].getName());
	// break;
	// }
	// }
	//
	// initConnection(showItems[i]);
	//
	// }
	// // BillUtil.sortBillItemsByShowOrder(showItems);
	// for (int i = 0; i < showItems.length; i++) {
	// setCompEditabled(showItems[i], showItems[i].isEdit());
	// pnl.add(showItems[i].getCaptionLabel());
	// pnl.add(showItems[i].getComponent());
	// }
	//
	// // 布局管理
	// pnl.setLayout(new nc.ui.bill.tools.BillHeadSpringLayout(showItems));
	//
	// return pnl;
	// }

	private void initBillItem(BillItem showItem) {
		Component com = showItem.getComponent();
		com.setVisible(true);

		if (com instanceof UIRefPane) {
			UIRefPane ref = (UIRefPane) com;
			BillItemEditerUtil.registerKey(ref.getUITextField());
			setRefReturnName(showItem, ref);
			((UIRefPaneTextField) ref.getUITextField())
					.setIsAutoAdjustLength(false);
			// ((UIRefPaneTextField)ref.getUITextField()).setIsAutoAdjustLength(false);
		} else if (com instanceof JComboBox) {
			BillItemEditerUtil.registerKey((JComboBox) com);
		} else if (com instanceof JCheckBox) {
			BillItemEditerUtil.registerKey((JCheckBox) com);
			resetCheckBoxBillItem(showItem);
		} else if (com instanceof JPasswordField) {
			BillItemEditerUtil.registerKey((JPasswordField) com);
		} else if (com instanceof UIMultiLangCombox) {
			BillItemEditerUtil
					.registerKey((JComponent) ((UIMultiLangCombox) com)
							.getEditor().getEditorComponent());
		} else if (com instanceof UIFractionTextField) {
			BillItemEditerUtil.registerKey(((UIFractionTextField) com)
					.getDenominator());
			// BillItemEditerUtil.registerKey(((UIFractionTextField) com)
			// .getNumerator());
		} 
		//add chenth 20170712 表体卡片编辑时 大文本框支持换行
		else if (com instanceof UITextAreaScrollPane) {
			BillItemEditerUtil
			.registerKey((JComponent) ((UITextAreaScrollPane) com).getUITextArea());
		}

	}

	private void initConnection(BillItem showItem) {
		setCompEditabled(showItem, showItem.isEdit());
		showItem.getItemEditor().addBillEditListener(billEditListener);
		showItem.getItemEditor().addBillCardBeforeEditListener(
				billCardBeforeEditListener);
	}

	// 使表体复选框类型的Billitem按表头类型布局，否则显示不出名称
	private void resetCheckBoxBillItem(BillItem item) {
		JCheckBox ccb = (JCheckBox) item.getComponent();
		ccb.setText(item.getName());
		ccb.setHorizontalAlignment(SwingConstants.LEFT);
	}

	private void restoreCheckBoxBillItem(BillItem item) {
		JCheckBox ccb = (JCheckBox) item.getComponent();
		ccb.setText("");
		ccb.setHorizontalAlignment(SwingConstants.CENTER);
	}

	public void restoreBillItem() {
		if (getBillModel() == null)
			return;

		getBillModel().removeTableModelListener(this);

		BillItem[] billitems = getBillItems();
		if (billitems != null) {
			for (int i = 0; i < billitems.length; i++) {
				if (isPanelShowItem(billitems[i])) {
					Component com = billitems[i].getComponent();
					if (com instanceof UIRefPane) {
						UIRefPane ref = (UIRefPane) com;
						restoreRegisterKey(ref.getUITextField());

						com = ref.getUITextField();
						// ref.setReturnCode(true);
						((UIRefPaneTextField) com).setIsAutoAdjustLength(true);
					} else if (com instanceof JComboBox) {
						restoreRegisterKey((JComboBox) com);
					} else if (com instanceof JCheckBox) {
						restoreCheckBoxBillItem(billitems[i]);
					} else if (com instanceof UIMultiLangCombox) {
						restoreRegisterKey((UIMultiLangCombox) com);
					} else if (com instanceof JPasswordField) {
						restoreRegisterKey((JPasswordField) com);
					} else if (com instanceof UIFractionTextField) {
						restoreRegisterKey(((UIFractionTextField) com)
								.getDenominator());
						// restoreRegisterKey(((UIFractionTextField) com)
						// .getNumerator());
					}
					setCompEditabled(billitems[i], billitems[i].isEdit());

					billitems[i].getItemEditor().addBillCardBeforeEditListener(
							null);
					billitems[i].getItemEditor().addBillEditListener(null);
				}
			}
		}
	}

	// private void initConnection(BillItem showItem) {
	// Component com = showItem.getComponent();
	//
	// if (com instanceof UIRefPane) {
	// UIRefPane ref = (UIRefPane) com;
	// if (!headValueChangeListener.hashItem.containsKey(ref)) {
	// ref.addValueChangedListener(headValueChangeListener);
	// headValueChangeListener.hashItem.put(ref, showItem);
	// registerKey(ref.getUITextField());
	// }
	// setRefReturnName(showItem,ref);
	// ((UIRefPaneTextField)ref.getUITextField()).setIsAutoAdjustLength(false);
	// } else if (com instanceof JComboBox) {
	// if (!comboBoxItemListener.hashItem.containsKey(com)) {
	// ((JComboBox) com).addItemListener(comboBoxItemListener);
	// comboBoxItemListener.hashItem.put(com, showItem);
	// registerKey((JComboBox) com);
	// }
	// } else if (com instanceof JCheckBox) {
	// if (!checkBoxItemListener.hashItem.containsKey(com)) {
	// ((JCheckBox) com).addActionListener(checkBoxItemListener);
	// checkBoxItemListener.hashItem.put(com, showItem);
	// }
	// } else if (com instanceof JPasswordField) {
	// registerKey((JPasswordField)com);
	// }
	//
	// addFocusListener(com,showItem);
	// }

	// private void addFocusListener(Component com, BillItem item) {
	//
	// if (com instanceof UIRefPane) {
	// if (!headTailRefEditListener.hashFocus.containsKey(com)) {
	// UIRefPane ref = (UIRefPane) com;
	// ref.addRefEditListener(headTailRefEditListener);
	// headTailRefEditListener.hashFocus.put(com, item);
	// }
	// } else if (!headTailFocusListener.hashFocus.containsKey(com)) {
	// com.addFocusListener(headTailFocusListener);
	// headTailFocusListener.hashFocus.put(com, item);
	// }
	// }
	//
	// private static void setLabelForground(BillItem item, java.awt.Color
	// color) {
	// if (item == null)
	// return;
	// nc.ui.pub.beans.UILabel label = item.getCaptionLabel();
	// label.setILabelType(0);
	// label.setForeground(color);
	// }

	// public void restoreBillItem() {
	// if (getBillModel() == null)
	// return;
	//
	// getBillModel().removeTableModelListener(this);
	//
	// BillItem[] billitems = getBillItems();
	// if (billitems != null) {
	// for (int i = 0; i < billitems.length; i++) {
	// if (isPanelShowItem(billitems[i])) {
	// Component com = billitems[i].getComponent();
	// if (com instanceof UIRefPane) {
	// UIRefPane ref = (UIRefPane) com;
	// if (headValueChangeListener.hashItem.containsKey(ref)) {
	// ref
	// .removeValueChangedListener(headValueChangeListener);
	// restoreRegisterKey(ref.getUITextField());
	// }
	//
	// ref.removeRefEditListener(headTailRefEditListener);
	//
	// com = ref.getUITextField();
	// ref.setReturnCode(true);
	// ((UIRefPaneTextField) com).setIsAutoAdjustLength(true);
	// } else if (com instanceof JComboBox) {
	// if (comboBoxItemListener.hashItem.containsKey(com)) {
	// ((JComboBox) com)
	// .removeItemListener(comboBoxItemListener);
	// restoreRegisterKey((JComboBox) com);
	// }
	// } else if (com instanceof JCheckBox) {
	// if (checkBoxItemListener.hashItem.containsKey(com)) {
	// ((JCheckBox) com)
	// .removeActionListener(checkBoxItemListener);
	// }
	// } else if (com instanceof JPasswordField) {
	// restoreRegisterKey((JPasswordField) com);
	// }
	//
	// if (!(com instanceof UIRefPane)
	// && headTailFocusListener.hashFocus.containsKey(com)) {
	// com.removeFocusListener(headTailFocusListener);
	// }
	// // com.setEnabled(billitems[i].isEdit());
	//
	// setCompEditabled(billitems[i], billitems[i].isEdit());
	// }
	// }
	// }
	// }

	// /*
	// * 表头控件回车的特殊处理
	// */
	// private void registerKey(JComponent c) {
	// // Enter key
	// InputMap map = c.getInputMap(JComponent.WHEN_FOCUSED);
	// ActionMap am = c.getActionMap();
	// // ENTER_P
	// map.remove(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false));
	// // ENTER_R
	// map.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
	// ShortCutKeyAction.KEY_FOCUS_TRANSFER);
	// am.put(ShortCutKeyAction.KEY_FOCUS_TRANSFER, new ShortCutKeyAction(
	// ShortCutKeyAction.VK_ENTER));
	// }

	/*
	 * 表头控件回车的特殊处理
	 */
	private void restoreRegisterKey(JComponent c) {
		// Enter key
		InputMap map = c.getInputMap(JComponent.WHEN_FOCUSED);
		// ActionMap am = c.getActionMap();
		// ENTER_R
		map.remove(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true));
		// ENTER_P
		map.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
				BillItemEditerUtil.KEY_FOCUS_TRANSFER);
		// am.put(ShortCutKeyAction.KEY_FOCUS_TRANSFER, new ShortCutKeyAction(
		// ShortCutKeyAction.VK_ENTER));
	}

	public void editingStopped(BillItem item, Object value) {
		
		if (item == null)
			return;
		
		boolean oldAutoAddLine = getBillModel().getBillScrollPane().isAutoAddLine();
		getBillModel().getBillScrollPane().setAutoAddLine(false);

		//Object oldValue = getBillModel().getValueAt(editingRow, item.getKey());
		Object oldValue = getBillModel().getValueAtModel(editingRow,
				item.getKey());
		if (oldValue instanceof IConstEnum) {
			oldValue = ((IConstEnum) oldValue).getValue();
		}

		// 有效性检测
		boolean bValueChanged = false;

		bValueChanged = isValueChanged(oldValue, value, item);

		if (value != null) {
			String sv = value.toString().trim();
			if ("".equals(sv))
				value = sv;
			else {
				try {
					if (item.getDataType() == IBillItem.INTEGER) {
						Integer.parseInt(sv);
					} else if (item.getDataType() == IBillItem.DECIMAL
							|| item.getDataType() == IBillItem.MONEY) {
						Double.parseDouble(sv);
					}
				} catch (Exception ex) {
					Logger.info("非法数字！");
					bValueChanged = false;
				}
			}
		}

		if (bValueChanged) {

			if (item.getDataType() == BillItem.UFREF && value != null) {
				UIRefPane ref = (UIRefPane) item.getComponent();
				value = new DefaultConstEnum(ref.getRefPK(), ref
						.getRefShowName());
			}

			nochange = true;
			getBillModel().setValueAt(value, editingRow, item.getKey());

			// 自动增行
			if (getBillModel().getBillScrollPane().isAutoAddLine()
					&& editingRow == getBillModel().getRowCount() - 1) {
				// getBillModel().getBillScrollPane().addLine(false);
				getBillModel().getBillScrollPane().execAddRowAction(false);
				reflashPageInfo();
			}
			nochange = false;

			// set ID column value
			if (item.getMetaDataProperty() == null) {
				if (item.getIDColName() != null
						&& item.getIDColName().trim().length() != 0
						&& item.getDataType() == BillItem.UFREF) {
					UIRefPane ref = (UIRefPane) item.getComponent();
					BillItem idItem = getBillModel().getItemByKey(
							item.getIDColName());
					if (idItem != null)
						getBillModel().setValueAt(ref.getRefPK(), editingRow,
								idItem.getKey());
				}
			} else {
				// 加载关联项的值
				getBillModel().loadEditRelationItemValue(editingRow,
						item.getKey());
			}

			// 执行公式
			getBillModel().execEditFormulasByKey(editingRow, item.getKey());
			// 修改状态
			if (getBillModel().getRowState(editingRow) == BillModel.NORMAL)
				getBillModel().setRowState(editingRow, BillModel.MODIFICATION);
		}
		// process afterEdit
		if (bValueChanged) {
			// 触发编辑后事件
			if (el != null) {
				String key = item.getKey();
				/**
				 * 行编辑界面参照类型的字段数值改变时会触发编辑后事件，此时BillEditEvent中存储的旧值为参照的主键(String)，修改后的值则是DefaultConstEnum。此时会导致我们在编辑后事件获取时类型转换错误。
                                                   在卡片界面参照修改后则都是存储的主键。
                                                  代码如下：
                   1、行编辑界面编辑后事件构建（BillModelRowEditPanel  L762）
                    BillEditEvent ev = new BillEditEvent(item, oldValue, value,
						key, editingRow, BillItem.BODY);
				        ev.setTableCode(getBaseTab().getTabcode());
				     el.afterEdit(ev);
                 2、卡片界面编辑后事件构建（BillScrollPane  L720）
                    value = getValueAt(row, col);
                    BillEditEvent ev = new BillEditEvent(e.getSource(),
                    getEnumValue(oldValue), getEnumValue(value), key,row, BillItem.BODY);
                    ev.setTableCode(getTableCode());
                    el.afterEdit(ev);
				 */
				BillEditEvent ev = new BillEditEvent(item, oldValue, getEnumValue(value),
						key, editingRow, BillItem.BODY);
				ev.setTableCode(getBaseTab().getTabcode());
				el.afterEdit(ev);

				BillItem[] headtailitems = getBillItems();
				if (headtailitems != null) {
					for (int i = 0; i < headtailitems.length; i++) {
						setCompEditabled(headtailitems[i], getBillModel()
								.isCellEditable(editingRow, i));
					}
				}
			}
		}
		getBillModel().getBillScrollPane().setAutoAddLine(oldAutoAddLine);
	}
	
	private Object getEnumValue(Object o) {
		IConstEnum em = null;
		if (o instanceof IConstEnum)
			em = (IConstEnum) o;
		else
			return o;

		if (em == null)
			return null;

		return em.getValue();
	}

	private boolean isValueChanged(Object oldValue, Object value, BillItem item) {
		boolean bValueChanged = false;
		if (oldValue != null) {
			if (oldValue instanceof IBillObject) {
				if (((IBillObject) oldValue).compareTo(value) != IBillObject.EQUAL_OPTION)
					bValueChanged = true;
			} else if (item.getDataType() == IBillItem.UFREF
					&& item.getIDColName() != null
					&& !item.getIDColName().trim().equals("")) {
				oldValue = getBillModel().getValueAt(editingRow,
						item.getIDColName());
				UIRefPane ref = (UIRefPane) item.getComponent();
				String pk = ref.getRefPK();
				if (pk != oldValue) {
					if (pk == null || oldValue == null || !oldValue.equals(pk))
						bValueChanged = true;
				}
			} else if (!oldValue.toString().equals(value==null?value:value.toString()))
				bValueChanged = true;
		} else {
			if (value != null && !value.equals(""))
				bValueChanged = true;
		}
		return bValueChanged;
	}

	public BillTabVO getBaseTab() {
		return baseTab;
	}

	public void setBaseTab(BillTabVO baseTab) {
		this.baseTab = baseTab;
	}

	public int getEditingRow() {
		return editingRow;
	}

	public void setEditingRow(int editingRow) {

		setEditingRow(editingRow, false,false);
	}
    //when delete,donot need stopediting
	private void setEditingRow(int editingRow, boolean isDel,boolean isAdd) {
		if (! (isDel||isAdd) ) {
		try {
			stopEditing();
		} catch (Exception e) {
			// TODO: handle exception
			Logger.debug(e.getMessage());
		}		
			
		}

		int oldrow = this.editingRow;
		this.editingRow = editingRow;
		//// yxq 关于行编辑问题的修改
		if (editingRow == -1)
			return;
		getBillSclPane().getTable().setRowSelectionInterval(editingRow, editingRow);

		if (el != null) {
			BillEditEvent ev = new BillEditEvent(this, oldrow, editingRow);
			ev.setTableCode(getBaseTab().getTabcode());
			el.bodyRowChange(ev);
		}

		changePageInfo();

	}

	public void tableChanged(TableModelEvent e) {
		if (nochange)
			return;

		switch (e.getType()) {
		case TableModelEvent.INSERT:
			// setEditingRow(getBillModel().getRowCount() - 1);
			changePageInfo();
			break;
		case TableModelEvent.UPDATE:

			if (e.getFirstRow() >= editingRow && e.getLastRow() <= editingRow) {
				if (e.getColumn() == TableModelEvent.ALL_COLUMNS) {

				} else {
					setBillItemValue(getBillModel().getBodyItems()[e
							.getColumn()]);
				}
			}
			break;
		case TableModelEvent.DELETE:
			boolean isAutoAddLine = getBillModel().getBillScrollPane()
					.isAutoAddLine();
			getBillModel().getBillScrollPane().setAutoAddLine(false);
			if (getEditingRow() != -1) {
				if (getEditingRow() == 0) {
					if (getBillModel().getRowCount() > 0)
						setEditingRow(0, true,false);
					else
						setEditingRow(editingRow - 1, true,false);
				} else {

					if (getEditingRow() < getBillModel().getRowCount()) {
						setEditingRow(getEditingRow(), true,false);
					} else {
						setEditingRow(getEditingRow() - 1, true,false);
					}
				}
			}
			getBillModel().getBillScrollPane().setAutoAddLine(isAutoAddLine);
		default:
			break;
		}

	}

	/**
	 * 清除单据结构数据.
	 */
	private void clearViewData() {
		// 表头表尾
		BillItem[] headtailitems = getBillItems();
		if (headtailitems != null) {
			for (int i = 0; i < headtailitems.length; i++) {
				headtailitems[i].clearViewData();
			}
		}

	}

	private BillItem fistEditItem = null;

	private void reflashViewData() {
		if (editingRow == -1) {
			clearViewData();
			return;
		}

		BillItem[] headtailitems = getBillItems();
		if (headtailitems != null) {
			for (int i = 0; i < headtailitems.length; i++) {
				resetDigits(headtailitems[i]);
				setBillItemValue(headtailitems[i]);
				boolean isEdit = getBillModel().isCellEditable(editingRow, i);
				setCompEditabled(headtailitems[i], isEdit);
				if (fistEditItem == null && isEdit)
					fistEditItem = headtailitems[i];
			}
			requestFristFocus();
		}
	}

	private void resetDigits(BillItem billItem) {
		if (billItem.getDecimalListener() != null) {
			{
				BillItem sourceitem = getBillModel().getItemByKey(
						billItem.getDecimalListener().getSource());
				if (sourceitem != null) {
					Object id = sourceitem.converType((getBillModel())
							.getValueObjectAt(getEditingRow(), billItem
									.getDecimalListener().getSource()));
					if (id != null)
						billItem.setDecimalDigits(billItem.getDecimalListener()
								.getDecimalFromSource(getEditingRow(), id));
				}
			}
		}
	}

	public void requestFristFocus() {
		if (fistEditItem != null)
			fistEditItem.getComponent().requestFocus();
	}

	private void setBillItemValue(BillItem item) {
		Object value = null;

		if (item.getDataType() == BillItem.UFREF) {
			value = getBillModel().getValueAt(editingRow,
					item.getKey() + IBillItem.ID_SUFFIX);
		} else {
			value = getBillModel().getValueAt(editingRow, item.getKey());
			if (item.getDataType() == BillItem.COMBO)
				value = item.getConverter().convertToObject(BillItem.COMBO,
						value);
		}

		item.setValue(value);

	}

	private void setRefReturnName(BillItem item, UIRefPane ref) {
		if (item.getDataType() == BillItem.UFREF) {
			String[] formulas = item.getEditFormulas();
			if (formulas != null) {
				for (int i = 0; i < formulas.length; i++) {
					if (formulas[i].startsWith(item.getKey())) {
						ref.setReturnCode(false);
					}
				}
			}
		}
	}

	/**
	 * 添加编辑监听. 创建日期:(2001-3-23 2:20:34)
	 * 
	 * @param l
	 *            BillEditListener
	 */
	public void addEditListener(BillEditListener el) {
		this.el = el;
	}

	/**
	 * 添加编辑监听. 创建日期:(2001-3-23 2:20:34)
	 * 
	 * @param l
	 *            BillEditListener
	 */
	public void addEditListener2(BillEditListener2 el) {
		this.el2 = el;
	}

	private void changePageInfo() {
		pageinfo.setM_iCurrentPage(editingRow + 1);

		reflashPageInfo();

		reflashViewData();

		getPageNavigationBar().updateView();

	}

	private void reflashPageInfo() {
		if (getBillModel() != null) {
			int endrow = getBillModel().getRowCount();
			if (endrow > 0) {
				pageinfo.setM_iStartRow(1);
				if (editingRow == -1) {
					editingRow = 0;
					pageinfo.setM_iCurrentPage(1);
				}
			} else
				pageinfo.setM_iStartRow(0);

			pageinfo.setM_iEndRow(endrow);
			pageinfo.setM_iTotalPage(endrow);
			pageinfo.setM_iTotalRow(endrow);
		}

		onPageRowsCountChange(pageinfo);

		getPageNavigationBar().updateView();
	}

	public void onPageRowAdd(PageInfo pageinfo) {
		if (getBillModel() != null) {
			if (getBillModel().getBillScrollPane().getBillTableAction(
					BillScrollPane.ADDLINE) != null) {
				ActionEvent ae = new ActionEvent(getBillModel()
						.getBillScrollPane().getTable(),
						BillScrollPane.ADDLINE, "AddLine");
				getBillModel().getBillScrollPane().getBillTableAction(
						BillScrollPane.ADDLINE).actionPerformed(ae);

			} else
				getBillModel().getBillScrollPane().addLine();

			setEditingRow(getBillModel().getRowCount() - 1,false,true);
			onPageRowsCountChange(pageinfo);
		}

	}

	public void onPageRowDelete(PageInfo pageinfo) {
		if (getBillModel() != null && getEditingRow() > -1
				&& getEditingRow() < getBillModel().getRowCount()) {
			if (getBillModel().getBillScrollPane().getBillTableAction(
					BillScrollPane.DELLINE) != null) {
				ActionEvent ae = new ActionEvent(getBillModel()
						.getBillScrollPane().getTable(),
						BillScrollPane.DELLINE, "DeleteLine");
				getBillModel().getBillScrollPane().getBillTableAction(
						BillScrollPane.DELLINE).actionPerformed(ae);

			} else
				getBillModel().getBillScrollPane().delLine(
						new int[] { getEditingRow() });

			onPageRowsCountChange(pageinfo);
			if (pageinfo.getM_iTotalPage()==1){
				reflashViewData();
				setItemEnabled(false);
			}
		}else{
			reflashViewData();
		}
	}

	public PageInfo getPageInfo() {
		return pageinfo;
	}

	public void firstPage(PageInfo pageinfo) {
		if (getBillModel() != null && getBillModel().getRowCount() > -1) {
			setEditingRow(0);
		}
	}

	public void goPage(PageInfo pageinfo, int pageindex) {
		if (getBillModel() != null && getBillModel().getRowCount() > -1
				&& pageindex <= getBillModel().getRowCount()) {
			setEditingRow(pageindex - 1);
		}
	}

	public void lastPage(PageInfo pageinfo) {
		if (getBillModel() != null && getBillModel().getRowCount() > -1) {
			setEditingRow(getBillModel().getRowCount() - 1);
		}
	}

	public void nextPage(PageInfo pageinfo) {
		if (getBillModel() != null && getBillModel().getRowCount() > -1
				&& getEditingRow() < getBillModel().getRowCount() - 1) {

			setEditingRow(getEditingRow() + 1);
		}
	}

	public void onPageRowsCountChange(PageInfo pageinfo) {
		if (pageinfo.getM_iTotalPage() == 0)
			setItemEnabled(false);
		else
			setItemEnabled(true);
	}

	public void priorPage(PageInfo pageinfo) {
		if (getBillModel() != null && getBillModel().getRowCount() > -1
				&& getEditingRow() > 0) {
			setEditingRow(getEditingRow() - 1);
		}
	}

	public void refreshPage(PageInfo pageinfo) {

	}

	public BillTabVO[] getShareTabs() {
		return shareTabs;
	}

	public void setShareTabs(BillTabVO[] shareTabs) {
		this.shareTabs = shareTabs;
	}

	public UIScrollPane getEditPanel() {

		if (editpanel == null) {
			editpanel = new UIScrollPane();

			// UIPanel pnl = new UIPanel(new BillCardPanelFlowLayout());
			//			
			// UIPanel basepanel = creatEditPanel(getBaseTab().getTabcode());
			//			
			// pnl.add(basepanel);
			//
			// if(getShareTabs() != null && getShareTabs().length > 0){
			//			
			// basepanel.setBorder(BorderFactory.createTitledBorder(getBaseTab().getTabname()));
			//				
			// for (int i = 0; i < getShareTabs().length; i++) {
			// UIPanel sharepanel =
			// creatEditPanel(getShareTabs()[i].getTabcode());
			// if(sharepanel != null){
			// sharepanel.setBorder(BorderFactory.createTitledBorder(getShareTabs()[i].getTabname()));
			// pnl.add(sharepanel);
			// }
			// }
			// }

			UIPanel pnl = new UIPanel(new BillCardPanelFlowLayout());
//			pnl.setFocusCycleRoot(true);
			// 创建主panel
			BillAreaPanel maingpnl = getBillAreaPanel(getBaseTab());

			if (maingpnl != null) {
				pnl.add(maingpnl);
			}

			// 创建分组Panel
			BillTabVO[] subgroups = getShareTabs();

			if (subgroups != null) {
				for (int i = 0; i < subgroups.length; i++) {
					BillTabVO tabvo = subgroups[i];
					BillAreaPanel gpnl = getBillAreaPanel(tabvo);
					if (gpnl != null) {
						gpnl.setShowGroupTitle(true);

						pnl.add(gpnl);
					}
				}
			}

			editpanel.setViewportView(pnl);
		}

		return editpanel;
	}

	private BillAreaPanel getBillAreaPanel(BillTabVO vo) {
		BillItem[] showItems;

		showItems = getShowItems(vo.getTabcode());

		if (showItems == null || showItems.length == 0)
			return null;

		for (int i = 0; i < showItems.length; i++) {
			initBillItem(showItems[i]);
			initConnection(showItems[i]);
		}

		BillAreaPanel pnl = new BillAreaPanel(editpanel, vo, showItems);

		return pnl;
	}

	private void addShowItem(BillItem item) {
		hasItemIsShow.add(item.getKey());
	}

	private boolean isPanelShowItem(BillItem item) {
		if (hasItemIsShow.contains(item.getKey()))
			return true;

		return false;
	}

	private BillScrollPane getBillSclPane() {
		return billSclPane;
	}

	private boolean isEditButtonVisible(int op) {
		Action action = getBillSclPane().getBillTableAction(op);

		return (action == null ? false : action.isEnabled());

	}

	private void stopEditing() {

		for (int i = 0; i < getBillItems().length; i++) {
//			if (getBillItems()[i].getItemEditor().getComponent().isFocusOwner()) {
			// yxq 关于行编辑问题的修改
			if (hasItemIsShow.contains(getBillItems()[i].getKey())){
				
				getBillItems()[i].getItemEditor().stopEditing();
				editingStopped(getBillItems()[i], getBillItems()[i]
						.getValueObject());
			}

		}
	}
}
