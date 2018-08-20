package nc.itf.mail;

import nc.message.ByteArrayAttachment;
import nc.message.FileAttachment;
import nc.vo.pub.BusinessException;

public interface ISendMail {

	
	public void sendMail(String dbilldate,String pk_customer,FileAttachment fileattachment) throws BusinessException;
	
	public void sendMail_byte(String dbilldate,String pk_customer,ByteArrayAttachment byteArrayattachment) throws BusinessException;
}
