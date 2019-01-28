package nc.ui.bhs.sostore.ace.handler;

import java.util.Collection;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.ui.bhs.ref.ITSAssetRefModel;
import nc.ui.bhs.ref.SOPartsRefModel;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.bd.cust.CustomerVO;
import nc.vo.pub.BusinessException;
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
		String csaleorderid = keyValue.getHeadStringValue("csaleorderid");
		
		StringBuilder sb = new StringBuilder(" 1=1 ");
		String location = keyValue.getHeadStringValue("warehousezone");
		if(location != null
				&& !location.trim().equals("")){
			sb.append(" and Location = '" + location + "'");
		}
		//同一个SO的才能被参照到
		sb.append(" and Doc_No in (select vbillno from bhs_sostore_h where csaleorderid = '" + csaleorderid + "'");
		model.setWherePart(sb.toString());
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
			BillCardPanel cardPanel = e.getBillCardPanel();
			// 设置可以多选
			UIRefPane refPane = (UIRefPane) cardPanel.getBodyItem(DEF_ASSETNO_KEY).getComponent();
			refPane.setMultiSelectedEnabled(true);
			
			String jobType = (String) cardPanel.getHeadItem("def_type").getValueObject();
			if("INBOUND".equals(jobType)){
				cardPanel.getBodyItem("def_assetno").setEdit(false);
				retFlag = false;
			}else{
				cardPanel.getBodyItem("def_assetno").setEdit(true);
				retFlag = true;
			}
			
			//重设置下参照PK，要不然多选有问题
			String asset_no = (String) e.getBillCardPanel().getBodyValueAt(e.getRow(), "def3");
	    	refPane.setPK(asset_no);
		}
		
		e.setReturnValue(retFlag);	
		
	}
}