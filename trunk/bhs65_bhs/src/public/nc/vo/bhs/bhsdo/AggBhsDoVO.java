package nc.vo.bhs.bhsdo;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.bhs.bhsdo.BhsDoVO")

public class AggBhsDoVO extends AbstractBill {
	
	  @Override
	  public IBillMeta getMetaData() {
	  	IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(AggBhsDoVOMeta.class);
	  	return billMeta;
	  }
	    
	  @Override
	  public BhsDoVO getParentVO(){
	  	return (BhsDoVO)this.getParent();
	  }
	  
}