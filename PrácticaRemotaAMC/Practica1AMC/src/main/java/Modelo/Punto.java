package Modelo;

import java.awt.Graphics;
import java.io.File;
import java.io.FileNotFoundException;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author marin
 */
public class Punto {

    private double x;
    private double y;
    private int id;
    private double tiempoAlg;
    private Mtime m = new Mtime();

    public Punto(double x, double y, int id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public Punto(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Punto() {

    }

    public double getX() {
        return x;
    }

    public int getId() {
        return id;
    }

    public double getY() {
        return y;
    }

    public double getTiempoAlg() {
        return tiempoAlg;
    }

    public void setTiempoAlg(double t) {
        this.tiempoAlg = t;
    }

    /**
     * Metodo que genera numPuntos puntos aleatorios
     *
     * @param numPuntos numero de puntos aleatorios que queremos generar
     * @param rangoMin numero minimo de las coordenadas x e y
     * @param rangoMax numero maximo de las coordenadas x e y
     * @return devuelve un ArrayList de puntos
     */
    public ArrayList<Punto> GeneraPuntos(int numPuntos, double rangoMin, double rangoMax) {
        double px, py;
        Punto p;
        Random rand = new Random();
        ArrayList<Punto> puntos = new ArrayList();
        for (int i = 0; i < numPuntos; i++) {
            //px=rand.doubles(0,rangoMax).findFirst().getAsDouble();
            //py=rand.doubles(0,rangoMax).findFirst().getAsDouble();
            px = rand.doubles(rangoMin, rangoMax).findFirst().getAsDouble();
            py = rand.doubles(rangoMin, rangoMax).findFirst().getAsDouble();
            p = new Punto(px, py);
            puntos.add(p);
        }

        /*for (int i = 0; i < numPuntos; i++) {
            System.out.println(Math.round(puntos.get(i).x * 100.0) / 100.0 + "  " + Math.round(puntos.get(i).y * 100.0) / 100.0);
        }*/
        return puntos;
    }

    public ArrayList<Punto> generaPuntosFichero(String path) throws FileNotFoundException {
        File f = new File(path);
        Scanner readOnlyNodes = new Scanner(f);
        ArrayList<Punto> puntos = new ArrayList();
        ArrayList<String[]> nodos = new ArrayList<>();
        int numPuntos;
        boolean untilNodos = false;

        while (readOnlyNodes.hasNextLine() && !untilNodos) {
            if (!untilNodos && readOnlyNodes.nextLine().equalsIgnoreCase("NODE_COORD_SECTION")) {
                untilNodos = true;
            }
        }
        while (readOnlyNodes.hasNextLine() && !readOnlyNodes.hasNext("EOF")) {
            nodos.add(readOnlyNodes.nextLine().split("\\s+"));
        }
        readOnlyNodes.close();
        for (String[] nodo : nodos) {
            System.out.println("id: " + nodo[0] + " X: " + nodo[1] + " Y: " + nodo[2]);
            puntos.add(new Punto(Double.parseDouble(nodo[1]), Double.parseDouble(nodo[2]), Integer.parseInt(nodo[0])));
        }

        numPuntos = puntos.get(puntos.size() - 1).getId();
        System.out.println("Numero de puntos = " + numPuntos);
        return puntos;
    }

   

    /**
     * Este metodo se encarga de calcular la distancia entre dos puntos pasados
     * por parametro
     *
     * @param a
     * @param b
     * @return
     */
    public double distanciaEntrePuntos(Punto a, Punto b) {
        double distancia = 0;
        distancia = sqrt(pow(a.x - b.x, 2) + pow(a.y - b.y, 2));
        return distancia;
    }

    /**
     * Metodo que devuelve los 2 puntos mas cercanos de un ArrayList de Puntos
     * pasado por parametros
     *
     * @param p
     * @return
     */
    public ParDePuntos Exhaustivo(ArrayList<Punto> p) {
        long ini = System.nanoTime();
        double distanciaMinima = Double.MAX_VALUE;
        double aux = 0;
        ParDePuntos PuntosMasCercanos;
        Punto p1 = new Punto();
        Punto p2 = new Punto();
        for (int i = 0; i < p.size(); i++) {
            for (int j = i + 1; j < p.size(); j++) {
                aux = distanciaEntrePuntos(p.get(i), p.get(j));
                if (aux < distanciaMinima) {
                    distanciaMinima = aux;
                    p1 = p.get(i);
                    p2 = p.get(j);
                }
            }
        }
        //sleep(4000);
        PuntosMasCercanos = new ParDePuntos(p1, p2);
        long fin = System.nanoTime();
        setTiempoAlg(m.performancecounterDiff(fin, ini));
        //System.out.println("Los puntos mas cercanos son: (" + p1.x + ", " + p1.y + ") y (" + p2.x + ", " + p2.y + ")");
        return PuntosMasCercanos;
    }

    public ParDePuntos ExhaustivoConPoda(ArrayList<Punto> p) {
        long ini = System.nanoTime();

        double distanciaMinima = Double.MAX_VALUE;
        double aux = 0;
        // ordenamos puntos por coordenada x
        //Hay que hacerlo con QuickSort o HeapSort

        /*Collections.sort(p, new Comparator<Punto>() {
            @Override
            public int compare(Punto c1, Punto c2) {
                return Double.compare(c1.x, c2.x);
            }
        });*/
        p = Ordenaquicksort(p, "x");

        for (Punto pu : p) {
            System.out.println(pu.x + "  " + pu.y);
        }
        //Realizamos una busqueda exhaustiva
        ParDePuntos ParMasCercano;
        Punto p1 = new Punto();
        Punto p2 = new Punto();
        double distanciaX;
        for (int i = 0; i < p.size(); i++) {
            for (int j = i + 1; j < p.size(); j++) {
                /*aux = distanciaEntrePuntos(p.get(i), p.get(j));
                if (aux < distanciaMinima) {
                    distanciaMinima = aux;
                    p1 = p.get(i);
                    p2 = p.get(j);
                }*/
                distanciaX = p.get(j).x - p.get(i).x;
                // Si distancia eje X es mayor que la distancia minima, no seguimos
                if (distanciaX >= distanciaMinima) {
                    break;
                }

                aux = distanciaEntrePuntos(p.get(i), p.get(j));
                if (aux < distanciaMinima) {
                    distanciaMinima = aux;
                    p1 = p.get(i);
                    p2 = p.get(j);
                }

            }
        }

        //System.out.println("CON PODA");
        ParMasCercano = new ParDePuntos(p1, p2);
        //System.out.println("Los puntos mas cercanos son: (" + p1.x + ", " + p1.y + ") y (" + p2.x + ", " + p2.y + ")");

        long fin = System.nanoTime();
        setTiempoAlg(m.performancecounterDiff(fin, ini));
        return ParMasCercano;
    }

    //ORDENACION
    public static ArrayList<Punto> Ordenaquicksort(ArrayList<Punto> puntos, String eje) {
        Quicksort(puntos, 0, puntos.size() - 1, eje);
        return puntos;
    }

    public static void Quicksort(ArrayList<Punto> puntos, int izq, int der, String eje) {

        if (izq < der) {
            int q = Partition(puntos, izq, der, eje);
            Quicksort(puntos, izq, q, eje);
            Quicksort(puntos, q + 1, der, eje);
        }
    }

    public static int Partition(ArrayList<Punto> punto, int p, int r, String eje) {

        if (eje.equalsIgnoreCase("x")) {

            double x = punto.get(p).getX();
            int i = p - 1;
            int j = r + 1;

            do {
                do {

                    j--;

                } while (punto.get(j).getX() > x);

                do {

                    i++;

                } while (punto.get(i).getX() < x);

                if (i < j) {
                    Punto aux = punto.get(i);
                    /*punto.get(i) = punto.get(j);
			punto.get(j) = aux;*/
                    punto.set(i, punto.get(j));
                    punto.set(j, aux);
                }

            } while (i < j);

            // return 0;
            return j;

        } else {

            double y = punto.get(p).getY();
            int i = p - 1;
            int j = r + 1;

            do {
                do {

                    j--;

                } while (punto.get(j).getY() > y);

                do {

                    i++;

                } while (punto.get(i).getY() < y);

                if (i < j) {
                    Punto aux = punto.get(i);
                    /*punto.get(i) = punto.get(j);
			punto.get(j) = aux;*/
                    punto.set(i, punto.get(j));
                    punto.set(j, aux);
                }

            } while (i < j);

            // return 0;
            return j;

        }

    }

    // DIVIDE Y VENCERÁS SIN MEJORA
    public ParDePuntos DyV(ArrayList<Punto> p) {
        long ini = System.nanoTime();
        p = Ordenaquicksort(p, "x");
        long fin = System.nanoTime();
        setTiempoAlg(m.performancecounterDiff(fin, ini));
        return DyVRec(p, 0, p.size() - 1);

    }

    public ParDePuntos DyVRec(ArrayList<Punto> p, int izq, int der) {
        ParDePuntos pDistMin;
        if (der - izq <= 3) {
            //Caso Base
            return Exhaustivo(p);
        } else {
            int medio = (izq + der) / 2;
            //Divide en dos mitades

            ParDePuntos pIzq, pDer;

            //llamadas recursivas
            pIzq = DyVRec(p, izq, medio);
            pDer = DyVRec(p, medio + 1, der);

            double distI, distD;
            distI = distanciaEntrePuntos(pIzq.getA(), pIzq.getB());
            distD = distanciaEntrePuntos(pDer.getA(), pDer.getB());

            //resolucion recursiva
            if (distI <= distD) {
                pDistMin = pIzq;

            } else {
                pDistMin = pDer;
            }

            /**
             * ***COMPROBACION DE LOS PUNTOS EN EL MEDIO*****
             */
            int a, b;
            ParDePuntos paux = null;
            for (a = medio + 1; a <= der; a++) {
                if (p.get(a).getX() - p.get(medio).getX() > distanciaEntrePuntos(pDistMin.getA(), pDistMin.getB())) {
                    break;
                }
            }

            for (b = medio; b >= izq; b--) {
                if (p.get(medio + 1).getX() - p.get(b).getX() > distanciaEntrePuntos(pDistMin.getA(), pDistMin.getB())) {
                    break;
                }
            }

            for (int c = b + 1; c <= medio; c++) {
                for (int d = medio + 1; d <= a - 1; d++) {
                    paux = new ParDePuntos(p.get(c), p.get(d));
                    if (distanciaEntrePuntos(p.get(c), p.get(d)) <= distanciaEntrePuntos(pDistMin.getA(), pDistMin.getB())) {
                        pDistMin = paux;

                    }
                }
            }

            for (int c = medio + 1; c <= a - 1; c++) {
                for (int d = b + 1; d <= medio; d++) {
                    paux = new ParDePuntos(p.get(c), p.get(d));
                    if (distanciaEntrePuntos(p.get(c), p.get(d)) <= distanciaEntrePuntos(pDistMin.getA(), pDistMin.getB())) {
                        pDistMin = paux;

                    }
                }
            }

        }
        return pDistMin;

    }

    // DIVIDE Y VENCERÁS CON MEJORA
    public ParDePuntos DyVMejorado(ArrayList<Punto> p) {
        long ini = System.nanoTime();

        p = Ordenaquicksort(p, "x");

        long fin = System.nanoTime();
        setTiempoAlg(m.performancecounterDiff(fin, ini));

        return DyVMejoradoRec(p, 0, p.size() - 1);

    }

    public ParDePuntos DyVMejoradoRec(ArrayList<Punto> p, int izq, int der) {

        ParDePuntos pDistMin;
        if (der - izq <= 3) {
            //Caso Base
            return Exhaustivo(p);
        } else {
            int medio = (izq + der) / 2;
            //Divide en dos mitades

            ParDePuntos pIzq, pDer;

            //llamadas recursivas
            pIzq = DyVRec(p, izq, medio);
            pDer = DyVRec(p, medio + 1, der);

            double distI, distD;
            distI = distanciaEntrePuntos(pIzq.getA(), pIzq.getB());
            distD = distanciaEntrePuntos(pDer.getA(), pDer.getB());

            //resolucion recursiva
            if (distI <= distD) {
                pDistMin = pIzq;

            } else {
                pDistMin = pDer;
            }

            /**
             * ***COMPROBACION DE LOS PUNTOS EN EL MEDIO*****
             */
            // Ordenamos punto franja media
            ArrayList<Punto> franjaMedia = new ArrayList<>();
            int a, b;
            ParDePuntos paux = null;
            for (a = medio + 1; a <= der; a++) {
                if (p.get(a).getX() - p.get(medio).getX() > distanciaEntrePuntos(pDistMin.getA(), pDistMin.getB())) {
                    break;
                } else {
                    franjaMedia.add(p.get(a));
                }
            }

            for (b = medio; b >= izq; b--) {
                if (p.get(medio + 1).getX() - p.get(b).getX() > distanciaEntrePuntos(pDistMin.getA(), pDistMin.getB())) {
                    break;
                } else {
                    franjaMedia.add(p.get(b));
                }
            }

            //ordenamos por eje Y
            franjaMedia = Ordenaquicksort(franjaMedia, "y");

            for (int i = 0; i < franjaMedia.size(); i++) {
                for (int j = i + 1; j < franjaMedia.size() && j < i + 12; j++) {
                    double dist = distanciaEntrePuntos(franjaMedia.get(i), franjaMedia.get(j));
                    if (dist < distanciaEntrePuntos(pDistMin.getA(), pDistMin.getB())) {
                        pDistMin = new ParDePuntos(franjaMedia.get(i), franjaMedia.get(j));
                    }
                }
            }

        }

        //System.out.println("***********************"+pDistMin.getA().getX()+", "+pDistMin.getA().getY());
        return pDistMin;

    }

    public ArrayList<Double> CompararTodosLosAlgoritmos(ArrayList<Punto> p) {
        ArrayList<Double> tiempos = new ArrayList();

        ArrayList<Punto> pDyV = p;
        ArrayList<Punto> pDyVMejor = p;
        ArrayList<Punto> pBusqPoda = p;
        Exhaustivo(p);
        System.out.println("El tiempo de Exhaustivo es: " + getTiempoAlg());
        tiempos.add(getTiempoAlg());
        DyV(pDyV);
        tiempos.add(getTiempoAlg());
        System.out.println("El tiempo de DyV es: " + getTiempoAlg());
        DyVMejorado(pDyVMejor);
        tiempos.add(getTiempoAlg());
        System.out.println("El tiempo de DyV mejorado es: " + getTiempoAlg());
        DyVMejorado(pBusqPoda);
        tiempos.add(getTiempoAlg());
        System.out.println("El tiempo de Busqueda con poda es: " + getTiempoAlg());

        return tiempos;
    }

    public void CompararTodosLosAlgoritmosTallas() {
        int talla[] = {100, 200, 300, 400, 500, 600, 700, 800, 900, 1000};

        Punto p = new Punto();
        for (int i = 0; i < 10; i++) {
            ArrayList<Punto> ap = p.GeneraPuntos(talla[i], 0, 1000);
            System.out.println("TALLA: " + talla[i]);
            CompararTodosLosAlgoritmos(ap);
        }
    }

    public void CompararDosAlgoritmos(String alg1, String alg2, ArrayList<Punto> p) {

        switch (alg1) {
            case "DyV": {
                DyV(p);
                break;
            }
            case "Exhaustivo": {
                Exhaustivo(p);
                break;
            }
            case "DyVMejorado": {
                DyVMejorado(p);
                break;
            }
            case "ExhaustivoConPoda": {
                ExhaustivoConPoda(p);
                break;
            }
        }

        switch (alg2) {
            case "DyV": {
                DyV(p);
                break;
            }
            case "Exhaustivo": {
                Exhaustivo(p);
                break;
            }
            case "DyVMejorado": {
                DyVMejorado(p);
                break;
            }
            case "ExhaustivoConPoda": {
                ExhaustivoConPoda(p);
                break;
            }
        }

    }
}
