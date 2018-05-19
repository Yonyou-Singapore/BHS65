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
 * 打印模板模块查询、存储服务接口的缺省实现

 * @version NC5.0
 * @author lhwei
 * @since 5.0
 */
public class PrintTemplateBSImpl implements IPrintTemplateQry,IPrintTemplateBS {
		
    /**
     * 根据打印模板ID获取该打印模板所有信息.
     * 这些信息包括基本信息、自定义变量信息、单元格信息、单元格扩展信息、区域画线信息.
     *
     * @param templateid 要查询的模板的ID(主键).
     * @return 装载打印模板所有信息的VO对象.
     * @exception BusinessException 如果获取打印模板信息时发生错误
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
     * 根据打印模板ID获取该打印模板所有信息,该打印模板必须为卡片类型.
     * 这些信息包括基本信息、自定义变量信息、单元格信息、单元格扩展信息、区域画线信息.
     *
     * @param templateid 要查询的模板的ID(主键).
     * @return 装载打印模板所有信息的VO对象.
     * @exception BusinessException 如果获取打印模板信息时发生错误.
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
     * 根据节点编号,获取其下的所有打印模板基本信息.
     * 
     * @param moduleName 要查询的节点编号.
     * @return PrintTemplateVO[] 查询到的打印模板基本信息VO数组.
     * @exception BusinessException 如果获取打印模板基本信息时发生错误.
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
     * 根据节点编号和公司,获取其下的所有打印模板基本信息.
     * 
     * @param moduleName 要查询的节点编号.
     * @param pkCorp 模板所属的公司主键.
     * @return PrintTemplateVO[] 查询到的打印模板基本信息VO数组.
     * @exception BusinessException 如果获取打印模板基本信息时发生错误.
     */
    public PrintTemplateVO[] getTempletByModule(String moduleName, String pkCorp) throws BusinessException {
    	// bq v55模板查找机制
    	// 找当前节点的模板
    	// 若找到：旧机制
    	// 若未找到：从模板分配表中找已分配的系统模板， 根据系统模板的nodecode找所有的模板
    	
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
        	// 根据任意一个分配的模板，找到模板对应的节点号
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
     * 根据节点编号和公司,获取其下的所有打印模板基本信息,该打印模板必须为卡片类型.
     * 
     * @param moduleName 要查询的节点编号.
     * @param pkCorp 模板所属的公司主键.
     * @return PrintTemplateVO[] 查询到的打印模板基本信息VO数组.
     * @exception BusinessException 如果获取打印模板基本信息时发生错误.
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
     * 根据模板ID,删除其对应的所有模板信息.
     * 
     * @param key 要删除的模板的ID(主键)
     * @return <tt>true</tt> 删除成功;
     *          <tt>false</tt> 该打印模板已被分配，必须先取消分配才能删除.
     * @exception BusinessException 删除打印模板信息时发生错误
     */
    public boolean delete(String key) throws BusinessException {
        PrintDAO dao =null;
        try {
        	IPFTemplate pfTemplate = 
        		(IPFTemplate) NCLocator.getInstance().lookup(IPFTemplate.class.getName());
            boolean bUsed = pfTemplate.isTemplateUsed(key, 3);
 
    		if (bUsed)
    			return false;

    		//执行删除
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
     * 根据打印模板ID获取其时间戳. 
     * 
     * @param id 要查询的模板ID(主键).
     * @return 其时间戳数组.
     * @exception BusinessException 获取时间戳时发生错误.
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
     * 将打印模板信息新增到数据库中.
     *
     * @param printVo 包含打印模板所有信息的对象.
     * @return java.lang.String  为该新增对象生成的唯一标识.
     * @exception BusinessException 将打印模板信息新增到数据库时发生错误.
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
     * 将打印模板信息新增到数据库中,并将该打印模板作为卡片类型模板.
     *
     * @param printVo 包含打印模板所有信息的对象.
     * @return java.lang.String  为该新增对象生成的唯一标识.
     * @exception BusinessException 将打印模板信息新增到数据库时发生错误.
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
     * 更新打印模板的所有信息.
     *
     * @param print 打印模板的新信息.
     * @exception BusinessException 更新打印模板信息事发生错误.
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
     * 更新打印模板的所有信息,并将该打印模板作为卡片类型模板.
     *
     * @param print 打印模板的新信息.
     * @exception BusinessException 更新打印模板信息事发生错误.
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
     * 更新打印模板的编码和名称.
     *
     * @param templateID 要更新的打印模板的ID.
     * @param templateCode 更新后的模板编码.
     * @param templateName 更新后的模板名称.
     * @exception BusinessException 更新打印模板编码和名称时发生错误.
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
     * 更新打印模板的编码和名称.
     * @param printTemplate 要更新的模板
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
     * 将异常统一记录
     * @param e
     */
    private void reportException(Exception e) {
    	Logger.error(e.getMessage(), e);
    }

    /**
     * 通过主键获取模板基本信息.
     *
     * @param key 要查询的模板主键.
     * @return 获取到的模板基本信息.
     * @exception BusinessException 获取模板基本信息时发生错误.
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
     * 向数据库中新增多个模板基本信息.
     *
     * @param printTemplates 要新增的多个模板基本信息对象.
     * @return java.lang.String[] 为新增的这些模板设置的唯一标识.
     * @exception BusinessException 向数据库中新增多个模板基本信息时发生错误.
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
     * 通过公司主键获得其所有打印模板基本信息对象数组.
     *
     * @param unitCode 模板所属的公司主键.
     * @return 获得的所有打印模板基本信息对象数组.
     * @exception BusinessException 获取模板基本信息事发生错误.
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
     * 根据多个模板ID,返回它们的模板基本信息.
     * 
     * @param 要获取的多个模板ID.
     * @return 获取到的多个模板对象.
     * @throws BusinessException 获取模板基本信息事发生错误.
     */
    public PrintTempletmanageHeaderVO[] queryHeaderVOByIDs(String[] ids) throws BusinessException {
    	PrintTempletmanageHeaderVO[] printTemplates = null;
    	PrintTemplateDAO dao = null;
    	try {
    		dao = new PrintTemplateDAO();
    		printTemplates = dao.queryHeaderVOByIDs(ids);
    		
    		// 还要根据ids的顺序对ptvo进行排序
    		if (printTemplates != null) {
    			//update chenth 20180503 打印时选择的模板按名称排序
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
     * 通过节点编号和公司获取节点级的(*不*是模板级)数据源变量信息.
     *
     * @param nodeCode 节点编号.
     * @param pkCorp 公司主键.
     * @return 获取到的数据源变量信息.
     * @exception BusinessException 异常说明
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
     * 通过传入的信息和节点信息创建数据源变量信息.
     * 
     * @param printItems 数据项信息.
     * @param paras 数据源创建信息.
     * @exception BusinessException 创建数据源变量信息发生错误.
     * 
     */
    public void createPrintTempData(CreateItemVO[] printItems, CreatePrintParaVO paras)
    	throws BusinessException {
        PrintToolsDAO dao = null;
    	try {
    		dao = new PrintToolsDAO();
    		//生成模板内容
    		String key = dao.createPrintTempData(printItems, paras);
    		Logger.debug(
    			"----------    为节点:<"
    				+ paras.getFunCode()
    				+ ">  生成默认打印数据成功！  默认打印模板id为：<"
    				+ key
    				+ "，模板名称为："
    				+ paras.getTemplateName()
    				+ " >");
    	} 
    	finally {
    	    dao.release();
    	}
    }

    /**
     * 通过传入的信息和节点信息创建数据源变量信息.
     * 
     * @param printItems 数据项信息.
     * @param paras 数据源创建信息.
     * @exception BusinessException 创建数据源变量信息发生错误.
     * 
     */
    public String createMDPrintTempData(CreateItemVO[] printItems, CreatePrintParaVO paras)
    	throws BusinessException {
        PrintToolsDAO dao = null;
        String key = null;
    	try {
    		dao = new PrintToolsDAO();
    		//生成模板内容
    		key = dao.createPrintTempDataUnRegistSystemTemplate(printItems, paras);
    		Logger.debug(
    			"----------    为节点:<"
    				+ paras.getFunCode()
    				+ ">  生成默认打印数据成功！  默认打印模板id为：<"
    				+ key
    				+ "，模板名称为："
    				+ paras.getTemplateName()
    				+ " >");
    	} 
    	finally {
    	    dao.release();
    	}
    	
    	return key;
    }
    
    /**
     * 取得系统的模板信息,包括单据模板,报表模板,查询模板,打印模板
     * 主要为从这些模板生成打印模板做准备
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
	 * 根据信息删除系统默认打印模板
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
//    			//生成默认数据
//    			String key = dao.generateSysPrintData(creatorVos[i]);
//    			creatorVos[i].setPtemplateid(key);
//    			Logger.debug("----------    生成默认数据成功   ----------");
//    			//生成模板内容
//    			dao.generatePData(creatorVos[i]);
//    			Logger.debug("----------    生成模板内容成功   ----------");
//    			Logger.debug("----------    节点:<" + creatorVos[i].getFunnode() + " " + creatorVos[i].getFun_name() + ">  的默认打印数据生成成功！   ----------");
//    			Logger.debug("----------    默认打印模板id为：<" + key + ">   ----------");				
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
