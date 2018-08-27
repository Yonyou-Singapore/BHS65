package com.su.sa.job.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 
 * <p>
 * ʱ����ز����Ĺ�����
 * </p>
 */
public class DateUtils {

	/** ����ʱ���ʽ ��2007-08-20 16:14:16 */
	public static final DateFormat DATE_TIME_FORMAT = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	/** ���ڸ�ʽ��2007-08-20 */
	public static final DateFormat DATE_FORMAT = new SimpleDateFormat(
			"yyyy-MM-dd");

	/** ���ڸ�ʽ��20070820 */
	public static final DateFormat yyyyMMdd_FORMAT = new SimpleDateFormat(
			"yyyyMMdd");

	/** ʱ���ʽ��16:07:40 */
	public static final DateFormat TIME_FORMAT = new SimpleDateFormat(
			"HH:mm:ss");

	/** ����ʱ���ʽ��08-20 16:15 */
	public static final DateFormat SHORT_DATE_TIME_FORMAT = new SimpleDateFormat(
			"MM-dd HH:mm");

	/** �������ĸ�ʽ��2007��9��20�� */
	public static final DateFormat CHINESE_DATE_FORMAT = new SimpleDateFormat(
			"yyyy��M��d��");

	/** ����ʱ�����ĸ�ʽ��2007��9��20�� 15ʱ */
	public static final DateFormat CHINESE_DATE_TIME_FORMAT = new SimpleDateFormat(
			"yyyy��M��d��Hʱ");

	/** �������ĸ�ʽ��2007��9��20�� ������ */
	public static final DateFormat CHINESE_DATE_WEEK_FORMAT = new SimpleDateFormat(
			"yyyy��M��d�� E");

	/** ����ʱ���ʽ ��2007-08-20 16:14 */
	public static final DateFormat yyyy_MM_dd_HH_mm_FORMAT = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm");

	/** ���ڸ�ʽ ��2007 */
	public static final DateFormat yyyy_FORMAT = new SimpleDateFormat("yyyy");

	/** ���ڸ�ʽ ��200709 */
	public static final DateFormat yyyyMM_FORMAT = new SimpleDateFormat(
			"yyyyMM");

	/** ���ڸ�ʽ ��2007-09 */
	public static final DateFormat yyyy_MM_FORMAT = new SimpleDateFormat(
			"yyyy-MM");

	/**
	 * ���������ַ����Ƿ�ʹ�ö�Ӧ��ģʽ
	 * 
	 * @param dateString
	 *            �����ַ���
	 * 
	 * @param pattern
	 *            ģʽ
	 * @return
	 */
	public static boolean isUsePattern(String dateString, String pattern) {
		if (dateString == null) {
			throw new NullPointerException();
		}

		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		try {
			formatter.parse(dateString);
			return true;
		} catch (ParseException ex) {
			return false;
		}
	}

	public static String format(Date date, String pattern) {
		if (date == null) {
			return "";
		}

		SimpleDateFormat formatter = new SimpleDateFormat(pattern,
				Locale.getDefault());
		return formatter.format(date);
	}

	public static String format(long longDate, String pattern) {
		Date tempDate = new Date(longDate);
		return format(tempDate, pattern);
	}

	// /**
	// * 24Сʱ�Ƶ�ʱ����ַ�����ʾ����
	// */
	// public static String dateTo24HString(long date) {
	// Date tempDate = new Date(date);
	// return format(tempDate, "yyyy-MM-dd HH:mm:ss");
	// }
	//
	// /**
	// * 24Сʱ�Ƶ�ʱ���ַ�����ʾ����
	// */
	// public static String dateTo24HString(Date date) {
	// return format(date, "yyyy-MM-dd HH:mm:ss");
	// }
	//
	// /**
	// * 12Сʱ�Ƶ�ʱ���ַ�����ʾ����
	// */
	// public static String dateTo12HString(Date date) {
	// return format(date, "yyyy-MM-dd hh:mm:ss");
	// }
	//
	// /**
	// * 12Сʱ�Ƶ�ʱ����ַ�����ʾ����
	// */
	// public static String dateTo12HString(long date) {
	// Date tempDate = new Date(date);
	// return dateTo12HString(tempDate);
	// }

