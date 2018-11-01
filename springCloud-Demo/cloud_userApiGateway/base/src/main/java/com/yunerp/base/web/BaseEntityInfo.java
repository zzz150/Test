package com.yunerp.base.web;

/**
 * 基本的Entity类的信息
 * V1.0: 主要是返回给页面的错误Id和错误信息。
 * All rights Reserved
 * @Title: 	BaseEntityInfo.java 
 * @Package com.yunerp.base.web 
 * @Description:  
 * @author: alan
 * @date:	2016年6月4日 下午3:47:18 
 * @version	V1.0
 */

import org.directwebremoting.annotations.DataTransferObject;
@DataTransferObject
public class BaseEntityInfo {
	
	private int webErrorId;
	
	private String webErrorDesc;

	public int getWebErrorId() {
		return webErrorId;
	}

	public void setWebErrorId(int webErrorId) {
		this.webErrorId = webErrorId;
	}

	public String getWebErrorDesc() {
		return webErrorDesc;
	}

	public void setWebErrorDesc(String webErrorDesc) {
		this.webErrorDesc = webErrorDesc;
	}
	
	
}
