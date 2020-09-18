package com.lqw.redisui.command;

import com.lqw.redisui.command.version.QueryServerVersion;
import com.lqw.redisui.model.RedisVersion;

import java.util.SortedSet;
import java.util.TreeSet;

public abstract class JedisCommandFactory {

    private int id;

    protected SortedSet<JedisCommand> commands = new TreeSet<>();

    public JedisCommand getCommand() {

        QueryServerVersion queryServerVersion = new QueryServerVersion(id);
        queryServerVersion.execute();

        RedisVersion redisVersion = queryServerVersion.getRedisVersion();

        for (JedisCommand jedisCommand : commands){
            if(jedisCommand.getSupportVersion().getVersion() < redisVersion.getVersion()){
                return jedisCommand;
            }
        }

        throw new RuntimeException("unsupport redis version");
    }
}
