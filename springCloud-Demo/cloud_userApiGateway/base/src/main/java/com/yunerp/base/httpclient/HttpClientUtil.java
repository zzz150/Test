package com.yunerp.base.httpclient;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yunerp.base.json.parse.JSONParsetUtil;
import com.yunerp.base.web.PicResult;
/**
 * 
 * @author:ljh
 * @version:1.0
 * @date:2016年4月29日下午2:35:06
 * @description:<li>业务描述：httpClien请求工具类</li>
 */
public class HttpClientUtil {
	/**
	 * 
	 * @author:李建行
	 * @version:1.0
	 * @date:2016年4月23日 下午9:50:28
	 * @description:<li>方法描述：</li>
	 * @param requestPath
	 *            void
	 */
	public void sendHttpGetRequest(String requestPath) {
		CloseableHttpClient httpClient = getHttpClient();
		try {
			HttpGet httpGet = new HttpGet(requestPath);
			CloseableHttpResponse httpResponse = null;
			httpResponse = httpClient.execute(httpGet);
			try {
				HttpEntity entity = httpResponse.getEntity();
				if (null != entity) {

					System.out.println("响应状态码:" + httpResponse.getStatusLine());
					System.out.println("响应内容:" + EntityUtils.toString(entity));
				}
			} finally {
				httpResponse.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				closeHttpClient(httpClient);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
	/**
	 * 
	 * @author:李建行
	 * @version:1.0
	 * @date:2016年4月23日 下午9:57:17
	 * @description:<li>方法描述：发送</li>
	 * @param url  
	 * @param param 
	 * @return int
	 */
	@SuppressWarnings("finally")
	public static int sendHttpPostRequest(String url,String param) {
		CloseableHttpClient httpClient = getHttpClient();
		Integer flag=0;
		try {
			HttpPost post = new HttpPost(url);
			
			List<NameValuePair> list = new ArrayList<NameValuePair>();
			list.add(new BasicNameValuePair("param",param));
			
			UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(list,"UTF-8");
			post.setEntity(uefEntity);
			
			CloseableHttpResponse httpResponse = httpClient.execute(post);
			try {
				HttpEntity entity = httpResponse.getEntity();
				if (null != entity) {
					String temp = EntityUtils.toString(entity);
					flag = Integer.valueOf(JSONObject.parse(temp).toString());
				}
			} finally {
				httpResponse.close();
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				closeHttpClient(httpClient);
				return flag;
			} catch (Exception e) {
				e.printStackTrace();
				return flag;
			}
		}

	}
	/**
	 * 单独线程进行Post，不必等待Post返回，伪异步。
	* @author: alan
	* @date: 2016年7月8日
	* @Title: sendAsyPostRequest  
	* @param url
	* @param param
	 */
	public static void sendAsyPostRequest(String url,String param) {
		HttpClientPost c = new HttpClientPost(url, param);
		Thread t = new Thread(c);
		t.start();
	}
	/**
	 * 
	 * @author:ljh
	 * @version:1.0
	 * @date:2016年6月21日上午10:15:16
	 * @description:<li>方法描述：向dubbo项目上传文件</li>
	 * @param url
	 * @param param
	 * @return PicResult
	 */
	public static PicResult dubboUploadFile(String url,String param){
		CloseableHttpClient httpClient = getHttpClient();
		PicResult picRuslt=null;
		try {
			HttpPost post = new HttpPost(url);
			
			List<NameValuePair> list = new ArrayList<NameValuePair>();
			list.add(new BasicNameValuePair("param",param));
			
			UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(list,"UTF-8");
			post.setEntity(uefEntity);
			
			CloseableHttpResponse httpResponse = httpClient.execute(post);
			try {
				HttpEntity entity = httpResponse.getEntity();
				if (null != entity) {
					String temp = EntityUtils.toString(entity);
					picRuslt = JSONParsetUtil.parseStrToObject(temp,PicResult.class);
				}
			} finally {
				httpResponse.close();
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				closeHttpClient(httpClient);
				return picRuslt;
			} catch (Exception e) {
				e.printStackTrace();
				return picRuslt;
			}
		}
	}
	/**
	 * 
	 * @author:ljh
	 * @version:1.0
	 * @date:2016年4月28日 下午2:51:57
	 * @description:<li>方法描述：存放文件的远程服务</li>
	 * @param targetServerPath <li>发送请求的路径</li>
	 * @param filePath       <li>文件在本机服务器上的的临时目录位置</>
	 * @param targetFilePath  <li>发送请求的路径</li>
	 * @throws ClientProtocolException
	 * @throws IOException void
	 */
	public static String HttpClientUpLoadFile(String targetServerPath, String filePath,String targetFilePath)
			throws ClientProtocolException, IOException {
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost post = new HttpPost(targetServerPath);
		FileBody fileBody = new FileBody(new File(filePath));
		StringBody stringBody = new StringBody(targetFilePath);
		MultipartEntity entity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE, null, Charset.forName("UTF-8"));
		entity.addPart("filePath",stringBody);
		entity.addPart("entity", fileBody);
		post.setEntity(entity);
		HttpResponse response = httpclient.execute(post);
		if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
			HttpEntity entitys = response.getEntity();
			if (entity != null) {
				String uploadResult = EntityUtils.toString(entitys);
				return uploadResult;
			}
		}
		httpclient.getConnectionManager().shutdown();
		return "";
	}

	private static CloseableHttpClient getHttpClient() {
		return HttpClients.createDefault();
	}

	private static void closeHttpClient(CloseableHttpClient client) throws IOException {
		if (client != null) {
			client.close();
		}
	}
}
