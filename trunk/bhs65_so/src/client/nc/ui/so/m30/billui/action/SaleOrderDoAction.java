package nc.ui.so.m30.billui.action;

import java.awt.event.ActionEvent;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.desktop.ui.WorkbenchEnvironment;
import nc.funcnode.ui.FuncletInitData;
import nc.funcnode.ui.FuncletWindowLauncher;
import nc.itf.pubapp.pub.smart.IBillQueryService;
import nc.itf.so.m30.self.ISaleOrderMaintain;
import nc.itf.uap.IUAPQueryBS;
import nc.itf.uap.bbd.func.IFuncRegisterQueryService;
import nc.jdbc.framework.processor.ColumnListProcessor;
import nc.md.persist.framework.MDPersistenceService;
import nc.ui.pub.linkoperate.ILinkType;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.so.m30.billui.view.SaleOrderBillForm;
import nc.vo.bhs.bhsdo.AggBhsDoVO;
import nc.vo.bhs.bhsdo.BhsDoVO;
import nc.vo.bhs.pack.AggSoOrderPackVO;
import nc.vo.bhs.pack.SoOrderPackVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.util.VOSortUtils;
import nc.vo.sm.funcreg.FuncRegisterVO;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * 打开DO单据 
 * 1、如果当前订单已经存在对应的do记录，则打开时显示对应的记录。 
 * 2、如果未存在对应的do记录，打开时默认新增状态。
 * @author Thinkpad 
 * 20170419
 * 
 */
public class SaleOrderDoAction extends SaleOrderBaseAction {

	private static final long serialVersionUID = -5751011383555490172L;
	
	private SaleOrderBillForm editor;

	private BillManageModel model;
	
	private String nodecode;

	public SaleOrderDoAction() {
		// TODO 自动生成的构造函数存根
		setCode("saleorderdoaction");
		setBtnName("Do");
	}
	@Override
	public void doAction(ActionEvent arg0) throws Exception {
		// TODO 自动生成的方法存根
		SaleOrderVO aggvo = (SaleOrderVO) getModel().getSelectedData();
		SaleOrderVO newaggvo = qryOrderVO(aggvo.getParentVO().getCsaleorderid());
		AbstractBill[] bill = qryBackwardVO(newaggvo);
		openCtDLG(bill);
	}
	/**
	 * 更新so上的最新信息 20171020
	 */
	private SaleOrderVO qryOrderVO(String csaleorderid) {
		ISaleOrderMaintain service = NCLocator.getInstance().lookup(
				ISaleOrderMaintain.class);
		try {
			SaleOrderVO[] ordervos = service
					.querySaleorder(new String[] { csaleorderid });
			return ordervos[0];
		} catch (BusinessException e) {
			// TODO 自动生成的 catch 块
			ExceptionUtils.wrappBusinessException(""+e.getMessage());
		}
		return null;
	}
	
	private void openCtDLG(Object aggvo) {

		FuncletInitData initData = new FuncletInitData();
		initData.setInitType(ILinkType.LINK_TYPE_QUERY);
		initData.setInitData(aggvo);
		initData.setInitType(9);

		IFuncRegisterQueryService service = NCLocator.getInstance().lookup(
				IFuncRegisterQueryService.class);
		FuncRegisterVO funvo = null;
		try {
			funvo = service.queryFunctionByCode(getNodecode());
		} catch (BusinessException e) {
			ExceptionUtils.wrappException(e);
		}
		
//		FuncletWindowLauncher.openFuncNodeDialog(WorkbenchEnvironment
//				.getInstance().getWorkbench(), funvo, initData, null,false,false);
		FuncletWindowLauncher.openFuncNodeInTabbedPane(WorkbenchEnvironment
				.getInstance().getWorkbench(), funvo, initData, null, false);
	}
	
