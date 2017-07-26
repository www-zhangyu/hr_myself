package ytu.icoding.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Service;

import ytu.icoding.entity.Resources;
import ytu.icoding.service.ResourcesService;


/**
 * ������Դ��Ȩ�޵Ķ�Ӧ��ϵ
 * @author lanyuan
 * 2013-11-19
 * @Email: mmm333zzz520@163.com
 * @version 1.0v
 * */
@Service
public class MySecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
	@Resource
	private ResourcesService resourcesService;
	
	
	public ResourcesService getResourcesService() {
		return resourcesService;
	}

	public void setResourcesService(ResourcesService resourcesService) {
		this.resourcesService = resourcesService;
	}
	private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	public boolean supports(Class<?> clazz) {
		return true;
	}
	/**
	 * @PostConstruct��Java EE 5�����ע�⣬
	 * Spring�����������ܹ�Bean��ʹ��������DI����ʵ������ǰ�ܹ�Beanʱ��
	 * @PostConstructע��ķ����ᱻ�Զ��������Ӷ����һЩ��ʼ��������
	 * 
	 * //����������Դ��Ȩ�޵Ĺ�ϵ
	 */
	@PostConstruct
	private void loadResourceDefine() {
		System.err.println("-----------MySecurityMetadataSource loadResourceDefine ----------- ");
		if (resourceMap == null) {
			resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
			List<Resources> resources = this.resourcesService.findAll();
			System.out.println(resources);
			for (Resources res : resources) {
				Collection<ConfigAttribute> configAttributes = new ArrayList<ConfigAttribute>();
				// TODO:ZZQ ͨ����Դ��������ʾ�����Ȩ�� ע�⣺����"ROLE_"��ͷ
				// �������룺applicationContext-security.xml
				// �������룺com.huaxin.security.MyUserDetailServiceImpl#obtionGrantedAuthorities
				ConfigAttribute configAttribute = new SecurityConfig("ROLE_" +res.getResKey());
				configAttributes.add(configAttribute);
				resourceMap.put(res.getResUrl(), configAttributes);
			}
		}
	}
	//������������Դ����Ҫ��Ȩ��
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
//		System.err.println("-----------MySecurityMetadataSource getAttributes ----------- ");
		String requestUrl = ((FilterInvocation) object).getRequestUrl();
		System.out.println("requestUrl is " + requestUrl);
		if(resourceMap == null) {
			loadResourceDefine();
		}
		//System.err.println("resourceMap.get(requestUrl); "+resourceMap.get(requestUrl));
		if(requestUrl.indexOf("?")>-1){
			requestUrl=requestUrl.substring(0,requestUrl.indexOf("?"));
		}
		Collection<ConfigAttribute> configAttributes = resourceMap.get(requestUrl);
//		if(configAttributes == null){
//			Collection<ConfigAttribute> returnCollection = new ArrayList<ConfigAttribute>();
//			 returnCollection.add(new SecurityConfig("ROLE_NO_USER")); 
//			return returnCollection;
//		}
		return configAttributes;
	}


}