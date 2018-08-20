package nc.vo.bhs.pack;

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
 
public class SoOrderPackVO extends SuperVO {
	
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
*Crate Sheet No
*/
public String cratesheetno;
/**
*Cost Sheet No
*/
public String costsheetno;
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
*Previous Survey #
*/
public String previoussurvey;
/**
*Crate delivery date
*/
public UFDate cratedeliverydate;
/**
*Pack date
*/
public UFDate packdate;
/**
*Client PO
*/
public String customerpono;
/**
*BHS PO #
*/
public String bhspo;
/**
*Scope
*/
public String scope;
/**
*Summary
*/
public String summary;
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
*Job Type
*/
public String jobtype;
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
* @return java.lang.String
*/
public String getPk_busitype() {
return this.pk_busitype;
} 

/**
* ����pk_busitype��Setter����.��������ҵ������
* ��������:2017-10-18
* @param newPk_busitype java.lang.String
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
* ���� cratesheetno��Getter����.��������Crate Sheet No
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getCratesheetno() {
return this.cratesheetno;
} 

/**
* ����cratesheetno��Setter����.��������Crate Sheet No
* ��������:2017-10-18
* @param newCratesheetno java.lang.String
*/
public void setCratesheetno ( String cratesheetno) {
this.cratesheetno=cratesheetno;
} 
 
/**
* ���� costsheetno��Getter����.��������Cost Sheet No
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getCostsheetno() {
return this.costsheetno;
} 

/**
* ����costsheetno��Setter����.��������Cost Sheet No
* ��������:2017-10-18
* @param newCostsheetno java.lang.String
*/
public void setCostsheetno ( String costsheetno) {
this.costsheetno=costsheetno;
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
* ���� previoussurvey��Getter����.��������Previous Survey #
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getPrevioussurvey() {
return this.previoussurvey;
} 

/**
* ����previoussurvey��Setter����.��������Previous Survey #
* ��������:2017-10-18
* @param newPrevioussurvey java.lang.String
*/
public void setPrevioussurvey ( String previoussurvey) {
this.previoussurvey=previoussurvey;
} 
 
/**
* ���� cratedeliverydate��Getter����.��������Crate delivery date
*  ��������:2017-10-18
* @return nc.vo.pub.lang.UFDate
*/
public UFDate getCratedeliverydate() {
return this.cratedeliverydate;
} 

/**
* ����cratedeliverydate��Setter����.��������Crate delivery date
* ��������:2017-10-18
* @param newCratedeliverydate nc.vo.pub.lang.UFDate
*/
public void setCratedeliverydate ( UFDate cratedeliverydate) {
this.cratedeliverydate=cratedeliverydate;
} 
 
/**
* ���� packdate��Getter����.��������Pack date
*  ��������:2017-10-18
* @return nc.vo.pub.lang.UFDate
*/
public UFDate getPackdate() {
return this.packdate;
} 

/**
* ����packdate��Setter����.��������Pack date
* ��������:2017-10-18
* @param newPackdate nc.vo.pub.lang.UFDate
*/
public void setPackdate ( UFDate packdate) {
this.packdate=packdate;
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
* ���� bhspo��Getter����.��������BHS PO #
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getBhspo() {
return this.bhspo;
} 

/**
* ����bhspo��Setter����.��������BHS PO #
* ��������:2017-10-18
* @param newBhspo java.lang.String
*/
public void setBhspo ( String bhspo) {
this.bhspo=bhspo;
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
* ���� summary��Getter����.��������Summary
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getSummary() {
return this.summary;
} 

/**
* ����summary��Setter����.��������Summary
* ��������:2017-10-18
* @param newSummary java.lang.String
*/
public void setSummary ( String summary) {
this.summary=summary;
} 
 
/**
* ���� summarym2��Getter����.��������M2
*  ��������:2017-10-18
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getSummarym2() {
return this.summarym2;
} 

/**
* ����summarym2��Setter����.��������M2
* ��������:2017-10-18
* @param newSummarym2 nc.vo.pub.lang.UFDouble
*/
public void setSummarym2 ( UFDouble summarym2) {
this.summarym2=summarym2;
} 
 
/**
* ���� summarym3��Getter����.��������M3
*  ��������:2017-10-18
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getSummarym3() {
return this.summarym3;
} 

/**
* ����summarym3��Setter����.��������M3
* ��������:2017-10-18
* @param newSummarym3 nc.vo.pub.lang.UFDouble
*/
public void setSummarym3 ( UFDouble summarym3) {
this.summarym3=summarym3;
} 
 
/**
* ���� summarykg��Getter����.��������kg
*  ��������:2017-10-18
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getSummarykg() {
return this.summarykg;
} 

/**
* ����summarykg��Setter����.��������kg
* ��������:2017-10-18
* @param newSummarykg nc.vo.pub.lang.UFDouble
*/
public void setSummarykg ( UFDouble summarykg) {
this.summarykg=summarykg;
} 
 
/**
* ���� summarypkgs��Getter����.��������pkgs
*  ��������:2017-10-18
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getSummarypkgs() {
return this.summarypkgs;
} 

/**
* ����summarypkgs��Setter����.��������pkgs
* ��������:2017-10-18
* @param newSummarypkgs nc.vo.pub.lang.UFDouble
*/
public void setSummarypkgs ( UFDouble summarypkgs) {
this.summarypkgs=summarypkgs;
} 
 
/**
* ���� noofshockwatches��Getter����.��������no of shock watches
*  ��������:2017-10-18
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getNoofshockwatches() {
return this.noofshockwatches;
} 

/**
* ����noofshockwatches��Setter����.��������no of shock watches
* ��������:2017-10-18
* @param newNoofshockwatches nc.vo.pub.lang.UFDouble
*/
public void setNoofshockwatches ( UFDouble noofshockwatches) {
this.noofshockwatches=noofshockwatches;
} 
 
/**
* ���� nooftiltwatches��Getter����.��������no of tilt watches
*  ��������:2017-10-18
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getNooftiltwatches() {
return this.nooftiltwatches;
} 

/**
* ����nooftiltwatches��Setter����.��������no of tilt watches
* ��������:2017-10-18
* @param newNooftiltwatches nc.vo.pub.lang.UFDouble
*/
public void setNooftiltwatches ( UFDouble nooftiltwatches) {
this.nooftiltwatches=nooftiltwatches;
} 
 
/**
* ���� largestlength��Getter����.��������Largest length
*  ��������:2017-10-18
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getLargestlength() {
return this.largestlength;
} 

/**
* ����largestlength��Setter����.��������Largest length
* ��������:2017-10-18
* @param newLargestlength nc.vo.pub.lang.UFDouble
*/
public void setLargestlength ( UFDouble largestlength) {
this.largestlength=largestlength;
} 
 
/**
* ���� largestwidth��Getter����.��������Largest width
*  ��������:2017-10-18
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getLargestwidth() {
return this.largestwidth;
} 

/**
* ����largestwidth��Setter����.��������Largest width
* ��������:2017-10-18
* @param newLargestwidth nc.vo.pub.lang.UFDouble
*/
public void setLargestwidth ( UFDouble largestwidth) {
this.largestwidth=largestwidth;
} 
 
/**
* ���� largestheight��Getter����.��������Largest height
*  ��������:2017-10-18
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getLargestheight() {
return this.largestheight;
} 

/**
* ����largestheight��Setter����.��������Largest height
* ��������:2017-10-18
* @param newLargestheight nc.vo.pub.lang.UFDouble
*/
public void setLargestheight ( UFDouble largestheight) {
this.largestheight=largestheight;
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
* ���� micapno��Getter����.��������Micap No
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getMicapno() {
return this.micapno;
} 

/**
* ����micapno��Setter����.��������Micap No
* ��������:2017-10-18
* @param newMicapno java.lang.String
*/
public void setMicapno ( String micapno) {
this.micapno=micapno;
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
* ���� toolidlid��Getter����.��������Tool ID/LID
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getToolidlid() {
return this.toolidlid;
} 

/**
* ����toolidlid��Setter����.��������Tool ID/LID
* ��������:2017-10-18
* @param newToolidlid java.lang.String
*/
public void setToolidlid ( String toolidlid) {
this.toolidlid=toolidlid;
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
* ���� suppliername��Getter����.��������Supplier Name
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getSuppliername() {
return this.suppliername;
} 

/**
* ����suppliername��Setter����.��������Supplier Name
* ��������:2017-10-18
* @param newSuppliername java.lang.String
*/
public void setSuppliername ( String suppliername) {
this.suppliername=suppliername;
} 
 
/**
* ���� lcrate��Getter����.��������Lcrate#
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getLcrate() {
return this.lcrate;
} 

/**
* ����lcrate��Setter����.��������Lcrate#
* ��������:2017-10-18
* @param newLcrate java.lang.String
*/
public void setLcrate ( String lcrate) {
this.lcrate=lcrate;
} 
 
/**
* ���� wcrate��Getter����.��������Wcrate#
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getWcrate() {
return this.wcrate;
} 

/**
* ����wcrate��Setter����.��������Wcrate#
* ��������:2017-10-18
* @param newWcrate java.lang.String
*/
public void setWcrate ( String wcrate) {
this.wcrate=wcrate;
} 
 
/**
* ���� hcrate��Getter����.��������Hcrate#
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getHcrate() {
return this.hcrate;
} 

/**
* ����hcrate��Setter����.��������Hcrate#
* ��������:2017-10-18
* @param newHcrate java.lang.String
*/
public void setHcrate ( String hcrate) {
this.hcrate=hcrate;
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
    return VOMetaFactory.getInstance().getVOMeta("bhs.SoOrderPackVO");
    }
   }
    