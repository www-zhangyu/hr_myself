package ytu.icoding.util;

import java.util.HashMap;
import java.util.Map;

public class ReturnValue {
	/**
	 * -1:失败 0:警告 1:成功
	 */
	private int result = -1;

	/*
	 * 存放错误或者警告信息或者提示信息
	 */
	private Map<java.lang.Long, String> errorInfoMap = new HashMap<Long, String>();

	/**
	 * -1:失败 0:警告 1:成功
	 * 
	 * @return
	 */
	public int getResult() {
		return result;
	}

	/**
	 * -1:失败 0:警告 1:成功
	 * 
	 * @param result
	 */
	public void setResult(int result) {
		this.result = result;
	}

	/**
	 * 存放错误或者警告信息或者提示信息
	 * 
	 * @return
	 */
	public Map<java.lang.Long, String> getInfoMap() {
		return errorInfoMap;
	}

	/**
	 * 返回错误或者警告信息或者提示信息
	 * 
	 * @return
	 */
	public String getInfo() {
		StringBuffer sb = new StringBuffer();
		for (Map.Entry<java.lang.Long, String> m : errorInfoMap.entrySet()) {
			if (result == 1)
				sb.append(m.getValue()).append("\\n");
			else
//				sb.append(
//						"错误码【" + m.getKey() + "】错误信息【"
//								+ m.getValue()).append("】\\n");
				sb.append(m.getValue()).append("\\n");
		}
		return sb.toString();
	}

	/**
	 * 添加错误或者警告信息或者提示信息
	 * 
	 * @param errorCode
	 *            错误码
	 */
	public void addInfo(long errorCode) {
		errorInfoMap.put(errorCode, ApplicationContextInit
				.getErrorInfo(errorCode + ""));
	}

	/**
	 * 添加错误或者警告信息或者提示信息
	 * 
	 * @param errorCode
	 *            错误码
	 * @param args
	 *            格式化字符串参数
	 */
	public void addInfo(long errorCode, Object[] args) {
		String format = ApplicationContextInit.getErrorInfo(errorCode + "");
		String errorInfo = format;
		try {
			errorInfo = String.format(format, args);
		} catch (Exception e) {
			
		}
		errorInfoMap.put(errorCode, errorInfo.replaceAll("\n", ""));
	}
}
