package com.yunerp.base.web;

import javax.servlet.http.HttpSession;

import com.yunerp.base.sessionutils.ContextHolderUtils;
public class Session {
	/**
	 * 得到基本用户信息
	* @author: alan
	* @date: 2016年5月17日
	* @Title: getUserInfo  
	* @return
	 */
	public static BaseUserInfo getUserInfo(){
		HttpSession session = ContextHolderUtils.getSession();
		BaseUserInfo userInfo = (BaseUserInfo) session.getAttribute("userInfo");
		return userInfo;
	}
	
	/**
	 * 得到基本系统信息
	* @author: alan
	* @date: 2016年6月16日
	* @Title: getUserSysInfo  
	* @return
	 */
	public static BaseSysInfo getUserSysInfo(){
		HttpSession session = ContextHolderUtils.getSession();
		BaseSysInfo sysInfo = (BaseSysInfo) session.getAttribute("sysInfo");
		return sysInfo;
	}
	
	
}
