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
 * @Data:Created in 2019/12/24 23:56
 * @Version:
 * @Acton:  QQ客户端系统
 */
public class QQClientSystem{
 
    private JFrame frame;		            //窗口
    private Container container;	        //创中的容器对象
    public JTextArea txtList;	        //文本列表框
    public JTextField txtMsg;	        //文本发送框
    public JButton btn;		        //发送按钮
    public String addMsg = "未连接";
    public OutputStream os;                 //用于发送信息。
 
    public QQClientSystem() {
        frame = new JFrame("九哥的QQ客户端");
        frame.setBounds(400, 300, 800, 600); // 设置窗口大小和位置
        frame.setLayout(new BorderLayout());
        container = frame.getContentPane();
        txtList = new JTextArea(5, 20);
        container.add(txtList, BorderLayout.CENTER);
        JPanel txtPanel = new JPanel();
        txtPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        txtMsg = new JTextField(60);
        btn = new JButton("发送");
 
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 实现给服务器端发送消息的部分
 
                //1、点击发送获取消息框中的信息
                String msgText = txtMsg.getText();
                //2、处理文本
                if(!"".equals(msgText) && msgText != null){      //判断是有文字的才会做处理
                    //2、发送
                    byte[] sendBuf = msgText.getBytes();
                    try {
                        if (os != null){
                            os.write(sendBuf);
                        }
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    txtList.append("我: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                    txtList.append("\r\n");
                    txtList.append(msgText);
                    txtList.append("\r\n");
                    txtMsg.setText("");
                }
 
            }
        });
 
        txtPanel.add(txtMsg);
        txtPanel.add(btn);
        container.add(txtPanel, BorderLayout.SOUTH);
        startRequestThread();
        refreshMsgList();
    }
 
    public  void startRequestThread() {
        new ClientRequestThread(this).start();
    }
 
    public void refreshMsgList() {
        String str = txtList.getText();
        txtList.setText(str + addMsg + "\n");
    }
 
    public void start(){
        frame.setVisible(true);
    }
 
}