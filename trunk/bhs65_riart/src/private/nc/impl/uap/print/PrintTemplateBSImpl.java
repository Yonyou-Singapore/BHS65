package nc.impl.uap.print;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import nc.bs.dao.BaseDAO;
import nc.bs.framework.common.NCLocator;
import nc.bs.framework.exception.ComponentException;
import nc.bs.logging.Logger;
import nc.bs.pub.print.util.PrintVOMapModelFactory;
import nc.itf.uap.pf.IPFTemplate;
import nc.itf.uap.print.IPrintTemplateBS;
import nc.itf.uap.print.IPrintTemplateQry;
import nc.itf.uap.pub.util.ITemplateDefQry;
import nc.jdbc.framework.exception.DbException;
import nc.jdbc.framework.processor.BeanMappingListProcessor;
import nc.vo.pub.BusinessException;
import nc.vo.pub.print.CreateItemVO;
import nc.vo.pub.print.CreatePrintParaVO;
import nc.vo.pub.print.PrintTemplateVO;
import nc.vo.pub.print.PrintTempletmanageHeaderVO;
import nc.vo.pub.print.PrintTempletmanageItemVO;
import nc.vo.pub.print.PrintVO;
import nc.vo.pub.print.tools.PrintTemplateCreatorVO;
import nc.vo.pub.template.ITemplateStyle;
import nc.vo.uap.pf.TemplateParaVO;

/**
 * ��ӡģ��ģ���ѯ���洢����ӿڵ�ȱʡʵ��

 * @version NC5.0
 * @author lhwei
 * @since 5.0
 */
public class PrintTemplateBSImpl implements IPrintTemplateQry,IPrintTemplateBS {
		
    /**
     * ���ݴ�ӡģ��ID��ȡ�ô�ӡģ��������Ϣ.
     * ��Щ��Ϣ����������Ϣ���Զ��������Ϣ����Ԫ����Ϣ����Ԫ����չ��Ϣ����������Ϣ.
     *
     * @param templateid Ҫ��ѯ��ģ���ID(����).
     * @return װ�ش�ӡģ��������Ϣ��VO����.
     * @exception BusinessException �����ȡ��ӡģ����Ϣʱ��������
     */
    public PrintVO getPrintVO(String templateid) throws BusinessException {
        PrintDAO dao = null;
    	try {
    		dao = new PrintDAO();
    		PrintVO printVO = dao.getPrintVO(templateid);
    		return printVO;
    	}	
    	finally {
    	    dao.release();
    	}
    }
    
    /**
     * ���ݴ�ӡģ��ID��ȡ�ô�ӡģ��������Ϣ,�ô�ӡģ�����Ϊ��Ƭ����.
     * ��Щ��Ϣ����������Ϣ���Զ��������Ϣ����Ԫ����Ϣ����Ԫ����չ��Ϣ����������Ϣ.
     *
     * @param templateid Ҫ��ѯ��ģ���ID(����).
     * @return װ�ش�ӡģ��������Ϣ��VO����.
     * @exception BusinessException �����ȡ��ӡģ����Ϣʱ��������.
     */
    public PrintVO getCardPrintVO(String templateid) throws BusinessException {
        PrintDAO dao = null;
    	try {
    		dao = new PrintDAO();
    		PrintVO printVO = dao.getCardPrintVO(templateid);
    		return printVO;
    	}
    	finally {
    	    dao.release();
    	}
    }    
    
    /**
     * ���ݽڵ���,��ȡ���µ����д�ӡģ�������Ϣ.
     * 
     * @param moduleName Ҫ��ѯ�Ľڵ���.
     * @return PrintTemplateVO[] ��ѯ���Ĵ�ӡģ�������ϢVO����.
     * @exception BusinessException �����ȡ��ӡģ�������Ϣʱ��������.
     */
    public PrintTemplateVO[] getTempletByModule(String moduleName) throws BusinessException {
        PrintDAO dao = null;
        try {
    		dao = new PrintDAO();
    		PrintTemplateVO[] VOs = dao.getTempletByModule(moduleName);
    		return VOs;
    	}
    	finally {
    	    dao.release();
    	}
    }    
    
