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
 
public class SoMoveHVO extends SuperVO {
	
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
*Survey Report Number
*/
public String surveyreportnumber;
/**
*DO#
*/
public String dono;
/**
*Job Date
*/
public UFDate jobdate;
/**
*Departure Time
*/
public UFDateTime departuretime;
/**
*Job Start Time
*/
public UFDateTime jobstarttime;
/**
*Job End Time
*/
public UFDateTime jobendtime;
/**
*From Corp
*/
public String fromcorp;
/**
*From Address
*/
public String fromaddress;
/**
*From Code
*/
public String fromcode;
/**
*Sublocation1
*/
public String sublocation1;
/**
*To Corp
*/
public String tocorp;
/**
*To Address
*/
public String toaddress;
/**
*To Code
*/
public String tocode;
/**
*Sublocation2
*/
public String sublocation2;
/**
*Contact Person
*/
public String contactperson;
/**
*Contact PersPhone
*/
public String contactpersphone;
/**
*Po/ Cost Center Number
*/
public String pocostcenternumber;
/**
*Micap Number / Machine ID
*/
public String micapnumbermachineid;
/**
*Lid Number
*/
public String lidnumber;
/**
*Machine ID
*/
public String machineid;
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
*Total # Of Pkgs
*/
public UFDouble totalofpkgs;
/**
*Gross Weight (KG)
*/
public UFDouble grossweight;
/**
*Net Weight (KG)
*/
public UFDouble netweight;
/**
*Volume (M3)
*/
public UFDouble volumem3;
/**
*LENGTH (CM)
*/
public UFDouble lengthcm;
/**
*CRATE1 #
*/
public String crate1;
/**
*WIDTH (CM)
*/
public UFDouble widthcm;
/**
*CRATE 2#
*/
public String crate2;
/**
*HEIGHT (CM)
*/
public UFDouble heightcm;
/**
*CRATE 3#
*/
public String crate3;
/**
*Special Instruction
*/
public String specialinstruction;
/**
*Common Instruction
*/
public String commoninstruction;
/**
*订单主键
*/
public String csaleorderid;
/**
*订单号
*/
public String csaleordercode;
/**
*Start Date
*/
public UFDateTime startdate;
/**
*End Date
*/
public UFDateTime enddate;
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
*Attn To
*/
public String attnto;
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
*Job Type
*/
public String jobtype;
/**
*OEM Tool No
*/
public String oemtoolno;
/**
*Chargeable Weight (Kg)
*/
public UFDouble chargeableweight;
/**
*Largest weight
*/
public UFDouble largestweight;
/**
*Kcrate#
*/
public String kcrate;
/**
*AWB#
*/
public String awb;
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
* 属性 surveyreportnumber的Getter方法.属性名：Survey Report Number
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getSurveyreportnumber() {
return this.surveyreportnumber;
} 

/**
* 属性surveyreportnumber的Setter方法.属性名：Survey Report Number
* 创建日期:2017-10-18
* @param newSurveyreportnumber java.lang.String
*/
public void setSurveyreportnumber ( String surveyreportnumber) {
this.surveyreportnumber=surveyreportnumber;
} 
 
/**
* 属性 dono的Getter方法.属性名：DO#
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getDono() {
return this.dono;
} 

/**
* 属性dono的Setter方法.属性名：DO#
* 创建日期:2017-10-18
* @param newDono java.lang.String
*/
public void setDono ( String dono) {
this.dono=dono;
} 
 
/**
* 属性 jobdate的Getter方法.属性名：Job Date
*  创建日期:2017-10-18
* @return nc.vo.pub.lang.UFDate
*/
public UFDate getJobdate() {
return this.jobdate;
} 

/**
* 属性jobdate的Setter方法.属性名：Job Date
* 创建日期:2017-10-18
* @param newJobdate nc.vo.pub.lang.UFDate
*/
public void setJobdate ( UFDate jobdate) {
this.jobdate=jobdate;
} 
 
/**
* 属性 departuretime的Getter方法.属性名：Departure Time
*  创建日期:2017-10-18
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getDeparturetime() {
return this.departuretime;
} 

/**
* 属性departuretime的Setter方法.属性名：Departure Time
* 创建日期:2017-10-18
* @param newDeparturetime nc.vo.pub.lang.UFDateTime
*/
public void setDeparturetime ( UFDateTime departuretime) {
this.departuretime=departuretime;
} 
 
/**
* 属性 jobstarttime的Getter方法.属性名：Job Start Time
*  创建日期:2017-10-18
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getJobstarttime() {
return this.jobstarttime;
} 

/**
* 属性jobstarttime的Setter方法.属性名：Job Start Time
* 创建日期:2017-10-18
* @param newJobstarttime nc.vo.pub.lang.UFDateTime
*/
public void setJobstarttime ( UFDateTime jobstarttime) {
this.jobstarttime=jobstarttime;
} 
 
/**
* 属性 jobendtime的Getter方法.属性名：Job End Time
*  创建日期:2017-10-18
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getJobendtime() {
return this.jobendtime;
} 

/**
* 属性jobendtime的Setter方法.属性名：Job End Time
* 创建日期:2017-10-18
* @param newJobendtime nc.vo.pub.lang.UFDateTime
*/
public void setJobendtime ( UFDateTime jobendtime) {
this.jobendtime=jobendtime;
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
* 属性 fromaddress的Getter方法.属性名：From Address
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getFromaddress() {
return this.fromaddress;
} 

/**
* 属性fromaddress的Setter方法.属性名：From Address
* 创建日期:2017-10-18
* @param newFromaddress java.lang.String
*/
public void setFromaddress ( String fromaddress) {
this.fromaddress=fromaddress;
} 
 
/**
* 属性 fromcode的Getter方法.属性名：From Code
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getFromcode() {
return this.fromcode;
} 

/**
* 属性fromcode的Setter方法.属性名：From Code
* 创建日期:2017-10-18
* @param newFromcode java.lang.String
*/
public void setFromcode ( String fromcode) {
this.fromcode=fromcode;
} 
 
/**
* 属性 sublocation1的Getter方法.属性名：Sublocation1
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getSublocation1() {
return this.sublocation1;
} 

/**
* 属性sublocation1的Setter方法.属性名：Sublocation1
* 创建日期:2017-10-18
* @param newSublocation1 java.lang.String
*/
public void setSublocation1 ( String sublocation1) {
this.sublocation1=sublocation1;
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
* 属性 toaddress的Getter方法.属性名：To Address
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getToaddress() {
return this.toaddress;
} 

/**
* 属性toaddress的Setter方法.属性名：To Address
* 创建日期:2017-10-18
* @param newToaddress java.lang.String
*/
public void setToaddress ( String toaddress) {
this.toaddress=toaddress;
} 
 
/**
* 属性 tocode的Getter方法.属性名：To Code
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getTocode() {
return this.tocode;
} 

/**
* 属性tocode的Setter方法.属性名：To Code
* 创建日期:2017-10-18
* @param newTocode java.lang.String
*/
public void setTocode ( String tocode) {
this.tocode=tocode;
} 
 
/**
* 属性 sublocation2的Getter方法.属性名：Sublocation2
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getSublocation2() {
return this.sublocation2;
} 

/**
* 属性sublocation2的Setter方法.属性名：Sublocation2
* 创建日期:2017-10-18
* @param newSublocation2 java.lang.String
*/
public void setSublocation2 ( String sublocation2) {
this.sublocation2=sublocation2;
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
* 属性 contactpersphone的Getter方法.属性名：Contact PersPhone
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getContactpersphone() {
return this.contactpersphone;
} 

/**
* 属性contactpersphone的Setter方法.属性名：Contact PersPhone
* 创建日期:2017-10-18
* @param newContactpersphone java.lang.String
*/
public void setContactpersphone ( String contactpersphone) {
this.contactpersphone=contactpersphone;
} 
 
/**
* 属性 pocostcenternumber的Getter方法.属性名：Po/ Cost Center Number
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getPocostcenternumber() {
return this.pocostcenternumber;
} 

/**
* 属性pocostcenternumber的Setter方法.属性名：Po/ Cost Center Number
* 创建日期:2017-10-18
* @param newPocostcenternumber java.lang.String
*/
public void setPocostcenternumber ( String pocostcenternumber) {
this.pocostcenternumber=pocostcenternumber;
} 
 
/**
* 属性 micapnumbermachineid的Getter方法.属性名：Micap Number / Machine ID
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getMicapnumbermachineid() {
return this.micapnumbermachineid;
} 

/**
* 属性micapnumbermachineid的Setter方法.属性名：Micap Number / Machine ID
* 创建日期:2017-10-18
* @param newMicapnumbermachineid java.lang.String
*/
public void setMicapnumbermachineid ( String micapnumbermachineid) {
this.micapnumbermachineid=micapnumbermachineid;
} 
 
/**
* 属性 lidnumber的Getter方法.属性名：Lid Number
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getLidnumber() {
return this.lidnumber;
} 

/**
* 属性lidnumber的Setter方法.属性名：Lid Number
* 创建日期:2017-10-18
* @param newLidnumber java.lang.String
*/
public void setLidnumber ( String lidnumber) {
this.lidnumber=lidnumber;
} 
 
/**
* 属性 machineid的Getter方法.属性名：Machine ID
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getMachineid() {
return this.machineid;
} 

/**
* 属性machineid的Setter方法.属性名：Machine ID
* 创建日期:2017-10-18
* @param newMachineid java.lang.String
*/
public void setMachineid ( String machineid) {
this.machineid=machineid;
} 
 
/**
* 属性 machinemake的Getter方法.属性名：Machine Make
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getMachinemake() {
return this.machinemake;
} 

/**
* 属性machinemake的Setter方法.属性名：Machine Make
* 创建日期:2017-10-18
* @param newMachinemake java.lang.String
*/
public void setMachinemake ( String machinemake) {
this.machinemake=machinemake;
} 
 
/**
* 属性 machinemodel的Getter方法.属性名：Machine Model
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getMachinemodel() {
return this.machinemodel;
} 

/**
* 属性machinemodel的Setter方法.属性名：Machine Model
* 创建日期:2017-10-18
* @param newMachinemodel java.lang.String
*/
public void setMachinemodel ( String machinemodel) {
this.machinemodel=machinemodel;
} 
 
/**
* 属性 machinesubmodel的Getter方法.属性名：Machine Submodel
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getMachinesubmodel() {
return this.machinesubmodel;
} 

/**
* 属性machinesubmodel的Setter方法.属性名：Machine Submodel
* 创建日期:2017-10-18
* @param newMachinesubmodel java.lang.String
*/
public void setMachinesubmodel ( String machinesubmodel) {
this.machinesubmodel=machinesubmodel;
} 
 
/**
* 属性 totalofpkgs的Getter方法.属性名：Total # Of Pkgs
*  创建日期:2017-10-18
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getTotalofpkgs() {
return this.totalofpkgs;
} 

/**
* 属性totalofpkgs的Setter方法.属性名：Total # Of Pkgs
* 创建日期:2017-10-18
* @param newTotalofpkgs nc.vo.pub.lang.UFDouble
*/
public void setTotalofpkgs ( UFDouble totalofpkgs) {
this.totalofpkgs=totalofpkgs;
} 
 
/**
* 属性 grossweight的Getter方法.属性名：Gross Weight (KG)
*  创建日期:2017-10-18
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getGrossweight() {
return this.grossweight;
} 

/**
* 属性grossweight的Setter方法.属性名：Gross Weight (KG)
* 创建日期:2017-10-18
* @param newGrossweight nc.vo.pub.lang.UFDouble
*/
public void setGrossweight ( UFDouble grossweight) {
this.grossweight=grossweight;
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
* 属性 volumem3的Getter方法.属性名：Volume (M3)
*  创建日期:2017-10-18
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getVolumem3() {
return this.volumem3;
} 

/**
* 属性volumem3的Setter方法.属性名：Volume (M3)
* 创建日期:2017-10-18
* @param newVolumem3 nc.vo.pub.lang.UFDouble
*/
public void setVolumem3 ( UFDouble volumem3) {
this.volumem3=volumem3;
} 
 
/**
* 属性 lengthcm的Getter方法.属性名：LENGTH (CM)
*  创建日期:2017-10-18
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getLengthcm() {
return this.lengthcm;
} 

/**
* 属性lengthcm的Setter方法.属性名：LENGTH (CM)
* 创建日期:2017-10-18
* @param newLengthcm nc.vo.pub.lang.UFDouble
*/
public void setLengthcm ( UFDouble lengthcm) {
this.lengthcm=lengthcm;
} 
 
/**
* 属性 crate1的Getter方法.属性名：CRATE1 #
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getCrate1() {
return this.crate1;
} 

/**
* 属性crate1的Setter方法.属性名：CRATE1 #
* 创建日期:2017-10-18
* @param newCrate1 java.lang.String
*/
public void setCrate1 ( String crate1) {
this.crate1=crate1;
} 
 
/**
* 属性 widthcm的Getter方法.属性名：WIDTH (CM)
*  创建日期:2017-10-18
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getWidthcm() {
return this.widthcm;
} 

/**
* 属性widthcm的Setter方法.属性名：WIDTH (CM)
* 创建日期:2017-10-18
* @param newWidthcm nc.vo.pub.lang.UFDouble
*/
public void setWidthcm ( UFDouble widthcm) {
this.widthcm=widthcm;
} 
 
/**
* 属性 crate2的Getter方法.属性名：CRATE 2#
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getCrate2() {
return this.crate2;
} 

/**
* 属性crate2的Setter方法.属性名：CRATE 2#
* 创建日期:2017-10-18
* @param newCrate2 java.lang.String
*/
public void setCrate2 ( String crate2) {
this.crate2=crate2;
} 
 
/**
* 属性 heightcm的Getter方法.属性名：HEIGHT (CM)
*  创建日期:2017-10-18
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getHeightcm() {
return this.heightcm;
} 

/**
* 属性heightcm的Setter方法.属性名：HEIGHT (CM)
* 创建日期:2017-10-18
* @param newHeightcm nc.vo.pub.lang.UFDouble
*/
public void setHeightcm ( UFDouble heightcm) {
this.heightcm=heightcm;
} 
 
/**
* 属性 crate3的Getter方法.属性名：CRATE 3#
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getCrate3() {
return this.crate3;
} 

/**
* 属性crate3的Setter方法.属性名：CRATE 3#
* 创建日期:2017-10-18
* @param newCrate3 java.lang.String
*/
public void setCrate3 ( String crate3) {
this.crate3=crate3;
} 
 
/**
* 属性 specialinstruction的Getter方法.属性名：Special Instruction
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getSpecialinstruction() {
return this.specialinstruction;
} 

/**
* 属性specialinstruction的Setter方法.属性名：Special Instruction
* 创建日期:2017-10-18
* @param newSpecialinstruction java.lang.String
*/
public void setSpecialinstruction ( String specialinstruction) {
this.specialinstruction=specialinstruction;
} 
 
/**
* 属性 commoninstruction的Getter方法.属性名：Common Instruction
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getCommoninstruction() {
return this.commoninstruction;
} 

/**
* 属性commoninstruction的Setter方法.属性名：Common Instruction
* 创建日期:2017-10-18
* @param newCommoninstruction java.lang.String
*/
public void setCommoninstruction ( String commoninstruction) {
this.commoninstruction=commoninstruction;
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
* 属性 startdate的Getter方法.属性名：Start Date
*  创建日期:2017-10-18
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getStartdate() {
return this.startdate;
} 

/**
* 属性startdate的Setter方法.属性名：Start Date
* 创建日期:2017-10-18
* @param newStartdate nc.vo.pub.lang.UFDateTime
*/
public void setStartdate ( UFDateTime startdate) {
this.startdate=startdate;
} 
 
/**
* 属性 enddate的Getter方法.属性名：End Date
*  创建日期:2017-10-18
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getEnddate() {
return this.enddate;
} 

/**
* 属性enddate的Setter方法.属性名：End Date
* 创建日期:2017-10-18
* @param newEnddate nc.vo.pub.lang.UFDateTime
*/
public void setEnddate ( UFDateTime enddate) {
this.enddate=enddate;
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
* 属性 jobtype的Getter方法.属性名：Job Type
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getJobtype() {
return this.jobtype;
} 

/**
* 属性jobtype的Setter方法.属性名：Job Type
* 创建日期:2017-10-18
* @param newJobtype java.lang.String
*/
public void setJobtype ( String jobtype) {
this.jobtype=jobtype;
} 
 
/**
* 属性 oemtoolno的Getter方法.属性名：OEM Tool No
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getOemtoolno() {
return this.oemtoolno;
} 

/**
* 属性oemtoolno的Setter方法.属性名：OEM Tool No
* 创建日期:2017-10-18
* @param newOemtoolno java.lang.String
*/
public void setOemtoolno ( String oemtoolno) {
this.oemtoolno=oemtoolno;
} 
 
/**
* 属性 chargeableweight的Getter方法.属性名：Chargeable Weight (Kg)
*  创建日期:2017-10-18
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getChargeableweight() {
return this.chargeableweight;
} 

/**
* 属性chargeableweight的Setter方法.属性名：Chargeable Weight (Kg)
* 创建日期:2017-10-18
* @param newChargeableweight nc.vo.pub.lang.UFDouble
*/
public void setChargeableweight ( UFDouble chargeableweight) {
this.chargeableweight=chargeableweight;
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
* 属性 awb的Getter方法.属性名：AWB#
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getAwb() {
return this.awb;
} 

/**
* 属性awb的Setter方法.属性名：AWB#
* 创建日期:2017-10-18
* @param newAwb java.lang.String
*/
public void setAwb ( String awb) {
this.awb=awb;
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
    return VOMetaFactory.getInstance().getVOMeta("BHS.SoMoveHVO");
    }
   }
    