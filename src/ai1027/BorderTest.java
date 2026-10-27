package ai1027;

import center.CenterFrame2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BorderTest extends JFrame {

    ArrayList<JButton> btnList = new ArrayList<>();

    String[] locArr = {
            "North", "West", "Center", "East", "South"
    };

    public BorderTest() {
        super("BorderLayout 연습");

        // BorderLayout의 수평, 수직 간격을 10px로 설정
        setLayout(new BorderLayout(10, 10));
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // JFrame의 5개 영역에 버튼을 생성하여 추가
        int i = 1;

        for (String loc : locArr) {
            JButton btn = new JButton("버튼 " + i++);
            btn.addActionListener(btnListener);
            add(loc, btn);
            btnList.add(btn);
        }

        int w = 250, h = 250;
        Point point = CenterFrame2.getLocation(w, h);

        setBounds(point.x, point.y, w, h);
        setVisible(true);
    }

    ActionListener btnListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton eBtn = (JButton) e.getSource();

            JOptionPane.showMessageDialog(
                    null,
                    eBtn.getText() + "이(가) 선택되었습니다."
            );
        }
    };

    public static void main(String[] args) {
        new BorderTest();
    }
}