    /**
     * ���ݽڵ��ź͹�˾,��ȡ���µ����д�ӡģ�������Ϣ.
     * 
     * @param moduleName Ҫ��ѯ�Ľڵ���.
     * @param pkCorp ģ�������Ĺ�˾����.
     * @return PrintTemplateVO[] ��ѯ���Ĵ�ӡģ�������ϢVO����.
     * @exception BusinessException �����ȡ��ӡģ�������Ϣʱ��������.
     */
    public PrintTemplateVO[] getTempletByModule(String moduleName, String pkCorp) throws BusinessException {
    	// bq v55ģ����һ���
    	// �ҵ�ǰ�ڵ��ģ��
    	// ���ҵ����ɻ���
    	// ��δ�ҵ�����ģ�����������ѷ����ϵͳģ�壬 ����ϵͳģ���nodecode�����е�ģ��
    	
    	PrintTemplateVO[] VOs = null;
    	
    	PrintDAO dao =null;
        try {
    		dao = new PrintDAO();
    		VOs = dao.getTempletByModule(moduleName, pkCorp);
    	}
    	finally {
    	    dao.release();
    	}
    	
    	if(VOs != null && VOs.length > 0)
    		return VOs;
    	
    	TemplateParaVO paraVo = new TemplateParaVO();
        paraVo.setTemplateType(ITemplateStyle.printTemplate);       
        paraVo.setFunNode(moduleName);
        
        IPFTemplate name = (IPFTemplate) NCLocator.getInstance().lookup(IPFTemplate.class.getName());
        String[] ids = name.getSingleTempIdAry(paraVo);
    	
        if(ids != null && ids.length > 0) {
        	// ��������һ�������ģ�壬�ҵ�ģ���Ӧ�Ľڵ��
            IPrintTemplateQry templateQry = (IPrintTemplateQry) NCLocator.getInstance().lookup(IPrintTemplateQry.class.getName());
            String[] sysids = new String[1];
            sysids[0] = ids[0];
    		PrintTempletmanageHeaderVO[] templateHeads = templateQry.queryHeaderVOByIDs(sysids);
    		
    		if(templateHeads != null && templateHeads.length > 0) {
    			moduleName = templateHeads[0].getVnodecode();
    		}
    		
    		try {
        		dao = new PrintDAO();
        		VOs = dao.getTempletByModule(moduleName, pkCorp);
        	}
        	finally {
        	    dao.release();
        	}
        }
        
        return VOs;
    }        
    
    /**
     * ���ݽڵ��ź͹�˾,��ȡ���µ����д�ӡģ�������Ϣ,�ô�ӡģ�����Ϊ��Ƭ����.
     * 
     * @param moduleName Ҫ��ѯ�Ľڵ���.
     * @param pkCorp ģ�������Ĺ�˾����.
     * @return PrintTemplateVO[] ��ѯ���Ĵ�ӡģ�������ϢVO����.
     * @exception BusinessException �����ȡ��ӡģ�������Ϣʱ��������.
     */
    public PrintTemplateVO[] getCardTempletByModule(String moduleName, String pkCorp) throws BusinessException {
        PrintDAO dao = null;
        try {
    		dao = new PrintDAO();
    		PrintTemplateVO[] VOs = dao.getCardTempletByModule(moduleName, pkCorp);
    		return VOs;
    	}
    	finally {
    	    dao.release();
    	}
    }    
    
    /**
     * ����ģ��ID,ɾ�����Ӧ������ģ����Ϣ.
     * 
     * @param key Ҫɾ����ģ���ID(����)
     * @return <tt>true</tt> ɾ���ɹ�;
     *          <tt>false</tt> �ô�ӡģ���ѱ����䣬������ȡ���������ɾ��.
     * @exception BusinessException ɾ����ӡģ����Ϣʱ��������
     */
    public boolean delete(String key) throws BusinessException {
        PrintDAO dao =null;
        try {
        	IPFTemplate pfTemplate = 
        		(IPFTemplate) NCLocator.getInstance().lookup(IPFTemplate.class.getName());
            boolean bUsed = pfTemplate.isTemplateUsed(key, 3);
 
    		if (bUsed)
    			return false;

    		//ִ��ɾ��
    		dao = new PrintDAO();
    		dao.delete(key);
    	}
    	catch (ComponentException e) {
    		reportException(e);
    		throw new BusinessException("Service IPFTemplate Not FOUND");
    	}
    	finally {
    		if( dao != null )
    			dao.release();
    	}
    	return true;
    }
    
