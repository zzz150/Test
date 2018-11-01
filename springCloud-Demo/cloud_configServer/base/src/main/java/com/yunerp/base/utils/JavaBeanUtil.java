package com.yunerp.base.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

/**
 * @author:xiaolong
 * @version:1.0
 * @date:2016年6月20日下午4:10:28
 * @description:<li>业务描述：javabean 操作工具类</li>
 */
public class JavaBeanUtil {

	/**
	 * 把JavaBean的from的值自动set给to，省略了自己从from中get然后再set给to  
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Object coverBean2Bean(Object from, Object to) {
		Class fClass = from.getClass();
		Class tClass = to.getClass();
		
		Field[] cFields = tClass.getDeclaredFields();
		try {
			for (Field field : cFields) {
				String cKey = field.getName();
				
				cKey = cKey.substring(0, 1).toUpperCase() + cKey.substring(1);

				Method fMethod;
				Object fValue;
				try {
					fMethod = fClass.getMethod("get" + cKey);// public方法
					fValue = fMethod.invoke(from);// 取getfKey的值
				} catch (Exception e) {
					// 如果from没有此属性的get方法，跳过
					continue;
				}

				try {
					Method cMethod = tClass.getMethod("set" + cKey,
							fMethod.getReturnType());
					cMethod.invoke(to, fValue);
				} catch (Exception e) {
					// 如果to没有此属性set方法，跳过
					continue;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return to;
	}
	

	public static void transMap2Bean2(Map<String, Object> map, Object obj) {
		if (map == null || obj == null) {
			return;
		}
		try {
			BeanUtils.populate(obj, map);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Map<String, Object> transBean2Map(Object obj) {

		if (obj == null) {
			return null;
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for (PropertyDescriptor property : propertyDescriptors) {
				String key = property.getName();

				// 过滤class属性
				if (!key.equals("class")) {
					// 得到property对应的getter方法
					Method getter = property.getReadMethod();
					Object value = getter.invoke(obj);

					map.put(key, value);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	
}
