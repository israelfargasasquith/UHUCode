/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package APP;

import Controlador.ControlaPuntos;
import Modelo.Punto;

/**
 *
 * @author 34667
 */
public class Practica1AMC {

    public static void main(String[] args) {
        ControlaPuntos cp = new ControlaPuntos();
        cp.generaPuntosAleatorios(10, 0, 100);
    }
}
