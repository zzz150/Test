package com.yunerp.base.encrypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;
/**
 * 
 * @author:ljh
 * @version:1.0
 * @date:2016年5月5日下午4:03:34
 * @description:<li>业务描述：md5加密工具</li>
 */
public class MD5DigestUtil {
	/**
	 * 
	 * @author:ljh
	 * @version:1.0
	 * @date:2016年5月5日 下午4:03:52
	 * @description:<li>方法描述：md5加密方法</li>
	 * @param seq <li>要加密的字符串</li>
	 * @return String
	 */
	public static String md5Digest(String seq) {
        try {
            MessageDigest md5Code =MessageDigest.getInstance("md5");
            byte[] bTmp=md5Code.digest(seq.getBytes());
            BASE64Encoder base64=new BASE64Encoder();
           String str=base64.encode(bTmp);
            return str;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    };
    public static void main(String[] args) {
		System.out.println(md5Digest("123456"));
	}
}
