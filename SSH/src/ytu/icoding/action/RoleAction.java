package ytu.icoding.action;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import ytu.icoding.service.RoleService;

@Controller
public class RoleAction extends StandardAction{

	@Resource
	private RoleService roleService;
	public String addRoleToUser(){
		
		int userId = Integer.valueOf(request.getParameter("userId"));
		
		String user_role = request.getParameter("user_role");
		int roleId ;
		if(user_role.equals("��������Ա")) {
			roleId = 1;
		}else if (user_role.equals("����Ա")) {
			roleId = 2;
		}else roleId = 3;
		
		////��ѯ�û���ɫ�м�������޸��û�
		////���û��insert
		////�������update
		int count = roleService.isUserExit(userId);
		if (count > 0) {
			roleService.updateRoleTouser(userId, roleId);
		}else if(count == 0) {
			roleService.addRoleToUser(userId, roleId);
		}
		return "success";
	}
	
	
	public RoleService getRoleService() {
		return roleService;
	}
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}
	
	
}
