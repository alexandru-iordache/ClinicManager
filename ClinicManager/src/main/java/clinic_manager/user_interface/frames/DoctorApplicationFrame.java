/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinic_manager.user_interface.frames;

import clinic_manager.entities.User;
import clinic_manager.user_interface.panels.doctor_panels.DoctorMenuPanel;
import clinic_manager.user_interface.panels.doctor_panels.DoctorViewPanel;
import javax.swing.JFrame;

/**
 *
 * @author Iordi
 */
public class DoctorApplicationFrame extends JFrame{
    private final OpeningFrame openingFrame;
     
    private final DoctorMenuPanel doctorMenuPanel;
    private final DoctorViewPanel doctorViewPanel;
    
    private final User user;

    public DoctorApplicationFrame(OpeningFrame openingFrame, User user) {
        this.openingFrame = openingFrame;
        this.user = user;
        
        this.doctorMenuPanel = new DoctorMenuPanel(this, user);
        this.doctorViewPanel = new DoctorViewPanel(this, this.openingFrame.getDatabaseAPI(), this.user);
        
        init();
    }
    
    private void init(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000,600);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
     
        add(doctorMenuPanel);
        add(doctorViewPanel);
    }

    public OpeningFrame getOpeningFrame() {
        return openingFrame;
    }

    public DoctorMenuPanel getDoctorMenuPanel() {
        return doctorMenuPanel;
    }

    public DoctorViewPanel getDoctorViewPanel() {
        return doctorViewPanel;
    }
    
    
    
}
