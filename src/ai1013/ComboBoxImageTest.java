package ai1013;

import center.CenterFrame;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ComboBoxImageTest extends JFrame {

    JLabel lbl = new JLabel(new ImageIcon("wwe/wwe1.png"));

    public ComboBoxImageTest() {
        setTitle("문자열 입력 컴포넌트");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        String[] items = {"언더테이커", "헐크 호건", "브록 레스너", "카말라", "존시나"};
        JComboBox<String> comboBox = new JComboBox<>(items);
        comboBox.addItemListener(comboListener);

        WweStyle.apply(this, comboBox, lbl);

        add("North", comboBox);
        add("Center", lbl);

        int w = 800, h = 800;
        int[] locations = CenterFrame.getLocation(w, h);
        setBounds(locations[0], locations[1], w, h);
        setVisible(true);
    }

    ItemListener comboListener = new ItemListener() {
        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                String fileName = "";
                String selectedItem = (String) e.getItem();

                switch (selectedItem) {
                    case "언더테이커":
                        fileName = "wwe/wwe1.png";
                        break;
                    case "헐크 호건":
                        fileName = "wwe/wwe2.jpg";
                        break;
                    case "브록 레스너":
                        fileName = "wwe/wwe3.jpg";
                        break;
                    case "카말라":
                        fileName = "wwe/wwe4.jpg";
                        break;
                    case "존시나":
                        fileName = "wwe/wwe5.jpg";
                        break;
                }

                lbl.setIcon(new ImageIcon(fileName));
            }
        }
    };

    public static void main(String[] args) {
        new ComboBoxImageTest();
    }
}