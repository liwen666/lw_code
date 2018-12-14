package commons.utils;

/**
 * <p>Title: 字符串处理类</p>
 * <p>Description: 字符串处理，返回不同的值</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: 北京太极华青信息系统有限公司</p>
 * @author 
 * @version 1.0
 */

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;

/**
 * @author liyade
 * 
 * 更改所生成类型注释的模板为 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
public class StringUtil {
	/** null value for integer */
	public final static int INT_NULL = -911;

	private static Logger log = Logger.getLogger(StringUtil.class);

	private static HashMap hNum;

	private static String strVal[] = { "1", "2", "3", "4", "5", "6", "7", "8",
			"9", "0", "." };

	private static String flag[] = { "+", "-", " ", "＋", "－" };

	private static String block[] = { "+", ",", "(", ")" };

	private static HashMap strMap = new HashMap();
	static {
		hNum = new HashMap();
		for (int i = 0; i < strVal.length; i++) {
			hNum.put(strVal[i], strVal[i]);
		}

		for (int i = 0; i < flag.length; i++) {
			strMap.put(flag[i], flag[i]);
		}
	}

	/**
	 * check null value
	 * 
	 * @param p1
	 * @return
	 */
	public static boolean isNull(Object p1) {
		if (p1 == null)
			return true;
		if (p1 instanceof Integer) {
			if (((Integer) p1).intValue() == INT_NULL) {
				return true;
			}
		}

		if (p1 instanceof Double) {
			if (((Double) p1).intValue() == INT_NULL) {
				return true;
			}
		}

		if (p1 instanceof Float) {
			if (((Float) p1).intValue() == INT_NULL) {
				return true;
			}
		}
		return false;
	}

	public static boolean isEmpty(String p1) {
		return p1 == null ? true : p1.trim().length() <= 0;
	}

	/**
	 * 取出字符串中的数字
	 * 
	 * @param str
	 * @return
	 */
	public static int StringNum(String str) {

		StringBuffer nums = new StringBuffer(20);
		double ret = 0.0;
		char numStr[] = str.toCharArray();
		for (int i = 0; i < numStr.length; i++) {
			if (hNum.containsKey(String.valueOf(numStr[i]))) {
				nums.append(numStr[i]);
			}

		}

		try {
			ret = Double.parseDouble(nums.toString());

		} catch (Exception e) {
			log.info("StringUtil->StringNum(String str):" + e.toString());
			ret = 0.0;
		}

		return (int) Math.round(ret);

	}

	/**
	 * 字符集转换
	 * 
	 * @param str
	 * @return
	 */
	public static String iso2GBK(String str) {
		try {
			if (str != null) {
				str = new String(str.getBytes("ISO8859-1"), "GBK");
			} else {
				str = "";
			}
			return str;

		} catch (UnsupportedEncodingException e) {
			log.info("StringUtil->iso2GBK(String str):" + e.toString());
			return "error";
		}
	}

	/**
	 * 字符转数字
	 * 
	 * @param val
	 * @param def
	 * @return
	 */
	public static int str2int(String val, int def) {
		int temp;
		try {
			temp = Integer.parseInt(val);
			return temp;
		} catch (NumberFormatException e) {
			log
					.info("StringUtil->str2int(String val, int def):"
							+ e.toString());
			return def;
		}

	} // function

	/**
	 * 对字符串的空格处理
	 * 
	 * @param key
	 * @param set
	 * @return
	 */

	public static String space2dot(String key, HashSet set) {
		StringBuffer str = new StringBuffer(1024);
		str.append("'");
		StringTokenizer token = new StringTokenizer(key, " ");
		while (token.hasMoreTokens()) {
			String temp = token.nextToken();
			str.append(temp + "','");
			set.add(temp);
		}
		return str.toString().substring(0, str.toString().length() - 2);
	}

	/**
	 * 对全文检索的全角过滤成半角
	 * 
	 * @param key
	 * @return
	 */
	public static String filterADD(String key) {
		if (key == null) {
			return "";
		} // ＋－

		if (key.indexOf("+") == -1 && key.indexOf("＋") == -1
				&& key.indexOf("－") == -1 && key.indexOf("　") == -1) {
			return key;
		}

		StringBuffer str = new StringBuffer(1024);
		char temp[] = key.toCharArray();
		for (int i = 0; i < temp.length; i++) {
			if (temp[i] == '－') {
				str.append("-");
				continue;
			}
			if (temp[i] == '　') {
				str.append(" ");
				continue;
			}

			if (temp[i] != '+' && temp[i] != '＋') {
				str.append(temp[i]);
			}
		}
		return str.toString();
	} // function

	/**
	 * 去除回车换行符
	 * 
	 * @param str
	 * @return
	 */
	public static String replaceRN(String str) {
		str = StringUtil.gotNull(str).replaceAll("\n", "");
		str = str.replaceAll("\r", "");
		return str;
	}

	/**
	 * 对加密的字符串处理
	 * 
	 * @param str
	 * @return
	 */
	public static int[] String2Divide(String str) {
		// StringTokenizer val = new StringTokenizer(str,token);
		char[] val = str.toCharArray();
		int[] temp = new int[val.length];
		for (int i = 0; i < val.length; i++) {
			temp[i] = (int) val[i];
		}

		return temp;
	}

	/**
	 * 对加密的字符串处理
	 * 
	 * @param str
	 * @return
	 */
	public static int[] String2Divide(String str, String sp) {
		// StringTokenizer val = new StringTokenizer(str,token);
		String val[] = str.split(sp);
		int[] temp = new int[val.length];
		for (int i = 0; i < val.length; i++) {
			if (val[i] == null || val[i].trim().equals("")) {
				temp[i] = 0;
			} else {
				temp[i] = Integer.parseInt(val[i]);
			}

		}

		return temp;
	}

	/**
	 * 对全文检索的*进行处理
	 * 
	 * @param str
	 * @return
	 */
	public static String processStr(String str) {
		if (str == null || str.equals("")) {
			return "";
		}

		str += " ";
		StringBuffer strFinal = new StringBuffer(100);
		char charArray[] = str.toCharArray();
		boolean change = true;
		StringBuffer temp = null;
		for (int i = 0; i < charArray.length; i++) {

			if (i == 0) {
				temp = new StringBuffer(20);
			}
			if (strMap.containsKey(String.valueOf(charArray[i]))) {

				if (temp.length() == 1) {
					strFinal.append(temp.toString()).append("*").append(
							charArray[i]);
				} else {
					strFinal.append(temp.toString()).append(charArray[i]);
				}
				temp = new StringBuffer(20);
			} else {
				temp.append(charArray[i]);
			}
		}

		return strFinal.toString();
	}

	/**
	 * 字符替换
	 * 
	 * @return
	 */
	public static String replaceStr(String str) {
		String srcStr[] = { "+", "*" };
		String desStr[] = { " or ", " and " };

		String changeStr = str;

		for (int i = 0; i < srcStr.length; i++) {

			StringBuffer tmpStr = new StringBuffer();
			int start = 0;
			String temp = srcStr[i];
			int end = changeStr.indexOf(temp, start);

			while (end > 0) {
				tmpStr.append(changeStr.substring(start, end));
				tmpStr.append(desStr[i]);
				start = end + 1;
				end = changeStr.indexOf(temp, start);
			}
			tmpStr.append(changeStr.substring(start));
			changeStr = tmpStr.toString();
		}
		return changeStr;
	}

	public static String getStrHead(String str, String split) {
		int len = str.indexOf(split);
		if (len == -1) {
			return str;
		}
		return str.substring(0, len);
	}

	public static long getLong(String num) {
		try {
			return Long.parseLong(num);
		} catch (Exception e) {
			log.info("StringUtil->getLong(String num) :" + e.toString());
			return 0;
		}
	}

	public static int getInt(String num) {
		try {
			if (num == null || num.equals("")) {
				return 0;
			}
			return Integer.parseInt(num);
		} catch (Exception e) {
			log.info("StringUtil->getInt(String num):" + e.toString());
			return 0;
		}
	}

	public static String getString(String str, String defaultVal) {
		if (str == null) {
			return defaultVal;
		} else {
			return str;
		}
	}

	public static String getLinkStr(String ids[], String split) {
		StringBuffer str = new StringBuffer(1024);
		for (int i = 0; i < ids.length; i++) {
			str.append("'" + ids[i] + "'").append(split);
		}
		int len = str.length();
		if (len > 0) {
			return str.toString().substring(0, len - 1);
		}
		return str.toString();
	}

	public static String getTagStr(String tagStr) {
		if (tagStr == null || tagStr.indexOf("{[") == -1
				|| tagStr.indexOf("]}") == -1) {
			return "";
		}

		tagStr = tagStr.substring(tagStr.indexOf("{[") + 2, tagStr
				.indexOf("]}"));
		return tagStr;
	}

	/**
	 * 得到资串的位置
	 * 
	 * @param content
	 * @param title
	 * @return
	 */
	public static int getContentNum(String content, String title) {
		if (content == null || title == null) {
			return 0;
		}
		int index = content.indexOf(title);

		if (index != -1) {
			return index;
		}

		index = content.indexOf(title.toUpperCase());

		if (index != -1) {
			return index;
		}

		return 0;
	}

	/**
	 * 得到资串的位置
	 * 
	 * @param content
	 * @param title
	 * @return
	 */
	public static int getContentLastNum(String content, String title) {
		if (content == null || title == null) {
			return 0;
		}
		int index = content.lastIndexOf(title);

		if (index != -1) {
			return index;
		}

		index = content.lastIndexOf(title.toUpperCase());

		if (index != -1) {
			return index;
		}

		return 0;
	}

	/**
	 * 将主串中的旧串替为新串
	 * 
	 * @param mainString
	 * @param oldString
	 * @param newString
	 * @return
	 */
	public static String replaceString(String mainString, String oldString,
			String newString) {

		if (mainString == null) {
			return "";
		}
		if (oldString == null || oldString.length() == 0) {
			return mainString;
		}
		if (newString == null) {
			return mainString;
		}

		int i = mainString.lastIndexOf(oldString);

		if (i < 0) {
			return mainString;
		}

		StringBuffer mainSb = new StringBuffer(mainString);

		while (i >= 0) {
			mainSb.replace(i, i + oldString.length(), newString);

			i = mainString.lastIndexOf(oldString, i - 1);

		}
		return mainSb.toString();
	}

	/**
	 * 将主串中的子串替为新串
	 * 
	 * @param content
	 * @param subStr
	 * @param newStr
	 * @return
	 */
	public static String replaceSubStr(String content, String subStr,
			String newStr) {

		if (content == null) {
			return "";
		}
		if (subStr == null || subStr.length() == 0) {
			return content;
		}
		if (newStr == null) {
			return content;
		}

		int i = getStrBefore(content, subStr);

		int j = getStrAfter(content, subStr);

		if (i <= 0 || j <= 0) {
			return content;
		}

		StringBuffer mainSb = new StringBuffer(content);
		// System.out.println(i + "," + j);
		mainSb.replace(i, j, newStr);
		return mainSb.toString();
	}

	/**
	 * 
	 * @param content
	 * @param subStr
	 * @return
	 */
	public static int getStrBefore(String content, String subStr) {
		int intSub = getContentNum(content, subStr);
		String subContent = content.substring(0, intSub);

		for (int i = subContent.length() - 1; i > 0; i--) {
			if (subContent.charAt(i) == '=' || subContent.charAt(i) == '\"'
					|| subContent.charAt(i) == '\'') {

				return i + 1;
			}
		}
		return -1;
	}

	/**
	 * 
	 * @param content
	 * @param subStr
	 * @return
	 */
	public static int getStrAfter(String content, String subStr) {

		int intSub = getContentNum(content, subStr);
		String subContent = content.substring(intSub);

		for (int i = intSub; i < content.length() - 1; i++) {
			if (content.charAt(i) == '"' || content.charAt(i) == ' '
					|| content.charAt(i) == '\'') {
				return i;
			}
		}
		return -1;
	}

	/**
	 * 得到ｃｏｎｔｅｎｔ中ｖａｌ的位置数组
	 * 
	 * @param content
	 * @param val
	 * @return
	 */
	public static int[] getStringPlace(String content, String val) {
		HashSet set = new HashSet();
		int start = content.indexOf(val);

		while (start != -1) {
			set.add(new Integer(start));
			start = content.indexOf(val, start + 1);
		}
		Integer intStr[] = (Integer[]) set.toArray(new Integer[0]);
		int retInt[] = new int[intStr.length];
		for (int i = 0; i < intStr.length; i++) {
			retInt[i] = intStr[i].intValue();
		}
		return retInt;
	}

	/**
	 * 得到相近的两个字符串的距离
	 * 
	 * @param content
	 * @param val1
	 * @param val2
	 * @return
	 */
	public static int getKind(String content, String val1, String val2) {
		int first[] = getStringPlace(content, val1);
		int end[] = getStringPlace(content, val2);
		// System.out.println(first.length + "**" + end.length);
		if (first.length == 0 || end.length == 0) {
			return 0;
		}
		for (int i = 0; i < first.length; i++) {
			for (int j = 0; j < end.length; j++) {

				if (Math.abs(first[i] - end[j]) < 30) {
					return end[j];
				}
			}
		}
		return 0;

	}

	/**
	 * 得到之间的串
	 * 
	 * @param content
	 * @param val
	 * @return
	 */
	public static String getStrBetween(String content, String first, String end) {
		if (content == null) {
			return content;
		}

		int fir = content.indexOf(first);
		int en = content.indexOf(end);
		if (first == null || fir == -1) {
			return "";
		}

		if (end == null || en == -1) {
			return "";
		}
		return content.substring(fir + first.length(), en);
	}

	/**
	 * 得到content中的字符串集合
	 * 
	 * @param content
	 * @param first
	 * @param second
	 * @return
	 */
	public static String[] getStrings(String content, String first,
			String second) {
		ArrayList list = new ArrayList();
		int fir = content.indexOf(first);
		int en = content.indexOf(second);

		while (fir != -1 && en != -1) {
			String sub = content.substring(fir + first.length(), en);
			list.add(sub);
			content = content.substring(en + second.length());
			fir = content.indexOf(first);
			en = content.indexOf(second);
		}
		return (String[]) list.toArray(new String[0]);
	}

	/**
	 * 
	 * @param content
	 * @param first
	 * @param second
	 * @return
	 */
	public static String getStrs(String content, String first, String second) {
		String str[] = getStrings(content, first, second);

		if (str == null || str.length == 0) {
			return "";
		}

		StringBuffer strVal = new StringBuffer(200);
		for (int i = 0; i < str.length; i++) {
			strVal.append(str[i]).append(",");
		}
		return strVal.toString().substring(0, strVal.length() - 1);
	}

	public static String getBraceStr(String content, String sp) {
		int divide = content.indexOf(sp);
		if (divide == -1) {
			return "error";
		}
		String first = content.substring(0, divide);
		String second = content.substring(divide + 1);
		int before = getContentLastNum(first, "<span");
		int after = getContentNum(second, "</span>");
		if (before == -1 || after == -1) {
			return "error";
		}

		return content.substring(before, divide + after + "</span>".length()
				+ 1);
	}

	/**
	 * 计算一个字符串中某一个串出现的次数的方法
	 * 
	 * @param line
	 * @param oldString
	 * @return
	 */
	public static final int countNum(String line, String oldString) {
		int counter = 0;
		if (line == null) {
			return 0;
		}
		int i = 0;
		if (((i = line.indexOf(oldString, i)) >= 0)
				|| ((i = line.indexOf(oldString.toLowerCase(), i)) >= 0)) {
			counter++;
			char[] line2 = line.toCharArray();
			int oLength = oldString.length();
			StringBuffer buf = new StringBuffer(line2.length);
			i += oLength;
			int j = i;
			while ((i = line.indexOf(oldString, i)) > 0) {
				counter++;
				i += oLength;
				j = i;
			}
			return counter;
		}
		return counter;
	}

	public static String[] getDivideStr(String content, String split) {
		// Resource.PageNo
		if (content == null) {
			return new String[0];
		}

		int index = content.indexOf(split);
		if (index == -1) {
			String strVal[] = new String[1];
			strVal[0] = content;
			return strVal;
		}
		int end = 0;
		int first = 0;
		ArrayList list = new ArrayList();

		while (index != -1) {
			end = index;
			String temp = content.substring(first, end);
			list.add(temp);
			index = content.indexOf(split, end + 1);
			first = end + split.length();
			end = index;
		}

		String tmp = content.substring(first);
		if (tmp != null && !tmp.trim().equals("")) {
			list.add(tmp);

		}
		return (String[]) list.toArray(new String[0]);
	}

	/**
	 * getSingleTag(lyd,"<body")
	 * 
	 * @param content
	 * @param tag
	 * @return
	 */

	public static String getSingleTag(String content, String tag) {
		if (tag == null) {
			return "";
		}

		int len = content.indexOf(tag);
		if (len == -1) {
			return "";
		}
		StringBuffer str = new StringBuffer(100);
		for (int i = len; i < content.length(); i++) {

			char c = content.charAt(i);
			if (c == '>') {
				str.append(c);
				break;
			}
			str.append(c);
		}
		return str.toString();
	}

	/**
	 * pengl
	 * 
	 * @param content
	 *            :被查找的内容
	 * @param starttag
	 *            :html标志 如："<body"
	 * @param endtag
	 *            :html标志 如：">"
	 * @param myfilter
	 *            :返回标志 0: 取从网页开始 到 <body..> 1: 取 <body ..> 2: 取 从<body ..> 到
	 *            网页尾
	 * @return
	 */
	public static String getInterceptHTML(String content, String starttag,
			String endtag, int myfilter) {
		String returnstr = "";

		if (content == null || starttag == null || endtag == null) {
			return returnstr;
		}
		int beginIndex = 0;
		int endIndex = 0;
		int len_tag = content.indexOf(starttag);
		int len_tag1;
		if (len_tag == -1) {
			return returnstr;
		}
		len_tag1 = content.indexOf(endtag, len_tag);
		endIndex = len_tag1 + 1;
		if (myfilter == 1) {
			beginIndex = len_tag;
		}
		if (myfilter == 2) {
			beginIndex = len_tag;
			endIndex = content.length();
		}
		returnstr = content.substring(beginIndex, endIndex);

		return returnstr;
	}

	/**
	 * pengl (忽略大小写)
	 * 
	 * @param content
	 *            :被查找的内容
	 * @param starttag
	 *            :html标志 如："<body"
	 * @param endtag
	 *            :html标志 如：">"
	 * @param myfilter
	 *            :返回标志 0: 取从网页开始 到 <body..> 1: 取 <body ..> 2: 取 从<body ..> 到
	 *            网页尾
	 * @return
	 */

	public static String getInterceptHTMLIgnoreCase(String content,
			String starttag, String endtag, int myfilter) {
		if (content == null || starttag == null || endtag == null) {
			return "";
		}

		String returnstr = getInterceptHTML(content, starttag, endtag, myfilter);

		if (!"".equals(returnstr)) {
			return returnstr;
		}

		String temp_tag = starttag.toLowerCase();
		int beginIndex = 0;
		int endIndex = 0;
		int len_tag = content.indexOf(starttag);
		int len_tag1;
		len_tag = content.indexOf(temp_tag);

		if (len_tag == -1) {
			temp_tag = starttag.toUpperCase();
			len_tag = content.indexOf(temp_tag);
			if (len_tag == -1) {
				len_tag = content.toUpperCase().indexOf(temp_tag);
				if (len_tag == -1) {
					return returnstr;
				}
			}
		}
		len_tag1 = content.indexOf(endtag, len_tag);
		endIndex = len_tag1 + 1;
		if (myfilter == 1) {
			beginIndex = len_tag;
		}
		if (myfilter == 2) {
			beginIndex = len_tag;
			endIndex = content.length();
		}

		returnstr = content.substring(beginIndex, endIndex);
		return returnstr;
	}

	/**
	 * 去掉NULL值
	 * 
	 * @param nullstring
	 *            String
	 * @return String
	 */
	public static String gotNull(String nullstring) {
		if (null == nullstring) {
			nullstring = "";
		} else if ("null".equals(nullstring.toLowerCase())) {
			nullstring = "";
		} else {
			nullstring = nullstring.trim();
		}
		return nullstring;
	}

	/**
	 * 将HashMap对象转化为ArrayList对象的方法
	 * 
	 * @param map
	 *            HashMap
	 * @return ArrayList
	 */
	public static ArrayList HashMap2ArrayList(HashMap map) {
		ArrayList list = new ArrayList();
		Iterator it = map.keySet().iterator();
		while (it.hasNext()) {
			String key = (String) it.next();
			list.add(map.get(key));
		}
		return list;
	}

	/**
	 * 将Set对象转化成ArrayList对象的方法
	 * 
	 * @param map
	 *            Set
	 * @return ArrayList
	 */
	public static ArrayList Set2ArrayList(Set map) {
		ArrayList list = new ArrayList();
		Iterator it = map.iterator();
		while (it.hasNext()) {
			list.add(it.next());
		}
		return list;
	}

	/**
	 * 将字符串数组转化成ArrayList的方法
	 * 
	 * @param str
	 *            String[]
	 * @return ArrayList
	 */
	public static ArrayList StringArry2List(String[] str) {
		ArrayList list = new ArrayList();
		for (int i = 0; i <= str.length - 1; i++) {
			list.add(str[i]);
		}
		return list;
	}

	/**
	 * 格式化输出的显示内容
	 * 
	 * @param list
	 *            ArrayList:要显示的数据集合
	 * @param rowsNum
	 *            int ：列数
	 * @return ArrayList
	 */
	public static ArrayList FormatForm(ArrayList list, int rowsNum) {
		// 初始化行集合
		ArrayList cols = new ArrayList();
		if (list != null) {
			ArrayList rows = null;
			int num = 0;
			for (int i = 0; i <= list.size() - 1; i++) {
				if (num == 0) {
					// 初始化列集合
					rows = new ArrayList();
				}
				num++;
				rows.add(list.get(i));
				if (num == rowsNum || i == list.size() - 1) {
					num = 0;
					cols.add(rows);
				}
			}
		}
		return cols;
	}

	/**
	 * 格式化输出的显示内容
	 * 
	 * @param String
	 *            s:要显示的数据集合
	 * @param num
	 *            int ：每行显示的字数
	 * @return String s
	 */
	public static String showFormat(String s, int num) {
		int from = 0;
		int to = 0;
		int count = 0;
		String temp = "";
		StringBuffer sb = new StringBuffer();
		if (s.length() > num) {
			count = s.length() / num;

			for (int i = 0; i < count; i++) {
				from = i * num;
				to = from + num;
				temp = s.substring(from, to);
				sb.append(temp).append("\n");
			}
			temp = s.substring(to, s.length());
			sb.append(temp).append("\n");
		} else {
			return s;
		}
		return sb.toString();
	}

	/**
	 * 根据二维数组获取小计对象
	 * 
	 * @param data
	 *            String[][]
	 * @return HasMap
	 */
	public static HashMap getCol_RowTotalNum(String[][] data) {
		int row = data.length;
		int col = data[0].length;
		ArrayList colarry = new ArrayList();
		ArrayList rowarry = new ArrayList();
		ArrayList colarryStr = new ArrayList();
		ArrayList rowarryStr = new ArrayList();
		HashMap returnData = new HashMap();

		// 获取行统计数据集合
		for (int i = 0; i < row; i++) {
			int rownum = 0;
			String rowstr = "";
			for (int j = 0; j < col; j++) {
				rownum += Integer.parseInt(data[i][j] == null ? "0"
						: data[i][j]);
				rowstr += ","
						+ Integer.parseInt(data[i][j] == null ? "0"
								: data[i][j]);
			}
			rowarry.add(rownum + "");
			rowarryStr.add(rowstr);
		}
		// 获取列统计数据集合

		for (int i = 0; i < col; i++) {
			int colnum = 0;
			String colstr = "";
			for (int j = 0; j < row; j++) {
				colnum += Integer.parseInt(data[j][i] == null ? "0"
						: data[j][i]);
				colstr += ","
						+ Integer.parseInt(data[j][i] == null ? "0"
								: data[j][i]);
			}
			colarry.add(colnum + "");
			colarryStr.add(colstr);
		}
		returnData.put("rowarry", rowarry);
		returnData.put("colarry", colarry);
		returnData.put("rowarryStr", rowarryStr);
		returnData.put("colarryStr", colarryStr);
		return returnData;

	}

	public static String[] split(String source, String delim) {
		String wordLists[];
		if (source == null) {
			wordLists = new String[1];
			wordLists[0] = source;
			return wordLists;
		}
		if (delim == null)
			delim = ",";
		StringTokenizer st = new StringTokenizer(source, delim);
		int total = st.countTokens();
		wordLists = new String[total];
		for (int i = 0; i < total; i++)
			wordLists[i] = st.nextToken();

		return wordLists;
	}

	public static String[] split(String source, char delim) {
		return split(source, String.valueOf(delim));
	}

	public static String[] split(String source) {
		return split(source, ",");
	}

	public static String getReplaceString(String prefix, String source,
			String values[]) {
		String result = source;
		if (source == null || values == null || values.length < 1)
			return source;
		if (prefix == null)
			prefix = "%";
		for (int i = 0; i < values.length; i++) {
			String argument = prefix + Integer.toString(i + 1);
			int index = result.indexOf(argument);
			if (index == -1)
				continue;
			String temp = result.substring(0, index);
			if (i < values.length)
				temp = temp + values[i];
			else
				temp = temp + values[values.length - 1];
			temp = temp + result.substring(index + 2);
			result = temp;
		}

		return result;
	}

	public static String getReplaceString(String source, String values[]) {
		return getReplaceString("%", source, values);
	}

	public static boolean contains(String strings[], String string,
			boolean caseSensitive) {
		for (int i = 0; i < strings.length; i++) {
			if (caseSensitive) {
				if (strings[i].equals(string))
					return true;
				continue;
			}
			if (strings[i].equalsIgnoreCase(string))
				return true;
		}

		return false;
	}

	public static boolean contains(String strings[], String string) {
		return contains(strings, string, true);
	}

	public static boolean containsIgnoreCase(String strings[], String string) {
		return contains(strings, string, false);
	}

	public static String combineStringArray(String array[], String delim) {
		int length = array.length - 1;
		if (delim == null)
			delim = "";
		StringBuffer result = new StringBuffer(length * 8);
		for (int i = 0; i < length; i++) {
			result.append(array[i]);
			result.append(delim);
		}

		result.append(array[length]);
		return result.toString();
	}

	public static String fillString(char c, int length) {
		String ret = "";
		for (int i = 0; i < length; i++)
			ret = ret + c;

		return ret;
	}

	public static String trimLeft(String value) {
		String result = value;
		if (result == null)
			return result;
		char ch[] = result.toCharArray();
		int index = -1;
		for (int i = 0; i < ch.length && Character.isWhitespace(ch[i]); i++)
			index = i;

		if (index != -1)
			result = result.substring(index + 1);
		return result;
	}

	public static String trimRight(String value) {
		String result = value;
		if (result == null)
			return result;
		char ch[] = result.toCharArray();
		int endIndex = -1;
		for (int i = ch.length - 1; i > -1 && Character.isWhitespace(ch[i]); i--)
			endIndex = i;

		if (endIndex != -1)
			result = result.substring(0, endIndex);
		return result;
	}

	public static String escapeCharacter(String source, HashMap escapeCharMap) {
		if (source == null || source.length() == 0)
			return source;
		if (escapeCharMap.size() == 0)
			return source;
		StringBuffer sb = new StringBuffer();
		StringCharacterIterator sci = new StringCharacterIterator(source);
		for (char c = sci.first(); c != '\uFFFF'; c = sci.next()) {
			String character = String.valueOf(c);
			if (escapeCharMap.containsKey(character))
				character = (String) escapeCharMap.get(character);
			sb.append(character);
		}

		return sb.toString();
	}

	public static int getByteLength(String source) {
		int len = 0;
		for (int i = 0; i < source.length(); i++) {
			char c = source.charAt(i);
			int highByte = c >>> 8;
			len += highByte != 0 ? 2 : 1;
		}

		return len;
	}

	public static int stringToInt(String source) throws NumberFormatException {
		int i = Integer.parseInt(source);
		return i;
	}

	public static long stringToLong(String source) throws NumberFormatException {
		long l = Long.parseLong(source);
		return l;
	}

	public static boolean stringToBoolean(String source) {
		boolean value = source.equals("1");
		if (!value && source.toUpperCase().equals("TRUE"))
			return true;
		else
			return value;
	}

	public static Date stringToDate(String source) throws NumberFormatException {
		Date time = new Date(Long.parseLong(source));
		return time;
	}

	public static String replaceAll(String source, String target, String str) {
		for (int i = source.indexOf(target, 0); i < source.length() && i > -1; i = source
				.indexOf(target, i + str.length()))
			source = source.substring(0, i) + str
					+ source.substring(i + target.length());

		return source;
	}

	public static String getDecimalString(double dbValue) {

		DecimalFormat aaa = new DecimalFormat("##.##");
		StringBuffer sb = new StringBuffer();
		aaa.format(new BigDecimal(dbValue), sb, new FieldPosition(0));

		return sb.toString();

	}

	public static String getDecimalString(double dbValue, int length) {
		String strL = "";
		for (int i = 0; i < length; i++) {
			strL = strL + "#";
		}
		if(!"".equals(strL)){
			strL ="."+ strL; 
		}
		DecimalFormat aaa = new DecimalFormat("##" + strL);
		StringBuffer sb = new StringBuffer();
		aaa.format(new BigDecimal(dbValue), sb, new FieldPosition(0));

		return sb.toString();

	}

	/**
	 * 将double类型转换成货币类型，截掉人民币符号，并以string返回，格式为1,234.55
	 * 
	 * @param cent
	 * @return
	 */
	public static String doubleToCurrency(double cent) {
		NumberFormat chFormat = NumberFormat.getCurrencyInstance(Locale.CHINA);
		String str = chFormat.format(cent);
		if (str.indexOf("-") != -1) {
			str = "-" + str.substring(2);
		} else {
			str = str.substring(1);
		}
		return str;
	}
	/**
	 * 将double类型转换成货币类型，截掉人民币符号，并以string返回，格式为1234.55
	 * 
	 * @param cent
	 * @return
	 */
	public static String floatToCurrency(float fSum) {
		NumberFormat chFormat = NumberFormat.getCurrencyInstance(Locale.CHINA);
		String str = chFormat.format(fSum);
		if (str.indexOf("-") != -1) {
			str = "-" + str.substring(2);
		} else {
			str = str.substring(1);
		}
		str=str.replaceAll(",", "");
		return str;
	}
	
	public static void main(String ard[]){
		StringUtil.getDecimalString(2.34578,4);
		System.out.println(StringUtil.doubleToCurrency(1234.55));
		System.out.println(StringUtil.floatToCurrency(234578));
	}

} // class
