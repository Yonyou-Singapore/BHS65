package nc.vo.bhs.bhsdo;

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
 
public class BhsDoVO extends SuperVO {
	
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
*TO
*/
public String tocustomer;
/**
*ADDRESS
*/
public String toaddress;
/**
*D/O NO
*/
public String dono;
/**
*DODATE
*/
public UFDate dodate;
/**
*JOB NO
*/
public String dojobno;
/**
*Vessel
*/
public String dovessel;
/**
*Voyage
*/
public String dovoyage;
/**
*CONTAINER NO.
*/
public String docontainerno;
/**
*CONTAINER RECEIVE WITH GOOD CONDITION
*/
public String docondition;
/**
*REMARK
*/
public String doremark;
/**
*pk_saleorder
*/
public String pk_saleorder;
/**
*saleorderbillno
*/
public String saleorderbillno;
/**
*Customer PO No
*/
public String customerpono;
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
*To Corp
*/
public String tocorp;
/**
*To Code
*/
public String tocode;
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
*moveInstruction
*/
public String moveinstruction;
/**
*Ref
*/
public String ref;
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
*M2
*/
public UFDouble m2;
/**
*M3
*/
public UFDouble m3;
/**
*kg
*/
public UFDouble kg;
/**
*pkgs
*/
public UFDouble pkgs;
/**
*Chargeable Weight (Kg)
*/
public UFDouble chargeableweight;
/**
*Net Weight (KG)
*/
public UFDouble netweight;
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
/**
*AWB#
*/
public String awb;

public String deccription;



public String getDeccription() {
	return deccription;
}

public void setDeccription(String deccription) {
	this.deccription = deccription;
}

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
* 属性 tocustomer的Getter方法.属性名：TO
*  创建日期:2017-10-18
* @return nc.vo.bd.cust.CustomerVO
*/
public String getTocustomer() {
return this.tocustomer;
} 

/**
* 属性tocustomer的Setter方法.属性名：TO
* 创建日期:2017-10-18
* @param newTocustomer nc.vo.bd.cust.CustomerVO
*/
public void setTocustomer ( String tocustomer) {
this.tocustomer=tocustomer;
} 
 
/**
* 属性 toaddress的Getter方法.属性名：ADDRESS
*  创建日期:2017-10-18
* @return nc.vo.bd.cust.custaddress.CustaddressVO
*/
public String getToaddress() {
return this.toaddress;
} 

/**
* 属性toaddress的Setter方法.属性名：ADDRESS
* 创建日期:2017-10-18
* @param newToaddress nc.vo.bd.cust.custaddress.CustaddressVO
*/
public void setToaddress ( String toaddress) {
this.toaddress=toaddress;
} 
 
/**
* 属性 dono的Getter方法.属性名：D/O NO
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getDono() {
return this.dono;
} 

/**
* 属性dono的Setter方法.属性名：D/O NO
* 创建日期:2017-10-18
* @param newDono java.lang.String
*/
public void setDono ( String dono) {
this.dono=dono;
} 
 
/**
* 属性 dodate的Getter方法.属性名：DODATE
*  创建日期:2017-10-18
* @return nc.vo.pub.lang.UFDate
*/
public UFDate getDodate() {
return this.dodate;
} 

/**
* 属性dodate的Setter方法.属性名：DODATE
* 创建日期:2017-10-18
* @param newDodate nc.vo.pub.lang.UFDate
*/
public void setDodate ( UFDate dodate) {
this.dodate=dodate;
} 
 
/**
* 属性 dojobno的Getter方法.属性名：JOB NO
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getDojobno() {
return this.dojobno;
} 

/**
* 属性dojobno的Setter方法.属性名：JOB NO
* 创建日期:2017-10-18
* @param newDojobno java.lang.String
*/
public void setDojobno ( String dojobno) {
this.dojobno=dojobno;
} 
 
/**
* 属性 dovessel的Getter方法.属性名：Vessel
*  创建日期:2017-10-18
* @return nc.vo.bd.defdoc.DefdocVO
*/
public String getDovessel() {
return this.dovessel;
} 

/**
* 属性dovessel的Setter方法.属性名：Vessel
* 创建日期:2017-10-18
* @param newDovessel nc.vo.bd.defdoc.DefdocVO
*/
public void setDovessel ( String dovessel) {
this.dovessel=dovessel;
} 
 
/**
* 属性 dovoyage的Getter方法.属性名：Voyage
*  创建日期:2017-10-18
* @return nc.vo.bd.defdoc.DefdocVO
*/
public String getDovoyage() {
return this.dovoyage;
} 

/**
* 属性dovoyage的Setter方法.属性名：Voyage
* 创建日期:2017-10-18
* @param newDovoyage nc.vo.bd.defdoc.DefdocVO
*/
public void setDovoyage ( String dovoyage) {
this.dovoyage=dovoyage;
} 
 
/**
* 属性 docontainerno的Getter方法.属性名：CONTAINER NO.
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getDocontainerno() {
return this.docontainerno;
} 

/**
* 属性docontainerno的Setter方法.属性名：CONTAINER NO.
* 创建日期:2017-10-18
* @param newDocontainerno java.lang.String
*/
public void setDocontainerno ( String docontainerno) {
this.docontainerno=docontainerno;
} 
 
/**
* 属性 docondition的Getter方法.属性名：CONTAINER RECEIVE WITH GOOD CONDITION
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getDocondition() {
return this.docondition;
} 

/**
* 属性docondition的Setter方法.属性名：CONTAINER RECEIVE WITH GOOD CONDITION
* 创建日期:2017-10-18
* @param newDocondition java.lang.String
*/
public void setDocondition ( String docondition) {
this.docondition=docondition;
} 
 
/**
* 属性 doremark的Getter方法.属性名：REMARK
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getDoremark() {
return this.doremark;
} 

/**
* 属性doremark的Setter方法.属性名：REMARK
* 创建日期:2017-10-18
* @param newDoremark java.lang.String
*/
public void setDoremark ( String doremark) {
this.doremark=doremark;
} 
 
/**
* 属性 pk_saleorder的Getter方法.属性名：pk_saleorder
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getPk_saleorder() {
return this.pk_saleorder;
} 

/**
* 属性pk_saleorder的Setter方法.属性名：pk_saleorder
* 创建日期:2017-10-18
* @param newPk_saleorder java.lang.String
*/
public void setPk_saleorder ( String pk_saleorder) {
this.pk_saleorder=pk_saleorder;
} 
 
/**
* 属性 saleorderbillno的Getter方法.属性名：saleorderbillno
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getSaleorderbillno() {
return this.saleorderbillno;
} 

/**
* 属性saleorderbillno的Setter方法.属性名：saleorderbillno
* 创建日期:2017-10-18
* @param newSaleorderbillno java.lang.String
*/
public void setSaleorderbillno ( String saleorderbillno) {
this.saleorderbillno=saleorderbillno;
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
* 属性 moveinstruction的Getter方法.属性名：moveInstruction
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getMoveinstruction() {
return this.moveinstruction;
} 

/**
* 属性moveinstruction的Setter方法.属性名：moveInstruction
* 创建日期:2017-10-18
* @param newMoveinstruction java.lang.String
*/
public void setMoveinstruction ( String moveinstruction) {
this.moveinstruction=moveinstruction;
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
* 属性 micapno的Getter方法.属性名：Micap No
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getMicapno() {
return this.micapno;
} 

/**
* 属性micapno的Setter方法.属性名：Micap No
* 创建日期:2017-10-18
* @param newMicapno java.lang.String
*/
public void setMicapno ( String micapno) {
this.micapno=micapno;
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
* 属性 toolidlid的Getter方法.属性名：Tool ID/LID
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getToolidlid() {
return this.toolidlid;
} 

/**
* 属性toolidlid的Setter方法.属性名：Tool ID/LID
* 创建日期:2017-10-18
* @param newToolidlid java.lang.String
*/
public void setToolidlid ( String toolidlid) {
this.toolidlid=toolidlid;
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
* 属性 m2的Getter方法.属性名：M2
*  创建日期:2017-10-18
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getM2() {
return this.m2;
} 

/**
* 属性m2的Setter方法.属性名：M2
* 创建日期:2017-10-18
* @param newM2 nc.vo.pub.lang.UFDouble
*/
public void setM2 ( UFDouble m2) {
this.m2=m2;
} 
 
/**
* 属性 m3的Getter方法.属性名：M3
*  创建日期:2017-10-18
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getM3() {
return this.m3;
} 

/**
* 属性m3的Setter方法.属性名：M3
* 创建日期:2017-10-18
* @param newM3 nc.vo.pub.lang.UFDouble
*/
public void setM3 ( UFDouble m3) {
this.m3=m3;
} 
 
/**
* 属性 kg的Getter方法.属性名：kg
*  创建日期:2017-10-18
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getKg() {
return this.kg;
} 

/**
* 属性kg的Setter方法.属性名：kg
* 创建日期:2017-10-18
* @param newKg nc.vo.pub.lang.UFDouble
*/
public void setKg ( UFDouble kg) {
this.kg=kg;
} 
 
/**
* 属性 pkgs的Getter方法.属性名：pkgs
*  创建日期:2017-10-18
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getPkgs() {
return this.pkgs;
} 

/**
* 属性pkgs的Setter方法.属性名：pkgs
* 创建日期:2017-10-18
* @param newPkgs nc.vo.pub.lang.UFDouble
*/
public void setPkgs ( UFDouble pkgs) {
this.pkgs=pkgs;
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
* 属性 largestlength的Getter方法.属性名：Largest length
*  创建日期:2017-10-18
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getLargestlength() {
return this.largestlength;
} 

/**
* 属性largestlength的Setter方法.属性名：Largest length
* 创建日期:2017-10-18
* @param newLargestlength nc.vo.pub.lang.UFDouble
*/
public void setLargestlength ( UFDouble largestlength) {
this.largestlength=largestlength;
} 
 
/**
* 属性 largestwidth的Getter方法.属性名：Largest width
*  创建日期:2017-10-18
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getLargestwidth() {
return this.largestwidth;
} 

/**
* 属性largestwidth的Setter方法.属性名：Largest width
* 创建日期:2017-10-18
* @param newLargestwidth nc.vo.pub.lang.UFDouble
*/
public void setLargestwidth ( UFDouble largestwidth) {
this.largestwidth=largestwidth;
} 
 
/**
* 属性 largestheight的Getter方法.属性名：Largest height
*  创建日期:2017-10-18
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getLargestheight() {
return this.largestheight;
} 

/**
* 属性largestheight的Setter方法.属性名：Largest height
* 创建日期:2017-10-18
* @param newLargestheight nc.vo.pub.lang.UFDouble
*/
public void setLargestheight ( UFDouble largestheight) {
this.largestheight=largestheight;
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
* 属性 lcrate的Getter方法.属性名：Lcrate#
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getLcrate() {
return this.lcrate;
} 

/**
* 属性lcrate的Setter方法.属性名：Lcrate#
* 创建日期:2017-10-18
* @param newLcrate java.lang.String
*/
public void setLcrate ( String lcrate) {
this.lcrate=lcrate;
} 
 
/**
* 属性 wcrate的Getter方法.属性名：Wcrate#
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getWcrate() {
return this.wcrate;
} 

/**
* 属性wcrate的Setter方法.属性名：Wcrate#
* 创建日期:2017-10-18
* @param newWcrate java.lang.String
*/
public void setWcrate ( String wcrate) {
this.wcrate=wcrate;
} 
 
/**
* 属性 hcrate的Getter方法.属性名：Hcrate#
*  创建日期:2017-10-18
* @return java.lang.String
*/
public String getHcrate() {
return this.hcrate;
} 

/**
* 属性hcrate的Setter方法.属性名：Hcrate#
* 创建日期:2017-10-18
* @param newHcrate java.lang.String
*/
public void setHcrate ( String hcrate) {
this.hcrate=hcrate;
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
    return VOMetaFactory.getInstance().getVOMeta("bhs.BhsDoVO");
    }
   }
    