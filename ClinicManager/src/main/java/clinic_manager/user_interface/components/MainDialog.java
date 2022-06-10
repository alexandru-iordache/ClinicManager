/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinic_manager.user_interface.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JDialog;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Iordi
 */
public class MainDialog extends JDialog{
    private String text;

    public MainDialog(String text) {
        setTitle("Announcement!");
        this.text = text;
        init();
    }
    
    private void init(){
        setSize(new Dimension(400, 200));
        setResizable(false);
        setLocationRelativeTo(null);
        
        var dialogPanel = new JPanel();
        dialogPanel.setBounds(0, 0, 400, 200);
        dialogPanel.setPreferredSize(new Dimension(400, 200));
        dialogPanel.setBackground(new Color(110, 38, 14));
        dialogPanel.setLayout(new MigLayout("fill", "[]", "25[]35[]"));
        
        var dialogLabel = new ErrorLabel(text);
        dialogLabel.setFont(new Font("Monospaced", Font.BOLD, 14));
        dialogPanel.add(dialogLabel, "wrap, center");
        
        var dialogButton = new MainButton("Ok");
        dialogButton.setPreferredSize(new Dimension(50, 25));
        dialogButton.addActionListener(e -> {
            this.dispose();
        });
        dialogPanel.add(dialogButton, "center");
        
        this.add(dialogPanel);
        
        this.setVisible(true);
    }
    
}
