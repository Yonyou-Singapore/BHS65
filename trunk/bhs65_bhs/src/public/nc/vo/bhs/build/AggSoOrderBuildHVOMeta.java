package nc.vo.bhs.build;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class AggSoOrderBuildHVOMeta extends AbstractBillMeta{
	
	public AggSoOrderBuildHVOMeta(){
		this.init();
	}
	
	private void init() {
		this.setParent(nc.vo.bhs.build.SoOrderBuildHVO.class);
		this.addChildren(nc.vo.bhs.build.SoOrderBuildBVO.class);
		this.addChildren(nc.vo.bhs.build.SoBuildOrderVO.class);
	}
}