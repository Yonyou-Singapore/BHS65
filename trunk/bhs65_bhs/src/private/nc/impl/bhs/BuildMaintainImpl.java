package nc.impl.bhs;

import nc.impl.pub.ace.AceBuildPubServiceImpl;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.bhs.build.AggSoOrderBuildHVO;
import nc.itf.bhs.IBuildMaintain;
import nc.vo.pub.BusinessException;

public class BuildMaintainImpl extends AceBuildPubServiceImpl
		implements IBuildMaintain {

	@Override
	public void delete(AggSoOrderBuildHVO[] clientFullVOs,
			AggSoOrderBuildHVO[] originBills) throws BusinessException {
		super.pubdeleteBills(clientFullVOs, originBills);
	}

	@Override
	public AggSoOrderBuildHVO[] insert(AggSoOrderBuildHVO[] clientFullVOs,
			AggSoOrderBuildHVO[] originBills) throws BusinessException {
		return super.pubinsertBills(clientFullVOs, originBills);
	}

	@Override
	public AggSoOrderBuildHVO[] update(AggSoOrderBuildHVO[] clientFullVOs,
			AggSoOrderBuildHVO[] originBills) throws BusinessException {
		return super.pubupdateBills(clientFullVOs, originBills);
	}

	@Override
	public AggSoOrderBuildHVO[] query(IQueryScheme queryScheme)
			throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

	@Override
	public AggSoOrderBuildHVO[] save(AggSoOrderBuildHVO[] clientFullVOs,
			AggSoOrderBuildHVO[] originBills) throws BusinessException {
		return super.pubsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggSoOrderBuildHVO[] unsave(AggSoOrderBuildHVO[] clientFullVOs,
			AggSoOrderBuildHVO[] originBills) throws BusinessException {
		return super.pubunsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggSoOrderBuildHVO[] approve(AggSoOrderBuildHVO[] clientFullVOs,
			AggSoOrderBuildHVO[] originBills) throws BusinessException {
		return super.pubapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggSoOrderBuildHVO[] unapprove(AggSoOrderBuildHVO[] clientFullVOs,
			AggSoOrderBuildHVO[] originBills) throws BusinessException {
		return super.pubunapprovebills(clientFullVOs, originBills);
	}

}
