/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.AFD;
import Modelo.AFND;
import Modelo.TransicionAFD;
import Modelo.TransicionAFND;
import Modelo.TransicionLambda;
import Vista.VistaAFD_Elige;
import Vista.VistaAFND_Elige;
import Vista.VistaDatosAFD;
import Vista.VistaDatosAFND;
import Vista.VistaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author marin
 */
public class ControladorPrincipal implements ActionListener {

    VistaPrincipal vp = null;
    VistaAFD_Elige vafe = null;
    VistaDatosAFD vdafd = null;
    VistaDatosAFND vdafnd = null;
    VistaAFND_Elige vafnde = null;


    private List<List<String>> dataAFD;
    private List<List<String>> dataAFND;
    private ArrayList<TransicionAFD> transicionesAFD;
    private ArrayList<TransicionAFND> transicionesAFND;
    private ArrayList<TransicionLambda> transicionesLambda;
    private AFD afd;
    private AFND afnd;

    
    
    public ControladorPrincipal() {
        vp = new VistaPrincipal();
        vafe = new VistaAFD_Elige();
        vdafd = new VistaDatosAFD();
        vdafnd = new VistaDatosAFND();
        vafnde = new VistaAFND_Elige();
        transicionesAFD = new ArrayList<>();
        transicionesAFND = new ArrayList<>();
        transicionesLambda = new ArrayList<>();
        afd = new AFD();
        afnd = new AFND();
        addListeners();
        this.vp.setTitle("Practica 2 AMC");
        this.vp.setLocationRelativeTo(null);
        this.vp.setVisible(true);
    }

 
    private void addListeners() {
        vp.BotonAFD.addActionListener(this);
        vp.AFND.addActionListener(this);
        vafe.BotonAbrirFicheroAFD.addActionListener(this);
        vafe.ImportarDatosAFND.addActionListener(this);
        vafe.BotonVolverAFDElige.addActionListener(this);
        vdafd.BotonAceptarDatosAFD.addActionListener(this);
        vdafd.BotonVolverDatosAFND.addActionListener(this);
        vafnde.BotonAbrirFicheroAFND.addActionListener(this);
        vafnde.ImportarDatosAFND.addActionListener(this);
        vafnde.BotonVolverAFNDElige.addActionListener(this);
        vdafnd.BotonAceptarAFND.addActionListener(this);
        vdafnd.BotonVolverDatosAFND.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "AFD": {
                vp.setVisible(false);
                vafe.setLocationRelativeTo(null);
                vafe.setVisible(true);
                break;
            }
            case "AbrirFicheroAFD": {
                try {
                    abrirFicheroAFD();

                } catch (IOException ex) {
                    Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }

                break;
            }
            case "ImportarDatosAFD": {
                vafe.setVisible(false);
                vdafd.setLocationRelativeTo(null);
                vdafd.setVisible(true);
                break;
            }
            case "AceptarDatosAFD": {

                if (vdafd.TextEstadoFinalAFD.getText().equals("") || vdafd.TextEstadoInicialAFD.getText().equals("") || vdafd.TextNumNodosAFD.getText().equals("") || vdafd.TextSimbolosAFD.getText().equals("") || vdafd.TextTransicionesAFD.getText().equals("")) {
                    JOptionPane.showMessageDialog(vdafd, "Debe rellenar todos los campos", null, JOptionPane.ERROR_MESSAGE);

                }

                String numNodos = vdafd.TextNumNodosAFD.getText();
                String EstadoInicial = vdafd.TextEstadoInicialAFD.getText();
                String EstadoFinal = vdafd.TextEstadoFinalAFD.getText();
                String transiciones = vdafd.TextTransicionesAFD.getText();
                String simbolo = vdafd.TextSimbolosAFD.getText();
                String cadena = vdafd.TextCadena.getText();

                String[] lineas = transiciones.split("\n");
                String[] partes;
                for (int i = 0; i < lineas.length; i++) {
                    System.out.println(lineas[i]);
                    partes = lineas[i].split("\\s+");
                    if (partes.length == 3) {
                        String origen = partes[0];
                        char simbolo1 = partes[1].charAt(1);
                        String destino = partes[2];
                        TransicionAFD transicion = new TransicionAFD(origen, simbolo1, destino);
                        transicionesAFD.add(transicion);
                    }
                }

                String[] ArraySimbolos = simbolo.split("\\s+");
                String[] estadosFinales = EstadoFinal.split("\\s+");
                ArrayList<String> ArrayEstadosFinales = new ArrayList();
                for (int i = 0; i < estadosFinales.length; i++) {
                    ArrayEstadosFinales.add(estadosFinales[i]);
                }
                afd.setEstadoInicial(EstadoInicial);
                afd.setEstadosFinales(ArrayEstadosFinales);
                afd.setTransiciones(transicionesAFD);
                afd.setCadena(cadena);
                afd.probar();
                break;
            }
            case "VolverEligeAFD": {
                vafe.setVisible(false);
                vp.setVisible(true);
                break;
            }
            case "VolverDatosAFD": {
                vdafd.setVisible(false);
                vafe.setVisible(true);
                break;
            }
            case "AFND": {
                vp.setVisible(false);
                vafnde.setLocationRelativeTo(null);
                vafnde.setVisible(true);
                break;
            }
            case "AbrirFicheroAFND": {
                try {
                    abrirFicheroAFND();
                } catch (IOException ex) {
                    Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            }
            case "ImportarDatosAFND": {
                vafnde.setVisible(false);
                vdafnd.setLocationRelativeTo(null);
                vdafnd.setVisible(true);
                break;
            }
            case "AceptarAFND": {

                if (vdafnd.TextEstadoFinalAFND.getText().equals("") || vdafnd.TextEstadoInicialAFND.getText().equals("") || vdafnd.TextNumNodosAFND.getText().equals("") || vdafnd.TextSimbolosAFND.getText().equals("") || vdafnd.TextTransicionesAFND.getText().equals("") || vdafnd.TextTransicionesLambdaAFNS.equals("")) {
                    JOptionPane.showMessageDialog(vdafd, "Debe rellenar todos los campos", null, JOptionPane.ERROR_MESSAGE);

                }

                String numNodos = vdafnd.TextNumNodosAFND.getText();
                String EstadoInicial = vdafnd.TextEstadoInicialAFND.getText();
                String EstadoFinal = vdafnd.TextEstadoFinalAFND.getText();
                String transiciones = vdafnd.TextTransicionesAFND.getText();
                String transicionesL = vdafnd.TextTransicionesLambdaAFNS.getText();
                String simbolo = vdafnd.TextSimbolosAFND.getText();
                String cadena = vdafnd.TextCadenaAFND.getText();
                // String cadena = vdafnd.TextCadena.getText();

                String[] lineas = transiciones.split("\n");
                String[] partes;
                for (int i = 0; i < lineas.length; i++) {
                    System.out.println(lineas[i]);
                    partes = lineas[i].split("\\s+");
                    if (partes.length >= 3) {
                        String origen = partes[0];
                        char simbolo1 = partes[1].charAt(1);
                        ArrayList<String> destino = new ArrayList();
                        for (int j = 2; j < partes.length; j++) {
                            destino.add(partes[j]);
                        }
                        String[] destinos = destino.toArray(new String[0]);

                        TransicionAFND transicion = new TransicionAFND(origen, simbolo1, destinos);
                        transicionesAFND.add(transicion);
                    }
                }

                String[] lineas2 = transicionesL.split("\n");
                String[] partes2;
                for (int i = 0; i < lineas2.length; i++) {
                    System.out.println(lineas2[i]);
                    partes2 = lineas2[i].split("\\s+");
                    if (partes2.length >= 1) {
                        String origen = partes2[0];
                        ArrayList<String> destino = new ArrayList();
                        for (int j = 1; j < partes2.length; j++) {
                            destino.add(partes2[j]);
                        }
                        String[] destinos = destino.toArray(new String[0]);

                        TransicionLambda transicionL = new TransicionLambda(origen, destinos);
                        transicionesLambda.add(transicionL);
                    }
                }

                String[] ArraySimbolos = simbolo.split("\\s+");
                String[] estadosFinales = EstadoFinal.split("\\s+");
                ArrayList<String> ArrayEstadosFinales = new ArrayList();
                for (int i = 0; i < estadosFinales.length; i++) {
                    ArrayEstadosFinales.add(estadosFinales[i]);
                }
                afnd.setEstadoInicial(EstadoInicial);
                afnd.setEstadosFinales(ArrayEstadosFinales);
                afnd.setTransiciones(transicionesAFND);
                afnd.setTransicionesλ(transicionesLambda);
                afnd.setCadena(cadena);
                afnd.probar();

                break;
            }
            case "VolverEligeAFND": {
                vafnde.setVisible(false);
                vp.setVisible(true);
                break;
            }
            case "VolverDatosAFND": {
                vdafnd.setVisible(false);
                vafnde.setLocationRelativeTo(null);
                vafnde.setVisible(true);
            }

        }

    }
    //AbrirFicheroAFND

