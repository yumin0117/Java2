package ai1027;

import center.SetFrame;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseEventTest extends JFrame {
    JLabel lbl = new JLabel();

    public MouseEventTest() {
        JPanel panel = new JPanel();
        JButton btn = new JButton("마우스 이벤트 연습");

        panel.add(btn);
        add(panel);
        add("South", lbl);

        btn.addMouseListener(btnMouseListener);

        SetFrame.setFrame(this, "MouseEvent Programming", 300, 300);
    }

    MouseListener btnMouseListener = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
            System.out.println("버튼에 마우스가 클릭됨");
            lbl.setText("버튼에 마우스가 클릭됨");
        }

        @Override
        public void mousePressed(MouseEvent e) {
            int eBtnNum = e.getButton();
            String msg = "";
            switch (eBtnNum){
                case 1:
                    msg = "왼쪽";
                    break;
                case 2:
                    msg = "가운데";
                    break;
                case 3:
                    msg = "오른쪽";
                    break;
            }

            JOptionPane.showMessageDialog(null, msg + "버튼 눌러짐");
            System.out.println("버튼에 마우스가 해제됨");
            lbl.setText("버튼에 마우스가 해제됨");
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            System.out.println("버튼에 마우스가 해제됨");
            lbl.setText("버튼에 마우스가 해제됨");
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            System.out.println("버튼에 마우스가 들어옴");
            lbl.setText("버튼에 마우스가 들어옴");
        }

        @Override
        public void mouseExited(MouseEvent e) {
            System.out.println("버튼에 마우스가 나감");
            lbl.setText("버튼에 마우스가 나감");
        }
    };

    public static void main(String[] args) {
        new MouseEventTest();
    }
}
