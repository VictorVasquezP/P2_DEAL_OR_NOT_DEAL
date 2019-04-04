/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.BorderUIResource;
import main.Principal;
import objetos.ArrayControl;
import objetos.ArrayLabel;
import objetos.ArrayOfertas;
/**
 *
 * @author Victor
 */
public class PrincipalFrame extends JFrame{
    private JButton btnjugar;
    private ImageIcon img;
    private JPanel pnlBoton;
    private JPanel pnlImag;
    private JPanel pnlIzq;
    private JPanel pnlTablero;
    private JPanel pnlDer;
    private JLabel lbleleccion;
    private JPanel pnlBotonesOp;
    private JuegoDialog datos;
    private ArrayBotones botones;
    private ArrayLabel labels;
    private ArrayOfertas ofertas;
    private Integer elegido;
    private Integer cont;
    private Integer maletin;
    private OfertaDialog ofertadialog;
    private PreguntaAbrir preguntAbrir;
    private Image fondo1;
    private ArrayList<Integer> control;
    private ArrayList<Integer> abiertos;
    private UltimaOferta ultima;
    public PrincipalFrame(){
        super("DEAL OR NOT DEAL");
        super.setSize(1200,700);
        //super.setExtendedState(MAXIMIZED_BOTH);
        super.setDefaultCloseOperation(EXIT_ON_CLOSE);
        super.setLayout(new BorderLayout());
        control = new ArrayList<>();
        abiertos = new ArrayList<>();
        ultima=new UltimaOferta(this);
        for(int x=0;x<24;x++){
            control.add(0);
            abiertos.add(0);
        }
        elegido=0;
        cont=0;
        maletin=0;
        // FALTA COPIAR Y PEGAR LOS COMPORTAMIENTOS DE LOS OTROS BOTONES
        ofertas = new ArrayOfertas();
        ofertas.desordenar();
        preguntAbrir=new PreguntaAbrir(this);
        botones = new ArrayBotones();
        labels = new ArrayLabel(ofertas.getOfertas());
        pnlTablero=new JPanel(new FlowLayout((int)RIGHT_ALIGNMENT,50,90)){
            @Override
            public void paint(Graphics g) {
                fondo1=null;
                try {
                    fondo1=ImageIO.read(new File("C:\\Users\\Victor\\Documents\\NetBeansProjects\\Deal_OR_Not_Deal\\src\\imagenes\\fondoc.jpg"));
                } catch (IOException ex) {
                    System.out.println("No esta");
                }
                g.drawImage(fondo1, 0, 0,getWidth(),getHeight(),this);
                setOpaque(false);
                super.paint(g); 
            }
           
        };
        pnlTablero.setBackground(Color.BLACK);
        TitledBorder border =new TitledBorder(new BorderUIResource.EtchedBorderUIResource(30, Color.YELLOW, Color.BLACK));
        pnlTablero.setBorder(border);
        btnjugar = new JButton();
        btnjugar.setPreferredSize(new Dimension(80,80));
        //datos = new JuegoDialog(this);
        //pnlIzq = new JPanel(new FlowLayout((int)CENTER_ALIGNMENT,50,30));
        pnlIzq = new JPanel(new GridLayout(12,0));
        pnlIzq.setBackground(Color.decode("#302D3C"));
        pnlIzq.setPreferredSize(new Dimension(120,0));
        pnlDer = new JPanel(new GridLayout(12,0));
        pnlDer.setBackground(Color.decode("#302D3C"));
        pnlDer.setPreferredSize(new Dimension(120,0));
        lbleleccion=new JLabel("ELIGE UN MALETIN");
        ofertadialog=new OfertaDialog(this); 
        //border =new TitledBorder(new BorderUIResource.EtchedBorderUIResource(10, Color.YELLOW, Color.BLACK));
        for(int i=0;i<24;i++){
            botones.getBotones().get(i).setPreferredSize(new Dimension(70,60));
        }
        for(int i=0;i<24;i++){
            pnlTablero.add(botones.getBotones().get(i));
        }
        for(int n=0;n<12;n++){
            //labels.getLabels().get(n).setBorder(border);
            pnlIzq.add(labels.getLabels().get(n));
            pnlDer.add(labels.getLabels().get(n+12));
        }
        pnlBoton=new JPanel(new FlowLayout());
        pnlBoton.add(new JLabel("Click para jugar "));
        pnlBoton.add(btnjugar);
        img = new ImageIcon(getClass().getResource("/imagenes/icono1.gif"));
        //ImageIcon img2 = new ImageIcon(img.getImage().getScaledInstance(80, 80,Image.SCALE_DEFAULT));
        ImageIcon img3 = new ImageIcon(getClass().getResource("/imagenes/botonjugar.png"));
        ImageIcon img4 = new ImageIcon(img3.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT));
        btnjugar.setIcon(img4);
        btnjugar.setBorderPainted(false);
        JLabel gif = new JLabel(img);
        pnlImag = new JPanel(new BorderLayout());
        pnlImag.add(gif,BorderLayout.NORTH);
        pnlImag.add(pnlBoton,BorderLayout.SOUTH);
        
