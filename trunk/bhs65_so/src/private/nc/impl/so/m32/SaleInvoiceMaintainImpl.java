package nc.impl.so.m32;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.InvocationInfoProxy;
import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.impl.pubapp.pattern.data.view.SchemeViewQuery;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.impl.pubapp.pattern.database.TempTable;
import nc.impl.so.m32.action.DeleteSaleInvoiceAction;
import nc.impl.so.m32.action.InsertSaleInvoiceAction;
import nc.impl.so.m32.action.UpdateSaleInvoiceAction;
import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.itf.so.m32.ISaleInvoiceMaintain;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.bd.material.MaterialVO;
import nc.vo.bd.material.marbasclass.MarBasClassVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.JavaType;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;
import nc.vo.pubapp.util.CombineViewToAggUtil;
import nc.vo.scmpub.goldtax.GoldTaxVO;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.m32.entity.SaleInvoiceViewVO;
import nc.vo.so.m32.util.RemoteFormSOUtil;
import nc.vo.so.m32.util.SettleReturnVO;
import nc.vo.so.m32.util.StoreSettleUtil;
import nc.vo.so.pub.SOTable;
import nc.vo.so.pub.query.SOQuerySchemeUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * 
 * @since 6.0
 * @version 2011-6-13 下午05:51:15
 * @author 么贵敬
 */
public class SaleInvoiceMaintainImpl implements ISaleInvoiceMaintain {

  @Override
  public SaleInvoiceVO[] approveSaleInvoice(SaleInvoiceVO[] appvos)
      throws BusinessException {
    return null;
  }

  @Override
  public void deleteSaleInvoice(SaleInvoiceVO[] delvos)
      throws BusinessException {

    try {
      DeleteSaleInvoiceAction action = new DeleteSaleInvoiceAction();
      action.delete(delvos);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }

  }

