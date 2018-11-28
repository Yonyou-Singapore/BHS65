package nc.ui.bhs.sostore.ace.handler;

import nc.ui.pub.beans.UIRefPane;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.pubapp.uif2app.view.util.RefMoreSelectedUtils;
import nc.ui.so.pub.util.BodyEditEventUtil;

public class SoStoreBodyAfterEditHandler implements IAppEventHandler<CardBodyAfterEditEvent> {
	

	@SuppressWarnings({ "unused", "restriction" })
	@Override
	public void handleAppEvent(CardBodyAfterEditEvent e) {
		int[] rows = BodyEditEventUtil.getInstance().getAfterEditRow(e);
	    if (rows == null) {
	      return;
	    }
		String editKey = e.getKey();
	    
	    if ("def_assetno".equals(e.getKey())){
	    	// --参照多选处理
			RefMoreSelectedUtils utils = new RefMoreSelectedUtils(e.getBillCardPanel());
			int[] addRowIndexes = utils.refMoreSelected(e.getRow(), e.getKey(), true);
			
			//给PK设值
			if(addRowIndexes != null && addRowIndexes.length > 0){
				UIRefPane refPane = (UIRefPane) e.getBillCardPanel().getBodyItem("def_assetno").getComponent();
				String[] pks = refPane.getRefPKs();
				for(int i = 0; i<pks.length; i++){
					e.getBillCardPanel().setBodyValueAt(pks[i], addRowIndexes[i], "def3");
				}
			}
			//重新设置行号
			int rowcount = e.getBillCardPanel().getBillModel().getRowCount();
		    for (int i = 0; i < rowcount; i++){
		    	e.getBillCardPanel().setBodyValueAt("" + (i * 10 + 10), i, "rowno");
		    }
		    
	    }
	    
		
		
	}
}