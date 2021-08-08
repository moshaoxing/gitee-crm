package lesson01;

import java.awt.*;

public class TestFlowLayout {
    public static void main(String[] args) {


    Frame frame = new Frame();
    frame.setVisible(true);

    //组件，按钮
    Button button1 = new Button("button1");
    Button button2 = new Button("button2");
    Button button3 = new Button("button3");
    Button button4 = new Button("button4");

    //设置流式布局
    frame.setLayout(new FlowLayout());

    frame.setSize(200,200);

    //添加按钮
        frame.add(button1);
        frame.add(button2);
        frame.add(button3);
        frame.add(button4);
    }
}
