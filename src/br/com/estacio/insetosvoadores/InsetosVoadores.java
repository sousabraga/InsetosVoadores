/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.estacio.insetosvoadores;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author fic
 */
public class InsetosVoadores extends JFrame {

    private JLabel lblPosicaoMouse;    
    private JLabel lblCoordMouse;
    private JLabel lblVelocidade;
    private JComboBox cbxVelocidades;
    private final String[] velocidades = {"5", "10", "20", "30", "40", "50"};
    private JPanel pnlCentro;
    private JPanel pnlRodape;
    private JPanel pnlMouse;
    private JPanel pnlVelocidade;
    private Inseto inseto;
    
    public InsetosVoadores() {
        super("Insetos Voadores");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(700, 600);
        this.setLocationRelativeTo(null);
        
        startComponents();
        guiOrganized();
        startEvents();
        
        this.add(BorderLayout.CENTER, pnlCentro);
        this.add(BorderLayout.SOUTH, pnlRodape);
    }
    
    private void startComponents() {
        lblPosicaoMouse = new JLabel("Posição do Mouse: ");
        lblCoordMouse = new JLabel("( , )");
        lblVelocidade = new JLabel("Velocidade: ");
        cbxVelocidades = new JComboBox(velocidades);
        pnlCentro = new JPanel(null);
        pnlRodape = new JPanel(new GridLayout(1, 2, 5, 5));
        pnlMouse = new JPanel();
        pnlVelocidade = new JPanel();
        inseto = new Inseto(); 
    }
    
    private void guiOrganized() {
        pnlCentro.setLayout(null);
        pnlCentro.setBackground(new Color(34,139,34));
        pnlCentro.add(inseto);
        inseto.setBounds(50, 50, 130, 130);

        pnlRodape.setBackground(new Color(143,188,143));
        
        pnlMouse.setBackground(new Color(143,188,143));
        pnlMouse.add(lblPosicaoMouse);
        pnlMouse.add(lblCoordMouse);
        
        cbxVelocidades.setFocusable(false);
        pnlVelocidade.setBackground(new Color(143,188,143));
        pnlVelocidade.add(lblVelocidade);
        pnlVelocidade.add(cbxVelocidades);
        
        pnlRodape.add(pnlMouse);
        pnlRodape.add(pnlVelocidade);
    }
    
    private void startEvents(){
        pnlCentro.addMouseMotionListener(new MouseMotionHandler());
        pnlCentro.addMouseListener(new MouseHandler());
        this.addKeyListener(new KeyPressedHandler());
    }
    
    private class MouseMotionHandler implements MouseMotionListener {

        @Override
        public void mouseMoved(MouseEvent e) {
            lblCoordMouse.setText("(" + e.getX() + " , " + e.getY() + ")");
        }
        
        @Override
        public void mouseDragged(MouseEvent e) {
            mouseMoved(e);
            
            inseto.setLocation(e.getX(), e.getY());
        
            int x = e.getX(); 
            int y = e.getY();
            
            if(x < 0) {
                inseto.setLocation(x = 0, y);
            } 
            else if(x > pnlCentro.getWidth() - inseto.getWidth()) {
                inseto.setLocation(x = pnlCentro.getWidth() - inseto.getWidth(), y);
            }
            
            if(y < 0) {
                inseto.setLocation(x, y = 0);
            } 
            else if(y > pnlCentro.getHeight() - inseto.getHeight()) {
                inseto.setLocation(x, y = pnlCentro.getHeight() - inseto.getHeight());
            }         
        }

    }
    
    private class MouseHandler extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            inseto.setLocation(e.getX(), e.getY());
        }

    }
    
    private class KeyPressedHandler extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
             int vel = Integer.parseInt((String) cbxVelocidades.getSelectedItem());
             int x = inseto.getX();
             int y = inseto.getY();
             
             if(e.getKeyCode() == e.VK_UP) { 
                 inseto.setLocation(x ,y - vel);
             }
             else if(e.getKeyCode() == e.VK_DOWN) {
                 inseto.setLocation(x, y + vel);
             }
             else if(e.getKeyCode() == e.VK_LEFT) {
                 inseto.setLocation(x - vel, y);
             }
             else if(e.getKeyCode() == e.VK_RIGHT) {
                 inseto.setLocation(x + vel, y);
             }
             
             if(x < 0) {
                 inseto.setLocation(0, y);
             }
             else if(y < 0) {
                 inseto.setLocation(x, 0);
             }
             else if(x > pnlCentro.getWidth() - inseto.getWidth()) {
                 inseto.setLocation(pnlCentro.getWidth() - inseto.getWidth(), y);
             }
             else if(y > pnlCentro.getHeight() - inseto.getHeight()) {
                 inseto.setLocation(x, pnlCentro.getHeight() - inseto.getHeight());
             }
        }  
    } 
    
    public static void main(String[] args) {
        new InsetosVoadores().setVisible(true);
    }
}
