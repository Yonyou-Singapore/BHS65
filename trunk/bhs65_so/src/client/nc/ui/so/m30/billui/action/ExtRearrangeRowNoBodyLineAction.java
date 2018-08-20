package nc.ui.so.m30.billui.action;

import nc.ui.pubapp.uif2app.actions.RearrangeRowNoBodyLineAction;

/**
 * ���۶������������кŹ���
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
		// TODO �Զ����ɵķ������
		String currenttablecode = this.getCardPanel().getCurrentBodyTableCode();
		if(currenttablecode.equals("Black Box")){
			return;
		}
		super.doAction();
	}
	@Override
	protected boolean isActionEnable() {
		// TODO �Զ����ɵķ������
		String currenttablecode = this.getCardPanel().getCurrentBodyTableCode();
		if(currenttablecode.equals("Black Box")){
			return false;
		}
		return super.isActionEnable();
	}
	
}
