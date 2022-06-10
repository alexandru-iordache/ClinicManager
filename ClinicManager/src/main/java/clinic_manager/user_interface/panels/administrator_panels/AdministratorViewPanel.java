/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinic_manager.user_interface.panels.administrator_panels;

import clinic_manager.database_api.DatabaseAPI;
import clinic_manager.entities.User;
import clinic_manager.user_interface.frames.AdministratorApplicationFrame;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

/**
 *
 * @author Iordi
 */
public class AdministratorViewPanel extends JPanel {

    private final DatabaseAPI databaseAPI;
    private final TablePanel tablePanel;
    private final ProfilePanel profilePanel;
    private final AdministratorApplicationFrame applicationFrame;
    private final User user;

    public AdministratorViewPanel(AdministratorApplicationFrame applicationFrame, DatabaseAPI databaseAPI, User user) {
        this.databaseAPI = databaseAPI;
        this.user = user;
        this.applicationFrame = applicationFrame;
        
        this.tablePanel = new TablePanel(this.user, this.databaseAPI);
        this.profilePanel = new ProfilePanel(this.user, this.databaseAPI, this);
        
        this.tablePanel.setVisible(true);
        this.profilePanel.setVisible(false);
       
        init();
    }
    
    private void init(){
        setBounds(250, 0, 750, 600);
        setPreferredSize(new Dimension(750, 600));
        setBackground(new Color(110, 38, 14));
        setLayout(null);
        
        add(tablePanel);
        
        add(profilePanel);
    }

    public TablePanel getTablePanel() {
        return tablePanel;
    }

    public ProfilePanel getProfilePanel() {
        return profilePanel;
    }

    public AdministratorApplicationFrame getApplicationFrame() {
        return applicationFrame;
    }
    
    
    
    
}