	public AggBhsDoVO[] qryBackwardVO(SaleOrderVO soaggvo) throws BusinessException {
		// TODO 自动生成的方法存根
		SaleOrderHVO headvo = soaggvo.getParentVO();
		
		StringBuffer bufferStr = new StringBuffer();
		String querysql = "select vbdef2 from so_saleorder_b where csaleorderid = '"+headvo.getCsaleorderid()+"'  and isnull(vbdef2,'~') != '~' order by convert(int,\"crowno\")  ";
		try {
			List<String> list = (List<String>) NCLocator.getInstance().lookup(IUAPQueryBS.class).executeQuery(querysql, new ColumnListProcessor());
			for (int i = 0; i < list.size(); i++) {
				String vbdef2 = list.get(i);
				if(vbdef2 != null && !vbdef2.equals("")){
					bufferStr.append(vbdef2).append("\n");
				}
			}
		} catch (BusinessException e) {
			// TODO 自动生成的 catch 块
			ExceptionUtils.wrappBusinessException(""+e.getMessage());
		}
		
		AggBhsDoVO[] aggvos = null;
		//查询下游build的主键
		String sql = "select billid from bhs_bhsdo where pk_saleorder = '"+headvo.getCsaleorderid()+"' and isnull(dr,0) = 0 ";
		List<Object> list = (List<Object>) NCLocator.getInstance().lookup(IUAPQueryBS.class).executeQuery(sql, new ColumnListProcessor());
		//如果 不存在则新增
		if(list == null || list.size() == 0){
			AggBhsDoVO aggvo =  new AggBhsDoVO();
			BhsDoVO vo = new BhsDoVO();
			vo.setPk_group(headvo.getPk_group());
			vo.setPk_org(headvo.getPk_org());
			vo.setPk_org_v(headvo.getPk_org_v());
			vo.setPk_saleorder(headvo.getCsaleorderid());
			vo.setStatus(VOStatus.NEW);
			vo.setSaleorderbillno(headvo.getVbillcode());
			vo.setDbilldate(headvo.getDbilldate());
			vo.setTocustomer(headvo.getCcustomerid());
			vo.setToaddress(headvo.getChreceiveaddid());
			vo.setDeccription(bufferStr.toString());
			updateHeadVOValue(vo, headvo);
			
			vo.setVbillstatus(-1);
			aggvo.setParentVO(vo);
			aggvos = new AggBhsDoVO[1];
			aggvos[0] = aggvo;
		}else{
			IBillQueryService billQuery = (IBillQueryService) NCLocator
					.getInstance()
					.lookup(nc.itf.pubapp.pub.smart.IBillQueryService.class);
			AbstractBill[] bills = billQuery.queryAbstractBillsByPks(
					AggBhsDoVO.class, list.toArray(new String[0]));
			aggvos = new AggBhsDoVO[bills.length];
			for (int i = 0; i < bills.length; i++) {
				aggvos[i] =  new AggBhsDoVO();
				aggvos[i] = (AggBhsDoVO) bills[i];
			}
		}
		return aggvos;
	}
	
