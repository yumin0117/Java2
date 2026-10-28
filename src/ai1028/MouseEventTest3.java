
package ai1028;

import center.SetFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseEventTest3 extends JFrame {

    JLabel lblImg = new JLabel();

    public MouseEventTest3() {

        String[] btnsText = {"강아지", "고양이", "토끼"};
        JPanel panel = new JPanel();

        for (int i = 0; i < btnsText.length; i++) {
            JButton btn = new JButton(btnsText[i]);
            btn.addMouseListener(btnListener);
            panel.add(btn);
        }

        add("North", panel);
        add("Center", lblImg);

        lblImg.setOpaque(true);
        lblImg.setBackground(Color.WHITE);
        lblImg.setHorizontalAlignment(SwingConstants.CENTER);

        SetFrame.setFrame(this, "버튼에 따른 이미지 변경", 500, 500);
    }

    MouseListener btnListener = new MouseAdapter() {

        @Override
        public void mouseClicked(MouseEvent e) {

            JButton eBtn = (JButton) e.getSource();
            ImageIcon imageIcon = null;

            switch (eBtn.getText()) {

                case "강아지":
                    imageIcon = new ImageIcon("images/dog.jpg");
                    break;

                case "고양이":
                    imageIcon = new ImageIcon("images/cat.jpg");
                    break;

                case "토끼":
                    imageIcon = new ImageIcon("images/rabbit.jpg");
                    break;
            }

            lblImg.setIcon(imageIcon);
        }
    };

    public static void main(String[] args) {
        new MouseEventTest3();
    }
}
