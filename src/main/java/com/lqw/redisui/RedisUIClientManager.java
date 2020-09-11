package com.lqw.redisui;

import com.alibaba.fastjson.JSON;
import com.lqw.redisui.bean.RedisServerInfo;
import com.lqw.redisui.db.RedisKeyInfo;
import com.lqw.redisui.i18n.I18NTool;
import com.lqw.redisui.utils.FileUtils;
import com.lqw.redisui.utils.StringUtils;
import redis.clients.jedis.Jedis;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.ExpandVetoException;
import javax.swing.tree.TreePath;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.DoubleStream;

/**
 * Redis GUI 启动类, Redis 启动入口
 * @author liqiwen
 * @version 1.0
 * @since 1.0
 */
public class RedisUIClientManager {



    private JFrame jFrame;

    private JMenuBar jMenuBar;

    private Map<String, RedisServerInfo> redisServersMap = new HashMap<>();


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RedisUIClientManager redisUIClientManager = new RedisUIClientManager();
            redisUIClientManager.launch();
        });
    }


    /**
     * Open the window
     * create the mainUI window
     */
    public void launch() {
        //check version
        boolean fileExists = FileUtils.checkConfigFile(I18NTool.DEFAULT_CONFIG_FILE_PATH);

        if(fileExists) {

            createMainUI();
        }

    }


    /**
     * create main ui
     * <p>
     *     init main jframe
     *     init main menu
     *     init main content
     * </p>
     */
    protected void createMainUI(){

        initMainJFrame();

        initMainMenu();

        initMainContent();

    }


    /**
     * init main content
     */
    private void initMainContent() {

        JButton button3 = new JButton("中");
        JButton button5 = new JButton("下");

        jFrame.add(createCenterSplitPaneComponent(),BorderLayout.CENTER);

        jFrame.add(button5,BorderLayout.SOUTH);



        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String actionCommand = e.getActionCommand();

                Jedis jedis = new Jedis("192.168.19.60", 6379);

                if(jedis != null){
                    System.out.println("连接 redis 成功");

                    String hummingBirdAccessToken = jedis.get("HUMMING_BIRD_ACCESS_TOKEN");
                    System.out.println("蜂鸟 token:" + hummingBirdAccessToken);
                }
            }
        });
    }

    private void initMainMenu() {

        jMenuBar = new JMenuBar();

        JMenu jMenu = new JMenu("Servers");
        JMenuItem newServer = new JMenuItem("新建");


        newServer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 显示输入对话框, 返回输入的内容
                showNewServerDialog(jFrame, jFrame);
            }
        });

        jMenu.add(newServer);
        jMenuBar.add(jMenu);
        jFrame.setJMenuBar(jMenuBar);
    }

    private void initMainJFrame() {

        jFrame = new JFrame(I18NTool.MAIN_TITLE);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jFrame.setMinimumSize(new Dimension(I18NTool.DEFAULT_WIDTH, I18NTool.DEFAULT_HEIGHT));
        jFrame.setResizable(true);
        jFrame.setAutoRequestFocus(true);

        jFrame.setLayout(new BorderLayout());

        jFrame.pack();
        jFrame.setVisible(true);
    }


    private JSplitPane createCenterSplitPaneComponent(){

        //left and right layout
        JSplitPane leftRightSplitPane = new JSplitPane();

        //left is redis server list
        JScrollPane redisServerListsSplitPane = initRedisServerLists();
        leftRightSplitPane.setLeftComponent(redisServerListsSplitPane);


        leftRightSplitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        leftRightSplitPane.setDividerLocation(250);


        JSplitPane jSplitPane = new JSplitPane();

        jSplitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
        jSplitPane.setTopComponent(new JButton("上面"));
        jSplitPane.setBottomComponent(new JButton("下面"));

        leftRightSplitPane.setRightComponent(jSplitPane);

        return leftRightSplitPane;
    }


    /**
     * init redis server lists
     * @return JScrollPane
     */
    @SuppressWarnings("unchecked")
    private JScrollPane initRedisServerLists() {


        DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();

        //创建根节点
        DefaultMutableTreeNode redisServerRootNode = new DefaultMutableTreeNode(I18NTool.REDIS_SERVER_ROOT_NAMES);

        //解析配置文件

        String config = ""; /**FileUtils.readStringFromFile(Paths.get(I18NTool.DEFAULT_CONFIG_FILE_PATH));**/

        if(!StringUtils.isBlank(config)){

            //配置文件不为空
            List<RedisServerInfo> redisServerInfos = JSON.parseObject(config, List.class);
            if(redisServerInfos != null && redisServerInfos.size() > 0){
                for(RedisServerInfo redisServerInfo : redisServerInfos){

                    DefaultMutableTreeNode defaultMutableTreeNode = new DefaultMutableTreeNode(redisServerInfo.getAlias());

                    redisServerRootNode.add(defaultMutableTreeNode);
                }
            }
        }

        DefaultMutableTreeNode defaultMutableTreeNode = new DefaultMutableTreeNode("localhost@127.0.0.1");

        RedisServerInfo redisServerInfo = new RedisServerInfo();
        redisServerInfo.setAddress("192.168.19.60");
        redisServerInfo.setAlias("localhost@127.0.0.1");
        redisServerInfo.setPort(6379);

        redisServersMap.put("localhost@127.0.0.1", redisServerInfo);
        redisServerRootNode.add(defaultMutableTreeNode);


        JTree redisServersTree = new JTree(redisServerRootNode);


        redisServersTree.setEditable(false);


        MouseListener ml = new MouseAdapter() {

            TreePath treePath;

            @Override
            public void mouseClicked(MouseEvent e) {

                treePath = redisServersTree.getPathForLocation(e.getX(), e.getY());


                System.out.println("当前点击的 treePath 为：" + treePath);

                if (treePath != null) {

                    Object[] path = treePath.getPath();

                    if(path.length <= 1){
                        return;
                    }

                    RedisServerInfo currentRedisServerInfo = redisServersMap.get(String.valueOf(path[1]));
                    if (currentRedisServerInfo != null) {
                        Jedis jedis = new Jedis(currentRedisServerInfo.getAddress(), currentRedisServerInfo.getPort());
                        //connect server
                        if (path.length <= 2) {
                            DefaultMutableTreeNode lastPathComponent = (DefaultMutableTreeNode) treePath.getLastPathComponent();
                            for (int i = 0; i < 15; i++) {
                                String select = jedis.select(i);
                                if ("OK".equals(select)) {
                                    //点击的节点
                                    lastPathComponent.add(new DefaultMutableTreeNode("db" + i));
                                }
                            }
                            //重新绘制节点
                            redisServersTree.repaint();
                            return;
                        }
                        //select db
                        if (path.length <= 3) {
                            int dbIndex = Integer.parseInt(String.valueOf(path[2]).replaceAll("db", ""));
                            String select = jedis.select(dbIndex);
                            if("OK".equals(select)){ //选择 db
                                DefaultMutableTreeNode currentClickNode = ((DefaultMutableTreeNode) treePath.getLastPathComponent());
                                Set<String> keys = jedis.keys("*");
                                if(keys != null && keys.size() > 0){
                                    for(String key: keys) {
                                        currentClickNode.add(new DefaultMutableTreeNode(key));
                                    }
                                }
                            }
                            //重新绘制节点
                            redisServersTree.repaint();
                            return;
                        }

                        //拿到的 key
                        if(path.length <= 4){
                            String key = String.valueOf(path[3]);
                            String value = jedis.get(key);

                            RedisKeyInfo keyInfo = new RedisKeyInfo();
                            keyInfo.setKey(key);
                            keyInfo.setValue(value);

                            System.out.println(keyInfo.toString());

                        }
                    }
                }
            }
        };

        redisServersTree.addMouseListener(ml);





        //设置节点展开 和 折叠状态显示的图标
//        renderer.setOpenIcon();
//        renderer.setClosedIcon();


        //设置叶子节点显示的图标
//        renderer.setLeafIcon();
        //设置叶子节点的字体
//        renderer.setFont();

        //设置选中和未选中的颜色
//        renderer.setTextSelectionColor(Color newColor);
//        renderer.setTextNonSelectionColor(Color newColor);



        redisServersTree.setCellRenderer(renderer);
        // 设置树显示根节点句柄
        redisServersTree.setShowsRootHandles(true);


        // 创建滚动面板，包裹树（因为树节点展开后可能需要很大的空间来显示，所以需要用一个滚动面板来包裹）
        JScrollPane scrollPane = new JScrollPane(redisServersTree);
        scrollPane.setBounds(0, 0,400, 750);
        scrollPane.setVisible(true);
        return scrollPane;
    }






    private static void showNewServerDialog(Frame owner, Component parentComponent) {
        // 创建一个模态对话框
        final JDialog dialog = new JDialog(owner, "提示", true);
        // 设置对话框的宽高
        dialog.setSize(400, 400);
        // 设置对话框大小不可改变
        dialog.setResizable(false);
        dialog.setAlwaysOnTop(true);
        // 设置对话框相对显示的位置
        dialog.setLocationRelativeTo(parentComponent);

        // 创建一个标签显示消息内容
//        JLabel messageLabel = new JLabel("对话框消息内容");

        // 创建一个按钮用于关闭对话框
        JButton okBtn = new JButton("确定");
        okBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 关闭对话框
                dialog.dispose();
            }
        });




        // 创建对话框的内容面板, 在面板内可以根据自己的需要添加任何组件并做任意是布局


        JPanel jPanel = new JPanel(new GridLayout(2, 1));


        JTabbedPane tabbedPane = new JTabbedPane();

        //创建第一个选项卡
        Box vBox = createAddConnectionPanel();
        tabbedPane.add("connection", vBox);
        tabbedPane.add("SSL", new JLabel("2222"));

        jPanel.add(tabbedPane);

        JButton testCollectionBtn = new JButton("测试连接");
        testCollectionBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {




            }
        });

        GridBagLayout gridBagLayout = new GridBagLayout();

        JPanel buttonPanel = new JPanel(gridBagLayout);
        buttonPanel.add(okBtn);

        buttonPanel.add(testCollectionBtn);

        jPanel.add(buttonPanel);

        // 设置对话框的内容面板
        dialog.setContentPane(jPanel);
        // 显示对话框
        dialog.setVisible(true);
    }



    private static Box createAddConnectionPanel() {


        // 第 2 个 JPanel, 使用默认的浮动布局
        JPanel namePanel = new JPanel();
        namePanel.add(new JLabel("Name:"));
        namePanel.add(new JTextField( 10));

        JPanel hostPanel = new JPanel();
        hostPanel.add(new JLabel("Host:"));
        hostPanel.add(new JTextField(10));


        JPanel portPanel = new JPanel();
        portPanel.add(new JLabel("Port:"));
        portPanel.add(new JTextField("6379",10));




        JPanel authPanel = new JPanel();
        authPanel.add(new JLabel("Auth:"));
        authPanel.add(new JTextField(10));

        // 创建一个垂直盒子容器, 把上面 3 个 JPanel 串起来作为内容面板添加到窗口
        Box vBox = Box.createVerticalBox();
        vBox.add(namePanel);
        vBox.add(hostPanel);
        vBox.add(portPanel);
        vBox.add(authPanel);

        return vBox;



    }


}
