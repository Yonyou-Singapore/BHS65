package nc.ui.bhs.somove.ace.handler;

import nc.ui.bhs.somove.ref.SoMoveRefModel;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.so.pub.keyvalue.IKeyValue;

public class SoMoveHeadBeforeEditHandler implements IAppEventHandler<CardHeadTailBeforeEditEvent> {
	
	private static final String COMBINE_JOB_ORDER_KEY = "def_combjoborder";
	private static final String REF_JOB_ORDER_KEY = "def_refjoborder";
	private static final String SO_CODE_KEY = "csaleordercode";
	
	@Override
	public void handleAppEvent(CardHeadTailBeforeEditEvent e) {
		boolean retFlag = true;
		
		// Filter combine job orders
		if (COMBINE_JOB_ORDER_KEY.equals(e.getKey())) {
			this.filterCombineJO(e);
			retFlag = true;
		}
		
		// Filter ref job orders
		if (REF_JOB_ORDER_KEY.equals(e.getKey())) {
			this.filterRefJO(e);
			retFlag = true;
		}
		
		e.setReturnValue(retFlag);
	}
	
	private void filterCombineJO(CardHeadTailBeforeEditEvent e) {
		IKeyValue keyValue = (IKeyValue) new CardKeyValue(e.getBillCardPanel());
		SoMoveRefModel model = (SoMoveRefModel) ((UIRefPane) e
				.getBillCardPanel().getHeadItem(e.getKey()).getComponent())
				.getRefModel();
		String thisMoveSO = keyValue.getHeadStringValue(SO_CODE_KEY);
		UFDateTime departuretime = (UFDateTime) keyValue.getHeadValue("departuretime");
		UFDateTime jobendtime = (UFDateTime) keyValue.getHeadValue("jobendtime");
		StringBuilder sb = new StringBuilder();
		sb.append(" bhs_somove_h.dr = 0 ");
		sb.append(" and bhs_somove_h.def15 = '~' and bhs_somove_h.billid != '");
		sb.append(keyValue.getHeadStringValue("billid") + "'");
		sb.append(" and bhs_somove_h.billid in (select billid from bhs_somove_box where dr=0) ");
		
		//可以参照到当天的所有joborder
		//sb.append(" and bd_customer.pk_customer in (select ccustomerid from so_saleorder where vbillcode = '");
		//sb.append(thisMoveSO).append("') ");
		if(departuretime != null && jobendtime == null){
			sb.append(" and bhs_somove_h.departuretime <= '" + departuretime + "' and bhs_somove_h.jobendtime > '" + departuretime + "' ");
		}else if(departuretime == null && jobendtime != null){
			sb.append(" and bhs_somove_h.departuretime < '" + jobendtime + "' and bhs_somove_h.jobendtime >= '" + jobendtime + "' ");
		}else if(departuretime != null && jobendtime != null){
			sb.append(" and ( (bhs_somove_h.departuretime <= '" + departuretime + "' and bhs_somove_h.jobendtime > '" + departuretime + "') ");
			sb.append(" or (bhs_somove_h.departuretime < '" + jobendtime + "' and bhs_somove_h.jobendtime >= '" + jobendtime + "') ) ");
		}else{
			sb.append(" and 1=2 ");
		}
		model.setWherePart(sb.toString());
	}
	
	private void filterRefJO(CardHeadTailBeforeEditEvent e) {
		IKeyValue keyValue = (IKeyValue) new CardKeyValue(e.getBillCardPanel());
		SoMoveRefModel model = (SoMoveRefModel) ((UIRefPane) e
				.getBillCardPanel().getHeadItem(e.getKey()).getComponent())
				.getRefModel();
		String thisMoveSO = keyValue.getHeadStringValue(SO_CODE_KEY);
		UFDateTime departuretime = (UFDateTime) keyValue.getHeadValue("departuretime");
		UFDateTime jobendtime = (UFDateTime) keyValue.getHeadValue("jobendtime");
		
		StringBuilder sb = new StringBuilder();
		sb.append(" bhs_somove_h.dr = 0 and bhs_somove_h.billid != '");
		sb.append(keyValue.getHeadStringValue("billid") + "'");
		sb.append(" and bhs_somove_h.billid in (select billid from bhs_somove_box where dr=0) ");
		
		//可以参照到当前客户的历史joborder和当天的所有joborder
		sb.append(" and ( bd_customer.pk_customer in (select ccustomerid from so_saleorder where vbillcode = '");
		sb.append(thisMoveSO);
		sb.append("') or " );
		if(departuretime != null && jobendtime == null){
			sb.append(" (bhs_somove_h.departuretime <= '" + departuretime + "' and bhs_somove_h.jobendtime > '" + departuretime + "') ");
		}else if(departuretime == null && jobendtime != null){
			sb.append(" (bhs_somove_h.departuretime < '" + jobendtime + "' and bhs_somove_h.jobendtime >= '" + jobendtime + "') ");
		}else if(departuretime != null && jobendtime != null){
			sb.append(" ( ( bhs_somove_h.departuretime <= '" + departuretime + "' and bhs_somove_h.jobendtime > '" + departuretime + "') ");
			sb.append(" or (bhs_somove_h.departuretime < '" + jobendtime + "' and bhs_somove_h.jobendtime >= '" + jobendtime + "') )");
		}else{
			sb.append(" 1=2 ");
		}
		sb.append("	)");
		model.setWherePart(sb.toString());
	}
}