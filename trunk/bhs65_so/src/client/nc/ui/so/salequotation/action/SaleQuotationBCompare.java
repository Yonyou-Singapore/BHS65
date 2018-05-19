package nc.ui.so.salequotation.action;

import java.util.Comparator;

import nc.vo.pub.lang.UFDouble;
import nc.vo.so.salequotation.entity.SalequotationBVO;

public class SaleQuotationBCompare implements Comparator<SalequotationBVO> {

	@Override
	public int compare(SalequotationBVO o1, SalequotationBVO o2) {
		if(o1.getCrowno() == null )
			return -1;
		else if(o2.getCrowno() == null)
			return 1;
		
		return new UFDouble(o1.getCrowno()).compareTo(new UFDouble(o2.getCrowno()));
	}

}
