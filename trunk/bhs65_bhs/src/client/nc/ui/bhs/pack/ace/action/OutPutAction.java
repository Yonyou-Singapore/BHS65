package nc.ui.bhs.pack.ace.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnListProcessor;
import nc.ui.pubapp.uif2app.actions.OutputAction;
import nc.ui.pubapp.uif2app.model.BatchBillTableModel;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.model.HierachicalDataAppModel;
import nc.vo.bhs.pack.AggSoOrderPackVO;
import nc.vo.bhs.pack.SoPackOrderVO;
import nc.vo.pub.BusinessException;
import nc.vo.trade.checkrule.VOChecker;
/**
 * ���������ť������ȡ���۶�����ϸ����
 * @author Thinkpad
 * 2018-03-05
 *
 */
public class OutPutAction extends OutputAction{

	private static final long serialVersionUID = 6981972226948649702L;

	@Override
	public Object[] getDatas() {
		// TODO �Զ����ɵķ������
		Object[] datas = null;
	    if (this.getModel() instanceof BillManageModel) {
	      if (this.isPrintAll()) {
	        datas = ((BillManageModel) this.getModel()).getData().toArray(new Object[0]);
	      } else {
	        datas = ((BillManageModel) this.getModel()).getSelectedOperaDatas();
	      }
	      if (VOChecker.isEmpty(datas)) {
	        datas = new Object[] {this.getModel().getSelectedData()
	        };
	      }
	    }
	    else if (this.getModel() instanceof HierachicalDataAppModel) {
	      if (this.isPrintAll()) {
	        datas = ((HierachicalDataAppModel) this.getModel()).getAllDatas();
	      } else {
	        datas = ((HierachicalDataAppModel) this.getModel()).getSelectedDatas();
	      }
	    }
	    else {
	      if (this.isPrintAll()) {
	        datas = ((BatchBillTableModel) this.getModel()).getRows().toArray(new Object[0]);
	      } else {
	        datas = ((BatchBillTableModel) this.getModel()).getSelectedOperaDatas();
	      }
	    }
	    if (this.getDataSplit() != null) {
	      datas = this.getDataSplit().splitData(datas);
	    }
	    for (int i = 0; i < datas.length; i++) {
			String csaleorderid = ((AggSoOrderPackVO)datas[i]).getParentVO().getCsaleorderid();
			String sql = "select csaleorderbid from so_saleorder_b where isnull(dr,0) = 0 and csaleorderid = '"+csaleorderid+"'";
			List<String> orderbidList = new ArrayList<String>();
			try {
				orderbidList = (List<String>) NCLocator.getInstance().lookup(IUAPQueryBS.class).executeQuery(sql, new ColumnListProcessor());
			} catch (BusinessException e) {
				// TODO �Զ����ɵ� catch ��
			}
			if(orderbidList == null || orderbidList.size() == 0){
				continue;
			}
			List<SoPackOrderVO> moveorderList = new ArrayList<SoPackOrderVO>();
			for (Iterator iterator = orderbidList.iterator(); iterator
					.hasNext();) {
				String string = (String) iterator.next();
				SoPackOrderVO vo = new SoPackOrderVO();
				vo.setCsaleorderbid(string);
				moveorderList.add(vo);
			}
			((AggSoOrderPackVO)datas[i]).setChildren(SoPackOrderVO.class, moveorderList.toArray(new SoPackOrderVO[0]));
			
	      }
	    return datas;
	}
}
