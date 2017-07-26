package ytu.icoding.util;

import java.util.HashMap;
import java.util.Map;

public class ReturnValue {
	/**
	 * -1:ʧ�� 0:���� 1:�ɹ�
	 */
	private int result = -1;

	/*
	 * ��Ŵ�����߾�����Ϣ������ʾ��Ϣ
	 */
	private Map<java.lang.Long, String> errorInfoMap = new HashMap<Long, String>();

	/**
	 * -1:ʧ�� 0:���� 1:�ɹ�
	 * 
	 * @return
	 */
	public int getResult() {
		return result;
	}

	/**
	 * -1:ʧ�� 0:���� 1:�ɹ�
	 * 
	 * @param result
	 */
	public void setResult(int result) {
		this.result = result;
	}

	/**
	 * ��Ŵ�����߾�����Ϣ������ʾ��Ϣ
	 * 
	 * @return
	 */
	public Map<java.lang.Long, String> getInfoMap() {
		return errorInfoMap;
	}

	/**
	 * ���ش�����߾�����Ϣ������ʾ��Ϣ
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
//						"�����롾" + m.getKey() + "��������Ϣ��"
//								+ m.getValue()).append("��\\n");
				sb.append(m.getValue()).append("\\n");
		}
		return sb.toString();
	}

	/**
	 * ��Ӵ�����߾�����Ϣ������ʾ��Ϣ
	 * 
	 * @param errorCode
	 *            ������
	 */
	public void addInfo(long errorCode) {
		errorInfoMap.put(errorCode, ApplicationContextInit
				.getErrorInfo(errorCode + ""));
	}

	/**
	 * ��Ӵ�����߾�����Ϣ������ʾ��Ϣ
	 * 
	 * @param errorCode
	 *            ������
	 * @param args
	 *            ��ʽ���ַ�������
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
