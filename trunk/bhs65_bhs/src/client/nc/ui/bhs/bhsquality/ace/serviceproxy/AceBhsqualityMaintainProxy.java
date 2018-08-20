package nc.ui.bhs.bhsquality.ace.serviceproxy;

import nc.bs.framework.common.NCLocator;
import nc.itf.bhs.IBhsqualityMaintain;
import nc.ui.pubapp.uif2app.query2.model.IQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;

/**
 * 示例单据的操作代理
 * 
 * @author author
 * @version tempProject version
 */
public class AceBhsqualityMaintainProxy implements IQueryService {
	@Override
	public Object[] queryByQueryScheme(IQueryScheme queryScheme)
			throws Exception {
		IBhsqualityMaintain query = NCLocator.getInstance().lookup(
				IBhsqualityMaintain.class);
		return query.query(queryScheme);
	}

}