package nc.vo.bhs.bhsdo;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class AggBhsDoVOMeta extends AbstractBillMeta{
	
	public AggBhsDoVOMeta(){
		this.init();
	}
	
	private void init() {
		this.setParent(nc.vo.bhs.bhsdo.BhsDoVO.class);
	}
}