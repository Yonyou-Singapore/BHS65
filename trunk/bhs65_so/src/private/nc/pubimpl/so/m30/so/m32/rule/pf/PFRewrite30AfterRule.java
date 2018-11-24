package nc.pubimpl.so.m30.so.m32.rule.pf;

import java.util.HashSet;
import java.util.Set;

import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.bs.so.m30.plugin.ServicePlugInPoint;
import nc.impl.pub.util.db.InSqlManager;
import nc.impl.pubapp.bill.rewrite.BillRewriteObject;
import nc.impl.pubapp.bill.rewrite.PfRewriteParam;
import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.pubimpl.so.m30.pub.SORewriteParaForPFUtil;
import nc.pubimpl.so.m30.so.m32.rule.RewriteInvoiceStateRule;
import nc.pubitf.so.m30.so.m32.Rewrite32Para;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;

/**
 * 销售发票保存、删除流程回写销售订单累计开票数量后业务组扩展总后规则
 * 
 * @author zhangby5
 * 
 */
public class PFRewrite30AfterRule implements IRule<BillRewriteObject> {

  @Override
  public void process(BillRewriteObject[] rewriteObjs) {

    if (rewriteObjs == null || rewriteObjs.length == 0) {
      return;
    }

    SaleOrderVO[] currentTargetBills =
        (SaleOrderVO[]) rewriteObjs[0].getCurrentTargetBills();

    // 初始化回写视图VO
    SaleOrderViewVO[] rewriteViewVOs = SORewriteParaForPFUtil.initRewriteViewVOMap(rewriteObjs[0],
            currentTargetBills);

    if (rewriteViewVOs == null || rewriteViewVOs.length == 0) {
      return;
    }
    
    if (BSContext.getInstance().getSession(Rewrite32Para.class.getName()) == null) {
      return;
    }
    
    AroundProcesser<SaleOrderViewVO> processer =
        new AroundProcesser<SaleOrderViewVO>(
            ServicePlugInPoint.rewrite30NumFor32);
    
    processer.after(rewriteViewVOs);

    // 执行后开票状态规则
    new RewriteInvoiceStateRule().process(rewriteViewVOs);
    BSContext.getInstance().removeSession(Rewrite32Para.class.getName());
    
    //add chenth 20181123 BHS 支持销售订单多次开票
    Set<String> orderids = new HashSet<String>();
    for (SaleOrderViewVO vo : rewriteViewVOs) {
    	orderids.add(vo.getHead().getCsaleorderid());
    }
    Set<String> invoiceids = new HashSet<String>();
    if(PfRewriteParam.DELETE_ACTION.equals(rewriteObjs[0].getRewriteParas()[0].getActionType())){
    	SaleInvoiceVO[] invoicevos = (SaleInvoiceVO[]) rewriteObjs[0].getOriginSrcBills();
    	for(SaleInvoiceVO vo : invoicevos){
    		invoiceids.add(vo.getParentVO().getCsaleinvoiceid());
    	}
    }
    try {
  		BaseDAO dao = new BaseDAO();
  		int i = 0;
  		StringBuffer sql = new StringBuffer("update so_saleorder set binvoicendflag='N' where csaleorderid in").append(InSqlManager.getInSQLValue(orderids));
  		sql.append(" and not exists(select 1 from so_saleinvoice h inner join so_saleinvoice_b b on h.csaleinvoiceid = b.csaleinvoiceid where h.dr=0 and b.dr=0 and b.csrcid = so_saleorder.csaleorderid and h.vdef20='Y' ");
  		sql.append("    and h.csaleinvoiceid not in ").append(InSqlManager.getInSQLValue(invoiceids)).append(") ");
		i=dao.executeUpdate(sql.toString());
		sql = new StringBuffer("update so_saleorder_b set bbinvoicendflag='N' where csaleorderid in").append(InSqlManager.getInSQLValue(orderids));
  		sql.append(" and not exists(select 1 from so_saleinvoice h inner join so_saleinvoice_b b on h.csaleinvoiceid = b.csaleinvoiceid  where h.dr=0 and b.dr=0 and b.csrcid = so_saleorder_b.csaleorderid and h.vdef20='Y' ");
  		sql.append("    and h.csaleinvoiceid not in ").append(InSqlManager.getInSQLValue(invoiceids)).append(") ");
  		i=dao.executeUpdate(sql.toString());
		sql = new StringBuffer("update so_saleorder set binvoicendflag='Y' where csaleorderid in").append(InSqlManager.getInSQLValue(orderids));
  		sql.append(" and exists(select 1 from so_saleinvoice h inner join so_saleinvoice_b b on h.csaleinvoiceid = b.csaleinvoiceid  where h.dr=0 and b.dr=0 and b.csrcid = so_saleorder.csaleorderid and h.vdef20='Y' ");
  		sql.append("    and h.csaleinvoiceid not in ").append(InSqlManager.getInSQLValue(invoiceids)).append(") ");
  		i=dao.executeUpdate(sql.toString());
		sql = new StringBuffer("update so_saleorder_b set bbinvoicendflag='Y' where csaleorderid in").append(InSqlManager.getInSQLValue(orderids));
  		sql.append(" and exists(select 1 from so_saleinvoice h inner join so_saleinvoice_b b on h.csaleinvoiceid = b.csaleinvoiceid  where h.dr=0 and b.dr=0 and b.csrcid = so_saleorder_b.csaleorderid and h.vdef20='Y' ");
  		sql.append("    and h.csaleinvoiceid not in ").append(InSqlManager.getInSQLValue(invoiceids)).append(") ");
  		i=dao.executeUpdate(sql.toString());
	} catch (DAOException e) {
		ExceptionUtils.wrappException(e);
	}	
    //add end 
    
  }

}
