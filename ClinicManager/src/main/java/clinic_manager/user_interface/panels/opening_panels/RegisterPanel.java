package clinic_manager.user_interface.panels.opening_panels;

import clinic_manager.user_interface.frames.OpeningFrame;
import clinic_manager.user_interface.components.TitleLabel;
import clinic_manager.user_interface.components.MainDialog;
import clinic_manager.user_interface.components.MainButton;
import clinic_manager.user_interface.components.MainField;
import clinic_manager.user_interface.components.MainRadio;
import clinic_manager.user_interface.components.ErrorLabel;
import clinic_manager.user_interface.components.MainLabel;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.persistence.ValidationMode;

public class RegisterPanel extends JPanel {

    private final OpeningFrame frame;
    private ArrayList<ErrorLabel> errorList;
    private ArrayList<MainField> userFields;
    private ArrayList<MainField> commonFields;
    private ArrayList<MainField> doctorFields;

    public RegisterPanel(OpeningFrame frame) {
        this.frame = frame;
        this.errorList = new ArrayList<>();
        this.userFields = new ArrayList<>();
        this.commonFields = new ArrayList<>();
        this.doctorFields = new ArrayList<>();
        init();
    }

    private void init() {
        setBounds(0, 0, 600, 800);
        setBackground(new Color(110, 38, 14));
        setLayout(new MigLayout("fillx", "[][][][]", "5[]15[][][][][][]"));

        var titleLabel = new TitleLabel("Register");
        add(titleLabel, "center, span, wrap");

        var usernameLabel = new MainLabel("Username:");
        add(usernameLabel, "right, span 2");

        var usernameField = new MainField(30);
        userFields.add(usernameField);
        add(usernameField, "wrap 5, span 2");

        var usernameError = new ErrorLabel("Username must have at least 4 characters*");
        usernameError.setVisible(false);
        errorList.add(usernameError);
        add(usernameError, "wrap 5, span 4, center");

        var passwordLabel = new MainLabel("Password:");
        add(passwordLabel, "right, span 2");

        var passwordField = new MainField(30);
        userFields.add(passwordField);
        add(passwordField, "wrap 5, span 2");

        var passwordError = new ErrorLabel("Password must have at least 8 characters*");
        errorList.add(passwordError);
        add(passwordError, "wrap 5, span 4, center");

        var passwordConfirmationLabel = new MainLabel("Retype password:");
        add(passwordConfirmationLabel, "right, span 2");

        var passwordConfirmationField = new MainField(30);
        userFields.add(passwordConfirmationField);
        add(passwordConfirmationField, "wrap 5, span 2");

        var confirmationError = new ErrorLabel("Passwords are not the same*");
        errorList.add(confirmationError);
        add(confirmationError, "wrap 5, span 4, center");

        var typeLabel = new MainLabel("Type:");
        add(typeLabel, "right, span 2");

        JRadioButton administratorButton = new MainRadio("Administrator");
        JRadioButton doctorButton = new MainRadio("Doctor");
        JRadioButton pacientButton = new MainRadio("Pacient");
        ButtonGroup typesGroup = new ButtonGroup();

        typesGroup.add(administratorButton);
        typesGroup.add(doctorButton);
        typesGroup.add(pacientButton);
        add(administratorButton, "span 2, split 3");
        add(doctorButton, "");
        add(pacientButton, "wrap 5");

        var typeError = new ErrorLabel("Select a type*");
        errorList.add(typeError);
        add(typeError, "wrap 5, span 4, center");

        var pacientComponents = new ArrayList<Component>();
        var doctorComponents = new ArrayList<Component>();
        var administratorComponents = new ArrayList<Component>();

        var cnpLabel = new MainLabel("CNP:");
        pacientComponents.add(cnpLabel);
        doctorComponents.add(cnpLabel);
        add(cnpLabel, "right, span 2");

        var cnpField = new MainField(30);
        pacientComponents.add(cnpField);
        doctorComponents.add(cnpField);
        doctorFields.add(cnpField);
        add(cnpField, "wrap 5, span 2");

        var cnpError = new ErrorLabel("Cnp must have 13 digits*");
        errorList.add(cnpError);
        add(cnpError, "wrap 5, span 4, center");

        var firstNameLabel = new MainLabel("First name:");
        pacientComponents.add(firstNameLabel);
        doctorComponents.add(firstNameLabel);
        administratorComponents.add(firstNameLabel);
        add(firstNameLabel, "right, span 2");

        var firstNameField = new MainField(30);
        pacientComponents.add(firstNameField);
        doctorComponents.add(firstNameField);
        administratorComponents.add(firstNameField);
        commonFields.add(firstNameField);
        add(firstNameField, "wrap 5, span 2");

        var firstNameError = new ErrorLabel("First name must not be null*");
        errorList.add(firstNameError);
        add(firstNameError, "wrap 5, span 4, center");

        var lastNameLabel = new MainLabel("Last name:");
        pacientComponents.add(lastNameLabel);
        doctorComponents.add(lastNameLabel);
        administratorComponents.add(lastNameLabel);
        add(lastNameLabel, "right, span 2");

        var lastNameField = new MainField(30);
        pacientComponents.add(lastNameField);
        doctorComponents.add(lastNameField);
        administratorComponents.add(lastNameField);
        commonFields.add(lastNameField);
        add(lastNameField, "wrap 5, span 2");

        var lastNameError = new ErrorLabel("Last name must not be null*");
        errorList.add(lastNameError);
        add(lastNameError, "wrap 5, span 4, center");

        var phoneLabel = new MainLabel("Phone:");
        pacientComponents.add(phoneLabel);
        doctorComponents.add(phoneLabel);
        administratorComponents.add(phoneLabel);
        add(phoneLabel, "right, span 2");

        var phoneField = new MainField(30);
        pacientComponents.add(phoneField);
        doctorComponents.add(phoneField);
        administratorComponents.add(phoneField);
        commonFields.add(phoneField);
        add(phoneField, "wrap 5, span 2");

        var phoneError = new ErrorLabel("Phone must have 10 digits*");
        errorList.add(phoneError);
        add(phoneError, "wrap 5, span 4, center");

        var emailLabel = new MainLabel("Email:");
        pacientComponents.add(emailLabel);
        doctorComponents.add(emailLabel);
        administratorComponents.add(emailLabel);
        add(emailLabel, "right, span 2");

        var emailField = new MainField(30);
        pacientComponents.add(emailField);
        doctorComponents.add(emailField);
        administratorComponents.add(emailField);
        commonFields.add(emailField);
        add(emailField, "wrap 5, span 2");

        var emailError = new ErrorLabel("Enter a valid email*");
        errorList.add(emailError);
        add(emailError, "wrap 5, span 4, center");

        var addressLabel = new MainLabel("Address:");
        pacientComponents.add(addressLabel);
        doctorComponents.add(addressLabel);
        add(addressLabel, "right, span 2");

        var addressField = new MainField(30);
        pacientComponents.add(addressField);
        doctorComponents.add(addressField);
        doctorFields.add(addressField);
        add(addressField, "wrap 10, span 2");

        var birthdateLabel = new MainLabel("Birthdate:");
        pacientComponents.add(birthdateLabel);
        add(birthdateLabel, "right, span 2");

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        var birthdateField = new JFormattedTextField(dateFormat);
        birthdateField.setColumns(20);
        pacientComponents.add(birthdateField);
        add(birthdateField, "wrap 5, span 2");

        var birthdateError = new ErrorLabel("Birthdate must not be null*");
        errorList.add(birthdateError);
        add(birthdateError, "wrap 5, span 4, center");

        var genderLabel = new MainLabel("Gender:");
        pacientComponents.add(genderLabel);
        add(genderLabel, "right, span 2");

        JRadioButton maleButton = new MainRadio("Male");
        pacientComponents.add(maleButton);
        JRadioButton femaleButton = new MainRadio("Female");
        pacientComponents.add(femaleButton);
        JRadioButton otherButton = new MainRadio("Other");
        pacientComponents.add(otherButton);
        ButtonGroup genderGroup = new ButtonGroup();

        genderGroup.add(maleButton);
        genderGroup.add(femaleButton);
        genderGroup.add(otherButton);
        add(maleButton, "span 2, split 3");
        add(femaleButton, "");
        add(otherButton, "wrap 5");

        var genderError = new ErrorLabel("Select a gender*");
        errorList.add(genderError);
        add(genderError, "wrap 5, span 4, center");

        errorList.forEach((errorLabel) -> errorLabel.setVisible(false));

        pacientComponents.forEach((component) -> component.setVisible(false));
        doctorComponents.forEach((component) -> component.setVisible(false));
        administratorComponents.forEach((component) -> component.setVisible(false));

        pacientButton.addActionListener(e -> {
            administratorComponents.forEach((component) -> component.setVisible(false));
            doctorComponents.forEach((component) -> component.setVisible(false));
            pacientComponents.forEach((component) -> component.setVisible(true));
        });

        doctorButton.addActionListener(e -> {
            administratorComponents.forEach((component) -> component.setVisible(false));
            pacientComponents.forEach((component) -> component.setVisible(false));
            doctorComponents.forEach((component) -> component.setVisible(true));

        });

        administratorButton.addActionListener(e -> {
            pacientComponents.forEach((component) -> component.setVisible(false));
            doctorComponents.forEach((component -> component.setVisible(false)));
            administratorComponents.forEach((component -> component.setVisible(true)));
        });

        var backButton = new MainButton("Back");
        backButton.setPreferredSize(new Dimension(100, 50));
        add(backButton, "span 4, split 3, center");

        var loginButton = new MainButton("Login");
        loginButton.setPreferredSize(new Dimension(100, 50));
        add(loginButton, "");

        var registerButton = new MainButton("Register");
        registerButton.setPreferredSize(new Dimension(100, 50));
        add(registerButton, "wrap");

        loginButton.addActionListener(e -> {
            this.setVisible(false);
            this.frame.getLoginPanel().setVisible(true);
        });

        backButton.addActionListener(e -> {
            this.setVisible(false);
            this.frame.getOpeningPanel().setVisible(true);
        });

        registerButton.addActionListener(e -> {
            errorList.forEach((errorLabel) -> errorLabel.setVisible(false));
            String type = "nothing";
            for (Enumeration<AbstractButton> buttons = typesGroup.getElements(); buttons.hasMoreElements();) {
                AbstractButton button = buttons.nextElement();
                if (button.isSelected()) {
                    type = button.getText();
                }
            }
            int validation = 1;

            if (type.equals("nothing")) {
                validation -= 1;
                errorList.get(3).setVisible(true);
            }

            validation = validation + userFieldsValidation();
            String username = usernameField.getText();
            String password = passwordField.getText();
            String passwordConfirmation = passwordConfirmationField.getText();

            if (validation == 2) {
                validation = validation + commonFieldsValidation();
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                String phone = phoneField.getText();
                String email = emailField.getText();

                if (type.equals("Administrator") && validation == 3) {
                    boolean success = administratorRegister(username, password, passwordConfirmation, type,
                            firstName, lastName, phone, email);
                    if (success) {
                        userFields.forEach((field) -> field.setText(""));
                        commonFields.forEach((field) -> field.setText(""));
                        typesGroup.clearSelection();
                    }
                }

                if (type.equals("Pacient") || type.equals("Doctor")) {
                    validation = validation + doctorFieldsValidation();

                    if (validation == 4) {
                        long cnp = Long.parseLong(cnpField.getText());
                        String address = addressField.getText();
                        if (type.equals("Doctor")) {

                            boolean success = doctorRegister(username, password, type,
                                    cnp, firstName, lastName, phone, email,
                                    address);
                            if (success) {
                                userFields.forEach((field) -> field.setText(""));
                                commonFields.forEach((field) -> field.setText(""));
                                doctorFields.forEach((field) -> field.setText(""));
                                typesGroup.clearSelection();
                            }
                        }

                        if (type.equals("Pacient")) {
                            java.util.Date birthdate = null;
                            try {
                                birthdate = new SimpleDateFormat("dd/MM/yyyy").parse(birthdateField.getText());
                                validation += 1;
                            } catch (ParseException ex) {
                                birthdateError.setVisible(true);
                            }

                            String gender = "nothing";
                            for (Enumeration<AbstractButton> genderButtons = genderGroup.getElements(); genderButtons.hasMoreElements();) {
                                AbstractButton genderButton = genderButtons.nextElement();
                                if (genderButton.isSelected()) {
                                    gender = genderButton.getText();
                                }
                            }

                            validation += 1;
                            if (gender.equals("nothing")) {
                                validation -= 0;
                                genderError.setVisible(true);
                            }

                            if (validation == 6) {
                                boolean success = pacientRegister(username, password, type,
                                        cnp, firstName, lastName, phone, email,
                                        address, birthdate, gender);
                                if (success) {
                                    userFields.forEach((field) -> field.setText(""));
                                    commonFields.forEach((field) -> field.setText(""));
                                    doctorFields.forEach((field) -> field.setText(""));
                                    birthdateField.setText("");
                                    typesGroup.clearSelection();
                                    genderGroup.clearSelection();
                                }
                            }
                        }
                    }
                }
            }
        });
    }

