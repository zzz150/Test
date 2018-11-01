package com.yunerp.base.picture;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.log4j.Logger;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Base64ImageUtils {
	private static final Logger logger=Logger.getLogger(Base64ImageUtils.class);
	/**
	 * 图片转化成base64字符串
	 * @return
	 * @throws IOException 
	 */
	public static String imageToBase64(String imagefilePath) throws IOException {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
		try {
			InputStream in = new FileInputStream(imagefilePath);
			byte[] data = new byte[in.available()];
			in.read(data);
			in.close();
			//对字节数组Base64编码
			BASE64Encoder encoder = new BASE64Encoder();
			return encoder.encode(data);
		} catch (FileNotFoundException e) {
			logger.error("image at this path not exists:"+imagefilePath,e);
			return null;
		}
	}

	/**
	 * base64字符串转化成图片
	 * @param imgStr
	 * @return
	 */
	public static boolean base64ToImage(String imgStr,String imgPath) { // 对字节数组字符串进行Base64解码并生成图片
		if (imgStr == null){
			return false;
		}
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			//Base64解码
			byte[] b = decoder.decodeBuffer(imgStr);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {
					b[i] += 256;
				}
			}
			//生成jpeg图片
			OutputStream out = new FileOutputStream(imgPath);
			out.write(b);
			out.flush();
			out.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
