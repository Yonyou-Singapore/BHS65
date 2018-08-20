package nc.vo.bhs.somove;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class AggSoMoveHVOMeta extends AbstractBillMeta{
	
	public AggSoMoveHVOMeta(){
		this.init();
	}
	
	private void init() {
		this.setParent(nc.vo.bhs.somove.SoMoveHVO.class);
		this.addChildren(nc.vo.bhs.somove.SoMoveBVO.class);
		this.addChildren(nc.vo.bhs.somove.SoMoveBlackBoxVO.class);
		this.addChildren(nc.vo.bhs.somove.SoMoveOrderVO.class);
	}
}