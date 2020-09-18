package com.lqw.redisui.ui.dialog;

import com.lqw.redisui.model.Server;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddServerDialog extends AbstractBaseDialog {




    public AddServerDialog(JFrame parent) {
        super(parent);
    }



    protected void createDialogContents() {

        System.out.println("执行子类 createDialogContents ");

        final JDialog jDialog = new JDialog();

        jDialog.setSize(150, 200);

        jDialog.setResizable(false);

        jDialog.setLocationRelativeTo(parent);


        JLabel jLabel = new JLabel("新服务");

        // 创建一个按钮用于关闭对话框
        JButton okBtn = new JButton("确定");


        okBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 关闭对话框
                jDialog.dispose();

                Server server = new Server(1,"127.0.0.1", 6379);

                object = server;


//                parent.setEnabled(true);
            }
        });

        // 创建对话框的内容面板, 在面板内可以根据自己的需要添加任何组件并做任意是布局
        JPanel panel = new JPanel();

        // 添加组件到面板
        panel.add(jLabel);
        panel.add(okBtn);

        // 设置对话框的内容面板
        jDialog.setContentPane(panel);

//        parent.setEnabled(false);
//        parent.setAutoRequestFocus(false);
        // 显示对话框
        jDialog.setVisible(true);

        super.createDialogContents();

    }

}
