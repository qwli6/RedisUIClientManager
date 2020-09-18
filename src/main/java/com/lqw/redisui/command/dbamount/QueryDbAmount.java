package com.lqw.redisui.command.dbamount;

import com.lqw.redisui.command.JedisCommand;
import com.lqw.redisui.model.RedisVersion;
import redis.clients.jedis.exceptions.JedisException;

import java.util.List;

public class QueryDbAmount extends JedisCommand {

    private int dbAmount;

    public QueryDbAmount(int id) {
        super(id);
    }

    @Override
    public void execute() {

        try {
            List<String> str = jedis.configGet("databases");
            if (str != null && str.size() > 0) {
                dbAmount = Integer.parseInt(str.get(1));
            }
        } catch (JedisException ex){
            dbAmount = 16;
        }
    }

    @Override
    public RedisVersion getSupportVersion() {
        return RedisVersion.REDIS_1_0;
    }

    public int getDbAmount() {
        return dbAmount;
    }

    public void setDbAmount(int dbAmount) {
        this.dbAmount = dbAmount;
    }
}
