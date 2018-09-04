package nc.ui.bhs.build.ace.action;

import java.util.Collection;

import nc.bs.framework.common.NCLocator;
import nc.desktop.ui.ServerTimeProxy;
import nc.itf.uap.IUAPQueryBS;
import nc.ui.pubapp.uif2app.actions.intf.ICopyActionProcessor;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.vo.bhs.build.AggSoOrderBuildHVO;
import nc.vo.bhs.build.SoOrderBuildHVO;
import nc.vo.bhs.pack.SoOrderPackBVO;
import nc.vo.bhs.sostore.SoStoreBVO;
import nc.vo.bhs.sostore.SoStoreHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.pf.BillStatusEnum;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.uif2.LoginContext;

/**
 * 复制处理类
 * @author Thinkpad
 *
 */
public class CopyActionProcessor implements ICopyActionProcessor<AggSoOrderBuildHVO>{

	@Override
	public void processVOAfterCopy(AggSoOrderBuildHVO billVO, LoginContext context) {
		// TODO 自动生成的方法存根
		this.processHeadVO(billVO, context);
	    this.processBodyVO(billVO);
	}

	private void processBodyVO(AggSoOrderBuildHVO vo) {
	  	  vo.getParent().setAttributeValue(
	    vo.getMetaData().getParent().getPrimaryAttribute().getName(), null);
	    vo.getParent().setAttributeValue("ts", null);
	    for (IVOMeta meta : vo.getMetaData().getChildren()) {
	      if(vo.getChildren(meta)==null)
	      			     continue;
	      for (ISuperVO childvo : vo.getChildren(meta)) {
	          childvo.setAttributeValue(meta.getPrimaryAttribute().getName(), null);
	          childvo.setAttributeValue("pk_group", null);
	          childvo.setAttributeValue("pk_org", null);
	          childvo.setAttributeValue("ts", null);
	      }
	    }
	  }

	  private void processHeadVO(AggSoOrderBuildHVO vo, LoginContext context) {
	    UFDateTime datetime = ServerTimeProxy.getInstance().getServerTime();
	    SoOrderBuildHVO hvo = vo.getParentVO();
	    // 设置空处理
	    hvo.setVbillno(null);
	    hvo.setApprover(null);
	    hvo.setApprovedate(null);
	    hvo.setModifier(null);
	    hvo.setModifiedtime(null);
	    hvo.setCreator(null);
	    hvo.setCreationtime(null);
	    //hvo.setTs(null);
	    // 设置默认值
	    hvo.setDbilldate(datetime.getDate());
	    hvo.setPk_group(context.getPk_group());
	    hvo.setPk_org(context.getPk_org());
	    hvo.setAttributeValue("vbillstatus", BillStatusEnum.FREE.value());
	    
	    //add chenth 20180831 copy时把最新的SO信息带到joborder上
	    String csaleorderid = hvo.getCsaleorderid();
	    SaleOrderHVO headvo = getSaleOrderHVO(csaleorderid);
	    hvo.setCustomerpono(headvo.getAttributeValue("customerpono") == null?"":headvo.getAttributeValue("customerpono").toString());
		hvo.setAttnto(headvo.getAttributeValue("attnto") == null?"":headvo.getAttributeValue("attnto").toString());
		hvo.setContactno(headvo.getAttributeValue("contractno") == null?"":headvo.getAttributeValue("contractno").toString());
		hvo.setContactperson(headvo.getAttributeValue("contractperson") == null?"":headvo.getAttributeValue("contractperson").toString());
		hvo.setSubject(headvo.getAttributeValue("subject") == null?"":headvo.getAttributeValue("subject").toString());
		hvo.setJobinstruction(headvo.getAttributeValue("jobinstruction") == null?"":headvo.getAttributeValue("jobinstruction").toString());
		hvo.setRef(headvo.getAttributeValue("ref") == null?"":headvo.getAttributeValue("ref").toString());
		hvo.setJobtype(headvo.getAttributeValue("jobtype") == null?"":headvo.getAttributeValue("jobtype").toString());
		//add end
	  }
	  
	  //add chenth 20180831 copy时把最新的SO信息带到joborder上
	  private SaleOrderHVO getSaleOrderHVO(String csaleorderid) {
		SaleOrderHVO vo = null;
		try {
			vo = (SaleOrderHVO) NCLocator.getInstance()
					.lookup(IUAPQueryBS.class).retrieveByPK(SaleOrderHVO.class, csaleorderid);
		} catch (BusinessException e) {
			//TODO
		}
		return vo;
	  }
	  //add end
	
}
