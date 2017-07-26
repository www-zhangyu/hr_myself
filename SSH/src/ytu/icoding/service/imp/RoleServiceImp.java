package ytu.icoding.service.imp;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ytu.icoding.dao.RoleDao;
import ytu.icoding.entity.Role;
import ytu.icoding.service.RoleService;

@Service("roleService")
public class RoleServiceImp implements RoleService{
	
	@Resource
	private RoleDao roleDao;
	
	public RoleDao getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	@Override
	public List<Role> findAll() {
		// TODO Auto-generated method stub
		return roleDao.findAll();
	}
	
	@Override
	public int isUserExit(int id) {
		// TODO Auto-generated method stub
		return roleDao.isUserExit(id);
	}
	
	@Override
	public void addRoleToUser(int userId, int roleId) {
		// TODO Auto-generated method stub
		HashMap<String,Object> map = new HashMap<String,Object> ();
		map.put("userId", userId);
		map.put("roleId", roleId);
		roleDao.addRoleToUser(map);
	}
	
	@Override
	public void updateRoleTouser(int userId, int roleId) {
		// TODO Auto-generated method stub
		HashMap<String,Object> map = new HashMap<String,Object> ();
		map.put("userId", userId);
		map.put("roleId", roleId);
		roleDao.updateRoleTouser(map);
	}

}
