package nc.ui.so.m32.billui.pub;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.enumeration.OpposeFlag;
import nc.vo.so.m32.paravo.CombinCacheVO;
import nc.vo.so.pub.res.ParameterList;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.so.pub.keyvalue.CardKeyValue;

/**
 * ��Ƭ�����ʼ����ʱ�򻺴濨Ƭ�༭��
 * ������ֱ�־�����ı�ʱ���ƿ�Ƭ�༭��
 * 
 * @since 6.0
 * @version 2010-12-10 ����01:03:01
 * @author ô��
 */
public class CardEditSetter {

  /**
   * �ϲ���ʾ����༭���ֶ�
   */
  private static final String[] BODY_COMBINCANEDITKEY = new String[] {
    // ����������������������
    SaleInvoiceBVO.NASTNUM, SaleInvoiceBVO.NNUM, SaleInvoiceBVO.NQTUNITNUM,
    // ��˰�ϼơ���˰���
    SaleInvoiceBVO.NORIGTAXMNY, SaleInvoiceBVO.NORIGMNY,
    // ˰���ۿ۶�
    SaleInvoiceBVO.NTAX, SaleInvoiceBVO.NORIGDISCOUNT
  };

  /** ��Ҫ���Ʊ༭�Եı����ֶ� */
  private static final String[] BODY_EDITCHGKEY = new String[] {
    // ���ϡ���λ
    SaleInvoiceBVO.CMATERIALVID, SaleInvoiceBVO.CASTUNITID,
    // ������������
    SaleInvoiceBVO.NASTNUM, SaleInvoiceBVO.NNUM,
    // �����ʡ���������
    SaleInvoiceBVO.VCHANGERATE, SaleInvoiceBVO.NQTUNITNUM,
    // ���۵�λ�����ۻ�����
    SaleInvoiceBVO.CQTUNITID, SaleInvoiceBVO.VQTUNITRATE,
    // ��Ʒ����Ʊ�ۿ�
    SaleInvoiceBVO.BLARGESSFLAG, SaleInvoiceBVO.NINVOICEDISRATE,
    // ����˰���ۡ�����˰����
    SaleInvoiceBVO.NORIGTAXPRICE, SaleInvoiceBVO.NORIGPRICE,
    // ����˰���ۡ�����˰����
    SaleInvoiceBVO.NORIGTAXNETPRICE, SaleInvoiceBVO.NORIGNETPRICE,
    // ��˰���ۡ���˰����
    SaleInvoiceBVO.NQTORIGTAXPRICE, SaleInvoiceBVO.NQTORIGPRICE,
    // ��˰���ۡ���˰����
    SaleInvoiceBVO.NQTORIGTAXNETPRC, SaleInvoiceBVO.NQTORIGNETPRICE,
    // ˰���˰���
    SaleInvoiceBVO.NORIGMNY,
    // ��˰�ϼơ��ۿ۶�
    SaleInvoiceBVO.NORIGTAXMNY, SaleInvoiceBVO.NORIGDISCOUNT,
    // �������ġ�˰��
    SaleInvoiceBVO.CPROFITCENTERID, SaleInvoiceBVO.NTAXRATE
  };

  /** �Գ�����༭���ֶ� */
  private static final String[] OPPHEAD_CANEDITKEY = new String[] {
    // ��˰�š���ע����Ʊ��
    SaleInvoiceHVO.VGOLDTAXCODE,
    SaleInvoiceHVO.VNOTE,
    SaleInvoiceHVO.VBILLCODE,
    // ��ͷ�Զ����ֶ�
    SaleInvoiceHVO.VDEF1, SaleInvoiceHVO.VDEF2, SaleInvoiceHVO.VDEF3,
    SaleInvoiceHVO.VDEF4, SaleInvoiceHVO.VDEF5, SaleInvoiceHVO.VDEF6,
    SaleInvoiceHVO.VDEF7, SaleInvoiceHVO.VDEF8, SaleInvoiceHVO.VDEF9,
    SaleInvoiceHVO.VDEF10, SaleInvoiceHVO.VDEF11, SaleInvoiceHVO.VDEF12,
    SaleInvoiceHVO.VDEF13, SaleInvoiceHVO.VDEF14, SaleInvoiceHVO.VDEF15,
    SaleInvoiceHVO.VDEF16, SaleInvoiceHVO.VDEF17, SaleInvoiceHVO.VDEF18,
    SaleInvoiceHVO.VDEF19, SaleInvoiceHVO.VDEF20

  };

