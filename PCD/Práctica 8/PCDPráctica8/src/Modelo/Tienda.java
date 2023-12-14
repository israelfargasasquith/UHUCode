/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 *
 * @author 34667
 */
public class Tienda {

    private int cajasLibres;
    private int nEsperandoEfectivo;
    private boolean hayPagandoEfectivo;
    private Lock l;
    private Condition cEsperaEfectivo;
    private Condition cEsperaTarjeta;

    public Tienda() {
        nEsperandoEfectivo = 0;
        cajasLibres = 4;
        hayPagandoEfectivo = false;
        l = new ReentrantLock();
        cEsperaEfectivo = l.newCondition();
        cEsperaTarjeta = l.newCondition();
    }

    public void entraEfectivo() throws InterruptedException {

        l.lock();
        try {
            nEsperandoEfectivo++;
            while (cajasLibres == 0 || hayPagandoEfectivo) {
                cEsperaEfectivo.await();
            }
        } catch (InterruptedException e) {
            System.out.println("Error en entraEfectivo: " + e.getMessage());
        } finally {
            l.unlock();
        }

    }
    
    public void saleEfectivo(){
        cajasLibres++;
        hayPagandoEfectivo=false;
        if(nEsperandoEfectivo>0){
            cEsperaEfectivo.signal();
        }else{
            cEsperaTarjeta.signal();
        }
    }
    
    public void entraTarjeta() throws InterruptedException{
        while(cajasLibres == 0){
            cEsperaTarjeta.await();
        }
        cajasLibres--;
    }
    
    public void saleTarjeta(){
        cajasLibres++;
        if(nEsperandoEfectivo>0 && !hayPagandoEfectivo){
            cEsperaEfectivo.signal();
        }else{
            cEsperaTarjeta.signal();
        }
    }
}
