package nc.ui.so.m30.billui.action;

import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.itf.pubapp.pub.smart.IBillQueryService;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnListProcessor;
import nc.md.persist.framework.MDPersistenceService;
import nc.vo.bhs.bhsdo.AggBhsDoVO;
import nc.vo.bhs.build.AggSoOrderBuildHVO;
import nc.vo.bhs.build.SoOrderBuildHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.so.m30.entity.SaleOrderHVO;

/**
 * 打开build单据 
 * 1、如果当前订单已经存在对应的build记录，则打开时显示对应的记录。 
 * 2、如果未存在对应的build记录，打开时默认新增状态。
 * 
 * @author Thinkpad 
 * 20170306
 * 
 */
public class SaleOrderBuildAction extends SaleOrderBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5751011383555490172L;

	public SaleOrderBuildAction() {
		// TODO 自动生成的构造函数存根
		setCode("saleorderbuildaction");
		setBtnName("Build");
	}
	
	@Override
	public AbstractBill[] qryBackwardVO(SaleOrderHVO headvo) throws BusinessException {
		// TODO 自动生成的方法存根
		AggSoOrderBuildHVO[] aggvos = null;
		//查询下游build的主键
		String sql = "select billid from bhs_build_h where csaleorderid = '"+headvo.getCsaleorderid()+"' and isnull(dr,0) = 0 ";
		List<Object> list = (List<Object>) NCLocator.getInstance().lookup(IUAPQueryBS.class).executeQuery(sql, new ColumnListProcessor());
		//如果 不存在则新增
		if(list == null || list.size() == 0){
			AggSoOrderBuildHVO aggvo =  new AggSoOrderBuildHVO();
			SoOrderBuildHVO vo = new SoOrderBuildHVO();
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
			aggvos = new AggSoOrderBuildHVO[1];
			aggvos[0] = aggvo;
		}else{
			
			IBillQueryService billQuery = (IBillQueryService) NCLocator
					.getInstance()
					.lookup(nc.itf.pubapp.pub.smart.IBillQueryService.class);
			AbstractBill[] bills = billQuery.queryAbstractBillsByPks(
					AggSoOrderBuildHVO.class, list.toArray(new String[0]));
			aggvos = new AggSoOrderBuildHVO[bills.length];
			for (int i = 0; i < bills.length; i++) {
				aggvos[i] =  new AggSoOrderBuildHVO();
				aggvos[i] = (AggSoOrderBuildHVO) bills[i];
			}
			
		}
		return aggvos;
	}
	/**
	 * 将销售订单信息带到build中
	 * @param hvo
	 * @param headvo
	 */
	public void updateHeadVOValue(SoOrderBuildHVO hvo,SaleOrderHVO headvo){
		
		hvo.setCustomerpono(headvo.getAttributeValue("customerpono") == null?"":headvo.getAttributeValue("customerpono").toString());
		hvo.setAttnto(headvo.getAttributeValue("attnto") == null?"":headvo.getAttributeValue("attnto").toString());
		hvo.setContactno(headvo.getAttributeValue("contractno") == null?"":headvo.getAttributeValue("contractno").toString());
		hvo.setContactperson(headvo.getAttributeValue("contractperson") == null?"":headvo.getAttributeValue("contractperson").toString());
		hvo.setSubject(headvo.getAttributeValue("subject") == null?"":headvo.getAttributeValue("subject").toString());
		hvo.setJobinstruction(headvo.getAttributeValue("jobinstruction") == null?"":headvo.getAttributeValue("jobinstruction").toString());
		hvo.setRef(headvo.getAttributeValue("ref") == null?"":headvo.getAttributeValue("ref").toString());
		hvo.setJobtype(headvo.getAttributeValue("jobtype") == null?"":headvo.getAttributeValue("jobtype").toString());
		
	}
	
	
	
}
