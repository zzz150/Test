package com.yunerp.base.utils;

import com.alibaba.druid.filter.config.ConfigTools;
import com.alibaba.druid.pool.DruidDataSource;
/**
 * 数据库用户名解密
 * @author wzh
 *
 */

@SuppressWarnings("all")
public class DecryptDruidSource extends DruidDataSource {
	private final static String publicKey = ConfigUtil.getValue("publicKey");
	@Override
	public void setUsername(String dbUsername) {
		try {
			dbUsername = ConfigTools.decrypt(publicKey, dbUsername);
		} catch (Exception e) {
		e.printStackTrace();
		}
		super.setUsername(dbUsername);
	}

}
