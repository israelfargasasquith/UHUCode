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
public class Perro extends Thread {

    private CanvasPerrosGatos canvas;
    private Comedero comedero;
    private Random rand;
    private Image imagen;

    public Perro(CanvasPerrosGatos canvas, Comedero comedero) {
        this.comedero = comedero;
        this.canvas = canvas;
        rand = new Random(System.nanoTime());
    }

    @Override
    public void run() {
        Long id = Thread.currentThread().threadId();

        try {
            System.out.println("Perro con ID: " + id + " nace");
            canvas.pintaEsperaPerro(id);
            comedero.entraPerro();
            canvas.borraEsperaPerro(id);
            canvas.pintaEntraPerro(id);
            System.out.println("Perro con ID: " + id + " entra");
            Thread.sleep(Duration.ofSeconds(rand.nextInt(1, 3)));
            comedero.salePerro();
            canvas.borraPerroComiendo(id);
            System.out.println("Perro con ID: " + id + " sale");

        } catch (InterruptedException e) {
            System.out.println("Error en el hilo perro " + Thread.currentThread().getName() + " " + e.getMessage());
        }

    }
}
