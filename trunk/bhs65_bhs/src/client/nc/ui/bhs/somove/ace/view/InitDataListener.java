package nc.ui.bhs.somove.ace.view;

import nc.funcnode.ui.FuncletInitData;
import nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.uif2.UIState;
import nc.vo.bhs.somove.AggSoMoveHVO;

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
			AggSoMoveHVO[] aggvos = (AggSoMoveHVO[]) data.getInitData();
			
			if(aggvos.length ==  1 && aggvos[0].getParentVO().getBillid() == null ||aggvos[0].getParentVO().getBillid().equals("") ){
				getAutoShowUpComponent().showMeUp();
				getModel().initModel(null);
				getModel().setUiState(UIState.ADD);
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("csaleorderid", aggvos[0].getParentVO().getCsaleorderid());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("csaleordercode", aggvos[0].getParentVO().getCsaleordercode());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("dbilldate", aggvos[0].getParentVO().getDbilldate());
				//从销售订单带出来的默认值
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("surveyno", aggvos[0].getParentVO().getSurveyno());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("surveyby", aggvos[0].getParentVO().getSurveyby());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("surveydate", aggvos[0].getParentVO().getSurveydate());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("customerpono", aggvos[0].getParentVO().getCustomerpono());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("fromcorp", aggvos[0].getParentVO().getFromcorp());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("fromaddress", aggvos[0].getParentVO().getFromaddress());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("fromcode", aggvos[0].getParentVO().getFromcode());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("tocorp", aggvos[0].getParentVO().getTocorp());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("toaddress", aggvos[0].getParentVO().getToaddress());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("tocode", aggvos[0].getParentVO().getTocode());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("attnto", aggvos[0].getParentVO().getAttnto());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("contactpersphone", aggvos[0].getParentVO().getContactpersphone());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("contactperson", aggvos[0].getParentVO().getContactperson());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("subject", aggvos[0].getParentVO().getSubject());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("jobinstruction", aggvos[0].getParentVO().getJobinstruction());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("specialinstruction", aggvos[0].getParentVO().getSpecialinstruction());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("ref", aggvos[0].getParentVO().getRef());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("jobtype", aggvos[0].getParentVO().getJobtype());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("micapnumbermachineid", aggvos[0].getParentVO().getMicapnumbermachineid());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("oemtoolno", aggvos[0].getParentVO().getOemtoolno());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("lidnumber", aggvos[0].getParentVO().getLidnumber());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("machinemake", aggvos[0].getParentVO().getMachinemake());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("machinemodel", aggvos[0].getParentVO().getMachinemodel());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("machinesubmodel", aggvos[0].getParentVO().getMachinesubmodel());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("volumem3", aggvos[0].getParentVO().getVolumem3());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("grossweight", aggvos[0].getParentVO().getGrossweight());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("totalofpkgs", aggvos[0].getParentVO().getTotalofpkgs());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("chargeableweight", aggvos[0].getParentVO().getChargeableweight());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("netweight", aggvos[0].getParentVO().getNetweight());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("lengthcm", aggvos[0].getParentVO().getLengthcm());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("widthcm", aggvos[0].getParentVO().getWidthcm());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("heightcm", aggvos[0].getParentVO().getHeightcm());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("largestweight", aggvos[0].getParentVO().getLargestweight());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("crate1", aggvos[0].getParentVO().getCrate1());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("crate2", aggvos[0].getParentVO().getCrate2());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("crate3", aggvos[0].getParentVO().getCrate3());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("kcrate", aggvos[0].getParentVO().getKcrate());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().execHeadLoadFormulas();
			}else{
				this.getModel().initModel(aggvos);
			}
		}
	}
}
