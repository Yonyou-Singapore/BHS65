package nc.bs.pub.action;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.bs.framework.common.NCLocator;
import nc.bs.pub.compiler.AbstractCompiler2;
import nc.bs.scmpub.pf.PfParameterUtil;
import nc.impl.pub.util.db.InSqlManager;
import nc.itf.so.m32.ISaleInvoiceScriptMaintain;
import nc.vo.pub.BusinessException;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.pubapp.pflow.PfUserObject;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.uap.pf.PFBusinessException;

/**
 * ���۷�Ʊ���涯����̨���
 * 
 * @since 6.0
 * @version 2011-8-4 ����12:59:44
 * @author ô��
 */
public class N_32_WRITE extends AbstractCompiler2 {
  /**
   * N_32_WRITE �Ĺ�����
   */
  public N_32_WRITE() {
    super();
  }

  /*
   * ��ע��ƽ̨��дԭʼ�ű�
   */
  @Override
  public String getCodeRemark() {
    return "  \n";
  }

  /*
   * ��ע��ƽ̨��д������ �ӿ�ִ����
   */
  @Override
  public Object runComClass(PfParameterVO vo) throws BusinessException {
    super.m_tmpVo = vo;
    try {

      Object[] inCurObjects = this.getPfParameterVO().m_preValueVos;
      SaleInvoiceVO[] clientvos = (SaleInvoiceVO[]) inCurObjects;
      SaleInvoiceVO[] updatevos = this.getUpdateVO(clientvos);
      PfUserObject userObj = (PfUserObject) this.getUserObj();
      SaleInvoiceVO[] originBills = (SaleInvoiceVO[]) vo.getCustomProperty(PfParameterUtil.ORIGIN_VO_PARAMETER);
      ISaleInvoiceScriptMaintain maintainsrv = NCLocator.getInstance().lookup(ISaleInvoiceScriptMaintain.class);
      
      /***
       * add by lvj ��������дpsabills��ʶ��
       */
//      WriteRefflagWhenSaveSaleInvoice bprule = new WriteRefflagWhenSaveSaleInvoice();
//      bprule.process(clientvos);
      this.process(clientvos);
      /************end************/
      
      
      if (updatevos != null && updatevos.length > 0) {
    	  
    	SaleInvoiceVO[] uptVOS = maintainsrv.saleInvoiceUpdate(clientvos, userObj, originBills);
    	//add chenth 20180911 ���۶���֧�ֶ�ο�Ʊ
    	rewriteSOInvoiceendflag(updatevos);
    	//add end 
        
        return uptVOS;
      }
      SaleInvoiceVO[] insertvos = this.getInsertVO(clientvos);
      if (insertvos != null && insertvos.length > 0) {
    	 
    	SaleInvoiceVO[] svdVOS = maintainsrv.saleInvoiceInsert(clientvos, userObj);
    	//add chenth 20180911 ���۶���֧�ֶ�ο�Ʊ �����ڻ�д�����д���
//    	rewriteSOInvoiceendflag(insertvos);
    	//add end 
        return svdVOS;
      }
      
    }
    catch (Exception e) {
      if (e instanceof BusinessException) {
        throw (BusinessException) e;
      }
      throw new PFBusinessException(e.getMessage(), e);
    }
    // finally {
    // // �˴��ͷ�session�����������˷��ڴ�
    // BSContext.getInstance().removeSession(BusinessCheck.class.getName());
    // }
    return null;
  }

private SaleInvoiceVO[] getInsertVO(SaleInvoiceVO[] orderVos) {
    if (orderVos == null || orderVos.length == 0) {
      return null;
    }
    List<SaleInvoiceVO> newVos = new ArrayList<SaleInvoiceVO>();
    for (SaleInvoiceVO vo : orderVos) {
      if (vo != null && vo.getParentVO() != null) {

        if (PubAppTool.isNull(vo.getParentVO().getPrimaryKey())) {
          newVos.add(vo);
        }
      }
    }
    return newVos.toArray(new SaleInvoiceVO[newVos.size()]);
  }

