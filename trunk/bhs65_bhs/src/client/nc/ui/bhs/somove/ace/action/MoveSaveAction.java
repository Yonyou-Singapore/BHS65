package nc.ui.bhs.somove.ace.action;

import java.awt.event.ActionEvent;

import nc.ui.pubapp.uif2app.actions.pflow.SaveScriptAction;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.vo.bhs.somove.SoMoveBVO;
import nc.vo.pub.lang.UFDouble;
/**
 * move ���水ť�¼�
 * @author Thinkpad
 * @date 2017-4-16
 */
public class MoveSaveAction extends SaveScriptAction{

	private static final long serialVersionUID = -1930620149831825054L;


	@Override
	public void doAction(ActionEvent e) throws Exception {
		// TODO �Զ����ɵķ������
		doTotal();
		super.doAction(e);
	}
	
	/**
	 * Volume (M3):	Ӧ���Ǹ��е� 	L/100 * W/100 * H/100    �ĺ� 
	 * LENGTH (CM):	ȡ����Length (CM)������ֵ				
	 * Crate 1 #:	ȡ����Length (CM)�������к�				
	 *WIDTH (CM):	ȡ����Width (CM)������ֵ				
	  CRATE 2 #:	ȡ����Width (CM)�������к�				
	  HEIGHT (CM):	ȡ����Height (CM)������ֵ				
	  CRATE 3 #:	ȡ����Height (CM)�������к�
	  
	  update by 20170820
	  1��SO Move�Ŀ�Ƭ�У���ͷ���ֶ��У����Զ�����5��def5����ʾΪHEAVIEST (KG)���Զ�����6��def6����ʾΪCRATE 4#,�������ֶε�ֵҲ��Ҫ�ӱ���ȡ�ã�
	  HEAVIEST (KG)��Ҫȡ��������Gross Weigth (KG)����ֵ���ģ�CRATE 4#��Ҫȡ���Ǹ���ֵ���Ķ�Ӧ���кš���֮ǰ�����������ǹ̶���LENGTH (CM)��WIDTH (CM)��HEIGHT (CM)��
	  ��������������Զ�����Ŀ�Ĺ�������������һ���ģ���
2����ͷ���ֶ�Total # Of Pkgs�����ֵ��Ҫ��������еĺϼ�ֵ������������У��ϼ�Ϊ3�����������У��ϼ�Ϊ4��
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
