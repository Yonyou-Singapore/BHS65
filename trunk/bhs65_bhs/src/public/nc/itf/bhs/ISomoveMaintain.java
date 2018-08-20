package nc.itf.bhs;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.bhs.somove.AggSoMoveHVO;
import nc.vo.pub.BusinessException;

public interface ISomoveMaintain {

	public void delete(AggSoMoveHVO[] clientFullVOs,
			AggSoMoveHVO[] originBills) throws BusinessException;

	public AggSoMoveHVO[] insert(AggSoMoveHVO[] clientFullVOs,
			AggSoMoveHVO[] originBills) throws BusinessException;

	public AggSoMoveHVO[] update(AggSoMoveHVO[] clientFullVOs,
			AggSoMoveHVO[] originBills) throws BusinessException;

	public AggSoMoveHVO[] query(IQueryScheme queryScheme)
			throws BusinessException;

	public AggSoMoveHVO[] save(AggSoMoveHVO[] clientFullVOs,
			AggSoMoveHVO[] originBills) throws BusinessException;

	public AggSoMoveHVO[] unsave(AggSoMoveHVO[] clientFullVOs,
			AggSoMoveHVO[] originBills) throws BusinessException;

	public AggSoMoveHVO[] approve(AggSoMoveHVO[] clientFullVOs,
			AggSoMoveHVO[] originBills) throws BusinessException;

	public AggSoMoveHVO[] unapprove(AggSoMoveHVO[] clientFullVOs,
			AggSoMoveHVO[] originBills) throws BusinessException;
}
