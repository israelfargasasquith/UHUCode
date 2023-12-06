/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Controlador.Centro;
import java.util.Random;

/**
 *
 * @author 34667
 */
public class ClienteRehabilita implements Runnable {

    private Centro centro;
    private Random rand;
    private String id;

    public ClienteRehabilita(Centro centro, String id) {
        this.centro = centro;
        this.id = id;
        rand = new Random(System.nanoTime());
    }

    @Override
    public void run() {
        try {
            System.out.println("Soy hilo " + id + " de la clase rehabilita y acabo de nacer");
            centro.entraRehabilita(id);
            Thread.currentThread().sleep(rand.nextInt(2, 5) * 1000);
            centro.saleRehabilita(id);
            Thread.currentThread().sleep(rand.nextInt(2, 5) * 1000);
            centro.termina(id);
            System.out.println("Soy hilo " + id + " de la clase rehabilita y he acabado");
        } catch (InterruptedException ex) {
            System.out.println("Error con los hilos:" + ex.getMessage());
        }
    }

}
