/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author miria
 */
public class AFND implements Proceso {

    private ArrayList<String> estadosFinales; //indica cuales son los estados Finales
    private ArrayList<TransicionAFND> transiciones; //indica la lista de transiciones del AFND
    private ArrayList<TransicionLambda> transicionesλ; //indica la lista de transiciones λ del AFND
    private String estadoInicial;
    private String Cadena;
    
    
    public AFND() {

        this.estadoInicial = "";
        this.transiciones = new ArrayList<>();
        this.transicionesλ = new ArrayList<>();
        this.estadosFinales = new ArrayList<>();

    }

    /**
     * este metodo consiste en agregar la transicion correspondiente a los
     * datos pasados por parametro
     * @param e1 estado origen de la transicion
     * @param simbolo simbolo de la transicion
     * @param e2  estado destino de la transicion
     */
    public void agregarTransicion(String e1, char simbolo, String[] e2) {

        this.transiciones.add(new TransicionAFND(e1, simbolo, e2));
    }

    /**
     * Este metodo consiste en agregar la transicion lambda corresponndiente a 
     * los datos pasados por parametro
     * @param e1 estado origen
     * @param e2 estado destino
     */
    public void agregarTransicionλ(String e1, String[] e2) {

        this.transicionesλ.add(new TransicionLambda(e1, e2));

    }

    /**
     * Este metodo consiste en devolver el estado destino correspondientes
     * a la transicion introducida por parametro
     * @param estado
     * @param simbolo
     * @return 
     */
    private String[] transicion(String estado, char simbolo) {

        String[] estadoDestino = null;

        for (TransicionAFND array : transiciones) {
            if (array.getOrigen().equalsIgnoreCase(estado) && array.getSimbolo() == simbolo) {
                estadoDestino = array.getDestino();
            }
        }

        return estadoDestino;
      
    }

    /**
     * Dado un macroestado y un simbolo devuelve los estados destino de la transicion
     * en caso de que no exista la transicion devuleve null
     * @param macroestado macroestado
     * @param simbolo simbolo
     * @return array de los estados destino de la transicion
     */
    public String[] transicion(String[] macroestado, char simbolo) {

        ArrayList<String> estadoDestinos = new ArrayList<>();
        String[] aux = null;

        for (String estado : macroestado) {
            aux = transicion(estado, simbolo);
            if (aux != null) {
                for (String destino : aux) {
                    if (!estadoDestinos.contains(destino)) {
                        estadoDestinos.add(destino);
                    }
                }
            }
        }

        return estadoDestinos.toArray(new String[0]);
    }

    /**
     * Dado un estado devuelve los estados destino de la transicion lambda
     * en caso de que no exista la transicion devuleve null
     * @param estado
     * @return array de los estados destino de la transicion
     */
    public String[] transicionλ(String[] estado) {

        ArrayList<String> estadoDestino = new ArrayList<String>();

        for (TransicionLambda transicion : transicionesλ) {
            for (String est : estado) {
                if (transicion.getOrigen().equalsIgnoreCase(est)) {
                    for (int k = 0; k < transicion.getDestino().length; k++) {
                        estadoDestino.add(transicion.getDestino()[k]);

                        String[] aux = transicionλ(estadoDestino.toArray(new String[0]));
                        for (int l = 0; l < aux.length; l++) {
                            if (!estadoDestino.contains(aux[l])) {
                                estadoDestino.add(aux[l]);
                            }
                        }
                    }
                }
            }
        }

        return estadoDestino.toArray(new String[0]);
    }

    /**
     * Este metodo devuelve true si el estado es un estado final y false en caso contrario
     * @param macroestado macroesatdo
     * @return 
     */
    public boolean esFinal(String[] macroestado) {

        boolean esFinal = false;

        for (String macro : macroestado) {
            if (esFinal(macro)) {
                esFinal = true;
            }
        }

        return esFinal;

    }
    
