/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Punto;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author 34667
 */
public class ControlaPuntos {

    private int numPuntosFichero = 0;

    public void generaFichero(int numPuntos, String nombreFichero, double rangoMin, double rangoMax) {

        ArrayList<Punto> lPuntos = generaPuntosAleatorios(numPuntos, rangoMin, rangoMax, rangoMin, rangoMax);
        String nombre = nombreFichero + numPuntos;
        try {
            String path = "C:\\Users\\34667\\Documents\\dataset_amc\\"+nombre+".tsp";
            FileWriter f = new FileWriter(path);
            f.write("NAME: " + nombre + "\nTYPE: TSP"
                    + "\nCOMMENT: Generado aleatoriamente para AMC"
                    + "\nDIMENSION: " + numPuntos
                    + "\nEDGE_WEIGHT_TYPE: EXPLICIT"
                    + "\nEDGE_WEIGHT_FORMAT: EXPLICIT"
                    + "\nDISPLAY_DATA_TYPE: COORD_DISPLAY"
                    + "\nNODE_COORD_SECTION\n");
            String linea;
            
            for (Punto punto : lPuntos) {
               linea = ""+punto.getId()+" "+punto.getX()+" "+punto.getY()+"\n";
               f.write(linea);
                //System.out.print(linea);
            }
            f.write("EOF");
            f.close();
        } catch (IOException ioe) {
            System.out.println("Error: Los ficheros han petao de la siguiente forma-> " + ioe.getMessage());
        }catch(Exception e){
            System.out.println("Error rarete: "+e.getMessage());
        }

    }

    public ArrayList<Punto> generaPuntosAleatorios(int numPuntos, double rangoMinX, double rangoMaxX, double rangoMinY, double rangoMaxY) {
        Random ran = new Random(System.currentTimeMillis());
        ArrayList<Punto> lPuntos = new ArrayList<>();
        Punto p;
        for (int i = 0; i < numPuntos; i++) {
            p = new Punto(ran.nextDouble(rangoMinX, rangoMaxX), ran.nextDouble(rangoMinY, rangoMaxY));
            lPuntos.add(p);
        }
        for (Punto punto : lPuntos) {
            System.out.print("Punto con id: " + punto.getId());
            System.out.printf(" X(%.2f) Y(%.2f)\n", punto.getX(), punto.getY());

        }
        return lPuntos;
    }

    public void pintaPuntos(ArrayList<Punto> lPuntos) {

    }

    public ArrayList<Punto> generaPuntosFichero(String path) throws Exception {
        //File f = new File("./dataset_amc/berlin52.tsp/berlin52.tsp");
        File f = new File(path);
        Scanner readOnlyNodes = new Scanner(f);
        ArrayList<Punto> puntos = new ArrayList();
        ArrayList<String[]> nodos = new ArrayList<>();
        boolean untilNodos = false;

        while (readOnlyNodes.hasNextLine() && !untilNodos) {
            if (!untilNodos && readOnlyNodes.nextLine().equalsIgnoreCase("NODE_COORD_SECTION")) {
                untilNodos = true;
            }
        }
        while (readOnlyNodes.hasNextLine() && !readOnlyNodes.hasNext("EOF")) {
            nodos.add(readOnlyNodes.nextLine().split("\\s+"));
        }
        readOnlyNodes.close();
        for (String[] nodo : nodos) {
            //System.out.println("id: " + nodo[0] + " X: " + nodo[1] + " Y: " + nodo[2]);
            puntos.add(new Punto(Double.parseDouble(nodo[1]), Double.parseDouble(nodo[2]), Integer.parseInt(nodo[0])));
        }

        numPuntosFichero = puntos.get(puntos.size() - 1).getId();
        System.out.println("Numero de puntos = " + numPuntosFichero);
        return puntos;
    }

    public ArrayList<Punto> factorConversion(ArrayList<Punto> lPuntos, int tamañoVistaX, int tamañoVistaY) {
        ArrayList<Punto> lPuntosReescalados = new ArrayList<>();
        //Primero cogemos el valor maximo del fichero para X y para Y
        double xMax = -1, yMax = -1;
        for (Punto lPunto : lPuntos) {
            System.out.println("El valor de xMax es: " + xMax + " y el de yMax es: " + yMax);
            if (xMax < lPunto.getX()) {
                xMax = lPunto.getX();
            }
            if (yMax < lPunto.getY()) {
                yMax = lPunto.getY();
            }
        }
        double factorConversionX = (double) (tamañoVistaX - 10.0) / xMax;
        double factorConversionY = (double) (tamañoVistaY - 10.0) / yMax;
        System.out.println("Factor x: " + factorConversionX + " Factor y: " + factorConversionY);
        for (Punto lPunto : lPuntos) {
            lPuntosReescalados.add(new Punto(factorConversionX * lPunto.getX(), factorConversionY * lPunto.getY(), lPunto.getId()));
            System.out.println("Valor del punto original-> id: " + lPunto.getId() + " x: " + lPunto.getX() + " y: " + lPunto.getY()
                    + "\n Valor del punto reescalado-> id: " + lPunto.getId() + " x: " + lPunto.getX() * factorConversionX + " y: " + lPunto.getY() * factorConversionY);
        }

        return lPuntosReescalados;
    }

}