  @Override
  public GoldTaxVO[] executeVOChangeTogtax(String[] hids)
      throws BusinessException {
    try {
      BillQuery<SaleInvoiceVO> srv =
          new BillQuery<SaleInvoiceVO>(SaleInvoiceVO.class);
      SaleInvoiceVO[] vos = srv.query(hids);

      GoldTaxVO[] goldtaxvos =
          PfServiceScmUtil.executeVOChange(SOBillType.Invoice.getCode(),
              "gtax", vos);
      return goldtaxvos;
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return new GoldTaxVO[0];
  }

  @Override
  public Map<String, String> getCmaterialids(String[] innercode)
      throws BusinessException {
    Map<String, String> rets = new HashMap<String, String>();
    SqlBuilder sql = new SqlBuilder();
    sql.append("select ");
    sql.append("b." + MarBasClassVO.INNERCODE);
    sql.append(",");
    sql.append("b." + MarBasClassVO.PK_MARBASCLASS);

    sql.append(" from ");
    sql.append(MarBasClassVO.getDefaultTableName());
    sql.append(" b");
    sql.append(" where ");
    sql.append(MarBasClassVO.INNERCODE, innercode);
    DataAccessUtils dataacc = new DataAccessUtils();
    try {
      IRowSet rowset = dataacc.query(sql.toString());
      while (rowset.next()) {
        rets.put(rowset.getString(0), rowset.getString(1));
      }
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return rets;
  }

  @Override
  public Map<String, String> getInnercodemaps(String[] cmaterialids)
      throws BusinessException {
    SqlBuilder sql = new SqlBuilder();
    sql.append("select ");

    sql.append("a." + MaterialVO.PK_MATERIAL);
    sql.append(",");
    sql.append("b." + MarBasClassVO.INNERCODE);

    sql.append(" from ");
    sql.append(MaterialVO.getDefaultTableName());
    sql.append(" a");
    sql.append(" inner join " + MarBasClassVO.getDefaultTableName() + " b");
    sql.append(" on a." + MaterialVO.PK_MARBASCLASS + "=b."
        + MarBasClassVO.PK_MARBASCLASS);
    sql.append(" where ");
    IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    sql.append(iq.buildSQL(MaterialVO.PK_MATERIAL, cmaterialids));
    DataAccessUtils dataacc = new DataAccessUtils();
    Map<String, String> ret = new HashMap<String, String>();
    try {
      IRowSet rowset = dataacc.query(sql.toString());
      while (rowset.next()) {
        ret.put(rowset.getString(0), rowset.getString(1));
      }
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return ret;
  }

  @Override
  public SaleInvoiceVO[] insertSaleInvoice(SaleInvoiceVO[] newvos)
      throws BusinessException {

    SaleInvoiceVO[] retvos = null;
    try {
      InsertSaleInvoiceAction action = new InsertSaleInvoiceAction();
      retvos = action.insert(newvos);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return retvos;

  }

  @Override
  public SaleInvoiceVO[] querySaleInvoice(String whereSql)
      throws BusinessException {
    try {
      DataAccessUtils utils = new DataAccessUtils();
      IRowSet rowset = utils.query(whereSql);
      if (rowset.size() == 0) {
        return new SaleInvoiceVO[0];
      }
      String[] ids = rowset.toOneDimensionStringArray();
      BillQuery<SaleInvoiceVO> query =
          new BillQuery<SaleInvoiceVO>(SaleInvoiceVO.class);
      return query.query(ids);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return null;
  }

  @Override
  public SaleInvoiceVO[] querySaleInvoiceFor4C(IQueryScheme queryScheme)
      throws BusinessException {
    this.createSqlByQuerySchemeFor4C(queryScheme);
    // 拼接排序sql
    String ordersql = this.createOrderSql(queryScheme);
    try {
      SaleInvoiceVO[] queryVos =
          this.querySaleInvoiceVOForSource(queryScheme, ordersql);
      return queryVos;
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return null;
  }

  @Override
  public SaleInvoiceViewVO[] queryViewvo(String sql) throws BusinessException {
    DataAccessUtils utils = new DataAccessUtils();
    try {
      String[] bids = utils.query(sql).toOneDimensionStringArray();
      SaleInvoiceViewVO[] views =
          new ViewQuery<SaleInvoiceViewVO>(SaleInvoiceViewVO.class).query(bids);
      return views;
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return null;
  }

  @Override
  public SaleInvoiceVO[] unapproveSaleInvoice(SaleInvoiceVO[] unappvos)
      throws BusinessException {
    return null;
  }

  @Override
  public SaleInvoiceHVO[] updateGoldTaxCode(SaleInvoiceHVO[] updateHeads)
      throws BusinessException {

    Map<String, SaleInvoiceHVO> mapbillcode =
        new HashMap<String, SaleInvoiceHVO>();
    for (SaleInvoiceHVO hvo : updateHeads) {
      mapbillcode.put(hvo.getVbillcode(), hvo);
    }
    String[] names = new String[] {
      SaleInvoiceHVO.CSALEINVOICEID, SaleInvoiceHVO.VBILLCODE
    };
    VOQuery<SaleInvoiceHVO> querysrv =
        new VOQuery<SaleInvoiceHVO>(SaleInvoiceHVO.class, names);

    SqlBuilder sql = new SqlBuilder();
    sql.append(" and ");
    String[] billcodes = mapbillcode.keySet().toArray(new String[0]);
    sql.append(createTempTable(billcodes));
    sql.append(" and ");
    String pk_group = InvocationInfoProxy.getInstance().getGroupId();
    sql.append(SaleInvoiceHVO.PK_GROUP, pk_group);
    try {
      SaleInvoiceHVO[] voHeads = querysrv.query(sql.toString(), null);
      List<SaleInvoiceHVO> newupdateHeads = new ArrayList<SaleInvoiceHVO>();
      for (SaleInvoiceHVO hvo : voHeads) {
        String vbillcode = hvo.getVbillcode();
        if (mapbillcode.containsKey(vbillcode)) {
          String id = hvo.getCsaleinvoiceid();
          if (null == id) {
            mapbillcode.remove(vbillcode);
            continue;
          }
          mapbillcode.get(vbillcode).setCsaleinvoiceid(hvo.getCsaleinvoiceid());
          newupdateHeads.add(mapbillcode.get(vbillcode));
        }
      }
      if (newupdateHeads.size() == 0) {
        return new SaleInvoiceHVO[0];
      }
      names = new String[] {
        SaleInvoiceHVO.VGOLDTAXCODE
      };
      VOUpdate<SaleInvoiceHVO> updatesrv = new VOUpdate<SaleInvoiceHVO>();
      SaleInvoiceHVO[] updatehvos =
          newupdateHeads.toArray(new SaleInvoiceHVO[newupdateHeads.size()]);
      return updatesrv.update(updatehvos, names);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return new SaleInvoiceHVO[0];
  }

  private String createTempTable(String[] codes) {
    if (codes == null) {
      return new String();
    }
    if (codes.length <= 100) {
      SqlBuilder sql = new SqlBuilder();
      sql.append("vbillcode", codes);
      return sql.toString();
    }
    else {
      List<List<Object>> datalist = new ArrayList<List<Object>>();
      for (String code : codes) {
        List<Object> codelist = new ArrayList<Object>();
        codelist.add(code);
        datalist.add(codelist);
      }
      TempTable bo = new TempTable();
      String table = bo.getTempTable("tmp_invcodes", new String[] {
        "code"
      }, new String[] {
        "varchar(40)"
      }, new JavaType[] {
        JavaType.String
      }, datalist);
      return " vbillcode in (select code from " + table + ")";
    }
  }

  @Override
  public SaleInvoiceVO[] updateSaleInvoice(SaleInvoiceVO[] updatevos)
      throws BusinessException {
    SaleInvoiceVO[] retvos = null;
    BillTransferTool<SaleInvoiceVO> transferTool =
        new BillTransferTool<SaleInvoiceVO>(updatevos);
    try {
      SaleInvoiceVO[] fullbills = transferTool.getClientFullInfoBill();
      SaleInvoiceVO[] originBill = transferTool.getOriginBills();
      UpdateSaleInvoiceAction action = new UpdateSaleInvoiceAction();
      retvos = action.update(fullbills, originBill);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return retvos;

  }

  @Override
  public SaleInvoiceHVO[] updateWhenExportGoldTax(SaleInvoiceHVO[] voHeads)
      throws BusinessException {
    String[] names = new String[] {
      SaleInvoiceHVO.BTOGOLDTAXFLAG, SaleInvoiceHVO.TGOLDTAXTIME
    };
    try {
      VOUpdate<SaleInvoiceHVO> updatesrv = new VOUpdate<SaleInvoiceHVO>();
      return updatesrv.update(voHeads, names);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return new SaleInvoiceHVO[0];
  }

  /**
   * 拼接排序sql 默认方法（单据号、行号排序）
   * 
   * @param queryScheme
   * @return
   */
  private String createOrderSql(IQueryScheme queryScheme) {
    // 根据单据号、行号排序
    SqlBuilder order = new SqlBuilder();
    QuerySchemeProcessor processor = new QuerySchemeProcessor(queryScheme);
    order.append(" order by ");
    String tableName =
        processor.getTableAliasOfAttribute(SaleInvoiceHVO.class,
            SaleInvoiceHVO.VBILLCODE);
    order.append(tableName);
    order.append(".");
    order.append(SaleInvoiceHVO.VBILLCODE);
    order.append(",");
    tableName =
        processor.getTableAliasOfAttribute(SaleInvoiceBVO.class,
            SaleInvoiceBVO.CROWNO);
    order.append(tableName);
    order.append(".");
    order.append(SaleInvoiceBVO.CROWNO);
    return order.toString();

  }

  private void createSqlByQuerySchemeFor4C(IQueryScheme queryScheme) {
    QuerySchemeProcessor qsp = new QuerySchemeProcessor(queryScheme);
    // 增加固定where条件
    new SOQuerySchemeUtils().appendFixedWhr(queryScheme, qsp);

    // 增加集团
    qsp.appendCurrentGroup();

    // 得到子表别名
    String subTable =
        qsp.getTableAliasOfAttribute(SaleInvoiceBVO.class,
            SaleInvoiceBVO.BDISCOUNTFLAG);

    IQueryScheme filterorder = null;

    filterorder =
        RemoteFormSOUtil.getOutEndSQL4Filter32(subTable,
            SaleInvoiceBVO.CFIRSTBID);

    if (null != filterorder) {
      qsp.appendFrom(filterorder.getTableJoinFromWhereSQL().getFrom());
      qsp.appendWhere(" and ");
      qsp.appendWhere(filterorder.getTableJoinFromWhereSQL().getWhere());
    }

  }

  private SaleInvoiceVO[] querySaleInvoiceVOForSource(IQueryScheme scheme,
      String ordersql) {
    SchemeViewQuery<SaleInvoiceViewVO> query =
        new SchemeViewQuery<SaleInvoiceViewVO>(SaleInvoiceViewVO.class);
    SaleInvoiceViewVO[] views = query.query(scheme, ordersql);

    if (ArrayUtils.isEmpty(views)) {
      return null;
    }

    SaleInvoiceVO[] queryVos =
        new CombineViewToAggUtil<SaleInvoiceVO>(SaleInvoiceVO.class,
            SaleInvoiceHVO.class, SaleInvoiceBVO.class).combineViewToAgg(views,
            SaleInvoiceHVO.CSALEINVOICEID);

    return queryVos;
  }

  @Override
  public SaleInvoiceVO[] querySaleInvoice(String[] hids)
      throws BusinessException {
    SaleInvoiceVO[] bills = null;
    BillQuery<SaleInvoiceVO> query =
        new BillQuery<SaleInvoiceVO>(SaleInvoiceVO.class);
    bills = query.query(hids);
    return bills;
  }

	@Override
	public SettleReturnVO settle(SaleInvoiceVO vo) throws BusinessException {
		return new StoreSettleUtil().settle(vo);
	}
}
