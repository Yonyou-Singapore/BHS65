package nc.ui.arap.actions.interceptor;

import java.awt.event.ActionEvent;

import javax.swing.Action;

import nc.ui.arap.actions.PayableShowChgAction;
import nc.ui.uif2.actions.ActionInterceptor;

/***
 * �����������ť����
 * @author Administrator
 *
 */
public class PayApprovalActionInterceptor  implements ActionInterceptor {
	
	private PayableShowChgAction payable;

	@Override
	public boolean beforeDoAction(Action action, ActionEvent e) {
		//����ִ��ǰ
		boolean flag = false;
		if(payable.isBcombinflag
				&& (action instanceof nc.ui.arap.actions.BillApproveAction
						|| action instanceof nc.ui.arap.actions.BillUnApproveAction
						|| action instanceof nc.ui.arap.actions.BillListApproveAction
						|| action instanceof nc.ui.arap.actions.BillListUnApproveAction)){
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

	public PayableShowChgAction getPayable() {
		return payable;
	}

	public void setPayable(PayableShowChgAction payable) {
		this.payable = payable;
	}

}
