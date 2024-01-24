/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.ArrayList;

/**
 *
 * @author miria
 */
public class AFD implements Proceso {

    private ArrayList<String> estadosFinales; //indica cuales son los estados Finales
    //private ArrayList<Nodo> nodos;
    private String estadoInicial;            // Estado inicial del AFD
    private ArrayList<TransicionAFD> transiciones; //indica la lista de transiciones del AFD
    private String Cadena;
    public AFD() {

        this.estadoInicial = "";
        this.transiciones = new ArrayList<>();
        this.estadosFinales = new ArrayList<>();
        this.Cadena="";

    }

     /**
     * Este metodo consiste en añadir al ArrayList la transicion correspondiente 
     * a los datos pasados por parametro
     * @param e1 estado de origen de la transicion
     * @param simbolo simbolo de la transicion
     * @param e2 estado destino de la transicion
     */
    public void agregarTransicion(String e1, char simbolo, String e2) {

        this.transiciones.add(new TransicionAFD(e1, simbolo, e2));
    }

     /**
     * Este metodo cosiste en devolver el estado destino a partir
     * de un estado origen y un simbolo pasados por parametro
     * @param estado
     * @param simbolo
     * @return devuelve el estado destino de la transicion 
     */
    public String transicion(String estado, char simbolo) {

        String estadoDestino = "-1";
        for (TransicionAFD array : transiciones) {
            if (array.getOrigen().equalsIgnoreCase(estado) && array.getSimbolo() == simbolo) {
                estadoDestino = array.getDestino();
            }
        }

        return estadoDestino;
    }

    
    /**
     * Este metodo consiste en comprobar si un estado es final o no
     * @param estado a comprobar 
     * @return devuelve true si es final y false si no es final
     */
    @Override
    public boolean esFinal(String estado) {

        //buscammos 
        boolean esFinal = false;
        int i = 0;
        while (i < estadosFinales.size() && !esFinal) {
            if (estadosFinales.get(i).equalsIgnoreCase(estado)) {
                esFinal = true;
            } else {
                i++;
            }
        }
        return esFinal;
    }

    /**
     * Este metodo consiste en comprobar si una cadena pertenece o no al lenguaje del 
     * automata
     * @param cadena a comprobar 
     * @return 
     */
    @Override
    public boolean reconocer(String cadena) {
        char[] simbolo = cadena.toCharArray();
        //String estado = "0"; //El estado inicial es el 0
        String estado = estadoInicial;
        for (int i = 0; i < simbolo.length; i++) {
           System.out.println("Estado: " + estado + "          SIMBOLO: " + simbolo[i]);
            estado = transicion(estado, simbolo[i]);
        }
        return esFinal(estado);
    }

    
    /**
     * Este es un metodo que no utilizamos actualmente pero lo hemos
     * utilizado para realizar pruebas
     * @return 
     */
    public static AFD pedir() {

        //int [] estadosFinales = {3};
        AFD afd = new AFD();

        afd.agregarTransicion("0", '1', "1");
        afd.agregarTransicion("1", '2', "2");
        afd.agregarTransicion("2", '1', "1");
        afd.agregarTransicion("2", '2', "3");
        //afd.estadosFinales[0]= 3;
        afd.estadosFinales.add("3");

        return afd;
    }

    /**
     * Este metodo consiste en comprobar si la cadena es valida
     * para ello utiliza el metodo anteriomente explicado reconocer(String cadena);
     */
    public void probar() {

        //String cadena = "122";
        //String cadena = "1212";
        boolean esFinal = reconocer(Cadena);
        if (esFinal) {
            System.out.println("Es final");
        } else {
            System.out.println("No es final");
        }
    }

    
    /**
     * 
     * @return array de estados finales
     */
    public ArrayList<String> getEstadosFinales() {
        return estadosFinales;
    }
    
    /**
     * 
     * @param estadosFinales  array de estados finales
     */
    public void setEstadosFinales(ArrayList<String> estadosFinales) {
        this.estadosFinales = estadosFinales;
    }

    /**
     * 
     * @return estado inicial
     */
    public String getEstadoInicial() {
        return estadoInicial;
    }

    /**
     * 
     * @param estadoInicial  estado inicial
     */
    public void setEstadoInicial(String estadoInicial) {
        this.estadoInicial = estadoInicial;
    }

    /**
     * 
     * @return array de transiciones
     */
    public ArrayList<TransicionAFD> getTransiciones() {
        return transiciones;
    }

    
    /**
     * 
     * @param transiciones  array de transiciones
     */
    public void setTransiciones(ArrayList<TransicionAFD> transiciones) {
        this.transiciones = transiciones;
    }

    
    /**
     * 
     * @return cadena 
     */
    public String getCadena() {
        return Cadena;
    }

    /**
     * 
     * @param Cadena cadena
     */
    public void setCadena(String Cadena) {
        this.Cadena = Cadena;
    }
    
    

}
