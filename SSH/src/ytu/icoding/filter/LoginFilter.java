package ytu.icoding.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ytu.icoding.entity.User;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter("/LoginFilter")
public class LoginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LoginFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain chain) throws IOException, ServletException {

		
		HttpServletRequest request=(HttpServletRequest) arg0;
		HttpServletResponse response=(HttpServletResponse) arg1;
		//获取到session对象，登录状态信息存放在里面
		HttpSession session=request.getSession();
		
		//获取请求路径
		String url=request.getServletPath();
		//System.out.println("ServletPath="+url);
		
		//设置检测一切非/login.jsp,/login.action,图片,js,css的请求   
		
		if(!url.contains("/login.jsp") && !url.contains("/login.action") && !url.endsWith(".js") && !url.endsWith(".css") && !url.endsWith("png")
				&& !url.endsWith(".jpg") && !url.endsWith(".gif") && !url.contains("logoncheckimage.jsp")){
			User user = (User)session.getAttribute("CurrentUser");
			if(user == null){///没有用户登录  重定向
				response.sendRedirect("/SSH/login.jsp");
				return ;
			}else{
				chain.doFilter(arg0, arg1);
			}
		}else {
			chain.doFilter(arg0, arg1);
		}
		
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
