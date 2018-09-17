package nc.vo.so.m32.vochange;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.impl.pub.util.db.InSqlManager;
import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ResultSetProcessor;
import nc.pubitf.so.m30.pub.ISaleOrderForPub;
import nc.vo.bhs.sostore.ItsStoreInOutVO;
import nc.vo.pf.change.ChangeVOAdjustContext;
import nc.vo.pf.change.IChangeVOAdjust;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.m32.rule.VATDefaultRule;
import nc.vo.so.m32.util.RemoteFormSOUtil;
import nc.vo.so.m32.util.SaleInvoiceMarginUtil;
import nc.vo.so.m32.util.SaleInvoiceVOUtil;
import nc.vo.so.m32.util.StoreSettleUtil;
import nc.vo.so.m32.util.VOVatCalculator;
import nc.vo.so.pub.SOConstant;
import nc.vo.so.pub.SOItemKey;
import nc.vo.so.pub.comparator.BillNOandRowNoComparator;
import nc.vo.so.pub.comparator.RowNoComparator;
import nc.vo.so.pub.keyvalue.VOKeyValue;
import nc.vo.trade.voutils.SafeCompute;

/**
 * ���۷�ƱVO��������������
 * 
 * @since 6.0
 * @version 2011-4-14 ����08:49:58
 * @author ô��
 */
public class SaleInvoiceChgVOAdjust implements IChangeVOAdjust {

  @Override
  public AggregatedValueObject adjustAfterChange(AggregatedValueObject srcVO,
      AggregatedValueObject destVO, ChangeVOAdjustContext adjustContext)
      throws BusinessException {

    AggregatedValueObject[] srcVOs = new AggregatedValueObject[] {
      srcVO
    };

    AggregatedValueObject[] destVOs = new AggregatedValueObject[] {
      destVO
    };
    this.batchAdjustAfterChange(srcVOs, destVOs, adjustContext);
    return destVOs[0];
  }

  @Override
  public AggregatedValueObject adjustBeforeChange(AggregatedValueObject srcVO,
      ChangeVOAdjustContext adjustContext) throws BusinessException {
    return null;
  }

  @Override
  public AggregatedValueObject[] batchAdjustAfterChange(
      AggregatedValueObject[] srcVOs, AggregatedValueObject[] destVOs,
      ChangeVOAdjustContext adjustContext) throws BusinessException {
    // �Ƶ���ʱ���������۶��������ۿ��� ����������Ʊ�رգ���������Ҫȥ����Ӧ����
    Map<String, UFBoolean> upmap = this.getinvoiceendflagMap(srcVOs);
    this.fiterDestVOs(upmap, destVOs);
    SaleInvoiceVO[] vos = (SaleInvoiceVO[]) destVOs;
    // У��VO���� �����Զ�����
    this.checkUserDef(vos);
    
    //add chenth 20180912 
    new StoreSettleUtil().processStorage(vos);
    //add end

    // ��ȡ��Դ��������
    Map<String, UFDouble> srcnummaps = this.getSrcNumMap(srcVOs);

    this.processVOs(vos, srcnummaps);

    // ������Դ���ݺź��к����򣬱�֤�����ε��ݵ���˳��
    for (SaleInvoiceVO vo : vos) {
      BillNOandRowNoComparator c = new BillNOandRowNoComparator();
      Arrays.sort(vo.getChildrenVO(), c);
    }

    return vos;
  }

  

private void fiterDestVOs(Map<String, UFBoolean> upmap,
      AggregatedValueObject[] destVOs) {
    for (AggregatedValueObject destVO : destVOs) {
      List<SaleInvoiceBVO> bvolist = new ArrayList<SaleInvoiceBVO>();
      CircularlyAccessibleValueObject[] bvos = destVO.getChildrenVO();
      for (CircularlyAccessibleValueObject bvo : bvos) {
        String srcid = (String) bvo.getAttributeValue(SaleInvoiceBVO.CSRCBID);
        UFBoolean bbinvoiceendflag = upmap.get(srcid);
        if (null == bbinvoiceendflag || !bbinvoiceendflag.booleanValue()) {
          bvolist.add((SaleInvoiceBVO) bvo);
        }
      }
      destVO.setChildrenVO(bvolist.toArray(new SaleInvoiceBVO[bvolist.size()]));
    }
  }

