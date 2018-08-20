package nc.vo.bhs.build;

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
 
public class SoOrderBuildHVO extends SuperVO {
	
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
*Project start date
*/
public UFDate projectstartdate;
/**
*Move-in date
*/
public UFDate moveindate;
/**
*Install start date
*/
public UFDate installstartdate;
/**
*Power up date
*/
public UFDate powerupdate;
/**
*CT adjustment date
*/
public UFDate ctadjustmentdate;
/**
*Client PO
*/
public String customerpono;
/**
*BHS DO
*/
public String bhsdo;
/**
*Scope
*/
public String scope;
/**
*Mechanical install
*/
public UFBoolean mechanicalinstall;
/**
*Mechanical de-install
*/
public UFBoolean mechanicaldeinstall;
/**
*Pre-install
*/
public UFBoolean preinstall;
/**
*Job descriptions
*/
public String jobinstruction;
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
*Ref
*/
public String ref;
/**
*Job Type
*/
public String jobtype;
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
* 属性 projectstartdate的Getter方法.属性名：Project start date
*  创建日期:2017-10-18
* @return nc.vo.pub.lang.UFDate
*/
public UFDate getProjectstartdate() {
return this.projectstartdate;
} 

/**
* 属性projectstartdate的Setter方法.属性名：Project start date
* 创建日期:2017-10-18
* @param newProjectstartdate nc.vo.pub.lang.UFDate
*/
public void setProjectstartdate ( UFDate projectstartdate) {
this.projectstartdate=projectstartdate;
} 
 
/**
* 属性 moveindate的Getter方法.属性名：Move-in date
*  创建日期:2017-10-18
* @return nc.vo.pub.lang.UFDate
*/
public UFDate getMoveindate() {
return this.moveindate;
} 

/**
* 属性moveindate的Setter方法.属性名：Move-in date
* 创建日期:2017-10-18
* @param newMoveindate nc.vo.pub.lang.UFDate
*/
public void setMoveindate ( UFDate moveindate) {
this.moveindate=moveindate;
} 
 
/**
* 属性 installstartdate的Getter方法.属性名：Install start date
*  创建日期:2017-10-18
* @return nc.vo.pub.lang.UFDate
*/
public UFDate getInstallstartdate() {
return this.installstartdate;
} 

/**
* 属性installstartdate的Setter方法.属性名：Install start date
* 创建日期:2017-10-18
* @param newInstallstartdate nc.vo.pub.lang.UFDate
*/
public void setInstallstartdate ( UFDate installstartdate) {
this.installstartdate=installstartdate;
} 
 
/**
* 属性 powerupdate的Getter方法.属性名：Power up date
*  创建日期:2017-10-18
* @return nc.vo.pub.lang.UFDate
*/
public UFDate getPowerupdate() {
return this.powerupdate;
} 

/**
* 属性powerupdate的Setter方法.属性名：Power up date
* 创建日期:2017-10-18
* @param newPowerupdate nc.vo.pub.lang.UFDate
*/
public void setPowerupdate ( UFDate powerupdate) {
this.powerupdate=powerupdate;
} 
 
/**
* 属性 ctadjustmentdate的Getter方法.属性名：CT adjustment date
*  创建日期:2017-10-18
* @return nc.vo.pub.lang.UFDate
*/
public UFDate getCtadjustmentdate() {
return this.ctadjustmentdate;
} 

/**
* 属性ctadjustmentdate的Setter方法.属性名：CT adjustment date
* 创建日期:2017-10-18
* @param newCtadjustmentdate nc.vo.pub.lang.UFDate
*/
public void setCtadjustmentdate ( UFDate ctadjustmentdate) {
this.ctadjustmentdate=ctadjustmentdate;
} 
 
/**
* 属性 customerpono的Getter方法.属性名：Client PO
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getCustomerpono() {
return this.customerpono;
} 

/**
* 属性customerpono的Setter方法.属性名：Client PO
* 创建日期:2017-10-18
* @param newCustomerpono java.lang.String
*/
public void setCustomerpono ( String customerpono) {
this.customerpono=customerpono;
} 
 
/**
* 属性 bhsdo的Getter方法.属性名：BHS DO
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getBhsdo() {
return this.bhsdo;
} 

/**
* 属性bhsdo的Setter方法.属性名：BHS DO
* 创建日期:2017-10-18
* @param newBhsdo java.lang.String
*/
public void setBhsdo ( String bhsdo) {
this.bhsdo=bhsdo;
} 
 
/**
* 属性 scope的Getter方法.属性名：Scope
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getScope() {
return this.scope;
} 

/**
* 属性scope的Setter方法.属性名：Scope
* 创建日期:2017-10-18
* @param newScope java.lang.String
*/
public void setScope ( String scope) {
this.scope=scope;
} 
 
/**
* 属性 mechanicalinstall的Getter方法.属性名：Mechanical install
*  创建日期:2017-10-18
* @return nc.vo.pub.lang.UFBoolean
*/
public UFBoolean getMechanicalinstall() {
return this.mechanicalinstall;
} 

/**
* 属性mechanicalinstall的Setter方法.属性名：Mechanical install
* 创建日期:2017-10-18
* @param newMechanicalinstall nc.vo.pub.lang.UFBoolean
*/
public void setMechanicalinstall ( UFBoolean mechanicalinstall) {
this.mechanicalinstall=mechanicalinstall;
} 
 
/**
* 属性 mechanicaldeinstall的Getter方法.属性名：Mechanical de-install
*  创建日期:2017-10-18
* @return nc.vo.pub.lang.UFBoolean
*/
public UFBoolean getMechanicaldeinstall() {
return this.mechanicaldeinstall;
} 

/**
* 属性mechanicaldeinstall的Setter方法.属性名：Mechanical de-install
* 创建日期:2017-10-18
* @param newMechanicaldeinstall nc.vo.pub.lang.UFBoolean
*/
public void setMechanicaldeinstall ( UFBoolean mechanicaldeinstall) {
this.mechanicaldeinstall=mechanicaldeinstall;
} 
 
/**
* 属性 preinstall的Getter方法.属性名：Pre-install
*  创建日期:2017-10-18
* @return nc.vo.pub.lang.UFBoolean
*/
public UFBoolean getPreinstall() {
return this.preinstall;
} 

/**
* 属性preinstall的Setter方法.属性名：Pre-install
* 创建日期:2017-10-18
* @param newPreinstall nc.vo.pub.lang.UFBoolean
*/
public void setPreinstall ( UFBoolean preinstall) {
this.preinstall=preinstall;
} 
 
/**
* 属性 jobinstruction的Getter方法.属性名：Job descriptions
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getJobinstruction() {
return this.jobinstruction;
} 

/**
* 属性jobinstruction的Setter方法.属性名：Job descriptions
* 创建日期:2017-10-18
* @param newJobinstruction java.lang.String
*/
public void setJobinstruction ( String jobinstruction) {
this.jobinstruction=jobinstruction;
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
    return VOMetaFactory.getInstance().getVOMeta("bhs.SoOrderBuildHVO");
    }
   }
    