package ai1013;

import center.CenterFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JLabelJButtonImageTest extends JFrame {

    JLabel lbl = new JLabel();
    ImageIcon lblIcon = new ImageIcon("imgs/wwe2.jpg");

    public JLabelJButtonImageTest() {
        setTitle("ImageIcon설정 컴포넌트");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        ImageIcon btnIcon = new ImageIcon("imgs/wwe.jpg");
        JButton btn = new JButton("Click Here~", btnIcon);
        btn.addActionListener(btnListener);

        add("North", btn);
        add("Center", lbl);

        int w = 1500, h = 1500;
        int[] locations = CenterFrame.getLocation(w, h);
        setBounds(locations[0], locations[1], w, h);
        setVisible(true);
    }

    ActionListener btnListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (lbl.getIcon() == null) {
                lbl.setIcon(lblIcon);
            } else {
                lbl.setIcon(null);
            }
        }
    };

    public static void main(String[] args) {
        new JLabelJButtonImageTest();
    }
}