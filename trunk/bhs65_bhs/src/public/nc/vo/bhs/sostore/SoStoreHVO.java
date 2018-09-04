package nc.vo.bhs.sostore;

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
 * @author 
 * @version NCPrj ??
 */
 
public class SoStoreHVO extends SuperVO {
	
/**
*主表主键
*/
public String billid;
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
*单据号
*/
public String vbillno;
/**
*单据日期
*/
public UFDate dbilldate;
/**
*单据状态
*/
public Integer vbillstatus;
/**
*业务类型
*/
public String pk_busitype;
/**
*单据类型
*/
public String pk_billtype;
/**
*交易类型编码
*/
public String transtypecode;
/**
*交易类型pk
*/
public String pk_transtype;
/**
*制单人
*/
public String creator;
/**
*制单时间
*/
public UFDateTime creationtime;
/**
*最后修改人
*/
public String modifier;
/**
*最后修改时间
*/
public UFDateTime modifiedtime;
/**
*审批人
*/
public String approver;
/**
*审批时间
*/
public UFDateTime approvedate;
/**
*审批批语
*/
public String approvenote;
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
*PO #
*/
public String pono;
/**
*Purpose
*/
public String purpose;
/**
*Duration UOM
*/
public String durationuom;
/**
*Rate Type
*/
public String ratetype;
/**
*Measurement UOM
*/
public String measurementuom;
/**
*Rate Amt
*/
public UFDouble rateamt;
/**
*Quantity UOM
*/
public String quantityuom;
/**
*Date In
*/
public UFDate datein;
/**
*Storage Type
*/
public String storagetype;
/**
*Warehouse Zone
*/
public String warehousezone;
/**
*Stored At/Location
*/
public String storedatlocation;
/**
*CS In-Charge
*/
public String csincharge;
/**
*Order Remarks
*/
public String orderremarks;
/**
*订单主键
*/
public String csaleorderid;
/**
*订单号
*/
public String csaleordercode;
/**
*Attn To
*/
public String attnto;
/**
*Contact No
*/
public String contactno;
/**
*Contact Person
*/
public String contactperson;
/**
*subject
*/
public String subject;
/**
*jobInstruction
*/
public String jobinstruction;
/**
*Ref
*/
public String ref;
/**
*时间戳
*/
public UFDateTime ts;
    

/**
*M2
*/
public UFDouble summarym2;
/**
*M3
*/
public UFDouble summarym3;
/**
*kg
*/
public UFDouble summarykg;
/**
*pkgs
*/
public UFDouble summarypkgs;

/**
*no of shock watches
*/
public UFDouble noofshockwatches;
/**
*no of tilt watches
*/
public UFDouble nooftiltwatches;
/**
*Largest length
*/
public UFDouble largestlength;
/**
*Largest width
*/
public UFDouble largestwidth;
/**
*Largest height
*/
public UFDouble largestheight;
/**
*Largest weight
*/
public UFDouble largestweight;

/**
*Micap No
*/
public String micapno;
/**
*OEM Tool No
*/
public String oemtoolno;
/**
*Tool ID/LID
*/
public String toolidlid;
/**
*Machine Make
*/
public String machinemake;
/**
*Machine Model
*/
public String machinemodel;
/**
*Machine Submodel
*/
public String machinesubmodel;
/**
*Supplier Name
*/
public String suppliername;
/**
*Lcrate#
*/
public String lcrate;
/**
*Wcrate#
*/
public String wcrate;
/**
*Hcrate#
*/
public String hcrate;
/**
*Kcrate#
*/
public String kcrate;

public UFBoolean istopplerisk;
public String toppleriskcrateno;
public String damagedcrateno;
public String cratestatus;
public UFBoolean iscentralgrapoint;

    
/**
* 属性 billid的Getter方法.属性名：主表主键
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getBillid() {
return this.billid;
} 

/**
* 属性billid的Setter方法.属性名：主表主键
* 创建日期:2017-10-18
* @param newBillid java.lang.String
*/
public void setBillid ( String billid) {
this.billid=billid;
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
* 属性 vbillno的Getter方法.属性名：单据号
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getVbillno() {
return this.vbillno;
} 

/**
* 属性vbillno的Setter方法.属性名：单据号
* 创建日期:2017-10-18
* @param newVbillno java.lang.String
*/
public void setVbillno ( String vbillno) {
this.vbillno=vbillno;
} 
 
/**
* 属性 dbilldate的Getter方法.属性名：单据日期
*  创建日期:2017-10-18
* @return nc.vo.pub.lang.UFDate
*/
public UFDate getDbilldate() {
return this.dbilldate;
} 

/**
* 属性dbilldate的Setter方法.属性名：单据日期
* 创建日期:2017-10-18
* @param newDbilldate nc.vo.pub.lang.UFDate
*/
public void setDbilldate ( UFDate dbilldate) {
this.dbilldate=dbilldate;
} 
 
/**
* 属性 vbillstatus的Getter方法.属性名：单据状态
*  创建日期:2017-10-18
* @return nc.vo.pub.pf.BillStatusEnum
*/
public Integer getVbillstatus() {
return this.vbillstatus;
} 

/**
* 属性vbillstatus的Setter方法.属性名：单据状态
* 创建日期:2017-10-18
* @param newVbillstatus nc.vo.pub.pf.BillStatusEnum
*/
public void setVbillstatus ( Integer vbillstatus) {
this.vbillstatus=vbillstatus;
} 
 
/**
* 属性 pk_busitype的Getter方法.属性名：业务类型
*  创建日期:2017-10-18
* @return nc.vo.pf.pub.BusitypeVO
*/
public String getPk_busitype() {
return this.pk_busitype;
} 

/**
* 属性pk_busitype的Setter方法.属性名：业务类型
* 创建日期:2017-10-18
* @param newPk_busitype nc.vo.pf.pub.BusitypeVO
*/
public void setPk_busitype ( String pk_busitype) {
this.pk_busitype=pk_busitype;
} 
 
/**
* 属性 pk_billtype的Getter方法.属性名：单据类型
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getPk_billtype() {
return this.pk_billtype;
} 

/**
* 属性pk_billtype的Setter方法.属性名：单据类型
* 创建日期:2017-10-18
* @param newPk_billtype java.lang.String
*/
public void setPk_billtype ( String pk_billtype) {
this.pk_billtype=pk_billtype;
} 
 
/**
* 属性 transtypecode的Getter方法.属性名：交易类型编码
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getTranstypecode() {
return this.transtypecode;
} 

/**
* 属性transtypecode的Setter方法.属性名：交易类型编码
* 创建日期:2017-10-18
* @param newTranstypecode java.lang.String
*/
public void setTranstypecode ( String transtypecode) {
this.transtypecode=transtypecode;
} 
 
/**
* 属性 pk_transtype的Getter方法.属性名：交易类型pk
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getPk_transtype() {
return this.pk_transtype;
} 

/**
* 属性pk_transtype的Setter方法.属性名：交易类型pk
* 创建日期:2017-10-18
* @param newPk_transtype java.lang.String
*/
public void setPk_transtype ( String pk_transtype) {
this.pk_transtype=pk_transtype;
} 
 
/**
* 属性 creator的Getter方法.属性名：制单人
*  创建日期:2017-10-18
* @return nc.vo.sm.UserVO
*/
public String getCreator() {
return this.creator;
} 

/**
* 属性creator的Setter方法.属性名：制单人
* 创建日期:2017-10-18
* @param newCreator nc.vo.sm.UserVO
*/
public void setCreator ( String creator) {
this.creator=creator;
} 
 
/**
* 属性 creationtime的Getter方法.属性名：制单时间
*  创建日期:2017-10-18
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getCreationtime() {
return this.creationtime;
} 

/**
* 属性creationtime的Setter方法.属性名：制单时间
* 创建日期:2017-10-18
* @param newCreationtime nc.vo.pub.lang.UFDateTime
*/
public void setCreationtime ( UFDateTime creationtime) {
this.creationtime=creationtime;
} 
 
/**
* 属性 modifier的Getter方法.属性名：最后修改人
*  创建日期:2017-10-18
* @return nc.vo.sm.UserVO
*/
public String getModifier() {
return this.modifier;
} 

/**
* 属性modifier的Setter方法.属性名：最后修改人
* 创建日期:2017-10-18
* @param newModifier nc.vo.sm.UserVO
*/
public void setModifier ( String modifier) {
this.modifier=modifier;
} 
 
/**
* 属性 modifiedtime的Getter方法.属性名：最后修改时间
*  创建日期:2017-10-18
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getModifiedtime() {
return this.modifiedtime;
} 

/**
* 属性modifiedtime的Setter方法.属性名：最后修改时间
* 创建日期:2017-10-18
* @param newModifiedtime nc.vo.pub.lang.UFDateTime
*/
public void setModifiedtime ( UFDateTime modifiedtime) {
this.modifiedtime=modifiedtime;
} 
 
/**
* 属性 approver的Getter方法.属性名：审批人
*  创建日期:2017-10-18
* @return nc.vo.sm.UserVO
*/
public String getApprover() {
return this.approver;
} 

/**
* 属性approver的Setter方法.属性名：审批人
* 创建日期:2017-10-18
* @param newApprover nc.vo.sm.UserVO
*/
public void setApprover ( String approver) {
this.approver=approver;
} 
 
/**
* 属性 approvedate的Getter方法.属性名：审批时间
*  创建日期:2017-10-18
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getApprovedate() {
return this.approvedate;
} 

/**
* 属性approvedate的Setter方法.属性名：审批时间
* 创建日期:2017-10-18
* @param newApprovedate nc.vo.pub.lang.UFDateTime
*/
public void setApprovedate ( UFDateTime approvedate) {
this.approvedate=approvedate;
} 
 
/**
* 属性 approvenote的Getter方法.属性名：审批批语
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getApprovenote() {
return this.approvenote;
} 

/**
* 属性approvenote的Setter方法.属性名：审批批语
* 创建日期:2017-10-18
* @param newApprovenote java.lang.String
*/
public void setApprovenote ( String approvenote) {
this.approvenote=approvenote;
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
* 属性 pono的Getter方法.属性名：PO #
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getPono() {
return this.pono;
} 

/**
* 属性pono的Setter方法.属性名：PO #
* 创建日期:2017-10-18
* @param newPono java.lang.String
*/
public void setPono ( String pono) {
this.pono=pono;
} 
 
/**
* 属性 purpose的Getter方法.属性名：Purpose
*  创建日期:2017-10-18
* @return nc.vo.bhs.sostore.EnumPurpose
*/
public String getPurpose() {
return this.purpose;
} 

/**
* 属性purpose的Setter方法.属性名：Purpose
* 创建日期:2017-10-18
* @param newPurpose nc.vo.bhs.sostore.EnumPurpose
*/
public void setPurpose ( String purpose) {
this.purpose=purpose;
} 
 
/**
* 属性 durationuom的Getter方法.属性名：Duration UOM
*  创建日期:2017-10-18
* @return nc.vo.bd.material.measdoc.MeasdocVO
*/
public String getDurationuom() {
return this.durationuom;
} 

/**
* 属性durationuom的Setter方法.属性名：Duration UOM
* 创建日期:2017-10-18
* @param newDurationuom nc.vo.bd.material.measdoc.MeasdocVO
*/
public void setDurationuom ( String durationuom) {
this.durationuom=durationuom;
} 
 
/**
* 属性 ratetype的Getter方法.属性名：Rate Type
*  创建日期:2017-10-18
* @return nc.vo.bhs.sostore.Enumerate
*/
public String getRatetype() {
return this.ratetype;
} 

/**
* 属性ratetype的Setter方法.属性名：Rate Type
* 创建日期:2017-10-18
* @param newRatetype nc.vo.bhs.sostore.Enumerate
*/
public void setRatetype ( String ratetype) {
this.ratetype=ratetype;
} 
 
/**
* 属性 measurementuom的Getter方法.属性名：Measurement UOM
*  创建日期:2017-10-18
* @return nc.vo.bd.material.measdoc.MeasdocVO
*/
public String getMeasurementuom() {
return this.measurementuom;
} 

/**
* 属性measurementuom的Setter方法.属性名：Measurement UOM
* 创建日期:2017-10-18
* @param newMeasurementuom nc.vo.bd.material.measdoc.MeasdocVO
*/
public void setMeasurementuom ( String measurementuom) {
this.measurementuom=measurementuom;
} 
 
/**
* 属性 rateamt的Getter方法.属性名：Rate Amt
*  创建日期:2017-10-18
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getRateamt() {
return this.rateamt;
} 

/**
* 属性rateamt的Setter方法.属性名：Rate Amt
* 创建日期:2017-10-18
* @param newRateamt nc.vo.pub.lang.UFDouble
*/
public void setRateamt ( UFDouble rateamt) {
this.rateamt=rateamt;
} 
 
/**
* 属性 quantityuom的Getter方法.属性名：Quantity UOM
*  创建日期:2017-10-18
* @return nc.vo.bd.material.measdoc.MeasdocVO
*/
public String getQuantityuom() {
return this.quantityuom;
} 

/**
* 属性quantityuom的Setter方法.属性名：Quantity UOM
* 创建日期:2017-10-18
* @param newQuantityuom nc.vo.bd.material.measdoc.MeasdocVO
*/
public void setQuantityuom ( String quantityuom) {
this.quantityuom=quantityuom;
} 
 
/**
* 属性 datein的Getter方法.属性名：Date In
*  创建日期:2017-10-18
* @return nc.vo.pub.lang.UFDate
*/
public UFDate getDatein() {
return this.datein;
} 

/**
* 属性datein的Setter方法.属性名：Date In
* 创建日期:2017-10-18
* @param newDatein nc.vo.pub.lang.UFDate
*/
public void setDatein ( UFDate datein) {
this.datein=datein;
} 
 
/**
* 属性 storagetype的Getter方法.属性名：Storage Type
*  创建日期:2017-10-18
* @return nc.vo.bhs.sostore.EnumStoragetype
*/
public String getStoragetype() {
return this.storagetype;
} 

/**
* 属性storagetype的Setter方法.属性名：Storage Type
* 创建日期:2017-10-18
* @param newStoragetype nc.vo.bhs.sostore.EnumStoragetype
*/
public void setStoragetype ( String storagetype) {
this.storagetype=storagetype;
} 
 
/**
* 属性 warehousezone的Getter方法.属性名：Warehouse Zone
*  创建日期:2017-10-18
* @return nc.vo.bd.stordoc.StordocVO
*/
public String getWarehousezone() {
return this.warehousezone;
} 

/**
* 属性warehousezone的Setter方法.属性名：Warehouse Zone
* 创建日期:2017-10-18
* @param newWarehousezone nc.vo.bd.stordoc.StordocVO
*/
public void setWarehousezone ( String warehousezone) {
this.warehousezone=warehousezone;
} 
 
/**
* 属性 storedatlocation的Getter方法.属性名：Stored At/Location
*  创建日期:2017-10-18
* @return nc.vo.bd.rack.RackVO
*/
public String getStoredatlocation() {
return this.storedatlocation;
} 

/**
* 属性storedatlocation的Setter方法.属性名：Stored At/Location
* 创建日期:2017-10-18
* @param newStoredatlocation nc.vo.bd.rack.RackVO
*/
public void setStoredatlocation ( String storedatlocation) {
this.storedatlocation=storedatlocation;
} 
 
/**
* 属性 csincharge的Getter方法.属性名：CS In-Charge
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getCsincharge() {
return this.csincharge;
} 

/**
* 属性csincharge的Setter方法.属性名：CS In-Charge
* 创建日期:2017-10-18
* @param newCsincharge java.lang.String
*/
public void setCsincharge ( String csincharge) {
this.csincharge=csincharge;
} 
 
/**
* 属性 orderremarks的Getter方法.属性名：Order Remarks
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getOrderremarks() {
return this.orderremarks;
} 

/**
* 属性orderremarks的Setter方法.属性名：Order Remarks
* 创建日期:2017-10-18
* @param newOrderremarks java.lang.String
*/
public void setOrderremarks ( String orderremarks) {
this.orderremarks=orderremarks;
} 
 
/**
* 属性 csaleorderid的Getter方法.属性名：订单主键
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getCsaleorderid() {
return this.csaleorderid;
} 

/**
* 属性csaleorderid的Setter方法.属性名：订单主键
* 创建日期:2017-10-18
* @param newCsaleorderid java.lang.String
*/
public void setCsaleorderid ( String csaleorderid) {
this.csaleorderid=csaleorderid;
} 
 
/**
* 属性 csaleordercode的Getter方法.属性名：订单号
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getCsaleordercode() {
return this.csaleordercode;
} 

/**
* 属性csaleordercode的Setter方法.属性名：订单号
* 创建日期:2017-10-18
* @param newCsaleordercode java.lang.String
*/
public void setCsaleordercode ( String csaleordercode) {
this.csaleordercode=csaleordercode;
} 
 
/**
* 属性 attnto的Getter方法.属性名：Attn To
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getAttnto() {
return this.attnto;
} 

/**
* 属性attnto的Setter方法.属性名：Attn To
* 创建日期:2017-10-18
* @param newAttnto java.lang.String
*/
public void setAttnto ( String attnto) {
this.attnto=attnto;
} 
 
/**
* 属性 contactno的Getter方法.属性名：Contact No
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getContactno() {
return this.contactno;
} 

/**
* 属性contactno的Setter方法.属性名：Contact No
* 创建日期:2017-10-18
* @param newContactno java.lang.String
*/
public void setContactno ( String contactno) {
this.contactno=contactno;
} 
 
/**
* 属性 contactperson的Getter方法.属性名：Contact Person
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getContactperson() {
return this.contactperson;
} 

/**
* 属性contactperson的Setter方法.属性名：Contact Person
* 创建日期:2017-10-18
* @param newContactperson java.lang.String
*/
public void setContactperson ( String contactperson) {
this.contactperson=contactperson;
} 
 
/**
* 属性 subject的Getter方法.属性名：subject
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getSubject() {
return this.subject;
} 

/**
* 属性subject的Setter方法.属性名：subject
* 创建日期:2017-10-18
* @param newSubject java.lang.String
*/
public void setSubject ( String subject) {
this.subject=subject;
} 
 
/**
* 属性 jobinstruction的Getter方法.属性名：jobInstruction
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getJobinstruction() {
return this.jobinstruction;
} 

/**
* 属性jobinstruction的Setter方法.属性名：jobInstruction
* 创建日期:2017-10-18
* @param newJobinstruction java.lang.String
*/
public void setJobinstruction ( String jobinstruction) {
this.jobinstruction=jobinstruction;
} 
 
/**
* 属性 ref的Getter方法.属性名：Ref
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getRef() {
return this.ref;
} 

/**
* 属性ref的Setter方法.属性名：Ref
* 创建日期:2017-10-18
* @param newRef java.lang.String
*/
public void setRef ( String ref) {
this.ref=ref;
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
    return VOMetaFactory.getInstance().getVOMeta("BHS.SoStoreHVO");
    }

	public UFDouble getSummarym2() {
		return summarym2;
	}

	public void setSummarym2(UFDouble summarym2) {
		this.summarym2 = summarym2;
	}

	public UFDouble getSummarym3() {
		return summarym3;
	}

	public void setSummarym3(UFDouble summarym3) {
		this.summarym3 = summarym3;
	}

	public UFDouble getSummarykg() {
		return summarykg;
	}

	public void setSummarykg(UFDouble summarykg) {
		this.summarykg = summarykg;
	}

	public UFDouble getNoofshockwatches() {
		return noofshockwatches;
	}

	public void setNoofshockwatches(UFDouble noofshockwatches) {
		this.noofshockwatches = noofshockwatches;
	}

	public UFDouble getNooftiltwatches() {
		return nooftiltwatches;
	}

	public void setNooftiltwatches(UFDouble nooftiltwatches) {
		this.nooftiltwatches = nooftiltwatches;
	}

	public UFDouble getLargestlength() {
		return largestlength;
	}

	public void setLargestlength(UFDouble largestlength) {
		this.largestlength = largestlength;
	}

	public UFDouble getLargestwidth() {
		return largestwidth;
	}

	public void setLargestwidth(UFDouble largestwidth) {
		this.largestwidth = largestwidth;
	}

	public UFDouble getLargestheight() {
		return largestheight;
	}

	public void setLargestheight(UFDouble largestheight) {
		this.largestheight = largestheight;
	}

	public UFDouble getLargestweight() {
		return largestweight;
	}

	public void setLargestweight(UFDouble largestweight) {
		this.largestweight = largestweight;
	}

	public String getMicapno() {
		return micapno;
	}

	public void setMicapno(String micapno) {
		this.micapno = micapno;
	}

	public String getOemtoolno() {
		return oemtoolno;
	}

	public void setOemtoolno(String oemtoolno) {
		this.oemtoolno = oemtoolno;
	}

	public String getToolidlid() {
		return toolidlid;
	}

	public void setToolidlid(String toolidlid) {
		this.toolidlid = toolidlid;
	}

	public String getMachinemake() {
		return machinemake;
	}

	public void setMachinemake(String machinemake) {
		this.machinemake = machinemake;
	}

	public String getMachinemodel() {
		return machinemodel;
	}

	public void setMachinemodel(String machinemodel) {
		this.machinemodel = machinemodel;
	}

	public String getMachinesubmodel() {
		return machinesubmodel;
	}

	public void setMachinesubmodel(String machinesubmodel) {
		this.machinesubmodel = machinesubmodel;
	}

	public String getSuppliername() {
		return suppliername;
	}

	public void setSuppliername(String suppliername) {
		this.suppliername = suppliername;
	}

	public String getLcrate() {
		return lcrate;
	}

	public void setLcrate(String lcrate) {
		this.lcrate = lcrate;
	}

	public String getWcrate() {
		return wcrate;
	}

	public void setWcrate(String wcrate) {
		this.wcrate = wcrate;
	}

	public String getHcrate() {
		return hcrate;
	}

	public void setHcrate(String hcrate) {
		this.hcrate = hcrate;
	}

	public String getKcrate() {
		return kcrate;
	}

	public void setKcrate(String kcrate) {
		this.kcrate = kcrate;
	}

	public UFBoolean getIstopplerisk() {
		return istopplerisk;
	}

	public void setIstopplerisk(UFBoolean istopplerisk) {
		this.istopplerisk = istopplerisk;
	}

	public String getToppleriskcrateno() {
		return toppleriskcrateno;
	}

	public void setToppleriskcrateno(String toppleriskcrateno) {
		this.toppleriskcrateno = toppleriskcrateno;
	}

	public String getDamagedcrateno() {
		return damagedcrateno;
	}

	public void setDamagedcrateno(String damagedcrateno) {
		this.damagedcrateno = damagedcrateno;
	}

	public String getCratestatus() {
		return cratestatus;
	}

	public void setCratestatus(String cratestatus) {
		this.cratestatus = cratestatus;
	}

	public UFBoolean getIscentralgrapoint() {
		return iscentralgrapoint;
	}

	public void setIscentralgrapoint(UFBoolean iscentralgrapoint) {
		this.iscentralgrapoint = iscentralgrapoint;
	}

	public UFDouble getSummarypkgs() {
		return summarypkgs;
	}

	public void setSummarypkgs(UFDouble summarypkgs) {
		this.summarypkgs = summarypkgs;
	}
   }
    