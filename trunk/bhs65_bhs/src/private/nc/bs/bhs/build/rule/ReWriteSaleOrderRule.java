package nc.bs.bhs.build.rule;

import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.bhs.build.AggSoOrderBuildHVO;
import nc.vo.bhs.build.SoOrderBuildHVO;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * build 保存后回写订单部分信息
 * @author Thinkpad
 * 20171018
 */
public class ReWriteSaleOrderRule implements IRule<AggSoOrderBuildHVO>{

	@Override
	public void process(AggSoOrderBuildHVO[] aggvos) {
		// TODO 自动生成的方法存根
		for (int i = 0; i < aggvos.length; i++) {
			execute(aggvos[i]);
		}
	}
	public void execute(AggSoOrderBuildHVO aggvo){
		SoOrderBuildHVO headvo = aggvo.getParentVO();
		String csaleorderid = headvo.getCsaleorderid();
		if(csaleorderid == null || csaleorderid.equals("") || csaleorderid.equals("null")){
			return;
		}
		SaleOrderVO ordervo = qryOrderVO(csaleorderid);
		SaleOrderHVO hvo =   ordervo.getParentVO();
		
//		hvo.setAttributeValue("surveyno", null);
//		hvo.setAttributeValue("surveyby", null);
//		hvo.setAttributeValue("surveydate", null);
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
		hvo.setAttributeValue("moveinstruction", null);
		hvo.setAttributeValue("ref", headvo.getRef());
//		hvo.setAttributeValue("vdef12", null);
//		hvo.setAttributeValue("vdef13", null);
		hvo.setAttributeValue("jobtype", headvo.getJobtype());
//		hvo.setAttributeValue("micapno",null);
//		hvo.setAttributeValue("oemtoolno", null);
//		hvo.setAttributeValue("toolidlid", null);
//		hvo.setAttributeValue("machinemake", null);
//		hvo.setAttributeValue("machinemodel", null);
//		hvo.setAttributeValue("suppliername", null);
//		hvo.setAttributeValue("m2", null);
//		hvo.setAttributeValue("m3", null);
//		hvo.setAttributeValue("kg", null);
//		hvo.setAttributeValue("pkgs", null);
//		hvo.setAttributeValue("chargeableweight", null);
//		hvo.setAttributeValue("netweight", null);
//		hvo.setAttributeValue("largestlength", null);
//		hvo.setAttributeValue("largestwidth", null);
//		hvo.setAttributeValue("largestheight", null);
//		hvo.setAttributeValue("largestweight", null);
//		hvo.setAttributeValue("lcrate", null);
//		hvo.setAttributeValue("wcrate", null);
//		hvo.setAttributeValue("hcrate", null);
//		hvo.setAttributeValue("kcrate", null);
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
