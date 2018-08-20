package nc.vo.bhs.sotruck;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.bhs.sotruck.SoTruckHVO")

public class AggSoTruckHVO extends AbstractBill {
	
	  @Override
	  public IBillMeta getMetaData() {
	  	IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(AggSoTruckHVOMeta.class);
	  	return billMeta;
	  }
	    
	  @Override
	  public SoTruckHVO getParentVO(){
	  	return (SoTruckHVO)this.getParent();
	  }
	  
}