  /** �Գ�����༭���ֶ� */
  private static final String[] OPPBODY_CANEDITKEY = new String[] {
    // �����Զ����ֶ�
    SaleInvoiceBVO.VBDEF1, SaleInvoiceBVO.VBDEF2, SaleInvoiceBVO.VBDEF3,
    SaleInvoiceBVO.VBDEF4, SaleInvoiceBVO.VBDEF5, SaleInvoiceBVO.VBDEF6,
    SaleInvoiceBVO.VBDEF7, SaleInvoiceBVO.VBDEF8, SaleInvoiceBVO.VBDEF9,
    SaleInvoiceBVO.VBDEF10, SaleInvoiceBVO.VBDEF11, SaleInvoiceBVO.VBDEF12,
    SaleInvoiceBVO.VBDEF13, SaleInvoiceBVO.VBDEF14, SaleInvoiceBVO.VBDEF15,
    SaleInvoiceBVO.VBDEF16, SaleInvoiceBVO.VBDEF17, SaleInvoiceBVO.VBDEF18,
    SaleInvoiceBVO.VBDEF19, SaleInvoiceBVO.VBDEF20
  };

  /** ����ı���༭�� */
  private Map<String, Boolean> bodyEditCache;

  /** ����ı���cell�ı༭�� */
  private Set<String> bodycellEditCache = new HashSet<String>();

  /** ����ı���cell�ı༭�� */
  private Set<Integer> bodycellrows = new HashSet<Integer>();

  /** ����ı�ͷ�༭�� */
  private Map<String, Boolean> headEditCache;

  /**
   * ����ԭʼ��Ƭ��������Ŀ�ı༭��
   * 
   * @param card
   */
  public void cacheEditEnable(BillCardPanel card) {
    // �����ͷ�༭��
    this.headEditCache = new HashMap<String, Boolean>();

    for (BillItem headitem : card.getHeadItems()) {
      this.headEditCache.put(headitem.getKey(),
          Boolean.valueOf(headitem.isEdit()));
    }
    // �������༭��
    this.bodyEditCache = new HashMap<String, Boolean>();
    for (BillItem bodyitem : card.getBodyItems()) {
      String key = bodyitem.getKey();
      this.bodyEditCache.put(key, Boolean.valueOf(bodyitem.isEdit()));
    }
  }

  /**
   * ���ݱ�ͷ�Գ��־���úͺϲ���ʾ��־���ÿ�Ƭ����༭��
   * 
   * @param card
   * @param cachecomvo
   */
  public void setEditEnable(BillCardPanel card, CombinCacheVO cachecomvo) {
    // ���úϲ���ʾʱ�ı༭��
    this.setComEditEnable(card, cachecomvo);
    // ���ñ�ͷ�༭��
    this.setEditEnable(card);

  }

  private boolean isArsub(BillCardPanel card) {
    CardKeyValue keyvalue = new CardKeyValue(card);
    // ֻ��δ��ֵķ�Ʊ���Ա༭����ֱ�־Ϊ��ʱ�������Ա༭
    UFBoolean arsubflag =
        keyvalue.getHeadUFBooleanValue(SaleInvoiceHVO.BSUBUNITFLAG);
    return null != arsubflag && arsubflag.booleanValue();
  }

  private boolean isOppose(BillCardPanel card) {
    CardKeyValue keyvalue = new CardKeyValue(card);
    // �Գ��־Ϊ�����Ĳſ��Ա༭���Գ�����ɺͶԳ����ɵĶ������Ա༭
    Integer oppflag = keyvalue.getHeadIntegerValue(SaleInvoiceHVO.FOPPOSEFLAG);
    return OpposeFlag.FINSH.equalsValue(oppflag)
        || OpposeFlag.GENERAL.equalsValue(oppflag);
  }

  /**
   * �ָ������ֶ�ԭʼ�༭��
   * 
   * @param card
   */
  private void resumeBodyEdit(BillCardPanel card) {

    for (BillItem bodyitem : card.getBodyShowItems()) {
      String key = bodyitem.getKey();
      bodyitem.setEdit(this.bodyEditCache.get(key).booleanValue());
    }

  }

  /**
   * ��ʾ��ϸʱ����
   * 
   * @param card
   */
  private void resumeBodyCellEdit(BillCardPanel card) {
    if (card.getRowCount() <= 0) {
      return;
    }
    BillItem[] bodyitems = card.getBodyShowItems();
    for (Integer i : this.bodycellrows) {
      if (i.intValue() <= card.getRowCount()) {
        break;
      }
      for (BillItem bodyitem : bodyitems) {
        String key = bodyitem.getKey();
        if (this.bodycellEditCache.contains(key)) {
          card.setCellEditable(i.intValue(), key, true);
        }
      }
    }
    this.bodycellEditCache.clear();
    this.bodycellrows.clear();
  }

