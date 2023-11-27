/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Controlador.CanvasPerrosGatos;
import java.awt.Image;
import java.time.Duration;
import java.util.Random;

/**
 *
 * @author 34667
 */
public class Gato implements Runnable {

    private Random rand;
    private Comedero comedero;
    private CanvasPerrosGatos canvas;
    private Image imagen;
    

    public Gato(Comedero comedero, CanvasPerrosGatos canvas) {
        this.comedero = comedero;
        this.canvas = canvas;
        rand = new Random(System.nanoTime());
    }

    @Override
    public void run() {
        Long id = Thread.currentThread().threadId();
        try {
            System.out.println("Gato con ID: " + id + " nace");
            canvas.pintaEsperaGato(id);
            comedero.entraGato();
            canvas.borraEsperaGato(id);
            canvas.pintaEntraGato(id);
            System.out.println("Gato con ID: " + id + " entra");
            Thread.sleep(Duration.ofSeconds(rand.nextInt(1, 3)));
            comedero.saleGato();
            canvas.borraGatoComiendo(id);
            System.out.println("Gato con ID: " + id + " sale");

        } catch (InterruptedException e) {
            System.out.println("Error en el hilo gato " + id + " " + e.getMessage());
        }

    }

}
