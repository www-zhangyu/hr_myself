package ytu.icoding.service;


import java.util.Date;
import java.util.HashMap;
import java.util.List;

import ytu.icoding.entity.User;

public interface UserService {
	
	User getUserByName(String name);

	List<String> getUserResources(int id,String roleName);
	
	
	void changePwd(String password, int id);
	
	List<User> listAllUser();
	
	List<User> listUserPage(int beginIndex,int pageSize,String name);
	
	List<User> listUserByName(String name);
	
	
	
	///userId,name,password,userAge,userSex,userPhone,userMail,bankAccount,hireDate
	void addUser(String name, String password, int age, String sex, String phone, String mail, String account, int shouldWage, String hireDate);
	
	
	int maxId();
	
	void addUserDep(int userId, int depId);
	
	void deleteUser(int id);
	
	void updateUser(int id, int age, String sex, String phone, String mail, String account);
	
	/*List<User> listUserPageByName(int beginIndex,int pageSize,String name);*/

	List<User> selectNotCheckInUser();
	
	List<User> selectLateUser();
	
	List<User> selectAbsentUser();
}
