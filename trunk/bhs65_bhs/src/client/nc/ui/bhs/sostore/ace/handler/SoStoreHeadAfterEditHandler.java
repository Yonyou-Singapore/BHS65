package nc.ui.bhs.sostore.ace.handler;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;

public class SoStoreHeadAfterEditHandler implements IAppEventHandler<CardHeadTailAfterEditEvent> {
	
	@Override
	public void handleAppEvent(CardHeadTailAfterEditEvent e) {
		boolean retFlag = true;
		
		if ("def_type".equals(e.getKey())) {
			BillCardPanel cardPanel = e.getBillCardPanel();
			String jobType = (String) e.getBillCardPanel().getHeadItem("def_type").getValueObject();
			if("INBOUND".equals(jobType)){
				cardPanel.getBodyItem("def_assetno").setEdit(false);
			}else{
				cardPanel.getBodyItem("def_assetno").setEdit(true);
			}
		}
		
	}
	
}