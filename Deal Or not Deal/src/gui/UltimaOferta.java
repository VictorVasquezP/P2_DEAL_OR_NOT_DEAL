/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Victor
 */
public class UltimaOferta extends JDialog{
    private Integer estado;
    private JLabel lbPregunta;
    private JButton btnElegido;
    private JButton btnUltimo;
    private JPanel pnlOptiones;
    public UltimaOferta(JFrame parent){
        super(parent,true);
        super.setLayout(new BorderLayout());
        super.setSize(new Dimension(200,200));
        estado=0;
        lbPregunta=new JLabel("Que maletin quiere");
        btnElegido=new JButton("ELegido");
        pnlOptiones=new JPanel();
        btnElegido.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                estado=1;
                setVisible(false);
            }
        });
        btnUltimo=new JButton("Ultimo");
        btnUltimo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        pnlOptiones.add(btnUltimo,BorderLayout.WEST);
        pnlOptiones.add(btnElegido,BorderLayout.EAST);
        super.add(lbPregunta,BorderLayout.CENTER);
        super.add(pnlOptiones,BorderLayout.SOUTH);
    }

    public Integer getEstado() {
        return estado;
    }
    
}
