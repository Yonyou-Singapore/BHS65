package nc.itf.bhs;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.bhs.bhsquality.AggBhsPoHeadVO;
import nc.vo.pub.BusinessException;

public interface IBhsqualityMaintain {

	public void delete(AggBhsPoHeadVO[] clientFullVOs,
			AggBhsPoHeadVO[] originBills) throws BusinessException;

	public AggBhsPoHeadVO[] insert(AggBhsPoHeadVO[] clientFullVOs,
			AggBhsPoHeadVO[] originBills) throws BusinessException;

	public AggBhsPoHeadVO[] update(AggBhsPoHeadVO[] clientFullVOs,
			AggBhsPoHeadVO[] originBills) throws BusinessException;

	public AggBhsPoHeadVO[] query(IQueryScheme queryScheme)
			throws BusinessException;

	public AggBhsPoHeadVO[] save(AggBhsPoHeadVO[] clientFullVOs,
			AggBhsPoHeadVO[] originBills) throws BusinessException;

	public AggBhsPoHeadVO[] unsave(AggBhsPoHeadVO[] clientFullVOs,
			AggBhsPoHeadVO[] originBills) throws BusinessException;

	public AggBhsPoHeadVO[] approve(AggBhsPoHeadVO[] clientFullVOs,
			AggBhsPoHeadVO[] originBills) throws BusinessException;

	public AggBhsPoHeadVO[] unapprove(AggBhsPoHeadVO[] clientFullVOs,
			AggBhsPoHeadVO[] originBills) throws BusinessException;
}
