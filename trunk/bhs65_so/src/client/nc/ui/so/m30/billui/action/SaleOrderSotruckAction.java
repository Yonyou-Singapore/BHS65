package nc.ui.so.m30.billui.action;

import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.itf.pubapp.pub.smart.IBillQueryService;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnListProcessor;
import nc.md.persist.framework.MDPersistenceService;
import nc.vo.bhs.build.AggSoOrderBuildHVO;
import nc.vo.bhs.sotruck.AggSoTruckHVO;
import nc.vo.bhs.sotruck.SoTruckHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDate;
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
public class SaleOrderSotruckAction extends SaleOrderBaseAction {

	private static final long serialVersionUID = -5751011383555490172L;

	public SaleOrderSotruckAction() {
		// TODO 自动生成的构造函数存根
		setCode("saleorderSotruckaction");
		setBtnName("Truck-Sea");
	}
	
	@Override
	public AbstractBill[] qryBackwardVO(SaleOrderHVO headvo) throws BusinessException {
		// TODO 自动生成的方法存根
		AggSoTruckHVO[] aggvos = null;
		//查询下游build的主键
		String sql = "select billid from bhs_sotruck_h where csaleorderid = '"+headvo.getCsaleorderid()+"' and isnull(dr,0) = 0 and isnull(transtypecode,'~') = 'TRUC-Cxx-sea'";//TRUC-Cxx-001
		List<Object> list = (List<Object>) NCLocator.getInstance().lookup(IUAPQueryBS.class).executeQuery(sql, new ColumnListProcessor());
		//如果 不存在则新增
		if(list == null || list.size() == 0){
			AggSoTruckHVO aggvo =  new AggSoTruckHVO();
			SoTruckHVO vo = new SoTruckHVO();
			vo.setPk_group(headvo.getPk_group());
			vo.setPk_org(headvo.getPk_org());
			vo.setPk_org_v(headvo.getPk_org_v());
			vo.setCsaleorderid(headvo.getCsaleorderid());
			vo.setStatus(VOStatus.NEW);
			vo.setCsaleordercode(headvo.getVbillcode());
			vo.setDbilldate(headvo.getDbilldate());
			vo.setVbillstatus(-1);
			vo.setPk_transtype("1001V81000000002346Y");//1001V81000000002346Y   ,,本机1001V810000000022OA0
			vo.setTranstypecode("TRUC-Cxx-sea"); //TRUC-Cxx-sea    ,本机 TRUC-Cxx-001
			updateHeadVOValue(vo, headvo);
			aggvo.setParentVO(vo);
			aggvos = new AggSoTruckHVO[1];
			aggvos[0] = aggvo;
		}else{
			
			IBillQueryService billQuery = (IBillQueryService) NCLocator
					.getInstance()
					.lookup(nc.itf.pubapp.pub.smart.IBillQueryService.class);
			AbstractBill[] bills = billQuery.queryAbstractBillsByPks(
					AggSoTruckHVO.class, list.toArray(new String[0]));
			aggvos = new AggSoTruckHVO[bills.length];
			for (int i = 0; i < bills.length; i++) {
				aggvos[i] =  new AggSoTruckHVO();
				aggvos[i] = (AggSoTruckHVO) bills[i];
			}
		}
		return aggvos;
	}
	
