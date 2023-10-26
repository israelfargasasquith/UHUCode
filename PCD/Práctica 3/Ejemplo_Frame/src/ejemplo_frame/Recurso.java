/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplo_frame;

/**
 *
 * @author pedro
 */
public class Recurso {

    private final int[] contadores = {0, 0};
    VistaCanvas canvas;

    public Recurso(VistaCanvas vista){
        canvas=vista;
    }

    /**
     *
     * @param cual
     */
    public synchronized void Incrementa(int cual) {
        contadores[cual]++;        
        System.out.println("El contador ["+cual+"] vale-> "+contadores[cual]);
        canvas.representa(contadores);
    }

    /**
     * @return the contadores
     */
    public int[] getContadores() {
        return contadores;
    }
}
