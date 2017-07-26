package ytu.icoding.security;
import java.util.Collection;

import java.util.Iterator;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
/**
 * �Լ�ʵ�ֵĹ����û������࣬Ҳ����ֱ��ʹ�� FilterSecurityInterceptor
 * 
 * AbstractSecurityInterceptor�����������ࣺ
 * FilterSecurityInterceptor��������FilterInvocation��ʵ�ֶ�URL��Դ�����ء�
 * MethodSecurityInterceptor��������MethodInvocation��ʵ�ֶԷ������õ����ء�
 * AspectJSecurityInterceptor��������JoinPoint����Ҫ�����ڶ����淽��(AOP)���õ����ء�
 * 
 * ������ֱ��ʹ��ע���Action�����������أ������ڷ����ϼӣ�
 * @PreAuthorize("hasRole('ROLE_SUPER')")
 * <!-- �û��Ƿ�ӵ����������Դ��Ȩ�� -->
 * 
 * @author lanyuan
 * 2013-11-19
 * @Email: mmm333zzz520@163.com
 * @version 1.0v
 */
@Service
public class MyAccessDecisionManager implements AccessDecisionManager {
	
	public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
		System.err.println(" ---------------  MyAccessDecisionManager --------------- ");
		if(configAttributes == null) {
			return;
		}
		//���������Դӵ�е�Ȩ��(һ����Դ�Զ��Ȩ��)
		Iterator<ConfigAttribute> iterator = configAttributes.iterator();
		while(iterator.hasNext()) {
			ConfigAttribute configAttribute = iterator.next();
			//������������Դ����Ҫ��Ȩ��
			String needPermission = configAttribute.getAttribute();
			//System.out.println("needPermission is " + needPermission);
			//�û���ӵ�е�Ȩ��authentication
			for(GrantedAuthority ga : authentication.getAuthorities()) {
				if(needPermission.equals(ga.getAuthority())) {
					return;
				}
			}
		}
		//û��Ȩ��
		throw new AccessDeniedException(" û��Ȩ�޷��ʣ� ");
	}

	public boolean supports(ConfigAttribute attribute) {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return true;
	}
	
}