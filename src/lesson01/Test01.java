package lesson01;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Test01 {
    public static void main(String[] args) {
        Frame frame = new Frame();
        frame.setSize(400,300);
        frame.setLocation(300,400);
        frame.setBackground(Color.black);
        frame.setVisible(true);
        frame.setLayout(new GridLayout(2,1));//两行面板，1列

        //4个面板
        Panel p1 = new Panel(new BorderLayout());
        Panel p3 = new Panel(new BorderLayout());
        Panel p2 = new Panel(new GridLayout(2,1));
        Panel p4 = new Panel(new GridLayout(2,2));

        p1.add(new Button("e-1"),BorderLayout.EAST);
        p1.add(new Button("w-1"),BorderLayout.WEST);
        p2.add(new Button("p2-1"));
        p2.add(new Button("p2-2"));
        p1.add(p2,BorderLayout.CENTER);

        p3.add(new Button("e-2"),BorderLayout.EAST);
        p3.add(new Button("w-2"),BorderLayout.WEST);

        for (int i = 0; i < 4; i++){
            p4.add(new Button("for-" + i));
        }
        p3.add(p4,BorderLayout.CENTER);

        frame.add(p1);
        frame.add(p3);


        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}
