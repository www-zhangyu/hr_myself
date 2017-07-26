package ytu.icoding.dao;

import java.util.HashMap;
import java.util.List;

import ytu.icoding.entity.Role;

public interface RoleDao {

	public List<Role> findAll();
	
	int isUserExit(int id);
	
	void addRoleToUser(HashMap<String,Object> map);
	
	void updateRoleTouser(HashMap<String,Object> map);
}
