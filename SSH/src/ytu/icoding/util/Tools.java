package ytu.icoding.util;


import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;



/**
 * 自定义工具类 包含的方法全部为静态方法
 * 
 * @author xuejt
 * 
 */
public class Tools {
	/**
	 * 大写第一个字母
	 * 
	 * @param source
	 * @return the String
	 */
	public static String capitalize(String source) {
		if ((source == null) || (source.length() == 0))
			return source;
		if (source.length() == 1)
			return source.toUpperCase();
		return source.toUpperCase().charAt(0) + source.substring(1);
	}

	
	
	/**
	 * 将Timestamp类型数据转换为'YYYY-MM-DD'
	 * 
	 * @param Timestamp
	 * @return String
	 */
	public static String convertTS(Timestamp ts) {
		return ts.toString().substring(0, ts.toString().indexOf(" "));
	}

	/**
	 * 格式化双精度浮点数，保留小数点后两位
	 * 
	 * @param num
	 * @return String
	 */
	public static String fmtDouble2(double num) {
		String result = "0.00";
		try {
			DecimalFormat nf = (DecimalFormat) NumberFormat.getNumberInstance();
			nf.applyPattern("###0.00");
			result = nf.format(num);
		} catch (Exception e) {
		}
		return result;
	}

	/**
	 * 把双精度浮点数转换为字符串
	 * 
	 * @param num
	 * @return
	 */
	public static String fmtDouble(double num) {
		String result = "0";
		try {
			result = String.valueOf(BigDecimal.valueOf(num));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 格式化时间，yyyy-MM-dd HH:mm:ss
	 * 
	 * @param time
	 * @return String
	 */
	public static String fmtTime(Timestamp time) {
		String result = "";
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			result = sdf.format(time);
		} catch (Exception e) {
		}
		return result;
	}

	/**
	 * 格式化日期，yyyy-MM-dd
	 * 
	 * @param time
	 * @return String
	 */
	public static String fmtDate(Timestamp time) {
		String result = "";
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			result = sdf.format(time);
		} catch (Exception e) {
		}
		return result;
	}

