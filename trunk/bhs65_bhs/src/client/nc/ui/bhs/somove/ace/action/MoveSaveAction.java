package nc.ui.bhs.somove.ace.action;

import java.awt.event.ActionEvent;

import nc.ui.pubapp.uif2app.actions.pflow.SaveScriptAction;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.vo.bhs.somove.SoMoveBVO;
import nc.vo.pub.lang.UFDouble;
/**
 * move 保存按钮事件
 * @author Thinkpad
 * @date 2017-4-16
 */
public class MoveSaveAction extends SaveScriptAction{

	private static final long serialVersionUID = -1930620149831825054L;


	@Override
	public void doAction(ActionEvent e) throws Exception {
		// TODO 自动生成的方法存根
		doTotal();
		super.doAction(e);
	}
	
	/**
	 * Volume (M3):	应该是各行的 	L/100 * W/100 * H/100    的和 
	 * LENGTH (CM):	取表体Length (CM)列最大的值				
	 * Crate 1 #:	取表体Length (CM)列最大的行号				
	 *WIDTH (CM):	取表体Width (CM)列最大的值				
	  CRATE 2 #:	取表体Width (CM)列最大的行号				
	  HEIGHT (CM):	取表体Height (CM)列最大的值				
	  CRATE 3 #:	取表体Height (CM)列最大的行号
	  
	  update by 20170820
	  1、SO Move的卡片中，表头的字段中，用自定义项5（def5）标示为HEAVIEST (KG)，自定义项6（def6）标示为CRATE 4#,这两个字段的值也需要从表体取得，
	  HEAVIEST (KG)需要取表体列中Gross Weigth (KG)中数值最大的，CRATE 4#需要取到那个数值最大的对应的行号。（之前开发的内容是固定了LENGTH (CM)，WIDTH (CM)，HEIGHT (CM)，
	  所以这个新增的自定义项目的规则与这三个是一样的）；
2、表头的字段Total # Of Pkgs，这个值需要计算表体行的合计值，如表体有三行，合计为3，表体有四行，合计为4；
	 */
	private void doTotal(){
		ShowUpableBillForm e = (ShowUpableBillForm)this.editor;
		int rowCount = e.getBillCardPanel().getRowCount();
		UFDouble grossweight = UFDouble.ZERO_DBL;
		UFDouble netweight = UFDouble.ZERO_DBL;
		
		UFDouble sumvolumem3 = UFDouble.ZERO_DBL;
		UFDouble maxlengthcm = UFDouble.ZERO_DBL;
		UFDouble maxwidthcm = UFDouble.ZERO_DBL;
		UFDouble maxheightcm = UFDouble.ZERO_DBL;
		
		UFDouble sumgrossweight = UFDouble.ZERO_DBL;
		UFDouble sumnetweight = UFDouble.ZERO_DBL;
		
		UFDouble maxheaviest = UFDouble.ZERO_DBL;
		
		String maxlengthno = "";
		String maxwidthno = "";
		String maxheightno = "";
		String maxheaviestno = "";
		
		for(int i = 0 ;i<rowCount; i++){
			
			SoMoveBVO itemvo = (SoMoveBVO) e.getBillCardPanel().getBillModel().getBodyValueRowVO(i,SoMoveBVO.class.getName());
			grossweight = itemvo.getVgrossweigthkg() == null?UFDouble.ZERO_DBL:itemvo.getVgrossweigthkg();
			netweight = itemvo.getVnetweightkg() == null?UFDouble.ZERO_DBL:itemvo.getVnetweightkg();
			UFDouble vlengthcm = itemvo.getVlengthcm() == null?UFDouble.ZERO_DBL:itemvo.getVlengthcm();
			UFDouble vwidthcm = itemvo.getVwidthcm() == null?UFDouble.ZERO_DBL:itemvo.getVwidthcm();
			UFDouble vheightcm = itemvo.getVheightcm() == null?UFDouble.ZERO_DBL:itemvo.getVheightcm();
			
			sumgrossweight = sumgrossweight.add(grossweight);
			sumnetweight = sumnetweight.add(netweight);
			
			
			sumvolumem3 = sumvolumem3.add(vlengthcm.div(100).multiply(vwidthcm.div(100)).multiply(vheightcm.div(100)));
			if(maxlengthcm.compareTo(vlengthcm) < 0){
				maxlengthcm = vlengthcm;
				maxlengthno = itemvo.getSno();
			}
			if(maxwidthcm.compareTo(vwidthcm) < 0){
				maxwidthcm = vwidthcm;
				maxwidthno = itemvo.getSno();
			}
			if(maxheightcm.compareTo(vheightcm) < 0){
				maxheightcm = vheightcm;
				maxheightno = itemvo.getSno();
			}
			if(maxheaviest.compareTo(grossweight) < 0){
				maxheaviest = grossweight;
				maxheaviestno = itemvo.getSno();
			}
			
		}
		e.getBillCardPanel().setHeadItem("volumem3", sumvolumem3);
		e.getBillCardPanel().setHeadItem("grossweight", sumgrossweight);
		e.getBillCardPanel().setHeadItem("netweight", sumnetweight);
		e.getBillCardPanel().setHeadItem("lengthcm", maxlengthcm);
		e.getBillCardPanel().setHeadItem("widthcm", maxwidthcm);
		e.getBillCardPanel().setHeadItem("heightcm", maxheightcm);
		e.getBillCardPanel().setHeadItem("crate1", maxlengthno);
		e.getBillCardPanel().setHeadItem("crate2", maxwidthno);
		e.getBillCardPanel().setHeadItem("crate3", maxheightno);
		//2017-11-23
		e.getBillCardPanel().setHeadItem("chargeableweight", sumvolumem3.multiply(166.67));
		
		e.getBillCardPanel().setHeadItem("largestweight", maxheaviest);
		e.getBillCardPanel().setHeadItem("kcrate", maxheaviestno);
		e.getBillCardPanel().setHeadItem("totalofpkgs", e.getBillCardPanel().getRowCount());
		
	}
}
