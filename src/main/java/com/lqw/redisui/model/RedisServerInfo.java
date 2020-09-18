package com.lqw.redisui.model;

import java.io.Serializable;

public class RedisServerInfo implements Serializable {

    /**
     * Redis 地址
     */
    private String address;

    /**
     * Redis 端口
     */
    private int port;

    /**
     * Redis 连接别名
     */
    private String alias;

    /**
     * Redis 密码
     */
    private String authPass;



    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getAuthPass() {
        return authPass;
    }

    public void setAuthPass(String authPass) {
        this.authPass = authPass;
    }
}
