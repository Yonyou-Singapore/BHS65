package nc.impl.bhs;

import nc.impl.pub.ace.AceBhsdoPubServiceImpl;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.bhs.bhsdo.AggBhsDoVO;
import nc.itf.bhs.IBhsdoMaintain;
import nc.vo.pub.BusinessException;

public class BhsdoMaintainImpl extends AceBhsdoPubServiceImpl
		implements IBhsdoMaintain {

	@Override
	public void delete(AggBhsDoVO[] clientFullVOs,
			AggBhsDoVO[] originBills) throws BusinessException {
		super.pubdeleteBills(clientFullVOs, originBills);
	}

	@Override
	public AggBhsDoVO[] insert(AggBhsDoVO[] clientFullVOs,
			AggBhsDoVO[] originBills) throws BusinessException {
		return super.pubinsertBills(clientFullVOs, originBills);
	}

	@Override
	public AggBhsDoVO[] update(AggBhsDoVO[] clientFullVOs,
			AggBhsDoVO[] originBills) throws BusinessException {
		return super.pubupdateBills(clientFullVOs, originBills);
	}

	@Override
	public AggBhsDoVO[] query(IQueryScheme queryScheme)
			throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

	@Override
	public AggBhsDoVO[] save(AggBhsDoVO[] clientFullVOs,
			AggBhsDoVO[] originBills) throws BusinessException {
		return super.pubsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggBhsDoVO[] unsave(AggBhsDoVO[] clientFullVOs,
			AggBhsDoVO[] originBills) throws BusinessException {
		return super.pubunsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggBhsDoVO[] approve(AggBhsDoVO[] clientFullVOs,
			AggBhsDoVO[] originBills) throws BusinessException {
		return super.pubapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggBhsDoVO[] unapprove(AggBhsDoVO[] clientFullVOs,
			AggBhsDoVO[] originBills) throws BusinessException {
		return super.pubunapprovebills(clientFullVOs, originBills);
	}

}
