/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

//import Vista.VistaPerrosGatos.ColaGato;
//import Vista.VistaPerrosGatos.ColaPerro;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author 34667
 */
public class Comedero {

    private int nPerrosDentro;
    private int nGatosDentro;
    private int nPerrosEsperando;
    private int nGatosEsperando;
    private Lock l;
    private Condition esperaPerro;
    private Condition esperaGato;

    public Comedero() {
        l = new ReentrantLock();
        nPerrosEsperando = 0;
        nGatosEsperando = 0;
        nPerrosDentro = 0;
        nGatosDentro = 0;
        esperaPerro = l.newCondition();
        esperaGato = l.newCondition();
    }

    public void entraPerro() {
        l.lock();
        try {
            while(nPerrosDentro + nGatosDentro == 4 || nGatosDentro == 3 || (nGatosDentro == 1 && nPerrosDentro == 2)) {
                esperaPerro.await();
            }
            nPerrosDentro++;
        } catch (InterruptedException e) {
            System.out.println("Error en entraPerro: " + e.getMessage());
        } finally {
            l.unlock();
        }

    }

    public void entraGato() {
        l.lock();
        try {
            while(nPerrosDentro + nGatosDentro == 4 || nPerrosDentro == 3 || (nPerrosDentro == 1 && nGatosDentro == 2)) {
                esperaGato.await();
            }
            nGatosDentro++;
        } catch (InterruptedException e) {
            System.out.println("Error en entra Gato: " + e.getMessage());
        } finally {
            l.unlock();
        }

    }

    public void saleGato() {
        l.lock();
        try {
            nGatosDentro--;
            if (nGatosEsperando > 0) { //Si hay gatos esperando desperartamos un gato
                esperaGato.signal();
                nGatosEsperando--;
            } else if (nGatosDentro < 3 && !(nGatosDentro == 1 && nPerrosDentro == 2)) {
                esperaPerro.signal();
                nPerrosEsperando--;
            }
        } catch (Exception e) {
            System.out.println("Error en sale gato " + e.getMessage());
        } finally {
            l.unlock();
        }
    }

    public void salePerro() {
        l.lock();
        try {
            nPerrosDentro--;
            if (nPerrosEsperando > 0) {
                esperaPerro.signal();
                nPerrosEsperando--;
            } else if (nPerrosDentro < 3 && !(nPerrosDentro == 1 && nGatosDentro == 2)) {
                esperaGato.signal();
                nGatosEsperando--;
            }

        } catch (Exception e) {
            System.out.println("Error en sale perro " + e.getMessage());
        } finally {
            l.unlock();
        }

    }
}
