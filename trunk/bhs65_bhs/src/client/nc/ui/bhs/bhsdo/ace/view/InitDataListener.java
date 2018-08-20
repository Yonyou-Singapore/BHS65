package nc.ui.bhs.bhsdo.ace.view;

import nc.funcnode.ui.FuncletInitData;
import nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.uif2.UIState;
import nc.vo.bhs.bhsdo.AggBhsDoVO;

/**
 * Do单据打开初始化。
 * @author Thinkpad
 * 20170419
 */
public class InitDataListener extends DefaultFuncNodeInitDataListener{

	
	@Override
	public void initData(FuncletInitData data) {
		// TODO 自动生成的方法存根
		if(data == null){
			super.initData(data);
		}
		//处理通过销售订单快捷打开过来。
		else if(data != null && data.getInitType() == 9){
			AggBhsDoVO[] aggvos = (AggBhsDoVO[]) data.getInitData();
			if(aggvos.length ==  1 && aggvos[0].getParentVO().getBillid() == null ||aggvos[0].getParentVO().getBillid().equals("") ){
				getModel().initModel(null);
				getAutoShowUpComponent().showMeUp();
				getModel().setUiState(UIState.ADD);
				
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("pk_org", aggvos[0].getParentVO().getPk_org());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("pk_org_v", aggvos[0].getParentVO().getPk_org_v());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("pk_group", aggvos[0].getParentVO().getPk_group());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("pk_saleorder", aggvos[0].getParentVO().getPk_saleorder());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("saleorderbillno", aggvos[0].getParentVO().getSaleorderbillno());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("dojobno", aggvos[0].getParentVO().getSaleorderbillno());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("dbilldate", aggvos[0].getParentVO().getDbilldate());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("tocustomer", aggvos[0].getParentVO().getTocustomer());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("toaddress", aggvos[0].getParentVO().getToaddress());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("customerpono", aggvos[0].getParentVO().getCustomerpono());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("fromcorp", aggvos[0].getParentVO().getFromcorp());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("fromaddress", aggvos[0].getParentVO().getFromaddress());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("fromcode", aggvos[0].getParentVO().getFromcode());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("tocorp", aggvos[0].getParentVO().getTocorp());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("toaddress", aggvos[0].getParentVO().getToaddress());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("tocode", aggvos[0].getParentVO().getTocode());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("attnto", aggvos[0].getParentVO().getAttnto());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("contactno", aggvos[0].getParentVO().getContactno());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("contactperson", aggvos[0].getParentVO().getContactperson());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("subject", aggvos[0].getParentVO().getSubject());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("jobinstruction", aggvos[0].getParentVO().getJobinstruction());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("moveinstruction", aggvos[0].getParentVO().getMoveinstruction());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("ref", aggvos[0].getParentVO().getRef());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("dovoyage", aggvos[0].getParentVO().getDovoyage());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("dovessel", aggvos[0].getParentVO().getDovessel());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("micapno", aggvos[0].getParentVO().getMicapno());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("oemtoolno", aggvos[0].getParentVO().getOemtoolno());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("toolidlid", aggvos[0].getParentVO().getToolidlid());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("machinemake", aggvos[0].getParentVO().getMachinemake());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("machinemodel", aggvos[0].getParentVO().getMachinemodel());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("machinesubmodel", aggvos[0].getParentVO().getMachinesubmodel());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("m2", aggvos[0].getParentVO().getM2());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("m3", aggvos[0].getParentVO().getM3());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("kg", aggvos[0].getParentVO().getKg());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("pkgs", aggvos[0].getParentVO().getPkgs());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("chargeableweight", aggvos[0].getParentVO().getChargeableweight());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("netweight", aggvos[0].getParentVO().getNetweight());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("largestheight", aggvos[0].getParentVO().getLargestheight());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("largestlength", aggvos[0].getParentVO().getLargestlength());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("largestwidth", aggvos[0].getParentVO().getLargestwidth());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("largestweight", aggvos[0].getParentVO().getLargestweight());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("lcrate", aggvos[0].getParentVO().getLcrate());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("hcrate", aggvos[0].getParentVO().getHcrate());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("wcrate", aggvos[0].getParentVO().getWcrate());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("kcrate", aggvos[0].getParentVO().getKcrate());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("awb", aggvos[0].getParentVO().getAwb());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("deccription", aggvos[0].getParentVO().getDeccription());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().execHeadLoadFormulas();
			}else{
				this.getModel().initModel(aggvos);
			}
		}
	}
}
