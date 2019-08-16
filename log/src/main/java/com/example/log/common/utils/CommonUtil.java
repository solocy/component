package com.example.log.common.utils;

import com.example.log.domain.Log;
import com.example.log.domain.enumeration.LogType;

import java.io.PrintWriter;
import java.io.StringWriter;

public class CommonUtil {
    /**
     * 将异常堆栈转为String
     *
     * @param e
     * @return
     */
    public static String throwToString(Throwable e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        pw.close();
        return sw.toString();
    }
}
