/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinic_manager.user_interface.panels.doctor_panels;

import clinic_manager.database_api.DatabaseAPI;
import clinic_manager.entities.Doctor;
import clinic_manager.entities.User;
import clinic_manager.user_interface.components.ErrorLabel;
import clinic_manager.user_interface.components.MainButton;
import clinic_manager.user_interface.components.MainDialog;
import clinic_manager.user_interface.components.MainField;
import clinic_manager.user_interface.components.MainLabel;
import clinic_manager.user_interface.components.TitleLabel;
import java.awt.Color;
import java.awt.Dimension;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Iordi
 */
public class DoctorProfilePanel extends JPanel {

    private final User user;
    private final DatabaseAPI databaseAPI;
    private final Doctor doctor;
    private final DoctorViewPanel doctorViewPanel;

    private MainField passwordField;
    private MainField passwordConfirmationField;
    private MainField phoneField;
    private MainField emailField;
    private MainField addressField;

    private ErrorLabel passwordError;
    private ErrorLabel confirmationError;
    private ErrorLabel phoneError;
    private ErrorLabel emailError;

    private MainButton changeButton;
    private MainButton deleteButton;

    public DoctorProfilePanel(User user, DatabaseAPI databaseAPI, DoctorViewPanel doctorViewPanel) {
        this.user = user;
        this.databaseAPI = databaseAPI;
        this.doctorViewPanel = doctorViewPanel;

        this.doctor = this.databaseAPI.getDoctorByUser(this.user);

        init();
    }

    private void init() {
        setBounds(0, 0, 750, 600);
        setPreferredSize(new Dimension(750, 600));
        setBackground(new Color(110, 38, 14));
        setLayout(new MigLayout("fillx", "[][][][]", "10[]20[][][][]"));

        var titleLabel = new TitleLabel("Profile");
        add(titleLabel, "center, span, wrap");

        var usernameLabel = new MainLabel("Username:");
        add(usernameLabel, "right, span 2");

        var usernameValueLabel = new MainLabel(user.getUsername());
        add(usernameValueLabel, "wrap 15, span 2");

        var passwordLabel = new MainLabel("Password:");
        add(passwordLabel, "right, span 2");

        passwordField = new MainField(30);
        passwordField.setText(user.getPassword());
        add(passwordField, "wrap 5, span 2");

        passwordError = new ErrorLabel("Password must have at least 8 characters*");
        passwordError.setVisible(false);
        add(passwordError, "wrap 5, span 4, right");

        var passwordConfirmationLabel = new MainLabel("Retype password:");
        passwordConfirmationLabel.setVisible(false);
        add(passwordConfirmationLabel, "right, span 2");

        passwordConfirmationField = new MainField(30);
        passwordConfirmationField.setVisible(false);
        add(passwordConfirmationField, "wrap 5, span 2");

        confirmationError = new ErrorLabel("Passwords are not the same*");
        confirmationError.setVisible(false);
        add(confirmationError, "wrap 10, span 4, center");

        var cnpLabel = new MainLabel("CNP:");
        add(cnpLabel, "right, span 2");

        var cnpValueLabel = new MainLabel(String.valueOf(doctor.getCnp()));
        add(cnpValueLabel, "wrap 15, span 2");

        var firstNameLabel = new MainLabel("First name:");
        add(firstNameLabel, "span 2, split 2, right");

        var firstNameField = new MainLabel(doctor.getFirstName());
        add(firstNameField, "left");

        var lastNameLabel = new MainLabel("Last name:");
        add(lastNameLabel, "span 2, split 2, left, gapx 60");

        var lastNameField = new MainLabel(doctor.getLastName());
        add(lastNameField, "wrap 15, left");

        var phoneLabel = new MainLabel("Phone:");
        add(phoneLabel, "right, span 2");

        phoneField = new MainField(30);
        phoneField.setText(doctor.getPhone());
        add(phoneField, "wrap 5, span 2");

        phoneError = new ErrorLabel("Phone must have 10 digits*");
        add(phoneError, "wrap 5, span 4, center");

        var emailLabel = new MainLabel("Email:");
        add(emailLabel, "right, span 2");

        emailField = new MainField(30);
        emailField.setText(doctor.getEmail());
        add(emailField, "wrap 5, span 2");

        emailError = new ErrorLabel("Enter a valid email*");
        add(emailError, "wrap 5, span 4, center");

        var addressLabel = new MainLabel("Address:");
        add(addressLabel, "right, span 2");

        addressField = new MainField(30);
        addressField.setText(doctor.getAdress());
        add(addressField, "wrap 10, span 2");

        changeButton = new MainButton("Change");
        changeButton.setPreferredSize(new Dimension(100, 50));
        changeButton.addActionListener(e -> {
            if (changeButton.getText().equals("Change")) {
                passwordConfirmationLabel.setVisible(true);
                passwordConfirmationField.setVisible(true);
                changeButton.setText("Commit changes");
                deleteButton.setText("Renounce");
            } else if (changeButton.getText().equals("Commit changes")) {
                if (changeDoctorDetails() == true) {
                    passwordField.setText(user.getPassword());
                    phoneField.setText(doctor.getPhone());
                    emailField.setText(doctor.getEmail());
                    addressField.setText(doctor.getAdress());

                    passwordConfirmationField.setVisible(false);

                    changeButton.setText("Change");
                    deleteButton.setText("Delete");
                    var dialogBox = new MainDialog("Schimbarea datelor a avut succes!");
                } else {
                    var dialogBox = new MainDialog("Schimbarea datelor nu a avut succes!");
                }
            }
        });
        add(changeButton, "center, span 4, split 2 ");

        deleteButton = new MainButton("Delete");
        deleteButton.setPreferredSize(new Dimension(100, 50));
        deleteButton.addActionListener(e -> {
            if (deleteButton.getText().equals("Delete")) {
                databaseAPI.deleteRecordFromUsers(user.getId());

                var dialogBox = new MainDialog("Userul a fost sters!");

                this.doctorViewPanel.getApplicationFrame().getOpeningFrame().setVisible(true);
                this.doctorViewPanel.getApplicationFrame().dispose();
            } else if (deleteButton.getText().equals("Renounce")) {
                passwordField.setText(user.getPassword());
                phoneField.setText(doctor.getPhone());
                emailField.setText(doctor.getEmail());
                addressField.setText(doctor.getAdress());

                passwordConfirmationField.setVisible(false);

                changeButton.setText("Change");
                deleteButton.setText("Delete");
            }
        });

        add(deleteButton, "gapx 100, center");
        
        passwordError.setVisible(false);
        phoneError.setVisible(false);
        emailError.setVisible(false);
    }

    private boolean changeDoctorDetails() {
        passwordError.setVisible(false);
        phoneError.setVisible(false);
        emailError.setVisible(false);
        
        String password = passwordField.getText();
        if (password.length() < 8) {
            passwordError.setVisible(true);
            return false;
        }
        String passwordConfirmation = passwordConfirmationField.getText();
        if (!passwordConfirmation.equals(password) && !password.equals(user.getPassword())) {
            confirmationError.setVisible(true);
            return false;
        }
        String phone = phoneField.getText();
        if (phone.length() != 10) {
            phoneError.setVisible(true);
            return false;
        }

        String email = emailField.getText();
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            emailError.setVisible(true);
            return false;
        }

        String address = addressField.getText();

        return databaseAPI.updateDoctor(user, password, phone, email, address);
    }
}
