package clinic_manager.user_interface.components;

import javax.swing.*;
import java.awt.*;

public class MainRadio extends JRadioButton {
    public MainRadio(String text){
        setText(text);
        init();
    }

    private void init(){
        setFont(new Font("Monospaced", Font.PLAIN, 13));
        setForeground(new Color(255, 245, 245));
        setBackground(new Color(110, 38, 14));
    }
}
