package com.yunerp.base.resourcebundle;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * 资源国际化
 * @author Administrator
 *
 */
public class Resource {
	public static void main(String[] args) {
		System.setProperty("language", "CN");
		System.setProperty("language", "US");
		String value = System.getProperty("language");
		
		System.out.println(value);
		/*Locale locale1 = new Locale("zh", "CN"); 
        ResourceBundle resb1 = ResourceBundle.getBundle("config", locale1); 
        System.out.println(resb1.getString("userName")); 

        ResourceBundle resb2 = ResourceBundle.getBundle("config", Locale.getDefault()); 
        System.out.println(resb2.getString("userName")); 

        Locale locale3 = new Locale("en", "US"); 
        ResourceBundle resb3 = ResourceBundle.getBundle("config", locale3); 
        System.out.println(resb3.getString("userName")); */
	}
}
