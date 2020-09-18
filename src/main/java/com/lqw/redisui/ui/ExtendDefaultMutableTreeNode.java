package com.lqw.redisui.ui;

import com.lqw.redisui.model.RedisServerInfo;

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
