package lesson02;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
 
/**
 * @Author: create_By:
 * @Data:Created in 2019/12/24 23:58
 * @Version:
 * @Acton:
 */
public class ClientRequestThread extends Thread {
 
    private Socket socket;
    private InputStream is;
 
    QQClientSystem qqc;
 
    public ClientRequestThread(QQClientSystem qqc) {
        this.qqc = qqc;
    }
 
    @Override
    public void run() {
        // 实现连接服务器的功能，并可以接收服务器端发送的消息，显示到程序界面
        try {
            socket = new Socket("localhost", 8888);
            is = socket.getInputStream();
            qqc.os = socket.getOutputStream();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        while (true) {
            if (socket.isConnected()) {       //已经连接才做以下处理
                byte[] buff = new byte[1024];            //设置一个临时缓冲区
                int len = 0;                //从输入流读取字节长度
                try {
                    len = is.read(buff);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                byte[] eBuff = new byte[len];           //根据实际长度，定义一个输入缓冲区
                System.arraycopy(buff, 0, eBuff, 0, len);   //拷贝数据
                String text = new String(eBuff);        //把字节转换字符
                qqc.txtList.append("对方: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                qqc.txtList.append("\r\n");
                qqc.txtList.append(text);
                qqc.txtList.append("\r\n");
            }
        }
    }
 
}