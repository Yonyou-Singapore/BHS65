package nc.vo.bhs.pack;

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
 
public class SoOrderPackBVO extends SuperVO {
	
/**
*子表主键
*/
public String billid_b;
/**
*行号
*/
public String rowno;
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
*S/N
*/
public String snno;
/**
*Description
*/
public String packdescription;
/**
*Qty
*/
public UFDouble packqty;
/**
*cargodimensionl
*/
public UFDouble cargodimensionl;
/**
*cargodimensionw
*/
public UFDouble cargodimensionw;
/**
*cargodimensionh
*/
public UFDouble cargodimensionh;
/**
*cargodimensionweight
*/
public UFDouble cargodimensionweight;
/**
*internaldimensionl
*/
public UFDouble internaldimensionl;
/**
*internaldimensionw
*/
public UFDouble internaldimensionw;
/**
*internaldimensionh
*/
public UFDouble internaldimensionh;
/**
*Internal M3
*/
public UFDouble internalm3;
/**
*cratedimensionl
*/
public UFDouble cratedimensionl;
/**
*cratedimensionw
*/
public UFDouble cratedimensionw;
/**
*cratedimensionh
*/
public UFDouble cratedimensionh;
/**
*Weight
*/
public UFDouble cratedimensionweight;
/**
*Remark
*/
public String packremark;
/**
*Unit px per crate
*/
public UFDouble uppc;
/**
*External M3
*/
public UFDouble externalm3;
/**
*Machine Description
*/
public String machinedescription;
/**
*Machine Length
*/
public UFDouble machinelength;
/**
*Machine Width
*/
public UFDouble machinewidth;
/**
*Machine Height
*/
public UFDouble machineheight;
/**
*Full Box/Skid
*/
public String fullboxskid;
/**
*If Full Box
*/
public String iffullbox;
/**
*2 way/4 way
*/
public String way4way;
/**
*Vacum/Normal
*/
public String vacumnormal;
/**
*Cleat
*/
public String cleat;
/**
*External Securing
*/
public String externalsecuring;
/**
*Additional External
*/
public String additionalexternal;
/**
*Skid Type
*/
public String skidtype;
/**
*Floating/Non Floating
*/
public String floating;
/**
*Securing to Skid
*/
public String securingtoskid;
/**
*Additional Securing to Skid
*/
public String addsecuringtoskid;
/**
*Pest Management
*/
public String pestmanagement;
/**
*Shock Watch
*/
public String shockwatch;
/**
*Shock Watch Qty/Crate
*/
public UFDouble shockwatchqtycrate;
/**
*Tilt Watch
*/
public String tiltwatch;
/**
*Tilt Watch Qty/Crate
*/
public UFDouble tiltwatchqtycrate;
/**
*Remarks
*/
public String remarks;
/**
*Machine Description1
*/
public String machinedescri;
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
* 属性 snno的Getter方法.属性名：S/N
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getSnno() {
return this.snno;
} 

/**
* 属性snno的Setter方法.属性名：S/N
* 创建日期:2017-10-18
* @param newSnno java.lang.String
*/
public void setSnno ( String snno) {
this.snno=snno;
} 
 
/**
* 属性 packdescription的Getter方法.属性名：Description
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getPackdescription() {
return this.packdescription;
} 

/**
* 属性packdescription的Setter方法.属性名：Description
* 创建日期:2017-10-18
* @param newPackdescription java.lang.String
*/
public void setPackdescription ( String packdescription) {
this.packdescription=packdescription;
} 
 
/**
* 属性 packqty的Getter方法.属性名：Qty
*  创建日期:2017-10-18
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getPackqty() {
return this.packqty;
} 

/**
* 属性packqty的Setter方法.属性名：Qty
* 创建日期:2017-10-18
* @param newPackqty nc.vo.pub.lang.UFDouble
*/
public void setPackqty ( UFDouble packqty) {
this.packqty=packqty;
} 
 
/**
* 属性 cargodimensionl的Getter方法.属性名：cargodimensionl
*  创建日期:2017-10-18
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getCargodimensionl() {
return this.cargodimensionl;
} 

/**
* 属性cargodimensionl的Setter方法.属性名：cargodimensionl
* 创建日期:2017-10-18
* @param newCargodimensionl nc.vo.pub.lang.UFDouble
*/
public void setCargodimensionl ( UFDouble cargodimensionl) {
this.cargodimensionl=cargodimensionl;
} 
 
/**
* 属性 cargodimensionw的Getter方法.属性名：cargodimensionw
*  创建日期:2017-10-18
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getCargodimensionw() {
return this.cargodimensionw;
} 

/**
* 属性cargodimensionw的Setter方法.属性名：cargodimensionw
* 创建日期:2017-10-18
* @param newCargodimensionw nc.vo.pub.lang.UFDouble
*/
public void setCargodimensionw ( UFDouble cargodimensionw) {
this.cargodimensionw=cargodimensionw;
} 
 
/**
* 属性 cargodimensionh的Getter方法.属性名：cargodimensionh
*  创建日期:2017-10-18
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getCargodimensionh() {
return this.cargodimensionh;
} 

/**
* 属性cargodimensionh的Setter方法.属性名：cargodimensionh
* 创建日期:2017-10-18
* @param newCargodimensionh nc.vo.pub.lang.UFDouble
*/
public void setCargodimensionh ( UFDouble cargodimensionh) {
this.cargodimensionh=cargodimensionh;
} 
 
/**
* 属性 cargodimensionweight的Getter方法.属性名：cargodimensionweight
*  创建日期:2017-10-18
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getCargodimensionweight() {
return this.cargodimensionweight;
} 

/**
* 属性cargodimensionweight的Setter方法.属性名：cargodimensionweight
* 创建日期:2017-10-18
* @param newCargodimensionweight nc.vo.pub.lang.UFDouble
*/
public void setCargodimensionweight ( UFDouble cargodimensionweight) {
this.cargodimensionweight=cargodimensionweight;
} 
 
/**
* 属性 internaldimensionl的Getter方法.属性名：internaldimensionl
*  创建日期:2017-10-18
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getInternaldimensionl() {
return this.internaldimensionl;
} 

/**
* 属性internaldimensionl的Setter方法.属性名：internaldimensionl
* 创建日期:2017-10-18
* @param newInternaldimensionl nc.vo.pub.lang.UFDouble
*/
public void setInternaldimensionl ( UFDouble internaldimensionl) {
this.internaldimensionl=internaldimensionl;
} 
 
/**
* 属性 internaldimensionw的Getter方法.属性名：internaldimensionw
*  创建日期:2017-10-18
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getInternaldimensionw() {
return this.internaldimensionw;
} 

/**
* 属性internaldimensionw的Setter方法.属性名：internaldimensionw
* 创建日期:2017-10-18
* @param newInternaldimensionw nc.vo.pub.lang.UFDouble
*/
public void setInternaldimensionw ( UFDouble internaldimensionw) {
this.internaldimensionw=internaldimensionw;
} 
 
/**
* 属性 internaldimensionh的Getter方法.属性名：internaldimensionh
*  创建日期:2017-10-18
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getInternaldimensionh() {
return this.internaldimensionh;
} 

/**
* 属性internaldimensionh的Setter方法.属性名：internaldimensionh
* 创建日期:2017-10-18
* @param newInternaldimensionh nc.vo.pub.lang.UFDouble
*/
public void setInternaldimensionh ( UFDouble internaldimensionh) {
this.internaldimensionh=internaldimensionh;
} 
 
/**
* 属性 internalm3的Getter方法.属性名：Internal M3
*  创建日期:2017-10-18
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getInternalm3() {
return this.internalm3;
} 

/**
* 属性internalm3的Setter方法.属性名：Internal M3
* 创建日期:2017-10-18
* @param newInternalm3 nc.vo.pub.lang.UFDouble
*/
public void setInternalm3 ( UFDouble internalm3) {
this.internalm3=internalm3;
} 
 
/**
* 属性 cratedimensionl的Getter方法.属性名：cratedimensionl
*  创建日期:2017-10-18
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getCratedimensionl() {
return this.cratedimensionl;
} 

/**
* 属性cratedimensionl的Setter方法.属性名：cratedimensionl
* 创建日期:2017-10-18
* @param newCratedimensionl nc.vo.pub.lang.UFDouble
*/
public void setCratedimensionl ( UFDouble cratedimensionl) {
this.cratedimensionl=cratedimensionl;
} 
 
/**
* 属性 cratedimensionw的Getter方法.属性名：cratedimensionw
*  创建日期:2017-10-18
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getCratedimensionw() {
return this.cratedimensionw;
} 

/**
* 属性cratedimensionw的Setter方法.属性名：cratedimensionw
* 创建日期:2017-10-18
* @param newCratedimensionw nc.vo.pub.lang.UFDouble
*/
public void setCratedimensionw ( UFDouble cratedimensionw) {
this.cratedimensionw=cratedimensionw;
} 
 
/**
* 属性 cratedimensionh的Getter方法.属性名：cratedimensionh
*  创建日期:2017-10-18
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getCratedimensionh() {
return this.cratedimensionh;
} 

/**
* 属性cratedimensionh的Setter方法.属性名：cratedimensionh
* 创建日期:2017-10-18
* @param newCratedimensionh nc.vo.pub.lang.UFDouble
*/
public void setCratedimensionh ( UFDouble cratedimensionh) {
this.cratedimensionh=cratedimensionh;
} 
 
/**
* 属性 cratedimensionweight的Getter方法.属性名：Weight
*  创建日期:2017-10-18
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getCratedimensionweight() {
return this.cratedimensionweight;
} 

/**
* 属性cratedimensionweight的Setter方法.属性名：Weight
* 创建日期:2017-10-18
* @param newCratedimensionweight nc.vo.pub.lang.UFDouble
*/
public void setCratedimensionweight ( UFDouble cratedimensionweight) {
this.cratedimensionweight=cratedimensionweight;
} 
 
/**
* 属性 packremark的Getter方法.属性名：Remark
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getPackremark() {
return this.packremark;
} 

/**
* 属性packremark的Setter方法.属性名：Remark
* 创建日期:2017-10-18
* @param newPackremark java.lang.String
*/
public void setPackremark ( String packremark) {
this.packremark=packremark;
} 
 
/**
* 属性 uppc的Getter方法.属性名：Unit px per crate
*  创建日期:2017-10-18
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getUppc() {
return this.uppc;
} 

/**
* 属性uppc的Setter方法.属性名：Unit px per crate
* 创建日期:2017-10-18
* @param newUppc nc.vo.pub.lang.UFDouble
*/
public void setUppc ( UFDouble uppc) {
this.uppc=uppc;
} 
 
/**
* 属性 externalm3的Getter方法.属性名：External M3
*  创建日期:2017-10-18
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getExternalm3() {
return this.externalm3;
} 

/**
* 属性externalm3的Setter方法.属性名：External M3
* 创建日期:2017-10-18
* @param newExternalm3 nc.vo.pub.lang.UFDouble
*/
public void setExternalm3 ( UFDouble externalm3) {
this.externalm3=externalm3;
} 
 
/**
* 属性 machinedescription的Getter方法.属性名：Machine Description
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getMachinedescription() {
return this.machinedescription;
} 

/**
* 属性machinedescription的Setter方法.属性名：Machine Description
* 创建日期:2017-10-18
* @param newMachinedescription java.lang.String
*/
public void setMachinedescription ( String machinedescription) {
this.machinedescription=machinedescription;
} 
 
/**
* 属性 machinelength的Getter方法.属性名：Machine Length
*  创建日期:2017-10-18
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getMachinelength() {
return this.machinelength;
} 

/**
* 属性machinelength的Setter方法.属性名：Machine Length
* 创建日期:2017-10-18
* @param newMachinelength nc.vo.pub.lang.UFDouble
*/
public void setMachinelength ( UFDouble machinelength) {
this.machinelength=machinelength;
} 
 
/**
* 属性 machinewidth的Getter方法.属性名：Machine Width
*  创建日期:2017-10-18
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getMachinewidth() {
return this.machinewidth;
} 

/**
* 属性machinewidth的Setter方法.属性名：Machine Width
* 创建日期:2017-10-18
* @param newMachinewidth nc.vo.pub.lang.UFDouble
*/
public void setMachinewidth ( UFDouble machinewidth) {
this.machinewidth=machinewidth;
} 
 
/**
* 属性 machineheight的Getter方法.属性名：Machine Height
*  创建日期:2017-10-18
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getMachineheight() {
return this.machineheight;
} 

/**
* 属性machineheight的Setter方法.属性名：Machine Height
* 创建日期:2017-10-18
* @param newMachineheight nc.vo.pub.lang.UFDouble
*/
public void setMachineheight ( UFDouble machineheight) {
this.machineheight=machineheight;
} 
 
/**
* 属性 fullboxskid的Getter方法.属性名：Full Box/Skid
*  创建日期:2017-10-18
* @return nc.vo.bhs.pack.Enumerate
*/
public String getFullboxskid() {
return this.fullboxskid;
} 

/**
* 属性fullboxskid的Setter方法.属性名：Full Box/Skid
* 创建日期:2017-10-18
* @param newFullboxskid nc.vo.bhs.pack.Enumerate
*/
public void setFullboxskid ( String fullboxskid) {
this.fullboxskid=fullboxskid;
} 
 
/**
* 属性 iffullbox的Getter方法.属性名：If Full Box
*  创建日期:2017-10-18
* @return nc.vo.bhs.pack.Enumerate
*/
public String getIffullbox() {
return this.iffullbox;
} 

/**
* 属性iffullbox的Setter方法.属性名：If Full Box
* 创建日期:2017-10-18
* @param newIffullbox nc.vo.bhs.pack.Enumerate
*/
public void setIffullbox ( String iffullbox) {
this.iffullbox=iffullbox;
} 
 
/**
* 属性 way4way的Getter方法.属性名：2 way/4 way
*  创建日期:2017-10-18
* @return nc.vo.bhs.pack.Enumerate
*/
public String getWay4way() {
return this.way4way;
} 

/**
* 属性way4way的Setter方法.属性名：2 way/4 way
* 创建日期:2017-10-18
* @param newWay4way nc.vo.bhs.pack.Enumerate
*/
public void setWay4way ( String way4way) {
this.way4way=way4way;
} 
 
/**
* 属性 vacumnormal的Getter方法.属性名：Vacum/Normal
*  创建日期:2017-10-18
* @return nc.vo.bhs.pack.Enumerate
*/
public String getVacumnormal() {
return this.vacumnormal;
} 

/**
* 属性vacumnormal的Setter方法.属性名：Vacum/Normal
* 创建日期:2017-10-18
* @param newVacumnormal nc.vo.bhs.pack.Enumerate
*/
public void setVacumnormal ( String vacumnormal) {
this.vacumnormal=vacumnormal;
} 
 
/**
* 属性 cleat的Getter方法.属性名：Cleat
*  创建日期:2017-10-18
* @return nc.vo.bhs.pack.Enumerate
*/
public String getCleat() {
return this.cleat;
} 

/**
* 属性cleat的Setter方法.属性名：Cleat
* 创建日期:2017-10-18
* @param newCleat nc.vo.bhs.pack.Enumerate
*/
public void setCleat ( String cleat) {
this.cleat=cleat;
} 
 
/**
* 属性 externalsecuring的Getter方法.属性名：External Securing
*  创建日期:2017-10-18
* @return nc.vo.bhs.pack.Enumerate
*/
public String getExternalsecuring() {
return this.externalsecuring;
} 

/**
* 属性externalsecuring的Setter方法.属性名：External Securing
* 创建日期:2017-10-18
* @param newExternalsecuring nc.vo.bhs.pack.Enumerate
*/
public void setExternalsecuring ( String externalsecuring) {
this.externalsecuring=externalsecuring;
} 
 
/**
* 属性 additionalexternal的Getter方法.属性名：Additional External
*  创建日期:2017-10-18
* @return nc.vo.bhs.pack.Enumerate
*/
public String getAdditionalexternal() {
return this.additionalexternal;
} 

/**
* 属性additionalexternal的Setter方法.属性名：Additional External
* 创建日期:2017-10-18
* @param newAdditionalexternal nc.vo.bhs.pack.Enumerate
*/
public void setAdditionalexternal ( String additionalexternal) {
this.additionalexternal=additionalexternal;
} 
 
/**
* 属性 skidtype的Getter方法.属性名：Skid Type
*  创建日期:2017-10-18
* @return nc.vo.bhs.pack.Enumerate
*/
public String getSkidtype() {
return this.skidtype;
} 

/**
* 属性skidtype的Setter方法.属性名：Skid Type
* 创建日期:2017-10-18
* @param newSkidtype nc.vo.bhs.pack.Enumerate
*/
public void setSkidtype ( String skidtype) {
this.skidtype=skidtype;
} 
 
/**
* 属性 floating的Getter方法.属性名：Floating/Non Floating
*  创建日期:2017-10-18
* @return nc.vo.bhs.pack.Enumerate
*/
public String getFloating() {
return this.floating;
} 

/**
* 属性floating的Setter方法.属性名：Floating/Non Floating
* 创建日期:2017-10-18
* @param newFloating nc.vo.bhs.pack.Enumerate
*/
public void setFloating ( String floating) {
this.floating=floating;
} 
 
/**
* 属性 securingtoskid的Getter方法.属性名：Securing to Skid
*  创建日期:2017-10-18
* @return nc.vo.bhs.pack.Enumerate
*/
public String getSecuringtoskid() {
return this.securingtoskid;
} 

/**
* 属性securingtoskid的Setter方法.属性名：Securing to Skid
* 创建日期:2017-10-18
* @param newSecuringtoskid nc.vo.bhs.pack.Enumerate
*/
public void setSecuringtoskid ( String securingtoskid) {
this.securingtoskid=securingtoskid;
} 
 
/**
* 属性 addsecuringtoskid的Getter方法.属性名：Additional Securing to Skid
*  创建日期:2017-10-18
* @return nc.vo.bhs.pack.Enumerate
*/
public String getAddsecuringtoskid() {
return this.addsecuringtoskid;
} 

/**
* 属性addsecuringtoskid的Setter方法.属性名：Additional Securing to Skid
* 创建日期:2017-10-18
* @param newAddsecuringtoskid nc.vo.bhs.pack.Enumerate
*/
public void setAddsecuringtoskid ( String addsecuringtoskid) {
this.addsecuringtoskid=addsecuringtoskid;
} 
 
/**
* 属性 pestmanagement的Getter方法.属性名：Pest Management
*  创建日期:2017-10-18
* @return nc.vo.bhs.pack.Enumerate
*/
public String getPestmanagement() {
return this.pestmanagement;
} 

/**
* 属性pestmanagement的Setter方法.属性名：Pest Management
* 创建日期:2017-10-18
* @param newPestmanagement nc.vo.bhs.pack.Enumerate
*/
public void setPestmanagement ( String pestmanagement) {
this.pestmanagement=pestmanagement;
} 
 
/**
* 属性 shockwatch的Getter方法.属性名：Shock Watch
*  创建日期:2017-10-18
* @return nc.vo.bhs.pack.Enumerate
*/
public String getShockwatch() {
return this.shockwatch;
} 

/**
* 属性shockwatch的Setter方法.属性名：Shock Watch
* 创建日期:2017-10-18
* @param newShockwatch nc.vo.bhs.pack.Enumerate
*/
public void setShockwatch ( String shockwatch) {
this.shockwatch=shockwatch;
} 
 
/**
* 属性 shockwatchqtycrate的Getter方法.属性名：Shock Watch Qty/Crate
*  创建日期:2017-10-18
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getShockwatchqtycrate() {
return this.shockwatchqtycrate;
} 

/**
* 属性shockwatchqtycrate的Setter方法.属性名：Shock Watch Qty/Crate
* 创建日期:2017-10-18
* @param newShockwatchqtycrate nc.vo.pub.lang.UFDouble
*/
public void setShockwatchqtycrate ( UFDouble shockwatchqtycrate) {
this.shockwatchqtycrate=shockwatchqtycrate;
} 
 
/**
* 属性 tiltwatch的Getter方法.属性名：Tilt Watch
*  创建日期:2017-10-18
* @return nc.vo.bhs.pack.Enumerate
*/
public String getTiltwatch() {
return this.tiltwatch;
} 

/**
* 属性tiltwatch的Setter方法.属性名：Tilt Watch
* 创建日期:2017-10-18
* @param newTiltwatch nc.vo.bhs.pack.Enumerate
*/
public void setTiltwatch ( String tiltwatch) {
this.tiltwatch=tiltwatch;
} 
 
/**
* 属性 tiltwatchqtycrate的Getter方法.属性名：Tilt Watch Qty/Crate
*  创建日期:2017-10-18
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getTiltwatchqtycrate() {
return this.tiltwatchqtycrate;
} 

/**
* 属性tiltwatchqtycrate的Setter方法.属性名：Tilt Watch Qty/Crate
* 创建日期:2017-10-18
* @param newTiltwatchqtycrate nc.vo.pub.lang.UFDouble
*/
public void setTiltwatchqtycrate ( UFDouble tiltwatchqtycrate) {
this.tiltwatchqtycrate=tiltwatchqtycrate;
} 
 
/**
* 属性 remarks的Getter方法.属性名：Remarks
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getRemarks() {
return this.remarks;
} 

/**
* 属性remarks的Setter方法.属性名：Remarks
* 创建日期:2017-10-18
* @param newRemarks java.lang.String
*/
public void setRemarks ( String remarks) {
this.remarks=remarks;
} 
 
/**
* 属性 machinedescri的Getter方法.属性名：Machine Description1
*  创建日期:2017-10-18
* @return nc.vo.bhs.pack.Enumerate
*/
public String getMachinedescri() {
return this.machinedescri;
} 

/**
* 属性machinedescri的Setter方法.属性名：Machine Description1
* 创建日期:2017-10-18
* @param newMachinedescri nc.vo.bhs.pack.Enumerate
*/
public void setMachinedescri ( String machinedescri) {
this.machinedescri=machinedescri;
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
    return VOMetaFactory.getInstance().getVOMeta("bhs.SoOrderPackBVO");
    }
   }
    