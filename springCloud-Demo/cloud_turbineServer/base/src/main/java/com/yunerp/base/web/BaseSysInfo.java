package com.yunerp.base.web;

/**
 * 基本系统信息
 * All rights Reserved
 * @Title: 	BaseSysInfo.java 
 * @Package com.yunerp.base.web 
 * @Description:  
 * @author: alan
 * @date:	2016年6月16日 下午2:56:24 
 * @version	V1.0
 */
public class BaseSysInfo {
	private Long sysId;

	private Integer areaId;

	private Long clientId;

	private Byte sysType;

	private String sysCode;

	private Long sysParentId;

	private Integer sysLevel;

	private Integer sysIndex;

	private Byte sysInstallType;

	private String sysGuid;

	private String sysName;

	private String sysNamePy;

	private String sysApi;

	private String sysApiUrl;
	
	private Byte syncFlag;

	private Byte delFlag;
	
	private Byte ctrlFlag;
	
	private Byte startFlag;
	
	/*sysType 枚举开始  by cym*/
	
	public static final byte SYSTYPE_HOSPITAL = 0;
	public static final byte SYSTYPE_SUPPLY = 1;
	public static final byte SYSTYPE_PLATFORM = 2;
	public static final byte SYSTYPE_FACTORY = 3;
	
	/*sysType 枚举结束*/
	/**
	 * @return the sysId
	 */
	public Long getSysId() {
		return sysId;
	}

	/**
	 * @param sysId the sysId to set
	 */
	public void setSysId(Long sysId) {
		this.sysId = sysId;
	}

	/**
	 * @return the areaId
	 */
	public Integer getAreaId() {
		return areaId;
	}

	/**
	 * @param areaId the areaId to set
	 */
	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	/**
	 * @return the clientId
	 */
	public Long getClientId() {
		return clientId;
	}

	/**
	 * @param clientId the clientId to set
	 */
	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	/**
	 * @return the sysType
	 */
	public Byte getSysType() {
		return sysType;
	}

	/**
	 * @param sysType the sysType to set
	 */
	public void setSysType(Byte sysType) {
		this.sysType = sysType;
	}

	/**
	 * @return the sysCode
	 */
	public String getSysCode() {
		return sysCode;
	}

	/**
	 * @param sysCode the sysCode to set
	 */
	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}

	/**
	 * @return the sysParentId
	 */
	public Long getSysParentId() {
		return sysParentId;
	}

	/**
	 * @param sysParentId the sysParentId to set
	 */
	public void setSysParentId(Long sysParentId) {
		this.sysParentId = sysParentId;
	}

	/**
	 * @return the sysLevel
	 */
	public Integer getSysLevel() {
		return sysLevel;
	}

	/**
	 * @param sysLevel the sysLevel to set
	 */
	public void setSysLevel(Integer sysLevel) {
		this.sysLevel = sysLevel;
	}

	/**
	 * @return the sysIndex
	 */
	public Integer getSysIndex() {
		return sysIndex;
	}

	/**
	 * @param sysIndex the sysIndex to set
	 */
	public void setSysIndex(Integer sysIndex) {
		this.sysIndex = sysIndex;
	}

	/**
	 * @return the sysInstallType
	 */
	public Byte getSysInstallType() {
		return sysInstallType;
	}

	/**
	 * @param sysInstallType the sysInstallType to set
	 */
	public void setSysInstallType(Byte sysInstallType) {
		this.sysInstallType = sysInstallType;
	}

	/**
	 * @return the sysGuid
	 */
	public String getSysGuid() {
		return sysGuid;
	}

	/**
	 * @param sysGuid the sysGuid to set
	 */
	public void setSysGuid(String sysGuid) {
		this.sysGuid = sysGuid;
	}

	/**
	 * @return the sysName
	 */
	public String getSysName() {
		return sysName;
	}

	/**
	 * @param sysName the sysName to set
	 */
	public void setSysName(String sysName) {
		this.sysName = sysName;
	}

	/**
	 * @return the sysNamePy
	 */
	public String getSysNamePy() {
		return sysNamePy;
	}

	/**
	 * @param sysNamePy the sysNamePy to set
	 */
	public void setSysNamePy(String sysNamePy) {
		this.sysNamePy = sysNamePy;
	}

	/**
	 * @return the sysApi
	 */
	public String getSysApi() {
		return sysApi;
	}

	/**
	 * @param sysApi the sysApi to set
	 */
	public void setSysApi(String sysApi) {
		this.sysApi = sysApi;
	}

	/**
	 * @return the sysApiUrl
	 */
	public String getSysApiUrl() {
		return sysApiUrl;
	}

	/**
	 * @param sysApiUrl the sysApiUrl to set
	 */
	public void setSysApiUrl(String sysApiUrl) {
		this.sysApiUrl = sysApiUrl;
	}

	/**
	 * @return the syncFlag
	 */
	public Byte getSyncFlag() {
		return syncFlag;
	}

	/**
	 * @param syncFlag the syncFlag to set
	 */
	public void setSyncFlag(Byte syncFlag) {
		this.syncFlag = syncFlag;
	}

	/**
	 * @return the delFlag
	 */
	public Byte getDelFlag() {
		return delFlag;
	}

	/**
	 * @param delFlag the delFlag to set
	 */
	public void setDelFlag(Byte delFlag) {
		this.delFlag = delFlag;
	}

	public Byte getCtrlFlag() {
		return ctrlFlag;
	}

	public void setCtrlFlag(Byte ctrlFlag) {
		this.ctrlFlag = ctrlFlag;
	}

	public Byte getStartFlag() {
		return startFlag;
	}

	public void setStartFlag(Byte startFlag) {
		this.startFlag = startFlag;
	}
	
	
}
