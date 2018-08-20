package nc.bs.so.mail;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import nc.bs.framework.common.RuntimeEnv;
import nc.bs.logging.Logger;
import nc.vo.pub.msg.SysMessageParam;

import com.thoughtworks.xstream.XStream;

/**
 * @describe 邮箱系统设置的邮件服务器地址
 * @version 1.0
 */
public class MailConfigTools {

	private static XStream getAliasXstream() {
		XStream xstream = new XStream();
		xstream.alias("SysMessageParam", SysMessageParam.class);
		return xstream;
	}
	private static SysMessageParam m_smp = null;
	/*邮箱服务器地址*/
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
				// catch住所有异常
				Logger.error(e.getMessage(), e);
			} finally {
				if (in != null) {
					try {
						in.close();
					} catch (IOException e) {
						Logger.error(e.getMessage(), e);
					}
				}
				
				// yanke1 2012-3-7 取消息中心的配置
				if (m_smp == null) {
					// 一定要生成param
					m_smp = new SysMessageParam();
				}
			}
		}
		return m_smp;
	}
	
}
