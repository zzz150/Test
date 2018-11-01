package com.yunerp.base.annotations.authority;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import com.yunerp.base.annotations.Authority;
import com.yunerp.base.annotations.datasource.DynamicDataSourceHolder;
/**
 * 
 * @author:李建行
 * @version:1.0
 * @date:2016年4月23日下午7:01:50
 * @description:<li>业务描述:验证是否允许用户访问<li>
 */
public class AuthorityAspect {
	/**
	 * 
	 * @author:李建行
	 * @version:1.0
	 * @date:2016年4月23日 下午6:09:48
	 * @description:<li>方法描述：</li>
	 * @param pointvoid
	 */
	public void before(JoinPoint point)
    {
        Object target = point.getTarget();
        String method = point.getSignature().getName();
        Class<?>[] classz = target.getClass().getInterfaces();
        Class<?>[] parameterTypes = ((MethodSignature) point.getSignature())
                .getMethod().getParameterTypes();
        try {
            Method m = classz[0].getMethod(method, parameterTypes);
            boolean isFlag = m.isAnnotationPresent(Authority.class);
            System.out.println("表示："+isFlag);
            if (m != null && m.isAnnotationPresent(Authority.class)) {
            	Authority data = m
                        .getAnnotation(Authority.class);
                DynamicDataSourceHolder.putDataSource(data.value());
            }
        } catch (Exception e) {
            
        }
    }
}
