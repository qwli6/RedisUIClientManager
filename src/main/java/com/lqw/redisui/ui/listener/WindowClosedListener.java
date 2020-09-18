package com.lqw.redisui.ui.listener;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class WindowClosedListener implements WindowListener {

    private JFrame mainFrame;

    public WindowClosedListener(JFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {

        int oldState = e.getOldState();
        System.out.println("窗口关闭事件");

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
