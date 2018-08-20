package nc.vo.bhs.bhstools;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class BhsToolsHeadVO extends SuperVO {
/**
*����ʱ��
*/
public UFDateTime approvedate;
/**
*��������
*/
public String approvenote;
/**
*������
*/
public String approver;
/**
*��������
*/
public String billid;
/**
*�Ƶ�ʱ��
*/
public UFDateTime creationtime;
/**
*�Ƶ���
*/
public String creator;
/**
*��������
*/
public UFDate dbilldate;
/**
*�Զ�����1
*/
public String def1;
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
*�Զ�����2
*/
public String def2;
/**
*�Զ�����20
*/
public String def20;
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
*����޸�ʱ��
*/
public UFDateTime modifiedtime;
/**
*����޸���
*/
public String modifier;
/**
*��������
*/
public String pk_billtype;
/**
*ҵ������
*/
public String pk_busitype;
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
*pk_saleorder
*/
public String pk_saleorder;
/**
*��������pk
*/
public String pk_transtype;
/**
*saleorderbillno
*/
public String saleorderbillno;
/**
*SO Doc Date
*/
public UFDate toolssodocdate;
/**
*SO No.
*/
public String toolssono;
/**
*�������ͱ���
*/
public String transtypecode;
/**
*ʱ���
*/
public UFDateTime ts;
/**
*���ݺ�
*/
public String vbillno;
/**
*����״̬
*/
public Integer vbillstatus;
/** 
* ��ȡ����ʱ��
*
* @return ����ʱ��
*/
public UFDateTime getApprovedate () {
return this.approvedate;
 } 

/** 
* ��������ʱ��
*
* @param approvedate ����ʱ��
*/
public void setApprovedate ( UFDateTime approvedate) {
this.approvedate=approvedate;
 } 

/** 
* ��ȡ��������
*
* @return ��������
*/
public String getApprovenote () {
return this.approvenote;
 } 

/** 
* ������������
*
* @param approvenote ��������
*/
public void setApprovenote ( String approvenote) {
this.approvenote=approvenote;
 } 

/** 
* ��ȡ������
*
* @return ������
*/
public String getApprover () {
return this.approver;
 } 

/** 
* ����������
*
* @param approver ������
*/
public void setApprover ( String approver) {
this.approver=approver;
 } 

/** 
* ��ȡ��������
*
* @return ��������
*/
public String getBillid () {
return this.billid;
 } 

/** 
* ������������
*
* @param billid ��������
*/
public void setBillid ( String billid) {
this.billid=billid;
 } 

/** 
* ��ȡ�Ƶ�ʱ��
*
* @return �Ƶ�ʱ��
*/
public UFDateTime getCreationtime () {
return this.creationtime;
 } 

/** 
* �����Ƶ�ʱ��
*
* @param creationtime �Ƶ�ʱ��
*/
public void setCreationtime ( UFDateTime creationtime) {
this.creationtime=creationtime;
 } 

/** 
* ��ȡ�Ƶ���
*
* @return �Ƶ���
*/
public String getCreator () {
return this.creator;
 } 

/** 
* �����Ƶ���
*
* @param creator �Ƶ���
*/
public void setCreator ( String creator) {
this.creator=creator;
 } 

/** 
* ��ȡ��������
*
* @return ��������
*/
public UFDate getDbilldate () {
return this.dbilldate;
 } 

/** 
* ���õ�������
*
* @param dbilldate ��������
*/
public void setDbilldate ( UFDate dbilldate) {
this.dbilldate=dbilldate;
 } 

/** 
* ��ȡ�Զ�����1
*
* @return �Զ�����1
*/
public String getDef1 () {
return this.def1;
 } 

/** 
* �����Զ�����1
*
* @param def1 �Զ�����1
*/
public void setDef1 ( String def1) {
this.def1=def1;
 } 

/** 
* ��ȡ�Զ�����10
*
* @return �Զ�����10
*/
public String getDef10 () {
return this.def10;
 } 

/** 
* �����Զ�����10
*
* @param def10 �Զ�����10
*/
public void setDef10 ( String def10) {
this.def10=def10;
 } 

/** 
* ��ȡ�Զ�����11
*
* @return �Զ�����11
*/
public String getDef11 () {
return this.def11;
 } 

/** 
* �����Զ�����11
*
* @param def11 �Զ�����11
*/
public void setDef11 ( String def11) {
this.def11=def11;
 } 

/** 
* ��ȡ�Զ�����12
*
* @return �Զ�����12
*/
public String getDef12 () {
return this.def12;
 } 

/** 
* �����Զ�����12
*
* @param def12 �Զ�����12
*/
public void setDef12 ( String def12) {
this.def12=def12;
 } 

/** 
* ��ȡ�Զ�����13
*
* @return �Զ�����13
*/
public String getDef13 () {
return this.def13;
 } 

/** 
* �����Զ�����13
*
* @param def13 �Զ�����13
*/
public void setDef13 ( String def13) {
this.def13=def13;
 } 

/** 
* ��ȡ�Զ�����14
*
* @return �Զ�����14
*/
public String getDef14 () {
return this.def14;
 } 

/** 
* �����Զ�����14
*
* @param def14 �Զ�����14
*/
public void setDef14 ( String def14) {
this.def14=def14;
 } 

/** 
* ��ȡ�Զ�����15
*
* @return �Զ�����15
*/
public String getDef15 () {
return this.def15;
 } 

/** 
* �����Զ�����15
*
* @param def15 �Զ�����15
*/
public void setDef15 ( String def15) {
this.def15=def15;
 } 

/** 
* ��ȡ�Զ�����16
*
* @return �Զ�����16
*/
public String getDef16 () {
return this.def16;
 } 

/** 
* �����Զ�����16
*
* @param def16 �Զ�����16
*/
public void setDef16 ( String def16) {
this.def16=def16;
 } 

/** 
* ��ȡ�Զ�����17
*
* @return �Զ�����17
*/
public String getDef17 () {
return this.def17;
 } 

/** 
* �����Զ�����17
*
* @param def17 �Զ�����17
*/
public void setDef17 ( String def17) {
this.def17=def17;
 } 

/** 
* ��ȡ�Զ�����18
*
* @return �Զ�����18
*/
public String getDef18 () {
return this.def18;
 } 

/** 
* �����Զ�����18
*
* @param def18 �Զ�����18
*/
public void setDef18 ( String def18) {
this.def18=def18;
 } 

/** 
* ��ȡ�Զ�����19
*
* @return �Զ�����19
*/
public String getDef19 () {
return this.def19;
 } 

/** 
* �����Զ�����19
*
* @param def19 �Զ�����19
*/
public void setDef19 ( String def19) {
this.def19=def19;
 } 

/** 
* ��ȡ�Զ�����2
*
* @return �Զ�����2
*/
public String getDef2 () {
return this.def2;
 } 

/** 
* �����Զ�����2
*
* @param def2 �Զ�����2
*/
public void setDef2 ( String def2) {
this.def2=def2;
 } 

/** 
* ��ȡ�Զ�����20
*
* @return �Զ�����20
*/
public String getDef20 () {
return this.def20;
 } 

/** 
* �����Զ�����20
*
* @param def20 �Զ�����20
*/
public void setDef20 ( String def20) {
this.def20=def20;
 } 

/** 
* ��ȡ�Զ�����3
*
* @return �Զ�����3
*/
public String getDef3 () {
return this.def3;
 } 

/** 
* �����Զ�����3
*
* @param def3 �Զ�����3
*/
public void setDef3 ( String def3) {
this.def3=def3;
 } 

/** 
* ��ȡ�Զ�����4
*
* @return �Զ�����4
*/
public String getDef4 () {
return this.def4;
 } 

/** 
* �����Զ�����4
*
* @param def4 �Զ�����4
*/
public void setDef4 ( String def4) {
this.def4=def4;
 } 

/** 
* ��ȡ�Զ�����5
*
* @return �Զ�����5
*/
public String getDef5 () {
return this.def5;
 } 

/** 
* �����Զ�����5
*
* @param def5 �Զ�����5
*/
public void setDef5 ( String def5) {
this.def5=def5;
 } 

/** 
* ��ȡ�Զ�����6
*
* @return �Զ�����6
*/
public String getDef6 () {
return this.def6;
 } 

/** 
* �����Զ�����6
*
* @param def6 �Զ�����6
*/
public void setDef6 ( String def6) {
this.def6=def6;
 } 

/** 
* ��ȡ�Զ�����7
*
* @return �Զ�����7
*/
public String getDef7 () {
return this.def7;
 } 

/** 
* �����Զ�����7
*
* @param def7 �Զ�����7
*/
public void setDef7 ( String def7) {
this.def7=def7;
 } 

/** 
* ��ȡ�Զ�����8
*
* @return �Զ�����8
*/
public String getDef8 () {
return this.def8;
 } 

/** 
* �����Զ�����8
*
* @param def8 �Զ�����8
*/
public void setDef8 ( String def8) {
this.def8=def8;
 } 

/** 
* ��ȡ�Զ�����9
*
* @return �Զ�����9
*/
public String getDef9 () {
return this.def9;
 } 

/** 
* �����Զ�����9
*
* @param def9 �Զ�����9
*/
public void setDef9 ( String def9) {
this.def9=def9;
 } 

/** 
* ��ȡ����޸�ʱ��
*
* @return ����޸�ʱ��
*/
public UFDateTime getModifiedtime () {
return this.modifiedtime;
 } 

/** 
* ��������޸�ʱ��
*
* @param modifiedtime ����޸�ʱ��
*/
public void setModifiedtime ( UFDateTime modifiedtime) {
this.modifiedtime=modifiedtime;
 } 

/** 
* ��ȡ����޸���
*
* @return ����޸���
*/
public String getModifier () {
return this.modifier;
 } 

/** 
* ��������޸���
*
* @param modifier ����޸���
*/
public void setModifier ( String modifier) {
this.modifier=modifier;
 } 

/** 
* ��ȡ��������
*
* @return ��������
*/
public String getPk_billtype () {
return this.pk_billtype;
 } 

/** 
* ���õ�������
*
* @param pk_billtype ��������
*/
public void setPk_billtype ( String pk_billtype) {
this.pk_billtype=pk_billtype;
 } 

/** 
* ��ȡҵ������
*
* @return ҵ������
*/
public String getPk_busitype () {
return this.pk_busitype;
 } 

/** 
* ����ҵ������
*
* @param pk_busitype ҵ������
*/
public void setPk_busitype ( String pk_busitype) {
this.pk_busitype=pk_busitype;
 } 

/** 
* ��ȡ����
*
* @return ����
*/
public String getPk_group () {
return this.pk_group;
 } 

/** 
* ���ü���
*
* @param pk_group ����
*/
public void setPk_group ( String pk_group) {
this.pk_group=pk_group;
 } 

/** 
* ��ȡ��֯
*
* @return ��֯
*/
public String getPk_org () {
return this.pk_org;
 } 

/** 
* ������֯
*
* @param pk_org ��֯
*/
public void setPk_org ( String pk_org) {
this.pk_org=pk_org;
 } 

/** 
* ��ȡ��֯�汾
*
* @return ��֯�汾
*/
public String getPk_org_v () {
return this.pk_org_v;
 } 

/** 
* ������֯�汾
*
* @param pk_org_v ��֯�汾
*/
public void setPk_org_v ( String pk_org_v) {
this.pk_org_v=pk_org_v;
 } 

/** 
* ��ȡpk_saleorder
*
* @return pk_saleorder
*/
public String getPk_saleorder () {
return this.pk_saleorder;
 } 

/** 
* ����pk_saleorder
*
* @param pk_saleorder pk_saleorder
*/
public void setPk_saleorder ( String pk_saleorder) {
this.pk_saleorder=pk_saleorder;
 } 

/** 
* ��ȡ��������pk
*
* @return ��������pk
*/
public String getPk_transtype () {
return this.pk_transtype;
 } 

/** 
* ���ý�������pk
*
* @param pk_transtype ��������pk
*/
public void setPk_transtype ( String pk_transtype) {
this.pk_transtype=pk_transtype;
 } 

/** 
* ��ȡsaleorderbillno
*
* @return saleorderbillno
*/
public String getSaleorderbillno () {
return this.saleorderbillno;
 } 

/** 
* ����saleorderbillno
*
* @param saleorderbillno saleorderbillno
*/
public void setSaleorderbillno ( String saleorderbillno) {
this.saleorderbillno=saleorderbillno;
 } 

/** 
* ��ȡSO Doc Date
*
* @return SO Doc Date
*/
public UFDate getToolssodocdate () {
return this.toolssodocdate;
 } 

/** 
* ����SO Doc Date
*
* @param toolssodocdate SO Doc Date
*/
public void setToolssodocdate ( UFDate toolssodocdate) {
this.toolssodocdate=toolssodocdate;
 } 

/** 
* ��ȡSO No.
*
* @return SO No.
*/
public String getToolssono () {
return this.toolssono;
 } 

/** 
* ����SO No.
*
* @param toolssono SO No.
*/
public void setToolssono ( String toolssono) {
this.toolssono=toolssono;
 } 

/** 
* ��ȡ�������ͱ���
*
* @return �������ͱ���
*/
public String getTranstypecode () {
return this.transtypecode;
 } 

/** 
* ���ý������ͱ���
*
* @param transtypecode �������ͱ���
*/
public void setTranstypecode ( String transtypecode) {
this.transtypecode=transtypecode;
 } 

/** 
* ��ȡʱ���
*
* @return ʱ���
*/
public UFDateTime getTs () {
return this.ts;
 } 

/** 
* ����ʱ���
*
* @param ts ʱ���
*/
public void setTs ( UFDateTime ts) {
this.ts=ts;
 } 

/** 
* ��ȡ���ݺ�
*
* @return ���ݺ�
*/
public String getVbillno () {
return this.vbillno;
 } 

/** 
* ���õ��ݺ�
*
* @param vbillno ���ݺ�
*/
public void setVbillno ( String vbillno) {
this.vbillno=vbillno;
 } 

/** 
* ��ȡ����״̬
*
* @return ����״̬
* @see String
*/
public Integer getVbillstatus () {
return this.vbillstatus;
 } 

/** 
* ���õ���״̬
*
* @param vbillstatus ����״̬
* @see String
*/
public void setVbillstatus ( Integer vbillstatus) {
this.vbillstatus=vbillstatus;
 } 


  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("bhs.BhsToolsHeadVO");
  }
}