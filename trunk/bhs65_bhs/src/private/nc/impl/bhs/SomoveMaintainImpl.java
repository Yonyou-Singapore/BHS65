package nc.impl.bhs;

import nc.impl.pub.ace.AceSomovePubServiceImpl;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.bhs.somove.AggSoMoveHVO;
import nc.itf.bhs.ISomoveMaintain;
import nc.vo.pub.BusinessException;

public class SomoveMaintainImpl extends AceSomovePubServiceImpl
		implements ISomoveMaintain {

	@Override
	public void delete(AggSoMoveHVO[] clientFullVOs,
			AggSoMoveHVO[] originBills) throws BusinessException {
		super.pubdeleteBills(clientFullVOs, originBills);
	}

	@Override
	public AggSoMoveHVO[] insert(AggSoMoveHVO[] clientFullVOs,
			AggSoMoveHVO[] originBills) throws BusinessException {
		return super.pubinsertBills(clientFullVOs, originBills);
	}

	@Override
	public AggSoMoveHVO[] update(AggSoMoveHVO[] clientFullVOs,
			AggSoMoveHVO[] originBills) throws BusinessException {
		return super.pubupdateBills(clientFullVOs, originBills);
	}

	@Override
	public AggSoMoveHVO[] query(IQueryScheme queryScheme)
			throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

	@Override
	public AggSoMoveHVO[] save(AggSoMoveHVO[] clientFullVOs,
			AggSoMoveHVO[] originBills) throws BusinessException {
		return super.pubsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggSoMoveHVO[] unsave(AggSoMoveHVO[] clientFullVOs,
			AggSoMoveHVO[] originBills) throws BusinessException {
		return super.pubunsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggSoMoveHVO[] approve(AggSoMoveHVO[] clientFullVOs,
			AggSoMoveHVO[] originBills) throws BusinessException {
		return super.pubapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggSoMoveHVO[] unapprove(AggSoMoveHVO[] clientFullVOs,
			AggSoMoveHVO[] originBills) throws BusinessException {
		return super.pubunapprovebills(clientFullVOs, originBills);
	}

}
