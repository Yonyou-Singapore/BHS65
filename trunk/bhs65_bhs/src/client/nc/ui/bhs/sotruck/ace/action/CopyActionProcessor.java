package nc.ui.bhs.sotruck.ace.action;

import nc.bs.framework.common.NCLocator;
import nc.desktop.ui.ServerTimeProxy;
import nc.itf.uap.IUAPQueryBS;
import nc.ui.pubapp.uif2app.actions.intf.ICopyActionProcessor;
import nc.vo.bhs.build.AggSoOrderBuildHVO;
import nc.vo.bhs.build.SoOrderBuildHVO;
import nc.vo.bhs.pack.AggSoOrderPackVO;
import nc.vo.bhs.pack.SoOrderPackVO;
import nc.vo.bhs.sotruck.AggSoTruckHVO;
import nc.vo.bhs.sotruck.SoTruckHVO;
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
public class CopyActionProcessor implements ICopyActionProcessor<AggSoTruckHVO>{

	@Override
	public void processVOAfterCopy(AggSoTruckHVO billVO, LoginContext context) {
		// TODO 自动生成的方法存根
		this.processHeadVO(billVO, context);
	    this.processBodyVO(billVO);
	}

	private void processBodyVO(AggSoTruckHVO vo) {
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

	  private void processHeadVO(AggSoTruckHVO vo, LoginContext context) {
	    UFDateTime datetime = ServerTimeProxy.getInstance().getServerTime();
	    SoTruckHVO hvo = vo.getParentVO();
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
	    String transtypecode = hvo.getTranstypecode();
	    if("TRUC-Cxx-air".equals(transtypecode)){
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
	    }else if("TRUC-Cxx-sea".equals(transtypecode)){
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
