/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author israel
 */
public class Pila implements IPila {

    private int cima; //señala a la posicion del elemento mas alto en la pila
    private int capacidad; //tamaño del array
    private int numElementos; //Numero de elementos actuales, cuando pila es vacia -> 0
    private Object[] datos;

    public Pila(int capacidad) {
        this.cima = -1;
        this.numElementos = 0;
        this.capacidad = capacidad;
        this.datos = new Object[capacidad];
    }

    public void verPila(){
        for (int i = 0; i < numElementos; i++) {
            System.out.println("Dato["+i+"] = "+datos[i]);
        }
    }
    
    private boolean pilaVacia() { //Si la pila esta vacía devuelve true
        return numElementos == 0;
    }

    private boolean pilaLlena() { //True si la pila esta llena
        return numElementos == capacidad;
    }

    @Override
    public int getnNum() {
        return numElementos;
    }

    @Override
    public void apila(Object obj) throws Exception {
        if (!pilaLlena()) {
            cima++;
            numElementos++;
            datos[cima] = obj;
        } else {
            throw new Exception("Error al intentar apilar el valor " + obj + ", la pila estaba llena con " + numElementos + " elementos");
        }
    }

    @Override
    public Object desapila() throws Exception {
        if (!pilaVacia()) {
            cima--;
            numElementos--;
            return datos[cima + 1];
        } else {
            throw new Exception("Error al intentar desapilar la pila estaba vacia");
        }
    }

    @Override
    public Object primero() throws Exception {
        if (!pilaVacia()) {
            return datos[cima];
        } else {
            throw new Exception("Error al intentar mirar la cima, la pila estaba vacia");
        }
    }

}
