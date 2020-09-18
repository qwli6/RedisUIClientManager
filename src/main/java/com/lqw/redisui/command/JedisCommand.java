package com.lqw.redisui.command;

import com.lqw.redisui.model.RedisVersion;
import com.lqw.redisui.model.Server;
import redis.clients.jedis.Jedis;

public abstract class JedisCommand implements Comparable<JedisCommand>{


    protected int id;

    protected Server server;

    protected Jedis jedis;


    public JedisCommand(int id) {
        super();
        this.id = id;
    }

    @Override
    public int compareTo(JedisCommand o) {
        return 0;
    }

    public abstract void execute();

    public abstract RedisVersion getSupportVersion();
}
