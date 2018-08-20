package nc.vo.bhs.pack;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.bhs.pack.SoOrderPackVO")

public class AggSoOrderPackVO extends AbstractBill {
	
	  @Override
	  public IBillMeta getMetaData() {
	  	IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(AggSoOrderPackVOMeta.class);
	  	return billMeta;
	  }
	    
	  @Override
	  public SoOrderPackVO getParentVO(){
	  	return (SoOrderPackVO)this.getParent();
	  }
	  
}