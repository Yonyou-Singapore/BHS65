package nc.ui.arap.actions.interceptor;

import java.awt.event.ActionEvent;

import javax.swing.Action;

import nc.ui.arap.actions.GatheringShowChgAction;
import nc.ui.uif2.actions.ActionInterceptor;

/***
 * �տ�����޸İ�ť����
 * @author Administrator
 *
 */
public class EditActionInterceptor implements ActionInterceptor {
	
	private GatheringShowChgAction gathering;

	@Override
	public boolean beforeDoAction(Action action, ActionEvent e) {
		//����ִ��ǰ
		boolean flag = false;
		if(gathering.isBcombinflag
				&& action instanceof nc.ui.arap.actions.BillEditAction){
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

	public GatheringShowChgAction getGathering() {
		return gathering;
	}

	public void setGathering(GatheringShowChgAction gathering) {
		this.gathering = gathering;
	}

}
