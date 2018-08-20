package nc.ui.bhs.sotruck.ace.serviceproxy;

import nc.bs.framework.common.NCLocator;
import nc.itf.bhs.ISotruckMaintain;
import nc.ui.pubapp.uif2app.query2.model.IQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;

/**
 * 示例单据的操作代理
 * 
 * @author author
 * @version tempProject version
 */
public class AceSotruckMaintainProxy implements IQueryService {
	@Override
	public Object[] queryByQueryScheme(IQueryScheme queryScheme)
			throws Exception {
		ISotruckMaintain query = NCLocator.getInstance().lookup(
				ISotruckMaintain.class);
		return query.query(queryScheme);
	}

}