package nc.ui.bhs.somove.ace.handler;

import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;

public class SoMoveBodyBeforeEditHandler implements IAppEventHandler<CardBodyBeforeEditEvent> {
	

	@Override
	public void handleAppEvent(CardBodyBeforeEditEvent e) {
		boolean retFlag = true;
		// Filter ref job orders
		if ("skill".equals(e.getKey())) {
			BillCardPanel cardPanel = e.getBillCardPanel();
			// 设置可以多选
			UIRefPane refPane = (UIRefPane) cardPanel.getBodyItem("skill").getComponent();
			refPane.setMultiSelectedEnabled(true);
		}
		
		e.setReturnValue(retFlag);	
	}
}