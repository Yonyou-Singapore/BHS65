package nc.ui.bhs.pack.ace.serviceproxy;

import nc.bs.framework.common.NCLocator;
import nc.itf.bhs.IPackMaintain;
import nc.ui.pubapp.uif2app.query2.model.IQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;

/**
 * 示例单据的操作代理
 * 
 * @author author
 * @version tempProject version
 */
public class AcePackMaintainProxy implements IQueryService {
	@Override
	public Object[] queryByQueryScheme(IQueryScheme queryScheme)
			throws Exception {
		IPackMaintain query = NCLocator.getInstance().lookup(
				IPackMaintain.class);
		return query.query(queryScheme);
	}

}