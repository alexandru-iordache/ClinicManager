/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinic_manager.user_interface.components;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;

/**
 *
 * @author Iordi
 */
public class ErrorLabel extends JLabel{
    
     public ErrorLabel(String text) {
        setText(text);
        init();
    }

    private void init(){
        setFont(new Font("Monospaced", Font.BOLD, 10));
        setForeground(new Color(255, 245, 245));
    }
    
}
