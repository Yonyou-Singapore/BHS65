package nc.ui.arap.actions.interceptor;

import java.awt.event.ActionEvent;

import javax.swing.Action;

import nc.ui.arap.actions.PayEntryShowChgAction;
import nc.ui.uif2.actions.ActionInterceptor;

/***
 * ���¼���޸İ�ť����
 * @author Administrator
 *
 */
public class PayEntryEditActionInterceptor  implements ActionInterceptor {
	
	private PayEntryShowChgAction payable;

	@Override
	public boolean beforeDoAction(Action action, ActionEvent e) {
		//����ִ��ǰ
		boolean flag = false;
		if(payable.isBcombinflag){
			try {
				payable.doAction(e);
				flag = true;
			} catch (Exception e1) {
				e1.printStackTrace();
				flag = false;
			}
		}else {
			flag = true;
		}
		
		return flag;
	}

	@Override
	public void afterDoActionSuccessed(Action action, ActionEvent e) {
		//����ִ�гɹ���
	}

	@Override
	public boolean afterDoActionFailed(Action action, ActionEvent e,
			Throwable ex) {
		//����ִ��ʧ�ܺ�
		return false;
	}

	public PayEntryShowChgAction getPayable() {
		return payable;
	}

	public void setPayable(PayEntryShowChgAction payable) {
		this.payable = payable;
	}

}