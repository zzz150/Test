package com.yunerp.base.utils;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtilsBean;

/**
 * @version:1.0
 * @date:2016年5月10日下午6:36:34
 * @description:<li>业务描述：集合操作常用方法</li>
 */
public class ConnectionsUtils {

	/**
	 * 
	 * @author:xiaolong
	 * @version:1.0
	 * @date:2016年5月10日下午6:37:20
	 * @description:<li>方法描述：将javabean实体类转为map类型，然后返回一个map类型的值(如果有空值 则返回空字符串)</li>
	 * @param obj
	 * @returnMap<String,Object>
	 */
	public static Map<String, Object> beanToMap(Object obj) {
		Map<String, Object> params = new HashMap<String, Object>(0);
		try {
			PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean();
			PropertyDescriptor[] descriptors = propertyUtilsBean
					.getPropertyDescriptors(obj);
			for (int i = 0; i < descriptors.length; i++) {
				String name = descriptors[i].getName();
				if (!"class".equals(name)) {
					if(propertyUtilsBean.getNestedProperty(obj, name)==null){
						params.put(name,"");
					}else{
						params.put(name,propertyUtilsBean.getNestedProperty(obj, name));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return params;
	}

	
	/**
	 * @author:xiaolong
	 * @version:1.0
	 * @date:2016年6月28日上午11:09:10
	 * @description:<li>方法描述：根据pagesize分割成若干个list</li>
	 * @param list     待分割的list
	 * @param pageSize 每段list的大小
	 * @returnList<List<T>>
	 */
	public static <T> List<List<T>> splitList(List<T> list, int pageSize) {
		int listSize = list.size();// list的大小
		int page = (listSize + (pageSize - 1)) / pageSize;// 页数
		List<List<T>> listArray = new ArrayList<List<T>>();
															
		for (int i = 0; i < page; i++) { 
			List<T> subList = new ArrayList<T>();
			for (int j = 0; j < listSize; j++) { 
				int pageIndex = ((j + 1) + (pageSize - 1)) / pageSize;
				if (pageIndex == (i + 1)) { 
					subList.add(list.get(j));
				}
				if ((j + 1) == ((j + 1) * pageSize)) { 
					break;
				}
			}
			listArray.add(subList);
		}
		return listArray;
	}
	
	
	
}
