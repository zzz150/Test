package com.yunerp.base.annotations.datasource;
/**
 * 为每一个线程放入数据源或者获取数据源
 * @author 李建行
 *
 * 2015-5-9
 */
public class DynamicDataSourceHolder {
    public static final ThreadLocal<String> holder = new ThreadLocal<String>();

    public static void putDataSource(String name) {
        holder.set(name);
    }

    public static String getDataSouce() {
        return holder.get();
    }
}
