package nc.ui.bhs.pack.ace.action;

import java.awt.event.ActionEvent;

import nc.ui.bhs.pack.ace.view.PackBillForm;
import nc.ui.bhs.pub.action.DataUtils;
import nc.vo.bhs.pack.SoOrderPackBVO;
import nc.vo.pub.lang.UFDouble;

/**
 * pack保存事件
 * 1、Remark字段的内容由第二表体区域中选择的字段拼接而成
 * @author Thinkpad
 * @date 2017-3-17
 */
public class PackSaveAction extends nc.ui.pubapp.uif2app.actions.pflow.SaveScriptAction{

	private static final long serialVersionUID = 4666500181312210369L;
	@Override
	public void doAction(ActionEvent e) throws Exception {
		// TODO 自动生成的方法存根
		appendRemarkinfo();
		calcHeadValue();
		super.doAction(e);
	}
	/**
	 * 计算表头M2、M3、kg、pkgs、no of shock watches、no of tilt watches
	 * 其中M2：取item listings页签中的"Crate Dimension (CM)"里的 (L*W)汇总，然后把CM转换成M.
	 * M3：取item listings页签中的"Crate Dimension (CM)"里的 (L*W*H)汇总，然后把CM转换成M.
	 * KG：取item listings页签中的Crate Dimension旁边的"Weight"的汇总
	 * PKGs：取item listings页签中的"Qty"的汇总
	 * no of shock watches：取pack parameter页签中的"Shock Watch Qty/Crate"的汇总
	 * no of tilt watches：取pack parameter页签中的"Tilt Watch Qty/Crate"的汇总
	 * Largest length:取item listings页签中的"Crate Dimension (CM)"里的L列最大的值
	 * Largest width:取item listings页签中的"Crate Dimension (CM)"里的W列最大的值
	 * Largest height:取item listings页签中的"Crate Dimension (CM)"里的H列最大的值
	 * Largest weight:取item listings页签中的Crate Dimension旁边的"Weight"列最大的值
	 */
	public void calcHeadValue(){
		PackBillForm e = (PackBillForm) this.editor;
		int rowCount = e.getBillCardPanel().getRowCount();
		UFDouble sumnheadM2 = UFDouble.ZERO_DBL;
		UFDouble sumnheadM3 = UFDouble.ZERO_DBL;
		UFDouble sumcdweight = UFDouble.ZERO_DBL;
		UFDouble sumnosw = UFDouble.ZERO_DBL;
		UFDouble sumnotw = UFDouble.ZERO_DBL;
		
		UFDouble sumqty = UFDouble.ZERO_DBL;
		
		UFDouble largestlength = UFDouble.ZERO_DBL;
		UFDouble largestwidth = UFDouble.ZERO_DBL;
		UFDouble largestheight = UFDouble.ZERO_DBL;
		UFDouble largestweight = UFDouble.ZERO_DBL;
		
		
		String maxlengthno = "";
		String maxwidthno = "";
		String maxheightno = "";
		String maxheaviestno = "";
		
		
		for(int i = 0;i < rowCount;i++){
			
			SoOrderPackBVO vo = (SoOrderPackBVO) e.getBillCardPanel().getBillModel().getBodyValueRowVO(i,SoOrderPackBVO.class.getName());
			UFDouble cdl = DataUtils.getInstance().getUFDouble(vo.getCratedimensionl()); 
			UFDouble cdw = DataUtils.getInstance().getUFDouble(vo.getCratedimensionw());
//			UFDouble cdh = DataUtils.getInstance().getUFDouble(vo.getCratedimensionh());
			UFDouble cdweight = DataUtils.getInstance().getUFDouble(vo.getCratedimensionweight());
			UFDouble nosw = DataUtils.getInstance().getUFDouble(vo.getShockwatchqtycrate());
			UFDouble notw = DataUtils.getInstance().getUFDouble(vo.getTiltwatchqtycrate());
			UFDouble qty = DataUtils.getInstance().getUFDouble(vo.getPackqty());
			
			UFDouble cdlength = DataUtils.getInstance().getUFDouble(vo.getCratedimensionl());
			UFDouble cdwidth = DataUtils.getInstance().getUFDouble(vo.getCratedimensionw());
			UFDouble cdheight = DataUtils.getInstance().getUFDouble(vo.getCratedimensionh());
			UFDouble cdweigh = DataUtils.getInstance().getUFDouble(vo.getCratedimensionweight());
			
			if(largestlength.compareTo(cdlength) < 0){
				largestlength = cdlength;
				maxlengthno = vo.getSnno();
			}
			
			if(largestwidth.compareTo(cdwidth) < 0){
				largestwidth = cdwidth;
				maxwidthno = vo.getSnno();
			}
			if(largestheight.compareTo(cdheight) < 0){
				largestheight = cdheight;
				maxheightno = vo.getSnno();
			}
			if(largestweight.compareTo(cdweigh) < 0){
				largestweight = cdweigh;
				maxheaviestno = vo.getSnno();
			}
			sumnheadM3 = sumnheadM3.add(DataUtils.getInstance().getUFDouble(vo.getExternalm3()));
			sumnheadM2 = sumnheadM2.add(cdl.div(100).multiply(cdw.div(100)));
			sumcdweight = sumcdweight.add(cdweight);
			sumnosw = sumnosw.add(nosw);
			sumnotw = sumnotw.add(notw);
			sumqty = sumqty.add(qty);
		}
		
		UFDouble nheadkg = sumcdweight;
		UFDouble nheadpkg = sumqty;
		UFDouble nheadnosw =sumnosw;
		UFDouble nheadnotw = sumnotw;
		
		e.getBillCardPanel().setHeadItem("summarym2", sumnheadM2);
		e.getBillCardPanel().setHeadItem("summarym3", sumnheadM3);
		e.getBillCardPanel().setHeadItem("summarykg", nheadkg);
		e.getBillCardPanel().setHeadItem("summarypkgs", nheadpkg);
		e.getBillCardPanel().setHeadItem("noofshockwatches", nheadnosw);
		e.getBillCardPanel().setHeadItem("nooftiltwatches", nheadnotw);
		e.getBillCardPanel().setHeadItem("largestlength", largestlength);
		e.getBillCardPanel().setHeadItem("largestwidth", largestwidth);
		e.getBillCardPanel().setHeadItem("largestheight", largestheight);
		e.getBillCardPanel().setHeadItem("largestweight", largestweight);
		
		e.getBillCardPanel().setHeadItem("lcrate", maxlengthno);
		e.getBillCardPanel().setHeadItem("wcrate", maxwidthno);
		e.getBillCardPanel().setHeadItem("hcrate", maxheightno);
		e.getBillCardPanel().setHeadItem("kcrate", maxheaviestno);
		
	}
	
	
	/**
	 * 拼接remark字段的值
	 * @author Thinkpad
	 * @date 2017-3-17
	 */
	public void appendRemarkinfo(){
		
		PackBillForm e = (PackBillForm) this.editor;
		int rowCount = e.getBillCardPanel().getRowCount();
		for(int i = 0;i < rowCount;i++){
			SoOrderPackBVO vo = (SoOrderPackBVO) e.getBillCardPanel().getBillModel().getBodyValueRowVO(i,SoOrderPackBVO.class.getName());
			StringBuffer bufferStr = new StringBuffer();
			bufferStr.append(getFullBoxSkidStr(vo.getFullboxskid())).append(",");
			bufferStr.append(getIffullboxStr(vo.getIffullbox())).append(",");
			bufferStr.append(getMachineDescriptionStr(vo.getMachinedescri())).append(",");
			bufferStr.append(get2way4wayStr(vo.getWay4way())).append(",");
			bufferStr.append(getVacumnormalStr(vo.getVacumnormal())).append(",");
			bufferStr.append(getCleatStr(vo.getCleat())).append(",");
			bufferStr.append(getExternalsecuringStr(vo.getExternalsecuring())).append(",");
			bufferStr.append(getAdditionalexternalStr(vo.getAddsecuringtoskid())).append(",");
			bufferStr.append(getSkidtypeStr(vo.getSkidtype())).append(",");
			bufferStr.append(getFloatingStr(vo.getFloating())).append(",");
			bufferStr.append(getSecuringtoskidStr(vo.getSecuringtoskid())).append(",");
			bufferStr.append(getAddsecuringtoskid(vo.getAddsecuringtoskid())).append(",");
			bufferStr.append(getPestmanagement(vo.getPestmanagement())).append(",");
			bufferStr.append(getShockwatch(vo.getShockwatch())).append(",");
			bufferStr.append(getTiltWatch(vo.getTiltwatch()));
			e.getBillCardPanel().getBillModel().setValueAt(bufferStr.toString(),i,"packremark");
		}
	}
	public String getFullBoxSkidStr(String fullboxskid){
		if(fullboxskid == null || fullboxskid.equals("")){
			return "";
		}else if(fullboxskid.equals("1")){
			return "Full Box";
		}else{
			return "Skid";
		}
	}
	public String getIffullboxStr(String iffullbox){
		if(iffullbox == null || iffullbox.equals("")){
			return "";
		}else if(iffullbox.equals("1")){
			return "Crate";
		}else{
			return "Case";
		}
	}
	
