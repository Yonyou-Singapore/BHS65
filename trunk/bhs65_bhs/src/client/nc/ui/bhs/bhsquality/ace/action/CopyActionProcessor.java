package nc.ui.bhs.bhsquality.ace.action;

import nc.desktop.ui.ServerTimeProxy;
import nc.ui.pubapp.uif2app.actions.intf.ICopyActionProcessor;
import nc.vo.bhs.bhsquality.AggBhsPoHeadVO;
import nc.vo.bhs.bhsquality.BhsPoHeadVO;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.pf.BillStatusEnum;
import nc.vo.uif2.LoginContext;

/**
 * ���ƴ�����
 * @author Thinkpad
 *
 */
public class CopyActionProcessor implements ICopyActionProcessor<AggBhsPoHeadVO>{

	@Override
	public void processVOAfterCopy(AggBhsPoHeadVO billVO, LoginContext context) {
		// TODO �Զ����ɵķ������
		this.processHeadVO(billVO, context);
	    this.processBodyVO(billVO);
	}

	private void processBodyVO(AggBhsPoHeadVO vo) {
	  	  vo.getParent().setAttributeValue(
	    vo.getMetaData().getParent().getPrimaryAttribute().getName(), null);
	    vo.getParent().setAttributeValue("ts", null);
	    for (IVOMeta meta : vo.getMetaData().getChildren()) {
	      if(vo.getChildren(meta)==null)
	      			     continue;
	      for (ISuperVO childvo : vo.getChildren(meta)) {
	          childvo.setAttributeValue(meta.getPrimaryAttribute().getName(), null);
	          childvo.setAttributeValue("pk_group", null);
	          childvo.setAttributeValue("pk_org", null);
	          childvo.setAttributeValue("ts", null);
	      }
	    }
	  }

	  private void processHeadVO(AggBhsPoHeadVO vo, LoginContext context) {
	    UFDateTime datetime = ServerTimeProxy.getInstance().getServerTime();
	    BhsPoHeadVO hvo = vo.getParentVO();
	    // ���ÿմ���
	    hvo.setVbillno(null);
	    hvo.setApprover(null);
	    hvo.setApprovedate(null);
	    hvo.setModifier(null);
	    hvo.setModifiedtime(null);
	    hvo.setCreator(null);
	    hvo.setCreationtime(null);
	    //hvo.setTs(null);
	    // ����Ĭ��ֵ
	    hvo.setDbilldate(datetime.getDate());
	    hvo.setPk_group(context.getPk_group());
	    hvo.setPk_org(context.getPk_org());
	    hvo.setAttributeValue("vbillstatus", BillStatusEnum.FREE.value());
	    hvo.setPono(null);
	  }
	
}
