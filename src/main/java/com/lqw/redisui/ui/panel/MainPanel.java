package com.lqw.redisui.ui.panel;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {

    private final JFrame parent;

    public MainPanel(JFrame parent) {
        this.parent = parent;
    }

    public void initMainPanel(){

        //left and right layout
        JSplitPane leftRightSplitPane = new JSplitPane();
        //设置分裂方向 左右分裂
        leftRightSplitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        //设置分割线位置
        leftRightSplitPane.setDividerLocation(250);
        //设置分割线宽度
        leftRightSplitPane.setDividerSize(1);
        //是否连续重绘组件，如果为 false，则拖动分隔条停止后才重绘组件。
        leftRightSplitPane.setContinuousLayout(true);

//        leftRightSplitPane.setBorder(new BorderUIResource.BevelBorderUIResource());

        leftRightSplitPane.setBackground(Color.WHITE);

        leftRightSplitPane.setBorder(BorderFactory.createEmptyBorder());

        //设置左边组件
        leftRightSplitPane.setLeftComponent(new RedisServersPanel(parent));



        JSplitPane mainContentPane = new JSplitPane();
        mainContentPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
        mainContentPane.setDividerSize(1);
        mainContentPane.setDividerLocation(250);
        mainContentPane.setContinuousLayout(true);
        mainContentPane.setBackground(Color.WHITE);
        mainContentPane.setBorder(BorderFactory.createEmptyBorder());

        //显示 Redis 值
        mainContentPane.setTopComponent(new RedisValueInfoPanel(parent));
        //显示日志，终端
        mainContentPane.setBottomComponent(new RedisConsolePanel(parent));


        leftRightSplitPane.setRightComponent(mainContentPane);

        parent.setContentPane(leftRightSplitPane);

    }
}
