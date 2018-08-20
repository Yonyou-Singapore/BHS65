package nc.vo.bhs.pack;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class AggSoOrderPackVOMeta extends AbstractBillMeta{
	
	public AggSoOrderPackVOMeta(){
		this.init();
	}
	
	private void init() {
		this.setParent(nc.vo.bhs.pack.SoOrderPackVO.class);
		this.addChildren(nc.vo.bhs.pack.SoOrderPackBVO.class);
		this.addChildren(nc.vo.bhs.pack.SoPackOrderVO.class);
	}
}