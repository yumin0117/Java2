package ai1013;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class WweStyle {
    public static void apply(JFrame frame, JComboBox<?> comboBox, JLabel lbl) {
        // 프레임 배경 (다크 톤)
        frame.getContentPane().setBackground(new Color(18, 18, 20));

        // 👈 한글 지원 볼드 폰트("맑은 고딕", BOLD)로 변경
        comboBox.setFont(new Font("맑은 고딕", Font.BOLD, 16));
        comboBox.setBackground(new Color(220, 20, 60));
        comboBox.setForeground(Color.WHITE);
        comboBox.setFocusable(false);

        // 라벨 디자인 (레드 메탈 4px 테두리)
        lbl.setOpaque(true);
        lbl.setBackground(new Color(18, 18, 20));
        lbl.setBorder(new LineBorder(new Color(220, 20, 60), 4));
    }
}