    /**
     * ���ݴ�ӡģ��ID��ȡ��ʱ���. 
     * 
     * @param id Ҫ��ѯ��ģ��ID(����).
     * @return ��ʱ�������.
     * @exception BusinessException ��ȡʱ���ʱ��������.
     */
    public String[] findDefaultIDwithTS(String id) throws BusinessException {
        String[] ids = null;
        PrintDAO dao = null;
        try {
            dao = new PrintDAO();
            ids = dao.findDefaultIDwithTS(id);
        }
    	finally {
    	    dao.release();
    	}
        return ids;
    }

    /**
     * ����ӡģ����Ϣ���������ݿ���.
     *
     * @param printVo ������ӡģ��������Ϣ�Ķ���.
     * @return java.lang.String  Ϊ�������������ɵ�Ψһ��ʶ.
     * @exception BusinessException ����ӡģ����Ϣ���������ݿ�ʱ��������.
     */
    public String insert(PrintVO printVo) throws BusinessException {
        PrintDAO dao =null;
    	try {
    		dao = new PrintDAO();
    		String key = dao.insert(printVo);
    		return key;
    	}    	
    	finally {
    	    dao.release();
    	}
    }

    /**
     * ����ӡģ����Ϣ���������ݿ���,�����ô�ӡģ����Ϊ��Ƭ����ģ��.
     *
     * @param printVo ������ӡģ��������Ϣ�Ķ���.
     * @return java.lang.String  Ϊ�������������ɵ�Ψһ��ʶ.
     * @exception BusinessException ����ӡģ����Ϣ���������ݿ�ʱ��������.
     */
    public String insertCard(PrintVO printVo) throws BusinessException {
        PrintDAO dao = null;
    	try {
    		dao = new PrintDAO();
    		String key = dao.insertCard(printVo);
    		return key;
    	}
    	finally {
    	    dao.release();
    	}
    }

    /**
     * ���´�ӡģ���������Ϣ.
     *
     * @param print ��ӡģ�������Ϣ.
     * @exception BusinessException ���´�ӡģ����Ϣ�·�������.
     */
    public void update(PrintVO print) throws BusinessException {
        PrintDAO dao = null;
    	try {
    		dao = new PrintDAO();
    		dao.update(print);
    	}
    	finally {
    	    dao.release();
    	}
    }

    /**
     * ���´�ӡģ���������Ϣ,�����ô�ӡģ����Ϊ��Ƭ����ģ��.
     *
     * @param print ��ӡģ�������Ϣ.
     * @exception BusinessException ���´�ӡģ����Ϣ�·�������.
     */
    public void updateCard(PrintVO print) throws BusinessException {
        PrintDAO dao = null;
    	try {
    		dao = new PrintDAO();
    		dao.updateCard(print);
    	}
    	finally {
    	    dao.release();
    	}
    }

    /**
     * ���´�ӡģ��ı��������.
     *
     * @param templateID Ҫ���µĴ�ӡģ���ID.
     * @param templateCode ���º��ģ�����.
     * @param templateName ���º��ģ������.
     * @exception BusinessException ���´�ӡģ����������ʱ��������.
     */
    public void updateTemplateName(
    	String templateID,
    	String templateCode,
    	String templateName)
    	throws BusinessException {

        PrintDAO dao = null;
    	try {
    		dao = new PrintDAO();
    		dao.updateTemplateName(templateID, templateCode, templateName);
    	}
    	finally {
    	    dao.release();
    	}
    }    
    
    /**
     * ���´�ӡģ��ı��������.
     * @param printTemplate Ҫ���µ�ģ��
     * @throws BusinessException
     */
	public void updateTemplateName(PrintTemplateVO printTemplate)
			throws BusinessException {
		PrintDAO dao = null;
		try {
			dao = new PrintDAO();
			dao.updateTemplateName(printTemplate);
		} finally {
			dao.release();
		}
	}
    
    /**
     * ���쳣ͳһ��¼
     * @param e
     */
    private void reportException(Exception e) {
    	Logger.error(e.getMessage(), e);
    }

