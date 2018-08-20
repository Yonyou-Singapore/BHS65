package nc.impl.bhs;

import nc.impl.pub.ace.AceBhsqualityPubServiceImpl;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.bhs.bhsquality.AggBhsPoHeadVO;
import nc.itf.bhs.IBhsqualityMaintain;
import nc.vo.pub.BusinessException;

public class BhsqualityMaintainImpl extends AceBhsqualityPubServiceImpl
		implements IBhsqualityMaintain {

	@Override
	public void delete(AggBhsPoHeadVO[] clientFullVOs,
			AggBhsPoHeadVO[] originBills) throws BusinessException {
		super.pubdeleteBills(clientFullVOs, originBills);
	}

	@Override
	public AggBhsPoHeadVO[] insert(AggBhsPoHeadVO[] clientFullVOs,
			AggBhsPoHeadVO[] originBills) throws BusinessException {
		return super.pubinsertBills(clientFullVOs, originBills);
	}

	@Override
	public AggBhsPoHeadVO[] update(AggBhsPoHeadVO[] clientFullVOs,
			AggBhsPoHeadVO[] originBills) throws BusinessException {
		return super.pubupdateBills(clientFullVOs, originBills);
	}

	@Override
	public AggBhsPoHeadVO[] query(IQueryScheme queryScheme)
			throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

	@Override
	public AggBhsPoHeadVO[] save(AggBhsPoHeadVO[] clientFullVOs,
			AggBhsPoHeadVO[] originBills) throws BusinessException {
		return super.pubsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggBhsPoHeadVO[] unsave(AggBhsPoHeadVO[] clientFullVOs,
			AggBhsPoHeadVO[] originBills) throws BusinessException {
		return super.pubunsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggBhsPoHeadVO[] approve(AggBhsPoHeadVO[] clientFullVOs,
			AggBhsPoHeadVO[] originBills) throws BusinessException {
		return super.pubapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggBhsPoHeadVO[] unapprove(AggBhsPoHeadVO[] clientFullVOs,
			AggBhsPoHeadVO[] originBills) throws BusinessException {
		return super.pubunapprovebills(clientFullVOs, originBills);
	}

}
