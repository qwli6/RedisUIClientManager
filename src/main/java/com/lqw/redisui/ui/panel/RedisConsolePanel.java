package com.lqw.redisui.ui.panel;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class RedisConsolePanel extends JPanel {

    private final JFrame parent;

    public RedisConsolePanel(JFrame jFrame) {
        this.parent = jFrame;

        initRedisTerminalPanel();

    }

    private void initRedisTerminalPanel() {

        //SwingConstants.TOP  选项卡的位置顶端正中间
        JTabbedPane jTabbedPane = new JTabbedPane(SwingConstants.TOP);

        this.setBorder(BorderFactory.createEmptyBorder());

        this.setLayout(new BorderLayout());

        jTabbedPane.addTab("日志", new SystemLogPanel());

        jTabbedPane.addTab("终端", new TerminalPanel());

        jTabbedPane.setSelectedIndex(0);

        jTabbedPane.setBorder(BorderFactory.createEmptyBorder());

        jTabbedPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                System.out.println("改变了");
            }
        });

        this.add(jTabbedPane);

    }
}
