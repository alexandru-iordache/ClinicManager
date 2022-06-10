/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinic_manager.user_interface.panels.doctor_panels;

import clinic_manager.entities.User;
import clinic_manager.user_interface.components.MainButton;
import clinic_manager.user_interface.components.MainLabel;
import clinic_manager.user_interface.panels.doctor_panels.DoctorViewPanel;
import clinic_manager.user_interface.frames.DoctorApplicationFrame;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Iordi
 */
public class DoctorMenuPanel extends JPanel{
    
    private final DoctorApplicationFrame applicationFrame;
    private final User user;

    public DoctorMenuPanel(DoctorApplicationFrame applicationFrame, User user) {
        this.applicationFrame = applicationFrame;
        this.user = user;
        
        init();
    }
    
    private void init(){
        setBounds(0, 0, 250, 600);
        setBackground(new Color(110, 38, 14));
        setLayout(new MigLayout("fill", "[]", "[]"));
        
        var titleLabel = new MainLabel("Menu");
        titleLabel.setFont(new Font("Monospaced", Font.BOLD, 30));
        add(titleLabel, "center, wrap 20");
        
        var tableButton = new MainButton("Appointments");
        tableButton.setPreferredSize(new Dimension(200, 75));
        tableButton.addActionListener(e -> {
            this.applicationFrame.getDoctorViewPanel().getDoctorProfilePanel().setVisible(false);
            this.applicationFrame.getDoctorViewPanel().getAppointmentsPanel().setVisible(true);
        });
        add(tableButton, "center, wrap 20");
        
        
        var profileButton = new MainButton("Profile");
        profileButton.setPreferredSize(new Dimension(200, 75));
        profileButton.addActionListener(e -> {
            this.applicationFrame.getDoctorViewPanel().getAppointmentsPanel().setVisible(false);
            this.applicationFrame.getDoctorViewPanel().getDoctorProfilePanel().setVisible(true);
        });
        add(profileButton, "center, wrap 20 ");
        
        var backButton = new MainButton("Back");
        backButton.setPreferredSize(new Dimension(200, 75));
        backButton.addActionListener(e -> {
            this.applicationFrame.getOpeningFrame().setVisible(true);
            this.applicationFrame.dispose();
        });
        add(backButton, "center, wrap 20 ");
        
        var exitButton = new MainButton("Exit");
        exitButton.setPreferredSize(new Dimension(200, 75));
        exitButton.addActionListener(e -> {
            this.applicationFrame.getOpeningFrame().dispose();
            this.applicationFrame.dispose();
        });
        add(exitButton, "center, wrap 20 ");
    
}
}