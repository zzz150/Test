package com.yunerp.base.utils;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @author:ljh
 * @version:1.0
 * @date:2016年5月23日下午1:56:02
 * @description:<li>业务描述:</li>
 */
public class SMSUtil {
	/**
	 * key:mobileNo:[key:verifyCode,value:sendDate]
	 */
	public static Map<String,Map<String,Date>> smsStore=new HashMap<String,Map<String,Date>>();
	
	private static String url=ConfigUtil.getValue("SMS.url");
	private static String user=ConfigUtil.getValue("SMS.usr");
	private static String psw=ConfigUtil.getValue("SMS.psw");
	private static String authkey=ConfigUtil.getValue("SMS.authkey");
	private static String msgUrl=url+"usr="+user+"&psw="+psw+"&authkey="+authkey;
	private static String ms1=ConfigUtil.getValue("SMS.part1");
	/**
	 * 
	 * @author:wzh
	 * @version:1.0
	 * @date:2016年7月20日下午4:02:27
	 * @description:<li>方法描述：供应商第一次申请医院 审核通过短息通知<>
	 * @param mobileNo
	 * @param code
	 * @param admin
	 * @return  boolean
	 */
	@SuppressWarnings("finally")
	public static boolean sendSupplierNotice(String mobileNo,String code,String admin) {
		Integer successFlag=0;
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			msgUrl = msgUrl+"&mobile="+mobileNo+"&tpl=40&text[sysType]="+ms1+"&text[sysCode]="+code+"&text[userNo]="+admin;
			System.out.println("请求url:"+msgUrl);
			HttpGet httpget = new HttpGet(msgUrl);
			CloseableHttpResponse response = httpclient.execute(httpget);
			try {
				HttpEntity entity = response.getEntity();
				int statusCode=response.getStatusLine().getStatusCode();
				if (statusCode==200) {
					String result=EntityUtils.toString(entity);
					JSONObject json = JSON.parseObject(result);
					successFlag = (Integer)json.get("success");//1：成功；0：失败。
					if(successFlag.toString().equals("1")){
//						proeceesVerifyCode(mobileNo,String.valueOf(msg));
					}
				}
			} finally {
				response.close();
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 关闭连接,释放资源  
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(successFlag.toString().equals("1")){
				return true;
			}else{
				return false;
			}
		}
		
	}
	/**
	 * 
	 * @author:ljh
	 * @version:1.0
	 * @date:2016年5月23日下午2:49:58
	 * @description:<li>方法描述：发送短信验证码接口</li>
	 * @param mobileNo <li>手机号码</li>
	 * @return boolean <li>true:发送成功,false：发送失败</li>
	 */
	@SuppressWarnings("finally")
	public static boolean sendGetRequest(String mobileNo) {
		Integer successFlag=0;
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			Random random = new Random();
			int code = random.nextInt(999999);
			msgUrl = msgUrl+"&mobile="+mobileNo+"&tpl=4&text[vCode]="+String.valueOf(code);
			System.out.println("请求url:"+msgUrl);
			HttpGet httpget = new HttpGet(msgUrl);
			CloseableHttpResponse response = httpclient.execute(httpget);
			try {
				HttpEntity entity = response.getEntity();
				int statusCode=response.getStatusLine().getStatusCode();
				if (statusCode==200) {
					String result=EntityUtils.toString(entity);
					JSONObject json = JSON.parseObject(result);
					successFlag = (Integer)json.get("success");//1：成功；0：失败。
					if(successFlag.toString().equals("1")){
						proeceesVerifyCode(mobileNo,String.valueOf(code));
					}
				}
			} finally {
				response.close();
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 关闭连接,释放资源  
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(successFlag.toString().equals("1")){
				return true;
			}else{
				return false;
			}
		}
	}
	/**
	 * 
	 * @author:ljh
	 * @version:1.0
	 * @date:2016年5月23日下午2:34:07
	 * @description:<li>方法描述：存储验证码</li>
	 * @param mobileNo
	 * @param verifyCode void
	 */
	@SuppressWarnings("unused")
	public static void proeceesVerifyCode(String mobileNo,String verifyCode){
		Map<String,Date> codeMap = smsStore.get("mobileNo");
		codeMap = new HashMap<String,Date>();
		if(codeMap==null){
			codeMap.put(verifyCode, new Date());
			smsStore.put(mobileNo,codeMap);
		}else{
			smsStore.remove(mobileNo);
			codeMap.clear();
			codeMap.put(verifyCode, new Date());
			smsStore.put(mobileNo,codeMap);
		}
	}
	/**
	 * 
	 * @author:ljh
	 * @version:1.0
	 * @date:2016年5月23日下午2:43:20
	 * @description:<li>方法描述：根据客户端传的参数判断验证码是否正确</li>
	 * @param mobileNo <li>手机号码</li>
	 * @param verifyCode <li>验证码</li>
	 * @return boolean <li>返回true代表匹配成功,返回false代表匹配失败</li>
	 */
	public static boolean verifySMS(String mobileNo,String verifyCode){
		Map<String,Date> map = smsStore.get(mobileNo);
		String storedCode = map.keySet().iterator().next();
		boolean compareResult = differTime(new Date());
		if(storedCode.equals(verifyCode) && compareResult==true){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * 
	 * @author:ljh
	 * @version:1.0
	 * @date:2016年5月23日下午2:40:46
	 * @description:<li>方法描述：计算两个日期之间相差多少秒</li>
	 * @param date
	 * @return boolean
	 */
	public static boolean differTime(Date date){
		Date now = new Date();
		long nowTime = now.getTime();
		long startTime = date.getTime();
		int result = (int)((nowTime-startTime) / 1000);
		int timeOut = ConfigUtil.getParseInt("SMS.TimeOut");
		if(result>timeOut) 
			return false;
		else 
			return true;
	}
}
