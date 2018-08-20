package nc.ui.bhs.sostore.ace.serviceproxy;

import nc.bs.framework.common.NCLocator;
import nc.itf.bhs.ISostoreMaintain;
import nc.ui.pubapp.uif2app.query2.model.IQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;

/**
 * 示例单据的操作代理
 * 
 * @author author
 * @version tempProject version
 */
public class AceSostoreMaintainProxy implements IQueryService {
	@Override
	public Object[] queryByQueryScheme(IQueryScheme queryScheme)
			throws Exception {
		ISostoreMaintain query = NCLocator.getInstance().lookup(
				ISostoreMaintain.class);
		return query.query(queryScheme);
	}

}