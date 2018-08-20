package nc.vo.bhs.sotruck;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class AggSoTruckHVOMeta extends AbstractBillMeta{
	
	public AggSoTruckHVOMeta(){
		this.init();
	}
	
	private void init() {
		this.setParent(nc.vo.bhs.sotruck.SoTruckHVO.class);
		this.addChildren(nc.vo.bhs.sotruck.SoTruckToneBVO.class);
		this.addChildren(nc.vo.bhs.sotruck.SoTruckTsedBVO.class);
		this.addChildren(nc.vo.bhs.sotruck.SoTruckTthBVO.class);
		this.addChildren(nc.vo.bhs.sotruck.SoTruckTEoneBVO.class);
		this.addChildren(nc.vo.bhs.sotruck.SoTruckTEsedBVO.class);
		this.addChildren(nc.vo.bhs.sotruck.SoTruckTEthBVO.class);
		this.addChildren(nc.vo.bhs.sotruck.SoTruckFoneBVO.class);
		this.addChildren(nc.vo.bhs.sotruck.SoTruckOrderVO.class);
	}
}