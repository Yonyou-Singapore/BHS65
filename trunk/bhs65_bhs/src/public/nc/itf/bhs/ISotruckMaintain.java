package nc.itf.bhs;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.bhs.sotruck.AggSoTruckHVO;
import nc.vo.pub.BusinessException;

public interface ISotruckMaintain {

	public void delete(AggSoTruckHVO[] clientFullVOs,
			AggSoTruckHVO[] originBills) throws BusinessException;

	public AggSoTruckHVO[] insert(AggSoTruckHVO[] clientFullVOs,
			AggSoTruckHVO[] originBills) throws BusinessException;

	public AggSoTruckHVO[] update(AggSoTruckHVO[] clientFullVOs,
			AggSoTruckHVO[] originBills) throws BusinessException;

	public AggSoTruckHVO[] query(IQueryScheme queryScheme)
			throws BusinessException;

	public AggSoTruckHVO[] save(AggSoTruckHVO[] clientFullVOs,
			AggSoTruckHVO[] originBills) throws BusinessException;

	public AggSoTruckHVO[] unsave(AggSoTruckHVO[] clientFullVOs,
			AggSoTruckHVO[] originBills) throws BusinessException;

	public AggSoTruckHVO[] approve(AggSoTruckHVO[] clientFullVOs,
			AggSoTruckHVO[] originBills) throws BusinessException;

	public AggSoTruckHVO[] unapprove(AggSoTruckHVO[] clientFullVOs,
			AggSoTruckHVO[] originBills) throws BusinessException;
}
