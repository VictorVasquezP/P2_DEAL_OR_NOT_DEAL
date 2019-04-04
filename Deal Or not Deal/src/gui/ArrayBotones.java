/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import objetos.ArrayIcon;

/**
 *
 * @author Victor
 */
public class ArrayBotones {
    private ArrayList<JButton>botones;
    private ArrayIcon iconos;
    public ArrayBotones(){
       botones = new ArrayList<>();
       agregar();
    }
    public void agregar(){
        iconos = new ArrayIcon();
        for(ImageIcon a: iconos.getIconos()){
            this.botones.add(new JButton(a));
        }
    }
    public ArrayList<JButton> getBotones() {
        return botones;
    }
    public void setBotones(ArrayList<JButton> botones) {
        this.botones = botones;
    }
    
}
