package nc.vo.bhs.bhstools;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class AggBhsToolsHeadVOMeta extends AbstractBillMeta {
  public AggBhsToolsHeadVOMeta() {
    this.init();
  }
  private void init() {
    this.setParent(nc.vo.bhs.bhstools.BhsToolsHeadVO.class);
    this.addChildren(nc.vo.bhs.bhstools.BhsToolsBodyVO.class);
  }
}