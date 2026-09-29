package ai0929.GUI;

import javax.swing.*;

public class FirstFrame {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setTitle("처음 만든 윈도우창");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  // JFrame.EXIT_ON_CLOSE 도 가능
        frame.setSize(500, 300);
        frame.setVisible(true);
    }
}
