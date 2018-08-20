package nc.ui.so.pub.ref;

import nc.ui.bd.ref.AbstractRefModel;

/**
 * ���۷�Ʊ��ӡģ�����
 * @author Thinkpad
 * @date 2017-8-25
 */
public class PrintTemplateRefModel  extends AbstractRefModel {

	
	public PrintTemplateRefModel() {
		// TODO �Զ����ɵĹ��캯�����
		setRefNodeName("���۷�Ʊ��ӡģ�����");
		setMutilLangNameRef(false);
	}
	/**
	 * ��������
	 */
	public int getDefaultFieldCount() {
		return getFieldCode().length;
	}
	/**
	 * �������ݿ��ֶ�������
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String[] getFieldCode() {
		return new String[] { "vtemplatecode","vtemplatename","ctemplateid"};

	}

	public String getRefCodeField() {
		return "vtemplatecode";
	}

	/**
	 * �����ݿ��ֶ��������Ӧ��������������
	 * @return java.lang.String
	 */
	public java.lang.String[] getFieldName() {
		return new String[] { "ģ�����","ģ������","����" };
	}

	public java.lang.String[] getHiddenFieldCode() {
		return new String[]{"ctemplateid"};
	}
	/**
	 * Ҫ���ص������ֶ���
	 * @return java.lang.String
	 */
	public String getPkFieldCode() {
		return "ctemplateid";
	}
	/**
	 * ���ձ���
	 * 
	 * @return java.lang.String
	 */
	public String getRefTitle() {
		return "��ӡģ��ѡ��";
	}

	/**
	 * �������ݿ�������ͼ��
	 * 
	 * @return java.lang.String
	 */
	public String getTableName() {
		return "pub_print_template";
	}
	/**
	 * �˴����뷽��˵����
	 * @return java.lang.String
	 */
	public java.lang.String getWherePart() {
		return " vnodecode = '40060501' and isnull(dr,0) = 0 ";
	}

	@Override
	public boolean isCacheEnabled() {
		return false;
	}
	
	@Override
	public void setMutilLangNameRef(boolean isMutilLangNameRef) {
		// TODO �Զ����ɵķ������
		super.setMutilLangNameRef(isMutilLangNameRef);
	}
}
