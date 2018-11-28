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
import nc.vo.bhs.pack.SoOrderPackBVO;
import nc.vo.pub.BusinessException;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.salequotation.entity.SalequotationTermVO;

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
//		String customer = keyValue.getHeadStringValue("def_customer");
		String customer = null;
		String csaleorderid = keyValue.getHeadStringValue("csaleorderid");
		String condition = " pk_customer in ( select ccustomerid from so_saleorder where csaleorderid = '" 
				+ csaleorderid + "') ";
		Collection<CustomerVO> list = null;
		try {
			list = (Collection<CustomerVO>) NCLocator.getInstance().lookup(IUAPQueryBS.class).retrieveByClause(CustomerVO.class, condition);
		} catch (BusinessException e1) {
			// TODO Auto-generated catch block
		}
		if(list != null ){
			for ( CustomerVO vo : list){
				customer = vo.getCode();
			}
		}
		
		String location = keyValue.getHeadStringValue("warehousezone");
		StringBuilder sb = new StringBuilder(" 1=1 ");
		if(location != null
				&& !location.trim().equals("")){
			sb.append(" and Location = '" + location + "'");
		}
		sb.append(" and customer = '" + customer + "'");
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