package com.yunerp.base.utils;

import org.springframework.util.StringUtils;

/**
 * @author:xiaolong
 * @version:1.0
 * @date:2016年6月20日下午3:28:03
 * @description:<li>业务描述：字符串操作工具类</li>
 */
public class StringUtil {

	/**
	 * @author:xiaolong
	 * @version:1.0
	 * @date:2016年6月20日下午3:28:24
	 * @description:<li>方法描述：如果为空则返回空字符串</li>
	 * @param obj
	 * @return String
	 */
	public static String getString(Object obj){
		if(StringUtils.isEmpty(obj))return "";
		return obj.toString();
	}
	
	
	
}