    private boolean administratorRegister(String username, String password, String passwordConfirmation, String type,
            String firstName, String lastName, String phone, String email) {
        int response = frame.getDatabaseAPI().registerAdministrator(username, password, type,
                firstName, lastName, phone, email);

        responseHandler(type, response);
        if (response == 10) {
            return true;
        }
        return false;
    }

    private boolean pacientRegister(String username, String password, String type,
            long cnp, String firstName, String lastName, String phone, String email,
            String address, java.util.Date birthdate, String gender) {
        int response = frame.getDatabaseAPI().registerPacient(username, password, type,
                cnp, firstName, lastName, phone, email,
                address, birthdate, gender);

        responseHandler(type, response);
        if (response == 10) {
            return true;
        }
        return false;
    }

    private boolean doctorRegister(String username, String password, String type,
            long cnp, String firstName, String lastName, String phone, String email, String address) {
        int response = frame.getDatabaseAPI().registerDoctor(username, password, type,
                cnp, firstName, lastName, phone, email, address);

        responseHandler(type, response);
        if (response == 10) {
            return true;
        }
        return false;
    }

    private void responseHandler(String type, int response) {
        if (response == 10) {
            var dialogBox = new MainDialog("Inregistrarea a avut succes!");
        }
        if (response == 1) {
            var dialogBox = new MainDialog("Exista deja un utilizator cu acest username!");
        }
        if (response == 2) {
            var dialogBox = new MainDialog("Exista deja un " + type + " cu acest cnp!");
        }
        if (response == 3) {
            var dialogBox = new MainDialog("Exista deja un " + type + " cu aceasta adresa de mail!");
        }
    }

