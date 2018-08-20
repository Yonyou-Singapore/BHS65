package nc.ui.bhs.build.ace.serviceproxy;

import nc.bs.framework.common.NCLocator;
import nc.itf.bhs.IBuildMaintain;
import nc.ui.pubapp.uif2app.query2.model.IQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;

/**
 * 示例单据的操作代理
 * 
 * @author author
 * @version tempProject version
 */
public class AceBuildMaintainProxy implements IQueryService {
	@Override
	public Object[] queryByQueryScheme(IQueryScheme queryScheme)
			throws Exception {
		IBuildMaintain query = NCLocator.getInstance().lookup(
				IBuildMaintain.class);
		return query.query(queryScheme);
	}

}