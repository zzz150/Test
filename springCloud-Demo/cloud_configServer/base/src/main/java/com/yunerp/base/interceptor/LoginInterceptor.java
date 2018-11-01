package com.yunerp.base.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.yunerp.base.utils.ServerInfo;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

public class LoginInterceptor extends HandlerInterceptorAdapter {


	@Autowired
	private InternalResourceViewResolver jspViewResolver;

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		Object sessionObj = request.getSession().getAttribute("userInfo");  
		Object sessionClient = request.getSession().getAttribute("sysClient");  
		String uri = request.getRequestURI();

		boolean isLogin = uri.indexOf("login/toLogin")>0 || 
				uri.indexOf("login/userLogin")>0;
		boolean isResource = uri.indexOf("/resourcs/")>0 || uri.endsWith(".html");
		boolean isRegister = uri.indexOf("register/")>0;
		boolean splLogin = uri.indexOf("splLogin/")>0;
	    if(isLogin || isResource || sessionObj!=null || isRegister || splLogin || sessionClient != null) {

			String customPage = request.getParameter("customPage");
			if(customPage != null){
				jspViewResolver.setPrefix("/WEB-INF/customPage/");
			}else {
				jspViewResolver.setPrefix("/WEB-INF/page/");
			}

			return true;
	    } 

	    if (ServerInfo.isAjax(request)) {

			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.println("{\"statusCode\":\"301\", \"message\":\"系统超时,请重新登录\"}");
			out.flush();
		} else {

			String webpath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
		    response.sendRedirect(webpath + "timeout.html");
		}
		return false;
	}
	
}
