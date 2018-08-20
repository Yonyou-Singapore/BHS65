package nc.ui.so.m32.billui.action;

import java.awt.event.ActionEvent;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import nc.bs.framework.common.NCLocator;
import nc.itf.mail.ISendMail;
import nc.message.ByteArrayAttachment;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.print.IMetaDataDataSource;
import nc.ui.pub.print.PrintCil;
import nc.ui.pub.print.PrintEntry;
import nc.ui.pub.print.TemplateContainer;
import nc.ui.pub.print.TemplateItem;
import nc.ui.pub.print.page.DocGroupUtil;
import nc.ui.pub.print.version55.print.PrintCache;
import nc.ui.pub.print.version55.print.merge.CopyConfig;
import nc.ui.pub.print.version55.print.output.OutputDocument;
import nc.ui.pub.print.version55.print.output.paint.pdf.PdfOutputSetting;
import nc.ui.pub.print.version55.print.output.paint.pdf.PdfTask;
import nc.ui.pub.print.version55.print.template.Paper;
import nc.ui.so.m32.billui.action.print.InvoiceMetaDataBasedPrintAction;
import nc.ui.so.m32.billui.model.SaleInvoiceManageModel;
import nc.ui.so.m32.billui.view.SaleInvoiceEditor;
import nc.ui.so.pub.ref.PrintTemplateRefModel;
import nc.vo.pub.print.MergeConfig;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.uif2.LoginContext;

/**
 * 发送邮件
 * @author Thinkpad
 * @date 2017-6-30
 */
public class SendMailAction extends InvoiceMetaDataBasedPrintAction {
	
	private static final long serialVersionUID = -6121361063052853978L;
	private SaleInvoiceEditor editor;
	private SaleInvoiceManageModel model;

	public SendMailAction() {
		// TODO 自动生成的构造函数存根
		setBtnName("Send Mail");
		setCode("sendMailAction");
	}
	@Override
	public void doAction(ActionEvent arg0) throws Exception {
		// TODO 自动生成的方法存根
		Object obj = this.getModel().getSelectedData();
		if(obj == null){
			ExceptionUtils.wrappBusinessException("请选择一条数据！");
		}
		SaleInvoiceVO aggvo = (SaleInvoiceVO) obj;
		// 发票的单据号
		String billCode = aggvo.getParentVO().getVbillcode();
		// 单据日期
		String dbilldate = aggvo.getParentVO().getDbilldate().toString();
		// 客户
		String pk_customer = aggvo.getParentVO().getCinvoicecustid();
		// 获取打印数据源
		IMetaDataDataSource[] datasource = getDefaultMetaDataSource();
		LoginContext ctx = getModel().getContext();
		//弹出模板选择框，选择发送邮件使用的模板。
		UIRefPane refPane = new UIRefPane();
		//参照
		PrintTemplateRefModel model = new PrintTemplateRefModel();
		refPane.setRefModel(model);
		refPane.showModel();
		//获取模板主键
		String ctemplateid = refPane.getRefPK();
		PrintEntry  printEntry = new PrintEntry(getParent(), null);
	    printEntry.setTemplateID(ctemplateid);
	    
	    if(printEntry.getTemplateID() == null){
	    	return;
	    }
	    if(getPrintListener() != null)
	        printEntry.setPrintListener(getPrintListener());
		
		
		TemplateContainer templates = new TemplateContainer();
		// 打印模板，ot为打印的nodekey
		TemplateItem item = new TemplateItem("default", "", ctx.getPk_group(),
				ctx.getNodeCode(), ctx.getPk_loginUser(), null, "mail", null);
		item.addDs(datasource[0]);
		item.setPk_org(null);
		item.setTemplateId(printEntry.getTemplateID());
		templates.addItem(item);
		printEntry.setTemplates(templates);

		PdfOutputSetting pos = new PdfOutputSetting();
		String userHome = System.getProperty("user.home");
		pos.setExportDestPath(userHome + File.separator + "BHS Sale Invoice " +billCode + ".pdf");
		// 初始化
		PrintCache.getInstance().storePdfOutputSetting(templates, pos);

		Paper pageSize = DocGroupUtil.getTaskPaper(templates);
		MergeConfig mc = DocGroupUtil.getTaskMergeConfig(templates);
		CopyConfig cc = DocGroupUtil.getTaskCopyConfig(templates);
		OutputDocument docs = DocGroupUtil.orgOutputDocument(templates);
		// 调用打印功能生成pdf文件
		PrintCil.processDemoPrint(templates, pageSize);
		PdfTask task = new PdfTask(null, docs, pos, pageSize, mc, cc);
		task.setShowProgressor(false);
		task.setNewThread(false);
		task.run();
		// 将pdf文件作为附件发送邮件
		File file = new File(userHome + File.separator + "BHS Sale Invoice " +billCode + ".pdf");
		boolean flag = true;
		ByteArrayOutputStream bos = null;
		BufferedInputStream in = null;
		FileInputStream input = null;
		try{
			while(flag){
				if(file.exists()){
					flag = false;
					bos = new ByteArrayOutputStream(); 
					input = new FileInputStream(file);
			        in =  new BufferedInputStream(input);  
		            int buf_size = 1024;  
		            byte[] buffer = new byte[buf_size];  
		            int len = 0;  
		            while (-1 != (len = in.read(buffer))) {  
		                bos.write(buffer, 0, len);  
		            }  
		            byte[] bytes = bos.toByteArray();      
					ByteArrayAttachment byteattachment = new ByteArrayAttachment("BHS Sale Invoice " +billCode + ".pdf",bytes);
					ISendMail service = NCLocator.getInstance().lookup(ISendMail.class);
					service.sendMail_byte(dbilldate, pk_customer,byteattachment);
					break;
				}
			}
		}catch(Exception e){
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}finally{
			
			try {  
				input.close();
                in.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
			bos.close();  
            if(file.exists()){
            	file.delete();
			}
		}
		MessageDialog.showWarningDlg(getEditor(), "提示", "发送邮件成功");
	}

	public SaleInvoiceEditor getEditor() {
		return editor;
	}
	public void setEditor(SaleInvoiceEditor editor) {
		this.editor = editor;
	}
	public SaleInvoiceManageModel getModel() {
		return model;
	}
	public void setModel(SaleInvoiceManageModel model) {
		this.model = model;
	}
}
