package nc.vo.so.m30.vochange;

import java.util.Comparator;

import nc.vo.pub.lang.UFDouble;
import nc.vo.so.m30.entity.SaleOrderBVO;

public class SaleOrderBCompare implements Comparator<SaleOrderBVO> {

	@Override
	public int compare(SaleOrderBVO o1, SaleOrderBVO o2) {
		if(o1.getCrowno() == null )
			return -1;
		else if(o2.getCrowno() == null)
			return 1;
		
		return new UFDouble(o1.getCrowno()).compareTo(new UFDouble(o2.getCrowno()));
	}

}
