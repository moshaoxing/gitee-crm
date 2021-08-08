package lesson02;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
 
/**
 * @Author: create_By:
 * @Data:Created in 2019/12/24 23:26
 * @Version:
 * @Acton: QQ服务系统
 */
public class QQServerSystem {
 
    private JFrame frame; // 窗口
    private Container container; // 创中的容器对象
    public JTextArea txtList; // 文本列表框
    public JTextField txtMsg; // 文本发送框
    public JButton btn; // 发送按钮
    public String addMsg = "未连接";
    public OutputStream os;    //发送消息的
 
    public QQServerSystem() {
        frame = new JFrame("九哥的QQ服务端");
        frame.setBounds(400, 300, 800, 600);            //设置窗口大小位置
        frame.setLayout(new BorderLayout());                                    //设置样式
        container = frame.getContentPane();                                     //窗口生成容器
 
        txtList = new JTextArea(5, 20);                          //生成文本域的大小
        container.add(txtList, BorderLayout.CENTER);                             //把文本加入容器中，并设置布局
        JPanel txtPanel = new JPanel();
        txtPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        txtMsg = new JTextField(60);                     //设置消息文本行高
        btn = new JButton("发送");
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //实现给客户端发送消息的功能
                //1、点击发送获取消息框中的信息
                String msgText = txtMsg.getText();
                //2、处理文本
                if (!"".equals(msgText) && msgText != null) {      //判断是有文字的才会做处理
                    //1、显示到自己的连天窗口
                    txtList.append("我: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                    txtList.append("\r\n");
                    txtList.append(msgText);
                    txtList.append("\r\n");
                    txtMsg.setText("");
                    //2、发送
                    byte[] sendBuf = msgText.getBytes();
                    try {
                        if (os != null) {
                            os.write(sendBuf);
                        }
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
 
 
            }
        });
        txtPanel.add(txtMsg);                                   //消息框加入面板中
        txtPanel.add(btn);                                      //按钮加入面板中
        container.add(txtPanel, BorderLayout.SOUTH);            //把面板加入容器中，并设置布局
        startServerThread();
        refreshMsgList();
    }
 
    /* 刷新消息列表 */
    private void refreshMsgList() {
        String txt = txtList.getText();
        txtList.setText(txt + addMsg + "\n");
    }
 
    /* 启动线程 */
    private void startServerThread() {
        new ServerListenThread(this).start();
    }
 
    /* 启动面板 */
    public void start() {
        frame.setVisible(true);         //设置可以显示
    }
}