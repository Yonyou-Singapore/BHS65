package nc.ui.bhs.somove.ace.action;

import nc.bs.framework.common.NCLocator;
import nc.desktop.ui.ServerTimeProxy;
import nc.itf.uap.IUAPQueryBS;
import nc.ui.pubapp.uif2app.actions.intf.ICopyActionProcessor;
import nc.vo.bhs.build.AggSoOrderBuildHVO;
import nc.vo.bhs.build.SoOrderBuildHVO;
import nc.vo.bhs.pack.AggSoOrderPackVO;
import nc.vo.bhs.pack.SoOrderPackVO;
import nc.vo.bhs.somove.AggSoMoveHVO;
import nc.vo.bhs.somove.SoMoveHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pub.pf.BillStatusEnum;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.uif2.LoginContext;

/**
 * 复制处理类
 * @author Thinkpad
 *
 */
public class CopyActionProcessor implements ICopyActionProcessor<AggSoMoveHVO>{

	@Override
	public void processVOAfterCopy(AggSoMoveHVO billVO, LoginContext context) {
		// TODO 自动生成的方法存根
		this.processHeadVO(billVO, context);
	    this.processBodyVO(billVO);
	}

	private void processBodyVO(AggSoMoveHVO vo) {
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

	  private void processHeadVO(AggSoMoveHVO vo, LoginContext context) {
	    UFDateTime datetime = ServerTimeProxy.getInstance().getServerTime();
	    SoMoveHVO hvo = vo.getParentVO();
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
