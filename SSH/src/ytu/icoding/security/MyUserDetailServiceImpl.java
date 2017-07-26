package ytu.icoding.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ytu.icoding.entity.Resources;
import ytu.icoding.service.ResourcesService;
import ytu.icoding.service.UserService;


/**
 * User userdetail����ʵ�� UserDetails �ӿڣ���������֤�ɹ���ᱻ�����ڵ�ǰ�ػ���principal������
 * 
 * ��ö���ķ�ʽ��
 * WebUserDetails webUserDetails = (WebUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
 * 
 * ����JSP�У�
 * <sec:authentication property="principal.username"/>
 * 
 * �����Ҫ�����û����������ԣ�����ʵ�� UserDetails �ӿ���������Ӧ���Լ���
 * Ȩ����֤��
 * @author lanyuan
 * 2013-11-19
 * @Email: mmm333zzz520@163.com
 * @version 1.0v
 */
@Service
public class MyUserDetailServiceImpl implements UserDetailsService {
	
	@Resource
	private UserService userService;
	@Resource
	private ResourcesService resourcesService ;
	
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}


	public ResourcesService getResourcesService() {
		return resourcesService;
	}

	public void setResourcesService(ResourcesService resourcesService) {
		this.resourcesService = resourcesService;
	}
	// ��¼��֤
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.err.println("-----------MyUserDetailServiceImpl loadUserByUsername ----------- ");
		
		//ȡ���û���Ȩ��
        ytu.icoding.entity.User users = userService.getUserByName(username);
		if  (users==null)  
            throw new UsernameNotFoundException(username+" not exist!");  
		Collection<GrantedAuthority> grantedAuths = obtionGrantedAuthorities(users);
		// ��װ��spring security��user
		User userdetail = new User(
				users.getName(), 
				users.getPassword(),
				true, 
				true, 
				true,
				true, 
				grantedAuths	//�û���Ȩ��
			);
		return userdetail;
	}

	// ȡ���û���Ȩ��
	private Set<GrantedAuthority> obtionGrantedAuthorities(ytu.icoding.entity.User user) {
		List<Resources> resources = resourcesService.getUserResources(String.valueOf(user.getUserId()));
		Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>();
		for (Resources res : resources) {
			// TODO:ZZQ �û����Է��ʵ���Դ���ƣ�����˵�û���ӵ�е�Ȩ�ޣ� ע�⣺����"ROLE_"��ͷ
			// �������룺applicationContext-security.xml
			// �������룺com.huaxin.security.MySecurityMetadataSource#loadResourceDefine
			authSet.add(new SimpleGrantedAuthority("ROLE_" + res.getResKey()));
		}
		return authSet;
	}
}