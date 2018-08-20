package nc.ui.bhs.pack.ace.handler;

import nc.ui.bhs.pub.action.DataUtils;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.vo.pub.lang.UFDouble;

/**
 * pack 表体编辑后事件处理类
 * @autor:Thinkpad
 * @date :2017-3-13
 */
public class AceBodyAfterEditHandler  implements IAppEventHandler<CardBodyAfterEditEvent>{

	@Override
	public void handleAppEvent(CardBodyAfterEditEvent e) {
		// TODO 自动生成的方法存根
		String key = e.getKey();
		if(key.equals("fullboxskid") || key.equals("way4way") || key.equals("floating")
				|| key.equals("cargodimensionl") || key.equals("cargodimensionw") || key.equals("cargodimensionh") 
				 ){
			afterfullboxskidWayEdit(e);
		}else if(key.equals("cratedimensionl") || key.equals("cratedimensionw") || key.equals("cratedimensionh")){
			
			UFDouble cdl = DataUtils.getInstance().getUFDouble(e.getBillCardPanel().getBillModel("bhs_pack_b").getValueAt(e.getRow(), "cratedimensionl"));
			UFDouble cdw = DataUtils.getInstance().getUFDouble(e.getBillCardPanel().getBillModel("bhs_pack_b").getValueAt(e.getRow(), "cratedimensionw"));
			UFDouble cdh = DataUtils.getInstance().getUFDouble(e.getBillCardPanel().getBillModel("bhs_pack_b").getValueAt(e.getRow(), "cratedimensionh"));
			e.getBillCardPanel().setBodyValueAt(cdl.div(100).multiply(cdw.div(100)).multiply(cdh.div(100)).setScale(2, UFDouble.ROUND_HALF_UP), e.getRow(), "externalm3", "bhs_pack_b");
		}
	}
	/**
	 * 表体箱子内尺寸，箱子外尺寸计算方法
	 * @param e
	 * 2017-03-17
	 */
	public void afterfullboxskidWayEdit(CardBodyAfterEditEvent e){
		int row = e.getRow();
		String fullboxskid = String.valueOf(e.getBillCardPanel().getBodyValueAt(row, "fullboxskid"));
		String way4way = String.valueOf(e.getBillCardPanel().getBodyValueAt(row, "way4way"));
		String floating = String.valueOf(e.getBillCardPanel().getBodyValueAt(row, "floating"));
		//如果为空，则清空数据
		if(fullboxskid.equals("") || fullboxskid.equals("null") || way4way.equals("null") || way4way.equals("") ){
			e.getBillCardPanel().setBodyValueAt(null, row, "internaldimensionl", "bhs_pack_b");
			e.getBillCardPanel().setBodyValueAt(null, row, "internaldimensionw", "bhs_pack_b");
			e.getBillCardPanel().setBodyValueAt(null, row, "internaldimensionh", "bhs_pack_b");
			e.getBillCardPanel().setBodyValueAt(null, row, "cratedimensionl", "bhs_pack_b");
			e.getBillCardPanel().setBodyValueAt(null, row, "cratedimensionw", "bhs_pack_b");
			e.getBillCardPanel().setBodyValueAt(null, row, "cratedimensionh", "bhs_pack_b");
			//如果选择的是floating deck 则箱子外尺寸高 + 5
			if(floating.equals("1")){
				e.getBillCardPanel().setBodyValueAt(new UFDouble(5), row, "cratedimensionh", "bhs_pack_b");
			}
			return;
		}
		UFDouble flatnum = UFDouble.ZERO_DBL;
		if(floating.equals("1")){
			flatnum = new UFDouble(5);
		}
		//货物尺寸:
		UFDouble cargodimensionl = DataUtils.getInstance().getUFDouble(e.getBillCardPanel().getBillModel("bhs_pack_b").getValueAt(row, "cargodimensionl"));
		UFDouble cargodimensionw = DataUtils.getInstance().getUFDouble(e.getBillCardPanel().getBillModel("bhs_pack_b").getValueAt(row, "cargodimensionw"));
		UFDouble cargodimensionh = DataUtils.getInstance().getUFDouble(e.getBillCardPanel().getBillModel("bhs_pack_b").getValueAt(row, "cargodimensionh"));
		//计算箱子内尺寸及箱子外尺寸算法
//		1)	If Full Box + 2 Way
//		 Int Dims +20cm +20cm +15cm
//		 Ext Dims +10cm +10cm +19cm
//		2)	If Full Box  + 4 Way
//		 Int Dims +20cm +20cm +15cm
//		 Ext Dims +10cm +10cm +23cm
//		3)	If Skid + 2 Way
//		Int Dims +20cm +20cm +0cm
//		Ext Dims +10cm +10cm +14cm
//		4)	If Skid + 4 Way
//		Int Dims +20cm +20cm +0cm
//		Ext Dims +10cm +10cm +18cm
//		5)	If Floating Deck +5cm @ Ext Dims.
		if(fullboxskid.equals("1") && way4way.equals("1")){
			e.getBillCardPanel().setBodyValueAt(cargodimensionl.add(new UFDouble(20)), row, "internaldimensionl", "bhs_pack_b");
			e.getBillCardPanel().setBodyValueAt(cargodimensionw.add(new UFDouble(20)), row, "internaldimensionw", "bhs_pack_b");
			e.getBillCardPanel().setBodyValueAt(cargodimensionh.add(new UFDouble(15)), row, "internaldimensionh", "bhs_pack_b");
			e.getBillCardPanel().setBodyValueAt(cargodimensionl.add(new UFDouble(20)).add(new UFDouble(10)), row, "cratedimensionl", "bhs_pack_b");
			e.getBillCardPanel().setBodyValueAt(cargodimensionw.add(new UFDouble(20)).add(new UFDouble(10)), row, "cratedimensionw", "bhs_pack_b");
			e.getBillCardPanel().setBodyValueAt(cargodimensionh.add(new UFDouble(15)).add(new UFDouble(19)).add(flatnum), row, "cratedimensionh", "bhs_pack_b");
		}else if(fullboxskid.equals("1") && way4way.equals("2")){
			e.getBillCardPanel().setBodyValueAt(cargodimensionl.add(new UFDouble(20)), row, "internaldimensionl", "bhs_pack_b");
			e.getBillCardPanel().setBodyValueAt(cargodimensionw.add(new UFDouble(20)), row, "internaldimensionw", "bhs_pack_b");
			e.getBillCardPanel().setBodyValueAt(cargodimensionh.add(new UFDouble(15)), row, "internaldimensionh", "bhs_pack_b");
			e.getBillCardPanel().setBodyValueAt(cargodimensionl.add(new UFDouble(20)).add(new UFDouble(10)), row, "cratedimensionl", "bhs_pack_b");
			e.getBillCardPanel().setBodyValueAt(cargodimensionw.add(new UFDouble(20)).add(new UFDouble(10)), row, "cratedimensionw", "bhs_pack_b");
			e.getBillCardPanel().setBodyValueAt(cargodimensionh.add(new UFDouble(15)).add(new UFDouble(23)).add(flatnum), row, "cratedimensionh", "bhs_pack_b");
		}else if(fullboxskid.equals("2") && way4way.equals("1")){
			e.getBillCardPanel().setBodyValueAt(cargodimensionl.add(new UFDouble(20)), row, "internaldimensionl", "bhs_pack_b");
			e.getBillCardPanel().setBodyValueAt(cargodimensionw.add(new UFDouble(20)), row, "internaldimensionw", "bhs_pack_b");
			e.getBillCardPanel().setBodyValueAt(cargodimensionh.add(new UFDouble(0)), row, "internaldimensionh", "bhs_pack_b");
			e.getBillCardPanel().setBodyValueAt(cargodimensionl.add(new UFDouble(20)).add(new UFDouble(10)), row, "cratedimensionl", "bhs_pack_b");
			e.getBillCardPanel().setBodyValueAt(cargodimensionw.add(new UFDouble(20)).add(new UFDouble(10)), row, "cratedimensionw", "bhs_pack_b");
			e.getBillCardPanel().setBodyValueAt(cargodimensionh.add(new UFDouble(0)).add(new UFDouble(14)).add(flatnum), row, "cratedimensionh", "bhs_pack_b");
		}else if(fullboxskid.equals("2") && way4way.equals("2")){
			e.getBillCardPanel().setBodyValueAt(cargodimensionl.add(new UFDouble(20)), row, "internaldimensionl", "bhs_pack_b");
			e.getBillCardPanel().setBodyValueAt(cargodimensionw.add(new UFDouble(20)), row, "internaldimensionw", "bhs_pack_b");
			e.getBillCardPanel().setBodyValueAt(cargodimensionh.add(new UFDouble(0)), row, "internaldimensionh", "bhs_pack_b");
			e.getBillCardPanel().setBodyValueAt(cargodimensionl.add(new UFDouble(20)).add(new UFDouble(10)), row, "cratedimensionl", "bhs_pack_b");
			e.getBillCardPanel().setBodyValueAt(cargodimensionw.add(new UFDouble(20)).add(new UFDouble(10)), row, "cratedimensionw", "bhs_pack_b");
			e.getBillCardPanel().setBodyValueAt(cargodimensionh.add(new UFDouble(0)).add(new UFDouble(18)).add(flatnum), row, "cratedimensionh", "bhs_pack_b");
		}
		
		
		UFDouble idl = DataUtils.getInstance().getUFDouble(e.getBillCardPanel().getBillModel("bhs_pack_b").getValueAt(row, "internaldimensionl"));
		UFDouble idw = DataUtils.getInstance().getUFDouble(e.getBillCardPanel().getBillModel("bhs_pack_b").getValueAt(row, "internaldimensionw"));
		UFDouble idh = DataUtils.getInstance().getUFDouble(e.getBillCardPanel().getBillModel("bhs_pack_b").getValueAt(row, "internaldimensionh"));
		
		UFDouble cdl = DataUtils.getInstance().getUFDouble(e.getBillCardPanel().getBillModel("bhs_pack_b").getValueAt(row, "cratedimensionl"));
		UFDouble cdw = DataUtils.getInstance().getUFDouble(e.getBillCardPanel().getBillModel("bhs_pack_b").getValueAt(row, "cratedimensionw"));
		UFDouble cdh = DataUtils.getInstance().getUFDouble(e.getBillCardPanel().getBillModel("bhs_pack_b").getValueAt(row, "cratedimensionh"));
		
		
		
		
		e.getBillCardPanel().setBodyValueAt(idl.div(100).multiply(idw.div(100)).multiply(idh.div(100)).setScale(2, UFDouble.ROUND_HALF_UP), row, "internalm3", "bhs_pack_b");
		e.getBillCardPanel().setBodyValueAt(cdl.div(100).multiply(cdw.div(100)).multiply(cdh.div(100)).setScale(2, UFDouble.ROUND_HALF_UP), row, "externalm3", "bhs_pack_b");
		
		
		
	}

}
