package ytu.icoding.service;

import java.util.List;

import ytu.icoding.entity.Role;

public interface RoleService {
	
	public List<Role> findAll();
	
	int isUserExit(int id);

	void addRoleToUser(int userId, int roleId);
	
	void updateRoleTouser(int userId, int roleId);

}
