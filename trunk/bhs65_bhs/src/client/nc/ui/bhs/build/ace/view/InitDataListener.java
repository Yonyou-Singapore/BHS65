package nc.ui.bhs.build.ace.view;

import nc.funcnode.ui.FuncletInitData;
import nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.uif2.UIState;
import nc.vo.bhs.build.AggSoOrderBuildHVO;

/**
 * build���ݴ򿪳�ʼ����
 * @author Thinkpad
 * 20170308
 */
public class InitDataListener extends DefaultFuncNodeInitDataListener{

	
	@Override
	public void initData(FuncletInitData data) {
		// TODO �Զ����ɵķ������
		if(data == null){
			super.initData(data);
		}
		//����ͨ�����۶������ӹ�����
		else if(data != null && data.getInitType() == 9){
			AggSoOrderBuildHVO[] aggvos = (AggSoOrderBuildHVO[]) data.getInitData();
			if(aggvos.length ==  1 && aggvos[0].getParentVO().getBillid() == null ||aggvos[0].getParentVO().getBillid().equals("") ){
				getAutoShowUpComponent().showMeUp();
				getModel().initModel(null);
				getModel().setOtherUiState(UIState.NOT_EDIT);
				getModel().setUiState(UIState.ADD);
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("csaleorderid", aggvos[0].getParentVO().getCsaleorderid());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("csaleordercode", aggvos[0].getParentVO().getCsaleordercode());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("dbilldate", aggvos[0].getParentVO().getDbilldate());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("customerpono", aggvos[0].getParentVO().getCustomerpono());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("attnto", aggvos[0].getParentVO().getAttnto());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("contactno", aggvos[0].getParentVO().getContactno());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("contractperson", aggvos[0].getParentVO().getContactperson());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("subject", aggvos[0].getParentVO().getSubject());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("jobinstruction", aggvos[0].getParentVO().getJobinstruction());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("ref", aggvos[0].getParentVO().getRef());
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("jobtype", aggvos[0].getParentVO().getJobtype());
				
				((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().execHeadLoadFormulas();
				
			}else{
				this.getModel().initModel(aggvos);
			}
		}
	}
}
