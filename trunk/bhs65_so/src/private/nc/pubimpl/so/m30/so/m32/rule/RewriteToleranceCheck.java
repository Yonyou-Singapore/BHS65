package nc.pubimpl.so.m30.so.m32.rule;

import java.util.Map;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.env.BSContext;
import nc.pubitf.so.m30.so.m32.Rewrite32Para;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.scmpub.res.BusinessCheck;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;
import nc.vo.so.m32.util.StoreSettleUtil;
import nc.vo.so.pub.exeception.OrderToleranceException;
import nc.vo.so.pub.util.SOSysParaInitUtil;

/**
 * 
 * @description
 * ���۷�Ʊ��д���۶���ִ��ǰ�Ĺ�����(before)��
 * @scene
 * ���ݲ���SO07��������Ƿ�Ʊ�����ڶ���������Χ��
 * @param
 * ��
 *
 * @since 6.0
 * @version 2010-01-28 ����13:49:07
 * @author ��־ΰ
 */
public class RewriteToleranceCheck {
  private Map<String, Rewrite32Para> index;

  private String sSO07;

  public void process(SaleOrderViewVO[] views) throws BusinessException {

    this.initPara(views);

    if ("������".equals(this.sSO07)) {/*-=notranslate=-*/
      return;
    }
    else if ("��ʾ".equals(this.sSO07)) {/*-=notranslate=-*/
      this.hintCheckSendRange(views);
    }
    else if ("�ϸ����".equals(this.sSO07)) {/*-=notranslate=-*/
      this.strictCheckSendRange(views);
    }
  }

  /**
   * ������ϳ����ݲ�
   * 
   * @author ��־ΰ
   * @time 2010-8-17 ����08:07:15
   */
  private String checkSendRange(SaleOrderViewVO[] views) {
    StringBuffer errMsg = new StringBuffer();
    for (SaleOrderViewVO view : views) {
      SaleOrderBVO body = view.getBody();
      
      //add chenth 20181115 BHS �����store ��Ҫ��ν�������������
      if (StoreSettleUtil.isStorage(body)) {
    	  continue;
      }
      //add end
      
      
      // �����ݲΧ
      UFDouble range = UFDouble.ONE_DBL;
      // ��������
      UFDouble rangeNum =
          body.getNnum() == null ? UFDouble.ZERO_DBL : body.getNnum().multiply(
              range);
      // ʵ�ʿ�Ʊ����
      UFDouble tempNum =
          MathTool
              .add(
                  MathTool.add(body.getNtotalrushnum(),
                      body.getNtotalinvoicenum()),
                  this.index.get(body.getCsaleorderbid()).getNchangenum());

      // ��������*�ݲ�ֵ>=�ۼƳ���Գ�����+�ۼƿ�Ʊ����+��ǰ��Ʊ����
      if (MathTool.absCompareTo(rangeNum, tempNum) < 0) {
        errMsg.append(NCLangResOnserver.getInstance().getStrByID("4006011_0",
            "04006011-0355")/*���ɳ�����������Ʊ:*/);
        errMsg.append(":\n");
        errMsg.append(NCLangResOnserver.getInstance().getStrByID("4006011_0",
            "04006011-0340", null, new String[] {
              view.getHead().getVbillcode(), body.getCrowno()
            })/*���۶���{0}��{1}��*/);
        errMsg.append("\n");
      }
    }
    return errMsg.toString();
  }

  private void hintCheckSendRange(SaleOrderViewVO[] views)
      throws OrderToleranceException {
    Object o =
        BSContext.getInstance().getSession(
            BusinessCheck.OrderToleranceCheck.getCheckCode());
    if (null != o && !Boolean.parseBoolean(o.toString())) {
      return;
    }
    // ����ݲ�
    String errMsg = this.checkSendRange(views);
    if (errMsg.length() > 0) {
      throw new OrderToleranceException(NCLangResOnserver.getInstance()
          .getStrByID("4006011_0", "04006011-0350", null, new String[] {
            errMsg.toString()
          })/*{0}�Ƿ����?*/);
    }
    // �˴��ͷ�session�����������˷��ڴ�
    BSContext.getInstance().removeSession(
        BusinessCheck.OrderToleranceCheck.getCheckCode());
  }

  /**
   * ��ʼ����...
   * <ul>
   * <li>Rewrite32Para
   * <li>SO07��������Ʊ���Ʒ�ʽ
   * </ul>
   * 
   * @author ��־ΰ
   * @time 2010-8-17 ����08:07:15
   */
  @SuppressWarnings("unchecked")
  private void initPara(SaleOrderViewVO[] views) {

    // 1.��ʼ��д����
    this.index =
        (Map<String, Rewrite32Para>) BSContext.getInstance().getSession(
            Rewrite32Para.class.getName());

    // 2.������Ʊ���Ʒ�ʽ��������֯SO07
    this.sSO07 = SOSysParaInitUtil.getSO07(views[0].getHead().getPk_org());

  }

  private void strictCheckSendRange(SaleOrderViewVO[] views)
      throws BusinessException {
    // ����ݲ�
    String errMsg = this.checkSendRange(views);
    if (errMsg.length() > 0) {
      throw new BusinessException(errMsg.toString());
    }
  }
}
