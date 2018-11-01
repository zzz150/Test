package com.yunerp.base.redis;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by shican on 2016-10-27.
 */
public class ExceptionUtil {
    public static String getTrace(Throwable throwable) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        throwable.printStackTrace(writer);
        StringBuffer buffer = stringWriter.getBuffer();
        return buffer.toString();
    }
}