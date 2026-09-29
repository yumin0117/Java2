package ai0929.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonTest2 extends JFrame {
    ButtonTest2(){

        int w = 500;
        int h = 200;

//        Dimension locationDim = CenterFrame.getLocation(w, h);
//        int x = locationDim.width; // 정가운데로 표시하는 x좌표값
//        int y = locationDim.height; // 정가운데로 표시하는 y좌표값
        int[] location = CenterFrame.getLocation(w, h);
        int x = location[0];
        int y = location[1];

        setLayout(new FlowLayout());
        setTitle("Button 컴포넌트");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(w, h);
        setLocation(x, y);
        setVisible(true);
        JButton btn = new JButton("메세지 대화상자 보이기");
        add(btn);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "대화상자를 선택하셨네요.");
            }
        });
    }

    static void main(String[] args) {
        new ButtonTest2();
    }
}
