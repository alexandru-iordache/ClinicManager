/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinic_manager.user_interface.panels.client_panels;

import clinic_manager.database_api.DatabaseAPI;
import clinic_manager.entities.User;
import clinic_manager.user_interface.frames.ClientApplicationFrame;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

/**
 *
 * @author Iordi
 */
public class ClientViewPanel extends JPanel{
    private final DatabaseAPI databaseAPI;

    private final ClientApplicationFrame applicationFrame;
    private final User user;
    
    private final ClientAppointmentsPanel appointmentsPanel;
    private final ClientProfilePanel clientProfilePanel;
    private final AppointmentMaker appointmentMaker;

    public ClientViewPanel(ClientApplicationFrame applicationFrame, DatabaseAPI databaseAPI, User user) {
        this.databaseAPI = databaseAPI;
        this.user = user;
        this.applicationFrame = applicationFrame;

        this.appointmentsPanel = new ClientAppointmentsPanel(user, databaseAPI);
        this.clientProfilePanel = new ClientProfilePanel(user, databaseAPI, this);
        this.appointmentMaker = new AppointmentMaker(user, databaseAPI, this);
        
        init();
    }
    
    private void init(){
        setBounds(250, 0, 750, 600);
        setPreferredSize(new Dimension(750, 600));
        setBackground(new Color(110, 38, 14));
        setLayout(null);
        
        add(appointmentsPanel);
        
        clientProfilePanel.setVisible(false);
        add(clientProfilePanel);
        
        appointmentMaker.setVisible(false);
        add(appointmentMaker);
    }

    public ClientAppointmentsPanel getClientAppointmentsPanel() {
        return appointmentsPanel;
    }

    public ClientProfilePanel getClientProfilePanel() {
        return clientProfilePanel;
    }

    public ClientApplicationFrame getApplicationFrame() {
        return applicationFrame;
    }

    public AppointmentMaker getAppointmentMaker() {
        return appointmentMaker;
    }
    
    
}