	static Date parse(String dateString, String pattern) {
		if (dateString == null || dateString.trim().length() == 0) {
			return null;
		}

		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		try {
			return formatter.parse(dateString);
		} catch (ParseException ex) {
			throw new IllegalArgumentException("Illegal datetime string "
					+ dateString);
		}
	}

	/**
	 * ����String�����ڸ�ʽת����sql����.
	 * 
	 * @param dateString
	 *            �����ַ���
	 * @param pattern
	 *            ���ڸ�ʽ��
	 * @return Date
	 */
	public static java.sql.Date parseSQLDate(String dateString, String pattern) {
		Date utilDate = parse(dateString, pattern);
		return (utilDate != null) ? (new java.sql.Date(utilDate.getTime()))
				: null;
	}

	/**
	 * util���ڵ�sql����.
	 * 
	 * @param pDate
	 *            Date
	 * @return Date
	 */
	public static java.sql.Date utilDate2sqlDate(java.util.Date pDate) {
		if (pDate == null)
			return null;
		long utilLong = pDate.getTime();
		java.sql.Date result = new java.sql.Date(utilLong);
		return result;
	}

	/**
	 * ��һ��
	 * 
	 * @param date
	 * @return
	 */
	public static Date nextDate(Date now) {
		if (now == null) {
			throw new NullPointerException();
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(now);
		calendar.add(Calendar.DATE, 1);
		return calendar.getTime();
	}

	/**
	 * �Ӵ������ڶ����л�ȡ������ݣ����������ڶ���Ϊ<code>null</code>ʱ������1990
	 * 
	 * @param date
	 * @return
	 */
	public static int getYearOfDate(Date date) {
		if (date == null) {
			return 1990;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);
	}

	/**
	 * �Ӵ������ڶ����л�ȡ�·����ݣ����������ڶ���Ϊ<code>null</code>ʱ������1
	 * 
	 * @param date
	 * @return
	 */
	public static int getMonthOfDate(Date date) {
		if (date == null) {
			return 1;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH) + 1;
	}

	/**
	 * �Ӵ������ڶ����л�ȡ�������ݣ����������ڶ���Ϊ<code>null</code>ʱ������1
	 * 
	 * @param date
	 * @return
	 */
	public static int getDayOfDate(Date date) {
		if (date == null) {
			return 1;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * ��ȡ���ڶ��������ֵ
	 * 
	 * @param date
	 *            ���ڶ���
	 * @return ����ֵ
	 */
	public static int getDayOfWeek(Date date) {
		if (date == null) {
			return 1;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * ��������ʱ��Ĳ�ֵ�������Ժ���Ϊ��λ��long
	 * 
	 * @param date1
	 * @param date2
	 * @return date1-date2
	 */
	public static long compareDate(Date date1, Date date2) {
		if (date1 == null) {
			long now = System.currentTimeMillis();
			date1 = new Date(now);
		}
		if (date2 == null) {
			long now = System.currentTimeMillis();
			date2 = new Date(now);
		}
		return date1.getTime() - date2.getTime();
	}

	
	/**
	 * ��������ʱ��Ĳ�ֵ�������Ժ���Ϊ��λ��long
	 * 
	 * @param date1
	 * @param date2
	 * @return date1-date2
	 * @throws ParseException 
	 */
	public static boolean compareDateEX(String compareDate) {
		java.util.Date nowdate=new java.util.Date(); 
		//String myString = "2008-09-08";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d = null;
		try {
			d = sdf.parse(compareDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//�ж��Ƿ����
		boolean todayFlag = isSameDay(d,new Date());
		if(todayFlag){
			return false;
		}
		
		boolean flag = d.before(nowdate);
		if(flag){
			System.out.print("���ڽ���");
		}
		else{
			System.out.print("���ڽ���");
		}
		return flag;
	}
	
	public static boolean isSameDay(Date day1, Date day2) {
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    String ds1 = sdf.format(day1);
	    String ds2 = sdf.format(day2);
	    if (ds1.equals(ds2)) {
	        return true;
	    } else {
	        return false;
	    }
	}
	/**
	 * ������ת���� ���Сʱ����֡���
	 * 
	 * @param timeMills
	 * @return "���Сʱ����֡���"���ַ���ʽ, ��3��2Сʱ35��23��
	 * 
	 * @throws OAException
	 */
	public static String ms2HMS(long ms) {
		if (ms < 0)
			return null;
		long s = ms / 1000;
		long d = s / (3600 * 24);
		long h = (s - d * 3600 * 24) / 3600;
		long m = (s - d * 3600 * 24 - h * 3600) / 60;
		long ss = s - d * 3600 * 24 - h * 3600 - m * 60;
		String returnStr = "";
		if (d > 0)
			returnStr = returnStr + d + "��";
		if (h > 0)
			returnStr = returnStr + h + "Сʱ";
		if (m > 0 && (d == 0))
			returnStr = returnStr + m + "����";
		if (returnStr.equals("") || (d == 0 && h == 0))
			returnStr = returnStr + ss + "��";
		return returnStr;
	}

	// /**
	// * ��ָ����ʽ��java.util.Date����ת��Ϊ�ַ��� ����:2003��04��22��
	// *
	// *
	// * @param date
	// * @return �� "2003��04��22��" ��ʽ�������ַ���
	// */
	// public static String getChinaDate(java.util.Date date) {
	// return format(date, "yyyy��MM��dd��");
	// }
	//
	// /**
	// * ��ָ����ʽ��java.util.Date����ת��Ϊ�ַ��� ����:2003��04��22�� 15ʱ02��01��
	// *
	// *
	// * @param date
	// * @return �� "2003��04��22�� 15ʱ02��01��" ��ʽ�������ַ���
	// */
	//
	// public static String getChinaDateTime(java.util.Date date) {
	// return format(date, "yyyy��MM��dd�� hhʱmm��ss��");
	// }

	// /**
	// * ��ȡ��ǰʱ��֮���day����ĳһ��(���磺2003��05��22��֮���23����2003-06-14)
	// *
	// * @param day
	// * @return ��ǰʱ��֮���day����ĳһ�죬���ַ�����ʽ����,��2003-06-14
	// * @throws Exception
	// */
	//
	// public static String getAfterDateString(int day) throws Exception {
	// long onetimes = (long) 1 * 24 * 60 * 60 * 1000; // һ��ĺ�����
	//
	// long now = System.currentTimeMillis();
	// long after = now + onetimes * day;
	//
	// java.util.Date date = new java.util.Date(after);
	//
	// return format(date, "yyyy-MM-dd");
	//
	// }

	/**
	 * ��ĳ����������������.
	 * 
	 * @param d
	 * @param days
	 * @return
	 */
	public static Date addDate(Date d, int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH)
				+ days);
		return calendar.getTime();
	}

	/**
	 * ��ȡ��ǰʱ��֮���day����ĳһ��(���磺2003��05��22��֮���23����2003-06-14)
	 * 
	 * @param day
	 * @return ���ڶ���
	 * @throws Exception
	 */

	public static java.util.Date getAfterDate(int day) {
		long onetimes = (long) 1 * 24 * 60 * 60 * 1000; // һ��ĺ�����

		long now = System.currentTimeMillis();
		long after = now + onetimes * day;

		java.util.Date date = new java.util.Date(after);

		return date;

	}

	/**
	 * ��ȡ��ǰʱ��֮ǰ��day����ĳһ��(���磺2003��05��22��֮ǰ��20����2003-05-02)
	 * 
	 * @param day
	 * @return ��ǰʱ��֮ǰ��day����ĳһ�죬���ַ�����ʽ����,��2003-06-14
	 * @throws Exception
	 */

	public static String getBeforeDateString(int day) {
		long onetimes = (long) 1 * 24 * 60 * 60 * 1000; // һ��ĺ�����

		long now = System.currentTimeMillis();
		long after = now - onetimes * day;

		java.util.Date date = new java.util.Date(after);

		return format(date, "yyyy-MM-dd");

	}

	/**
	 * ��ȡ��ǰʱ��֮ǰ��day����ĳһ��(���磺2003��05��22��֮ǰ��20����2003-05-02)
	 * 
	 * @param day
	 * @return ���ڶ���
	 * @throws Exception
	 */

	public static java.util.Date getBeforeDate(int day) {
		long onetimes = (long) 1 * 24 * 60 * 60 * 1000; // һ��ĺ�����

		long now = System.currentTimeMillis();
		long after = now - onetimes * day;

		java.util.Date date = new java.util.Date(after);

		return date;

	}

	/**
	 * ��ĳ����������������.
	 * 
	 * @param d
	 * @param months
	 * @return
	 */
	public static Date addMonth(Date d, int months) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d);
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + months);
		return calendar.getTime();
	}

	// /**
	// * ���ڸ�����ʱ���ʽ�����磺2006-2 2006-2-1���ص��µ����һ�������2006-02-28���������һ�����ڸ�ʽû��Ҫ
	// * @param date
	// * @return
	// */
	// public static String getActualMaximumOfDate(String date){
	// Date d = parse(date,"yyyy-MM");
	// String dstr = format(d,"yyyy-MM");
	// Calendar calendar = Calendar.getInstance();
	// calendar.setTime(d);
	// int days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	// return dstr+"-"+days;
	// }

	// /**
	// * ����
	// *
	// * @return
	// */
	// public static Date tomorrow() {
	// return nextDate(new Date());
	// }

	// /**
	// * ����
	// *
	// * @return
	// */
	// public static Date yesterday() {
	// return preDate(new Date());
	// }

	/**
	 * ǰһ��
	 * 
	 * @param date
	 * @return
	 */
	public static Date preDate(Date date) {
		return new Date(date.getTime() - 24 * 60 * 60 * 1000);
	}

	// public static Date nextDate(Date date) {
	// return new Date(date.getTime() + 24 * 60 * 60 * 1000);
	// }

	// /**
	// * ����1��1��
	// *
	// * @return
	// */
	// public static Date thisYear() {
	// return parse(yyyy_FORMAT, yyyy_FORMAT.format(new Date()));
	// }

	// /**
	// * ����1��1��
	// *
	// * @return
	// */
	// public static Date nextYear() {
	// int year = Integer.parseInt(yyyy_FORMAT.format(new Date()));
	// return parse(yyyy_FORMAT, year + 1 + "");
	// }

	// /**
	// * �����磺��2007-08-24�������ڸ�ʽ�ַ���
	// *
	// * @param date
	// * @return �����ʽ����ȷ������null
	// */
	// public static Date parseDate(String date) {
	// return parse(DATE_FORMAT, date);
	// }

	// /**
	// * �����磺��2007-08-24 10:07:23�������ڸ�ʽ�ַ���
	// *
	// * @param datetime
	// * @return �����ʽ����ȷ������null
	// */
	// public static Date parseDateTime(String datetime) {
	// return parse(DATE_TIME_FORMAT, datetime);
	// }

	/**
	 * �������ĸ�ʽ��������ʱ���ַ���
	 * 
	 * @param format
	 * @param datetime
	 * @return �����ʽ����ȷ������null
	 */
	public static Date parse(DateFormat format, String datetime) {
		try {
			return format.parse(datetime);
		} catch (Exception e) {
			return null;
		}
	}

	public static String getOneHoursAfterTime(int hour) {
		String oneHoursAgoTime = "";
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR, Calendar.HOUR + (hour + 1)); // ��ʱ������Ϊ��ǰʱ��-1Сʱ��ͬ��Ҳ������������ʱ��
		// cal.set(Calendar.MONTH, Calendar.MONTH - 1); // ��ǰ��ǰһ��
		oneHoursAgoTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(cal.getTime());// ��ȡ��������ʱ��
		return oneHoursAgoTime;
	}

	public static String beforeOneHourToNowDate(int hour) {
		Calendar calendar = Calendar.getInstance();
		/* HOUR_OF_DAY ָʾһ���е�Сʱ */
		calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY)
				- hour);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(calendar.getTime());
	}

	// public static void main(String[] args) {
	// System.out.println(DateUtils.getOneHoursAgoTime());
	// }

	public static void main(String[] args) throws Exception {
		System.out.println(DateUtils.getBeforeDateString(10));
	}
}
