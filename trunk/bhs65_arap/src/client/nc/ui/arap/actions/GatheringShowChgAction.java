package nc.ui.arap.actions;

import java.awt.event.ActionEvent;
import java.util.List;
import nc.ui.arap.model.ArapBillManageModel;
import nc.ui.arap.pub.GatheringCombin;
import nc.ui.arap.view.ArapBillCardForm;
import nc.ui.pubapp.uif2app.view.BillListView;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.UIState;
import nc.vo.arap.gathering.AggGatheringBillVO;
import nc.vo.arap.gathering.CombinCacheVO;
import nc.vo.arap.gathering.CombinResultVO;
import nc.vo.arap.pub.res.ParameterList;
import nc.vo.ml.AbstractNCLangRes;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pubapp.pattern.pub.MapList;

public class GatheringShowChgAction extends NCAction
{
  String details = NCLangRes4VoTransl.getNCLangRes()
    .getStrByID("summaryext", 
    "summaryext-0001");

  String summary = NCLangRes4VoTransl.getNCLangRes()
    .getStrByID("summaryext", 
    "summaryext-0002");
  private static final long serialVersionUID = 1L;
  protected ArapBillManageModel model;
  private ArapBillCardForm editor;
  private BillListView listView = null;

  public Boolean isBcombinflag = Boolean.valueOf(false);
  private CombinCacheVO combinvo;

  public void setCombinCacheVO(CombinCacheVO cachevo)
  {
    this.combinvo = cachevo;
  }

  public CombinCacheVO getCombinCacheVO() {
    if (this.combinvo == null) {
      this.combinvo = new CombinCacheVO(false);
    }
    return this.combinvo;
  }

  public GatheringShowChgAction()
  {
    setBtnName(this.summary);
    putValue("Code", "Megredisplay");
  }

  public void initSummary()
  {
    setBtnName(this.summary);
    this.combinvo = new CombinCacheVO(false);
  }

  public void doAction(ActionEvent arg0)
    throws Exception
  {
    GatheringCombin combinutil = new GatheringCombin();
    CombinCacheVO cachevo = getCombinCacheVO();

    List alldata = getModel().getData();
    int selectedRow = getModel().getSelectedRow();

    if (cachevo.getBcombinflag()) {
      cachevo.setBcombinflag(false);
      cachevo.setMapGroupKeys(null);
      AggGatheringBillVO[] chgedvos = combinutil.splitNoEditGathering(
        (AggGatheringBillVO[])alldata.toArray(new AggGatheringBillVO[alldata.size()]), 
        cachevo.getCombinRela());

      initModel(chgedvos);
      setBtnName(this.summary);

      cachevo.setCombinrela(new MapList());

      this.isBcombinflag = Boolean.valueOf(false);
    }
    else
    {
      combinutil.updateChildVO(alldata);

      AggGatheringBillVO[] allvos = new AggGatheringBillVO[alldata.size()];
      alldata.toArray(allvos);
      cachevo.setBcombinflag(true);
      cachevo.setCombinrela(null);
      setCombinCacheVO(cachevo);
      initModel(allvos);

      setBtnName(this.details);

      this.isBcombinflag = Boolean.valueOf(true);
    }

    getModel().setSelectedRow(selectedRow);
  }

  public void initModel(Object data) {
    if (data == null) {
      getModel().initModel(data);
      return;
    }
    AggGatheringBillVO[] vos = (AggGatheringBillVO[])null;
    if (data.getClass().isArray())
      vos = (AggGatheringBillVO[])data;
    else {
      vos = new AggGatheringBillVO[] { (AggGatheringBillVO)data };
    }

    CombinResultVO combinres = null;
    CombinCacheVO cache = getCombinCacheVO();

    if ((cache.getBcombinflag()) && (cache.getCombinRela() != null) && 
      (cache.getCombinRela().size() > 0))
    {
      cache.setCombinrela(new MapList());
      GatheringCombin combinutil = new GatheringCombin();
      combinres = combinutil.combinGathering(vos, cache, 
        ParameterList.AR101.getCode());
      getModel().initModel(combinres.getCombinvos());
    } else if ((cache.getBcombinflag()) && (
      (cache.getCombinRela() == null) || 
      (cache.getCombinRela()
      .size() == 0)))
    {
      cache.setCombinrela(new MapList());
      GatheringCombin combinutil = new GatheringCombin();
      combinres = combinutil.combinGathering(vos, cache, 
        ParameterList.AR101.getCode());
      getModel().initModel(combinres.getCombinvos());
    } else {
      getModel().initModel(vos);
    }
    setCombinCacheVO(cache);
  }

  protected boolean isActionEnable()
  {
    return (super.isActionEnable()) && 
      (getModel().getUiState() != UIState.EDIT) && 
      (getModel().getSelectedData() != null);
  }

  public ArapBillManageModel getModel() {
    return this.model;
  }

  public void setModel(ArapBillManageModel model) {
    this.model = model;
    model.addAppEventListener(this);
  }

  public ArapBillCardForm getEditor() {
    return this.editor;
  }

  public void setEditor(ArapBillCardForm editor) {
    this.editor = editor;
  }

  public BillListView getListView() {
    return this.listView;
  }

  public void setListView(BillListView listView) {
    this.listView = listView;
  }
}