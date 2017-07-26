package ytu.icoding.security;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Service;
/**
 * ���ĵ�InterceptorStatusToken token = super.beforeInvocation(fi);��������Ƕ����accessDecisionManager:decide(Object object)��securityMetadataSource
 
  :getAttributes(Object object)������
 * �Լ�ʵ�ֵĹ����û������࣬Ҳ����ֱ��ʹ�� FilterSecurityInterceptor
 * 
 * AbstractSecurityInterceptor�����������ࣺ
 * FilterSecurityInterceptor��������FilterInvocation��ʵ�ֶ�URL��Դ�����ء�
 * MethodSecurityInterceptor��������MethodInvocation��ʵ�ֶԷ������õ����ء�
 * AspectJSecurityInterceptor��������JoinPoint����Ҫ�����ڶ����淽��(AOP)���õ����ء�
 * 
 * ������ֱ��ʹ��ע���Action�����������أ������ڷ����ϼӣ�
 * @PreAuthorize("hasRole('ROLE_SUPER')")
 * 

 */
@Service
public class MySecurityFilter extends AbstractSecurityInterceptor implements Filter {
	//��applicationContext-security.xml���myFilter������securityMetadataSource��Ӧ��
	//����������������Ѿ���AbstractSecurityInterceptor����
	@Resource
	private MySecurityMetadataSource securityMetadataSource;
	@Resource
	private MyAccessDecisionManager accessDecisionManager;
	@Resource
	private AuthenticationManager myAuthenticationManager; 
	
	public MySecurityMetadataSource getSecurityMetadataSource() {
		return securityMetadataSource;
	}

	public void setSecurityMetadataSource(
			MySecurityMetadataSource securityMetadataSource) {
		this.securityMetadataSource = securityMetadataSource;
	}

	public MyAccessDecisionManager getAccessDecisionManager() {
		return accessDecisionManager;
	}

	public void setAccessDecisionManager(
			MyAccessDecisionManager accessDecisionManager) {
		this.accessDecisionManager = accessDecisionManager;
	}

	public AuthenticationManager getMyAuthenticationManager() {
		return myAuthenticationManager;
	}

	public void setMyAuthenticationManager(
			AuthenticationManager myAuthenticationManager) {
		this.myAuthenticationManager = myAuthenticationManager;
	}

	@PostConstruct
	public void init(){
		System.err.println(" ---------------  MySecurityFilter init--------------- ");
		super.setAuthenticationManager(myAuthenticationManager);
		super.setAccessDecisionManager(accessDecisionManager);
	}
	
	@Override
	public SecurityMetadataSource obtainSecurityMetadataSource() {
		return this.securityMetadataSource;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		FilterInvocation fi = new FilterInvocation(request, response, chain);
		invoke(fi);
	}
	
	private void invoke(FilterInvocation fi) throws IOException, ServletException {
		// objectΪFilterInvocation����
                  //super.beforeInvocation(fi);Դ��
		//1.��ȡ������Դ��Ȩ��
		//ִ��Collection<ConfigAttribute> attributes = SecurityMetadataSource.getAttributes(object);
		//2.�Ƿ�ӵ��Ȩ��
		//this.accessDecisionManager.decide(authenticated, object, attributes);
//		System.err.println(" ---------------  MySecurityFilter invoke--------------- ");
		InterceptorStatusToken token = super.beforeInvocation(fi);
		try {
			fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
		} finally {
			super.afterInvocation(token, null);
		}
	}

	public void init(FilterConfig arg0) throws ServletException {
	}
	
	public void destroy() {
		
	}

	@Override
	public Class<? extends Object> getSecureObjectClass() {
		//�����MyAccessDecisionManager��supports�������Ż�true,������������ʹ���
		return FilterInvocation.class;
	}
}