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
 * �Զ��幤���� �����ķ���ȫ��Ϊ��̬����
 * 
 * @author xuejt
 * 
 */
public class Tools {
	/**
	 * ��д��һ����ĸ
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
	 * ��Timestamp��������ת��Ϊ'YYYY-MM-DD'
	 * 
	 * @param Timestamp
	 * @return String
	 */
	public static String convertTS(Timestamp ts) {
		return ts.toString().substring(0, ts.toString().indexOf(" "));
	}

	/**
	 * ��ʽ��˫���ȸ�����������С�������λ
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
	 * ��˫���ȸ�����ת��Ϊ�ַ���
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
	 * ��ʽ��ʱ�䣬yyyy-MM-dd HH:mm:ss
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
	 * ��ʽ�����ڣ�yyyy-MM-dd
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
	 * ��ʽ�����ڣ�HH:mm:ss
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
	 * ��ʽ��ʱ�䣬yyyy-MM-dd HH:mm:ss
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
	 * ��ʽ�����ڣ�yyyy-MM-dd
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
	 * ��ʽ��ʱ�䣬yyyy-MM-dd HH:mm:ss
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
	 * ��ʽ�����ڣ�yyyy-MM-dd
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
	 * ���ַ���ת����int
	 * 
	 * @param str
	 * @return int
	 */
	public static int strToInt(String str) {
		return strToInt(str, -1000);
	}

	/**
	 * ���ַ���ת����int
	 * 
	 * @param str
	 * @param defaultV
	 *            �����쳣�� Ĭ��ֵ
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
	 * ���ַ���ת����long
	 * 
	 * @param str
	 * @return int
	 */
	public static long strToLong(String str) {
		return strToLong(str, -1000);
	}

	/**
	 * ���ַ���ת����long
	 * 
	 * @param str
	 * @param defaultV
	 *            �����쳣�� Ĭ��ֵ
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
	 * ���ַ���ת����double
	 * 
	 * @param str
	 * @param defaultValue
	 *            Ĭ��ֵ
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
	 * ���ַ���ת����float
	 * 
	 * @param str
	 * @return float ���ת�������򷵻� 0
	 */
	public static float strToFloat(String str) {
		return strToFloat(str, 0f);
	}

	/**
	 * ���ַ���ת����float
	 * 
	 * @param str
	 * @param defaultValue
	 *            Ĭ��ֵ
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
	 * ���ַ���ת��Ϊboolean����
	 * 
	 * @param source
	 * @return ��� �ַ�������null�����ַ���ʱ true����1 �򷵻�true
	 */
	public static boolean strToBoolean(String source) {
		return (source != null)
				&& (source.equalsIgnoreCase("true") || source.equals("1"));
	}

	/**
	 * ���ַ���ת����date
	 * 
	 * @param str
	 *            ��ʽyyyy-mm-dd
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
	 * �ַ���ת��Ϊʱ������
	 * 
	 * @param time
	 *            ��ʽHH:mm:ss
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
	 * ����Ϊnull���ַ���
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
	 * ����ʱ�� ����������ʱ���ַ�����ϳ�������ʱ���ַ��� yyyy-MM-dd hh:mm:ss
	 * 
	 * @param source
	 * @return
	 */
	public static String combineDateTime(String source) {
		return combineDateTime(source, 0);
	}

	/**
	 * ����������ʱ���ַ�����ϳ�������ʱ���ַ��� yyyy-MM-dd hh:mm:ss
	 * 
	 * @param source
	 * @param lastTimeFlag
	 *            1����ʱ������Ϊ 23:59:59 ����ֵ����ʱ������Ϊ 00:00:00
	 * @return
	 */
	public static String combineDateTime(String source, int lastTimeFlag) {
		String date = null;
		String time = null;
		// ����ַ����в������ո� ��Ϊʱ���ַ�����ʽ���������ַ�����ʽ
		if (source.indexOf(" ") < 0) {
			// �����������-����ʱ���ַ�����ʽ����Ϊ�����ַ�����ʽ
			if (source.indexOf("-") < 0) {
				date = "";
				time = source;
			}
			// �����ַ�����ʽ
			else {
				date = source;
				time = "";
			}
		}
		// �������ں�ʱ��
		else {
			String[] dt = source.split(" ");
			date = dt[0];
			time = dt[1];
		}
		// ����Date����
		String[] dateFields = date.split("-");
		// ����ֶ�������2����Ϊ��MM-dd����ʽ
		if (dateFields.length == 2)
			date = Calendar.getInstance().get(Calendar.YEAR) + "-" + date;
		else if (dateFields.length < 2)
			date = new SimpleDateFormat("yyyy-MM-dd")
					.format(new java.util.Date());

		// ����time����
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
			// ����ֶ�������1����Ϊ��hh����ʽ
			else if (timeFields.length == 1)
				time = time + ":00:00";
			// ����ֶ�������2����Ϊ��hh:mm����ʽ
			else if (timeFields.length == 2)
				time = time + ":00";
		}

