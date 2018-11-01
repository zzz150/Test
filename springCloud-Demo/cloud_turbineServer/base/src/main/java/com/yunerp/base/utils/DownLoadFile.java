package com.yunerp.base.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class DownLoadFile {
	/**
	 * 
	 * @author:ljh
	 * @version:1.0
	 * @date:2016年5月24日下午6:31:04
	 * @description:<li>方法描述：根据url下载文件</li>
	 * @param urlString <li>根据url下载文件</li>
	 * @param filename <li>临时保存文件的</li>
	 * @throws Exception void
	 */
	public static void download(String urlString, String direct,String filename) throws Exception {
	    URL url = new URL(urlString);
	    URLConnection con = url.openConnection();
	    InputStream is = con.getInputStream();
	    byte[] bs = new byte[1024*2];//2k
	    int len;
	    File file = new File(filename);
	    File rootFile = new File(direct);
	    if(!rootFile.exists()) rootFile.mkdirs();
	    	file.createNewFile();
	    OutputStream os = new FileOutputStream(file);
	    while ((len = is.read(bs)) != -1) {
	      os.write(bs, 0, len);
	    }
	    os.close();
	    is.close();
	}   
}
