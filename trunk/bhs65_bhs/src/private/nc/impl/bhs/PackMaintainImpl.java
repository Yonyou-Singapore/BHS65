package nc.impl.bhs;

import nc.impl.pub.ace.AcePackPubServiceImpl;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.bhs.pack.AggSoOrderPackVO;
import nc.itf.bhs.IPackMaintain;
import nc.vo.pub.BusinessException;

public class PackMaintainImpl extends AcePackPubServiceImpl
		implements IPackMaintain {

	@Override
	public void delete(AggSoOrderPackVO[] clientFullVOs,
			AggSoOrderPackVO[] originBills) throws BusinessException {
		super.pubdeleteBills(clientFullVOs, originBills);
	}

	@Override
	public AggSoOrderPackVO[] insert(AggSoOrderPackVO[] clientFullVOs,
			AggSoOrderPackVO[] originBills) throws BusinessException {
		return super.pubinsertBills(clientFullVOs, originBills);
	}

	@Override
	public AggSoOrderPackVO[] update(AggSoOrderPackVO[] clientFullVOs,
			AggSoOrderPackVO[] originBills) throws BusinessException {
		return super.pubupdateBills(clientFullVOs, originBills);
	}

	@Override
	public AggSoOrderPackVO[] query(IQueryScheme queryScheme)
			throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

	@Override
	public AggSoOrderPackVO[] save(AggSoOrderPackVO[] clientFullVOs,
			AggSoOrderPackVO[] originBills) throws BusinessException {
		return super.pubsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggSoOrderPackVO[] unsave(AggSoOrderPackVO[] clientFullVOs,
			AggSoOrderPackVO[] originBills) throws BusinessException {
		return super.pubunsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggSoOrderPackVO[] approve(AggSoOrderPackVO[] clientFullVOs,
			AggSoOrderPackVO[] originBills) throws BusinessException {
		return super.pubapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggSoOrderPackVO[] unapprove(AggSoOrderPackVO[] clientFullVOs,
			AggSoOrderPackVO[] originBills) throws BusinessException {
		return super.pubunapprovebills(clientFullVOs, originBills);
	}

}
