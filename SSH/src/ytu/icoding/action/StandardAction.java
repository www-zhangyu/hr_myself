package ytu.icoding.action;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import net.sf.json.JSONArray;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import ytu.icoding.util.ReturnValue;

import com.opensymphony.xwork2.ActionSupport;

/**
 * ��׼Action��
 * 
 * 
 */
@Controller("standardAction")
@Scope("request")
public class StandardAction extends ActionSupport implements
		ServletRequestAware, ServletResponseAware {
	private static final long serialVersionUID = -2324265780415999469L;

	

	/**
	 * ��ҳ��С�ı�����
	 */
	public static final String PAGESIZE = "pageSize";

	/**
	 * ��ǰҳ��ı�����
	 */
	public static final String PAGENUMBER = "pageNo";

	/**
	 * �����еı�����
	 */
	public static final String SORTCOLUMNS = "sortColumns";

	/**
	 * ҳ����Ϣ������
	 */
	public static final String PAGEINFO = "pageInfo";

	/**
	 * ҳ����Ϣ������
	 */
	public static final String OLDPARAMETER = "oldParams";

	/**
	 * HttpServletRequest
	 */
	protected HttpServletRequest request;

	/**
	 * HttpServletResponse
	 */
	protected HttpServletResponse response;

	/**
	 * ���ڷ��ص� json ��
	 */
	protected JSONArray jsonValidateReturn;

	
	/**
	 * ServletRequestAware �ӿڵķ��� ��ʼ��Actionʵ��ʱ��������ô˷���
	 */
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	/**
	 * ServletResponseAware �ӿڵķ��� ��ʼ��Actionʵ��ʱ��������ô˷���
	 */
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	public void setJsonValidateReturn(JSONArray jsonValidateReturn) {
		this.jsonValidateReturn = jsonValidateReturn;
	}

	/**
	 * ��ӷ�����Ϣ��Attitude
	 * 
	 * @param result
	 *            ���ؽ�� -1:ʧ�� 0:���� 1:�ɹ�
	 * 
	 * @param code
	 *            ���ش��� ͨ�����ش��뵽�����ļ���ȡ��Ϣд�뵽ReturnValue��
	 */
	public void addReturnValue(int result, long code) {
		addReturnValue(result, code, null);
	}

	/**
	 * ��ӷ�����Ϣ��Attitude
	 * 
	 * @param result
	 *            ���ؽ�� -1:ʧ�� 0:���� 1:�ɹ�
	 * @param args
	 *            ��ʽ���ַ�������
	 * 
	 * @param code
	 *            ���ش��� ͨ�����ش��뵽�����ļ���ȡ��Ϣд�뵽ReturnValue��
	 */
	public void addReturnValue(int result, long code, Object[] args) {
		ReturnValue returnValue = new ReturnValue();
		returnValue.setResult(result);
		if (args == null)
			returnValue.addInfo(code);
		else
			returnValue.addInfo(code, args);
		// �����ؽ��д��Attribute
		request.setAttribute("ReturnValue", returnValue);
	}
	
	/**
	 * ͨ������������� JSONArray ��
	 * @param objects ����
	 * @return JSONArray
	 */
	protected JSONArray createJSONArray(Object ... objects){
		JSONArray result = new JSONArray();
		for(Object obj : objects){
			result.add(obj);
		}
		return result;
	}
	
	


}
