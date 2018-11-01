package com.yunerp.base.httpclient;

public class HttpClientPost implements Runnable {
	private String url;
	private String param;
	
	public HttpClientPost(String url, String param) {
		super();
		this.url = url;
		this.param = param;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		HttpClientUtil.sendHttpPostRequest(url, param);
	}

}
