package com.lqw.redisui.ui.dialog;

import javax.swing.*;

public class AbstractBaseDialog extends JDialog {

    protected JFrame parent;

    protected Object object;

    protected int width = 350;

    protected int height = 500;

    public AbstractBaseDialog(JFrame parent) {
        this.parent = parent;
    }



    public Object open() {

        createDialogContents();

        return object;
    }


    protected void createDialogContents() {
        System.out.println("执行父类 createDialogContents ");
//        parent.setSize(width, height);
//        parent.pack();

        setMiddle();
    }

    protected void setMiddle() {
//
//        Rectangle bounds = parent.getParent().getBounds();
//
//        Rectangle dialogBounds = parent.getBounds();
//
//
//
//        parent.setLocation(bounds.x + bounds.width / 2 - bounds.width / 2,
//                bounds.y + bounds.height / 2 - dialogBounds.height / 2);

    }

}
