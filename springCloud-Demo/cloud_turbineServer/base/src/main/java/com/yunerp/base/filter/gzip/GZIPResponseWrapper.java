package com.yunerp.base.filter.gzip;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 *描述：<li></li>
 *作者：李建行
 *创建时间:2016-4-10下午4:46:43
 */
public class GZIPResponseWrapper extends HttpServletResponseWrapper {
	private HttpServletResponse response = null;
	private ServletOutputStream stream = null;
	private PrintWriter writer = null;
	public GZIPResponseWrapper(HttpServletResponse response) {
		super(response);
		this.response = response;
	}
	public ServletOutputStream createOutputStream()throws IOException{
		    return new GZIPResponseStream(this.response);
	}
	
	public void finishResponse(){
		try{
	      if (this.writer != null) {
	        this.writer.close();
	      } else if (this.stream != null) {
	        this.stream.close();
	      }
	    }catch (IOException e) {
	    	e.printStackTrace();
	    }
	}
	public void flushBuffer() throws IOException{
		this.stream.flush();
	}
	public ServletOutputStream getOutputStream() throws IOException{
		if (this.writer != null) {
		      throw new IllegalStateException("getWriter() has already been called!");
	    }
	    if (this.stream == null) {
	      this.stream = createOutputStream();
	    }
	    return this.stream;
	}
	public PrintWriter getWriter() throws IOException{
		if (this.writer != null) {
	      return this.writer;
	    }
	    if (this.stream != null) {
	      throw new IllegalStateException("getOutputStream() has already been called!");
	    }
	    this.stream = createOutputStream();
	    this.writer = new PrintWriter(new OutputStreamWriter(this.stream, "UTF-8"));
	    return this.writer;
	}
	public void setContentLength(int length) {}
}
