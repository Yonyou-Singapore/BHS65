package nc.ui.arap.actions;

import java.awt.event.ActionEvent;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import nc.bs.framework.common.NCLocator;
import nc.itf.mail.ISendMail;
import nc.itf.uap.busibean.ISysInitQry;
import nc.message.ByteArrayAttachment;
import nc.message.FileAttachment;
import nc.ui.arap.model.ArapBillManageModel;
import nc.ui.arap.view.ArapBillCardForm;
import nc.ui.pub.beans.MessageDialog;
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
import nc.vo.arap.receivable.AggReceivableBillVO;
import nc.vo.pub.print.MergeConfig;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.uif2.LoginContext;

/**
 * �����ʼ�
 * @author Thinkpad
 * @date 2017-6-30
 */
public class SendMailAction extends PrintOutputAction {
	
	private static final long serialVersionUID = -6121361063052853978L;
	private ArapBillCardForm editor;
	private ArapBillManageModel model;

	public SendMailAction() {
		// TODO �Զ����ɵĹ��캯�����
		setBtnName("Send Mail");
		setCode("sendMailAction");
	}

	@Override
	public void doAction(ActionEvent arg0) throws Exception {
		// TODO �Զ����ɵķ������
		Object obj = this.getModel().getSelectedData();
		if(obj == null){
			ExceptionUtils.wrappBusinessException("��ѡ��һ�����ݣ�");
		}
		AggReceivableBillVO aggvo = (AggReceivableBillVO) obj;
		// ��Ʊ�ĵ��ݺ�
		String billCode = aggvo.getHeadVO().getBillno();
		// ��������
		String dbilldate = aggvo.getHeadVO().getBilldate().toString();
		// �ͻ�
		String pk_customer = aggvo.getHeadVO().getCustomer();
		// ��ȡ��ӡ����Դ
		LoginContext ctx = getModel().getContext();
		ISysInitQry qry = NCLocator.getInstance().lookup(ISysInitQry.class);
		String templateid = qry.getParaString(aggvo.getHeadVO().getPk_org(), "BHS002");
		
		getTaxForm().setShowDatas(aggvo);
		
		PrintEntry  printEntry = createPrintentry();
	    printEntry.setTemplateID(templateid);
	    
	    if(printEntry.getTemplateID() == null){
	    	ExceptionUtils.wrappBusinessException("����BHS002��Ӧ�յ������ʼ���Ӧ��ӡģ��������δά�����ݣ�����ϵ����Ա��");
	    }
	    setDatasource(printEntry);
		TemplateContainer templates = new TemplateContainer();
		// ��ӡģ�壬otΪ��ӡ��nodekey
		TemplateItem item = new TemplateItem("default", "", ctx.getPk_group(),
				ctx.getNodeCode(), ctx.getPk_loginUser(), null, "mail", null);
		item.addDs(printEntry.getDataSource());
		item.setPk_org(null);
		item.setTemplateId(printEntry.getTemplateID());
		templates.addItem(item);
		printEntry.setTemplates(templates);
		
		PdfOutputSetting pos = new PdfOutputSetting();
		String userHome = System.getProperty("user.home");
		pos.setExportDestPath(userHome + File.separator + " BHS AR " +billCode + ".pdf");
		// ��ʼ��
		PrintCache.getInstance().storePdfOutputSetting(templates, pos);

		Paper pageSize = DocGroupUtil.getTaskPaper(templates);
		MergeConfig mc = DocGroupUtil.getTaskMergeConfig(templates);
		CopyConfig cc = DocGroupUtil.getTaskCopyConfig(templates);
		OutputDocument docs = DocGroupUtil.orgOutputDocument(templates);
		// ���ô�ӡ��������pdf�ļ�
		PrintCil.processDemoPrint(templates, pageSize);
		PdfTask task = new PdfTask(null, docs, pos, pageSize, mc, cc);
		task.setShowProgressor(false);
		task.setNewThread(false);
		task.run();
		
		// ��pdf�ļ���Ϊ���������ʼ�
		File file = new File(userHome + File.separator + " BHS AR " +billCode + ".pdf");
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
					ByteArrayAttachment byteattachment = new ByteArrayAttachment(" BHS AR " +billCode + ".pdf",bytes);
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
		MessageDialog.showWarningDlg(getEditor(), "��ʾ", "�����ʼ��ɹ�");
	}

	public ArapBillCardForm getEditor() {
		return editor;
	}
	public void setEditor(ArapBillCardForm editor) {
		this.editor = editor;
	}
	public ArapBillManageModel getModel() {
		return model;
	}
	public void setModel(ArapBillManageModel model) {
		this.model = model;
	}
}
