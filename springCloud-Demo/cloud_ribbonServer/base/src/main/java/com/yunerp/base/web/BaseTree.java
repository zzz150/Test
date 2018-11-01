package com.yunerp.base.web;

import java.io.Serializable;

import org.directwebremoting.annotations.DataTransferObject;
@DataTransferObject
public class BaseTree implements Serializable{
	private static final long serialVersionUID = 8904051424735271669L;

	private String name;
	private String id;


	private String pid;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}
}
