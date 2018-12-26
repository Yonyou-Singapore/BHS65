package nc.ui.so.salequotation.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction.IBeforePrintDataProcess;
import nc.ui.so.salequotation.model.SalequoModel;
import nc.ui.so.salequotation.scale.SalequoScaleProcessor;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.salequotation.entity.AggSalequotationHVO;
import nc.vo.so.salequotation.entity.SalequotationBVO;
import nc.vo.so.salequotation.entity.SalequotationHVO;
import nc.vo.trade.voutils.SafeCompute;
import nc.vo.util.CloneUtil;

public class SaleQuotationPrintProcessor implements IBeforePrintDataProcess {

  private AbstractAppModel model;

  /**
   * @return model
   */
  public AbstractAppModel getModel() {
    return this.model;
  }

	@Override
	public Object[] processData(Object[] datas) {
		Object[] oridatas = ((SalequoModel)this.getModel()).getSelectedOperaDatas();
		
		AggSalequotationHVO[] vos = new AggSalequotationHVO[datas.length];
		for (int i = 0; i < datas.length; i++) {
//			vos[i] = (AggSalequotationHVO) datas[i];
//			vos[i] = (AggSalequotationHVO) ((AggSalequotationHVO) datas[i]).clone();
			vos[i] = (AggSalequotationHVO) CloneUtil.deepClone(((AggSalequotationHVO) datas[i]));
			
			//add chenth 20181204 打印时要重新取下界面的数据，要不然获取的是拆分后的数据
			SalequotationHVO headvo = vos[i].getParentVO();
			for(Object oridata : oridatas){
				AggSalequotationHVO orivo = (AggSalequotationHVO)oridata;
				if(headvo.getPk_salequotation().equals(orivo.getParentVO().getPk_salequotation())){
					vos[i] =(AggSalequotationHVO) CloneUtil.deepClone(orivo);
				}
			}
			//add end
			
			SalequotationBVO[] bvos = vos[i].getSalequotationBVO();
			if (bvos == null || bvos.length < 1)
				continue;
			List<SalequotationBVO> bvoLst = new ArrayList<SalequotationBVO>();
			
			//是否需要小计
			UFBoolean isSubtotal = vos[i].getParentVO().getVdef5() == null? UFBoolean.FALSE : new UFBoolean(vos[i].getParentVO().getVdef5());

			// 按行号排序
			SaleQuotationBCompare compare = new SaleQuotationBCompare();
			Arrays.sort(bvos, compare);

			String section = null;
			SalequotationBVO sectionVO = null;
			SalequotationBVO sectionSubTotalVO = null;
			Map<String, SalequotationBVO> sectionMap = new HashMap<String, SalequotationBVO>();
			int rowno = 0;
			UFBoolean isSection = null;
			for (SalequotationBVO bvo : bvos) {
				// 不清楚为什么打印会把上次打印的数据带过来，即会把上次生成的Section和Subtotal行带过来，这边过滤掉
				isSection = (UFBoolean) bvo.getAttributeValue("isSection");
				if (UFBoolean.TRUE.equals(isSection))
					continue;

				// 每个section末尾插入一行小计
				if (section != null && false == section.equals(bvo.getVbdef3())
						&& UFBoolean.TRUE.equals(isSubtotal)) {
					sectionSubTotalVO = sectionMap.get(section);
					bvoLst.add(sectionSubTotalVO);
				}

				// 插入一行section
				section = bvo.getVbdef3();
				String[] attrNames = bvo.getAttributeNames();
				if (section != null && section.trim().length() > 0) {
					sectionSubTotalVO = sectionMap.get(section);
					if (sectionSubTotalVO == null) {
						//插入section行
						sectionVO = (SalequotationBVO) bvo.clone();
						sectionVO.setCrowno(null);
						sectionVO.setVbdef1(null);
						sectionVO.setVbdef2(section);
						sectionVO.setAttributeValue("isSection", UFBoolean.TRUE);
						for (String attr : attrNames) {
							if (attr.startsWith("n") 
									&& false == attr.contains("rate")
									&& bvo.getAttributeValue(attr) instanceof UFDouble) {
								sectionVO.setAttributeValue(attr,null);
							}
						}
						bvoLst.add(sectionVO);
						

						sectionSubTotalVO = (SalequotationBVO) sectionVO
								.clone();
						sectionSubTotalVO.setVbdef2("Subtotal");
						sectionMap.put(section, sectionSubTotalVO);
					}
					
					// 小计行金额汇总,单价和小计行不汇总
					for (String attr : attrNames) {
						if (attr.startsWith("n")
								&& false == attr.contains("price")
								&& false == attr.contains("rate")
								&& bvo.getAttributeValue(attr) instanceof UFDouble) {
							sectionSubTotalVO.setAttributeValue(
											attr,
											SafeCompute.add((UFDouble) sectionSubTotalVO.getAttributeValue(attr),
															(UFDouble) bvo.getAttributeValue(attr)));
						}
					}
				}
				
				//行号处理
				rowno = rowno + 1;
				bvo.setCrowno("" + rowno);
				bvoLst.add(bvo);
			}
			// 最后一个section 加上小计行
			sectionSubTotalVO = sectionMap.get(section);
			if(sectionSubTotalVO != null
					&& UFBoolean.TRUE.equals(isSubtotal))
				bvoLst.add(sectionSubTotalVO);
			
			bvos = bvoLst.toArray(new SalequotationBVO[bvoLst.size()]);
			vos[i].setSalequotationBVO(bvos);
		}
		// 精度处理
		SalequoScaleProcessor handler = SalequoScaleProcessor.getInstance();
		handler.setVOPrecision(this.getModel().getContext().getPk_group(), vos);
		return vos;

	}

  /**
   * @param model
   */
  public void setModel(AbstractAppModel model) {
    this.model = model;
  }

}
