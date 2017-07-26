package ytu.icoding.dao;


import java.util.Date;
import java.util.HashMap;
import java.util.List;

import ytu.icoding.entity.User;


public interface UserDao {
	
	User getUserByName(String name);
	
	
	List<String> getUserResources(HashMap<String,Object> map);
	
	void changePwd(HashMap<String,Object> map);
	
	List<User> listAllUser();
	
	List<User> listUserPage(HashMap<String,Object> map);
	
	List<User> listUserByName(String name);
	
	void addUser(HashMap<String,Object> map);
	
	int maxId();
	
	void addUserDep(HashMap<String,Object> map);
	
	void deleteUser(int id);
	
	void updateUser(HashMap<String,Object> map);
	
	/*List<User> listUserPageByName(HashMap<String,Object> map);*/

	List<User> selectNotCheckInUser();
	
	List<User> selectLateUser();
	List<User> selectAbsentUser();
}
