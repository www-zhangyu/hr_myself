package ytu.icoding.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;























import ytu.icoding.entity.User;
import ytu.icoding.service.UserService;


@Controller("userAction")
@Scope("request")
public class UserAction extends StandardAction{

	private static final long serialVersionUID = 1L;
	
	@Resource
	private UserService userService;

	@Resource
	private AuthenticationManager myAuthenticationManager;
	
	private String result;
	
	private User user;


	/**
	 * 用户登录
	 * 
	 * @return
	 * @throws Exception
	 */
	public String login() throws Exception {	
		
		// 服务端session中存放的验证码
		String randNumSys = (String) request.getSession().getAttribute(
				"RANDOMICITYNUM");
		// 客户端传入的验证码
		String randNumInput = request.getParameter("randNumInput");
		
		// 客户端传入的用户编号
		String username = request.getParameter("username");
		
		// 客户端传入的密码
		String password = request.getParameter("password");	
		//回传用户代码
		request.setAttribute("username", username);

		// 验证验证码
		if (randNumSys == null
				|| (randNumSys != null && !randNumSys.toUpperCase().equals(
						randNumInput.toUpperCase()))) { 
			addReturnValue(-1, 131202);
			return "fail";
		}
		//通过用户代码得到用户
		user = userService.getUserByName(username);
		if (user == null) {
			addReturnValue(-1,131203 );
			return "fail";
		}else if (user != null && !password.equals(user.getPassword())) {
			addReturnValue(-1,131203 );
			return "fail";
		}
		
		Authentication authentication = myAuthenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(username,user.getPassword()));
		SecurityContext securityContext = SecurityContextHolder.getContext();
		securityContext.setAuthentication(authentication);
		HttpSession session = request.getSession(true);  
	    session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext); 
	 // 当验证都通过后，把用户信息放在session里
		request.getSession().setAttribute("CurrentUser", user);
		//System.out.println(user.getChangePwdDate()+"-----------------");
		if(user.getChangePwdDate() == null) {
			request.getSession().setAttribute("days", 1);
		}else
			request.getSession().setAttribute("days", 0);
		System.out.println(authentication.getPrincipal().toString());
		return "success";
	}
	
	
	
	/**
	 * 密码修改
	 */
	public void changePwd(){
		response.setContentType("text/html;charset=UTF-8");
		user = (User)request.getSession().getAttribute("CurrentUser");
		System.out.println(user);
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String oldPassword = request.getParameter("oldPwd");
		
		String password = request.getParameter("password");
		
		//验证旧密码是否正确
		
		
		if (oldPassword.equals(password)) {
			out.write("修改密码失败，新密码与原密码一致");
		}else if (!oldPassword.equals(user.getPassword())) {
			out.write("旧密码输入错误");
		}else {
			userService.changePwd(password, user.getUserId());
			out.write("修改密码成功");
		}

		
		
	}
	
	/**
	 * 退出系统
	 * @return
	 */
	public String logout(){
		//清除session中的信息
		request.getSession().invalidate();
		return "success";
		
	}
	
	
	/**
	 * 
	 * @throws Exception
	 */
	public void listAllUser() throws Exception{
		
		PrintWriter out = response.getWriter();
		
		request.getSession().removeAttribute("totalPage");
		
		//分页具备的参数 总记录数  总页数   当前页  每页的记录数
		///
		int curPage = Integer.valueOf(request.getParameter("curPage"));
		
		int pageCount = Integer.valueOf(request.getParameter("pageCount"));
		
		String key = request.getParameter("key");
		
		List<User> users = null;
		if ("".equals(key)) {
			users = userService.listAllUser();
		}else {
			users = userService.listUserByName(key);
		}
		
		
		int totalCount = users.size();  //////查询结果的数目

		
		////计算总页数
		int totalPage = 0;
		if (totalCount % pageCount == 0) {
			totalPage = totalCount / pageCount;
		}else {
			totalPage = totalCount / pageCount + 1;
		}
		
		request.getSession().setAttribute("curPage", curPage);
		
		//////每一页的记录
		int beginIndex = (curPage -1) * pageCount;
		
		List<User> usersPage = userService.listUserPage(beginIndex, pageCount, key);
		
		
		net.sf.json.JSONArray jsonArray = net.sf.json.JSONArray.fromObject(usersPage);
		
		result = jsonArray.toString();
		
		String jsonResult = "{}";
		
		jsonResult = "{";
		jsonResult += "\"totalPage\":" + totalPage + ',';
		jsonResult += "\"data\":" + result + "}";
		
		out.write(jsonResult);
		
		for (User user : usersPage) {
			System.out.println("user------"+user.getName()+user.getRoleName());
		}
		
		System.out.println("jsonResult:"+jsonResult);
		
	}
	
	
	public void getUserResources() throws Exception{
		PrintWriter out = response.getWriter();
		User user = (User)request.getSession().getAttribute("CurrentUser");
		List<String> lst = userService.getUserResources(user.getUserId(),user.getRoleName());
		result = lst.toString();
		System.out.println("userResources:"+result);
		out.write(result);
	}
	
	
	public String addUser(){
		
		
		
		String name = request.getParameter("add_userName");
		
		int depId = Integer.valueOf(request.getParameter("depName"));
		
		
		int age = Integer.valueOf(request.getParameter("add_userAge"));
		
		String sex = request.getParameter("add_userSex");
		
		String phone = request.getParameter("add_userPhone");
		
		String mail = request.getParameter("add_userMail");
		
		String date = request.getParameter("add_hireDate");
		
		String bankAccount = request.getParameter("add_bankAccount");
		
		int shouldWage = 0;
		if(request.getParameter("add_shouldWage") != null){
			shouldWage = Integer.valueOf(request.getParameter("add_shouldWage"));
		}
		 
		
		userService.addUser(name, "111111", age, sex, phone, mail, bankAccount, shouldWage, date);
		
		int userId = userService.maxId();
		
		userService.addUserDep(userId,depId);
		
		addReturnValue(1, 119901);
		
		return "success";
	}
	
	
	public void deleteSelectedUsers() throws Exception{
		PrintWriter out = response.getWriter();
		String ids = request.getParameter("ids");
		String[] id = ids.split(",");
		for (String str : id) {
			int i = Integer.valueOf(str);
			userService.deleteUser(i);
		}
		out.write("删除成功");
	}
	
	
	public String updateUser(){
		
		User user = (User)request.getSession().getAttribute("CurrentUser");///获取当前用户
		
		int age = Integer.valueOf(request.getParameter("edit_userAge"));
		String sex = request.getParameter("edit_userSex");
		String phone = request.getParameter("edit_userPhone");
		String mail = request.getParameter("edit_userMail");
		String account = request.getParameter("edit_bankAccount");
		
		userService.updateUser(user.getUserId(), age, sex, phone, mail, account);
		
		addReturnValue(1, 119902);
		
		return "success";
	}
	
	
	
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
		
	public AuthenticationManager getMyAuthenticationManager() {
		return myAuthenticationManager;
	}

	public void setMyAuthenticationManager(
			AuthenticationManager myAuthenticationManager) {
		this.myAuthenticationManager = myAuthenticationManager;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
