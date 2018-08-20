package nc.vo.bhs.sotruck;

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
 
public class SoTruckHVO extends SuperVO {
	
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
*TsTransaction Date
*/
public UFDate tstransactiondate;
/**
*TsJob Order #
*/
public String tsjoborderno;
/**
*TsCustomer PO#
*/
public String tscustomerpono;
/**
*TsQuotation #
*/
public String tsquotationno;
/**
*TsCustomer Preferred Date/Time
*/
public UFDateTime tspreferreddatetime;
/**
*TsVessel
*/
public String tsimportvessel;
/**
*TsPort of Loading
*/
public String tsportofloading;
/**
*TsVoyage
*/
public String tsimportvoyage;
/**
*TsAgent
*/
public String tsimportagent;
/**
*TsPermit #
*/
public String tsimportpermitno;
/**
*TsPolice escort permit # (if applicable)
*/
public String tspoliceescortpermitno;
/**
*TsLink To Contractor
*/
public String tslinktocontractor;
/**
*TsReturn to yard
*/
public String tsreturntoyard;
/**
*TsETA
*/
public UFDate tsimporteta;
/**
*TsAIR RIDE
*/
public UFBoolean tsairride;
/**
*TsUNSTUFFING
*/
public UFBoolean tsunstuffing;
/**
*TsLOOSE CARGO DELIVER
*/
public UFBoolean tsloosecargodeliver;
/**
*TsTruck Instruction
*/
public String tstruckinstruction;
/**
*TsMove Instruction
*/
public String tsmoveinstruction;
/**
*TsJob Instruction
*/
public String tsjobinstruction;
/**
*TsCS COMMENT
*/
public String tscscomment;
/**
*TsFrom
*/
public String tsfrom;
/**
*TsPort
*/
public String tsport;
/**
*TsTo
*/
public String tsto;
/**
*TsfeTransaction Date
*/
public UFDate tsfeextransactiondate;
/**
*TsfeJob Order #
*/
public String tsfeexjoborderno;
/**
*TsfeCustomer PO#
*/
public String tsfeexcustomerpono;
/**
*TsfeQuotation #
*/
public String tsfeexquotationno;
/**
*TsfeCustomer Preferred Date/Time
*/
public UFDateTime tsfeexpreferreddatetime;
/**
*TsfeVessel
*/
public String tsfeexportvessel;
/**
*TsfeVoyage
*/
public String tsfeexportvoyage;
/**
*TsfeAgent
*/
public String tsfeexportagent;
/**
*TsfePermit #
*/
public String tsfeexportpermitno;
/**
*TsfeBooking Ref
*/
public String tsfebookingref;
/**
*TsfePortnet Ref
*/
public String tsfeportnetref;
/**
*TsfePick up Ref
*/
public String tsfepickupref;
/**
*TsfePort of Discharge
*/
public String tsfeportofdischarge;
/**
*TsfePolice escort permit # (if applicable)
*/
public String tsfeexpoliceescortpermitno;
/**
*TsfeLink To Contractor
*/
public String tsfeexlinktocontractor;
/**
*TsfeEmpty Container Collection
*/
public String tsfeemptycntrcollection;
/**
*TsfeETA
*/
public UFDate tsfeexporteta;
/**
*TsfeFrom
*/
public String tsfefrom;
/**
*TsfeTo
*/
public String tsfeto;
/**
*TsfePort
*/
public String tsfeport;
/**
*TsfeAIR RIDE
*/
public UFBoolean tsfeairride;
/**
*TsfeUNSTUFFING
*/
public UFBoolean tsfeunstuffing;
/**
*TsfeLOOSE CARGO COLLECT
*/
public UFBoolean tsfeloosecargocollect;
/**
*TsfeLOOSE CARGO COLLECTs
*/
public String tsfeloosecargocollects;
/**
*TsfeTruck Instruction
*/
public String tsfetruckinstruction;
/**
*TsfeMove Instruction
*/
public String tsfemoveinstruction;
/**
*TsfeJob Instruction
*/
public String tsfejobinstruction;
/**
*TsfeCS COMMENT
*/
public String tsfecscomment;
/**
*Ta Job Type
*/
public String tajobtype;
/**
*Ta DO#
*/
public String tadono;
/**
*Ta Pickup Date
*/
public UFDate taapickupdate;
/**
*Ta Pickup Time
*/
public UFDateTime taapickuptime;
/**
*Ta Ondock Date
*/
public UFDate taaondockdate;
/**
*Ta Ondock Time
*/
public UFDateTime taaondocktime;
/**
*Ta Pickup From Name
*/
public String taapickupfromname;
/**
*Ta Pickup From Address
*/
public String taapickupfromaddress;
/**
*Ta Pickup From Code
*/
public String taapickupfromcode;
/**
*Ta Contact Person
*/
public String taacontactperson;
/**
*Ta Contact Person Phone
*/
public String taacontactpersonphone;
/**
*Ta Bpickup Date
*/
public UFDate tabpickupdate;
/**
*Ta Bpickup Time
*/
public UFDateTime tabpickuptime;
/**
*Ta Bondock Date
*/
public UFDate tabondockdate;
/**
*Ta Bondock Time
*/
public UFDateTime tabondocktime;
/**
*Ta Preload To/From Name
*/
public String tabpreloadtofromname;
/**
*Ta Preload To/From Address
*/
public String tabpreloadtofromaddres;
/**
*Ta Preload To/From Code
*/
public String tabpreloadtofromcode;
/**
*Ta Cpickup Date
*/
public UFDate tacpickupdate;
/**
*Ta Cpickup Time
*/
public UFDateTime tacpickuptime;
/**
*Ta Condock Date
*/
public UFDate tacondockdate;
/**
*Ta Condock Time
*/
public UFDateTime tacondocktime;
/**
*Ta Deliver To Name
*/
public String tacdelivertoname;
/**
*Ta Deliver To Address
*/
public String tacdelivertoaddress;
/**
*Ta Deliver To Code
*/
public String tacdelivertocode;
/**
*Ta Ccontact Person
*/
public String taccontactperson;
/**
*Ta Ccontact Person Phone
*/
public String taccontactpersonphone;
/**
*Ta AWB #
*/
public String taawbno;
/**
*Ta Total # Of Pkgs
*/
public UFDouble tatotalofpkgs;
/**
*Ta Chargable Weight(KG)
*/
public UFDouble tachargableweight;
/**
*Ta Volume(M3)
*/
public UFDouble tavolume;
/**
*Ta Gross Weight(KG)
*/
public UFDouble tagrossweight;
/**
*Ta Area (M2)
*/
public UFDouble taaream2;
/**
*Ta Length(CM)
*/
public UFDouble talengthcm;
/**
*Ta Crate #
*/
public UFDouble talcrateno;
/**
*Ta Width(CM)
*/
public UFDouble tawidthcm;
/**
*Ta Wcrate #
*/
public UFDouble tawcrateno;
/**
*Ta Height(CM)
*/
public UFDouble taheightcm;
/**
*Ta HCrate #
*/
public UFDouble tahcrateno;
/**
*Ta Special Instruction
*/
public String taspecialinstruction;
/**
*Ta Common Instruction
*/
public String tacommoninstruction;
/**
*订单主键
*/
public String csaleorderid;
/**
*订单号
*/
public String csaleordercode;
/**
*Survey No
*/
public String surveyno;
/**
*Survey By
*/
public String surveyby;
/**
*Survey Date
*/
public UFDate surveydate;
/**
*Customer PO No
*/
public String customerpono;
/**
*From Corp
*/
public String fromcorp;
/**
*To Corp
*/
public String tocorp;
/**
*Attn To
*/
public String attnto;
/**
*subject
*/
public String subject;
/**
*Ref
*/
public String ref;
/**
*Net Weight (KG)
*/
public UFDouble netweight;
/**
*Largest weight
*/
public UFDouble largestweight;
/**
*Kcrate#
*/
public String kcrate;
/**
*时间戳
*/
public UFDateTime ts;


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
* 属性 tstransactiondate的Getter方法.属性名：TsTransaction Date
*  创建日期:2017-10-18
* @return nc.vo.pub.lang.UFDate
*/
public UFDate getTstransactiondate() {
return this.tstransactiondate;
} 

/**
* 属性tstransactiondate的Setter方法.属性名：TsTransaction Date
* 创建日期:2017-10-18
* @param newTstransactiondate nc.vo.pub.lang.UFDate
*/
public void setTstransactiondate ( UFDate tstransactiondate) {
this.tstransactiondate=tstransactiondate;
} 
 
/**
* 属性 tsjoborderno的Getter方法.属性名：TsJob Order #
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getTsjoborderno() {
return this.tsjoborderno;
} 

/**
* 属性tsjoborderno的Setter方法.属性名：TsJob Order #
* 创建日期:2017-10-18
* @param newTsjoborderno java.lang.String
*/
public void setTsjoborderno ( String tsjoborderno) {
this.tsjoborderno=tsjoborderno;
} 
 
/**
* 属性 tscustomerpono的Getter方法.属性名：TsCustomer PO#
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getTscustomerpono() {
return this.tscustomerpono;
} 

/**
* 属性tscustomerpono的Setter方法.属性名：TsCustomer PO#
* 创建日期:2017-10-18
* @param newTscustomerpono java.lang.String
*/
public void setTscustomerpono ( String tscustomerpono) {
this.tscustomerpono=tscustomerpono;
} 
 
/**
* 属性 tsquotationno的Getter方法.属性名：TsQuotation #
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getTsquotationno() {
return this.tsquotationno;
} 

/**
* 属性tsquotationno的Setter方法.属性名：TsQuotation #
* 创建日期:2017-10-18
* @param newTsquotationno java.lang.String
*/
public void setTsquotationno ( String tsquotationno) {
this.tsquotationno=tsquotationno;
} 
 
/**
* 属性 tspreferreddatetime的Getter方法.属性名：TsCustomer Preferred Date/Time
*  创建日期:2017-10-18
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getTspreferreddatetime() {
return this.tspreferreddatetime;
} 

/**
* 属性tspreferreddatetime的Setter方法.属性名：TsCustomer Preferred Date/Time
* 创建日期:2017-10-18
* @param newTspreferreddatetime nc.vo.pub.lang.UFDateTime
*/
public void setTspreferreddatetime ( UFDateTime tspreferreddatetime) {
this.tspreferreddatetime=tspreferreddatetime;
} 
 
/**
* 属性 tsimportvessel的Getter方法.属性名：TsVessel
*  创建日期:2017-10-18
* @return nc.vo.bd.defdoc.DefdocVO
*/
public String getTsimportvessel() {
return this.tsimportvessel;
} 

/**
* 属性tsimportvessel的Setter方法.属性名：TsVessel
* 创建日期:2017-10-18
* @param newTsimportvessel nc.vo.bd.defdoc.DefdocVO
*/
public void setTsimportvessel ( String tsimportvessel) {
this.tsimportvessel=tsimportvessel;
} 
 
/**
* 属性 tsportofloading的Getter方法.属性名：TsPort of Loading
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getTsportofloading() {
return this.tsportofloading;
} 

/**
* 属性tsportofloading的Setter方法.属性名：TsPort of Loading
* 创建日期:2017-10-18
* @param newTsportofloading java.lang.String
*/
public void setTsportofloading ( String tsportofloading) {
this.tsportofloading=tsportofloading;
} 
 
/**
* 属性 tsimportvoyage的Getter方法.属性名：TsVoyage
*  创建日期:2017-10-18
* @return nc.vo.bd.defdoc.DefdocVO
*/
public String getTsimportvoyage() {
return this.tsimportvoyage;
} 

/**
* 属性tsimportvoyage的Setter方法.属性名：TsVoyage
* 创建日期:2017-10-18
* @param newTsimportvoyage nc.vo.bd.defdoc.DefdocVO
*/
public void setTsimportvoyage ( String tsimportvoyage) {
this.tsimportvoyage=tsimportvoyage;
} 
 
/**
* 属性 tsimportagent的Getter方法.属性名：TsAgent
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getTsimportagent() {
return this.tsimportagent;
} 

/**
* 属性tsimportagent的Setter方法.属性名：TsAgent
* 创建日期:2017-10-18
* @param newTsimportagent java.lang.String
*/
public void setTsimportagent ( String tsimportagent) {
this.tsimportagent=tsimportagent;
} 
 
/**
* 属性 tsimportpermitno的Getter方法.属性名：TsPermit #
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getTsimportpermitno() {
return this.tsimportpermitno;
} 

/**
* 属性tsimportpermitno的Setter方法.属性名：TsPermit #
* 创建日期:2017-10-18
* @param newTsimportpermitno java.lang.String
*/
public void setTsimportpermitno ( String tsimportpermitno) {
this.tsimportpermitno=tsimportpermitno;
} 
 
/**
* 属性 tspoliceescortpermitno的Getter方法.属性名：TsPolice escort permit # (if applicable)
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getTspoliceescortpermitno() {
return this.tspoliceescortpermitno;
} 

/**
* 属性tspoliceescortpermitno的Setter方法.属性名：TsPolice escort permit # (if applicable)
* 创建日期:2017-10-18
* @param newTspoliceescortpermitno java.lang.String
*/
public void setTspoliceescortpermitno ( String tspoliceescortpermitno) {
this.tspoliceescortpermitno=tspoliceescortpermitno;
} 
 
/**
* 属性 tslinktocontractor的Getter方法.属性名：TsLink To Contractor
*  创建日期:2017-10-18
* @return nc.vo.bd.supplier.SupplierVO
*/
public String getTslinktocontractor() {
return this.tslinktocontractor;
} 

/**
* 属性tslinktocontractor的Setter方法.属性名：TsLink To Contractor
* 创建日期:2017-10-18
* @param newTslinktocontractor nc.vo.bd.supplier.SupplierVO
*/
public void setTslinktocontractor ( String tslinktocontractor) {
this.tslinktocontractor=tslinktocontractor;
} 
 
/**
* 属性 tsreturntoyard的Getter方法.属性名：TsReturn to yard
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getTsreturntoyard() {
return this.tsreturntoyard;
} 

/**
* 属性tsreturntoyard的Setter方法.属性名：TsReturn to yard
* 创建日期:2017-10-18
* @param newTsreturntoyard java.lang.String
*/
public void setTsreturntoyard ( String tsreturntoyard) {
this.tsreturntoyard=tsreturntoyard;
} 
 
/**
* 属性 tsimporteta的Getter方法.属性名：TsETA
*  创建日期:2017-10-18
* @return nc.vo.pub.lang.UFDate
*/
public UFDate getTsimporteta() {
return this.tsimporteta;
} 

/**
* 属性tsimporteta的Setter方法.属性名：TsETA
* 创建日期:2017-10-18
* @param newTsimporteta nc.vo.pub.lang.UFDate
*/
public void setTsimporteta ( UFDate tsimporteta) {
this.tsimporteta=tsimporteta;
} 
 
/**
* 属性 tsairride的Getter方法.属性名：TsAIR RIDE
*  创建日期:2017-10-18
* @return java.lang.String
*/
public UFBoolean getTsairride() {
return this.tsairride;
} 

/**
* 属性tsairride的Setter方法.属性名：TsAIR RIDE
* 创建日期:2017-10-18
* @param newTsairride java.lang.String
*/
public void setTsairride ( UFBoolean tsairride) {
this.tsairride=tsairride;
} 
 
/**
* 属性 tsunstuffing的Getter方法.属性名：TsUNSTUFFING
*  创建日期:2017-10-18
* @return nc.vo.pub.lang.UFBoolean
*/
public UFBoolean getTsunstuffing() {
return this.tsunstuffing;
} 

/**
* 属性tsunstuffing的Setter方法.属性名：TsUNSTUFFING
* 创建日期:2017-10-18
* @param newTsunstuffing nc.vo.pub.lang.UFBoolean
*/
public void setTsunstuffing ( UFBoolean tsunstuffing) {
this.tsunstuffing=tsunstuffing;
} 
 
/**
* 属性 tsloosecargodeliver的Getter方法.属性名：TsLOOSE CARGO DELIVER
*  创建日期:2017-10-18
* @return nc.vo.pub.lang.UFBoolean
*/
public UFBoolean getTsloosecargodeliver() {
return this.tsloosecargodeliver;
} 

/**
* 属性tsloosecargodeliver的Setter方法.属性名：TsLOOSE CARGO DELIVER
* 创建日期:2017-10-18
* @param newTsloosecargodeliver nc.vo.pub.lang.UFBoolean
*/
public void setTsloosecargodeliver ( UFBoolean tsloosecargodeliver) {
this.tsloosecargodeliver=tsloosecargodeliver;
} 
 
/**
* 属性 tstruckinstruction的Getter方法.属性名：TsTruck Instruction
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getTstruckinstruction() {
return this.tstruckinstruction;
} 

/**
* 属性tstruckinstruction的Setter方法.属性名：TsTruck Instruction
* 创建日期:2017-10-18
* @param newTstruckinstruction java.lang.String
*/
public void setTstruckinstruction ( String tstruckinstruction) {
this.tstruckinstruction=tstruckinstruction;
} 
 
/**
* 属性 tsmoveinstruction的Getter方法.属性名：TsMove Instruction
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getTsmoveinstruction() {
return this.tsmoveinstruction;
} 

/**
* 属性tsmoveinstruction的Setter方法.属性名：TsMove Instruction
* 创建日期:2017-10-18
* @param newTsmoveinstruction java.lang.String
*/
public void setTsmoveinstruction ( String tsmoveinstruction) {
this.tsmoveinstruction=tsmoveinstruction;
} 
 
/**
* 属性 tsjobinstruction的Getter方法.属性名：TsJob Instruction
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getTsjobinstruction() {
return this.tsjobinstruction;
} 

/**
* 属性tsjobinstruction的Setter方法.属性名：TsJob Instruction
* 创建日期:2017-10-18
* @param newTsjobinstruction java.lang.String
*/
public void setTsjobinstruction ( String tsjobinstruction) {
this.tsjobinstruction=tsjobinstruction;
} 
 
/**
* 属性 tscscomment的Getter方法.属性名：TsCS COMMENT
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getTscscomment() {
return this.tscscomment;
} 

/**
* 属性tscscomment的Setter方法.属性名：TsCS COMMENT
* 创建日期:2017-10-18
* @param newTscscomment java.lang.String
*/
public void setTscscomment ( String tscscomment) {
this.tscscomment=tscscomment;
} 
 
/**
* 属性 tsfrom的Getter方法.属性名：TsFrom
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getTsfrom() {
return this.tsfrom;
} 

/**
* 属性tsfrom的Setter方法.属性名：TsFrom
* 创建日期:2017-10-18
* @param newTsfrom java.lang.String
*/
public void setTsfrom ( String tsfrom) {
this.tsfrom=tsfrom;
} 
 
/**
* 属性 tsport的Getter方法.属性名：TsPort
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getTsport() {
return this.tsport;
} 

/**
* 属性tsport的Setter方法.属性名：TsPort
* 创建日期:2017-10-18
* @param newTsport java.lang.String
*/
public void setTsport ( String tsport) {
this.tsport=tsport;
} 
 
/**
* 属性 tsto的Getter方法.属性名：TsTo
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getTsto() {
return this.tsto;
} 

/**
* 属性tsto的Setter方法.属性名：TsTo
* 创建日期:2017-10-18
* @param newTsto java.lang.String
*/
public void setTsto ( String tsto) {
this.tsto=tsto;
} 
 
/**
* 属性 tsfeextransactiondate的Getter方法.属性名：TsfeTransaction Date
*  创建日期:2017-10-18
* @return nc.vo.pub.lang.UFDate
*/
public UFDate getTsfeextransactiondate() {
return this.tsfeextransactiondate;
} 

/**
* 属性tsfeextransactiondate的Setter方法.属性名：TsfeTransaction Date
* 创建日期:2017-10-18
* @param newTsfeextransactiondate nc.vo.pub.lang.UFDate
*/
public void setTsfeextransactiondate ( UFDate tsfeextransactiondate) {
this.tsfeextransactiondate=tsfeextransactiondate;
} 
 
/**
* 属性 tsfeexjoborderno的Getter方法.属性名：TsfeJob Order #
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getTsfeexjoborderno() {
return this.tsfeexjoborderno;
} 

/**
* 属性tsfeexjoborderno的Setter方法.属性名：TsfeJob Order #
* 创建日期:2017-10-18
* @param newTsfeexjoborderno java.lang.String
*/
public void setTsfeexjoborderno ( String tsfeexjoborderno) {
this.tsfeexjoborderno=tsfeexjoborderno;
} 
 
/**
* 属性 tsfeexcustomerpono的Getter方法.属性名：TsfeCustomer PO#
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getTsfeexcustomerpono() {
return this.tsfeexcustomerpono;
} 

/**
* 属性tsfeexcustomerpono的Setter方法.属性名：TsfeCustomer PO#
* 创建日期:2017-10-18
* @param newTsfeexcustomerpono java.lang.String
*/
public void setTsfeexcustomerpono ( String tsfeexcustomerpono) {
this.tsfeexcustomerpono=tsfeexcustomerpono;
} 
 
/**
* 属性 tsfeexquotationno的Getter方法.属性名：TsfeQuotation #
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getTsfeexquotationno() {
return this.tsfeexquotationno;
} 

/**
* 属性tsfeexquotationno的Setter方法.属性名：TsfeQuotation #
* 创建日期:2017-10-18
* @param newTsfeexquotationno java.lang.String
*/
public void setTsfeexquotationno ( String tsfeexquotationno) {
this.tsfeexquotationno=tsfeexquotationno;
} 
 
/**
* 属性 tsfeexpreferreddatetime的Getter方法.属性名：TsfeCustomer Preferred Date/Time
*  创建日期:2017-10-18
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getTsfeexpreferreddatetime() {
return this.tsfeexpreferreddatetime;
} 

/**
* 属性tsfeexpreferreddatetime的Setter方法.属性名：TsfeCustomer Preferred Date/Time
* 创建日期:2017-10-18
* @param newTsfeexpreferreddatetime nc.vo.pub.lang.UFDateTime
*/
public void setTsfeexpreferreddatetime ( UFDateTime tsfeexpreferreddatetime) {
this.tsfeexpreferreddatetime=tsfeexpreferreddatetime;
} 
 
/**
* 属性 tsfeexportvessel的Getter方法.属性名：TsfeVessel
*  创建日期:2017-10-18
* @return nc.vo.bd.defdoc.DefdocVO
*/
public String getTsfeexportvessel() {
return this.tsfeexportvessel;
} 

/**
* 属性tsfeexportvessel的Setter方法.属性名：TsfeVessel
* 创建日期:2017-10-18
* @param newTsfeexportvessel nc.vo.bd.defdoc.DefdocVO
*/
public void setTsfeexportvessel ( String tsfeexportvessel) {
this.tsfeexportvessel=tsfeexportvessel;
} 
 
/**
* 属性 tsfeexportvoyage的Getter方法.属性名：TsfeVoyage
*  创建日期:2017-10-18
* @return nc.vo.bd.defdoc.DefdocVO
*/
public String getTsfeexportvoyage() {
return this.tsfeexportvoyage;
} 

/**
* 属性tsfeexportvoyage的Setter方法.属性名：TsfeVoyage
* 创建日期:2017-10-18
* @param newTsfeexportvoyage nc.vo.bd.defdoc.DefdocVO
*/
public void setTsfeexportvoyage ( String tsfeexportvoyage) {
this.tsfeexportvoyage=tsfeexportvoyage;
} 
 
/**
* 属性 tsfeexportagent的Getter方法.属性名：TsfeAgent
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getTsfeexportagent() {
return this.tsfeexportagent;
} 

/**
* 属性tsfeexportagent的Setter方法.属性名：TsfeAgent
* 创建日期:2017-10-18
* @param newTsfeexportagent java.lang.String
*/
public void setTsfeexportagent ( String tsfeexportagent) {
this.tsfeexportagent=tsfeexportagent;
} 
 
/**
* 属性 tsfeexportpermitno的Getter方法.属性名：TsfePermit #
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getTsfeexportpermitno() {
return this.tsfeexportpermitno;
} 

/**
* 属性tsfeexportpermitno的Setter方法.属性名：TsfePermit #
* 创建日期:2017-10-18
* @param newTsfeexportpermitno java.lang.String
*/
public void setTsfeexportpermitno ( String tsfeexportpermitno) {
this.tsfeexportpermitno=tsfeexportpermitno;
} 
 
/**
* 属性 tsfebookingref的Getter方法.属性名：TsfeBooking Ref
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getTsfebookingref() {
return this.tsfebookingref;
} 

/**
* 属性tsfebookingref的Setter方法.属性名：TsfeBooking Ref
* 创建日期:2017-10-18
* @param newTsfebookingref java.lang.String
*/
public void setTsfebookingref ( String tsfebookingref) {
this.tsfebookingref=tsfebookingref;
} 
 
/**
* 属性 tsfeportnetref的Getter方法.属性名：TsfePortnet Ref
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getTsfeportnetref() {
return this.tsfeportnetref;
} 

/**
* 属性tsfeportnetref的Setter方法.属性名：TsfePortnet Ref
* 创建日期:2017-10-18
* @param newTsfeportnetref java.lang.String
*/
public void setTsfeportnetref ( String tsfeportnetref) {
this.tsfeportnetref=tsfeportnetref;
} 
 
/**
* 属性 tsfepickupref的Getter方法.属性名：TsfePick up Ref
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getTsfepickupref() {
return this.tsfepickupref;
} 

/**
* 属性tsfepickupref的Setter方法.属性名：TsfePick up Ref
* 创建日期:2017-10-18
* @param newTsfepickupref java.lang.String
*/
public void setTsfepickupref ( String tsfepickupref) {
this.tsfepickupref=tsfepickupref;
} 
 
/**
* 属性 tsfeportofdischarge的Getter方法.属性名：TsfePort of Discharge
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getTsfeportofdischarge() {
return this.tsfeportofdischarge;
} 

/**
* 属性tsfeportofdischarge的Setter方法.属性名：TsfePort of Discharge
* 创建日期:2017-10-18
* @param newTsfeportofdischarge java.lang.String
*/
public void setTsfeportofdischarge ( String tsfeportofdischarge) {
this.tsfeportofdischarge=tsfeportofdischarge;
} 
 
/**
* 属性 tsfeexpoliceescortpermitno的Getter方法.属性名：TsfePolice escort permit # (if applicable)
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getTsfeexpoliceescortpermitno() {
return this.tsfeexpoliceescortpermitno;
} 

/**
* 属性tsfeexpoliceescortpermitno的Setter方法.属性名：TsfePolice escort permit # (if applicable)
* 创建日期:2017-10-18
* @param newTsfeexpoliceescortpermitno java.lang.String
*/
public void setTsfeexpoliceescortpermitno ( String tsfeexpoliceescortpermitno) {
this.tsfeexpoliceescortpermitno=tsfeexpoliceescortpermitno;
} 
 
/**
* 属性 tsfeexlinktocontractor的Getter方法.属性名：TsfeLink To Contractor
*  创建日期:2017-10-18
* @return nc.vo.bd.supplier.SupplierVO
*/
public String getTsfeexlinktocontractor() {
return this.tsfeexlinktocontractor;
} 

/**
* 属性tsfeexlinktocontractor的Setter方法.属性名：TsfeLink To Contractor
* 创建日期:2017-10-18
* @param newTsfeexlinktocontractor nc.vo.bd.supplier.SupplierVO
*/
public void setTsfeexlinktocontractor ( String tsfeexlinktocontractor) {
this.tsfeexlinktocontractor=tsfeexlinktocontractor;
} 
 
/**
* 属性 tsfeemptycntrcollection的Getter方法.属性名：TsfeEmpty Container Collection
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getTsfeemptycntrcollection() {
return this.tsfeemptycntrcollection;
} 

/**
* 属性tsfeemptycntrcollection的Setter方法.属性名：TsfeEmpty Container Collection
* 创建日期:2017-10-18
* @param newTsfeemptycntrcollection java.lang.String
*/
public void setTsfeemptycntrcollection ( String tsfeemptycntrcollection) {
this.tsfeemptycntrcollection=tsfeemptycntrcollection;
} 
 
/**
* 属性 tsfeexporteta的Getter方法.属性名：TsfeETA
*  创建日期:2017-10-18
* @return nc.vo.pub.lang.UFDate
*/
public UFDate getTsfeexporteta() {
return this.tsfeexporteta;
} 

/**
* 属性tsfeexporteta的Setter方法.属性名：TsfeETA
* 创建日期:2017-10-18
* @param newTsfeexporteta nc.vo.pub.lang.UFDate
*/
public void setTsfeexporteta ( UFDate tsfeexporteta) {
this.tsfeexporteta=tsfeexporteta;
} 
 
/**
* 属性 tsfefrom的Getter方法.属性名：TsfeFrom
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getTsfefrom() {
return this.tsfefrom;
} 

/**
* 属性tsfefrom的Setter方法.属性名：TsfeFrom
* 创建日期:2017-10-18
* @param newTsfefrom java.lang.String
*/
public void setTsfefrom ( String tsfefrom) {
this.tsfefrom=tsfefrom;
} 
 
/**
* 属性 tsfeto的Getter方法.属性名：TsfeTo
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getTsfeto() {
return this.tsfeto;
} 

/**
* 属性tsfeto的Setter方法.属性名：TsfeTo
* 创建日期:2017-10-18
* @param newTsfeto java.lang.String
*/
public void setTsfeto ( String tsfeto) {
this.tsfeto=tsfeto;
} 
 
/**
* 属性 tsfeport的Getter方法.属性名：TsfePort
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getTsfeport() {
return this.tsfeport;
} 

/**
* 属性tsfeport的Setter方法.属性名：TsfePort
* 创建日期:2017-10-18
* @param newTsfeport java.lang.String
*/
public void setTsfeport ( String tsfeport) {
this.tsfeport=tsfeport;
} 
 
/**
* 属性 tsfeairride的Getter方法.属性名：TsfeAIR RIDE
*  创建日期:2017-10-18
* @return java.lang.String
*/
public UFBoolean getTsfeairride() {
return this.tsfeairride;
} 

/**
* 属性tsfeairride的Setter方法.属性名：TsfeAIR RIDE
* 创建日期:2017-10-18
* @param newTsfeairride java.lang.String
*/
public void setTsfeairride ( UFBoolean tsfeairride) {
this.tsfeairride=tsfeairride;
} 
 
/**
* 属性 tsfeunstuffing的Getter方法.属性名：TsfeUNSTUFFING
*  创建日期:2017-10-18
* @return nc.vo.pub.lang.UFBoolean
*/
public UFBoolean getTsfeunstuffing() {
return this.tsfeunstuffing;
} 

/**
* 属性tsfeunstuffing的Setter方法.属性名：TsfeUNSTUFFING
* 创建日期:2017-10-18
* @param newTsfeunstuffing nc.vo.pub.lang.UFBoolean
*/
public void setTsfeunstuffing ( UFBoolean tsfeunstuffing) {
this.tsfeunstuffing=tsfeunstuffing;
} 
 
/**
* 属性 tsfeloosecargocollect的Getter方法.属性名：TsfeLOOSE CARGO COLLECT
*  创建日期:2017-10-18
* @return nc.vo.pub.lang.UFBoolean
*/
public UFBoolean getTsfeloosecargocollect() {
return this.tsfeloosecargocollect;
} 

/**
* 属性tsfeloosecargocollect的Setter方法.属性名：TsfeLOOSE CARGO COLLECT
* 创建日期:2017-10-18
* @param newTsfeloosecargocollect nc.vo.pub.lang.UFBoolean
*/
public void setTsfeloosecargocollect ( UFBoolean tsfeloosecargocollect) {
this.tsfeloosecargocollect=tsfeloosecargocollect;
} 
 
/**
* 属性 tsfeloosecargocollects的Getter方法.属性名：TsfeLOOSE CARGO COLLECTs
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getTsfeloosecargocollects() {
return this.tsfeloosecargocollects;
} 

/**
* 属性tsfeloosecargocollects的Setter方法.属性名：TsfeLOOSE CARGO COLLECTs
* 创建日期:2017-10-18
* @param newTsfeloosecargocollects java.lang.String
*/
public void setTsfeloosecargocollects ( String tsfeloosecargocollects) {
this.tsfeloosecargocollects=tsfeloosecargocollects;
} 
 
/**
* 属性 tsfetruckinstruction的Getter方法.属性名：TsfeTruck Instruction
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getTsfetruckinstruction() {
return this.tsfetruckinstruction;
} 

/**
* 属性tsfetruckinstruction的Setter方法.属性名：TsfeTruck Instruction
* 创建日期:2017-10-18
* @param newTsfetruckinstruction java.lang.String
*/
public void setTsfetruckinstruction ( String tsfetruckinstruction) {
this.tsfetruckinstruction=tsfetruckinstruction;
} 
 
/**
* 属性 tsfemoveinstruction的Getter方法.属性名：TsfeMove Instruction
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getTsfemoveinstruction() {
return this.tsfemoveinstruction;
} 

/**
* 属性tsfemoveinstruction的Setter方法.属性名：TsfeMove Instruction
* 创建日期:2017-10-18
* @param newTsfemoveinstruction java.lang.String
*/
public void setTsfemoveinstruction ( String tsfemoveinstruction) {
this.tsfemoveinstruction=tsfemoveinstruction;
} 
 
/**
* 属性 tsfejobinstruction的Getter方法.属性名：TsfeJob Instruction
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getTsfejobinstruction() {
return this.tsfejobinstruction;
} 

/**
* 属性tsfejobinstruction的Setter方法.属性名：TsfeJob Instruction
* 创建日期:2017-10-18
* @param newTsfejobinstruction java.lang.String
*/
public void setTsfejobinstruction ( String tsfejobinstruction) {
this.tsfejobinstruction=tsfejobinstruction;
} 
 
/**
* 属性 tsfecscomment的Getter方法.属性名：TsfeCS COMMENT
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getTsfecscomment() {
return this.tsfecscomment;
} 

/**
* 属性tsfecscomment的Setter方法.属性名：TsfeCS COMMENT
* 创建日期:2017-10-18
* @param newTsfecscomment java.lang.String
*/
public void setTsfecscomment ( String tsfecscomment) {
this.tsfecscomment=tsfecscomment;
} 
 
/**
* 属性 tajobtype的Getter方法.属性名：Ta Job Type
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getTajobtype() {
return this.tajobtype;
} 

/**
* 属性tajobtype的Setter方法.属性名：Ta Job Type
* 创建日期:2017-10-18
* @param newTajobtype java.lang.String
*/
public void setTajobtype ( String tajobtype) {
this.tajobtype=tajobtype;
} 
 
/**
* 属性 tadono的Getter方法.属性名：Ta DO#
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getTadono() {
return this.tadono;
} 

/**
* 属性tadono的Setter方法.属性名：Ta DO#
* 创建日期:2017-10-18
* @param newTadono java.lang.String
*/
public void setTadono ( String tadono) {
this.tadono=tadono;
} 
 
/**
* 属性 taapickupdate的Getter方法.属性名：Ta Pickup Date
*  创建日期:2017-10-18
* @return nc.vo.pub.lang.UFDate
*/
public UFDate getTaapickupdate() {
return this.taapickupdate;
} 

/**
* 属性taapickupdate的Setter方法.属性名：Ta Pickup Date
* 创建日期:2017-10-18
* @param newTaapickupdate nc.vo.pub.lang.UFDate
*/
public void setTaapickupdate ( UFDate taapickupdate) {
this.taapickupdate=taapickupdate;
} 
 
/**
* 属性 taapickuptime的Getter方法.属性名：Ta Pickup Time
*  创建日期:2017-10-18
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getTaapickuptime() {
return this.taapickuptime;
} 

/**
* 属性taapickuptime的Setter方法.属性名：Ta Pickup Time
* 创建日期:2017-10-18
* @param newTaapickuptime nc.vo.pub.lang.UFDateTime
*/
public void setTaapickuptime ( UFDateTime taapickuptime) {
this.taapickuptime=taapickuptime;
} 
 
/**
* 属性 taaondockdate的Getter方法.属性名：Ta Ondock Date
*  创建日期:2017-10-18
* @return nc.vo.pub.lang.UFDate
*/
public UFDate getTaaondockdate() {
return this.taaondockdate;
} 

/**
* 属性taaondockdate的Setter方法.属性名：Ta Ondock Date
* 创建日期:2017-10-18
* @param newTaaondockdate nc.vo.pub.lang.UFDate
*/
public void setTaaondockdate ( UFDate taaondockdate) {
this.taaondockdate=taaondockdate;
} 
 
/**
* 属性 taaondocktime的Getter方法.属性名：Ta Ondock Time
*  创建日期:2017-10-18
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getTaaondocktime() {
return this.taaondocktime;
} 

/**
* 属性taaondocktime的Setter方法.属性名：Ta Ondock Time
* 创建日期:2017-10-18
* @param newTaaondocktime nc.vo.pub.lang.UFDateTime
*/
public void setTaaondocktime ( UFDateTime taaondocktime) {
this.taaondocktime=taaondocktime;
} 
 
/**
* 属性 taapickupfromname的Getter方法.属性名：Ta Pickup From Name
*  创建日期:2017-10-18
* @return nc.vo.bd.cust.CustomerVO
*/
public String getTaapickupfromname() {
return this.taapickupfromname;
} 

/**
* 属性taapickupfromname的Setter方法.属性名：Ta Pickup From Name
* 创建日期:2017-10-18
* @param newTaapickupfromname nc.vo.bd.cust.CustomerVO
*/
public void setTaapickupfromname ( String taapickupfromname) {
this.taapickupfromname=taapickupfromname;
} 
 
/**
* 属性 taapickupfromaddress的Getter方法.属性名：Ta Pickup From Address
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getTaapickupfromaddress() {
return this.taapickupfromaddress;
} 

/**
* 属性taapickupfromaddress的Setter方法.属性名：Ta Pickup From Address
* 创建日期:2017-10-18
* @param newTaapickupfromaddress java.lang.String
*/
public void setTaapickupfromaddress ( String taapickupfromaddress) {
this.taapickupfromaddress=taapickupfromaddress;
} 
 
/**
* 属性 taapickupfromcode的Getter方法.属性名：Ta Pickup From Code
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getTaapickupfromcode() {
return this.taapickupfromcode;
} 

/**
* 属性taapickupfromcode的Setter方法.属性名：Ta Pickup From Code
* 创建日期:2017-10-18
* @param newTaapickupfromcode java.lang.String
*/
public void setTaapickupfromcode ( String taapickupfromcode) {
this.taapickupfromcode=taapickupfromcode;
} 
 
/**
* 属性 taacontactperson的Getter方法.属性名：Ta Contact Person
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getTaacontactperson() {
return this.taacontactperson;
} 

/**
* 属性taacontactperson的Setter方法.属性名：Ta Contact Person
* 创建日期:2017-10-18
* @param newTaacontactperson java.lang.String
*/
public void setTaacontactperson ( String taacontactperson) {
this.taacontactperson=taacontactperson;
} 
 
/**
* 属性 taacontactpersonphone的Getter方法.属性名：Ta Contact Person Phone
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getTaacontactpersonphone() {
return this.taacontactpersonphone;
} 

/**
* 属性taacontactpersonphone的Setter方法.属性名：Ta Contact Person Phone
* 创建日期:2017-10-18
* @param newTaacontactpersonphone java.lang.String
*/
public void setTaacontactpersonphone ( String taacontactpersonphone) {
this.taacontactpersonphone=taacontactpersonphone;
} 
 
/**
* 属性 tabpickupdate的Getter方法.属性名：Ta Bpickup Date
*  创建日期:2017-10-18
* @return nc.vo.pub.lang.UFDate
*/
public UFDate getTabpickupdate() {
return this.tabpickupdate;
} 

/**
* 属性tabpickupdate的Setter方法.属性名：Ta Bpickup Date
* 创建日期:2017-10-18
* @param newTabpickupdate nc.vo.pub.lang.UFDate
*/
public void setTabpickupdate ( UFDate tabpickupdate) {
this.tabpickupdate=tabpickupdate;
} 
 
/**
* 属性 tabpickuptime的Getter方法.属性名：Ta Bpickup Time
*  创建日期:2017-10-18
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getTabpickuptime() {
return this.tabpickuptime;
} 

/**
* 属性tabpickuptime的Setter方法.属性名：Ta Bpickup Time
* 创建日期:2017-10-18
* @param newTabpickuptime nc.vo.pub.lang.UFDateTime
*/
public void setTabpickuptime ( UFDateTime tabpickuptime) {
this.tabpickuptime=tabpickuptime;
} 
 
/**
* 属性 tabondockdate的Getter方法.属性名：Ta Bondock Date
*  创建日期:2017-10-18
* @return nc.vo.pub.lang.UFDate
*/
public UFDate getTabondockdate() {
return this.tabondockdate;
} 

/**
* 属性tabondockdate的Setter方法.属性名：Ta Bondock Date
* 创建日期:2017-10-18
* @param newTabondockdate nc.vo.pub.lang.UFDate
*/
public void setTabondockdate ( UFDate tabondockdate) {
this.tabondockdate=tabondockdate;
} 
 
/**
* 属性 tabondocktime的Getter方法.属性名：Ta Bondock Time
*  创建日期:2017-10-18
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getTabondocktime() {
return this.tabondocktime;
} 

/**
* 属性tabondocktime的Setter方法.属性名：Ta Bondock Time
* 创建日期:2017-10-18
* @param newTabondocktime nc.vo.pub.lang.UFDateTime
*/
public void setTabondocktime ( UFDateTime tabondocktime) {
this.tabondocktime=tabondocktime;
} 
 
/**
* 属性 tabpreloadtofromname的Getter方法.属性名：Ta Preload To/From Name
*  创建日期:2017-10-18
* @return nc.vo.bd.cust.CustomerVO
*/
public String getTabpreloadtofromname() {
return this.tabpreloadtofromname;
} 

/**
* 属性tabpreloadtofromname的Setter方法.属性名：Ta Preload To/From Name
* 创建日期:2017-10-18
* @param newTabpreloadtofromname nc.vo.bd.cust.CustomerVO
*/
public void setTabpreloadtofromname ( String tabpreloadtofromname) {
this.tabpreloadtofromname=tabpreloadtofromname;
} 
 
/**
* 属性 tabpreloadtofromaddres的Getter方法.属性名：Ta Preload To/From Address
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getTabpreloadtofromaddres() {
return this.tabpreloadtofromaddres;
} 

/**
* 属性tabpreloadtofromaddres的Setter方法.属性名：Ta Preload To/From Address
* 创建日期:2017-10-18
* @param newTabpreloadtofromaddres java.lang.String
*/
public void setTabpreloadtofromaddres ( String tabpreloadtofromaddres) {
this.tabpreloadtofromaddres=tabpreloadtofromaddres;
} 
 
/**
* 属性 tabpreloadtofromcode的Getter方法.属性名：Ta Preload To/From Code
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getTabpreloadtofromcode() {
return this.tabpreloadtofromcode;
} 

/**
* 属性tabpreloadtofromcode的Setter方法.属性名：Ta Preload To/From Code
* 创建日期:2017-10-18
* @param newTabpreloadtofromcode java.lang.String
*/
public void setTabpreloadtofromcode ( String tabpreloadtofromcode) {
this.tabpreloadtofromcode=tabpreloadtofromcode;
} 
 
/**
* 属性 tacpickupdate的Getter方法.属性名：Ta Cpickup Date
*  创建日期:2017-10-18
* @return nc.vo.pub.lang.UFDate
*/
public UFDate getTacpickupdate() {
return this.tacpickupdate;
} 

/**
* 属性tacpickupdate的Setter方法.属性名：Ta Cpickup Date
* 创建日期:2017-10-18
* @param newTacpickupdate nc.vo.pub.lang.UFDate
*/
public void setTacpickupdate ( UFDate tacpickupdate) {
this.tacpickupdate=tacpickupdate;
} 
 
/**
* 属性 tacpickuptime的Getter方法.属性名：Ta Cpickup Time
*  创建日期:2017-10-18
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getTacpickuptime() {
return this.tacpickuptime;
} 

/**
* 属性tacpickuptime的Setter方法.属性名：Ta Cpickup Time
* 创建日期:2017-10-18
* @param newTacpickuptime nc.vo.pub.lang.UFDateTime
*/
public void setTacpickuptime ( UFDateTime tacpickuptime) {
this.tacpickuptime=tacpickuptime;
} 
 
/**
* 属性 tacondockdate的Getter方法.属性名：Ta Condock Date
*  创建日期:2017-10-18
* @return nc.vo.pub.lang.UFDate
*/
public UFDate getTacondockdate() {
return this.tacondockdate;
} 

/**
* 属性tacondockdate的Setter方法.属性名：Ta Condock Date
* 创建日期:2017-10-18
* @param newTacondockdate nc.vo.pub.lang.UFDate
*/
public void setTacondockdate ( UFDate tacondockdate) {
this.tacondockdate=tacondockdate;
} 
 
/**
* 属性 tacondocktime的Getter方法.属性名：Ta Condock Time
*  创建日期:2017-10-18
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getTacondocktime() {
return this.tacondocktime;
} 

/**
* 属性tacondocktime的Setter方法.属性名：Ta Condock Time
* 创建日期:2017-10-18
* @param newTacondocktime nc.vo.pub.lang.UFDateTime
*/
public void setTacondocktime ( UFDateTime tacondocktime) {
this.tacondocktime=tacondocktime;
} 
 
/**
* 属性 tacdelivertoname的Getter方法.属性名：Ta Deliver To Name
*  创建日期:2017-10-18
* @return nc.vo.bd.cust.CustomerVO
*/
public String getTacdelivertoname() {
return this.tacdelivertoname;
} 

/**
* 属性tacdelivertoname的Setter方法.属性名：Ta Deliver To Name
* 创建日期:2017-10-18
* @param newTacdelivertoname nc.vo.bd.cust.CustomerVO
*/
public void setTacdelivertoname ( String tacdelivertoname) {
this.tacdelivertoname=tacdelivertoname;
} 
 
/**
* 属性 tacdelivertoaddress的Getter方法.属性名：Ta Deliver To Address
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getTacdelivertoaddress() {
return this.tacdelivertoaddress;
} 

/**
* 属性tacdelivertoaddress的Setter方法.属性名：Ta Deliver To Address
* 创建日期:2017-10-18
* @param newTacdelivertoaddress java.lang.String
*/
public void setTacdelivertoaddress ( String tacdelivertoaddress) {
this.tacdelivertoaddress=tacdelivertoaddress;
} 
 
/**
* 属性 tacdelivertocode的Getter方法.属性名：Ta Deliver To Code
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getTacdelivertocode() {
return this.tacdelivertocode;
} 

/**
* 属性tacdelivertocode的Setter方法.属性名：Ta Deliver To Code
* 创建日期:2017-10-18
* @param newTacdelivertocode java.lang.String
*/
public void setTacdelivertocode ( String tacdelivertocode) {
this.tacdelivertocode=tacdelivertocode;
} 
 
/**
* 属性 taccontactperson的Getter方法.属性名：Ta Ccontact Person
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getTaccontactperson() {
return this.taccontactperson;
} 

/**
* 属性taccontactperson的Setter方法.属性名：Ta Ccontact Person
* 创建日期:2017-10-18
* @param newTaccontactperson java.lang.String
*/
public void setTaccontactperson ( String taccontactperson) {
this.taccontactperson=taccontactperson;
} 
 
/**
* 属性 taccontactpersonphone的Getter方法.属性名：Ta Ccontact Person Phone
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getTaccontactpersonphone() {
return this.taccontactpersonphone;
} 

/**
* 属性taccontactpersonphone的Setter方法.属性名：Ta Ccontact Person Phone
* 创建日期:2017-10-18
* @param newTaccontactpersonphone java.lang.String
*/
public void setTaccontactpersonphone ( String taccontactpersonphone) {
this.taccontactpersonphone=taccontactpersonphone;
} 
 
/**
* 属性 taawbno的Getter方法.属性名：Ta AWB #
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getTaawbno() {
return this.taawbno;
} 

/**
* 属性taawbno的Setter方法.属性名：Ta AWB #
* 创建日期:2017-10-18
* @param newTaawbno java.lang.String
*/
public void setTaawbno ( String taawbno) {
this.taawbno=taawbno;
} 
 
/**
* 属性 tatotalofpkgs的Getter方法.属性名：Ta Total # Of Pkgs
*  创建日期:2017-10-18
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getTatotalofpkgs() {
return this.tatotalofpkgs;
} 

/**
* 属性tatotalofpkgs的Setter方法.属性名：Ta Total # Of Pkgs
* 创建日期:2017-10-18
* @param newTatotalofpkgs nc.vo.pub.lang.UFDouble
*/
public void setTatotalofpkgs ( UFDouble tatotalofpkgs) {
this.tatotalofpkgs=tatotalofpkgs;
} 
 
/**
* 属性 tachargableweight的Getter方法.属性名：Ta Chargable Weight(KG)
*  创建日期:2017-10-18
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getTachargableweight() {
return this.tachargableweight;
} 

/**
* 属性tachargableweight的Setter方法.属性名：Ta Chargable Weight(KG)
* 创建日期:2017-10-18
* @param newTachargableweight nc.vo.pub.lang.UFDouble
*/
public void setTachargableweight ( UFDouble tachargableweight) {
this.tachargableweight=tachargableweight;
} 
 
/**
* 属性 tavolume的Getter方法.属性名：Ta Volume(M3)
*  创建日期:2017-10-18
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getTavolume() {
return this.tavolume;
} 

/**
* 属性tavolume的Setter方法.属性名：Ta Volume(M3)
* 创建日期:2017-10-18
* @param newTavolume nc.vo.pub.lang.UFDouble
*/
public void setTavolume ( UFDouble tavolume) {
this.tavolume=tavolume;
} 
 
/**
* 属性 tagrossweight的Getter方法.属性名：Ta Gross Weight(KG)
*  创建日期:2017-10-18
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getTagrossweight() {
return this.tagrossweight;
} 

/**
* 属性tagrossweight的Setter方法.属性名：Ta Gross Weight(KG)
* 创建日期:2017-10-18
* @param newTagrossweight nc.vo.pub.lang.UFDouble
*/
public void setTagrossweight ( UFDouble tagrossweight) {
this.tagrossweight=tagrossweight;
} 
 
/**
* 属性 taaream2的Getter方法.属性名：Ta Area (M2)
*  创建日期:2017-10-18
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getTaaream2() {
return this.taaream2;
} 

/**
* 属性taaream2的Setter方法.属性名：Ta Area (M2)
* 创建日期:2017-10-18
* @param newTaaream2 nc.vo.pub.lang.UFDouble
*/
public void setTaaream2 ( UFDouble taaream2) {
this.taaream2=taaream2;
} 
 
/**
* 属性 talengthcm的Getter方法.属性名：Ta Length(CM)
*  创建日期:2017-10-18
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getTalengthcm() {
return this.talengthcm;
} 

/**
* 属性talengthcm的Setter方法.属性名：Ta Length(CM)
* 创建日期:2017-10-18
* @param newTalengthcm nc.vo.pub.lang.UFDouble
*/
public void setTalengthcm ( UFDouble talengthcm) {
this.talengthcm=talengthcm;
} 
 
/**
* 属性 talcrateno的Getter方法.属性名：Ta Crate #
*  创建日期:2017-10-18
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getTalcrateno() {
return this.talcrateno;
} 

/**
* 属性talcrateno的Setter方法.属性名：Ta Crate #
* 创建日期:2017-10-18
* @param newTalcrateno nc.vo.pub.lang.UFDouble
*/
public void setTalcrateno ( UFDouble talcrateno) {
this.talcrateno=talcrateno;
} 
 
/**
* 属性 tawidthcm的Getter方法.属性名：Ta Width(CM)
*  创建日期:2017-10-18
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getTawidthcm() {
return this.tawidthcm;
} 

/**
* 属性tawidthcm的Setter方法.属性名：Ta Width(CM)
* 创建日期:2017-10-18
* @param newTawidthcm nc.vo.pub.lang.UFDouble
*/
public void setTawidthcm ( UFDouble tawidthcm) {
this.tawidthcm=tawidthcm;
} 
 
/**
* 属性 tawcrateno的Getter方法.属性名：Ta Wcrate #
*  创建日期:2017-10-18
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getTawcrateno() {
return this.tawcrateno;
} 

/**
* 属性tawcrateno的Setter方法.属性名：Ta Wcrate #
* 创建日期:2017-10-18
* @param newTawcrateno nc.vo.pub.lang.UFDouble
*/
public void setTawcrateno ( UFDouble tawcrateno) {
this.tawcrateno=tawcrateno;
} 
 
/**
* 属性 taheightcm的Getter方法.属性名：Ta Height(CM)
*  创建日期:2017-10-18
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getTaheightcm() {
return this.taheightcm;
} 

/**
* 属性taheightcm的Setter方法.属性名：Ta Height(CM)
* 创建日期:2017-10-18
* @param newTaheightcm nc.vo.pub.lang.UFDouble
*/
public void setTaheightcm ( UFDouble taheightcm) {
this.taheightcm=taheightcm;
} 
 
/**
* 属性 tahcrateno的Getter方法.属性名：Ta HCrate #
*  创建日期:2017-10-18
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getTahcrateno() {
return this.tahcrateno;
} 

/**
* 属性tahcrateno的Setter方法.属性名：Ta HCrate #
* 创建日期:2017-10-18
* @param newTahcrateno nc.vo.pub.lang.UFDouble
*/
public void setTahcrateno ( UFDouble tahcrateno) {
this.tahcrateno=tahcrateno;
} 
 
/**
* 属性 taspecialinstruction的Getter方法.属性名：Ta Special Instruction
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getTaspecialinstruction() {
return this.taspecialinstruction;
} 

/**
* 属性taspecialinstruction的Setter方法.属性名：Ta Special Instruction
* 创建日期:2017-10-18
* @param newTaspecialinstruction java.lang.String
*/
public void setTaspecialinstruction ( String taspecialinstruction) {
this.taspecialinstruction=taspecialinstruction;
} 
 
/**
* 属性 tacommoninstruction的Getter方法.属性名：Ta Common Instruction
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getTacommoninstruction() {
return this.tacommoninstruction;
} 

/**
* 属性tacommoninstruction的Setter方法.属性名：Ta Common Instruction
* 创建日期:2017-10-18
* @param newTacommoninstruction java.lang.String
*/
public void setTacommoninstruction ( String tacommoninstruction) {
this.tacommoninstruction=tacommoninstruction;
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
* 属性 surveyno的Getter方法.属性名：Survey No
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getSurveyno() {
return this.surveyno;
} 

/**
* 属性surveyno的Setter方法.属性名：Survey No
* 创建日期:2017-10-18
* @param newSurveyno java.lang.String
*/
public void setSurveyno ( String surveyno) {
this.surveyno=surveyno;
} 
 
/**
* 属性 surveyby的Getter方法.属性名：Survey By
*  创建日期:2017-10-18
* @return nc.vo.bd.psn.PsndocVO
*/
public String getSurveyby() {
return this.surveyby;
} 

/**
* 属性surveyby的Setter方法.属性名：Survey By
* 创建日期:2017-10-18
* @param newSurveyby nc.vo.bd.psn.PsndocVO
*/
public void setSurveyby ( String surveyby) {
this.surveyby=surveyby;
} 
 
/**
* 属性 surveydate的Getter方法.属性名：Survey Date
*  创建日期:2017-10-18
* @return nc.vo.pub.lang.UFDate
*/
public UFDate getSurveydate() {
return this.surveydate;
} 

/**
* 属性surveydate的Setter方法.属性名：Survey Date
* 创建日期:2017-10-18
* @param newSurveydate nc.vo.pub.lang.UFDate
*/
public void setSurveydate ( UFDate surveydate) {
this.surveydate=surveydate;
} 
 
/**
* 属性 customerpono的Getter方法.属性名：Customer PO No
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getCustomerpono() {
return this.customerpono;
} 

/**
* 属性customerpono的Setter方法.属性名：Customer PO No
* 创建日期:2017-10-18
* @param newCustomerpono java.lang.String
*/
public void setCustomerpono ( String customerpono) {
this.customerpono=customerpono;
} 
 
/**
* 属性 fromcorp的Getter方法.属性名：From Corp
*  创建日期:2017-10-18
* @return nc.vo.bd.cust.addressdoc.AddressDocVO
*/
public String getFromcorp() {
return this.fromcorp;
} 

/**
* 属性fromcorp的Setter方法.属性名：From Corp
* 创建日期:2017-10-18
* @param newFromcorp nc.vo.bd.cust.addressdoc.AddressDocVO
*/
public void setFromcorp ( String fromcorp) {
this.fromcorp=fromcorp;
} 
 
/**
* 属性 tocorp的Getter方法.属性名：To Corp
*  创建日期:2017-10-18
* @return nc.vo.bd.cust.addressdoc.AddressDocVO
*/
public String getTocorp() {
return this.tocorp;
} 

/**
* 属性tocorp的Setter方法.属性名：To Corp
* 创建日期:2017-10-18
* @param newTocorp nc.vo.bd.cust.addressdoc.AddressDocVO
*/
public void setTocorp ( String tocorp) {
this.tocorp=tocorp;
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
* 属性 netweight的Getter方法.属性名：Net Weight (KG)
*  创建日期:2017-10-18
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getNetweight() {
return this.netweight;
} 

/**
* 属性netweight的Setter方法.属性名：Net Weight (KG)
* 创建日期:2017-10-18
* @param newNetweight nc.vo.pub.lang.UFDouble
*/
public void setNetweight ( UFDouble netweight) {
this.netweight=netweight;
} 
 
/**
* 属性 largestweight的Getter方法.属性名：Largest weight
*  创建日期:2017-10-18
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getLargestweight() {
return this.largestweight;
} 

/**
* 属性largestweight的Setter方法.属性名：Largest weight
* 创建日期:2017-10-18
* @param newLargestweight nc.vo.pub.lang.UFDouble
*/
public void setLargestweight ( UFDouble largestweight) {
this.largestweight=largestweight;
} 
 
/**
* 属性 kcrate的Getter方法.属性名：Kcrate#
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getKcrate() {
return this.kcrate;
} 

/**
* 属性kcrate的Setter方法.属性名：Kcrate#
* 创建日期:2017-10-18
* @param newKcrate java.lang.String
*/
public void setKcrate ( String kcrate) {
this.kcrate=kcrate;
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
    return VOMetaFactory.getInstance().getVOMeta("BHS.SoTruckHVO");
    }
   }
    