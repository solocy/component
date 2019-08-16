package com.example.log.common.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class LogQueue {

    private static LogQueue logQueue = new LogQueue();

    private LogQueue(){}

    public static LogQueue getLogQueue() {
        return logQueue;
    }

    private static BlockingQueue<Object> blockingQueue = new LinkedBlockingQueue<>();

    public void push(Object o) {
        blockingQueue.add(o);
    }

    public Object poll() {
        Object o = null;
        try {
            o = blockingQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return o;
    }

}
