package nc.ui.bhs.sotruck.ace.action;

import java.awt.event.ActionEvent;

import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.uif2.NCAction;
import nc.vo.bhs.sotruck.AggSoTruckHVO;
import nc.vo.bhs.sotruck.SoTruckFoneBVO;
import nc.vo.bhs.sotruck.SoTruckTEthBVO;
import nc.vo.bhs.sotruck.SoTruckTthBVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 车辆排导出excel
 * @author Thinkpad
 * @date 2017-4-16
 */
public class ExcelExportAction extends NCAction{
	
	private ShowUpableBillForm editor;
	
	private BillManageModel model;
	
	public ExcelExportAction() {
		// TODO 自动生成的构造函数存根
		setCode("excelexportaction");
		setBtnName("Excel Export");
	}

	@Override
	public void doAction(ActionEvent actionevent) throws Exception {
		// TODO 自动生成的方法存根
		Object obj = getModel().getSelectedData();
		if(obj == null ){
			ExceptionUtils.wrappBusinessException("未选中数据，不能导出！");
		}
		AggSoTruckHVO aggvo = (AggSoTruckHVO) obj;
		SoTruckTEthBVO[] ethbvos = (SoTruckTEthBVO[]) aggvo.getChildren(SoTruckTEthBVO.class);
		SoTruckTthBVO[] tthbvos = (SoTruckTthBVO[]) aggvo.getChildren(SoTruckTthBVO.class);
		SoTruckFoneBVO[] fonebvos = (SoTruckFoneBVO[]) aggvo.getChildren(SoTruckFoneBVO.class);
		if((ethbvos == null || ethbvos.length == 0) && 
				(tthbvos == null || tthbvos.length == 0) && (fonebvos == null || fonebvos.length == 0) ){
			ExceptionUtils.wrappBusinessException("选择的数据不包含车辆排车信息，不能导出！");
		}
		
	}
	/**
	 * 执行导出
	 * @author Thinkpad
	 * @date 2017-4-16
	 */
	private void doexport(SoTruckTEthBVO[] ethbvos,SoTruckTthBVO[] tthbvos,SoTruckFoneBVO[] fonebvos){
		
	}

	public ShowUpableBillForm getEditor() {
		return editor;
	}

	public void setEditor(ShowUpableBillForm editor) {
		this.editor = editor;
	}

	public BillManageModel getModel() {
		return model;
	}

	public void setModel(BillManageModel model) {
		this.model = model;
	}
	
	
	

}
