package nc.vo.bhs.somove;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

/**
 * <b> �˴���Ҫ�������๦�� </b>
 * <p>
 *   �˴�����۵�������Ϣ
 * </p>
 *  ��������:2017-10-18
 * @author YONYOU NC
 * @version NCPrj ??
 */
 
public class SoMoveHVO extends SuperVO {
	
/**
*��������
*/
public String billid;
/**
*����
*/
public String pk_group;
/**
*��֯
*/
public String pk_org;
/**
*��֯�汾
*/
public String pk_org_v;
/**
*���ݺ�
*/
public String vbillno;
/**
*��������
*/
public UFDate dbilldate;
/**
*����״̬
*/
public Integer vbillstatus;
/**
*ҵ������
*/
public String pk_busitype;
/**
*��������
*/
public String pk_billtype;
/**
*�������ͱ���
*/
public String transtypecode;
/**
*��������pk
*/
public String pk_transtype;
/**
*�Ƶ���
*/
public String creator;
/**
*�Ƶ�ʱ��
*/
public UFDateTime creationtime;
/**
*����޸���
*/
public String modifier;
/**
*����޸�ʱ��
*/
public UFDateTime modifiedtime;
/**
*������
*/
public String approver;
/**
*����ʱ��
*/
public UFDateTime approvedate;
/**
*��������
*/
public String approvenote;
/**
*�Զ�����1
*/
public String def1;
/**
*�Զ�����2
*/
public String def2;
/**
*�Զ�����3
*/
public String def3;
/**
*�Զ�����4
*/
public String def4;
/**
*�Զ�����5
*/
public String def5;
/**
*�Զ�����6
*/
public String def6;
/**
*�Զ�����7
*/
public String def7;
/**
*�Զ�����8
*/
public String def8;
/**
*�Զ�����9
*/
public String def9;
/**
*�Զ�����10
*/
public String def10;
/**
*�Զ�����11
*/
public String def11;
/**
*�Զ�����12
*/
public String def12;
/**
*�Զ�����13
*/
public String def13;
/**
*�Զ�����14
*/
public String def14;
/**
*�Զ�����15
*/
public String def15;
/**
*�Զ�����16
*/
public String def16;
/**
*�Զ�����17
*/
public String def17;
/**
*�Զ�����18
*/
public String def18;
/**
*�Զ�����19
*/
public String def19;
/**
*�Զ�����20
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
*��������
*/
public String csaleorderid;
/**
*������
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
*ʱ���
*/
public UFDateTime ts;
    
    
/**
* ���� billid��Getter����.����������������
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getBillid() {
return this.billid;
} 

/**
* ����billid��Setter����.����������������
* ��������:2017-10-18
* @param newBillid java.lang.String
*/
public void setBillid ( String billid) {
this.billid=billid;
} 
 
/**
* ���� pk_group��Getter����.������������
*  ��������:2017-10-18
* @return nc.vo.org.GroupVO
*/
public String getPk_group() {
return this.pk_group;
} 

/**
* ����pk_group��Setter����.������������
* ��������:2017-10-18
* @param newPk_group nc.vo.org.GroupVO
*/
public void setPk_group ( String pk_group) {
this.pk_group=pk_group;
} 
 
/**
* ���� pk_org��Getter����.����������֯
*  ��������:2017-10-18
* @return nc.vo.org.OrgVO
*/
public String getPk_org() {
return this.pk_org;
} 

/**
* ����pk_org��Setter����.����������֯
* ��������:2017-10-18
* @param newPk_org nc.vo.org.OrgVO
*/
public void setPk_org ( String pk_org) {
this.pk_org=pk_org;
} 
 
/**
* ���� pk_org_v��Getter����.����������֯�汾
*  ��������:2017-10-18
* @return nc.vo.vorg.OrgVersionVO
*/
public String getPk_org_v() {
return this.pk_org_v;
} 

/**
* ����pk_org_v��Setter����.����������֯�汾
* ��������:2017-10-18
* @param newPk_org_v nc.vo.vorg.OrgVersionVO
*/
public void setPk_org_v ( String pk_org_v) {
this.pk_org_v=pk_org_v;
} 
 
/**
* ���� vbillno��Getter����.�����������ݺ�
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getVbillno() {
return this.vbillno;
} 

/**
* ����vbillno��Setter����.�����������ݺ�
* ��������:2017-10-18
* @param newVbillno java.lang.String
*/
public void setVbillno ( String vbillno) {
this.vbillno=vbillno;
} 
 
/**
* ���� dbilldate��Getter����.����������������
*  ��������:2017-10-18
* @return nc.vo.pub.lang.UFDate
*/
public UFDate getDbilldate() {
return this.dbilldate;
} 

/**
* ����dbilldate��Setter����.����������������
* ��������:2017-10-18
* @param newDbilldate nc.vo.pub.lang.UFDate
*/
public void setDbilldate ( UFDate dbilldate) {
this.dbilldate=dbilldate;
} 
 
/**
* ���� vbillstatus��Getter����.������������״̬
*  ��������:2017-10-18
* @return nc.vo.pub.pf.BillStatusEnum
*/
public Integer getVbillstatus() {
return this.vbillstatus;
} 

/**
* ����vbillstatus��Setter����.������������״̬
* ��������:2017-10-18
* @param newVbillstatus nc.vo.pub.pf.BillStatusEnum
*/
public void setVbillstatus ( Integer vbillstatus) {
this.vbillstatus=vbillstatus;
} 
 
/**
* ���� pk_busitype��Getter����.��������ҵ������
*  ��������:2017-10-18
* @return nc.vo.pf.pub.BusitypeVO
*/
public String getPk_busitype() {
return this.pk_busitype;
} 

/**
* ����pk_busitype��Setter����.��������ҵ������
* ��������:2017-10-18
* @param newPk_busitype nc.vo.pf.pub.BusitypeVO
*/
public void setPk_busitype ( String pk_busitype) {
this.pk_busitype=pk_busitype;
} 
 
/**
* ���� pk_billtype��Getter����.����������������
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getPk_billtype() {
return this.pk_billtype;
} 

/**
* ����pk_billtype��Setter����.����������������
* ��������:2017-10-18
* @param newPk_billtype java.lang.String
*/
public void setPk_billtype ( String pk_billtype) {
this.pk_billtype=pk_billtype;
} 
 
/**
* ���� transtypecode��Getter����.���������������ͱ���
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getTranstypecode() {
return this.transtypecode;
} 

/**
* ����transtypecode��Setter����.���������������ͱ���
* ��������:2017-10-18
* @param newTranstypecode java.lang.String
*/
public void setTranstypecode ( String transtypecode) {
this.transtypecode=transtypecode;
} 
 
/**
* ���� pk_transtype��Getter����.����������������pk
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getPk_transtype() {
return this.pk_transtype;
} 

/**
* ����pk_transtype��Setter����.����������������pk
* ��������:2017-10-18
* @param newPk_transtype java.lang.String
*/
public void setPk_transtype ( String pk_transtype) {
this.pk_transtype=pk_transtype;
} 
 
/**
* ���� creator��Getter����.���������Ƶ���
*  ��������:2017-10-18
* @return nc.vo.sm.UserVO
*/
public String getCreator() {
return this.creator;
} 

/**
* ����creator��Setter����.���������Ƶ���
* ��������:2017-10-18
* @param newCreator nc.vo.sm.UserVO
*/
public void setCreator ( String creator) {
this.creator=creator;
} 
 
/**
* ���� creationtime��Getter����.���������Ƶ�ʱ��
*  ��������:2017-10-18
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getCreationtime() {
return this.creationtime;
} 

/**
* ����creationtime��Setter����.���������Ƶ�ʱ��
* ��������:2017-10-18
* @param newCreationtime nc.vo.pub.lang.UFDateTime
*/
public void setCreationtime ( UFDateTime creationtime) {
this.creationtime=creationtime;
} 
 
/**
* ���� modifier��Getter����.������������޸���
*  ��������:2017-10-18
* @return nc.vo.sm.UserVO
*/
public String getModifier() {
return this.modifier;
} 

/**
* ����modifier��Setter����.������������޸���
* ��������:2017-10-18
* @param newModifier nc.vo.sm.UserVO
*/
public void setModifier ( String modifier) {
this.modifier=modifier;
} 
 
/**
* ���� modifiedtime��Getter����.������������޸�ʱ��
*  ��������:2017-10-18
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getModifiedtime() {
return this.modifiedtime;
} 

/**
* ����modifiedtime��Setter����.������������޸�ʱ��
* ��������:2017-10-18
* @param newModifiedtime nc.vo.pub.lang.UFDateTime
*/
public void setModifiedtime ( UFDateTime modifiedtime) {
this.modifiedtime=modifiedtime;
} 
 
/**
* ���� approver��Getter����.��������������
*  ��������:2017-10-18
* @return nc.vo.sm.UserVO
*/
public String getApprover() {
return this.approver;
} 

/**
* ����approver��Setter����.��������������
* ��������:2017-10-18
* @param newApprover nc.vo.sm.UserVO
*/
public void setApprover ( String approver) {
this.approver=approver;
} 
 
/**
* ���� approvedate��Getter����.������������ʱ��
*  ��������:2017-10-18
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getApprovedate() {
return this.approvedate;
} 

/**
* ����approvedate��Setter����.������������ʱ��
* ��������:2017-10-18
* @param newApprovedate nc.vo.pub.lang.UFDateTime
*/
public void setApprovedate ( UFDateTime approvedate) {
this.approvedate=approvedate;
} 
 
/**
* ���� approvenote��Getter����.����������������
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getApprovenote() {
return this.approvenote;
} 

/**
* ����approvenote��Setter����.����������������
* ��������:2017-10-18
* @param newApprovenote java.lang.String
*/
public void setApprovenote ( String approvenote) {
this.approvenote=approvenote;
} 
 
/**
* ���� def1��Getter����.���������Զ�����1
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getDef1() {
return this.def1;
} 

/**
* ����def1��Setter����.���������Զ�����1
* ��������:2017-10-18
* @param newDef1 java.lang.String
*/
public void setDef1 ( String def1) {
this.def1=def1;
} 
 
/**
* ���� def2��Getter����.���������Զ�����2
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getDef2() {
return this.def2;
} 

/**
* ����def2��Setter����.���������Զ�����2
* ��������:2017-10-18
* @param newDef2 java.lang.String
*/
public void setDef2 ( String def2) {
this.def2=def2;
} 
 
/**
* ���� def3��Getter����.���������Զ�����3
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getDef3() {
return this.def3;
} 

/**
* ����def3��Setter����.���������Զ�����3
* ��������:2017-10-18
* @param newDef3 java.lang.String
*/
public void setDef3 ( String def3) {
this.def3=def3;
} 
 
/**
* ���� def4��Getter����.���������Զ�����4
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getDef4() {
return this.def4;
} 

/**
* ����def4��Setter����.���������Զ�����4
* ��������:2017-10-18
* @param newDef4 java.lang.String
*/
public void setDef4 ( String def4) {
this.def4=def4;
} 
 
/**
* ���� def5��Getter����.���������Զ�����5
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getDef5() {
return this.def5;
} 

/**
* ����def5��Setter����.���������Զ�����5
* ��������:2017-10-18
* @param newDef5 java.lang.String
*/
public void setDef5 ( String def5) {
this.def5=def5;
} 
 
/**
* ���� def6��Getter����.���������Զ�����6
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getDef6() {
return this.def6;
} 

/**
* ����def6��Setter����.���������Զ�����6
* ��������:2017-10-18
* @param newDef6 java.lang.String
*/
public void setDef6 ( String def6) {
this.def6=def6;
} 
 
/**
* ���� def7��Getter����.���������Զ�����7
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getDef7() {
return this.def7;
} 

/**
* ����def7��Setter����.���������Զ�����7
* ��������:2017-10-18
* @param newDef7 java.lang.String
*/
public void setDef7 ( String def7) {
this.def7=def7;
} 
 
/**
* ���� def8��Getter����.���������Զ�����8
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getDef8() {
return this.def8;
} 

/**
* ����def8��Setter����.���������Զ�����8
* ��������:2017-10-18
* @param newDef8 java.lang.String
*/
public void setDef8 ( String def8) {
this.def8=def8;
} 
 
/**
* ���� def9��Getter����.���������Զ�����9
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getDef9() {
return this.def9;
} 

/**
* ����def9��Setter����.���������Զ�����9
* ��������:2017-10-18
* @param newDef9 java.lang.String
*/
public void setDef9 ( String def9) {
this.def9=def9;
} 
 
/**
* ���� def10��Getter����.���������Զ�����10
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getDef10() {
return this.def10;
} 

/**
* ����def10��Setter����.���������Զ�����10
* ��������:2017-10-18
* @param newDef10 java.lang.String
*/
public void setDef10 ( String def10) {
this.def10=def10;
} 
 
/**
* ���� def11��Getter����.���������Զ�����11
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getDef11() {
return this.def11;
} 

/**
* ����def11��Setter����.���������Զ�����11
* ��������:2017-10-18
* @param newDef11 java.lang.String
*/
public void setDef11 ( String def11) {
this.def11=def11;
} 
 
/**
* ���� def12��Getter����.���������Զ�����12
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getDef12() {
return this.def12;
} 

/**
* ����def12��Setter����.���������Զ�����12
* ��������:2017-10-18
* @param newDef12 java.lang.String
*/
public void setDef12 ( String def12) {
this.def12=def12;
} 
 
/**
* ���� def13��Getter����.���������Զ�����13
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getDef13() {
return this.def13;
} 

/**
* ����def13��Setter����.���������Զ�����13
* ��������:2017-10-18
* @param newDef13 java.lang.String
*/
public void setDef13 ( String def13) {
this.def13=def13;
} 
 
/**
* ���� def14��Getter����.���������Զ�����14
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getDef14() {
return this.def14;
} 

/**
* ����def14��Setter����.���������Զ�����14
* ��������:2017-10-18
* @param newDef14 java.lang.String
*/
public void setDef14 ( String def14) {
this.def14=def14;
} 
 
/**
* ���� def15��Getter����.���������Զ�����15
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getDef15() {
return this.def15;
} 

/**
* ����def15��Setter����.���������Զ�����15
* ��������:2017-10-18
* @param newDef15 java.lang.String
*/
public void setDef15 ( String def15) {
this.def15=def15;
} 
 
/**
* ���� def16��Getter����.���������Զ�����16
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getDef16() {
return this.def16;
} 

/**
* ����def16��Setter����.���������Զ�����16
* ��������:2017-10-18
* @param newDef16 java.lang.String
*/
public void setDef16 ( String def16) {
this.def16=def16;
} 
 
/**
* ���� def17��Getter����.���������Զ�����17
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getDef17() {
return this.def17;
} 

/**
* ����def17��Setter����.���������Զ�����17
* ��������:2017-10-18
* @param newDef17 java.lang.String
*/
public void setDef17 ( String def17) {
this.def17=def17;
} 
 
/**
* ���� def18��Getter����.���������Զ�����18
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getDef18() {
return this.def18;
} 

/**
* ����def18��Setter����.���������Զ�����18
* ��������:2017-10-18
* @param newDef18 java.lang.String
*/
public void setDef18 ( String def18) {
this.def18=def18;
} 
 
/**
* ���� def19��Getter����.���������Զ�����19
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getDef19() {
return this.def19;
} 

/**
* ����def19��Setter����.���������Զ�����19
* ��������:2017-10-18
* @param newDef19 java.lang.String
*/
public void setDef19 ( String def19) {
this.def19=def19;
} 
 
/**
* ���� def20��Getter����.���������Զ�����20
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getDef20() {
return this.def20;
} 

/**
* ����def20��Setter����.���������Զ�����20
* ��������:2017-10-18
* @param newDef20 java.lang.String
*/
public void setDef20 ( String def20) {
this.def20=def20;
} 
 
/**
* ���� surveyreportnumber��Getter����.��������Survey Report Number
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getSurveyreportnumber() {
return this.surveyreportnumber;
} 

/**
* ����surveyreportnumber��Setter����.��������Survey Report Number
* ��������:2017-10-18
* @param newSurveyreportnumber java.lang.String
*/
public void setSurveyreportnumber ( String surveyreportnumber) {
this.surveyreportnumber=surveyreportnumber;
} 
 
/**
* ���� dono��Getter����.��������DO#
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getDono() {
return this.dono;
} 

/**
* ����dono��Setter����.��������DO#
* ��������:2017-10-18
* @param newDono java.lang.String
*/
public void setDono ( String dono) {
this.dono=dono;
} 
 
/**
* ���� jobdate��Getter����.��������Job Date
*  ��������:2017-10-18
* @return nc.vo.pub.lang.UFDate
*/
public UFDate getJobdate() {
return this.jobdate;
} 

/**
* ����jobdate��Setter����.��������Job Date
* ��������:2017-10-18
* @param newJobdate nc.vo.pub.lang.UFDate
*/
public void setJobdate ( UFDate jobdate) {
this.jobdate=jobdate;
} 
 
/**
* ���� departuretime��Getter����.��������Departure Time
*  ��������:2017-10-18
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getDeparturetime() {
return this.departuretime;
} 

/**
* ����departuretime��Setter����.��������Departure Time
* ��������:2017-10-18
* @param newDeparturetime nc.vo.pub.lang.UFDateTime
*/
public void setDeparturetime ( UFDateTime departuretime) {
this.departuretime=departuretime;
} 
 
/**
* ���� jobstarttime��Getter����.��������Job Start Time
*  ��������:2017-10-18
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getJobstarttime() {
return this.jobstarttime;
} 

/**
* ����jobstarttime��Setter����.��������Job Start Time
* ��������:2017-10-18
* @param newJobstarttime nc.vo.pub.lang.UFDateTime
*/
public void setJobstarttime ( UFDateTime jobstarttime) {
this.jobstarttime=jobstarttime;
} 
 
/**
* ���� jobendtime��Getter����.��������Job End Time
*  ��������:2017-10-18
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getJobendtime() {
return this.jobendtime;
} 

/**
* ����jobendtime��Setter����.��������Job End Time
* ��������:2017-10-18
* @param newJobendtime nc.vo.pub.lang.UFDateTime
*/
public void setJobendtime ( UFDateTime jobendtime) {
this.jobendtime=jobendtime;
} 
 
/**
* ���� fromcorp��Getter����.��������From Corp
*  ��������:2017-10-18
* @return nc.vo.bd.cust.addressdoc.AddressDocVO
*/
public String getFromcorp() {
return this.fromcorp;
} 

/**
* ����fromcorp��Setter����.��������From Corp
* ��������:2017-10-18
* @param newFromcorp nc.vo.bd.cust.addressdoc.AddressDocVO
*/
public void setFromcorp ( String fromcorp) {
this.fromcorp=fromcorp;
} 
 
/**
* ���� fromaddress��Getter����.��������From Address
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getFromaddress() {
return this.fromaddress;
} 

/**
* ����fromaddress��Setter����.��������From Address
* ��������:2017-10-18
* @param newFromaddress java.lang.String
*/
public void setFromaddress ( String fromaddress) {
this.fromaddress=fromaddress;
} 
 
/**
* ���� fromcode��Getter����.��������From Code
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getFromcode() {
return this.fromcode;
} 

/**
* ����fromcode��Setter����.��������From Code
* ��������:2017-10-18
* @param newFromcode java.lang.String
*/
public void setFromcode ( String fromcode) {
this.fromcode=fromcode;
} 
 
/**
* ���� sublocation1��Getter����.��������Sublocation1
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getSublocation1() {
return this.sublocation1;
} 

/**
* ����sublocation1��Setter����.��������Sublocation1
* ��������:2017-10-18
* @param newSublocation1 java.lang.String
*/
public void setSublocation1 ( String sublocation1) {
this.sublocation1=sublocation1;
} 
 
/**
* ���� tocorp��Getter����.��������To Corp
*  ��������:2017-10-18
* @return nc.vo.bd.cust.addressdoc.AddressDocVO
*/
public String getTocorp() {
return this.tocorp;
} 

/**
* ����tocorp��Setter����.��������To Corp
* ��������:2017-10-18
* @param newTocorp nc.vo.bd.cust.addressdoc.AddressDocVO
*/
public void setTocorp ( String tocorp) {
this.tocorp=tocorp;
} 
 
/**
* ���� toaddress��Getter����.��������To Address
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getToaddress() {
return this.toaddress;
} 

/**
* ����toaddress��Setter����.��������To Address
* ��������:2017-10-18
* @param newToaddress java.lang.String
*/
public void setToaddress ( String toaddress) {
this.toaddress=toaddress;
} 
 
/**
* ���� tocode��Getter����.��������To Code
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getTocode() {
return this.tocode;
} 

/**
* ����tocode��Setter����.��������To Code
* ��������:2017-10-18
* @param newTocode java.lang.String
*/
public void setTocode ( String tocode) {
this.tocode=tocode;
} 
 
/**
* ���� sublocation2��Getter����.��������Sublocation2
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getSublocation2() {
return this.sublocation2;
} 

/**
* ����sublocation2��Setter����.��������Sublocation2
* ��������:2017-10-18
* @param newSublocation2 java.lang.String
*/
public void setSublocation2 ( String sublocation2) {
this.sublocation2=sublocation2;
} 
 
/**
* ���� contactperson��Getter����.��������Contact Person
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getContactperson() {
return this.contactperson;
} 

/**
* ����contactperson��Setter����.��������Contact Person
* ��������:2017-10-18
* @param newContactperson java.lang.String
*/
public void setContactperson ( String contactperson) {
this.contactperson=contactperson;
} 
 
/**
* ���� contactpersphone��Getter����.��������Contact PersPhone
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getContactpersphone() {
return this.contactpersphone;
} 

/**
* ����contactpersphone��Setter����.��������Contact PersPhone
* ��������:2017-10-18
* @param newContactpersphone java.lang.String
*/
public void setContactpersphone ( String contactpersphone) {
this.contactpersphone=contactpersphone;
} 
 
/**
* ���� pocostcenternumber��Getter����.��������Po/ Cost Center Number
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getPocostcenternumber() {
return this.pocostcenternumber;
} 

/**
* ����pocostcenternumber��Setter����.��������Po/ Cost Center Number
* ��������:2017-10-18
* @param newPocostcenternumber java.lang.String
*/
public void setPocostcenternumber ( String pocostcenternumber) {
this.pocostcenternumber=pocostcenternumber;
} 
 
/**
* ���� micapnumbermachineid��Getter����.��������Micap Number / Machine ID
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getMicapnumbermachineid() {
return this.micapnumbermachineid;
} 

/**
* ����micapnumbermachineid��Setter����.��������Micap Number / Machine ID
* ��������:2017-10-18
* @param newMicapnumbermachineid java.lang.String
*/
public void setMicapnumbermachineid ( String micapnumbermachineid) {
this.micapnumbermachineid=micapnumbermachineid;
} 
 
/**
* ���� lidnumber��Getter����.��������Lid Number
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getLidnumber() {
return this.lidnumber;
} 

/**
* ����lidnumber��Setter����.��������Lid Number
* ��������:2017-10-18
* @param newLidnumber java.lang.String
*/
public void setLidnumber ( String lidnumber) {
this.lidnumber=lidnumber;
} 
 
/**
* ���� machineid��Getter����.��������Machine ID
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getMachineid() {
return this.machineid;
} 

/**
* ����machineid��Setter����.��������Machine ID
* ��������:2017-10-18
* @param newMachineid java.lang.String
*/
public void setMachineid ( String machineid) {
this.machineid=machineid;
} 
 
/**
* ���� machinemake��Getter����.��������Machine Make
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getMachinemake() {
return this.machinemake;
} 

/**
* ����machinemake��Setter����.��������Machine Make
* ��������:2017-10-18
* @param newMachinemake java.lang.String
*/
public void setMachinemake ( String machinemake) {
this.machinemake=machinemake;
} 
 
/**
* ���� machinemodel��Getter����.��������Machine Model
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getMachinemodel() {
return this.machinemodel;
} 

/**
* ����machinemodel��Setter����.��������Machine Model
* ��������:2017-10-18
* @param newMachinemodel java.lang.String
*/
public void setMachinemodel ( String machinemodel) {
this.machinemodel=machinemodel;
} 
 
/**
* ���� machinesubmodel��Getter����.��������Machine Submodel
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getMachinesubmodel() {
return this.machinesubmodel;
} 

/**
* ����machinesubmodel��Setter����.��������Machine Submodel
* ��������:2017-10-18
* @param newMachinesubmodel java.lang.String
*/
public void setMachinesubmodel ( String machinesubmodel) {
this.machinesubmodel=machinesubmodel;
} 
 
/**
* ���� totalofpkgs��Getter����.��������Total # Of Pkgs
*  ��������:2017-10-18
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getTotalofpkgs() {
return this.totalofpkgs;
} 

/**
* ����totalofpkgs��Setter����.��������Total # Of Pkgs
* ��������:2017-10-18
* @param newTotalofpkgs nc.vo.pub.lang.UFDouble
*/
public void setTotalofpkgs ( UFDouble totalofpkgs) {
this.totalofpkgs=totalofpkgs;
} 
 
/**
* ���� grossweight��Getter����.��������Gross Weight (KG)
*  ��������:2017-10-18
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getGrossweight() {
return this.grossweight;
} 

/**
* ����grossweight��Setter����.��������Gross Weight (KG)
* ��������:2017-10-18
* @param newGrossweight nc.vo.pub.lang.UFDouble
*/
public void setGrossweight ( UFDouble grossweight) {
this.grossweight=grossweight;
} 
 
/**
* ���� netweight��Getter����.��������Net Weight (KG)
*  ��������:2017-10-18
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getNetweight() {
return this.netweight;
} 

/**
* ����netweight��Setter����.��������Net Weight (KG)
* ��������:2017-10-18
* @param newNetweight nc.vo.pub.lang.UFDouble
*/
public void setNetweight ( UFDouble netweight) {
this.netweight=netweight;
} 
 
/**
* ���� volumem3��Getter����.��������Volume (M3)
*  ��������:2017-10-18
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getVolumem3() {
return this.volumem3;
} 

/**
* ����volumem3��Setter����.��������Volume (M3)
* ��������:2017-10-18
* @param newVolumem3 nc.vo.pub.lang.UFDouble
*/
public void setVolumem3 ( UFDouble volumem3) {
this.volumem3=volumem3;
} 
 
/**
* ���� lengthcm��Getter����.��������LENGTH (CM)
*  ��������:2017-10-18
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getLengthcm() {
return this.lengthcm;
} 

/**
* ����lengthcm��Setter����.��������LENGTH (CM)
* ��������:2017-10-18
* @param newLengthcm nc.vo.pub.lang.UFDouble
*/
public void setLengthcm ( UFDouble lengthcm) {
this.lengthcm=lengthcm;
} 
 
/**
* ���� crate1��Getter����.��������CRATE1 #
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getCrate1() {
return this.crate1;
} 

/**
* ����crate1��Setter����.��������CRATE1 #
* ��������:2017-10-18
* @param newCrate1 java.lang.String
*/
public void setCrate1 ( String crate1) {
this.crate1=crate1;
} 
 
/**
* ���� widthcm��Getter����.��������WIDTH (CM)
*  ��������:2017-10-18
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getWidthcm() {
return this.widthcm;
} 

/**
* ����widthcm��Setter����.��������WIDTH (CM)
* ��������:2017-10-18
* @param newWidthcm nc.vo.pub.lang.UFDouble
*/
public void setWidthcm ( UFDouble widthcm) {
this.widthcm=widthcm;
} 
 
/**
* ���� crate2��Getter����.��������CRATE 2#
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getCrate2() {
return this.crate2;
} 

/**
* ����crate2��Setter����.��������CRATE 2#
* ��������:2017-10-18
* @param newCrate2 java.lang.String
*/
public void setCrate2 ( String crate2) {
this.crate2=crate2;
} 
 
/**
* ���� heightcm��Getter����.��������HEIGHT (CM)
*  ��������:2017-10-18
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getHeightcm() {
return this.heightcm;
} 

/**
* ����heightcm��Setter����.��������HEIGHT (CM)
* ��������:2017-10-18
* @param newHeightcm nc.vo.pub.lang.UFDouble
*/
public void setHeightcm ( UFDouble heightcm) {
this.heightcm=heightcm;
} 
 
/**
* ���� crate3��Getter����.��������CRATE 3#
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getCrate3() {
return this.crate3;
} 

/**
* ����crate3��Setter����.��������CRATE 3#
* ��������:2017-10-18
* @param newCrate3 java.lang.String
*/
public void setCrate3 ( String crate3) {
this.crate3=crate3;
} 
 
/**
* ���� specialinstruction��Getter����.��������Special Instruction
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getSpecialinstruction() {
return this.specialinstruction;
} 

/**
* ����specialinstruction��Setter����.��������Special Instruction
* ��������:2017-10-18
* @param newSpecialinstruction java.lang.String
*/
public void setSpecialinstruction ( String specialinstruction) {
this.specialinstruction=specialinstruction;
} 
 
/**
* ���� commoninstruction��Getter����.��������Common Instruction
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getCommoninstruction() {
return this.commoninstruction;
} 

/**
* ����commoninstruction��Setter����.��������Common Instruction
* ��������:2017-10-18
* @param newCommoninstruction java.lang.String
*/
public void setCommoninstruction ( String commoninstruction) {
this.commoninstruction=commoninstruction;
} 
 
/**
* ���� csaleorderid��Getter����.����������������
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getCsaleorderid() {
return this.csaleorderid;
} 

/**
* ����csaleorderid��Setter����.����������������
* ��������:2017-10-18
* @param newCsaleorderid java.lang.String
*/
public void setCsaleorderid ( String csaleorderid) {
this.csaleorderid=csaleorderid;
} 
 
/**
* ���� csaleordercode��Getter����.��������������
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getCsaleordercode() {
return this.csaleordercode;
} 

/**
* ����csaleordercode��Setter����.��������������
* ��������:2017-10-18
* @param newCsaleordercode java.lang.String
*/
public void setCsaleordercode ( String csaleordercode) {
this.csaleordercode=csaleordercode;
} 
 
/**
* ���� startdate��Getter����.��������Start Date
*  ��������:2017-10-18
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getStartdate() {
return this.startdate;
} 

/**
* ����startdate��Setter����.��������Start Date
* ��������:2017-10-18
* @param newStartdate nc.vo.pub.lang.UFDateTime
*/
public void setStartdate ( UFDateTime startdate) {
this.startdate=startdate;
} 
 
/**
* ���� enddate��Getter����.��������End Date
*  ��������:2017-10-18
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getEnddate() {
return this.enddate;
} 

/**
* ����enddate��Setter����.��������End Date
* ��������:2017-10-18
* @param newEnddate nc.vo.pub.lang.UFDateTime
*/
public void setEnddate ( UFDateTime enddate) {
this.enddate=enddate;
} 
 
/**
* ���� surveyno��Getter����.��������Survey No
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getSurveyno() {
return this.surveyno;
} 

/**
* ����surveyno��Setter����.��������Survey No
* ��������:2017-10-18
* @param newSurveyno java.lang.String
*/
public void setSurveyno ( String surveyno) {
this.surveyno=surveyno;
} 
 
/**
* ���� surveyby��Getter����.��������Survey By
*  ��������:2017-10-18
* @return nc.vo.bd.psn.PsndocVO
*/
public String getSurveyby() {
return this.surveyby;
} 

/**
* ����surveyby��Setter����.��������Survey By
* ��������:2017-10-18
* @param newSurveyby nc.vo.bd.psn.PsndocVO
*/
public void setSurveyby ( String surveyby) {
this.surveyby=surveyby;
} 
 
/**
* ���� surveydate��Getter����.��������Survey Date
*  ��������:2017-10-18
* @return nc.vo.pub.lang.UFDate
*/
public UFDate getSurveydate() {
return this.surveydate;
} 

/**
* ����surveydate��Setter����.��������Survey Date
* ��������:2017-10-18
* @param newSurveydate nc.vo.pub.lang.UFDate
*/
public void setSurveydate ( UFDate surveydate) {
this.surveydate=surveydate;
} 
 
/**
* ���� customerpono��Getter����.��������Customer PO No
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getCustomerpono() {
return this.customerpono;
} 

/**
* ����customerpono��Setter����.��������Customer PO No
* ��������:2017-10-18
* @param newCustomerpono java.lang.String
*/
public void setCustomerpono ( String customerpono) {
this.customerpono=customerpono;
} 
 
/**
* ���� attnto��Getter����.��������Attn To
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getAttnto() {
return this.attnto;
} 

/**
* ����attnto��Setter����.��������Attn To
* ��������:2017-10-18
* @param newAttnto java.lang.String
*/
public void setAttnto ( String attnto) {
this.attnto=attnto;
} 
 
/**
* ���� subject��Getter����.��������subject
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getSubject() {
return this.subject;
} 

/**
* ����subject��Setter����.��������subject
* ��������:2017-10-18
* @param newSubject java.lang.String
*/
public void setSubject ( String subject) {
this.subject=subject;
} 
 
/**
* ���� jobinstruction��Getter����.��������jobInstruction
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getJobinstruction() {
return this.jobinstruction;
} 

/**
* ����jobinstruction��Setter����.��������jobInstruction
* ��������:2017-10-18
* @param newJobinstruction java.lang.String
*/
public void setJobinstruction ( String jobinstruction) {
this.jobinstruction=jobinstruction;
} 
 
/**
* ���� ref��Getter����.��������Ref
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getRef() {
return this.ref;
} 

/**
* ����ref��Setter����.��������Ref
* ��������:2017-10-18
* @param newRef java.lang.String
*/
public void setRef ( String ref) {
this.ref=ref;
} 
 
/**
* ���� jobtype��Getter����.��������Job Type
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getJobtype() {
return this.jobtype;
} 

/**
* ����jobtype��Setter����.��������Job Type
* ��������:2017-10-18
* @param newJobtype java.lang.String
*/
public void setJobtype ( String jobtype) {
this.jobtype=jobtype;
} 
 
/**
* ���� oemtoolno��Getter����.��������OEM Tool No
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getOemtoolno() {
return this.oemtoolno;
} 

/**
* ����oemtoolno��Setter����.��������OEM Tool No
* ��������:2017-10-18
* @param newOemtoolno java.lang.String
*/
public void setOemtoolno ( String oemtoolno) {
this.oemtoolno=oemtoolno;
} 
 
/**
* ���� chargeableweight��Getter����.��������Chargeable Weight (Kg)
*  ��������:2017-10-18
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getChargeableweight() {
return this.chargeableweight;
} 

/**
* ����chargeableweight��Setter����.��������Chargeable Weight (Kg)
* ��������:2017-10-18
* @param newChargeableweight nc.vo.pub.lang.UFDouble
*/
public void setChargeableweight ( UFDouble chargeableweight) {
this.chargeableweight=chargeableweight;
} 
 
/**
* ���� largestweight��Getter����.��������Largest weight
*  ��������:2017-10-18
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getLargestweight() {
return this.largestweight;
} 

/**
* ����largestweight��Setter����.��������Largest weight
* ��������:2017-10-18
* @param newLargestweight nc.vo.pub.lang.UFDouble
*/
public void setLargestweight ( UFDouble largestweight) {
this.largestweight=largestweight;
} 
 
/**
* ���� kcrate��Getter����.��������Kcrate#
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getKcrate() {
return this.kcrate;
} 

/**
* ����kcrate��Setter����.��������Kcrate#
* ��������:2017-10-18
* @param newKcrate java.lang.String
*/
public void setKcrate ( String kcrate) {
this.kcrate=kcrate;
} 
 
/**
* ���� awb��Getter����.��������AWB#
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getAwb() {
return this.awb;
} 

/**
* ����awb��Setter����.��������AWB#
* ��������:2017-10-18
* @param newAwb java.lang.String
*/
public void setAwb ( String awb) {
this.awb=awb;
} 
 
/**
* ���� ����ʱ�����Getter����.��������ʱ���
*  ��������:2017-10-18
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getTs() {
return this.ts;
}
/**
* ��������ʱ�����Setter����.��������ʱ���
* ��������:2017-10-18
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
    