  private Map<String, UFBoolean> getinvoiceendflagMap(
      AggregatedValueObject[] srcVOs) {
    Map<String, UFBoolean> upmap = new HashMap<String, UFBoolean>();
    Set<String> sobids = new HashSet<String>();
    for (AggregatedValueObject srcVO : srcVOs) {
      if (srcVO instanceof SaleOrderVO) {
        CircularlyAccessibleValueObject[] bvos = srcVO.getChildrenVO();
        for (CircularlyAccessibleValueObject bvo : bvos) {
          String bids =
              (String) bvo.getAttributeValue(SaleOrderBVO.CSALEORDERBID);
          if (null != bids) {
            sobids.add(bids);
          }
        }
      }
    }
    if (sobids.size() > 0) {
      ISaleOrderForPub squaresrv =
          NCLocator.getInstance().lookup(ISaleOrderForPub.class);
      try {
        SaleOrderViewVO[] viewvos =
            squaresrv.querySaleOrderViewVOs(
                sobids.toArray(new String[sobids.size()]), new String[] {
                  SaleOrderBVO.CSALEORDERBID, SaleOrderBVO.BBINVOICENDFLAG
                });

        for (SaleOrderViewVO viewvo : viewvos) {
          upmap.put(viewvo.getBody().getCsaleorderbid(), viewvo.getBody()
              .getBbinvoicendflag());
        }

      }
      catch (BusinessException e) {
        ExceptionUtils.wrappException(e);
      }
    }
    return upmap;
  }

  @Override
  public AggregatedValueObject[] batchAdjustBeforeChange(
      AggregatedValueObject[] srcVOs, ChangeVOAdjustContext adjustContext)
      throws BusinessException {
    return null;
  }

  @SuppressWarnings("rawtypes")
  private void checkUserDef(SaleInvoiceVO[] destVOs) {
    String[] ruleCodes = new String[] {
      "so.saleinvoice", "so.saleinvoice_b"
    };
    String[] prefixs = new String[] {
      SOConstant.VDEF, SOConstant.VBDEF
    };
    Class[] voClasses = new Class[] {
      SaleInvoiceHVO.class, SaleInvoiceBVO.class
    };
    nc.impl.pubapp.bd.userdef.UserDefCheckUtils.check(destVOs, ruleCodes,
        prefixs, voClasses);
  }

  private Map<String, UFDouble> getSrcNumMap(AggregatedValueObject[] srcVOs) {
    Map<String, UFDouble> srcnummap = new HashMap<String, UFDouble>();
    for (AggregatedValueObject srcvo : srcVOs) {
      CircularlyAccessibleValueObject[] bvos = srcvo.getChildrenVO();
      for (CircularlyAccessibleValueObject bvo : bvos) {
        try {
          srcnummap.put(bvo.getPrimaryKey(),
              (UFDouble) bvo.getAttributeValue(SOItemKey.NNUM));
        }
        catch (BusinessException e) {
          ExceptionUtils.wrappException(e);
        }
      }
    }
    return srcnummap;
  }

  /**
   * 
   * @param vos
   */
  private void processVOs(SaleInvoiceVO[] vos, Map<String, UFDouble> srcnummaps) {
    SaleInvoiceVOUtil voutil = new SaleInvoiceVOUtil();
    SaleInvoiceMarginUtil marginutil = new SaleInvoiceMarginUtil();
    voutil.checkVO(vos);
    // ��ȫPk_Org_V
    this.setPkOrgVValues(vos);

    // �������ڷ���VO������ȥ�����ظ���Ӱ��Ч�ʣ�
    this.setDefaultValue(vos);
    // β���
    marginutil.processMargin(vos, srcnummaps);
    // VAT�ϲ�����
    for (SaleInvoiceVO invoicevo : vos) {
      if (invoicevo.getAllChildrenVO().length > 1) {
        VOVatCalculator calcultor = new VOVatCalculator(invoicevo);
        calcultor.calVatAll();
      }
    }
    // ������Դ�����к����򣬱�֤�����ε��ݵ���˳��
    for (SaleInvoiceVO vo : vos) {
      RowNoComparator c = new RowNoComparator();
      Arrays.sort(vo.getChildrenVO(), c);
    }
  }