  /**
   * �ָ��༭��
   * 
   * @param card cardPanel
   */
  public void resumeEdit(BillCardPanel card) {

    this.resumeBodyEdit(card);

  }

  private void setCombinEdit(BillCardPanel card, Set<String> setGroupKeys) {
    int row = card.getRowCount();
    for (BillItem bodyitem : card.getBodyShowItems()) {
      String key = bodyitem.getKey();
      if (setGroupKeys.contains(key)) {
        bodyitem.setEdit(this.bodyEditCache.get(key).booleanValue());
      }
      else {
        if (this.bodyEditCache.get(key).booleanValue()) {
          this.bodycellEditCache.add(key);
        }
        for (int i = 0; i < row; i++) {
          if (!this.bodycellrows.contains(Integer.valueOf(i))) {
            this.bodycellrows.add(Integer.valueOf(i));
          }
          card.setCellEditable(i, key, false);
        }
      }
    }

  }

  private void setComEditEnable(BillCardPanel card, CombinCacheVO cachecomvo) {
    // �ϲ���־
    boolean comflag = cachecomvo.getBcombinflag();
    Set<String> setGroupKeys = null;
    if (comflag) {
      CardKeyValue keyvalue = new CardKeyValue(card);
      String pk_org = keyvalue.getHeadStringValue(SaleInvoiceHVO.PK_ORG);
      if (null != pk_org) {
        setGroupKeys = new HashSet<String>();
        String groupstr =
            cachecomvo.getGroupKeys(pk_org).split(ParameterList.BIGSPLITKEY)[0];
        String[] groups = groupstr.split(ParameterList.SPLITKEY);
        for (String key : groups) {
          setGroupKeys.add(key);
        }
        for (String key : CardEditSetter.BODY_COMBINCANEDITKEY) {
          setGroupKeys.add(key);
        }
        // ���ñ༭��
        this.setCombinEdit(card, setGroupKeys);
      }
    }
    else {
      this.resumeBodyCellEdit(card);
    }
  }

  /**
   * �Գ�ͳ�ֱ༭�Կ���
   * 
   * @param card
   */
  private void setEditEnable(BillCardPanel card) {
    boolean oppflag = this.isOppose(card);
    boolean subflag = this.isArsub(card);
    if (!subflag && !oppflag && null != this.headEditCache) {
      for (BillItem headitem : card.getHeadShowItems()) {
        headitem.setEdit(this.headEditCache.get(headitem.getKey())
            .booleanValue());
      }
      this.resumeBodyEdit(card);
    }
    if (subflag) {
      this.setSubEdit(card);
    }

    if (oppflag) {
      this.setOppEdit(card);
    }
  }

  /**
   * ���������������Գ巢Ʊʱ�����ֶα༭�ԡ�
   * <p>
   * <b>����˵��</b>
   * 
   * @param card
   *          <p>
   * @author fengjb
   * @time 2010-5-24 ����03:58:55
   */
  private void setOppEdit(BillCardPanel card) {
    Set<String> setEdit = new HashSet<String>();
    for (String key : CardEditSetter.OPPHEAD_CANEDITKEY) {
      setEdit.add(key);
    }
    Set<String> setBodyEdit = new HashSet<String>();
    for (String key : CardEditSetter.OPPBODY_CANEDITKEY) {
      setBodyEdit.add(key);
    }
    //add chenth 20171220 ���ַ�Ʊʱ���ѽ����������
    card.getHeadItem(SaleInvoiceHVO.CTRANTYPEID).setValue(null);

   /* del chenth 20171218 BHS֧�ֺ��ַ�Ʊ�����޸������͵��۵��ֶ���Ϣ
    * for (BillItem headitem : card.getHeadShowItems()) {
      String key = headitem.getKey();
      if (setEdit.contains(key)) {
        headitem.setEdit(headitem.isEdit());
      }
      else {
        headitem.setEdit(false);
      }
    }

    for (BillItem bodyitem : card.getBodyShowItems()) {
      String bodyKey = bodyitem.getKey();
      if (setBodyEdit.contains(bodyKey)) {
        bodyitem.setEdit(bodyitem.isEdit());
      }
      else {
        bodyitem.setEdit(false);
      }
    }*/
  }

  /**
   * ��ֱ༭�Կ���
   * 
   * @param card
   */
  private void setSubEdit(BillCardPanel card) {
    Set<String> setNoEdit = new HashSet<String>();
    for (String key : CardEditSetter.BODY_EDITCHGKEY) {
      setNoEdit.add(key);
    }

    for (BillItem bodyitem : card.getBodyShowItems()) {
      String key = bodyitem.getKey();
      if (setNoEdit.contains(key)) {
        bodyitem.setEdit(false);
      }
    }
  }
}
