package com.yunerp.base.utils;

import java.util.HashMap;
import java.util.Map;
/**
 * 
 * @author:ljh
 * @version:1.0
 * @date:2016年5月6日下午6:21:30
 * @description:<li>业务描述：包装返回JSON格式的数据</li>
 */
public class MapPackResult {
	/**
	 * 
	 * @author:ljh
	 * @version:1.0
	 * @date:2016年5月6日 下午6:18:25
	 * @description:<li>方法描述：封装返回的JSON格式的数据</li>
	 * @param code <li>状态码: 0.表示出错,1.表示正常</li>
	 * @param msg  <p>错误信息：java堆栈的异常信息</p>
	 * @param data <p>返回的数据：如果返回数据,设置为 null</p>
	 * @return Map<String,Object>
	 */
	public static Map<String,Object> packData(int code,String msg,Object data){
		msg = msg == null? "":msg;
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("code", code);
		map.put("msg", msg);
		map.put("data", data);
		return map;
	}
}
