package ai1028;

import center.SetFrame;

import javax.swing.*;

public class GraphicTest extends JFrame {

    public GraphicTest() {
        add("center", new GraphicPanel());

        SetFrame.setFrame(this, "도형그리기 연습", 500, 500);
    }

    public static void main(String[] args) {
        new GraphicTest();
    }
}