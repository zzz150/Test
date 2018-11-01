package com.yunerp.base.exception;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import com.yunerp.base.utils.ServerInfo;

public class CustomSimpleMappingExceptionResolver extends SimpleMappingExceptionResolver {
	
	protected final Logger log = Logger.getLogger(this.getClass().getName());
	  
	@Override
	protected ModelAndView doResolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		
		try {
			PrintWriter out = response.getWriter();
			
			log.error("error",ex);//记录异常信息
			
			if (ServerInfo.isAjax(request)) {//ajax
				response.setCharacterEncoding("UTF-8");
				out.println("{\"statusCode\":\"500\", \"message\":\"系统内部错误，请联系客服人员\"}");
				out.flush();
				return null;	 
			}else{
				ModelAndView model = new ModelAndView("500");
				return model;
			}
		} catch (IOException e1) {
			e1.printStackTrace();
			return null;
		}
	}
}
