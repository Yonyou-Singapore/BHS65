package nc.ui.bhs.bhstools.action;

import java.awt.Container;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.adapter.ic.icreport.base.ICRptBaseScalePrcStrategy;
import nc.bs.ic.icreport.pub.ICReportConditionUtils;
import nc.bs.scmpub.report.ReportQueryCondition;
import nc.itf.iufo.freereport.extend.IQueryCondition;
import nc.ui.pu.m20.report.dlg.PrayBillDetailsQryDlgInit;
import nc.ui.pu.report.pub.action.PURptDefaultQueryAction;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer;
import nc.ui.querytemplate.QueryConditionDLG;
import nc.ui.querytemplate.filter.IFilter;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m20.entity.PraybillHeaderVO;
import nc.vo.pu.report.adjustor.PuReportAdjustor;
import nc.vo.pu.report.queryinfo.PuQueryInfoPara;
import nc.vo.pu.report.queryinfo.praybill.PrayBillQryInfoPara;
import nc.vo.pu.report.scale.m20.PrayBillDetailScaleStrategy;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.query.ConditionVO;
import nc.vo.querytemplate.QueryTemplateConvertor;
import nc.vo.querytemplate.TemplateInfo;

import com.ufida.dataset.IContext;
import com.ufida.report.anareport.FreeReportContextKey;
import com.ufida.report.anareport.base.BaseQueryCondition;
import com.ufida.report.anareport.model.AbsAnaReportModel;
import com.ufida.report.free.plugin.param.ReportVariables;

/**
 * @author Thinkpad
 * 2017-07-23
 */
public class ToolQryAction extends PURptDefaultQueryAction{

	public IQueryCondition doQueryByScheme(Container parent, IContext context,
		      AbsAnaReportModel reportModel, IQueryScheme queryScheme) {
		    if (queryScheme == null) {
		      return new BaseQueryCondition(false);
		    }
		    Object obj = queryScheme.get(IQueryScheme.KEY_FILTERS);
		    PrayBillQryInfoPara para = new PrayBillQryInfoPara();
		    
		    UFDate ddate = new UFDate();
		    
		    if (obj != null) {
		      IFilter[] filters = (IFilter[]) obj;
		      List<IFilter> list = Arrays.asList(filters);
		      ConditionVO[] conds =
		          QueryTemplateConvertor.convertIFilter2ConditionVO(list);
		      
		      for (int i = 0; i < conds.length; i++) {
				if(conds[i].getFieldCode().equals("ddate")){
					ddate = new UFDate(conds[i].getValue().toString());
				}
		      }
		      
		      String where = queryScheme.getWhereSQLOnly();
		      context.setAttribute("#queryconds#", where);
		      context.setAttribute(FreeReportContextKey.KEY_REPORT_QUERYCONDITIONVOS, conds);
		    }
		    ICReportConditionUtils.setDescription(context, queryScheme);
		    ReportQueryCondition condition =
		        (ReportQueryCondition) this.createQueryCondition(context);
		    this.setCondOrgAttribute(context, queryScheme);
		    
		    
		    ReportVariables varPool = ReportVariables.getInstance(reportModel.getFormatModel());
		    varPool.getVariable("var_query_date1").setValue( ddate);
		    varPool.getVariable("var_query_date2").setValue( ddate.getDateAfter(1));
		    varPool.getVariable("var_query_date3").setValue( ddate.getDateAfter(2));
		    varPool.getVariable("var_query_date4").setValue( ddate.getDateAfter(3));
		    varPool.getVariable("var_query_date5").setValue( ddate.getDateAfter(4));
		    varPool.getVariable("var_query_date6").setValue( ddate.getDateAfter(5));
		    varPool.getVariable("var_query_date7").setValue( ddate.getDateAfter(6));
		    varPool.getVariable("var_query_date8").setValue( ddate.getDateAfter(7));
		    varPool.getVariable("var_query_date9").setValue( ddate.getDateAfter(8));
		    varPool.getVariable("var_query_date10").setValue( ddate.getDateAfter(9));
		    varPool.getVariable("var_query_date11").setValue( ddate.getDateAfter(10));
		    varPool.getVariable("var_query_date12").setValue( ddate.getDateAfter(11));
		    varPool.getVariable("var_query_date13").setValue( ddate.getDateAfter(12));
		    varPool.getVariable("var_query_date14").setValue( ddate.getDateAfter(13));
		    
		    
		    return condition;
		  }
	
	@Override
	  public IQueryConditionDLGInitializer getQryCondDLGInitializer() {
	    return new PrayBillDetailsQryDlgInit();
	  }

	  @Override
	  protected Map<String, String> getColumnMapping() {
	    Map<String, String> columnMapping = new HashMap<String, String>();
	    columnMapping.put(PrayBillQryInfoPara.CMATERIALOID,
	        PrayBillQryInfoPara.SRCMATERIAL);
	    columnMapping.put(PrayBillQryInfoPara.CMATERIALOID_NAME,
	        PrayBillQryInfoPara.SRCMATERIAL);
	    return columnMapping;
	  }

	  @Override
	  protected Class<? extends ISuperVO> getMainClass() {
	    return PraybillHeaderVO.class;
	  }

	  @Override
	  protected String getMainOrgKey() {
	    return PrayBillQryInfoPara.mainOrg;
	  }

	  @Override
	  protected ICRptBaseScalePrcStrategy getScaleStrategy() {
	    return new PrayBillDetailScaleStrategy();
	  }

	  @Override
	  protected IQueryCondition setQueryResult(IQueryCondition condition,
	      QueryConditionDLG queryDlg, AbsAnaReportModel reportModel,
	      IContext context) {
	    if (condition == null || !(condition instanceof BaseQueryCondition)) {
	      return condition;
	    }
	    BaseQueryCondition result = (BaseQueryCondition) condition;
	    PrayBillQryInfoPara para = new PrayBillQryInfoPara();
	    ConditionVO[] conds = queryDlg.getLogicalConditionVOs();
	    String where = queryDlg.getWhereSQL();
	    context.setAttribute("#queryconds#", where);

	    for (ConditionVO cond : conds) {
	      String feildCode = cond.getFieldCode();

	      if (feildCode != null
	          && feildCode.equalsIgnoreCase("po_praybill.bsctype")) {
	        String bsc = cond.getValue();
	        para.setBsc(bsc);
	      }

	      if (feildCode != null
	          && feildCode.equals(PrayBillQryInfoPara.QUERY_GROUP_KEY)) {

	        para.setGroupcondtion(cond.getValue());
	        PuReportAdjustor adjustor = new PuReportAdjustor();
	        if (para.getHideKeys() != null) {
	          result.setRoportAdjustor(adjustor);
	        }
	        context.setAttribute(PuQueryInfoPara.QUERY_PARA, para);
	      }
	    }
	    this.setCondOrgAttribute(context);

	    return result;
	  }

	  @Override
	  protected IQueryCondition showQueryDialog(Container parent, IContext context,
	      AbsAnaReportModel reportModel, TemplateInfo tempinfo,
	      IQueryCondition oldCondition) {
	    QueryConditionDLG queryDlg =
	        this.getQueryConditionDlg(parent, context, reportModel, oldCondition);
	    if (queryDlg.showModal() == UIDialog.ID_OK) {
	      IQueryCondition condition = this.createQueryCondition(context);
	      condition =
	          this.setQueryResult(condition, queryDlg, reportModel, context);
	      return condition;
	    }
	    else {
	      return new BaseQueryCondition(false);
	    }
	  }
	  
}