    private int userFieldsValidation() {
        int validation = 1;
        String username = userFields.get(0).getText();
        if (username.length() < 4) {
            validation = 0;
            errorList.get(0).setVisible(true);
        }
        String password = userFields.get(1).getText();
        if (password.length() < 8) {
            validation = 0;
            errorList.get(1).setVisible(true);
        }
        String passwordConfirmation = userFields.get(2).getText();
        if (!passwordConfirmation.equals(password)) {
            validation = 0;
            errorList.get(2).setVisible(true);
        }
        return validation;
    }

    private int commonFieldsValidation() {
        int validation = 1;
        String firstName = commonFields.get(0).getText();
        if (firstName.length() < 1) {
            validation = 0;
            errorList.get(5).setVisible(true);
        }
        String lastName = commonFields.get(1).getText();
        if (lastName.length() < 1) {
            validation = 0;
            errorList.get(6).setVisible(true);
        }
        String phone = commonFields.get(2).getText();
        if (phone.length() != 10) {
            validation = 0;
            errorList.get(7).setVisible(true);
        }

        String email = commonFields.get(3).getText();
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            validation = 0;
            errorList.get(8).setVisible(true);
        }
        return validation;
    }

    private int doctorFieldsValidation() {
        int validation = 1;
        if (doctorFields.get(0).getText().length() > 0) {
            long cnp = Long.parseLong(doctorFields.get(0).getText());
            if ((double)(cnp / Math.pow(10, 12)) < 1) {
                validation = 0;
                errorList.get(4).setVisible(true);
            }
        }else{
            validation = 0;
            errorList.get(4).setVisible(true);
        }
        return validation;
    }
}
