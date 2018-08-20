package nc.itf.bhs;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.bhs.sostore.AggSoStoreHVO;
import nc.vo.pub.BusinessException;

public interface ISostoreMaintain {

	public void delete(AggSoStoreHVO[] clientFullVOs,
			AggSoStoreHVO[] originBills) throws BusinessException;

	public AggSoStoreHVO[] insert(AggSoStoreHVO[] clientFullVOs,
			AggSoStoreHVO[] originBills) throws BusinessException;

	public AggSoStoreHVO[] update(AggSoStoreHVO[] clientFullVOs,
			AggSoStoreHVO[] originBills) throws BusinessException;

	public AggSoStoreHVO[] query(IQueryScheme queryScheme)
			throws BusinessException;

	public AggSoStoreHVO[] save(AggSoStoreHVO[] clientFullVOs,
			AggSoStoreHVO[] originBills) throws BusinessException;

	public AggSoStoreHVO[] unsave(AggSoStoreHVO[] clientFullVOs,
			AggSoStoreHVO[] originBills) throws BusinessException;

	public AggSoStoreHVO[] approve(AggSoStoreHVO[] clientFullVOs,
			AggSoStoreHVO[] originBills) throws BusinessException;

	public AggSoStoreHVO[] unapprove(AggSoStoreHVO[] clientFullVOs,
			AggSoStoreHVO[] originBills) throws BusinessException;
}
