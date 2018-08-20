package nc.ui.bhs.sotruck.ace.handler;

import nc.ui.bhs.pub.action.DataUtils;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * truck 编辑后事件
 * @author Thinkpad
 * @date 2017-3-22
 */
public class AceBodyAfterEditHandler  implements IAppEventHandler<CardBodyAfterEditEvent>{

	@Override
	public void handleAppEvent(CardBodyAfterEditEvent e) {
		// TODO 自动生成的方法存根
		String key = e.getKey();
		String tablecode = e.getTableCode();
		UFDouble def19 = DataUtils.getInstance().getUFDouble(e.getBillCardPanel().getBillModel(tablecode).getValueAt(e.getRow(), "def19"));
		UFDouble def20 = DataUtils.getInstance().getUFDouble(e.getBillCardPanel().getBillModel(tablecode).getValueAt(e.getRow(), "def20"));
		if(key.equals("owlr") && (tablecode.equals("id_sotrucktonebvo") || tablecode.equals("id_sotruckteonebvo") )){
			
			UFDouble nowlr =  DataUtils.getInstance().getUFDouble(e.getValue());
			UFDouble noh = DataUtils.getInstance().getUFDouble(e.getBillCardPanel().getBillModel(tablecode).getValueAt(e.getRow(), "oh"));
			if(nowlr.compareTo(new UFDouble(def19)) > 0 && noh.compareTo(new UFDouble(def20)) <= 0){
				ExceptionUtils.wrappBusinessException("OW MORE THAN "+def19+"CM REQUIRE ESCORT");
			}
			if(nowlr.compareTo(new UFDouble(def19)) > 0 && noh.compareTo(new UFDouble(def20)) > 0){
				ExceptionUtils.wrappBusinessException("OW MORE THAN "+def19+"CM, OH MORE THAN "+def20+"CM REQUIRE ESCORT");
			}
			
			if(nowlr.compareTo(new UFDouble(def19)) <= 0 && noh.compareTo(new UFDouble(def20)) > 0){
				ExceptionUtils.wrappBusinessException("OH MORE THAN "+def20+"CM REQUIRE ESCORT");
			}
			
		}else if(key.equals("oh")){
			UFDouble noh =  DataUtils.getInstance().getUFDouble(e.getValue());
			UFDouble nowlr = DataUtils.getInstance().getUFDouble(e.getBillCardPanel().getBillModel(tablecode).getValueAt(e.getRow(), "owlr"));
			
			if(nowlr.compareTo(new UFDouble(def19)) > 0 && noh.compareTo(new UFDouble(def20)) <= 0){
				ExceptionUtils.wrappBusinessException("OW MORE THAN "+def19+"CM REQUIRE ESCORT");
			}
			if(nowlr.compareTo(new UFDouble(def19)) > 0 && noh.compareTo(new UFDouble(def20)) > 0){
				ExceptionUtils.wrappBusinessException("OW MORE THAN "+def19+"CM, OH MORE THAN "+def20+"CM REQUIRE ESCORT");
			}
			if(nowlr.compareTo(new UFDouble(def19)) <= 0 && noh.compareTo(new UFDouble(def20)) > 0){
				ExceptionUtils.wrappBusinessException("OH MORE THAN "+def20+"CM REQUIRE ESCORT");
			}
		}
		ShowStatusBarMsgUtil.showStatusBarMsg(" ", e.getContext());
	}
}
