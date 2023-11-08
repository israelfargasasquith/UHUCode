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

    public ClienteRehabilita(Centro centro) {
        this.centro = centro;
        rand = new Random(System.nanoTime());
    }

    @Override
    public void run() {
        try {
            centro.entraRehabilita();
            Thread.currentThread().sleep(rand.nextInt(2, 5)*1000);
            centro.saleRehabilita();
            Thread.currentThread().sleep(rand.nextInt(2, 5) * 1000);
            centro.termina();
        } catch (InterruptedException ex) {
            System.out.println("Error con los hilos:" + ex.getMessage());
        }
    }

}
