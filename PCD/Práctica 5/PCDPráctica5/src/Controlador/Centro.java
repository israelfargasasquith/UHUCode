/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import java.util.ArrayList;

/**
 *
 * @author 34667
 */
public class Centro {

    private boolean masajistaLibre = true;
    private boolean fisioterapeutaLibre = true;
    private boolean vestuarioLibre = true;
    private CanvasCentroMasajes canvas;
    
    public Centro(CanvasCentroMasajes canvas) {
        this.canvas = canvas;
    }

    synchronized public void entraMasaje() throws InterruptedException {
        while (!masajistaLibre) {
            wait();
        }
        masajistaLibre = false;
        //canvas.actualiza(masajistaLibre, fisioterapeutaLibre, vestuarioLibre, Thread.currentThread().threadId()s);
    }

    synchronized public void saleMasaje() throws InterruptedException {
        while (!vestuarioLibre) {
            wait();
        }
        masajistaLibre = true;
        vestuarioLibre = false;
       // canvas.actualiza(masajistaLibre, fisioterapeutaLibre, vestuarioLibre,Thread.currentThread().threadId());
        notifyAll();
    }

    synchronized public void entraRehabilita() throws InterruptedException {
        while (!fisioterapeutaLibre) {
            wait();
        }
        fisioterapeutaLibre = false;
        //canvas.actualiza(masajistaLibre, fisioterapeutaLibre, vestuarioLibre,Thread.currentThread().threadId());

    }

    synchronized public void saleRehabilita() throws InterruptedException {
        while (!vestuarioLibre) {
            wait();
        }
        fisioterapeutaLibre = true;
        vestuarioLibre = false;
        //canvas.actualiza(masajistaLibre, fisioterapeutaLibre, vestuarioLibre,Thread.currentThread().threadId());
        notifyAll();
    }

    synchronized public void termina() {
        vestuarioLibre = true;
       // canvas.actualiza(masajistaLibre, fisioterapeutaLibre, vestuarioLibre,Thread.currentThread().threadId());
        notifyAll();
    }

}
