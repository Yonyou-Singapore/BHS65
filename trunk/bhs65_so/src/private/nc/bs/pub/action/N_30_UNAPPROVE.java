package nc.bs.pub.action;

import nc.bs.pub.compiler.AbstractCompiler2;
import nc.bs.so.saleorder.SaleOrderAction;
import nc.impl.so.m30.action.main.UnApproveSaleOrderAction;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * �������ű�
 * 
 * @author ƽ̨�ű�����
 * @since 6.0
 */
public class N_30_UNAPPROVE extends AbstractCompiler2 {
  /**
   * N_5X_UNAPPROVE ������ע�⡣
   */
  public N_30_UNAPPROVE() {
    super();
  }

  /*
   * ��ע��ƽ̨��дԭʼ�ű�
   */
  @Override
  public String getCodeRemark() {
    return "\n";
  }

  /*
   * ��ע��ƽ̨��д������ �ӿ�ִ����
   */
  @Override
  public Object runComClass(PfParameterVO vo) throws BusinessException {
    try {
      super.m_tmpVo = vo;

      SaleOrderVO[] inCurVOs = this.getVos();
      UnApproveSaleOrderAction action = new UnApproveSaleOrderAction();
      
      
      /*added by ThinkPad start 20170506*/
      Object rts = action.unApprove(inCurVOs, this);
      AggregatedValueObject[] aggvos = (AggregatedValueObject[]) rts;
      for (int i = 0; i < aggvos.length; i++) {
    	  new SaleOrderAction().afterUnApproveAction(aggvos[i]);
      }
      /*added by ThinkPad start 20170506*/
      return rts;
    }
    catch (Exception ex) {

      ExceptionUtils.marsh(ex);
    }
    return null;
  }

  @Override
  public SaleOrderVO[] getVos() {
    return (SaleOrderVO[]) super.getVos();
  }
}
