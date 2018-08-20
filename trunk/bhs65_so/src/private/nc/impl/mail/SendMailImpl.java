package nc.impl.mail;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.bs.framework.common.RuntimeEnv;
import nc.bs.so.mail.MailConfigTools;
import nc.bs.so.mail.tool.SendMailToolUtil;
import nc.itf.mail.ISendMail;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.message.Attachment;
import nc.message.ByteArrayAttachment;
import nc.message.FileAttachment;
import nc.vo.framework.rsa.Encode;
import nc.vo.pub.BusinessException;
import nc.vo.pub.msg.DefaultSMTP;
import nc.vo.pub.msg.SysMessageParam;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * �����ʼ�����
 * 
 * @author Thinkpad
 * @date 2017-7-6
 */
public class SendMailImpl implements ISendMail {

	@Override
	public void sendMail(String dbilldate,String pk_customer,FileAttachment fileattachment)
			throws BusinessException {
		// TODO �Զ����ɵķ������

		// �ʼ�����
		String mailtitle = getMailTitle(dbilldate);
		//�ʼ�����
		String mailContent = getMailContent(dbilldate);
		
		String email = getCustomerEmail(pk_customer);
		
		/* ��ȡSysConfig�ж�����ʼ���������ַ */
		SysMessageParam smp = MailConfigTools.fetchSysMsgParam();
		if (smp == null)
			throw new BusinessException(
					"����1��û����ȷ�����ʼ��������ļ�/ierp/bin/message4pf.xml");
		DefaultSMTP defaultSmtp = smp.getSmtp();
		if (defaultSmtp == null)
			throw new BusinessException(
					"����2��û����ȷ�����ʼ��������ļ�/ierp/bin/message4pf.xml");
		// ���������
		String smtpHost = defaultSmtp.getSmtp();
		// ���͵�ַ
		String fromAddr = defaultSmtp.getSender();
		// ��������������
		String password = defaultSmtp.getPassword();
		// ��������������
		String senderName = defaultSmtp.getSenderName();
		// ������������
//		if (password != null)
//			password = new Encode().decode(password);

		SendMailToolUtil util = new SendMailToolUtil(smtpHost, fromAddr,
				password);
		try {
			util.sendHtmlMail(senderName,
					new String[] { email}, null, mailtitle,
					mailContent, new Attachment[] { fileattachment });
		} catch (Exception e) {
			// TODO �Զ����ɵ� catch ��
			throw new BusinessException("�����ʼ����������ʼ������������Լ��ͻ�ά���������Ƿ���ȷ" + e.getMessage());
		}
	}
	
	/**
	 * ��ȡ�ͻ�����
	 * @author Thinkpad
	 * @date 2017-7-6
	 */
	public String getCustomerEmail(String pk_customer) throws BusinessException{
		String sql = " select email from bd_customer where pk_customer = '"+pk_customer+"'";
		try {
			Object obj = new BaseDAO().executeQuery(sql, new ColumnProcessor());
			if(obj == null || obj.toString().equals("")){
				throw new BusinessException("�ͻ�δά�����䣡");
			}
			return obj.toString();
		} catch (DAOException e) {
			// TODO �Զ����ɵ� catch ��
			throw new BusinessException("��ȡ�ͻ�����ʧ�ܣ�"+e.getMessage());
		}
	}

	/**
	 * ��ȡ�ʼ��ı���
	 * 
	 * @author Thinkpad
	 * @date 2017-7-6
	 */
	public String getMailTitle(String dbilldate) {

		StringBuffer bufferStr = new StringBuffer();
		bufferStr.append("SOA For ");
		String month = dbilldate.substring(5, 7);
		bufferStr.append(getMonthStr(month));
		String year = dbilldate.substring(0, 4);
		bufferStr.append(" " + year);
		return bufferStr.toString();
	}

	public String getMailContent(String dbilldate) {
		StringBuffer bufferStr = new StringBuffer();
		bufferStr.append("Hi <br>");
		bufferStr.append("Please find the attached  ");
		String month = dbilldate.substring(5, 7);
		bufferStr.append(getMonthStr(month));
		String year = dbilldate.substring(0, 4);
		bufferStr.append(" " + year);
		bufferStr.append(" SOA for your reference");
		return bufferStr.toString();
	}

	/**
	 * ��ȡ�·��ַ���
	 * 
	 * @author Thinkpad
	 * @date 2017-7-6
	 */
	public String getMonthStr(String month) {
		StringBuffer bufferStr = new StringBuffer();
		switch (month) {
		case "01":
			bufferStr.append("January");
			break;
		case "02":
			bufferStr.append("February");
			break;
		case "03":
			bufferStr.append("March");
			break;
		case "04":
			bufferStr.append("April");
			break;
		case "05":
			bufferStr.append("May");
			break;
		case "06":
			bufferStr.append("June");
			break;
		case "07":
			bufferStr.append("July");
			break;
		case "08":
			bufferStr.append("Aguest");
			break;
		case "09":
			bufferStr.append("September");
			break;
		case "10":
			bufferStr.append("October");
			break;
		case "11":
			bufferStr.append("November");
			break;
		default:
			bufferStr.append("December");
			break;
		}
		return bufferStr.toString();
	}

	@Override
	public void sendMail_byte(String dbilldate, String pk_customer,ByteArrayAttachment byteArrayattachment) throws BusinessException {
		// TODO �Զ����ɵķ������
		// �ʼ�����
		String mailtitle = getMailTitle(dbilldate);
		//�ʼ�����
		String mailContent = getMailContent(dbilldate);
		
		String email = getCustomerEmail(pk_customer);
		
		/* ��ȡSysConfig�ж�����ʼ���������ַ */
		SysMessageParam smp = MailConfigTools.fetchSysMsgParam();
		if (smp == null)
			throw new BusinessException(
					"����1��û����ȷ�����ʼ��������ļ�/ierp/bin/message4pf.xml");
		DefaultSMTP defaultSmtp = smp.getSmtp();
		if (defaultSmtp == null)
			throw new BusinessException(
					"����2��û����ȷ�����ʼ��������ļ�/ierp/bin/message4pf.xml");
		// ���������
		String smtpHost = "mail.yonyou.com";// defaultSmtp.getSmtp(); 
		// ���͵�ַ
		String fromAddr = "wangzna@yonyou.com";//defaultSmtp.getSender();
		// ��������������
		String password = "ning7500534";//defaultSmtp.getPassword();
		// ��������������
		String senderName =   "wangzna@yonyou.com";//defaultSmtp.getSenderName();
		// ������������
//		if (password != null)
//			password = new Encode().decode(password);

		SendMailToolUtil util = new SendMailToolUtil(smtpHost, fromAddr,
				password);
		try {
			util.sendHtmlMail(senderName,
					new String[] { email}, null, mailtitle,
					mailContent, new Attachment[] { byteArrayattachment });
		} catch (Exception e) {
			// TODO �Զ����ɵ� catch ��
			throw new BusinessException("�����ʼ����������ʼ������������Լ��ͻ�ά���������Ƿ���ȷ" + e.getMessage());
		}		
	}

}
