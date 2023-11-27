/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

import java.awt.Component;
import javax.swing.JOptionPane;

/**
 *
 * @author 34667
 */
public class VistaMensajes {

    public enum enumMensajes {
        ERROR,
        INFORMACION,
        SI_O_NO
    }

    public int muestraMensaje(Component parentComponent, String mensaje, Enum tipo) {
        int respuestaPregunta = 0;
        if (tipo.equals(enumMensajes.ERROR)) {
            JOptionPane.showMessageDialog(parentComponent, mensaje, "", JOptionPane.ERROR_MESSAGE);
        } else if (tipo.equals(enumMensajes.INFORMACION)) {
            JOptionPane.showMessageDialog(parentComponent, mensaje, "", JOptionPane.INFORMATION_MESSAGE);
        } else {
            respuestaPregunta = JOptionPane.showConfirmDialog(parentComponent, mensaje, "", JOptionPane.YES_NO_OPTION);

        }

        return respuestaPregunta;
    }
}

