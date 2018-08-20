package nc.ui.so.pub.ref;

import nc.ui.bd.ref.AbstractRefModel;

/**
 * 销售发票打印模板参照
 * @author Thinkpad
 * @date 2017-8-25
 */
public class PrintTemplateRefModel  extends AbstractRefModel {

	
	public PrintTemplateRefModel() {
		// TODO 自动生成的构造函数存根
		setRefNodeName("销售发票打印模板参照");
		setMutilLangNameRef(false);
	}
	/**
	 * 参照列数
	 */
	public int getDefaultFieldCount() {
		return getFieldCode().length;
	}
	/**
	 * 参照数据库字段名数组
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
	 * 和数据库字段名数组对应的中文名称数组
	 * @return java.lang.String
	 */
	public java.lang.String[] getFieldName() {
		return new String[] { "模板编码","模板名称","主键" };
	}

	public java.lang.String[] getHiddenFieldCode() {
		return new String[]{"ctemplateid"};
	}
	/**
	 * 要返回的主键字段名
	 * @return java.lang.String
	 */
	public String getPkFieldCode() {
		return "ctemplateid";
	}
	/**
	 * 参照标题
	 * 
	 * @return java.lang.String
	 */
	public String getRefTitle() {
		return "打印模板选择";
	}

	/**
	 * 参照数据库表或者视图名
	 * 
	 * @return java.lang.String
	 */
	public String getTableName() {
		return "pub_print_template";
	}
	/**
	 * 此处插入方法说明。
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
		// TODO 自动生成的方法存根
		super.setMutilLangNameRef(isMutilLangNameRef);
	}
}
