/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.ParDePuntos;
import Modelo.Punto;
import Vista.VistaPrincipal;
import Vista.VistaPuntos;
import Vista.VistaPuntosAleatorios;
import Vista.VistaPuntosFichero;
import java.awt.Canvas;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.AncestorListener;

/**
 *
 * @author marin
 */
public class ControladorPuntos implements ActionListener {

    private VistaPuntosAleatorios vp = null;
    private VistaPrincipal vprin = null;
    private VistaPuntosFichero vPuntosFichero;
    private Punto p = null;

    public ControladorPuntos() {
        vp = new VistaPuntosAleatorios();
        vprin = new VistaPrincipal();
        vPuntosFichero = new VistaPuntosFichero();
        p = new Punto();

        addListeners();
        this.vprin.setTitle("Practica1 AMC");
        this.vprin.setLocationRelativeTo(null);
        this.vprin.setVisible(true);

    }

    private void addListeners() {
        vp.BotonGenerarAleatorios.addActionListener(this);
        vprin.OPCFichero.addActionListener(this);
        vprin.OPCAleatorio.addActionListener(this);
        vp.BotonVolver.addActionListener(this);
        vprin.BotonTodosAleatorios.addActionListener(this);

        // vp.BotonFichero.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Análisis de un algoritmo": {
                vprin.setVisible(false);
                vp.setVisible(true);
                break;
            }
            case "Análisis de un fichero existente":
                vprin.setVisible(false);
                vPuntosFichero.setVisible(true);
                break;
            case "Volver": {
                vp.dispose();
                vprin.setVisible(true);
                break;

            }

            case "Elegir Fichero":

                JFileChooser fileChooser = new JFileChooser();
                int seleccion = fileChooser.showOpenDialog(vPuntosFichero);

                if (seleccion == JFileChooser.APPROVE_OPTION) {
                    try {
                        ArrayList<Punto> lPuntos = p.generaPuntosFichero(fileChooser.getSelectedFile().getPath());
                        
//                        lPuntos = p.factorConversion(lPuntos, vPuntosFichero.jPanelPuntosFichero.getWidth(),
//                                vPuntosFichero.jPanelPuntosFichero.getHeight());

//                        graficos.drawLine(0, jPanelPuntos.getHeight() / 2, jPanelPuntos.getWidth(), jPanelPuntos.getHeight() / 2);
//                        graficos.drawLine(jPanelPuntos.getWidth() / 2, 0, jPanelPuntos.getWidth() / 2, jPanelPuntos.getHeight());
//                        graficos.drawLine(jPanelPuntos.getWidth() / 2, 0, jPanelPuntos.getWidth() / 2, jPanelPuntos.getHeight());
//                        Font f = new Font("Serif", Font.BOLD, 12);
//                        graficos.setFont(f);
//                        graficos.drawString("X", jPanelPuntos.getWidth() - 15, (jPanelPuntos.getHeight() / 2) - 5);
//                        graficos.drawString("Y", (jPanelPuntos.getWidth() / 2) + 5, 15);
//                        for (Punto punto : lPuntos) {
//                            graficos.drawOval((int) punto.getX(), (int) punto.getY(), 7, 7);
//                            graficos.fillOval((int) punto.getX(), (int) punto.getY(), 7, 7);
//
//                        }

                    } catch (Exception ex) {
                        System.out.println("Error con el generaPuntosFichero: " + ex.getMessage());
                    }
                }
                break;

            case "Generar Fichero Aleatorio":

                break;

            case "GenerarAleatorios": {

                int anchura = vp.JPanelAleatorios.getWidth();
                int altura = vp.JPanelAleatorios.getHeight();
                System.out.println("Se ha pulsado generar");
                String cadena = vp.TextNum.getText();
                String algoritmo = (String) vp.ComboBoxAleatorio.getSelectedItem();
                int num = Integer.parseInt(cadena);
                System.out.println("generar " + num + " puntos");
                //ArrayList<Punto> ap = p.GeneraPuntos(num, anchura, altura);
                ArrayList<Punto> ap = p.GeneraPuntos(num, 0, 100);
                vp.JPanelAleatorios.setVisible(true);
                vp.JPanelAleatorios.setBackground(Color.white);
                vp.JPanelAleatorios.setBorder(new LineBorder(Color.black));

                System.out.println("****************************************");
                p.CompararTodosLosAlgoritmosTallas();
                //ArrayList<Double>tiempos=p.CompararTodosLosAlgoritmos(ap);
                //System.out.println("EX      DYV     DYVM        EXPODA");
                //System.out.println(tiempos.get(0)+"     "+tiempos.get(1)+"      "+tiempos.get(2)+"      "+ tiempos.get(3));

                System.out.println("**************************************");
                ParDePuntos par = null;
                switch (algoritmo) {
                    case "DyV": {
                        par = p.DyV(ap);
                        vp.setPuntos(ap);
                        vp.setPuntosCercanos(par);
                        System.out.println("El par de punto más cercanos son: A:" + par.getA().getX() + "," + par.getA().getY() + " B:" + par.getB().getX() + "," + par.getB().getY());

                        break;
                    }
                    case "Exhaustivo": {
                        par = p.Exhaustivo(ap);
                        vp.setPuntos(ap);
                        vp.setPuntosCercanos(par);
                        System.out.println("El par de punto más cercanos son: A:" + par.getA().getX() + "," + par.getA().getY() + " B:" + par.getB().getX() + "," + par.getB().getY());

                        break;
                    }
                    case "Búsqueda con Poda": {
                        par = p.ExhaustivoConPoda(ap);
                        vp.setPuntos(ap);
                        vp.setPuntosCercanos(par);
                        System.out.println("El par de punto más cercanos son: A:" + par.getA().getX() + "," + par.getA().getY() + " B:" + par.getB().getX() + "," + par.getB().getY());

                        break;
                    }
                    case "DyV mejorado": {
                        par = p.DyVMejorado(ap);
                        vp.setPuntos(ap);
                        vp.setPuntosCercanos(par);
                        System.out.println("El par de punto más cercanos son: A:" + par.getA().getX() + "," + par.getA().getY() + " B:" + par.getB().getX() + "," + par.getB().getY());

                        //vp.pintaPuntos();
                        break;
                    }

                }

                //c.setSize(300,300);
                //.out.println("El par de punto más cercanos son: A:"+par.getA().getX()+","+par.getA().getY()+" B:"+par.getB().getX()+","+par.getB().getY());
                vp.pintaPuntos();
                break;
            }
            case "Comparación de todos los algoritmos": {

            }
        }

    }
}
