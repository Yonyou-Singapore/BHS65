package nc.vo.so.m32.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.m32.profit.ISaleInvoiceProfitCal;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.entry.ProfitVO;
import nc.vo.so.m30.paravo.Info30For32Para;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.m35.entity.ArsubInterfaceVO;
import nc.vo.so.pub.enumeration.BillStatus;
import nc.vo.so.pub.util.ProfitCaculateUtil;

/**
 * VO��������
 * 
 * @since 6.0
 * @version 2010-12-31 ����09:16:03
 * @author ô��
 */
public class SaleInvoiceVOUtil {

  /**
   * ����ƱVOת��Ϊ���۷��õ��ӿ�VO
   * 
   * @param vos ��Ʊvo
   * @return �ӿ�VO
   */
  public ArsubInterfaceVO[] changeToArsubInterfaceVO(SaleInvoiceVO[] vos) {
    ArsubInterfaceVO[] arsubvo = new ArsubInterfaceVO[vos.length];
    for (int i = 0; i < arsubvo.length; i++) {
      arsubvo[i] = new ArsubInterfaceVO();
      SaleInvoiceHVO hvo = vos[i].getParentVO();

      arsubvo[i].setCsalebillid(hvo.getCsaleinvoiceid());
      arsubvo[i].setVbillcode(hvo.getVbillcode());

      arsubvo[i].setVbilltype(SOBillType.Invoice.getCode());
    }
    return arsubvo;
  }

  /**
   * ë��������Ҫ������ȡ�ɱ���ͳɱ�����
   * 
   * @param vos ���۷�ƱVO����
   * @return ����õ�ë��VO
   * @throws BusinessException
   * @see
   */
  public ProfitVO[] changeToProfitVO(SaleInvoiceVO[] vos) {
    List<ProfitVO> vievowlist = new ArrayList<ProfitVO>();

    for (SaleInvoiceVO vo : vos) {
      SaleInvoiceHVO hvo = vo.getParentVO();
      SaleInvoiceBVO[] bvos = vo.getChildrenVO();
      for (SaleInvoiceBVO bvo : bvos) {

        ProfitVO profitvo = new ProfitVO();
        profitvo.setCstockorgid(bvo.getCsendstockorgid());
        profitvo.setCstordocid(bvo.getCsendstordocid());
        profitvo.setCmaterialid(bvo.getCmaterialid());
        profitvo.setCmaterialvid(bvo.getCmaterialvid());
        profitvo.setNastnum(bvo.getNnum());
        profitvo.setVbatchcode(bvo.getVbatchcode());
        profitvo.setNqtorignetprice(bvo.getNnetprice());
        profitvo.setCastunitid(bvo.getCastunitid());
        profitvo.setCorigcurrencyid(hvo.getCcurrencyid());
        UFDouble totalincome = bvo.getNmny();
        if (bvo.getBlargessflag().booleanValue()) {
          totalincome = UFDouble.ZERO_DBL;
        }
        profitvo.setNtotalincome(totalincome);

        profitvo.setPk_org(bvo.getCsaleorgid());
        profitvo.setCfinanceorg(bvo.getPk_org());

        profitvo.setBlargessflag(bvo.getBlargessflag());
        profitvo.setBdiscountflag(bvo.getBdiscountflag());
        profitvo.setBlaborflag(bvo.getBlaborflag());

        vievowlist.add(profitvo);
      }
    }

    // ��������ȡ�ɱ���ͳɱ����۽ӿڼ���ë��
    ProfitCaculateUtil cacProfigUtil = new ProfitCaculateUtil(vievowlist);
    cacProfigUtil.caculateProfit();

    ProfitVO[] profitvos = vievowlist.toArray(new ProfitVO[vievowlist.size()]);
    return profitvos;

  }

  /**
   * ë��������Ҫ������ȡ�ɱ���ͳɱ�����
   * 
   * @param hids ���۷�ƱͷID����
   * @return ����õ�ë��VO
   * @throws BusinessException
   * @see
   */
  public ProfitVO[] changeToProfitVO(String[] hids) throws BusinessException {
    ISaleInvoiceProfitCal service =
        NCLocator.getInstance().lookup(ISaleInvoiceProfitCal.class);
    return service.caculate32Profit(hids);
  }

