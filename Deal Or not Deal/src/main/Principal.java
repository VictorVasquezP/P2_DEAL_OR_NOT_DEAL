/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import gui.PrincipalFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author Victor
 */
public class Principal {
    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PrincipalFrame();
               
            }
        });
    }
}
