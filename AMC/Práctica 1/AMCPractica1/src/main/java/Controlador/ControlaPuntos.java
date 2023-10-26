/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.ParPuntos;
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

    private int numPuntos = 0;

    public ArrayList<Punto> generaPuntosAleatorios(int numPuntos, double rangoMinX, double rangoMaxX, double rangoMinY, double rangoMaxY) {
        Random ran = new Random(System.currentTimeMillis());
        ArrayList<Punto> lPuntos = new ArrayList<>();
        this.numPuntos = numPuntos;
        Punto p;
        for (int i = 0; i < numPuntos; i++) {
            p = new Punto(ran.nextDouble(rangoMinX, rangoMaxX), ran.nextDouble(rangoMinY, rangoMaxY));
            lPuntos.add(p);
        }
        for (Punto punto : lPuntos) {
            System.out.println("Punto con id: " + punto.getId() + " X(" + punto.getX() + ") Y(" + punto.getY() + ")");
        }
        System.out.println("Numero puntos: " + numPuntos);
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
            System.out.println("id: " + nodo[0] + " X: " + nodo[1] + " Y: " + nodo[2]);
            puntos.add(new Punto(Double.parseDouble(nodo[1]), Double.parseDouble(nodo[2]), Integer.parseInt(nodo[0])));
        }

        numPuntos = puntos.get(puntos.size() - 1).getId();
        System.out.println("Numero de puntos = " + numPuntos);
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

    public ParPuntos exhaustivo(ArrayList<Punto> lPuntos) {
        ParPuntos minimos = new ParPuntos();
        double distanciaMinima = Double.MAX_VALUE;
        double distanciaTemp;
        for (int i = 0; i < numPuntos - 1; i++) {
            for (int j = i; j < numPuntos - 1; j++) {
                distanciaTemp = Math.sqrt(Math.pow((lPuntos.get(j).getX() - lPuntos.get(j + 1).getX()), 2.0)
                        + Math.pow((lPuntos.get(j).getY() - lPuntos.get(j + 1).getY()), 2.0));
                System.out.println("DistanciaTmp = "+distanciaTemp +" DistaciaMin = "+distanciaMinima);
                if (distanciaMinima > distanciaTemp) {
                    distanciaMinima = distanciaTemp;
                    minimos.setA(lPuntos.get(j));
                    minimos.setB(lPuntos.get(j + 1));
                }
            }
        }
        System.out.println("DistanciaMinima actual = " + distanciaMinima + " con los puntos P1: id = "
                + minimos.getA().getId() + " X1 = " + minimos.getA().getX() + " Y1 = " + minimos.getA().getY()
                + " P2: id = " + minimos.getB().getId() + " X2 = " + minimos.getB().getX() + " Y2 = "
                + minimos.getB().getY());
        return minimos;
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
