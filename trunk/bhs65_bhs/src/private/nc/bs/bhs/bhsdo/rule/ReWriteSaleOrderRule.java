package nc.bs.bhs.bhsdo.rule;

import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.bhs.bhsdo.AggBhsDoVO;
import nc.vo.bhs.bhsdo.BhsDoVO;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * pack 保存后回写订单部分信息
 * @author Thinkpad
 * 20171018
 */
public class ReWriteSaleOrderRule implements IRule<AggBhsDoVO>{

	@Override
	public void process(AggBhsDoVO[] aggvos) {
		// TODO 自动生成的方法存根
		for (int i = 0; i < aggvos.length; i++) {
			execute(aggvos[i]);
		}
	}
	public void execute(AggBhsDoVO aggvo){
		BhsDoVO headvo = aggvo.getParentVO();
		String csaleorderid = headvo.getPk_saleorder();
		if(csaleorderid == null || csaleorderid.equals("") || csaleorderid.equals("null")){
			return;
		}
		SaleOrderVO ordervo = qryOrderVO(csaleorderid);
		SaleOrderHVO hvo =   ordervo.getParentVO();
		
		
		if(headvo.getStatus() == VOStatus.DELETED){
			String olddono = hvo.getAttributeValue("dono") == null?"":hvo.getAttributeValue("dono").toString();
			if(olddono.indexOf(headvo.getVbillno()+",") != -1){
				hvo.setAttributeValue("dono", olddono.replaceAll(headvo.getVbillno()+",", ""));
			}else if(olddono.indexOf(","+headvo.getVbillno()) != -1){
				hvo.setAttributeValue("dono", olddono.replaceAll(","+headvo.getVbillno(), ""));
			}else if(olddono.indexOf(headvo.getVbillno()+"") != -1){
				hvo.setAttributeValue("dono", olddono.replaceAll(headvo.getVbillno(), ""));
			}
		}else{
			String olddono = hvo.getAttributeValue("dono") == null?"":hvo.getAttributeValue("dono")+",";
			if(olddono.indexOf(headvo.getVbillno()) == -1){
				hvo.setAttributeValue("dono", olddono+headvo.getVbillno());
			}
		}
		
//		hvo.setAttributeValue("surveyno", headvo.getSurveyno());
//		hvo.setAttributeValue("surveyby", headvo.getSurveyby());
//		hvo.setAttributeValue("surveydate", headvo.getSurveydate());
		
		
//		hvo.setAttributeValue("customerpono", headvo.getCustomerpono());
//		hvo.setAttributeValue("fromcorp", headvo.getFromcorp());
//		hvo.setAttributeValue("fromaddress", headvo.getFromaddress());
//		hvo.setAttributeValue("fromcode", headvo.getFromcode());
//		hvo.setAttributeValue("tocorp", headvo.getTocorp());
//		hvo.setAttributeValue("toaddress",headvo.getToaddress());
//		hvo.setAttributeValue("tocode", headvo.getTocode());
//		hvo.setAttributeValue("attnto",headvo.getAttnto());
//		hvo.setAttributeValue("contractno", headvo.getContactno());
//		hvo.setAttributeValue("contractperson", headvo.getContactperson());
//		hvo.setAttributeValue("subject", headvo.getSubject());
//		hvo.setAttributeValue("jobinstruction", headvo.getJobinstruction());
//		hvo.setAttributeValue("moveInstruction", headvo.getMoveinstruction());
//		hvo.setAttributeValue("ref", headvo.getRef());
//		hvo.setAttributeValue("vdef12", headvo.getDovoyage());
//		hvo.setAttributeValue("vdef13", headvo.getDovessel());
////		hvo.setAttributeValue("jobtype", headvo.getJobtype());
//		hvo.setAttributeValue("micapno",headvo.getMicapno());
//		hvo.setAttributeValue("oemtoolno", headvo.getOemtoolno());
//		hvo.setAttributeValue("toolidlid", headvo.getToolidlid());
//		hvo.setAttributeValue("machinemake", headvo.getMachinemake());
//		hvo.setAttributeValue("machinemodel", headvo.getMachinemodel());
//		hvo.setAttributeValue("machinesubmodel", headvo.getMachinesubmodel());
////		hvo.setAttributeValue("suppliername", headvo.getSuppliername());
//		hvo.setAttributeValue("m2", headvo.getM2());
//		hvo.setAttributeValue("m3", headvo.getM3());
//		hvo.setAttributeValue("kg", headvo.getKg());
//		hvo.setAttributeValue("pkgs", headvo.getPkgs());
//		hvo.setAttributeValue("chargeableweight", headvo.getChargeableweight());
//		hvo.setAttributeValue("netweight", headvo.getNetweight());
//		hvo.setAttributeValue("largestlength", headvo.getLargestlength());
//		hvo.setAttributeValue("largestwidth", headvo.getLargestwidth());
//		hvo.setAttributeValue("largestheight", headvo.getLargestheight());
//		hvo.setAttributeValue("largestweight", headvo.getLargestweight());
//		hvo.setAttributeValue("lcrate", headvo.getLcrate());
//		hvo.setAttributeValue("wcrate", headvo.getWcrate());
//		hvo.setAttributeValue("hcrate", headvo.getHcrate());
//		hvo.setAttributeValue("kcrate", headvo.getKcrate());
//		hvo.setAttributeValue("awb", headvo.getAwb());
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
