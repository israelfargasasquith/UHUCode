/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.ClienteMasaje;
import Modelo.ClienteRehabilita;
import java.util.ArrayList;

/**
 *
 * @author 34667
 */
public class Centro {

    private boolean masajistaLibre = true;
    private boolean fisioterapeutaLibre = true;
    private boolean vestuarioLibre = true;
    private int nEsperandoMasaje = 0;
    private int nEsperandoFisio = 0;
    private CanvasCentroMasajes canvas;
    private ClienteMasaje clm;
    private ClienteRehabilita clr;
    private boolean clienteMasajeEnRehabilita;

    public Centro(CanvasCentroMasajes canvas) {
        this.canvas = canvas;
    }

    public synchronized void entraMasaje(String id) throws InterruptedException {
        nEsperandoMasaje++;
        canvas.pintaEsperandoMasaje(id);
        while (!masajistaLibre || (!fisioterapeutaLibre && nEsperandoFisio == 0)) {
            wait();
        }
        
        nEsperandoMasaje--;
        canvas.borraEsperandoMasaje(id);
        if (masajistaLibre) {
            masajistaLibre = false;
           System.out.println("Soy hilo " + id + " y acabo de entrar en el masajista");
            canvas.pintaEnMasaje(id);
        }else if (fisioterapeutaLibre) {
            fisioterapeutaLibre = false;
            clienteMasajeEnRehabilita = true;  //este hace falta??
            System.out.println("Soy hilo " + id + " y acabo de entrar en el fisio pero quiero masaje");
            canvas.pintaEnFisio(id, "M");
        }

    }

    public synchronized void saleMasaje(String id) throws InterruptedException {
        while (!vestuarioLibre) {
            wait();
        }
         canvas.borraMasaje();
        canvas.pintaVestuario(id, "M");
        masajistaLibre = true;
        vestuarioLibre = false;
        
        System.out.println("Soy hilo " +id + " y acabo de entrar en el vestuario");
        notifyAll();
       
    }

    public synchronized void entraRehabilita(String id) throws InterruptedException {
        nEsperandoFisio++;
        canvas.pintaEsperandoRehabilitacion(id);
        while (!fisioterapeutaLibre) {
            wait();
        }
        canvas.borraEsperandoRehabilitacion(id);
        canvas.pintaEnFisio(id, "R");
        nEsperandoFisio--;
        fisioterapeutaLibre = false;
        
        System.out.println("Soy hilo " + id+ " y acabo de entrar en el fisio");

    }

    public synchronized void saleRehabilita(String id) throws InterruptedException {
        while (!vestuarioLibre) {
            wait();
        }
         if (clienteMasajeEnRehabilita) {
            clienteMasajeEnRehabilita = false;
            canvas.pintaVestuario(id, "M");
        } else {
            canvas.pintaVestuario(id, "R");
        }
        fisioterapeutaLibre = true;
        vestuarioLibre = false;
        canvas.borraRehabilita();
       
        System.out.println("Soy hilo " + id + " y acabo de entrar en el vestuario");
        notifyAll();
    }

    public synchronized void termina(String id) {
        canvas.borraVestuario(id);
        vestuarioLibre = true;
        notifyAll();
    }

}
