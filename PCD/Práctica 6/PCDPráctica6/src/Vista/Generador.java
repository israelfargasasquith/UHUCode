/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/AWTForms/Frame.java to edit this template
 */
package Vista;

import Controlador.CavasCongreso;
import Modelo.Camarero;
import Modelo.Cortado;
import Modelo.Manchado;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 34667
 */
public class Generador extends java.awt.Frame {

    /**
     * Creates new form VistaCongreso
     */
    public Generador() {
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
    public static void main(String args[]) {
        /* El camarero hay que interrumpirlo con una señal, esto provoca que el hilo lance una excepcion de interrupcion, para ello
        tiene que estar en una accion interrumpible, usamos this.isInterrupted() en el while del camarero. Después del interrupt se hace el join
        camarero.
        El canvas podemos copiarlo desde moodle porque es muy complicado
        Lo importante son los semáforos el canvas lo copiamos y ya
         */

        try {
            final int alto = 800, ancho = 550;
            Generador vista = new Generador();
            CavasCongreso cv = new CavasCongreso(ancho, alto);
            Semaphore papelera, salaCafe, salaLeche, maquinaLeche, maquinaCafe;
            int cantidadLeche = 10;
            int cantidadCafe = 10;
            papelera = new Semaphore(1);
            maquinaLeche = new Semaphore(1);
            maquinaCafe = new Semaphore(1);
            salaCafe = new Semaphore(3);
            salaLeche = new Semaphore(3);
            Random r = new Random(System.nanoTime());
            Camarero c = new Camarero(cv);
            ArrayList<Thread> lClientes = new ArrayList<>();

            vista.add(cv);
            vista.setVisible(true);
            vista.setLocationRelativeTo(null);
            c.start();

            for (int i = 0; i < 30; i++) {
                if (r.nextBoolean()) {
                    lClientes.add(new Cortado(salaCafe, salaLeche, papelera, maquinaLeche, maquinaCafe, cv));
                } else {
                    lClientes.add(new Thread(new Manchado(salaCafe, salaLeche, papelera, maquinaLeche, maquinaCafe, cv)));
                }
                Thread.sleep(Duration.ofSeconds((long) 0.5));
            }

            for (Thread lCliente : lClientes) {
                lCliente.join();
            }
            c.interrupt();
            c.join();

        } catch (InterruptedException ex) {
            System.out.println("Error, ha llegado una interrupcion " + ex.getMessage());
        }
        System.exit(0);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
