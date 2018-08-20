package nc.ui.bhs.sostore.ace.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnListProcessor;
import nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction;
import nc.ui.pubapp.uif2app.model.BatchBillTableModel;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.model.HierachicalDataAppModel;
import nc.vo.bhs.somove.AggSoMoveHVO;
import nc.vo.bhs.somove.SoMoveOrderVO;
import nc.vo.bhs.sostore.AggSoStoreHVO;
import nc.vo.bhs.sostore.SoStoreOrderVO;
import nc.vo.pub.BusinessException;
import nc.vo.trade.checkrule.VOChecker;
/**
 * 重新打印按钮，处理取销售订单明细数据
 * @author Thinkpad
 * 2018-03-05
 */
public class PrintAction extends MetaDataBasedPrintAction{

	private static final long serialVersionUID = -3462182698174257675L;

	@Override
	public Object[] getDatas() {
		// TODO 自动生成的方法存根
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
			String csaleorderid = ((AggSoStoreHVO)datas[i]).getParentVO().getCsaleorderid();
			String sql = "select csaleorderbid from so_saleorder_b where isnull(dr,0) = 0 and csaleorderid = '"+csaleorderid+"'";
			List<String> orderbidList = new ArrayList<String>();
			try {
				orderbidList = (List<String>) NCLocator.getInstance().lookup(IUAPQueryBS.class).executeQuery(sql, new ColumnListProcessor());
			} catch (BusinessException e) {
				// TODO 自动生成的 catch 块
			}
			if(orderbidList == null || orderbidList.size() == 0){
				continue;
			}
			List<SoStoreOrderVO> moveorderList = new ArrayList<SoStoreOrderVO>();
			for (Iterator iterator = orderbidList.iterator(); iterator
					.hasNext();) {
				String string = (String) iterator.next();
				SoStoreOrderVO vo = new SoStoreOrderVO();
				vo.setCsaleorderbid(string);
				moveorderList.add(vo);
			}
			((AggSoStoreHVO)datas[i]).setChildren(SoStoreOrderVO.class, moveorderList.toArray(new SoStoreOrderVO[0]));
			
	      }
	    return datas;
	}
}
