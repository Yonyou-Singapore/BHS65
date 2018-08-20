package nc.ui.bhs.bhsdo.ace.action;

import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.desktop.ui.ServerTimeProxy;
import nc.itf.so.m30.self.ISaleOrderMaintain;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnListProcessor;
import nc.ui.pubapp.uif2app.actions.intf.ICopyActionProcessor;
import nc.vo.bhs.bhsdo.AggBhsDoVO;
import nc.vo.bhs.bhsdo.BhsDoVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pub.pf.BillStatusEnum;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.util.VOSortUtils;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.uif2.LoginContext;

/**
 * 复制处理类
 * 
 * @author Thinkpad
 * 
 */
public class CopyActionProcessor implements ICopyActionProcessor<AggBhsDoVO> {

	@Override
	public void processVOAfterCopy(AggBhsDoVO billVO, LoginContext context) {
		// TODO 自动生成的方法存根
		this.processHeadVO(billVO, context);
		this.processBodyVO(billVO);
	}

	private void processBodyVO(AggBhsDoVO vo) {
		vo.getParent().setAttributeValue(
				vo.getMetaData().getParent().getPrimaryAttribute().getName(),
				null);
		vo.getParent().setAttributeValue("ts", null);
		for (IVOMeta meta : vo.getMetaData().getChildren()) {
			if (vo.getChildren(meta) == null)
				continue;
			for (ISuperVO childvo : vo.getChildren(meta)) {
				childvo.setAttributeValue(meta.getPrimaryAttribute().getName(),
						null);
				childvo.setAttributeValue("pk_group", null);
				childvo.setAttributeValue("pk_org", null);
				childvo.setAttributeValue("ts", null);
			}
		}
	}

	private void processHeadVO(AggBhsDoVO vo, LoginContext context) {
		UFDateTime datetime = ServerTimeProxy.getInstance().getServerTime();
		BhsDoVO hvo = vo.getParentVO();
		// 设置空处理
		hvo.setVbillno(null);
		hvo.setApprover(null);
		hvo.setApprovedate(null);
		hvo.setModifier(null);
		hvo.setModifiedtime(null);
		hvo.setCreator(null);
		hvo.setCreationtime(null);
		// hvo.setTs(null);
		// 设置默认值
		hvo.setDbilldate(datetime.getDate());
		hvo.setPk_group(context.getPk_group());
		hvo.setPk_org(context.getPk_org());
		hvo.setAttributeValue("vbillstatus", BillStatusEnum.FREE.value());
		
		//复制do时，取最新订单上的字段信息
		SaleOrderVO ordervo = qryOrderVO(hvo.getPk_saleorder());
		
		updateHeadVOValue(hvo, ordervo.getParentVO());
		
		String sql = "select vbdef2 from so_saleorder_b where csaleorderid = '"+ordervo.getParentVO().getCsaleorderid()+"'  and isnull(vbdef2,'~') != '~' order by convert(int,\"crowno\")  ";
		try {
			List<String> list = (List<String>) NCLocator.getInstance().lookup(IUAPQueryBS.class).executeQuery(sql, new ColumnListProcessor());
			StringBuffer bufferStr = new StringBuffer();
			for (int i = 0; i < list.size(); i++) {
				String vbdef2 = list.get(i);
				if(vbdef2 != null && !vbdef2.equals("")){
					bufferStr.append(vbdef2).append("\n");
				}
			}
			hvo.setDbilldate(ordervo.getParentVO().getDbilldate());
			hvo.setTocustomer(ordervo.getParentVO().getCcustomerid());
			hvo.setToaddress(ordervo.getParentVO().getChreceiveaddid());
			hvo.setDeccription(bufferStr.toString());
		} catch (BusinessException e) {
			// TODO 自动生成的 catch 块
			ExceptionUtils.wrappBusinessException(""+e.getMessage());
		}
		
		
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

}
