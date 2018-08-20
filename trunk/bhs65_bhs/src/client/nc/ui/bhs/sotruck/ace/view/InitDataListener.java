package nc.ui.bhs.sotruck.ace.view;

import nc.funcnode.ui.FuncletInitData;
import nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.uif2.UIState;
import nc.vo.bhs.sotruck.AggSoTruckHVO;

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
			AggSoTruckHVO[] aggvos = (AggSoTruckHVO[]) data.getInitData();
			if(aggvos.length ==  1 && aggvos[0].getParentVO().getBillid() == null ||aggvos[0].getParentVO().getBillid().equals("") ){
				getAutoShowUpComponent().showMeUp();
				getModel().initModel(null);
				getModel().setUiState(UIState.ADD);
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("csaleorderid", aggvos[0].getParentVO().getCsaleorderid());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("csaleordercode", aggvos[0].getParentVO().getCsaleordercode());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("dbilldate", aggvos[0].getParentVO().getDbilldate());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("tstransactiondate", aggvos[0].getParentVO().getDbilldate());
				
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("surveyno", aggvos[0].getParentVO().getSurveyno());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("surveyby", aggvos[0].getParentVO().getSurveyby());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("surveydate", aggvos[0].getParentVO().getSurveydate());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("customerpono", aggvos[0].getParentVO().getCustomerpono());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("fromcorp", aggvos[0].getParentVO().getFromcorp());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("taapickupfromaddress", aggvos[0].getParentVO().getTaapickupfromaddress());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("taapickupfromcode", aggvos[0].getParentVO().getTaapickupfromcode());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("tocorp", aggvos[0].getParentVO().getTocorp());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("tacdelivertoaddress", aggvos[0].getParentVO().getTacdelivertoaddress());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("tacdelivertocode", aggvos[0].getParentVO().getTacdelivertocode());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("attnto", aggvos[0].getParentVO().getAttnto());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("taccontactpersonphone", aggvos[0].getParentVO().getTaccontactpersonphone());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("taccontactperson", aggvos[0].getParentVO().getTaccontactperson());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("subject", aggvos[0].getParentVO().getSubject());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("tacommoninstruction", aggvos[0].getParentVO().getTacommoninstruction());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("ref", aggvos[0].getParentVO().getRef());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("tsimportvoyage", aggvos[0].getParentVO().getTsimportvoyage());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("tsimportvessel", aggvos[0].getParentVO().getTsimportvessel());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("taaream2", aggvos[0].getParentVO().getTaaream2());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("avolume", aggvos[0].getParentVO().getTavolume());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("tagrossweight", aggvos[0].getParentVO().getTagrossweight());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("tatotalofpkgs", aggvos[0].getParentVO().getTatotalofpkgs());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("tachargableweight", aggvos[0].getParentVO().getTachargableweight());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("netweight", aggvos[0].getParentVO().getNetweight());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("talengthcm", aggvos[0].getParentVO().getTalengthcm());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("tawidthcm", aggvos[0].getParentVO().getTawidthcm());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("taheightcm", aggvos[0].getParentVO().getTaheightcm());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("largestweight", aggvos[0].getParentVO().getLargestweight());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("talcrateno", aggvos[0].getParentVO().getTalcrateno());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("tawcrateno", aggvos[0].getParentVO().getTawcrateno());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("tahcrateno", aggvos[0].getParentVO().getTahcrateno());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("kcrate", aggvos[0].getParentVO().getKcrate());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("taawbno", aggvos[0].getParentVO().getTaawbno());
				
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("pk_transtype", aggvos[0].getParentVO().getPk_transtype());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("transtypecode", aggvos[0].getParentVO().getTranstypecode());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().execHeadLoadFormulas();
				
			}else{
				this.getModel().initModel(aggvos);
			}
		}
	}
}