	public String getMachineDescriptionStr(String machineDescription){
		if(machineDescription == null || machineDescription.equals("")){
			return "";
		}else if(machineDescription.equals("1")){
			return "Fumigation Yes";
		}else if(machineDescription.equals("2")){
			return "Fumigation No";
		}else if(machineDescription.equals("3")){
			return "Plywood Only";
		}else {
			return "Heat Treat";
		}
	}
	
	
	public String get2way4wayStr(String way4way){
		if(way4way == null || way4way.equals("")){
			return "";
		}else if(way4way.equals("1")){
			return "2 way";
		}else{
			return "4 way";
		}
	}
	public String getVacumnormalStr(String vacumnormal){
		if(vacumnormal == null || vacumnormal.equals("")){
			return "";
		}else if(vacumnormal.equals("1")){
			return "Vacum";
		}else{
			return "Normal";
		}
	}
	public String getCleatStr(String cleat){
		if(cleat == null || cleat.equals("")){
			return "";
		}else if(cleat.equals("1")){
			return "Pine Wood";
		}else if(cleat.equals("2")){
			return "Tropical Hardwood";
		}else{
			return "Plywood";
		}
	}
	public String getExternalsecuringStr(String externalsecuring){
		if(externalsecuring == null || externalsecuring.equals("")){
			return "";
		}else if(externalsecuring.equals("1")){
			return "Screws";
		}else{
			return "Nails";
		}
	}
	public String getAdditionalexternalStr(String additionalexternal){
		if(additionalexternal == null || additionalexternal.equals("")){
			return "";
		}else if(additionalexternal.equals("1")){
			return "Klimps";
		}else{
			return "Metal Bands";
		}
	}
	public String getSkidtypeStr(String skidtype){
		if(skidtype == null || skidtype.equals("")){
			return "";
		}else if(skidtype.equals("1")){
			return "Shifting Skid";
		}else if(skidtype.equals("2")){
			return "Shipping Skid";
		}else{
			return "Metal Skid";
		}
	}
	public String getFloatingStr(String floating){
		if(floating == null || floating.equals("")){
			return "";
		}else if(floating.equals("1")){
			return "Floating Deck";
		}else{
			return "Non Floating Deck";
		}
	}
	public String getSecuringtoskidStr(String securingtoskid){
		if(securingtoskid == null || securingtoskid.equals("")){
			return "";
		}else if(securingtoskid.equals("1")){
			return "Metal Bracket Small";
		}else if(securingtoskid.equals("2")){
			return "Metal Bracket Big";
		}else if(securingtoskid.equals("3")){
			return "Long Choking";
		}else{
			return "Choking in Section";
		}
	}
	public String getAddsecuringtoskid(String addsecuringtoskid){
		if(addsecuringtoskid == null || addsecuringtoskid.equals("")){
			return "";
		}else if(addsecuringtoskid.equals("1")){
			return "LAshing Using Metal Bands";
		}else if(addsecuringtoskid.equals("2")){
			return "LAshing Using Ratchet Belts";
		}else{
			return "LAshing Using Plastic Bands";
		}
	}
	public String getPestmanagement(String pestmanagement){
		if(pestmanagement == null || pestmanagement.equals("")){
			return "";
		}else if(pestmanagement.equals("1")){
			return "Fumigation";
		}else if(pestmanagement.equals("2")){
			return "Heat Treatment";
		}else{
			return "Use All Plywood";
		}
	}
	public String getShockwatch(String shockwatch){
		if(shockwatch == null || shockwatch.equals("")){
			return "";
		}else if(shockwatch.equals("1")){
			return "5g";
		}else if(shockwatch.equals("2")){
			return "10g";
		}else if(shockwatch.equals("3")){
			return "15g";
		}else if(shockwatch.equals("4")){
			return "25g";
		}else{
			return "50g";
		}
	}
	
	
	public String getTiltWatch(String titlewatch){
		if(titlewatch == null || titlewatch.equals("")){
			return "";
		}else if(titlewatch.equals("1")){
			return "Yes";
		}else{
			return "No";
		}
	}
}
