package nc.itf.bhs;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.bhs.pack.AggSoOrderPackVO;
import nc.vo.pub.BusinessException;

public interface IPackMaintain {

	public void delete(AggSoOrderPackVO[] clientFullVOs,
			AggSoOrderPackVO[] originBills) throws BusinessException;

	public AggSoOrderPackVO[] insert(AggSoOrderPackVO[] clientFullVOs,
			AggSoOrderPackVO[] originBills) throws BusinessException;

	public AggSoOrderPackVO[] update(AggSoOrderPackVO[] clientFullVOs,
			AggSoOrderPackVO[] originBills) throws BusinessException;

	public AggSoOrderPackVO[] query(IQueryScheme queryScheme)
			throws BusinessException;

	public AggSoOrderPackVO[] save(AggSoOrderPackVO[] clientFullVOs,
			AggSoOrderPackVO[] originBills) throws BusinessException;

	public AggSoOrderPackVO[] unsave(AggSoOrderPackVO[] clientFullVOs,
			AggSoOrderPackVO[] originBills) throws BusinessException;

	public AggSoOrderPackVO[] approve(AggSoOrderPackVO[] clientFullVOs,
			AggSoOrderPackVO[] originBills) throws BusinessException;

	public AggSoOrderPackVO[] unapprove(AggSoOrderPackVO[] clientFullVOs,
			AggSoOrderPackVO[] originBills) throws BusinessException;
}
