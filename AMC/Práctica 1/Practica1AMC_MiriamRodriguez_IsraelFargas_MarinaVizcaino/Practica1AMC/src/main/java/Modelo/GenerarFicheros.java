/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFileChooser;

/**
 *
 * @author marin
 */
public class GenerarFicheros {

    private Punto p = new Punto();
    String nombre = "";
    public String getNombreFichero(){
        return nombre;
    }
    public void setNombreFichero(String nombre){
        this.nombre=nombre;
    }
    public void GenerarFicheroAPartirDeAleatorios(ArrayList<Punto> puntos) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar archivo");
        BigDecimal numeroRedondeado;
        
        int userSelection = fileChooser.showSaveDialog(null);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            // Obtener la ubicación y el nombre del archivo seleccionado
            String fileToSave = fileChooser.getSelectedFile().getAbsolutePath();
            File fich = fileChooser.getSelectedFile();
            System.out.println("Se ha seleccionado guardar en: " + fileToSave);
            String nombre = fich.getName();

            try {
                // Crear un FileWriter para escribir en el archivo
                FileWriter writer = new FileWriter(fileToSave);

                // Escribir contenido en el archivo
                writer.write("NAME: " + nombre + "\nTYPE: TSP"
                        + "\nCOMMENT: Generado aleatoriamente para AMC"
                        + "\nDIMENSION: " + puntos.size()
                        + "\nEDGE_WEIGHT_TYPE: EXPLICIT"
                        + "\nEDGE_WEIGHT_FORMAT: EXPLICIT"
                        + "\nDISPLAY_DATA_TYPE: COORD_DISPLAY"
                        + "\nNODE_COORD_SECTION\n");
                String linea;

                for (int i = 0; i < puntos.size(); i++) {
                    int id = i + 1;
                    BigDecimal coodx= new BigDecimal( puntos.get(i).getX());
                    BigDecimal coody= new BigDecimal( puntos.get(i).getY());
                    BigDecimal coodxredondeo = coodx.setScale(10,RoundingMode.HALF_UP);
                    BigDecimal coodyredondeo =coody.setScale(10,RoundingMode.HALF_UP);
                    linea = "" + id + " " + coodxredondeo + " " +coodyredondeo + "\n";
                    writer.write(linea);
                }

                writer.write("EOF");
                // Cerrar el FileWriter
                writer.close();

                System.out.println("Se ha escrito en el archivo: " + fileToSave);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void GeneraFicheroDeAleatorios(int numPuntos, int rangoMin, int rangoMax) {

        String NombreFichero = "dataset" + numPuntos + ".tsp";

        ArrayList<Punto> puntos = p.GeneraPuntos(numPuntos, rangoMin, rangoMax);

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar archivo");

        int userSelection = fileChooser.showSaveDialog(null);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            // Obtener la ubicación y el nombre del archivo seleccionado
            String fileToSave = fileChooser.getSelectedFile().getAbsolutePath();
            System.out.println("Se ha seleccionado guardar en: " + fileToSave);

            try {
                // Crear un FileWriter para escribir en el archivo
                FileWriter writer = new FileWriter(fileToSave);

                // Escribir contenido en el archivo
                writer.write("NAME: " + NombreFichero + "\nTYPE: TSP"
                        + "\nCOMMENT: Generado aleatoriamente para AMC"
                        + "\nDIMENSION: " + puntos.size()
                        + "\nEDGE_WEIGHT_TYPE: EXPLICIT"
                        + "\nEDGE_WEIGHT_FORMAT: EXPLICIT"
                        + "\nDISPLAY_DATA_TYPE: COORD_DISPLAY"
                        + "\nNODE_COORD_SECTION\n");
                String linea;

                for (int i = 0; i < puntos.size(); i++) {
                    int id = i + 1;
                    linea = "" + id + " " + puntos.get(i).getX() + " "+  puntos.get(i).getY() + "\n";
                    writer.write(linea);
                }

                writer.write("EOF");
                // Cerrar el FileWriter
                writer.close();

                System.out.println("Se ha escrito en el archivo: " + fileToSave);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public ArrayList<Punto> AbrirFichero() throws FileNotFoundException, IOException {
        Punto pq = new Punto();
        ArrayList<Punto> Ap = new ArrayList();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Abrir archivo");
        fileChooser.setApproveButtonText("Abrir");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        ArrayList<Punto> puntos = new ArrayList();
        // Mostrar el diálogo para seleccionar el archivo a abrir
        int userSelection = fileChooser.showOpenDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            // Obtener el archivo seleccionado
            File fileToOpen = fileChooser.getSelectedFile();
            setNombreFichero(fileToOpen.getName());
            File f = fileChooser.getSelectedFile();
            Scanner readOnlyNodes = new Scanner(f);

            ArrayList<String[]> nodos = new ArrayList<>();
            boolean untilNodos = false;

            while (readOnlyNodes.hasNextLine() && !untilNodos) {
                if (!untilNodos && readOnlyNodes.nextLine().equalsIgnoreCase("NODE_COORD_SECTION")) {
                    untilNodos = true;
                }
            }
            while (readOnlyNodes.hasNextLine() && !readOnlyNodes.hasNext("EOF")) {
                nodos.add(readOnlyNodes.nextLine().split(" "));
            }
            readOnlyNodes.close();
            for (String[] nodo : nodos) {
                System.out.println("id: " + nodo[0] + " X: " + nodo[1] + " Y: " + nodo[2]);
                puntos.add(new Punto(Double.parseDouble(nodo[1]), Double.parseDouble(nodo[2]), Integer.parseInt(nodo[0])));
            }

        }
        return puntos;
    }

}
