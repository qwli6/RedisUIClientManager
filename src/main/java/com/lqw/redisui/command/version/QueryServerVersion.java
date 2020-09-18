package com.lqw.redisui.command.version;

import com.lqw.redisui.command.JedisCommand;
import com.lqw.redisui.model.RedisVersion;

public class QueryServerVersion extends JedisCommand {

    private RedisVersion redisVersion;

    public RedisVersion getRedisVersion() {
        return redisVersion;
    }

    public QueryServerVersion(int id) {
        super(id);
    }

    @Override
    public void execute() {
        this.redisVersion = getRedisVersion();
    }

    @Override
    public RedisVersion getSupportVersion() {
        return RedisVersion.REDIS_1_0;
    }
}
