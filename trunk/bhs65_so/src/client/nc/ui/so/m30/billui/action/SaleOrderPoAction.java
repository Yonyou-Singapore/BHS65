package nc.ui.so.m30.billui.action;

import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.itf.pubapp.pub.smart.IBillQueryService;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnListProcessor;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.md.persist.framework.MDPersistenceService;
import nc.vo.bhs.bhsquality.AggBhsPoHeadVO;
import nc.vo.bhs.bhsquality.BhsPoHeadVO;
import nc.vo.bhs.build.AggSoOrderBuildHVO;
import nc.vo.bhs.somove.AggSoMoveHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.so.m30.entity.SaleOrderHVO;


/**
 * �򿪰�ȫ������������ 
 * 1�������ǰ�����Ѿ����ڶ�Ӧ�İ�ȫ�����������¼�����ʱ��ʾ��Ӧ�ļ�¼�� 
 * 2�����δ���ڶ�Ӧ�İ�ȫ�����������¼����ʱĬ������״̬��
 * 
 * @author Thinkpad 
 *  2017-07-17
 * 
 */
public class SaleOrderPoAction extends SaleOrderBaseAction {

	private static final long serialVersionUID = -5751011383555490172L;

	public SaleOrderPoAction() {
		// TODO �Զ����ɵĹ��캯�����
		setCode("saleorderPoaction");
		setBtnName("Qualitys");
	}
	
	@Override
	public AbstractBill[] qryBackwardVO(SaleOrderHVO headvo) throws BusinessException {
		// TODO �Զ����ɵķ������
		AggBhsPoHeadVO[] aggvos = null;
		//��ѯ����build������
		String sql = "select billid from bhs_po_h where pono = '"+headvo.getVbillcode()+"' and isnull(dr,0) = 0 ";
		List<Object> list = (List<Object>) NCLocator.getInstance().lookup(IUAPQueryBS.class).executeQuery(sql, new ColumnListProcessor());
		//��� ������������
		if(list == null || list.size() == 0){
			AggBhsPoHeadVO aggvo =  new AggBhsPoHeadVO();
			BhsPoHeadVO vo = new BhsPoHeadVO();
			vo.setPk_group(headvo.getPk_group());
			vo.setPk_org(headvo.getPk_org());
			vo.setPk_org_v(headvo.getPk_org_v());
			vo.setPono(headvo.getVbillcode());
			vo.setStatus(VOStatus.NEW);
			vo.setPdate(headvo.getDbilldate());
			vo.setDbilldate(headvo.getDbilldate());
			vo.setPk_customer(headvo.getCcustomerid());
			vo.setVbillstatus(-1);
			aggvo.setParentVO(vo);
			aggvos = new AggBhsPoHeadVO[1];
			aggvos[0] = aggvo;
		}else{
			IBillQueryService billQuery = (IBillQueryService) NCLocator
					.getInstance()
					.lookup(nc.itf.pubapp.pub.smart.IBillQueryService.class);
			AbstractBill[] bills = billQuery.queryAbstractBillsByPks(
					AggBhsPoHeadVO.class, list.toArray(new String[0]));
			aggvos = new AggBhsPoHeadVO[bills.length];
			for (int i = 0; i < bills.length; i++) {
				aggvos[i] =  new AggBhsPoHeadVO();
				aggvos[i] = (AggBhsPoHeadVO) bills[i];
			}
			
		}
		return aggvos;
	}
	
	
	
	
}
