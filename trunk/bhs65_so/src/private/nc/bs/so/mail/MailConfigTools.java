package nc.bs.so.mail;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import nc.bs.framework.common.RuntimeEnv;
import nc.bs.logging.Logger;
import nc.vo.pub.msg.SysMessageParam;

import com.thoughtworks.xstream.XStream;

/**
 * @describe ����ϵͳ���õ��ʼ���������ַ
 * @version 1.0
 */
public class MailConfigTools {

	private static XStream getAliasXstream() {
		XStream xstream = new XStream();
		xstream.alias("SysMessageParam", SysMessageParam.class);
		return xstream;
	}
	private static SysMessageParam m_smp = null;
	/*�����������ַ*/
	static String MESSAGE_4PF_CONF = RuntimeEnv.getInstance().getNCHome() + "/ierp/bin/message4pf.xml";
	public static SysMessageParam fetchSysMsgParam(){
		File msgFile = new File(MESSAGE_4PF_CONF);
		if (msgFile.exists()) {
			
			FileReader in = null;
			try {
				in = new FileReader(msgFile);
				XStream xstream = getAliasXstream();
				m_smp = (SysMessageParam) xstream.fromXML(in);
			} catch (Throwable e) {
				// catchס�����쳣
				Logger.error(e.getMessage(), e);
			} finally {
				if (in != null) {
					try {
						in.close();
					} catch (IOException e) {
						Logger.error(e.getMessage(), e);
					}
				}
				
				// yanke1 2012-3-7 ȡ��Ϣ���ĵ�����
				if (m_smp == null) {
					// һ��Ҫ����param
					m_smp = new SysMessageParam();
				}
			}
		}
		return m_smp;
	}
	
}
