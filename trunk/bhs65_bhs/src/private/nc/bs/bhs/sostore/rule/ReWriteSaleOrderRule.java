package nc.bs.bhs.sostore.rule;

import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.bhs.somove.SoMoveHVO;
import nc.vo.bhs.sostore.AggSoStoreHVO;
import nc.vo.bhs.sostore.SoStoreHVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * move ������д����������Ϣ
 * @author Thinkpad
 * 20171018
 */
public class ReWriteSaleOrderRule implements IRule<AggSoStoreHVO>{

	@Override
	public void process(AggSoStoreHVO[] aggvos) {
		// TODO �Զ����ɵķ������
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
//		hvo.setAttributeValue("chargeableweight", headvo.getChargeableweight());
//		hvo.setAttributeValue("netweight", headvo.getNetweight());
		hvo.setAttributeValue("largestlength", headvo.getLargestlength());
		hvo.setAttributeValue("largestwidth", headvo.getLargestwidth());
		hvo.setAttributeValue("largestheight", headvo.getLargestheight());
		hvo.setAttributeValue("largestweight", headvo.getLargestweight());
		hvo.setAttributeValue("lcrate", headvo.getLcrate());
		hvo.setAttributeValue("wcrate", headvo.getWcrate());
		hvo.setAttributeValue("hcrate", headvo.getHcrate());
		hvo.setAttributeValue("kcrate", headvo.getKcrate());
//		hvo.setAttributeValue("awb", headvo.getAwb());
		hvo.setStatus(VOStatus.UPDATED);
		hvo.setAttributeValue("dr", 0);
		hvo.setDr(Integer.valueOf(0));
		//add chenth 20180904 ���� Topple Risk
		hvo.setVdef16(headvo.getIstopplerisk() == null ? UFBoolean.FALSE.toString() :
			headvo.getIstopplerisk().toString());
		try {
			new BaseDAO().updateVO(hvo);
		} catch (DAOException e) {
			// TODO �Զ����ɵ� catch ��
			ExceptionUtils.wrappBusinessException("update order info error");
		}
	}
	/**
	 * ��ѯ���۶���
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
