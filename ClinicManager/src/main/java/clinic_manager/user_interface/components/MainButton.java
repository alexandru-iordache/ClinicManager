package clinic_manager.user_interface.components;

import javax.swing.*;
import java.awt.*;

public class MainButton extends JButton {
    public MainButton(String text) {
        setText(text);
        init();
    }

    private void init(){
         setSize(500, 250);
         setBackground(new Color(255,245,245));
         setFont(new Font("Monospaced", Font.BOLD, 25));
         setForeground(new Color(110, 38, 14));
         setFocusable(false);
         setMargin(new Insets(2,4,2,4));
    }
}
