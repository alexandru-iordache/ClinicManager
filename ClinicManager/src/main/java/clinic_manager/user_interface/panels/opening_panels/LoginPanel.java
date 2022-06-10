package clinic_manager.user_interface.panels.opening_panels;

import clinic_manager.user_interface.frames.AdministratorApplicationFrame;
import clinic_manager.user_interface.frames.OpeningFrame;
import clinic_manager.user_interface.components.TitleLabel;
import clinic_manager.user_interface.components.MainButton;
import clinic_manager.user_interface.components.MainField;
import clinic_manager.user_interface.components.MainLabel;
import clinic_manager.entities.User;
import clinic_manager.user_interface.components.MainDialog;
import clinic_manager.user_interface.frames.ClientApplicationFrame;
import clinic_manager.user_interface.frames.DoctorApplicationFrame;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {

    private final OpeningFrame frame;
    private MainField usernameField;
    private JPasswordField passwordField;

    public LoginPanel(OpeningFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        setBounds(0, 0, 600, 800);
        setBackground(new Color(110, 38, 14));
        setLayout(new MigLayout("fillx", "[][]", "50[]75[][][][]"));

        var titleLabel = new TitleLabel("Login");
        add(titleLabel, "center, span, wrap");

        var usernameLabel = new MainLabel("Username:");
        add(usernameLabel, "right");

        usernameField = new MainField(30);
        add(usernameField, "wrap 30");

        var passwordLabel = new MainLabel("Password:");
        add(passwordLabel, "right");

        passwordField = new JPasswordField(30);
        add(passwordField, "wrap 30");

        var typeLabel = new MainLabel("Type:");
        add(typeLabel, "right");

        String[] userTypes = new String[3];
        userTypes[0] = "Administrator";
        userTypes[1] = "Doctor";
        userTypes[2] = "Pacient";
        var listTypes = new JList(userTypes);
        listTypes.setVisibleRowCount(1);
        add(listTypes, "wrap 30");

        var loginButton = new MainButton("Login");
        add(loginButton, "center, span, wrap 30");
        loginButton.addActionListener(e -> {
            loginUser(usernameField, passwordField, listTypes);
        });

        var textLabel = new MainLabel("Don't have an account?");
        add(textLabel, "center, span, wrap 15");

        var registerButton = new MainButton("Register");
        add(registerButton, "center, span, wrap 30");
        registerButton.addActionListener(e -> {
            this.setVisible(false);
            this.frame.getRegisterPanel().setVisible(true);
        });
        
        var backButton = new MainButton("Back");
        add(backButton, "center, span");
        backButton.addActionListener(e -> {
            this.setVisible(false);
            this.frame.getOpeningPanel().setVisible(true);
        });
    }

    private void loginUser(MainField usernameField, JPasswordField passwordField, JList listTypes) {
        User user = frame.getDatabaseAPI()
                .loginValidation(usernameField.getText(), new String(passwordField.getPassword()), (String) listTypes.getSelectedValue());
        if (user != null) {
            frame.setVisible(false);
            usernameField.setText("");
            passwordField.setText("");
            switch (listTypes.getSelectedIndex()) {
                case 0 -> {
                    frame.setAdministratorApplicationFrame(new AdministratorApplicationFrame(frame, user));
                    frame.getAdministratorApplicationFrame().setVisible(true);
                }
                case 1 -> {
                    frame.setDoctorApplicationFrame(new DoctorApplicationFrame(frame, user));
                    frame.getDoctorApplicationFrame().setVisible(true);
                }
                case 2 -> {
                    frame.setClientApplicationFrame(new ClientApplicationFrame(frame, user));
                    frame.getClientApplicationFrame().setVisible(true);
                }

            }
        } else {
            var dialogBox = new MainDialog("Nu exista acest user!");
        }
    }

}