    /**
     * ͨ��������ȡģ�������Ϣ.
     *
     * @param key Ҫ��ѯ��ģ������.
     * @return ��ȡ����ģ�������Ϣ.
     * @exception BusinessException ��ȡģ�������Ϣʱ��������.
     */
    public PrintTemplateVO findByPrimaryKey(String key) throws BusinessException {

    	PrintTemplateVO printTemplate = null;
    	PrintTemplateDAO dao = null;
    	try {
    		dao = new PrintTemplateDAO();
    		printTemplate = dao.findByPrimaryKey(key);
    	}
    	finally {
    	    dao.release();
    	}
    	return printTemplate;
    }

    /**
     * �����ݿ����������ģ�������Ϣ.
     *
     * @param printTemplates Ҫ�����Ķ��ģ�������Ϣ����.
     * @return java.lang.String[] Ϊ��������Щģ�����õ�Ψһ��ʶ.
     * @exception BusinessException �����ݿ����������ģ�������Ϣʱ��������.
     */
    public String[] insertArray(PrintTemplateVO[] printTemplates) throws BusinessException {

        PrintTemplateDAO dao = null;
    	try {
    		dao = new PrintTemplateDAO();
    		String[] keys = dao.insertArray(printTemplates);
    		return keys;
    	}
    	finally {
    	    dao.release();
    	}
    }
    
    /**
     * ͨ����˾������������д�ӡģ�������Ϣ��������.
     *
     * @param unitCode ģ�������Ĺ�˾����.
     * @return ��õ����д�ӡģ�������Ϣ��������.
     * @exception BusinessException ��ȡģ�������Ϣ�·�������.
     */
    public PrintTemplateVO[] queryAll(String unitCode) throws BusinessException {

    	PrintTemplateVO[] printTemplates = null;
    	PrintTemplateDAO dao = null;
    	try {
    		dao = new PrintTemplateDAO();
    		printTemplates = dao.queryAll(unitCode);
    	}
    	finally {
    	    dao.release();
    	}
    	return printTemplates;
    }

    /**
     * ���ݶ��ģ��ID,�������ǵ�ģ�������Ϣ.
     * 
     * @param Ҫ��ȡ�Ķ��ģ��ID.
     * @return ��ȡ���Ķ��ģ�����.
     * @throws BusinessException ��ȡģ�������Ϣ�·�������.
     */
    public PrintTempletmanageHeaderVO[] queryHeaderVOByIDs(String[] ids) throws BusinessException {
    	PrintTempletmanageHeaderVO[] printTemplates = null;
    	PrintTemplateDAO dao = null;
    	try {
    		dao = new PrintTemplateDAO();
    		printTemplates = dao.queryHeaderVOByIDs(ids);
    		
    		// ��Ҫ����ids��˳���ptvo��������
    		if (printTemplates != null) {
    			//update chenth 20180503 ��ӡʱѡ���ģ�尴��������
//				List<PrintTempletmanageHeaderVO> vos = new ArrayList<PrintTempletmanageHeaderVO>();
//				for (String id : ids) {
//					for (PrintTempletmanageHeaderVO vo : printTemplates) {
//						if (vo.getCtemplateid().equals(id)) {
//							vos.add(vo);
//						}
//					}
//				}
//				printTemplates = vos.toArray(new PrintTempletmanageHeaderVO[0]);
    			
    			Arrays.sort(printTemplates, new Comparator<PrintTempletmanageHeaderVO>(){
    				
    				@Override
    				public int compare(PrintTempletmanageHeaderVO o1,
    						PrintTempletmanageHeaderVO o2) {
    					return o1.getVtemplatename().compareTo(o2.getVtemplatename());
    				}
    				
    			});
    			//end
			}
    	}
    	finally {
    	    dao.release();
    	}
    	
    	return printTemplates;
    }

