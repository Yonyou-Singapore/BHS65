package nc.bs.bhs.sostore.rule;

import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.bhs.somove.SoMoveHVO;
import nc.vo.bhs.sostore.AggSoStoreHVO;
import nc.vo.bhs.sostore.SoStoreHVO;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * move 保存后回写订单部分信息
 * @author Thinkpad
 * 20171018
 */
public class ReWriteSaleOrderRule implements IRule<AggSoStoreHVO>{

	@Override
	public void process(AggSoStoreHVO[] aggvos) {
		// TODO 自动生成的方法存根
		for (int i = 0; i < aggvos.length; i++) {
			execute(aggvos[i]);
		}
	}
	public void execute(AggSoStoreHVO aggvo){
		SoStoreHVO headvo = aggvo.getParentVO();
		String csaleorderid = headvo.getCsaleorderid();
		if(csaleorderid == null || csaleorderid.equals("") || csaleorderid.equals("null")){
			return;
		}
		SaleOrderVO ordervo = qryOrderVO(csaleorderid);
		SaleOrderHVO hvo =   ordervo.getParentVO();
		
//		hvo.setAttributeValue("surveyno", headvo.getSurveyno());
//		hvo.setAttributeValue("surveyby", headvo.getSurveyby());
//		hvo.setAttributeValue("surveydate", headvo.getSurveydate());
		hvo.setAttributeValue("customerpono", headvo.getPono());
//		hvo.setAttributeValue("fromcorp", headvo.getFromcorp());
//		hvo.setAttributeValue("fromaddress", headvo.getFromaddress());
//		hvo.setAttributeValue("fromcode", headvo.getFromcode());
//		hvo.setAttributeValue("tocorp", headvo.getTocorp());
//		hvo.setAttributeValue("toaddress",headvo.getToaddress());
//		hvo.setAttributeValue("tocode", headvo.getTocode());
		hvo.setAttributeValue("attnto",headvo.getAttnto());
		hvo.setAttributeValue("contractno", headvo.getContactno());
		hvo.setAttributeValue("contractperson", headvo.getContactperson());
		hvo.setAttributeValue("subject", headvo.getSubject());
		hvo.setAttributeValue("jobinstruction", headvo.getJobinstruction());
//		hvo.setAttributeValue("moveInstruction", headvo.getSpecialinstruction());
		hvo.setAttributeValue("ref", headvo.getRef());
//		hvo.setAttributeValue("vdef12", null);
//		hvo.setAttributeValue("vdef13", null);
//		hvo.setAttributeValue("jobtype", headvo.getJobtype());
//		hvo.setAttributeValue("micapno",headvo.getMicapnumbermachineid());
//		hvo.setAttributeValue("oemtoolno", headvo.getOemtoolno());
//		hvo.setAttributeValue("toolidlid", headvo.getLidnumber());
//		hvo.setAttributeValue("machinemake", headvo.getMachinemake());
//		hvo.setAttributeValue("machinemodel", headvo.getMachinemodel());
//		hvo.setAttributeValue("suppliername", null);
//		hvo.setAttributeValue("m2", null);
//		hvo.setAttributeValue("m3", headvo.getVolumem3());
//		hvo.setAttributeValue("kg", headvo.getGrossweight());
//		hvo.setAttributeValue("pkgs", headvo.getTotalofpkgs());
//		hvo.setAttributeValue("chargeableweight", headvo.getChargeableweight());
//		hvo.setAttributeValue("netweight", headvo.getNetweight());
//		hvo.setAttributeValue("largestlength", headvo.getLengthcm());
//		hvo.setAttributeValue("largestwidth", headvo.getWidthcm());
//		hvo.setAttributeValue("largestheight", headvo.getHeightcm());
//		hvo.setAttributeValue("largestweight", headvo.getLargestweight());
//		hvo.setAttributeValue("lcrate", headvo.getCrate1());
//		hvo.setAttributeValue("wcrate", headvo.getCrate2());
//		hvo.setAttributeValue("hcrate", headvo.getCrate3());
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
