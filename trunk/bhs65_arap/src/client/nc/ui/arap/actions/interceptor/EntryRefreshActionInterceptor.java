package nc.ui.arap.actions.interceptor;

import java.awt.event.ActionEvent;

import javax.swing.Action;

import nc.ui.arap.actions.GatherEntryShowChgAction;
import nc.ui.uif2.actions.ActionInterceptor;

/***
 * �տ¼��ˢ�°�ť����
 * @author Administrator
 *
 */
public class EntryRefreshActionInterceptor   implements ActionInterceptor {
	
	private GatherEntryShowChgAction gathering;

	@Override
	public boolean beforeDoAction(Action action, ActionEvent e) {
		return true;
	}

	@Override
	public void afterDoActionSuccessed(Action action, ActionEvent e) {
		//����ִ�гɹ���
		if(gathering.isBcombinflag){
			gathering.initSummary();
		}
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