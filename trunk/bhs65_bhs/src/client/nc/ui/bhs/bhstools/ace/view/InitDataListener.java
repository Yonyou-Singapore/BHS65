package nc.ui.bhs.bhstools.ace.view;

import nc.funcnode.ui.FuncletInitData;
import nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.uif2.UIState;
import nc.vo.bhs.bhstools.AggBhsToolsHeadVO;

/**
 * Tools���ݴ򿪳�ʼ����
 * @author Thinkpad
 * 20170419
 */
public class InitDataListener extends DefaultFuncNodeInitDataListener{

	
	@Override
	public void initData(FuncletInitData data) {
		// TODO �Զ����ɵķ������
		if(data == null){
			super.initData(data);
		}else{
			//����ͨ�����۶������ӹ�����
			if(data != null && data.getInitType() == 9){
				AggBhsToolsHeadVO[] aggvos = (AggBhsToolsHeadVO[]) data.getInitData();
				if(aggvos.length ==  1 && aggvos[0].getParentVO().getBillid() == null ||aggvos[0].getParentVO().getBillid().equals("") ){
					getModel().initModel(null);
					getAutoShowUpComponent().showMeUp();
					getModel().setUiState(UIState.ADD);
					((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("pk_org", aggvos[0].getParentVO().getPk_org());
					((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("pk_org_v", aggvos[0].getParentVO().getPk_org_v());
					((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("pk_group", aggvos[0].getParentVO().getPk_group());
					((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("pk_saleorder", aggvos[0].getParentVO().getPk_saleorder());
					((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("saleorderbillno", aggvos[0].getParentVO().getSaleorderbillno());
					((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("toolssono", aggvos[0].getParentVO().getSaleorderbillno());
					((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("dbilldate", aggvos[0].getParentVO().getDbilldate());
					((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("toolssodocdate", aggvos[0].getParentVO().getDbilldate());
					
				}else{
					this.getModel().initModel(aggvos);
				}
			}
		}
		
	}
}
