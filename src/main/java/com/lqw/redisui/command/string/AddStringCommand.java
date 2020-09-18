package com.lqw.redisui.command.string;

import com.lqw.redisui.command.JedisCommand;
import com.lqw.redisui.model.RedisVersion;

public class AddStringCommand extends JedisCommand {

    private int db;

    private String key;

    private String value;

    private long seconds;

    public AddStringCommand(int id, int db, String key, String value) {
        super(id);
        this.db = db;
        this.key = key;
        this.value = value;
        this.seconds = 0;
    }

    public AddStringCommand(int id, int db, String key, String value, long seconds) {
        super(id);
        this.db = db;
        this.key = key;
        this.value = value;
        this.seconds = seconds;
    }

    public AddStringCommand(int id) {
        super(id);
    }

    @Override
    public void execute() {

        jedis.select(db);

        jedis.set(key, value);

        if(seconds != 0){
            jedis.expire(key, Integer.parseInt(String.valueOf(seconds)));
        }
    }

    @Override
    public RedisVersion getSupportVersion() {
        return RedisVersion.REDIS_1_0;
    }
}
