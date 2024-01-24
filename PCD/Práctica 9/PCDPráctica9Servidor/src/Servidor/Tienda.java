package Servidor;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


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

    public void entraEfectivo()  {

        l.lock();
        try {
            nEsperandoEfectivo++;
            while (cajasLibres == 0 || hayPagandoEfectivo) {
                cEsperaEfectivo.await();
            }
            cajasLibres--;
            hayPagandoEfectivo =true;
            nEsperandoEfectivo--;
        } catch (InterruptedException e) {
            System.out.println("Error en entraEfectivo: " + e.getMessage());
        } finally {
            l.unlock();
        }

    }

    public void saleEfectivo() {
        l.lock();
        try {
            cajasLibres++;
            hayPagandoEfectivo = false;
            if (nEsperandoEfectivo > 0) {
                cEsperaEfectivo.signal();
            } else {
                cEsperaTarjeta.signal();
            }
        } catch (Exception e) {
            System.out.println("Error saleEfectivo: " + e.getMessage());
        } finally {
            l.unlock();
        }
    }

    public void entraTarjeta() {
        l.lock();
        try{
            
        while (cajasLibres == 0 || (!hayPagandoEfectivo && nEsperandoEfectivo>0)) {
            cEsperaTarjeta.await();
        }
        cajasLibres--;
        }catch(InterruptedException e){
            System.out.println("Error en entraTarjeta: "+e.getMessage());
        }finally{
            l.unlock();
        }
    }

    public void saleTarjeta() {
        l.lock();
        try{
        cajasLibres++;
        if (nEsperandoEfectivo > 0 && !hayPagandoEfectivo) {
            cEsperaEfectivo.signal();
        } else {
            cEsperaTarjeta.signal();
        }
        }catch(Exception e){
            System.out.println("Error en saleTarjeta: "+e.getMessage());
        }finally{
            l.unlock();
        }
    }
}
