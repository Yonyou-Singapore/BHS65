package nc.ui.so.m32.billui.action;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.m32.ISaleInvoiceMaintain;
import nc.ui.pub.bill.BillEditEvent;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.so.m32.billui.editor.bodyevent.BodyAfterEditHandler;
import nc.ui.so.m32.billui.model.SaleInvoiceManageModel;
import nc.ui.so.m32.billui.view.SaleInvoiceEditor;
import nc.ui.uif2.NCAction;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.m32.util.StoreSettleUtil;

/**
 */
public class SettleAction extends NCAction {

	private SaleInvoiceEditor editor;
	private SaleInvoiceManageModel model;

	public SettleAction() {
		super();
		setBtnName("Settle");
		setCode("settleAction");
	}

	@SuppressWarnings("restriction")
	@Override
	public void doAction(ActionEvent arg0) throws Exception {
		SaleInvoiceVO aggvo = (SaleInvoiceVO) this.editor.getValue();

		SaleInvoiceHVO hvo = aggvo.getParentVO();
		// 本次结算开始日期
		String rentFrom = (String) hvo
				.getAttributeValue(StoreSettleUtil.FLD_RENTFROM);
		// 本次结算结束日期
		String rentTo = (String) hvo
				.getAttributeValue(StoreSettleUtil.FLD_RENTTO);
		if (rentFrom == null || rentTo == null) {
			ExceptionUtils
					.wrappBusinessException("Please input [Rent From],[Rent To].");
		}
		
		//计算租金
		ISaleInvoiceMaintain query = NCLocator.getInstance().lookup(
				ISaleInvoiceMaintain.class);
		UFDouble settlQty = query.settle(aggvo);

		UFDouble oldQty = UFDouble.ZERO_DBL;
		BillModel billmodel = getEditor().getBillCardPanel().getBillModel();
		for (int i = 0; i < billmodel.getRowCount(); i++) {
			if (billmodel.getValueAt(i, StoreSettleUtil.FLD_DURATION) != null) {
				oldQty = (UFDouble) billmodel.getValueObjectAt(i,
						SaleInvoiceBVO.NASTNUM);
				CardBodyAfterEditEvent event = new CardBodyAfterEditEvent(
						getEditor().getBillCardPanel(),
						new BillEditEvent(billmodel
								.getItemByKey(SaleInvoiceBVO.NASTNUM), oldQty,
								settlQty, SaleInvoiceBVO.NASTNUM, i, 1), oldQty);
				billmodel.setValueAt(settlQty, i, SaleInvoiceBVO.NASTNUM);
				new BodyAfterEditHandler().handleAppEvent(event);
				break;
			}
		}
	}

	public SaleInvoiceEditor getEditor() {
		return editor;
	}

	public void setEditor(SaleInvoiceEditor editor) {
		this.editor = editor;
	}

	public SaleInvoiceManageModel getModel() {
		return model;
	}

	public void setModel(SaleInvoiceManageModel model) {
		this.model = model;
	}
}
