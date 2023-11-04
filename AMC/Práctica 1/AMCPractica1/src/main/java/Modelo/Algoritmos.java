/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.ArrayList;

/**
 *
 * @author israel
 */
public class Algoritmos {

    public enum AlgoritmosEnum {
        EXHAUSTIVO, PODA, DIVIDEYVENCERAS
    }

    public ParPuntos exhaustivo(ArrayList<Punto> lPuntos) {
        ParPuntos minimos = new ParPuntos();
        double distanciaMinima = Double.MAX_VALUE;
        double distanciaTemp;
        int numPuntos = lPuntos.size();
        for (int i = 0; i < numPuntos - 1; i++) {
            for (int j = i; j < numPuntos - 1; j++) {
                distanciaTemp = Math.sqrt(Math.pow((lPuntos.get(j).getX() - lPuntos.get(j + 1).getX()), 2.0)
                        + Math.pow((lPuntos.get(j).getY() - lPuntos.get(j + 1).getY()), 2.0));
                System.out.printf(" DistanciaTmp = %.2f DistanciaMin = %.2f \n", distanciaTemp, distanciaMinima);
                if (distanciaMinima > distanciaTemp) {
                    distanciaMinima = distanciaTemp;
                    minimos.setA(lPuntos.get(j));
                    minimos.setB(lPuntos.get(j + 1));
                }
            }
        }
        System.out.printf("DistanciaMinima actual = %.2f con los puntos P1: id = "
                + minimos.getA().getId() + " X1 = %.2f Y1 = %.2f  P2: id = " + minimos.getB().getId() + " X2 = %.2f Y2 = %.2f \n", distanciaMinima,
                minimos.getA().getX(), minimos.getA().getY(), minimos.getB().getX(), minimos.getB().getY());
        return minimos;
    }

    public ParPuntos busquedaPoda(ArrayList<Punto> lPuntos) {
        ParPuntos minimo = new ParPuntos();

        return minimo;
    }

    public void mergeSort(ArrayList<Punto> lPuntos, int n) {
        if (n < 2) {
            return;
        }
        int mid = n / 2;
        ArrayList<Punto> lPuntosLeft = new ArrayList<>();
        ArrayList<Punto> lPuntosRight = new ArrayList<>();

        for (int i = 0; i < mid; i++) {
            lPuntosLeft.add(lPuntos.get(i));
        }
        for (int i = mid; i < n; i++) {
            lPuntosRight.add(lPuntos.get(i));
        }
        mergeSort(lPuntosLeft, mid);
        mergeSort(lPuntosRight, n - mid);

        merge(lPuntos, lPuntosLeft, lPuntosRight, mid, n - mid);
    }

    public void merge(ArrayList<Punto> lPuntos, ArrayList<Punto> lPuntosLeft, ArrayList<Punto> lPuntosRight,
            int left, int right) {
        int i = 0, j = 0;
        ArrayList<Punto> merged = new ArrayList<>();

        while (i < left && j < right) {
            if (lPuntosLeft.get(i).getX() <= lPuntosRight.get(j).getX()) {
                merged.add(lPuntosLeft.get(i++));
            } else {
                merged.add(lPuntosRight.get(j++));
            }
        }
        while (i < left) {
            merged.add(lPuntosLeft.get(i++));
        }
        while (j < right) {
            merged.add(lPuntosRight.get(j++));
        }

        for (i = 0; i < merged.size(); i++) {
            lPuntos.set(i, merged.get(i));
        }
    }

//    public void printArray(ArrayList<Punto> lPuntos) {
//        for (Punto punto : lPuntos) {
//            System.out.print("Punto con id: " + punto.getId());
//            System.out.printf(" X(%.2f) Y(%.2f)\n", punto.getX(), punto.getY());
//
//        }
//        System.out.println();
//    }

}
