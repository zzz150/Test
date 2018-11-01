package com.yunerp.base.springMvc.dateconvert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;
/**
 * 
 * <p>功能描述：日期格式转换</p>
 * <p>form表单提交：(添加时间，修改时间)</p>
 * @author jianghang
 *
 */
public class StringConverteToDate implements Converter<String,Date>{

	@SuppressWarnings("deprecation")
	public Date convert(String source) {
		Date date=null;
		boolean flag = StringUtils.isEmpty(source);
		if(flag==false){
			try {
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				if(source.indexOf("GMT")!=-1){
					source = df.format(Date.parse(source));
				} 
				date = df.parse(source);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}			
		return date;
	}

}
