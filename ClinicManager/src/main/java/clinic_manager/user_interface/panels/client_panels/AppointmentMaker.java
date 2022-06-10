/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinic_manager.user_interface.panels.client_panels;

import clinic_manager.database_api.DatabaseAPI;
import clinic_manager.entities.Pacient;
import clinic_manager.entities.User;
import clinic_manager.user_interface.components.ErrorLabel;
import clinic_manager.user_interface.components.MainButton;
import clinic_manager.user_interface.components.MainDialog;
import clinic_manager.user_interface.components.MainLabel;
import clinic_manager.user_interface.components.TitleLabel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Iordi
 */
public class AppointmentMaker extends JPanel{
    private final User user;
    private final DatabaseAPI databaseAPI;
    private final Pacient pacient;
    private final ClientViewPanel clientViewPanel;
    
    
    private JComboBox doctorList;
    private JFormattedTextField dateField;
    private ErrorLabel dateError;
    
    public AppointmentMaker(User user, DatabaseAPI databaseAPI, ClientViewPanel clientViewPanel){
        this.user = user;
        
        this.databaseAPI = databaseAPI;
        this.clientViewPanel = clientViewPanel;
        
        this.pacient = this.databaseAPI.getPacientByUser(user);
        
        init();
    }
    
    private void init(){
        setBounds(0, 0, 750, 600);
        setPreferredSize(new Dimension(750, 600));
        setBackground(new Color(110, 38, 14));
        setLayout(new MigLayout("fillx", "[][][][]", "10[]20[][][][]"));

        var titleLabel = new TitleLabel("Make an appointment");
        add(titleLabel, "center, span, wrap");
        
        var doctorLabel = new MainLabel("Doctor: ");
        add(doctorLabel, "right, span 2");
        
        String[] doctorNameList = getDoctorName();
        doctorList = new JComboBox(doctorNameList);
        add(doctorList, "wrap 15, span 2");
        
        var dateLabel = new MainLabel("Date: ");
        add(dateLabel, "right, span 2");
        
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateField = new JFormattedTextField(dateFormat);
        dateField.setColumns(20);
        add(dateField, "wrap 5, span 2");
        
        dateError = new ErrorLabel("Correct date format dd/MM/yyyy*");
        dateError.setVisible(false);
        add(dateError, "wrap 15, span 4, center");
        
        var infoLabel = new MainLabel("La momentul apasarii butonului de commit, se va cauta un loc liber in ziua selectata.");
        infoLabel.setFont(new Font("Monospaced", Font.BOLD, 12) );
        add(infoLabel, "wrap 15, span 4, center");
        
        var infoLabel2 = new MainLabel("Intr-o singura zi o camera poate avea maxim 5 programari, iar un doctor la fel.");
        infoLabel2.setFont(new Font("Monospaced", Font.BOLD, 12));
        add(infoLabel2, "wrap 15, span 4, center");
                
        var commitButton = new MainButton("Commit");
        commitButton.setPreferredSize(new Dimension(100, 50));
        add(commitButton, "span 4, center");
        
        commitButton.addActionListener(e ->{
            addAppointment();
        });
    }

    private String[] getDoctorName() {
        String[] doctors = databaseAPI.getDoctorNames();
        return doctors;
    }
    
    private void addAppointment(){
        dateError.setVisible(false);
        int response = databaseAPI.addAppointment(String.valueOf(doctorList.getSelectedItem()), dateField, pacient);
        
        switch(response){
            case 10 -> {
                var dialogBox = new MainDialog("Programarea a fost realizata cu succes!");
            }
            case 1 -> {
                var dialogBox = new MainDialog("Doctorul nu mai are locuri libere in aceasta zi!");
            }
            case 2 ->{
                var dialogBox = new MainDialog("Nu mai sunt cabinete libere in aceasta zi!");
            }
            case 3 ->{
                var dialogBox = new MainDialog("Eroare neprevazuta!");
            }
            case 4 ->{
                var dialogBox = new MainDialog("Data nu este intr-un format valabil!");
                dateError.setVisible(true);
            }
        }
    }
}
