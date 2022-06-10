package clinic_manager.user_interface.frames;

import clinic_manager.database_api.DatabaseAPI;
import clinic_manager.user_interface.panels.opening_panels.LoginPanel;
import clinic_manager.user_interface.panels.opening_panels.OpeningPanel;
import clinic_manager.user_interface.panels.opening_panels.RegisterPanel;
import javax.swing.*;

public class OpeningFrame extends JFrame {
    private final OpeningPanel openingPanel;
    private final LoginPanel loginPanel;
    private final RegisterPanel registerPanel;
    private AdministratorApplicationFrame administratorApplicationFrame;
    private DoctorApplicationFrame doctorApplicationFrame;
    private ClientApplicationFrame clientApplicationFrame;
    private final DatabaseAPI databaseAPI;
    
    public OpeningFrame() {
        super("Clinic Manager");
        
        databaseAPI = new DatabaseAPI();
        
        openingPanel = new OpeningPanel(this);
        loginPanel = new LoginPanel(this);
        registerPanel = new RegisterPanel(this);
        
        loginPanel.setVisible(false);
        registerPanel.setVisible(false);
        
        init();
    }

    private void init(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600,800);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);

        add(openingPanel);
        add(loginPanel);
        add(registerPanel);
        //pack();
    }

    public OpeningPanel getOpeningPanel() {
        return openingPanel;
    }

    public LoginPanel getLoginPanel() {
        return loginPanel;
    }

    public RegisterPanel getRegisterPanel() {
        return registerPanel;
    }

    public DatabaseAPI getDatabaseAPI() {
        return databaseAPI;
    }

    public void setAdministratorApplicationFrame(AdministratorApplicationFrame administratorApplicationFrame){
        this.administratorApplicationFrame = administratorApplicationFrame;
    }
    
    public AdministratorApplicationFrame getAdministratorApplicationFrame() {
        return administratorApplicationFrame;
    }

    public DoctorApplicationFrame getDoctorApplicationFrame() {
        return doctorApplicationFrame;
    }

    public void setDoctorApplicationFrame(DoctorApplicationFrame doctorApplicationFrame) {
        this.doctorApplicationFrame = doctorApplicationFrame;
    }

    public ClientApplicationFrame getClientApplicationFrame() {
        return clientApplicationFrame;
    }

    public void setClientApplicationFrame(ClientApplicationFrame clientApplicationFrame) {
        this.clientApplicationFrame = clientApplicationFrame;
    }
    
}
