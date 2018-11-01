package com.yunerp.base.web;

import java.io.Serializable;

import org.directwebremoting.annotations.DataTransferObject;
/**
 * 
 * @author lk
 * @version  1.0	
 * @date 2016年5月6日下午5:35:09
 * @description <li>列表树工具类</li>
 */
@DataTransferObject
public class TreeEntity extends BaseTree implements Serializable{

	private static final long serialVersionUID = -5310670291682195987L;
	
	private Integer areaId;

    private Long sysId;

    private Long level;
    
	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public Long getSysId() {
		return sysId;
	}

	public void setSysId(Long sysId) {
		this.sysId = sysId;
	}

	public Long getLevel() {
		return level;
	}

	public void setLevel(Long level) {
		this.level = level;
	}

    




	
}
