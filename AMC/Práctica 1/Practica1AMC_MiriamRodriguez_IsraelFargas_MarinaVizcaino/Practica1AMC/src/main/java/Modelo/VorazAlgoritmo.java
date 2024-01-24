/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFileChooser;

/**
 *
 * @author marin
 */
public class VorazAlgoritmo {

    private double[][] coste;      // Matriz de coste
    private int num_nodos;          // Número de nodos 
    private ArrayList<Punto> vertices;       // Array de vértices
    private ArrayList<Punto> camino;

    private String CaminoRecorrido = "";
    private String CaminoRecorridoCostes = "";
    private Double CosteTotal;

    public String getCaminoRecorrido() {
        return CaminoRecorrido;
    }

    public void setCaminoRecorrido(String caminoRecorrido) {
        this.CaminoRecorrido = caminoRecorrido;
    }

    public String getCaminoRecorridoCostes() {
        return CaminoRecorridoCostes;
    }

    public void setCaminoRecorridoCostes(String CaminoRecorridoCostes) {
        this.CaminoRecorridoCostes = CaminoRecorridoCostes;
    }

    public Double getCosteTotal() {
        return CosteTotal;
    }

    public void setCosteTotal(Double coste) {
        this.CosteTotal = coste;
    }

    public VorazAlgoritmo() {

    }

    public VorazAlgoritmo(ArrayList<Punto> listaPuntos) {

        camino = new ArrayList();
        num_nodos = listaPuntos.size();
        vertices = listaPuntos;
        // Inicialización matriz adyacencia.
        coste = new double[num_nodos][num_nodos];
        for (int i = 0; i < num_nodos; i++) {
            for (int j = 0; j < num_nodos; j++) {
                coste[i][j] = Punto.distanciaEntrePuntos(listaPuntos.get(i), listaPuntos.get(j));
                if (coste[i][j] == 0) {
                    /*Esto se hace para representar que no hay una arista directa entre el punto i y el
                    punto j en el grago.*/
                    coste[i][j] = Double.POSITIVE_INFINITY;
                }
            }
        }

    }

    //BIDIRECCIONAL
    public ArrayList<ParDePuntos> AlgoritmoBidireccional() {
        String camino_recorrido_costes = "";
        Double costeT = 0.0;
        Punto p = new Punto();
        ArrayList<ParDePuntos> sol = new ArrayList<>();
        boolean[] nodosVisitados = new boolean[num_nodos];
        int puntoInicio = (int) (Math.random() * num_nodos);
        nodosVisitados[puntoInicio] = true;
        int nodoActual = puntoInicio;
        int nodosVisitadosCount = 1;

        while (nodosVisitadosCount < num_nodos) {

            int ciudadMasCercanaInicio = encontrarCiudadMasCercana(nodoActual, nodosVisitados);
            int ciudadMasCercanaFinal = encontrarCiudadMasCercanaAlFinal(nodoActual, nodosVisitados);

            if (coste[nodoActual][ciudadMasCercanaInicio] <= coste[nodoActual][ciudadMasCercanaFinal]) {

                nodosVisitados[ciudadMasCercanaInicio] = true;
                sol.add(new ParDePuntos(vertices.get(nodoActual), vertices.get(ciudadMasCercanaInicio)));

                nodoActual = ciudadMasCercanaInicio;

            } else {

                nodosVisitados[ciudadMasCercanaFinal] = true;
                sol.add(new ParDePuntos(vertices.get(nodoActual), vertices.get(ciudadMasCercanaFinal)));

                nodoActual = ciudadMasCercanaFinal;

            }

            nodosVisitadosCount++;
        }

        sol.add(new ParDePuntos(vertices.get(nodoActual), vertices.get(puntoInicio)));

        for (int i = 0; i < sol.size(); i++) {
            double distancia = p.distanciaEntrePuntos(sol.get(i).getA(), sol.get(i).getB());
            costeT = costeT + distancia;
            camino_recorrido_costes = camino_recorrido_costes + distancia + "   -   " + sol.get(i).getA().getId() + "  ,  " + sol.get(i).getB().getId() + "\n";
        }

        String camino_recorrido = "";
        for (int i = 0; i < sol.size(); i++) {
            camino_recorrido = camino_recorrido + sol.get(i).getA().getId();
            if (i < sol.size() - 1) {
                camino_recorrido = camino_recorrido + ", ";
            } else {
                camino_recorrido = camino_recorrido + ", " + sol.get(i).getB().getId();
            }
        }
        setCaminoRecorridoCostes(camino_recorrido_costes);
        setCaminoRecorrido(camino_recorrido);
        setCosteTotal(costeT);
        return sol;
    }

    private int encontrarCiudadMasCercana(int nodoActual, boolean[] nodosVisitados) {
        int ciudadMasCercana = -1;
        double distanciaMinima = Double.POSITIVE_INFINITY;

        for (int i = 0; i < num_nodos; i++) {
            if (!nodosVisitados[i] && coste[nodoActual][i] < distanciaMinima) {
                ciudadMasCercana = i;
                distanciaMinima = coste[nodoActual][i];
            }
        }

        return ciudadMasCercana;
    }

