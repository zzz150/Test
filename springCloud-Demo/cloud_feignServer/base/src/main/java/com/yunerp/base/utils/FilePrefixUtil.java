package com.yunerp.base.utils;

public class FilePrefixUtil {
	/**
	 * 
	 * @author:ljh
	 * @version:1.0
	 * @date:2015年12月1日下午2:58:25
	 * @description:<li>方法描述：获取文件或者图片的超链接前例如：http://127.0.0.1:8080/h-rps/</li>
	 * @return String
	 */
	public static String getFilePrefix(){
		return ConfigUtil.getValue("remote.file.server");
	}
	
	public static String getTelnetFilePrefix(){
		return ConfigUtil.getValue("remote.file.telnet");
	}
}