    /**
     * ͨ���ڵ��ź͹�˾��ȡ�ڵ㼶��(*��*��ģ�弶)����Դ������Ϣ.
     *
     * @param nodeCode �ڵ���.
     * @param pkCorp ��˾����.
     * @return ��ȡ��������Դ������Ϣ.
     * @exception BusinessException �쳣˵��
     */
    public PrintTempletmanageItemVO[] findItemsForHeader(String nodeCode, String pkCorp) throws BusinessException {
    	PrintTempletmanageDAO dao = null;
    	try{
	    	ITemplateDefQry defQry = 
	    		(ITemplateDefQry) NCLocator.getInstance().lookup(ITemplateDefQry.class.getName());
	    	dao = new PrintTempletmanageDAO();
	    	return dao.findItemsForHeader(defQry,nodeCode,pkCorp);	    	
    	}
    	catch(ComponentException e){
    		reportException(e);
    		throw new BusinessException("Service ITemplateDefQry Not FOUND!");
    	}
    	finally{
    		if( dao != null )
    			dao.release();
    	}
    }
    

    /**
     * ͨ���������Ϣ�ͽڵ���Ϣ��������Դ������Ϣ.
     * 
     * @param printItems ��������Ϣ.
     * @param paras ����Դ������Ϣ.
     * @exception BusinessException ��������Դ������Ϣ��������.
     * 
     */
    public void createPrintTempData(CreateItemVO[] printItems, CreatePrintParaVO paras)
    	throws BusinessException {
        PrintToolsDAO dao = null;
    	try {
    		dao = new PrintToolsDAO();
    		//����ģ������
    		String key = dao.createPrintTempData(printItems, paras);
    		Logger.debug(
    			"----------    Ϊ�ڵ�:<"
    				+ paras.getFunCode()
    				+ ">  ����Ĭ�ϴ�ӡ���ݳɹ���  Ĭ�ϴ�ӡģ��idΪ��<"
    				+ key
    				+ "��ģ������Ϊ��"
    				+ paras.getTemplateName()
    				+ " >");
    	} 
    	finally {
    	    dao.release();
    	}
    }

    /**
     * ͨ���������Ϣ�ͽڵ���Ϣ��������Դ������Ϣ.
     * 
     * @param printItems ��������Ϣ.
     * @param paras ����Դ������Ϣ.
     * @exception BusinessException ��������Դ������Ϣ��������.
     * 
     */
    public String createMDPrintTempData(CreateItemVO[] printItems, CreatePrintParaVO paras)
    	throws BusinessException {
        PrintToolsDAO dao = null;
        String key = null;
    	try {
    		dao = new PrintToolsDAO();
    		//����ģ������
    		key = dao.createPrintTempDataUnRegistSystemTemplate(printItems, paras);
    		Logger.debug(
    			"----------    Ϊ�ڵ�:<"
    				+ paras.getFunCode()
    				+ ">  ����Ĭ�ϴ�ӡ���ݳɹ���  Ĭ�ϴ�ӡģ��idΪ��<"
    				+ key
    				+ "��ģ������Ϊ��"
    				+ paras.getTemplateName()
    				+ " >");
    	} 
    	finally {
    	    dao.release();
    	}
    	
    	return key;
    }
    
    /**
     * ȡ��ϵͳ��ģ����Ϣ,��������ģ��,����ģ��,��ѯģ��,��ӡģ��
     * ��ҪΪ����Щģ�����ɴ�ӡģ����׼��
     */
	public PrintTemplateCreatorVO[] getSourceTemplates(String datasource, String condition) throws BusinessException {
		
		PrintToolsDAO dao = null;
    	try {
    		dao = new PrintToolsDAO(datasource);
    		return dao.getPrintCreatorVOs(condition);
    	} 
    	finally {
    	    dao.release();
    	}  
	}
	
