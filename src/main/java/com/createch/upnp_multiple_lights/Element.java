/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.createch.upnp_multiple_lights;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author telly
 */
public class Element extends JPanel{
    private Color couleur=Color.BLUE;
    private boolean started;
    
    public Element(){
        started = false;
        initComponents();
    }
    
    public void selectionner(){
        Border border = BorderFactory.createLineBorder(Color.WHITE);
        setBorder(border);
    }
    
    public void deselectionner(){
       setBorder(new EmptyBorder(new Insets(0, 0, 0, 0)));
    }
    
    @Override
    public void paintComponent(Graphics grphcs) {
        super.paintComponents(grphcs);
        if(started == true){
            grphcs.setColor(couleur);
            grphcs.fillOval(0, 0, getWidth(), getHeight());
        }else {
            grphcs.setColor(Color.DARK_GRAY);
            grphcs.fillOval(0, 0, getWidth(), getHeight());
        }
    }
    
    public void comporter(){
        started = !started;
        repaint();
    }
    private void initComponents() {
        javax.swing.GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                    layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                         .addGap(0,300,Short.MAX_VALUE)
        );
    }
}
