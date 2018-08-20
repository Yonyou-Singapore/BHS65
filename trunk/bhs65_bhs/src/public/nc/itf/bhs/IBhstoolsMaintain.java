package nc.itf.bhs;

import com.ufida.dataset.IContext;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.bhs.bhstools.AggBhsToolsHeadVO;
import nc.vo.pub.BusinessException;

public interface IBhstoolsMaintain {

	public void delete(AggBhsToolsHeadVO[] clientFullVOs,
			AggBhsToolsHeadVO[] originBills) throws BusinessException;

	public AggBhsToolsHeadVO[] insert(AggBhsToolsHeadVO[] clientFullVOs,
			AggBhsToolsHeadVO[] originBills) throws BusinessException;

	public AggBhsToolsHeadVO[] update(AggBhsToolsHeadVO[] clientFullVOs,
			AggBhsToolsHeadVO[] originBills) throws BusinessException;

	public AggBhsToolsHeadVO[] query(IQueryScheme queryScheme)
			throws BusinessException;

	public AggBhsToolsHeadVO[] save(AggBhsToolsHeadVO[] clientFullVOs,
			AggBhsToolsHeadVO[] originBills) throws BusinessException;

	public AggBhsToolsHeadVO[] unsave(AggBhsToolsHeadVO[] clientFullVOs,
			AggBhsToolsHeadVO[] originBills) throws BusinessException;

	public AggBhsToolsHeadVO[] approve(AggBhsToolsHeadVO[] clientFullVOs,
			AggBhsToolsHeadVO[] originBills) throws BusinessException;

	public AggBhsToolsHeadVO[] unapprove(AggBhsToolsHeadVO[] clientFullVOs,
			AggBhsToolsHeadVO[] originBills) throws BusinessException;
	
	/**
	 * 工具报表
	 * @author Thinkpad
	 * @date 2017-7-17
	 */
	public String getToolReportSql(IContext context) throws BusinessException;
	
	
	
}