	/**
	 * 格式化日期，HH:mm:ss
	 * 
	 * @param time
	 * @return String
	 */
	public static String fmtOnlyTime(Timestamp time) {
		String result = "";
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			result = sdf.format(time);
		} catch (Exception e) {
		}
		return result;
	}

	/**
	 * 格式化时间，yyyy-MM-dd HH:mm:ss
	 * 
	 * @param time
	 * @return String
	 */
	public static String fmtTime(Date time) {
		String result = "";
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			result = sdf.format(time);
		} catch (Exception e) {
		}
		return result;
	}

	/**
	 * 格式化日期，yyyy-MM-dd
	 * 
	 * @param time
	 * @return String
	 */
	public static String fmtDate(java.sql.Date time) {
		String result = "";
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			result = sdf.format(time);
		} catch (Exception e) {
		}
		return result;
	}

	/**
	 * 格式化时间，yyyy-MM-dd HH:mm:ss
	 * 
	 * @param time
	 * @return String
	 */
	public static String fmtTime(java.util.Date time) {
		String result = "";
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			result = sdf.format(time);
		} catch (Exception e) {
		}
		return result;
	}

	/**
	 * 格式化日期，yyyy-MM-dd
	 * 
	 * @param time
	 * @return String
	 */
	public static String fmtDate(java.util.Date time) {
		String result = "";
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			result = sdf.format(time);
		} catch (Exception e) {
		}
		return result;
	}

	/**
	 * 将字符串转换成int
	 * 
	 * @param str
	 * @return int
	 */
	public static int strToInt(String str) {
		return strToInt(str, -1000);
	}

	/**
	 * 将字符串转换成int
	 * 
	 * @param str
	 * @param defaultV
	 *            出现异常后 默认值
	 * @return
	 */
	public static int strToInt(String str, int defaultV) {
		int result = defaultV;
		try {
			if (str != null && str.length() != 0)
				result = Integer.parseInt(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 将字符串转换成long
	 * 
	 * @param str
	 * @return int
	 */
	public static long strToLong(String str) {
		return strToLong(str, -1000);
	}

	/**
	 * 将字符串转换成long
	 * 
	 * @param str
	 * @param defaultV
	 *            出现异常后 默认值
	 * @return
	 */
	public static long strToLong(String str, long defaultV) {
		long result = defaultV;
		try {
			if (str != null && str.length() != 0){
				if(str.endsWith("L") || str.endsWith("l")){
					str = str.substring(0,str.length()-1);
				}
				result = Long.parseLong(str);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 将字符串转换成double
	 * 
	 * @param str
	 * @param defaultValue
	 *            默认值
	 * @return double
	 */
	public static double strToDouble(String str, double defaultValue) {
		double result = defaultValue;
		try {
			if (str != null && str.length() != 0)
				result = Double.parseDouble(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 将字符串转换成float
	 * 
	 * @param str
	 * @return float 如果转换错误则返回 0
	 */
	public static float strToFloat(String str) {
		return strToFloat(str, 0f);
	}

	/**
	 * 将字符串转换成float
	 * 
	 * @param str
	 * @param defaultValue
	 *            默认值
	 * @return float
	 */
	public static float strToFloat(String str, float defaultValue) {
		float result = defaultValue;
		try {
			if (str != null && str.length() != 0)
				result = Float.parseFloat(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 将字符串转换为boolean类型
	 * 
	 * @param source
	 * @return 如果 字符串不是null并且字符串时 true或者1 则返回true
	 */
	public static boolean strToBoolean(String source) {
		return (source != null)
				&& (source.equalsIgnoreCase("true") || source.equals("1"));
	}

	/**
	 * 将字符串转换成date
	 * 
	 * @param str
	 *            格式yyyy-mm-dd
	 * @return double
	 */
	public static java.util.Date strToDate(String str) {
		java.util.Date result = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			result = sdf.parse(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 字符串转换为时间类型
	 * 
	 * @param time
	 *            格式HH:mm:ss
	 * @return
	 */
	public static java.sql.Time convertTime(String time) {
		java.sql.Time result = null;
		try {
			result = java.sql.Time.valueOf(time);
		} catch (Exception e) {
		}
		return result;
	}

	/**
	 * 屏蔽为null的字符串
	 * 
	 * @param str
	 * @return String
	 */
	public static String delNull(String str) {
		if (str == null) {
			return "";
		} else {
			return str;
		}
	}

	/**
	 * 联合时间 将不完整的时间字符串组合成完整的时间字符串 yyyy-MM-dd hh:mm:ss
	 * 
	 * @param source
	 * @return
	 */
	public static String combineDateTime(String source) {
		return combineDateTime(source, 0);
	}

	/**
	 * 将不完整的时间字符串组合成完整的时间字符串 yyyy-MM-dd hh:mm:ss
	 * 
	 * @param source
	 * @param lastTimeFlag
	 *            1：将时间设置为 23:59:59 其它值：将时间设置为 00:00:00
	 * @return
	 */
	public static String combineDateTime(String source, int lastTimeFlag) {
		String date = null;
		String time = null;
		// 如果字符串中不包含空格 则为时间字符串格式或者日期字符串格式
		if (source.indexOf(" ") < 0) {
			// 如果不包含“-”是时间字符串格式否则为日期字符串格式
			if (source.indexOf("-") < 0) {
				date = "";
				time = source;
			}
			// 日期字符串格式
			else {
				date = source;
				time = "";
			}
		}
		// 包含日期和时间
		else {
			String[] dt = source.split(" ");
			date = dt[0];
			time = dt[1];
		}
		// 分析Date部分
		String[] dateFields = date.split("-");
		// 如果字段数等于2则认为是MM-dd的形式
		if (dateFields.length == 2)
			date = Calendar.getInstance().get(Calendar.YEAR) + "-" + date;
		else if (dateFields.length < 2)
			date = new SimpleDateFormat("yyyy-MM-dd")
					.format(new java.util.Date());

		// 分析time部分
		if (time == "")
			if (lastTimeFlag == 1) {
				time = "23:59:59";
			} else {
				time = "00:00:00";
			}
		else {
			String[] timeFields = time.split(":");
			if (timeFields.length == 0)
				time = "00:00:00";
			// 如果字段数等于1则认为是hh的形式
			else if (timeFields.length == 1)
				time = time + ":00:00";
			// 如果字段数等于2则认为是hh:mm的形式
			else if (timeFields.length == 2)
				time = time + ":00";
		}

		return date + " " + time;
	}

	/**
	 * 根据所传入的年月日时分秒获得相对应的时间
	 * @param timeStr (yyyy-MM-dd HH:mm:ss)
	 */
	public static java.sql.Timestamp strToTimestamp(String timeStr){
		java.sql.Timestamp result=null;
		java.util.Calendar ca =java.util.Calendar.getInstance();
		try{
			Integer[] nums= {0,0,0,0,0,0};
			timeStr=timeStr.replaceAll(":|-|/|\\\\| ", "");
			int length = timeStr.length();
			if(length>=14){
				nums[5]=Integer.parseInt(timeStr.substring(12, 14));
			}
			if(length>=12){
				nums[4]=Integer.parseInt(timeStr.substring(10, 12));
			}
			if(length>=10){
				nums[3]=Integer.parseInt(timeStr.substring(8, 10));
			}
			if(length>=8){
				nums[2]=Integer.parseInt(timeStr.substring(6, 8));
			}
			if(length>=6){
				nums[1]=Integer.parseInt(timeStr.substring(4, 6));
			}
			if(length>=4){
				nums[0]=Integer.parseInt(timeStr.substring(0, 4));
			}
			ca.set(nums[0], nums[1]-1, nums[2], nums[3], nums[4], nums[5]);
			result = new java.sql.Timestamp(ca.getTime().getTime()/1000*1000);
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 根据所传入的年月日时分秒获得相对应的时间
	 * @param timeStr (yyyy-MM-dd HH:mm:ss)
	 */
	public static java.util.Date strToDateTime(String timeStr){
		java.util.Date result=null;
		java.util.Calendar ca =java.util.Calendar.getInstance();
		try{
			Integer[] nums= {0,0,0,0,0,0};
			timeStr=timeStr.replaceAll(":|-|/|\\\\| ", "");
			int length = timeStr.length();
			if(length>=14){
				nums[5]=Integer.parseInt(timeStr.substring(12, 14));
			}
			if(length>=12){
				nums[4]=Integer.parseInt(timeStr.substring(10, 12));
			}
			if(length>=10){
				nums[3]=Integer.parseInt(timeStr.substring(8, 10));
			}
			if(length>=8){
				nums[2]=Integer.parseInt(timeStr.substring(6, 8));
			}
			if(length>=6){
				nums[1]=Integer.parseInt(timeStr.substring(4, 6));
			}
			if(length>=4){
				nums[0]=Integer.parseInt(timeStr.substring(0, 4));
			}
			ca.set(nums[0], nums[1]-1, nums[2], nums[3], nums[4], nums[5]);
			result = ca.getTime();
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public static void main(String args[]){
		java.util.Date date =strToDateTime("2012-03-04 32:06:07");
		System.out.println(fmtTime(date));
	}

	/**
	 * 字符串转换为 java.util.Date
	 * 
	 * @param 日期格式
	 * @param 待转换的字符串
	 * @return 如果传入的字符串不是日期格式则返回空 否则返回转换后的日期格式
	 */
	public static java.util.Date strToDate(DateFormat df, String date) {
		try {
			return df.parse(date);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 把字符串转换成指定的类型，若有错误则抛出异常
	 * 
	 * @param src
	 * @param type
	 * @return the Object
	 * @throws BaseException
	 */
	public static Object convert(Object src, String type) throws Exception {
		// 如果为null则直接返回
		if (src == null)
			return src;

		// 如果src是List类型，则取出第一个元素
		if (src instanceof List<?>) {
			List<?> list = (List<?>) src;
			if (list.size() == 0)
				return null;

			src = list.get(0);
		}

		// 如果source不是String类型，则无须进行转换
		if ((src == null) || !(src instanceof String))
			return src;

		// 转换成字符串
		String source = (String) src;
		if (source.length() == 0)
			return null;

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 根据数据类型进行转换
		if (type.equals("int"))
			return strToInt((String) source);
		else if (type.equals("number"))
			return strToFloat((String) source);
		else if(type.equals("Long"))
			return strToLong((String)source);
		else if (type.equals("simpledate")) {
			source = combineDateTime(source);
			return Date.valueOf(fmtDate(strToDate(source)));
		}
		else if (type.equals("double"))
			return strToDouble((String) source,0);
		else if (type.equals("date")) {
			source = combineDateTime(source);
			return strToDate(df, (String) source);
		} else if (type.equals("datetime")) {
			source = combineDateTime(source, 1);
			return strToDate(df, (String) source);
		} else if ("timestamp".equalsIgnoreCase(type)){
			return strToTimestamp(source);
		}else if (type.equals("boolean"))
			return new Boolean(strToBoolean((String) source));
		else
			return source;
	}

	/**
	 * 数字转中文大写
	 * 
	 * @param num
	 * @return
	 */
	public static String convertCurrencyToChinese(Object num) {
		if (num == null)
			return "";
		BigDecimal value = new BigDecimal(num.toString());
		// System.out.println(value);
		String[] strs = value.toString().split("\\.");
		String preDotNum = strs[0];

		String format = "";
		for (int i = preDotNum.length(), j = preDotNum.length(); i > 0; i--) {
			if (preDotNum.charAt(j - i) == '-')
				format = format + "负";
			else
				format = format + numChar2chinese(preDotNum.charAt(j - i), i);
		}
		format = format.replaceAll("零仟零佰零拾零", "零");
		format = format.replaceAll("零仟零佰零拾", "零");
		format = format.replaceAll("零仟零佰", "零");
		format = format.replaceAll("零仟", "零");
		format = format.replaceAll("零佰零拾零", "零");
		format = format.replaceAll("零佰零拾", "零");
		format = format.replaceAll("零佰", "零");
		format = format.replaceAll("零拾零", "零");
		format = format.replaceAll("零拾", "零");
		format = format.replaceAll("零亿", "亿");
		format = format.replaceAll("零万", "万");
		format = format.replaceAll("亿万", "亿零");
		format = format.replaceAll("零零", "零");
		if (format.endsWith("零"))
			format = format.substring(0, format.length() - 1);
		format = format + "元";
		if (strs.length == 2 && !"00".equals(strs[1]) && !"0".equals(strs[1])) {
			String afterDotNum = strs[1];
			for (int i = 0; i < afterDotNum.length() && i < 2; i++) {
				format = format + numChar2chinese(afterDotNum.charAt(i), 0 - i);
			}
		}
		return format;
	}

	/**
	 * 数字转中文大写并且包括单位
	 * 
	 * @param num
	 *            数字
	 * @param pos
	 *            数字所在位置 根据位置确定单位
	 * @return
	 */
	private static String numChar2chinese(char num, int pos) {
		String str = "";
		switch (num) {
		case '0':
			str = "零";
			break;
		case '1':
			str = "壹";
			break;
		case '2':
			str = "贰";
			break;
		case '3':
			str = "叁";
			break;
		case '4':
			str = "肆";
			break;
		case '5':
			str = "伍";
			break;
		case '6':
			str = "陆";
			break;
		case '7':
			str = "柒";
			break;
		case '8':
			str = "扒";
			break;
		case '9':
			str = "玖";
			break;
		}
		String posName = "";
		switch (pos) {
		case -1:
			posName = "分";
			break;
		case 0:
			posName = "角";
			break;
		case 1:
			break;
		case 2:
			posName = "拾";
			break;
		case 3:
			posName = "佰";
			break;
		case 4:
			posName = "仟";
			break;
		case 5:
			posName = "万";
			break;
		case 6:
			posName = "拾";
			break;
		case 7:
			posName = "佰";
			break;
		case 8:
			posName = "仟";
			break;
		case 9:
			posName = "亿";
			break;
		case 10:
			posName = "拾";
			break;
		case 11:
			posName = "佰";
			break;
		case 12:
			posName = "仟";
			break;
		}
		return str + posName;
	}

	/**
	 * 将url 参数部分转义
	 * 
	 * @param urlStr
	 *            url字符串
	 * @return 转义后字符串
	 */
	public static String encodeUrl(String urlStr) {
		String encoderedStr = "";
		if (urlStr != null && urlStr.length() > 0) {
			// 以问号分割字符串
			String[] strArr = urlStr.split("\\?");
			// 开始字符串为？前部分
			encoderedStr = strArr[0];
			// 问号后部分为变量部分
			String paramStr = strArr.length == 1 ? "" : strArr[1];
			// 如果有变量部分
			if (paramStr.length() > 0) {
				Map<String, String> paramsMap = new HashMap<String, String>();
				int ampersandIndex, lastAmpersandIndex = 0;
				String subStr, param, value;
				String[] paramPair;
				do {
					ampersandIndex = paramStr.indexOf('&', lastAmpersandIndex) + 1;
					if (ampersandIndex > 0) {
						subStr = paramStr.substring(lastAmpersandIndex,
								ampersandIndex - 1);
						lastAmpersandIndex = ampersandIndex;
					} else {
						subStr = paramStr.substring(lastAmpersandIndex);
					}
					paramPair = subStr.split("=");
					param = paramPair[0];
					value = paramPair.length == 1 ? "" : paramPair[1];
					try {
						value = URLEncoder.encode(value, "utf-8");
					} catch (UnsupportedEncodingException ignored) {

					}
					paramsMap.put(param, value);
				} while (ampersandIndex > 0);

				encoderedStr += "?";
				for (String string : paramsMap.keySet()) {
					encoderedStr += string + "=" + paramsMap.get(string);
				}
			}
		}
		return encoderedStr;
	}
	/**
	 * 将异常信息导入字符串
	 * @param e 异常信息
	 * @return String
	 */
	public static String getExceptionTrace(Throwable e) {
		if (e != null) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			return sw.toString();
		}
       return "No Exception";
    }
	
	/**
	 * 将以秒计算的时间转化成以小时计算的时间
	 * @param num
	 * @return String (h小时m分s秒)
	 */
	public static Integer secondToHour(Integer num){
		if(num==null){
			return null;
		}else if(num<0){
			return -1;
		}else{
			int h = num/60/60;
			return h;
		}
	}
	/**
	 * 将小时换算成秒
	 */
	public static Integer HourToSecond(Integer hour){
		if(hour == null){
			return null;
		}
		if(hour<0){
			return -1;
		}
		return hour * 60 * 60;
	}
	
	/**
	 * 将 util.Date 转换成 sql.Date
	 */
	public static java.sql.Date utilDateTosqlDate(java.util.Date date){
		if(date == null){
			return null;
		}
		return new java.sql.Date(date.getTime());
	}
	
	/**
	 * 获取当前认证通过的用户名
	 * @return
	 */
	public static String findAuthenticatedUsername() { 
	    String username = null; 
	    Object principal = SecurityContextHolder.getContext() 
	        .getAuthentication().getPrincipal(); 
	    if (principal instanceof UserDetails) { 
	        username = ((UserDetails) principal).getUsername(); 
	    } else { 
	        username = principal.toString(); 
	    } 
	    return username; 
	 }
	
}
