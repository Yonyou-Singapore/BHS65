package nc.impl.bhs;

import nc.impl.pub.ace.AceSostorePubServiceImpl;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.bhs.sostore.AggSoStoreHVO;
import nc.itf.bhs.ISostoreMaintain;
import nc.vo.pub.BusinessException;

public class SostoreMaintainImpl extends AceSostorePubServiceImpl
		implements ISostoreMaintain {

	@Override
	public void delete(AggSoStoreHVO[] clientFullVOs,
			AggSoStoreHVO[] originBills) throws BusinessException {
		super.pubdeleteBills(clientFullVOs, originBills);
	}

	@Override
	public AggSoStoreHVO[] insert(AggSoStoreHVO[] clientFullVOs,
			AggSoStoreHVO[] originBills) throws BusinessException {
		return super.pubinsertBills(clientFullVOs, originBills);
	}

	@Override
	public AggSoStoreHVO[] update(AggSoStoreHVO[] clientFullVOs,
			AggSoStoreHVO[] originBills) throws BusinessException {
		return super.pubupdateBills(clientFullVOs, originBills);
	}

	@Override
	public AggSoStoreHVO[] query(IQueryScheme queryScheme)
			throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

	@Override
	public AggSoStoreHVO[] save(AggSoStoreHVO[] clientFullVOs,
			AggSoStoreHVO[] originBills) throws BusinessException {
		return super.pubsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggSoStoreHVO[] unsave(AggSoStoreHVO[] clientFullVOs,
			AggSoStoreHVO[] originBills) throws BusinessException {
		return super.pubunsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggSoStoreHVO[] approve(AggSoStoreHVO[] clientFullVOs,
			AggSoStoreHVO[] originBills) throws BusinessException {
		return super.pubapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggSoStoreHVO[] unapprove(AggSoStoreHVO[] clientFullVOs,
			AggSoStoreHVO[] originBills) throws BusinessException {
		return super.pubunapprovebills(clientFullVOs, originBills);
	}

}
