package clinic_manager.user_interface.panels.opening_panels;

import clinic_manager.user_interface.frames.OpeningFrame;
import clinic_manager.user_interface.components.TitleLabel;
import clinic_manager.user_interface.components.MainButton;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class OpeningPanel extends JPanel {
    final OpeningFrame frame;

    public OpeningPanel(OpeningFrame frame) {
        this.frame = frame;
        init();
    }

    private void init(){
        setBounds(0, 0, 600, 800);
        setBackground(new Color(110, 38, 14));
        setLayout(new MigLayout("fillx", "[][]", "100[]100[][][]"));

        var titleLabel = new TitleLabel("Clinic manager");
        add(titleLabel, "center, span 2, wrap ");

        var loginButton = new MainButton("Login");
        loginButton.setPreferredSize(new Dimension(200, 100));
        loginButton.addActionListener(e -> {
            this.setVisible(false);
            this.frame.getLoginPanel().setVisible(true);
        });
        add(loginButton, "center, span 2, wrap 50");

        var registerButton = new MainButton("Register");
        registerButton.setPreferredSize(new Dimension(200, 100));
        registerButton.addActionListener(e -> {
            this.setVisible(false);
            this.frame.getRegisterPanel().setVisible(true);
        });
        add(registerButton, "center, span 2, wrap 50");
        
        var exitButton = new MainButton("Exit");
        exitButton.setPreferredSize(new Dimension(200, 100));
        exitButton.addActionListener(e -> {
            this.frame.dispose();
        });
        add(exitButton, "center, span 2");
    }
}
