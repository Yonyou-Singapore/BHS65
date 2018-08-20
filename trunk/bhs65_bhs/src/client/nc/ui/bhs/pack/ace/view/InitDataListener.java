package nc.ui.bhs.pack.ace.view;

import nc.funcnode.ui.FuncletInitData;
import nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.uif2.UIState;
import nc.vo.bhs.pack.AggSoOrderPackVO;

/**
 * Pack单据打开初始化。
 * @author Thinkpad
 * 20170308
 */
public class InitDataListener extends DefaultFuncNodeInitDataListener{

	
	@Override
	public void initData(FuncletInitData data) {
		
		if(data == null){
			super.initData(data);
		}else{
			AggSoOrderPackVO[] aggvos = (AggSoOrderPackVO[]) data.getInitData();
			if(aggvos.length ==  1 && aggvos[0].getParentVO().getBillid() == null ||aggvos[0].getParentVO().getBillid().equals("") ){
				getAutoShowUpComponent().showMeUp();
				getModel().initModel(null);
				getModel().setUiState(UIState.ADD);
				//从销售订单带出来的默认值
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("csaleorderid", aggvos[0].getParentVO().getCsaleorderid());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("csaleordercode", aggvos[0].getParentVO().getCsaleordercode());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("dbilldate", aggvos[0].getParentVO().getDbilldate());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("surveyno", aggvos[0].getParentVO().getSurveyno());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("surveyby", aggvos[0].getParentVO().getSurveyby());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("surveydate", aggvos[0].getParentVO().getSurveydate());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("customerpono", aggvos[0].getParentVO().getCustomerpono());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("attnto", aggvos[0].getParentVO().getAttnto());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("contactno", aggvos[0].getParentVO().getContactno());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("contactperson", aggvos[0].getParentVO().getContactperson());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("subject", aggvos[0].getParentVO().getSubject());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("jobinstruction", aggvos[0].getParentVO().getJobinstruction());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("ref", aggvos[0].getParentVO().getRef());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("jobtype", aggvos[0].getParentVO().getJobtype());
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
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().execHeadLoadFormulas();
			}else{
				this.getModel().initModel(aggvos);
			}
		}
	}
}
