/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author 34667
 */
public class Tienda {
    private int nPagando; //numero de personas que estan pagando en ese momento
    private int nEsperandoEfectivo;
    //Lock l;
//    Condition caja[];
    
    public Tienda(){
        nPagando =0;
        nEsperandoEfectivo = 0;
//        l = new ReentrantLock();
//        for (int i = 0; i < 4; i++) {
//            
//        }
    } AQUI TE QUEDASTE ISRA, TIENES QUE VER LAS OTRAS PRÁCTICAS Y PLANTEAR MEJOR EL PROBLEMA DE LA TIENDA ESTA
    
    public void entraTarjeta() {
        while(nPagando ==4 || nEsperandoEfectivo > 0){
            wait();
        }
        nPagando++;
            System.out.println("Ha entrado a pagar el hilo "+Thread.currentThread().getName());
    }
    
    public void saleTarjeta(){
         
        nPagando--;
    }
    
    
}
