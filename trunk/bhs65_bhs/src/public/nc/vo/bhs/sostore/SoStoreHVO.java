package nc.vo.bhs.sostore;

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
 * @author 
 * @version NCPrj ??
 */
 
public class SoStoreHVO extends SuperVO {
	
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
*��������
*/
public String csaleorderid;
/**
*������
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
*ʱ���
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
* ���� pono��Getter����.��������PO #
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getPono() {
return this.pono;
} 

/**
* ����pono��Setter����.��������PO #
* ��������:2017-10-18
* @param newPono java.lang.String
*/
public void setPono ( String pono) {
this.pono=pono;
} 
 
/**
* ���� purpose��Getter����.��������Purpose
*  ��������:2017-10-18
* @return nc.vo.bhs.sostore.EnumPurpose
*/
public String getPurpose() {
return this.purpose;
} 

/**
* ����purpose��Setter����.��������Purpose
* ��������:2017-10-18
* @param newPurpose nc.vo.bhs.sostore.EnumPurpose
*/
public void setPurpose ( String purpose) {
this.purpose=purpose;
} 
 
/**
* ���� durationuom��Getter����.��������Duration UOM
*  ��������:2017-10-18
* @return nc.vo.bd.material.measdoc.MeasdocVO
*/
public String getDurationuom() {
return this.durationuom;
} 

/**
* ����durationuom��Setter����.��������Duration UOM
* ��������:2017-10-18
* @param newDurationuom nc.vo.bd.material.measdoc.MeasdocVO
*/
public void setDurationuom ( String durationuom) {
this.durationuom=durationuom;
} 
 
/**
* ���� ratetype��Getter����.��������Rate Type
*  ��������:2017-10-18
* @return nc.vo.bhs.sostore.Enumerate
*/
public String getRatetype() {
return this.ratetype;
} 

/**
* ����ratetype��Setter����.��������Rate Type
* ��������:2017-10-18
* @param newRatetype nc.vo.bhs.sostore.Enumerate
*/
public void setRatetype ( String ratetype) {
this.ratetype=ratetype;
} 
 
/**
* ���� measurementuom��Getter����.��������Measurement UOM
*  ��������:2017-10-18
* @return nc.vo.bd.material.measdoc.MeasdocVO
*/
public String getMeasurementuom() {
return this.measurementuom;
} 

/**
* ����measurementuom��Setter����.��������Measurement UOM
* ��������:2017-10-18
* @param newMeasurementuom nc.vo.bd.material.measdoc.MeasdocVO
*/
public void setMeasurementuom ( String measurementuom) {
this.measurementuom=measurementuom;
} 
 
/**
* ���� rateamt��Getter����.��������Rate Amt
*  ��������:2017-10-18
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getRateamt() {
return this.rateamt;
} 

/**
* ����rateamt��Setter����.��������Rate Amt
* ��������:2017-10-18
* @param newRateamt nc.vo.pub.lang.UFDouble
*/
public void setRateamt ( UFDouble rateamt) {
this.rateamt=rateamt;
} 
 
/**
* ���� quantityuom��Getter����.��������Quantity UOM
*  ��������:2017-10-18
* @return nc.vo.bd.material.measdoc.MeasdocVO
*/
public String getQuantityuom() {
return this.quantityuom;
} 

/**
* ����quantityuom��Setter����.��������Quantity UOM
* ��������:2017-10-18
* @param newQuantityuom nc.vo.bd.material.measdoc.MeasdocVO
*/
public void setQuantityuom ( String quantityuom) {
this.quantityuom=quantityuom;
} 
 
/**
* ���� datein��Getter����.��������Date In
*  ��������:2017-10-18
* @return nc.vo.pub.lang.UFDate
*/
public UFDate getDatein() {
return this.datein;
} 

/**
* ����datein��Setter����.��������Date In
* ��������:2017-10-18
* @param newDatein nc.vo.pub.lang.UFDate
*/
public void setDatein ( UFDate datein) {
this.datein=datein;
} 
 
/**
* ���� storagetype��Getter����.��������Storage Type
*  ��������:2017-10-18
* @return nc.vo.bhs.sostore.EnumStoragetype
*/
public String getStoragetype() {
return this.storagetype;
} 

/**
* ����storagetype��Setter����.��������Storage Type
* ��������:2017-10-18
* @param newStoragetype nc.vo.bhs.sostore.EnumStoragetype
*/
public void setStoragetype ( String storagetype) {
this.storagetype=storagetype;
} 
 
/**
* ���� warehousezone��Getter����.��������Warehouse Zone
*  ��������:2017-10-18
* @return nc.vo.bd.stordoc.StordocVO
*/
public String getWarehousezone() {
return this.warehousezone;
} 

/**
* ����warehousezone��Setter����.��������Warehouse Zone
* ��������:2017-10-18
* @param newWarehousezone nc.vo.bd.stordoc.StordocVO
*/
public void setWarehousezone ( String warehousezone) {
this.warehousezone=warehousezone;
} 
 
/**
* ���� storedatlocation��Getter����.��������Stored At/Location
*  ��������:2017-10-18
* @return nc.vo.bd.rack.RackVO
*/
public String getStoredatlocation() {
return this.storedatlocation;
} 

/**
* ����storedatlocation��Setter����.��������Stored At/Location
* ��������:2017-10-18
* @param newStoredatlocation nc.vo.bd.rack.RackVO
*/
public void setStoredatlocation ( String storedatlocation) {
this.storedatlocation=storedatlocation;
} 
 
/**
* ���� csincharge��Getter����.��������CS In-Charge
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getCsincharge() {
return this.csincharge;
} 

/**
* ����csincharge��Setter����.��������CS In-Charge
* ��������:2017-10-18
* @param newCsincharge java.lang.String
*/
public void setCsincharge ( String csincharge) {
this.csincharge=csincharge;
} 
 
/**
* ���� orderremarks��Getter����.��������Order Remarks
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getOrderremarks() {
return this.orderremarks;
} 

/**
* ����orderremarks��Setter����.��������Order Remarks
* ��������:2017-10-18
* @param newOrderremarks java.lang.String
*/
public void setOrderremarks ( String orderremarks) {
this.orderremarks=orderremarks;
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
* ���� contactno��Getter����.��������Contact No
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getContactno() {
return this.contactno;
} 

/**
* ����contactno��Setter����.��������Contact No
* ��������:2017-10-18
* @param newContactno java.lang.String
*/
public void setContactno ( String contactno) {
this.contactno=contactno;
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
    