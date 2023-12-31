/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/AWTForms/Frame.java to edit this template
 */
package Vista;

import Modelo.ClienteEfectivo;
import Modelo.ClienteTarjeta;
import Modelo.Tienda;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 *
 * @author 34667
 */
public class VistaDecatlon extends java.awt.Frame {

    /**
     * Creates new form VistaDecatlon
     */
    public VistaDecatlon() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Exit the Application
     */
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        System.exit(0);
    }//GEN-LAST:event_exitForm

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws InterruptedException, ExecutionException {
        ExecutorService poolEfectivo = Executors.newFixedThreadPool(3); //Cola hilos efectivo
        ExecutorService poolTarjeta = Executors.newFixedThreadPool(3);//Cola hilos tarjeta

        ArrayList<Future<Integer>> tiempoTarjeta = new ArrayList<>();
        ArrayList<Future<Integer>> tiempoEfectivo = new ArrayList<>();

        Random rand = new Random(System.nanoTime());
        Tienda recurso = new Tienda();

        for (int i = 0; i < 10; i++) {
            if (rand.nextBoolean()) {
                tiempoTarjeta.add(poolTarjeta.submit(new ClienteTarjeta(recurso, i)));
            } else {
                tiempoEfectivo.add(poolEfectivo.submit(new ClienteEfectivo(recurso, i)));
            }
            Thread.sleep(500);
        }

       

        int tiempoTotalTarjeta = 0;
        int tiempoTotalEfectivo = 0;

        for (int i = 0; i < tiempoTarjeta.size(); i++) {
            System.out.println("Size tarjeta-> " + tiempoTarjeta.size());
            System.out.println("El tiempoTarjeta a sumar es de " + tiempoTarjeta.get(i).get() + " milisegundos");
            tiempoTotalTarjeta += tiempoTarjeta.get(i).get();
        }
        for (int i = 0; i < tiempoEfectivo.size(); i++) {
            System.out.println("Size efectivo-> " + tiempoEfectivo.size());
            System.out.println("El tiempoEfectivo a sumar es de " + tiempoEfectivo.get(i).get() + " milisegundos");
            tiempoTotalEfectivo += tiempoEfectivo.get(i).get();
        }
        poolEfectivo.shutdown(); EXPLOTA POR CONCURRENCIA
        poolTarjeta.shutdown();

        System.out.println("El tiempo total en pagar con Tarjeta es de: " + tiempoTotalTarjeta + "\n El tiempo total en pagar con Efectivo es de: " + tiempoTotalEfectivo);
        System.exit(0);

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
