package nc.impl.bhs;

import nc.impl.pub.ace.AceSotruckPubServiceImpl;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.bhs.sotruck.AggSoTruckHVO;
import nc.itf.bhs.ISotruckMaintain;
import nc.vo.pub.BusinessException;

public class SotruckMaintainImpl extends AceSotruckPubServiceImpl
		implements ISotruckMaintain {

	@Override
	public void delete(AggSoTruckHVO[] clientFullVOs,
			AggSoTruckHVO[] originBills) throws BusinessException {
		super.pubdeleteBills(clientFullVOs, originBills);
	}

	@Override
	public AggSoTruckHVO[] insert(AggSoTruckHVO[] clientFullVOs,
			AggSoTruckHVO[] originBills) throws BusinessException {
		return super.pubinsertBills(clientFullVOs, originBills);
	}

	@Override
	public AggSoTruckHVO[] update(AggSoTruckHVO[] clientFullVOs,
			AggSoTruckHVO[] originBills) throws BusinessException {
		return super.pubupdateBills(clientFullVOs, originBills);
	}

	@Override
	public AggSoTruckHVO[] query(IQueryScheme queryScheme)
			throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

	@Override
	public AggSoTruckHVO[] save(AggSoTruckHVO[] clientFullVOs,
			AggSoTruckHVO[] originBills) throws BusinessException {
		return super.pubsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggSoTruckHVO[] unsave(AggSoTruckHVO[] clientFullVOs,
			AggSoTruckHVO[] originBills) throws BusinessException {
		return super.pubunsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggSoTruckHVO[] approve(AggSoTruckHVO[] clientFullVOs,
			AggSoTruckHVO[] originBills) throws BusinessException {
		return super.pubapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggSoTruckHVO[] unapprove(AggSoTruckHVO[] clientFullVOs,
			AggSoTruckHVO[] originBills) throws BusinessException {
		return super.pubunapprovebills(clientFullVOs, originBills);
	}

}