    /**
     * 
     * @param macroestado conjunto de estados a los que se aplicara lambda clausura
     * @return array de la lambda clausura del macroestado pasado
     */
    private String[] λ_clausura(String[] macroestado) {

        ArrayList<String> lambda_clausura = new ArrayList<String>();
        String[] aux = transicionλ(macroestado);;

        for (String macro : macroestado) {
            if (!lambda_clausura.contains(macro)) {
                lambda_clausura.add(macro);
            }
        }

        for (String a : aux) {
            if (!lambda_clausura.contains(a)) {
                lambda_clausura.add(a);
            }
        }

        return lambda_clausura.toArray(new String[0]);

    }

    /**
     * Este es un metodo que no utilizamos actualmente pero lo hemos
     * utilizado para realizar pruebas
     * @return 
     */
    public static AFND pedir() {

        AFND afnd = new AFND();

        /*afd.agregarTransicion("0", '1', "1");
        afd.agregarTransicion("1", '2', "2");
        afd.agregarTransicion("2", '1', "1");
        afd.agregarTransicion("2", '2', "3");
        //afd.estadosFinales[0]= 3;
        afd.estadosFinales.add("3");*/
        String[] destinos = {"1", "3"};
        afnd.agregarTransicionλ("0", destinos);
        afnd.agregarTransicion("1", 'a', new String[]{"2"});
        afnd.agregarTransicion("3", 'b', new String[]{"4"});
        afnd.agregarTransicion("2", 'a', new String[]{"2"});
        afnd.agregarTransicion("4", 'b', new String[]{"4"});
        afnd.estadosFinales.add("2");
        return afnd;
    }

    
    /**
     * Este metodo que devuelve true si el estado a comprobar es un estado final
     * y false en caso contrario
     * @param estado estado a comprobar
     * @return 
     */
    @Override
    public boolean esFinal(String estado) {

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
     * 
     * @param cadena cadena a comprobar 
     * @return devuelve true si la cadena pertenece al leguaje 
     * o false en caso contrario
     */
    @Override
    public boolean reconocer(String cadena) {
        char[] simbolo = cadena.toCharArray();
        String[] estado = {estadoInicial}; //El estado inicial es el 0
        String[] macroestado = λ_clausura(estado);
        for (int i = 0; i < simbolo.length; i++) {
            macroestado = transicion(macroestado, simbolo[i]);
        }
        return esFinal(macroestado);
    }

    /**
     * Este metodo consiste en comprobar si la cadena es valida
     * para ello utiliza el metodo anteriomente explicado reconocer(String cadena);
     */
    public void probar() {

        //String cadena = "a";
        //String cadena = "a";

        boolean esFinal = reconocer(Cadena);
        if (esFinal) {
            System.out.println("Es final");
        } else {
            System.out.println("No es final");
        }
    }
    
    
    
    //getters y setters 
    
    /**
     * 
     * @return devuelve el array con los estados finales
     */
    public ArrayList<String> getEstadosFinales() {
        return estadosFinales;
    }

    /**
     * 
     * @param estadosFinales estados finales
     */
    public void setEstadosFinales(ArrayList<String> estadosFinales) {
        this.estadosFinales = estadosFinales;
    }

    /**
     * 
     * @return devuleve el array de las transiciones
     */
    public ArrayList<TransicionAFND> getTransiciones() {
        return transiciones;
    }

    /**
     * 
     * @param transiciones transiciones
     */
    public void setTransiciones(ArrayList<TransicionAFND> transiciones) {
        this.transiciones = transiciones;
    }
    
    /**
     * 
     * @return devuevle el array de las transiciones lambda
     */
    public ArrayList<TransicionLambda> getTransicionesλ() {
        return transicionesλ;
    }

    /**
     * 
     * 
     * @param transicionesλ  transiciones lambda
     */
    public void setTransicionesλ(ArrayList<TransicionLambda> transicionesλ) {
        this.transicionesλ = transicionesλ;
    }

    /**
     * devuelve el esatdo inicial
     * @return 
     */
    public String getEstadoInicial() {
        return estadoInicial;
    }

    /**
     * 
     * @param estadoInicial estado incial
     */
    public void setEstadoInicial(String estadoInicial) {
        this.estadoInicial = estadoInicial;
    }

    /**
     * 
     * @return devuelve la cadena
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
