package nc.vo.bhs.sostore;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.bhs.sostore.SoStoreHVO")

public class AggSoStoreHVO extends AbstractBill {
	
	  @Override
	  public IBillMeta getMetaData() {
	  	IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(AggSoStoreHVOMeta.class);
	  	return billMeta;
	  }
	    
	  @Override
	  public SoStoreHVO getParentVO(){
	  	return (SoStoreHVO)this.getParent();
	  }
	  
}