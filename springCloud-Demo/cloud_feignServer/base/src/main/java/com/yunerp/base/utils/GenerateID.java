package com.yunerp.base.utils;

import java.util.UUID;



public class GenerateID {
	/**
	 * 
	 * @author:ljh
	 * @version:1.0
	 * @date:2016年5月7日 下午3:09:41
	 * @description:<li>方法描述：UUID生成方法</li>
	 * @return String
	 */
    public static String getGenerateID(){
        String id = UUID.randomUUID().toString().replaceAll("-","");
        return id;
    }
    /**
     * 
     * @author:ljh
     * @version:1.0
     * @date:2016年6月21日下午5:07:15
     * @description:<li>方法描述：返回主键</li>
     * @return Long
     */
    public static Long getId(){
    	return System.currentTimeMillis();
    }
}