	/**
	 * ������Ϣɾ��ϵͳĬ�ϴ�ӡģ��
	 */
	public void delOldDefaulatPrintTemplates(PrintTemplateCreatorVO[] creatorVos) throws BusinessException{
		
		if( creatorVos == null || creatorVos.length == 0)
			return;
		
		PrintToolsDAO dao = null;		
		String datasource = creatorVos[0].getDatasource();
		
    	try {
    		dao = new PrintToolsDAO(datasource);
			dao.delOldDefaulatPrintTemplates(creatorVos);
    	} 
    	finally {
    	    dao.release();
    	}  
	}

//	public void generatePrintTemplateData(PrintTemplateCreatorVO[] creatorVos) throws BusinessException {
//		if( creatorVos == null || creatorVos.length == 0)
//			return;
//		
//		PrintToolsDAO dao = null;		
//		String datasource = creatorVos[0].getDatasource();
//		
//    	try {
//    		dao = new PrintToolsDAO(datasource);
//    		for (int i = 0; i < creatorVos.length; i++) {
//    			//����Ĭ������
//    			String key = dao.generateSysPrintData(creatorVos[i]);
//    			creatorVos[i].setPtemplateid(key);
//    			Logger.debug("----------    ����Ĭ�����ݳɹ�   ----------");
//    			//����ģ������
//    			dao.generatePData(creatorVos[i]);
//    			Logger.debug("----------    ����ģ�����ݳɹ�   ----------");
//    			Logger.debug("----------    �ڵ�:<" + creatorVos[i].getFunnode() + " " + creatorVos[i].getFun_name() + ">  ��Ĭ�ϴ�ӡ�������ɳɹ���   ----------");
//    			Logger.debug("----------    Ĭ�ϴ�ӡģ��idΪ��<" + key + ">   ----------");				
//			}
//    	}
//    	finally {
//    	    dao.release();
//    	}  
//	}
	
	public PrintTempletmanageHeaderVO[] queryHeaderVO(String pkCorp, String pk_org,
			String funCode, String pkUser, String pkBusiType, String nodeKey,
			String orgType) throws BusinessException {
		
		TemplateParaVO paraVo = new TemplateParaVO();
        paraVo.setTemplateType(ITemplateStyle.printTemplate);
        paraVo.setPk_Corp(pkCorp);
        paraVo.setPk_orgUnit(pk_org);
        
        paraVo.setFunNode(funCode);
        paraVo.setOperator(pkUser);
        paraVo.setBusiType(pkBusiType);
        paraVo.setNodeKey(nodeKey);
        IPFTemplate name = (IPFTemplate) NCLocator.getInstance().lookup(IPFTemplate.class.getName());
        
        String[] ids = name.getSingleTempIdAry(paraVo);
        if(ids == null || ids.length == 0)
        	return null;
        return queryHeaderVOByIDs(ids);
	}

	@Override
	public String generateTemplate(PrintVO vo, PrintTempletmanageItemVO[] varVOes,
			boolean isRemoveTemplate, boolean isRemoveVars)
			throws BusinessException {
		PrintDAO dao = new PrintDAO();
		
		// remove old
		String funcode = vo.getTemplateVO().getVnodecode();
		if(isRemoveTemplate){
			try {
//				String delDs = "delete from pub_print_datasource where ctemplateid in (select ctemplateid from pub_print_template where vnodecode='" + funcode + "' and (pk_corp is null or pk_corp = '@@@@'))";
				String delDs = "delete from pub_print_datasource where ctemplateid in (select ctemplateid from pub_print_template where vnodecode='" + funcode + "' and (pk_corp = '~' or pk_corp = '@@@@'))";
				dao.executeUpdate(delDs);
//				String delCells = "delete from pub_print_cell where ctemplateid in (select ctemplateid from pub_print_template where vnodecode='" + funcode + "' and (pk_corp is null or pk_corp = '@@@@'))";
				String delCells = "delete from pub_print_cell where ctemplateid in (select ctemplateid from pub_print_template where vnodecode='" + funcode + "' and (pk_corp = '~' or pk_corp = '@@@@'))";
				dao.executeUpdate(delCells);
//				String delTemplates = "delete from pub_print_template where vnodecode='" + funcode + "' and (pk_corp is null or pk_corp = '@@@@')"; 
				String delTemplates = "delete from pub_print_template where vnodecode='" + funcode + "' and (pk_corp = '~' or pk_corp = '@@@@')";
				dao.executeUpdate(delTemplates);
			} catch (DbException e) {
				Logger.error(e.getMessage(), e);
				throw new BusinessException(e.getMessage());
			}
		}
		
		// 
		if(isRemoveVars){
			try {
				dao.executeUpdate("delete from pub_print_dataitem where vnodecode = '" + funcode + "'");
			} catch (DbException e) {
				Logger.error(e.getMessage(), e);
				throw new BusinessException(e.getMessage());
			}
		}
		
		//
		String pk = dao.insert(vo);
		
		//
		if(varVOes != null) {
		dao.getProxy().insertVOArray(varVOes);
		}
		
		return pk;
	}

