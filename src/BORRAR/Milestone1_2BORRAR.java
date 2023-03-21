package BORRAR;

import javax.swing.*;
import java.awt.*;

public class Milestone1_2BORRAR {

    public static void showFrame() {
        JFrame frame0 = new JFrame("FrameDemo");
        frame0.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame0.setPreferredSize(new Dimension(600, 300));

        /*Container container = frame0.getContentPane();
        container.setLayout(new BorderLayout());

        top(frame0);
        right(frame0);
        middle(frame0);
        down(frame0);*/

        frame0.pack();
        frame0.setVisible(true);
    }

    public static void main(String[] args) {
        //showFrame();
        System.out.println("HELO");
    }
}
