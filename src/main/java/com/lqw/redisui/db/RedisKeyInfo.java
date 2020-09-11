package com.lqw.redisui.db;

import java.io.Serializable;

public class RedisKeyInfo implements Serializable {

    private String key;

    private String value;

    private long expireAt;

    private String ttl;

    private String type;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public long getExpireAt() {
        return expireAt;
    }

    public void setExpireAt(long expireAt) {
        this.expireAt = expireAt;
    }

    public String getTtl() {
        return ttl;
    }

    public void setTtl(String ttl) {
        this.ttl = ttl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "RedisKeyInfo{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                ", expireAt=" + expireAt +
                ", ttl='" + ttl + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