	@Override
	public PrintTemplateVO[] getTemplateBySysTemplateIDs(String[] sysTemplateIDs,String pk_group)
			throws BusinessException {
		PrintDAO dao = null;
        try {
    		dao = new PrintDAO();
    		PrintTemplateVO[] VOs = dao.getTemplateBySysTemplateIDs(sysTemplateIDs,pk_group);
    		return VOs;
    	}
    	finally {
    	    dao.release();
    	}
	}

	@Override
	public PrintTemplateVO[] getChildrenByParent(String parentPk, String pkCorp)
			throws BusinessException {
		String where = " ptemplateid = '" + parentPk + "' and pk_corp = '"
				+ pkCorp + "'";
		PrintDAO dao = new PrintDAO();
		Collection c = dao.getProxy().retrieveByClause(PrintTemplateVO.class,
				PrintVOMapModelFactory.getPrintTemplateVOMapMeta(), where);
		return (PrintTemplateVO[]) (c == null ? null : c.toArray(new PrintTemplateVO[0]));
	}

	@Override
	public PrintTemplateVO[] queryByCorp(String fun_code, String pk_corp)
			throws BusinessException {
		BaseDAO dao = new BaseDAO();
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append("vnodecode = '" + fun_code + "' and ");
		sqlBuilder.append("pk_corp = '" + pk_corp + "' ");
		
		String sql = "select ctemplateid, vdefaultprinter, bnormalcolor, vleftnote, ipageheight, "
				+ "igridcolor, vfontname, bdistotalpagenum, bdirector, ibotmargin, ifontsize, vmidnote, "
				+ "bdispagenum, itopmargin, iscale, vtemplatename, ipagewidth, ileftmargin, fpagination, "
				+ "pk_corp, irightmargin, ffontstyle, ipagelocate, ibreakposition, vrightnote, vtemplatecode, "
				+ "vnodecode,itype,ptemplateid,mdclass,pk_org,layer,ts "
				+ "from pub_print_template where " + sqlBuilder.toString();
		
		PrintTemplateVO[] templates = null; 
		List result = (List) dao.executeQuery(sql,
				new BeanMappingListProcessor(PrintTemplateVO.class,
						PrintVOMapModelFactory.getPrintTemplateVOMapMeta()));
		if( result!=null ) {
			templates = (PrintTemplateVO[]) result.toArray(new PrintTemplateVO[result.size()]);
        }
		return templates;
	}

	@Override
	public String[] insertPrintVOS(PrintVO[] printV0S) throws BusinessException {
		  PrintDAO dao =null;
	    	try {
	    		dao = new PrintDAO();
	    		List<String> keys = new ArrayList<String>();
	    		for(PrintVO vo : printV0S){
	    			String key = dao.insert(vo);
	    			keys.add(key);
	    		}
	    		return keys.toArray(new String[0]);
	    	}    	
	    	finally {
	    	    dao.release();
	    	}
	}

	@Override
	public PrintTemplateVO[] queryByWherePart(String wherePart) throws BusinessException
	{
		if(wherePart == null || wherePart.length() == 0)
			return new PrintTemplateVO[0];
			
		BaseDAO dao = new BaseDAO();
		
		String sql = "select ctemplateid, vdefaultprinter, bnormalcolor, vleftnote, ipageheight, "
				+ "igridcolor, vfontname, bdistotalpagenum, bdirector, ibotmargin, ifontsize, vmidnote, "
				+ "bdispagenum, itopmargin, iscale, vtemplatename, ipagewidth, ileftmargin, fpagination, "
				+ "pk_corp, irightmargin, ffontstyle, ipagelocate, ibreakposition, vrightnote, vtemplatecode, "
				+ "vnodecode,itype,ptemplateid,mdclass,pk_org,layer,ts "
				+ "from pub_print_template where " + wherePart;
		
		PrintTemplateVO[] templates = null; 
		List result = (List) dao.executeQuery(sql,
				new BeanMappingListProcessor(PrintTemplateVO.class,
						PrintVOMapModelFactory.getPrintTemplateVOMapMeta()));
		if( result!=null ) {
			templates = (PrintTemplateVO[]) result.toArray(new PrintTemplateVO[result.size()]);
        }
		return templates;
	}
}
