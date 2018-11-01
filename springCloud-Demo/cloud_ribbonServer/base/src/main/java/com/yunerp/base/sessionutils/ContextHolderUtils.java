package com.yunerp.base.sessionutils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
/**
 * 
 * @author:ljh
 * @version:1.0
 * @date:2016年5月9日上午9:18:11
 * @description:<li>业务描述：上下文工具类</li>
 */
public class ContextHolderUtils {
	/**
	 * 
	 * @author:ljh
	 * @version:1.0
	 * @date:2016年5月9日 上午9:17:26
	 * @description:<li>方法描述：SpringMvc下获取request</li>
	 * @return HttpServletRequest
	 */
	public static HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		return request;

	}
	/**
	 * 
	 * @author:ljh
	 * @version:1.0
	 * @date:2016年5月9日 上午9:17:39
	 * @description:<li>方法描述：SpringMvc下获取session</li>
	 * @return HttpSession
	 */
	public static HttpSession getSession() {
		HttpSession session = getRequest().getSession();
		return session;
	}

}
