package clinic_manager.user_interface.frames;

import clinic_manager.entities.User;
import clinic_manager.user_interface.panels.administrator_panels.AdministratorMenuPanel;
import clinic_manager.user_interface.panels.administrator_panels.AdministratorViewPanel;
import javax.swing.*;


public class AdministratorApplicationFrame extends JFrame {
    
    private final OpeningFrame openingFrame;
    private final AdministratorMenuPanel administratorMenuPanel;
    private final AdministratorViewPanel administratorViewPanel;
    
    private final User user;
    
    public AdministratorApplicationFrame(OpeningFrame openingFrame, User user) {
        super("Clinic Manager");
        
        this.openingFrame = openingFrame;
        
        this.user = user;
        
        this.administratorMenuPanel = new AdministratorMenuPanel(this, user);
        this.administratorViewPanel = new AdministratorViewPanel(this, this.openingFrame.getDatabaseAPI(), this.user);
        
        init();
    }

    private void init() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000,600);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
        
        add(administratorMenuPanel);
        add(administratorViewPanel);
        

    }

    public OpeningFrame getOpeningFrame() {
        return this.openingFrame;
    }

    public AdministratorViewPanel getAdministratorViewPanel() {
        return administratorViewPanel;
    }
    
    
}
