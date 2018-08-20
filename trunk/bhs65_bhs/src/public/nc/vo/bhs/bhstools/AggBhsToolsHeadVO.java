package nc.vo.bhs.bhstools;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.bhs.bhstools.BhsToolsHeadVO")
public class AggBhsToolsHeadVO extends AbstractBill {

  @Override
  public IBillMeta getMetaData() {
    IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(AggBhsToolsHeadVOMeta.class);
    return billMeta;
  }

  @Override
  public BhsToolsHeadVO getParentVO() {
    return (BhsToolsHeadVO) this.getParent();
  }
}