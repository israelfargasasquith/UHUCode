/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.VorazAlgoritmo;
import Modelo.GenerarFicheros;
import Modelo.ParDePuntos;
import Modelo.Punto;
import Vista.VistaBidireccionalAleatorios;
import Vista.VistaBidireccionalFichero;
import Vista.VistaCompararAlgoritmos;
import Vista.VistaCompararDos;
import Vista.VistaCompararTodos;
import Vista.VistaCompararVoraces;
import Vista.VistaCompararVoracesFichero;
import Vista.VistaEligeVoraz;
import Vista.VistaGenerarficheroPuntosAleatorios;
import Vista.VistaPrincipal;
import Vista.VistaPuntosAleatorios;
import Vista.VistaPuntosFichero;
import Vista.VistaUnidireccionalAleatorios;
import Vista.VistaUnidireccionalFichero;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;

/**
 *
 * @author marin
 */
public class ControladorPuntos implements ActionListener {

    private VistaPuntosAleatorios vp = null;
    private VistaPrincipal vprin = null;
    private Punto p = null;
    private ControladorTablas tablas = null;
    private VistaCompararDos vcd = null;
    private ArrayList<ArrayList<Double>> tiempos;
    private VistaCompararTodos vct = null;
    private VistaCompararAlgoritmos vca = null;

    private ArrayList<Punto> dataSet = null;
    private VistaGenerarficheroPuntosAleatorios vgf = null;
    private VistaBidireccionalFichero vbf = null;
    private VistaPuntosFichero vpf = null;
    private VistaBidireccionalAleatorios vba = null;
    private VorazAlgoritmo voraz;
    private ParDePuntos DataSetMasCercanos = null;
    private VistaEligeVoraz vev = null;
    private VistaUnidireccionalFichero vuf = null;
    private VistaUnidireccionalAleatorios vua = null;
    private VistaCompararVoraces vcv = null;
    private VistaCompararVoracesFichero vcvf=null;
    
    private double distanciaDataSet;
    private VorazAlgoritmo valg;
    private Punto[] lista;
    private int numelem;
    private int[] indices = new int[3], indicesvacio = new int[3];
    private Double resultado;
    int[][] graph = null;
    int distanciatotal = 0;
    int[][] solucion;

    private GenerarFicheros generaFich = null;

    public ControladorPuntos() {
        numelem = 500;
        lista = new Punto[numelem];

        vp = new VistaPuntosAleatorios();
        vprin = new VistaPrincipal();
        p = new Punto();
        tablas = new ControladorTablas();
        vcd = new VistaCompararDos();
        vct = new VistaCompararTodos();
        vca = new VistaCompararAlgoritmos();
        vba = new VistaBidireccionalAleatorios();
        vgf = new VistaGenerarficheroPuntosAleatorios();
        vpf = new VistaPuntosFichero();
        vev = new VistaEligeVoraz();
        vuf = new VistaUnidireccionalFichero();
        vua = new VistaUnidireccionalAleatorios();
        vcv = new VistaCompararVoraces();
        vcvf = new VistaCompararVoracesFichero();
        tiempos = new ArrayList();
        dataSet = new ArrayList();
        vbf = new VistaBidireccionalFichero();
        generaFich = new GenerarFicheros();
        valg = new VorazAlgoritmo();
        addListeners();
        this.vprin.setTitle("Practica1 AMC");
        this.vprin.setLocationRelativeTo(null);
        this.vprin.setVisible(true);

    }

