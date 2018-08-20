package nc.ui.so.m30.billui.action;

import nc.ui.pubapp.uif2app.actions.RearrangeRowNoBodyLineAction;

/**
 * 销售订单表体重拍行号功能
 * @author Thinkpad
 * @date 2017-7-2
 */
public class ExtRearrangeRowNoBodyLineAction extends RearrangeRowNoBodyLineAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4372882554679676792L;
	@Override
	public void doAction() {
		// TODO 自动生成的方法存根
		String currenttablecode = this.getCardPanel().getCurrentBodyTableCode();
		if(currenttablecode.equals("Black Box")){
			return;
		}
		super.doAction();
	}
	@Override
	protected boolean isActionEnable() {
		// TODO 自动生成的方法存根
		String currenttablecode = this.getCardPanel().getCurrentBodyTableCode();
		if(currenttablecode.equals("Black Box")){
			return false;
		}
		return super.isActionEnable();
	}
	
}
