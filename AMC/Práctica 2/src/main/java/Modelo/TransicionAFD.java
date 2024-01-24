/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Miriam Rodríguez, Israel Fargas, Marina Vizcaíno
 * Esta clase contendrá los metodos get y set de las transiciones AFD
 */
public class TransicionAFD {
    
    private String origen;
    private String destino;
    private char simbolo;
    
    /**
     * Se crea una transicion con los datos introducidos por parametro
     * @param e1 estado origen
     * @param simbolo simbolo
     * @param e2 estado final
     */
    public TransicionAFD(String e1, char simbolo, String e2) {
        this.origen = e1;
        this.destino = e2;
        this.simbolo = simbolo;
    }

    /**
     * 
     * @return devuelve el estado origen
     */
    public String getOrigen() {
        return origen;
    }
    /**
     * 
     * @param origen estado origen
     */
    public void setOrigen(String origen) {
        this.origen = origen;
    }
    /**
     * 
     * @return devuelve el estado destino
     */
    public String getDestino() {
        return destino;
    }

    /**
     * 
     * @param destino  estado destino
     */
    public void setDestino(String destino) {
        this.destino = destino;
    }

    /**
     * 
     * @return devuelve el simbolo de la transicion
     */
    public char getSimbolo() {
        return simbolo;
    }

    /**
     * 
     * @param simbolo simbolo
     */
    public void setSimbolo(char simbolo) {
        this.simbolo = simbolo;
    }
    
    /**
     * Sirve para mostrar por pantalla las transiciones del automata
     * @return 
     */
    
    @Override
    public String toString() {
        return "TransicionAFD{" + "origen='" + origen + '\'' +
                ", simbolo=" + simbolo +
                ", destino='" + destino + '\'' + '}';
    }
    
}
