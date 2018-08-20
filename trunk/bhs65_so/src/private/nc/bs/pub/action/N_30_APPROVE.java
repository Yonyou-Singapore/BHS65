package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pub.compiler.AbstractCompiler2;
import nc.bs.so.saleorder.SaleOrderAction;
import nc.impl.so.m30.action.main.ApproveSaleOrderAction;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.pubitf.credit.accountcheck.IIgnoreAccountCheck;
import nc.pubitf.credit.creditcheck.IAuditFlowFuncFlag;
import nc.pubitf.credit.creditcheck.IIgnoreCreditCheck;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pflow.PfUserObject;
import nc.vo.scmpub.res.BusinessCheck;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * ���������ű�
 * 
 * @author ƽ̨�ű�����
 * @since 6.0
 */
public class N_30_APPROVE extends AbstractCompiler2 {
  public N_30_APPROVE() {
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
      Object creditsrv = null;
      if (SysInitGroupQuery.isCREDITEnabled()) {
        creditsrv = NCLocator.getInstance().lookup(IAuditFlowFuncFlag.class);
        ((IAuditFlowFuncFlag) creditsrv).setAuditFlowFuncFlag();
      }

      ApproveSaleOrderAction action = new ApproveSaleOrderAction();
      Object rts = action.approve(inCurVOs, this);

      if (SysInitGroupQuery.isCREDITEnabled() && null != creditsrv) {
        ((IAuditFlowFuncFlag) creditsrv).removeAuditFlowFuncFlag();
      }
      /*added by ThinkPad start 20170506*/
      AggregatedValueObject[] aggvos = (AggregatedValueObject[]) rts;
      for (int i = 0; i < aggvos.length; i++) {
    	  new SaleOrderAction().afterApproveAction(aggvos[i]);
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
