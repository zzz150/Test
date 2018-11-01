package com.yunerp.base.utils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class StrUtil {
	/**
	 * 得到定长的字符串，不够前面填0
	* @author: alan
	* @date: 2016年6月17日
	* @Title: getZeroStr  
	* @param s
	* @param len
	* @return
	 */
	public static String getFixedlenStr(String s,int len){
		int l = s.length();
		if(l == len) return s;
		if(l>len) return s.substring(l-len);
		char[] cs = new char[len-l];
		for (int i=0;i<len-l;i++) {
			cs[i] = '0';
		}
		return String.valueOf(cs) + s;
		
	}
	/**
	 * 返回去除前面0的值
	* @author: alan
	* @date: 2016年6月17日
	* @Title: removePreZero  
	* @param s
	* @return
	 */
	public static String removePreZero(String s){
		return s.replaceFirst("^0*", ""); 
	}
	
	/**
	 * 得到大些金额
	* @author: alan
	* @date: 2016年6月17日
	* @Title: getBigRMB  
	* @param value
	* @return
	 */
	public static String parseToBigRMB(double value) {
		char[] hunit = { '拾', '佰', '仟' };
		char[] vunit = { '万', '亿' };
		char[] digit = { '零', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖' };
		String minus = "";
		if (value < 0) {
			minus = "负";
			value = 0 - value;
		}
		long midVal = Math.round(value * 100);
		if (midVal == 0)
			return "零圆整";
		String valStr = String.valueOf(midVal);
		String head = valStr.substring(0, valStr.length() - 2);
		String rail = valStr.substring(valStr.length() - 2);

		String prefix = "";
		String suffix = "";
		
		if (rail.equals("00")) {
			suffix = "整";
		} else {
			suffix = digit[rail.charAt(0) - '0'] + "角"
					+ digit[rail.charAt(1) - '0'] + "分";
		}
		
		char[] chDig = head.toCharArray();
		char zero = '0';
		byte zeroSerNum = 0;
		for (int i = 0; i < chDig.length; i++) {
			int idx = (chDig.length - i - 1) % 4;
			int vidx = (chDig.length - i - 1) / 4;
			if (chDig[i] == '0') {
				zeroSerNum++;
				if (zero == '0') {
					zero = digit[0];
				}
				
				if (idx == 0 && vidx > 0 && zeroSerNum < 4) {
					prefix += vunit[vidx - 1];
					zero = '0';
				}
				continue;
			}
			zeroSerNum = 0;
			if (zero != '0') {
				prefix += zero;
				zero = '0';
			}
			prefix += digit[chDig[i] - '0'];
			if (idx > 0)
				prefix += hunit[idx - 1];
			if (idx == 0 && vidx > 0) {
				prefix += vunit[vidx - 1];
			}
		}

		if (prefix.length() > 0)
			prefix += '圆';
		return minus + prefix + suffix;
	}
	
	/**
	 * 根据属性，获得list对应属性的值的数组
	* @author: alan
	* @date: 2016年7月6日
	* @Title: parsePropValues  
	* @param list
	* @param fieldName
	* @return
	 */
	public static String[] parsePropValues(List list,String fieldName){
		if(list==null || list.size()==0) return null;
		String[] rst = new String[list.size()];
		try {
			Object obj = list.get(0);
			Field ff = obj.getClass().getDeclaredField(fieldName);
			PropertyDescriptor pd = new PropertyDescriptor(ff.getName(), obj.getClass());    
		    Method rM = pd.getReadMethod();
			for (int i = 0; i < list.size(); i++) {
				obj = list.get(i);
			    rst[i] =  rM.invoke(obj).toString();    
			}
			return rst;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * join 属性的值
	 * @author:wzh
	 * @version:1.0
	 * @date:2016年7月8日上午11:26:24
	 * @description:<li>方法描述：<>
	 * @param list
	 * @param fieldName
	 * @return  String
	 */
	public static String joinPropValues(List list,String fieldName){
		String[] rst = parsePropValues(list, fieldName);
		return StringUtils.join(rst, ",");
	}
	
}
