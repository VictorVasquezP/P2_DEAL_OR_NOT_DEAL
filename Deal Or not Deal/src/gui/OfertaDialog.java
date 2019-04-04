/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.BorderUIResource;

/**
 *
 * @author Victor
 */
public class OfertaDialog extends JDialog{
    private JButton btnAceptar;
    private JButton btnCancelar;
    private JPanel pnlOferta;
    private JLabel lblmsg;
    private ArrayList<Integer>random;
    private JPanel pnlOp;
    private JPanel pnlofer;
    private Integer c;
    private Integer i;
    private Integer x;
    private JLabel ofer;
    private TitledBorder border;
    public OfertaDialog(JFrame parent){
        super(parent,true);
        super.setSize(200,200);
        super.setLayout(new BorderLayout());
        super.setBackground(Color.BLACK);
        border =new TitledBorder(new BorderUIResource.EtchedBorderUIResource(10, Color.YELLOW, Color.BLACK));
        pnlOp=new JPanel(new BorderLayout());
        random=new ArrayList<>();
        x=0;
        i=1000;
        while(i<300000){
            random.add(i);
            i+=10000;
        }
        deso();
        x = (int)(Math.random()*random.size()-1)+1;
        pnlofer = new JPanel(new BorderLayout());
        ofer = new JLabel(random.get(x).toString());
        ofer.setBorder(border);
        pnlofer.add(ofer,BorderLayout.CENTER);
        pnlOferta=new JPanel(new FlowLayout((int)CENTER_ALIGNMENT,10,50));
        //Integer b = calcOf(a);
        lblmsg=new JLabel("Acepta la oferta?");
        lblmsg.setBorder(border);
        c=0;
        //pnlOferta.add(lbl);
        pnlOferta.add(lblmsg);
        btnAceptar=new JButton("Aceptar");
        btnCancelar=new JButton("Rechazar");
        btnAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c=1;
                //Ponerle una funcion cuando acepta la oferta
                setVisible(false);
            }
        });
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                deso();
                x = (int)(Math.random()*random.size()-1)+1;
                ofer.setText(random.get(x).toString());
            }
        });
       pnlOp.add(btnAceptar,BorderLayout.EAST);
       pnlOp.add(btnCancelar,BorderLayout.WEST);
       super.add(ofer,BorderLayout.NORTH);
       super.add(pnlOferta,BorderLayout.CENTER);
       super.add(new JScrollPane(pnlOp),BorderLayout.SOUTH);
    }

    public Integer getC() {
        return c;
    }
    public void deso(){
        Collections.shuffle(this.random);
    }
}
