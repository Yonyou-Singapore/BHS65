package nc.ui.so.m30.billui.action;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.desktop.ui.WorkbenchEnvironment;
import nc.funcnode.ui.FuncletInitData;
import nc.funcnode.ui.FuncletWindowLauncher;
import nc.itf.so.m30.self.ISaleOrderMaintain;
import nc.itf.uap.bbd.func.IFuncRegisterQueryService;
import nc.ui.pub.linkoperate.ILinkType;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.so.m30.billui.view.SaleOrderBillForm;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.UIState;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.sm.funcreg.FuncRegisterVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
/**
 * 订单打开build\pack\move\stuck\restore 节点基类
 * @author Thinkpad
 * 20170305
 *
 */
public class SaleOrderBaseAction extends NCAction {


	private SaleOrderBillForm editor;

	private BillManageModel model;
	
	private String nodecode;

	@Override
	public void doAction(ActionEvent arg0) throws Exception {
		// TODO 自动生成的方法存根
		SaleOrderVO aggvo = (SaleOrderVO) getModel().getSelectedData();
		SaleOrderVO newaggvo = qryOrderVO(aggvo.getParentVO().getCsaleorderid());
		AbstractBill[] bills = qryBackwardVO(newaggvo.getParentVO());
		openCtDLG(bills);
	}

	private void openCtDLG(Object aggvo) {

		FuncletInitData initData = new FuncletInitData();
		initData.setInitType(ILinkType.LINK_TYPE_QUERY);
		initData.setInitData(aggvo);
		initData.setInitType(9);

		IFuncRegisterQueryService service = NCLocator.getInstance().lookup(
				IFuncRegisterQueryService.class);
		FuncRegisterVO funvo = null;
		try {
			funvo = service.queryFunctionByCode(getNodecode());
		} catch (BusinessException e) {
			ExceptionUtils.wrappException(e);
		}
		
		FuncletWindowLauncher.openFuncNodeInTabbedPane(WorkbenchEnvironment
				.getInstance().getWorkbench(), funvo, initData, null, false);
	}
	
	
	/**
	 * 更新so上的最新信息 20171020
	 */
	private SaleOrderVO qryOrderVO(String csaleorderid) {
		ISaleOrderMaintain service = NCLocator.getInstance().lookup(
				ISaleOrderMaintain.class);
		try {
			SaleOrderVO[] ordervos = service
					.querySaleorder(new String[] { csaleorderid });
			return ordervos[0];
		} catch (BusinessException e) {
			// TODO 自动生成的 catch 块
			ExceptionUtils.wrappBusinessException(""+e.getMessage());
		}
		return null;
	}
	

	public AbstractBill[] qryBackwardVO(SaleOrderHVO headvo)
			throws BusinessException {
		return null;
	}

	public SaleOrderBillForm getEditor() {
		return this.editor;
	}

	public BillManageModel getModel() {
		return this.model;
	}

	public void setEditor(SaleOrderBillForm editor) {
		this.editor = editor;
	}

	public void setModel(BillManageModel model) {
		this.model = model;
		this.model.addAppEventListener(this);
	}

	@Override
	protected boolean isActionEnable() {

		int selectRow = getModel().getSelectedOperaDatas() == null?0:getModel().getSelectedOperaDatas().length;

		boolean isEnable = this.model.getUiState() == UIState.NOT_EDIT
				&& selectRow > 0;

		return isEnable;
	}

	public String getNodecode() {
		return nodecode;
	}

	public void setNodecode(String nodecode) {
		this.nodecode = nodecode;
	}
	
	
	
}
