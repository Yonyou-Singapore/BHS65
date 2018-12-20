package nc.ui.so.salequotation.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction.IBeforePrintDataProcess;
import nc.ui.so.salequotation.scale.SalequoScaleProcessor;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.so.salequotation.entity.AggSalequotationHVO;
import nc.vo.so.salequotation.entity.SalequotationBVO;
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
		AggSalequotationHVO[] vos = new AggSalequotationHVO[datas.length];
		for (int i = 0; i < datas.length; i++) {
//			vos[i] = (AggSalequotationHVO) datas[i];
//			vos[i] = (AggSalequotationHVO) ((AggSalequotationHVO) datas[i]).clone();
			vos[i] = (AggSalequotationHVO) CloneUtil.deepClone(((AggSalequotationHVO) datas[i]));
			SalequotationBVO[] bvos = vos[i].getSalequotationBVO();
			if (bvos == null || bvos.length < 1)
				continue;
			List<SalequotationBVO> bvoLst = new ArrayList<SalequotationBVO>();
			
			//�Ƿ���ҪС��
			UFBoolean isSubtotal = vos[i].getParentVO().getVdef5() == null? UFBoolean.FALSE : new UFBoolean(vos[i].getParentVO().getVdef5());

			// ���к�����
			SaleQuotationBCompare compare = new SaleQuotationBCompare();
			Arrays.sort(bvos, compare);

			String section = null;
			SalequotationBVO sectionVO = null;
			SalequotationBVO sectionSubTotalVO = null;
			Map<String, SalequotationBVO> sectionMap = new HashMap<String, SalequotationBVO>();
			int rowno = 0;
			UFBoolean isSection = null;
			for (SalequotationBVO bvo : bvos) {
				// �����Ϊʲô��ӡ����ϴδ�ӡ�����ݴ�������������ϴ����ɵ�Section��Subtotal�д���������߹��˵�
				isSection = (UFBoolean) bvo.getAttributeValue("isSection");
				if (UFBoolean.TRUE.equals(isSection))
					continue;

				// ÿ��sectionĩβ����һ��С��
				if (section != null && false == section.equals(bvo.getVbdef3())
						&& UFBoolean.TRUE.equals(isSubtotal)) {
					sectionSubTotalVO = sectionMap.get(section);
					bvoLst.add(sectionSubTotalVO);
				}

				// ����һ��section
				section = bvo.getVbdef3();
				String[] attrNames = bvo.getAttributeNames();
				if (section != null && section.trim().length() > 0) {
					sectionSubTotalVO = sectionMap.get(section);
					if (sectionSubTotalVO == null) {
						//����section��
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
					
					// С���н�����,���ۺ�С���в�����
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
				
				//�кŴ���
				rowno = rowno + 1;
				bvo.setCrowno("" + rowno);
				bvoLst.add(bvo);
			}
			// ���һ��section ����С����
			sectionSubTotalVO = sectionMap.get(section);
			if(sectionSubTotalVO != null
					&& UFBoolean.TRUE.equals(isSubtotal))
				bvoLst.add(sectionSubTotalVO);
			
			bvos = bvoLst.toArray(new SalequotationBVO[bvoLst.size()]);
			vos[i].setSalequotationBVO(bvos);
		}
		// ���ȴ���
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
