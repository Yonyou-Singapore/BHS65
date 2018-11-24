package nc.ui.so.m32.billui.action;

import java.awt.event.ActionEvent;
import java.util.List;

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
		List<SaleInvoiceBVO> settlReturnVos = query.settle(aggvo);

		if(settlReturnVos != null
				&& settlReturnVos.size() > 0){
			UFDouble oldQty = UFDouble.ZERO_DBL;
			UFDouble oldPrice = UFDouble.ZERO_DBL;
			UFDouble oldAmt = UFDouble.ZERO_DBL;
			UFDouble settlQty = UFDouble.ZERO_DBL;
			UFDouble settlPrice = UFDouble.ZERO_DBL;
			UFDouble settlAmt = UFDouble.ZERO_DBL;
			String rowno = null;
			String part = null;
			String oldDescription = null;
			String description = null;
			BillModel billmodel = getEditor().getBillCardPanel().getBillModel();
			for (int i = 0; i < billmodel.getRowCount(); i++) {
				if (billmodel.getValueAt(i, StoreSettleUtil.FLD_DURATION_UNIT) != null) {
					rowno = (String) billmodel.getValueAt(i, SaleInvoiceBVO.CROWNO);
					oldQty = (UFDouble) billmodel.getValueObjectAt(i,
							SaleInvoiceBVO.NASTNUM);
					oldPrice = (UFDouble) billmodel.getValueObjectAt(i,
							SaleInvoiceBVO.NQTORIGPRICE);
					oldAmt = (UFDouble) billmodel.getValueObjectAt(i,
							SaleInvoiceBVO.NORIGMNY);
					oldDescription = (String) billmodel.getValueObjectAt(i,
							"cc");
					for(SaleInvoiceBVO bvo : settlReturnVos){
						if(rowno.equals(bvo.getCrowno())){
							settlQty = bvo.getNastnum();
							settlPrice = bvo.getNqtorigprice();
							settlAmt = bvo.getNorigmny();
							description = bvo.getVbdef2();
							if(settlQty != null){
								CardBodyAfterEditEvent event = new CardBodyAfterEditEvent(
										getEditor().getBillCardPanel(),
										new BillEditEvent(billmodel
												.getItemByKey(SaleInvoiceBVO.NASTNUM), oldQty,
												settlQty, SaleInvoiceBVO.NASTNUM, i, 1), oldQty);
								billmodel.setValueAt(settlQty, i, SaleInvoiceBVO.NASTNUM);
								new BodyAfterEditHandler().handleAppEvent(event);
							}
							if(settlPrice != null){
								CardBodyAfterEditEvent event = new CardBodyAfterEditEvent(
										getEditor().getBillCardPanel(),
										new BillEditEvent(billmodel
												.getItemByKey(SaleInvoiceBVO.NQTORIGPRICE), oldPrice,
												settlPrice, SaleInvoiceBVO.NQTORIGPRICE, i, 1), oldPrice);
								billmodel.setValueAt(settlPrice, i, SaleInvoiceBVO.NQTORIGPRICE);
								new BodyAfterEditHandler().handleAppEvent(event);
							}
							if(settlAmt != null){
								CardBodyAfterEditEvent event = new CardBodyAfterEditEvent(
										getEditor().getBillCardPanel(),
										new BillEditEvent(billmodel
												.getItemByKey(SaleInvoiceBVO.NORIGMNY), oldAmt,
												settlAmt, SaleInvoiceBVO.NORIGMNY, i, 1), oldAmt);
								billmodel.setValueAt(settlAmt, i, SaleInvoiceBVO.NORIGMNY);
								new BodyAfterEditHandler().handleAppEvent(event);
							}
							if(description != null){
								CardBodyAfterEditEvent event = new CardBodyAfterEditEvent(
										getEditor().getBillCardPanel(),
										new BillEditEvent(billmodel
												.getItemByKey("cc"), oldDescription,
												description, "cc", i, 1), oldDescription);
								billmodel.setValueAt(description, i, "cc");
								billmodel.setValueAt(description, i, SaleInvoiceBVO.VBDEF2);
								new BodyAfterEditHandler().handleAppEvent(event);
							}
						}
					}
				}
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