  private SaleInvoiceVO[] getUpdateVO(SaleInvoiceVO[] orderVos) {

    if (orderVos == null || orderVos.length == 0) {
      return null;
    }
    List<SaleInvoiceVO> updateVos = new ArrayList<SaleInvoiceVO>();
    for (SaleInvoiceVO vo : orderVos) {
      if (vo != null && vo.getParentVO() != null) {

        if (!PubAppTool.isNull(vo.getParentVO().getPrimaryKey())) {
          updateVos.add(vo);
        }
      }
    }
    return updateVos.toArray(new SaleInvoiceVO[updateVos.size()]);
  }
  
  
  /***
   * add by lvj
   * add by lvj(���������۷�Ʊʱ�����������vdef1(psabills����)��Ϊ��ʱ���д��Ӧ��psabills�����ݵġ��Ƿ���Ч���۷�Ʊ���ı�ʶ)
   * ͬʱ�жϱ�ͷ��vdef11��Ӧ�Ĵ��ű�����vdef12��ֵ�Ƿ���ͬ���������ͬ��vder12��ֵ��Ϊ���š����Ƹ��µ������š��Զ��������
 * @throws DAOException 
   */
  public void process(SaleInvoiceVO[] vo) throws DAOException {
		// TODO �Զ����ɵķ������
		if(null == vo || vo.length == 0)
			return ;
		BaseDAO dao = new BaseDAO();
//		String vdef11 = vo[0].getParentVO().getVdef11();//���۶�����������
//		String vdef12 = vo[0].getParentVO().getVdef12();//psa����
//		if((null == vdef11 || "".equals(vdef11) || "null".equalsIgnoreCase(vdef11)) && (null == vdef12 || "".equals(vdef12) || "null".equalsIgnoreCase(vdef12)))
//			return ;
//		String qrySql = "select a.pk_defdoc pk_defdoc,a.pk_org,a.pk_group,a.code code,a.name name,a.pk_defdoclist from bd_defdoc a,bd_defdoclist b "
//		  				+ "where b.pk_defdoclist = a.pk_defdoclist and b.code = 'BHS61' and a.code = '"+ vdef12 +"' "
//		  				+ "and isnull(a.dr,0) = 0 and isnull(b.dr,0) = 0 ";
//		String qrySql2 = "select code,name,pk_defdoclist from bd_defdoclist where code = 'BHS61' ";
//		String pk_defdoclist = "";
//		DefdocVO defdocvo = null;
//		try {
//			defdocvo = (DefdocVO)dao.executeQuery(qrySql, new BeanProcessor(DefdocVO.class));//���ݴ���������ѯ�����Զ������vo
//			Object obj = dao.executeQuery(qrySql2, new ColumnProcessor("pk_defdoclist"));//�@ȡ��̖�n�����I
//			pk_defdoclist = null == obj || "".equals(obj) ? "" : obj.toString();
//			if(null == defdocvo){
//				//����Զ������
//				DBTool t = new DBTool();
//				String oid = t.getOIDs(1)[0];
//				DefdocVO voyageVO = new DefdocVO();
//				voyageVO.setPk_defdoc(oid);
//				voyageVO.setPk_org(vo[0].getParentVO().getPk_org());
//				voyageVO.setPk_group(vo[0].getParentVO().getPk_group());
//				voyageVO.setDatatype(new Integer(1));
//				voyageVO.setEnablestate(new Integer(2));
//				voyageVO.setPk_defdoclist(pk_defdoclist);
//				voyageVO.setCode(vdef12);
//				voyageVO.setName(vdef12);
//				dao.insertVO(voyageVO);//���롾���š��Զ������
//			}
//		} catch (DAOException e1) {
//			// TODO �Զ����ɵ� catch ��
//			e1.printStackTrace();
//		}
		
		/***
		 * �������������Ϊ�գ�����Ҫ�Ƚ���Ӧ������psabills�ġ��Ƿ��������۷�Ʊ����ʶ�޸�ΪN��Ϊ�������޸Ĺ�����ɾ�������е������û�н���ʶȡ����
		 */
		String primaryKey = vo[0].getParentVO().getPrimaryKey();
		if(null != primaryKey && !"".equals(primaryKey)){
			String updSql = "update bhsk_psabills set invoicegenerated = 'N' where pk_psabills in "
							+" (select vbdef1 from so_saleinvoice_b where csaleinvoiceid = '"+ primaryKey +"' and isnull(dr,0) = 0 and vbdef1 is not null ) ";
//			try {
				dao.executeUpdate(updSql);
//			} catch (DAOException e) {
//				// TODO �Զ����ɵ� catch ��
//				e.printStackTrace();
//			}
		}		
		
		for(int i=0;i<vo[0].getChildrenVO().length;i++){
			String vbdef1 = vo[0].getChildrenVO()[i].getVbdef1();
			//��дbhsk_psabills���invoicegenerated�ֶ�ΪY
			String SQL = "update bhsk_psabills set invoicegenerated = 'Y' where pk_psabills = '"+ vbdef1 +"' ";
			if(3 == vo[0].getChildrenVO()[i].getStatus())//3����ɾ����
				SQL = "update bhsk_psabills set invoicegenerated = 'N' where pk_psabills = '"+ vbdef1 +"' ";
//			try {
				if(null == vbdef1 || "".equals(vbdef1))
					continue;
				dao.executeUpdate(SQL);
//			} catch (DAOException e) {
//				// TODO �Զ����ɵ� catch ��
//				e.printStackTrace();
//			}
		}
	}
  
  

