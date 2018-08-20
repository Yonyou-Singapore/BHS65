package nc.vo.bhs.build;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.bhs.build.SoOrderBuildHVO")

public class AggSoOrderBuildHVO extends AbstractBill {
	
	  @Override
	  public IBillMeta getMetaData() {
	  	IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(AggSoOrderBuildHVOMeta.class);
	  	return billMeta;
	  }
	    
	  @Override
	  public SoOrderBuildHVO getParentVO(){
	  	return (SoOrderBuildHVO)this.getParent();
	  }
	  
}