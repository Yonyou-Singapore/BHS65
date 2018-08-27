package nc.ui.bhs.sostore.ace.handler;

import nc.ui.bhs.ref.ITSAssetRefModel;
import nc.ui.bhs.ref.SOPartsRefModel;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.so.pub.keyvalue.IKeyValue;

public class SoStoreBodyBeforeEditHandler implements IAppEventHandler<CardBodyBeforeEditEvent> {
	
	private static final String DEF_VAS_KEY = "def_vas";
	private static final String DEF_ASSETNO_KEY = "def_assetno";
	
	
	private void filterPart(CardBodyBeforeEditEvent e) {
		IKeyValue keyValue = (IKeyValue) new CardKeyValue(e.getBillCardPanel());
		SOPartsRefModel model = (SOPartsRefModel) ((UIRefPane) e
				.getBillCardPanel().getBodyItem(e.getKey()).getComponent())
				.getRefModel();
		String csaleorderid = keyValue.getHeadStringValue("csaleorderid");
		StringBuilder sb = new StringBuilder();
		sb.append(" so_saleorder_b.csaleorderid = '" + csaleorderid + "'");
		
		model.setWherePart(sb.toString());
	}
	
	private void filterAsset(CardBodyBeforeEditEvent e) {
		IKeyValue keyValue = (IKeyValue) new CardKeyValue(e.getBillCardPanel());
		ITSAssetRefModel model = (ITSAssetRefModel) ((UIRefPane) e
				.getBillCardPanel().getBodyItem(e.getKey()).getComponent())
				.getRefModel();
		String customer = keyValue.getHeadStringValue("def_customer");
		StringBuilder sb = new StringBuilder();
		sb.append(" customer = '" + customer + "'");
		
		model.setWherePart("1=1");
	}

	@Override
	public void handleAppEvent(CardBodyBeforeEditEvent e) {
		boolean retFlag = true;
		
		// Filter combine job orders
		if (DEF_VAS_KEY.equals(e.getKey())) {
			this.filterPart(e);
			retFlag = true;
		}
		
		// Filter ref job orders
		if (DEF_ASSETNO_KEY.equals(e.getKey())) {
			this.filterAsset(e);
			retFlag = true;
		}
		
		e.setReturnValue(retFlag);	
		
	}
}