package com.lqw.redisui.ui.panel;

import javax.swing.*;

public class SystemLogPanel extends JScrollPane {



    public SystemLogPanel() {

        JTextArea textArea = new JTextArea();

        textArea.setText("系统日志");

        this.add(textArea);

    }

    public void init(){



    }
}
