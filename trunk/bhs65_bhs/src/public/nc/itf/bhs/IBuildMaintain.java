package nc.itf.bhs;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.bhs.build.AggSoOrderBuildHVO;
import nc.vo.pub.BusinessException;

public interface IBuildMaintain {

	public void delete(AggSoOrderBuildHVO[] clientFullVOs,
			AggSoOrderBuildHVO[] originBills) throws BusinessException;

	public AggSoOrderBuildHVO[] insert(AggSoOrderBuildHVO[] clientFullVOs,
			AggSoOrderBuildHVO[] originBills) throws BusinessException;

	public AggSoOrderBuildHVO[] update(AggSoOrderBuildHVO[] clientFullVOs,
			AggSoOrderBuildHVO[] originBills) throws BusinessException;

	public AggSoOrderBuildHVO[] query(IQueryScheme queryScheme)
			throws BusinessException;

	public AggSoOrderBuildHVO[] save(AggSoOrderBuildHVO[] clientFullVOs,
			AggSoOrderBuildHVO[] originBills) throws BusinessException;

	public AggSoOrderBuildHVO[] unsave(AggSoOrderBuildHVO[] clientFullVOs,
			AggSoOrderBuildHVO[] originBills) throws BusinessException;

	public AggSoOrderBuildHVO[] approve(AggSoOrderBuildHVO[] clientFullVOs,
			AggSoOrderBuildHVO[] originBills) throws BusinessException;

	public AggSoOrderBuildHVO[] unapprove(AggSoOrderBuildHVO[] clientFullVOs,
			AggSoOrderBuildHVO[] originBills) throws BusinessException;
}
