package nc.ui.arap.actions.interceptor;

import java.awt.event.ActionEvent;

import javax.swing.Action;

import nc.ui.arap.actions.GatherEntryShowChgAction;
import nc.ui.uif2.actions.ActionInterceptor;

/***
 * �տ����ɾ����ť����
 * @author Administrator
 *
 */
public class EntryDeleteActionInterceptor implements ActionInterceptor {
	
	private GatherEntryShowChgAction gathering;

	@Override
	public boolean beforeDoAction(Action action, ActionEvent e) {
		//����ִ��ǰ
		boolean flag = false;
		if(gathering.isBcombinflag
				&& action instanceof nc.ui.arap.actions.CreditCheckDecoratorAction){
			try {
				gathering.doAction(e);
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

	public GatherEntryShowChgAction getGathering() {
		return gathering;
	}

	public void setGathering(GatherEntryShowChgAction gathering) {
		this.gathering = gathering;
	}

}