    //METODOS ABRIR FICHERO
    private void abrirFicheroAFD() throws FileNotFoundException, IOException {

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Abrir archivo");
        fileChooser.setApproveButtonText("Abrir");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int userSelection = fileChooser.showOpenDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            // Obtener el archivo seleccionado
            File fileToOpen = fileChooser.getSelectedFile();

            File f = fileChooser.getSelectedFile();
            FileReader readFichero = new FileReader(f);
            BufferedReader bufferedReader = new BufferedReader(readFichero);

            dataAFD = new ArrayList<>();
            String line;
            transicionesAFD = new ArrayList<>();

            boolean foundTransiciones = false;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.startsWith("TIPO:") || line.startsWith("ESTADOS:")
                        || line.startsWith("INICIAL:") || line.startsWith("FINALES:")) {
                    String[] lineData = line.split(":\\s+", 2);
                    if (lineData.length == 2) {
                        String[] values = lineData[1].split("\\s+");
                        dataAFD.add(Arrays.asList(values));
                    }
                }
                if (line.startsWith("TRANSICIONES:") && !foundTransiciones) {
                    foundTransiciones = true;
                    continue;
                }
                if (foundTransiciones) {
                    String[] parts = line.split("\\s+");
                    if (parts.length == 3) {
                        String origen = parts[0];
                        char simbolo = parts[1].charAt(1);
                        String destino = parts[2];
                        TransicionAFD transicion = new TransicionAFD(origen, simbolo, destino);
                        transicionesAFD.add(transicion);
                    }
                }
            }

            ArrayList<String> estados = new ArrayList<>();
            ArrayList<String> estadosFinales = new ArrayList<>();
            String inicial = "";
            int i = 0;

            for (List<String> dataList : dataAFD) {
                if (i == 1) {
                    for (String dato : dataList) {
                        estados.add(dato);

                    }
                    System.out.println("Estados: " + estados);
                } else if (i == 2) {
                    inicial = dataList.get(0);
                    System.out.println("Inicial " + inicial);
                } else if (i == 3) {
                    for (String dato : dataList) {
                        estadosFinales.add(dato);

                    }
                    System.out.println("Estados finales: " + estadosFinales);
                }
                System.out.println("***********************");
                System.out.println(dataList);
                i++;
            }
            //Display TransicionAFD objects
            for (TransicionAFD transicion : transicionesAFD) {
                System.out.println(transicion);
            }

            String cadena = JOptionPane.showInputDialog(this, "Introduce la cadena");
            afd.setCadena(cadena);
            afd.setEstadoInicial(inicial);
            //ArrayList arrayEstadosFinales = new ArrayList();
            /*for (int j = 0; j < estadosFinales.size(); j++) {
                System.out.println("Añadimo : " + estadosFinales.get(j));
                arrayEstadosFinales.add(estadosFinales.get(j));
            }*/
            afd.setEstadosFinales(estadosFinales);

            afd.setTransiciones(transicionesAFD);
            afd.probar();
            readFichero.close();

        }

    }

    private void abrirFicheroAFND() throws FileNotFoundException, IOException {

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Abrir archivo");
        fileChooser.setApproveButtonText("Abrir");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int userSelection = fileChooser.showOpenDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            // Obtener el archivo seleccionado
            File fileToOpen = fileChooser.getSelectedFile();

            File f = fileChooser.getSelectedFile();
            FileReader readFichero = new FileReader(f);
            BufferedReader bufferedReader = new BufferedReader(readFichero);
            dataAFND = new ArrayList<>();
            transicionesAFND = new ArrayList<>();
            transicionesLambda = new ArrayList<>();
            
            String line;
            boolean foundTransiciones = false;
            boolean foundTransicionesLambda = false;

            while ((line = bufferedReader.readLine()) != null && !line.equals("FIN")) {

                if (line.startsWith("TIPO:") || line.startsWith("ESTADOS:")
                        || line.startsWith("INICIAL:") || line.startsWith("FINALES:")) {
                    String[] lineData = line.split(":\\s+", 2);
                    if (lineData.length == 2) {
                        String[] values = lineData[1].split("\\s+");
                        dataAFND.add(Arrays.asList(values));
                    }
                }
                if (line.startsWith("TRANSICIONES:") && !foundTransiciones) {
                    foundTransiciones = true;
                    continue;
                }

                if (foundTransiciones) {
                    String[] parts = line.split("\\s+");
                    if (parts.length >= 3) {
                        String origen = parts[0];
                        char simbolo = parts[1].charAt(1);
                        List<String> destinosList = new ArrayList<>();
                        for (int i = 2; i < parts.length; i++) {
                            destinosList.add(parts[i]);
                        }
                        String[] destinos = destinosList.toArray(new String[0]);
                        TransicionAFND transicion = new TransicionAFND(origen, simbolo, destinos);
                        transicionesAFND.add(transicion);
                    }
                }

                if (line.startsWith("TRANSICIONES LAMBDA:") && !foundTransicionesLambda) {
                    foundTransicionesLambda = true;
                    continue;
                }

                if (foundTransicionesLambda) {
                    String[] parts = line.split("\\s+");
                    if (parts.length >= 1) {
                        String origen = parts[0];
                        List<String> destinosList = new ArrayList<>();
                        for (int i = 1; i < parts.length; i++) {
                            destinosList.add(parts[i]);
                        }
                        String[] destinos = destinosList.toArray(new String[0]);
                        TransicionLambda transicionLambda = new TransicionLambda(origen, destinos);
                        transicionesLambda.add(transicionLambda);
                    }
                }
            }
            
            ArrayList<String> estados = new ArrayList<>();
            ArrayList<String> estadosFinales = new ArrayList<>();
            String inicial = "";
            int i = 0;

            for (List<String> dataList : dataAFND) {
                if (i == 1) {
                    for (String dato : dataList) {
                        estados.add(dato);

                    }
                    System.out.println("Estados: " + estados);
                } else if (i == 2) {
                    inicial = dataList.get(0);
                    System.out.println("Inicial " + inicial);
                } else if (i == 3) {
                    for (String dato : dataList) {
                        estadosFinales.add(dato);

                    }
                    System.out.println("Estados finales: " + estadosFinales);
                }
                System.out.println("***********************");
                System.out.println(dataList);
                i++;
            }

           /* for (List<String> dataList : dataAFND) {
                System.out.println(dataList);
            }
            for (TransicionAFND transicion : transicionesAFND) {
                System.out.println(transicion);
            }
            for (TransicionLambda transicionLambda : transicionesLambda) {
                System.out.println(transicionLambda);
            }*/
            
            String cadena = JOptionPane.showInputDialog(this, "Introduce la cadena");
            afnd.setCadena(cadena);
            afnd.setEstadoInicial(inicial);           
            afnd.setEstadosFinales(estadosFinales);
            afnd.setTransiciones(transicionesAFND);
            afnd.setTransicionesλ(transicionesLambda);
            afnd.probar();
            readFichero.close();
//         CREAR LAS TABLAS PARA LOS AUTOMATAS, QUE LOS RESUELVA Y QUE RELLENE LAS TABLAS
//         HACER LAS VISTA QUE RECOJAN LOS DATOS Y QUE LOS PASE IGUAL QUE LOS FICHEROS AL AFD Y AFND
        }
    }

}
