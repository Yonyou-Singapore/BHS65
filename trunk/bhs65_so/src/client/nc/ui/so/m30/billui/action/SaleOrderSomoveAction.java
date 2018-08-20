package nc.ui.so.m30.billui.action;

import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.itf.pubapp.pub.smart.IBillQueryService;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnListProcessor;
import nc.md.persist.framework.MDPersistenceService;
import nc.vo.bhs.somove.AggSoMoveHVO;
import nc.vo.bhs.somove.SoMoveHVO;
import nc.vo.bhs.sostore.AggSoStoreHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.so.m30.entity.SaleOrderHVO;


/**
 * 打开somove填写
 * @author Thinkpad 
 * 20170306
 * 
 */
public class SaleOrderSomoveAction extends SaleOrderBaseAction {
	private static final long serialVersionUID = -5751011383555490172L;

	public SaleOrderSomoveAction() {
		// TODO 自动生成的构造函数存根
		setCode("saleorderSomoveaction");
		setBtnName("Move");
	}
	
	@Override
	public AbstractBill[] qryBackwardVO(SaleOrderHVO headvo) throws BusinessException {
		// TODO 自动生成的方法存根
		AggSoMoveHVO[] aggvos = null;
		//查询下游build的主键
		String sql = "select billid from bhs_somove_h where csaleorderid = '"+headvo.getCsaleorderid()+"' and isnull(dr,0) = 0 ";
		List<Object> list = (List<Object>) NCLocator.getInstance().lookup(IUAPQueryBS.class).executeQuery(sql, new ColumnListProcessor());
		//如果 不存在则新增
		if(list == null || list.size() == 0){
			AggSoMoveHVO aggvo =  new AggSoMoveHVO();
			SoMoveHVO vo = new SoMoveHVO();
			vo.setPk_group(headvo.getPk_group());
			vo.setPk_org(headvo.getPk_org());
			vo.setPk_org_v(headvo.getPk_org_v());
			vo.setCsaleorderid(headvo.getCsaleorderid());
			vo.setStatus(VOStatus.NEW);
			vo.setCsaleordercode(headvo.getVbillcode());
			vo.setDbilldate(headvo.getDbilldate());
			vo.setVbillstatus(-1);
			
			updateHeadVOValue(vo, headvo);
			
			aggvo.setParentVO(vo);
			aggvos = new AggSoMoveHVO[1];
			aggvos[0] = aggvo;
		}else{
			IBillQueryService billQuery = (IBillQueryService) NCLocator
					.getInstance()
					.lookup(nc.itf.pubapp.pub.smart.IBillQueryService.class);
			AbstractBill[] bills = billQuery.queryAbstractBillsByPks(
					AggSoMoveHVO.class, list.toArray(new String[0]));
			aggvos = new AggSoMoveHVO[bills.length];
			for (int i = 0; i < bills.length; i++) {
				aggvos[i] =  new AggSoMoveHVO();
				aggvos[i] = (AggSoMoveHVO) bills[i];
			}
		}
		return aggvos;
	}
	
