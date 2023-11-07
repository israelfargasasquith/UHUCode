/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/AWTForms/Frame.java to edit this template
 */
package Vista;

import Controlador.CanvasCentroMasajes;
import Controlador.Centro;
import Modelo.ClienteMasaje;
import Modelo.ClienteRehabilita;
import java.awt.Color;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author 34667
 */
public class FrameCentroMasajes extends java.awt.Frame {

    private Random r;
    private ArrayList<Thread> lClientes;
    private CanvasCentroMasajes vista;

    /**
     * Creates new form FrameCentroMasajes
     */
    public FrameCentroMasajes(CanvasCentroMasajes vista) {
        Random r = new Random(System.nanoTime());
        ArrayList<Thread> lClientes = new ArrayList<>();
        this.vista = vista;
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
        final int alto = 950, ancho = 650;
        CanvasCentroMasajes vista = new CanvasCentroMasajes(alto, ancho);
        Centro centro = new Centro(vista);
        FrameCentroMasajes vCentro = new FrameCentroMasajes(vista);

        vCentro.setSize(alto, ancho);
        vCentro.setTitle("Práctica 5 Israel Fargas");
        vCentro.setBackground(Color.LIGHT_GRAY);
        vCentro.add(vista);
        vCentro.setVisible(true);
    }

    public void generador() throws InterruptedException { //Genera todos los hilos

        for (int i = 0; i < 10; i++) {

            if (r.nextInt(1, 101) > 60) { //genera un clienteMasaje
                lClientes.add(new ClienteMasaje());
            } else { //genera un clienteRehabilita
                lClientes.add(new Thread(new ClienteRehabilita()));
            }
            sleep(200 / r.nextInt(1, 3));
        }

        for (Thread cliente : lClientes) {
            cliente.join();
        }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
