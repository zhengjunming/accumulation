package com.mins.httpclient.http;

import org.apache.http.conn.HttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author <a href="http://minsming.com">小铭</a>
 * Date: 2018/5/16
 * No struggle, talent how to match the willfulness.
 * Description: 自动清除无效Http链接
 */
@Component
public class IdleConnectionEvictor extends Thread {

    @Autowired
    private HttpClientConnectionManager connectionManager;

    private volatile boolean shutdown;

    public IdleConnectionEvictor() {
        this.start();
    }

    @Override
    public void run() {
        try {
            while (!shutdown) {
                synchronized (this) {
                    wait(5000);
                    connectionManager.closeExpiredConnections();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void shutdown() {
        shutdown = true;
        synchronized (this) {
            notifyAll();
        }
    }
}
