package com.example.log.common.filer;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;
import com.example.log.common.queue.LogQueue;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class LogFilter extends Filter<ILoggingEvent> {


    /**
     * 过滤日志，可在此对日志进行操作
     *
     * 获取日志记录到队列
     *
     * @param event
     * @return
     */
    @Override
    public FilterReply decide(ILoggingEvent event) {
//
//        System.out.println(event.getFormattedMessage());
//        System.out.println(DateFormat.getDateTimeInstance().format(new Date(event.getTimeStamp())));
//        System.out.println(event.getThreadName());
//        System.out.println(event.getLoggerName());
//        System.out.println(event.getLevel().levelStr);
        StringBuilder stringBuffer = new StringBuilder();
        stringBuffer.append(SimpleDateFormat.getDateTimeInstance().format(new Date(event.getTimeStamp())));
        stringBuffer.append(" ");
        stringBuffer.append("event.getLevel().levelStr");
        stringBuffer.append(" ");
        stringBuffer.append("[").append(event.getThreadName()).append("] ");
        stringBuffer.append(event.getLoggerName());
        stringBuffer.append(" ").append(event.getFormattedMessage());
        LogQueue.getLogQueue().push(stringBuffer.toString());
        return FilterReply.ACCEPT;
    }
}
