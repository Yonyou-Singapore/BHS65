package nc.ui.bhs.somove.ace.serviceproxy;

import nc.bs.framework.common.NCLocator;
import nc.itf.bhs.ISomoveMaintain;
import nc.ui.pubapp.uif2app.query2.model.IQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;

/**
 * ʾ�����ݵĲ�������
 * 
 * @author author
 * @version tempProject version
 */
public class AceSomoveMaintainProxy implements IQueryService {
	@Override
	public Object[] queryByQueryScheme(IQueryScheme queryScheme)
			throws Exception {
		ISomoveMaintain query = NCLocator.getInstance().lookup(
				ISomoveMaintain.class);
		return query.query(queryScheme);
	}

}