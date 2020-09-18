package com.lqw.redisui;

import com.alibaba.fastjson.JSON;
import com.formdev.flatlaf.FlatDarculaLaf;
import com.lqw.redisui.model.RedisServerInfo;
import com.lqw.redisui.model.Server;
import com.lqw.redisui.i18n.I18n;
import com.lqw.redisui.ui.dialog.AddServerDialog;
import com.lqw.redisui.ui.listener.WindowClosedListener;
import com.lqw.redisui.ui.panel.*;
import com.lqw.redisui.utils.FileUtil;
import com.lqw.redisui.utils.StringUtil;
import redis.clients.jedis.Jedis;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Redis GUI 启动类, Redis 启动入口
 * @author liqiwen
 * @version 1.0
 * @since 1.0
 */
public class RedisUIClientManager {

    private JFrame jFrame;

    private Map<String, RedisServerInfo> redisServersMap = new HashMap<>();


    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            RedisUIClientManager redisUIClientManager = new RedisUIClientManager();
            redisUIClientManager.jFrame.setVisible(true);
        });
    }


    public RedisUIClientManager() {
        launch();
    }

    /**
     * Open the window
     * create the mainUI window
     */
    public void launch() {
        //check version
        boolean fileExists = FileUtil.checkConfigFile(I18n.DEFAULT_CONFIG_FILE_PATH);

        if(fileExists) {
            try {
                //设置黑色主题
                UIManager.setLookAndFeel(new FlatDarculaLaf());
//                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (UnsupportedLookAndFeelException e) {
                e.printStackTrace();
            }
            createMainUI();
        }

    }


    protected void createMainUI(){

        initMainJFrame();

        initMainMenu();

        initMainContent();

    }


    private void initMainContent() {

        MainPanel mainPanel = new MainPanel(jFrame);
        mainPanel.initMainPanel();

    }

    private void initMainMenu() {
        HomeMenuPanel homeMenuPanel = new HomeMenuPanel(jFrame);
        homeMenuPanel.initHomeMenu();
    }

    private void initMainJFrame() {

        jFrame = new JFrame(I18n.MAIN_TITLE);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);

        jFrame.setMinimumSize(new Dimension(I18n.DEFAULT_WIDTH, I18n.DEFAULT_HEIGHT));
        jFrame.setResizable(true);

        jFrame.setLayout(new BorderLayout());

        jFrame.addWindowListener(new WindowClosedListener(jFrame));

    }


    /**
     * init redis server lists
     * @return JScrollPane
     */
    @SuppressWarnings("unchecked")
    private JScrollPane initRedisServerLists() {


        DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();

        //创建根节点
        DefaultMutableTreeNode redisServerRootNode = new DefaultMutableTreeNode(I18n.REDIS_SERVER_ROOT_NAMES);

        //解析配置文件

        String config = ""; /**FileUtils.readStringFromFile(Paths.get(I18NTool.DEFAULT_CONFIG_FILE_PATH));**/

        if(!StringUtil.isBlank(config)){

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

                            String type = jedis.type(key);

                            System.out.println(type);


//                            String value = jedis.get(key);
//
//                            RedisKeyInfo keyInfo = new RedisKeyInfo();
//                            keyInfo.setKey(key);
//                            keyInfo.setValue(value);
//
//                            System.out.println(keyInfo.toString());

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


    

    private void showNewServerDialog(JFrame jFrame) {
        AddServerDialog addServerDialog = new AddServerDialog(jFrame);

        Server open = (Server) addServerDialog.open();
        System.out.println("添加的 server: " + open.toString());
    }



}
