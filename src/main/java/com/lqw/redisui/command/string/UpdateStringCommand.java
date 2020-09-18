package com.lqw.redisui.command.string;

import com.lqw.redisui.command.JedisCommand;
import com.lqw.redisui.model.RedisVersion;

public class UpdateStringCommand extends JedisCommand {


    private int db;

    private String key;

    private String value;

    public UpdateStringCommand(int id, int db, String key, String value) {
        super(id);
        this.db = db;
        this.key = key;
        this.value = value;
    }

    public UpdateStringCommand(int id) {
        super(id);
    }

    @Override
    public void execute() {

        jedis.select(db);

        jedis.set(key, value);

    }

    @Override
    public RedisVersion getSupportVersion() {
        return RedisVersion.REDIS_1_0;
    }
}
