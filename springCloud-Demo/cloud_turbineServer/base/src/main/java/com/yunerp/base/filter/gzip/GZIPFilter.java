package com.yunerp.base.filter.gzip;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *描述：<li></li>
 *作者：李建行
 *创建时间:2016-4-10下午4:37:38
 */
public class GZIPFilter implements Filter{

	public void destroy() {
		
	}
	/**
	 * 
	 * 描述：<li>判断浏览器是否支持GZIP</li>
	 * 作者: 李建行
	 * 创建时间:2016-4-10下午4:42:36
	 * @param request
	 * @return boolean
	 */
	@SuppressWarnings("unused")
	private  boolean isGZipEncoding(HttpServletRequest request){
        String encoding=request.getHeader("Accept-Encoding");
        if(encoding.indexOf("gzip")!=-1) return true;
        return false;
	}
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		 HttpServletResponse response = (HttpServletResponse) res;
	     HttpServletRequest request=(HttpServletRequest)req;
	     if(isGZipEncoding(request)){
	    	 GZIPResponseWrapper gZIPResponseWrapper = new GZIPResponseWrapper(response);
	         chain.doFilter(req, gZIPResponseWrapper);
	         gZIPResponseWrapper.finishResponse();
	         return;
	     }
	     chain.doFilter(req, res);
	}

	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
