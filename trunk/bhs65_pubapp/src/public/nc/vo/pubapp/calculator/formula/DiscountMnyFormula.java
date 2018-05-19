package nc.vo.pubapp.calculator.formula;

import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.calculator.CalculatorUtil;
import nc.vo.pubapp.calculator.data.CacheDataSet;
import nc.vo.pubapp.scale.ScaleUtils;

/**
 * 主单位原币折扣额=含税单价*数量-主单位原币价税合计
 * 
 * 含税单价可以是：主单位原币含税单价、报价单位原币含税单价 数量可以是：主数量、报价数量
 * 
 * 2017-09-12本地化改动：折扣额以无税金额为准
 * by Ethan Wu
 */
public class DiscountMnyFormula {
  // 主单位原币币种id
  private String currid;

  private CacheDataSet data;

  // 原币折扣额
  private String discountMnyKey;

  // 主数量key（报价单位数量key）
  private String numKey;

  private ScaleUtils scale;

  // 主单位原币价税合计key
  private String sunmnyKey;

  // 主单位原币含税净价
  private String taxNetPriceKey;

  // 主单位原币含税单价（报价单位原币含税单价）key
  private String taxPriceKey;
  
  // 主单位原币无税单价key
  private String priceKey = "norigprice";
  
  // 主单位原币无税金额key
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

  /** 主单位原币折扣额 */
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
    // 初始化主单位原币折扣额精度
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
