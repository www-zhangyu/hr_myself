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
		if(user_role.equals("超级管理员")) {
			roleId = 1;
		}else if (user_role.equals("管理员")) {
			roleId = 2;
		}else roleId = 3;
		
		////查询用户角色中间表中有无该用户
		////如果没有insert
		////如果有则update
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
