package com.yunerp.base.enums;

import org.directwebremoting.annotations.DataTransferObject;
import org.springframework.util.StringUtils;

/**
 * 在生成序列号和创建/返回barcode的时候使用 All rights Reserved
 * 
 * @Title: EnmBizType.java
 * @Package com.yunerp.base.enums
 * @Description:
 * @author: alan
 * @date: 2016年6月13日 下午9:30:35
 * @version V1.0
 */
@DataTransferObject
public enum EnmBizType {
	
	NONE(-1),	

	// ============基础=============0-20
	BASE_GOODS(0), 
	BASE_GOODS_SPEC(1), 
	BASE_TOOL(2), 
	BASE_TOOLPACK(3), 
	BASE_DEVICE(4), 
	BASE_OPPACK(5), 
	BASE_GOODS_YH(11), 

	// ===========采购==============21-30
	PURCHASE_APPLY(21), 
	PURCHASE_ORDER(22), 
	PURCHASE_PLAN(23), 

	// ===========仓库===============31-60
	STORE_OUT(31), 
	STORE_OUT_TRANSFER(32), 
	STORE_OUT_HIGHVAL(33), 
	STORE_OUT_REAGENT(34), 
	STORE_OUT_DEVICE(35), 
	STORE_OUT_DIRECT(36), 
	STORE_OUT_CHANNEL(37), 
	STORE_OUT_OTHER(38), 

	STORE_IN(45), 
	STORE_IN_DEVICE(46), 
	STORE_IN_OTHER(47), 
	
	STORE_CHECK(51), 
	STORE_REVERSE(52), 
	STORE_BACK(53), 

	// =============销售============61-70
	SALES_ORDER(61), 
	SALES_DELIVER(62), 
	SALES_BACKORDER(63), 

	// ===========手术==============71-80
	OPERATE_ORDER(71), 

	// =============结算==============81-90
	SETTLE_CHECK(81), 
	SETTLE_CONFIRM(82), 
	SETTLE_PAYBILL(83), 

	;

	private int id;
	private String code;

	private EnmBizType(int id) {
		this(id, "");
	}

	private EnmBizType(int id, String code) {
		this.id = id;
		this.code = code;
	}

	public int getId() {
		return id;
	}

	public String getCode() {
		if (StringUtils.isEmpty(this.code))
			return this.name();
		return code;
	}

	/**
	 * 根据Id得到对象
	 * 
	 * @author: alan
	 * @date: 2016年6月13日
	 * @Title: getById
	 * @param id
	 * @return
	 */
	public static EnmBizType getById(int id) {
		for (EnmBizType c : EnmBizType.values()) {
			if (c.getId() == id) {
				return c;
			}
		}
		return null;
	}

	/**
	 * 根据name得到枚举对象
	 * 
	 * @author: alan
	 * @date: 2016年6月13日
	 * @Title: getByName
	 * @param name
	 * @return
	 */
	public static EnmBizType getByName(String name) {
		for (EnmBizType c : EnmBizType.values()) {
			if (c.name().equals(name)) {
				return c;
			}
		}
		return null;
	}
}
