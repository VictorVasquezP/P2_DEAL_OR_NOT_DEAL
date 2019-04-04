/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import java.awt.Color;
import java.awt.Component;
import static java.awt.Component.CENTER_ALIGNMENT;
import java.awt.Font;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.BorderUIResource;

/**
 *
 * @author Victor
 */
public class ArrayLabel {
    private ArrayList<JLabel>labels;
    private ArrayList<Integer>labell;
    private TitledBorder border;
    private ImageIcon img1;
    private ImageIcon img2;
    public ArrayLabel(ArrayList<Integer> a){
       //Collections.shuffle(a);
       labell=new ArrayList<>();
       labell=(ArrayList<Integer>) a.clone();
       desord();
       labels = new ArrayList<>();
       img1=new ImageIcon(getClass().getResource("/imagenes/labels.jpg"));
       img2=new ImageIcon(img1.getImage().getScaledInstance(120,50,Image.SCALE_DEFAULT));
       // 10 tamaño, yellow margen , black una linea
       border =new TitledBorder(new BorderUIResource.EtchedBorderUIResource(10, Color.BLUE, Color.BLACK));
       
        for(int i=0;i<a.size();i++){
            
            JLabel label = new JLabel();
            label.setForeground(Color.BLACK);
            label.setBorder(border);           
            //agregar imagen
            label.setIcon(img2);
            label.setText(String.valueOf(labell.get(i)));
            label.setHorizontalTextPosition((int) CENTER_ALIGNMENT);
            label.setFont(new Font("Times New Roman",8,16));
            
            labels.add(label);
            
        }
    }
    public ArrayList<Integer> getLabel() {
        return labell;
    }
    
    public ArrayList<JLabel> getLabels() {
        return labels;
    }

    public void setLabels(ArrayList<JLabel> labels) {
        this.labels = labels;
    }
    public Integer buscar(Integer valor){
        int pos=0;
        for(int i=0;i<this.getLabel().size();i++){
            if(valor==this.getLabel().get(i)){
                pos=i;
                break;
            }
        }
        return pos;
    }
    public void desord(){
        Collections.shuffle(this.labell);
    }
}
