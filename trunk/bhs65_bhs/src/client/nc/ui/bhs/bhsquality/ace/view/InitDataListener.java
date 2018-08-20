package nc.ui.bhs.bhsquality.ace.view;

import nc.funcnode.ui.FuncletInitData;
import nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.uif2.UIState;
import nc.vo.bhs.bhsquality.AggBhsPoHeadVO;
import nc.vo.bhs.bhstools.AggBhsToolsHeadVO;
import nc.vo.pub.lang.UFDate;

/**
 * ��ȫ�����������ݴ򿪳�ʼ����
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
				AggBhsPoHeadVO[] aggvos = (AggBhsPoHeadVO[]) data.getInitData();
				getAutoShowUpComponent().showMeUp();
				if(aggvos.length ==  1 && aggvos[0].getParentVO().getBillid() == null ||aggvos[0].getParentVO().getBillid().equals("") ){
					getModel().initModel(null);
					getModel().setUiState(UIState.ADD);
					((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("pk_org", aggvos[0].getParentVO().getPk_org());
					((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("pk_org_v", aggvos[0].getParentVO().getPk_org_v());
					((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("pk_group", aggvos[0].getParentVO().getPk_group());
					((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("pk_customer", aggvos[0].getParentVO().getPk_customer());
					((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("pono", aggvos[0].getParentVO().getPono());
					((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("pdate", aggvos[0].getParentVO().getPdate());
					((ShowUpableBillForm)getAutoShowUpComponent()).getBillCardPanel().setHeadItem("dbilldate", new UFDate());
					
				}else{
					this.getModel().initModel(aggvos);
				}
			}
		}
		
	}
}
