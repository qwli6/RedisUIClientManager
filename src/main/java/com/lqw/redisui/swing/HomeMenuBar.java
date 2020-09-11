package com.lqw.redisui.swing;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class HomeMenuBar extends JMenuBar {


    public HomeMenuBar() {
        super();
        add(createNewMenu());
        add(createServerMenu());
        add(createToolsMenu());
        add(createHelpMenu());
        setVisible(true);
    }

    private JMenu createNewMenu(){
        JMenu jMenu = new JMenu("新建(N)");

        jMenu.setMnemonic(KeyEvent.VK_N); //设置快速访问按钮

        JMenuItem item = new JMenuItem("新建(N)",KeyEvent.VK_N);
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
        jMenu.add(item);
        item=new JMenuItem("打开(O)",KeyEvent.VK_O);
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
        jMenu.add(item);
        item=new JMenuItem("保存(S)",KeyEvent.VK_S);
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
        jMenu.add(item);
        jMenu.addSeparator();
        item=new JMenuItem("退出(E)",KeyEvent.VK_E);
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_MASK));
        jMenu.add(item);
        return jMenu;
    }


    /**
     * 创建 server 菜单
     * @return JMenu
     */
    private JMenu createServerMenu(){

        JMenu serversMenu = new JMenu("Servers");

        JMenuItem jMenuItem = new JMenuItem("new server");
        jMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                add(showAddServerDialog());
            }
        });
        serversMenu.add(jMenuItem);

        return serversMenu;

    }

    /**
     * 创建工具菜单
     * @return JMenu
     */
    private JMenu createToolsMenu() {
        JMenu toolsMenu = new JMenu("Tools");

        JMenuItem jMenuItem = new JMenuItem("xx");
        toolsMenu.add(jMenuItem);


        return toolsMenu;
    }


    private JMenu createHelpMenu(){

        JMenu helpMenu = new JMenu("Help?");

        JMenuItem jMenuItem = new JMenuItem("yy");

        helpMenu.add(jMenuItem);

        return helpMenu;
    }


    private void showAddServerDialog(){

    }

}
