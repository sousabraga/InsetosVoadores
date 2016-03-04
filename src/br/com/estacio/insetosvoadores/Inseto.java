/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.estacio.insetosvoadores;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author fic
 */
public class Inseto extends JPanel {
    
    private JLabel lblInseto = new JLabel("Inseto");    
    private ImageIcon inseto = new ImageIcon(getClass().getResource("folder/abelha.gif"));
    private JLabel gif = new JLabel(inseto);
    
    public Inseto() {
        this.setBackground(new Color(34,139,34));
        lblInseto.setForeground(Color.WHITE);
        
        this.add(gif);
        this.add(lblInseto);   
    }
    
}
