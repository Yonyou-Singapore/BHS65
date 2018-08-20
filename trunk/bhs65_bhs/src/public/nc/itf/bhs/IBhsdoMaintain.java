package nc.itf.bhs;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.bhs.bhsdo.AggBhsDoVO;
import nc.vo.pub.BusinessException;

public interface IBhsdoMaintain {

	public void delete(AggBhsDoVO[] clientFullVOs,
			AggBhsDoVO[] originBills) throws BusinessException;

	public AggBhsDoVO[] insert(AggBhsDoVO[] clientFullVOs,
			AggBhsDoVO[] originBills) throws BusinessException;

	public AggBhsDoVO[] update(AggBhsDoVO[] clientFullVOs,
			AggBhsDoVO[] originBills) throws BusinessException;

	public AggBhsDoVO[] query(IQueryScheme queryScheme)
			throws BusinessException;

	public AggBhsDoVO[] save(AggBhsDoVO[] clientFullVOs,
			AggBhsDoVO[] originBills) throws BusinessException;

	public AggBhsDoVO[] unsave(AggBhsDoVO[] clientFullVOs,
			AggBhsDoVO[] originBills) throws BusinessException;

	public AggBhsDoVO[] approve(AggBhsDoVO[] clientFullVOs,
			AggBhsDoVO[] originBills) throws BusinessException;

	public AggBhsDoVO[] unapprove(AggBhsDoVO[] clientFullVOs,
			AggBhsDoVO[] originBills) throws BusinessException;
}
