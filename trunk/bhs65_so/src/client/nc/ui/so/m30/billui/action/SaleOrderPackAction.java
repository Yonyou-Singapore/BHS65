package nc.ui.so.m30.billui.action;

import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.itf.pubapp.pub.smart.IBillQueryService;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnListProcessor;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.md.model.MetaDataException;
import nc.md.persist.framework.MDPersistenceService;
import nc.vo.bhs.bhsquality.AggBhsPoHeadVO;
import nc.vo.bhs.build.AggSoOrderBuildHVO;
import nc.vo.bhs.build.SoOrderBuildHVO;
import nc.vo.bhs.pack.AggSoOrderPackVO;
import nc.vo.bhs.pack.SoOrderPackVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.so.m30.entity.SaleOrderHVO;

/**
 * 打开pack单据 
 * 1、如果当前订单已经存在对应的pack记录，则打开时显示对应的记录。 
 * 2、如果未存在对应的pack记录，打开时默认新增状态。
 * 
 * @author Thinkpad 
 * 20170306
 * 
 */
public class SaleOrderPackAction extends SaleOrderBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5751011383555490172L;

	public SaleOrderPackAction() {
		// TODO 自动生成的构造函数存根
		setCode("saleorderpackaction");
		setBtnName("Pack");
	}
	
	@Override
	public AbstractBill[] qryBackwardVO(SaleOrderHVO headvo) throws BusinessException {
		// TODO 自动生成的方法存根
		AggSoOrderPackVO[] aggvos = null;
		//查询下游build的主键
		String sql = "select billid from bhs_pack_h where csaleorderid = '"+headvo.getCsaleorderid()+"' and isnull(dr,0) = 0 ";
		List<Object> list = (List<Object>) NCLocator.getInstance().lookup(IUAPQueryBS.class).executeQuery(sql, new ColumnListProcessor());
		//如果 不存在则新增
		if(list == null || list.size() == 0){
			AggSoOrderPackVO aggvo =  new AggSoOrderPackVO();
			SoOrderPackVO vo = new SoOrderPackVO();
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
			aggvos = new AggSoOrderPackVO[1];
			aggvos[0] = aggvo;
		}else{
			IBillQueryService billQuery = (IBillQueryService) NCLocator
					.getInstance()
					.lookup(nc.itf.pubapp.pub.smart.IBillQueryService.class);
			AbstractBill[] bills = billQuery.queryAbstractBillsByPks(
					AggSoOrderPackVO.class, list.toArray(new String[0]));
			aggvos = new AggSoOrderPackVO[bills.length];
			for (int i = 0; i < bills.length; i++) {
				aggvos[i] =  new AggSoOrderPackVO();
				aggvos[i] = (AggSoOrderPackVO) bills[i];
			}
			
		}
		return aggvos;
	}
	
	/**
	 * 将销售订单信息带到Pack中
	 * @param hvo
	 * @param headvo
	 */
	public void updateHeadVOValue(SoOrderPackVO hvo,SaleOrderHVO headvo){
		
		hvo.setSurveyno(headvo.getAttributeValue("surveyno") == null?"":headvo.getAttributeValue("surveyno").toString());
		hvo.setSurveyby(headvo.getAttributeValue("surveyby") == null?"":headvo.getAttributeValue("surveyby").toString());
		hvo.setSurveydate(headvo.getAttributeValue("surveydate") == null?null:new UFDate(headvo.getAttributeValue("surveydate").toString()));
		hvo.setCustomerpono(headvo.getAttributeValue("customerpono") == null?"":headvo.getAttributeValue("customerpono").toString());
		hvo.setAttnto(headvo.getAttributeValue("attnto") == null?"":headvo.getAttributeValue("attnto").toString());
		hvo.setContactno(headvo.getAttributeValue("contractno") == null?"":headvo.getAttributeValue("contractno").toString());
		hvo.setContactperson(headvo.getAttributeValue("contractperson") == null?"":headvo.getAttributeValue("contractperson").toString());
		hvo.setSubject(headvo.getAttributeValue("subject") == null?"":headvo.getAttributeValue("subject").toString());
		hvo.setJobinstruction(headvo.getAttributeValue("jobinstruction") == null?"":headvo.getAttributeValue("jobinstruction").toString());
		hvo.setRef(headvo.getAttributeValue("ref") == null?"":headvo.getAttributeValue("ref").toString());
		hvo.setJobtype(headvo.getAttributeValue("jobtype") == null?"":headvo.getAttributeValue("jobtype").toString());
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
