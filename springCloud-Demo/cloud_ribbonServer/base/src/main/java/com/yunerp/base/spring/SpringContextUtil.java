package com.yunerp.base.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
/**
 * 
 * @author:ljh
 * @version:1.0
 * @date:2016年5月20日上午11:35:18
 * @description:<li>业务描述:获取bean工具类</li>
 */
public class SpringContextUtil implements ApplicationContextAware {  
	  
	    private static ApplicationContext applicationContext;  
	     
	    public void setApplicationContext(ApplicationContext applicationContext) {  
	        SpringContextUtil.applicationContext = applicationContext;  
	    }  
   
	    public static ApplicationContext getApplicationContext() {  
	        return applicationContext;  
	    }  
	    
	    public static Object getBean(String name) throws BeansException {  
	       return applicationContext.getBean(name);  
	    }  
	}
