package com.lqw.redisui.db;

import redis.clients.jedis.Jedis;

import javax.swing.tree.DefaultMutableTreeNode;
import java.util.List;

public class RedisServersInfoHandler {


    public static void main(String[] args){

        getDbsFromCurrentRedisServer(new DefaultMutableTreeNode());

    }


    public static void getDbsFromCurrentRedisServer(DefaultMutableTreeNode root) {

        System.out.println("从 redis 中获取 db 数量");

        Jedis jedis = new Jedis("192.168.19.60", 6379);

        List<String> databases = jedis.configGet("databases");

        if(databases != null && databases.size() != 2){
            String dbStr = databases.get(1);
            int dbCount = Integer.parseInt(dbStr);

            for(int i = 0; i < dbCount; i++){

                DefaultMutableTreeNode defaultMutableTreeNode = new DefaultMutableTreeNode("db" + i);

                //第一个
                root.add(defaultMutableTreeNode);
            }
        }
    }
}
