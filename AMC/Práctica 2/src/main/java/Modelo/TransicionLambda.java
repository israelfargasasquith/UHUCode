/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Miriam Rodríguez, Israel Fargas, Marina Vizcaíno
 * Esta clase contendrá los metodos get y set de las transiciones Lambda
 */
public class TransicionLambda {
    
    private String origen;
    private String [] destino;
    
    /**
     * Se crea una transicion con los datos introducidos por parametro
     * @param e1 estado origen
     * @param e2 estado final
     */
    public TransicionLambda(String e1, String [] e2){
        this.origen = e1;
        this.destino = e2;
    }

    /**
     * 
     * @return estado origen
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
     * @return devuelve el esatado destino
     */
    public String [] getDestino() {
        return destino;
    }

    /**
     * 
     * @param destino estado destino
     */
    public void setDestino(String [] destino) {
        this.destino = destino;
    }

    
}
