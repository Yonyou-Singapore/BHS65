package nc.vo.bhs.somove;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.bhs.somove.SoMoveHVO")

public class AggSoMoveHVO extends AbstractBill {
	
	  @Override
	  public IBillMeta getMetaData() {
	  	IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(AggSoMoveHVOMeta.class);
	  	return billMeta;
	  }
	    
	  @Override
	  public SoMoveHVO getParentVO(){
	  	return (SoMoveHVO)this.getParent();
	  }
	  
}