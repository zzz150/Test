package com.yunerp.base.mybatis.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
/**
 * 
 * @author:ljh
 * @version:1.0
 * @date:2016年4月27日下午4:57:07
 * @description:<li>业务描述：分页查询以及根据条件查询工具封装</li>
 */
public class SearchPageUtil implements Serializable{
	private static final long serialVersionUID = 1L;

	private int total;
	
	private int pageIndex;
	
	private int pageSize;
	
	private String sortField;
	
	private String sortOrder;
	
	
	
	private Map<String,Object> map=new HashMap<String,Object>();
	
	/**
	 * added by Alan 
	 */
	private Object fields;

	public Object getFields() {
		return fields;
	}

	public void setFields(Object fields) {
		this.fields = fields;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/*public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}*/

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public String getSortField() {
		return sortField;
	}

	public void setSortField(String sortField) {
		this.sortField = sortField;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
	
	
}
