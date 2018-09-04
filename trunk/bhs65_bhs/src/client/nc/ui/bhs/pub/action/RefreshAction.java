package nc.ui.bhs.pub.action;

import nc.ui.pubapp.uif2app.query2.action.DefaultRefreshAction;

public class RefreshAction extends DefaultRefreshAction {

	@Override
	protected boolean isActionEnable() {
		boolean refreshable = true;
		return refreshable;
	}
}
