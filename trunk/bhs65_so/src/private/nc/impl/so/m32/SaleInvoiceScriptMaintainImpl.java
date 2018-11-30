package nc.impl.so.m32;

import nc.bs.dao.BaseDAO;
import nc.impl.pubapp.env.BSContext;
import nc.impl.so.m32.action.DeleteSaleInvoiceAction;
import nc.impl.so.m32.action.InsertSaleInvoiceAction;
import nc.impl.so.m32.action.UpdateSaleInvoiceAction;
import nc.itf.so.m32.ISaleInvoiceScriptMaintain;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pflow.PfUserObject;
import nc.vo.scmpub.res.BusinessCheck;
import nc.vo.sm.UserVO;
import nc.vo.so.m30.entity.OffsetTempVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.m32.entity.SaleinvoiceUserObject;
import nc.vo.so.m32.scale.SaleInvoiceScaleProcessor;
import nc.vo.so.m32.util.RemoteFormSOUtil;
import nc.vo.so.m32.util.SaleInvoiceVOUtil;
import nc.vo.so.m35.entity.ArsubInterfaceVO;
import nc.vo.uap.pf.PFBusinessException;

/**
 * ���۷�Ʊ�����ӿ�ʵ����
 * 
 * @since 6.3
 * @version 2012-12-21 ����10:05:22
 * @author yaogj
 */
public class SaleInvoiceScriptMaintainImpl implements
    ISaleInvoiceScriptMaintain {

  @Override
  public SaleInvoiceVO[] saleInvoiceUpdate(SaleInvoiceVO[] vos,
      PfUserObject userObj, SaleInvoiceVO[] originBills)
      throws BusinessException {
    try {
      // ���ȼ��
      checkScale(userObj, vos);

      SaleInvoiceVO[] retvos = null;
      UpdateSaleInvoiceAction action = new UpdateSaleInvoiceAction();
      retvos = action.update(vos, originBills);

      // �����ֹ�ϵ����д���۷��õ�
      if (userObj != null && userObj.getUserObject() != null) {
    	SaleinvoiceUserObject invoiceuserobj =
    			  (SaleinvoiceUserObject) userObj.getUserObject();
    	if(invoiceuserobj.getTempvo() == null){
    		return retvos;
    	}
        SaleInvoiceVOUtil voutil = new SaleInvoiceVOUtil();
        ArsubInterfaceVO[] arsubvo = voutil.changeToArsubInterfaceVO(retvos);
        OffsetTempVO[] tempvo = new OffsetTempVO[] {
          invoiceuserobj.getTempvo()
        };
        RemoteFormSOUtil.writeArsubRelation(arsubvo, tempvo);
      }
      return retvos;
    }
    catch (Exception e) {
      if (e instanceof BusinessException) {
        throw (BusinessException) e;
      }
      throw new PFBusinessException(e.getMessage(), e);
    }
    finally {
      // �˴��ͷ�session�����������˷��ڴ�
      BSContext.getInstance().removeSession(BusinessCheck.class.getName());
    }
  }

  private void checkScale(PfUserObject userObj, SaleInvoiceVO[] vos) {
    // �ⲿ����ƽ̨�ĵ��ݲŽ��о��ȼ�顣
    if (userObj == null||userObj.getUserObject() == null) {
      new SaleInvoiceScaleProcessor().checkBillPrecision(vos);
    }
    else {
      SaleinvoiceUserObject invoiceuserobj =
          (SaleinvoiceUserObject) userObj.getUserObject();
      if (!invoiceuserobj.isIsclientsave()) {
        new SaleInvoiceScaleProcessor().checkBillPrecision(vos);
      }
    }
  }

  @Override
  public void saleInvoiceDelete(SaleInvoiceVO[] vos) throws BusinessException {
    try {
    	//add chenth 20181130  ɾ������
    	String cuserid = BSContext.getInstance().getUserID();
    	UserVO user = (UserVO) new BaseDAO().retrieveByPK(UserVO.class, cuserid);
    	String deleteReason = "Delete By: " + user.getUser_code() + " " + user.getUser_name() + "; Reason: ";
    	SaleInvoiceHVO[] hvos = new SaleInvoiceHVO[vos.length];
    	for(int i=0; i<hvos.length; i++){
    		hvos[i] = vos[i].getParentVO();
    		hvos[i].setVnote( deleteReason + hvos[i].getVnote() );
    	}
    	new BaseDAO().updateVOArray(hvos, new String[]{SaleInvoiceHVO.VNOTE});
    	//add end
    	
      DeleteSaleInvoiceAction action = new DeleteSaleInvoiceAction();
      action.delete(vos);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
  }

  @Override
  public SaleInvoiceVO[] saleInvoiceInsert(SaleInvoiceVO[] vos,
      PfUserObject userObj) throws BusinessException {
    SaleInvoiceVO[] retvos = null;
    try {

      // ���ȼ��
      checkScale(userObj, vos);

      InsertSaleInvoiceAction action = new InsertSaleInvoiceAction();
      retvos = action.insert(vos);
      // �����ֹ�ϵ����д���۷��õ�
      if (userObj != null && userObj.getUserObject() != null) {
        SaleinvoiceUserObject invoiceuserobj =
            (SaleinvoiceUserObject) userObj.getUserObject();
        if(invoiceuserobj.getTempvo() == null){
    		return retvos;
    	}
        SaleInvoiceVOUtil voutil = new SaleInvoiceVOUtil();
        ArsubInterfaceVO[] arsubvo = voutil.changeToArsubInterfaceVO(retvos);
        OffsetTempVO[] tempvo = new OffsetTempVO[] {
          invoiceuserobj.getTempvo()
        };
        RemoteFormSOUtil.writeArsubRelation(arsubvo, tempvo);
      }
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return retvos;
  }

}
