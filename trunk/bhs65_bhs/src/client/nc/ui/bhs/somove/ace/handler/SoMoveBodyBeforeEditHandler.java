package nc.ui.bhs.somove.ace.handler;

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