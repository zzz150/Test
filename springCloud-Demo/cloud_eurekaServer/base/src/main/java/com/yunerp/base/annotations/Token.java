package com.yunerp.base.annotations;
/**
 * 
 * @author:李建行
 * @version:1.0
 * @date:2016年4月23日下午7:03:14
 * @description:<li>业务描述：放置表单重复提交<li>
 */
public @interface Token {
	/**
	 * 
	 * @author:李建行
	 * @version:1.0
	 * @date:2016年4月23日 下午7:04:03
	 * @description:<li>方法描述：为每一个表单生成一个新的token值</li>
	 * @returnboolean
	 */
	boolean saveToken() default false;
	/**
	 * 
	 * @author:李建行
	 * @version:1.0
	 * @date:2016年4月23日 下午7:07:25
	 * @description:<li>方法描述：删除表单生成的token值</li>
	 * @return boolean
	 */
    boolean removeToken() default false;
}
