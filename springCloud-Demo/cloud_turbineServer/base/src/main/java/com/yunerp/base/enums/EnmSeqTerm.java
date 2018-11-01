package com.yunerp.base.enums;

/**
 * 序列号生成的区间类别
 * All rights Reserved
 * @Title: 	EnumSeqTerm.java 
 * @Package com.yunerp.base.enums 
 * @Description:  
 * @author: alan
 * @date:	2016年6月13日 下午5:24:18 
 * @version	V1.0
 */
public enum EnmSeqTerm {
	NULL(0,9),	
	DAY(1,3),	
	MONTH(2,5),	
	YEAR(3,7);	
	
	private int id;
	private int seqLen;
	 
    private EnmSeqTerm(int id,int seqLen) {
        this.id = id;
        this.seqLen = seqLen;
    }

	public int getSeqLen() {
		return seqLen;
	}

	public int getId() {
		return id;
	}
	
	/**
	 * 根据Id得到对象
	* @author: alan
	* @date: 2016年6月13日
	* @Title: getById  
	* @param id
	* @return
	 */
	public static EnmSeqTerm getById(int id){
		for (EnmSeqTerm c : EnmSeqTerm.values()) {
			if(c.getId() == id){
				return c;
			}
		}
		return null;
	}
	
	/**
	 * 根据name得到枚举对象
	* @author: alan
	* @date: 2016年6月13日
	* @Title: getByName  
	* @param name
	* @return
	 */
	public static EnmSeqTerm getByName(String name){
		for (EnmSeqTerm c : EnmSeqTerm.values()) {
			if(c.name().equals(name)){
				return c;
			}
		}
		return null;
	}
}
