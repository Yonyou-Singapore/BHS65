package nc.ui.bhs.bhstools.ace.serviceproxy;

import nc.bs.framework.common.NCLocator;
import nc.itf.bhs.IBhstoolsMaintain;
import nc.ui.pubapp.uif2app.query2.model.IQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;

/**
 * 示例单据的操作代理
 * 
 * @author author
 * @version tempProject version
 */
public class AceBhstoolsMaintainProxy implements IQueryService {
	@Override
	public Object[] queryByQueryScheme(IQueryScheme queryScheme)
			throws Exception {
		IBhstoolsMaintain query = NCLocator.getInstance().lookup(
				IBhstoolsMaintain.class);
		return query.query(queryScheme);
	}

}