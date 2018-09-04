package nc.ui.so.m30.billui.action;

import java.util.Collection;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.itf.pubapp.pub.smart.IBillQueryService;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnListProcessor;
import nc.vo.bhs.pack.SoOrderPackBVO;
import nc.vo.bhs.sostore.AggSoStoreHVO;
import nc.vo.bhs.sostore.SoStoreBVO;
import nc.vo.bhs.sostore.SoStoreHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.so.m30.entity.SaleOrderHVO;


/**
 * 打开单据 
 * 
 * @author Thinkpad 
 * 20170306
 * 
 */
public class SaleOrderSostoreAction extends SaleOrderBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5751011383555490172L;

	public SaleOrderSostoreAction() {
		// TODO 自动生成的构造函数存根
		setCode("saleordersostoreaction");
		setBtnName("Store");
	}
	
	@Override
	public AbstractBill[] qryBackwardVO(SaleOrderHVO headvo) throws BusinessException {
		// TODO 自动生成的方法存根
		AggSoStoreHVO[] aggvos = null;
		//查询下游build的主键
		String sql = "select billid from bhs_sostore_h where csaleorderid = '"+headvo.getCsaleorderid()+"' and isnull(dr,0) = 0 ";
		List<Object> list = (List<Object>) NCLocator.getInstance().lookup(IUAPQueryBS.class).executeQuery(sql, new ColumnListProcessor());
		//如果 不存在则新增
		if(list == null || list.size() == 0){
			AggSoStoreHVO aggvo =  new AggSoStoreHVO();
			SoStoreHVO vo = new SoStoreHVO();
			vo.setPk_group(headvo.getPk_group());
			vo.setPk_org(headvo.getPk_org());
			vo.setPk_org_v(headvo.getPk_org_v());
			vo.setCsaleorderid(headvo.getCsaleorderid());
			vo.setStatus(VOStatus.NEW);
			vo.setCsaleordercode(headvo.getVbillcode());
			vo.setDbilldate(headvo.getDbilldate());
			vo.setVbillstatus(-1);
			
			updateHeadVOValue(vo, headvo);
			
			SoStoreBVO[] bvos = getPackDetail(vo, headvo);
			aggvo.setChildrenVO(bvos);
			
			aggvo.setParentVO(vo);
			aggvos = new AggSoStoreHVO[1];
			aggvos[0] = aggvo;
		}else{
			IBillQueryService billQuery = (IBillQueryService) NCLocator
					.getInstance()
					.lookup(nc.itf.pubapp.pub.smart.IBillQueryService.class);
			AbstractBill[] bills = billQuery.queryAbstractBillsByPks(
					AggSoStoreHVO.class, list.toArray(new String[0]));
			aggvos = new AggSoStoreHVO[bills.length];
			for (int i = 0; i < bills.length; i++) {
				aggvos[i] =  new AggSoStoreHVO();
				aggvos[i] = (AggSoStoreHVO) bills[i];
			}
		}
		return aggvos;
	}
	
	private SoStoreBVO[] getPackDetail(SoStoreHVO vo, SaleOrderHVO headvo) throws BusinessException {
		String condition = " billid in (select billid from bhs_pack_h where csaleorderid = '"+headvo.getCsaleorderid()+"') and isnull(dr,0) = 0 ";
		Collection<SoOrderPackBVO> list = (Collection<SoOrderPackBVO>) NCLocator.getInstance().lookup(IUAPQueryBS.class).retrieveByClause(SoOrderPackBVO.class, condition);
		SoStoreBVO[] bvos = new SoStoreBVO[list.size()];
		int i=0;
		for(SoOrderPackBVO packvo: list){
			SoStoreBVO bvo = new SoStoreBVO();
			bvo.setStatus(VOStatus.NEW);
			
			bvo.setCsrcid(packvo.getBillid());
			bvo.setCsrcbid(packvo.getBillid_b());
			bvo.setRowno(packvo.getRowno());
			bvo.setDef11(packvo.getSnno());//S/N
			bvo.setDef2(packvo.getPackdescription());//Description
			bvo.setDef4(String.valueOf(packvo.getPackqty()));//Qty
			bvo.setDef6(String.valueOf(packvo.getCratedimensionl()));//Length
			bvo.setDef7(String.valueOf(packvo.getCratedimensionw()));//Width
			bvo.setDef8(String.valueOf(packvo.getCratedimensionh()));//Height
			bvo.setDef9(String.valueOf(packvo.getCratedimensionweight()));//Weight
			
			bvos[i] = bvo;
			i++;
		}
		return bvos;
	}

	/**
	 * 将销售订单信息带到store中
	 * @param hvo
	 * @param headvo
	 */
	public void updateHeadVOValue(SoStoreHVO hvo,SaleOrderHVO headvo){
		
		hvo.setPono(headvo.getAttributeValue("customerpono") == null?"":headvo.getAttributeValue("customerpono").toString());
		hvo.setAttnto(headvo.getAttributeValue("attnto") == null?"":headvo.getAttributeValue("attnto").toString());
		hvo.setContactno(headvo.getAttributeValue("contractno") == null?"":headvo.getAttributeValue("contractno").toString());
		hvo.setContactperson(headvo.getAttributeValue("contractperson") == null?"":headvo.getAttributeValue("contractperson").toString());
		hvo.setSubject(headvo.getAttributeValue("subject") == null?"":headvo.getAttributeValue("subject").toString());
		hvo.setJobinstruction(headvo.getAttributeValue("jobinstruction") == null?"":headvo.getAttributeValue("jobinstruction").toString());
		hvo.setRef(headvo.getAttributeValue("ref") == null?"":headvo.getAttributeValue("ref").toString());
		
		//add chenth 20180825
//		hvo.setDef19(headvo.getAttributeValue("toolidlid") == null?"":headvo.getAttributeValue("toolidlid").toString());
//		hvo.setDef17(headvo.getAttributeValue("machinemodel") == null?"":headvo.getAttributeValue("machinemodel").toString());
//		hvo.setDef18(headvo.getAttributeValue("machinesubmodel") == null?"":headvo.getAttributeValue("machinesubmodel").toString());
//		hvo.setDef16(headvo.getAttributeValue("suppliername") == null?"":headvo.getAttributeValue("suppliername").toString());
//		hvo.setDef2(headvo.getAttributeValue("pkgs") == null?"":headvo.getAttributeValue("pkgs").toString());
//		hvo.setDef9(headvo.getAttributeValue("largestlength") == null?"":headvo.getAttributeValue("largestlength").toString());
//		hvo.setDef10(headvo.getAttributeValue("largestwidth") == null?"":headvo.getAttributeValue("largestwidth").toString());
//		hvo.setDef11(headvo.getAttributeValue("largestheight") == null?"":headvo.getAttributeValue("largestheight").toString());
//		hvo.setDef12(headvo.getAttributeValue("largestweight") == null?"":headvo.getAttributeValue("largestweight").toString());
		
		//add chenth 20180903
		hvo.setMicapno(headvo.getAttributeValue("micapno") == null?"":headvo.getAttributeValue("micapno").toString());
		hvo.setOemtoolno(headvo.getAttributeValue("oemtoolno") == null?"":headvo.getAttributeValue("oemtoolno").toString());
		hvo.setToolidlid(headvo.getAttributeValue("toolidlid") == null?"":headvo.getAttributeValue("toolidlid").toString());
		hvo.setMachinemake(headvo.getAttributeValue("machinemake") == null?"":headvo.getAttributeValue("machinemake").toString());
		hvo.setMachinemodel(headvo.getAttributeValue("machinemodel") == null?"":headvo.getAttributeValue("machinemodel").toString());
		hvo.setMachinesubmodel(headvo.getAttributeValue("machinesubmodel") == null?"":headvo.getAttributeValue("machinesubmodel").toString());
		hvo.setSuppliername(headvo.getAttributeValue("suppliername") == null?"":headvo.getAttributeValue("suppliername").toString());
		hvo.setSummarym2(headvo.getAttributeValue("m2") == null?UFDouble.ZERO_DBL:new UFDouble(headvo.getAttributeValue("m2").toString()));
		hvo.setSummarym3(headvo.getAttributeValue("m3") == null?UFDouble.ZERO_DBL:new UFDouble(headvo.getAttributeValue("m3").toString()));
		hvo.setSummarykg(headvo.getAttributeValue("kg") == null?UFDouble.ZERO_DBL:new UFDouble(headvo.getAttributeValue("kg").toString()));
		hvo.setSummarypkgs(headvo.getAttributeValue("pkgs") == null?UFDouble.ZERO_DBL:new UFDouble(headvo.getAttributeValue("pkgs").toString()));
		hvo.setLargestheight(headvo.getAttributeValue("largestheight") == null?UFDouble.ZERO_DBL:new UFDouble(headvo.getAttributeValue("largestheight").toString()));
		hvo.setLargestlength(headvo.getAttributeValue("largestlength") == null?UFDouble.ZERO_DBL:new UFDouble(headvo.getAttributeValue("largestlength").toString()));
		hvo.setLargestwidth(headvo.getAttributeValue("largestwidth") == null?UFDouble.ZERO_DBL:new UFDouble(headvo.getAttributeValue("largestwidth").toString()));
		hvo.setLargestweight(headvo.getAttributeValue("largestweight") == null?UFDouble.ZERO_DBL:new UFDouble(headvo.getAttributeValue("largestweight").toString()));
		hvo.setLcrate(headvo.getAttributeValue("lcrate") == null?"":headvo.getAttributeValue("lcrate").toString());
		hvo.setHcrate(headvo.getAttributeValue("hcrate") == null?"":headvo.getAttributeValue("hcrate").toString());
		hvo.setWcrate(headvo.getAttributeValue("wcrate") == null?"":headvo.getAttributeValue("wcrate").toString());
		hvo.setKcrate(headvo.getAttributeValue("kcrate") == null?"":headvo.getAttributeValue("kcrate").toString());
		
	}
	
}