  private void rewriteSOInvoiceendflag(SaleInvoiceVO[] vos) throws DAOException {
	  if(vos != null && vos.length > 0){
		  Set<String> openStateHids = new HashSet<String>();
		  Set<String> closeStateHids = new HashSet<String>();
		  for(SaleInvoiceVO vo : vos){
			  SaleInvoiceHVO hvo = vo.getParentVO();
			  SaleInvoiceBVO[] bvos = vo.getChildrenVO();
			  //�ж��Ƿ����һ���˵����ǵĻ��ر�
			  if(UFBoolean.TRUE.equals(new UFBoolean(hvo.getVdef20()))){
				  for(SaleInvoiceBVO bvo : bvos){
					  closeStateHids.add(bvo.getCsrcid());
				  }
			  }else{
				  for(SaleInvoiceBVO bvo : bvos){
					  openStateHids.add(bvo.getCsrcid());
				  }
			  }
		  }
		  
		  BaseDAO dao = new BaseDAO();
		  if(openStateHids.size() > 0){
			  //��������
			  String updatesql = " update so_saleorder set binvoicendflag = 'N' where csaleorderid in " + InSqlManager.getInSQLValue(openStateHids);
			  //ֻ����store 
			  updatesql = updatesql + " and exists(select 1 from so_saleorder_b b inner join bd_material m on b.cmaterialid = m.pk_material where so_saleorder.csaleorderid = b.csaleorderid and m.def20='Y') ";
			  dao.executeUpdate(updatesql);
			  
			  //�����ӱ�
			  updatesql = " update so_saleorder_b set bbinvoicendflag = 'N' where csaleorderid in " + InSqlManager.getInSQLValue(openStateHids);
			  //ֻ����store 
			  updatesql = updatesql + " and cmaterialid in (select pk_material from bd_material m where so_saleorder_b.cmaterialid = m.pk_material and m.def20='Y') ";
			  dao.executeUpdate(updatesql);
		  }
		  
		  if(closeStateHids.size() > 0){
			  //��������
			  String updatesql = " update so_saleorder set binvoicendflag = 'Y' where csaleorderid in " + InSqlManager.getInSQLValue(closeStateHids);
			  updatesql = updatesql + " and exists(select 1 from so_saleorder_b b inner join bd_material m on b.cmaterialid = m.pk_material where so_saleorder.csaleorderid = b.csaleorderid and m.def20='Y') ";
			  dao.executeUpdate(updatesql);
			  
			  //�����ӱ�
			  updatesql = " update so_saleorder_b set bbinvoicendflag = 'Y' where csaleorderid in " + InSqlManager.getInSQLValue(closeStateHids);
			  updatesql = updatesql + " and cmaterialid in (select pk_material from bd_material m where so_saleorder_b.cmaterialid = m.pk_material and m.def20='Y') ";
			  dao.executeUpdate(updatesql);
		  }
	  }
  }
}