        pnlBotonesOp=new JPanel();
        pnlBotonesOp.add(lbleleccion);
        btnjugar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              pnlImag.setVisible(false);
              pnlBoton.setVisible(false);
              //datos.setVisible(true);
              //if(datos.activo()){
                  pnlTablero.setVisible(true);
                  pnlDer.setVisible(true);
                  pnlIzq.setVisible(true);
                  pnlBotonesOp.setVisible(true);
              //}
            }
        });
        //termina juego
        botones.getBotones().get(0).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(elegido==0){
                   msg("1");
                   maletin=ofertas.getOfertas().get(0);
                   lbleleccion.setVisible(false);
                   elegido=1;
                   botones.getBotones().get(0).setBackground(Color.red);
                   //lbleleccion.add(botones.getBotones().get(0));
                   control.set(0,1);
                   abiertos.set(0,1);
                }else{
                    if(control.get(0)==0){
                        //HACER UN DIALOG APARTE EN LA MISMA CLASE
                        preguntAbrir.setVisible(true);
                        if(preguntAbrir.getA()==1){
                            abiertos.set(0,1);
                            msgValor(ofertas.getOfertas().get(0).toString());
                            cont++;
                            //checar eso
                            botones.getBotones().get(0).setVisible(false);
                            int b= labels.buscar(ofertas.getOfertas().get(0));
                            labels.getLabels().get(b).setText("-----");
                            if(cont%4==0){
                            ofertadialog.setVisible(true);
                            // c =1 acepto la banca
                            if(ofertadialog.getC()==1){
                                terminado();
                                System.exit(0);
                            }
                            }else{
                                if(cont==22){
                                    //elegido = 1
                                    ultima.setVisible(true);
                                    if(ultima.getEstado()==0){
                                        for(int h=0;h<24;h++){
                                            if(abiertos.get(h)==0){
                                                //abiertos nos da la posicion
                                                msgValoresUlt(ofertas.getOfertas().get(h).toString());
                                                
                                            }
                                        }
                                    }else{
                                        msgValoresUlt(maletin.toString());
                                         }
                                    terminado();
                                    System.exit(0);
                                    }
                            }
                            //abrir.setVisible(true);
                        }
                    }
                }
            }
        });
        botones.getBotones().get(1).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(elegido==0){
                  maletin=ofertas.getOfertas().get(1);
                  msg("2");
                  elegido=1;
                  botones.getBotones().get(1).setBackground(Color.red);
                  control.set(1,1);
                  abiertos.set(1,1);
                  lbleleccion.setVisible(false);
                }else{
                    if(control.get(1)==0){
                        preguntAbrir.setVisible(true);
                        if(preguntAbrir.getA()==1){
                            abiertos.set(1,1);
                            msgValor(ofertas.getOfertas().get(1).toString());
                            cont++;
                            //checar eso
                            botones.getBotones().get(1).setVisible(false);
                            int b= labels.buscar(ofertas.getOfertas().get(1));
                            labels.getLabels().get(b).setText("-----");
                            if(cont%4==0){
                            ofertadialog.setVisible(true);
                            if(ofertadialog.getC()==1){
                                terminado();
                                System.exit(0);
                            }
                            }else{
                                if(cont==22){
                                    //elegido = 1
                                    ultima.setVisible(true);
                                    if(ultima.getEstado()==0){
                                        for(int h=0;h<24;h++){
                                            if(abiertos.get(h)==0){
                                                //abiertos nos da la posicion
                                                msgValoresUlt(ofertas.getOfertas().get(h).toString());
                                                
                                            }
                                        }
                                    }else{
                                        msgValoresUlt(maletin.toString());
                                         }
                                    terminado();
                                    System.exit(0);
                                    }
                            }
                        }
                    }
                }
            }
        });
        botones.getBotones().get(2).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(elegido==0){
                  maletin=ofertas.getOfertas().get(2);
                  msg("3");
                  elegido=1;
                  botones.getBotones().get(2).setBackground(Color.red);
                  control.set(2,1);
                  abiertos.set(2,1);
                  lbleleccion.setVisible(false);
                }else{
                   if(control.get(2)==0){
                        preguntAbrir.setVisible(true);
                        if(preguntAbrir.getA()==1){
                            abiertos.set(2,1);
                            msgValor(ofertas.getOfertas().get(2).toString());
                            cont++;
                            //checar eso
                            botones.getBotones().get(2).setVisible(false);
                            int b= labels.buscar(ofertas.getOfertas().get(2));
                            labels.getLabels().get(b).setText("-----");
                            if(cont%4==0){
                            ofertadialog.setVisible(true);
                            if(ofertadialog.getC()==1){
                                terminado();
                                System.exit(0);
                            }
                            }else{
                                if(cont==22){
                                    //elegido = 1
                                    ultima.setVisible(true);
                                    if(ultima.getEstado()==0){
                                        for(int h=0;h<24;h++){
                                            if(abiertos.get(h)==0){
                                                //abiertos nos da la posicion
                                                msgValoresUlt(ofertas.getOfertas().get(h).toString());
                                                
                                            }
                                        }
                                    }else{
                                        msgValoresUlt(maletin.toString());
                                        }
                                    terminado();
                                    System.exit(0);
                                    }
                            }
                        }
                    }
                }
            }
        });
        botones.getBotones().get(3).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(elegido==0){
                  msg("4");
                  elegido=1;
                  maletin=ofertas.getOfertas().get(3);
                  botones.getBotones().get(3).setBackground(Color.red);
                  control.set(3,1);
                  abiertos.set(3,1);
                  lbleleccion.setVisible(false);
                }else{
                   if(control.get(3)==0){
                        preguntAbrir.setVisible(true);
                        if(preguntAbrir.getA()==1){
                            abiertos.set(3,1);
                            msgValor(ofertas.getOfertas().get(3).toString());
                            cont++;
                            //checar eso
                            botones.getBotones().get(3).setVisible(false);
                            int b= labels.buscar(ofertas.getOfertas().get(3));
                            labels.getLabels().get(b).setText("-----");
                            if(cont%4==0){
                            ofertadialog.setVisible(true);
                            if(ofertadialog.getC()==1){
                                terminado();
                                System.exit(0);
                            }
                            }else{
                                if(cont==22){
                                    //elegido = 1
                                    ultima.setVisible(true);
                                    if(ultima.getEstado()==0){
                                        for(int h=0;h<24;h++){
                                            if(abiertos.get(h)==0){
                                                //abiertos nos da la posicion
                                                msgValoresUlt(ofertas.getOfertas().get(h).toString());
                                                
                                            }
                                        }
                                    }else{
                                        msgValoresUlt(maletin.toString());
                                        }
                                    terminado();
                                    System.exit(0);
                                    }
                            }
                        }
                    }
                }
            }
        });
        botones.getBotones().get(4).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(elegido==0){
                  msg("5");
                  maletin=ofertas.getOfertas().get(4);
                  elegido=1;
                  botones.getBotones().get(4).setBackground(Color.red);
                  control.set(4,1);
                  abiertos.set(4,1);
                  lbleleccion.setVisible(false);
                }else{
                    if(control.get(4)==0){
                        preguntAbrir.setVisible(true);
                        if(preguntAbrir.getA()==1){
                            abiertos.set(4,1);
                            msgValor(ofertas.getOfertas().get(4).toString());
                            cont++;
                            //checar eso
                            botones.getBotones().get(4).setVisible(false);
                            int b= labels.buscar(ofertas.getOfertas().get(4));
                            labels.getLabels().get(b).setText("-----");
                            if(cont%4==0){
                            ofertadialog.setVisible(true);
                            if(ofertadialog.getC()==1){
                                terminado();
                                System.exit(0);
                            }
                            }else{
                                if(cont==22){
                                    //elegido = 1
                                    ultima.setVisible(true);
                                    if(ultima.getEstado()==0){
                                        for(int h=0;h<24;h++){
                                            if(abiertos.get(h)==0){
                                                //abiertos nos da la posicion
                                                msgValoresUlt(ofertas.getOfertas().get(h).toString());
                                                
                                            }
                                        }
                                    }else{
                                        msgValoresUlt(maletin.toString());
                                        }
                                    terminado();
                                    System.exit(0);
                                    }
                            }
                        }
                    }
                }
            }
        });
        botones.getBotones().get(5).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(elegido==0){
                  msg("6");
                  maletin=ofertas.getOfertas().get(5);
                  elegido=1;
                  botones.getBotones().get(5).setBackground(Color.red);
                  control.set(5,1);
                  abiertos.set(5,1);
                  lbleleccion.setVisible(false);
                }else{
                   if(control.get(5)==0){
                        preguntAbrir.setVisible(true);
                        if(preguntAbrir.getA()==1){
                            abiertos.set(5,1);
                            msgValor(ofertas.getOfertas().get(5).toString());
                            cont++;
                            //checar eso
                            botones.getBotones().get(5).setVisible(false);
                            int b= labels.buscar(ofertas.getOfertas().get(5));
                            labels.getLabels().get(b).setText("-----");
                            if(cont%4==0){
                            ofertadialog.setVisible(true);
                            if(ofertadialog.getC()==1){
                                terminado();
                                System.exit(0);
                            }
                            }else{
                                if(cont==22){
                                    //elegido = 1
                                    ultima.setVisible(true);
                                    if(ultima.getEstado()==0){
                                        for(int h=0;h<24;h++){
                                            if(abiertos.get(h)==0){
                                                //abiertos nos da la posicion
                                                msgValoresUlt(ofertas.getOfertas().get(h).toString());
                                                
                                            }
                                        }
                                    }else{
                                        msgValoresUlt(maletin.toString());
                                        }
                                    terminado();
                                    System.exit(0);
                                    }
                            }
                        }
                    }
                }
            }
        });
        botones.getBotones().get(6).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(elegido==0){
                  msg("7");
                  maletin=ofertas.getOfertas().get(6);
                  elegido=1;
                  botones.getBotones().get(6).setBackground(Color.red);
                  control.set(6,1);
                  abiertos.set(6,1);
                  lbleleccion.setVisible(false);
                }else{
                   if(control.get(6)==0){
                        preguntAbrir.setVisible(true);
                        if(preguntAbrir.getA()==1){
                            abiertos.set(6,1);
                            msgValor(ofertas.getOfertas().get(6).toString());
                            cont++;
                            //checar eso
                            botones.getBotones().get(6).setVisible(false);
                            int b= labels.buscar(ofertas.getOfertas().get(6));
                            labels.getLabels().get(b).setText("-----");
                            if(cont%4==0){
                            ofertadialog.setVisible(true);
                            if(ofertadialog.getC()==1){
                                terminado();
                                System.exit(0);
                            }
                            }else{
                                if(cont==22){
                                    //elegido = 1
                                    ultima.setVisible(true);
                                    if(ultima.getEstado()==0){
                                        for(int h=0;h<24;h++){
                                            if(abiertos.get(h)==0){
                                                //abiertos nos da la posicion
                                                msgValoresUlt(ofertas.getOfertas().get(h).toString());
                                                
                                            }
                                        }
                                    }else{
                                        msgValoresUlt(maletin.toString());
                                        }
                                    terminado();
                                    System.exit(0);
                                    }
                            }
                        }
                    }
                }
            }
        });
        botones.getBotones().get(7).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(elegido==0){
                  msg("8");
                  maletin=ofertas.getOfertas().get(7);
                  elegido=1;
                  botones.getBotones().get(7).setBackground(Color.red);
                  control.set(7,1);
                  abiertos.set(7,1);
                  lbleleccion.setVisible(false);
                }else{
                   if(control.get(7)==0){
                        preguntAbrir.setVisible(true);
                        if(preguntAbrir.getA()==1){
                            abiertos.set(7,1);
                            msgValor(ofertas.getOfertas().get(7).toString());
                            cont++;
                            //checar eso
                            botones.getBotones().get(7).setVisible(false);
                            int b= labels.buscar(ofertas.getOfertas().get(7));
                            labels.getLabels().get(b).setText("-----");
                            if(cont%4==0){
                            ofertadialog.setVisible(true);
                            if(ofertadialog.getC()==1){
                                terminado();
                                System.exit(0);
                            }
                           }else{
                                if(cont==22){
                                    //elegido = 1
                                    ultima.setVisible(true);
                                    if(ultima.getEstado()==0){
                                        for(int h=0;h<24;h++){
                                            if(abiertos.get(h)==0){
                                                //abiertos nos da la posicion
                                                msgValoresUlt(ofertas.getOfertas().get(h).toString());
                                                
                                            }
                                        }
                                    }else{
                                        msgValoresUlt(maletin.toString());
                                        }
                                    terminado();
                                    System.exit(0);
                                    }
                            }
                        }
                    }
                }
            }
        });
        botones.getBotones().get(8).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(elegido==0){
                  msg("9");
                  maletin=ofertas.getOfertas().get(8);
                  elegido=1;
                  botones.getBotones().get(8).setBackground(Color.red);
                  control.set(8,1);
                  abiertos.set(8,1);
                  lbleleccion.setVisible(false);
                }else{
                   if(control.get(8)==0){
                        preguntAbrir.setVisible(true);
                        if(preguntAbrir.getA()==1){
                            abiertos.set(8,1);
                            msgValor(ofertas.getOfertas().get(8).toString());
                            cont++;
                            //checar eso
                            botones.getBotones().get(8).setVisible(false);
                            int b= labels.buscar(ofertas.getOfertas().get(8));
                            labels.getLabels().get(b).setText("-----");
                            if(cont%4==0){
                            ofertadialog.setVisible(true);
                            if(ofertadialog.getC()==1){
                                terminado();
                                System.exit(0);
                            }
                           }else{
                                if(cont==22){
                                    //elegido = 1
                                    ultima.setVisible(true);
                                    if(ultima.getEstado()==0){
                                        for(int h=0;h<24;h++){
                                            if(abiertos.get(h)==0){
                                                //abiertos nos da la posicion
                                                msgValoresUlt(ofertas.getOfertas().get(h).toString());
                                                
                                            }
                                        }
                                    }else{
                                        msgValoresUlt(maletin.toString());
                                        }
                                    terminado();
                                    System.exit(0);
                                    }
                            }
                        }
                    }
                }
            }
        });
        botones.getBotones().get(9).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(elegido==0){
                  msg("10");
                  maletin=ofertas.getOfertas().get(9);
                  elegido=1;
                  botones.getBotones().get(9).setBackground(Color.red);
                  control.set(9,1);
                  abiertos.set(9,1);
                  lbleleccion.setVisible(false);
                }else{
                   if(control.get(9)==0){
                        preguntAbrir.setVisible(true);
                        if(preguntAbrir.getA()==1){
                            abiertos.set(9,1);
                            msgValor(ofertas.getOfertas().get(9).toString());
                            cont++;
                            //checar eso
                            botones.getBotones().get(9).setVisible(false);
                            int b= labels.buscar(ofertas.getOfertas().get(9));
                            labels.getLabels().get(b).setText("-----");
                            if(cont%4==0){
                            ofertadialog.setVisible(true);
                            if(ofertadialog.getC()==1){
                                terminado();
                                System.exit(0);
                            }
                           }else{
                                if(cont==22){
                                    //elegido = 1
                                    ultima.setVisible(true);
                                    if(ultima.getEstado()==0){
                                        for(int h=0;h<24;h++){
                                            if(abiertos.get(h)==0){
                                                //abiertos nos da la posicion
                                                msgValoresUlt(ofertas.getOfertas().get(h).toString());
                                                
                                            }
                                        }
                                    }else{
                                        msgValoresUlt(maletin.toString());
                                        }
                                    terminado();
                                    System.exit(0);
                                    }
                            }
                        }
                    }
                }
            }
        });
        botones.getBotones().get(10).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(elegido==0){
                  msg("11");
                  maletin=ofertas.getOfertas().get(10);
                  elegido=1;
                  botones.getBotones().get(10).setBackground(Color.red);
                  control.set(10,1);
                  abiertos.set(10,1);
                  lbleleccion.setVisible(false);
                }else{
                   if(control.get(10)==0){
                        preguntAbrir.setVisible(true);
                        if(preguntAbrir.getA()==1){
                            abiertos.set(10,1);
                            msgValor(ofertas.getOfertas().get(10).toString());
                            cont++;
                            //checar eso
                            botones.getBotones().get(10).setVisible(false);
                            int b= labels.buscar(ofertas.getOfertas().get(10));
                            labels.getLabels().get(b).setText("-----");
                            if(cont%4==0){
                            ofertadialog.setVisible(true);
                            if(ofertadialog.getC()==1){
                                terminado();
                                System.exit(0);
                            }
                            }else{
                                if(cont==22){
                                    //elegido = 1
                                    ultima.setVisible(true);
                                    if(ultima.getEstado()==0){
                                        for(int h=0;h<24;h++){
                                            if(abiertos.get(h)==0){
                                                //abiertos nos da la posicion
                                                msgValoresUlt(ofertas.getOfertas().get(h).toString());
                                                
                                            }
                                        }
                                    }else{
                                        msgValoresUlt(maletin.toString());
                                        }
                                    terminado();
                                    System.exit(0);
                                    }
                            }
                        }
                    }
                }
            }
        });
        botones.getBotones().get(11).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(elegido==0){
                  msg("12");
                  maletin=ofertas.getOfertas().get(11);
                  elegido=1;
                  botones.getBotones().get(11).setBackground(Color.red);
                  control.set(11,1);
                  abiertos.set(11,1);
                  lbleleccion.setVisible(false);
                }else{
                   if(control.get(11)==0){
                        preguntAbrir.setVisible(true);
                        if(preguntAbrir.getA()==1){
                            abiertos.set(11,1);
                            msgValor(ofertas.getOfertas().get(11).toString());
                            cont++;
                            //checar eso
                            botones.getBotones().get(11).setVisible(false);
                            int b= labels.buscar(ofertas.getOfertas().get(11));
                            labels.getLabels().get(b).setText("-----");
                            if(cont%4==0){
                            ofertadialog.setVisible(true);
                            if(ofertadialog.getC()==1){
                                terminado();
                                System.exit(0);
                            }
                            }else{
                                if(cont==22){
                                    //elegido = 1
                                    ultima.setVisible(true);
                                    if(ultima.getEstado()==0){
                                        for(int h=0;h<24;h++){
                                            if(abiertos.get(h)==0){
                                                //abiertos nos da la posicion
                                                msgValoresUlt(ofertas.getOfertas().get(h).toString());
                                                
                                            }
                                        }
                                    }else{
                                        msgValoresUlt(maletin.toString());
                                        }
                                    terminado();
                                    System.exit(0);
                                    }
                            }
                        }
                    }
                }
            }
        });
        botones.getBotones().get(12).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(elegido==0){
                  msg("13");
                  maletin=ofertas.getOfertas().get(12);
                  elegido=1;
                  botones.getBotones().get(12).setBackground(Color.red);
                  control.set(12,1);
                  abiertos.set(12,1);
                  lbleleccion.setVisible(false);
                }else{
                   if(control.get(12)==0){
                        preguntAbrir.setVisible(true);
                        if(preguntAbrir.getA()==1){
                            abiertos.set(12,1);
                            msgValor(ofertas.getOfertas().get(12).toString());
                            cont++;
                            //checar eso
                            botones.getBotones().get(12).setVisible(false);
                            int b= labels.buscar(ofertas.getOfertas().get(12));
                            labels.getLabels().get(b).setText("-----");
                            if(cont%4==0){
                            ofertadialog.setVisible(true);
                            if(ofertadialog.getC()==1){
                                terminado();
                                System.exit(0);
                            }
                            }else{
                                if(cont==22){
                                    //elegido = 1
                                    ultima.setVisible(true);
                                    if(ultima.getEstado()==0){
                                        for(int h=0;h<24;h++){
                                            if(abiertos.get(h)==0){
                                                //abiertos nos da la posicion
                                                msgValoresUlt(ofertas.getOfertas().get(h).toString());
                                                
                                            }
                                        }
                                    }else{
                                        msgValoresUlt(maletin.toString());
                                        }
                                    terminado();
                                    System.exit(0);
                                    }
                            };
                        }
                    }
                }
            }
        });
        botones.getBotones().get(13).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(elegido==0){
                  msg("14");
                  maletin=ofertas.getOfertas().get(13);
                  elegido=1;
                  botones.getBotones().get(13).setBackground(Color.red);
                  control.set(13,1);
                  abiertos.set(13,1);
                  lbleleccion.setVisible(false);
                }else{
                   if(control.get(13)==0){
                        preguntAbrir.setVisible(true);
                        if(preguntAbrir.getA()==1){
                            abiertos.set(13,1);
                            msgValor(ofertas.getOfertas().get(13).toString());
                            cont++;
                            //checar eso
                            botones.getBotones().get(13).setVisible(false);
                            int b= labels.buscar(ofertas.getOfertas().get(13));
                            labels.getLabels().get(b).setText("-----");
                            if(cont%4==0){
                            ofertadialog.setVisible(true);
                            if(ofertadialog.getC()==1){
                                terminado();
                                System.exit(0);
                            }
                            }else{
                                if(cont==22){
                                    //elegido = 1
                                    ultima.setVisible(true);
                                    if(ultima.getEstado()==0){
                                        for(int h=0;h<24;h++){
                                            if(abiertos.get(h)==0){
                                                //abiertos nos da la posicion
                                                msgValoresUlt(ofertas.getOfertas().get(h).toString());
                                                
                                            }
                                        }
                                    }else{
                                        msgValoresUlt(maletin.toString());
                                        }
                                    terminado();
                                    System.exit(0);
                                    }
                            }
                        }
                    }
                }
            }
        });
        botones.getBotones().get(14).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(elegido==0){
                  msg("15");
                  maletin=ofertas.getOfertas().get(14);
                  elegido=1;
                  botones.getBotones().get(14).setBackground(Color.red);
                  control.set(14,1);
                  abiertos.set(14,1);
                  lbleleccion.setVisible(false);
                }else{
                   if(control.get(14)==0){
                        preguntAbrir.setVisible(true);
                        if(preguntAbrir.getA()==1){
                            abiertos.set(14,1);
                            msgValor(ofertas.getOfertas().get(14).toString());
                            cont++;
                            //checar eso
                            botones.getBotones().get(14).setVisible(false);
                            int b= labels.buscar(ofertas.getOfertas().get(14));
                            labels.getLabels().get(b).setText("-----");
                            if(cont%4==0){
                            ofertadialog.setVisible(true);
                            if(ofertadialog.getC()==1){
                                terminado();
                                System.exit(0);
                            }
                            }else{
                                if(cont==22){
                                    //elegido = 1
                                    ultima.setVisible(true);
                                    if(ultima.getEstado()==0){
                                        for(int h=0;h<24;h++){
                                            if(abiertos.get(h)==0){
                                                //abiertos nos da la posicion
                                                msgValoresUlt(ofertas.getOfertas().get(h).toString());
                                                
                                            }
                                        }
                                    }else{
                                        msgValoresUlt(maletin.toString());
                                        }
                                    terminado();
                                    System.exit(0);
                                    }
                            }
                        }
                    }
                }
            }
        });
        botones.getBotones().get(15).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(elegido==0){
                  msg("16");
                  maletin=ofertas.getOfertas().get(15);
                  elegido=1;
                  botones.getBotones().get(15).setBackground(Color.red);
                  control.set(15,1);
                  abiertos.set(15,1);
                  lbleleccion.setVisible(false);
                }else{
                   if(control.get(15)==0){
                        preguntAbrir.setVisible(true);
                        if(preguntAbrir.getA()==1){
                            abiertos.set(15,1);
                            msgValor(ofertas.getOfertas().get(15).toString());
                            cont++;
                            //checar eso
                            botones.getBotones().get(15).setVisible(false);
                            int b= labels.buscar(ofertas.getOfertas().get(15));
                            labels.getLabels().get(b).setText("-----");
                            if(cont%4==0){
                            ofertadialog.setVisible(true);
                            if(ofertadialog.getC()==1){
                                terminado();
                                System.exit(0);
                            }
                            }else{
                                if(cont==22){
                                    //elegido = 1
                                    ultima.setVisible(true);
                                    if(ultima.getEstado()==0){
                                        for(int h=0;h<24;h++){
                                            if(abiertos.get(h)==0){
                                                //abiertos nos da la posicion
                                                msgValoresUlt(ofertas.getOfertas().get(h).toString());
                                                
                                            }
                                        }
                                    }else{
                                        msgValoresUlt(maletin.toString());
                                        }
                                    terminado();
                                    System.exit(0);
                                    }
                            }
                        }
                    }
                }
            }
        });
        botones.getBotones().get(16).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(elegido==0){
                  msg("17");
                  maletin=ofertas.getOfertas().get(16);
                  elegido=1;
                  botones.getBotones().get(16).setBackground(Color.red);
                  control.set(16,1);
                  abiertos.set(16,1);
                  lbleleccion.setVisible(false);
                }else{
                   if(control.get(16)==0){
                        preguntAbrir.setVisible(true);
                        if(preguntAbrir.getA()==1){
                            abiertos.set(16,1);
                            msgValor(ofertas.getOfertas().get(16).toString());
                            cont++;
                            //checar eso
                            botones.getBotones().get(16).setVisible(false);
                            int b= labels.buscar(ofertas.getOfertas().get(16));
                            labels.getLabels().get(b).setText("-----");
                            if(cont%4==0){
                            ofertadialog.setVisible(true);
                            if(ofertadialog.getC()==1){
                                terminado();
                                System.exit(0);
                            }
                            }else{
                                if(cont==22){
                                    //elegido = 1
                                    ultima.setVisible(true);
                                    if(ultima.getEstado()==0){
                                        for(int h=0;h<24;h++){
                                            if(abiertos.get(h)==0){
                                                //abiertos nos da la posicion
                                                msgValoresUlt(ofertas.getOfertas().get(h).toString());
                                                
                                            }
                                        }
                                    }else{
                                        msgValoresUlt(maletin.toString());
                                        }
                                    terminado();
                                    System.exit(0);
                                    }
                            }
                        }
                    }
                }
            }
        });
        botones.getBotones().get(17).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(elegido==0){
                  msg("18");
                  maletin=ofertas.getOfertas().get(17);
                  elegido=1;
                  botones.getBotones().get(17).setBackground(Color.red);
                  control.set(17,1);
                  abiertos.set(17,1);
                  lbleleccion.setVisible(false);
                }else{
                   if(control.get(17)==0){
                        preguntAbrir.setVisible(true);
                        if(preguntAbrir.getA()==1){
                            abiertos.set(17,1);
                            msgValor(ofertas.getOfertas().get(17).toString());
                            cont++;
                            //checar eso
                            botones.getBotones().get(17).setVisible(false);
                            int b= labels.buscar(ofertas.getOfertas().get(17));
                            labels.getLabels().get(b).setText("-----");
                            if(cont%4==0){
                            ofertadialog.setVisible(true);
                            if(ofertadialog.getC()==1){
                                terminado();
                                System.exit(0);
                            }
                            }else{
                                if(cont==22){
                                    //elegido = 1
                                    ultima.setVisible(true);
                                    if(ultima.getEstado()==0){
                                        for(int h=0;h<24;h++){
                                            if(abiertos.get(h)==0){
                                                //abiertos nos da la posicion
                                                msgValoresUlt(ofertas.getOfertas().get(h).toString());
                                                
                                            }
                                        }
                                    }else{
                                        msgValoresUlt(maletin.toString());
                                        }
                                    terminado();
                                    System.exit(0);
                                    }
                            }
                        }
                    }
                }
            }
        });
        botones.getBotones().get(18).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(elegido==0){
                  msg("19");
                  maletin=ofertas.getOfertas().get(18);
                  elegido=1;
                  botones.getBotones().get(18).setBackground(Color.red);
                  control.set(18,1);
                  abiertos.set(18,1);
                  lbleleccion.setVisible(false);
                }else{
                   if(control.get(18)==0){
                        preguntAbrir.setVisible(true);
                        if(preguntAbrir.getA()==1){
                            abiertos.set(18,1);
                            msgValor(ofertas.getOfertas().get(18).toString());
                            cont++;
                            //checar eso
                            botones.getBotones().get(18).setVisible(false);
                            int b= labels.buscar(ofertas.getOfertas().get(18));
                            labels.getLabels().get(b).setText("-----");
                            if(cont%4==0){
                            ofertadialog.setVisible(true);
                            if(ofertadialog.getC()==1){
                                terminado();
                                System.exit(0);
                            }
                            }else{
                                if(cont==22){
                                    //elegido = 1
                                    ultima.setVisible(true);
                                    if(ultima.getEstado()==0){
                                        for(int h=0;h<24;h++){
                                            if(abiertos.get(h)==0){
                                                //abiertos nos da la posicion
                                                msgValoresUlt(ofertas.getOfertas().get(h).toString());
                                                
                                            }
                                        }
                                    }else{
                                        msgValoresUlt(maletin.toString());
                                        }
                                    terminado();
                                    System.exit(0);
                                    }
                            }
                        }
                    }
                }
            }
        });
        botones.getBotones().get(19).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(elegido==0){
                  msg("20");
                  maletin=ofertas.getOfertas().get(19);
                  elegido=1;
                  botones.getBotones().get(19).setBackground(Color.red);
                  control.set(19,1);
                  abiertos.set(19,1);
                  lbleleccion.setVisible(false);
                }else{
                   if(control.get(19)==0){
                        preguntAbrir.setVisible(true);
                        if(preguntAbrir.getA()==1){
                            abiertos.set(19,1);
                            msgValor(ofertas.getOfertas().get(19).toString());
                            cont++;
                            //checar eso
                            botones.getBotones().get(19).setVisible(false);
                            int b= labels.buscar(ofertas.getOfertas().get(19));
                            labels.getLabels().get(b).setText("-----");
                            if(cont%4==0){
                            ofertadialog.setVisible(true);
                            if(ofertadialog.getC()==1){
                                terminado();
                                System.exit(0);
                            }
                            }else{
                                if(cont==22){
                                    //elegido = 1
                                    ultima.setVisible(true);
                                    if(ultima.getEstado()==0){
                                        for(int h=0;h<24;h++){
                                            if(abiertos.get(h)==0){
                                                //abiertos nos da la posicion
                                                msgValoresUlt(ofertas.getOfertas().get(h).toString());
                                                
                                            }
                                        }
                                    }else{
                                        msgValoresUlt(maletin.toString());
                                        }
                                    terminado();
                                    System.exit(0);
                                    }
                            }
                        }
                    }
                }
            }
        });
        botones.getBotones().get(20).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(elegido==0){
                  msg("21");
                  maletin=ofertas.getOfertas().get(20);
                  elegido=1;
                  botones.getBotones().get(20).setBackground(Color.red);
                  control.set(20,1);
                  abiertos.set(20,1);
                  lbleleccion.setVisible(false);
                }else{
                   if(control.get(20)==0){
                        preguntAbrir.setVisible(true);
                        if(preguntAbrir.getA()==1){
                            abiertos.set(20,1);
                            msgValor(ofertas.getOfertas().get(20).toString());
                            cont++;
                            //checar eso
                            botones.getBotones().get(20).setVisible(false);
                            int b= labels.buscar(ofertas.getOfertas().get(20));
                            labels.getLabels().get(b).setText("-----");
                            if(cont%4==0){
                            ofertadialog.setVisible(true);
                            if(ofertadialog.getC()==1){
                                terminado();
                                System.exit(0);
                            }
                            }else{
                                if(cont==22){
                                    //elegido = 1
                                    ultima.setVisible(true);
                                    if(ultima.getEstado()==0){
                                        for(int h=0;h<24;h++){
                                            if(abiertos.get(h)==0){
                                                //abiertos nos da la posicion
                                                msgValoresUlt(ofertas.getOfertas().get(h).toString());
                                                
                                            }
                                        }
                                    }else{
                                        msgValoresUlt(maletin.toString());
                                        }
                                    terminado();
                                    System.exit(0);
                                    }
                            }
                        }
                    }
                }
            }
        });
        botones.getBotones().get(21).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(elegido==0){
                  msg("22");
                  maletin=ofertas.getOfertas().get(21);
                  elegido=1;
                  botones.getBotones().get(21).setBackground(Color.red);
                  control.set(21,1);
                  abiertos.set(21,1);
                  lbleleccion.setVisible(false);
                }else{
                   if(control.get(21)==0){
                        preguntAbrir.setVisible(true);
                        if(preguntAbrir.getA()==1){
                            abiertos.set(21,1);
                            msgValor(ofertas.getOfertas().get(21).toString());
                            cont++;
                            //checar eso
                            botones.getBotones().get(21).setVisible(false);
                            int b= labels.buscar(ofertas.getOfertas().get(21));
                            labels.getLabels().get(b).setText("-----");
                            if(cont%4==0){
                            ofertadialog.setVisible(true);
                            if(ofertadialog.getC()==1){
                                terminado();
                                System.exit(0);
                            }
                            }else{
                                if(cont==22){
                                    //elegido = 1
                                    ultima.setVisible(true);
                                    if(ultima.getEstado()==0){
                                        for(int h=0;h<24;h++){
                                            if(abiertos.get(h)==0){
                                                //abiertos nos da la posicion
                                                msgValoresUlt(ofertas.getOfertas().get(h).toString());
                                                
                                            }
                                        }
                                    }else{
                                        msgValoresUlt(maletin.toString());
                                        }
                                    terminado();
                                    System.exit(0);
                                    }
                            }
                        }
                    }
                }
            }
        });
        botones.getBotones().get(22).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(elegido==0){
                  msg("23");
                  maletin=ofertas.getOfertas().get(22);
                  elegido=1;
                  botones.getBotones().get(22).setBackground(Color.red);
                  control.set(22,1);
                  abiertos.set(22,1);
                  lbleleccion.setVisible(false);
                }else{
                   if(control.get(22)==0){
                        preguntAbrir.setVisible(true);
                        if(preguntAbrir.getA()==1){
                            abiertos.set(22,1);
                            msgValor(ofertas.getOfertas().get(22).toString());
                            cont++;
                            //checar eso
                            botones.getBotones().get(22).setVisible(false);
                            int b= labels.buscar(ofertas.getOfertas().get(22));
                            labels.getLabels().get(b).setText("-----");
                            if(cont%4==0){
                            ofertadialog.setVisible(true);
                            if(ofertadialog.getC()==1){
                                terminado();
                                System.exit(0);
                            }
                            }else{
                                if(cont==22){
                                    //elegido = 1
                                    ultima.setVisible(true);
                                    if(ultima.getEstado()==0){
                                        for(int h=0;h<24;h++){
                                            if(abiertos.get(h)==0){
                                                //abiertos nos da la posicion
                                                msgValoresUlt(ofertas.getOfertas().get(h).toString());
                                                
                                            }
                                        }
                                    }else{
                                        msgValoresUlt(maletin.toString());
                                        }
                                    terminado();
                                    System.exit(0);
                                    }
                            }
                        }
                    }
                }
            }
        });
        botones.getBotones().get(23).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(elegido==0){
                  msg("24");
                  maletin=ofertas.getOfertas().get(23);
                  elegido=1;
                  botones.getBotones().get(23).setBackground(Color.red);
                  control.set(23,1);
                  abiertos.set(23,1);
                  lbleleccion.setVisible(false);
                }else{
                   if(control.get(23)==0){
                        preguntAbrir.setVisible(true);
                        if(preguntAbrir.getA()==1){
                            abiertos.set(23,1);
                            msgValor(ofertas.getOfertas().get(23).toString());
                            cont++;
                            //checar eso
                            botones.getBotones().get(23).setVisible(false);
                            int b= labels.buscar(ofertas.getOfertas().get(23));
                            labels.getLabels().get(b).setText("-----");
                            if(cont%4==0){
                            ofertadialog.setVisible(true);
                            if(ofertadialog.getC()==1){
                                terminado();
                                System.exit(0);
                            }
                           }else{
                                if(cont==22){
                                    //elegido = 1
                                    ultima.setVisible(true);
                                    if(ultima.getEstado()==0){
                                        for(int h=0;h<24;h++){
                                            if(abiertos.get(h)==0){
                                                //abiertos nos da la posicion
                                                msgValoresUlt(ofertas.getOfertas().get(h).toString());
                                                
                                            }
                                        }
                                    }else{
                                        msgValoresUlt(maletin.toString());
                                        }
                                    terminado();
                                    System.exit(0);
                                    }
                            }
                        }
                    }
                }
            }
        });
        
        
        pnlIzq.setVisible(false);
        pnlDer.setVisible(false);
        pnlBotonesOp.setVisible(false);
        super.getContentPane().add(pnlImag,BorderLayout.NORTH);
        pnlTablero.setBackground(Color.decode("#20063C"));
        pnlTablero.setVisible(false);
        super.add(pnlIzq,BorderLayout.WEST);
        super.add(pnlTablero,BorderLayout.CENTER);
        super.add(pnlDer,BorderLayout.EAST);
        super.add(pnlBotonesOp,BorderLayout.SOUTH);
        super.setJMenuBar(createMenu());
        //------------------------------------
        super.setVisible(true);
    }
    
    public JMenuBar createMenu() {
        JMenuBar mainMenu = new JMenuBar();

        JMenu mmJuego = new JMenu("Juego");
        
        JMenuItem miSalir = new JMenuItem("Salir");
        miSalir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        miSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        mmJuego.add(miSalir);

        mainMenu.add(mmJuego);
        return mainMenu;
    }
    public void msg(String a){
        JOptionPane.showMessageDialog(this,"Elegiste el maletin "+a);
    }
    public void msgValor(String a){
       ImageIcon im=new ImageIcon(getClass().getResource("/imagenes/maletiAb.png"));
       ImageIcon im2=new ImageIcon(im.getImage().getScaledInstance(120,100,Image.SCALE_DEFAULT));
       JOptionPane.showMessageDialog(this,"Valor: "+a,"El Valor del maletin",JOptionPane.OK_OPTION, im2);
    }
    public void terminado(){
        JOptionPane.showMessageDialog(this,"JUEGO TERMINADO");
    }
    public void msgValoresUlt(String a){
       ImageIcon im=new ImageIcon(getClass().getResource("/imagenes/maletiAb.png"));
       ImageIcon im2=new ImageIcon(im.getImage().getScaledInstance(120,100,Image.SCALE_DEFAULT));
       JOptionPane.showMessageDialog(this,"Valor: "+a,"El Valor del maletin",JOptionPane.OK_OPTION,im2);
    }
}
