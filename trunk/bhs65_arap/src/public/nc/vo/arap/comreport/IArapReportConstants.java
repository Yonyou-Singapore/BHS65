package nc.vo.arap.comreport;

import nc.vo.arap.pub.BillEnumCollection;

/**
 * �ո����������ӿ�<br>
 * 
 * @author ������<br>
 * @since V60<br>
 */
public interface IArapReportConstants {
	
	// �Ƿ���ʾ��С�ƣ�֧��UFBooleanֵ
	public static final String KEY_SHOW_DATE_TOTAL = "showDateTotalComb";
	// �ѻس���ݹ����ݲ���ʾ��֧��UFBooleanֵ
	public static final String KEY_NOTSHOW_BACKESTI = "notshowBackEstiComb";
	// �Ƿ��ǲ�ѯԤ��/Ԥ�������䣺֧��UFBooleanֵ
	public static final String KEY_PRE_REC_PAY_ACCAGE = "prerecpayaccage";
	// �޷�������ʾ&�޷���������ʾ
	public static final String KEY_NOOCC_NOSHOW = "nooccur_noshow";
	public static final String KEY_NOBAL_NOOCC_NOSHOW = "nobal_nooccur_noshow";
	// Ԥ�⹹�ɣ�Ӧ��/�����/������Ԥ��/�����
	public static final String KEY_FORECAST_COMPOSE_RECPAY = "recpaycheck";
	public static final String KEY_FORECAST_COMPOSE_RECPAYBAL = "recpaybalcheck";
	public static final String KEY_FORECAST_COMPOSE_PRERECPAYBAL = "prerecpaybalcheck";
	// Ӧ��/������������Ƿ���ʾ������
	public static final String KEY_SHOW_EXPIREDATE = "showexpiredate";

	// �������ģʽ
	public static final String ACC_ANA_MODE_AGE = "byAccAge"; // ������
	public static final String ACC_ANA_MODE_DATE = "byDate"; // ������

	// �����������
	public static final String ACC_ANA_DIRECT_RECABLE_REC = "recable_rec"; // Ӧ��-�տ�
	public static final String ACC_ANA_DIRECT_PAYABLE_PAY = "payable_pay"; // Ӧ��-����

	// ��������
	public static final String ACC_ANA_TYP_SETTLE = "settle"; // ��������
	public static final String ACC_ANA_TYP_DEADLINE = "deadline"; // ��ֹ����

	// ��������
	public static final String ACC_ANA_DATE_EXPIREDATE = "expiredate"; // ������
	public static final String ACC_ANA_DATE_BILLDATE = "billdate"; // ��������
	public static final String ACC_ANA_DATE_AUDITDATE = "auditdate"; // ��������
	public static final String ACC_ANA_DATE_EFFECTDATE = "effectdate"; // ��Ч����
	public static final String ACC_ANA_DATE_WITHINDATE = "withindate"; // �ڿص�������
	public static final String ACC_ANA_DATE_RAISEDATE = "raisedate"; // ��������

	// ������ʽ
	public static final String ACC_ANA_PATTERN_FINAL = "final"; // �������
	public static final String ACC_ANA_PATTERN_POINT = "point"; // �����

	// ���滻����
	public static final String REPLACE_TABLE = "@Table";

	// ȫ������״̬
	public static final int I_BILL_STATUS_ALL = -1000;

	// ��������
	public static final String ANA_DIRECT_RECABLE = "recable"; // Ӧ��
	public static final String ANA_DIRECT_RECABLE_REC = "recable_rec"; // Ӧ��-�տ�
	public static final String ANA_DIRECT_RECABLE_REC_PRE = "recable_rec_pre"; // Ӧ��-�տ�-Ԥ��
	public static final String ANA_DIRECT_PAYABLE = "payable"; // Ӧ��
	public static final String ANA_DIRECT_PAYABLE_PAY = "payable_pay"; // Ӧ��-����
	public static final String ANA_DIRECT_PAYABLE_PAY_PRE = "payable_pay_pre"; // Ӧ��-����-Ԥ��

	// ϵͳ��ʶ
	public static final int SYS_FLAG_REC = 3; // Ӧ��
	public static final int SYS_FLAG_PAY = 4; // Ӧ��
	public static final String MODULEID_AR = "2006";
	public static final String MODULEID_AP = "2008";

	// ��������
	public static final int ASSO_OBJ_ALL = 9; // ����
	public static final int ASSO_OBJ_CUS_SUP = 8; // ����
	public static final int ASSO_OBJ_CUSTOMER = BillEnumCollection.ObjType.CUSTOMER.VALUE; // �ͻ�
	public static final int ASSO_OBJ_SUPPLIER = BillEnumCollection.ObjType.SUPPLIER.VALUE; // ��Ӧ��
	public static final int ASSO_OBJ_DEPT = BillEnumCollection.ObjType.DEP.VALUE; // ����
	public static final int ASSO_OBJ_BUSIMAN = BillEnumCollection.ObjType.PERSON.VALUE; // ҵ��Ա

	public static final String DEF = "def";
	public static final int BAL_DEF_LEN = 6;

	public static final int REPORT_QRYOBJ_SIZE = 5; // ͬʱ֧�ֵı����ѯ�������
	
	public static final String IS_MATCUSTCODE = "isShowMatCustCode"; // �Ƿ���ʾ���Ͽͻ���
	
	//add for �¼���BDA 20161111
	public static final String IS_TOTALCODE = "isShowTotalCode"; // �Ƿ���ʾС��
	
	public static final String IS_CANAFTERCODE = "isCancelAfterCode"; // �Ƿ����ʾδ����
	
	public static final String IS_DJCODE = "isShowDjCode"; // �Ƿ���ʾ��Ӧ���ݺ���Ϣ
	
	public static final String IS_FINAL_BALANCE = "isFinalBalance"; //��ĩ���Ϊ���ұ����޷����Ƿ��ӡ
	
	public static final String IS_COUNTER_FEE = "isCounterFee"; //�Ƿ���ʾ������
	
	public static final String AGING_REF = "agingRef"; //�����ڼ�
	//add end
	
	// ������ҵ��ϵͳ���� ��������
	public static final String STATE_BY_LOCAL = "statebylocal"; // ������ԭ�Ҷ�
	public static final String STATE_BY_ORI = "statebyori"; // ֻ�����Ҷ�
	public static final String GLBR = "glbr"; // ֻ�����Ҷ�
	public static final String GLBRSUM = "glbrsum"; // ֻ�����Ҷ�
	
	
}

// /:~
