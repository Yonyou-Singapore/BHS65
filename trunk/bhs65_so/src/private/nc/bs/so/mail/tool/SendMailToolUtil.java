package nc.bs.so.mail.tool;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import javax.mail.util.ByteArrayDataSource;

import nc.bs.framework.common.RuntimeEnv;
import nc.message.Attachment;
import nc.message.ByteArrayAttachment;
import nc.message.FileAttachment;

public class SendMailToolUtil {
	
	private String mailHost = null;
	private String username = null;
	private String password = null;
	private boolean isAuthen = true;
	private String encode = Charset.defaultCharset().name();

	public SendMailToolUtil(String mailHost, String user, String pwd) {
		this(mailHost, user, pwd, true);
	}

	public SendMailToolUtil(String mailHost, String user, String pwd, boolean isAuthen) {
		super();
		this.mailHost = mailHost;
		this.username = user;
		this.password = pwd;
		this.isAuthen = isAuthen;
	}

	private Session createSession() {
		Properties props = new Properties();
		props.put("mail.smtp.host", mailHost);
		if (isAuthen) {			
			props.put("mail.smtp.auth", "true");
			Authenticator auth = new NCMailAuthenticator();
			return Session.getInstance(props, auth);
		}
		return Session.getInstance(props);
	}

	private class NCMailAuthenticator extends Authenticator {
		@Override
		protected PasswordAuthentication getPasswordAuthentication() {

			return new PasswordAuthentication(username, password);
		}
	}
	private Transport getTransport(Session session) throws Exception {
		Transport transport = session.getTransport("smtp");
		transport.connect(username, password);
		return transport;
	}

	private Address[] convertToAddress(String[] strs) throws AddressException {
		int count = strs == null ? 0 : strs.length;
		Address[] address = new Address[count];
		for (int i = 0; i < count; i++) {
			address[i] = new InternetAddress(strs[i]);
		}
		return address;
	}
	public void sendHtmlMail(String from, String[] to, String[] cc,
			String subject, String text, Attachment[] attachments)
			throws Exception {
		Session session = createSession();
		Message msg = createMimeMessage(from, to, cc, subject, text,
				attachments, true, session);
		// TODO 必须的？？
		msg.setHeader("X-Mailer", "htmlsend");
		Transport transport = getTransport(session);
		transport.sendMessage(msg, msg.getAllRecipients());
		transport.close();

	}
	private Message createMimeMessage(String from, String[] to, String[] cc,
			String subject, String text, Attachment[] attachments,
			boolean HTML, Session session) throws MessagingException,
			AddressException, UnsupportedEncodingException, IOException,
			Exception {
		Message msg = new MimeMessage(session);
		// 发送人
		if (from != null && from.trim().length() > 0)
			msg.setFrom(new InternetAddress(from));
		// 接收人
		msg.setRecipients(RecipientType.TO, convertToAddress(to));
		if (cc != null && cc.length > 0)
			msg.setRecipients(RecipientType.CC, convertToAddress(cc));
		// 主题（标题）
		msg.setSubject(subject);
		// 发送时间
		msg.setSentDate(new Date());

		int count = attachments == null ? 0 : attachments.length;
		// boolean HTML = false;
		// 处理附件
		if (count > 0) {
			javax.mail.Multipart multipart = new javax.mail.internet.MimeMultipart();
			javax.mail.internet.MimeBodyPart mimeBodyPart = new javax.mail.internet.MimeBodyPart();

			if (HTML) {
				mimeBodyPart.setContent(text, "text/html;charset="
						+ this.encode);
			} else {
				mimeBodyPart.setText(text, this.encode);
			}
			multipart.addBodyPart(mimeBodyPart);

			for (int i = 0; i < count; i++) {
				Attachment attach = attachments[i];
				MimeBodyPart part = new MimeBodyPart();
				String name = attach.getName();
				name = MimeUtility.encodeText(name);
				if (attach instanceof FileAttachment) {
					part.setDataHandler(new DataHandler(new FileDataSource(
							((FileAttachment) attach).getFile())));
				} else if (attach instanceof ByteArrayAttachment) {
					part.setDataHandler(new DataHandler(
							new ByteArrayDataSource(
									((ByteArrayAttachment) attach)
											.getInputStream(),
									((ByteArrayAttachment) attach)
											.getMimeType())));
				}
				part.setFileName(name);
				multipart.addBodyPart(part);
			}
			msg.setContent(multipart);
		} else {
			if (HTML) {
				msg.setContent(text, "text/html;charset=" + this.encode);
			} else {
				msg.setText(text);
			}
		}
		// TODO ?
		// msg.saveChanges();
		return msg;
	}
}
