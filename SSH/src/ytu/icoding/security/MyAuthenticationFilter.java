package ytu.icoding.security;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import ytu.icoding.entity.User;
import ytu.icoding.service.UserService;

/**
 * �������Ҫ���û���¼��֤
 * 
 */
public class MyAuthenticationFilter extends
		UsernamePasswordAuthenticationFilter {
	private static final String USERNAME = "username";
	private static final String PASSWORD = "password";
	/**
	 * ��¼�ɹ�����ת�ĵ�ַ
	 */
	private String successUrl = "/mainframe.jsp";
	/**
	 * ��¼ʧ�ܺ���ת�ĵ�ַ
	 */
	private String errorUrl = "/login.jsp";
	@Resource
	private UserService userService;
	
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * �Զ����������name���ԣ�Ĭ���� j_username �� j_password
	 * �����¼�ɹ���ʧ�ܵ���ת��ַ
	 * @author LJN
	 * Email: mmm333zzz520@163.com
	 * @date 2013-12-5 ����7:02:32
	 */
	public void init() {
		System.err.println(" ---------------  MyAuthenticationFilter init--------------- ");
		this.setUsernameParameter(USERNAME);
		this.setPasswordParameter(PASSWORD);
		// ��֤�ɹ�����ת��ҳ��
		SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
		successHandler.setDefaultTargetUrl(successUrl);
		this.setAuthenticationSuccessHandler(successHandler);

		// ��֤ʧ�ܣ���ת��ҳ��
		SimpleUrlAuthenticationFailureHandler failureHandler = new SimpleUrlAuthenticationFailureHandler();
		failureHandler.setDefaultFailureUrl(errorUrl);
		this.setAuthenticationFailureHandler(failureHandler);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,
			HttpServletResponse response) throws AuthenticationException {
//		System.err.println(" ---------------  MyAuthenticationFilter attemptAuthentication--------------- ");
		
		if (!request.getMethod().equals("POST")) {
			throw new AuthenticationServiceException(
					"Authentication method not supported: "
							+ request.getMethod());
		}

		String username = obtainUsername(request).trim();
		String password = obtainPassword(request).trim();
		// System.out.println(">>>>>>>>>>000<<<<<<<<<< username is " +
		// username);

		// ��֤�û��˺��������Ƿ���ȷ
		User users = this.userService.getUserByName(username);
		if (users == null || !users.getPassword().equals(password)) {
			BadCredentialsException exception = new BadCredentialsException(
					"�û��������벻ƥ�䣡");// �ڽ�������Զ������Ϣ����
			// request.setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION,
			// exception);
			throw exception;
		}
		// ����֤��ͨ���󣬰��û���Ϣ����session��
		request.getSession().setAttribute("userSession", users);
		// ʵ�� Authentication
		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
				username, password);
		// ��������������ϸ����
		setDetails(request, authRequest);

		// ����UserDetailsService��loadUserByUsername �ٴη�װAuthentication
		return this.getAuthenticationManager().authenticate(authRequest);
	}

	public String getSuccessUrl() {
		return successUrl;
	}

	public void setSuccessUrl(String successUrl) {
		this.successUrl = successUrl;
	}

	public String getErrorUrl() {
		return errorUrl;
	}

	public void setErrorUrl(String errorUrl) {
		this.errorUrl = errorUrl;
	}
}
