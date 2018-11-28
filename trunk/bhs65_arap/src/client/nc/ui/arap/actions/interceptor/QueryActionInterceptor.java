package nc.ui.arap.actions.interceptor;

import java.awt.event.ActionEvent;

import javax.swing.Action;

import nc.ui.arap.actions.GatheringShowChgAction;
import nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction;
import nc.ui.uif2.actions.ActionInterceptor;

/***
 * �տ�����ѯ��ť����
 * @author Administrator
 *
 */
public class QueryActionInterceptor  implements ActionInterceptor {
	
	private GatheringShowChgAction gathering;
	private DefaultQueryAction queryAction;

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

	public GatheringShowChgAction getGathering() {
		return gathering;
	}

	public void setGathering(GatheringShowChgAction gathering) {
		this.gathering = gathering;
	}
	
	public DefaultQueryAction getQueryAction() {
		return queryAction;
	}

	public void setQueryAction(DefaultQueryAction queryAction) {
		this.queryAction = queryAction;
		this.queryAction.setShowProgress(false);
	}

}
