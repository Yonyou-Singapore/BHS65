package nc.ui.so.m30.billui.action;

import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.itf.pubapp.pub.smart.IBillQueryService;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnListProcessor;
import nc.md.persist.framework.MDPersistenceService;
import nc.vo.bhs.bhstools.AggBhsToolsHeadVO;
import nc.vo.bhs.bhstools.BhsToolsHeadVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.so.m30.entity.SaleOrderHVO;


/**
 * ��tools���� 
 * 1�������ǰ�����Ѿ����ڶ�Ӧ��tools��¼�����ʱ��ʾ��Ӧ�ļ�¼�� 
 * 2�����δ���ڶ�Ӧ��tools��¼����ʱĬ������״̬��
 * 
 * @author Thinkpad 
 *  2017-04-19
 * 
 */
public class SaleOrderToolsAction extends SaleOrderBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5751011383555490172L;

	public SaleOrderToolsAction() {
		// TODO �Զ����ɵĹ��캯�����
		setCode("saleordertoolaction");
		setBtnName("Tools");
	}
	
	@Override
	public AbstractBill[] qryBackwardVO(SaleOrderHVO headvo) throws BusinessException {
		// TODO �Զ����ɵķ������
		AggBhsToolsHeadVO[] aggvos = null;
		//��ѯ����build������
		String sql = "select billid from bhs_bhstools_h where pk_saleorder = '"+headvo.getCsaleorderid()+"' and isnull(dr,0) = 0 ";
		List<Object> list = (List<Object>) NCLocator.getInstance().lookup(IUAPQueryBS.class).executeQuery(sql, new ColumnListProcessor());
		//��� ������������
		if(list == null || list.size() == 0){
			AggBhsToolsHeadVO aggvo =  new AggBhsToolsHeadVO();
			BhsToolsHeadVO vo = new BhsToolsHeadVO();
			vo.setPk_group(headvo.getPk_group());
			vo.setPk_org(headvo.getPk_org());
			vo.setPk_org_v(headvo.getPk_org_v());
			vo.setPk_saleorder(headvo.getCsaleorderid());
			vo.setStatus(VOStatus.NEW);
			vo.setSaleorderbillno(headvo.getVbillcode());
			vo.setDbilldate(headvo.getDbilldate());
			vo.setVbillstatus(-1);
			aggvo.setParentVO(vo);
			aggvos = new AggBhsToolsHeadVO[1];
			aggvos[0] = aggvo;
		}else{
			IBillQueryService billQuery = (IBillQueryService) NCLocator
					.getInstance()
					.lookup(nc.itf.pubapp.pub.smart.IBillQueryService.class);
			AbstractBill[] bills = billQuery.queryAbstractBillsByPks(
					AggBhsToolsHeadVO.class, list.toArray(new String[0]));
			aggvos = new AggBhsToolsHeadVO[bills.length];
			for (int i = 0; i < bills.length; i++) {
				aggvos[i] =  new AggBhsToolsHeadVO();
				aggvos[i] = (AggBhsToolsHeadVO) bills[i];
			}
		}
		return aggvos;
	}
	
	
	
	
}
