package nc.ui.so.m30.billui.action;

import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.itf.pubapp.pub.smart.IBillQueryService;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnListProcessor;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.md.persist.framework.MDPersistenceService;
import nc.vo.bhs.bhsquality.AggBhsPoHeadVO;
import nc.vo.bhs.bhsquality.BhsPoHeadVO;
import nc.vo.bhs.build.AggSoOrderBuildHVO;
import nc.vo.bhs.somove.AggSoMoveHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.so.m30.entity.SaleOrderHVO;


/**
 * 打开安全与质量管理单据 
 * 1、如果当前订单已经存在对应的安全与质量管理记录，则打开时显示对应的记录。 
 * 2、如果未存在对应的安全与质量管理记录，打开时默认新增状态。
 * 
 * @author Thinkpad 
 *  2017-07-17
 * 
 */
public class SaleOrderPoAction extends SaleOrderBaseAction {

	private static final long serialVersionUID = -5751011383555490172L;

	public SaleOrderPoAction() {
		// TODO 自动生成的构造函数存根
		setCode("saleorderPoaction");
		setBtnName("Qualitys");
	}
	
	@Override
	public AbstractBill[] qryBackwardVO(SaleOrderHVO headvo) throws BusinessException {
		// TODO 自动生成的方法存根
		AggBhsPoHeadVO[] aggvos = null;
		//查询下游build的主键
		String sql = "select billid from bhs_po_h where pono = '"+headvo.getVbillcode()+"' and isnull(dr,0) = 0 ";
		List<Object> list = (List<Object>) NCLocator.getInstance().lookup(IUAPQueryBS.class).executeQuery(sql, new ColumnListProcessor());
		//如果 不存在则新增
		if(list == null || list.size() == 0){
			AggBhsPoHeadVO aggvo =  new AggBhsPoHeadVO();
			BhsPoHeadVO vo = new BhsPoHeadVO();
			vo.setPk_group(headvo.getPk_group());
			vo.setPk_org(headvo.getPk_org());
			vo.setPk_org_v(headvo.getPk_org_v());
			vo.setPono(headvo.getVbillcode());
			vo.setStatus(VOStatus.NEW);
			vo.setPdate(headvo.getDbilldate());
			vo.setDbilldate(headvo.getDbilldate());
			vo.setPk_customer(headvo.getCcustomerid());
			vo.setVbillstatus(-1);
			aggvo.setParentVO(vo);
			aggvos = new AggBhsPoHeadVO[1];
			aggvos[0] = aggvo;
		}else{
			IBillQueryService billQuery = (IBillQueryService) NCLocator
					.getInstance()
					.lookup(nc.itf.pubapp.pub.smart.IBillQueryService.class);
			AbstractBill[] bills = billQuery.queryAbstractBillsByPks(
					AggBhsPoHeadVO.class, list.toArray(new String[0]));
			aggvos = new AggBhsPoHeadVO[bills.length];
			for (int i = 0; i < bills.length; i++) {
				aggvos[i] =  new AggBhsPoHeadVO();
				aggvos[i] = (AggBhsPoHeadVO) bills[i];
			}
			
		}
		return aggvos;
	}
	
	
	
	
}
