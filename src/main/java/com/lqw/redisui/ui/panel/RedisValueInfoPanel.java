package com.lqw.redisui.ui.panel;

import javax.swing.*;
import java.awt.*;

public class RedisValueInfoPanel extends JPanel {

    private final JFrame parent;

    public RedisValueInfoPanel(JFrame parent) {
        this.parent = parent;

        initRedisTerminalPanel();

    }

    private void initRedisTerminalPanel() {

        setLayout(new BorderLayout());

        JLabel label = new JLabel("显示值");

        this.add(label);

    }
}
