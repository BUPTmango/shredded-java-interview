package com.wgl.redisson;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Wang Guolong
 * @version 1.0
 * @date 2021/4/28 22:13
 */
@ConfigurationProperties(prefix = "auto.redisson")
public class RedissonProperties {

    /** Redis server host */
    private String host = "localhost";

    /** Redis server port */
    private int port = 6379;

    /** 连接超时时间 */
    private int timeout;

    /** 是否启用ssl支持 */
    private boolean ssl;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public boolean isSsl() {
        return ssl;
    }

    public void setSsl(boolean ssl) {
        this.ssl = ssl;
    }
}

