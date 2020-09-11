package com.lqw.redisui.swing;

import com.lqw.redisui.bean.RedisServerInfo;

import javax.swing.tree.DefaultMutableTreeNode;

public class ExtendDefaultMutableTreeNode extends DefaultMutableTreeNode {

    private RedisServerInfo redisServerInfo;

    public ExtendDefaultMutableTreeNode(Object userObject, RedisServerInfo redisServerInfo) {
        super(userObject);
        this.redisServerInfo = redisServerInfo;
    }

    public RedisServerInfo getRedisServerInfo() {
        return redisServerInfo;
    }

    public void setRedisServerInfo(RedisServerInfo redisServerInfo) {
        this.redisServerInfo = redisServerInfo;
    }
}
