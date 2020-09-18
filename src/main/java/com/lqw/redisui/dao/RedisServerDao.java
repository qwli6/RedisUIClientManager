package com.lqw.redisui.dao;

import com.lqw.redisui.model.Server;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

public class RedisServerDao {


    public List<Server> getServers() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("server.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String addr = (String) properties.getOrDefault("addr", "");
        Integer port = (Integer) properties.getOrDefault("port", 6379);

        return Collections.singletonList(new Server(addr, port));

    }
}
