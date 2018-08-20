package nc.vo.bhs.bhsquality;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class AggBhsPoHeadVOMeta extends AbstractBillMeta{
	
	public AggBhsPoHeadVOMeta(){
		this.init();
	}
	
	private void init() {
		this.setParent(nc.vo.bhs.bhsquality.BhsPoHeadVO.class);
		this.addChildren(nc.vo.bhs.bhsquality.BhsPoBodyVO.class);
	}
}