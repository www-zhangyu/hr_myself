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
 * 标准Action类
 * 
 * 
 */
@Controller("standardAction")
@Scope("request")
public class StandardAction extends ActionSupport implements
		ServletRequestAware, ServletResponseAware {
	private static final long serialVersionUID = -2324265780415999469L;

	

	/**
	 * 分页大小的变量名
	 */
	public static final String PAGESIZE = "pageSize";

	/**
	 * 当前页码的变量名
	 */
	public static final String PAGENUMBER = "pageNo";

	/**
	 * 排序列的变量名
	 */
	public static final String SORTCOLUMNS = "sortColumns";

	/**
	 * 页面信息变量名
	 */
	public static final String PAGEINFO = "pageInfo";

	/**
	 * 页面信息变量名
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
	 * 用于返回的 json 串
	 */
	protected JSONArray jsonValidateReturn;

	
	/**
	 * ServletRequestAware 接口的方法 初始化Action实例时容器会调用此方法
	 */
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	/**
	 * ServletResponseAware 接口的方法 初始化Action实例时容器会调用此方法
	 */
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	public void setJsonValidateReturn(JSONArray jsonValidateReturn) {
		this.jsonValidateReturn = jsonValidateReturn;
	}

	/**
	 * 添加返回信息到Attitude
	 * 
	 * @param result
	 *            返回结果 -1:失败 0:警告 1:成功
	 * 
	 * @param code
	 *            返回代码 通过返回代码到配置文件获取信息写入到ReturnValue中
	 */
	public void addReturnValue(int result, long code) {
		addReturnValue(result, code, null);
	}

	/**
	 * 添加返回信息到Attitude
	 * 
	 * @param result
	 *            返回结果 -1:失败 0:警告 1:成功
	 * @param args
	 *            格式化字符串参数
	 * 
	 * @param code
	 *            返回代码 通过返回代码到配置文件获取信息写入到ReturnValue中
	 */
	public void addReturnValue(int result, long code, Object[] args) {
		ReturnValue returnValue = new ReturnValue();
		returnValue.setResult(result);
		if (args == null)
			returnValue.addInfo(code);
		else
			returnValue.addInfo(code, args);
		// 将返回结果写入Attribute
		request.setAttribute("ReturnValue", returnValue);
	}
	
	/**
	 * 通过传入参数生成 JSONArray 类
	 * @param objects 参数
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
