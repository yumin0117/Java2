package ai1028;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FindLocationMouseEvent extends JFrame {

    JLabel lbl = new JLabel("마우스를 움직여 보세요.", SwingConstants.CENTER);

    public FindLocationMouseEvent() {
        super("마우스 위치 확인");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);

        add(panel, BorderLayout.CENTER);
        add(lbl, BorderLayout.SOUTH);

        // 마우스 이벤트
        MouseAdapter listener = new MouseAdapter() {

            @Override
            public void mouseMoved(MouseEvent e) {
                lbl.setText("X: " + e.getX() + "  Y: " + e.getY());
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(null,
                        "클릭 위치\nX: " + e.getX()
                                + "\nY: " + e.getY());
            }
        };

        panel.addMouseListener(listener);
        panel.addMouseMotionListener(listener);

        setVisible(true);
    }

    public static void main(String[] args) {
        new FindLocationMouseEvent();
    }
}