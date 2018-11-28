package nc.ui.bhs.somove.ace.handler;

import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.pubapp.uif2app.view.util.RefMoreSelectedUtils;
import nc.ui.so.pub.util.BodyEditEventUtil;

public class SoMoveBodyAfterEditHandler implements IAppEventHandler<CardBodyAfterEditEvent> {
	

	@Override
	public void handleAppEvent(CardBodyAfterEditEvent e) {
		int[] rows = BodyEditEventUtil.getInstance().getAfterEditRow(e);
	    if (rows == null) {
	      return;
	    }
	    String editKey = e.getKey();
	    
	    if ("skill".equals(e.getKey())){
	    	// --参照多选处理
			RefMoreSelectedUtils utils = new RefMoreSelectedUtils(e.getBillCardPanel());
			utils.refMoreSelected(e.getRow(), e.getKey(), true);
			int rowcount = e.getBillCardPanel().getBillModel().getRowCount();
		    for (int i = 0; i < rowcount; i++){
		    	e.getBillCardPanel().setBodyValueAt("" + (i * 10 + 10), i, "rowno");
		    }
	    }
	    
		
		
	}
}