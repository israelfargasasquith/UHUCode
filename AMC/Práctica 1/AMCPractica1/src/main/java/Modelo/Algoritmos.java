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
            System.out.printf( " DistanciaTmp = %.2f DistanciaMin = %.2f \n", distanciaTemp,distanciaMinima);
                if (distanciaMinima > distanciaTemp) {
                    distanciaMinima = distanciaTemp;
                    minimos.setA(lPuntos.get(j));
                    minimos.setB(lPuntos.get(j + 1));
                }
            }
        }
        System.out.printf("DistanciaMinima actual = %.2f con los puntos P1: id = "
                + minimos.getA().getId() + " X1 = %.2f Y1 = %.2f  P2: id = " + minimos.getB().getId() + " X2 = %.2f Y2 = %.2f \n",distanciaMinima,
                minimos.getA().getX(), minimos.getA().getY(), minimos.getB().getX(),minimos.getB().getY());
        return minimos;
    }
    
    public ParPuntos busquedaPoda(ArrayList<Punto> lPuntos){
     ParPuntos minimo = new ParPuntos();
     
     
     return minimo;
    }


    public void mergeSort(ArrayList<Punto> lPuntos, int l, int r) {
        if (l < r) {
            // Find the middle point 
            int m = (l + r) / 2;

            // Sort first and second halves 
            mergeSort(lPuntos, l, m);
            mergeSort(lPuntos, m + 1, r);

            // Merge the sorted halves 
            merge(lPuntos, l, m, r);
        }
    }

    void merge(ArrayList<Punto> lPuntos, int l, int m, int r) {
        // Find sizes of two subarrays to be merged 
        int n1 = m - l + 1;
        int n2 = r - m;

        // Create temp arrays 
        int L[] = new int[n1];
        int R[] = new int[n2];

        // Copy data to temp arrays 
        for (int i = 0; i < n1; ++i) {
            L[i] = lPuntos[l + i];
        }
        for (int j = 0; j < n2; ++j) {
            R[j] = lPuntos[m + 1 + j];
        }

        // Merge the temp arrays 
        // Initial indexes of first and second subarrays 
        int i = 0, j = 0;

        // Initial index of merged subarray array 
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        // Copy remaining elements of L[] if any 
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        // Copy remaining elements of R[] if any 
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    public void printArray(int arr[]) {
        int n = arr.length;
        for (int i = 0; i < n; ++i) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

}