		return date + " " + time;
	}

	/**
	 * �����������������ʱ���������Ӧ��ʱ��
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
	 * �����������������ʱ���������Ӧ��ʱ��
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
	 * �ַ���ת��Ϊ java.util.Date
	 * 
	 * @param ���ڸ�ʽ
	 * @param ��ת�����ַ���
	 * @return ���������ַ����������ڸ�ʽ�򷵻ؿ� ���򷵻�ת��������ڸ�ʽ
	 */
	public static java.util.Date strToDate(DateFormat df, String date) {
		try {
			return df.parse(date);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * ���ַ���ת����ָ�������ͣ����д������׳��쳣
	 * 
	 * @param src
	 * @param type
	 * @return the Object
	 * @throws BaseException
	 */
	public static Object convert(Object src, String type) throws Exception {
		// ���Ϊnull��ֱ�ӷ���
		if (src == null)
			return src;

		// ���src��List���ͣ���ȡ����һ��Ԫ��
		if (src instanceof List<?>) {
			List<?> list = (List<?>) src;
			if (list.size() == 0)
				return null;

			src = list.get(0);
		}

		// ���source����String���ͣ����������ת��
		if ((src == null) || !(src instanceof String))
			return src;

		// ת�����ַ���
		String source = (String) src;
		if (source.length() == 0)
			return null;

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// �����������ͽ���ת��
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
	 * ����ת���Ĵ�д
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
				format = format + "��";
			else
				format = format + numChar2chinese(preDotNum.charAt(j - i), i);
		}
		format = format.replaceAll("��Ǫ�����ʰ��", "��");
		format = format.replaceAll("��Ǫ�����ʰ", "��");
		format = format.replaceAll("��Ǫ���", "��");
		format = format.replaceAll("��Ǫ", "��");
		format = format.replaceAll("�����ʰ��", "��");
		format = format.replaceAll("�����ʰ", "��");
		format = format.replaceAll("���", "��");
		format = format.replaceAll("��ʰ��", "��");
		format = format.replaceAll("��ʰ", "��");
		format = format.replaceAll("����", "��");
		format = format.replaceAll("����", "��");
		format = format.replaceAll("����", "����");
		format = format.replaceAll("����", "��");
		if (format.endsWith("��"))
			format = format.substring(0, format.length() - 1);
		format = format + "Ԫ";
		if (strs.length == 2 && !"00".equals(strs[1]) && !"0".equals(strs[1])) {
			String afterDotNum = strs[1];
			for (int i = 0; i < afterDotNum.length() && i < 2; i++) {
				format = format + numChar2chinese(afterDotNum.charAt(i), 0 - i);
			}
		}
		return format;
	}

	/**
	 * ����ת���Ĵ�д���Ұ�����λ
	 * 
	 * @param num
	 *            ����
	 * @param pos
	 *            ��������λ�� ����λ��ȷ����λ
	 * @return
	 */
	private static String numChar2chinese(char num, int pos) {
		String str = "";
		switch (num) {
		case '0':
			str = "��";
			break;
		case '1':
			str = "Ҽ";
			break;
		case '2':
			str = "��";
			break;
		case '3':
			str = "��";
			break;
		case '4':
			str = "��";
			break;
		case '5':
			str = "��";
			break;
		case '6':
			str = "½";
			break;
		case '7':
			str = "��";
			break;
		case '8':
			str = "��";
			break;
		case '9':
			str = "��";
			break;
		}
		String posName = "";
		switch (pos) {
		case -1:
			posName = "��";
			break;
		case 0:
			posName = "��";
			break;
		case 1:
			break;
		case 2:
			posName = "ʰ";
			break;
		case 3:
			posName = "��";
			break;
		case 4:
			posName = "Ǫ";
			break;
		case 5:
			posName = "��";
			break;
		case 6:
			posName = "ʰ";
			break;
		case 7:
			posName = "��";
			break;
		case 8:
			posName = "Ǫ";
			break;
		case 9:
			posName = "��";
			break;
		case 10:
			posName = "ʰ";
			break;
		case 11:
			posName = "��";
			break;
		case 12:
			posName = "Ǫ";
			break;
		}
		return str + posName;
	}

	/**
	 * ��url ��������ת��
	 * 
	 * @param urlStr
	 *            url�ַ���
	 * @return ת����ַ���
	 */
	public static String encodeUrl(String urlStr) {
		String encoderedStr = "";
		if (urlStr != null && urlStr.length() > 0) {
			// ���ʺŷָ��ַ���
			String[] strArr = urlStr.split("\\?");
			// ��ʼ�ַ���Ϊ��ǰ����
			encoderedStr = strArr[0];
			// �ʺź󲿷�Ϊ��������
			String paramStr = strArr.length == 1 ? "" : strArr[1];
			// ����б�������
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
	 * ���쳣��Ϣ�����ַ���
	 * @param e �쳣��Ϣ
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
	 * ����������ʱ��ת������Сʱ�����ʱ��
	 * @param num
	 * @return String (hСʱm��s��)
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
	 * ��Сʱ�������
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
	 * �� util.Date ת���� sql.Date
	 */
	public static java.sql.Date utilDateTosqlDate(java.util.Date date){
		if(date == null){
			return null;
		}
		return new java.sql.Date(date.getTime());
	}
	
	/**
	 * ��ȡ��ǰ��֤ͨ�����û���
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
