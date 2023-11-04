/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package APP;

import Controlador.ControlaPuntos;
import Modelo.Algoritmos;
import Modelo.Punto;
import Vistas.VistaPrincipal;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author 34667
 */
public class Practica1AMC {

    public static void main(String[] args) {
        ControlaPuntos cp = new ControlaPuntos();
        VistaPrincipal vp = new VistaPrincipal(cp);
        try {
            vp.setLocationRelativeTo(null);
            vp.setVisible(true);
            vp.setResizable(false);
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
//        ControlaPuntos cp = new ControlaPuntos();
//        Algoritmos a = new Algoritmos();
//        ArrayList<Punto> lPunto = cp.generaPuntosAleatorios(5, 0, 10, 10, 100);
//         cp.generaFichero(10, "Prueba", 0, 1000);
//        a.exhaustivo(lPunto);
//        System.out.println("Array sin ordenar:");
//        a.printArray(lPunto);
//        a.mergeSort(lPunto, lPunto.size());
//        System.out.println("\n\nArray ordenado:");
//        a.printArray(lPunto);

    }
}
