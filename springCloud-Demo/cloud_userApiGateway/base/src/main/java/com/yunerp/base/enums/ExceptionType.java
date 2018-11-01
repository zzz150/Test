package com.yunerp.base.enums;
/**
 * 
 * @author:ljh
 * @version:1.0
 * @date:2016年6月21日下午4:16:48
 * @description:<li>业务描述:异常类型</li>
 */
public enum ExceptionType {
	NO_ERROR{
		public  Byte getExceptionType(){
			return 0;
		}
	},
	NET_ERROR{
		public  Byte getExceptionType(){
			return 1;
		}
	},
	DB_ERROR{
		public  Byte getExceptionType(){
			return 2;
		}
	},
	CALL_ERROR{
		public  Byte getExceptionType(){
			return 3;
		}
	};
	public abstract Byte getExceptionType();
}
