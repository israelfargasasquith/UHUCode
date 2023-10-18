/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Punto;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author 34667
 */
public class ControlaPuntos {
    
    
    public ArrayList<Punto> generaPuntosAleatorios(int numPuntos, double rangoMin, double rangoMax) {
        Random ran = new Random(System.currentTimeMillis());
        ArrayList<Punto> lPuntos = new ArrayList<>();
        Punto p;
        for (int i = 0; i < numPuntos; i++) {
            p = new Punto(ran.nextDouble(rangoMin, rangoMax), ran.nextDouble(rangoMin, rangoMax));
            lPuntos.add(p);
        }
        for (Punto punto : lPuntos) {
            System.out.println("Punto con id: "+punto.getId() +" X("+punto.getX()+") Y("+punto.getY()+")");
        }
        return lPuntos;
    }
    
    public void pintaPuntos(ArrayList<Punto> lPuntos){
        
    }
    
   public ArrayList<Punto> GeneraPuntosFichero() throws Exception {
        File f = new File("../../dataset_amc/berlin52.tsp/berlin52.tsp");
        Scanner readOnlyNodes = new Scanner(f);
        ArrayList<Punto> puntos = new ArrayList();
        ArrayList<String[]> nodos = new ArrayList<>();
        boolean untilNodos = false;

        while (readOnlyNodes.hasNextLine() && !untilNodos) {
            if (!untilNodos && readOnlyNodes.nextLine().equalsIgnoreCase("NODE_COORD_SECTION")) {
                untilNodos = true;
            }
        }
        while (readOnlyNodes.hasNextLine() && !readOnlyNodes.nextLine().equalsIgnoreCase("EOF")) {
            nodos.add(readOnlyNodes.nextLine().split("\\s+"));
        }
        readOnlyNodes.close();
        for (String[] nodo : nodos) {
            puntos.add(new Punto(Double.parseDouble(nodo[1]), Double.parseDouble(nodo[2]), Integer.parseInt(nodo[0])));
        }
        return puntos;
    }
    
}