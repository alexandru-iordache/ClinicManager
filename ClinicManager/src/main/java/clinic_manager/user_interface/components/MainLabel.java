package clinic_manager.user_interface.components;

import javax.swing.*;
import java.awt.*;

public class MainLabel extends JLabel {
    public MainLabel(String text) {
        setText(text);
        init();
    }

    private void init(){
        setFont(new Font("Monospaced", Font.BOLD, 20));
        setForeground(new Color(255, 245, 245));
    }
}
