package nc.vo.bhs.sostore;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class AggSoStoreHVOMeta extends AbstractBillMeta{
	
	public AggSoStoreHVOMeta(){
		this.init();
	}
	
	private void init() {
		this.setParent(nc.vo.bhs.sostore.SoStoreHVO.class);
		this.addChildren(nc.vo.bhs.sostore.SoStoreBVO.class);
		this.addChildren(nc.vo.bhs.sostore.SoStoreOrderVO.class);
	}
}