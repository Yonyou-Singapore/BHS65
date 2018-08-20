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
 
public class SoOrderPackBVO extends SuperVO {
	
/**
*�ӱ�����
*/
public String billid_b;
/**
*�к�
*/
public String rowno;
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
*S/N
*/
public String snno;
/**
*Description
*/
public String packdescription;
/**
*Qty
*/
public UFDouble packqty;
/**
*cargodimensionl
*/
public UFDouble cargodimensionl;
/**
*cargodimensionw
*/
public UFDouble cargodimensionw;
/**
*cargodimensionh
*/
public UFDouble cargodimensionh;
/**
*cargodimensionweight
*/
public UFDouble cargodimensionweight;
/**
*internaldimensionl
*/
public UFDouble internaldimensionl;
/**
*internaldimensionw
*/
public UFDouble internaldimensionw;
/**
*internaldimensionh
*/
public UFDouble internaldimensionh;
/**
*Internal M3
*/
public UFDouble internalm3;
/**
*cratedimensionl
*/
public UFDouble cratedimensionl;
/**
*cratedimensionw
*/
public UFDouble cratedimensionw;
/**
*cratedimensionh
*/
public UFDouble cratedimensionh;
/**
*Weight
*/
public UFDouble cratedimensionweight;
/**
*Remark
*/
public String packremark;
/**
*Unit px per crate
*/
public UFDouble uppc;
/**
*External M3
*/
public UFDouble externalm3;
/**
*Machine Description
*/
public String machinedescription;
/**
*Machine Length
*/
public UFDouble machinelength;
/**
*Machine Width
*/
public UFDouble machinewidth;
/**
*Machine Height
*/
public UFDouble machineheight;
/**
*Full Box/Skid
*/
public String fullboxskid;
/**
*If Full Box
*/
public String iffullbox;
/**
*2 way/4 way
*/
public String way4way;
/**
*Vacum/Normal
*/
public String vacumnormal;
/**
*Cleat
*/
public String cleat;
/**
*External Securing
*/
public String externalsecuring;
/**
*Additional External
*/
public String additionalexternal;
/**
*Skid Type
*/
public String skidtype;
/**
*Floating/Non Floating
*/
public String floating;
/**
*Securing to Skid
*/
public String securingtoskid;
/**
*Additional Securing to Skid
*/
public String addsecuringtoskid;
/**
*Pest Management
*/
public String pestmanagement;
/**
*Shock Watch
*/
public String shockwatch;
/**
*Shock Watch Qty/Crate
*/
public UFDouble shockwatchqtycrate;
/**
*Tilt Watch
*/
public String tiltwatch;
/**
*Tilt Watch Qty/Crate
*/
public UFDouble tiltwatchqtycrate;
/**
*Remarks
*/
public String remarks;
/**
*Machine Description1
*/
public String machinedescri;
/**
*�ϲ㵥������
*/
public String billid;
/**
*ʱ���
*/
public UFDateTime ts;
    
    
/**
* ���� billid_b��Getter����.���������ӱ�����
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getBillid_b() {
return this.billid_b;
} 

/**
* ����billid_b��Setter����.���������ӱ�����
* ��������:2017-10-18
* @param newBillid_b java.lang.String
*/
public void setBillid_b ( String billid_b) {
this.billid_b=billid_b;
} 
 
/**
* ���� rowno��Getter����.���������к�
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getRowno() {
return this.rowno;
} 

/**
* ����rowno��Setter����.���������к�
* ��������:2017-10-18
* @param newRowno java.lang.String
*/
public void setRowno ( String rowno) {
this.rowno=rowno;
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
* ���� snno��Getter����.��������S/N
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getSnno() {
return this.snno;
} 

/**
* ����snno��Setter����.��������S/N
* ��������:2017-10-18
* @param newSnno java.lang.String
*/
public void setSnno ( String snno) {
this.snno=snno;
} 
 
/**
* ���� packdescription��Getter����.��������Description
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getPackdescription() {
return this.packdescription;
} 

/**
* ����packdescription��Setter����.��������Description
* ��������:2017-10-18
* @param newPackdescription java.lang.String
*/
public void setPackdescription ( String packdescription) {
this.packdescription=packdescription;
} 
 
/**
* ���� packqty��Getter����.��������Qty
*  ��������:2017-10-18
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getPackqty() {
return this.packqty;
} 

/**
* ����packqty��Setter����.��������Qty
* ��������:2017-10-18
* @param newPackqty nc.vo.pub.lang.UFDouble
*/
public void setPackqty ( UFDouble packqty) {
this.packqty=packqty;
} 
 
/**
* ���� cargodimensionl��Getter����.��������cargodimensionl
*  ��������:2017-10-18
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getCargodimensionl() {
return this.cargodimensionl;
} 

/**
* ����cargodimensionl��Setter����.��������cargodimensionl
* ��������:2017-10-18
* @param newCargodimensionl nc.vo.pub.lang.UFDouble
*/
public void setCargodimensionl ( UFDouble cargodimensionl) {
this.cargodimensionl=cargodimensionl;
} 
 
/**
* ���� cargodimensionw��Getter����.��������cargodimensionw
*  ��������:2017-10-18
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getCargodimensionw() {
return this.cargodimensionw;
} 

/**
* ����cargodimensionw��Setter����.��������cargodimensionw
* ��������:2017-10-18
* @param newCargodimensionw nc.vo.pub.lang.UFDouble
*/
public void setCargodimensionw ( UFDouble cargodimensionw) {
this.cargodimensionw=cargodimensionw;
} 
 
/**
* ���� cargodimensionh��Getter����.��������cargodimensionh
*  ��������:2017-10-18
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getCargodimensionh() {
return this.cargodimensionh;
} 

/**
* ����cargodimensionh��Setter����.��������cargodimensionh
* ��������:2017-10-18
* @param newCargodimensionh nc.vo.pub.lang.UFDouble
*/
public void setCargodimensionh ( UFDouble cargodimensionh) {
this.cargodimensionh=cargodimensionh;
} 
 
/**
* ���� cargodimensionweight��Getter����.��������cargodimensionweight
*  ��������:2017-10-18
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getCargodimensionweight() {
return this.cargodimensionweight;
} 

/**
* ����cargodimensionweight��Setter����.��������cargodimensionweight
* ��������:2017-10-18
* @param newCargodimensionweight nc.vo.pub.lang.UFDouble
*/
public void setCargodimensionweight ( UFDouble cargodimensionweight) {
this.cargodimensionweight=cargodimensionweight;
} 
 
/**
* ���� internaldimensionl��Getter����.��������internaldimensionl
*  ��������:2017-10-18
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getInternaldimensionl() {
return this.internaldimensionl;
} 

/**
* ����internaldimensionl��Setter����.��������internaldimensionl
* ��������:2017-10-18
* @param newInternaldimensionl nc.vo.pub.lang.UFDouble
*/
public void setInternaldimensionl ( UFDouble internaldimensionl) {
this.internaldimensionl=internaldimensionl;
} 
 
/**
* ���� internaldimensionw��Getter����.��������internaldimensionw
*  ��������:2017-10-18
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getInternaldimensionw() {
return this.internaldimensionw;
} 

/**
* ����internaldimensionw��Setter����.��������internaldimensionw
* ��������:2017-10-18
* @param newInternaldimensionw nc.vo.pub.lang.UFDouble
*/
public void setInternaldimensionw ( UFDouble internaldimensionw) {
this.internaldimensionw=internaldimensionw;
} 
 
/**
* ���� internaldimensionh��Getter����.��������internaldimensionh
*  ��������:2017-10-18
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getInternaldimensionh() {
return this.internaldimensionh;
} 

/**
* ����internaldimensionh��Setter����.��������internaldimensionh
* ��������:2017-10-18
* @param newInternaldimensionh nc.vo.pub.lang.UFDouble
*/
public void setInternaldimensionh ( UFDouble internaldimensionh) {
this.internaldimensionh=internaldimensionh;
} 
 
/**
* ���� internalm3��Getter����.��������Internal M3
*  ��������:2017-10-18
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getInternalm3() {
return this.internalm3;
} 

/**
* ����internalm3��Setter����.��������Internal M3
* ��������:2017-10-18
* @param newInternalm3 nc.vo.pub.lang.UFDouble
*/
public void setInternalm3 ( UFDouble internalm3) {
this.internalm3=internalm3;
} 
 
/**
* ���� cratedimensionl��Getter����.��������cratedimensionl
*  ��������:2017-10-18
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getCratedimensionl() {
return this.cratedimensionl;
} 

/**
* ����cratedimensionl��Setter����.��������cratedimensionl
* ��������:2017-10-18
* @param newCratedimensionl nc.vo.pub.lang.UFDouble
*/
public void setCratedimensionl ( UFDouble cratedimensionl) {
this.cratedimensionl=cratedimensionl;
} 
 
/**
* ���� cratedimensionw��Getter����.��������cratedimensionw
*  ��������:2017-10-18
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getCratedimensionw() {
return this.cratedimensionw;
} 

/**
* ����cratedimensionw��Setter����.��������cratedimensionw
* ��������:2017-10-18
* @param newCratedimensionw nc.vo.pub.lang.UFDouble
*/
public void setCratedimensionw ( UFDouble cratedimensionw) {
this.cratedimensionw=cratedimensionw;
} 
 
/**
* ���� cratedimensionh��Getter����.��������cratedimensionh
*  ��������:2017-10-18
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getCratedimensionh() {
return this.cratedimensionh;
} 

/**
* ����cratedimensionh��Setter����.��������cratedimensionh
* ��������:2017-10-18
* @param newCratedimensionh nc.vo.pub.lang.UFDouble
*/
public void setCratedimensionh ( UFDouble cratedimensionh) {
this.cratedimensionh=cratedimensionh;
} 
 
/**
* ���� cratedimensionweight��Getter����.��������Weight
*  ��������:2017-10-18
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getCratedimensionweight() {
return this.cratedimensionweight;
} 

/**
* ����cratedimensionweight��Setter����.��������Weight
* ��������:2017-10-18
* @param newCratedimensionweight nc.vo.pub.lang.UFDouble
*/
public void setCratedimensionweight ( UFDouble cratedimensionweight) {
this.cratedimensionweight=cratedimensionweight;
} 
 
/**
* ���� packremark��Getter����.��������Remark
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getPackremark() {
return this.packremark;
} 

/**
* ����packremark��Setter����.��������Remark
* ��������:2017-10-18
* @param newPackremark java.lang.String
*/
public void setPackremark ( String packremark) {
this.packremark=packremark;
} 
 
/**
* ���� uppc��Getter����.��������Unit px per crate
*  ��������:2017-10-18
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getUppc() {
return this.uppc;
} 

/**
* ����uppc��Setter����.��������Unit px per crate
* ��������:2017-10-18
* @param newUppc nc.vo.pub.lang.UFDouble
*/
public void setUppc ( UFDouble uppc) {
this.uppc=uppc;
} 
 
/**
* ���� externalm3��Getter����.��������External M3
*  ��������:2017-10-18
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getExternalm3() {
return this.externalm3;
} 

/**
* ����externalm3��Setter����.��������External M3
* ��������:2017-10-18
* @param newExternalm3 nc.vo.pub.lang.UFDouble
*/
public void setExternalm3 ( UFDouble externalm3) {
this.externalm3=externalm3;
} 
 
/**
* ���� machinedescription��Getter����.��������Machine Description
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getMachinedescription() {
return this.machinedescription;
} 

/**
* ����machinedescription��Setter����.��������Machine Description
* ��������:2017-10-18
* @param newMachinedescription java.lang.String
*/
public void setMachinedescription ( String machinedescription) {
this.machinedescription=machinedescription;
} 
 
/**
* ���� machinelength��Getter����.��������Machine Length
*  ��������:2017-10-18
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getMachinelength() {
return this.machinelength;
} 

/**
* ����machinelength��Setter����.��������Machine Length
* ��������:2017-10-18
* @param newMachinelength nc.vo.pub.lang.UFDouble
*/
public void setMachinelength ( UFDouble machinelength) {
this.machinelength=machinelength;
} 
 
/**
* ���� machinewidth��Getter����.��������Machine Width
*  ��������:2017-10-18
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getMachinewidth() {
return this.machinewidth;
} 

/**
* ����machinewidth��Setter����.��������Machine Width
* ��������:2017-10-18
* @param newMachinewidth nc.vo.pub.lang.UFDouble
*/
public void setMachinewidth ( UFDouble machinewidth) {
this.machinewidth=machinewidth;
} 
 
/**
* ���� machineheight��Getter����.��������Machine Height
*  ��������:2017-10-18
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getMachineheight() {
return this.machineheight;
} 

/**
* ����machineheight��Setter����.��������Machine Height
* ��������:2017-10-18
* @param newMachineheight nc.vo.pub.lang.UFDouble
*/
public void setMachineheight ( UFDouble machineheight) {
this.machineheight=machineheight;
} 
 
/**
* ���� fullboxskid��Getter����.��������Full Box/Skid
*  ��������:2017-10-18
* @return nc.vo.bhs.pack.Enumerate
*/
public String getFullboxskid() {
return this.fullboxskid;
} 

/**
* ����fullboxskid��Setter����.��������Full Box/Skid
* ��������:2017-10-18
* @param newFullboxskid nc.vo.bhs.pack.Enumerate
*/
public void setFullboxskid ( String fullboxskid) {
this.fullboxskid=fullboxskid;
} 
 
/**
* ���� iffullbox��Getter����.��������If Full Box
*  ��������:2017-10-18
* @return nc.vo.bhs.pack.Enumerate
*/
public String getIffullbox() {
return this.iffullbox;
} 

/**
* ����iffullbox��Setter����.��������If Full Box
* ��������:2017-10-18
* @param newIffullbox nc.vo.bhs.pack.Enumerate
*/
public void setIffullbox ( String iffullbox) {
this.iffullbox=iffullbox;
} 
 
/**
* ���� way4way��Getter����.��������2 way/4 way
*  ��������:2017-10-18
* @return nc.vo.bhs.pack.Enumerate
*/
public String getWay4way() {
return this.way4way;
} 

/**
* ����way4way��Setter����.��������2 way/4 way
* ��������:2017-10-18
* @param newWay4way nc.vo.bhs.pack.Enumerate
*/
public void setWay4way ( String way4way) {
this.way4way=way4way;
} 
 
/**
* ���� vacumnormal��Getter����.��������Vacum/Normal
*  ��������:2017-10-18
* @return nc.vo.bhs.pack.Enumerate
*/
public String getVacumnormal() {
return this.vacumnormal;
} 

/**
* ����vacumnormal��Setter����.��������Vacum/Normal
* ��������:2017-10-18
* @param newVacumnormal nc.vo.bhs.pack.Enumerate
*/
public void setVacumnormal ( String vacumnormal) {
this.vacumnormal=vacumnormal;
} 
 
/**
* ���� cleat��Getter����.��������Cleat
*  ��������:2017-10-18
* @return nc.vo.bhs.pack.Enumerate
*/
public String getCleat() {
return this.cleat;
} 

/**
* ����cleat��Setter����.��������Cleat
* ��������:2017-10-18
* @param newCleat nc.vo.bhs.pack.Enumerate
*/
public void setCleat ( String cleat) {
this.cleat=cleat;
} 
 
/**
* ���� externalsecuring��Getter����.��������External Securing
*  ��������:2017-10-18
* @return nc.vo.bhs.pack.Enumerate
*/
public String getExternalsecuring() {
return this.externalsecuring;
} 

/**
* ����externalsecuring��Setter����.��������External Securing
* ��������:2017-10-18
* @param newExternalsecuring nc.vo.bhs.pack.Enumerate
*/
public void setExternalsecuring ( String externalsecuring) {
this.externalsecuring=externalsecuring;
} 
 
/**
* ���� additionalexternal��Getter����.��������Additional External
*  ��������:2017-10-18
* @return nc.vo.bhs.pack.Enumerate
*/
public String getAdditionalexternal() {
return this.additionalexternal;
} 

/**
* ����additionalexternal��Setter����.��������Additional External
* ��������:2017-10-18
* @param newAdditionalexternal nc.vo.bhs.pack.Enumerate
*/
public void setAdditionalexternal ( String additionalexternal) {
this.additionalexternal=additionalexternal;
} 
 
/**
* ���� skidtype��Getter����.��������Skid Type
*  ��������:2017-10-18
* @return nc.vo.bhs.pack.Enumerate
*/
public String getSkidtype() {
return this.skidtype;
} 

/**
* ����skidtype��Setter����.��������Skid Type
* ��������:2017-10-18
* @param newSkidtype nc.vo.bhs.pack.Enumerate
*/
public void setSkidtype ( String skidtype) {
this.skidtype=skidtype;
} 
 
/**
* ���� floating��Getter����.��������Floating/Non Floating
*  ��������:2017-10-18
* @return nc.vo.bhs.pack.Enumerate
*/
public String getFloating() {
return this.floating;
} 

/**
* ����floating��Setter����.��������Floating/Non Floating
* ��������:2017-10-18
* @param newFloating nc.vo.bhs.pack.Enumerate
*/
public void setFloating ( String floating) {
this.floating=floating;
} 
 
/**
* ���� securingtoskid��Getter����.��������Securing to Skid
*  ��������:2017-10-18
* @return nc.vo.bhs.pack.Enumerate
*/
public String getSecuringtoskid() {
return this.securingtoskid;
} 

/**
* ����securingtoskid��Setter����.��������Securing to Skid
* ��������:2017-10-18
* @param newSecuringtoskid nc.vo.bhs.pack.Enumerate
*/
public void setSecuringtoskid ( String securingtoskid) {
this.securingtoskid=securingtoskid;
} 
 
/**
* ���� addsecuringtoskid��Getter����.��������Additional Securing to Skid
*  ��������:2017-10-18
* @return nc.vo.bhs.pack.Enumerate
*/
public String getAddsecuringtoskid() {
return this.addsecuringtoskid;
} 

/**
* ����addsecuringtoskid��Setter����.��������Additional Securing to Skid
* ��������:2017-10-18
* @param newAddsecuringtoskid nc.vo.bhs.pack.Enumerate
*/
public void setAddsecuringtoskid ( String addsecuringtoskid) {
this.addsecuringtoskid=addsecuringtoskid;
} 
 
/**
* ���� pestmanagement��Getter����.��������Pest Management
*  ��������:2017-10-18
* @return nc.vo.bhs.pack.Enumerate
*/
public String getPestmanagement() {
return this.pestmanagement;
} 

/**
* ����pestmanagement��Setter����.��������Pest Management
* ��������:2017-10-18
* @param newPestmanagement nc.vo.bhs.pack.Enumerate
*/
public void setPestmanagement ( String pestmanagement) {
this.pestmanagement=pestmanagement;
} 
 
/**
* ���� shockwatch��Getter����.��������Shock Watch
*  ��������:2017-10-18
* @return nc.vo.bhs.pack.Enumerate
*/
public String getShockwatch() {
return this.shockwatch;
} 

/**
* ����shockwatch��Setter����.��������Shock Watch
* ��������:2017-10-18
* @param newShockwatch nc.vo.bhs.pack.Enumerate
*/
public void setShockwatch ( String shockwatch) {
this.shockwatch=shockwatch;
} 
 
/**
* ���� shockwatchqtycrate��Getter����.��������Shock Watch Qty/Crate
*  ��������:2017-10-18
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getShockwatchqtycrate() {
return this.shockwatchqtycrate;
} 

/**
* ����shockwatchqtycrate��Setter����.��������Shock Watch Qty/Crate
* ��������:2017-10-18
* @param newShockwatchqtycrate nc.vo.pub.lang.UFDouble
*/
public void setShockwatchqtycrate ( UFDouble shockwatchqtycrate) {
this.shockwatchqtycrate=shockwatchqtycrate;
} 
 
/**
* ���� tiltwatch��Getter����.��������Tilt Watch
*  ��������:2017-10-18
* @return nc.vo.bhs.pack.Enumerate
*/
public String getTiltwatch() {
return this.tiltwatch;
} 

/**
* ����tiltwatch��Setter����.��������Tilt Watch
* ��������:2017-10-18
* @param newTiltwatch nc.vo.bhs.pack.Enumerate
*/
public void setTiltwatch ( String tiltwatch) {
this.tiltwatch=tiltwatch;
} 
 
/**
* ���� tiltwatchqtycrate��Getter����.��������Tilt Watch Qty/Crate
*  ��������:2017-10-18
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getTiltwatchqtycrate() {
return this.tiltwatchqtycrate;
} 

/**
* ����tiltwatchqtycrate��Setter����.��������Tilt Watch Qty/Crate
* ��������:2017-10-18
* @param newTiltwatchqtycrate nc.vo.pub.lang.UFDouble
*/
public void setTiltwatchqtycrate ( UFDouble tiltwatchqtycrate) {
this.tiltwatchqtycrate=tiltwatchqtycrate;
} 
 
/**
* ���� remarks��Getter����.��������Remarks
*  ��������:2017-10-18
* @return java.lang.String
*/
public String getRemarks() {
return this.remarks;
} 

/**
* ����remarks��Setter����.��������Remarks
* ��������:2017-10-18
* @param newRemarks java.lang.String
*/
public void setRemarks ( String remarks) {
this.remarks=remarks;
} 
 
/**
* ���� machinedescri��Getter����.��������Machine Description1
*  ��������:2017-10-18
* @return nc.vo.bhs.pack.Enumerate
*/
public String getMachinedescri() {
return this.machinedescri;
} 

/**
* ����machinedescri��Setter����.��������Machine Description1
* ��������:2017-10-18
* @param newMachinedescri nc.vo.bhs.pack.Enumerate
*/
public void setMachinedescri ( String machinedescri) {
this.machinedescri=machinedescri;
} 
 
/**
* ���� �����ϲ�������Getter����.���������ϲ�����
*  ��������:2017-10-18
* @return String
*/
public String getBillid(){
return this.billid;
}
/**
* ���������ϲ�������Setter����.���������ϲ�����
* ��������:2017-10-18
* @param newBillid String
*/
public void setBillid(String billid){
this.billid=billid;
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
    return VOMetaFactory.getInstance().getVOMeta("bhs.SoOrderPackBVO");
    }
   }
    