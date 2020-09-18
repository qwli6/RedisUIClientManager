package com.lqw.redisui.ui.panel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RedisServersPanel extends JPanel {

    private final JFrame parent;

    public RedisServersPanel(JFrame jFrame){
        this.parent = jFrame;

        initPanel();
    }

    private void initPanel() {

        JScrollPane jPanel = new JScrollPane();

        setLayout(new BorderLayout());

        Button button = new Button("新链接");

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("点击了新链接");
            }
        });
        jPanel.setViewportView(button);


        this.add(jPanel);

//        JTextArea textArea = new JTextArea();

//        textArea.setText("系统日志");

//        this.add(textArea);

//        jPanel.add(button);


//        this.add(button);


    }
}
