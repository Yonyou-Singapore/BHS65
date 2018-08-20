package nc.vo.bhs.bhsquality;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.bhs.bhsquality.BhsPoHeadVO")

public class AggBhsPoHeadVO extends AbstractBill {
	
	  @Override
	  public IBillMeta getMetaData() {
	  	IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(AggBhsPoHeadVOMeta.class);
	  	return billMeta;
	  }
	    
	  @Override
	  public BhsPoHeadVO getParentVO(){
	  	return (BhsPoHeadVO)this.getParent();
	  }
	  
}