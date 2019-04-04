/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import static java.awt.Component.RIGHT_ALIGNMENT;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.BorderUIResource;

/**
 *
 * @author Victor
 */
public class PreguntaAbrir extends JDialog{
    private JButton btnAbrir;
    private JButton btnCancelar;
    private JLabel lbPregunta;
    private JPanel pnlOption;
    private Integer a;
    private TitledBorder border;
    //private AbrirMaletin abrir;
    public PreguntaAbrir(JFrame parent) {
        super(parent,true);
        super.setSize(200,200);
        super.setLayout(new BorderLayout());
        
        btnAbrir=new JButton("Abrir");
        border =new TitledBorder(new BorderUIResource.EtchedBorderUIResource(10, Color.YELLOW, Color.BLACK));
        //abrir = new AbrirMaletin(this);
        
        
        a=0;
        btnAbrir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                a=1;
                //abrir.setLbl2(lbl3);
                //abrir.setVisible(true);
                setVisible(false);
            }
        });
        
        btnCancelar=new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                a=0;
                setVisible(false);
            }
        });
        lbPregunta=new JLabel("          ¿Quiere abrirlo?");
        lbPregunta.setLayout(new FlowLayout((int)RIGHT_ALIGNMENT,50,90));
        lbPregunta.setBorder(border);
        pnlOption=new JPanel(new BorderLayout());
        pnlOption.add(btnAbrir,BorderLayout.EAST);
        pnlOption.add(btnCancelar,BorderLayout.WEST);
        super.setBackground(Color.DARK_GRAY);
        super.add(lbPregunta,BorderLayout.CENTER);
        super.add(pnlOption,BorderLayout.SOUTH);
        
    }
    public Integer getA() {
        return a;
    }
    
}
