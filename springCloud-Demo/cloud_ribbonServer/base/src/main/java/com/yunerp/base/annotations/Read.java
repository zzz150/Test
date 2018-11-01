package com.yunerp.base.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @author:ljh
 * @version:1.0
 * @date:2016年5月4日下午1:37:28
 * @description:<li>业务描述：控制读的数据源</li>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Read {
	String value() default "read";
}
