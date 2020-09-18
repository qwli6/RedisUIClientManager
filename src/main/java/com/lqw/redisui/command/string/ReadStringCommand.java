package com.lqw.redisui.command.string;

import com.lqw.redisui.command.JedisCommand;
import com.lqw.redisui.model.RedisVersion;

public class ReadStringCommand extends JedisCommand {

    private int db;

    private String key;

    private String value;



    public ReadStringCommand(int id) {
        super(id);
    }

    @Override
    public void execute() {
        jedis.select(db);
        value = jedis.get(key);
    }

    @Override
    public RedisVersion getSupportVersion() {
        return RedisVersion.REDIS_1_0;
    }
}