	/**
	 * 将销售订单信息带到move中
	 * @param hvo
	 * @param headvo
	 */
	public void updateHeadVOValue(SoTruckHVO hvo,SaleOrderHVO headvo){
		
		hvo.setSurveyno(headvo.getAttributeValue("surveyno") == null?"":headvo.getAttributeValue("surveyno").toString());
		hvo.setSurveyby(headvo.getAttributeValue("surveyby") == null?"":headvo.getAttributeValue("surveyby").toString());
		hvo.setSurveydate(headvo.getAttributeValue("surveydate") == null?null:new UFDate(headvo.getAttributeValue("surveydate").toString()));
		hvo.setCustomerpono(headvo.getAttributeValue("customerpono") == null?"":headvo.getAttributeValue("customerpono").toString());
		hvo.setFromcorp(headvo.getAttributeValue("fromcorp") == null?"":headvo.getAttributeValue("fromcorp").toString());
		hvo.setTaapickupfromaddress(headvo.getAttributeValue("fromaddress") == null?"":headvo.getAttributeValue("fromaddress").toString());
		hvo.setTaapickupfromcode(headvo.getAttributeValue("fromcode") == null?"":headvo.getAttributeValue("fromcode").toString());
		hvo.setTocorp(headvo.getAttributeValue("tocorp") == null?"":headvo.getAttributeValue("tocorp").toString());
		hvo.setTacdelivertoaddress(headvo.getAttributeValue("toaddress") == null?"":headvo.getAttributeValue("toaddress").toString());
		hvo.setTacdelivertocode(headvo.getAttributeValue("tocode") == null?"":headvo.getAttributeValue("tocode").toString());
		hvo.setAttnto(headvo.getAttributeValue("attnto") == null?"":headvo.getAttributeValue("attnto").toString());
		hvo.setTaacontactpersonphone(headvo.getAttributeValue("contractno") == null?"":headvo.getAttributeValue("contractno").toString());
		hvo.setTaacontactperson(headvo.getAttributeValue("contractperson") == null?"":headvo.getAttributeValue("contractperson").toString());
		hvo.setSubject(headvo.getAttributeValue("subject") == null?"":headvo.getAttributeValue("subject").toString());
		hvo.setTacommoninstruction(headvo.getAttributeValue("jobinstruction") == null?"":headvo.getAttributeValue("jobinstruction").toString());
		hvo.setRef(headvo.getAttributeValue("ref") == null?"":headvo.getAttributeValue("ref").toString());
		hvo.setTsimportvoyage(headvo.getAttributeValue("vdef12") == null?"":headvo.getAttributeValue("vdef12").toString());
		hvo.setTsimportvessel(headvo.getAttributeValue("vdef13") == null?"":headvo.getAttributeValue("vdef13").toString());
		hvo.setTaaream2(headvo.getAttributeValue("m2") == null?UFDouble.ZERO_DBL:new UFDouble(headvo.getAttributeValue("m2").toString()));
		hvo.setTavolume(headvo.getAttributeValue("m3") == null?UFDouble.ZERO_DBL:new UFDouble(headvo.getAttributeValue("m3").toString()));
		hvo.setTagrossweight(headvo.getAttributeValue("kg") == null?UFDouble.ZERO_DBL:new UFDouble(headvo.getAttributeValue("kg").toString()));
		hvo.setTatotalofpkgs(headvo.getAttributeValue("pkgs") == null?UFDouble.ZERO_DBL:new UFDouble(headvo.getAttributeValue("pkgs").toString()));
		hvo.setTachargableweight(headvo.getAttributeValue("chargeableweight") == null?UFDouble.ZERO_DBL:new UFDouble(headvo.getAttributeValue("chargeableweight").toString()));
		hvo.setNetweight(headvo.getAttributeValue("netweight") == null?UFDouble.ZERO_DBL:new UFDouble(headvo.getAttributeValue("netweight").toString()));
		hvo.setTaheightcm(headvo.getAttributeValue("largestheight") == null?UFDouble.ZERO_DBL:new UFDouble(headvo.getAttributeValue("largestheight").toString()));
		hvo.setTalengthcm(headvo.getAttributeValue("largestlength") == null?UFDouble.ZERO_DBL:new UFDouble(headvo.getAttributeValue("largestlength").toString()));
		hvo.setTawidthcm(headvo.getAttributeValue("largestwidth") == null?UFDouble.ZERO_DBL:new UFDouble(headvo.getAttributeValue("largestwidth").toString()));
		hvo.setLargestweight(headvo.getAttributeValue("largestweight") == null?UFDouble.ZERO_DBL:new UFDouble(headvo.getAttributeValue("largestweight").toString()));
		hvo.setTalcrateno(headvo.getAttributeValue("lcrate") == null?UFDouble.ZERO_DBL:new UFDouble(headvo.getAttributeValue("lcrate").toString()));
		hvo.setTahcrateno(headvo.getAttributeValue("hcrate") == null?UFDouble.ZERO_DBL:new UFDouble(headvo.getAttributeValue("hcrate").toString()));
		hvo.setTawcrateno(headvo.getAttributeValue("wcrate") == null?UFDouble.ZERO_DBL:new UFDouble(headvo.getAttributeValue("wcrate").toString()));
		hvo.setKcrate(headvo.getAttributeValue("kcrate") == null?"":headvo.getAttributeValue("kcrate").toString());
		hvo.setTaawbno(headvo.getAttributeValue("awb") == null?"":headvo.getAttributeValue("awb").toString());
		
	}
	
	
}
