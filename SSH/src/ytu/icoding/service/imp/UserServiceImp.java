package ytu.icoding.service.imp;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ytu.icoding.dao.UserDao;
import ytu.icoding.entity.User;
import ytu.icoding.service.UserService;

@Service("userService")
public class UserServiceImp implements UserService{
	
	@Resource
	private UserDao userDao;

	@Override
	public User getUserByName(String name) {
		return userDao.getUserByName(name);
	}
	
	@Override
	public List<String> getUserResources(int id, String roleName) {
		// TODO Auto-generated method stub
		HashMap<String,Object> map = new HashMap<>();
		map.put("roleName",roleName);
		map.put("id", id);
		return userDao.getUserResources(map);
	}
	
	@Transactional
	@Override
	public void changePwd(String password, int id) {
		// TODO Auto-generated method stub
		HashMap<String,Object> map = new HashMap<>();
		map.put("password",password);
		map.put("id", id);
		userDao.changePwd(map);
	}
	
	@Override
	public List<User> listAllUser() {
		// TODO Auto-generated method stub
		return userDao.listAllUser();
	}
	
	@Override
	public List<User> listUserPage(int beginIndex, int pageSize, String name) {
		// TODO Auto-generated method stub
		
		HashMap<String, Object> map = new HashMap<>();
		map.put("beginIndex", beginIndex);
		map.put("pageSize", pageSize);
		map.put("name", name);
		List<User> users = userDao.listUserPage(map);
		return users;
	}
	
	@Override
	public List<User> listUserByName(String name) {
		// TODO Auto-generated method stub
		return userDao.listUserByName(name);
	}
	
	
	
	
	@Transactional
	@Override
	public void addUser( String name, String password, int age, String sex, String phone,
			String mail, String account, int shouldWage, String hireDate) {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<>();
		map.put("name", name);
		map.put("password", password);
		map.put("age", age);
		map.put("sex", sex);
		map.put("phone", phone);
		map.put("mail", mail);
		map.put("account", account);
		map.put("hireDate", hireDate);
		map.put("shouldWage", shouldWage);
		userDao.addUser(map);
	}
	
	
	@Override
	public int maxId() {
		// TODO Auto-generated method stub
		return userDao.maxId();
	}
	@Transactional
	@Override
	public void addUserDep(int userId, int depId) {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<>();
		map.put("userId", userId);
		map.put("depId", depId);
		userDao.addUserDep(map);
	}
	
	
	@Transactional
	@Override
	public void deleteUser(int id) {
		// TODO Auto-generated method stub
		userDao.deleteUser(id);
	}
	
	
	@Transactional
	@Override
	public void updateUser(int id, int age, String sex, String phone,
			String mail, String account) {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("age", age);
		map.put("sex", sex);
		map.put("phone", phone);
		map.put("mail", mail);
		map.put("account", account);
		userDao.updateUser(map);
	}
	
	/**
	 * 查询没有签到记录的用户
	 */
	@Override
	public List<User> selectNotCheckInUser() {
		// TODO Auto-generated method stub
		List<User> users = userDao.selectNotCheckInUser();
		System.out.println(users);
		return users;
	}
	
	/**
	 * 查询当天迟到的用户
	 */
	@Override
	public List<User> selectLateUser() {
		// TODO Auto-generated method stub
		return userDao.selectLateUser();
	}
	
	@Override
	public List<User> selectAbsentUser() {
		// TODO Auto-generated method stub
		return userDao.selectAbsentUser();
	}
}
