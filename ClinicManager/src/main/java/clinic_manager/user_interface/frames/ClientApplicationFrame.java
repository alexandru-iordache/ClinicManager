/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinic_manager.user_interface.frames;

import clinic_manager.entities.User;
import clinic_manager.user_interface.panels.client_panels.ClientMenuPanel;
import clinic_manager.user_interface.panels.client_panels.ClientViewPanel;
import javax.swing.JFrame;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 *
 * @author Iordi
 */
public class ClientApplicationFrame extends JFrame{
    private final OpeningFrame openingFrame;
    
    private final ClientMenuPanel clientMenuPanel;
    private final ClientViewPanel clientViewPanel;
    
    private final User user;
    
    public ClientApplicationFrame(OpeningFrame openingFrame, User user) {
        super("Clinic Manager");
        
        this.openingFrame = openingFrame;
        
        this.user = user;
        
        this.clientMenuPanel = new ClientMenuPanel(this, user);
        this.clientViewPanel = new ClientViewPanel(this, this.openingFrame.getDatabaseAPI(), this.user);
        
        init();
    }

    private void init() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000,600);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
        
        add(clientMenuPanel);
        add(clientViewPanel);
        

    }

    public OpeningFrame getOpeningFrame() {
        return this.openingFrame;
    }

    public ClientViewPanel getClientViewPanel() {
        return clientViewPanel;
    }
    
    
}

