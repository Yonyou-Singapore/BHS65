package nc.ui.bhs.sostore.ace.view;

import nc.funcnode.ui.FuncletInitData;
import nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.uif2.UIState;
import nc.vo.bhs.sostore.AggSoStoreHVO;

/**
 * Move单据打开初始化。
 * @author Thinkpad
 * 20170308
 */
public class InitDataListener extends DefaultFuncNodeInitDataListener{

	
	@Override
	public void initData(FuncletInitData data) {
		// TODO 自动生成的方法存根
		if(data == null){
			super.initData(data);
		}
		//处理通过销售订单连接过来。
		else if(data != null && data.getInitType() == 9){
			AggSoStoreHVO[] aggvos = (AggSoStoreHVO[]) data.getInitData();
			
			if(aggvos.length ==  1 && aggvos[0].getParentVO().getBillid() == null ||aggvos[0].getParentVO().getBillid().equals("") ){
				getAutoShowUpComponent().showMeUp();
				getModel().initModel(null);
				getModel().setUiState(UIState.ADD);
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("csaleorderid", aggvos[0].getParentVO().getCsaleorderid());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("csaleordercode", aggvos[0].getParentVO().getCsaleordercode());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("dbilldate", aggvos[0].getParentVO().getDbilldate());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("pono", aggvos[0].getParentVO().getPono());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("attnto", aggvos[0].getParentVO().getAttnto());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("contactno", aggvos[0].getParentVO().getContactno());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("contactperson", aggvos[0].getParentVO().getContactperson());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("subject", aggvos[0].getParentVO().getSubject());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("jobinstruction", aggvos[0].getParentVO().getJobinstruction());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("ref", aggvos[0].getParentVO().getRef());
				
				//add chenth 20180825
//				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("def19", aggvos[0].getParentVO().getDef19());
//				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("def17", aggvos[0].getParentVO().getDef17());
//				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("def18", aggvos[0].getParentVO().getDef18());
//				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("def16", aggvos[0].getParentVO().getDef16());
//				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("def2", aggvos[0].getParentVO().getDef2());
//				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("def9", aggvos[0].getParentVO().getDef9());
//				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("def10", aggvos[0].getParentVO().getDef10());
//				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("def11", aggvos[0].getParentVO().getDef11());
//				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("def12", aggvos[0].getParentVO().getDef12());
				//add chenth 20180903
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("micapno", aggvos[0].getParentVO().getMicapno());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("oemtoolno", aggvos[0].getParentVO().getOemtoolno());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("toolidlid", aggvos[0].getParentVO().getToolidlid());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("machinemake", aggvos[0].getParentVO().getMachinemake());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("machinemodel", aggvos[0].getParentVO().getMachinemodel());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("machinesubmodel", aggvos[0].getParentVO().getMachinesubmodel());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("suppliername", aggvos[0].getParentVO().getSuppliername());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("summarym2", aggvos[0].getParentVO().getSummarym2());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("summarym3", aggvos[0].getParentVO().getSummarym3());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("summarykg", aggvos[0].getParentVO().getSummarykg());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("summarypkgs", aggvos[0].getParentVO().getSummarypkgs());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("largestheight", aggvos[0].getParentVO().getLargestheight());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("largestlength", aggvos[0].getParentVO().getLargestlength());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("largestwidth", aggvos[0].getParentVO().getLargestwidth());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("largestweight", aggvos[0].getParentVO().getLargestweight());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("lcrate", aggvos[0].getParentVO().getLcrate());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("hcrate", aggvos[0].getParentVO().getHcrate());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("wcrate", aggvos[0].getParentVO().getWcrate());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("kcrate", aggvos[0].getParentVO().getKcrate());
				
				//带入表体
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().getBillModel().setBodyDataVO(aggvos[0].getChildrenVO());
				
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().execHeadLoadFormulas();
				
				
			}else{
				this.getModel().initModel(aggvos);
			}
		}
	}
}
