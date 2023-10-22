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
    private int numPuntosFichero = 0;

    public ArrayList<Punto> generaPuntosAleatorios(int numPuntos, double rangoMinX, double rangoMaxX, double rangoMinY, double rangoMaxY) {
        Random ran = new Random(System.currentTimeMillis());
        ArrayList<Punto> lPuntos = new ArrayList<>();
        Punto p;
        for (int i = 0; i < numPuntos; i++) {
            p = new Punto(ran.nextDouble(rangoMinX, rangoMaxX), ran.nextDouble(rangoMinY, rangoMaxY));
            lPuntos.add(p);
        }
        for (Punto punto : lPuntos) {
            System.out.println("Punto con id: " + punto.getId() + " X(" + punto.getX() + ") Y(" + punto.getY() + ")");
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
            puntos.add(new Punto(Double.parseDouble(nodo[1]), Double.parseDouble(nodo[2]), Integer.parseInt(nodo[0])));
        }
        numPuntosFichero = puntos.get(puntos.size()-1).getId();
        return puntos;
    }

    public void quicksort(int A[], int izq, int der) {

        int pivote = A[izq]; // tomamos primer elemento como pivote
        int i = izq;         // i realiza la búsqueda de izquierda a derecha
        int j = der;         // j realiza la búsqueda de derecha a izquierda
        int aux;

        while (i < j) {                          // mientras no se crucen las búsquedas                                   
            while (A[i] <= pivote && i < j) {
                i++; // busca elemento mayor que pivote
            }
            while (A[j] > pivote) {
                j--;           // busca elemento menor que pivote
            }
            if (i < j) {                        // si no se han cruzado                      
                aux = A[i];                      // los intercambia
                A[i] = A[j];
                A[j] = aux;
            }
        }

        A[izq] = A[j];      // se coloca el pivote en su lugar de forma que tendremos                                    
        A[j] = pivote;      // los menores a su izquierda y los mayores a su derecha

        if (izq < j - 1) {
            quicksort(A, izq, j - 1);          // ordenamos subarray izquierdo
        }
        if (j + 1 < der) {
            quicksort(A, j + 1, der);          // ordenamos subarray derecho
        }
    }
}
