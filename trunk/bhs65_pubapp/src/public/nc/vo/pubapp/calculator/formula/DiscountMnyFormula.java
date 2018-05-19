package nc.vo.pubapp.calculator.formula;

import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.calculator.CalculatorUtil;
import nc.vo.pubapp.calculator.data.CacheDataSet;
import nc.vo.pubapp.scale.ScaleUtils;

/**
 * ����λԭ���ۿ۶�=��˰����*����-����λԭ�Ҽ�˰�ϼ�
 * 
 * ��˰���ۿ����ǣ�����λԭ�Һ�˰���ۡ����۵�λԭ�Һ�˰���� ���������ǣ�����������������
 * 
 * 2017-09-12���ػ��Ķ����ۿ۶�����˰���Ϊ׼
 * by Ethan Wu
 */
public class DiscountMnyFormula {
  // ����λԭ�ұ���id
  private String currid;

  private CacheDataSet data;

  // ԭ���ۿ۶�
  private String discountMnyKey;

  // ������key�����۵�λ����key��
  private String numKey;

  private ScaleUtils scale;

  // ����λԭ�Ҽ�˰�ϼ�key
  private String sunmnyKey;

  // ����λԭ�Һ�˰����
  private String taxNetPriceKey;

  // ����λԭ�Һ�˰���ۣ����۵�λԭ�Һ�˰���ۣ�key
  private String taxPriceKey;
  
  // ����λԭ����˰����key
  private String priceKey = "norigprice";
  
  // ����λԭ����˰���key
  private String moneyKey = "norigmny";

  public DiscountMnyFormula(CacheDataSet data, ScaleUtils scale,
      String taxPriceKey, String numKey, String summnyKey,
      String discountMnyKey, String currid) {
    this.data = data;
    this.scale = scale;
    this.taxPriceKey = taxPriceKey;
    // this.taxNetPriceKey = taxNetPriceKey;
    this.numKey = numKey;
    this.sunmnyKey = summnyKey;
    this.discountMnyKey = discountMnyKey;
    this.currid = currid;
  }

  /** ����λԭ���ۿ۶� */
  public void calculateDiscountMny() {
    UFDouble taxPrice =
        (UFDouble) this.data.getAttributeValue(priceKey);
    UFDouble taxNetPrice =
        (UFDouble) this.data.getAttributeValue(this.taxNetPriceKey);

    UFDouble value = null;
    if ((taxPrice != null) && (taxNetPrice != null)
        && taxPrice.equals(taxNetPrice)) {
      value = UFDouble.ZERO_DBL;
    }
    else {
      UFDouble num = (UFDouble) this.data.getAttributeValue(this.numKey);
      UFDouble summny = (UFDouble) this.data.getAttributeValue(moneyKey);
      taxPrice = CalculatorUtil.multiply(taxPrice, num);
      value = CalculatorUtil.sub(taxPrice, summny);
    }
    String curr = (String) this.data.getAttributeValue(this.currid);
    // ��ʼ������λԭ���ۿ۶��
    value = this.scale.adjustMnyScale(value, curr);
    this.data.setAttributeValue(this.discountMnyKey, value);
  }

  public String getTaxNetPriceKey() {
    return this.taxNetPriceKey;
  }

  public void setTaxNetPriceKey(String taxNetPriceKey) {
    this.taxNetPriceKey = taxNetPriceKey;
  }
}