	/**
	 * 将销售订单信息带到move中
	 * @param hvo
	 * @param headvo
	 */
	public void updateHeadVOValue(SoMoveHVO hvo,SaleOrderHVO headvo){
		
		hvo.setSurveyno(headvo.getAttributeValue("surveyno") == null?"":headvo.getAttributeValue("surveyno").toString());
		hvo.setSurveyby(headvo.getAttributeValue("surveyby") == null?"":headvo.getAttributeValue("surveyby").toString());
		hvo.setSurveydate(headvo.getAttributeValue("surveydate") == null?null:new UFDate(headvo.getAttributeValue("surveydate").toString()));
		hvo.setCustomerpono(headvo.getAttributeValue("customerpono") == null?"":headvo.getAttributeValue("customerpono").toString());
		hvo.setFromcorp(headvo.getAttributeValue("fromcorp") == null?"":headvo.getAttributeValue("fromcorp").toString());
		hvo.setFromaddress(headvo.getAttributeValue("fromaddress") == null?"":headvo.getAttributeValue("fromaddress").toString());
		hvo.setFromcode(headvo.getAttributeValue("fromcode") == null?"":headvo.getAttributeValue("fromcode").toString());
		hvo.setTocorp(headvo.getAttributeValue("tocorp") == null?"":headvo.getAttributeValue("tocorp").toString());
		hvo.setToaddress(headvo.getAttributeValue("toaddress") == null?"":headvo.getAttributeValue("toaddress").toString());
		hvo.setTocode(headvo.getAttributeValue("tocode") == null?"":headvo.getAttributeValue("tocode").toString());
		hvo.setAttnto(headvo.getAttributeValue("attnto") == null?"":headvo.getAttributeValue("attnto").toString());
		hvo.setContactpersphone(headvo.getAttributeValue("contractno") == null?"":headvo.getAttributeValue("contractno").toString());
		hvo.setContactperson(headvo.getAttributeValue("contractperson") == null?"":headvo.getAttributeValue("contractperson").toString());
		hvo.setSubject(headvo.getAttributeValue("subject") == null?"":headvo.getAttributeValue("subject").toString());
		hvo.setJobinstruction(headvo.getAttributeValue("jobinstruction") == null?"":headvo.getAttributeValue("jobinstruction").toString());
		hvo.setSpecialinstruction(headvo.getAttributeValue("moveInstruction") == null?"":headvo.getAttributeValue("moveInstruction").toString());
		hvo.setRef(headvo.getAttributeValue("ref") == null?"":headvo.getAttributeValue("ref").toString());
		hvo.setJobtype(headvo.getAttributeValue("jobtype") == null?"":headvo.getAttributeValue("jobtype").toString());
		hvo.setMicapnumbermachineid(headvo.getAttributeValue("micapno") == null?"":headvo.getAttributeValue("micapno").toString());
		hvo.setOemtoolno(headvo.getAttributeValue("oemtoolno") == null?"":headvo.getAttributeValue("oemtoolno").toString());
		hvo.setLidnumber(headvo.getAttributeValue("toolidlid") == null?"":headvo.getAttributeValue("toolidlid").toString());
		hvo.setMachinemake(headvo.getAttributeValue("machinemake") == null?"":headvo.getAttributeValue("machinemake").toString());
		hvo.setMachinemodel(headvo.getAttributeValue("machinemodel") == null?"":headvo.getAttributeValue("machinemodel").toString());
		hvo.setMachinesubmodel(headvo.getAttributeValue("machinesubmodel") == null?"":headvo.getAttributeValue("machinesubmodel").toString());
		hvo.setVolumem3(headvo.getAttributeValue("m3") == null?UFDouble.ZERO_DBL:new UFDouble(headvo.getAttributeValue("m3").toString()));
		hvo.setGrossweight(headvo.getAttributeValue("kg") == null?UFDouble.ZERO_DBL:new UFDouble(headvo.getAttributeValue("kg").toString()));
		hvo.setTotalofpkgs(headvo.getAttributeValue("pkgs") == null?UFDouble.ZERO_DBL:new UFDouble(headvo.getAttributeValue("pkgs").toString()));
		hvo.setChargeableweight(headvo.getAttributeValue("chargeableweight") == null?UFDouble.ZERO_DBL:new UFDouble(headvo.getAttributeValue("chargeableweight").toString()));
		hvo.setNetweight(headvo.getAttributeValue("netweight") == null?UFDouble.ZERO_DBL:new UFDouble(headvo.getAttributeValue("netweight").toString()));
		hvo.setHeightcm(headvo.getAttributeValue("largestheight") == null?UFDouble.ZERO_DBL:new UFDouble(headvo.getAttributeValue("largestheight").toString()));
		hvo.setLengthcm(headvo.getAttributeValue("largestlength") == null?UFDouble.ZERO_DBL:new UFDouble(headvo.getAttributeValue("largestlength").toString()));
		hvo.setWidthcm(headvo.getAttributeValue("largestwidth") == null?UFDouble.ZERO_DBL:new UFDouble(headvo.getAttributeValue("largestwidth").toString()));
		hvo.setLargestweight(headvo.getAttributeValue("largestweight") == null?UFDouble.ZERO_DBL:new UFDouble(headvo.getAttributeValue("largestweight").toString()));
		hvo.setCrate1(headvo.getAttributeValue("lcrate") == null?"":headvo.getAttributeValue("lcrate").toString());
		hvo.setCrate2(headvo.getAttributeValue("hcrate") == null?"":headvo.getAttributeValue("hcrate").toString());
		hvo.setCrate3(headvo.getAttributeValue("wcrate") == null?"":headvo.getAttributeValue("wcrate").toString());
		hvo.setKcrate(headvo.getAttributeValue("kcrate") == null?"":headvo.getAttributeValue("kcrate").toString());
		
	}
	
	
}
