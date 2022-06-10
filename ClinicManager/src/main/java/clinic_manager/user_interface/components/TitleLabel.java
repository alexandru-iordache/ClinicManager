package clinic_manager.user_interface.components;

import javax.swing.*;
import java.awt.*;

public class TitleLabel extends JLabel {
    public TitleLabel(String textLabel) {
        this.setText(textLabel);
        init();
    }

    private void init(){
        setFont(new Font("Monospaced", Font.BOLD, 40));
        setForeground(new Color(255, 245, 245));
        setHorizontalAlignment(JLabel.CENTER);
    }
}