  /**
   * У���������ݺϷ���
   * 
   * @param vos
   */
  public void checkVO(SaleInvoiceVO[] vos) {
    for (SaleInvoiceVO vo : vos) {
      SaleInvoiceBVO[] bvos = vo.getChildrenVO();
      StringBuilder errMsg = new StringBuilder();
      for (SaleInvoiceBVO bvo : bvos) {
    	//add chenth 20181115 BHS �����store ��Ҫ��ν�������������
          if (StoreSettleUtil.isStorage(bvo)) {
        	  continue;
          }
          //add end
          
        UFBoolean disflag =
            bvo.getBdiscountflag() == null ? UFBoolean.FALSE : bvo
                .getBdiscountflag();
        UFBoolean freeflag =
            bvo.getBlaborflag() == null ? UFBoolean.FALSE : bvo.getBlaborflag();
        String srctype = bvo.getVsrctype();
        if (ICBillType.SaleOut.getCode().equals(srctype)) {
          srctype =
              NCLangRes4VoTransl.getNCLangRes().getStrByID("4006008_0",
                  "04006008-0110")/* ���۳��ⵥ */;
        }
        else {
          srctype =
              NCLangRes4VoTransl.getNCLangRes().getStrByID("4006008_0",
                  "04006008-0111")/* ���۶��� */;
        }
        String srccode = bvo.getVsrccode();
        String srcrowno = bvo.getVsrcrowno();

        if (disflag.booleanValue() || freeflag.booleanValue()) {
          if (freeflag.booleanValue() && null == bvo.getNnum()) {
            errMsg.append(NCLangRes4VoTransl.getNCLangRes().getStrByID(
                "4006008_0", "04006008-0112", null, new String[] {
                  bvo.getCrowno(), srccode, srctype, srcrowno
                })/* ���۷�Ʊ��[{0}]����������Ϊ�գ��������ε��ݵ��ݺ�Ϊ{1}��{2}��{3}�����ݵ����� */);
            ExceptionUtils.wrappBusinessException(errMsg.toString());
          }
        }
        else {
          if (MathTool.isZero(bvo.getNnum())) {
            errMsg.append(NCLangRes4VoTransl.getNCLangRes().getStrByID(
                "4006008_0", "04006008-0113", null, new String[] {
                  bvo.getCrowno(), srccode, srctype, srcrowno
                })/* ���۷�Ʊ��[{0}]����������Ϊ�ջ�0���������ε��ݵ��ݺ�Ϊ{1}��{2}��{3}�����ݵ����� */);
            ExceptionUtils.wrappBusinessException(errMsg.toString());
          }
        }

      }
    }
  }

  /**
   * ����ɢ����Ϣ���ո���Э��
   * 
   * @param vos
   */
  public void makeupInfo(SaleInvoiceVO[] vos) {
    Set<String> orderbids = new HashSet<String>();
    for (SaleInvoiceVO vo : vos) {
      SaleInvoiceBVO[] bvos = vo.getChildrenVO();
      for (SaleInvoiceBVO bvo : bvos) {
        if (ICBillType.SaleOut.getCode().equals(bvo.getVsrctype())) {
          orderbids.add(bvo.getCfirstbid());
        }
      }
    }
    // ���������Դ�ڳ��ⵥ���ò�����Ϣ
    if (orderbids.size() == 0) {
      return;
    }
    String[] ids = orderbids.toArray(new String[orderbids.size()]);
    Map<String, Info30For32Para> infomap =
        RemoteFormSOUtil.queryInfosByOrderIDs(ids);
    for (SaleInvoiceVO vo : vos) {
      SaleInvoiceHVO hvo = vo.getParentVO();
      SaleInvoiceBVO[] bvos = vo.getChildrenVO();
      for (SaleInvoiceBVO bvo : bvos) {
        if (ICBillType.SaleOut.getCode().equals(bvo.getVsrctype())) {
          String orderbid = bvo.getCfirstbid();
          Info30For32Para info = infomap.get(orderbid);
          if (null != info.getCfreecustid()) {
            bvo.setBfreecustflag(UFBoolean.TRUE);
            bvo.setCfreecustid(info.getCfreecustid());
          }
          bvo.setCchanneltypeid(info.getCchanneltypeid());
          bvo.setCctmanageid(info.getCctmanageid());
          hvo.setCpaytermid(info.getCpaytermid());
          hvo.setCcustbankid(info.getCcustbankid());
          hvo.setCcustbankaccid(info.getCcustbankaccid());
        }
      }
    }
  }

  /**
   * ����ת��֮���Ĭ��ֵ
   * 
   * @param vos
   */
  public void setDefaultValue(SaleInvoiceVO[] vos) {

    for (SaleInvoiceVO vo : vos) {
      UFDate busidate = AppContext.getInstance().getBusiDate();
      SaleInvoiceHVO hvo = vo.getParentVO();
      SaleInvoiceBVO[] bvos = vo.getChildrenVO();
      hvo.setDbilldate(busidate);
      for (SaleInvoiceBVO bvo : bvos) {
        bvo.setDbilldate(busidate);
      }
    }

  }

  /**
   * ����״̬���ϣ�ֵ��
   * 
   * @return
   */
  public Integer[] getBillStatusValue() {
    Integer[] statusValue =
        new Integer[] {
          BillStatus.FREE.getIntegerValue(),
          BillStatus.AUDITING.getIntegerValue(),
          BillStatus.AUDIT.getIntegerValue(),
          BillStatus.NOPASS.getIntegerValue()
        };
    return statusValue;
  }

  /**
   * ����״̬���ϣ����ƣ�
   * 
   * @return
   */
  public String[] getBillStatusName() {
    String[] statusName =
        new String[] {
          BillStatus.FREE.getName(), BillStatus.AUDITING.getName(),
          BillStatus.AUDIT.getName(), BillStatus.NOPASS.getName()
        };
    return statusName;
  }
}
