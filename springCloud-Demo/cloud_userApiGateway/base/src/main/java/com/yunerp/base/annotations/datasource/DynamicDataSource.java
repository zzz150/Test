package com.yunerp.base.annotations.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
/**
 * 项目启动时加载spring容器，并将读,写数据源放入到sprig容器里面
 * 
 * @author 李建行
 *
 * 2015-5-9
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceHolder.getDataSouce();
    }
    
}