  /**
   * ��ȫPk_Org_V
   * 
   * @param vos
   */
  private void setPkOrgVValues(SaleInvoiceVO[] vos) {
    // pk_org����
    Set<String> orgidset = new HashSet<String>();
    for (SaleInvoiceVO vo : vos) {
      SaleInvoiceHVO head = vo.getParentVO();
      orgidset.add(head.getPk_org());
    }
    Map<String, String> orgmapmap =
        OrgUnitPubService.getNewVIDSByOrgIDS(orgidset
            .toArray(new String[orgidset.size()]));
    // ����pk_org_v
    for (SaleInvoiceVO vo : vos) {
      SaleInvoiceHVO head = vo.getParentVO();
      String orgid = head.getPk_org();
      head.setPk_org_v(orgmapmap.get(orgid));
    }
  }

  /**
   * ��ȫĬ����Ϣ
   * 
   * @param vos
   */
  private void setDefaultValue(SaleInvoiceVO[] vos) {

    Map<String, SaleOrderViewVO> maporderviewvos = this.getOrderViewvos(vos);
    for (SaleInvoiceVO vo : vos) {
      // ��������VAT�ֶ�
      VOKeyValue<SaleInvoiceVO> keyvalue = new VOKeyValue<SaleInvoiceVO>(vo);
      VATDefaultRule vatrule = new VATDefaultRule(keyvalue);
      vatrule.setVatCodeValue();

      if (maporderviewvos.size() == 0) {
        continue;
      }

      // ��ȫ�������кͿ��������˺�
      String orderbid =
          keyvalue.getBodyStringValue(0, SaleInvoiceBVO.CFIRSTBID);
      SaleOrderViewVO orderviewvo = maporderviewvos.get(orderbid);
      if (orderviewvo != null) {
        SaleOrderHVO headvo = orderviewvo.getHead();
        keyvalue.setHeadValue(SaleInvoiceHVO.CCUSTBANKID,
            headvo.getCcustbankid());
        keyvalue.setHeadValue(SaleInvoiceHVO.CCUSTBANKACCID,
            headvo.getCcustbankaccid());
      }

      // �����۶����ϲ�ȫ��������
      SaleInvoiceBVO[] invobvos = vo.getChildrenVO();
      int i = 0;
      for (SaleInvoiceBVO invobvo : invobvos) {
        String firstbid =
            keyvalue.getBodyStringValue(i, SaleInvoiceBVO.CFIRSTBID);
        String channeltypeid =
            keyvalue.getBodyStringValue(i, SaleInvoiceBVO.CCHANNELTYPEID);
        if (maporderviewvos.containsKey(firstbid)
            && PubAppTool.isNull(channeltypeid)) {
          String orderchanneltypeid =
              maporderviewvos.get(firstbid).getHead().getCchanneltypeid();
          keyvalue.setBodyValue(i, SaleInvoiceBVO.CCHANNELTYPEID,
              orderchanneltypeid);
        }
        i++;
      }
    }
  }

  private Map<String, SaleOrderViewVO> getOrderViewvos(SaleInvoiceVO[] vos) {
    List<String> idsset = new ArrayList<String>();
    for (SaleInvoiceVO vo : vos) {
      SaleInvoiceBVO[] invobvos = vo.getChildrenVO();
      for (SaleInvoiceBVO invobvo : invobvos) {
        if (!SOBillType.Order.getCode().equals(invobvo.getVsrctype())) {
          // ������۷�Ʊ��ֱֵ�������۶���ת����������������ֱֵ��ȡ��VOת�������У������ٴβ�ѯ
          idsset.add(invobvo.getCfirstbid());
        }
      }
    }
    if (idsset.size() == 0) {
      return new HashMap<String, SaleOrderViewVO>();
    }
    SaleOrderViewVO[] orderviewvos =
        RemoteFormSOUtil.querySaleOrderViewVOs(
            idsset.toArray(new String[idsset.size()]), new String[] {
              SaleOrderBVO.CSALEORDERBID, SaleOrderHVO.CCHANNELTYPEID,
              SaleOrderHVO.CCUSTBANKID, SaleOrderHVO.CCUSTBANKACCID
            });
    Map<String, SaleOrderViewVO> maporderviewvos =
        new HashMap<String, SaleOrderViewVO>();
    for (SaleOrderViewVO orderviewvo : orderviewvos) {
      maporderviewvos
          .put(orderviewvo.getBody().getCsaleorderbid(), orderviewvo);
    }
    return maporderviewvos;
  }
}
