package com.yunerp.base.mybatis.interceptor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.log4j.Logger;

import com.yunerp.base.mybatis.entity.SearchPageUtil;
import com.yunerp.base.utils.ReflectUtil;

/** 
 * @author lijianhang 
 * @date 2012-11-20 下午04:33:35 
 * @description 不在拦截器中计算总数，影响效率 
 * @version V1.0 
 *  
 */  
@SuppressWarnings("unused")
@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class }) })
public class PageInterceptor implements Interceptor {
	private String databaseType;
	
	private String sqlMethod;
	/**
	 * 拦截后要执行的方法
	 */
	public Object intercept(Invocation invocation) throws Throwable {
        
        final RoutingStatementHandler handler = (RoutingStatementHandler) invocation.getTarget();
        
        final StatementHandler delegate = (StatementHandler) ReflectUtil.getFieldValue(handler, "delegate");  
         
        final BoundSql boundSql = delegate.getBoundSql();  
        
        final Object obj = boundSql.getParameterObject();  
        
        if (obj instanceof SearchPageUtil) {  
            final SearchPageUtil page = (SearchPageUtil) obj;  
            MappedStatement mappedStatement = (MappedStatement)ReflectUtil.getFieldValue(delegate, "mappedStatement");  
            // 拦截到的prepare方法参数是一个Connection对象              
            Connection connection = (Connection)invocation.getArgs()[0];  
            final String sql = boundSql.getSql();  
            this.setTotalRecord(page, mappedStatement, connection);  
           final String pageSql = this.getPageSql(page, sql);  
            ReflectUtil.setFieldValue(boundSql, "sql", pageSql);  
            Logger logger = Logger.getLogger(PageInterceptor.class);
            logger.debug("pageSql:"+pageSql);
        }  
        return invocation.proceed();  
	}
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	public void setProperties(Properties properties) {
		this.databaseType = properties.getProperty("databaseType"); 
	}
	
	/** 
     * 根据page对象获取对应的分页查询Sql语句，这里只做了两种数据库类型，Mysql和Oracle 其它的数据库都 没有进行分页 
     *  
     * @param page 分页对象 
     * @param sql 原sql语句 
     * @return 
     */  
    private String getPageSql(SearchPageUtil page, String sql) {  
        final StringBuffer sqlBuffer = new StringBuffer(sql);  
        if ("mysql".equalsIgnoreCase(databaseType)) {  
            return getMysqlPageSql(page, sqlBuffer);  
        } else if ("oracle".equalsIgnoreCase(databaseType)) {  
            return getOraclePageSql(page, sqlBuffer);  
        }  
        return sqlBuffer.toString();  
    }  
    
	/** 
     * 获取Mysql数据库的分页查询语句 
     *  
     * @param page 分页对象 
     * @param sqlBuffer 包含原sql语句的StringBuffer对象 
     * @return Mysql数据库分页语句 
     */  
    private String getMysqlPageSql(SearchPageUtil page, StringBuffer sqlBuffer) {  
        sqlBuffer.append(" limit ").append(page.getPageIndex()*page.getPageSize()).append(",").append(page.getPageSize());  
        return sqlBuffer.toString();  
    }  
    /** 
     * 获取Oracle数据库的分页查询语句 
     *  
     * @param page 分页对象 
     * @param sqlBuffer 包含原sql语句的StringBuffer对象 
     * @return Oracle数据库的分页查询语句 
     */  
    private String getOraclePageSql(SearchPageUtil page, StringBuffer sqlBuffer) {  
        // 计算第一条记录的位置，Oracle分页是通过rownum进行的，而rownum是从1开始的  
        final int offset = (page.getPageIndex() - 1) * page.getPageSize() + 1;  
        sqlBuffer.insert(0, "select u.*, rownum r from (").append(") u where rownum < ")  
                .append(offset + page.getPageSize());  
        sqlBuffer.insert(0, "select * from (").append(") where r >= ").append(offset);  
        return sqlBuffer.toString();  
    }  
    
    /** 
     * 给当前的参数对象page设置总记录数 
     *  
     * @param page 
     *            Mapper映射语句对应的参数对象 
     * @param mappedStatement 
     *            Mapper映射语句 
     * @param connection 
     *            当前的数据库连接 
     */  
    private void setTotalRecord(SearchPageUtil page,MappedStatement mappedStatement,Connection connection) {  
        final BoundSql boundSql = mappedStatement.getBoundSql(page);  
        final String sql = boundSql.getSql();  
        final String countSql = this.getCountSql(sql);  
        final List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();  
        final BoundSql countBoundSql = new BoundSql(mappedStatement.getConfiguration(), countSql, parameterMappings,  
                page);  
        final ParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement, page, countBoundSql);  
        PreparedStatement pstmt = null;  
        ResultSet rs = null;  
        try {  
            pstmt = connection.prepareStatement(countSql);  
            parameterHandler.setParameters(pstmt);  
            rs = pstmt.executeQuery();  
            if (rs.next()) {  
                final int totalRecord = rs.getInt(1);  
                // 给当前的参数page对象设置总记录数  
                page.setTotal(totalRecord);
            }  
        } catch (SQLException e) {  
            e.printStackTrace();  
        } finally {  
            try {  
                if (rs != null)  
                    rs.close();  
                if (pstmt != null)  
                    pstmt.close();  
            } catch (SQLException e) {  
                e.printStackTrace();  
            }  
        }  
    }  
    
    /** 
     * 根据原Sql语句获取对应的查询总记录数的Sql语句 
     * @param sql 
     * @return 
     */  
	private String getCountSql(String sql) {  
        if(isComplexSQL(sql)){
        	String aSql = sql;
        	int i = sql.indexOf("order by");
        	if(i>0){
        		aSql = sql.substring(0, i);
        	}
        	return "select count(*) from (" + aSql + ") XYZ";
        }else{
            final int index = sql.toLowerCase().indexOf("from");  
            return "select count(*) " + sql.substring(index,sql.length());  
        }
    }  
	/**
	 * 判断是否复杂SQL
	* @author: alan
	* @date: 2016年6月8日
	* @Title: isComplexSQL  
	* @param sql
	* @return
	 */
	private boolean isComplexSQL(String sql){
		return true;

	}
	public String getDatabaseType() {
		return databaseType;
	}
	public void setDatabaseType(String databaseType) {
		this.databaseType = databaseType;
	}
}
