package commons.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

public class DateHelp {

	private static Logger log = Logger.getLogger(DateHelp.class);

	// private static ResourceBundle resource = ResourceBundle.getBundle(
	// "resources.date", Locale.CHINA);

	private DateHelp() {
	}

	/** 获取日期格式 yyyy/MM/dd */
	public static final int PATTERN_1 = 1;

	/** 获取日期格式 yyyy/MM/dd HH:mm:ss */
	public static final int PATTERN_2 = 2;

	/** 获取日期格式 yyyy/MM/dd HH:mm:ss:sss */
	public static final int PATTERN_3 = 3;

	/** 获取日期格式 yyyy-MM-dd */
	public static final int PATTERN_4 = 4;

	/** 获取日期格式 yyyy-MM-dd HH:mm:ss */
	public static final int PATTERN_5 = 5;

	/** 获取日期格式 yyyy-MM-dd HH:mm:ss:sss */
	public static final int PATTERN_6 = 6;

	/** 获取年 */
	public static final int PATTERN_7 = 7;

	/** 获取年终的月份 */
	public static final int PATTERN_8 = 8;

	/** 获取年终的周数 */
	public static final int PATTERN_9 = 9;

	/** 获取月份中的周数 */
	public static final int PATTERN_10 = 10;

	/** 获取年中的天数 */
	public static final int PATTERN_11 = 11;

	/** 获取月份中的天数 */
	public static final int PATTERN_12 = 12;

	/** 获取月份中的星期 */
	public static final int PATTERN_13 = 13;

	/** 获取星期中的天数 */
	public static final int PATTERN_14 = 14;

	/** 获取一天中的小时数(0-23) */
	public static final int PATTERN_15 = 15;

	/** 获取一天中的小时数(1-24) */
	public static final int PATTERN_16 = 16;

	/** 获取am/pm 中的小时数(0-11) */
	public static final int PATTERN_17 = 17;

	/** 获取am/pm 中的小时数(1-11) */
	public static final int PATTERN_18 = 18;

	/** 获取小时中的分钟数 */
	public static final int PATTERN_19 = 19;

	/** 获取分钟中的秒数 */
	public static final int PATTERN_20 = 20;

	/** 获取毫秒数 */
	public static final int PATTERN_21 = 21;

	/**
	 * 
	 * @param date:
	 *            格式化的日期, 如果日期为null, 则代表当前日期
	 * @param pattern:
	 *            格式化的日期模式
	 * @return: 返回字符串日期的格式.如果发生异常或格式不存在返回null
	 */
	public static String getStringDate(Date date, int pattern) {
		String strDate = null;
		SimpleDateFormat sdf = new SimpleDateFormat();
		String errMsg = null;

		if (date == null)
			date = new Date();

		try {
			switch (pattern) {
			case PATTERN_1:
				sdf.applyPattern("yyyy/MM/dd");
				break;
			case PATTERN_2:
				sdf.applyPattern("yyyy/MM/dd HH:mm:ss");
				break;
			case PATTERN_3:
				sdf.applyPattern("yyyy/MM/dd HH:mm:ss:sss");
				break;
			case PATTERN_4:
				sdf.applyPattern("yyyy-MM-dd");
				break;
			case PATTERN_5:
				sdf.applyPattern("yyyy-MM-dd HH:mm:ss");
				break;
			case PATTERN_6:
				sdf.applyPattern("yyyy-MM-dd HH:mm:ss:sss");
				break;
			case PATTERN_7:
				sdf.applyPattern("y");
				break;
			case PATTERN_8:
				sdf.applyPattern("M");
				break;
			case PATTERN_9:
				sdf.applyPattern("w");
				break;
			case PATTERN_10:
				sdf.applyPattern("W");
				break;
			case PATTERN_11:
				sdf.applyPattern("D");
				break;
			case PATTERN_12:
				sdf.applyPattern("d");
				strDate = sdf.format(date);
				break;
			case PATTERN_13:
				sdf.applyPattern("F");
				break;
			case PATTERN_14:
				sdf.applyPattern("E");
				break;
			case PATTERN_15:
				sdf.applyPattern("H");
				break;
			case PATTERN_16:
				sdf.applyPattern("k");
				break;
			case PATTERN_17:
				sdf.applyPattern("K");
				break;
			case PATTERN_18:
				sdf.applyPattern("h");
				break;
			case PATTERN_19:
				sdf.applyPattern("m");
				break;
			case PATTERN_20:
				sdf.applyPattern("s");
				break;
			case PATTERN_21:
				sdf.applyPattern("S");
				break;
			default:
				// errMsg = resource.getString("format_not_exist");
				log.info(errMsg);
				break;
			}

			strDate = sdf.format(date);

			sdf = null;
		} catch (Exception e) {
			strDate = null;
			// errMsg = resource.getString("date_convert_string_error");
			log.info(errMsg + "-->>" + e.getMessage());
		}

		return strDate;
	}

