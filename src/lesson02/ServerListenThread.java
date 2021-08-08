package lesson02;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
 
/**
 * @Author: create_By:
 * @Data:Created in 2019/12/24 23:47
 * @Version:
 * @Acton: 继承线程让其成为线程类
 */
public class ServerListenThread extends Thread {
 
    private ServerSocket serverSocket;
    private Socket socket;
    private InputStream is;
 
 
    public QQServerSystem qqs;
 
    public ServerListenThread(QQServerSystem qqs) {
        super();
        this.qqs = qqs;
    }
 
    @Override
    public void run() {
        //实现监听8888端口,接收客户端发送的消息，并显示到界面
        try {
            serverSocket = new ServerSocket(8888, 1000);     //创建服务端套接字并设置端口
            socket = serverSocket.accept();                                 //创建套接字
            is = socket.getInputStream();                                   //获取输入流
            qqs.os = socket.getOutputStream();                              //获取输出流
            while (true) {
                if (socket.isConnected()) {
                    byte[] buff = new byte[1024];            //设置一个临时缓冲区
                    int len = is.read(buff);                //从输入流读取字节长度
                    byte[] eBuff = new byte[len];           //根据实际长度，定义一个输入缓冲区
                    System.arraycopy(buff, 0, eBuff, 0, len);   //拷贝数据
                    String text = new String(eBuff);        //把字节转换字符
                    qqs.txtList.append("对方: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                    qqs.txtList.append("\r\n");
                    qqs.txtList.append(text);
                    qqs.txtList.append("\r\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}