package lesson01;

import java.awt.*;

public class TestFrame2 {
    public static void main(String[] args) {
        //展示多个窗口
        MyFrame myFrame1 = new MyFrame(100,100,200,200,Color.blue);
        MyFrame myFrame2 = new MyFrame(300,100,200,200,Color.yellow);
        MyFrame myFrame3 = new MyFrame(100,300,200,200,Color.red);
        MyFrame myFrame4 = new MyFrame(300,300,200,200,Color.MAGENTA);

    }
}
class MyFrame extends Frame{
    static int id = 0;//可能存在多个窗口，我们需要一个计数器
    public MyFrame(int x,int y, int z, int h,Color color){
        super("myframe" + (++id) );
        setBackground(color);
        setBounds(x, y, z, h);
        setVisible(true);
    }
}
