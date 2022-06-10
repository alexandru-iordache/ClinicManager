/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinic_manager.user_interface.panels.doctor_panels;

import clinic_manager.database_api.DatabaseAPI;
import clinic_manager.entities.User;
import clinic_manager.user_interface.frames.DoctorApplicationFrame;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

/**
 *
 * @author Iordi
 */
public class DoctorViewPanel extends JPanel {

    private final DatabaseAPI databaseAPI;

    private final DoctorApplicationFrame applicationFrame;
    private final User user;
    
    private final AppointmentsPanel appointmentsPanel;
    private final DoctorProfilePanel doctorProfilePanel;

    public DoctorViewPanel(DoctorApplicationFrame applicationFrame, DatabaseAPI databaseAPI, User user) {
        this.databaseAPI = databaseAPI;
        this.user = user;
        this.applicationFrame = applicationFrame;

        this.appointmentsPanel = new AppointmentsPanel(user, databaseAPI);
        this.doctorProfilePanel = new DoctorProfilePanel(user, databaseAPI, this);
        
        init();
    }
    
    private void init(){
        setBounds(250, 0, 750, 600);
        setPreferredSize(new Dimension(750, 600));
        setBackground(new Color(110, 38, 14));
        setLayout(null);
        
        add(appointmentsPanel);
        
        doctorProfilePanel.setVisible(false);
        add(doctorProfilePanel);
    }

    public AppointmentsPanel getAppointmentsPanel() {
        return appointmentsPanel;
    }

    public DoctorProfilePanel getDoctorProfilePanel() {
        return doctorProfilePanel;
    }

    public DoctorApplicationFrame getApplicationFrame() {
        return applicationFrame;
    }
    
    
    
}
