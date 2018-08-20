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
 * 发送邮件服务
 * 
 * @author Thinkpad
 * @date 2017-7-6
 */
public class SendMailImpl implements ISendMail {

	@Override
	public void sendMail(String dbilldate,String pk_customer,FileAttachment fileattachment)
			throws BusinessException {
		// TODO 自动生成的方法存根

		// 邮件主题
		String mailtitle = getMailTitle(dbilldate);
		//邮件内容
		String mailContent = getMailContent(dbilldate);
		
		String email = getCustomerEmail(pk_customer);
		
		/* 获取SysConfig中定义的邮件服务器地址 */
		SysMessageParam smp = MailConfigTools.fetchSysMsgParam();
		if (smp == null)
			throw new BusinessException(
					"错误1：没有正确配置邮件服务器文件/ierp/bin/message4pf.xml");
		DefaultSMTP defaultSmtp = smp.getSmtp();
		if (defaultSmtp == null)
			throw new BusinessException(
					"错误2：没有正确配置邮件服务器文件/ierp/bin/message4pf.xml");
		// 邮箱服务器
		String smtpHost = defaultSmtp.getSmtp();
		// 发送地址
		String fromAddr = defaultSmtp.getSender();
		// 发件人邮箱密码
		String password = defaultSmtp.getPassword();
		// 发件人邮箱姓名
		String senderName = defaultSmtp.getSenderName();
		// 解析加密密码
//		if (password != null)
//			password = new Encode().decode(password);

		SendMailToolUtil util = new SendMailToolUtil(smtpHost, fromAddr,
				password);
		try {
			util.sendHtmlMail(senderName,
					new String[] { email}, null, mailtitle,
					mailContent, new Attachment[] { fileattachment });
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			throw new BusinessException("发送邮件错误：请检查邮件服务器配置以及客户维护的邮箱是否正确" + e.getMessage());
		}
	}
	
	/**
	 * 获取客户邮箱
	 * @author Thinkpad
	 * @date 2017-7-6
	 */
	public String getCustomerEmail(String pk_customer) throws BusinessException{
		String sql = " select email from bd_customer where pk_customer = '"+pk_customer+"'";
		try {
			Object obj = new BaseDAO().executeQuery(sql, new ColumnProcessor());
			if(obj == null || obj.toString().equals("")){
				throw new BusinessException("客户未维护邮箱！");
			}
			return obj.toString();
		} catch (DAOException e) {
			// TODO 自动生成的 catch 块
			throw new BusinessException("获取客户邮箱失败："+e.getMessage());
		}
	}

	/**
	 * 获取邮件的标题
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
	 * 获取月份字符串
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
		// TODO 自动生成的方法存根
		// 邮件主题
		String mailtitle = getMailTitle(dbilldate);
		//邮件内容
		String mailContent = getMailContent(dbilldate);
		
		String email = getCustomerEmail(pk_customer);
		
		/* 获取SysConfig中定义的邮件服务器地址 */
		SysMessageParam smp = MailConfigTools.fetchSysMsgParam();
		if (smp == null)
			throw new BusinessException(
					"错误1：没有正确配置邮件服务器文件/ierp/bin/message4pf.xml");
		DefaultSMTP defaultSmtp = smp.getSmtp();
		if (defaultSmtp == null)
			throw new BusinessException(
					"错误2：没有正确配置邮件服务器文件/ierp/bin/message4pf.xml");
		// 邮箱服务器
		String smtpHost = "mail.yonyou.com";// defaultSmtp.getSmtp(); 
		// 发送地址
		String fromAddr = "wangzna@yonyou.com";//defaultSmtp.getSender();
		// 发件人邮箱密码
		String password = "ning7500534";//defaultSmtp.getPassword();
		// 发件人邮箱姓名
		String senderName =   "wangzna@yonyou.com";//defaultSmtp.getSenderName();
		// 解析加密密码
//		if (password != null)
//			password = new Encode().decode(password);

		SendMailToolUtil util = new SendMailToolUtil(smtpHost, fromAddr,
				password);
		try {
			util.sendHtmlMail(senderName,
					new String[] { email}, null, mailtitle,
					mailContent, new Attachment[] { byteArrayattachment });
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			throw new BusinessException("发送邮件错误：请检查邮件服务器配置以及客户维护的邮箱是否正确" + e.getMessage());
		}		
	}

}
