package com.yunerp.base.json.parse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 
 *@ClassName:JSONParsetUtil
 *@Description:<li></li>
 *@Author ljh
 *@Date 2016年4月16日
 */
public class JSONParsetUtil {
	/**
	 * 
	 * @Description:<li>功能描述：处理返回值</li>
	 * @Author:ljh
	 * @Date:2016年4月16日
	 * @param code <p>状态码: 0.表示出错,1.表示正常</p>
	 * @param msg  <p>错误信息：java堆栈的异常信息</p>
	 * @param data <p>返回的数据：如果返回数据,设置为 null</p>
	 * @return String
	 */
	public static  String parseStringJSON(int code,String msg,Object data){
		msg = msg == null? "":msg;
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("code", code);
		map.put("msg", msg);
		map.put("data", data);
		return JSON.toJSONString(map);
	}
	/**
	 * 
	 * @Description:<li>将json格式的字符串转换为对象</li>
	 * @Author:ljh
	 * @Date:2016年4月16日
	 * @param josnStr
	 * @param clazz
	 * @return T
	 */
	public static <T> T parseStrToObject(String josnStr,Class<T> clazz){
		return JSON.parseObject(josnStr,clazz);
	}
	/**
	 * 
	 * @Description:<li>将json格式的字符串转换为集合List</li>
	 * @Author:ljh
	 * @Date:2016年4月16日
	 * @param jsonStr
	 * @param clazz
	 * @return List<T>
	 */
	public static <T> List<T> parseStringToArray(String jsonStr,Class<T> clazz){
		return JSON.parseArray(jsonStr, clazz);
	}
	/**
	 * 
	 * @author:ljh
	 * @version:1.0
	 * @date:2016年5月7日 上午11:34:43
	 * @description:<li>方法描述：根据传入的key,获取JSON格式字符串中的值</li>
	 * @param jsonStr <li>JSON格式字符串</li>
	 * @param key     <li>属性Key</li>
	 * @return Object <li>返回值</li>
	 */
	public static Object parseObj(String jsonStr,String key){
		JSONObject jsonObject = JSON.parseObject(jsonStr);
		return jsonObject.get(key);
	}
}
