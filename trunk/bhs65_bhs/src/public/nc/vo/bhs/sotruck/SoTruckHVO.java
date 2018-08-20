package nc.vo.bhs.sotruck;

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
 
public class SoTruckHVO extends SuperVO {
	
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
*��������
*/
public String csaleorderid;
/**
*������
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
* ���� tstransactiondate��Getter����.��������TsTransaction Date
*  ��������:2017-10-18
* @return nc.vo.pub.lang.UFDate
*/
public UFDate getTstransactiondate() {
return this.tstransactiondate;
} 

/**
* ����tstransactiondate��Setter����.��������TsTransaction Date
* ��������:2017-10-18
* @param newTstransactiondate nc.vo.pub.lang.UFDate
*/
public void setTstransactiondate ( UFDate tstransactiondate) {
this.tstransactiondate=tstransactiondate;
} 
 
/**
* ���� tsjoborderno��Getter����.��������TsJob Order #
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getTsjoborderno() {
return this.tsjoborderno;
} 

/**
* ����tsjoborderno��Setter����.��������TsJob Order #
* ��������:2017-10-18
* @param newTsjoborderno java.lang.String
*/
public void setTsjoborderno ( String tsjoborderno) {
this.tsjoborderno=tsjoborderno;
} 
 
/**
* ���� tscustomerpono��Getter����.��������TsCustomer PO#
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getTscustomerpono() {
return this.tscustomerpono;
} 

/**
* ����tscustomerpono��Setter����.��������TsCustomer PO#
* ��������:2017-10-18
* @param newTscustomerpono java.lang.String
*/
public void setTscustomerpono ( String tscustomerpono) {
this.tscustomerpono=tscustomerpono;
} 
 
/**
* ���� tsquotationno��Getter����.��������TsQuotation #
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getTsquotationno() {
return this.tsquotationno;
} 

/**
* ����tsquotationno��Setter����.��������TsQuotation #
* ��������:2017-10-18
* @param newTsquotationno java.lang.String
*/
public void setTsquotationno ( String tsquotationno) {
this.tsquotationno=tsquotationno;
} 
 
/**
* ���� tspreferreddatetime��Getter����.��������TsCustomer Preferred Date/Time
*  ��������:2017-10-18
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getTspreferreddatetime() {
return this.tspreferreddatetime;
} 

/**
* ����tspreferreddatetime��Setter����.��������TsCustomer Preferred Date/Time
* ��������:2017-10-18
* @param newTspreferreddatetime nc.vo.pub.lang.UFDateTime
*/
public void setTspreferreddatetime ( UFDateTime tspreferreddatetime) {
this.tspreferreddatetime=tspreferreddatetime;
} 
 
/**
* ���� tsimportvessel��Getter����.��������TsVessel
*  ��������:2017-10-18
* @return nc.vo.bd.defdoc.DefdocVO
*/
public String getTsimportvessel() {
return this.tsimportvessel;
} 

/**
* ����tsimportvessel��Setter����.��������TsVessel
* ��������:2017-10-18
* @param newTsimportvessel nc.vo.bd.defdoc.DefdocVO
*/
public void setTsimportvessel ( String tsimportvessel) {
this.tsimportvessel=tsimportvessel;
} 
 
/**
* ���� tsportofloading��Getter����.��������TsPort of Loading
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getTsportofloading() {
return this.tsportofloading;
} 

/**
* ����tsportofloading��Setter����.��������TsPort of Loading
* ��������:2017-10-18
* @param newTsportofloading java.lang.String
*/
public void setTsportofloading ( String tsportofloading) {
this.tsportofloading=tsportofloading;
} 
 
/**
* ���� tsimportvoyage��Getter����.��������TsVoyage
*  ��������:2017-10-18
* @return nc.vo.bd.defdoc.DefdocVO
*/
public String getTsimportvoyage() {
return this.tsimportvoyage;
} 

/**
* ����tsimportvoyage��Setter����.��������TsVoyage
* ��������:2017-10-18
* @param newTsimportvoyage nc.vo.bd.defdoc.DefdocVO
*/
public void setTsimportvoyage ( String tsimportvoyage) {
this.tsimportvoyage=tsimportvoyage;
} 
 
/**
* ���� tsimportagent��Getter����.��������TsAgent
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getTsimportagent() {
return this.tsimportagent;
} 

/**
* ����tsimportagent��Setter����.��������TsAgent
* ��������:2017-10-18
* @param newTsimportagent java.lang.String
*/
public void setTsimportagent ( String tsimportagent) {
this.tsimportagent=tsimportagent;
} 
 
/**
* ���� tsimportpermitno��Getter����.��������TsPermit #
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getTsimportpermitno() {
return this.tsimportpermitno;
} 

/**
* ����tsimportpermitno��Setter����.��������TsPermit #
* ��������:2017-10-18
* @param newTsimportpermitno java.lang.String
*/
public void setTsimportpermitno ( String tsimportpermitno) {
this.tsimportpermitno=tsimportpermitno;
} 
 
/**
* ���� tspoliceescortpermitno��Getter����.��������TsPolice escort permit # (if applicable)
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getTspoliceescortpermitno() {
return this.tspoliceescortpermitno;
} 

/**
* ����tspoliceescortpermitno��Setter����.��������TsPolice escort permit # (if applicable)
* ��������:2017-10-18
* @param newTspoliceescortpermitno java.lang.String
*/
public void setTspoliceescortpermitno ( String tspoliceescortpermitno) {
this.tspoliceescortpermitno=tspoliceescortpermitno;
} 
 
/**
* ���� tslinktocontractor��Getter����.��������TsLink To Contractor
*  ��������:2017-10-18
* @return nc.vo.bd.supplier.SupplierVO
*/
public String getTslinktocontractor() {
return this.tslinktocontractor;
} 

/**
* ����tslinktocontractor��Setter����.��������TsLink To Contractor
* ��������:2017-10-18
* @param newTslinktocontractor nc.vo.bd.supplier.SupplierVO
*/
public void setTslinktocontractor ( String tslinktocontractor) {
this.tslinktocontractor=tslinktocontractor;
} 
 
/**
* ���� tsreturntoyard��Getter����.��������TsReturn to yard
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getTsreturntoyard() {
return this.tsreturntoyard;
} 

/**
* ����tsreturntoyard��Setter����.��������TsReturn to yard
* ��������:2017-10-18
* @param newTsreturntoyard java.lang.String
*/
public void setTsreturntoyard ( String tsreturntoyard) {
this.tsreturntoyard=tsreturntoyard;
} 
 
/**
* ���� tsimporteta��Getter����.��������TsETA
*  ��������:2017-10-18
* @return nc.vo.pub.lang.UFDate
*/
public UFDate getTsimporteta() {
return this.tsimporteta;
} 

/**
* ����tsimporteta��Setter����.��������TsETA
* ��������:2017-10-18
* @param newTsimporteta nc.vo.pub.lang.UFDate
*/
public void setTsimporteta ( UFDate tsimporteta) {
this.tsimporteta=tsimporteta;
} 
 
/**
* ���� tsairride��Getter����.��������TsAIR RIDE
*  ��������:2017-10-18
* @return java.lang.String
*/
public UFBoolean getTsairride() {
return this.tsairride;
} 

/**
* ����tsairride��Setter����.��������TsAIR RIDE
* ��������:2017-10-18
* @param newTsairride java.lang.String
*/
public void setTsairride ( UFBoolean tsairride) {
this.tsairride=tsairride;
} 
 
/**
* ���� tsunstuffing��Getter����.��������TsUNSTUFFING
*  ��������:2017-10-18
* @return nc.vo.pub.lang.UFBoolean
*/
public UFBoolean getTsunstuffing() {
return this.tsunstuffing;
} 

/**
* ����tsunstuffing��Setter����.��������TsUNSTUFFING
* ��������:2017-10-18
* @param newTsunstuffing nc.vo.pub.lang.UFBoolean
*/
public void setTsunstuffing ( UFBoolean tsunstuffing) {
this.tsunstuffing=tsunstuffing;
} 
 
/**
* ���� tsloosecargodeliver��Getter����.��������TsLOOSE CARGO DELIVER
*  ��������:2017-10-18
* @return nc.vo.pub.lang.UFBoolean
*/
public UFBoolean getTsloosecargodeliver() {
return this.tsloosecargodeliver;
} 

/**
* ����tsloosecargodeliver��Setter����.��������TsLOOSE CARGO DELIVER
* ��������:2017-10-18
* @param newTsloosecargodeliver nc.vo.pub.lang.UFBoolean
*/
public void setTsloosecargodeliver ( UFBoolean tsloosecargodeliver) {
this.tsloosecargodeliver=tsloosecargodeliver;
} 
 
/**
* ���� tstruckinstruction��Getter����.��������TsTruck Instruction
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getTstruckinstruction() {
return this.tstruckinstruction;
} 

/**
* ����tstruckinstruction��Setter����.��������TsTruck Instruction
* ��������:2017-10-18
* @param newTstruckinstruction java.lang.String
*/
public void setTstruckinstruction ( String tstruckinstruction) {
this.tstruckinstruction=tstruckinstruction;
} 
 
/**
* ���� tsmoveinstruction��Getter����.��������TsMove Instruction
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getTsmoveinstruction() {
return this.tsmoveinstruction;
} 

/**
* ����tsmoveinstruction��Setter����.��������TsMove Instruction
* ��������:2017-10-18
* @param newTsmoveinstruction java.lang.String
*/
public void setTsmoveinstruction ( String tsmoveinstruction) {
this.tsmoveinstruction=tsmoveinstruction;
} 
 
/**
* ���� tsjobinstruction��Getter����.��������TsJob Instruction
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getTsjobinstruction() {
return this.tsjobinstruction;
} 

/**
* ����tsjobinstruction��Setter����.��������TsJob Instruction
* ��������:2017-10-18
* @param newTsjobinstruction java.lang.String
*/
public void setTsjobinstruction ( String tsjobinstruction) {
this.tsjobinstruction=tsjobinstruction;
} 
 
/**
* ���� tscscomment��Getter����.��������TsCS COMMENT
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getTscscomment() {
return this.tscscomment;
} 

/**
* ����tscscomment��Setter����.��������TsCS COMMENT
* ��������:2017-10-18
* @param newTscscomment java.lang.String
*/
public void setTscscomment ( String tscscomment) {
this.tscscomment=tscscomment;
} 
 
/**
* ���� tsfrom��Getter����.��������TsFrom
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getTsfrom() {
return this.tsfrom;
} 

/**
* ����tsfrom��Setter����.��������TsFrom
* ��������:2017-10-18
* @param newTsfrom java.lang.String
*/
public void setTsfrom ( String tsfrom) {
this.tsfrom=tsfrom;
} 
 
/**
* ���� tsport��Getter����.��������TsPort
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getTsport() {
return this.tsport;
} 

/**
* ����tsport��Setter����.��������TsPort
* ��������:2017-10-18
* @param newTsport java.lang.String
*/
public void setTsport ( String tsport) {
this.tsport=tsport;
} 
 
/**
* ���� tsto��Getter����.��������TsTo
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getTsto() {
return this.tsto;
} 

/**
* ����tsto��Setter����.��������TsTo
* ��������:2017-10-18
* @param newTsto java.lang.String
*/
public void setTsto ( String tsto) {
this.tsto=tsto;
} 
 
/**
* ���� tsfeextransactiondate��Getter����.��������TsfeTransaction Date
*  ��������:2017-10-18
* @return nc.vo.pub.lang.UFDate
*/
public UFDate getTsfeextransactiondate() {
return this.tsfeextransactiondate;
} 

/**
* ����tsfeextransactiondate��Setter����.��������TsfeTransaction Date
* ��������:2017-10-18
* @param newTsfeextransactiondate nc.vo.pub.lang.UFDate
*/
public void setTsfeextransactiondate ( UFDate tsfeextransactiondate) {
this.tsfeextransactiondate=tsfeextransactiondate;
} 
 
/**
* ���� tsfeexjoborderno��Getter����.��������TsfeJob Order #
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getTsfeexjoborderno() {
return this.tsfeexjoborderno;
} 

/**
* ����tsfeexjoborderno��Setter����.��������TsfeJob Order #
* ��������:2017-10-18
* @param newTsfeexjoborderno java.lang.String
*/
public void setTsfeexjoborderno ( String tsfeexjoborderno) {
this.tsfeexjoborderno=tsfeexjoborderno;
} 
 
/**
* ���� tsfeexcustomerpono��Getter����.��������TsfeCustomer PO#
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getTsfeexcustomerpono() {
return this.tsfeexcustomerpono;
} 

/**
* ����tsfeexcustomerpono��Setter����.��������TsfeCustomer PO#
* ��������:2017-10-18
* @param newTsfeexcustomerpono java.lang.String
*/
public void setTsfeexcustomerpono ( String tsfeexcustomerpono) {
this.tsfeexcustomerpono=tsfeexcustomerpono;
} 
 
/**
* ���� tsfeexquotationno��Getter����.��������TsfeQuotation #
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getTsfeexquotationno() {
return this.tsfeexquotationno;
} 

/**
* ����tsfeexquotationno��Setter����.��������TsfeQuotation #
* ��������:2017-10-18
* @param newTsfeexquotationno java.lang.String
*/
public void setTsfeexquotationno ( String tsfeexquotationno) {
this.tsfeexquotationno=tsfeexquotationno;
} 
 
/**
* ���� tsfeexpreferreddatetime��Getter����.��������TsfeCustomer Preferred Date/Time
*  ��������:2017-10-18
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getTsfeexpreferreddatetime() {
return this.tsfeexpreferreddatetime;
} 

/**
* ����tsfeexpreferreddatetime��Setter����.��������TsfeCustomer Preferred Date/Time
* ��������:2017-10-18
* @param newTsfeexpreferreddatetime nc.vo.pub.lang.UFDateTime
*/
public void setTsfeexpreferreddatetime ( UFDateTime tsfeexpreferreddatetime) {
this.tsfeexpreferreddatetime=tsfeexpreferreddatetime;
} 
 
/**
* ���� tsfeexportvessel��Getter����.��������TsfeVessel
*  ��������:2017-10-18
* @return nc.vo.bd.defdoc.DefdocVO
*/
public String getTsfeexportvessel() {
return this.tsfeexportvessel;
} 

/**
* ����tsfeexportvessel��Setter����.��������TsfeVessel
* ��������:2017-10-18
* @param newTsfeexportvessel nc.vo.bd.defdoc.DefdocVO
*/
public void setTsfeexportvessel ( String tsfeexportvessel) {
this.tsfeexportvessel=tsfeexportvessel;
} 
 
/**
* ���� tsfeexportvoyage��Getter����.��������TsfeVoyage
*  ��������:2017-10-18
* @return nc.vo.bd.defdoc.DefdocVO
*/
public String getTsfeexportvoyage() {
return this.tsfeexportvoyage;
} 

/**
* ����tsfeexportvoyage��Setter����.��������TsfeVoyage
* ��������:2017-10-18
* @param newTsfeexportvoyage nc.vo.bd.defdoc.DefdocVO
*/
public void setTsfeexportvoyage ( String tsfeexportvoyage) {
this.tsfeexportvoyage=tsfeexportvoyage;
} 
 
/**
* ���� tsfeexportagent��Getter����.��������TsfeAgent
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getTsfeexportagent() {
return this.tsfeexportagent;
} 

/**
* ����tsfeexportagent��Setter����.��������TsfeAgent
* ��������:2017-10-18
* @param newTsfeexportagent java.lang.String
*/
public void setTsfeexportagent ( String tsfeexportagent) {
this.tsfeexportagent=tsfeexportagent;
} 
 
/**
* ���� tsfeexportpermitno��Getter����.��������TsfePermit #
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getTsfeexportpermitno() {
return this.tsfeexportpermitno;
} 

/**
* ����tsfeexportpermitno��Setter����.��������TsfePermit #
* ��������:2017-10-18
* @param newTsfeexportpermitno java.lang.String
*/
public void setTsfeexportpermitno ( String tsfeexportpermitno) {
this.tsfeexportpermitno=tsfeexportpermitno;
} 
 
/**
* ���� tsfebookingref��Getter����.��������TsfeBooking Ref
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getTsfebookingref() {
return this.tsfebookingref;
} 

/**
* ����tsfebookingref��Setter����.��������TsfeBooking Ref
* ��������:2017-10-18
* @param newTsfebookingref java.lang.String
*/
public void setTsfebookingref ( String tsfebookingref) {
this.tsfebookingref=tsfebookingref;
} 
 
/**
* ���� tsfeportnetref��Getter����.��������TsfePortnet Ref
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getTsfeportnetref() {
return this.tsfeportnetref;
} 

/**
* ����tsfeportnetref��Setter����.��������TsfePortnet Ref
* ��������:2017-10-18
* @param newTsfeportnetref java.lang.String
*/
public void setTsfeportnetref ( String tsfeportnetref) {
this.tsfeportnetref=tsfeportnetref;
} 
 
/**
* ���� tsfepickupref��Getter����.��������TsfePick up Ref
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getTsfepickupref() {
return this.tsfepickupref;
} 

/**
* ����tsfepickupref��Setter����.��������TsfePick up Ref
* ��������:2017-10-18
* @param newTsfepickupref java.lang.String
*/
public void setTsfepickupref ( String tsfepickupref) {
this.tsfepickupref=tsfepickupref;
} 
 
/**
* ���� tsfeportofdischarge��Getter����.��������TsfePort of Discharge
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getTsfeportofdischarge() {
return this.tsfeportofdischarge;
} 

/**
* ����tsfeportofdischarge��Setter����.��������TsfePort of Discharge
* ��������:2017-10-18
* @param newTsfeportofdischarge java.lang.String
*/
public void setTsfeportofdischarge ( String tsfeportofdischarge) {
this.tsfeportofdischarge=tsfeportofdischarge;
} 
 
/**
* ���� tsfeexpoliceescortpermitno��Getter����.��������TsfePolice escort permit # (if applicable)
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getTsfeexpoliceescortpermitno() {
return this.tsfeexpoliceescortpermitno;
} 

/**
* ����tsfeexpoliceescortpermitno��Setter����.��������TsfePolice escort permit # (if applicable)
* ��������:2017-10-18
* @param newTsfeexpoliceescortpermitno java.lang.String
*/
public void setTsfeexpoliceescortpermitno ( String tsfeexpoliceescortpermitno) {
this.tsfeexpoliceescortpermitno=tsfeexpoliceescortpermitno;
} 
 
/**
* ���� tsfeexlinktocontractor��Getter����.��������TsfeLink To Contractor
*  ��������:2017-10-18
* @return nc.vo.bd.supplier.SupplierVO
*/
public String getTsfeexlinktocontractor() {
return this.tsfeexlinktocontractor;
} 

/**
* ����tsfeexlinktocontractor��Setter����.��������TsfeLink To Contractor
* ��������:2017-10-18
* @param newTsfeexlinktocontractor nc.vo.bd.supplier.SupplierVO
*/
public void setTsfeexlinktocontractor ( String tsfeexlinktocontractor) {
this.tsfeexlinktocontractor=tsfeexlinktocontractor;
} 
 
/**
* ���� tsfeemptycntrcollection��Getter����.��������TsfeEmpty Container Collection
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getTsfeemptycntrcollection() {
return this.tsfeemptycntrcollection;
} 

/**
* ����tsfeemptycntrcollection��Setter����.��������TsfeEmpty Container Collection
* ��������:2017-10-18
* @param newTsfeemptycntrcollection java.lang.String
*/
public void setTsfeemptycntrcollection ( String tsfeemptycntrcollection) {
this.tsfeemptycntrcollection=tsfeemptycntrcollection;
} 
 
/**
* ���� tsfeexporteta��Getter����.��������TsfeETA
*  ��������:2017-10-18
* @return nc.vo.pub.lang.UFDate
*/
public UFDate getTsfeexporteta() {
return this.tsfeexporteta;
} 

/**
* ����tsfeexporteta��Setter����.��������TsfeETA
* ��������:2017-10-18
* @param newTsfeexporteta nc.vo.pub.lang.UFDate
*/
public void setTsfeexporteta ( UFDate tsfeexporteta) {
this.tsfeexporteta=tsfeexporteta;
} 
 
/**
* ���� tsfefrom��Getter����.��������TsfeFrom
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getTsfefrom() {
return this.tsfefrom;
} 

/**
* ����tsfefrom��Setter����.��������TsfeFrom
* ��������:2017-10-18
* @param newTsfefrom java.lang.String
*/
public void setTsfefrom ( String tsfefrom) {
this.tsfefrom=tsfefrom;
} 
 
/**
* ���� tsfeto��Getter����.��������TsfeTo
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getTsfeto() {
return this.tsfeto;
} 

/**
* ����tsfeto��Setter����.��������TsfeTo
* ��������:2017-10-18
* @param newTsfeto java.lang.String
*/
public void setTsfeto ( String tsfeto) {
this.tsfeto=tsfeto;
} 
 
/**
* ���� tsfeport��Getter����.��������TsfePort
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getTsfeport() {
return this.tsfeport;
} 

/**
* ����tsfeport��Setter����.��������TsfePort
* ��������:2017-10-18
* @param newTsfeport java.lang.String
*/
public void setTsfeport ( String tsfeport) {
this.tsfeport=tsfeport;
} 
 
/**
* ���� tsfeairride��Getter����.��������TsfeAIR RIDE
*  ��������:2017-10-18
* @return java.lang.String
*/
public UFBoolean getTsfeairride() {
return this.tsfeairride;
} 

/**
* ����tsfeairride��Setter����.��������TsfeAIR RIDE
* ��������:2017-10-18
* @param newTsfeairride java.lang.String
*/
public void setTsfeairride ( UFBoolean tsfeairride) {
this.tsfeairride=tsfeairride;
} 
 
/**
* ���� tsfeunstuffing��Getter����.��������TsfeUNSTUFFING
*  ��������:2017-10-18
* @return nc.vo.pub.lang.UFBoolean
*/
public UFBoolean getTsfeunstuffing() {
return this.tsfeunstuffing;
} 

/**
* ����tsfeunstuffing��Setter����.��������TsfeUNSTUFFING
* ��������:2017-10-18
* @param newTsfeunstuffing nc.vo.pub.lang.UFBoolean
*/
public void setTsfeunstuffing ( UFBoolean tsfeunstuffing) {
this.tsfeunstuffing=tsfeunstuffing;
} 
 
/**
* ���� tsfeloosecargocollect��Getter����.��������TsfeLOOSE CARGO COLLECT
*  ��������:2017-10-18
* @return nc.vo.pub.lang.UFBoolean
*/
public UFBoolean getTsfeloosecargocollect() {
return this.tsfeloosecargocollect;
} 

/**
* ����tsfeloosecargocollect��Setter����.��������TsfeLOOSE CARGO COLLECT
* ��������:2017-10-18
* @param newTsfeloosecargocollect nc.vo.pub.lang.UFBoolean
*/
public void setTsfeloosecargocollect ( UFBoolean tsfeloosecargocollect) {
this.tsfeloosecargocollect=tsfeloosecargocollect;
} 
 
/**
* ���� tsfeloosecargocollects��Getter����.��������TsfeLOOSE CARGO COLLECTs
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getTsfeloosecargocollects() {
return this.tsfeloosecargocollects;
} 

/**
* ����tsfeloosecargocollects��Setter����.��������TsfeLOOSE CARGO COLLECTs
* ��������:2017-10-18
* @param newTsfeloosecargocollects java.lang.String
*/
public void setTsfeloosecargocollects ( String tsfeloosecargocollects) {
this.tsfeloosecargocollects=tsfeloosecargocollects;
} 
 
/**
* ���� tsfetruckinstruction��Getter����.��������TsfeTruck Instruction
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getTsfetruckinstruction() {
return this.tsfetruckinstruction;
} 

/**
* ����tsfetruckinstruction��Setter����.��������TsfeTruck Instruction
* ��������:2017-10-18
* @param newTsfetruckinstruction java.lang.String
*/
public void setTsfetruckinstruction ( String tsfetruckinstruction) {
this.tsfetruckinstruction=tsfetruckinstruction;
} 
 
/**
* ���� tsfemoveinstruction��Getter����.��������TsfeMove Instruction
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getTsfemoveinstruction() {
return this.tsfemoveinstruction;
} 

/**
* ����tsfemoveinstruction��Setter����.��������TsfeMove Instruction
* ��������:2017-10-18
* @param newTsfemoveinstruction java.lang.String
*/
public void setTsfemoveinstruction ( String tsfemoveinstruction) {
this.tsfemoveinstruction=tsfemoveinstruction;
} 
 
/**
* ���� tsfejobinstruction��Getter����.��������TsfeJob Instruction
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getTsfejobinstruction() {
return this.tsfejobinstruction;
} 

/**
* ����tsfejobinstruction��Setter����.��������TsfeJob Instruction
* ��������:2017-10-18
* @param newTsfejobinstruction java.lang.String
*/
public void setTsfejobinstruction ( String tsfejobinstruction) {
this.tsfejobinstruction=tsfejobinstruction;
} 
 
/**
* ���� tsfecscomment��Getter����.��������TsfeCS COMMENT
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getTsfecscomment() {
return this.tsfecscomment;
} 

/**
* ����tsfecscomment��Setter����.��������TsfeCS COMMENT
* ��������:2017-10-18
* @param newTsfecscomment java.lang.String
*/
public void setTsfecscomment ( String tsfecscomment) {
this.tsfecscomment=tsfecscomment;
} 
 
/**
* ���� tajobtype��Getter����.��������Ta Job Type
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getTajobtype() {
return this.tajobtype;
} 

/**
* ����tajobtype��Setter����.��������Ta Job Type
* ��������:2017-10-18
* @param newTajobtype java.lang.String
*/
public void setTajobtype ( String tajobtype) {
this.tajobtype=tajobtype;
} 
 
/**
* ���� tadono��Getter����.��������Ta DO#
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getTadono() {
return this.tadono;
} 

/**
* ����tadono��Setter����.��������Ta DO#
* ��������:2017-10-18
* @param newTadono java.lang.String
*/
public void setTadono ( String tadono) {
this.tadono=tadono;
} 
 
/**
* ���� taapickupdate��Getter����.��������Ta Pickup Date
*  ��������:2017-10-18
* @return nc.vo.pub.lang.UFDate
*/
public UFDate getTaapickupdate() {
return this.taapickupdate;
} 

/**
* ����taapickupdate��Setter����.��������Ta Pickup Date
* ��������:2017-10-18
* @param newTaapickupdate nc.vo.pub.lang.UFDate
*/
public void setTaapickupdate ( UFDate taapickupdate) {
this.taapickupdate=taapickupdate;
} 
 
/**
* ���� taapickuptime��Getter����.��������Ta Pickup Time
*  ��������:2017-10-18
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getTaapickuptime() {
return this.taapickuptime;
} 

/**
* ����taapickuptime��Setter����.��������Ta Pickup Time
* ��������:2017-10-18
* @param newTaapickuptime nc.vo.pub.lang.UFDateTime
*/
public void setTaapickuptime ( UFDateTime taapickuptime) {
this.taapickuptime=taapickuptime;
} 
 
/**
* ���� taaondockdate��Getter����.��������Ta Ondock Date
*  ��������:2017-10-18
* @return nc.vo.pub.lang.UFDate
*/
public UFDate getTaaondockdate() {
return this.taaondockdate;
} 

/**
* ����taaondockdate��Setter����.��������Ta Ondock Date
* ��������:2017-10-18
* @param newTaaondockdate nc.vo.pub.lang.UFDate
*/
public void setTaaondockdate ( UFDate taaondockdate) {
this.taaondockdate=taaondockdate;
} 
 
/**
* ���� taaondocktime��Getter����.��������Ta Ondock Time
*  ��������:2017-10-18
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getTaaondocktime() {
return this.taaondocktime;
} 

/**
* ����taaondocktime��Setter����.��������Ta Ondock Time
* ��������:2017-10-18
* @param newTaaondocktime nc.vo.pub.lang.UFDateTime
*/
public void setTaaondocktime ( UFDateTime taaondocktime) {
this.taaondocktime=taaondocktime;
} 
 
/**
* ���� taapickupfromname��Getter����.��������Ta Pickup From Name
*  ��������:2017-10-18
* @return nc.vo.bd.cust.CustomerVO
*/
public String getTaapickupfromname() {
return this.taapickupfromname;
} 

/**
* ����taapickupfromname��Setter����.��������Ta Pickup From Name
* ��������:2017-10-18
* @param newTaapickupfromname nc.vo.bd.cust.CustomerVO
*/
public void setTaapickupfromname ( String taapickupfromname) {
this.taapickupfromname=taapickupfromname;
} 
 
/**
* ���� taapickupfromaddress��Getter����.��������Ta Pickup From Address
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getTaapickupfromaddress() {
return this.taapickupfromaddress;
} 

/**
* ����taapickupfromaddress��Setter����.��������Ta Pickup From Address
* ��������:2017-10-18
* @param newTaapickupfromaddress java.lang.String
*/
public void setTaapickupfromaddress ( String taapickupfromaddress) {
this.taapickupfromaddress=taapickupfromaddress;
} 
 
/**
* ���� taapickupfromcode��Getter����.��������Ta Pickup From Code
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getTaapickupfromcode() {
return this.taapickupfromcode;
} 

/**
* ����taapickupfromcode��Setter����.��������Ta Pickup From Code
* ��������:2017-10-18
* @param newTaapickupfromcode java.lang.String
*/
public void setTaapickupfromcode ( String taapickupfromcode) {
this.taapickupfromcode=taapickupfromcode;
} 
 
/**
* ���� taacontactperson��Getter����.��������Ta Contact Person
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getTaacontactperson() {
return this.taacontactperson;
} 

/**
* ����taacontactperson��Setter����.��������Ta Contact Person
* ��������:2017-10-18
* @param newTaacontactperson java.lang.String
*/
public void setTaacontactperson ( String taacontactperson) {
this.taacontactperson=taacontactperson;
} 
 
/**
* ���� taacontactpersonphone��Getter����.��������Ta Contact Person Phone
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getTaacontactpersonphone() {
return this.taacontactpersonphone;
} 

/**
* ����taacontactpersonphone��Setter����.��������Ta Contact Person Phone
* ��������:2017-10-18
* @param newTaacontactpersonphone java.lang.String
*/
public void setTaacontactpersonphone ( String taacontactpersonphone) {
this.taacontactpersonphone=taacontactpersonphone;
} 
 
/**
* ���� tabpickupdate��Getter����.��������Ta Bpickup Date
*  ��������:2017-10-18
* @return nc.vo.pub.lang.UFDate
*/
public UFDate getTabpickupdate() {
return this.tabpickupdate;
} 

/**
* ����tabpickupdate��Setter����.��������Ta Bpickup Date
* ��������:2017-10-18
* @param newTabpickupdate nc.vo.pub.lang.UFDate
*/
public void setTabpickupdate ( UFDate tabpickupdate) {
this.tabpickupdate=tabpickupdate;
} 
 
/**
* ���� tabpickuptime��Getter����.��������Ta Bpickup Time
*  ��������:2017-10-18
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getTabpickuptime() {
return this.tabpickuptime;
} 

/**
* ����tabpickuptime��Setter����.��������Ta Bpickup Time
* ��������:2017-10-18
* @param newTabpickuptime nc.vo.pub.lang.UFDateTime
*/
public void setTabpickuptime ( UFDateTime tabpickuptime) {
this.tabpickuptime=tabpickuptime;
} 
 
/**
* ���� tabondockdate��Getter����.��������Ta Bondock Date
*  ��������:2017-10-18
* @return nc.vo.pub.lang.UFDate
*/
public UFDate getTabondockdate() {
return this.tabondockdate;
} 

/**
* ����tabondockdate��Setter����.��������Ta Bondock Date
* ��������:2017-10-18
* @param newTabondockdate nc.vo.pub.lang.UFDate
*/
public void setTabondockdate ( UFDate tabondockdate) {
this.tabondockdate=tabondockdate;
} 
 
/**
* ���� tabondocktime��Getter����.��������Ta Bondock Time
*  ��������:2017-10-18
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getTabondocktime() {
return this.tabondocktime;
} 

/**
* ����tabondocktime��Setter����.��������Ta Bondock Time
* ��������:2017-10-18
* @param newTabondocktime nc.vo.pub.lang.UFDateTime
*/
public void setTabondocktime ( UFDateTime tabondocktime) {
this.tabondocktime=tabondocktime;
} 
 
/**
* ���� tabpreloadtofromname��Getter����.��������Ta Preload To/From Name
*  ��������:2017-10-18
* @return nc.vo.bd.cust.CustomerVO
*/
public String getTabpreloadtofromname() {
return this.tabpreloadtofromname;
} 

/**
* ����tabpreloadtofromname��Setter����.��������Ta Preload To/From Name
* ��������:2017-10-18
* @param newTabpreloadtofromname nc.vo.bd.cust.CustomerVO
*/
public void setTabpreloadtofromname ( String tabpreloadtofromname) {
this.tabpreloadtofromname=tabpreloadtofromname;
} 
 
/**
* ���� tabpreloadtofromaddres��Getter����.��������Ta Preload To/From Address
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getTabpreloadtofromaddres() {
return this.tabpreloadtofromaddres;
} 

/**
* ����tabpreloadtofromaddres��Setter����.��������Ta Preload To/From Address
* ��������:2017-10-18
* @param newTabpreloadtofromaddres java.lang.String
*/
public void setTabpreloadtofromaddres ( String tabpreloadtofromaddres) {
this.tabpreloadtofromaddres=tabpreloadtofromaddres;
} 
 
/**
* ���� tabpreloadtofromcode��Getter����.��������Ta Preload To/From Code
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getTabpreloadtofromcode() {
return this.tabpreloadtofromcode;
} 

/**
* ����tabpreloadtofromcode��Setter����.��������Ta Preload To/From Code
* ��������:2017-10-18
* @param newTabpreloadtofromcode java.lang.String
*/
public void setTabpreloadtofromcode ( String tabpreloadtofromcode) {
this.tabpreloadtofromcode=tabpreloadtofromcode;
} 
 
/**
* ���� tacpickupdate��Getter����.��������Ta Cpickup Date
*  ��������:2017-10-18
* @return nc.vo.pub.lang.UFDate
*/
public UFDate getTacpickupdate() {
return this.tacpickupdate;
} 

/**
* ����tacpickupdate��Setter����.��������Ta Cpickup Date
* ��������:2017-10-18
* @param newTacpickupdate nc.vo.pub.lang.UFDate
*/
public void setTacpickupdate ( UFDate tacpickupdate) {
this.tacpickupdate=tacpickupdate;
} 
 
/**
* ���� tacpickuptime��Getter����.��������Ta Cpickup Time
*  ��������:2017-10-18
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getTacpickuptime() {
return this.tacpickuptime;
} 

/**
* ����tacpickuptime��Setter����.��������Ta Cpickup Time
* ��������:2017-10-18
* @param newTacpickuptime nc.vo.pub.lang.UFDateTime
*/
public void setTacpickuptime ( UFDateTime tacpickuptime) {
this.tacpickuptime=tacpickuptime;
} 
 
/**
* ���� tacondockdate��Getter����.��������Ta Condock Date
*  ��������:2017-10-18
* @return nc.vo.pub.lang.UFDate
*/
public UFDate getTacondockdate() {
return this.tacondockdate;
} 

/**
* ����tacondockdate��Setter����.��������Ta Condock Date
* ��������:2017-10-18
* @param newTacondockdate nc.vo.pub.lang.UFDate
*/
public void setTacondockdate ( UFDate tacondockdate) {
this.tacondockdate=tacondockdate;
} 
 
/**
* ���� tacondocktime��Getter����.��������Ta Condock Time
*  ��������:2017-10-18
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getTacondocktime() {
return this.tacondocktime;
} 

/**
* ����tacondocktime��Setter����.��������Ta Condock Time
* ��������:2017-10-18
* @param newTacondocktime nc.vo.pub.lang.UFDateTime
*/
public void setTacondocktime ( UFDateTime tacondocktime) {
this.tacondocktime=tacondocktime;
} 
 
/**
* ���� tacdelivertoname��Getter����.��������Ta Deliver To Name
*  ��������:2017-10-18
* @return nc.vo.bd.cust.CustomerVO
*/
public String getTacdelivertoname() {
return this.tacdelivertoname;
} 

/**
* ����tacdelivertoname��Setter����.��������Ta Deliver To Name
* ��������:2017-10-18
* @param newTacdelivertoname nc.vo.bd.cust.CustomerVO
*/
public void setTacdelivertoname ( String tacdelivertoname) {
this.tacdelivertoname=tacdelivertoname;
} 
 
/**
* ���� tacdelivertoaddress��Getter����.��������Ta Deliver To Address
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getTacdelivertoaddress() {
return this.tacdelivertoaddress;
} 

/**
* ����tacdelivertoaddress��Setter����.��������Ta Deliver To Address
* ��������:2017-10-18
* @param newTacdelivertoaddress java.lang.String
*/
public void setTacdelivertoaddress ( String tacdelivertoaddress) {
this.tacdelivertoaddress=tacdelivertoaddress;
} 
 
/**
* ���� tacdelivertocode��Getter����.��������Ta Deliver To Code
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getTacdelivertocode() {
return this.tacdelivertocode;
} 

/**
* ����tacdelivertocode��Setter����.��������Ta Deliver To Code
* ��������:2017-10-18
* @param newTacdelivertocode java.lang.String
*/
public void setTacdelivertocode ( String tacdelivertocode) {
this.tacdelivertocode=tacdelivertocode;
} 
 
/**
* ���� taccontactperson��Getter����.��������Ta Ccontact Person
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getTaccontactperson() {
return this.taccontactperson;
} 

/**
* ����taccontactperson��Setter����.��������Ta Ccontact Person
* ��������:2017-10-18
* @param newTaccontactperson java.lang.String
*/
public void setTaccontactperson ( String taccontactperson) {
this.taccontactperson=taccontactperson;
} 
 
/**
* ���� taccontactpersonphone��Getter����.��������Ta Ccontact Person Phone
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getTaccontactpersonphone() {
return this.taccontactpersonphone;
} 

/**
* ����taccontactpersonphone��Setter����.��������Ta Ccontact Person Phone
* ��������:2017-10-18
* @param newTaccontactpersonphone java.lang.String
*/
public void setTaccontactpersonphone ( String taccontactpersonphone) {
this.taccontactpersonphone=taccontactpersonphone;
} 
 
/**
* ���� taawbno��Getter����.��������Ta AWB #
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getTaawbno() {
return this.taawbno;
} 

/**
* ����taawbno��Setter����.��������Ta AWB #
* ��������:2017-10-18
* @param newTaawbno java.lang.String
*/
public void setTaawbno ( String taawbno) {
this.taawbno=taawbno;
} 
 
/**
* ���� tatotalofpkgs��Getter����.��������Ta Total # Of Pkgs
*  ��������:2017-10-18
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getTatotalofpkgs() {
return this.tatotalofpkgs;
} 

/**
* ����tatotalofpkgs��Setter����.��������Ta Total # Of Pkgs
* ��������:2017-10-18
* @param newTatotalofpkgs nc.vo.pub.lang.UFDouble
*/
public void setTatotalofpkgs ( UFDouble tatotalofpkgs) {
this.tatotalofpkgs=tatotalofpkgs;
} 
 
/**
* ���� tachargableweight��Getter����.��������Ta Chargable Weight(KG)
*  ��������:2017-10-18
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getTachargableweight() {
return this.tachargableweight;
} 

/**
* ����tachargableweight��Setter����.��������Ta Chargable Weight(KG)
* ��������:2017-10-18
* @param newTachargableweight nc.vo.pub.lang.UFDouble
*/
public void setTachargableweight ( UFDouble tachargableweight) {
this.tachargableweight=tachargableweight;
} 
 
/**
* ���� tavolume��Getter����.��������Ta Volume(M3)
*  ��������:2017-10-18
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getTavolume() {
return this.tavolume;
} 

/**
* ����tavolume��Setter����.��������Ta Volume(M3)
* ��������:2017-10-18
* @param newTavolume nc.vo.pub.lang.UFDouble
*/
public void setTavolume ( UFDouble tavolume) {
this.tavolume=tavolume;
} 
 
/**
* ���� tagrossweight��Getter����.��������Ta Gross Weight(KG)
*  ��������:2017-10-18
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getTagrossweight() {
return this.tagrossweight;
} 

/**
* ����tagrossweight��Setter����.��������Ta Gross Weight(KG)
* ��������:2017-10-18
* @param newTagrossweight nc.vo.pub.lang.UFDouble
*/
public void setTagrossweight ( UFDouble tagrossweight) {
this.tagrossweight=tagrossweight;
} 
 
/**
* ���� taaream2��Getter����.��������Ta Area (M2)
*  ��������:2017-10-18
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getTaaream2() {
return this.taaream2;
} 

/**
* ����taaream2��Setter����.��������Ta Area (M2)
* ��������:2017-10-18
* @param newTaaream2 nc.vo.pub.lang.UFDouble
*/
public void setTaaream2 ( UFDouble taaream2) {
this.taaream2=taaream2;
} 
 
/**
* ���� talengthcm��Getter����.��������Ta Length(CM)
*  ��������:2017-10-18
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getTalengthcm() {
return this.talengthcm;
} 

/**
* ����talengthcm��Setter����.��������Ta Length(CM)
* ��������:2017-10-18
* @param newTalengthcm nc.vo.pub.lang.UFDouble
*/
public void setTalengthcm ( UFDouble talengthcm) {
this.talengthcm=talengthcm;
} 
 
/**
* ���� talcrateno��Getter����.��������Ta Crate #
*  ��������:2017-10-18
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getTalcrateno() {
return this.talcrateno;
} 

/**
* ����talcrateno��Setter����.��������Ta Crate #
* ��������:2017-10-18
* @param newTalcrateno nc.vo.pub.lang.UFDouble
*/
public void setTalcrateno ( UFDouble talcrateno) {
this.talcrateno=talcrateno;
} 
 
/**
* ���� tawidthcm��Getter����.��������Ta Width(CM)
*  ��������:2017-10-18
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getTawidthcm() {
return this.tawidthcm;
} 

/**
* ����tawidthcm��Setter����.��������Ta Width(CM)
* ��������:2017-10-18
* @param newTawidthcm nc.vo.pub.lang.UFDouble
*/
public void setTawidthcm ( UFDouble tawidthcm) {
this.tawidthcm=tawidthcm;
} 
 
/**
* ���� tawcrateno��Getter����.��������Ta Wcrate #
*  ��������:2017-10-18
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getTawcrateno() {
return this.tawcrateno;
} 

/**
* ����tawcrateno��Setter����.��������Ta Wcrate #
* ��������:2017-10-18
* @param newTawcrateno nc.vo.pub.lang.UFDouble
*/
public void setTawcrateno ( UFDouble tawcrateno) {
this.tawcrateno=tawcrateno;
} 
 
/**
* ���� taheightcm��Getter����.��������Ta Height(CM)
*  ��������:2017-10-18
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getTaheightcm() {
return this.taheightcm;
} 

/**
* ����taheightcm��Setter����.��������Ta Height(CM)
* ��������:2017-10-18
* @param newTaheightcm nc.vo.pub.lang.UFDouble
*/
public void setTaheightcm ( UFDouble taheightcm) {
this.taheightcm=taheightcm;
} 
 
/**
* ���� tahcrateno��Getter����.��������Ta HCrate #
*  ��������:2017-10-18
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getTahcrateno() {
return this.tahcrateno;
} 

/**
* ����tahcrateno��Setter����.��������Ta HCrate #
* ��������:2017-10-18
* @param newTahcrateno nc.vo.pub.lang.UFDouble
*/
public void setTahcrateno ( UFDouble tahcrateno) {
this.tahcrateno=tahcrateno;
} 
 
/**
* ���� taspecialinstruction��Getter����.��������Ta Special Instruction
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getTaspecialinstruction() {
return this.taspecialinstruction;
} 

/**
* ����taspecialinstruction��Setter����.��������Ta Special Instruction
* ��������:2017-10-18
* @param newTaspecialinstruction java.lang.String
*/
public void setTaspecialinstruction ( String taspecialinstruction) {
this.taspecialinstruction=taspecialinstruction;
} 
 
/**
* ���� tacommoninstruction��Getter����.��������Ta Common Instruction
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getTacommoninstruction() {
return this.tacommoninstruction;
} 

/**
* ����tacommoninstruction��Setter����.��������Ta Common Instruction
* ��������:2017-10-18
* @param newTacommoninstruction java.lang.String
*/
public void setTacommoninstruction ( String tacommoninstruction) {
this.tacommoninstruction=tacommoninstruction;
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
    return VOMetaFactory.getInstance().getVOMeta("BHS.SoTruckHVO");
    }
   }
    