	/**
	 * 
	 * @param pattern:
	 *            转换成当前日期的格式
	 * @return: 返回字符串当前日期格式
	 */
	public static String getStringNowDate(int pattern) {
		return getStringDate(null, pattern);
	}

	/**
	 * 
	 * @param date:
	 *            格式化的日期, 如果日期为null, 则代表当前日期
	 * @param pattern:
	 *            格式化日期的字符串模式, 例如:yyyy/MM/dd
	 * @return 返回字符串日期的格式.如果发生异常或格式不存在返回null
	 */
	public static String getStringDate(Date date, String pattern) {
		String errMsg = "";
		try {
			SimpleDateFormat sdf = new SimpleDateFormat();
			sdf.applyPattern(pattern);
			if (date == null)
				date = new Date();

			String strDate = sdf.format(date);

			sdf = null;
			return strDate;
		} catch (Exception e) {
			// errMsg = resource.getString("date_convert_string_error");
			log.info(errMsg + "-->>" + e.getMessage());
			return null;
		}
	}

	/**
	 * 
	 * @param pattern:
	 *            格式化日期的字符串模式, 例如:yyyy/MM/dd
	 * @return 返回字符串日期的格式.如果发生异常或格式不存在返回null
	 */
	public static String getStringNowDate(String pattern) {
		return getStringDate(null, pattern);
	}

	/**
	 * 
	 * @param strDate:
	 *            字符串日期
	 * @param pattern:
	 *            转换的格式, 字符串日期要与转换的格式相匹配
	 * @return 返回把字符串日期转换成日期, 如果发生异常返回null
	 */
	public static Date getDate(String strDate, String pattern)
			throws ParseException {
		String errMsg = "";
		try {
			SimpleDateFormat sdf = new SimpleDateFormat();
			sdf.applyPattern(pattern);
			Date date = sdf.parse(strDate);

			sdf = null;
			return date;
		} catch (Exception e) {
			// errMsg = resource.getString("string_convert_date_error");
			log.info(errMsg + "-->>" + e.getMessage());
		}

		return null;
	}

	/**
	 * 
	 * @param date:
	 *            字符串日期
	 * @param pattern:
	 *            字符串格式,字符串格式与日期匹配
	 * @return 返回日期加一天后的日期
	 */
	public static Date dateAddA(String strDate, String pattern)
			throws ParseException {
		Date date = getDate(strDate, pattern);
		if (date == null) {
			throw new ParseException("", 1);
		}

		return dateAddSub(date, '+');
	}

	/**
	 * 
	 * @param date:
	 *            日期
	 * @return 返回日期加一天后的日期
	 * @throws Exception
	 */
	public static Date dateAddA(Date date) throws Exception {
		if (date == null) {
			throw new Exception("");
		}

		return dateAddSub(date, '+');
	}

	/**
	 * 
	 * @param date:
	 *            字符串日期
	 * @param pattern:
	 *            字符串格式,字符串格式与日期匹配
	 * @return 返回日期减一天后的日期
	 */
	public static Date dateSubA(String strDate, String pattern)
			throws ParseException {
		Date date = getDate(strDate, pattern);
		if (date == null) {
			throw new ParseException("", 1);
		}

		return dateAddSub(date, '-');
	}

	/**
	 * 
	 * @param date:
	 *            日期
	 * @return 返回日期减一天后的日期
	 * @throws Exception
	 */
	public static Date dateSubA(Date date) throws Exception {
		if (date == null) {
			throw new Exception("");
		}

		return dateAddSub(date, '-');
	}

	private static Date dateAddSub(Date date, char ch) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		if (ch == '+') {
			calendar.set(Calendar.DAY_OF_YEAR, calendar
					.get(Calendar.DAY_OF_YEAR) + 1);
		} else if (ch == '-') {
			calendar.set(Calendar.DAY_OF_YEAR, calendar
					.get(Calendar.DAY_OF_YEAR) - 1);
		}

		return calendar.getTime();
	}

	public static Date getMyDate(String key) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			// Date date = new Date(Long.parseLong(key));
			Date date = format.parse(key);
			return format.parse(key);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			log.info("DateUtil->getDate(String key):" + e.toString());
			return null;
		}
	}

	public static void main(String[] args) throws Exception {
		String sBeginDate = "2009-09-01";
		String sEndDate = "2010-01-03";
		ArrayList arrRet = new ArrayList();
		while (!(sBeginDate.equals(sEndDate))) {
			Date dDate = dateAddA(getMyDate(sBeginDate));
			String sDateTmp = getStringDate(dDate, 14);
			if (!("星期六".equals(sDateTmp)) && (!"星期日".equals(sDateTmp))) {
				arrRet.add(sDateTmp + "==" + getStringDate(dDate, 4));
			}
			sBeginDate = getStringDate(dDate, 4);
		}
		for (int i = 0; i < arrRet.size(); i++) {
			System.out.println("..." + arrRet.get(i));
		}
		// System.out.println(getStringDate(dateAddA(new Date()),
		// DateHelp.PATTERN_1));
	}

}
