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
 * 时间相关操作的工具类
 * </p>
 */
public class DateUtils {

	/** 日期时间格式 ：2007-08-20 16:14:16 */
	public static final DateFormat DATE_TIME_FORMAT = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	/** 日期格式：2007-08-20 */
	public static final DateFormat DATE_FORMAT = new SimpleDateFormat(
			"yyyy-MM-dd");

	/** 日期格式：20070820 */
	public static final DateFormat yyyyMMdd_FORMAT = new SimpleDateFormat(
			"yyyyMMdd");

	/** 时间格式：16:07:40 */
	public static final DateFormat TIME_FORMAT = new SimpleDateFormat(
			"HH:mm:ss");

	/** 日期时间格式：08-20 16:15 */
	public static final DateFormat SHORT_DATE_TIME_FORMAT = new SimpleDateFormat(
			"MM-dd HH:mm");

	/** 日期中文格式：2007年9月20日 */
	public static final DateFormat CHINESE_DATE_FORMAT = new SimpleDateFormat(
			"yyyy年M月d日");

	/** 日期时间中文格式：2007年9月20日 15时 */
	public static final DateFormat CHINESE_DATE_TIME_FORMAT = new SimpleDateFormat(
			"yyyy年M月d日H时");

	/** 日期中文格式：2007年9月20日 星期三 */
	public static final DateFormat CHINESE_DATE_WEEK_FORMAT = new SimpleDateFormat(
			"yyyy年M月d日 E");

	/** 日期时间格式 ：2007-08-20 16:14 */
	public static final DateFormat yyyy_MM_dd_HH_mm_FORMAT = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm");

	/** 日期格式 ：2007 */
	public static final DateFormat yyyy_FORMAT = new SimpleDateFormat("yyyy");

	/** 日期格式 ：200709 */
	public static final DateFormat yyyyMM_FORMAT = new SimpleDateFormat(
			"yyyyMM");

	/** 日期格式 ：2007-09 */
	public static final DateFormat yyyy_MM_FORMAT = new SimpleDateFormat(
			"yyyy-MM");

	/**
	 * 测试日期字符串是否使用对应的模式
	 * 
	 * @param dateString
	 *            日期字符串
	 * 
	 * @param pattern
	 *            模式
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
	// * 24小时制的时间的字符串表示方法
	// */
	// public static String dateTo24HString(long date) {
	// Date tempDate = new Date(date);
	// return format(tempDate, "yyyy-MM-dd HH:mm:ss");
	// }
	//
	// /**
	// * 24小时制的时间字符串表示方法
	// */
	// public static String dateTo24HString(Date date) {
	// return format(date, "yyyy-MM-dd HH:mm:ss");
	// }
	//
	// /**
	// * 12小时制的时间字符串表示方法
	// */
	// public static String dateTo12HString(Date date) {
	// return format(date, "yyyy-MM-dd hh:mm:ss");
	// }
	//
	// /**
	// * 12小时制的时间的字符串表示方法
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
	 * 日期String和日期格式转换成sql日期.
	 * 
	 * @param dateString
	 *            日期字符串
	 * @param pattern
	 *            日期格式串
	 * @return Date
	 */
	public static java.sql.Date parseSQLDate(String dateString, String pattern) {
		Date utilDate = parse(dateString, pattern);
		return (utilDate != null) ? (new java.sql.Date(utilDate.getTime()))
				: null;
	}

	/**
	 * util日期到sql日期.
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
	 * 后一天
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
	 * 从传入日期对象中获取年份数据，当传入日期对象为<code>null</code>时，返回1990
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
	 * 从传入日期对象中获取月份数据，当传入日期对象为<code>null</code>时，返回1
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
	 * 从传入日期对象中获取日期数据，当传入日期对象为<code>null</code>时，返回1
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
	 * 获取日期对象的星期值
	 * 
	 * @param date
	 *            日期对象
	 * @return 星期值
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
	 * 计算两个时间的差值，返回以毫秒为单位的long
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
	 * 计算两个时间的差值，返回以毫秒为单位的long
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
		
		//判断是否今天
		boolean todayFlag = isSameDay(d,new Date());
		if(todayFlag){
			return false;
		}
		
		boolean flag = d.before(nowdate);
		if(flag){
			System.out.print("早于今天");
		}
		else{
			System.out.print("晚于今天");
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
	 * 将毫秒转化成 天×小时零×分×秒
	 * 
	 * @param timeMills
	 * @return "天×小时零×分×秒"的字符格式, 如3天2小时35分23秒
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
			returnStr = returnStr + d + "天";
		if (h > 0)
			returnStr = returnStr + h + "小时";
		if (m > 0 && (d == 0))
			returnStr = returnStr + m + "分钟";
		if (returnStr.equals("") || (d == 0 && h == 0))
			returnStr = returnStr + ss + "秒";
		return returnStr;
	}

	// /**
	// * 按指定格式将java.util.Date日期转换为字符串 例如:2003年04月22日
	// *
	// *
	// * @param date
	// * @return 如 "2003年04月22日" 格式的日期字符串
	// */
	// public static String getChinaDate(java.util.Date date) {
	// return format(date, "yyyy年MM月dd日");
	// }
	//
	// /**
	// * 按指定格式将java.util.Date日期转换为字符串 例如:2003年04月22日 15时02分01秒
	// *
	// *
	// * @param date
	// * @return 如 "2003年04月22日 15时02分01秒" 格式的日期字符串
	// */
	//
	// public static String getChinaDateTime(java.util.Date date) {
	// return format(date, "yyyy年MM月dd日 hh时mm分ss秒");
	// }

