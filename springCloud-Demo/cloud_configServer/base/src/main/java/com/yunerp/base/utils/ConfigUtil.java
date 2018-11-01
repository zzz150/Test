package com.yunerp.base.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigUtil {
	private static Properties prop = new Properties();
	static {
		InputStream in = ConfigUtil.class.getClassLoader().getResourceAsStream("config.properties");
		try {
			prop.load(in);
		} catch (IOException e) {
			throw new RuntimeException("A error happens when reading config.properties", e);
		}
	}

	public static String getValue(String key) {
		return prop.getProperty(key);
	}
	public static int getParseInt(String key) {
		return Integer.valueOf(prop.getProperty(key));
	}
}