    private void addListeners() {
        vprin.OPCAleatorio.addActionListener(this);
        vprin.BotonTodosAleatorios.addActionListener(this);
        vprin.BotonCompararDos.addActionListener(this);
        vprin.BotonVoraz.addActionListener(this);
        vprin.BotonCrearFichPuntosAleatorios.addActionListener(this);
        vprin.BotonAnalisisFich.addActionListener(this);

        vp.BotonGenerarAnalizarAleatorios.addActionListener(this);
        vp.BotonVolverAnalisisAleatorio.addActionListener(this);
        vp.CompararTodosAleatorios.addActionListener(this);
        vp.GuardarAleatoriosEnFichero.addActionListener(this);

        vcd.BComp2.addActionListener(this);
        vcd.BotonCompararDosVolver.addActionListener(this);
        vct.VolverCompararTodos.addActionListener(this);

        vgf.BotonGenerarFicheroAl.addActionListener(this);
        vgf.BotonVolver.addActionListener(this);

        vpf.BotonAnalizarFichero.addActionListener(this);
        vpf.BotonAbrirFichero.addActionListener(this);
        vpf.CompararTodosfichero.addActionListener(this);
        vpf.BotonVolverAnalisisFichero.addActionListener(this);

        vca.BotonVolverCompararAlgoritmos.addActionListener(this);

        vev.BotonUnidireccionalAleatorios.addActionListener(this);
        vev.BotonUnidireccionalFichero.addActionListener(this);
        vev.BotonBidireccionalAleatorios.addActionListener(this);
        vev.BotonBidireccionalFichero.addActionListener(this);
        vev.BotonVolverEligeVoraz.addActionListener(this);
        vev.BotonCompararVoraces.addActionListener(this);
        vev.BotonAbrirFicheroCompararVoraces.addActionListener(this);

        vbf.BotonAbrirFichero.addActionListener(this);
        vbf.BotonAnalizarFichero.addActionListener(this);
        vbf.BotonVolverBidireccionalFichero.addActionListener(this);
        vbf.BotonGuardarEnFicheroBidireccionalFich.addActionListener(this);

        vba.BotonGenerarAnalizarAleatoriosBidireccional.addActionListener(this);
        vba.BotonVolverBidireccionalAleatorios.addActionListener(this);
        vba.BotonGuardarEnFicheroBidireccionalAleatorio.addActionListener(this);

        vuf.BotonAbrirFicheroUnidireccional.addActionListener(this);
        vuf.BotonAnalizarUnidireccionalFichero.addActionListener(this);
        vuf.BotonVolverUnidireccionalFichero.addActionListener(this);
        vuf.BotonGuardarFicheroUnidireccionalFich.addActionListener(this);

        vua.BotonGenerarAnalizarAleatoriosUnidireccional.addActionListener(this);
        vua.BotonVolverUnidireccionalAleatorios.addActionListener(this);
        vua.BotonGuardarEnFicheroUnidireccionalAleatorios.addActionListener(this);

        vcv.BotonVolverCompararVoraces.addActionListener(this);
        vcvf.BotonCerrarCompararVoracesFichero.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {

            //ANALISIS DE UN ALGORITMO PUNTOS ALEATORIOS
            case "Análisis de un algoritmo (al)": {
                vprin.setVisible(false);
                vp.setTitle("Análisis de un algoritmo (puntos aleatorios)");
                vp.setLocationRelativeTo(null);
                vp.setVisible(true);
                break;
            }
            case "Generar y Analizar Aleatorios": {

                int anchura = vp.JPanelAleatorios.getWidth();
                int altura = vp.JPanelAleatorios.getHeight();
                System.out.println("Se ha pulsado generar");
                String cadena = vp.TextNum.getText();
                String algoritmo = (String) vp.ComboBoxAleatorio.getSelectedItem();

                if (cadena.equals("")) {
                    JOptionPane.showMessageDialog(vp, "Introduzca cuantos numeros quiere generar", null, JOptionPane.ERROR_MESSAGE);

                } else {

                    int num = Integer.parseInt(cadena);
                    System.out.println("generar " + num + " puntos");
                    ArrayList<Punto> ap = p.GeneraPuntos(num, 0, 100);
                    dataSet = ap;
                    //ArrayList<Punto> ap = p.GeneraPuntos(num, anchura, altura);

                    vp.JPanelAleatorios.setVisible(true);
                    vp.JPanelAleatorios.setBackground(Color.white);
                    vp.JPanelAleatorios.setBorder(new LineBorder(Color.black));

                    System.out.println("****************************************");
                    System.out.println("**************************************");
                    ParDePuntos par = null;
                    switch (algoritmo) {
                        case "DyV": {
                            par = p.DyV(ap);
                            DataSetMasCercanos = par;
                            distanciaDataSet = p.distanciaEntrePuntos(DataSetMasCercanos.getA(), DataSetMasCercanos.getB());
                            vp.setPuntos(ap);
                            vp.setPuntosCercanos(par);

                            String solucion1 = par.getA().getX() + ", " + par.getA().getY();
                            String solucion2 = par.getB().getX() + ", " + par.getB().getY();
                            vp.TextSolPunto1.setText(solucion1);
                            vp.TextSolPunto2.setText(solucion2);

                            String tiempoAlg = p.getTiempoAlg() + " ms";
                            vp.TextTiempo.setText(tiempoAlg);
                            System.out.println("El par de punto más cercanos son: A:" + par.getA().getX() + "," + par.getA().getY() + " B:" + par.getB().getX() + "," + par.getB().getY());

                            break;
                        }
                        case "Exhaustivo": {
                            par = p.Exhaustivo(ap);
                            DataSetMasCercanos = par;
                            distanciaDataSet = p.distanciaEntrePuntos(DataSetMasCercanos.getA(), DataSetMasCercanos.getB());
                            vp.setPuntos(ap);
                            vp.setPuntosCercanos(par);
                            String solucion1 = par.getA().getX() + ", " + par.getA().getY();
                            String solucion2 = par.getB().getX() + ", " + par.getB().getY();
                            vp.TextSolPunto1.setText(solucion1);
                            vp.TextSolPunto2.setText(solucion2);

                            String tiempoAlg = p.getTiempoAlg() + " ms";
                            vp.TextTiempo.setText(tiempoAlg);
                            System.out.println("El par de punto más cercanos son: A:" + par.getA().getX() + "," + par.getA().getY() + " B:" + par.getB().getX() + "," + par.getB().getY());

                            break;
                        }

                        case "Búsqueda con Poda": {
                            par = p.ExhaustivoConPoda(ap);
                            DataSetMasCercanos = par;
                            distanciaDataSet = p.distanciaEntrePuntos(DataSetMasCercanos.getA(), DataSetMasCercanos.getB());
                            vp.setPuntos(ap);
                            vp.setPuntosCercanos(par);
                            String solucion1 = par.getA().getX() + ", " + par.getA().getY();
                            String solucion2 = par.getB().getX() + ", " + par.getB().getY();
                            vp.TextSolPunto1.setText(solucion1);
                            vp.TextSolPunto2.setText(solucion2);

                            String tiempoAlg = p.getTiempoAlg() + " ms";
                            vp.TextTiempo.setText(tiempoAlg);
                            System.out.println("El par de punto más cercanos son: A:" + par.getA().getX() + "," + par.getA().getY() + " B:" + par.getB().getX() + "," + par.getB().getY());

                            break;
                        }
                        case "DyV mejorado": {
                            par = p.DyVMejorado(ap);
                            DataSetMasCercanos = par;
                            distanciaDataSet = p.distanciaEntrePuntos(DataSetMasCercanos.getA(), DataSetMasCercanos.getB());
                            vp.setPuntos(ap);
                            vp.setPuntosCercanos(par);
                            String solucion1 = par.getA().getX() + ", " + par.getA().getY();
                            String solucion2 = par.getB().getX() + ", " + par.getB().getY();
                            vp.TextSolPunto1.setText(solucion1);
                            vp.TextSolPunto2.setText(solucion2);

                            String tiempoAlg = p.getTiempoAlg() + " ms";
                            vp.TextTiempo.setText(tiempoAlg);
                            System.out.println("El par de punto más cercanos son: A:" + par.getA().getX() + "," + par.getA().getY() + " B:" + par.getB().getX() + "," + par.getB().getY());

                            //vp.pintaPuntos();
                            break;
                        }
                        case "Elige Algoritmo": {
                            JOptionPane.showMessageDialog(vcd, "Debe elegir un algoritmo", null, JOptionPane.ERROR_MESSAGE);

                        }

                    }

                    //c.setSize(300,300);
                    //.out.println("El par de punto más cercanos son: A:"+par.getA().getX()+","+par.getA().getY()+" B:"+par.getB().getX()+","+par.getB().getY());
                    vp.pintaPuntos();
                }

                break;
            }

            case "Guardar puntos en un fichero": {
                generaFich.GenerarFicheroAPartirDeAleatorios(dataSet);
                JOptionPane.showMessageDialog(vgf, "Fichero creado correctamente", null, JOptionPane.INFORMATION_MESSAGE);
                break;
            }

            case "Comparar todas las estrategias Aleatorios": {
                vca.setTitle("Comparar todas las estrategias");
                vca.setLocationRelativeTo(null);
                vca.setVisible(true);

                tablas.dibujarTablaCompararAlgoritmos(vca);
                tablas.rellenarTablaCompararAlgoritmos(p.CompararTodosLosAlgoritmos(dataSet), DataSetMasCercanos, distanciaDataSet);

                break;
            }

            case "volver comparar algoritmos": {
                
                vca.dispose();
                tablas.vaciarTablaCompararAlgoritmos();

                break;
            }

            case "Volver Analisis Puntos Aleatorios": {
                vp.dispose();
                vp.TextNum.setText("");
                vp.TextSolPunto1.setText("");
                vp.TextSolPunto2.setText("");
                vp.TextTiempo.setText("");
                vp.ComboBoxAleatorio.setSelectedIndex(0);
                vp.JPanelAleatorios.setVisible(false);
                vprin.setVisible(true);
                break;
            }
            //ANALISIS DE UN ALGORITMO PUNTOS A PARTIR DE FICHERO
            case "Analisis de un algoritmo (fich)": {
                vprin.setVisible(false);
                vpf.setTitle("Analisis de un algoritmo (fichero)");
                vpf.setLocationRelativeTo(null);
                vpf.setVisible(true);
                break;
            }

            case "Abrir fichero de Puntos": {

                try {

                    dataSet = generaFich.AbrirFichero();
                    //dataSet=generaFich.factorConversion(dataSet,vpf.JPanelAleatorios.getWidth() , vpf.JPanelAleatorios.getHeight());
                    System.out.println("PUNTOS/////////////////////");
                    for (int i = 0; i < dataSet.size(); i++) {
                        System.out.println(dataSet.get(i).getX() + "        " + dataSet.get(i).getY());
                    }

                } catch (IOException ex) {
                    Logger.getLogger(ControladorPuntos.class.getName()).log(Level.SEVERE, null, ex);
                }

                break;
            }
            case "AnalizarPorFichero": {

                int anchura = vpf.JPanelFicheros.getWidth();

                int altura = vpf.JPanelFicheros.getHeight();
                System.out.println("Se ha pulsado generar");

                String algoritmo = (String) vpf.ComboBoxFichero.getSelectedItem();

                ArrayList<Punto> ap = dataSet;
                System.out.println("AP*************");
                for (int i = 0; i < ap.size(); i++) {

                    System.out.println(ap.get(i).getX() + "        " + ap.get(i).getY());
                }
                vpf.JPanelFicheros.setVisible(true);
                vpf.JPanelFicheros.setBackground(Color.white);
                vpf.JPanelFicheros.setBorder(new LineBorder(Color.black));

                System.out.println("**************************************");
                ParDePuntos par = null;
                switch (algoritmo) {
                    case "DyV": {
                        par = p.DyV(ap);
                        DataSetMasCercanos = par;
                        distanciaDataSet = p.distanciaEntrePuntos(DataSetMasCercanos.getA(), DataSetMasCercanos.getB());
                        vpf.setPuntos(ap);
                        vpf.setPuntosCercanos(par);

                        String solucion1 = par.getA().getX() + ", " + par.getA().getY();
                        String solucion2 = par.getB().getX() + ", " + par.getB().getY();
                        vpf.TextSolPunto1.setText(solucion1);
                        vpf.TextSolPunto2.setText(solucion2);

                        String tiempoAlg = p.getTiempoAlg() + " ms";
                        vpf.TextTiempo.setText(tiempoAlg);
                        System.out.println("El par de punto más cercanos son: A:" + par.getA().getX() + "," + par.getA().getY() + " B:" + par.getB().getX() + "," + par.getB().getY());

                        break;
                    }
                    case "Exhaustivo": {
                        par = p.Exhaustivo(ap);
                        DataSetMasCercanos = par;
                        distanciaDataSet = p.distanciaEntrePuntos(DataSetMasCercanos.getA(), DataSetMasCercanos.getB());
                        vpf.setPuntos(ap);
                        vpf.setPuntosCercanos(par);
                        String solucion1 = par.getA().getX() + ", " + par.getA().getY();
                        String solucion2 = par.getB().getX() + ", " + par.getB().getY();
                        vpf.TextSolPunto1.setText(solucion1);
                        vpf.TextSolPunto2.setText(solucion2);

                        String tiempoAlg = p.getTiempoAlg() + " ms";
                        vpf.TextTiempo.setText(tiempoAlg);
                        System.out.println("El par de punto más cercanos son: A:" + par.getA().getX() + "," + par.getA().getY() + " B:" + par.getB().getX() + "," + par.getB().getY());

                        break;
                    }

                    case "Búsqueda con Poda": {
                        par = p.ExhaustivoConPoda(ap);
                        DataSetMasCercanos = par;
                        distanciaDataSet = p.distanciaEntrePuntos(DataSetMasCercanos.getA(), DataSetMasCercanos.getB());
                        vpf.setPuntos(ap);
                        vpf.setPuntosCercanos(par);
                        String solucion1 = par.getA().getX() + ", " + par.getA().getY();
                        String solucion2 = par.getB().getX() + ", " + par.getB().getY();
                        vpf.TextSolPunto1.setText(solucion1);
                        vpf.TextSolPunto2.setText(solucion2);

                        String tiempoAlg = p.getTiempoAlg() + " ms";
                        vpf.TextTiempo.setText(tiempoAlg);
                        System.out.println("El par de punto más cercanos son: A:" + par.getA().getX() + "," + par.getA().getY() + " B:" + par.getB().getX() + "," + par.getB().getY());

                        break;
                    }
                    case "DyV mejorado": {
                        par = p.DyVMejorado(ap);
                        DataSetMasCercanos = par;
                        distanciaDataSet = p.distanciaEntrePuntos(DataSetMasCercanos.getA(), DataSetMasCercanos.getB());
                        vpf.setPuntos(ap);
                        vpf.setPuntosCercanos(par);
                        String solucion1 = par.getA().getX() + ", " + par.getA().getY();
                        String solucion2 = par.getB().getX() + ", " + par.getB().getY();
                        vpf.TextSolPunto1.setText(solucion1);
                        vpf.TextSolPunto2.setText(solucion2);

                        String tiempoAlg = p.getTiempoAlg() + " ms";
                        vpf.TextTiempo.setText(tiempoAlg);
                        System.out.println("El par de punto más cercanos son: A:" + par.getA().getX() + "," + par.getA().getY() + " B:" + par.getB().getX() + "," + par.getB().getY());

                        //vp.pintaPuntos();
                        break;
                    }
                    case "Elige Algoritmo": {
                        JOptionPane.showMessageDialog(vcd, "Debe elegir un algoritmo", null, JOptionPane.ERROR_MESSAGE);

                    }

                }

                //c.setSize(300,300);
                //.out.println("El par de punto más cercanos son: A:"+par.getA().getX()+","+par.getA().getY()+" B:"+par.getB().getX()+","+par.getB().getY());
                vpf.pintaPuntos();
                break;
            }

            case "VolverAnalisisFichero": {
               
                vpf.TextSolPunto1.setText("");
                vpf.TextSolPunto2.setText("");
                vpf.TextTiempo.setText("");
                vpf.ComboBoxFichero.setSelectedIndex(0);
                vpf.JPanelFicheros.setVisible(false);
                vpf.dispose();
                vprin.setVisible(true);
                break;
            }

            case "Comparar todas las estrategias fichero": {
                vca.setTitle("Comparar todas las estrategias");
                vca.setLocationRelativeTo(null);
                vca.setVisible(true);

                tablas.dibujarTablaCompararAlgoritmos(vca);
                tablas.rellenarTablaCompararAlgoritmos(p.CompararTodosLosAlgoritmos(dataSet), DataSetMasCercanos, distanciaDataSet);

                break;
            }

           

            //COMPARAR DOS ALGORITMOS POR TALLAS
            case "BotonCompararDos": {
                tablas.dibujarTabla2(vcd);
                String alg1 = vcd.Comp2Combo1.getSelectedItem().toString();
                String alg2 = vcd.Comp2Combo2.getSelectedItem().toString();
                if (alg1.equals("Algoritmo 1") || alg2.equals("Algoritmo 2")) {
                    JOptionPane.showMessageDialog(vcd, "Debe elegir 2 algoritmos", null, JOptionPane.ERROR_MESSAGE);
                } else if (alg1.equals(alg2)) {
                    JOptionPane.showMessageDialog(vcd, "Los algoritmos elegidos deben ser diferentes", null, JOptionPane.ERROR_MESSAGE);
                } else {
                    tiempos = p.CompararDosAlgoritmos(alg1, alg2);
                    tablas.rellenarTablaComparar2(tiempos);
                }

                break;
            }

            case "Comparar dos algoritmos": {

                vprin.setVisible(false);
                vcd.setTitle("Comparar dos algoritmos");
                vcd.setLocationRelativeTo(null);
                vcd.setVisible(true);

                break;
            }
            case "BotonCompararDosVolver": {
                tablas.vaciarTablaComparar2();
                vcd.dispose();
                vprin.setVisible(true);
                break;
            }

            //COMPARAR TODOS LOS ALGORTIMOS POR TALLAS
            case "Comparación de todos los algoritmos": {
                ArrayList<ArrayList<Double>> t = new ArrayList();
                vprin.setVisible(false);
                vct.setTitle("Comparar todos los algoritmos");
                vct.setLocationRelativeTo(null);
                t = p.CompararTodosLosAlgoritmosTallas();
                tablas.dibujarTablaTodosTallas(vct);
                tablas.rellenarTablaCompararTodosTallas(t);
                vct.setVisible(true);
                break;
            }
            case "VolverCompararTodos": {
                tablas.vaciarTablaCompararTodos();
                vct.dispose();
                vprin.setVisible(true);

                break;
            }

            //GENERAR FICHERO DE PUNTOS ALEATORIOS 
            case "Generar fichero de puntos Aleatorios": {
                vprin.dispose();
                vgf.setTitle("Generar fichero de puntos aleatorios");
                vgf.setLocationRelativeTo(null);
                vgf.setVisible(true);

                break;
            }
            case "Generar Fichero Aleatorios": {
                generaFich.GeneraFicheroDeAleatorios(Integer.parseInt(vgf.TextNumPuntos.getText()), Integer.parseInt(vgf.RangoMin.getText()), Integer.parseInt(vgf.RangoMax.getText()));
                JOptionPane.showMessageDialog(vgf, "Fichero creado correctamente", null, JOptionPane.INFORMATION_MESSAGE);
                vgf.dispose();
                vprin.setVisible(true);
                break;
            }
            case "VolverDeCrearFichero": {
                vgf.TextNumPuntos.setText("");
                vgf.RangoMin.setText("");
                vgf.RangoMax.setText("");
                vgf.dispose();
                vprin.setVisible(true);
                break;
            }

            //VORAZ
            case "Voraz": {
                
                vprin.setVisible(false);
                vev.setTitle("Voraces");
                vev.setLocationRelativeTo(null);
                vev.setVisible(true);
                break;

            }
            case "VolverEligeVoraz": {
                vev.dispose();
                vprin.setVisible(true);
                break;
            }

            //BIDIRECCIONAL ALEATORIOS
            case "Vista Aleatorios Bidireccional": {
                vev.setVisible(false);
                vba.setTitle("Analisis Algoritmo Bidireccional (puntos aleatorios)");
                vba.setLocationRelativeTo(null);
                vba.setVisible(true);
                break;
            }
            case "Generar y Analizar Bidireccional Aleatorios": {
                int num = Integer.parseInt(vba.TextNumBidireccional.getText());
                ArrayList<ParDePuntos> camino = null;
                ArrayList<Punto> ap = p.GeneraPuntos(num, 0, 100);
                voraz = new VorazAlgoritmo(ap);
                camino = voraz.AlgoritmoBidireccional();
                vba.JPanelBidireccionalAleatorios.setVisible(true);
                
                vba.JPanelBidireccionalAleatorios.setBackground(Color.white);
                vba.JPanelBidireccionalAleatorios.setBorder(new LineBorder(Color.black));
                vba.setPuntos(ap);
                vba.setPuntosCamino(camino);
                vba.pintaGrafo();
                
                String texto="";
                for (int i = 0; i < ap.size(); i++) { //sacamos los puntos con sus respectivos id
                    texto = texto + ap.get(i).getId() + "  " + ap.get(i).getX() + " , " + ap.get(i).getY() + "\n";
                }
                
                String costeT=voraz.getCaminoRecorrido()+ "\n" + voraz.getCaminoRecorridoCostes() + "\n\n" + "Coste total:" + voraz.getCosteTotal();
                vba.TextCoste.setText(costeT);
                vba.TextCamino.setText(texto);
                
                break;
            }
            case "Guardar en Fichero Bidireccional Aleatorio": {
                voraz.GuardarEnFichero();
                JOptionPane.showMessageDialog(vgf, "Fichero creado correctamente", null, JOptionPane.INFORMATION_MESSAGE);
            }
            case "Volver Bidireccional Aleatorios": {
              
                vba.TextCamino.setText("");
                vba.TextCoste.setText("");
                vba.TextNumBidireccional.setText("");
                vba.dispose();
                vev.setVisible(true);
                break;
            }

            //BIDIRECCIONAL FICHEROS
            case "Abrir Vista Fichero Bidireccional": {
                vev.setVisible(false);
                vbf.setTitle("Analisis Algoritmo Bidireccional (fichero)");
                vbf.setLocationRelativeTo(null);
                vbf.setVisible(true);
                break;
            }
            case "Abrir fichero Bidireccional": {

                try {
                    dataSet = generaFich.AbrirFichero();
                    JOptionPane.showMessageDialog(vuf, "Fichero cargado correctamente. Preparado para analizar.", null, JOptionPane.INFORMATION_MESSAGE);

                    //dataSet=generaFich.factorConversion(dataSet,vpf.JPanelAleatorios.getWidth() , vpf.JPanelAleatorios.getHeight());
                } catch (IOException ex) {
                    Logger.getLogger(ControladorPuntos.class.getName()).log(Level.SEVERE, null, ex);
                }

                break;
            }
            case "AnalizarBidireccionalPorFichero": {

                ArrayList<ParDePuntos> camino = null;
                ArrayList<Punto> ap = dataSet;
                voraz = new VorazAlgoritmo(ap);
                camino = voraz.AlgoritmoBidireccional();

                vbf.JPanelBidireccionalFicheros.setBackground(Color.white);
                vbf.JPanelBidireccionalFicheros.setBorder(new LineBorder(Color.black));
                vbf.setPuntos(ap);
                vbf.setPuntosCamino(camino);
                vbf.pintaGrafo();

               String texto="";
                for (int i = 0; i < ap.size(); i++) { //sacamos los puntos con sus respectivos id
                    texto = texto + ap.get(i).getId() + "  " + ap.get(i).getX() + " , " + ap.get(i).getY() + "\n";
                }
                
                String costeT=voraz.getCaminoRecorrido()+ "\n" + voraz.getCaminoRecorridoCostes() + "\n\n" + "Coste total:" + voraz.getCosteTotal();
                vbf.TextCoste.setText(costeT);
                vbf.TextCamino.setText(texto);
                break;
            }

            case "Guardar en Fichero Bidireccional fich": {
                voraz.GuardarEnFichero();
                JOptionPane.showMessageDialog(vgf, "Fichero creado correctamente", null, JOptionPane.INFORMATION_MESSAGE);
                break;
            }
            case "Volver Bidireccional Fichero": {

                vbf.TextCamino.setText("");
                vbf.TextCoste.setText("");
                vbf.dispose();
                vev.setVisible(true);
                break;
            }

            //UNIDIRECCIONAL FICHERO
            case "Abrir Vista Fichero Unidireccional": {
                vev.setVisible(false);
                vuf.setTitle("Analisis Algoritmo Unidireccional (fichero)");
                vuf.setLocationRelativeTo(null);
                vuf.setVisible(true);
                break;
            }
            case "Abrir fichero Unidireccional": {
                try {
                    dataSet = generaFich.AbrirFichero();
                    JOptionPane.showMessageDialog(vuf, "Fichero cargado correctamente. Preparado para analizar.", null, JOptionPane.INFORMATION_MESSAGE);

                    //dataSet=generaFich.factorConversion(dataSet,vpf.JPanelAleatorios.getWidth() , vpf.JPanelAleatorios.getHeight());
                } catch (IOException ex) {
                    Logger.getLogger(ControladorPuntos.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            }

            case "AnalizarUnidireccionalPorFichero": {
                ArrayList<ParDePuntos> camino = null;
                ArrayList<Punto> ap = dataSet;
                voraz = new VorazAlgoritmo(ap);
                camino = voraz.AlgoritmoUnidireccional();
                vuf.JPanelUnidireccionalAleatorios.setBackground(Color.white);
                vuf.JPanelUnidireccionalAleatorios.setBorder(new LineBorder(Color.black));
                vuf.setPuntos(ap);
                vuf.setPuntosCamino(camino);
                vuf.pintaGrafo();
                
               String texto="";
                for (int i = 0; i < ap.size(); i++) { //sacamos los puntos con sus respectivos id
                    texto = texto + ap.get(i).getId() + "  " + ap.get(i).getX() + " , " + ap.get(i).getY() + "\n";
                }
                
                String costeT=voraz.getCaminoRecorrido()+ "\n" + voraz.getCaminoRecorridoCostes() + "\n\n" + "Coste total:" + voraz.getCosteTotal();
                vuf.TextCoste.setText(costeT);
                vuf.TextCamino.setText(texto);
                break;

            }

            case "Volver unidireccional Fichero": {
                vuf.TextCamino.setText("");
                vuf.TextCoste.setText("");
                System.out.println("Antes de limpiar el panel");
                vuf.JPanelUnidireccionalAleatorios.removeAll();
                vuf.repaint();
                System.out.println("DESPUES de limpiar el panel");
                vuf.setVisible(false);
                vev.setVisible(true);
                break;
            }

            case "Guardar en Fichero Unidireccional fich": {
                voraz.GuardarEnFichero();
                JOptionPane.showMessageDialog(vgf, "Fichero creado correctamente", null, JOptionPane.INFORMATION_MESSAGE);
                break;
            }

            //UNIDIRECCIONAL ALEATORIOS
            case "Abrir Vista Aleatorios Unidireccional": {
                vev.setVisible(false);
                vua.setTitle("Analisis Algoritmo Unidireccional (puntos aleatorios)");
                vua.setLocationRelativeTo(null);
                vua.setVisible(true);
                break;
            }
            case "Generar y Analizar Unidireccional Aleatorios": {
                int numPuntos = Integer.parseInt(vua.TextNumUnidireccional.getText());
                ArrayList<ParDePuntos> camino = null;
                ArrayList<Punto> ap = p.GeneraPuntos(numPuntos, 0, 100);
                voraz = new VorazAlgoritmo(ap);
                camino = voraz.AlgoritmoUnidireccional();
                vua.JPanelUnidireccionalAleatorios.setBackground(Color.white);
                vua.JPanelUnidireccionalAleatorios.setBorder(new LineBorder(Color.black));
                vua.setPuntos(ap);
                vua.setPuntosCamino(camino);
                vua.pintaGrafo();
                
               String texto="";
                for (int i = 0; i < ap.size(); i++) { //sacamos los puntos con sus respectivos id
                    texto = texto + ap.get(i).getId() + "  " + ap.get(i).getX() + " , " + ap.get(i).getY() + "\n";
                }
                
                String costeT=voraz.getCaminoRecorrido()+ "\n" + voraz.getCaminoRecorridoCostes() + "\n\n" + "Coste total:" + voraz.getCosteTotal();
                vua.TextCoste.setText(costeT);
                vua.TextCamino.setText(texto);

               
                break;
            }
           case "Guardar En Fichero Unidireccional aleatorios": {
                voraz.GuardarEnFichero();
                JOptionPane.showMessageDialog(vgf, "Fichero creado correctamente", null, JOptionPane.INFORMATION_MESSAGE);
                break;
            }
            case "Volver Unidireccional Aleatorios": {
                vua.dispose();
                vev.setVisible(true);
                break;
            }

            case "Comparar Voraces": {
                ArrayList<ArrayList<Double>> distancias = new ArrayList();
                int talla[] = {100, 200, 300, 400, 500, 600, 700, 800, 900, 1000};

                Punto p = new Punto();
                for (int i = 0; i < 10; i++) {
                    ArrayList<Double> dist = new ArrayList();
                    ArrayList<Punto> ap = p.GeneraPuntos(talla[i], 0, 1000);
                    voraz = new VorazAlgoritmo(ap);
                    voraz.AlgoritmoBidireccional();
                    dist.add(voraz.getCosteTotal());
                    voraz.AlgoritmoUnidireccional();
                    dist.add(voraz.getCosteTotal());
                    distancias.add(dist);

                }
                
                tablas.dibujarTablaCompararVoraces(vcv);
                tablas.rellenarTablaCompararVoraces(distancias);

                vev.setVisible(false);
                vcv.setTitle("Comparar por tallas Unidireccional y Bidireccional");
                vcv.setLocationRelativeTo(null);
                vcv.setVisible(true);
                break;
            }
            case "Volver Comparar Voraces":{
                
                vcv.dispose();
                tablas.vaciarTablaCompararVoraces();
                vev.setVisible(true);
                break;
            }
            case "Abrir Fichero y comparar estrategias":{
                try {
                    
                    Double costeBi, costeUni;
                    dataSet = generaFich.AbrirFichero();
                    JOptionPane.showMessageDialog(vuf, "Fichero cargado correctamente. Preparado para analizar.", null, JOptionPane.INFORMATION_MESSAGE);
                    ArrayList<Punto> ap = dataSet;
                    voraz = new VorazAlgoritmo(ap);
                    voraz.AlgoritmoBidireccional();
                    costeBi=voraz.getCosteTotal();
                    voraz.AlgoritmoUnidireccional();
                    costeUni=voraz.getCosteTotal();
                    tablas.dibujarTablaCompararVoracesFichero(vcvf);
                    tablas.rellenarTablaCompararVoracesFichero(costeUni, costeBi,vcvf);
                    vcvf.jLabel1.setText("TABLA COMPARATIVA DE COSTES. " +  generaFich.getNombreFichero());
                    vcvf.setTitle("Comparar Voraces. Fichero: " + generaFich.getNombreFichero());
                    vcvf.setLocationRelativeTo(null);
                    vcvf.setVisible(true);
                    
                    //dataSet=generaFich.factorConversion(dataSet,vpf.JPanelAleatorios.getWidth() , vpf.JPanelAleatorios.getHeight());
                } catch (IOException ex) {
                    Logger.getLogger(ControladorPuntos.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            }
            case "Cerrar Comparar Voraces Fichero":{
                tablas.vaciarTablaCompararVoracesFichero();
                vcvf.dispose();
                break;
            }

        }

    }

}
