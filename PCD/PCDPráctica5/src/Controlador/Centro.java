/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

/**
 *
 * @author 34667
 */
public class Centro {

    private boolean MLibre = true;
    private boolean FLibre = true;
    private boolean VLibre = true;

    synchronized public void entraMasaje() throws InterruptedException {
        while (!MLibre) {
            wait();
        }
        MLibre = false;
    }

    synchronized public void saleMasaje() throws InterruptedException {
        while (!VLibre) {
            wait();
        }
        MLibre = true;
        VLibre = false;
        notifyAll();
    }

    synchronized public void entraRehabilita() throws InterruptedException {
        while (!FLibre) {
            wait();
        }
        FLibre = false;
    }

    synchronized public void saleRehabilita() throws InterruptedException {
        while (!VLibre) {
            wait();
        }
        FLibre = true;
        VLibre = false;
        notifyAll();
    }

    synchronized public void termina() {
        VLibre = true;
        notifyAll();
    }

}
