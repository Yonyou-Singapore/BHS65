package nc.bs.bhs.pack.rule;

import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.bhs.pack.AggSoOrderPackVO;
import nc.vo.bhs.pack.SoOrderPackVO;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * pack 保存后回写订单部分信息
 * @author Thinkpad
 * 20171018
 */
public class ReWriteSaleOrderRule implements IRule<AggSoOrderPackVO>{

	@Override
	public void process(AggSoOrderPackVO[] aggvos) {
		// TODO 自动生成的方法存根
		for (int i = 0; i < aggvos.length; i++) {
			execute(aggvos[i]);
		}
	}
	public void execute(AggSoOrderPackVO aggvo){
		SoOrderPackVO headvo = aggvo.getParentVO();
		String csaleorderid = headvo.getCsaleorderid();
		if(csaleorderid == null || csaleorderid.equals("") || csaleorderid.equals("null")){
			return;
		}
		SaleOrderVO ordervo = qryOrderVO(csaleorderid);
		SaleOrderHVO hvo =   ordervo.getParentVO();
		
		hvo.setAttributeValue("surveyno", headvo.getSurveyno());
		hvo.setAttributeValue("surveyby", headvo.getSurveyby());
		hvo.setAttributeValue("surveydate", headvo.getSurveydate());
		hvo.setAttributeValue("customerpono", headvo.getCustomerpono());
//		hvo.setAttributeValue("fromcorp", null);
//		hvo.setAttributeValue("fromaddress", null);
//		hvo.setAttributeValue("fromcode", null);
//		hvo.setAttributeValue("tocorp", null);
//		hvo.setAttributeValue("toaddress",null);
//		hvo.setAttributeValue("tocode", null);
		hvo.setAttributeValue("attnto",headvo.getAttnto());
		hvo.setAttributeValue("contractno", headvo.getContactno());
		hvo.setAttributeValue("contractperson", headvo.getContactperson());
		hvo.setAttributeValue("subject", headvo.getSubject());
		hvo.setAttributeValue("jobinstruction", headvo.getJobinstruction());
//		hvo.setAttributeValue("moveInstruction", null);
		hvo.setAttributeValue("ref", headvo.getRef());
//		hvo.setAttributeValue("vdef12", null);
//		hvo.setAttributeValue("vdef13", null);
		hvo.setAttributeValue("jobtype", headvo.getJobtype());
		hvo.setAttributeValue("micapno",headvo.getMicapno());
		hvo.setAttributeValue("oemtoolno", headvo.getOemtoolno());
		hvo.setAttributeValue("toolidlid", headvo.getToolidlid());
		hvo.setAttributeValue("machinemake", headvo.getMachinemake());
		hvo.setAttributeValue("machinemodel", headvo.getMachinemodel());
		hvo.setAttributeValue("machinesubmodel", headvo.getMachinesubmodel());
		hvo.setAttributeValue("suppliername", headvo.getSuppliername());
		hvo.setAttributeValue("m2", headvo.getSummarym2());
		hvo.setAttributeValue("m3", headvo.getSummarym3());
		hvo.setAttributeValue("kg", headvo.getSummarykg());
		hvo.setAttributeValue("pkgs", headvo.getSummarypkgs());
//		hvo.setAttributeValue("chargeableweight", null);
//		hvo.setAttributeValue("netweight", null);
		hvo.setAttributeValue("largestlength", headvo.getLargestlength());
		hvo.setAttributeValue("largestwidth", headvo.getLargestwidth());
		hvo.setAttributeValue("largestheight", headvo.getLargestheight());
		hvo.setAttributeValue("largestweight", headvo.getLargestweight());
		hvo.setAttributeValue("lcrate", headvo.getLcrate());
		hvo.setAttributeValue("wcrate", headvo.getWcrate());
		hvo.setAttributeValue("hcrate", headvo.getHcrate());
		hvo.setAttributeValue("kcrate", headvo.getKcrate());
//		hvo.setAttributeValue("awb", null);
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
