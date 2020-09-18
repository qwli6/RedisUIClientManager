package com.lqw.redisui.model;

public enum RedisVersion {
    REDIS_1_0(1),
    REDIS_2_0(2),
    REDIS_3_0(3),
    REDIS_4_0(4),
    REDIS_5_0(5),
    ;

    public int getVersion(){
        return version;
    }

    int version;

    RedisVersion(int version) {
        this.version = version;
    }
}
