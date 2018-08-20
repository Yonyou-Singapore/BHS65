package nc.bs.bhs.sotruck.rule;

import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.bhs.somove.AggSoMoveHVO;
import nc.vo.bhs.somove.SoMoveHVO;
import nc.vo.bhs.sotruck.AggSoTruckHVO;
import nc.vo.bhs.sotruck.SoTruckHVO;
import nc.vo.bhs.sotruck.SoTruckToneBVO;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * truck 保存后回写订单部分信息
 * @author Thinkpad
 * 20171018
 */
public class ReWriteSaleOrderRule implements IRule<AggSoTruckHVO>{

	@Override
	public void process(AggSoTruckHVO[] aggvos) {
		// TODO 自动生成的方法存根
		for (int i = 0; i < aggvos.length; i++) {
			execute(aggvos[i]);
		}
	}
	public void execute(AggSoTruckHVO aggvo){
		
		SoTruckHVO headvo = aggvo.getParentVO();
		String csaleorderid = headvo.getCsaleorderid();
		if(csaleorderid == null || csaleorderid.equals("") || csaleorderid.equals("null")){
			return;
		}
		
		SoTruckToneBVO[] itemvos = (SoTruckToneBVO[]) aggvo.getChildren(SoTruckToneBVO.class);
		
		
		SaleOrderVO ordervo = qryOrderVO(csaleorderid);
		SaleOrderHVO hvo =   ordervo.getParentVO();
		
		
		if(headvo.getStatus() == VOStatus.DELETED){
			
			String olddono = hvo.getAttributeValue("vdef10") == null?"":hvo.getAttributeValue("vdef10").toString();
			for (int i = 0; i < itemvos.length; i++) {
				if(itemvos[i].getContainerno() != null && olddono.indexOf(itemvos[i].getContainerno()+",") != -1){
					olddono = olddono.replaceAll(itemvos[i].getContainerno()+",", "");
				}else if(itemvos[i].getContainerno() != null && olddono.indexOf(","+itemvos[i].getContainerno()) != -1){
					olddono = olddono.replaceAll(","+itemvos[i].getContainerno(), "");
				}else if(itemvos[i].getContainerno() != null && olddono.indexOf(itemvos[i].getContainerno()+"") != -1){
					olddono =  olddono.replaceAll(itemvos[i].getContainerno(), "");
				}
			}
			hvo.setAttributeValue("vdef10", olddono);
		}else{
			String olddono = hvo.getAttributeValue("vdef10") == null?"":hvo.getAttributeValue("vdef10")+",";
			for (int i = 0; i < itemvos.length; i++) {
				if(itemvos[i].getContainerno() != null && olddono.indexOf(itemvos[i].getContainerno()) == -1){
					hvo.setAttributeValue("vdef10", olddono+itemvos[i].getContainerno());
					olddono = hvo.getAttributeValue("vdef10") == null?"":hvo.getAttributeValue("vdef10")+",";
				}
			}
			
		}
		
		hvo.setAttributeValue("surveyno", headvo.getSurveyno());
		hvo.setAttributeValue("surveyby", headvo.getSurveyby());
		hvo.setAttributeValue("surveydate", headvo.getSurveydate());
		hvo.setAttributeValue("customerpono", headvo.getCustomerpono());
		hvo.setAttributeValue("fromcorp", headvo.getFromcorp());
		hvo.setAttributeValue("fromaddress", headvo.getTaapickupfromaddress());
		hvo.setAttributeValue("fromcode", headvo.getTaapickupfromcode());
		hvo.setAttributeValue("tocorp", headvo.getTocorp());
		hvo.setAttributeValue("toaddress",headvo.getTacdelivertoaddress());
		hvo.setAttributeValue("tocode", headvo.getTacdelivertocode());
		hvo.setAttributeValue("attnto",headvo.getAttnto());
		hvo.setAttributeValue("contractno", headvo.getTaacontactpersonphone());
		hvo.setAttributeValue("contractperson", headvo.getTaacontactperson());
		hvo.setAttributeValue("subject", headvo.getSubject());
		hvo.setAttributeValue("jobinstruction", headvo.getTacommoninstruction());
//		hvo.setAttributeValue("moveInstruction", headvo.getSpecialinstruction());
		hvo.setAttributeValue("ref", headvo.getRef());
		hvo.setAttributeValue("vdef12", headvo.getTsimportvoyage());
		hvo.setAttributeValue("vdef13", headvo.getTsimportvessel());
//		hvo.setAttributeValue("jobtype", headvo.getJobtype());
//		hvo.setAttributeValue("micapno",headvo.getMicapnumbermachineid());
//		hvo.setAttributeValue("oemtoolno", headvo.getOemtoolno());
//		hvo.setAttributeValue("toolidlid", headvo.getLidnumber());
//		hvo.setAttributeValue("machinemake", headvo.getMachinemake());
//		hvo.setAttributeValue("machinemodel", headvo.getMachinemodel());
//		hvo.setAttributeValue("machinesubmodel", headvo.getMachinesubmodel());
//		hvo.setAttributeValue("suppliername", null);
		hvo.setAttributeValue("m2", headvo.getTaaream2());
		hvo.setAttributeValue("m3", headvo.getTavolume());
		hvo.setAttributeValue("kg", headvo.getTagrossweight());
		hvo.setAttributeValue("pkgs", headvo.getTatotalofpkgs());
		hvo.setAttributeValue("chargeableweight", headvo.getTachargableweight());
		hvo.setAttributeValue("netweight", headvo.getNetweight());
		hvo.setAttributeValue("largestlength", headvo.getTalengthcm());
		hvo.setAttributeValue("largestwidth", headvo.getTawidthcm());
		hvo.setAttributeValue("largestheight", headvo.getTaheightcm());
		hvo.setAttributeValue("largestweight", headvo.getLargestweight());
		hvo.setAttributeValue("lcrate", headvo.getTalcrateno());
		hvo.setAttributeValue("wcrate", headvo.getTawcrateno());
		hvo.setAttributeValue("hcrate", headvo.getTahcrateno());
		hvo.setAttributeValue("kcrate", headvo.getKcrate());
		hvo.setAttributeValue("awb", headvo.getTaawbno());
		hvo.setStatus(VOStatus.UPDATED);
		hvo.setAttributeValue("dr", 0);
		hvo.setDr(Integer.valueOf(0));
		try {
			new BaseDAO().updateVO(hvo);
		} catch (DAOException e) {
			// TODO 自动生成的 catch 块
			ExceptionUtils.wrappBusinessException("update order info error");
		}
	}
	/**
	 * 查询销售订单
	 * @param csaleorderid
	 * @return
	 */
	public SaleOrderVO qryOrderVO(String csaleorderid){
		BillQuery<SaleOrderVO> query = new BillQuery<SaleOrderVO>(SaleOrderVO.class);
		SaleOrderVO[] ordervos = query.query(new String[]{csaleorderid});
		if(ordervos == null || ordervos.length == 0){
			return null;
		}
		return ordervos[0];
	}

}
