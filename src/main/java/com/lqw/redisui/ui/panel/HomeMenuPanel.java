package com.lqw.redisui.ui.panel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeMenuPanel extends JPanel {

    private final JMenuBar jMenuBar;

    private final JFrame parent;

    public HomeMenuPanel(JFrame jFrame) {
        this.parent = jFrame;
        this.jMenuBar = new JMenuBar();
    }


    public void initHomeMenu() {
        JMenu serverMenu = new JMenu("服务器");
        JMenuItem newServer = new JMenuItem("新建");

        serverMenu.add(newServer);

        JMenu settingMenu = new JMenu("设置");

        JMenu aboutMenu = new JMenu("关于");




        jMenuBar.add(serverMenu);
        jMenuBar.add(settingMenu);
        jMenuBar.add(aboutMenu);



        newServer.addActionListener(e -> {

            System.out.println("点击了新建服务器");
            JOptionPane.showInternalMessageDialog(parent, "hello Information message",
                    "新建服务器", JOptionPane.INFORMATION_MESSAGE);
        });

        aboutMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showInternalMessageDialog(parent, "hello Information message",
                        "消息标题", JOptionPane.INFORMATION_MESSAGE);
            }
        });



        parent.setJMenuBar(jMenuBar);

    }


}