    private int encontrarCiudadMasCercanaAlFinal(int nodoActual, boolean[] nodosVisitados) {
        int ciudadMasCercana = -1;
        double distanciaMinima = Double.POSITIVE_INFINITY;

        for (int i = 0; i < num_nodos; i++) {
            if (!nodosVisitados[i] && coste[i][nodoActual] < distanciaMinima) {
                ciudadMasCercana = i;
                distanciaMinima = coste[i][nodoActual];
            }
        }

        return ciudadMasCercana;
    }

    //UNIDIRECCIONAL
    public ArrayList<ParDePuntos> AlgoritmoUnidireccional() {
        String camino_recorrido_costes = "";
        Punto p = new Punto();
        String camino_recorrido = "";
        Double costeT = 0.0;
        ArrayList<ParDePuntos> solucion = new ArrayList();
        boolean[] visitado = new boolean[num_nodos];

        // Elegir un nodo inicial aleatorio
        int nodoActual = (int) (Math.random() * num_nodos);
        int nodoPrimero = nodoActual;
        visitado[nodoActual] = true;
        int nodoSiguiente = 0;

        for (int i = 1; i < num_nodos; i++) {
            nodoSiguiente = encontrarNodoMasCercano(nodoActual, visitado);
            visitado[nodoSiguiente] = true;

            solucion.add(new ParDePuntos(vertices.get(nodoActual), vertices.get(nodoSiguiente)));

            nodoActual = nodoSiguiente;

        }
        // Añadir el último enlace para cerrar el circuito
        solucion.add(new ParDePuntos(vertices.get(nodoActual), vertices.get(nodoPrimero)));

        // Mostrar camino
        for (int i = 0; i < solucion.size(); i++) {
            double distancia = p.distanciaEntrePuntos(solucion.get(i).getA(), solucion.get(i).getB());
            costeT = costeT + distancia;
            camino_recorrido_costes = camino_recorrido_costes + distancia + "   -   " + solucion.get(i).getA().getId() + "  ,  " + solucion.get(i).getB().getId() + "\n";
        }

        for (int i = 0; i < solucion.size(); i++) {
            camino_recorrido = camino_recorrido + solucion.get(i).getA().getId();
            if (i < solucion.size() - 1) {
                camino_recorrido = camino_recorrido + ", ";
            } else {
                camino_recorrido = camino_recorrido + ", " + solucion.get(i).getB().getId();
            }
        }
        setCaminoRecorridoCostes(camino_recorrido_costes);
        setCaminoRecorrido(camino_recorrido);
        setCosteTotal(costeT);
        return solucion;
    }

    private int encontrarNodoMasCercano(int nodoActual, boolean[] visitado) {
        int nodoSiguiente = -1;
        double distanciaMinima = Double.POSITIVE_INFINITY;
        double distancia = 0;
        for (int i = 0; i < num_nodos; i++) {
            if (!visitado[i] && coste[nodoActual][i] < distanciaMinima) {
                distanciaMinima = coste[nodoActual][i];
                nodoSiguiente = i;
            }
        }

        return nodoSiguiente;
    }

    public void GuardarEnFichero() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar archivo");

        int userSelection = fileChooser.showSaveDialog(null);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            // Obtener la ubicación y el nombre del archivo seleccionado
            String fileToSave = fileChooser.getSelectedFile().getAbsolutePath();
            File fich = fileChooser.getSelectedFile();
            System.out.println("Se ha seleccionado guardar en: " + fileToSave);
            String nombre = fich.getName();

            try {
                // Crear un FileWriter para escribir en el archivo
                FileWriter writer = new FileWriter(fileToSave);

                // Escribir contenido en el archivo
                writer.write("NAME: " + nombre
                        + "\nTYPE: TOUR"
                        + "\nDIMENSION: " + vertices.size()
                        + "\nSOLUCION:" + getCosteTotal()
                        + "\nTOUR_SECTION\n");

                writer.write(getCaminoRecorrido());

                writer.write("\n");

                writer.write(getCaminoRecorridoCostes());

                writer.write("EOF");
                // Cerrar el FileWriter
                writer.close();

                System.out.println("Se ha escrito en el archivo: " + fileToSave);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


/*public ArrayList<ParDePuntos> AlgoritmoDijkstra() {

        int x, y;
        ArrayList<ParDePuntos> sol = new ArrayList<ParDePuntos>();

        boolean[] path = new boolean[num_nodos]; //Nodos visitados
        int[] minCoste = new int[num_nodos]; // Array para mantener el coste de la arista mínima de cada nodo

        path[0] = true; // Añadimos el primer verdadero
        for (int k = 1; k < num_nodos; k++) {
            path[k] = false;
        }

        minCoste[0] = 0;

        for (int k = 1; k < num_nodos; k++) {
            x = y = 0;

            for (int i = 0; i < num_nodos; i++) {
                for (int j = 0; j < num_nodos; j++) {
                    // Inicializamos el coste mínimo
                    if (path[i] && !path[j] && coste[i][j] < coste[x][y]) {
                        x = i;
                        y = j;
                    }
                }
            }

            minCoste[y] = x;
            path[y] = true;
        }

        for (int i = 1; i < num_nodos; i++) {
            sol.add(new ParDePuntos(vertices.get(i), vertices.get(minCoste[i])));
        }

        return sol;
    }
 */
