package nc.ui.bhs.bhsdo.ace.serviceproxy;

import nc.bs.framework.common.NCLocator;
import nc.itf.bhs.IBhsdoMaintain;
import nc.ui.pubapp.uif2app.query2.model.IQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;

/**
 * 示例单据的操作代理
 * 
 * @author author
 * @version tempProject version
 */
public class AceBhsdoMaintainProxy implements IQueryService {
	@Override
	public Object[] queryByQueryScheme(IQueryScheme queryScheme)
			throws Exception {
		IBhsdoMaintain query = NCLocator.getInstance().lookup(
				IBhsdoMaintain.class);
		return query.query(queryScheme);
	}

}