	// /**
	// * 获取当前时间之后的day天是某一天(例如：2003年05月22日之后的23天是2003-06-14)
	// *
	// * @param day
	// * @return 当前时间之后的day天是某一天，以字符串格式返回,如2003-06-14
	// * @throws Exception
	// */
	//
	// public static String getAfterDateString(int day) throws Exception {
	// long onetimes = (long) 1 * 24 * 60 * 60 * 1000; // 一天的毫秒数
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
	 * 在某个日期上增加天数.
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
	 * 获取当前时间之后的day天是某一天(例如：2003年05月22日之后的23天是2003-06-14)
	 * 
	 * @param day
	 * @return 日期对象
	 * @throws Exception
	 */

	public static java.util.Date getAfterDate(int day) {
		long onetimes = (long) 1 * 24 * 60 * 60 * 1000; // 一天的毫秒数

		long now = System.currentTimeMillis();
		long after = now + onetimes * day;

		java.util.Date date = new java.util.Date(after);

		return date;

	}

	/**
	 * 获取当前时间之前的day天是某一天(例如：2003年05月22日之前的20天是2003-05-02)
	 * 
	 * @param day
	 * @return 当前时间之前的day天是某一天，以字符串格式返回,如2003-06-14
	 * @throws Exception
	 */

	public static String getBeforeDateString(int day) {
		long onetimes = (long) 1 * 24 * 60 * 60 * 1000; // 一天的毫秒数

		long now = System.currentTimeMillis();
		long after = now - onetimes * day;

		java.util.Date date = new java.util.Date(after);

		return format(date, "yyyy-MM-dd");

	}

	/**
	 * 获取当前时间之前的day天是某一天(例如：2003年05月22日之前的20天是2003-05-02)
	 * 
	 * @param day
	 * @return 日期对象
	 * @throws Exception
	 */

	public static java.util.Date getBeforeDate(int day) {
		long onetimes = (long) 1 * 24 * 60 * 60 * 1000; // 一天的毫秒数

		long now = System.currentTimeMillis();
		long after = now - onetimes * day;

		java.util.Date date = new java.util.Date(after);

		return date;

	}

	/**
	 * 在某个日期上增加月数.
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
	// * 对于给定的时间格式，形如：2006-2 2006-2-1返回当月的最后一天的日期2006-02-28；对于求第一天日期格式没必要
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
	// * 明天
	// *
	// * @return
	// */
	// public static Date tomorrow() {
	// return nextDate(new Date());
	// }

	// /**
	// * 昨天
	// *
	// * @return
	// */
	// public static Date yesterday() {
	// return preDate(new Date());
	// }

	/**
	 * 前一天
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
	// * 今年1月1日
	// *
	// * @return
	// */
	// public static Date thisYear() {
	// return parse(yyyy_FORMAT, yyyy_FORMAT.format(new Date()));
	// }

	// /**
	// * 明年1月1日
	// *
	// * @return
	// */
	// public static Date nextYear() {
	// int year = Integer.parseInt(yyyy_FORMAT.format(new Date()));
	// return parse(yyyy_FORMAT, year + 1 + "");
	// }

	// /**
	// * 解析如：“2007-08-24”的日期格式字符串
	// *
	// * @param date
	// * @return 如果格式不正确，返回null
	// */
	// public static Date parseDate(String date) {
	// return parse(DATE_FORMAT, date);
	// }

	// /**
	// * 解析如：“2007-08-24 10:07:23”的日期格式字符串
	// *
	// * @param datetime
	// * @return 如果格式不正确，返回null
	// */
	// public static Date parseDateTime(String datetime) {
	// return parse(DATE_TIME_FORMAT, datetime);
	// }

	/**
	 * 按给定的格式解析日期时间字符串
	 * 
	 * @param format
	 * @param datetime
	 * @return 如果格式不正确，返回null
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
		cal.set(Calendar.HOUR, Calendar.HOUR + (hour + 1)); // 把时间设置为当前时间-1小时，同理，也可以设置其他时间
		// cal.set(Calendar.MONTH, Calendar.MONTH - 1); // 当前月前一月
		oneHoursAgoTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(cal.getTime());// 获取到完整的时间
		return oneHoursAgoTime;
	}

	public static String beforeOneHourToNowDate(int hour) {
		Calendar calendar = Calendar.getInstance();
		/* HOUR_OF_DAY 指示一天中的小时 */
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
