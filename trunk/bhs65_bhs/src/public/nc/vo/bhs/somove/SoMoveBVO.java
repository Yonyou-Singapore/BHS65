package nc.vo.bhs.somove;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

/**
 * <b> 此处简要描述此类功能 </b>
 * <p>
 *   此处添加累的描述信息
 * </p>
 *  创建日期:2017-10-18
 * @author YONYOU NC
 * @version NCPrj ??
 */
 
public class SoMoveBVO extends SuperVO {
	
/**
*子表主键
*/
public String billid_b;
/**
*集团
*/
public String pk_group;
/**
*组织
*/
public String pk_org;
/**
*组织版本
*/
public String pk_org_v;
/**
*行号
*/
public String rowno;
/**
*来源单据类型
*/
public String vsrctype;
/**
*来源交易类型
*/
public String vsrctrantype;
/**
*来源单据号
*/
public String vsrccode;
/**
*来源单据主表主键
*/
public String csrcid;
/**
*来源单据表体主键
*/
public String csrcbid;
/**
*来源单据行号
*/
public String vsrcrowno;
/**
*源头单据类型
*/
public String vfirsttype;
/**
*源头交易类型
*/
public String vfirsttrantype;
/**
*源头单据号
*/
public String vfirstcode;
/**
*源头单据主表主键
*/
public String cfirstid;
/**
*源头单据表体主键
*/
public String cfirstbid;
/**
*源头单据行号
*/
public String vfirstrowno;
/**
*自定义项1
*/
public String def1;
/**
*自定义项2
*/
public String def2;
/**
*自定义项3
*/
public String def3;
/**
*自定义项4
*/
public String def4;
/**
*自定义项5
*/
public String def5;
/**
*自定义项6
*/
public String def6;
/**
*自定义项7
*/
public String def7;
/**
*自定义项8
*/
public String def8;
/**
*自定义项9
*/
public String def9;
/**
*自定义项10
*/
public String def10;
/**
*自定义项11
*/
public String def11;
/**
*自定义项12
*/
public String def12;
/**
*自定义项13
*/
public String def13;
/**
*自定义项14
*/
public String def14;
/**
*自定义项15
*/
public String def15;
/**
*自定义项16
*/
public String def16;
/**
*自定义项17
*/
public String def17;
/**
*自定义项18
*/
public String def18;
/**
*自定义项19
*/
public String def19;
/**
*自定义项20
*/
public String def20;
/**
*S/NO.
*/
public String sno;
/**
*Description
*/
public String description;
/**
*Length (CM)
*/
public UFDouble vlengthcm;
/**
*Width (CM)
*/
public UFDouble vwidthcm;
/**
*Heigth (CM)
*/
public UFDouble vheightcm;
/**
*Gross Weigth (KG)
*/
public UFDouble vgrossweigthkg;
/**
*Net Weight (KG)
*/
public UFDouble vnetweightkg;
/**
*Remark
*/
public String vremark;
/**
*上层单据主键
*/
public String billid;
/**
*时间戳
*/
public UFDateTime ts;
    
    
/**
* 属性 billid_b的Getter方法.属性名：子表主键
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getBillid_b() {
return this.billid_b;
} 

/**
* 属性billid_b的Setter方法.属性名：子表主键
* 创建日期:2017-10-18
* @param newBillid_b java.lang.String
*/
public void setBillid_b ( String billid_b) {
this.billid_b=billid_b;
} 
 
/**
* 属性 pk_group的Getter方法.属性名：集团
*  创建日期:2017-10-18
* @return nc.vo.org.GroupVO
*/
public String getPk_group() {
return this.pk_group;
} 

/**
* 属性pk_group的Setter方法.属性名：集团
* 创建日期:2017-10-18
* @param newPk_group nc.vo.org.GroupVO
*/
public void setPk_group ( String pk_group) {
this.pk_group=pk_group;
} 
 
/**
* 属性 pk_org的Getter方法.属性名：组织
*  创建日期:2017-10-18
* @return nc.vo.org.OrgVO
*/
public String getPk_org() {
return this.pk_org;
} 

/**
* 属性pk_org的Setter方法.属性名：组织
* 创建日期:2017-10-18
* @param newPk_org nc.vo.org.OrgVO
*/
public void setPk_org ( String pk_org) {
this.pk_org=pk_org;
} 
 
/**
* 属性 pk_org_v的Getter方法.属性名：组织版本
*  创建日期:2017-10-18
* @return nc.vo.vorg.OrgVersionVO
*/
public String getPk_org_v() {
return this.pk_org_v;
} 

/**
* 属性pk_org_v的Setter方法.属性名：组织版本
* 创建日期:2017-10-18
* @param newPk_org_v nc.vo.vorg.OrgVersionVO
*/
public void setPk_org_v ( String pk_org_v) {
this.pk_org_v=pk_org_v;
} 
 
/**
* 属性 rowno的Getter方法.属性名：行号
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getRowno() {
return this.rowno;
} 

/**
* 属性rowno的Setter方法.属性名：行号
* 创建日期:2017-10-18
* @param newRowno java.lang.String
*/
public void setRowno ( String rowno) {
this.rowno=rowno;
} 
 
/**
* 属性 vsrctype的Getter方法.属性名：来源单据类型
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getVsrctype() {
return this.vsrctype;
} 

/**
* 属性vsrctype的Setter方法.属性名：来源单据类型
* 创建日期:2017-10-18
* @param newVsrctype java.lang.String
*/
public void setVsrctype ( String vsrctype) {
this.vsrctype=vsrctype;
} 
 
/**
* 属性 vsrctrantype的Getter方法.属性名：来源交易类型
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getVsrctrantype() {
return this.vsrctrantype;
} 

/**
* 属性vsrctrantype的Setter方法.属性名：来源交易类型
* 创建日期:2017-10-18
* @param newVsrctrantype java.lang.String
*/
public void setVsrctrantype ( String vsrctrantype) {
this.vsrctrantype=vsrctrantype;
} 
 
/**
* 属性 vsrccode的Getter方法.属性名：来源单据号
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getVsrccode() {
return this.vsrccode;
} 

/**
* 属性vsrccode的Setter方法.属性名：来源单据号
* 创建日期:2017-10-18
* @param newVsrccode java.lang.String
*/
public void setVsrccode ( String vsrccode) {
this.vsrccode=vsrccode;
} 
 
/**
* 属性 csrcid的Getter方法.属性名：来源单据主表主键
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getCsrcid() {
return this.csrcid;
} 

/**
* 属性csrcid的Setter方法.属性名：来源单据主表主键
* 创建日期:2017-10-18
* @param newCsrcid java.lang.String
*/
public void setCsrcid ( String csrcid) {
this.csrcid=csrcid;
} 
 
/**
* 属性 csrcbid的Getter方法.属性名：来源单据表体主键
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getCsrcbid() {
return this.csrcbid;
} 

/**
* 属性csrcbid的Setter方法.属性名：来源单据表体主键
* 创建日期:2017-10-18
* @param newCsrcbid java.lang.String
*/
public void setCsrcbid ( String csrcbid) {
this.csrcbid=csrcbid;
} 
 
/**
* 属性 vsrcrowno的Getter方法.属性名：来源单据行号
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getVsrcrowno() {
return this.vsrcrowno;
} 

/**
* 属性vsrcrowno的Setter方法.属性名：来源单据行号
* 创建日期:2017-10-18
* @param newVsrcrowno java.lang.String
*/
public void setVsrcrowno ( String vsrcrowno) {
this.vsrcrowno=vsrcrowno;
} 
 
/**
* 属性 vfirsttype的Getter方法.属性名：源头单据类型
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getVfirsttype() {
return this.vfirsttype;
} 

/**
* 属性vfirsttype的Setter方法.属性名：源头单据类型
* 创建日期:2017-10-18
* @param newVfirsttype java.lang.String
*/
public void setVfirsttype ( String vfirsttype) {
this.vfirsttype=vfirsttype;
} 
 
/**
* 属性 vfirsttrantype的Getter方法.属性名：源头交易类型
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getVfirsttrantype() {
return this.vfirsttrantype;
} 

/**
* 属性vfirsttrantype的Setter方法.属性名：源头交易类型
* 创建日期:2017-10-18
* @param newVfirsttrantype java.lang.String
*/
public void setVfirsttrantype ( String vfirsttrantype) {
this.vfirsttrantype=vfirsttrantype;
} 
 
/**
* 属性 vfirstcode的Getter方法.属性名：源头单据号
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getVfirstcode() {
return this.vfirstcode;
} 

/**
* 属性vfirstcode的Setter方法.属性名：源头单据号
* 创建日期:2017-10-18
* @param newVfirstcode java.lang.String
*/
public void setVfirstcode ( String vfirstcode) {
this.vfirstcode=vfirstcode;
} 
 
/**
* 属性 cfirstid的Getter方法.属性名：源头单据主表主键
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getCfirstid() {
return this.cfirstid;
} 

/**
* 属性cfirstid的Setter方法.属性名：源头单据主表主键
* 创建日期:2017-10-18
* @param newCfirstid java.lang.String
*/
public void setCfirstid ( String cfirstid) {
this.cfirstid=cfirstid;
} 
 
/**
* 属性 cfirstbid的Getter方法.属性名：源头单据表体主键
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getCfirstbid() {
return this.cfirstbid;
} 

/**
* 属性cfirstbid的Setter方法.属性名：源头单据表体主键
* 创建日期:2017-10-18
* @param newCfirstbid java.lang.String
*/
public void setCfirstbid ( String cfirstbid) {
this.cfirstbid=cfirstbid;
} 
 
/**
* 属性 vfirstrowno的Getter方法.属性名：源头单据行号
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getVfirstrowno() {
return this.vfirstrowno;
} 

/**
* 属性vfirstrowno的Setter方法.属性名：源头单据行号
* 创建日期:2017-10-18
* @param newVfirstrowno java.lang.String
*/
public void setVfirstrowno ( String vfirstrowno) {
this.vfirstrowno=vfirstrowno;
} 
 
/**
* 属性 def1的Getter方法.属性名：自定义项1
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getDef1() {
return this.def1;
} 

/**
* 属性def1的Setter方法.属性名：自定义项1
* 创建日期:2017-10-18
* @param newDef1 java.lang.String
*/
public void setDef1 ( String def1) {
this.def1=def1;
} 
 
/**
* 属性 def2的Getter方法.属性名：自定义项2
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getDef2() {
return this.def2;
} 

/**
* 属性def2的Setter方法.属性名：自定义项2
* 创建日期:2017-10-18
* @param newDef2 java.lang.String
*/
public void setDef2 ( String def2) {
this.def2=def2;
} 
 
/**
* 属性 def3的Getter方法.属性名：自定义项3
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getDef3() {
return this.def3;
} 

/**
* 属性def3的Setter方法.属性名：自定义项3
* 创建日期:2017-10-18
* @param newDef3 java.lang.String
*/
public void setDef3 ( String def3) {
this.def3=def3;
} 
 
/**
* 属性 def4的Getter方法.属性名：自定义项4
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getDef4() {
return this.def4;
} 

/**
* 属性def4的Setter方法.属性名：自定义项4
* 创建日期:2017-10-18
* @param newDef4 java.lang.String
*/
public void setDef4 ( String def4) {
this.def4=def4;
} 
 
/**
* 属性 def5的Getter方法.属性名：自定义项5
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getDef5() {
return this.def5;
} 

/**
* 属性def5的Setter方法.属性名：自定义项5
* 创建日期:2017-10-18
* @param newDef5 java.lang.String
*/
public void setDef5 ( String def5) {
this.def5=def5;
} 
 
/**
* 属性 def6的Getter方法.属性名：自定义项6
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getDef6() {
return this.def6;
} 

/**
* 属性def6的Setter方法.属性名：自定义项6
* 创建日期:2017-10-18
* @param newDef6 java.lang.String
*/
public void setDef6 ( String def6) {
this.def6=def6;
} 
 
/**
* 属性 def7的Getter方法.属性名：自定义项7
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getDef7() {
return this.def7;
} 

/**
* 属性def7的Setter方法.属性名：自定义项7
* 创建日期:2017-10-18
* @param newDef7 java.lang.String
*/
public void setDef7 ( String def7) {
this.def7=def7;
} 
 
/**
* 属性 def8的Getter方法.属性名：自定义项8
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getDef8() {
return this.def8;
} 

/**
* 属性def8的Setter方法.属性名：自定义项8
* 创建日期:2017-10-18
* @param newDef8 java.lang.String
*/
public void setDef8 ( String def8) {
this.def8=def8;
} 
 
/**
* 属性 def9的Getter方法.属性名：自定义项9
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getDef9() {
return this.def9;
} 

/**
* 属性def9的Setter方法.属性名：自定义项9
* 创建日期:2017-10-18
* @param newDef9 java.lang.String
*/
public void setDef9 ( String def9) {
this.def9=def9;
} 
 
/**
* 属性 def10的Getter方法.属性名：自定义项10
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getDef10() {
return this.def10;
} 

/**
* 属性def10的Setter方法.属性名：自定义项10
* 创建日期:2017-10-18
* @param newDef10 java.lang.String
*/
public void setDef10 ( String def10) {
this.def10=def10;
} 
 
/**
* 属性 def11的Getter方法.属性名：自定义项11
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getDef11() {
return this.def11;
} 

/**
* 属性def11的Setter方法.属性名：自定义项11
* 创建日期:2017-10-18
* @param newDef11 java.lang.String
*/
public void setDef11 ( String def11) {
this.def11=def11;
} 
 
/**
* 属性 def12的Getter方法.属性名：自定义项12
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getDef12() {
return this.def12;
} 

/**
* 属性def12的Setter方法.属性名：自定义项12
* 创建日期:2017-10-18
* @param newDef12 java.lang.String
*/
public void setDef12 ( String def12) {
this.def12=def12;
} 
 
/**
* 属性 def13的Getter方法.属性名：自定义项13
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getDef13() {
return this.def13;
} 

/**
* 属性def13的Setter方法.属性名：自定义项13
* 创建日期:2017-10-18
* @param newDef13 java.lang.String
*/
public void setDef13 ( String def13) {
this.def13=def13;
} 
 
/**
* 属性 def14的Getter方法.属性名：自定义项14
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getDef14() {
return this.def14;
} 

/**
* 属性def14的Setter方法.属性名：自定义项14
* 创建日期:2017-10-18
* @param newDef14 java.lang.String
*/
public void setDef14 ( String def14) {
this.def14=def14;
} 
 
/**
* 属性 def15的Getter方法.属性名：自定义项15
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getDef15() {
return this.def15;
} 

/**
* 属性def15的Setter方法.属性名：自定义项15
* 创建日期:2017-10-18
* @param newDef15 java.lang.String
*/
public void setDef15 ( String def15) {
this.def15=def15;
} 
 
/**
* 属性 def16的Getter方法.属性名：自定义项16
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getDef16() {
return this.def16;
} 

/**
* 属性def16的Setter方法.属性名：自定义项16
* 创建日期:2017-10-18
* @param newDef16 java.lang.String
*/
public void setDef16 ( String def16) {
this.def16=def16;
} 
 
/**
* 属性 def17的Getter方法.属性名：自定义项17
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getDef17() {
return this.def17;
} 

/**
* 属性def17的Setter方法.属性名：自定义项17
* 创建日期:2017-10-18
* @param newDef17 java.lang.String
*/
public void setDef17 ( String def17) {
this.def17=def17;
} 
 
/**
* 属性 def18的Getter方法.属性名：自定义项18
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getDef18() {
return this.def18;
} 

/**
* 属性def18的Setter方法.属性名：自定义项18
* 创建日期:2017-10-18
* @param newDef18 java.lang.String
*/
public void setDef18 ( String def18) {
this.def18=def18;
} 
 
/**
* 属性 def19的Getter方法.属性名：自定义项19
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getDef19() {
return this.def19;
} 

/**
* 属性def19的Setter方法.属性名：自定义项19
* 创建日期:2017-10-18
* @param newDef19 java.lang.String
*/
public void setDef19 ( String def19) {
this.def19=def19;
} 
 
/**
* 属性 def20的Getter方法.属性名：自定义项20
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getDef20() {
return this.def20;
} 

/**
* 属性def20的Setter方法.属性名：自定义项20
* 创建日期:2017-10-18
* @param newDef20 java.lang.String
*/
public void setDef20 ( String def20) {
this.def20=def20;
} 
 
/**
* 属性 sno的Getter方法.属性名：S/NO.
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getSno() {
return this.sno;
} 

/**
* 属性sno的Setter方法.属性名：S/NO.
* 创建日期:2017-10-18
* @param newSno java.lang.String
*/
public void setSno ( String sno) {
this.sno=sno;
} 
 
/**
* 属性 description的Getter方法.属性名：Description
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getDescription() {
return this.description;
} 

/**
* 属性description的Setter方法.属性名：Description
* 创建日期:2017-10-18
* @param newDescription java.lang.String
*/
public void setDescription ( String description) {
this.description=description;
} 
 
/**
* 属性 vlengthcm的Getter方法.属性名：Length (CM)
*  创建日期:2017-10-18
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getVlengthcm() {
return this.vlengthcm;
} 

/**
* 属性vlengthcm的Setter方法.属性名：Length (CM)
* 创建日期:2017-10-18
* @param newVlengthcm nc.vo.pub.lang.UFDouble
*/
public void setVlengthcm ( UFDouble vlengthcm) {
this.vlengthcm=vlengthcm;
} 
 
/**
* 属性 vwidthcm的Getter方法.属性名：Width (CM)
*  创建日期:2017-10-18
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getVwidthcm() {
return this.vwidthcm;
} 

/**
* 属性vwidthcm的Setter方法.属性名：Width (CM)
* 创建日期:2017-10-18
* @param newVwidthcm nc.vo.pub.lang.UFDouble
*/
public void setVwidthcm ( UFDouble vwidthcm) {
this.vwidthcm=vwidthcm;
} 
 
/**
* 属性 vheightcm的Getter方法.属性名：Heigth (CM)
*  创建日期:2017-10-18
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getVheightcm() {
return this.vheightcm;
} 

/**
* 属性vheightcm的Setter方法.属性名：Heigth (CM)
* 创建日期:2017-10-18
* @param newVheightcm nc.vo.pub.lang.UFDouble
*/
public void setVheightcm ( UFDouble vheightcm) {
this.vheightcm=vheightcm;
} 
 
/**
* 属性 vgrossweigthkg的Getter方法.属性名：Gross Weigth (KG)
*  创建日期:2017-10-18
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getVgrossweigthkg() {
return this.vgrossweigthkg;
} 

/**
* 属性vgrossweigthkg的Setter方法.属性名：Gross Weigth (KG)
* 创建日期:2017-10-18
* @param newVgrossweigthkg nc.vo.pub.lang.UFDouble
*/
public void setVgrossweigthkg ( UFDouble vgrossweigthkg) {
this.vgrossweigthkg=vgrossweigthkg;
} 
 
/**
* 属性 vnetweightkg的Getter方法.属性名：Net Weight (KG)
*  创建日期:2017-10-18
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getVnetweightkg() {
return this.vnetweightkg;
} 

/**
* 属性vnetweightkg的Setter方法.属性名：Net Weight (KG)
* 创建日期:2017-10-18
* @param newVnetweightkg nc.vo.pub.lang.UFDouble
*/
public void setVnetweightkg ( UFDouble vnetweightkg) {
this.vnetweightkg=vnetweightkg;
} 
 
/**
* 属性 vremark的Getter方法.属性名：Remark
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getVremark() {
return this.vremark;
} 

/**
* 属性vremark的Setter方法.属性名：Remark
* 创建日期:2017-10-18
* @param newVremark java.lang.String
*/
public void setVremark ( String vremark) {
this.vremark=vremark;
} 
 
/**
* 属性 生成上层主键的Getter方法.属性名：上层主键
*  创建日期:2017-10-18
* @return String
*/
public String getBillid(){
return this.billid;
}
/**
* 属性生成上层主键的Setter方法.属性名：上层主键
* 创建日期:2017-10-18
* @param newBillid String
*/
public void setBillid(String billid){
this.billid=billid;
} 
/**
* 属性 生成时间戳的Getter方法.属性名：时间戳
*  创建日期:2017-10-18
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getTs() {
return this.ts;
}
/**
* 属性生成时间戳的Setter方法.属性名：时间戳
* 创建日期:2017-10-18
* @param newts nc.vo.pub.lang.UFDateTime
*/
public void setTs(UFDateTime ts){
this.ts=ts;
} 
     
    @Override
    public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("BHS.SoMoveBVO");
    }
   }
    