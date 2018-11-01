package com.yunerp.base.annotations.datasource;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import com.yunerp.base.annotations.DataSource;
/**
 * 在方法调用前将决定是用读取数据源的库，还是写入数据源的库
 * @author 李建行
 *
 * 2015-5-9
 */
public class DataSourceAspect {
    public void before(JoinPoint point)
    {
        Object target = point.getTarget();
        String method = point.getSignature().getName();
        Class<?>[] classz = target.getClass().getInterfaces();
        Class<?>[] parameterTypes = ((MethodSignature) point.getSignature())
                .getMethod().getParameterTypes();
        try {
            Method m = classz[0].getMethod(method, parameterTypes);
            boolean isFlag = m.isAnnotationPresent(DataSource.class);
            System.out.println("表示："+isFlag);
            if (m != null && m.isAnnotationPresent(DataSource.class)) {
                DataSource data = m
                        .getAnnotation(DataSource.class);
                DynamicDataSourceHolder.putDataSource(data.value());
            }
        } catch (Exception e) {
            
        }
    }
}