	/**
	 * 将销售订单信息带到do中
	 * @param hvo
	 * @param headvo
	 */
	public void updateHeadVOValue(BhsDoVO hvo,SaleOrderHVO headvo){
		
		hvo.setCustomerpono(headvo.getAttributeValue("customerpono") == null?"":headvo.getAttributeValue("customerpono").toString());
		hvo.setFromcorp(headvo.getAttributeValue("fromcorp") == null?"":headvo.getAttributeValue("fromcorp").toString());
		hvo.setFromaddress(headvo.getAttributeValue("fromaddress") == null?"":headvo.getAttributeValue("fromaddress").toString());
		hvo.setFromcode(headvo.getAttributeValue("fromcode") == null?"":headvo.getAttributeValue("fromcode").toString());
		hvo.setTocorp(headvo.getAttributeValue("tocorp") == null?"":headvo.getAttributeValue("tocorp").toString());
		hvo.setToaddress(headvo.getAttributeValue("toaddress") == null?"":headvo.getAttributeValue("toaddress").toString());
		hvo.setTocode(headvo.getAttributeValue("tocode") == null?"":headvo.getAttributeValue("tocode").toString());
		hvo.setAttnto(headvo.getAttributeValue("attnto") == null?"":headvo.getAttributeValue("attnto").toString());
		hvo.setContactno(headvo.getAttributeValue("contractno") == null?"":headvo.getAttributeValue("contractno").toString());
		hvo.setContactperson(headvo.getAttributeValue("contractperson") == null?"":headvo.getAttributeValue("contractperson").toString());
		hvo.setSubject(headvo.getAttributeValue("subject") == null?"":headvo.getAttributeValue("subject").toString());
		hvo.setJobinstruction(headvo.getAttributeValue("jobinstruction") == null?"":headvo.getAttributeValue("jobinstruction").toString());
		hvo.setMoveinstruction(headvo.getAttributeValue("moveInstruction") == null?"":headvo.getAttributeValue("moveInstruction").toString());
		hvo.setRef(headvo.getAttributeValue("ref") == null?"":headvo.getAttributeValue("ref").toString());
		hvo.setDovoyage(headvo.getAttributeValue("vdef12") == null?"":headvo.getAttributeValue("vdef12").toString());
		hvo.setDovessel(headvo.getAttributeValue("vdef13") == null?"":headvo.getAttributeValue("vdef13").toString());
		hvo.setMicapno(headvo.getAttributeValue("micapno") == null?"":headvo.getAttributeValue("micapno").toString());
		hvo.setOemtoolno(headvo.getAttributeValue("oemtoolno") == null?"":headvo.getAttributeValue("oemtoolno").toString());
		hvo.setToolidlid(headvo.getAttributeValue("toolidlid") == null?"":headvo.getAttributeValue("toolidlid").toString());
		hvo.setMachinemake(headvo.getAttributeValue("machinemake") == null?"":headvo.getAttributeValue("machinemake").toString());
		hvo.setMachinemodel(headvo.getAttributeValue("machinemodel") == null?"":headvo.getAttributeValue("machinemodel").toString());
		hvo.setMachinesubmodel(headvo.getAttributeValue("machinesubmodel") == null?"":headvo.getAttributeValue("machinesubmodel").toString());
		hvo.setM2(headvo.getAttributeValue("m2") == null?UFDouble.ZERO_DBL:new UFDouble(headvo.getAttributeValue("m2").toString()));
		hvo.setM3(headvo.getAttributeValue("m3") == null?UFDouble.ZERO_DBL:new UFDouble(headvo.getAttributeValue("m3").toString()));
		hvo.setKg(headvo.getAttributeValue("kg") == null?UFDouble.ZERO_DBL:new UFDouble(headvo.getAttributeValue("kg").toString()));
		hvo.setPkgs(headvo.getAttributeValue("pkgs") == null?UFDouble.ZERO_DBL:new UFDouble(headvo.getAttributeValue("pkgs").toString()));
		hvo.setChargeableweight(headvo.getAttributeValue("chargeableweight") == null?UFDouble.ZERO_DBL:new UFDouble(headvo.getAttributeValue("chargeableweight").toString()));
		hvo.setNetweight(headvo.getAttributeValue("netweight") == null?UFDouble.ZERO_DBL:new UFDouble(headvo.getAttributeValue("netweight").toString()));
		hvo.setLargestheight(headvo.getAttributeValue("largestheight") == null?UFDouble.ZERO_DBL:new UFDouble(headvo.getAttributeValue("largestheight").toString()));
		hvo.setLargestlength(headvo.getAttributeValue("largestlength") == null?UFDouble.ZERO_DBL:new UFDouble(headvo.getAttributeValue("largestlength").toString()));
		hvo.setLargestwidth(headvo.getAttributeValue("largestwidth") == null?UFDouble.ZERO_DBL:new UFDouble(headvo.getAttributeValue("largestwidth").toString()));
		hvo.setLargestweight(headvo.getAttributeValue("largestweight") == null?UFDouble.ZERO_DBL:new UFDouble(headvo.getAttributeValue("largestweight").toString()));
		hvo.setLcrate(headvo.getAttributeValue("lcrate") == null?"":headvo.getAttributeValue("lcrate").toString());
		hvo.setHcrate(headvo.getAttributeValue("hcrate") == null?"":headvo.getAttributeValue("hcrate").toString());
		hvo.setWcrate(headvo.getAttributeValue("wcrate") == null?"":headvo.getAttributeValue("wcrate").toString());
		hvo.setKcrate(headvo.getAttributeValue("kcrate") == null?"":headvo.getAttributeValue("kcrate").toString());
		hvo.setAwb(headvo.getAttributeValue("awb") == null?"":headvo.getAttributeValue("awb").toString());
	}
	
	public SaleOrderBillForm getEditor() {
		return editor;
	}
	public void setEditor(SaleOrderBillForm editor) {
		this.editor = editor;
	}
	public BillManageModel getModel() {
		return model;
	}
	public void setModel(BillManageModel model) {
		this.model = model;
	}
	public String getNodecode() {
		return nodecode;
	}
	public void setNodecode(String nodecode) {
		this.nodecode = nodecode;
	}
	
	
	
}
