package nc.vo.bhs.build;

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
 
public class SoOrderBuildHVO extends SuperVO {
	
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
*Ref
*/
public String ref;
/**
*Job Type
*/
public String jobtype;
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
* ���� projectstartdate��Getter����.��������Project start date
*  ��������:2017-10-18
* @return nc.vo.pub.lang.UFDate
*/
public UFDate getProjectstartdate() {
return this.projectstartdate;
} 

/**
* ����projectstartdate��Setter����.��������Project start date
* ��������:2017-10-18
* @param newProjectstartdate nc.vo.pub.lang.UFDate
*/
public void setProjectstartdate ( UFDate projectstartdate) {
this.projectstartdate=projectstartdate;
} 
 
/**
* ���� moveindate��Getter����.��������Move-in date
*  ��������:2017-10-18
* @return nc.vo.pub.lang.UFDate
*/
public UFDate getMoveindate() {
return this.moveindate;
} 

/**
* ����moveindate��Setter����.��������Move-in date
* ��������:2017-10-18
* @param newMoveindate nc.vo.pub.lang.UFDate
*/
public void setMoveindate ( UFDate moveindate) {
this.moveindate=moveindate;
} 
 
/**
* ���� installstartdate��Getter����.��������Install start date
*  ��������:2017-10-18
* @return nc.vo.pub.lang.UFDate
*/
public UFDate getInstallstartdate() {
return this.installstartdate;
} 

/**
* ����installstartdate��Setter����.��������Install start date
* ��������:2017-10-18
* @param newInstallstartdate nc.vo.pub.lang.UFDate
*/
public void setInstallstartdate ( UFDate installstartdate) {
this.installstartdate=installstartdate;
} 
 
/**
* ���� powerupdate��Getter����.��������Power up date
*  ��������:2017-10-18
* @return nc.vo.pub.lang.UFDate
*/
public UFDate getPowerupdate() {
return this.powerupdate;
} 

/**
* ����powerupdate��Setter����.��������Power up date
* ��������:2017-10-18
* @param newPowerupdate nc.vo.pub.lang.UFDate
*/
public void setPowerupdate ( UFDate powerupdate) {
this.powerupdate=powerupdate;
} 
 
/**
* ���� ctadjustmentdate��Getter����.��������CT adjustment date
*  ��������:2017-10-18
* @return nc.vo.pub.lang.UFDate
*/
public UFDate getCtadjustmentdate() {
return this.ctadjustmentdate;
} 

/**
* ����ctadjustmentdate��Setter����.��������CT adjustment date
* ��������:2017-10-18
* @param newCtadjustmentdate nc.vo.pub.lang.UFDate
*/
public void setCtadjustmentdate ( UFDate ctadjustmentdate) {
this.ctadjustmentdate=ctadjustmentdate;
} 
 
/**
* ���� customerpono��Getter����.��������Client PO
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getCustomerpono() {
return this.customerpono;
} 

/**
* ����customerpono��Setter����.��������Client PO
* ��������:2017-10-18
* @param newCustomerpono java.lang.String
*/
public void setCustomerpono ( String customerpono) {
this.customerpono=customerpono;
} 
 
/**
* ���� bhsdo��Getter����.��������BHS DO
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getBhsdo() {
return this.bhsdo;
} 

/**
* ����bhsdo��Setter����.��������BHS DO
* ��������:2017-10-18
* @param newBhsdo java.lang.String
*/
public void setBhsdo ( String bhsdo) {
this.bhsdo=bhsdo;
} 
 
/**
* ���� scope��Getter����.��������Scope
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getScope() {
return this.scope;
} 

/**
* ����scope��Setter����.��������Scope
* ��������:2017-10-18
* @param newScope java.lang.String
*/
public void setScope ( String scope) {
this.scope=scope;
} 
 
/**
* ���� mechanicalinstall��Getter����.��������Mechanical install
*  ��������:2017-10-18
* @return nc.vo.pub.lang.UFBoolean
*/
public UFBoolean getMechanicalinstall() {
return this.mechanicalinstall;
} 

/**
* ����mechanicalinstall��Setter����.��������Mechanical install
* ��������:2017-10-18
* @param newMechanicalinstall nc.vo.pub.lang.UFBoolean
*/
public void setMechanicalinstall ( UFBoolean mechanicalinstall) {
this.mechanicalinstall=mechanicalinstall;
} 
 
/**
* ���� mechanicaldeinstall��Getter����.��������Mechanical de-install
*  ��������:2017-10-18
* @return nc.vo.pub.lang.UFBoolean
*/
public UFBoolean getMechanicaldeinstall() {
return this.mechanicaldeinstall;
} 

/**
* ����mechanicaldeinstall��Setter����.��������Mechanical de-install
* ��������:2017-10-18
* @param newMechanicaldeinstall nc.vo.pub.lang.UFBoolean
*/
public void setMechanicaldeinstall ( UFBoolean mechanicaldeinstall) {
this.mechanicaldeinstall=mechanicaldeinstall;
} 
 
/**
* ���� preinstall��Getter����.��������Pre-install
*  ��������:2017-10-18
* @return nc.vo.pub.lang.UFBoolean
*/
public UFBoolean getPreinstall() {
return this.preinstall;
} 

/**
* ����preinstall��Setter����.��������Pre-install
* ��������:2017-10-18
* @param newPreinstall nc.vo.pub.lang.UFBoolean
*/
public void setPreinstall ( UFBoolean preinstall) {
this.preinstall=preinstall;
} 
 
/**
* ���� jobinstruction��Getter����.��������Job descriptions
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getJobinstruction() {
return this.jobinstruction;
} 

/**
* ����jobinstruction��Setter����.��������Job descriptions
* ��������:2017-10-18
* @param newJobinstruction java.lang.String
*/
public void setJobinstruction ( String jobinstruction) {
this.jobinstruction=jobinstruction;
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
    return VOMetaFactory.getInstance().getVOMeta("bhs.SoOrderBuildHVO");
    }
   }
    